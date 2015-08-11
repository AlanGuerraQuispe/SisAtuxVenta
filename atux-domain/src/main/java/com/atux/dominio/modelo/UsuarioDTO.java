package com.atux.dominio.modelo;

/**
 * Created by MATRIX-JAVA on 19/11/2014.
 */
public class UsuarioDTO {

    private String coCompania;
    private String coLocal;
    private String nuSecUsuario;
    private String idUsuario;
    private String noUsuario;
    private String apPaternoUsuario;
    private String apMaternoUsuario;


    public UsuarioDTO(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UsuarioDTO() {
    }

    public String getNoCompleto() {
        return apPaternoUsuario + " " + apMaternoUsuario + "," + noUsuario;
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

    public String getNuSecUsuario() {
        return nuSecUsuario;
    }

    public void setNuSecUsuario(String nuSecUsuario) {
        this.nuSecUsuario = nuSecUsuario;
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

    public String getApPaternoUsuario() {
        return apPaternoUsuario;
    }

    public void setApPaternoUsuario(String apPaternoUsuario) {
        this.apPaternoUsuario = apPaternoUsuario;
    }

    public String getApMaternoUsuario() {
        return apMaternoUsuario;
    }

    public void setApMaternoUsuario(String apMaternoUsuario) {
        this.apMaternoUsuario = apMaternoUsuario;
    }

}
