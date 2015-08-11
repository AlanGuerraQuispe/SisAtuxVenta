package com.atux.bean.inventario;

import com.atux.comun.FilterBaseLocal;

import java.math.BigDecimal;

/**
 * Created by MATRIX-JAVA on 6/3/2015.
 */
public class OrdenarFlt {
    private String atributo;

    private BigDecimal diferencias;

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public BigDecimal getDiferencias() {
        return diferencias;
    }

    public void setDiferencias(BigDecimal diferencias) {
        this.diferencias = diferencias;
    }
}
