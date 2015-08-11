package com.atux.bean.precios.support;

import com.atux.bean.kardex.Producto;
import com.aw.core.util.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by MATRIX-JAVA on 01/12/2014.
 */
public class PrecioProvider {
    protected final Log LOG = LogFactory.getLog(getClass());

    //+B18*C18*(1-D18/100)*(1+E18/100)
    public void calcularDsctoPorPVP(Producto backBean) {
        LOG.info("-- calcularDsctoPorPVP Inicio --");
        backBean.setPrecioVenta(
                NumberUtils.nvlZero(backBean.getPrecioProveedor())
                        .multiply(Producto.FACTOR_PRECIO)
                        .multiply(new BigDecimal(1).add(backBean.getVaImpuesto()
                                .divide(new BigDecimal(100), 2, RoundingMode.HALF_UP))));

        LOG.info("precioVenta <" + backBean.getPrecioVenta() + ">");
        BigDecimal porcentajeDescuento =
                (new BigDecimal(1)
                        .subtract(
                                NumberUtils.nvlZero(backBean.getPrecioVentaPublico())
                                        .divide(NumberUtils.nvlZero(backBean.getPrecioVenta()), 4, RoundingMode.HALF_UP)))
                        .multiply(new BigDecimal(100));
        LOG.info("porcentajeDescuento <" + porcentajeDescuento + ">");
        if (porcentajeDescuento.doubleValue() < 0) {
            porcentajeDescuento = BigDecimal.ZERO;
        }
        backBean.setPorcentajeDescuento(porcentajeDescuento);
        backBean.recalcularPrecios();
        LOG.info("-- calcularDsctoPorPVP Fin --");
    }

    public void calcularPVPPorDscto(Producto backBean) {
        backBean.calcularPrecioVentaPublico();
        backBean.recalcularPrecios();
    }

    public void calcularPVPPorPrecioProveedor(Producto backBean) {
        backBean.calcularPrecioVentaPublico();
        backBean.recalcularPrecios();
    }


}
