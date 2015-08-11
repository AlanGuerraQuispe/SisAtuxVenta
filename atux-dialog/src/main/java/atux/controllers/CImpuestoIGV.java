package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.ImpuestoIGV;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxVariables;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CImpuestoIGV extends JAbstractController{
    private ImpuestoIGV imp;
   
    
    @Override
    public ArrayList<ImpuestoIGV> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{ImpuestoIGV.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<ImpuestoIGV> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<ImpuestoIGV> getRegistros() {                 
        ArrayList<ImpuestoIGV> rgs = new ArrayList<ImpuestoIGV>();
        try {                        
            rs = this.getRegistros(ImpuestoIGV.nt,ImpuestoIGV.FULL_NOM_CAMPOS);                       
            while(rs.next())
            {
                imp = new ImpuestoIGV();
                imp.setPrimaryKey(new String[]{rs.getString("Co_Compania"),rs.getString("Co_Impuesto")});    
                imp.setCoCompania(rs.getString("CO_COMPANIA"));
                imp.setCoImpuesto(rs.getString("CO_IMPUESTO"));
                imp.setNuOrdenFila(rs.getInt("NU_ORDEN_FILA"));
                imp.setDeCortaImpuesto(rs.getString("DE_CORTA_IMPUESTO"));
                imp.setDeImpuesto(rs.getString("DE_IMPUESTO"));
                imp.setPcImpuesto(rs.getInt("PC_IMPUESTO"));
                imp.setInOperacionImpuesto(rs.getString("IN_OPERACION_IMPUESTO"));
                imp.setEsImpuesto(rs.getString("ES_IMPUESTO"));
                imp.setIdCreaImpuesto(rs.getString("ID_CREA_IMPUESTO"));
                imp.setFeCreaImpuesto(rs.getDate("FE_CREA_IMPUESTO"));
                imp.setIdModImpuesto(rs.getString("ID_MOD_IMPUESTO"));
                imp.setFeModImpuesto(rs.getDate("FE_MOD_IMPUESTO"));
                rgs.add(imp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public ImpuestoIGV getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(ImpuestoIGV.nt, ImpuestoIGV.FULL_NOM_CAMPOS, ImpuestoIGV.COLUMNA_PK , id);
            while(rs.next())
            {
                imp = new ImpuestoIGV();
                imp.setPrimaryKey(new String[]{rs.getString("Co_Compania"),rs.getString("Co_Impuesto")});    
                imp.setCoCompania(rs.getString("CO_COMPANIA"));
                imp.setCoImpuesto(rs.getString("CO_IMPUESTO"));
                imp.setNuOrdenFila(rs.getInt("NU_ORDEN_FILA"));
                imp.setDeCortaImpuesto(rs.getString("DE_CORTA_IMPUESTO"));
                imp.setDeImpuesto(rs.getString("DE_IMPUESTO"));
                imp.setPcImpuesto(rs.getInt("PC_IMPUESTO"));
                imp.setInOperacionImpuesto(rs.getString("IN_OPERACION_IMPUESTO"));
                imp.setEsImpuesto(rs.getString("ES_IMPUESTO"));
                imp.setIdCreaImpuesto(rs.getString("ID_CREA_IMPUESTO"));
                imp.setFeCreaImpuesto(rs.getDate("FE_CREA_IMPUESTO"));
                imp.setIdModImpuesto(rs.getString("ID_MOD_IMPUESTO"));
                imp.setFeModImpuesto(rs.getDate("FE_MOD_IMPUESTO"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return imp;
    }
    
    @Override
    public ArrayList<ImpuestoIGV> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<ImpuestoIGV> rgs = new ArrayList<ImpuestoIGV>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(ImpuestoIGV.nt, ImpuestoIGV.COLUMNA_ACTIVO, ImpuestoIGV.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(ImpuestoIGV.nt, ImpuestoIGV.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(ImpuestoIGV.nt, campos, columnaId, id, columnaId);
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
                imp= new ImpuestoIGV();
                imp.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_IMPUESTO")});    
                imp.setCoCompania(rs.getString("CO_COMPANIA"));
                imp.setCoImpuesto(rs.getString("CO_IMPUESTO"));
                imp.setNuOrdenFila(rs.getInt("NU_ORDEN_FILA"));
                imp.setDeCortaImpuesto(rs.getString("DE_CORTA_IMPUESTO"));
                imp.setDeImpuesto(rs.getString("DE_IMPUESTO"));
                imp.setPcImpuesto(rs.getInt("PC_IMPUESTO"));
                imp.setInOperacionImpuesto(rs.getString("IN_OPERACION_IMPUESTO"));
                imp.setEsImpuesto(rs.getString("ES_IMPUESTO"));
                imp.setIdCreaImpuesto(rs.getString("ID_CREA_IMPUESTO"));
                imp.setFeCreaImpuesto(rs.getDate("FE_CREA_IMPUESTO"));
                imp.setIdModImpuesto(rs.getString("ID_MOD_IMPUESTO"));
                imp.setFeModImpuesto(rs.getDate("FE_MOD_IMPUESTO"));
                rgs.add(imp);
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
        imp = (ImpuestoIGV)mdl;
        int gravado = 0;
        String campos = ImpuestoIGV.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {imp.getCoCompania(),
                            imp.getCoImpuesto(),
                            imp.getNuOrdenFila(),
                            imp.getDeCortaImpuesto(),
                            imp.getDeImpuesto(),
                            imp.getPcImpuesto(),
                            imp.getInOperacionImpuesto(),
                            imp.getEsImpuesto(),
                            imp.getIdCreaImpuesto(),
                            imp.getFeCreaImpuesto(),
                            imp.getIdModImpuesto(),
                            imp.getFeModImpuesto()
                           };
       
           gravado = this.agregarRegistroPs(imp.getNombreTabla(),ImpuestoIGV.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        imp = (ImpuestoIGV)mdl;
        int gravado = 0;        
        
        Object[] valores = {imp.getNuOrdenFila(),
                            imp.getDeCortaImpuesto(),
                            imp.getDeImpuesto(),
                            imp.getPcImpuesto(),
                            imp.getInOperacionImpuesto(),
                            imp.getEsImpuesto(),
                            imp.getIdCreaImpuesto(),
                            imp.getFeCreaImpuesto(),
                            imp.getIdModImpuesto(),
                            imp.getFeModImpuesto()
                           };

        gravado = this.actualizarRegistroPs(imp.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(ImpuestoIGV.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(ImpuestoIGV.COLUMNA_PK, imp.getPrimaryKey()) , valores);
        return gravado;
    }

    public ImpuestoIGV getImpuesto() {
        if(imp == null)
        {
            imp = new ImpuestoIGV();
        }
        return imp;
    }

    public void setImpuestoIGV(JAbstractModelBD prv) {
        this.imp = (ImpuestoIGV)prv;
    }
    public String getNuevoCodigoImpuesto(){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(ImpuestoIGV.nt,"rtrim(ltrim(to_char(max(co_Impuesto) + 1,'00')))"," co_compania = '" + AtuxVariables.vCodigoCompania + "'");
        } catch (SQLException ex) {
            Logger.getLogger(CImpuestoIGV.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }
}
