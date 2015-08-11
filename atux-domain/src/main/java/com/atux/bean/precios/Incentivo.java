package com.atux.bean.precios;

import com.atux.bean.kardex.Producto;
import com.aw.core.validation.annotation.MoneyFormat;
import com.aw.core.validation.annotation.Validation;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by MATRIX-JAVA on 24/11/2014.
 */
public class Incentivo {

    private Laboratorio laboratorio= new Laboratorio();


    private Date feCreacion;
    private Date feInicio;
    private Date feFin;
    private Long nuSecProdBono;
    private BigDecimal vaIncentivo;
    private BigDecimal porcentajeMargen;
    private BigDecimal precioVenta;
    private BigDecimal precioPromedio;
    private BigDecimal precioPVP;
    private BigDecimal precioPVPFinal;

    private Producto producto=new Producto();

    private String idUsuario;
    private String coCompania;
    private String coProducto;
    private String nuRevision;
    private String deProducto;
    private String unidadProducto;
    private String unidadFraccion;
    private String inProdFraccionado;


    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public Producto getProducto()
    {
        if(producto!=null && StringUtils.isBlank(producto.getCoProducto())){
            producto=new Producto();
            producto.setCoCompania(coCompania);
            producto.setCoProducto(coProducto);
            producto.setNuRevision(nuRevision);
            producto.setUnidadFraccion(unidadFraccion);
            producto.setUnidadProducto(unidadProducto);
            producto.setInProdFraccionado(inProdFraccionado);
            producto.setPrecioVenta(precioVenta);
            producto.setPrecioPromedio(precioPromedio);
            producto.setPorcentajeMargen(porcentajeMargen);
            producto.setDeProducto(deProducto);
            producto.setPrecioPVP(precioPVP);
            producto.setPrecioPVPFinal(precioPVPFinal);
        }

        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Validation("R")
    public Date getFeInicio() {
        return feInicio;
    }

    public void setFeInicio(Date feInicio) {
        this.feInicio = feInicio;
    }

    @Validation("R")
    public Date getFeFin() {
        return feFin;
    }

    public void setFeFin(Date feFin) {
        this.feFin = feFin;
    }

    @MoneyFormat
    @Validation("R")
    public BigDecimal getVaIncentivo() {
        return vaIncentivo;
    }

    public void setVaIncentivo(BigDecimal vaIncentivo) {
        this.vaIncentivo = vaIncentivo;
    }

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

    public String getUnidadProducto() {
        return unidadProducto;
    }

    public void setUnidadProducto(String unidadProducto) {
        this.unidadProducto = unidadProducto;
    }

    public String getUnidadFraccion() {
        return unidadFraccion;
    }

    public void setUnidadFraccion(String unidadFraccion) {
        this.unidadFraccion = unidadFraccion;
    }

    public String getInProdFraccionado() {
        return inProdFraccionado;
    }

    public void setInProdFraccionado(String inProdFraccionado) {
        this.inProdFraccionado = inProdFraccionado;
    }

    public BigDecimal getPorcentajeMargen() {
        return porcentajeMargen;
    }

    public void setPorcentajeMargen(BigDecimal porcentajeMargen) {
        this.porcentajeMargen = porcentajeMargen;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Long getNuSecProdBono() {
        return nuSecProdBono;
    }

    public void setNuSecProdBono(Long nuSecProdBono) {
        this.nuSecProdBono = nuSecProdBono;
    }

    public String getNuRevision() {
        return nuRevision;
    }

    public void setNuRevision(String nuRevision) {
        this.nuRevision = nuRevision;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFeCreacion() {
        return feCreacion;
    }

    public void setFeCreacion(Date feCreacion) {
        this.feCreacion = feCreacion;
    }

    public BigDecimal getPrecioPromedio() {
        return precioPromedio;
    }

    public void setPrecioPromedio(BigDecimal precioPromedio) {
        this.precioPromedio = precioPromedio;
    }

    public BigDecimal getPrecioPVP() {
        return precioPVP;
    }

    public void setPrecioPVP(BigDecimal precioPVP) {
        this.precioPVP = precioPVP;
    }

    public BigDecimal getPrecioPVPFinal() {
        return precioPVPFinal;
    }

    public void setPrecioPVPFinal(BigDecimal precioPVPFinal) {
        this.precioPVPFinal = precioPVPFinal;
    }
}
