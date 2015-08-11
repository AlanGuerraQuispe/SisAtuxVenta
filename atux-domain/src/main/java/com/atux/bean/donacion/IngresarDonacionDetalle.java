package com.atux.bean.donacion;

import com.aw.core.validation.annotation.MoneyFormat;
import com.aw.core.validation.annotation.Validation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MATRIX-JAVA on 5/2/2015.
 */
public class IngresarDonacionDetalle {
    private Donacion donacion;
    private BigDecimal monto;

    public IngresarDonacionDetalle() {
    }

    public IngresarDonacionDetalle(String coInstitucion,String deInstitucion,String deInstitucionCorta, BigDecimal monto) {
        this.donacion = new Donacion(coInstitucion,deInstitucion,deInstitucionCorta);
        this.monto = monto;
    }

    public Donacion getDonacion() {
        return donacion;
    }

    public void setDonacion(Donacion donacion) {
        this.donacion = donacion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
