package com.atux.bean.inventario;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by MATRIX-JAVA on 1/3/2015.
 */
public class TomaInventarioProducto {

    private String coCompania;

    private String coProducto;

    private String unidadFraccion;
    private String unidadProducto;
    private String deProducto;
    private String deLaboratorio;
    private String coLaboratorio;
    private String nuSecTomaInventario;
    private BigDecimal caEntero;
    private BigDecimal caFraccion;
    private BigDecimal vaFraccion;
    private String inProdFraccionado;

    public BigDecimal getCantidad() {
        if ("S".equals(inProdFraccionado)) {
            return (caEntero.multiply(vaFraccion).add(caFraccion));
        }
        return caEntero;
    }

    public String getInProdFraccionado() {
        return inProdFraccionado;
    }

    public void setInProdFraccionado(String inProdFraccionado) {
        this.inProdFraccionado = inProdFraccionado;
    }

    public String getUnidad() {
        return "S".equals(getInProdFraccionado()) ? getUnidadFraccion() : getUnidadProducto();
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoProducto() {
        return coProducto;
    }

    public void setCoProducto(String coProducto) {
        this.coProducto = coProducto;
    }

    public String getUnidadFraccion() {
        return unidadFraccion;
    }

    public void setUnidadFraccion(String unidadFraccion) {
        this.unidadFraccion = unidadFraccion;
    }

    public String getUnidadProducto() {
        return unidadProducto;
    }

    public void setUnidadProducto(String unidadProducto) {
        this.unidadProducto = unidadProducto;
    }

    public String getDeProducto() {
        return deProducto;
    }

    public void setDeProducto(String deProducto) {
        this.deProducto = deProducto;
    }

    public BigDecimal getCaEntero() {
        return caEntero;
    }

    public void setCaEntero(BigDecimal caEntero) {
        this.caEntero = caEntero;
    }

    public BigDecimal getCaFraccion() {
        return caFraccion;
    }

    public void setCaFraccion(BigDecimal caFraccion) {
        this.caFraccion = caFraccion;
    }

    public String getDeLaboratorio() {
        return deLaboratorio;
    }

    public void setDeLaboratorio(String deLaboratorio) {
        this.deLaboratorio = deLaboratorio;
    }

    public String getCoLaboratorio() {
        return coLaboratorio;
    }

    public void setCoLaboratorio(String coLaboratorio) {
        this.coLaboratorio = coLaboratorio;
    }

    public String getNuSecTomaInventario() {
        return nuSecTomaInventario;
    }

    public void setNuSecTomaInventario(String nuSecTomaInventario) {
        this.nuSecTomaInventario = nuSecTomaInventario;
    }

    public BigDecimal getVaFraccion() {
        return vaFraccion;
    }

    public void setVaFraccion(BigDecimal vaFraccion) {
        this.vaFraccion = vaFraccion;
    }
}
