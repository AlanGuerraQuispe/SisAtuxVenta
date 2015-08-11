package com.atux.bean.central;

import com.atux.bean.kardex.Producto;
import com.aw.core.domain.AWBusinessException;
import com.aw.core.format.NumberFormatter;
import com.aw.core.util.NumberUtils;
import com.aw.core.validation.annotation.Label;
import com.aw.core.validation.annotation.Validation;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by MATRIX-JAVA on 20/1/2015.
 */
public class FraccionamientoLocal {

    private Producto producto;
    private Long nuSecProdFraccion;
    private String coCompania;
    private String coLocal;
    private String deLocal;
    private String usuario;
    private BigDecimal vaFraccion;
    private BigDecimal stkEntero;
    private BigDecimal stkFraccion;
    private String inProdFraccionadoLocal;
    private String unidadFraccion;
    private String minFraccion;
    private String maxFraccion;
    private String minFraccionLabel;
    private String maxFraccionLabel;
    private String inActivarFraccion="S";
    private String inProdFraccionadoNuevo;
    private String inActivo;
    private String esFraccion;
    private Date feVigencia;
    private BigDecimal vaFraccionNuevo;
    private String unidadFraccionNuevo;

    public String getStkDisponibleFmt() {
        if ("S".equals(getInProdFraccionadoLocal())) {
            return NumberFormatter.INTEGER_FORMATTER_W_GRP_SYM.format(NumberUtils.nvlZero(getStkEntero()))
                    + " / " + NumberFormatter.INTEGER_FORMATTER_W_GRP_SYM.format(NumberUtils.nvlZero(getStkFraccion()));
        }
        return (String) NumberFormatter.INTEGER_FORMATTER_W_GRP_SYM.format(NumberUtils.nvlZero(getStkEntero()));
    }


    public String getLocal() {
        if (StringUtils.isBlank(coLocal)) {
            return null;
        }
        return coLocal + "-" + deLocal;
    }

    public String getCoLocal() {
        return coLocal;
    }

    public void setCoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public String getDeLocal() {
        return deLocal;
    }

    public void setDeLocal(String deLocal) {
        this.deLocal = deLocal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public BigDecimal getVaFraccion() {
        return vaFraccion;
    }

    public void setVaFraccion(BigDecimal vaFraccion) {
        this.vaFraccion = vaFraccion;
    }

    public String getInProdFraccionadoLocal() {
        return inProdFraccionadoLocal;
    }

    public void setInProdFraccionadoLocal(String inProdFraccionadoLocal) {
        this.inProdFraccionadoLocal = inProdFraccionadoLocal;
    }

    public String getUnidadFraccion() {
        return unidadFraccion;
    }

    public void setUnidadFraccion(String unidadFraccion) {
        this.unidadFraccion = unidadFraccion;
    }

    public String getInProdFraccionadoNuevo() {
        return inProdFraccionadoNuevo;
    }

    public void setInProdFraccionadoNuevo(String inProdFraccionadoNuevo) {
        this.inProdFraccionadoNuevo = inProdFraccionadoNuevo;
    }

    public String getInActivo() {
        return inActivo;
    }

    public void setInActivo(String inActivo) {
        this.inActivo = inActivo;
    }

    public Date getFeVigencia() {
        return feVigencia;
    }

    public void setFeVigencia(Date feVigencia) {
        this.feVigencia = feVigencia;
    }

    public BigDecimal getVaFraccionNuevo() {
        return vaFraccionNuevo;
    }

    public void setVaFraccionNuevo(BigDecimal vaFraccionNuevo) {
        this.vaFraccionNuevo = vaFraccionNuevo;
    }

    public String getUnidadFraccionNuevo() {
        return unidadFraccionNuevo;
    }

    public void setUnidadFraccionNuevo(String unidadFraccionNuevo) {
        this.unidadFraccionNuevo = unidadFraccionNuevo;
    }

    public BigDecimal getStkEntero() {
        return stkEntero;
    }

    public void setStkEntero(BigDecimal stkEntero) {
        this.stkEntero = stkEntero;
    }

    public BigDecimal getStkFraccion() {
        return stkFraccion;
    }

    public void setStkFraccion(BigDecimal stkFraccion) {
        this.stkFraccion = stkFraccion;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getMinFraccion() {
        return minFraccion;
    }

    public void setMinFraccion(String minFraccion) {
        this.minFraccion = minFraccion;
    }

    public String getMaxFraccion() {
        return maxFraccion;
    }

    public void setMaxFraccion(String maxFraccion) {
        this.maxFraccion = maxFraccion;
    }

    public String getMinFraccionLabel() {
        return minFraccionLabel;
    }

    public void setMinFraccionLabel(String minFraccionLabel) {
        this.minFraccionLabel = minFraccionLabel;
    }

    public String getMaxFraccionLabel() {
        return maxFraccionLabel;
    }

    public void setMaxFraccionLabel(String maxFraccionLabel) {
        this.maxFraccionLabel = maxFraccionLabel;
    }

    public void buildUnidadFraccionNueva(String unidadMaxCortaLabel) {
        if (StringUtils.isBlank(getMinFraccionLabel())
                || getVaFraccionNuevo() == null
                || StringUtils.isBlank(unidadMaxCortaLabel)) return;
        String lblVaFraccion = String.valueOf(getVaFraccionNuevo());
        if ("S".equals(producto.getInProdFraccionado())) {
            if (producto.getCaEmpaqueMax() != null) {
                if (vaFraccionNuevo.intValue() < producto.getCaEmpaqueMax()) {
                    lblVaFraccion = String.valueOf(producto.getCaEmpaqueMax() / vaFraccionNuevo.intValue());
                } else if (vaFraccionNuevo.intValue() == producto.getCaEmpaqueMax()) {
                    setUnidadFraccionNuevo(getMinFraccionLabel());
                    return;
                }
            }
        }
        setUnidadFraccionNuevo(getMinFraccionLabel() + "/" + lblVaFraccion + " " + unidadMaxCortaLabel);
    }

    public Long getNuSecProdFraccion() {
        return nuSecProdFraccion;
    }

    public void setNuSecProdFraccion(Long nuSecProdFraccion) {
        this.nuSecProdFraccion = nuSecProdFraccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEsFraccion() {
        return esFraccion;
    }

    public void setEsFraccion(String esFraccion) {
        this.esFraccion = esFraccion;
    }

    public boolean tieneCambios() {

        return (inProdFraccionadoNuevo != null && !inProdFraccionadoNuevo.equals(inProdFraccionadoLocal))
                || (vaFraccionNuevo != null && !vaFraccionNuevo.equals(vaFraccion))
                || (unidadFraccionNuevo != null && !unidadFraccionNuevo.equals(unidadFraccion));
    }

    public void validarNuevaFraccion() {

        if (getFeVigencia() == null) {
            throw new AWBusinessException("Ingrese la fecha de vigencia");
        }
        Date fechaActual = new Date(); // todo jalar la fecha de base de datos
        if (getFeVigencia().compareTo(fechaActual) <= 0) {
            throw new AWBusinessException("La fecha de vigencia debe ser superior al dia de hoy");
        }

        if ("S".equals(getInActivarFraccion())) {
            if (StringUtils.isBlank(getMinFraccion())) {
                throw new AWBusinessException("Ingrese el Min. Fracción");
            }
            if (StringUtils.isBlank(getMaxFraccion())) {
                throw new AWBusinessException("Ingrese el Max. Fracción");
            }
            if (getMinFraccionLabel().equals(getMaxFraccionLabel())) {
                throw new AWBusinessException("Ingrese unidades diferentes");
            }
        } else {
            setUnidadFraccionNuevo(null);
            setVaFraccionNuevo(null);
        }

    }

    public String getInActivarFraccion() {
        return inActivarFraccion;
    }

    public void setInActivarFraccion(String inActivarFraccion) {
        this.inActivarFraccion = inActivarFraccion;
    }
}
