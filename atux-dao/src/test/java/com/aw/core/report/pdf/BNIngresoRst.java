package com.aw.core.report.pdf;

import java.math.BigDecimal;

/**
 * User: gmc
 * Date: 05/10/2009
 */
public class BNIngresoRst {
    protected int itemOc;
    protected String rc;
    protected String  codigo;
    protected String descripcion;
    protected String unidadMedida;
    protected BigDecimal caComprada;
    protected BigDecimal caLlegada;
    protected BigDecimal caPendiente;
    protected String ubicacion;
    protected BigDecimal stock;

    public BNIngresoRst(int itemOc, String rc, String codigo, String descripcion, String unidadMedida, BigDecimal caComprada, BigDecimal caLlegada, BigDecimal caPendiente, String ubicacion, BigDecimal stock) {
        this.itemOc = itemOc;
        this.rc = rc;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.unidadMedida = unidadMedida;
        this.caComprada = caComprada;
        this.caLlegada = caLlegada;
        this.caPendiente = caPendiente;
        this.ubicacion = ubicacion;
        this.stock = stock;
    }

    public int getItemOc() {
        return itemOc;
    }

    public void setItemOc(int itemOc) {
        this.itemOc = itemOc;
    }

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public BigDecimal getCaComprada() {
        return caComprada;
    }

    public void setCaComprada(BigDecimal caComprada) {
        this.caComprada = caComprada;
    }

    public BigDecimal getCaLlegada() {
        return caLlegada;
    }

    public void setCaLlegada(BigDecimal caLlegada) {
        this.caLlegada = caLlegada;
    }

    public BigDecimal getCaPendiente() {
        return caPendiente;
    }

    public void setCaPendiente(BigDecimal caPendiente) {
        this.caPendiente = caPendiente;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }
}
