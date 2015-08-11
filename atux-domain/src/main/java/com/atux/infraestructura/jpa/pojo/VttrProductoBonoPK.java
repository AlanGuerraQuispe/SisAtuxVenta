package com.atux.infraestructura.jpa.pojo;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class VttrProductoBonoPK implements Serializable {

    @Column(name = "CO_COMPANIA")
    private String coCompania;


    @Column(name = "CO_PRODUCTO")
    private String coProducto;


    @Column(name = "NU_REVISION_PRODUCTO")
    private String nuRevisionProducto;


    @Column(name = "NU_SEC_PROD_BONO")
    private Long nuSecProdBono;


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

    public String getNuRevisionProducto() {
        return nuRevisionProducto;
    }

    public void setNuRevisionProducto(String nuRevisionProducto) {
        this.nuRevisionProducto = nuRevisionProducto;
    }

    public Long getNuSecProdBono() {
        return nuSecProdBono;
    }

    public void setNuSecProdBono(Long nuSecProdBono) {
        this.nuSecProdBono = nuSecProdBono;
    }
}
