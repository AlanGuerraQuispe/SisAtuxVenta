package atux.handlers;

import atux.controllers.CProductoLocal;
import atux.modelbd.DetallePedidoVenta;
import atux.modelbd.PedidoVenta;
import atux.modelbd.ProductoLocal;
import com.atux.bean.promocion.PromocionDetalle;
import com.atux.bean.promocion.PromocionFlt;
import com.atux.comun.context.AppCtx;
import com.atux.infraestructura.jpa.LgtrProductoLocalRepository;
import com.atux.infraestructura.jpa.pojo.LgtrProductoLocalPK;
import com.atux.service.qryMapper.PromocionQryMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by MATRIX-JAVA on 16/2/2015.
 */
@Component
public class PromocionPedidoVentaInterceptorImpl implements PedidoVentaInterceptor {

    protected final Log LOG = LogFactory.getLog(getClass());

    @Autowired
    private PromocionQryMapper promocionQryMapper;

    @Autowired
    LgtrProductoLocalRepository lgtrProductoLocalRepository;

    private CProductoLocal cpProductoLocal = new CProductoLocal();


    @Transactional
    public void procesar(PedidoVenta pedidoVenta, DetallePedidoVenta item, Map result) throws Exception {

        PromocionFlt promocionFlt = new PromocionFlt();
        promocionFlt.setCoProducto(item.getCoProducto());
        promocionFlt.setCaAtendida(item.getCaAtendida());
        List<PromocionDetalle> promocionList = promocionQryMapper.findPromocionPorProducto(promocionFlt);
        LOG.info("Analizando producto <" + item.getCoProducto() + "," + item.getCaAtendida() + "> promociones <" + promocionList.size() + ">");
        DetallePedidoVenta detallePedidoVentaOld = null;
        for (PromocionDetalle promocion : promocionList) {

            if (pedidoVenta.tienePromocion(promocion)) {
                detallePedidoVentaOld = pedidoVenta.getItemPromocion(promocion);
            }

            int multiplo = item.getCaAtendida() / promocion.getCaProducto().intValue();

            int caTotalProductoP = multiplo * promocion.getCaProductoP().intValue();

            LgtrProductoLocalPK productoPromocionPk = new LgtrProductoLocalPK(AppCtx.instance().getCoCompania(), AppCtx.instance().getCoLocal(),
                    promocion.getCoProductoP());

            int stockActual = lgtrProductoLocalRepository.obtenerStockActual(productoPromocionPk);

            LOG.info("promocion <" + promocion.getCoPromocion() + "> solicitando stock de <" + caTotalProductoP + "> de <" + stockActual + "> producto <" + promocion.getCoProductoP() + ">");


            if (stockActual < caTotalProductoP) {
                LOG.info("No existe stock suficiente en el local ");
                while (stockActual < caTotalProductoP) {
                    caTotalProductoP -= promocion.getCaProductoP().intValue();
                }
                if (caTotalProductoP == 0)
                    continue;
            }
            if (detallePedidoVentaOld != null && detallePedidoVentaOld.getCaAtendida().equals(caTotalProductoP)) {
                LOG.info("promocion <" + promocion.getCoPromocion() + "> producto <" + promocion.getCoProductoP() + "> no existen cambios");
                return;
            }


            String mensajeImpresora = " El Cliente ha ganado " + caTotalProductoP + " de " + promocion.getDeProductoP();
            String mensajeVentana = "El Cliente ha ganado\n" + "* " +
                    caTotalProductoP + " " + promocion.getUnidadProductoP() + "(S) de " +
                    promocion.getDeProductoP() + "\n";

            ProductoLocal productoLocal = (ProductoLocal) cpProductoLocal.getProductosPedidoVenta(promocion.getCoProductoP()).get(0);
            DetallePedidoVenta detallePedidoVenta = null;
            if (detallePedidoVentaOld != null) {
                detallePedidoVenta = detallePedidoVentaOld;
                detallePedidoVenta.setCaAtendida(caTotalProductoP);
            } else {
                detallePedidoVenta = new DetallePedidoVenta();
                detallePedidoVenta.setNuItemPedido(pedidoVenta.getDetallePedidoVenta().size() - 1);
                detallePedidoVenta.agregarItemPromocion(promocion.getCoPromocion(), promocion.getCoProducto(), productoLocal, caTotalProductoP);
                pedidoVenta.getDetallePedidoVenta().add(pedidoVenta.getDetallePedidoVenta().size() - 1, detallePedidoVenta);
            }
            result.put("itemPedidoVenta", detallePedidoVenta);
            result.put("itemProductoLocal", productoLocal);
            result.put("caTotalProductoP", caTotalProductoP);
            result.put("mensajeImpresora", mensajeImpresora);
            result.put("mensajeVentana", mensajeVentana);
            result.put("coProductoPrincipal", promocion.getCoProducto());
            result.put("coPromocion", promocion.getCoPromocion());


//            lgtrProductoLocalRepository.updateStockComprometido(productoPromocionPk,caTotalProductoP);

        }
    }

    @Transactional
    public void quitar(PedidoVenta pedidoVenta, DetallePedidoVenta item, Map result) {

        List<DetallePedidoVenta> detallePedidoList = pedidoVenta.getDetallePedidoVenta();
        LOG.info("Eliminando producto <" + item.getCoProducto() + "," + item.getCaAtendida() + "> ");
        List<DetallePedidoVenta> itemsEliminados = new ArrayList<DetallePedidoVenta>();
        for (DetallePedidoVenta detalle : detallePedidoList) {
            if (item.getCoProducto().equals(detalle.getCoProductoPrincipal()) && StringUtils.isNotBlank(detalle.getCoVentaPromocion())) {
                LOG.info("Eliminando producto de promocion <" + detalle.getCoProducto() + ">");
                itemsEliminados.add(detalle);
            }
        }
        result.put("itemsEliminados", itemsEliminados);
    }
}
