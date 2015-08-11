package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.FormaPago;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxVariables;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CFormaPago extends JAbstractController{
    private FormaPago FPago;   
    
    @Override
    public ArrayList<FormaPago> getRegistros(Object[] op) {  
        return this.getRegistros(new String[]{}, op!=null?new String[]{FormaPago.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<FormaPago> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<FormaPago> getRegistros() {                 
        ArrayList<FormaPago> rgs = new ArrayList<FormaPago>();
        try {                        
            rs = this.getRegistros(FormaPago.nt,FormaPago.FULL_NOM_CAMPOS);                       
            while(rs.next())
            {
                FPago  = new FormaPago();
                FPago.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_FORMA_PAGO")});
                FPago.setCoCompania(rs.getString("CO_COMPANIA"));
                FPago.setCoFormaPago(rs.getString("CO_FORMA_PAGO"));
                FPago.setNuOrdenFila(rs.getInt("NU_ORDEN_FILA"));
                FPago.setDeCortaFormaPago(rs.getString("DE_CORTA_FORMA_PAGO"));
                FPago.setDeFormaPago(rs.getString("DE_FORMA_PAGO"));
                FPago.setInPagoExacto(rs.getString("IN_PAGO_EXACTO"));
                FPago.setInTarjetaBancaria(rs.getString("IN_TARJETA_BANCARIA"));
                FPago.setInDeficitCaja(rs.getString("IN_DEFICIT_CAJA"));
                FPago.setInDetalle(rs.getString("IN_DETALLE"));
                FPago.setInSobreHermes(rs.getString("IN_SOBRE_HERMES"));
                FPago.setEsFormaPago(rs.getString("ES_FORMA_PAGO"));
                FPago.setCoClienteCompania(rs.getString("CO_CLIENTE_COMPANIA"));
                FPago.setTiTransaccionTarjeta(rs.getString("TI_TRANSACCION_TARJETA"));
                FPago.setInCreditoConvenioCompania(rs.getString("IN_CREDITO_CONVENIO_COMPANIA"));
                FPago.setInAperturaGaveta(rs.getString("IN_APERTURA_GAVETA"));
                FPago.setInMuestraCajaElectronica(rs.getString("IN_MUESTRA_CAJA_ELECTRONICA"));
                FPago.setIdCreaFormaPago(rs.getString("ID_CREA_FORMA_PAGO"));
                FPago.setFeCreaFormaPago(rs.getDate("FE_CREA_FORMA_PAGO"));
                FPago.setIdModFormaPago(rs.getString("ID_MOD_FORMA_PAGO"));
                FPago.setFeModFormaPago(rs.getDate("FE_MOD_FORMA_PAGO"));
                rgs.add(FPago);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public FormaPago getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(FormaPago.nt, FormaPago.FULL_NOM_CAMPOS, FormaPago.COLUMNA_PK , id);
            while(rs.next())
            {
                FPago = new FormaPago();
                FPago.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_FORMA_PAGO")});
                FPago.setCoCompania(rs.getString("CO_COMPANIA"));
                FPago.setCoFormaPago(rs.getString("CO_FORMA_PAGO"));
                FPago.setNuOrdenFila(rs.getInt("NU_ORDEN_FILA"));
                FPago.setDeCortaFormaPago(rs.getString("DE_CORTA_FORMA_PAGO"));
                FPago.setDeFormaPago(rs.getString("DE_FORMA_PAGO"));
                FPago.setInPagoExacto(rs.getString("IN_PAGO_EXACTO"));
                FPago.setInTarjetaBancaria(rs.getString("IN_TARJETA_BANCARIA"));
                FPago.setInDeficitCaja(rs.getString("IN_DEFICIT_CAJA"));
                FPago.setInDetalle(rs.getString("IN_DETALLE"));
                FPago.setInSobreHermes(rs.getString("IN_SOBRE_HERMES"));
                FPago.setEsFormaPago(rs.getString("ES_FORMA_PAGO"));
                FPago.setCoClienteCompania(rs.getString("CO_CLIENTE_COMPANIA"));
                FPago.setTiTransaccionTarjeta(rs.getString("TI_TRANSACCION_TARJETA"));
                FPago.setInCreditoConvenioCompania(rs.getString("IN_CREDITO_CONVENIO_COMPANIA"));
                FPago.setInAperturaGaveta(rs.getString("IN_APERTURA_GAVETA"));
                FPago.setInMuestraCajaElectronica(rs.getString("IN_MUESTRA_CAJA_ELECTRONICA"));
                FPago.setIdCreaFormaPago(rs.getString("ID_CREA_FORMA_PAGO"));
                FPago.setFeCreaFormaPago(rs.getDate("FE_CREA_FORMA_PAGO"));
                FPago.setIdModFormaPago(rs.getString("ID_MOD_FORMA_PAGO"));
                FPago.setFeModFormaPago(rs.getDate("FE_MOD_FORMA_PAGO"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return FPago;
    }
    
    @Override
    public ArrayList<FormaPago> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<FormaPago> rgs = new ArrayList<FormaPago>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(FormaPago.nt, FormaPago.COLUMNA_ACTIVO, FormaPago.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(FormaPago.nt, FormaPago.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            
            rs = this.getRegistros(FormaPago.nt, campos, columnaId, id, FormaPago.COLUMNA_ORDER);
            if(this.numRegistros<finalPag){
              finalPag =  this.numRegistros;
            }
            if(finalPag>inicioPag){
                inicioPag -= (finalPag-inicioPag)-1;
            }
            while(rs.next()){
                FPago = new FormaPago();
                FPago.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_FORMA_PAGO")});
                FPago.setCoCompania(rs.getString("CO_COMPANIA"));
                FPago.setCoFormaPago(rs.getString("CO_FORMA_PAGO"));
                FPago.setNuOrdenFila(rs.getInt("NU_ORDEN_FILA"));
                FPago.setDeCortaFormaPago(rs.getString("DE_CORTA_FORMA_PAGO"));
                FPago.setDeFormaPago(rs.getString("DE_FORMA_PAGO"));
                FPago.setInPagoExacto(rs.getString("IN_PAGO_EXACTO"));
                FPago.setInTarjetaBancaria(rs.getString("IN_TARJETA_BANCARIA"));
                FPago.setInDeficitCaja(rs.getString("IN_DEFICIT_CAJA"));
                FPago.setInDetalle(rs.getString("IN_DETALLE"));
                FPago.setInSobreHermes(rs.getString("IN_SOBRE_HERMES"));
                FPago.setEsFormaPago(rs.getString("ES_FORMA_PAGO"));
                FPago.setCoClienteCompania(rs.getString("CO_CLIENTE_COMPANIA"));
                FPago.setTiTransaccionTarjeta(rs.getString("TI_TRANSACCION_TARJETA"));
                FPago.setInCreditoConvenioCompania(rs.getString("IN_CREDITO_CONVENIO_COMPANIA"));
                FPago.setInAperturaGaveta(rs.getString("IN_APERTURA_GAVETA"));
                FPago.setInMuestraCajaElectronica(rs.getString("IN_MUESTRA_CAJA_ELECTRONICA"));
                FPago.setIdCreaFormaPago(rs.getString("ID_CREA_FORMA_PAGO"));
                FPago.setFeCreaFormaPago(rs.getDate("FE_CREA_FORMA_PAGO"));
                FPago.setIdModFormaPago(rs.getString("ID_MOD_FORMA_PAGO"));
                FPago.setFeModFormaPago(rs.getDate("FE_MOD_FORMA_PAGO"));
                rgs.add(FPago);
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

    @Override
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException {
        FPago = (FormaPago)mdl;
        int gravado = 0;
        String campos = FormaPago.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {FPago.getCoCompania(),
                            FPago.getCoFormaPago(),
                            FPago.getNuOrdenFila(),
                            FPago.getDeCortaFormaPago(),
                            FPago.getDeFormaPago(),
                            FPago.getInPagoExacto(),
                            FPago.getInTarjetaBancaria(),
                            FPago.getInDeficitCaja(),
                            FPago.getInDetalle(),
                            FPago.getInSobreHermes(),
                            FPago.getEsFormaPago(),
                            FPago.getCoClienteCompania(),
                            FPago.getTiTransaccionTarjeta(),
                            FPago.getInCreditoConvenioCompania(),
                            FPago.getInAperturaGaveta(),
                            FPago.getInMuestraCajaElectronica(),
                            FPago.getIdCreaFormaPago(),
                            FPago.getFeCreaFormaPago(),
                            FPago.getIdModFormaPago(),
                            FPago.getFeModFormaPago()
                           };
       
           gravado = this.agregarRegistroPs(FPago.getNombreTabla(), FormaPago.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        FPago = (FormaPago)mdl;
        int gravado = 0;        
        
        Object[] valores = {FPago.getNuOrdenFila(),
                            FPago.getDeCortaFormaPago(),
                            FPago.getDeFormaPago(),
                            FPago.getInPagoExacto(),
                            FPago.getInTarjetaBancaria(),
                            FPago.getInDeficitCaja(),
                            FPago.getInDetalle(),
                            FPago.getInSobreHermes(),
                            FPago.getEsFormaPago(),
                            FPago.getCoClienteCompania(),
                            FPago.getTiTransaccionTarjeta(),
                            FPago.getInCreditoConvenioCompania(),
                            FPago.getInAperturaGaveta(),
                            FPago.getInMuestraCajaElectronica(),
                            FPago.getIdCreaFormaPago(),
                            FPago.getFeCreaFormaPago(),
                            FPago.getIdModFormaPago(),
                            FPago.getFeModFormaPago()
                            };
       
        gravado = this.actualizarRegistroPs(FPago.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(FormaPago.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(FormaPago.COLUMNA_PK,FPago.getPrimaryKey()) , valores);

   
        return gravado;
    }

    public FormaPago getFormaPago() {
        if(FPago == null)
        {
            FPago = new FormaPago();
        }
        return FPago;
    }

    public void setFormaPago(JAbstractModelBD prv) {
        this.FPago = (FormaPago)prv;
    }
    public String getNuevoCodigo(){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(FormaPago.nt,"rtrim(ltrim(to_char(max(co_forma_pago) + 1,'00000')))"," co_compania = '" + AtuxVariables.vCodigoCompania + "'");
        } catch (SQLException ex) {
            Logger.getLogger(CFormaPago.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }
}
