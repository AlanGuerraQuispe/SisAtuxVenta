package com.atux.infraestructura.jpa.pojo;


import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class LgtdFarinV2PK implements Serializable {

    @Column(name = "CO_COMPANIA")
    private String coCompania;


    @Column(name = "CO_PRODUCTO")
    private String coProducto;


    @Column(name = "CO_PROVEEDOR")
    private String coProveedor;


    @Column(name = "NU_GUIA")
    private String nuGuia;

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

    public String getCoProveedor() {
        return coProveedor;
    }

    public void setCoProveedor(String coProveedor) {
        this.coProveedor = coProveedor;
    }

    public String getNuGuia() {
        return nuGuia;
    }

    public void setNuGuia(String nuGuia) {
        this.nuGuia = nuGuia;
    }
}
