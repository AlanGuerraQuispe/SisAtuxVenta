package com.atux.infraestructura.jpa.pojo;


import com.sun.imageio.plugins.common.BogusColorSpace;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="LGTM_PRODUCTO")
public class LgtmProducto {

    @EmbeddedId
    private LgtmProductoPK id;

    @Column(name = "VA_BONO")
    private BigDecimal vaBono;

    public LgtmProductoPK getId() {
        return id;
    }

    public void setId(LgtmProductoPK id) {
        this.id = id;
    }

    public BigDecimal getVaBono() {
        return vaBono;
    }

    public void setVaBono(BigDecimal vaBono) {
        this.vaBono = vaBono;
    }
}
