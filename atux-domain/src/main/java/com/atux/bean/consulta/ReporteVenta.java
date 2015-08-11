package com.atux.bean.consulta;

import com.aw.core.util.NumberUtils;
import com.aw.core.validation.annotation.MoneyFormat;
import com.aw.core.validation.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by MATRIX-JAVA on 19/11/2014.
 */
public class ReporteVenta {
    private Date feInicio;
    private Date feFin;
    private String tipoDocumento;
    private BigDecimal vaPrecioPromedio;
    private BigDecimal vaUtilidad;
    private BigDecimal porcentajeUtilidad;
    private BigDecimal porcentajeMargen;

    private BigDecimal vaEmitido;
    private BigDecimal vaTicket;
    private BigDecimal vaFactura;
    private BigDecimal vaDevolucion;
    private BigDecimal vaOtro;

    private BigDecimal vaTotal;
    private BigDecimal documentoTotal;
    private BigDecimal grupoATotal;
    private BigDecimal margenGrupoA;
    private BigDecimal nuPedidos;
    private BigDecimal totalBono;
    private BigDecimal margenPP;
    private BigDecimal totalPP;
    private BigDecimal formaPagoTotal;

    List<ReporteVentaItem> items;
    List<ReporteVentaFormaPagoItem> formaPagoList;

    public Date getFeInicio() {
        return feInicio;
    }

    public void setFeInicio(Date feInicio) {
        this.feInicio = feInicio;
    }

    public Date getFeFin() {
        return feFin;
    }

    public void setFeFin(Date feFin) {
        this.feFin = feFin;
    }

    @MoneyFormat
    public BigDecimal getVaPrecioPromedio() {
        return vaPrecioPromedio;
    }

    public void setVaPrecioPromedio(BigDecimal vaPrecioPromedio) {
        this.vaPrecioPromedio = vaPrecioPromedio;
    }

    @MoneyFormat
    public BigDecimal getVaTotal() {
        return vaTotal;
    }

    public void setVaTotal(BigDecimal vaTotal) {
        this.vaTotal = vaTotal;
    }

    @MoneyFormat
    public BigDecimal getVaUtilidad() {
        return vaUtilidad;
    }

    public void setVaUtilidad(BigDecimal vaUtilidad) {
        this.vaUtilidad = vaUtilidad;
    }

    @MoneyFormat
    public BigDecimal getPorcentajeUtilidad() {
        return porcentajeUtilidad;
    }

    public void setPorcentajeUtilidad(BigDecimal porcentajeUtilidad) {
        this.porcentajeUtilidad = porcentajeUtilidad;
    }

    @MoneyFormat
    public BigDecimal getPorcentajeMargen() {
        return porcentajeMargen;
    }

    public void setPorcentajeMargen(BigDecimal porcentajeMargen) {
        this.porcentajeMargen = porcentajeMargen;
    }

    public List<ReporteVentaItem> getItems() {
        return items;
    }

    public void setItems(List<ReporteVentaItem> items) {
        this.items = items;
    }

    @MoneyFormat
    public BigDecimal getVaEmitido() {
        return vaEmitido;
    }

    public void setVaEmitido(BigDecimal vaEmitido) {
        this.vaEmitido = vaEmitido;
    }

    @MoneyFormat
    public BigDecimal getVaTicket() {
        return vaTicket;
    }

    public void setVaTicket(BigDecimal vaTicket) {
        this.vaTicket = vaTicket;
    }

    @MoneyFormat
    public BigDecimal getVaFactura() {
        return vaFactura;
    }

    public void setVaFactura(BigDecimal vaFactura) {
        this.vaFactura = vaFactura;
    }

    @MoneyFormat
    public BigDecimal getVaDevolucion() {
        return vaDevolucion;
    }

    public void setVaDevolucion(BigDecimal vaDevolucion) {
        this.vaDevolucion = vaDevolucion;
    }

    @MoneyFormat
    public BigDecimal getVaOtro() {
        return vaOtro;
    }

    public void setVaOtro(BigDecimal vaOtro) {
        this.vaOtro = vaOtro;
    }

    public List<ReporteVentaFormaPagoItem> getFormaPagoList() {
        return formaPagoList;
    }

    public void setFormaPagoList(List<ReporteVentaFormaPagoItem> formaPagoList) {
        this.formaPagoList = formaPagoList;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @MoneyFormat
    public BigDecimal getDocumentoTotal() {
        return documentoTotal;
    }

    public void setDocumentoTotal(BigDecimal documentoTotal) {
        this.documentoTotal = documentoTotal;
    }

    @MoneyFormat
    public BigDecimal getFormaPagoTotal() {
        return formaPagoTotal;
    }

    public void setFormaPagoTotal(BigDecimal formaPagoTotal) {
        this.formaPagoTotal = formaPagoTotal;
    }

    public void sumDocumentoTotal() {
        setDocumentoTotal(
                NumberUtils.nvlZero(getVaTicket())
                        .add(NumberUtils.nvlZero(getVaFactura()))
                        .add(NumberUtils.nvlZero(getVaDevolucion()))
        );
    }

    @MoneyFormat
    public BigDecimal getGrupoATotal() {
        return grupoATotal;
    }

    public void setGrupoATotal(BigDecimal grupoATotal) {
        this.grupoATotal = grupoATotal;
    }

    @MoneyFormat
    public BigDecimal getMargenGrupoA() {
        return margenGrupoA;
    }

    public void setMargenGrupoA(BigDecimal margenGrupoA) {
        this.margenGrupoA = margenGrupoA;
    }

    @NumberFormat
    public BigDecimal getNuPedidos() {
        return nuPedidos;
    }

    public void setNuPedidos(BigDecimal nuPedidos) {
        this.nuPedidos = nuPedidos;
    }

    @MoneyFormat
    public BigDecimal getTotalBono() {
        return totalBono;
    }

    public void setTotalBono(BigDecimal totalBono) {
        this.totalBono = totalBono;
    }

    @MoneyFormat
    public BigDecimal getMargenPP() {
        return margenPP;
    }

    public void setMargenPP(BigDecimal margenPP) {
        this.margenPP = margenPP;
    }
    @MoneyFormat
    public BigDecimal getTotalPP() {
        return totalPP;
    }

    public void setTotalPP(BigDecimal totalPP) {
        this.totalPP = totalPP;
    }
}
