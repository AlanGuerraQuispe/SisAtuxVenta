package com.atux.bean.inventario;

import java.util.Date;

/**
 * Created by MATRIX-JAVA on 1/3/2015.
 */
public class TomaInventarioLaboratorio {

    private String coCompania;

    private String coLocal;

    private String nuSecTomaInventario;

    private String inTipoInventario;

    private String inLabProceso;

    private String coLaboratorio;
    private String deLaboratorio;

    private String creadoPor;

    private Date fechaCreacion;

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

    public String getCoLaboratorio() {
        return coLaboratorio;
    }

    public void setCoLaboratorio(String coLaboratorio) {
        this.coLaboratorio = coLaboratorio;
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


    public String getInTipoInventario() {
        return inTipoInventario;
    }

    public void setInTipoInventario(String inTipoInventario) {
        this.inTipoInventario = inTipoInventario;
    }

    public String getInLabProceso() {
        return inLabProceso;
    }

    public void setInLabProceso(String inLabProceso) {
        this.inLabProceso = inLabProceso;
    }

    public String getDeLaboratorio() {
        return deLaboratorio;
    }

    public void setDeLaboratorio(String deLaboratorio) {
        this.deLaboratorio = deLaboratorio;
    }
}
