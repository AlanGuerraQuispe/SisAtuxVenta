package com.atux.bean.kardex;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by JAVA on 15/11/2014.
 */
public class KardexDetalle {

    private Date feKardex;
    private String tipoMovimiento;
    private String lote;
    private Date feVencimiento;
    private String nuReferencia;
    private String noProveedor;
    private BigDecimal vaFraccion;
    private String usuario;
    private String sistema;
    private String justificacion;
    private BigDecimal precioPromedio;
    private BigDecimal costoPromedio;
    private String deMotivo;
    private String coMotivo;
    private String coGrupoMotivo;
    private String nuDocumento;
    private BigDecimal caInicial;
    private BigDecimal caMovimiento;
    private BigDecimal caFinal;
    private BigDecimal caEnteroInicial;
    private BigDecimal caFraccionInicial;
    private BigDecimal caEnteroMovimiento;
    private BigDecimal caFraccionMovimiento;
    private BigDecimal caEnteroFinal;
    private BigDecimal caFraccionFinal;


    public Date getFeKardex() {
        return feKardex;
    }

    public void setFeKardex(Date feKardex) {
        this.feKardex = feKardex;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Date getFeVencimiento() {
        return feVencimiento;
    }

    public void setFeVencimiento(Date feVencimiento) {
        this.feVencimiento = feVencimiento;
    }

    public String getNuReferencia() {
        return nuReferencia;
    }

    public void setNuReferencia(String nuReferencia) {
        this.nuReferencia = nuReferencia;
    }

    public String getNoProveedor() {
        return noProveedor;
    }

    public void setNoProveedor(String noProveedor) {
        this.noProveedor = noProveedor;
    }

    public BigDecimal getVaFraccion() {
        return vaFraccion;
    }

    public void setVaFraccion(BigDecimal vaFraccion) {
        this.vaFraccion = vaFraccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getPrecioPromedio() {
        return precioPromedio;
    }

    public void setPrecioPromedio(BigDecimal precioPromedio) {
        this.precioPromedio = precioPromedio;
    }

    public String getDeMotivo() {
        return deMotivo;
    }

    public void setDeMotivo(String deMotivo) {
        this.deMotivo = deMotivo;
    }

    public String getNuDocumento() {
        return nuDocumento;
    }

    public void setNuDocumento(String nuDocumento) {
        this.nuDocumento = nuDocumento;
    }

    public BigDecimal getCaEnteroInicial() {
        return caEnteroInicial;
    }

    public void setCaEnteroInicial(BigDecimal caEnteroInicial) {
        this.caEnteroInicial = caEnteroInicial;
    }

    public BigDecimal getCaFraccionInicial() {
        return caFraccionInicial;
    }

    public void setCaFraccionInicial(BigDecimal caFraccionInicial) {
        this.caFraccionInicial = caFraccionInicial;
    }

    public BigDecimal getCaEnteroMovimiento() {
        return caEnteroMovimiento;
    }

    public void setCaEnteroMovimiento(BigDecimal caEnteroMovimiento) {
        this.caEnteroMovimiento = caEnteroMovimiento;
    }

    public BigDecimal getCaFraccionMovimiento() {
        return caFraccionMovimiento;
    }

    public void setCaFraccionMovimiento(BigDecimal caFraccionMovimiento) {
        this.caFraccionMovimiento = caFraccionMovimiento;
    }

    public BigDecimal getCaEnteroFinal() {
        return caEnteroFinal;
    }

    public void setCaEnteroFinal(BigDecimal caEnteroFinal) {
        this.caEnteroFinal = caEnteroFinal;
    }

    public BigDecimal getCaFraccionFinal() {
        return caFraccionFinal;
    }

    public void setCaFraccionFinal(BigDecimal caFraccionFinal) {
        this.caFraccionFinal = caFraccionFinal;
    }

    public BigDecimal getCaInicial() {
        return caInicial;
    }

    public void setCaInicial(BigDecimal caInicial) {
        this.caInicial = caInicial;
    }

    public BigDecimal getCaMovimiento() {
        return caMovimiento;
    }

    public void setCaMovimiento(BigDecimal caMovimiento) {
        this.caMovimiento = caMovimiento;
    }

    public BigDecimal getCaFinal() {
        return caFinal;
    }

    public void setCaFinal(BigDecimal caFinal) {
        this.caFinal = caFinal;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public String getCoMotivo() {
        return coMotivo;
    }

    public void setCoMotivo(String coMotivo) {
        this.coMotivo = coMotivo;
    }

    public String getCoGrupoMotivo() {
        return coGrupoMotivo;
    }

    public void setCoGrupoMotivo(String coGrupoMotivo) {
        this.coGrupoMotivo = coGrupoMotivo;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public BigDecimal getCostoPromedio() {
        return costoPromedio;
    }

    public void setCostoPromedio(BigDecimal costoPromedio) {
        this.costoPromedio = costoPromedio;
    }
}
