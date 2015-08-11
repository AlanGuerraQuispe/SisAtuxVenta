package com.atux.bean.central;

import com.atux.bean.kardex.KardexDetalle;
import com.atux.bean.kardex.Producto;
import com.aw.core.validation.annotation.Validation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
public class Fraccionamiento {

    String coGrupoMotivo;
    String coMotivo;
    String coGrupoMotivoAjuste;
    String coMotivoAjuste;
    String idUsuario;
    String justificacion;
    Producto producto ;
    Date feInicio;
    Date feFin;
    List<FraccionamientoLocal> detalleList ;

    public Fraccionamiento() {
        producto=new Producto();
        detalleList=new ArrayList<FraccionamientoLocal>();
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<FraccionamientoLocal> getDetalleList() {
        return detalleList;
    }

    public void setDetalleList(List<FraccionamientoLocal> detalleList) {
        this.detalleList = detalleList;
    }

    public String getCoGrupoMotivo() {
        return coGrupoMotivo;
    }

    public void setCoGrupoMotivo(String coGrupoMotivo) {
        this.coGrupoMotivo = coGrupoMotivo;
    }


    @Validation("R")
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

    @Validation("RY100")
    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getCoGrupoMotivoAjuste() {
        return coGrupoMotivoAjuste;
    }

    public void setCoGrupoMotivoAjuste(String coGrupoMotivoAjuste) {
        this.coGrupoMotivoAjuste = coGrupoMotivoAjuste;
    }

    public String getCoMotivoAjuste() {
        return coMotivoAjuste;
    }

    public void setCoMotivoAjuste(String coMotivoAjuste) {
        this.coMotivoAjuste = coMotivoAjuste;
    }
}
