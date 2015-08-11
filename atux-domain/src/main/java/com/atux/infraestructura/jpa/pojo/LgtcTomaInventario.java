package com.atux.infraestructura.jpa.pojo;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="LGTC_TOMA_INVENTARIO")
public class LgtcTomaInventario extends JpaEntityBase implements AuditoriaEntity{

    @EmbeddedId
    private LgtcTomaInventarioPK id;


    @Column(name = "TI_TOMA_INVENTARIO")
    private String tiTomaInventario;

    @Column(name = "ES_TOMA_INVENTARIO")
    private String estado;

    @Column(name = "FE_INICIO_TOMA_INVENTARIO")
    private Date fechaInicio;

    @Column(name = "ID_CREA_TOMA_INVENTARIO", columnDefinition = "VARCHAR(15)")
    private String creadoPor;

    @Column(name = "FE_CREA_TOMA_INVENTARIO")
    private Date fechaCreacion;

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

    public LgtcTomaInventarioPK getId() {
        return id;
    }

    public void setId(LgtcTomaInventarioPK id) {
        this.id = id;
    }

    public String getTiTomaInventario() {
        return tiTomaInventario;
    }

    public void setTiTomaInventario(String tiTomaInventario) {
        this.tiTomaInventario = tiTomaInventario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
