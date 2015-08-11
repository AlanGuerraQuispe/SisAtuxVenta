package com.atux.infraestructura.jpa.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="CMTS_USUARIO",schema = "COMUN")
public class UsuarioPK implements Serializable {
    @Id
    @Column(name = "CO_COMPANIA")
    private String coCompania;
    @Id
    @Column(name = "CO_LOCAL")
    private String coLocal;
    @Id
    @Column(name = "NU_SEC_USUARIO")
    private String nuSecUsuario;
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
}
