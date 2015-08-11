package com.atux.bean.donacion;

import com.aw.core.validation.annotation.Label;
import com.aw.core.validation.annotation.MoneyFormat;
import com.aw.core.validation.annotation.Validation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MATRIX-JAVA on 5/2/2015.
 */
public class IngresarDonacion {
    private String coInstitucion;
    private BigDecimal monto;

    public IngresarDonacion() {
    }

    List<IngresarDonacionDetalle> detalle=new ArrayList<IngresarDonacionDetalle>();

    public List<IngresarDonacionDetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<IngresarDonacionDetalle> detalle) {
        this.detalle = detalle;
    }

    @Label("Institución")
    @Validation("R")
    public String getCoInstitucion() {
        return coInstitucion;
    }

    public void setCoInstitucion(String coInstitucion) {
        this.coInstitucion = coInstitucion;
    }

    @MoneyFormat
    @Validation("RY5")
    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
