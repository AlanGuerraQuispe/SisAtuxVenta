package com.atux.bean.inventario;

import java.util.Date;

/**
 * Created by MATRIX-JAVA on 1/3/2015.
 */
public class TomaInventarioDetalle {

    private String coCompania;

    private String coLocal;

    private String nuSecTomaInventario;

    private String tiTomaInventario;

    private String estado;

    private Date fechaInicio;

    private String creadoPor;

    private Date fechaCreacion;

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoLocal() {
        return coLocal;
    }

    public void setCoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public String getNuSecTomaInventario() {
        return nuSecTomaInventario;
    }

    public void setNuSecTomaInventario(String nuSecTomaInventario) {
        this.nuSecTomaInventario = nuSecTomaInventario;
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
}
