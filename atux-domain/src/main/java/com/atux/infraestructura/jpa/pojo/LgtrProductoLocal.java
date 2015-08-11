package com.atux.infraestructura.jpa.pojo;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="LGTR_PRODUCTO_LOCAL")
public class LgtrProductoLocal {

    @EmbeddedId
    private LgtrProductoLocalPK id;

    @Column(name = "VA_VENTA")
    private BigDecimal vaVenta;

    @Column(name = "PC_DESCUENTO_1")
    private BigDecimal vaDescuento;

    @Column(name = "VA_PRECIO_PUBLICO")
    private BigDecimal vaPrecioPublico;

    @Column(name = "CA_MIN_PROD_EXHIBICION")
    private Integer vaExhibicion;

    @Column(name = "CA_STOCK_DISPONIBLE")
    private Integer caStockDisponible;

    @Column(name = "CA_STOCK_COMPROMETIDO")
    private Integer caStockComprometido;

    @Column(name = "IN_REPLICA")
    private String inReplica;


    @Column(name = "FE_MOD_PROD_LOCAL")
    private Date fechaModificacion;

    public Integer getCaStockDisponible() {
        return caStockDisponible;
    }

    public void setCaStockDisponible(Integer caStockDisponible) {
        this.caStockDisponible = caStockDisponible;
    }

    public Integer getCaStockComprometido() {
        return caStockComprometido;
    }

    public void setCaStockComprometido(Integer caStockComprometido) {
        this.caStockComprometido = caStockComprometido;
    }

    public LgtrProductoLocalPK getId() {
        return id;
    }

    public void setId(LgtrProductoLocalPK id) {
        this.id = id;
    }

    public BigDecimal getVaVenta() {
        return vaVenta;
    }

    public void setVaVenta(BigDecimal vaVenta) {
        this.vaVenta = vaVenta;
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

    public Integer getVaExhibicion() {
        return vaExhibicion;
    }

    public void setVaExhibicion(Integer vaExhibicion) {
        this.vaExhibicion = vaExhibicion;
    }

    public String getInReplica() {
        return inReplica;
    }

    public void setInReplica(String inReplica) {
        this.inReplica = inReplica;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
