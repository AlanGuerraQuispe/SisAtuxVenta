package com.atux.bean.donacion;

import java.util.Date;

/**
 * Created by MATRIX-JAVA on 5/2/2015.
 */
public class DonacionDetalle {
    private String coInstitucion;
    private String coCompania;
    private String coLocal;
    private String deLocal;
    private String nuInterno;
    private Date fechaInicio;
    private Date fechaFin;
    private String esCobertura="A";
    private String idCreaCoberturaConvenio;
    private Date feCreaCoberturaConvenio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DonacionDetalle that = (DonacionDetalle) o;

        if (coLocal != null ? !coLocal.equals(that.coLocal) : that.coLocal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return coLocal != null ? coLocal.hashCode() : 0;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoInstitucion() {
        return coInstitucion;
    }

    public void setCoInstitucion(String coInstitucion) {
        this.coInstitucion = coInstitucion;
    }

    public String getCoLocal() {
        return coLocal;
    }

    public void setCoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public String getNuInterno() {
        return nuInterno;
    }

    public void setNuInterno(String nuInterno) {
        this.nuInterno = nuInterno;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEsCobertura() {
        return esCobertura;
    }

    public void setEsCobertura(String esCobertura) {
        this.esCobertura = esCobertura;
    }

    public String getIdCreaCoberturaConvenio() {
        return idCreaCoberturaConvenio;
    }

    public void setIdCreaCoberturaConvenio(String idCreaCoberturaConvenio) {
        this.idCreaCoberturaConvenio = idCreaCoberturaConvenio;
    }

    public Date getFeCreaCoberturaConvenio() {
        return feCreaCoberturaConvenio;
    }

    public void setFeCreaCoberturaConvenio(Date feCreaCoberturaConvenio) {
        this.feCreaCoberturaConvenio = feCreaCoberturaConvenio;
    }

    public String getDeLocal() {
        return deLocal;
    }

    public void setDeLocal(String deLocal) {
        this.deLocal = deLocal;
    }
}
