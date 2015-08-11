package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.Locales;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxVariables;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CLocales extends JAbstractController{
    private Locales mLocales;
   
    
    @Override
    public ArrayList<Locales> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{Locales.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<Locales> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<Locales> getRegistros() {                 
        ArrayList<Locales> rgs = new ArrayList<Locales>();
        try {                        
//            rs = this.getRegistros(TipoDeCambio.nt,TipoDeCambio.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(Locales.nt, Locales.FULL_NOM_CAMPOS, null, null, Locales.COLUMNA_ORDER);
            while(rs.next())
            {
                mLocales = new Locales();
                mLocales.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL")});
                mLocales.setCoCompania(rs.getString("CO_COMPANIA"));
                mLocales.setCoLocal(rs.getString("CO_LOCAL"));
                mLocales.setTiLocal(rs.getString("TI_LOCAL"));
                mLocales.setInTipoCaja(rs.getString("IN_TIPO_CAJA"));
                mLocales.setCoRegion(rs.getString("CO_REGION"));
                mLocales.setDeCortaLocal(rs.getString("DE_CORTA_LOCAL"));
                mLocales.setDeLocal(rs.getString("DE_LOCAL"));
                mLocales.setCoPais(rs.getString("CO_PAIS"));
                mLocales.setCoDepartamento(rs.getString("CO_DEPARTAMENTO"));
                mLocales.setCoProvincia(rs.getString("CO_PROVINCIA"));
                mLocales.setCoDistrito(rs.getString("CO_DISTRITO"));
                mLocales.setTiVia(rs.getString("TI_VIA"));
                mLocales.setNoVia(rs.getString("NO_VIA"));
                mLocales.setNuVia(rs.getInt("NU_VIA"));
                mLocales.setNuInteriorVia(rs.getString("NU_INTERIOR_VIA"));
                mLocales.setNuManzanaVia(rs.getString("NU_MANZANA_VIA"));
                mLocales.setNuLoteVia(rs.getString("NU_LOTE_VIA"));
                mLocales.setTiPoblacion(rs.getString("TI_POBLACION"));
                mLocales.setNoPoblacion(rs.getString("NO_POBLACION"));
                mLocales.setDeDireccionLocal(rs.getString("DE_DIRECCION_LOCAL"));
                mLocales.setCoTelPais(rs.getString("CO_TEL_PAIS"));
                mLocales.setCoTelCiudad(rs.getString("CO_TEL_CIUDAD"));
                mLocales.setNuTelNormal(rs.getString("NU_TEL_NORMAL"));
                mLocales.setCoExtTelNormal(rs.getString("CO_EXT_TEL_NORMAL"));
                mLocales.setNuTelFax(rs.getString("NU_TEL_FAX"));
                mLocales.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                mLocales.setIdRespLocal(rs.getString("ID_RESP_LOCAL"));
                mLocales.setIdRespAlternoLocal(rs.getString("ID_RESP_ALTERNO_LOCAL"));
                mLocales.setDeEmail(rs.getString("DE_EMAIL"));
                mLocales.setCoLocalDelivery(rs.getString("CO_LOCAL_DELIVERY"));
                mLocales.setEsLocal(rs.getString("ES_LOCAL"));
                mLocales.setIdCreaLocal(rs.getString("ID_CREA_LOCAL"));
                mLocales.setFeCreaLocal(rs.getDate("FE_CREA_LOCAL"));
                mLocales.setIdModLocal(rs.getString("ID_MOD_LOCAL"));
                mLocales.setFeModLocal(rs.getDate("FE_MOD_LOCAL"));
                mLocales.setInImprimeTestigo(rs.getString("IN_IMPRIME_TESTIGO"));
                mLocales.setInImpComanda(rs.getString("IN_IMP_COMANDA"));
                mLocales.setInImprimeTicket(rs.getString("IN_IMPRIME_TICKET"));
                mLocales.setDeMensajeTicket(rs.getString("DE_MENSAJE_TICKET"));
                mLocales.setInTicketBoleta(rs.getString("IN_TICKET_BOLETA"));
                mLocales.setInTicketFactura(rs.getString("IN_TICKET_FACTURA"));
                mLocales.setCoZonaSupervision(rs.getString("CO_ZONA_SUPERVISION"));
                mLocales.setNuOrdenApertura(rs.getInt("NU_ORDEN_APERTURA"));
                mLocales.setFeApertura(rs.getDate("FE_APERTURA"));
                mLocales.setInProvincia(rs.getString("IN_PROVINCIA"));
                mLocales.setVaMontoCotizacion(rs.getDouble("VA_MONTO_COTIZACION"));
                mLocales.setInAfectoIgvLocal(rs.getString("IN_AFECTO_IGV_LOCAL"));
                mLocales.setTsDias(rs.getInt("TS_DIAS"));
                mLocales.setTrDias(rs.getInt("TR_DIAS"));
                mLocales.setCoSucursal(rs.getString("CO_SUCURSAL"));
                mLocales.setCoCadenaLocal(rs.getString("CO_CADENA_LOCAL"));
                mLocales.setVaMinimoPedidoDelivery(rs.getInt("VA_MINIMO_PEDIDO_DELIVERY"));
                mLocales.setNuDiasRotacionPromedio(rs.getInt("NU_DIAS_ROTACION_PROMEDIO"));
                mLocales.setNuMinDiasReposicion(rs.getInt("NU_MIN_DIAS_REPOSICION"));
                mLocales.setNuMaxDiasReposicion(rs.getInt("NU_MAX_DIAS_REPOSICION"));
                mLocales.setPcAdicionalPedidoReposicion(rs.getInt("PC_ADICIONAL_PEDIDO_REPOSICION"));
                mLocales.setVaFactorBajaRotacion(rs.getInt("VA_FACTOR_BAJA_ROTACION"));
                mLocales.setInPedRepEnProc(rs.getString("IN_PED_REP_EN_PROC"));
                mLocales.setFeCalculoMaxMin(rs.getDate("FE_CALCULO_MAX_MIN"));
                mLocales.setNuDiasInventario(rs.getInt("NU_DIAS_INVENTARIO"));
                mLocales.setNuDiasVenta(rs.getInt("NU_DIAS_VENTA"));
                mLocales.setInIgnorarProdSinSaldo(rs.getString("IN_IGNORAR_PROD_SIN_SALDO"));
                mLocales.setInSumarTiempoSuministro(rs.getString("IN_SUMAR_TIEMPO_SUMINISTRO"));
                mLocales.setInSumarTransito(rs.getString("IN_SUMAR_TRANSITO"));
                mLocales.setInSumarMinExhibicion(rs.getString("IN_SUMAR_MIN_EXHIBICION"));
                mLocales.setInSumarComprasPendientes(rs.getString("IN_SUMAR_COMPRAS_PENDIENTES"));
                mLocales.setInTipoOperacion(rs.getString("IN_TIPO_OPERACION"));
                mLocales.setInOrigenProductos(rs.getString("IN_ORIGEN_PRODUCTOS"));
                mLocales.setInSoloProdActivos(rs.getString("IN_SOLO_PROD_ACTIVOS"));
                mLocales.setInProductosFraccionados(rs.getString("IN_PRODUCTOS_FRACCIONADOS"));
                mLocales.setInFaltaCero(rs.getString("IN_FALTA_CERO"));
                mLocales.setFeCierre(rs.getDate("FE_CIERRE"));
                rgs.add(mLocales);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public Locales getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(Locales.nt, Locales.FULL_NOM_CAMPOS, Locales.COLUMNA_PK , id);
            while(rs.next())
            {
                mLocales = new Locales();
                mLocales.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL")});
                mLocales.setCoCompania(rs.getString("CO_COMPANIA"));
                mLocales.setCoLocal(rs.getString("CO_LOCAL"));
                mLocales.setTiLocal(rs.getString("TI_LOCAL"));
                mLocales.setInTipoCaja(rs.getString("IN_TIPO_CAJA"));
                mLocales.setCoRegion(rs.getString("CO_REGION"));
                mLocales.setDeCortaLocal(rs.getString("DE_CORTA_LOCAL"));
                mLocales.setDeLocal(rs.getString("DE_LOCAL"));
                mLocales.setCoPais(rs.getString("CO_PAIS"));
                mLocales.setCoDepartamento(rs.getString("CO_DEPARTAMENTO"));
                mLocales.setCoProvincia(rs.getString("CO_PROVINCIA"));
                mLocales.setCoDistrito(rs.getString("CO_DISTRITO"));
                mLocales.setTiVia(rs.getString("TI_VIA"));
                mLocales.setNoVia(rs.getString("NO_VIA"));
                mLocales.setNuVia(rs.getInt("NU_VIA"));
                mLocales.setNuInteriorVia(rs.getString("NU_INTERIOR_VIA"));
                mLocales.setNuManzanaVia(rs.getString("NU_MANZANA_VIA"));
                mLocales.setNuLoteVia(rs.getString("NU_LOTE_VIA"));
                mLocales.setTiPoblacion(rs.getString("TI_POBLACION"));
                mLocales.setNoPoblacion(rs.getString("NO_POBLACION"));
                mLocales.setDeDireccionLocal(rs.getString("DE_DIRECCION_LOCAL"));
                mLocales.setCoTelPais(rs.getString("CO_TEL_PAIS"));
                mLocales.setCoTelCiudad(rs.getString("CO_TEL_CIUDAD"));
                mLocales.setNuTelNormal(rs.getString("NU_TEL_NORMAL"));
                mLocales.setCoExtTelNormal(rs.getString("CO_EXT_TEL_NORMAL"));
                mLocales.setNuTelFax(rs.getString("NU_TEL_FAX"));
                mLocales.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                mLocales.setIdRespLocal(rs.getString("ID_RESP_LOCAL"));
                mLocales.setIdRespAlternoLocal(rs.getString("ID_RESP_ALTERNO_LOCAL"));
                mLocales.setDeEmail(rs.getString("DE_EMAIL"));
                mLocales.setCoLocalDelivery(rs.getString("CO_LOCAL_DELIVERY"));
                mLocales.setEsLocal(rs.getString("ES_LOCAL"));
                mLocales.setIdCreaLocal(rs.getString("ID_CREA_LOCAL"));
                mLocales.setFeCreaLocal(rs.getDate("FE_CREA_LOCAL"));
                mLocales.setIdModLocal(rs.getString("ID_MOD_LOCAL"));
                mLocales.setFeModLocal(rs.getDate("FE_MOD_LOCAL"));
                mLocales.setInImprimeTestigo(rs.getString("IN_IMPRIME_TESTIGO"));
                mLocales.setInImpComanda(rs.getString("IN_IMP_COMANDA"));
                mLocales.setInImprimeTicket(rs.getString("IN_IMPRIME_TICKET"));
                mLocales.setDeMensajeTicket(rs.getString("DE_MENSAJE_TICKET"));
                mLocales.setInTicketBoleta(rs.getString("IN_TICKET_BOLETA"));
                mLocales.setInTicketFactura(rs.getString("IN_TICKET_FACTURA"));
                mLocales.setCoZonaSupervision(rs.getString("CO_ZONA_SUPERVISION"));
                mLocales.setNuOrdenApertura(rs.getInt("NU_ORDEN_APERTURA"));
                mLocales.setFeApertura(rs.getDate("FE_APERTURA"));
                mLocales.setInProvincia(rs.getString("IN_PROVINCIA"));
                mLocales.setVaMontoCotizacion(rs.getDouble("VA_MONTO_COTIZACION"));
                mLocales.setInAfectoIgvLocal(rs.getString("IN_AFECTO_IGV_LOCAL"));
                mLocales.setTsDias(rs.getInt("TS_DIAS"));
                mLocales.setTrDias(rs.getInt("TR_DIAS"));
                mLocales.setCoSucursal(rs.getString("CO_SUCURSAL"));
                mLocales.setCoCadenaLocal(rs.getString("CO_CADENA_LOCAL"));
                mLocales.setVaMinimoPedidoDelivery(rs.getInt("VA_MINIMO_PEDIDO_DELIVERY"));
                mLocales.setNuDiasRotacionPromedio(rs.getInt("NU_DIAS_ROTACION_PROMEDIO"));
                mLocales.setNuMinDiasReposicion(rs.getInt("NU_MIN_DIAS_REPOSICION"));
                mLocales.setNuMaxDiasReposicion(rs.getInt("NU_MAX_DIAS_REPOSICION"));
                mLocales.setPcAdicionalPedidoReposicion(rs.getInt("PC_ADICIONAL_PEDIDO_REPOSICION"));
                mLocales.setVaFactorBajaRotacion(rs.getInt("VA_FACTOR_BAJA_ROTACION"));
                mLocales.setInPedRepEnProc(rs.getString("IN_PED_REP_EN_PROC"));
                mLocales.setFeCalculoMaxMin(rs.getDate("FE_CALCULO_MAX_MIN"));
                mLocales.setNuDiasInventario(rs.getInt("NU_DIAS_INVENTARIO"));
                mLocales.setNuDiasVenta(rs.getInt("NU_DIAS_VENTA"));
                mLocales.setInIgnorarProdSinSaldo(rs.getString("IN_IGNORAR_PROD_SIN_SALDO"));
                mLocales.setInSumarTiempoSuministro(rs.getString("IN_SUMAR_TIEMPO_SUMINISTRO"));
                mLocales.setInSumarTransito(rs.getString("IN_SUMAR_TRANSITO"));
                mLocales.setInSumarMinExhibicion(rs.getString("IN_SUMAR_MIN_EXHIBICION"));
                mLocales.setInSumarComprasPendientes(rs.getString("IN_SUMAR_COMPRAS_PENDIENTES"));
                mLocales.setInTipoOperacion(rs.getString("IN_TIPO_OPERACION"));
                mLocales.setInOrigenProductos(rs.getString("IN_ORIGEN_PRODUCTOS"));
                mLocales.setInSoloProdActivos(rs.getString("IN_SOLO_PROD_ACTIVOS"));
                mLocales.setInProductosFraccionados(rs.getString("IN_PRODUCTOS_FRACCIONADOS"));
                mLocales.setInFaltaCero(rs.getString("IN_FALTA_CERO"));
                mLocales.setFeCierre(rs.getDate("FE_CIERRE"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return mLocales;
    }
    
    @Override
    public ArrayList<Locales> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<Locales> rgs = new ArrayList<Locales>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(Locales.nt, Locales.COLUMNA_ACTIVO, Locales.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(Locales.nt, Locales.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(Locales.nt, campos, columnaId, id, Locales.COLUMNA_ORDER);
            if(this.numRegistros<finalPag)
           {
              finalPag =  this.numRegistros;
           }
            if(finalPag>inicioPag)
            {
                inicioPag -= (finalPag-inicioPag)-1;
            }
            while(rs.next())
            {
                mLocales = new Locales();
                mLocales.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL")});
                mLocales.setCoCompania(rs.getString("CO_COMPANIA"));
                mLocales.setCoLocal(rs.getString("CO_LOCAL"));
                mLocales.setTiLocal(rs.getString("TI_LOCAL"));
                mLocales.setInTipoCaja(rs.getString("IN_TIPO_CAJA"));
                mLocales.setCoRegion(rs.getString("CO_REGION"));
                mLocales.setDeCortaLocal(rs.getString("DE_CORTA_LOCAL"));
                mLocales.setDeLocal(rs.getString("DE_LOCAL"));
                mLocales.setCoPais(rs.getString("CO_PAIS"));
                mLocales.setCoDepartamento(rs.getString("CO_DEPARTAMENTO"));
                mLocales.setCoProvincia(rs.getString("CO_PROVINCIA"));
                mLocales.setCoDistrito(rs.getString("CO_DISTRITO"));
                mLocales.setTiVia(rs.getString("TI_VIA"));
                mLocales.setNoVia(rs.getString("NO_VIA"));
                mLocales.setNuVia(rs.getInt("NU_VIA"));
                mLocales.setNuInteriorVia(rs.getString("NU_INTERIOR_VIA"));
                mLocales.setNuManzanaVia(rs.getString("NU_MANZANA_VIA"));
                mLocales.setNuLoteVia(rs.getString("NU_LOTE_VIA"));
                mLocales.setTiPoblacion(rs.getString("TI_POBLACION"));
                mLocales.setNoPoblacion(rs.getString("NO_POBLACION"));
                mLocales.setDeDireccionLocal(rs.getString("DE_DIRECCION_LOCAL"));
                mLocales.setCoTelPais(rs.getString("CO_TEL_PAIS"));
                mLocales.setCoTelCiudad(rs.getString("CO_TEL_CIUDAD"));
                mLocales.setNuTelNormal(rs.getString("NU_TEL_NORMAL"));
                mLocales.setCoExtTelNormal(rs.getString("CO_EXT_TEL_NORMAL"));
                mLocales.setNuTelFax(rs.getString("NU_TEL_FAX"));
                mLocales.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                mLocales.setIdRespLocal(rs.getString("ID_RESP_LOCAL"));
                mLocales.setIdRespAlternoLocal(rs.getString("ID_RESP_ALTERNO_LOCAL"));
                mLocales.setDeEmail(rs.getString("DE_EMAIL"));
                mLocales.setCoLocalDelivery(rs.getString("CO_LOCAL_DELIVERY"));
                mLocales.setEsLocal(rs.getString("ES_LOCAL"));
                mLocales.setIdCreaLocal(rs.getString("ID_CREA_LOCAL"));
                mLocales.setFeCreaLocal(rs.getDate("FE_CREA_LOCAL"));
                mLocales.setIdModLocal(rs.getString("ID_MOD_LOCAL"));
                mLocales.setFeModLocal(rs.getDate("FE_MOD_LOCAL"));
                mLocales.setInImprimeTestigo(rs.getString("IN_IMPRIME_TESTIGO"));
                mLocales.setInImpComanda(rs.getString("IN_IMP_COMANDA"));
                mLocales.setInImprimeTicket(rs.getString("IN_IMPRIME_TICKET"));
                mLocales.setDeMensajeTicket(rs.getString("DE_MENSAJE_TICKET"));
                mLocales.setInTicketBoleta(rs.getString("IN_TICKET_BOLETA"));
                mLocales.setInTicketFactura(rs.getString("IN_TICKET_FACTURA"));
                mLocales.setCoZonaSupervision(rs.getString("CO_ZONA_SUPERVISION"));
                mLocales.setNuOrdenApertura(rs.getInt("NU_ORDEN_APERTURA"));
                mLocales.setFeApertura(rs.getDate("FE_APERTURA"));
                mLocales.setInProvincia(rs.getString("IN_PROVINCIA"));
                mLocales.setVaMontoCotizacion(rs.getDouble("VA_MONTO_COTIZACION"));
                mLocales.setInAfectoIgvLocal(rs.getString("IN_AFECTO_IGV_LOCAL"));
                mLocales.setTsDias(rs.getInt("TS_DIAS"));
                mLocales.setTrDias(rs.getInt("TR_DIAS"));
                mLocales.setCoSucursal(rs.getString("CO_SUCURSAL"));
                mLocales.setCoCadenaLocal(rs.getString("CO_CADENA_LOCAL"));
                mLocales.setVaMinimoPedidoDelivery(rs.getInt("VA_MINIMO_PEDIDO_DELIVERY"));
                mLocales.setNuDiasRotacionPromedio(rs.getInt("NU_DIAS_ROTACION_PROMEDIO"));
                mLocales.setNuMinDiasReposicion(rs.getInt("NU_MIN_DIAS_REPOSICION"));
                mLocales.setNuMaxDiasReposicion(rs.getInt("NU_MAX_DIAS_REPOSICION"));
                mLocales.setPcAdicionalPedidoReposicion(rs.getInt("PC_ADICIONAL_PEDIDO_REPOSICION"));
                mLocales.setVaFactorBajaRotacion(rs.getInt("VA_FACTOR_BAJA_ROTACION"));
                mLocales.setInPedRepEnProc(rs.getString("IN_PED_REP_EN_PROC"));
                mLocales.setFeCalculoMaxMin(rs.getDate("FE_CALCULO_MAX_MIN"));
                mLocales.setNuDiasInventario(rs.getInt("NU_DIAS_INVENTARIO"));
                mLocales.setNuDiasVenta(rs.getInt("NU_DIAS_VENTA"));
                mLocales.setInIgnorarProdSinSaldo(rs.getString("IN_IGNORAR_PROD_SIN_SALDO"));
                mLocales.setInSumarTiempoSuministro(rs.getString("IN_SUMAR_TIEMPO_SUMINISTRO"));
                mLocales.setInSumarTransito(rs.getString("IN_SUMAR_TRANSITO"));
                mLocales.setInSumarMinExhibicion(rs.getString("IN_SUMAR_MIN_EXHIBICION"));
                mLocales.setInSumarComprasPendientes(rs.getString("IN_SUMAR_COMPRAS_PENDIENTES"));
                mLocales.setInTipoOperacion(rs.getString("IN_TIPO_OPERACION"));
                mLocales.setInOrigenProductos(rs.getString("IN_ORIGEN_PRODUCTOS"));
                mLocales.setInSoloProdActivos(rs.getString("IN_SOLO_PROD_ACTIVOS"));
                mLocales.setInProductosFraccionados(rs.getString("IN_PRODUCTOS_FRACCIONADOS"));
                mLocales.setInFaltaCero(rs.getString("IN_FALTA_CERO"));
                mLocales.setFeCierre(rs.getDate("FE_CIERRE"));
                rgs.add(mLocales);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
        
    }

    public ArrayList getListaLocales()
     {
        ArrayList<Locales> rgs = new ArrayList<Locales>();
        try {                        
            StringBuffer  query = new StringBuffer();

            query.append("SELECT *  ");            
            query.append("  FROM CMTR_TIPO_VIA ");
            query.append(" WHERE ES_TIPO_VIA ='A' ");
            query.append(" ORDER BY DE_TIPO_VIA");
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                mLocales = new Locales();
                mLocales.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL")});
                mLocales.setCoCompania(rs.getString("CO_COMPANIA"));
                mLocales.setCoLocal(rs.getString("CO_LOCAL"));
                mLocales.setTiLocal(rs.getString("TI_LOCAL"));
                mLocales.setInTipoCaja(rs.getString("IN_TIPO_CAJA"));
                mLocales.setCoRegion(rs.getString("CO_REGION"));
                mLocales.setDeCortaLocal(rs.getString("DE_CORTA_LOCAL"));
                mLocales.setDeLocal(rs.getString("DE_LOCAL"));
                mLocales.setCoPais(rs.getString("CO_PAIS"));
                mLocales.setCoDepartamento(rs.getString("CO_DEPARTAMENTO"));
                mLocales.setCoProvincia(rs.getString("CO_PROVINCIA"));
                mLocales.setCoDistrito(rs.getString("CO_DISTRITO"));
                mLocales.setTiVia(rs.getString("TI_VIA"));
                mLocales.setNoVia(rs.getString("NO_VIA"));
                mLocales.setNuVia(rs.getInt("NU_VIA"));
                mLocales.setNuInteriorVia(rs.getString("NU_INTERIOR_VIA"));
                mLocales.setNuManzanaVia(rs.getString("NU_MANZANA_VIA"));
                mLocales.setNuLoteVia(rs.getString("NU_LOTE_VIA"));
                mLocales.setTiPoblacion(rs.getString("TI_POBLACION"));
                mLocales.setNoPoblacion(rs.getString("NO_POBLACION"));
                mLocales.setDeDireccionLocal(rs.getString("DE_DIRECCION_LOCAL"));
                mLocales.setCoTelPais(rs.getString("CO_TEL_PAIS"));
                mLocales.setCoTelCiudad(rs.getString("CO_TEL_CIUDAD"));
                mLocales.setNuTelNormal(rs.getString("NU_TEL_NORMAL"));
                mLocales.setCoExtTelNormal(rs.getString("CO_EXT_TEL_NORMAL"));
                mLocales.setNuTelFax(rs.getString("NU_TEL_FAX"));
                mLocales.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                mLocales.setIdRespLocal(rs.getString("ID_RESP_LOCAL"));
                mLocales.setIdRespAlternoLocal(rs.getString("ID_RESP_ALTERNO_LOCAL"));
                mLocales.setDeEmail(rs.getString("DE_EMAIL"));
                mLocales.setCoLocalDelivery(rs.getString("CO_LOCAL_DELIVERY"));
                mLocales.setEsLocal(rs.getString("ES_LOCAL"));
                mLocales.setIdCreaLocal(rs.getString("ID_CREA_LOCAL"));
                mLocales.setFeCreaLocal(rs.getDate("FE_CREA_LOCAL"));
                mLocales.setIdModLocal(rs.getString("ID_MOD_LOCAL"));
                mLocales.setFeModLocal(rs.getDate("FE_MOD_LOCAL"));
                mLocales.setInImprimeTestigo(rs.getString("IN_IMPRIME_TESTIGO"));
                mLocales.setInImpComanda(rs.getString("IN_IMP_COMANDA"));
                mLocales.setInImprimeTicket(rs.getString("IN_IMPRIME_TICKET"));
                mLocales.setDeMensajeTicket(rs.getString("DE_MENSAJE_TICKET"));
                mLocales.setInTicketBoleta(rs.getString("IN_TICKET_BOLETA"));
                mLocales.setInTicketFactura(rs.getString("IN_TICKET_FACTURA"));
                mLocales.setCoZonaSupervision(rs.getString("CO_ZONA_SUPERVISION"));
                mLocales.setNuOrdenApertura(rs.getInt("NU_ORDEN_APERTURA"));
                mLocales.setFeApertura(rs.getDate("FE_APERTURA"));
                mLocales.setInProvincia(rs.getString("IN_PROVINCIA"));
                mLocales.setVaMontoCotizacion(rs.getDouble("VA_MONTO_COTIZACION"));
                mLocales.setInAfectoIgvLocal(rs.getString("IN_AFECTO_IGV_LOCAL"));
                mLocales.setTsDias(rs.getInt("TS_DIAS"));
                mLocales.setTrDias(rs.getInt("TR_DIAS"));
                mLocales.setCoSucursal(rs.getString("CO_SUCURSAL"));
                mLocales.setCoCadenaLocal(rs.getString("CO_CADENA_LOCAL"));
                mLocales.setVaMinimoPedidoDelivery(rs.getInt("VA_MINIMO_PEDIDO_DELIVERY"));
                mLocales.setNuDiasRotacionPromedio(rs.getInt("NU_DIAS_ROTACION_PROMEDIO"));
                mLocales.setNuMinDiasReposicion(rs.getInt("NU_MIN_DIAS_REPOSICION"));
                mLocales.setNuMaxDiasReposicion(rs.getInt("NU_MAX_DIAS_REPOSICION"));
                mLocales.setPcAdicionalPedidoReposicion(rs.getInt("PC_ADICIONAL_PEDIDO_REPOSICION"));
                mLocales.setVaFactorBajaRotacion(rs.getInt("VA_FACTOR_BAJA_ROTACION"));
                mLocales.setInPedRepEnProc(rs.getString("IN_PED_REP_EN_PROC"));
                mLocales.setFeCalculoMaxMin(rs.getDate("FE_CALCULO_MAX_MIN"));
                mLocales.setNuDiasInventario(rs.getInt("NU_DIAS_INVENTARIO"));
                mLocales.setNuDiasVenta(rs.getInt("NU_DIAS_VENTA"));
                mLocales.setInIgnorarProdSinSaldo(rs.getString("IN_IGNORAR_PROD_SIN_SALDO"));
                mLocales.setInSumarTiempoSuministro(rs.getString("IN_SUMAR_TIEMPO_SUMINISTRO"));
                mLocales.setInSumarTransito(rs.getString("IN_SUMAR_TRANSITO"));
                mLocales.setInSumarMinExhibicion(rs.getString("IN_SUMAR_MIN_EXHIBICION"));
                mLocales.setInSumarComprasPendientes(rs.getString("IN_SUMAR_COMPRAS_PENDIENTES"));
                mLocales.setInTipoOperacion(rs.getString("IN_TIPO_OPERACION"));
                mLocales.setInOrigenProductos(rs.getString("IN_ORIGEN_PRODUCTOS"));
                mLocales.setInSoloProdActivos(rs.getString("IN_SOLO_PROD_ACTIVOS"));
                mLocales.setInProductosFraccionados(rs.getString("IN_PRODUCTOS_FRACCIONADOS"));
                mLocales.setInFaltaCero(rs.getString("IN_FALTA_CERO"));
                mLocales.setFeCierre(rs.getDate("FE_CIERRE"));
                rgs.add(mLocales);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }    
    
    
    
    @Override
    public JAbstractModelBD getRegistro() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JAbstractModelBD getRegistro(JAbstractModelBD mdl, boolean opcion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JAbstractModelBD buscarRegistro(Object cvl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public ArrayList getBusquedaLocales(String Filtro){
        ArrayList<Locales> rgs = new ArrayList<Locales>();
        Locales      usr      = null;
        StringBuffer  query;
        try {            
            query = new StringBuffer();
            
            query.append("select * ");
            query.append("  from VTTM_LOCAL ");
            query.append(" WHERE CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("' ");

            if ("A".equals(Filtro) || "I".equals(Filtro)){
                query.append("   AND T1.ES_LOCAL = '").append(Filtro).append("' ");
            }else if (!"T".equals(Filtro)){
                query.append(Filtro);
            }
            
            query.append(" ORDER BY DE_LOCAL ");
            
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next()){
                mLocales = new Locales();
                mLocales.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL")});
                mLocales.setCoCompania(rs.getString("CO_COMPANIA"));
                mLocales.setCoLocal(rs.getString("CO_LOCAL"));
                mLocales.setTiLocal(rs.getString("TI_LOCAL"));
                mLocales.setInTipoCaja(rs.getString("IN_TIPO_CAJA"));
                mLocales.setCoRegion(rs.getString("CO_REGION"));
                mLocales.setDeCortaLocal(rs.getString("DE_CORTA_LOCAL"));
                mLocales.setDeLocal(rs.getString("DE_LOCAL"));
                mLocales.setCoPais(rs.getString("CO_PAIS"));
                mLocales.setCoDepartamento(rs.getString("CO_DEPARTAMENTO"));
                mLocales.setCoProvincia(rs.getString("CO_PROVINCIA"));
                mLocales.setCoDistrito(rs.getString("CO_DISTRITO"));
                mLocales.setTiVia(rs.getString("TI_VIA"));
                mLocales.setNoVia(rs.getString("NO_VIA"));
                mLocales.setNuVia(rs.getInt("NU_VIA"));
                mLocales.setNuInteriorVia(rs.getString("NU_INTERIOR_VIA"));
                mLocales.setNuManzanaVia(rs.getString("NU_MANZANA_VIA"));
                mLocales.setNuLoteVia(rs.getString("NU_LOTE_VIA"));
                mLocales.setTiPoblacion(rs.getString("TI_POBLACION"));
                mLocales.setNoPoblacion(rs.getString("NO_POBLACION"));
                mLocales.setDeDireccionLocal(rs.getString("DE_DIRECCION_LOCAL"));
                mLocales.setCoTelPais(rs.getString("CO_TEL_PAIS"));
                mLocales.setCoTelCiudad(rs.getString("CO_TEL_CIUDAD"));
                mLocales.setNuTelNormal(rs.getString("NU_TEL_NORMAL"));
                mLocales.setCoExtTelNormal(rs.getString("CO_EXT_TEL_NORMAL"));
                mLocales.setNuTelFax(rs.getString("NU_TEL_FAX"));
                mLocales.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                mLocales.setIdRespLocal(rs.getString("ID_RESP_LOCAL"));
                mLocales.setIdRespAlternoLocal(rs.getString("ID_RESP_ALTERNO_LOCAL"));
                mLocales.setDeEmail(rs.getString("DE_EMAIL"));
                mLocales.setCoLocalDelivery(rs.getString("CO_LOCAL_DELIVERY"));
                mLocales.setEsLocal(rs.getString("ES_LOCAL"));
                mLocales.setIdCreaLocal(rs.getString("ID_CREA_LOCAL"));
                mLocales.setFeCreaLocal(rs.getDate("FE_CREA_LOCAL"));
                mLocales.setIdModLocal(rs.getString("ID_MOD_LOCAL"));
                mLocales.setFeModLocal(rs.getDate("FE_MOD_LOCAL"));
                mLocales.setInImprimeTestigo(rs.getString("IN_IMPRIME_TESTIGO"));
                mLocales.setInImpComanda(rs.getString("IN_IMP_COMANDA"));
                mLocales.setInImprimeTicket(rs.getString("IN_IMPRIME_TICKET"));
                mLocales.setDeMensajeTicket(rs.getString("DE_MENSAJE_TICKET"));
                mLocales.setInTicketBoleta(rs.getString("IN_TICKET_BOLETA"));
                mLocales.setInTicketFactura(rs.getString("IN_TICKET_FACTURA"));
                mLocales.setCoZonaSupervision(rs.getString("CO_ZONA_SUPERVISION"));
                mLocales.setNuOrdenApertura(rs.getInt("NU_ORDEN_APERTURA"));
                mLocales.setFeApertura(rs.getDate("FE_APERTURA"));
                mLocales.setInProvincia(rs.getString("IN_PROVINCIA"));
                mLocales.setVaMontoCotizacion(rs.getDouble("VA_MONTO_COTIZACION"));
                mLocales.setInAfectoIgvLocal(rs.getString("IN_AFECTO_IGV_LOCAL"));
                mLocales.setTsDias(rs.getInt("TS_DIAS"));
                mLocales.setTrDias(rs.getInt("TR_DIAS"));
                mLocales.setCoSucursal(rs.getString("CO_SUCURSAL"));
                mLocales.setCoCadenaLocal(rs.getString("CO_CADENA_LOCAL"));
                mLocales.setVaMinimoPedidoDelivery(rs.getInt("VA_MINIMO_PEDIDO_DELIVERY"));
                mLocales.setNuDiasRotacionPromedio(rs.getInt("NU_DIAS_ROTACION_PROMEDIO"));
                mLocales.setNuMinDiasReposicion(rs.getInt("NU_MIN_DIAS_REPOSICION"));
                mLocales.setNuMaxDiasReposicion(rs.getInt("NU_MAX_DIAS_REPOSICION"));
                mLocales.setPcAdicionalPedidoReposicion(rs.getInt("PC_ADICIONAL_PEDIDO_REPOSICION"));
                mLocales.setVaFactorBajaRotacion(rs.getInt("VA_FACTOR_BAJA_ROTACION"));
                mLocales.setInPedRepEnProc(rs.getString("IN_PED_REP_EN_PROC"));
                mLocales.setFeCalculoMaxMin(rs.getDate("FE_CALCULO_MAX_MIN"));
                mLocales.setNuDiasInventario(rs.getInt("NU_DIAS_INVENTARIO"));
                mLocales.setNuDiasVenta(rs.getInt("NU_DIAS_VENTA"));
                mLocales.setInIgnorarProdSinSaldo(rs.getString("IN_IGNORAR_PROD_SIN_SALDO"));
                mLocales.setInSumarTiempoSuministro(rs.getString("IN_SUMAR_TIEMPO_SUMINISTRO"));
                mLocales.setInSumarTransito(rs.getString("IN_SUMAR_TRANSITO"));
                mLocales.setInSumarMinExhibicion(rs.getString("IN_SUMAR_MIN_EXHIBICION"));
                mLocales.setInSumarComprasPendientes(rs.getString("IN_SUMAR_COMPRAS_PENDIENTES"));
                mLocales.setInTipoOperacion(rs.getString("IN_TIPO_OPERACION"));
                mLocales.setInOrigenProductos(rs.getString("IN_ORIGEN_PRODUCTOS"));
                mLocales.setInSoloProdActivos(rs.getString("IN_SOLO_PROD_ACTIVOS"));
                mLocales.setInProductosFraccionados(rs.getString("IN_PRODUCTOS_FRACCIONADOS"));
                mLocales.setInFaltaCero(rs.getString("IN_FALTA_CERO"));
                mLocales.setFeCierre(rs.getDate("FE_CIERRE"));
                rgs.add(mLocales);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
        // Se cierran los recursos de base de datos.
        try {
        if (rs != null) {
            rs.close();
            }
        } catch (SQLException e) {
                System.out.println("No ha podido cerrar ResultSet.");
            }
        } 
        return rgs;
    }
    
    
    
    @Override
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException {
        mLocales = (Locales)mdl;
        int gravado = 0;
        String campos = Locales.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {mLocales.getCoCompania(),
                            mLocales.getCoLocal(),
                            mLocales.getTiLocal(),
                            mLocales.getInTipoCaja(),
                            mLocales.getCoRegion(),
                            mLocales.getDeCortaLocal(),
                            mLocales.getDeLocal(),
                            mLocales.getCoPais(),
                            mLocales.getCoDepartamento(),
                            mLocales.getCoProvincia(),
                            mLocales.getCoDistrito(),
                            mLocales.getTiVia(),
                            mLocales.getNoVia(),
                            mLocales.getNuVia(),
                            mLocales.getNuInteriorVia(),
                            mLocales.getNuManzanaVia(),
                            mLocales.getNuLoteVia(),
                            mLocales.getTiPoblacion(),
                            mLocales.getNoPoblacion(),
                            mLocales.getDeDireccionLocal(),
                            mLocales.getCoTelPais(),
                            mLocales.getCoTelCiudad(),
                            mLocales.getNuTelNormal(),
                            mLocales.getCoExtTelNormal(),
                            mLocales.getNuTelFax(),
                            mLocales.getNuTelMovil(),
                            mLocales.getIdRespLocal(),
                            mLocales.getIdRespAlternoLocal(),
                            mLocales.getDeEmail(),
                            mLocales.getCoLocalDelivery(),
                            mLocales.getEsLocal(),
                            mLocales.getIdCreaLocal(),
                            mLocales.getFeCreaLocal(),
                            mLocales.getIdModLocal(),
                            mLocales.getFeModLocal(),
                            mLocales.getInImprimeTestigo(),
                            mLocales.getInImpComanda(),
                            mLocales.getInImprimeTicket(),
                            mLocales.getDeMensajeTicket(),
                            mLocales.getInTicketBoleta(),
                            mLocales.getInTicketFactura(),
                            mLocales.getCoZonaSupervision(),
                            mLocales.getNuOrdenApertura(),
                            mLocales.getFeApertura(),
                            mLocales.getInProvincia(),
                            mLocales.getVaMontoCotizacion(),
                            mLocales.getInAfectoIgvLocal(),
                            mLocales.getTsDias(),
                            mLocales.getTrDias(),
                            mLocales.getCoSucursal(),
                            mLocales.getCoCadenaLocal(),
                            mLocales.getVaMinimoPedidoDelivery(),
                            mLocales.getNuDiasRotacionPromedio(),
                            mLocales.getNuMinDiasReposicion(),
                            mLocales.getNuMaxDiasReposicion(),
                            mLocales.getPcAdicionalPedidoReposicion(),
                            mLocales.getVaFactorBajaRotacion(),
                            mLocales.getInPedRepEnProc(),
                            mLocales.getFeCalculoMaxMin(),
                            mLocales.getNuDiasInventario(),
                            mLocales.getNuDiasVenta(),
                            mLocales.getInIgnorarProdSinSaldo(),
                            mLocales.getInSumarTiempoSuministro(),
                            mLocales.getInSumarTransito(),
                            mLocales.getInSumarMinExhibicion(),
                            mLocales.getInSumarComprasPendientes(),
                            mLocales.getInTipoOperacion(),
                            mLocales.getInOrigenProductos(),
                            mLocales.getInSoloProdActivos(),
                            mLocales.getInProductosFraccionados(),
                            mLocales.getInFaltaCero(),
                            mLocales.getFeCierre()
                           };
       
           gravado = this.agregarRegistroPs(mLocales.getNombreTabla(),Locales.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        mLocales = (Locales)mdl;
        int gravado = 0;        
        
        Object[] valores = {mLocales.getTiLocal(),
                            mLocales.getInTipoCaja(),
                            mLocales.getCoRegion(),
                            mLocales.getDeCortaLocal(),
                            mLocales.getDeLocal(),
                            mLocales.getCoPais(),
                            mLocales.getCoDepartamento(),
                            mLocales.getCoProvincia(),
                            mLocales.getCoDistrito(),
                            mLocales.getTiVia(),
                            mLocales.getNoVia(),
                            mLocales.getNuVia(),
                            mLocales.getNuInteriorVia(),
                            mLocales.getNuManzanaVia(),
                            mLocales.getNuLoteVia(),
                            mLocales.getTiPoblacion(),
                            mLocales.getNoPoblacion(),
                            mLocales.getDeDireccionLocal(),
                            mLocales.getCoTelPais(),
                            mLocales.getCoTelCiudad(),
                            mLocales.getNuTelNormal(),
                            mLocales.getCoExtTelNormal(),
                            mLocales.getNuTelFax(),
                            mLocales.getNuTelMovil(),
                            mLocales.getIdRespLocal(),
                            mLocales.getIdRespAlternoLocal(),
                            mLocales.getDeEmail(),
                            mLocales.getCoLocalDelivery(),
                            mLocales.getEsLocal(),
                            mLocales.getIdCreaLocal(),
                            mLocales.getFeCreaLocal(),
                            mLocales.getIdModLocal(),
                            mLocales.getFeModLocal(),
                            mLocales.getInImprimeTestigo(),
                            mLocales.getInImpComanda(),
                            mLocales.getInImprimeTicket(),
                            mLocales.getDeMensajeTicket(),
                            mLocales.getInTicketBoleta(),
                            mLocales.getInTicketFactura(),
                            mLocales.getCoZonaSupervision(),
                            mLocales.getNuOrdenApertura(),
                            mLocales.getFeApertura(),
                            mLocales.getInProvincia(),
                            mLocales.getVaMontoCotizacion(),
                            mLocales.getInAfectoIgvLocal(),
                            mLocales.getTsDias(),
                            mLocales.getTrDias(),
                            mLocales.getCoSucursal(),
                            mLocales.getCoCadenaLocal(),
                            mLocales.getVaMinimoPedidoDelivery(),
                            mLocales.getNuDiasRotacionPromedio(),
                            mLocales.getNuMinDiasReposicion(),
                            mLocales.getNuMaxDiasReposicion(),
                            mLocales.getPcAdicionalPedidoReposicion(),
                            mLocales.getVaFactorBajaRotacion(),
                            mLocales.getInPedRepEnProc(),
                            mLocales.getFeCalculoMaxMin(),
                            mLocales.getNuDiasInventario(),
                            mLocales.getNuDiasVenta(),
                            mLocales.getInIgnorarProdSinSaldo(),
                            mLocales.getInSumarTiempoSuministro(),
                            mLocales.getInSumarTransito(),
                            mLocales.getInSumarMinExhibicion(),
                            mLocales.getInSumarComprasPendientes(),
                            mLocales.getInTipoOperacion(),
                            mLocales.getInOrigenProductos(),
                            mLocales.getInSoloProdActivos(),
                            mLocales.getInProductosFraccionados(),
                            mLocales.getInFaltaCero(),
                            mLocales.getFeCierre()
                           };

        gravado = this.actualizarRegistroPs(mLocales.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(Locales.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(Locales.COLUMNA_PK, mLocales.getPrimaryKey()) , valores);
        return gravado;
    }

    public Locales getLocales() {
        if(mLocales == null)
        {
            mLocales = new Locales();
        }
        return mLocales;
    }

    public void setLocales(JAbstractModelBD prv) {
        this.mLocales = (Locales)prv;
    }

    public String getNuevoCodigo(String coCompania){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(Locales.nt,"CASE WHEN LENGTH(TRIM(TRANSLATE(max(co_local), ' +-.0123456789', ' '))) IS NULL THEN rtrim(ltrim(to_char(max(co_local) + 1,'000'))) ELSE CASE when substr(max(co_local),2,2)+1 < 100 then substr(max(co_local),1,1) || to_char(substr(max(co_local),2,2)+1) ELSE CHR(ASCII(substr(max(co_local),1,1)) +1) || '01' END END NuevoCodigo"," CO_COMPANIA = '" + coCompania + "'");
        } catch (SQLException ex) {
            Logger.getLogger(CLocales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }
}
