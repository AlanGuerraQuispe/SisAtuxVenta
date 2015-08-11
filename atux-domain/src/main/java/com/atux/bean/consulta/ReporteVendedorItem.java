package com.atux.bean.consulta;

import java.math.BigDecimal;

/**
 * Created by MATRIX-JAVA on 19/11/2014.
 */
public class ReporteVendedorItem {

    private String nuSecUsuario;
    private String nuEmpleado;
    private String empleado;
    private String tiRol;
    private BigDecimal totalVenta;
    private BigDecimal totalBono;
    private BigDecimal totalGrupoA;
    private BigDecimal margenGrupoA;
    private BigDecimal totalPP;
    private BigDecimal margenPP;
    private Integer totalPedidos;
    private Integer totalUnidades;
    private BigDecimal valePromocion;
    private BigDecimal unidadesVale;
    private BigDecimal promedioUnidades;

    public String getNuEmpleado() {
        return nuEmpleado;
    }

    public void setNuEmpleado(String nuEmpleado) {
        this.nuEmpleado = nuEmpleado;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getTiRol() {
        return tiRol;
    }

    public void setTiRol(String tiRol) {
        this.tiRol = tiRol;
    }

    public BigDecimal getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(BigDecimal totalVenta) {
        this.totalVenta = totalVenta;
    }

    public BigDecimal getTotalBono() {
        return totalBono;
    }

    public void setTotalBono(BigDecimal totalBono) {
        this.totalBono = totalBono;
    }

    public BigDecimal getTotalGrupoA() {
        return totalGrupoA;
    }

    public void setTotalGrupoA(BigDecimal totalGrupoA) {
        this.totalGrupoA = totalGrupoA;
    }

    public BigDecimal getMargenGrupoA() {
        return margenGrupoA;
    }

    public void setMargenGrupoA(BigDecimal margenGrupoA) {
        this.margenGrupoA = margenGrupoA;
    }

    public BigDecimal getTotalPP() {
        return totalPP;
    }

    public void setTotalPP(BigDecimal totalPP) {
        this.totalPP = totalPP;
    }

    public BigDecimal getMargenPP() {
        return margenPP;
    }

    public void setMargenPP(BigDecimal margenPP) {
        this.margenPP = margenPP;
    }

    public Integer getTotalPedidos() {
        return totalPedidos;
    }

    public void setTotalPedidos(Integer totalPedidos) {
        this.totalPedidos = totalPedidos;
    }

    public Integer getTotalUnidades() {
        return totalUnidades;
    }

    public void setTotalUnidades(Integer totalUnidades) {
        this.totalUnidades = totalUnidades;
    }

    public BigDecimal getValePromocion() {
        return valePromocion;
    }

    public void setValePromocion(BigDecimal valePromocion) {
        this.valePromocion = valePromocion;
    }

    public BigDecimal getUnidadesVale() {
        return unidadesVale;
    }

    public void setUnidadesVale(BigDecimal unidadesVale) {
        this.unidadesVale = unidadesVale;
    }

    public BigDecimal getPromedioUnidades() {
        return promedioUnidades;
    }

    public void setPromedioUnidades(BigDecimal promedioUnidades) {
        this.promedioUnidades = promedioUnidades;
    }

    public String getNuSecUsuario() {
        return nuSecUsuario;
    }

    public void setNuSecUsuario(String nuSecUsuario) {
        this.nuSecUsuario = nuSecUsuario;
    }
}
