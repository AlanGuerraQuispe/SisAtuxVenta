package com.atux.bean.kardex;

import com.atux.bean.precios.Laboratorio;
import com.atux.comun.FilterBaseLocal;

import java.util.Date;

/**
 * Created by JAVA on 16/11/2014.
 */
public class ProductoFlt extends FilterBaseLocal {
    
    private String buscar;
    private Laboratorio laboratorio;
    private String coLaboratorio;
    private String coProducto;
    private String nuRevision="0";
    private Date feInicio;
    private Date feFin;


    public ProductoFlt(String coProducto) {
        this.coProducto = coProducto;
    }

    public ProductoFlt() {
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
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

    public String getNuRevision() {
        return nuRevision;
    }

    public void setNuRevision(String nuRevision) {
        this.nuRevision = nuRevision;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
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
