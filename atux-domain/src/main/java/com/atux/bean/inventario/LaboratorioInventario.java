package com.atux.bean.inventario;

import com.atux.bean.precios.Laboratorio;

import java.math.BigDecimal;

/**
 * Created by MATRIX-JAVA on 24/2/2015.
 */
public class LaboratorioInventario extends Laboratorio {

    private String nuSecTomaInventario;
    private BigDecimal caProducto;

    public String getNuSecTomaInventario() {
        return nuSecTomaInventario;
    }

    public void setNuSecTomaInventario(String nuSecTomaInventario) {
        this.nuSecTomaInventario = nuSecTomaInventario;
    }

    public BigDecimal getCaProducto() {
        return caProducto;
    }

    public void setCaProducto(BigDecimal caProducto) {
        this.caProducto = caProducto;
    }
}
