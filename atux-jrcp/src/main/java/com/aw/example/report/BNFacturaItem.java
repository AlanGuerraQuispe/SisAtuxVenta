package com.aw.example.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Julio C. Macavilca
 * Date: 17/06/2008
 */
public class BNFacturaItem {
    // Datos encabezado
    Integer cantidad = new Integer(1);
    String codigo;
    List<String> descs = new ArrayList<String>();
    BigDecimal total;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<String> getDescs() {
        return descs;
    }

    public void setDescs(List<String> descs) {
        this.descs = descs;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}