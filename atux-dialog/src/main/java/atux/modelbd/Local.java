package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;


public class Local extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    public static final String nt = "VTTM_LOCAL";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_LOCAL"};
    public static final String COLUMNA_ACTIVO = "ES_LOCAL";

    
    private Compania compania; 
    private String coCompania; 
    private String coLocal;
    private String tiLocal;
    private String tiCaja;
    private String deLocal;   
    private String esLocal;       
    private String nuTelNormal;       
    
    private String deCortaLocal;            
    private String inImprimeTicket;
    private String deMensajeTicket;
    private String deDireccionLocal;
    private String inTicketBoleta;
    private String inTicketFactura;    
    
    private Integer tsDias;
    private Integer trDias;
    
    //Agregado para obtener saldo por local
    private Double saldo;
    private String deDepartamento;
    private String deProvincia;
    private String deDistrito;
    
    //Agregado para obtener el pedido reposición
    private Integer nuDiasRotacionPromedio;
    private Integer nuMinDiasReposicion;
    private Integer nuMaxDiasReposicion;    
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
      FULL_NOM_CAMPOS ={"CO_COMPANIA, CO_LOCAL, CO_ZONA_DELIVERY, TI_LOCAL, IN_TIPO_CAJA,"+
                        "CO_SERVICIO_TRANSP, CO_PROVEEDOR_TRANSP, CO_REGION, DE_CORTA_LOCAL, DE_LOCAL,"+
                        "CO_PAIS, CO_DEPARTAMENTO, CO_PROVINCIA, CO_DISTRITO, TI_VIA, NO_VIA, NU_VIA,"+
                        "NU_INTERIOR_VIA, NU_MANZANA_VIA, NU_LOTE_VIA, TI_POBLACION, NO_POBLACION,"+
                        "DE_DIRECCION_LOCAL, CO_TEL_PAIS, CO_TEL_CIUDAD, NU_TEL_NORMAL,"+
                        "CO_EXT_TEL_NORMAL, NU_TEL_FAX, NU_TEL_MOVIL, ID_RESP_LOCAL,"+
                        "ID_RESP_ALTERNO_LOCAL, DE_EMAIL, CO_LOCAL_DELIVERY, VA_MINIMO_PEDIDO_DELIVERY,"+
                        "NU_DIAS_ROTACION_PROMEDIO, NU_MIN_DIAS_REPOSICION, NU_MAX_DIAS_REPOSICION,"+
                        "PC_ADICIONAL_PEDIDO_REPOSICION, VA_FACTOR_BAJA_ROTACION, IN_PED_REP_EN_PROC,"+
                        "NU_PLACA_TRANSPORTE, CO_CENTRO_COSTO_ERP, ES_LOCAL, ID_CREA_LOCAL,"+
                        "FE_CREA_LOCAL, ID_MOD_LOCAL, FE_MOD_LOCAL, IN_IMPRIME_TESTIGO,"+
                        "PC_DCTO_ADICIONAL, CO_SUCURSAL, IN_MOD_PRECIOS, IN_IMP_COMANDA,"+
                        "FE_CALCULO_MAX_MIN, DE_MENSAJE_TICKET, IN_TICKET_BOLETA,"+
                        "IN_TICKET_FACTURA, CO_ZONA_SUPERVISION, CO_POSTAL_LOCAL, NU_ORDEN_APERTURA,"+
                        "FE_APERTURA, IN_PROVINCIA, CO_CADENA_LOCAL, CO_CEN, IN_IMPLEMENTA_SAP,"+
                        "CO_LOCAL_SAP, CO_CENTRO_BENEFICIO_SAP, CO_ALMACEN_LOCAL_SAP,"+
                        "VA_MONTO_COTIZACION, NU_SERIE_LOCAL, IN_AFECTO_IGV_LOCAL"};
    
    public static final String[]
       NO_FULL_NOM_CAMPOS ={"CO_COMPANIA, CO_LOCAL ,TI_LOCAL ,IN_TIPO_CAJA,DE_LOCAL,DE_CORTA_LOCAL,"
                          + "DE_MENSAJE_TICKET,DE_DIRECCION_LOCAL,IN_TICKET_BOLETA,IN_TICKET_FACTURA"};
    
    public static final String[]
       NO_CAMPOS_CAB_REPOSICION ={"CO_COMPANIA,CO_LOCAL,NU_DIAS_ROTACION_PROMEDIO,NU_MIN_DIAS_REPOSICION,"
                                + "NU_MAX_DIAS_REPOSICION,IN_IGNORAR_PROD_SIN_SALDO,IN_SUMAR_TIEMPO_SUMINISTRO,"
                                + "IN_SUMAR_TRANSITO,IN_SUMAR_MIN_EXHIBICION,IN_SUMAR_COMPRAS_PENDIENTES,"
                                + "IN_TIPO_OPERACION,IN_ORIGEN_PRODUCTOS,IN_SOLO_PROD_ACTIVOS,"
                                + "IN_PRODUCTOS_FRACCIONADOS,IN_FALTA_CERO"};

    public Local(String coCompania, String coLocal, String tiLocal, String tiCaja, String deLocal, String esLocal) {
        this.coCompania = coCompania;
        this.coLocal = coLocal;
        this.tiLocal = tiLocal;
        this.tiCaja = tiCaja;
        this.deLocal = deLocal;
        this.esLocal = esLocal;
        initBasic();
    }
    
    public Local() {
        initBasic();
    }
    
    private void initBasic()
    {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
    }

    public Compania getCompania() {
        return compania;
    }

    public void setCompania(Compania compania) {
        this.compania = compania;
    }
        
    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
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

    public String getEsLocal() {
        return esLocal;
    }

    public void setEsLocal(String esLocal) {
        this.esLocal = esLocal;
    }

    public String getTiCaja() {
        return tiCaja;
    }

    public void setTiCaja(String tiCaja) {
        this.tiCaja = tiCaja;
    }

    public String getTiLocal() {
        return tiLocal;
    }

    public void setTiLocal(String tiLocal) {
        this.tiLocal = tiLocal;
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

    public String getDeMensajeTicket() {
        return deMensajeTicket;
    }

    public void setDeMensajeTicket(String deMensajeTicket) {
        this.deMensajeTicket = deMensajeTicket;
    }

    public String getInImprimeTicket() {
        return inImprimeTicket;
    }

    public void setInImprimeTicket(String inImprimeTicket) {
        this.inImprimeTicket = inImprimeTicket;
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

     @Override
    public int hashCode() {
        int hash = 0;
        hash += (coLocal != null ? coLocal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Local)) {
            return false;
        }
        Local other = (Local) object;
        if ((this.coLocal == null && other.coLocal != null) || (this.coLocal != null && !this.coLocal.equals(other.coLocal))) {
            return false;
        }
        return true;
    }

    public String getDeDepartamento() {
        return deDepartamento;
    }

    public void setDeDepartamento(String deDepartamento) {
        this.deDepartamento = deDepartamento;
    }

    public String getDeDistrito() {
        return deDistrito;
    }

    public void setDeDistrito(String deDistrito) {
        this.deDistrito = deDistrito;
    }

    public String getDeProvincia() {
        return deProvincia;
    }

    public void setDeProvincia(String deProvincia) {
        this.deProvincia = deProvincia;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
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

    public String getInOrigenProductos() {
        return inOrigenProductos;
    }

    public void setInOrigenProductos(String inOrigenProductos) {
        this.inOrigenProductos = inOrigenProductos;
    }

    public String getInProductosFraccionados() {
        return inProductosFraccionados;
    }

    public void setInProductosFraccionados(String inProductosFraccionados) {
        this.inProductosFraccionados = inProductosFraccionados;
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

    public String getInTipoOperacion() {
        return inTipoOperacion;
    }

    public void setInTipoOperacion(String inTipoOperacion) {
        this.inTipoOperacion = inTipoOperacion;
    }

    public Integer getNuDiasRotacionPromedio() {
        return nuDiasRotacionPromedio;
    }

    public void setNuDiasRotacionPromedio(Integer nuDiasRotacionPromedio) {
        this.nuDiasRotacionPromedio = nuDiasRotacionPromedio;
    }

    public Integer getNuMinDiasReposicion() {
        return nuMinDiasReposicion;
    }

    public void setNuMinDiasReposicion(Integer nuMinDiasReposicion) {
        this.nuMinDiasReposicion = nuMinDiasReposicion;
    }

    public Integer getNuMaxDiasReposicion() {
        return nuMaxDiasReposicion;
    }

    public void setNuMaxDiasReposicion(Integer nuMaxDiasReposicion) {
        this.nuMaxDiasReposicion = nuMaxDiasReposicion;
    }

    public String getNuTelNormal() {
        return nuTelNormal;
    }

    public void setNuTelNormal(String nuTelNormal) {
        this.nuTelNormal = nuTelNormal;
    }
        
    public static class Compania{
        private static final long serialVersionUID = 1L;
        public static final String nt = "CMTM_COMPANIA";
        public static final String[] COLUMNA_PK ={"CO_COMPANIA"};
        public static final String COLUMNA_ACTIVO = "ES_COMPANIA";
    
        private String coCompania;
        private String coTelCiudad;
        private String deCompania;
        private String deCortaCompania;
        private String deDireccion;
        private String deDireccionWeb;
        private String nuRucCompania;
        private String nuTelNormal;
        
        public static final String[]
               NO_FULL_NOM_CAMPOS ={"CO_COMPANIA,CO_TEL_CIUDAD,DE_COMPANIA,"
                                  + "DE_CORTA_COMPANIA,DE_DIRECCION,DE_DIRECCION_WEB,"
                                  + "NU_RUC_COMPANIA,NU_TEL_NORMAL"};
        
        public String getCoCompania() {
            return coCompania;
        }

        public void setCoCompania(String coCompania) {
            this.coCompania = coCompania;
        }
               
        public String getCoTelCiudad() {
            return coTelCiudad;
        }

        public void setCoTelCiudad(String coTelCiudad) {
            this.coTelCiudad = coTelCiudad;
        }

        public String getDeCompania() {
            return deCompania;
        }

        public void setDeCompania(String deCompania) {
            this.deCompania = deCompania;
        }

        public String getDeCortaCompania() {
            return deCortaCompania;
        }

        public void setDeCortaCompania(String deCortaCompania) {
            this.deCortaCompania = deCortaCompania;
        }

        public String getDeDireccion() {
            return deDireccion;
        }

        public void setDeDireccion(String deDireccion) {
            this.deDireccion = deDireccion;
        }

        public String getDeDireccionWeb() {
            return deDireccionWeb;
        }

        public void setDeDireccionWeb(String deDireccionWeb) {
            this.deDireccionWeb = deDireccionWeb;
        }

        public String getNuRucCompania() {
            return nuRucCompania;
        }

        public void setNuRucCompania(String nuRucCompania) {
            this.nuRucCompania = nuRucCompania;
        }

        public String getNuTelNormal() {
            return nuTelNormal;
        }

        public void setNuTelNormal(String nuTelNormal) {
            this.nuTelNormal = nuTelNormal;
        }
        
        
    }
}
