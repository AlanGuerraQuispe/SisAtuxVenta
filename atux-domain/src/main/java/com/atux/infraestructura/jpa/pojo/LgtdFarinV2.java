package com.atux.infraestructura.jpa.pojo;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="LGTD_FARINV2")
public class LgtdFarinV2 {

    @EmbeddedId
    private LgtdFarinV2PK id;

    @Column(name = "PC_DCTO1")
    private BigDecimal pcDcto1;

    @Column(name = "VA_PRECIO")
    private BigDecimal vaPrecio;


    public LgtdFarinV2PK getId() {
        return id;
    }

    public void setId(LgtdFarinV2PK id) {
        this.id = id;
    }

    public BigDecimal getPcDcto1() {
        return pcDcto1;
    }

    public void setPcDcto1(BigDecimal pcDcto1) {
        this.pcDcto1 = pcDcto1;
    }

    public BigDecimal getVaPrecio() {
        return vaPrecio;
    }

    public void setVaPrecio(BigDecimal vaPrecio) {
        this.vaPrecio = vaPrecio;
    }
}
