package com.atux.bean.precios;

import java.util.List;

/**
 * Created by MATRIX-JAVA on 24/11/2014.
 */
public class LaboratorioIncientivo {

    private Laboratorio laboratorio;
    private String buscar;


    private List<Incentivo> incentivoList;

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public List<Incentivo> getIncentivoList() {
        return incentivoList;
    }

    public void setIncentivoList(List<Incentivo> incentivoList) {
        this.incentivoList = incentivoList;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }
}
