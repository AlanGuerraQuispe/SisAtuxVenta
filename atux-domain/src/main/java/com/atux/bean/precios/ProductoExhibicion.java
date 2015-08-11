package com.atux.bean.precios;

import com.atux.bean.kardex.Producto;

/**
 * Created by MATRIX-JAVA on 02/12/2014.
 */
public class ProductoExhibicion {

    private String buscar;
    private Laboratorio laboratorio;
    private Producto producto;

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
