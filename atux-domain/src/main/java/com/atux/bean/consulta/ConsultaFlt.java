package com.atux.bean.consulta;

import com.atux.comun.FilterBaseLocal;
import com.aw.core.validation.annotation.Label;
import com.aw.core.validation.annotation.Validation;

import java.util.Date;

/**
 * Created by MATRIX-JAVA on 19/11/2014.
 */
public class ConsultaFlt extends FilterBaseLocal {
    private Date feInicio;
    private Date feFin;
    private String tipoDocumento;
    private String coVendedor;
    private String noVendedor;
    private String vendedor;

    public String getVendedor() {
        return coVendedor + "-" + noVendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    @Validation("R")
    @Label("Fecha de Inicio")
    public Date getFeInicio() {
        return feInicio;
    }

    public void setFeInicio(Date feInicio) {
        this.feInicio = feInicio;
    }

    @Validation("R")
    @Label("Fecha de Fin")
    public Date getFeFin() {
        return feFin;
    }

    public void setFeFin(Date feFin) {
        this.feFin = feFin;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getCoVendedor() {
        return coVendedor;
    }

    public void setCoVendedor(String coVendedor) {
        this.coVendedor = coVendedor;
    }

    public String getNoVendedor() {
        return noVendedor;
    }

    public void setNoVendedor(String noVendedor) {
        this.noVendedor = noVendedor;
    }
}
