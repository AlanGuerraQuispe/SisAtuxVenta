package com.atux.infraestructura.jpa.pojo;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="VTTR_PRODUCTO_BONO")
public class VttrProductoBono extends JpaEntityBase implements AuditoriaEntity{

    @EmbeddedId
    private VttrProductoBonoPK id;

    @Column(name = "FE_INICIO_BONO")
    private Date feInicio;

    @Column(name = "FE_FIN_BONO")
    private Date feFin;

    @Column(name = "VA_BONO")
    private BigDecimal vaBono;

    @Column(name = "ES_PROD_BONO")
    private String esProdBono;

    @Column(name = "ID_CREA_PROD_BONO", columnDefinition = "VARCHAR(15)")
    private String creadoPor;

    @Column(name = "FE_CREA_PROD_BONO")
    private Date fechaCreacion;

    @Column(name = "ID_MOD_PROD_BONO", columnDefinition = "VARCHAR(15)")
    private String modificadoPor;

    @Column(name = "FE_MOD_PROD_BONO")
    private Date fechaModificacion;

    public VttrProductoBonoPK getId() {
        return id;
    }

    public void setId(VttrProductoBonoPK id) {
        this.id = id;
    }

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

    public BigDecimal getVaBono() {
        return vaBono;
    }

    public void setVaBono(BigDecimal vaBono) {
        this.vaBono = vaBono;
    }

    public String getEsProdBono() {
        return esProdBono;
    }

    public void setEsProdBono(String esProdBono) {
        this.esProdBono = esProdBono;
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
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
