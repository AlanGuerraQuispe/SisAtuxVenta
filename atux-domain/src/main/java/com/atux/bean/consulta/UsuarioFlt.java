package com.atux.bean.consulta;

import com.atux.comun.FilterBaseLocal;

/**
 * Created by MATRIX-JAVA on 27/11/2014.
 */
public class UsuarioFlt extends FilterBaseLocal {

    public static final String PICK = "PICK";

    private Long pkUsuario;
    private String idUsuario;
    private String noUsuario;
    private String idsToAvoid;
    private String nombreCompleto;

    public Long getPkUsuario() {
        return pkUsuario;
    }

    public void setPkUsuario(Long pkUsuario) {
        this.pkUsuario = pkUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNoUsuario() {
        return noUsuario;
    }

    public void setNoUsuario(String noUsuario) {
        this.noUsuario = noUsuario;
    }

    public String getIdsToAvoid() {
        return idsToAvoid;
    }

    public void setIdsToAvoid(String idsToAvoid) {
        this.idsToAvoid = idsToAvoid;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
