package atux.handlers;

import atux.controllers.CProductoLocal;
import atux.modelbd.DetallePedidoVenta;
import atux.modelbd.PedidoVenta;
import atux.modelbd.ProductoLocal;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import com.atux.bean.donacion.IngresarDonacionDetalle;
import com.atux.bean.promocion.PromocionDetalle;
import com.atux.bean.promocion.PromocionFlt;
import com.atux.comun.context.AppCtx;
import com.atux.infraestructura.jpa.LgtrProductoLocalRepository;
import com.atux.infraestructura.jpa.VttrPedidoDonacionRepository;
import com.atux.infraestructura.jpa.pojo.LgtrProductoLocalPK;
import com.atux.infraestructura.jpa.pojo.VttrPedidoDonacion;
import com.atux.infraestructura.jpa.pojo.VttrPedidoDonacionPK;
import com.atux.service.qryMapper.DonacionQryMapper;
import com.atux.service.qryMapper.PromocionQryMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by MATRIX-JAVA on 16/2/2015.
 */
@Component
public class DonacionServiceImpl implements DonacionService {

    protected final Log LOG = LogFactory.getLog(getClass());

    @Autowired
    private VttrPedidoDonacionRepository repository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void anularDonacion(String nuPedido) {

        List<VttrPedidoDonacion> donacionList=repository.obtenerDonaciones(AppCtx.instance().getCoCompania(),
                AppCtx.instance().getCoLocal(),
                nuPedido
                );
        for (VttrPedidoDonacion vttrPedidoDonacion : donacionList) {
            vttrPedidoDonacion.setFechaAnulaPedido(new Date());
            vttrPedidoDonacion.setIdAnulaPedido(AppCtx.instance().getUsuario().getIdUsuario());
            vttrPedidoDonacion.setInAnulaPedido("S");
            repository.save(vttrPedidoDonacion);
        }
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void grabarDonacion(PedidoVenta pedidoVenta, List<IngresarDonacionDetalle> donacionList) {
        for (IngresarDonacionDetalle donacionDetalle : donacionList) {
            VttrPedidoDonacion pedidoDonacion = new VttrPedidoDonacion();
            VttrPedidoDonacionPK id = new VttrPedidoDonacionPK();
            id.setCoCompania(AppCtx.instance().getCoCompania());
            id.setCoLocal(AppCtx.instance().getCoLocal());
            id.setCoInstitucion(donacionDetalle.getDonacion().getCoInstitucion());
            id.setNuPedido(pedidoVenta.getNuPedido());
            pedidoDonacion.setFePedido(pedidoVenta.getFePedido());
            pedidoDonacion.setTiComprobante(pedidoVenta.getTiComprobante());
            pedidoDonacion.setTiPedido(pedidoVenta.getTiPedido());
            pedidoDonacion.setCoMoneda(pedidoVenta.getCoMoneda());
            pedidoDonacion.setId(id);
            pedidoDonacion.setEsPedidoVenta("P");
            pedidoDonacion.setVaDonacion(donacionDetalle.getMonto());
            repository.save(pedidoDonacion);
        }
    }

    public double calcularMontoSugerido(PedidoVenta pedidoVenta, double montoRedondear) {

        double montoTotalVentaInicial = pedidoVenta.getVaTotalPrecioVenta() - pedidoVenta.getVaSaldoRedondeo();

        LOG.info("montoTotalVentaInicial <" + montoTotalVentaInicial + ">");

        double donacionSugerida = getRedondeoEspecial(montoTotalVentaInicial, montoRedondear);
        ;

        return donacionSugerida;
    }

    private double getRedondeoEspecial(double montoTotal, double montoRedondear) {
        double montoDecimal = AtuxUtility.trunc((AtuxUtility.getDecimalNumberRedondeado(montoTotal - AtuxUtility.trunc(montoTotal)) * 10));
        montoDecimal /= 10;

        double montoEntero = AtuxUtility.trunc(montoTotal);
        String montoReal = AtuxUtility.formatNumber(montoTotal);

        String centimosStr = montoReal.substring(montoReal.indexOf(".") + 1, montoReal.indexOf(".") + 2);
        double centimos = AtuxUtility.getDecimalNumber(centimosStr) * 0.1;

        double montoBase = montoEntero + centimos;

        double montoFinal = 0.0;
        if (montoRedondear == 0.1) {
            montoFinal = montoEntero + centimos + montoRedondear;
        } else if (montoRedondear == 0.5) {
            if (montoDecimal > montoRedondear)
                montoFinal = montoEntero + montoRedondear * 2;
            else
                montoFinal = montoEntero + montoRedondear;
        } else {
            montoFinal = montoBase + montoRedondear;
        }
        if (AtuxUtility.getDecimalNumberRedondeado(montoFinal - montoTotal) == montoRedondear) return 0;

        return AtuxUtility.getDecimalNumberRedondeado(montoFinal - montoTotal);
    }

}
