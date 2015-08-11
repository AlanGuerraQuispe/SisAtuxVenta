package com.atux.bean.donacion;

import com.aw.core.validation.annotation.Validation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MATRIX-JAVA on 5/2/2015.
 */
public class Donacion {
    private String coCompania;
    private String coInstitucion;
    private String deCortaInstitucion;
    private String deInstitucion;
    private String nuRucInstitucion;
    private String nuNroCuenta;
    private String deDireccion;
    private String noContacto;
    private String nuTelReferencia;
    private String deEmail;
    private String esInstitucion;
    private String idCreaInstitucion;
    private Date feCreaInstitucion;


    public Donacion() {
    }

    public Donacion(String coInstitucion, String deInstitucion) {
        this.coInstitucion= coInstitucion;
        this.deInstitucion = deInstitucion;
    }

    public Donacion(String coInstitucion, String deInstitucion, String deCortaInstitucion) {
        this.coInstitucion = coInstitucion;
        this.deInstitucion = deInstitucion;
        this.deCortaInstitucion = deCortaInstitucion;
    }

    private List<DonacionDetalle> detalle=new ArrayList<DonacionDetalle>();

    @Validation("RY50")
    public String getNoContacto() {
        return noContacto;
    }

    public void setNoContacto(String noContacto) {
        this.noContacto = noContacto;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    @Validation("RY15")
    public String getDeCortaInstitucion() {
        return deCortaInstitucion;
    }

    public void setDeCortaInstitucion(String deCortaInstitucion) {
        this.deCortaInstitucion = deCortaInstitucion;
    }

    @Validation("RY50")
    public String getDeInstitucion() {
        return deInstitucion;
    }

    public void setDeInstitucion(String deInstitucion) {
        this.deInstitucion = deInstitucion;
    }

    @Validation("RX11Y11")
    public String getNuRucInstitucion() {
        return nuRucInstitucion;
    }

    public void setNuRucInstitucion(String nuRucInstitucion) {
        this.nuRucInstitucion = nuRucInstitucion;
    }

    @Validation("RY100")
    public String getDeDireccion() {
        return deDireccion;
    }

    public void setDeDireccion(String deDireccion) {
        this.deDireccion = deDireccion;
    }

    @Validation("RY10")
    public String getNuTelReferencia() {
        return nuTelReferencia;
    }

    public void setNuTelReferencia(String nuTelReferencia) {
        this.nuTelReferencia = nuTelReferencia;
    }
    @Validation("Y50")
    public String getDeEmail() {
        return deEmail;
    }

    public void setDeEmail(String deEmail) {
        this.deEmail = deEmail;
    }

    public String getEsInstitucion() {
        return esInstitucion;
    }

    public void setEsInstitucion(String esInstitucion) {
        this.esInstitucion = esInstitucion;
    }

    public String getIdCreaInstitucion() {
        return idCreaInstitucion;
    }

    public void setIdCreaInstitucion(String idCreaInstitucion) {
        this.idCreaInstitucion = idCreaInstitucion;
    }

    public Date getFeCreaInstitucion() {
        return feCreaInstitucion;
    }

    public void setFeCreaInstitucion(Date feCreaInstitucion) {
        this.feCreaInstitucion = feCreaInstitucion;
    }

    public String getCoInstitucion() {
        return coInstitucion;
    }

    public void setCoInstitucion(String coInstitucion) {
        this.coInstitucion = coInstitucion;
    }

    @Validation("Y10")
    public String getNuNroCuenta() {
        return nuNroCuenta;
    }

    public void setNuNroCuenta(String nuNroCuenta) {
        this.nuNroCuenta = nuNroCuenta;
    }

    public List<DonacionDetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DonacionDetalle> detalle) {
        this.detalle = detalle;
    }
}
