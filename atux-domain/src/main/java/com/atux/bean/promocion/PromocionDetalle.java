package com.atux.bean.promocion;

import com.aw.core.validation.annotation.NumberFormat;
import com.aw.core.validation.annotation.Validation;

import java.math.BigDecimal;

/**
 * Created by MATRIX-JAVA on 5/2/2015.
 */
public class PromocionDetalle {

    private String promocion;
    private String coProducto;
    private String coPromocion;
    private String deProducto;
    private String unidadProducto;
    private String unidadProductoP;
    private String unidadFraccion;
    private String unidadFraccionP;
    private String deLaboratorio;
    private String deLaboratorioP;
    private String inProdFraccionado;
    private String inProdFraccionadoP;
    private BigDecimal caProducto;
    private BigDecimal vaFraccion;
    private BigDecimal vaFraccionP;
    private String coProductoP;
    private String deProductoP;
    private BigDecimal caProductoP;
    private String esProductoPlan="A";

    public String getCoProducto() {
        return coProducto;
    }

    public void setCoProducto(String coProducto) {
        this.coProducto = coProducto;
    }

    public String getDeProducto() {
        return deProducto;
    }

    public void setDeProducto(String deProducto) {
        this.deProducto = deProducto;
    }




    public String getCoProductoP() {
        return coProductoP;
    }

    public void setCoProductoP(String coProductoP) {
        this.coProductoP = coProductoP;
    }

    public String getDeProductoP() {
        return deProductoP;
    }

    public void setDeProductoP(String deProductoP) {
        this.deProductoP = deProductoP;
    }

    public String getEsProductoPlan() {
        return esProductoPlan;
    }

    public void setEsProductoPlan(String esProductoPlan) {
        this.esProductoPlan = esProductoPlan;
    }

    public String getUnidadProducto() {
        return unidadProducto;
    }

    public void setUnidadProducto(String unidadProducto) {
        this.unidadProducto = unidadProducto;
    }

    public String getUnidadProductoP() {
        return unidadProductoP;
    }

    public void setUnidadProductoP(String unidadProductoP) {
        this.unidadProductoP = unidadProductoP;
    }

    public String getUnidadFraccion() {
        return unidadFraccion;
    }

    public void setUnidadFraccion(String unidadFraccion) {
        this.unidadFraccion = unidadFraccion;
    }

    public String getUnidadFraccionP() {
        return unidadFraccionP;
    }

    public void setUnidadFraccionP(String unidadFraccionP) {
        this.unidadFraccionP = unidadFraccionP;
    }

    public String getDeLaboratorio() {
        return deLaboratorio;
    }

    public void setDeLaboratorio(String deLaboratorio) {
        this.deLaboratorio = deLaboratorio;
    }

    public String getDeLaboratorioP() {
        return deLaboratorioP;
    }

    public void setDeLaboratorioP(String deLaboratorioP) {
        this.deLaboratorioP = deLaboratorioP;
    }

    public String getInProdFraccionado() {
        return inProdFraccionado;
    }

    public void setInProdFraccionado(String inProdFraccionado) {
        this.inProdFraccionado = inProdFraccionado;
    }

    public String getInProdFraccionadoP() {
        return inProdFraccionadoP;
    }

    public void setInProdFraccionadoP(String inProdFraccionadoP) {
        this.inProdFraccionadoP = inProdFraccionadoP;
    }

    public BigDecimal getVaFraccion() {
        return vaFraccion;
    }

    public void setVaFraccion(BigDecimal vaFraccion) {
        this.vaFraccion = vaFraccion;
    }

    public BigDecimal getVaFraccionP() {
        return vaFraccionP;
    }

    public void setVaFraccionP(BigDecimal vaFraccionP) {
        this.vaFraccionP = vaFraccionP;
    }
    @Validation("R")
    @NumberFormat
    public BigDecimal getCaProducto() {
        return caProducto;
    }

    public void setCaProducto(BigDecimal caProducto) {
        this.caProducto = caProducto;
    }
    @Validation("R")
    @NumberFormat
    public BigDecimal getCaProductoP() {
        return caProductoP;
    }

    public void setCaProductoP(BigDecimal caProductoP) {
        this.caProductoP = caProductoP;
    }

    public String getCoPromocion() {
        return coPromocion;
    }

    public void setCoPromocion(String coPromocion) {
        this.coPromocion = coPromocion;
    }

    public String getPromocion() {
        return promocion;
    }

    public void setPromocion(String promocion) {
        this.promocion = promocion;
    }
}
