package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class Locales extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;

    public static final String nt = "VTTM_LOCAL";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_LOCAL"};
    public static final String COLUMNA_ACTIVO = "ES_LOCAL";
    public static final String[] COLUMNA_ORDER ={"DE_LOCAL"};

    private String  coCompania;
    private String  coLocal;
    private String  tiLocal;
    private String  inTipoCaja;
    private String  coRegion;
    private String  deCortaLocal;
    private String  deLocal;
    private String  coPais;
    private String  coDepartamento;
    private String  coProvincia;
    private String  coDistrito;
    private String  tiVia;
    private String  noVia;
    private Integer nuVia;
    private String  nuInteriorVia;
    private String  nuManzanaVia;
    private String  nuLoteVia;
    private String  tiPoblacion;
    private String  noPoblacion;
    private String  deDireccionLocal;
    private String  coTelPais;
    private String  coTelCiudad;
    private String  nuTelNormal;
    private String  coExtTelNormal;
    private String  nuTelFax;
    private String  nuTelMovil;
    private String  idRespLocal;
    private String  idRespAlternoLocal;
    private String  deEmail;
    private String  coLocalDelivery;
    private String  esLocal;
    private String  idCreaLocal;
    private Date    feCreaLocal;
    private String  idModLocal;
    private Date    feModLocal;
    private String  inImprimeTestigo;
    private String  inImpComanda;
    private String  inImprimeTicket;
    private String  deMensajeTicket;
    private String  inTicketBoleta;
    private String  inTicketFactura;
    private String  coZonaSupervision;
    private Integer nuOrdenApertura;
    private Date    feApertura;
    private Date    feCierre;
    private String  inProvincia;
    private Double  vaMontoCotizacion;
    private String  inAfectoIgvLocal;
    private Integer tsDias;
    private Integer trDias;
    private String  coSucursal;
    private String  coCadenaLocal;
    private Integer vaMinimoPedidoDelivery;
    private Integer nuDiasRotacionPromedio;
    private Integer nuMinDiasReposicion;
    private Integer nuMaxDiasReposicion;
    private Integer pcAdicionalPedidoReposicion;
    private Integer vaFactorBajaRotacion;
    private String  inPedRepEnProc;
    private Date    feCalculoMaxMin;
    private Integer nuDiasInventario;
    private Integer nuDiasVenta;
    private String  inIgnorarProdSinSaldo;
    private String  inSumarTiempoSuministro;
    private String  inSumarTransito;
    private String  inSumarMinExhibicion;
    private String  inSumarComprasPendientes;
    private String  inTipoOperacion;
    private String  inOrigenProductos;
    private String  inSoloProdActivos;
    private String  inProductosFraccionados;
    private String  inFaltaCero;
    

    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_COMPANIA, CO_LOCAL, TI_LOCAL, IN_TIPO_CAJA, CO_REGION, DE_CORTA_LOCAL, DE_LOCAL, CO_PAIS, CO_DEPARTAMENTO, CO_PROVINCIA, "
                      + "CO_DISTRITO, TI_VIA, NO_VIA, NU_VIA, NU_INTERIOR_VIA, NU_MANZANA_VIA, NU_LOTE_VIA, TI_POBLACION, NO_POBLACION, DE_DIRECCION_LOCAL, "
                      + "CO_TEL_PAIS, CO_TEL_CIUDAD, NU_TEL_NORMAL, CO_EXT_TEL_NORMAL, NU_TEL_FAX, NU_TEL_MOVIL, ID_RESP_LOCAL, ID_RESP_ALTERNO_LOCAL, "
                      + "DE_EMAIL, CO_LOCAL_DELIVERY, ES_LOCAL, ID_CREA_LOCAL, FE_CREA_LOCAL, ID_MOD_LOCAL, FE_MOD_LOCAL, IN_IMPRIME_TESTIGO, IN_IMP_COMANDA, "
                      + "IN_IMPRIME_TICKET, DE_MENSAJE_TICKET, IN_TICKET_BOLETA, IN_TICKET_FACTURA, CO_ZONA_SUPERVISION, NU_ORDEN_APERTURA, FE_APERTURA, "
                      + "IN_PROVINCIA, VA_MONTO_COTIZACION, IN_AFECTO_IGV_LOCAL, TS_DIAS, TR_DIAS, CO_SUCURSAL, CO_CADENA_LOCAL, VA_MINIMO_PEDIDO_DELIVERY,"
                      + "NU_DIAS_ROTACION_PROMEDIO, NU_MIN_DIAS_REPOSICION, NU_MAX_DIAS_REPOSICION, PC_ADICIONAL_PEDIDO_REPOSICION, "
                      + "VA_FACTOR_BAJA_ROTACION, IN_PED_REP_EN_PROC, FE_CALCULO_MAX_MIN, NU_DIAS_INVENTARIO, NU_DIAS_VENTA, IN_IGNORAR_PROD_SIN_SALDO,"
                      + "IN_SUMAR_TIEMPO_SUMINISTRO, IN_SUMAR_TRANSITO, IN_SUMAR_MIN_EXHIBICION, IN_SUMAR_COMPRAS_PENDIENTES, IN_TIPO_OPERACION, "
                      + "IN_ORIGEN_PRODUCTOS, IN_SOLO_PROD_ACTIVOS, IN_PRODUCTOS_FRACCIONADOS, IN_FALTA_CERO, FE_CIERRE"};

    public static final String[] 
      CAMPOS_NO_ID ={"TI_LOCAL, IN_TIPO_CAJA, CO_REGION, DE_CORTA_LOCAL, DE_LOCAL, CO_PAIS, CO_DEPARTAMENTO, CO_PROVINCIA, "
                   + "CO_DISTRITO, TI_VIA, NO_VIA, NU_VIA, NU_INTERIOR_VIA, NU_MANZANA_VIA, NU_LOTE_VIA, TI_POBLACION, NO_POBLACION, DE_DIRECCION_LOCAL, "
                   + "CO_TEL_PAIS, CO_TEL_CIUDAD, NU_TEL_NORMAL, CO_EXT_TEL_NORMAL, NU_TEL_FAX, NU_TEL_MOVIL, ID_RESP_LOCAL, ID_RESP_ALTERNO_LOCAL, "
                   + "DE_EMAIL, CO_LOCAL_DELIVERY, ES_LOCAL, ID_CREA_LOCAL, FE_CREA_LOCAL, ID_MOD_LOCAL, FE_MOD_LOCAL, IN_IMPRIME_TESTIGO, IN_IMP_COMANDA, "
                   + "IN_IMPRIME_TICKET, DE_MENSAJE_TICKET, IN_TICKET_BOLETA, IN_TICKET_FACTURA, CO_ZONA_SUPERVISION, NU_ORDEN_APERTURA, FE_APERTURA, "
                   + "IN_PROVINCIA, VA_MONTO_COTIZACION, IN_AFECTO_IGV_LOCAL, TS_DIAS, TR_DIAS, CO_SUCURSAL, CO_CADENA_LOCAL, VA_MINIMO_PEDIDO_DELIVERY,"
                   + "NU_DIAS_ROTACION_PROMEDIO, NU_MIN_DIAS_REPOSICION, NU_MAX_DIAS_REPOSICION, PC_ADICIONAL_PEDIDO_REPOSICION, "
                   + "VA_FACTOR_BAJA_ROTACION, IN_PED_REP_EN_PROC, FE_CALCULO_MAX_MIN, NU_DIAS_INVENTARIO, NU_DIAS_VENTA, IN_IGNORAR_PROD_SIN_SALDO,"
                   + "IN_SUMAR_TIEMPO_SUMINISTRO, IN_SUMAR_TRANSITO, IN_SUMAR_MIN_EXHIBICION, IN_SUMAR_COMPRAS_PENDIENTES, IN_TIPO_OPERACION, "
                   + "IN_ORIGEN_PRODUCTOS, IN_SOLO_PROD_ACTIVOS, IN_PRODUCTOS_FRACCIONADOS, IN_FALTA_CERO, FE_CIERRE"};

    public String getCoCadenaLocal() {
        return coCadenaLocal;
    }

    public void setCoCadenaLocal(String coCadenaLocal) {
        this.coCadenaLocal = coCadenaLocal;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoDepartamento() {
        return coDepartamento;
    }

    public void setCoDepartamento(String coDepartamento) {
        this.coDepartamento = coDepartamento;
    }

    public String getCoDistrito() {
        return coDistrito;
    }

    public void setCoDistrito(String coDistrito) {
        this.coDistrito = coDistrito;
    }

    public String getCoExtTelNormal() {
        return coExtTelNormal;
    }

    public void setCoExtTelNormal(String coExtTelNormal) {
        this.coExtTelNormal = coExtTelNormal;
    }

    public String getCoLocal() {
        return coLocal;
    }

    public void setCoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public String getCoLocalDelivery() {
        return coLocalDelivery;
    }

    public void setCoLocalDelivery(String coLocalDelivery) {
        this.coLocalDelivery = coLocalDelivery;
    }

    public String getCoPais() {
        return coPais;
    }

    public void setCoPais(String coPais) {
        this.coPais = coPais;
    }

    public String getCoProvincia() {
        return coProvincia;
    }

    public void setCoProvincia(String coProvincia) {
        this.coProvincia = coProvincia;
    }

    public String getCoRegion() {
        return coRegion;
    }

    public void setCoRegion(String coRegion) {
        this.coRegion = coRegion;
    }

    public String getCoSucursal() {
        return coSucursal;
    }

    public void setCoSucursal(String coSucursal) {
        this.coSucursal = coSucursal;
    }

    public String getCoTelCiudad() {
        return coTelCiudad;
    }

    public void setCoTelCiudad(String coTelCiudad) {
        this.coTelCiudad = coTelCiudad;
    }

    public String getCoTelPais() {
        return coTelPais;
    }

    public void setCoTelPais(String coTelPais) {
        this.coTelPais = coTelPais;
    }

    public String getCoZonaSupervision() {
        return coZonaSupervision;
    }

    public void setCoZonaSupervision(String coZonaSupervision) {
        this.coZonaSupervision = coZonaSupervision;
    }

    public String getDeCortaLocal() {
        return deCortaLocal;
    }

    public void setDeCortaLocal(String deCortaLocal) {
        this.deCortaLocal = deCortaLocal;
    }

    public String getDeDireccionLocal() {
        return deDireccionLocal;
    }

    public void setDeDireccionLocal(String deDireccionLocal) {
        this.deDireccionLocal = deDireccionLocal;
    }

    public String getDeEmail() {
        return deEmail;
    }

    public void setDeEmail(String deEmail) {
        this.deEmail = deEmail;
    }

    public String getDeLocal() {
        return deLocal;
    }

    public void setDeLocal(String deLocal) {
        this.deLocal = deLocal;
    }

    public String getDeMensajeTicket() {
        return deMensajeTicket;
    }

    public void setDeMensajeTicket(String deMensajeTicket) {
        this.deMensajeTicket = deMensajeTicket;
    }

    public String getEsLocal() {
        return esLocal;
    }

    public void setEsLocal(String esLocal) {
        this.esLocal = esLocal;
    }

    public Date getFeApertura() {
        return feApertura;
    }

    public void setFeApertura(Date feApertura) {
        this.feApertura = feApertura;
    }

    public Date getFeCierre() {
        return feCierre;
    }

    public void setFeCierre(Date feCierre) {
        this.feCierre = feCierre;
    }

    public Date getFeCalculoMaxMin() {
        return feCalculoMaxMin;
    }

    public void setFeCalculoMaxMin(Date feCalculoMaxMin) {
        this.feCalculoMaxMin = feCalculoMaxMin;
    }

    public Date getFeCreaLocal() {
        return feCreaLocal;
    }

    public void setFeCreaLocal(Date feCreaLocal) {
        this.feCreaLocal = feCreaLocal;
    }

    public Date getFeModLocal() {
        return feModLocal;
    }

    public void setFeModLocal(Date feModLocal) {
        this.feModLocal = feModLocal;
    }

    public String getIdCreaLocal() {
        return idCreaLocal;
    }

    public void setIdCreaLocal(String idCreaLocal) {
        this.idCreaLocal = idCreaLocal;
    }

    public String getIdModLocal() {
        return idModLocal;
    }

    public void setIdModLocal(String idModLocal) {
        this.idModLocal = idModLocal;
    }

    public String getIdRespAlternoLocal() {
        return idRespAlternoLocal;
    }

    public void setIdRespAlternoLocal(String idRespAlternoLocal) {
        this.idRespAlternoLocal = idRespAlternoLocal;
    }

    public String getIdRespLocal() {
        return idRespLocal;
    }

    public void setIdRespLocal(String idRespLocal) {
        this.idRespLocal = idRespLocal;
    }

    public String getInAfectoIgvLocal() {
        return inAfectoIgvLocal;
    }

    public void setInAfectoIgvLocal(String inAfectoIgvLocal) {
        this.inAfectoIgvLocal = inAfectoIgvLocal;
    }

    public String getInFaltaCero() {
        return inFaltaCero;
    }

    public void setInFaltaCero(String inFaltaCero) {
        this.inFaltaCero = inFaltaCero;
    }

    public String getInIgnorarProdSinSaldo() {
        return inIgnorarProdSinSaldo;
    }

    public void setInIgnorarProdSinSaldo(String inIgnorarProdSinSaldo) {
        this.inIgnorarProdSinSaldo = inIgnorarProdSinSaldo;
    }

    public String getInImpComanda() {
        return inImpComanda;
    }

    public void setInImpComanda(String inImpComanda) {
        this.inImpComanda = inImpComanda;
    }

    public String getInImprimeTestigo() {
        return inImprimeTestigo;
    }

    public void setInImprimeTestigo(String inImprimeTestigo) {
        this.inImprimeTestigo = inImprimeTestigo;
    }

    public String getInImprimeTicket() {
        return inImprimeTicket;
    }

    public void setInImprimeTicket(String inImprimeTicket) {
        this.inImprimeTicket = inImprimeTicket;
    }

    public String getInOrigenProductos() {
        return inOrigenProductos;
    }

    public void setInOrigenProductos(String inOrigenProductos) {
        this.inOrigenProductos = inOrigenProductos;
    }

    public String getInPedRepEnProc() {
        return inPedRepEnProc;
    }

    public void setInPedRepEnProc(String inPedRepEnProc) {
        this.inPedRepEnProc = inPedRepEnProc;
    }

    public String getInProductosFraccionados() {
        return inProductosFraccionados;
    }

    public void setInProductosFraccionados(String inProductosFraccionados) {
        this.inProductosFraccionados = inProductosFraccionados;
    }

    public String getInProvincia() {
        return inProvincia;
    }

    public void setInProvincia(String inProvincia) {
        this.inProvincia = inProvincia;
    }

    public String getInSoloProdActivos() {
        return inSoloProdActivos;
    }

    public void setInSoloProdActivos(String inSoloProdActivos) {
        this.inSoloProdActivos = inSoloProdActivos;
    }

    public String getInSumarComprasPendientes() {
        return inSumarComprasPendientes;
    }

    public void setInSumarComprasPendientes(String inSumarComprasPendientes) {
        this.inSumarComprasPendientes = inSumarComprasPendientes;
    }

    public String getInSumarMinExhibicion() {
        return inSumarMinExhibicion;
    }

    public void setInSumarMinExhibicion(String inSumarMinExhibicion) {
        this.inSumarMinExhibicion = inSumarMinExhibicion;
    }

    public String getInSumarTiempoSuministro() {
        return inSumarTiempoSuministro;
    }

    public void setInSumarTiempoSuministro(String inSumarTiempoSuministro) {
        this.inSumarTiempoSuministro = inSumarTiempoSuministro;
    }

    public String getInSumarTransito() {
        return inSumarTransito;
    }

    public void setInSumarTransito(String inSumarTransito) {
        this.inSumarTransito = inSumarTransito;
    }

    public String getInTicketBoleta() {
        return inTicketBoleta;
    }

    public void setInTicketBoleta(String inTicketBoleta) {
        this.inTicketBoleta = inTicketBoleta;
    }

    public String getInTicketFactura() {
        return inTicketFactura;
    }

    public void setInTicketFactura(String inTicketFactura) {
        this.inTicketFactura = inTicketFactura;
    }

    public String getInTipoCaja() {
        return inTipoCaja;
    }

    public void setInTipoCaja(String inTipoCaja) {
        this.inTipoCaja = inTipoCaja;
    }

    public String getInTipoOperacion() {
        return inTipoOperacion;
    }

    public void setInTipoOperacion(String inTipoOperacion) {
        this.inTipoOperacion = inTipoOperacion;
    }

    public String getNoPoblacion() {
        return noPoblacion;
    }

    public void setNoPoblacion(String noPoblacion) {
        this.noPoblacion = noPoblacion;
    }

    public String getNoVia() {
        return noVia;
    }

    public void setNoVia(String noVia) {
        this.noVia = noVia;
    }

    public Integer getNuDiasInventario() {
        return nuDiasInventario;
    }

    public void setNuDiasInventario(Integer nuDiasInventario) {
        this.nuDiasInventario = nuDiasInventario;
    }

    public Integer getNuDiasRotacionPromedio() {
        return nuDiasRotacionPromedio;
    }

    public void setNuDiasRotacionPromedio(Integer nuDiasRotacionPromedio) {
        this.nuDiasRotacionPromedio = nuDiasRotacionPromedio;
    }

    public Integer getNuDiasVenta() {
        return nuDiasVenta;
    }

    public void setNuDiasVenta(Integer nuDiasVenta) {
        this.nuDiasVenta = nuDiasVenta;
    }

    public String getNuInteriorVia() {
        return nuInteriorVia;
    }

    public void setNuInteriorVia(String nuInteriorVia) {
        this.nuInteriorVia = nuInteriorVia;
    }

    public String getNuLoteVia() {
        return nuLoteVia;
    }

    public void setNuLoteVia(String nuLoteVia) {
        this.nuLoteVia = nuLoteVia;
    }

    public String getNuManzanaVia() {
        return nuManzanaVia;
    }

    public void setNuManzanaVia(String nuManzanaVia) {
        this.nuManzanaVia = nuManzanaVia;
    }

    public Integer getNuMaxDiasReposicion() {
        return nuMaxDiasReposicion;
    }

    public void setNuMaxDiasReposicion(Integer nuMaxDiasReposicion) {
        this.nuMaxDiasReposicion = nuMaxDiasReposicion;
    }

    public Integer getNuMinDiasReposicion() {
        return nuMinDiasReposicion;
    }

    public void setNuMinDiasReposicion(Integer nuMinDiasReposicion) {
        this.nuMinDiasReposicion = nuMinDiasReposicion;
    }

    public Integer getNuOrdenApertura() {
        return nuOrdenApertura;
    }

    public void setNuOrdenApertura(Integer nuOrdenApertura) {
        this.nuOrdenApertura = nuOrdenApertura;
    }

    public String getNuTelFax() {
        return nuTelFax;
    }

    public void setNuTelFax(String nuTelFax) {
        this.nuTelFax = nuTelFax;
    }

    public String getNuTelMovil() {
        return nuTelMovil;
    }

    public void setNuTelMovil(String nuTelMovil) {
        this.nuTelMovil = nuTelMovil;
    }

    public String getNuTelNormal() {
        return nuTelNormal;
    }

    public void setNuTelNormal(String nuTelNormal) {
        this.nuTelNormal = nuTelNormal;
    }

    public Integer getNuVia() {
        return nuVia;
    }

    public void setNuVia(Integer nuVia) {
        this.nuVia = nuVia;
    }

    public Integer getPcAdicionalPedidoReposicion() {
        return pcAdicionalPedidoReposicion;
    }

    public void setPcAdicionalPedidoReposicion(Integer pcAdicionalPedidoReposicion) {
        this.pcAdicionalPedidoReposicion = pcAdicionalPedidoReposicion;
    }

    public String getTiLocal() {
        return tiLocal;
    }

    public void setTiLocal(String tiLocal) {
        this.tiLocal = tiLocal;
    }

    public String getTiPoblacion() {
        return tiPoblacion;
    }

    public void setTiPoblacion(String tiPoblacion) {
        this.tiPoblacion = tiPoblacion;
    }

    public Integer getTrDias() {
        return trDias;
    }

    public void setTrDias(Integer trDias) {
        this.trDias = trDias;
    }

    public Integer getTsDias() {
        return tsDias;
    }

    public void setTsDias(Integer tsDias) {
        this.tsDias = tsDias;
    }

    public Integer getVaFactorBajaRotacion() {
        return vaFactorBajaRotacion;
    }

    public void setVaFactorBajaRotacion(Integer vaFactorBajaRotacion) {
        this.vaFactorBajaRotacion = vaFactorBajaRotacion;
    }

    public Integer getVaMinimoPedidoDelivery() {
        return vaMinimoPedidoDelivery;
    }

    public void setVaMinimoPedidoDelivery(Integer vaMinimoPedidoDelivery) {
        this.vaMinimoPedidoDelivery = vaMinimoPedidoDelivery;
    }

    public Double getVaMontoCotizacion() {
        return vaMontoCotizacion;
    }

    public void setVaMontoCotizacion(Double vaMontoCotizacion) {
        this.vaMontoCotizacion = vaMontoCotizacion;
    }

    public String getTiVia() {
        return tiVia;
    }

    public void setTiVia(String tiVia) {
        this.tiVia = tiVia;
    }

    
    
    public Locales() {
        initBasic();
    }
    
    private void initBasic()
    {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
    }    
    
    @Override
    public String toString() {
        //return "tCambio{" + "nu_Sec_Tipo_Cambio=" + nuSecTipoCambio + ", fe_tipo_cambio=" + feTipoCambio + '}';
        return deLocal;
    } 
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (primaryKey != null ? primaryKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Locales)) {
            return false;
        }
        Locales other = (Locales) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }              
}