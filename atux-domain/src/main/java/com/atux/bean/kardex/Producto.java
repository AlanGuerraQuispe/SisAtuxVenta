package com.atux.bean.kardex;

import com.aw.core.format.NumberFormatter;
import com.aw.core.util.NumberUtils;
import com.aw.core.validation.annotation.MoneyFormat;
import com.aw.core.validation.annotation.NumberFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * Created by JAVA on 15/11/2014.
 */
public class Producto {
    protected final Log LOG = LogFactory.getLog(getClass());
    public static BigDecimal FACTOR_PRECIO = new BigDecimal(1.32);

    private String coCompania;
    private String coProducto;
    private String coProveedor;
    private String nuGuia;
    private String feAnnio;
    private String deProducto;
    private String nuRevision;
    private String esProdLocal;
    private String unidad;
    private Long caEmpaqueMin;
    private Long caEmpaqueMax;
    private Date feModificacion;
    private Date feUltCambio;
    private Date feCreacion;
    private Date feVigencia;
    private String minFraccion;
    private String maxFraccion;
    private String unidadProducto;
    private String unidadFraccion;
    private String moneda;
    private String esProductoLocal;
    private String inProdInventario;
    private String inProdFraccionado;
    private String deLaboratorio;
    private String justificacion;
    private BigDecimal vaFraccion;
    private BigDecimal caSaldo;
    private BigDecimal stkEnteroActual;
    private BigDecimal stkFraccionActual;
    private BigDecimal stkEntero;
    private BigDecimal stkFraccion;
    private BigDecimal stkDisponible;
    private BigDecimal stkDisponibleFmt;
    private BigDecimal precioProveedor;
    private BigDecimal precioPromedio;
    private BigDecimal precioPVPFinal;
    private BigDecimal precioPVP;
    private BigDecimal precioVenta;
    private BigDecimal precioVentaSinIGV;
    private BigDecimal precioVentaPublico;
    private BigDecimal precioValorizado;
    private BigDecimal porcentajeDescuento;
    private BigDecimal porcentajeMargen;
    private BigDecimal vaImpuesto;

    private Integer vaExhibicion;
    private Integer nroDiasSinCambioPrecio;
    private String lineaG1;
    private String divisionG2;
    private String subdivisionG3;
    private String familiaG4;
    private String subFamiliaG5;

    private BigDecimal vaVta01;
    private BigDecimal vaUni01;
    private BigDecimal vaMg01;
    private BigDecimal vaVta02;
    private BigDecimal vaUni02;
    private BigDecimal vaMg02;
    private BigDecimal vaVta03;
    private BigDecimal vaUni03;
    private BigDecimal vaMg03;
    private BigDecimal vaVta04;
    private BigDecimal vaUni04;
    private BigDecimal vaMg04;
    private BigDecimal vaVta05;
    private BigDecimal vaUni05;
    private BigDecimal vaMg05;
    private BigDecimal vaVta06;
    private BigDecimal vaUni06;
    private BigDecimal vaMg06;
    private BigDecimal vaVta07;
    private BigDecimal vaUni07;
    private BigDecimal vaMg07;
    private BigDecimal vaVta08;
    private BigDecimal vaUni08;
    private BigDecimal vaMg08;
    private BigDecimal vaVta09;
    private BigDecimal vaUni09;
    private BigDecimal vaMg09;
    private BigDecimal vaVta10;
    private BigDecimal vaUni10;
    private BigDecimal vaMg10;
    private BigDecimal vaVta11;
    private BigDecimal vaUni11;
    private BigDecimal vaMg11;
    private BigDecimal vaVta12;
    private BigDecimal vaUni12;
    private BigDecimal vaMg12;


    public String getStkDisponibleFmt() {
        if ("S".equals(getInProdFraccionado())) {
            return NumberFormatter.INTEGER_FORMATTER_W_GRP_SYM.format(NumberUtils.nvlZero(getStkEntero()))
                    + " / " + NumberFormatter.INTEGER_FORMATTER_W_GRP_SYM.format(NumberUtils.nvlZero(getStkFraccion()));
        }
        return (String) NumberFormatter.INTEGER_FORMATTER_W_GRP_SYM.format(NumberUtils.nvlZero(getStkEntero()));
    }

    public String getUnidad() {
        return "S".equals(getInProdFraccionado()) ? getUnidadFraccion() : getUnidadProducto();
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

    public String getLineaG1() {
        return lineaG1;
    }

    public void setLineaG1(String lineaG1) {
        this.lineaG1 = lineaG1;
    }

    public String getDivisionG2() {
        return divisionG2;
    }

    public void setDivisionG2(String divisionG2) {
        this.divisionG2 = divisionG2;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getDeLaboratorio() {
        return deLaboratorio;
    }

    public void setDeLaboratorio(String deLaboratorio) {
        this.deLaboratorio = deLaboratorio;
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

    public String getEsProductoLocal() {
        return esProductoLocal;
    }

    public void setEsProductoLocal(String esProductoLocal) {
        this.esProductoLocal = esProductoLocal;
    }

    public String getInProdInventario() {
        return inProdInventario;
    }

    public void setInProdInventario(String inProdInventario) {
        this.inProdInventario = inProdInventario;
    }

    @NumberFormat
    public BigDecimal getVaFraccion() {
        return vaFraccion;
    }

    public void setVaFraccion(BigDecimal vaFraccion) {
        this.vaFraccion = vaFraccion;
    }

    @NumberFormat
    public BigDecimal getCantidad() {
        if ("S".equals(getInProdFraccionado())) {
            return NumberUtils.nvlZero(stkEntero).multiply(NumberUtils.nvlZero(vaFraccion))
                    .add((NumberUtils.nvlZero(stkFraccion)));
        } else {
            return NumberUtils.nvlZero(stkEntero);
        }
    }


    @NumberFormat
    public BigDecimal getStkEntero() {
        return stkEntero;
    }

    public void setStkEntero(BigDecimal stkEntero) {
        this.stkEntero = stkEntero;
    }

    @NumberFormat
    public BigDecimal getStkFraccion() {
        return stkFraccion;
    }

    public void setStkFraccion(BigDecimal stkFraccion) {
        this.stkFraccion = stkFraccion;
    }

    @MoneyFormat
    public BigDecimal getPrecioPromedio() {
        return precioPromedio;
    }

    public void setPrecioPromedio(BigDecimal precioPromedio) {
        this.precioPromedio = precioPromedio;
    }

    @MoneyFormat
    public BigDecimal getPrecioValorizado() {
        return precioValorizado;
    }

    public void setPrecioValorizado(BigDecimal precioValorizado) {
        this.precioValorizado = precioValorizado;
    }

    public String getSubdivisionG3() {
        return subdivisionG3;
    }

    public void setSubdivisionG3(String subdivisionG3) {
        this.subdivisionG3 = subdivisionG3;
    }

    public String getFamiliaG4() {
        return familiaG4;
    }

    public void setFamiliaG4(String familiaG4) {
        this.familiaG4 = familiaG4;
    }

    public String getSubFamiliaG5() {
        return subFamiliaG5;
    }

    public void setSubFamiliaG5(String subFamiliaG5) {
        this.subFamiliaG5 = subFamiliaG5;
    }

    public BigDecimal getCaSaldo() {
        return caSaldo;
    }

    public void setCaSaldo(BigDecimal caSaldo) {
        this.caSaldo = caSaldo;
    }


    @MoneyFormat
    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    @MoneyFormat
    public BigDecimal getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public String getInProdFraccionado() {
        return inProdFraccionado;
    }

    public void setInProdFraccionado(String inProdFraccionado) {
        this.inProdFraccionado = inProdFraccionado;
    }

    public BigDecimal getStkEnteroActual() {
        return stkEnteroActual;
    }

    public void setStkEnteroActual(BigDecimal stkEnteroActual) {
        this.stkEnteroActual = stkEnteroActual;
    }

    public BigDecimal getStkFraccionActual() {
        return stkFraccionActual;
    }

    public void setStkFraccionActual(BigDecimal stkFraccionActual) {
        this.stkFraccionActual = stkFraccionActual;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public Date getFeModificacion() {
        return feModificacion;
    }

    public void setFeModificacion(Date feModificacion) {
        this.feModificacion = feModificacion;
    }

    @MoneyFormat
    public BigDecimal getPrecioProveedor() {
        return precioProveedor;
    }

    public void setPrecioProveedor(BigDecimal precioProveedor) {
        this.precioProveedor = precioProveedor;
    }

    @MoneyFormat
    public BigDecimal getPorcentajeMargen() {
        return porcentajeMargen;
    }

    public void setPorcentajeMargen(BigDecimal porcentajeMargen) {
        this.porcentajeMargen = porcentajeMargen;
    }

    @MoneyFormat
    public BigDecimal getPrecioVentaPublico() {
        return precioVentaPublico;
    }

    public void setPrecioVentaPublico(BigDecimal precioVentaPublico) {
        this.precioVentaPublico = precioVentaPublico;
    }


    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    @MoneyFormat
    public BigDecimal getPrecioPVPFinal() {
        return precioPVPFinal;
    }

    public void setPrecioPVPFinal(BigDecimal precioPVPFinal) {
        this.precioPVPFinal = precioPVPFinal;
    }

    @MoneyFormat
    public BigDecimal getPrecioPVP() {
        return precioPVP;
    }

    public void setPrecioPVP(BigDecimal precioPVP) {
        this.precioPVP = precioPVP;
    }

    @MoneyFormat
    public BigDecimal getVaImpuesto() {
        return vaImpuesto;
    }

    public void setVaImpuesto(BigDecimal vaImpuesto) {
        this.vaImpuesto = vaImpuesto;
    }

    public void recalcularPrecios() {
        calcularPvP();
        calcularPrecioVenta();
        calcularPrecioPVPFinal();
        calcularMargen();
    }


    private void calcularMargen() {
        if (getPrecioPromedio() == null) return;
        setPorcentajeMargen(NumberUtils.nvlZero(getPrecioPVP()).divide(NumberUtils.nvlZero(getPrecioPromedio()), 2, RoundingMode.HALF_UP));

        LOG.info(" porcentajeMargen <" + getPorcentajeMargen() + ">");
    }

    public void calcularPvP() {
        setPrecioPVP(NumberUtils.nvlZero(getPrecioProveedor())
                .multiply(FACTOR_PRECIO)
                .multiply(new BigDecimal(1).subtract(
                        NumberUtils.nvlZero(getPorcentajeDescuento())
                                .divide(new BigDecimal(100), 4, RoundingMode.HALF_UP)
                )));
        LOG.info("precioPVP <" + getPrecioPVP() + ">");
    }

    public void calcularPrecioVenta() {
        setPrecioVenta(NumberUtils.nvlZero(getPrecioProveedor())
                        .multiply(FACTOR_PRECIO)
                .multiply(new BigDecimal(1).add(
                        getVaImpuesto().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP))));
        ;
        LOG.info(" precioVenta <" + getPrecioVenta() + ">");
    }

    public void calcularPrecioVentaPublico() {

        setPrecioVentaPublico(NumberUtils.nvlZero(getPrecioProveedor())
                .multiply(FACTOR_PRECIO)
                .multiply(
                        new BigDecimal(1).subtract(NumberUtils.nvlZero(getPorcentajeDescuento())
                                .divide(new BigDecimal(100), 2, RoundingMode.HALF_UP)))
                .multiply(new BigDecimal(1).add(getVaImpuesto().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP))));

        LOG.info(" precioVentaPublico  <" + getPrecioVentaPublico() + "> precioProveedor<" + getPrecioProveedor() + "> porcentajeDescuento<" + getPorcentajeDescuento() + "> vaImpuesto<" + getVaImpuesto() + ">");

    }

    public void calcularPrecioPVPFinal() {
        setPrecioPVPFinal(getPrecioVentaPublico());
        LOG.info("precioPVPFinal <" + getPrecioPVPFinal() + ">");
    }

    public String toStringPrecios() {
        return "Producto{" +
                "coProducto='" + coProducto + '\'' +
                ", precioProveedor=" + precioProveedor +
                ", precioPromedio=" + precioPromedio +
                ", precioPVPFinal=" + precioPVPFinal +
                ", precioPVP=" + precioPVP +
                ", precioVenta=" + precioVenta +
                ", precioVentaPublico=" + precioVentaPublico +
                ", porcentajeDescuento=" + porcentajeDescuento +
                ", porcentajeMargen=" + porcentajeMargen +
                ", vaImpuesto=" + vaImpuesto +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Producto producto = (Producto) o;

        if (coCompania != null ? !coCompania.equals(producto.coCompania) : producto.coCompania != null) return false;
        if (coProducto != null ? !coProducto.equals(producto.coProducto) : producto.coProducto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = coCompania != null ? coCompania.hashCode() : 0;
        result = 31 * result + (coProducto != null ? coProducto.hashCode() : 0);
        return result;
    }

    public String getCoProveedor() {
        return coProveedor;
    }

    public void setCoProveedor(String coProveedor) {
        this.coProveedor = coProveedor;
    }

    public String getNuGuia() {
        return nuGuia;
    }

    public void setNuGuia(String nuGuia) {
        this.nuGuia = nuGuia;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getNuRevision() {
        return nuRevision;
    }

    public void setNuRevision(String nuRevision) {
        this.nuRevision = nuRevision;
    }

    @NumberFormat
    public Integer getVaExhibicion() {
        return vaExhibicion;
    }

    public void setVaExhibicion(Integer vaExhibicion) {
        this.vaExhibicion = vaExhibicion;
    }

    public BigDecimal getStkDisponible() {
        return stkDisponible;
    }

    public void setStkDisponible(BigDecimal stkDisponible) {
        this.stkDisponible = stkDisponible;
    }

    public BigDecimal getPrecioVentaSinIGV() {
        return precioVentaSinIGV;
    }

    public void setPrecioVentaSinIGV(BigDecimal precioVentaSinIGV) {
        this.precioVentaSinIGV = precioVentaSinIGV;
    }

    public BigDecimal getVaVta01() {
        return vaVta01;
    }

    public void setVaVta01(BigDecimal vaVta01) {
        this.vaVta01 = vaVta01;
    }

    public BigDecimal getVaUni01() {
        return vaUni01;
    }

    public void setVaUni01(BigDecimal vaUni01) {
        this.vaUni01 = vaUni01;
    }

    public BigDecimal getVaMg01() {
        return vaMg01;
    }

    public void setVaMg01(BigDecimal vaMg01) {
        this.vaMg01 = vaMg01;
    }

    public BigDecimal getVaVta02() {
        return vaVta02;
    }

    public void setVaVta02(BigDecimal vaVta02) {
        this.vaVta02 = vaVta02;
    }

    public BigDecimal getVaUni02() {
        return vaUni02;
    }

    public void setVaUni02(BigDecimal vaUni02) {
        this.vaUni02 = vaUni02;
    }

    public BigDecimal getVaMg02() {
        return vaMg02;
    }

    public void setVaMg02(BigDecimal vaMg02) {
        this.vaMg02 = vaMg02;
    }

    public BigDecimal getVaVta03() {
        return vaVta03;
    }

    public void setVaVta03(BigDecimal vaVta03) {
        this.vaVta03 = vaVta03;
    }

    public BigDecimal getVaUni03() {
        return vaUni03;
    }

    public void setVaUni03(BigDecimal vaUni03) {
        this.vaUni03 = vaUni03;
    }

    public BigDecimal getVaMg03() {
        return vaMg03;
    }

    public void setVaMg03(BigDecimal vaMg03) {
        this.vaMg03 = vaMg03;
    }

    public BigDecimal getVaVta04() {
        return vaVta04;
    }

    public void setVaVta04(BigDecimal vaVta04) {
        this.vaVta04 = vaVta04;
    }

    public BigDecimal getVaUni04() {
        return vaUni04;
    }

    public void setVaUni04(BigDecimal vaUni04) {
        this.vaUni04 = vaUni04;
    }

    public BigDecimal getVaMg04() {
        return vaMg04;
    }

    public void setVaMg04(BigDecimal vaMg04) {
        this.vaMg04 = vaMg04;
    }

    public BigDecimal getVaVta05() {
        return vaVta05;
    }

    public void setVaVta05(BigDecimal vaVta05) {
        this.vaVta05 = vaVta05;
    }

    public BigDecimal getVaUni05() {
        return vaUni05;
    }

    public void setVaUni05(BigDecimal vaUni05) {
        this.vaUni05 = vaUni05;
    }

    public BigDecimal getVaMg05() {
        return vaMg05;
    }

    public void setVaMg05(BigDecimal vaMg05) {
        this.vaMg05 = vaMg05;
    }

    public BigDecimal getVaVta06() {
        return vaVta06;
    }

    public void setVaVta06(BigDecimal vaVta06) {
        this.vaVta06 = vaVta06;
    }

    public BigDecimal getVaUni06() {
        return vaUni06;
    }

    public void setVaUni06(BigDecimal vaUni06) {
        this.vaUni06 = vaUni06;
    }

    public BigDecimal getVaMg06() {
        return vaMg06;
    }

    public void setVaMg06(BigDecimal vaMg06) {
        this.vaMg06 = vaMg06;
    }

    public BigDecimal getVaVta07() {
        return vaVta07;
    }

    public void setVaVta07(BigDecimal vaVta07) {
        this.vaVta07 = vaVta07;
    }

    public BigDecimal getVaUni07() {
        return vaUni07;
    }

    public void setVaUni07(BigDecimal vaUni07) {
        this.vaUni07 = vaUni07;
    }

    public BigDecimal getVaMg07() {
        return vaMg07;
    }

    public void setVaMg07(BigDecimal vaMg07) {
        this.vaMg07 = vaMg07;
    }

    public BigDecimal getVaVta08() {
        return vaVta08;
    }

    public void setVaVta08(BigDecimal vaVta08) {
        this.vaVta08 = vaVta08;
    }

    public BigDecimal getVaUni08() {
        return vaUni08;
    }

    public void setVaUni08(BigDecimal vaUni08) {
        this.vaUni08 = vaUni08;
    }

    public BigDecimal getVaMg08() {
        return vaMg08;
    }

    public void setVaMg08(BigDecimal vaMg08) {
        this.vaMg08 = vaMg08;
    }

    public BigDecimal getVaVta09() {
        return vaVta09;
    }

    public void setVaVta09(BigDecimal vaVta09) {
        this.vaVta09 = vaVta09;
    }

    public BigDecimal getVaUni09() {
        return vaUni09;
    }

    public void setVaUni09(BigDecimal vaUni09) {
        this.vaUni09 = vaUni09;
    }

    public BigDecimal getVaMg09() {
        return vaMg09;
    }

    public void setVaMg09(BigDecimal vaMg09) {
        this.vaMg09 = vaMg09;
    }

    public BigDecimal getVaVta10() {
        return vaVta10;
    }

    public void setVaVta10(BigDecimal vaVta10) {
        this.vaVta10 = vaVta10;
    }

    public BigDecimal getVaUni10() {
        return vaUni10;
    }

    public void setVaUni10(BigDecimal vaUni10) {
        this.vaUni10 = vaUni10;
    }

    public BigDecimal getVaMg10() {
        return vaMg10;
    }

    public void setVaMg10(BigDecimal vaMg10) {
        this.vaMg10 = vaMg10;
    }

    public BigDecimal getVaVta11() {
        return vaVta11;
    }

    public void setVaVta11(BigDecimal vaVta11) {
        this.vaVta11 = vaVta11;
    }

    public BigDecimal getVaUni11() {
        return vaUni11;
    }

    public void setVaUni11(BigDecimal vaUni11) {
        this.vaUni11 = vaUni11;
    }

    public BigDecimal getVaMg11() {
        return vaMg11;
    }

    public void setVaMg11(BigDecimal vaMg11) {
        this.vaMg11 = vaMg11;
    }

    public BigDecimal getVaVta12() {
        return vaVta12;
    }

    public void setVaVta12(BigDecimal vaVta12) {
        this.vaVta12 = vaVta12;
    }

    public BigDecimal getVaUni12() {
        return vaUni12;
    }

    public void setVaUni12(BigDecimal vaUni12) {
        this.vaUni12 = vaUni12;
    }

    public BigDecimal getVaMg12() {
        return vaMg12;
    }

    public void setVaMg12(BigDecimal vaMg12) {
        this.vaMg12 = vaMg12;
    }

    public String getFeAnnio() {
        return feAnnio;
    }

    public void setFeAnnio(String feAnnio) {
        this.feAnnio = feAnnio;
    }

    public Integer getNroDiasSinCambioPrecio() {
        return nroDiasSinCambioPrecio;
    }

    public void setNroDiasSinCambioPrecio(Integer nroDiasSinCambioPrecio) {
        this.nroDiasSinCambioPrecio = nroDiasSinCambioPrecio;
    }

    public Date getFeUltCambio() {
        return feUltCambio;
    }

    public void setFeUltCambio(Date feUltCambio) {
        this.feUltCambio = feUltCambio;
    }

    public Date getFeCreacion() {
        return feCreacion;
    }

    public void setFeCreacion(Date feCreacion) {
        this.feCreacion = feCreacion;
    }

    public void setStkDisponibleFmt(BigDecimal stkDisponibleFmt) {
        this.stkDisponibleFmt = stkDisponibleFmt;
    }

    public Date getFeVigencia() {
        return feVigencia;
    }

    public void setFeVigencia(Date feVigencia) {
        this.feVigencia = feVigencia;
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


    @NumberFormat
    public Long getCaEmpaqueMin() {
        return caEmpaqueMin;
    }

    public void setCaEmpaqueMin(Long caEmpaqueMin) {
        this.caEmpaqueMin = caEmpaqueMin;
    }

    @NumberFormat
    public Long getCaEmpaqueMax() {
        return caEmpaqueMax;
    }

    public void setCaEmpaqueMax(Long caEmpaqueMax) {
        this.caEmpaqueMax = caEmpaqueMax;
    }


}
