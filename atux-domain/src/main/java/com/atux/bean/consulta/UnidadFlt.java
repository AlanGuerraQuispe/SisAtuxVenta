package com.atux.bean.consulta;

import com.atux.comun.FilterBaseLocal;

/**
 * Created by MATRIX-JAVA on 27/11/2014.
 */
public class UnidadFlt extends FilterBaseLocal {

    public static final String PICK = "PICK";


    public UnidadFlt(String unidad) {
        this.unidad = unidad;
    }

    public UnidadFlt() {
    }

    private String unidad;
    private String coUnidad;

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getCoUnidad() {
        return coUnidad;
    }

    public void setCoUnidad(String coUnidad) {
        this.coUnidad = coUnidad;
    }
}
