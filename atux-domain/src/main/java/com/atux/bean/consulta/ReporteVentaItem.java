package com.atux.bean.consulta;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by MATRIX-JAVA on 19/11/2014.
 */
public class ReporteVentaItem {

    private String nuPedido;
    private String emitido;
    private String motivo;
    private String tiComprobante;
    private String noCliente;
    private String noVendedor;
    private String nuComprobante;
    private String esPedidoVenta;
    private String nuComprobanteCustom;
    private BigDecimal imp;
    private Date fePedido;
    private String coProducto;
    private String deProducto;
    private String unidadProducto;
    private String deLaboratorio;
    private BigDecimal caAtendida;
    private BigDecimal vaImpuesto;
    private BigDecimal vaPrecioVentaSinIgv;
    private BigDecimal vaVenta;
    private BigDecimal vaPrecioVenta;
    private BigDecimal vaPrecioPromedio;
    private BigDecimal vaMargen;
    private BigDecimal vaBono;
    private BigDecimal vaDescuento;
    private BigDecimal vaPrecioPublico;

    public String getProductoLabel(){
        return coProducto+"-"+deProducto+"-"+unidadProducto;
    }


    public String getEmitido() {
        return emitido;
    }

    public void setEmitido(String emitido) {
        this.emitido = emitido;
    }



    public String getCoProducto() {
        return coProducto;
    }

    public void setCoProducto(String coProducto) {
        this.coProducto = coProducto;
    }

    public String getDeProducto() {
        return deProducto;
    }

    public void setDeProducto(String deProducto) {
        this.deProducto = deProducto;
    }

    public String getUnidadProducto() {
        return unidadProducto;
    }

    public void setUnidadProducto(String unidadProducto) {
        this.unidadProducto = unidadProducto;
    }

    public String getDeLaboratorio() {
        return deLaboratorio;
    }

    public void setDeLaboratorio(String deLaboratorio) {
        this.deLaboratorio = deLaboratorio;
    }

    public BigDecimal getVaDescuento() {
        return vaDescuento;
    }

    public void setVaDescuento(BigDecimal vaDescuento) {
        this.vaDescuento = vaDescuento;
    }

    public BigDecimal getVaPrecioPublico() {
        return vaPrecioPublico;
    }

    public void setVaPrecioPublico(BigDecimal vaPrecioPublico) {
        this.vaPrecioPublico = vaPrecioPublico;
    }

    public String getTiComprobante() {
        return tiComprobante;
    }

    public void setTiComprobante(String tiComprobante) {
        this.tiComprobante = tiComprobante;
    }

    public BigDecimal getCaAtendida() {
        return caAtendida;
    }

    public void setCaAtendida(BigDecimal caAtendida) {
        this.caAtendida = caAtendida;
    }

    public BigDecimal getVaPrecioVenta() {
        return vaPrecioVenta;
    }

    public void setVaPrecioVenta(BigDecimal vaPrecioVenta) {
        this.vaPrecioVenta = vaPrecioVenta;
    }

    public String getNuPedido() {
        return nuPedido;
    }

    public void setNuPedido(String nuPedido) {
        this.nuPedido = nuPedido;
    }

    public String getNuComprobante() {
        return nuComprobante;
    }

    public void setNuComprobante(String nuComprobante) {
        this.nuComprobante = nuComprobante;
    }


    public Date getFePedido() {
        return fePedido;
    }

    public void setFePedido(Date fePedido) {
        this.fePedido = fePedido;
    }

    public BigDecimal getVaPrecioPromedio() {
        return vaPrecioPromedio;
    }

    public void setVaPrecioPromedio(BigDecimal vaPrecioPromedio) {
        this.vaPrecioPromedio = vaPrecioPromedio;
    }

    public BigDecimal getVaMargen() {
        return vaMargen;
    }

    public void setVaMargen(BigDecimal vaMargen) {
        this.vaMargen = vaMargen;
    }

    public String getNoCliente() {
        return noCliente;
    }

    public void setNoCliente(String noCliente) {
        this.noCliente = noCliente;
    }

    public BigDecimal getImp() {
        return imp;
    }

    public void setImp(BigDecimal imp) {
        this.imp = imp;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getNoVendedor() {
        return noVendedor;
    }

    public void setNoVendedor(String noVendedor) {
        this.noVendedor = noVendedor;
    }

    public String getNuComprobanteCustom() {
        return nuComprobanteCustom;
    }

    public void setNuComprobanteCustom(String nuComprobanteCustom) {
        this.nuComprobanteCustom = nuComprobanteCustom;
    }

    public BigDecimal getVaPrecioVentaSinIgv() {
        return vaPrecioVentaSinIgv;
    }

    public void setVaPrecioVentaSinIgv(BigDecimal vaPrecioVentaSinIgv) {
        this.vaPrecioVentaSinIgv = vaPrecioVentaSinIgv;
    }

    public BigDecimal getVaImpuesto() {
        return vaImpuesto;
    }

    public void setVaImpuesto(BigDecimal vaImpuesto) {
        this.vaImpuesto = vaImpuesto;
    }

    public BigDecimal getVaVenta() {
        return vaVenta;
    }

    public void setVaVenta(BigDecimal vaVenta) {
        this.vaVenta = vaVenta;
    }

    public String getEsPedidoVenta() {
        return esPedidoVenta;
    }

    public void setEsPedidoVenta(String esPedidoVenta) {
        this.esPedidoVenta = esPedidoVenta;
    }

    public BigDecimal getVaBono() {
        return vaBono;
    }

    public void setVaBono(BigDecimal vaBono) {
        this.vaBono = vaBono;
    }
}
