package com.atux.bean.promocion;

import com.atux.comun.FilterBaseLocal;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by JAVA on 15/11/2014.
 */
public class PromocionFlt extends FilterBaseLocal {

    private String noPromocion;
    private String coPromocion;
    private Integer caAtendida;
    private String coProducto;
    private Date fechaFin;
    private Date fechaInicio;

    public String getCoPromocion() {
        return coPromocion;
    }

    public void setCoPromocion(String coPromocion) {
        this.coPromocion = coPromocion;
    }

    public String getNoPromocion() {
        return noPromocion;
    }

    public void setNoPromocion(String noPromocion) {
        this.noPromocion = noPromocion;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Integer getCaAtendida() {
        return caAtendida;
    }

    public void setCaAtendida(Integer caAtendida) {
        this.caAtendida = caAtendida;
    }

    public String getCoProducto() {
        return coProducto;
    }

    public void setCoProducto(String coProducto) {
        this.coProducto = coProducto;
    }
}
