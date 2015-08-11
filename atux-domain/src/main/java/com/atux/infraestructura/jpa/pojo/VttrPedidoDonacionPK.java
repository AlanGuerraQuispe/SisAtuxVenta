package com.atux.infraestructura.jpa.pojo;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Embeddable
public class VttrPedidoDonacionPK implements Serializable {

    public VttrPedidoDonacionPK() {
    }



    @Column(name = "CO_COMPANIA")
    private String coCompania;


    @Column(name = "CO_LOCAL")
    private String coLocal;


    @Column(name = "CO_INSTITUCION")
    private String coInstitucion;


    @Column(name = "NU_PEDIDO")
    private String nuPedido;



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

    public String getCoInstitucion() {
        return coInstitucion;
    }

    public void setCoInstitucion(String coInstitucion) {
        this.coInstitucion = coInstitucion;
    }

    public String getNuPedido() {
        return nuPedido;
    }

    public void setNuPedido(String nuPedido) {
        this.nuPedido = nuPedido;
    }
}