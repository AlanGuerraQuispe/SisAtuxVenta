package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.Cadena;
import atux.util.common.AtuxDBUtility;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CCadena extends JAbstractController{
    private Cadena mCadena;
   
    
    @Override
    public ArrayList<Cadena> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{Cadena.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<Cadena> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       

    public ArrayList<Cadena> getRegistros(String Filtro) {                 
        ArrayList<Cadena> rgs = new ArrayList<Cadena>();
        StringBuffer  query;
        try {            
            query = new StringBuffer();
            
            query.append("select * ");
            query.append("  from CMTM_Cadena ");
            query.append(" WHERE CO_Compania IS NOT NULL ");

            if ("A".equals(Filtro) || "I".equals(Filtro)){
                query.append("   AND ES_PROVEEDOR = '").append(Filtro).append("' ");
            }else if (!"T".equals(Filtro)){
                query.append(Filtro);
            }
            
            query.append(" ORDER BY DE_Cadena ");
            
            rs =  this.getRegistrosSinFiltro(query);                      
            while(rs.next())
            {
                mCadena = new Cadena();
                mCadena.setPrimaryKey(new String[]{rs.getString("CO_Compania"),rs.getString("CO_Cadena")});
                mCadena.setCoCompania(rs.getString("CO_COMPANIA"));
                mCadena.setCoCadena(rs.getString("CO_CADENA"));
                mCadena.setDeCadena(rs.getString("DE_CADENA"));
                mCadena.setEsCadena(rs.getString("ES_CADENA"));
                mCadena.setIdCreaCadena(rs.getString("ID_CREA_CADENA"));
                mCadena.setFeCreaCadena(rs.getDate("FE_CREA_CADENA"));
                mCadena.setIdModCadena(rs.getString("ID_MOD_CADENA"));
                mCadena.setFeModCadena(rs.getDate("FE_MOD_CADENA"));
                rgs.add(mCadena);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }    
    
    @Override
    public ArrayList<Cadena> getRegistros() {                 
        ArrayList<Cadena> rgs = new ArrayList<Cadena>();
        try {                        
            rs = this.getRegistros(Cadena.nt, Cadena.FULL_NOM_CAMPOS, null, null, Cadena.COLUMNA_ORDER);
            while(rs.next())
            {
                mCadena = new Cadena();
                mCadena.setPrimaryKey(new String[]{rs.getString("CO_Compania"),rs.getString("CO_Cadena")});
                mCadena.setCoCompania(rs.getString("CO_COMPANIA"));
                mCadena.setCoCadena(rs.getString("CO_CADENA"));
                mCadena.setDeCadena(rs.getString("DE_CADENA"));
                mCadena.setEsCadena(rs.getString("ES_CADENA"));
                mCadena.setIdCreaCadena(rs.getString("ID_CREA_CADENA"));
                mCadena.setFeCreaCadena(rs.getDate("FE_CREA_CADENA"));
                mCadena.setIdModCadena(rs.getString("ID_MOD_CADENA"));
                mCadena.setFeModCadena(rs.getDate("FE_MOD_CADENA"));
                rgs.add(mCadena);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public Cadena getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(Cadena.nt, Cadena.FULL_NOM_CAMPOS, Cadena.COLUMNA_PK , id);
            while(rs.next())
            {
                mCadena = new Cadena();
                mCadena.setPrimaryKey(new String[]{rs.getString("CO_Compania"),rs.getString("CO_Cadena")});
                mCadena.setCoCompania(rs.getString("CO_COMPANIA"));
                mCadena.setCoCadena(rs.getString("CO_CADENA"));
                mCadena.setDeCadena(rs.getString("DE_CADENA"));
                mCadena.setEsCadena(rs.getString("ES_CADENA"));
                mCadena.setIdCreaCadena(rs.getString("ID_CREA_CADENA"));
                mCadena.setFeCreaCadena(rs.getDate("FE_CREA_CADENA"));
                mCadena.setIdModCadena(rs.getString("ID_MOD_CADENA"));
                mCadena.setFeModCadena(rs.getDate("FE_MOD_CADENA"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return mCadena;
    }
    
    @Override
    public ArrayList<Cadena> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<Cadena> rgs = new ArrayList<Cadena>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(Cadena.nt, Cadena.COLUMNA_ACTIVO, Cadena.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(Cadena.nt, Cadena.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(Cadena.nt, campos, columnaId, id, Cadena.COLUMNA_ORDER);
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
                mCadena = new Cadena();
                mCadena.setPrimaryKey(new String[]{rs.getString("CO_Compania"),rs.getString("CO_Cadena")});
                mCadena.setCoCompania(rs.getString("CO_COMPANIA"));
                mCadena.setCoCadena(rs.getString("CO_CADENA"));
                mCadena.setDeCadena(rs.getString("DE_CADENA"));
                mCadena.setEsCadena(rs.getString("ES_CADENA"));
                mCadena.setIdCreaCadena(rs.getString("ID_CREA_CADENA"));
                mCadena.setFeCreaCadena(rs.getDate("FE_CREA_CADENA"));
                mCadena.setIdModCadena(rs.getString("ID_MOD_CADENA"));
                mCadena.setFeModCadena(rs.getDate("FE_MOD_CADENA"));
                rgs.add(mCadena);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
        
    }

    public ArrayList getCadena()
     {
        ArrayList<Cadena> rgs = new ArrayList<Cadena>();
        try {                        
            StringBuffer  query = new StringBuffer();

            query.append("SELECT *  ");            
            query.append("  FROM CMTR_TIPO_VIA ");
            query.append(" WHERE ES_TIPO_VIA ='A' ");
            query.append(" ORDER BY DE_TIPO_VIA");
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                mCadena = new Cadena();
                mCadena.setPrimaryKey(new String[]{rs.getString("CO_Compania"),rs.getString("CO_Cadena")});
                mCadena.setCoCompania(rs.getString("CO_COMPANIA"));
                mCadena.setCoCadena(rs.getString("CO_CADENA"));
                mCadena.setDeCadena(rs.getString("DE_CADENA"));
                mCadena.setEsCadena(rs.getString("ES_CADENA"));
                mCadena.setIdCreaCadena(rs.getString("ID_CREA_CADENA"));
                mCadena.setFeCreaCadena(rs.getDate("FE_CREA_CADENA"));
                mCadena.setIdModCadena(rs.getString("ID_MOD_CADENA"));
                mCadena.setFeModCadena(rs.getDate("FE_MOD_CADENA"));
                rgs.add(mCadena);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }    
    
    public String BuscarCadena(String codigoCompania, String codigoCadena){
        CCadena BG1 = new CCadena();
        if (codigoCompania != null){
            Object[] valores = new Object[]{codigoCompania, codigoCadena}; 
            BG1.getRegistroPorPk(valores);
            return BG1.getMaestroCadena().getDeCadena().trim();
        }
        return "";
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
        mCadena = (Cadena)mdl;
        int gravado = 0;
        String campos = Cadena.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {mCadena.getCoCompania(),
                            mCadena.getCoCadena(),
                            mCadena.getDeCadena(),
                            mCadena.getEsCadena(),
                            mCadena.getIdCreaCadena(),
                            mCadena.getFeCreaCadena(),
                            mCadena.getIdModCadena(),
                            mCadena.getFeModCadena()
                           };
       
           gravado = this.agregarRegistroPs(mCadena.getNombreTabla(),Cadena.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        mCadena = (Cadena)mdl;
        int gravado = 0;        
        
        Object[] valores = {mCadena.getDeCadena(),
                            mCadena.getEsCadena(),
                            mCadena.getIdCreaCadena(),
                            mCadena.getFeCreaCadena(),
                            mCadena.getIdModCadena(),
                            mCadena.getFeModCadena()
                           };

        gravado = this.actualizarRegistroPs(mCadena.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(Cadena.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(Cadena.COLUMNA_PK, mCadena.getPrimaryKey()) , valores);
        return gravado;
    }

    public Cadena getMaestroCadena() {
        if(mCadena == null)
        {
            mCadena = new Cadena();
        }
        return mCadena;
    }

    public void setMaestroCadena(JAbstractModelBD prv) {
        this.mCadena = (Cadena)prv;
    }

    public String getNuevoCodigo(String coCompania){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(Cadena.nt,"rtrim(ltrim(to_char(nvl(max(CO_Cadena),0) + 1,'00')))"," CO_compania = '" + coCompania + "'");
        } catch (SQLException ex) {
            Logger.getLogger(CCadena.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }
}
