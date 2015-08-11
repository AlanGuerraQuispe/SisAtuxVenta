package com.atux.bean.donacion;

import com.atux.comun.FilterBaseLocal;

import java.util.Date;

/**
 * Created by JAVA on 15/11/2014.
 */
public class DonacionFlt extends FilterBaseLocal {

    private String coInstitucion;
    private String deInstitucion;
    private Date fechaFin;
    private Date fechaInicio;

    public DonacionFlt() {
    }

    public DonacionFlt(String coInstitucion) {
        this.coInstitucion = coInstitucion;
    }

    public String getDeInstitucion() {
        return deInstitucion;
    }

    public void setDeInstitucion(String deInstitucion) {
        this.deInstitucion = deInstitucion;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getCoInstitucion() {
        return coInstitucion;
    }

    public void setCoInstitucion(String coInstitucion) {
        this.coInstitucion = coInstitucion;
    }
}
