package com.atux.infraestructura.jpa.pojo;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class LgtmProductoPK implements Serializable {

    @Column(name = "CO_COMPANIA")
    private String coCompania;


    @Column(name = "CO_PRODUCTO")
    private String coProducto;


    @Column(name = "NU_REVISION_PRODUCTO")
    private String nuRevision="0";


    public LgtmProductoPK() {
    }

    public LgtmProductoPK(String coCompania, String coProducto) {
        this.coCompania = coCompania;
        this.coProducto = coProducto;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoProducto() {
        return coProducto;
    }

    public void setCoProducto(String coProducto) {
        this.coProducto = coProducto;
    }

    public String getNuRevision() {
        return nuRevision;
    }

    public void setNuRevision(String nuRevision) {
        this.nuRevision = nuRevision;
    }
}
