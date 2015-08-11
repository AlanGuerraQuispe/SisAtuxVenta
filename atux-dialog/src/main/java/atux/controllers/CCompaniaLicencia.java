package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.CompaniaLicencia;
import java.sql.SQLException;
import java.util.ArrayList;


public class CCompaniaLicencia extends JAbstractController{
    private CompaniaLicencia mSerieValidacionGeneracion;
   
    
    @Override
    public ArrayList<CompaniaLicencia> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{CompaniaLicencia.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<CompaniaLicencia> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       

    public ArrayList<CompaniaLicencia> getRegistros(String Filtro) {                 
        ArrayList<CompaniaLicencia> rgs = new ArrayList<CompaniaLicencia>();
        StringBuffer  query;
        try {            
            query = new StringBuffer();
            
            query.append("select * ");
            query.append("  from CMTM_COMPANIA_LICENCIA ");
            query.append(" WHERE CO_COMPANIA IS NOT NULL ");

            if ("A".equals(Filtro) || "I".equals(Filtro)){
                query.append("   AND ES_LICENCIA = '").append(Filtro).append("' ");
            }else if (!"T".equals(Filtro)){
                query.append(Filtro);
            }
            
            query.append(" ORDER BY DE_SerieValidacionGeneracion ");
            
            rs =  this.getRegistrosSinFiltro(query);                      
            while(rs.next())
            {
                mSerieValidacionGeneracion = new CompaniaLicencia();
                mSerieValidacionGeneracion.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA")});
                mSerieValidacionGeneracion.setCoCompania(rs.getString("CO_COMPANIA"));
                mSerieValidacionGeneracion.setNuLicencia(rs.getString("NU_LICENCIA"));
                mSerieValidacionGeneracion.setNuSerieLicencia(rs.getString("NU_SERIE_LICENCIA"));
                mSerieValidacionGeneracion.setEsLicencia(rs.getString("ES_LICENCIA"));
                mSerieValidacionGeneracion.setIdCreaLicencia(rs.getString("ID_CREA_LICENCIA"));
                mSerieValidacionGeneracion.setFeCreaLicencia(rs.getDate("FE_CREA_LICENCIA"));
                mSerieValidacionGeneracion.setIdModLicencia(rs.getString("ID_MOD_LICENCIA"));
                mSerieValidacionGeneracion.setFeModLicencia(rs.getDate("FE_MOD_LICENCIA"));
                rgs.add(mSerieValidacionGeneracion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }    
    
    @Override
    public ArrayList<CompaniaLicencia> getRegistros() {                 
        ArrayList<CompaniaLicencia> rgs = new ArrayList<CompaniaLicencia>();
        try {                        
            rs = this.getRegistros(CompaniaLicencia.nt, CompaniaLicencia.FULL_NOM_CAMPOS, null, null, CompaniaLicencia.COLUMNA_ORDER);
            while(rs.next()){
                mSerieValidacionGeneracion = new CompaniaLicencia();
                mSerieValidacionGeneracion.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA")});
                mSerieValidacionGeneracion.setCoCompania(rs.getString("CO_COMPANIA"));
                mSerieValidacionGeneracion.setNuLicencia(rs.getString("NU_LICENCIA"));
                mSerieValidacionGeneracion.setNuSerieLicencia(rs.getString("NU_SERIE_LICENCIA"));
                mSerieValidacionGeneracion.setEsLicencia(rs.getString("ES_LICENCIA"));
                mSerieValidacionGeneracion.setIdCreaLicencia(rs.getString("ID_CREA_LICENCIA"));
                mSerieValidacionGeneracion.setFeCreaLicencia(rs.getDate("FE_CREA_LICENCIA"));
                mSerieValidacionGeneracion.setIdModLicencia(rs.getString("ID_MOD_LICENCIA"));
                mSerieValidacionGeneracion.setFeModLicencia(rs.getDate("FE_MOD_LICENCIA"));
                rgs.add(mSerieValidacionGeneracion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public CompaniaLicencia getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(CompaniaLicencia.nt, CompaniaLicencia.FULL_NOM_CAMPOS, CompaniaLicencia.COLUMNA_PK , id);
            while(rs.next()){
                mSerieValidacionGeneracion = new CompaniaLicencia();
                mSerieValidacionGeneracion.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA")});
                mSerieValidacionGeneracion.setCoCompania(rs.getString("CO_COMPANIA"));
                mSerieValidacionGeneracion.setNuLicencia(rs.getString("NU_LICENCIA"));
                mSerieValidacionGeneracion.setNuSerieLicencia(rs.getString("NU_SERIE_LICENCIA"));
                mSerieValidacionGeneracion.setEsLicencia(rs.getString("ES_LICENCIA"));
                mSerieValidacionGeneracion.setIdCreaLicencia(rs.getString("ID_CREA_LICENCIA"));
                mSerieValidacionGeneracion.setFeCreaLicencia(rs.getDate("FE_CREA_LICENCIA"));
                mSerieValidacionGeneracion.setIdModLicencia(rs.getString("ID_MOD_LICENCIA"));
                mSerieValidacionGeneracion.setFeModLicencia(rs.getDate("FE_MOD_LICENCIA"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return mSerieValidacionGeneracion;
    }
    
    @Override
    public ArrayList<CompaniaLicencia> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<CompaniaLicencia> rgs = new ArrayList<CompaniaLicencia>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(CompaniaLicencia.nt, CompaniaLicencia.COLUMNA_ACTIVO, CompaniaLicencia.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(CompaniaLicencia.nt, CompaniaLicencia.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(CompaniaLicencia.nt, campos, columnaId, id, CompaniaLicencia.COLUMNA_ORDER);
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
                mSerieValidacionGeneracion = new CompaniaLicencia();
                mSerieValidacionGeneracion.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA")});
                mSerieValidacionGeneracion.setCoCompania(rs.getString("CO_COMPANIA"));
                mSerieValidacionGeneracion.setNuLicencia(rs.getString("NU_LICENCIA"));
                mSerieValidacionGeneracion.setNuSerieLicencia(rs.getString("NU_SERIE_LICENCIA"));
                mSerieValidacionGeneracion.setEsLicencia(rs.getString("ES_LICENCIA"));
                mSerieValidacionGeneracion.setIdCreaLicencia(rs.getString("ID_CREA_LICENCIA"));
                mSerieValidacionGeneracion.setFeCreaLicencia(rs.getDate("FE_CREA_LICENCIA"));
                mSerieValidacionGeneracion.setIdModLicencia(rs.getString("ID_MOD_LICENCIA"));
                mSerieValidacionGeneracion.setFeModLicencia(rs.getDate("FE_MOD_LICENCIA"));
                rgs.add(mSerieValidacionGeneracion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
        
    }

    public ArrayList getSerieValidacionGeneracion()
     {
        ArrayList<CompaniaLicencia> rgs = new ArrayList<CompaniaLicencia>();
        try {                        
            StringBuffer  query = new StringBuffer();

            query.append("SELECT *  ");            
            query.append("  FROM CMTM_COMPANIA_LICENCIA ");
            query.append(" WHERE ES_LICENCIA ='A' ");
            query.append(" ORDER BY NU_LICENCIA");
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                mSerieValidacionGeneracion = new CompaniaLicencia();
                mSerieValidacionGeneracion.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA")});
                mSerieValidacionGeneracion.setCoCompania(rs.getString("CO_COMPANIA"));
                mSerieValidacionGeneracion.setNuLicencia(rs.getString("NU_LICENCIA"));
                mSerieValidacionGeneracion.setNuSerieLicencia(rs.getString("NU_SERIE_LICENCIA"));
                mSerieValidacionGeneracion.setEsLicencia(rs.getString("ES_LICENCIA"));
                mSerieValidacionGeneracion.setIdCreaLicencia(rs.getString("ID_CREA_LICENCIA"));
                mSerieValidacionGeneracion.setFeCreaLicencia(rs.getDate("FE_CREA_LICENCIA"));
                mSerieValidacionGeneracion.setIdModLicencia(rs.getString("ID_MOD_LICENCIA"));
                mSerieValidacionGeneracion.setFeModLicencia(rs.getDate("FE_MOD_LICENCIA"));
                rgs.add(mSerieValidacionGeneracion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }    

    public void setSerieValidacionGeneracion(JAbstractModelBD prv) {
        this.mSerieValidacionGeneracion = (CompaniaLicencia)prv;
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
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException{
        mSerieValidacionGeneracion = (CompaniaLicencia)mdl;
        int gravado = 0;
        String campos = CompaniaLicencia.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {mSerieValidacionGeneracion.getCoCompania(),
                            mSerieValidacionGeneracion.getNuLicencia(),
                            mSerieValidacionGeneracion.getNuSerieLicencia(),
                            mSerieValidacionGeneracion.getEsLicencia(),
                            mSerieValidacionGeneracion.getIdCreaLicencia(),
                            mSerieValidacionGeneracion.getFeCreaLicencia(),
                            mSerieValidacionGeneracion.getIdModLicencia(),
                            mSerieValidacionGeneracion.getFeModLicencia()
                           };
       
        gravado = this.agregarRegistroPs(mSerieValidacionGeneracion.getNombreTabla(),CompaniaLicencia.FULL_NOM_CAMPOS, valores);
        if(gravado==1)
            return true;
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl){
        mSerieValidacionGeneracion = (CompaniaLicencia)mdl;
        int gravado = 0;        
        
        Object[] valores = {mSerieValidacionGeneracion.getNuSerieLicencia(),
                            mSerieValidacionGeneracion.getEsLicencia(),
                            mSerieValidacionGeneracion.getIdCreaLicencia(),
                            mSerieValidacionGeneracion.getFeCreaLicencia(),
                            mSerieValidacionGeneracion.getIdModLicencia(),
                            mSerieValidacionGeneracion.getFeModLicencia()
                           };

        gravado = this.actualizarRegistroPs(mSerieValidacionGeneracion.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(CompaniaLicencia.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(CompaniaLicencia.COLUMNA_PK, mSerieValidacionGeneracion.getPrimaryKey()) , valores);
        return gravado;
    }

    public CompaniaLicencia getCompaniaLicencia(){
        if(mSerieValidacionGeneracion == null){
            mSerieValidacionGeneracion = new CompaniaLicencia();
        }
        return mSerieValidacionGeneracion;
    }

    public void setCompaniaLicencia(JAbstractModelBD prv){
        this.mSerieValidacionGeneracion = (CompaniaLicencia)prv;
    }


}
