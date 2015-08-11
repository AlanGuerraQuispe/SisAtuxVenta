package com.atux.bean.promocion;

import com.aw.core.validation.annotation.Label;
import com.aw.core.validation.annotation.Validation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MATRIX-JAVA on 5/2/2015.
 */
public class Promocion {
    private String coCompania;
    private String coPromocion;
    private String noPromocion;
    private Date fechaInicio;
    private Date fechaFin;
    private String inTodosLocales;
    private String mensajeCorto;
    private String mensajeLargo;
    private String observacion;
    private String esPromocion;
    private String creadoPor;
    private Date fechaCreacion;

    List<PromocionDetalle> detalle=new ArrayList<PromocionDetalle>();
    List<PromocionLocal> detalleLocal=new ArrayList<PromocionLocal>();

    public List<PromocionDetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<PromocionDetalle> detalle) {
        this.detalle = detalle;
    }

    public String getCoPromocion() {
        return coPromocion;
    }

    public void setCoPromocion(String coPromocion) {
        this.coPromocion = coPromocion;
    }

    @Validation("R")
    @Label("Fecha Inicio")
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Validation("R")
    @Label("Fecha Fin")
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getInTodosLocales() {
        return inTodosLocales;
    }

    public void setInTodosLocales(String inTodosLocales) {
        this.inTodosLocales = inTodosLocales;
    }

    public String getEsPromocion() {
        return esPromocion;
    }

    public void setEsPromocion(String esPromocion) {
        this.esPromocion = esPromocion;
    }

    @Validation("R")
    @Label("Mensaje Corto")
    public String getMensajeCorto() {
        return mensajeCorto;
    }

    public void setMensajeCorto(String mensajeCorto) {
        this.mensajeCorto = mensajeCorto;
    }

    @Validation("R")
    @Label("Mensaje Largo")
    public String getMensajeLargo() {
        return mensajeLargo;
    }

    public void setMensajeLargo(String mensajeLargo) {
        this.mensajeLargo = mensajeLargo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Validation("R")
    @Label("Nombre de la Promoción")
    public String getNoPromocion() {
        return noPromocion;
    }

    public void setNoPromocion(String noPromocion) {
        this.noPromocion = noPromocion;
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

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public List<PromocionLocal> getDetalleLocal() {
        return detalleLocal;
    }

    public void setDetalleLocal(List<PromocionLocal> detalleLocal) {
        this.detalleLocal = detalleLocal;
    }
}
