package com.atux.bean.kardex;

import com.atux.comun.FilterBaseLocal;
import com.aw.core.validation.annotation.Label;
import com.aw.core.validation.annotation.Validation;

import java.util.Date;

/**
 * Created by JAVA on 15/11/2014.
 */
public class KardexFlt extends FilterBaseLocal {

    private String coProducto;
    private String coGrupoMotivo;
    private String coMotivo;
    private String idUsuario;
    private Date feInicio;
    private Date feFin;


    public String getCoProducto() {
        return coProducto;
    }

    public void setCoProducto(String coProducto) {
        this.coProducto = coProducto;
    }

    public String getCoGrupoMotivo() {
        return coGrupoMotivo;
    }

    public void setCoGrupoMotivo(String coGrupoMotivo) {
        this.coGrupoMotivo = coGrupoMotivo;
    }

    public String getCoMotivo() {
        return coMotivo;
    }

    public void setCoMotivo(String coMotivo) {
        this.coMotivo = coMotivo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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
}
