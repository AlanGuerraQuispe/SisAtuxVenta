package com.atux.bean.precios;

import java.util.Date;

/**
 * Created by MATRIX-JAVA on 24/11/2014.
 */
public class Laboratorio {
    private String coLaboratorio;
    private String deLaboratorio;
    private Date feRegistro;
    private Date feVigencia;


    public String toFormat() {
        return coLaboratorio + '-' + deLaboratorio;
    }

    public String getCoLaboratorio() {
        return coLaboratorio;
    }

    public void setCoLaboratorio(String coLaboratorio) {
        this.coLaboratorio = coLaboratorio;
    }

    public String getDeLaboratorio() {
        return deLaboratorio;
    }

    public void setDeLaboratorio(String deLaboratorio) {
        this.deLaboratorio = deLaboratorio;
    }

    public Date getFeRegistro() {
        return feRegistro;
    }

    public void setFeRegistro(Date feRegistro) {
        this.feRegistro = feRegistro;
    }

    public Date getFeVigencia() {
        return feVigencia;
    }

    public void setFeVigencia(Date feVigencia) {
        this.feVigencia = feVigencia;
    }
}
