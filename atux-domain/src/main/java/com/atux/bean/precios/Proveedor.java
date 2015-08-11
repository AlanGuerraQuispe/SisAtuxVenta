package com.atux.bean.precios;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by MATRIX-JAVA on 24/11/2014.
 */
public class Proveedor {

    private Date feFacturacion;
    private String search;
    private String coProveedor;
    private String noProveedor;
    private String condicion;
    private String nuGuia;
    private BigDecimal caTotal;
    private BigDecimal precioTotal;
    private BigDecimal dsctoF;
    private BigDecimal dsctoP;

    public Date getFeFacturacion() {
        return feFacturacion;
    }

    public void setFeFacturacion(Date feFacturacion) {
        this.feFacturacion = feFacturacion;
    }

    public String getCoProveedor() {
        return coProveedor;
    }

    public void setCoProveedor(String coProveedor) {
        this.coProveedor = coProveedor;
    }

    public String getNoProveedor() {
        return noProveedor;
    }

    public void setNoProveedor(String noProveedor) {
        this.noProveedor = noProveedor;
    }

    public BigDecimal getCaTotal() {
        return caTotal;
    }

    public void setCaTotal(BigDecimal caTotal) {
        this.caTotal = caTotal;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public BigDecimal getDsctoF() {
        return dsctoF;
    }

    public void setDsctoF(BigDecimal dsctoF) {
        this.dsctoF = dsctoF;
    }

    public BigDecimal getDsctoP() {
        return dsctoP;
    }

    public void setDsctoP(BigDecimal dsctoP) {
        this.dsctoP = dsctoP;
    }

    public String getNuGuia() {
        return nuGuia;
    }

    public void setNuGuia(String nuGuia) {
        this.nuGuia = nuGuia;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
