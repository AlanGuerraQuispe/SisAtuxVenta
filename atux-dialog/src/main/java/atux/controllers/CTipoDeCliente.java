package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.TipoDeCliente;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxVariables;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CTipoDeCliente extends JAbstractController{
    private TipoDeCliente tCliente;
   
    
    @Override
    public ArrayList<TipoDeCliente> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{TipoDeCliente.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<TipoDeCliente> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<TipoDeCliente> getRegistros() {                 
        ArrayList<TipoDeCliente> rgs = new ArrayList<TipoDeCliente>();
        try {                        
//            rs = this.getRegistros(TipoDeCliente.nt,TipoDeCliente.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(TipoDeCliente.nt, TipoDeCliente.FULL_NOM_CAMPOS, null, null, TipoDeCliente.COLUMNA_ORDER);
            while(rs.next())
            {
                tCliente = new TipoDeCliente();
                tCliente.setPrimaryKey(new String[]{rs.getString("Co_Compania"),rs.getString("TI_CLIENTE")});
                tCliente.setCoCompania(rs.getString("CO_COMPANIA"));
                tCliente.setTiCliente(rs.getString("TI_CLIENTE"));
                tCliente.setNuOrdenFila(rs.getInt("NU_ORDEN_FILA"));
                tCliente.setDeCortaTipoCliente(rs.getString("DE_CORTA_TIPO_CLIENTE"));
                tCliente.setDeTipoCliente(rs.getString("DE_TIPO_CLIENTE"));
                tCliente.setTiPantalla(rs.getString("TI_PANTALLA"));
                tCliente.setEsTipoCliente(rs.getString("ES_TIPO_CLIENTE"));
                tCliente.setIdCreaTipoCliente(rs.getString("ID_CREA_TIPO_CLIENTE"));
                tCliente.setFeCreaTipoCliente(rs.getDate("FE_CREA_TIPO_CLIENTE"));
                tCliente.setIdModTipoCliente(rs.getString("ID_MOD_TIPO_CLIENTE"));
                tCliente.setFeModTipoCliente(rs.getDate("FE_MOD_TIPO_CLIENTE"));
                rgs.add(tCliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public TipoDeCliente getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(TipoDeCliente.nt, TipoDeCliente.FULL_NOM_CAMPOS, TipoDeCliente.COLUMNA_PK , id);
            while(rs.next())
            {
                tCliente = new TipoDeCliente();
                tCliente.setPrimaryKey(new String[]{rs.getString("Co_Compania"),rs.getString("TI_CLIENTE")});
                tCliente.setCoCompania(rs.getString("CO_COMPANIA"));
                tCliente.setTiCliente(rs.getString("TI_CLIENTE"));
                tCliente.setNuOrdenFila(rs.getInt("NU_ORDEN_FILA"));
                tCliente.setDeCortaTipoCliente(rs.getString("DE_CORTA_TIPO_CLIENTE"));
                tCliente.setDeTipoCliente(rs.getString("DE_TIPO_CLIENTE"));
                tCliente.setTiPantalla(rs.getString("TI_PANTALLA"));
                tCliente.setEsTipoCliente(rs.getString("ES_TIPO_CLIENTE"));
                tCliente.setIdCreaTipoCliente(rs.getString("ID_CREA_TIPO_CLIENTE"));
                tCliente.setFeCreaTipoCliente(rs.getDate("FE_CREA_TIPO_CLIENTE"));
                tCliente.setIdModTipoCliente(rs.getString("ID_MOD_TIPO_CLIENTE"));
                tCliente.setFeModTipoCliente(rs.getDate("FE_MOD_TIPO_CLIENTE"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tCliente;
    }
    
    @Override
    public ArrayList<TipoDeCliente> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<TipoDeCliente> rgs = new ArrayList<TipoDeCliente>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(TipoDeCliente.nt, TipoDeCliente.COLUMNA_ACTIVO, TipoDeCliente.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(TipoDeCliente.nt, TipoDeCliente.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(TipoDeCliente.nt, campos, columnaId, id, TipoDeCliente.COLUMNA_ORDER);
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
                tCliente= new TipoDeCliente();
                tCliente.setPrimaryKey(new String[]{rs.getString("Co_Compania"),rs.getString("TI_CLIENTE")});
                tCliente.setCoCompania(rs.getString("CO_COMPANIA"));
                tCliente.setTiCliente(rs.getString("TI_CLIENTE"));
                tCliente.setNuOrdenFila(rs.getInt("NU_ORDEN_FILA"));
                tCliente.setDeCortaTipoCliente(rs.getString("DE_CORTA_TIPO_CLIENTE"));
                tCliente.setDeTipoCliente(rs.getString("DE_TIPO_CLIENTE"));
                tCliente.setTiPantalla(rs.getString("TI_PANTALLA"));
                tCliente.setEsTipoCliente(rs.getString("ES_TIPO_CLIENTE"));
                tCliente.setIdCreaTipoCliente(rs.getString("ID_CREA_TIPO_CLIENTE"));
                tCliente.setFeCreaTipoCliente(rs.getDate("FE_CREA_TIPO_CLIENTE"));
                tCliente.setIdModTipoCliente(rs.getString("ID_MOD_TIPO_CLIENTE"));
                tCliente.setFeModTipoCliente(rs.getDate("FE_MOD_TIPO_CLIENTE"));
                rgs.add(tCliente);
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
        tCliente = (TipoDeCliente)mdl;
        int gravado = 0;
        String campos = TipoDeCliente.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {tCliente.getCoCompania(),
                            tCliente.getTiCliente(),
                            tCliente.getNuOrdenFila(),
                            tCliente.getDeCortaTipoCliente(),
                            tCliente.getDeTipoCliente(),
                            tCliente.getTiPantalla(),
                            tCliente.getEsTipoCliente(),
                            tCliente.getIdCreaTipoCliente(),
                            tCliente.getFeCreaTipoCliente(),
                            tCliente.getIdModTipoCliente(),
                            tCliente.getFeModTipoCliente()
                           };
       
           gravado = this.agregarRegistroPs(tCliente.getNombreTabla(),TipoDeCliente.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        tCliente = (TipoDeCliente)mdl;
        int gravado = 0;        
        
        Object[] valores = {tCliente.getNuOrdenFila(),
                            tCliente.getDeCortaTipoCliente(),
                            tCliente.getDeTipoCliente(),
                            tCliente.getTiPantalla(),
                            tCliente.getEsTipoCliente(),
                            tCliente.getIdCreaTipoCliente(),
                            tCliente.getFeCreaTipoCliente(),
                            tCliente.getIdModTipoCliente(),
                            tCliente.getFeModTipoCliente()
                           };

        gravado = this.actualizarRegistroPs(tCliente.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(TipoDeCliente.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(TipoDeCliente.COLUMNA_PK, tCliente.getPrimaryKey()) , valores);
        return gravado;
    }

    public ArrayList getTipoCliente()
     {
        ArrayList<TipoDeCliente> rgs = new ArrayList<TipoDeCliente>();
        try {                        
            StringBuffer  query = new StringBuffer();
            query.append("SELECT * ");
            query.append("  FROM VTTR_TIPO_CLIENTE ");
            query.append(" WHERE ES_TIPO_CLIENTE ='A' ");
            query.append(" ORDER BY DE_TIPO_CLIENTE");
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                tCliente= new TipoDeCliente();
                tCliente.setPrimaryKey(new String[]{rs.getString("Co_Compania"),rs.getString("TI_CLIENTE"), rs.getString("NU_ORDEN_FILA")});
                tCliente.setCoCompania(rs.getString("CO_COMPANIA"));
                tCliente.setTiCliente(rs.getString("TI_CLIENTE"));
                tCliente.setNuOrdenFila(rs.getInt("NU_ORDEN_FILA"));
                tCliente.setDeCortaTipoCliente(rs.getString("DE_CORTA_TIPO_CLIENTE"));
                tCliente.setDeTipoCliente(rs.getString("DE_TIPO_CLIENTE"));
                tCliente.setTiPantalla(rs.getString("TI_PANTALLA"));
                tCliente.setEsTipoCliente(rs.getString("ES_TIPO_CLIENTE"));
                tCliente.setIdCreaTipoCliente(rs.getString("ID_CREA_TIPO_CLIENTE"));
                tCliente.setFeCreaTipoCliente(rs.getDate("FE_CREA_TIPO_CLIENTE"));
                tCliente.setIdModTipoCliente(rs.getString("ID_MOD_TIPO_CLIENTE"));
                tCliente.setFeModTipoCliente(rs.getDate("FE_MOD_TIPO_CLIENTE"));
                rgs.add(tCliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }    

    
    public TipoDeCliente getTipoDeCliente() {
        if(tCliente == null)
        {
            tCliente = new TipoDeCliente();
        }
        return tCliente;
    }

    public void setTipoDeCliente(JAbstractModelBD prv) {
        this.tCliente = (TipoDeCliente)prv;
    }

    public String getNuevoCodigo(){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(TipoDeCliente.nt,"rtrim(ltrim(to_char(max(ti_cliente) + 1,'00')))"," co_compania = '" + AtuxVariables.vCodigoCompania);
        } catch (SQLException ex) {
            Logger.getLogger(CTipoDeCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }
}
