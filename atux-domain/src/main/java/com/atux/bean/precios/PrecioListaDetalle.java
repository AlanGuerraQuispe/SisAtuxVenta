package com.atux.bean.precios;

import com.aw.core.validation.annotation.MoneyFormat;
import com.aw.core.validation.annotation.Validation;

import java.math.BigDecimal;

/**
 * Created by MATRIX-JAVA on 24/11/2014.
 */
public class PrecioListaDetalle {

    private String coLista;
    private String coProducto;
    private String deProducto;

    private String coProveedor;
    private String coProductoProv;
    private String unidadProducto;
    private String deLaboratorio;
    private BigDecimal cantidad;
    private BigDecimal vaPrecioVenta;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrecioListaDetalle that = (PrecioListaDetalle) o;

        if (coProducto != null ? !coProducto.equals(that.coProducto) : that.coProducto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return coProducto != null ? coProducto.hashCode() : 0;
    }

    public String getCoLista() {
        return coLista;
    }

    public void setCoLista(String coLista) {
        this.coLista = coLista;
    }

    public String getCoProveedor() {
        return coProveedor;
    }

    public void setCoProveedor(String coProveedor) {
        this.coProveedor = coProveedor;
    }

    @Validation("RX3Y6")
    public String getCoProducto() {
        return coProducto;
    }

    public void setCoProducto(String coProducto) {
        this.coProducto = coProducto;
    }

    @Validation("RY15")
    public String getCoProductoProv() {
        return coProductoProv;
    }

    public void setCoProductoProv(String coProductoProv) {
        this.coProductoProv = coProductoProv;
    }

    @Validation("RY6")
    @MoneyFormat
    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    @Validation("RY10")
    @MoneyFormat
    public BigDecimal getVaPrecioVenta() {
        return vaPrecioVenta;
    }

    public void setVaPrecioVenta(BigDecimal vaPrecioVenta) {
        this.vaPrecioVenta = vaPrecioVenta;
    }

    public String getUnidadProducto() {
        return unidadProducto;
    }

    public void setUnidadProducto(String unidadProducto) {
        this.unidadProducto = unidadProducto;
    }

    public String getDeLaboratorio() {
        return deLaboratorio;
    }

    public void setDeLaboratorio(String deLaboratorio) {
        this.deLaboratorio = deLaboratorio;
    }

    public String getDeProducto() {
        return deProducto;
    }

    public void setDeProducto(String deProducto) {
        this.deProducto = deProducto;
    }


}
