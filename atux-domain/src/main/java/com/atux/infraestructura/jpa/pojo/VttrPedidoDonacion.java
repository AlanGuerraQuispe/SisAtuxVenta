package com.atux.infraestructura.jpa.pojo;


import com.aw.core.domain.AWBusinessException;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="VTTR_PEDIDO_DONACION")
public class VttrPedidoDonacion extends JpaEntityBase implements AuditoriaEntity{

    @EmbeddedId
    private VttrPedidoDonacionPK id;

    @Column(name = "TI_COMPROBANTE")
    private String tiComprobante;

    @Column(name = "NU_COMPROBANTE_PAGO")
    private String nuComprobantePago;

    @Column(name = "CO_MONEDA")
    private String coMoneda;

    @Column(name = "VA_DONACION")
    private BigDecimal vaDonacion;

    @Column(name = "TI_PEDIDO")
    private String tiPedido;

    @Column(name = "NU_CAJA")
    private String nuCaja;

    @Column(name = "NU_TURNO")
    private String nuTurno;

    @Column(name = "ES_PEDIDO_VENTA")
    private String esPedidoVenta;

    @Column(name = "FE_PEDIDO")
    private Date fePedido;

    @Column(name = "ID_CREA_PEDIDO_VENTA", columnDefinition = "VARCHAR(15)")
    private String creadoPor;

    @Column(name = "FE_CREA_PEDIDO_VENTA")
    private Date fechaCreacion;

    @Column(name = "CO_CAJERO")
    private String coCajero;

    @Column(name = "CO_CAJERO_ANULACION")
    private String coCajeroAnulacion;

    @Column(name = "NU_CAJA_ANULACION")
    private String nuCajaAnulacion;

    @Column(name = "NU_TURNO_ANULACION")
    private String nuTurnoAnulacion;

    @Column(name = "ID_ANULA_PEDIDO")
    private String idAnulaPedido;

    @Column(name = "IN_PEDIDO_ANULADO")
    private String inAnulaPedido;

    @Column(name = "FE_ANULA_PEDIDO")
    private Date fechaAnulaPedido;

    public VttrPedidoDonacionPK getId() {
        return id;
    }

    public void setId(VttrPedidoDonacionPK id) {
        this.id = id;
    }

    public String getTiComprobante() {
        return tiComprobante;
    }

    public void setTiComprobante(String tiComprobante) {
        this.tiComprobante = tiComprobante;
    }

    public String getNuComprobantePago() {
        return nuComprobantePago;
    }

    public void setNuComprobantePago(String nuComprobantePago) {
        this.nuComprobantePago = nuComprobantePago;
    }

    public String getCoMoneda() {
        return coMoneda;
    }

    public void setCoMoneda(String coMoneda) {
        this.coMoneda = coMoneda;
    }

    public BigDecimal getVaDonacion() {
        return vaDonacion;
    }

    public void setVaDonacion(BigDecimal vaDonacion) {
        this.vaDonacion = vaDonacion;
    }

    public String getTiPedido() {
        return tiPedido;
    }

    public void setTiPedido(String tiPedido) {
        this.tiPedido = tiPedido;
    }

    public String getNuCaja() {
        return nuCaja;
    }

    public void setNuCaja(String nuCaja) {
        this.nuCaja = nuCaja;
    }

    public String getNuTurno() {
        return nuTurno;
    }

    public void setNuTurno(String nuTurno) {
        this.nuTurno = nuTurno;
    }

    public String getEsPedidoVenta() {
        return esPedidoVenta;
    }

    public void setEsPedidoVenta(String esPedidoVenta) {
        this.esPedidoVenta = esPedidoVenta;
    }

    public Date getFePedido() {
        return fePedido;
    }

    public void setFePedido(Date fePedido) {
        this.fePedido = fePedido;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getModificadoPor() {
        return null;
    }

    public void setModificadoPor(String modificadoPor) {

    }

    public Date getFechaModificacion() {
        return null;
    }

    public void setFechaModificacion(Date fechaModificacion) {

    }

    public String getCoCajero() {
        return coCajero;
    }

    public void setCoCajero(String coCajero) {
        this.coCajero = coCajero;
    }

    public String getCoCajeroAnulacion() {
        return coCajeroAnulacion;
    }

    public void setCoCajeroAnulacion(String coCajeroAnulacion) {
        this.coCajeroAnulacion = coCajeroAnulacion;
    }

    public String getNuCajaAnulacion() {
        return nuCajaAnulacion;
    }

    public void setNuCajaAnulacion(String nuCajaAnulacion) {
        this.nuCajaAnulacion = nuCajaAnulacion;
    }

    public String getNuTurnoAnulacion() {
        return nuTurnoAnulacion;
    }

    public void setNuTurnoAnulacion(String nuTurnoAnulacion) {
        this.nuTurnoAnulacion = nuTurnoAnulacion;
    }

    public String getIdAnulaPedido() {
        return idAnulaPedido;
    }

    public void setIdAnulaPedido(String idAnulaPedido) {
        this.idAnulaPedido = idAnulaPedido;
    }

    public String getInAnulaPedido() {
        return inAnulaPedido;
    }

    public void setInAnulaPedido(String inAnulaPedido) {
        this.inAnulaPedido = inAnulaPedido;
    }

    public Date getFechaAnulaPedido() {
        return fechaAnulaPedido;
    }


    public void setFechaAnulaPedido(Date fechaAnulaPedido) {
        this.fechaAnulaPedido = fechaAnulaPedido;
    }



}
