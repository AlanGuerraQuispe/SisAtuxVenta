package com.atux.bean.precios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MATRIX-JAVA on 24/11/2014.
 */
public class PrecioLista {

    private String coLista;
    private String coProveedor;
    private String noProveedor;
    private Date fechaInicio;
    private Date fechaFin;
    private List<PrecioListaDetalle> detalle= new ArrayList<PrecioListaDetalle>();


    public List<PrecioListaDetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<PrecioListaDetalle> detalle) {
        this.detalle = detalle;
    }

    public String getCoLista() {
        return coLista;
    }

    public void setCoLista(String coLista) {
        this.coLista = coLista;
    }

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
}
