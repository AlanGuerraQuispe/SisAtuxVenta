package com.atux.bean.inventario;

import com.atux.comun.FilterBaseLocal;

/**
 * Created by MATRIX-JAVA on 6/3/2015.
 */
public class TomaInventarioFlt extends FilterBaseLocal {
    private String nuSecTomaInventario;
    private String coLaboratorio;

    public String getCoLaboratorio() {
        return coLaboratorio;
    }

    public void setCoLaboratorio(String coLaboratorio) {
        this.coLaboratorio = coLaboratorio;
    }

    public String getNuSecTomaInventario() {
        return nuSecTomaInventario;
    }

    public void setNuSecTomaInventario(String nuSecTomaInventario) {
        this.nuSecTomaInventario = nuSecTomaInventario;
    }
}
