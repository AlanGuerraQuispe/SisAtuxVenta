package com.atux.bean.inventario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MATRIX-JAVA on 1/3/2015.
 */
public class TomaInventario {

    private String coCompania;

    private String coLocal;

    private String nuSecTomaInventario;

    private String tiTomaInventario;

    private String estado;

    private Date fechaInicio;
    private Date fechaFin;

    private String creadoPor;

    private Date fechaCreacion;

    private List<TomaInventarioLaboratorio> laboratorioList = new ArrayList<TomaInventarioLaboratorio>();

    public void addLaboratorios(List<LaboratorioInventario> seleccionados) {
        for (LaboratorioInventario laboratorioInventario : seleccionados) {
            TomaInventarioLaboratorio item = buildTomaInventarioLab(laboratorioInventario);
            laboratorioList.add(item);
        }
    }

    private TomaInventarioLaboratorio buildTomaInventarioLab(LaboratorioInventario laboratorioInventario) {
        TomaInventarioLaboratorio result = new TomaInventarioLaboratorio();
        result.setCoCompania(getCoCompania());
        result.setCoLocal(getCoLocal());
        result.setCoLaboratorio(laboratorioInventario.getCoLaboratorio());
        result.setCreadoPor(getCreadoPor());
        result.setNuSecTomaInventario(getNuSecTomaInventario());
        result.setInTipoInventario(getTiTomaInventario());
        return result;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoLocal() {
        return coLocal;
    }

    public void setCoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public String getNuSecTomaInventario() {
        return nuSecTomaInventario;
    }

    public void setNuSecTomaInventario(String nuSecTomaInventario) {
        this.nuSecTomaInventario = nuSecTomaInventario;
    }

    public String getTiTomaInventario() {
        return tiTomaInventario;
    }

    public void setTiTomaInventario(String tiTomaInventario) {
        this.tiTomaInventario = tiTomaInventario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }


    public List<TomaInventarioLaboratorio> getLaboratorioList() {
        return laboratorioList;
    }

    public void setLaboratorioList(List<TomaInventarioLaboratorio> laboratorioList) {
        this.laboratorioList = laboratorioList;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}
