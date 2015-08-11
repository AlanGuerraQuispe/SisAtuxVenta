package com.atux.bean.precios;

import com.atux.comun.FilterBaseLocal;
import com.aw.core.validation.annotation.Label;
import com.aw.core.validation.annotation.Validation;

import java.util.Date;

/**
 * Created by JAVA on 15/11/2014.
 */
public class ProveedorFlt extends FilterBaseLocal {

    private String coProveedor;
    private String coProducto;
    private String noProveedor;
    private String nuGuia;
    private String coLaboratorio;
    private String coLista;
    private String buscar;
    private Date feInicio;
    private Date feFin;

    public String getCoProveedor() {
        return coProveedor;
    }

    public void setCoProveedor(String coProveedor) {
        this.coProveedor = coProveedor;
    }

    public String getNoProveedor() {
        return noProveedor;
    }

    public void setNoProveedor(String noProveedor) {
        this.noProveedor = noProveedor;
    }

    public String getNuGuia() {
        return nuGuia;
    }

    public void setNuGuia(String nuGuia) {
        this.nuGuia = nuGuia;
    }

    public String getCoLaboratorio() {
        return coLaboratorio;
    }

    public void setCoLaboratorio(String coLaboratorio) {
        this.coLaboratorio = coLaboratorio;
    }

    public String getCoProducto() {
        return coProducto;
    }

    public void setCoProducto(String coProducto) {
        this.coProducto = coProducto;
    }

    public String getCoLista() {
        return coLista;
    }

    public void setCoLista(String coLista) {
        this.coLista = coLista;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
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
}
