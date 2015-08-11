package com.atux.bean.consulta;

import java.math.BigDecimal;

/**
 * Created by MATRIX-JAVA on 19/11/2014.
 */
public class ReporteVentaFormaPagoItem {

    private String coFormaPago;
    private String deFormaPago;
    private BigDecimal vaFormaPago;

    public String getCoFormaPago() {
        return coFormaPago;
    }

    public void setCoFormaPago(String coFormaPago) {
        this.coFormaPago = coFormaPago;
    }

    public BigDecimal getVaFormaPago() {
        return vaFormaPago;
    }

    public void setVaFormaPago(BigDecimal vaFormaPago) {
        this.vaFormaPago = vaFormaPago;
    }

    public String getDeFormaPago() {
        return deFormaPago;
    }

    public void setDeFormaPago(String deFormaPago) {
        this.deFormaPago = deFormaPago;
    }
}
