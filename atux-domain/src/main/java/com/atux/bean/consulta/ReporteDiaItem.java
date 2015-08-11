package com.atux.bean.consulta;

import java.math.BigDecimal;

/**
 * Created by MATRIX-JAVA on 19/11/2014.
 */
public class ReporteDiaItem {

    private String fechaStr;
    private String nuBoletaInicio;
    private String nuBoletaFin;
    private String nuFacturaInicio;
    private String nuFacturaFin;
    private String nuBoletaManualInicio;
    private String nuBoletaManualFin;
    private Integer totalBoleta;
    private Integer totalFactura;
    private Integer totalDevolucion;
    private BigDecimal vaMonto;

    public String getFechaStr() {
        return fechaStr;
    }

    public void setFechaStr(String fechaStr) {
        this.fechaStr = fechaStr;
    }

    public String getNuBoletaInicio() {
        return nuBoletaInicio;
    }

    public void setNuBoletaInicio(String nuBoletaInicio) {
        this.nuBoletaInicio = nuBoletaInicio;
    }

    public String getNuBoletaFin() {
        return nuBoletaFin;
    }

    public void setNuBoletaFin(String nuBoletaFin) {
        this.nuBoletaFin = nuBoletaFin;
    }

    public String getNuFacturaInicio() {
        return nuFacturaInicio;
    }

    public void setNuFacturaInicio(String nuFacturaInicio) {
        this.nuFacturaInicio = nuFacturaInicio;
    }

    public String getNuFacturaFin() {
        return nuFacturaFin;
    }

    public void setNuFacturaFin(String nuFacturaFin) {
        this.nuFacturaFin = nuFacturaFin;
    }

    public String getNuBoletaManualInicio() {
        return nuBoletaManualInicio;
    }

    public void setNuBoletaManualInicio(String nuBoletaManualInicio) {
        this.nuBoletaManualInicio = nuBoletaManualInicio;
    }

    public String getNuBoletaManualFin() {
        return nuBoletaManualFin;
    }

    public void setNuBoletaManualFin(String nuBoletaManualFin) {
        this.nuBoletaManualFin = nuBoletaManualFin;
    }

    public Integer getTotalBoleta() {
        return totalBoleta;
    }

    public void setTotalBoleta(Integer totalBoleta) {
        this.totalBoleta = totalBoleta;
    }

    public Integer getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(Integer totalFactura) {
        this.totalFactura = totalFactura;
    }

    public Integer getTotalDevolucion() {
        return totalDevolucion;
    }

    public void setTotalDevolucion(Integer totalDevolucion) {
        this.totalDevolucion = totalDevolucion;
    }

    public BigDecimal getVaMonto() {
        return vaMonto;
    }

    public void setVaMonto(BigDecimal vaMonto) {
        this.vaMonto = vaMonto;
    }
}
