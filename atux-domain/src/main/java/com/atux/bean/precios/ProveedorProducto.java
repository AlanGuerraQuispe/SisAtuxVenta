package com.atux.bean.precios;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by MATRIX-JAVA on 24/11/2014.
 */
public class ProveedorProducto {

    private Laboratorio laboratorio;
    private String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }
}
