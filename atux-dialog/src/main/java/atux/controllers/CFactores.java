package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.Factores;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxVariables;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CFactores extends JAbstractController{
    private Factores factorPrecio;
   
    
    @Override
    public ArrayList<Factores> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{Factores.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<Factores> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<Factores> getRegistros() {                 
        ArrayList<Factores> rgs = new ArrayList<Factores>();
        try {                        
//            rs = this.getRegistros(TipoDeCambio.nt,TipoDeCambio.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(Factores.nt, Factores.FULL_NOM_CAMPOS, null, null, Factores.COLUMNA_ORDER);
            while(rs.next())
            {
                factorPrecio = new Factores();
                factorPrecio.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_FACTOR_PRECIO")});
                factorPrecio.setCoCompania(rs.getString("CO_COMPANIA"));
                factorPrecio.setCoFactorPrecio(rs.getString("CO_FACTOR_PRECIO"));
                factorPrecio.setDeFactorPrecio(rs.getString("DE_FACTOR_PRECIO"));
                factorPrecio.setPcFactorPrecio(rs.getDouble("PC_FACTOR_PRECIO"));
                factorPrecio.setEsFactorPrecio(rs.getString("ES_FACTOR_PRECIO"));
                factorPrecio.setIdCreaFactorPrecio(rs.getString("ID_CREA_FACTOR_PRECIO"));
                factorPrecio.setFeCreaFactorPrecio(rs.getDate("FE_CREA_FACTOR_PRECIO"));
                factorPrecio.setIdModFactorPrecio(rs.getString("ID_MOD_FACTOR_PRECIO"));
                factorPrecio.setFeModFactorPrecio(rs.getDate("FE_MOD_FACTOR_PRECIO"));
                rgs.add(factorPrecio);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public Factores getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(Factores.nt, Factores.FULL_NOM_CAMPOS, Factores.COLUMNA_PK , id);
            while(rs.next())
            {
                factorPrecio = new Factores();
                factorPrecio.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_FACTOR_PRECIO")});
                factorPrecio.setCoCompania(rs.getString("CO_COMPANIA"));
                factorPrecio.setCoFactorPrecio(rs.getString("CO_FACTOR_PRECIO"));
                factorPrecio.setDeFactorPrecio(rs.getString("DE_FACTOR_PRECIO"));
                factorPrecio.setPcFactorPrecio(rs.getDouble("PC_FACTOR_PRECIO"));
                factorPrecio.setEsFactorPrecio(rs.getString("ES_FACTOR_PRECIO"));
                factorPrecio.setIdCreaFactorPrecio(rs.getString("ID_CREA_FACTOR_PRECIO"));
                factorPrecio.setFeCreaFactorPrecio(rs.getDate("FE_CREA_FACTOR_PRECIO"));
                factorPrecio.setIdModFactorPrecio(rs.getString("ID_MOD_FACTOR_PRECIO"));
                factorPrecio.setFeModFactorPrecio(rs.getDate("FE_MOD_FACTOR_PRECIO"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return factorPrecio;
    }
    
    @Override
    public ArrayList<Factores> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<Factores> rgs = new ArrayList<Factores>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(Factores.nt, Factores.COLUMNA_ACTIVO, Factores.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(Factores.nt, Factores.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(Factores.nt, campos, columnaId, id, Factores.COLUMNA_ORDER);
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
                factorPrecio = new Factores();
                factorPrecio.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_FACTOR_PRECIO")});
                factorPrecio.setCoCompania(rs.getString("CO_COMPANIA"));
                factorPrecio.setCoFactorPrecio(rs.getString("CO_FACTOR_PRECIO"));
                factorPrecio.setDeFactorPrecio(rs.getString("DE_FACTOR_PRECIO"));
                factorPrecio.setPcFactorPrecio(rs.getDouble("PC_FACTOR_PRECIO"));
                factorPrecio.setEsFactorPrecio(rs.getString("ES_FACTOR_PRECIO"));
                factorPrecio.setIdCreaFactorPrecio(rs.getString("ID_CREA_FACTOR_PRECIO"));
                factorPrecio.setFeCreaFactorPrecio(rs.getDate("FE_CREA_FACTOR_PRECIO"));
                factorPrecio.setIdModFactorPrecio(rs.getString("ID_MOD_FACTOR_PRECIO"));
                factorPrecio.setFeModFactorPrecio(rs.getDate("FE_MOD_FACTOR_PRECIO"));
                rgs.add(factorPrecio);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
        
    }

    public ArrayList getFactores()
     {
        ArrayList<Factores> rgs = new ArrayList<Factores>();
        try {                        
            StringBuffer  query = new StringBuffer();

            query.append("SELECT *  ");            
            query.append("  FROM CMTR_TIPO_VIA ");
            query.append(" WHERE ES_TIPO_VIA ='A' ");
            query.append(" ORDER BY DE_TIPO_VIA");
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                factorPrecio = new Factores();
                factorPrecio.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_FACTOR_PRECIO")});
                factorPrecio.setCoCompania(rs.getString("CO_COMPANIA"));
                factorPrecio.setCoFactorPrecio(rs.getString("CO_FACTOR_PRECIO"));
                factorPrecio.setDeFactorPrecio(rs.getString("DE_FACTOR_PRECIO"));
                factorPrecio.setPcFactorPrecio(rs.getDouble("PC_FACTOR_PRECIO"));
                factorPrecio.setEsFactorPrecio(rs.getString("ES_FACTOR_PRECIO"));
                factorPrecio.setIdCreaFactorPrecio(rs.getString("ID_CREA_FACTOR_PRECIO"));
                factorPrecio.setFeCreaFactorPrecio(rs.getDate("FE_CREA_FACTOR_PRECIO"));
                factorPrecio.setIdModFactorPrecio(rs.getString("ID_MOD_FACTOR_PRECIO"));
                factorPrecio.setFeModFactorPrecio(rs.getDate("FE_MOD_FACTOR_PRECIO"));
                rgs.add(factorPrecio);
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
        factorPrecio = (Factores)mdl;
        int gravado = 0;
        String campos = Factores.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {factorPrecio.getCoCompania(),
                            factorPrecio.getCoFactorPrecio(),
                            factorPrecio.getDeFactorPrecio(),
                            factorPrecio.getPcFactorPrecio(),
                            factorPrecio.getEsFactorPrecio(),
                            factorPrecio.getIdCreaFactorPrecio(),
                            factorPrecio.getFeCreaFactorPrecio(),
                            factorPrecio.getIdModFactorPrecio(),
                            factorPrecio.getFeModFactorPrecio()
                           };
       
           gravado = this.agregarRegistroPs(factorPrecio.getNombreTabla(),Factores.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        factorPrecio = (Factores)mdl;
        int gravado = 0;        
        
        Object[] valores = {factorPrecio.getDeFactorPrecio(),
                            factorPrecio.getPcFactorPrecio(),
                            factorPrecio.getEsFactorPrecio(),
                            factorPrecio.getIdCreaFactorPrecio(),
                            factorPrecio.getFeCreaFactorPrecio(),
                            factorPrecio.getIdModFactorPrecio(),
                            factorPrecio.getFeModFactorPrecio()
                           };

        gravado = this.actualizarRegistroPs(factorPrecio.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(Factores.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(Factores.COLUMNA_PK, factorPrecio.getPrimaryKey()) , valores);
        return gravado;
    }

    public Factores getFactorPrecio() {
        if(factorPrecio == null)
        {
            factorPrecio = new Factores();
        }
        return factorPrecio;
    }

    public void setFactoresPrecio(JAbstractModelBD prv) {
        this.factorPrecio = (Factores)prv;
    }

    public String getNuevoCodigo(){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(Factores.nt,"rtrim(ltrim(to_char(max(CO_FACTOR_PRECIO) + 1,'000')))"," CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'");
        } catch (SQLException ex) {
            Logger.getLogger(CFactores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }
}
