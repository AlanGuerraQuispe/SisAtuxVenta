package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.FormaPagoLocal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CFormaPagoLocal extends JAbstractController{
    private FormaPagoLocal FPagoLocal;   
    
    @Override
    public ArrayList<FormaPagoLocal> getRegistros(Object[] op) {  
        return this.getRegistros(new String[]{}, op!=null?new String[]{FormaPagoLocal.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<FormaPagoLocal> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<FormaPagoLocal> getRegistros() {                 
        ArrayList<FormaPagoLocal> rgs = new ArrayList<FormaPagoLocal>();
        try {                        
            rs = this.getRegistros(FormaPagoLocal.nt,FormaPagoLocal.FULL_NOM_CAMPOS);                       
            while(rs.next())
            {
                FPagoLocal  = new FormaPagoLocal();
                FPagoLocal.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_FORMA_PAGO")});
                FPagoLocal.setCoCompania(rs.getString("CO_COMPANIA"));
                FPagoLocal.setCoLocal(rs.getString("CO_LOCAL"));
                FPagoLocal.setCoFormaPago(rs.getString("CO_FORMA_PAGO"));
                FPagoLocal.setEsFormaPago(rs.getString("ES_FORMA_PAGO"));
                FPagoLocal.setIdCreaFormaPagoLocal(rs.getString("ID_CREA_FORMA_PAGO_LOCAL"));
                FPagoLocal.setFeCreaFormaPagoLocal(rs.getDate("FE_CREA_FORMA_PAGO_LOCAL"));
                FPagoLocal.setIdModFormaPagoLocal(rs.getString("ID_MOD_FORMA_PAGO_LOCAL"));
                FPagoLocal.setFeModFormaPagoLocal(rs.getDate("FE_MOD_FORMA_PAGO_LOCAL"));
                rgs.add(FPagoLocal);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public FormaPagoLocal getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(FormaPagoLocal.nt, FormaPagoLocal.FULL_NOM_CAMPOS, FormaPagoLocal.COLUMNA_PK , id);
            while(rs.next())
            {
                FPagoLocal = new FormaPagoLocal();
                FPagoLocal.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_FORMA_PAGO")});
                FPagoLocal.setCoCompania(rs.getString("CO_COMPANIA"));
                FPagoLocal.setCoLocal(rs.getString("CO_LOCAL"));
                FPagoLocal.setCoFormaPago(rs.getString("CO_FORMA_PAGO"));
                FPagoLocal.setEsFormaPago(rs.getString("ES_FORMA_PAGO"));
                FPagoLocal.setIdCreaFormaPagoLocal(rs.getString("ID_CREA_FORMA_PAGO_LOCAL"));
                FPagoLocal.setFeCreaFormaPagoLocal(rs.getDate("FE_CREA_FORMA_PAGO_LOCAL"));
                FPagoLocal.setIdModFormaPagoLocal(rs.getString("ID_MOD_FORMA_PAGO_LOCAL"));
                FPagoLocal.setFeModFormaPagoLocal(rs.getDate("FE_MOD_FORMA_PAGO_LOCAL"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return FPagoLocal;
    }
    
    @Override
    public ArrayList<FormaPagoLocal> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<FormaPagoLocal> rgs = new ArrayList<FormaPagoLocal>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(FormaPagoLocal.nt, FormaPagoLocal.COLUMNA_ACTIVO, FormaPagoLocal.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(FormaPagoLocal.nt, FormaPagoLocal.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
//            rs = this.getRegistros(Proveedores.nt, campos, columnaId, id,null);
            rs = this.getRegistros(FormaPagoLocal.nt, campos, columnaId, id, FormaPagoLocal.COLUMNA_ORDER);
            if(this.numRegistros<finalPag){
              finalPag =  this.numRegistros;
            }
            if(finalPag>inicioPag){
                inicioPag -= (finalPag-inicioPag)-1;
            }
            while(rs.next()){
                FPagoLocal = new FormaPagoLocal();
                FPagoLocal.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_FORMA_PAGO")});
                FPagoLocal.setCoCompania(rs.getString("CO_COMPANIA"));
                FPagoLocal.setCoLocal(rs.getString("CO_LOCAL"));
                FPagoLocal.setCoFormaPago(rs.getString("CO_FORMA_PAGO"));
                FPagoLocal.setEsFormaPago(rs.getString("ES_FORMA_PAGO"));
                FPagoLocal.setIdCreaFormaPagoLocal(rs.getString("ID_CREA_FORMA_PAGO_LOCAL"));
                FPagoLocal.setFeCreaFormaPagoLocal(rs.getDate("FE_CREA_FORMA_PAGO_LOCAL"));
                FPagoLocal.setIdModFormaPagoLocal(rs.getString("ID_MOD_FORMA_PAGO_LOCAL"));
                FPagoLocal.setFeModFormaPagoLocal(rs.getDate("FE_MOD_FORMA_PAGO_LOCAL"));
                rgs.add(FPagoLocal);
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
        FPagoLocal = (FormaPagoLocal)mdl;
        int gravado = 0;
        String campos = FormaPagoLocal.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {FPagoLocal.getCoCompania(),
                            FPagoLocal.getCoLocal(),
                            FPagoLocal.getCoFormaPago(),
                            FPagoLocal.getEsFormaPago(),
                            FPagoLocal.getIdCreaFormaPagoLocal(),
                            FPagoLocal.getFeCreaFormaPagoLocal(),
                            FPagoLocal.getIdModFormaPagoLocal(),
                            FPagoLocal.getFeModFormaPagoLocal()
                           };
       
           gravado = this.agregarRegistroPs(FPagoLocal.getNombreTabla(), FormaPagoLocal.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        FPagoLocal = (FormaPagoLocal)mdl;
        int gravado = 0;        
        
        Object[] valores = {FPagoLocal.getEsFormaPago(),
                            FPagoLocal.getIdCreaFormaPagoLocal(),
                            FPagoLocal.getFeCreaFormaPagoLocal(),
                            FPagoLocal.getIdModFormaPagoLocal(),
                            FPagoLocal.getFeModFormaPagoLocal()
                            };
       
        gravado = this.actualizarRegistroPs(FPagoLocal.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(FormaPagoLocal.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(FormaPagoLocal.COLUMNA_PK,FPagoLocal.getPrimaryKey()) , valores);

   
        return gravado;
    }

    public FormaPagoLocal getFormaPagoLocal() {
        if(FPagoLocal == null)
        {
            FPagoLocal = new FormaPagoLocal();
        }
        return FPagoLocal;
    }

    public void setFormaPagoLocal(JAbstractModelBD prv) {
        this.FPagoLocal = (FormaPagoLocal)prv;
    }
    
    public ArrayList LeerFormaPagoLocal(String vCodigoCompania, String vCodigoFormaPago){
        ArrayList<FormaPagoLocal> rgs = new ArrayList<FormaPagoLocal>();
        FormaPagoLocal      formaPagoLocal      = null;
        StringBuffer  query;

        query = new StringBuffer();
        query.append("select TFPL.*, TL.de_local");
        query.append("  from vttr_forma_pago_local TFPL,");
        query.append("       vttm_local TL");
        query.append(" where TFPL.co_compania = TL.co_compania");
        query.append("   and TFPL.co_local = TL.co_local");
        query.append("   and TFPL.co_compania = '001'");
        query.append("   and TFPL.co_forma_pago ='00563'");
        query.append(" order by TFPL.co_local");


        try {            
            rs =  this.getRegistrosSinFiltro(query);
            while(rs.next())
            {
                FPagoLocal = new FormaPagoLocal();
                FPagoLocal.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_FORMA_PAGO")});
                FPagoLocal.setCoCompania(rs.getString("CO_COMPANIA"));
                FPagoLocal.setCoLocal(rs.getString("CO_LOCAL"));
                FPagoLocal.setDeLocal(rs.getString("DE_LOCAL"));
                FPagoLocal.setCoFormaPago(rs.getString("CO_FORMA_PAGO"));
                FPagoLocal.setEsFormaPago(rs.getString("ES_FORMA_PAGO"));
                FPagoLocal.setIdCreaFormaPagoLocal(rs.getString("ID_CREA_FORMA_PAGO_LOCAL"));
                FPagoLocal.setFeCreaFormaPagoLocal(rs.getDate("FE_CREA_FORMA_PAGO_LOCAL"));
                FPagoLocal.setIdModFormaPagoLocal(rs.getString("ID_MOD_FORMA_PAGO_LOCAL"));
                FPagoLocal.setFeModFormaPagoLocal(rs.getDate("FE_MOD_FORMA_PAGO_LOCAL"));
                
                rgs.add(FPagoLocal);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormaPagoLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rgs;
    }    
    
    
//    public String getNuevoCodigo(){
//        String Codigo="";
//        try {
//            return AtuxDBUtility.getValueAt(FormaPago.nt,"rtrim(ltrim(to_char(max(co_forma_pago) + 1,'00000')))"," co_compania = '" + AtuxVariables.vCodigoCompania + "'");
//        } catch (SQLException ex) {
//            Logger.getLogger(CFormaPagoLocal.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return Codigo.trim();
//    }
}
