package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.Region;
import java.sql.SQLException;
import java.util.ArrayList;


public class CRegion extends JAbstractController{
    private Region mRegion;
   
    
    @Override
    public ArrayList<Region> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{Region.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<Region> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       

    public ArrayList<Region> getRegistros(String Filtro) {                 
        ArrayList<Region> rgs = new ArrayList<Region>();
        StringBuffer  query;
        try {            
            query = new StringBuffer();
            
            query.append("select * ");
            query.append("  from vttr_region ");
            query.append(" WHERE CO_Region IS NOT NULL ");

            if ("A".equals(Filtro) || "I".equals(Filtro)){
                query.append("   AND ES_REGION = '").append(Filtro).append("' ");
            }else if (!"T".equals(Filtro)){
                query.append(Filtro);
            }
            
            query.append(" ORDER BY DE_Region ");
            
            rs =  this.getRegistrosSinFiltro(query);                      
            while(rs.next())
            {
                mRegion = new Region();
                mRegion.setPrimaryKey(new String[]{rs.getString("CO_Region")});
                mRegion.setCoRegion(rs.getString("CO_REGION"));
                mRegion.setDeCortaRegion(rs.getString("DE_CORTA_REGION"));
                mRegion.setDeRegion(rs.getString("DE_REGION"));
                mRegion.setEsRegion(rs.getString("ES_REGION"));
                mRegion.setIdCreaRegion(rs.getString("ID_CREA_REGION"));
                mRegion.setFeCreaRegion(rs.getDate("FE_CREA_REGION"));
                mRegion.setIdModRegion(rs.getString("ID_MOD_REGION"));
                mRegion.setFeModRegion(rs.getDate("FE_MOD_REGION"));
                mRegion.setCoRegionSap(rs.getString("CO_REGION_SAP"));
                rgs.add(mRegion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }    
    
    @Override
    public ArrayList<Region> getRegistros() {                 
        ArrayList<Region> rgs = new ArrayList<Region>();
        try {                        
            rs = this.getRegistros(Region.nt, Region.FULL_NOM_CAMPOS, null, null, Region.COLUMNA_ORDER);
            while(rs.next())
            {
                mRegion = new Region();
                mRegion.setPrimaryKey(new String[]{rs.getString("CO_Region")});
                mRegion.setCoRegion(rs.getString("CO_REGION"));
                mRegion.setDeCortaRegion(rs.getString("DE_CORTA_REGION"));
                mRegion.setDeRegion(rs.getString("DE_REGION"));
                mRegion.setEsRegion(rs.getString("ES_REGION"));
                mRegion.setIdCreaRegion(rs.getString("ID_CREA_REGION"));
                mRegion.setFeCreaRegion(rs.getDate("FE_CREA_REGION"));
                mRegion.setIdModRegion(rs.getString("ID_MOD_REGION"));
                mRegion.setFeModRegion(rs.getDate("FE_MOD_REGION"));
                mRegion.setCoRegionSap(rs.getString("CO_REGION_SAP"));
                rgs.add(mRegion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public Region getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(Region.nt, Region.FULL_NOM_CAMPOS, Region.COLUMNA_PK , id);
            while(rs.next())
            {
                mRegion = new Region();
                mRegion.setPrimaryKey(new String[]{rs.getString("CO_Region")});
                mRegion.setCoRegion(rs.getString("CO_REGION"));
                mRegion.setDeCortaRegion(rs.getString("DE_CORTA_REGION"));
                mRegion.setDeRegion(rs.getString("DE_REGION"));
                mRegion.setEsRegion(rs.getString("ES_REGION"));
                mRegion.setIdCreaRegion(rs.getString("ID_CREA_REGION"));
                mRegion.setFeCreaRegion(rs.getDate("FE_CREA_REGION"));
                mRegion.setIdModRegion(rs.getString("ID_MOD_REGION"));
                mRegion.setFeModRegion(rs.getDate("FE_MOD_REGION"));
                mRegion.setCoRegionSap(rs.getString("CO_REGION_SAP"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return mRegion;
    }
    
    @Override
    public ArrayList<Region> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<Region> rgs = new ArrayList<Region>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(Region.nt, Region.COLUMNA_ACTIVO, Region.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(Region.nt, Region.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(Region.nt, campos, columnaId, id, Region.COLUMNA_ORDER);
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
                mRegion = new Region();
                mRegion.setPrimaryKey(new String[]{rs.getString("CO_Region")});
                mRegion.setCoRegion(rs.getString("CO_REGION"));
                mRegion.setDeCortaRegion(rs.getString("DE_CORTA_REGION"));
                mRegion.setDeRegion(rs.getString("DE_REGION"));
                mRegion.setEsRegion(rs.getString("ES_REGION"));
                mRegion.setIdCreaRegion(rs.getString("ID_CREA_REGION"));
                mRegion.setFeCreaRegion(rs.getDate("FE_CREA_REGION"));
                mRegion.setIdModRegion(rs.getString("ID_MOD_REGION"));
                mRegion.setFeModRegion(rs.getDate("FE_MOD_REGION"));
                mRegion.setCoRegionSap(rs.getString("CO_REGION_SAP"));
                rgs.add(mRegion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
        
    }

    public ArrayList getRegion()
     {
        ArrayList<Region> rgs = new ArrayList<Region>();
        try {                        
            StringBuffer  query = new StringBuffer();

            query.append("SELECT *  ");            
            query.append("  FROM VTTR_REGION ");
            query.append(" WHERE ES_REGION ='A' ");
            query.append(" ORDER BY DE_REGION");
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                mRegion = new Region();
                mRegion.setPrimaryKey(new String[]{rs.getString("CO_Region")});
                mRegion.setCoRegion(rs.getString("CO_REGION"));
                mRegion.setDeCortaRegion(rs.getString("DE_CORTA_REGION"));
                mRegion.setDeRegion(rs.getString("DE_REGION"));
                mRegion.setEsRegion(rs.getString("ES_REGION"));
                mRegion.setIdCreaRegion(rs.getString("ID_CREA_REGION"));
                mRegion.setFeCreaRegion(rs.getDate("FE_CREA_REGION"));
                mRegion.setIdModRegion(rs.getString("ID_MOD_REGION"));
                mRegion.setFeModRegion(rs.getDate("FE_MOD_REGION"));
                mRegion.setCoRegionSap(rs.getString("CO_REGION_SAP"));
                rgs.add(mRegion);
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
        mRegion = (Region)mdl;
        int gravado = 0;
        String campos = Region.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {mRegion.getCoRegion(),
                            mRegion.getDeCortaRegion(),
                            mRegion.getDeRegion(),
                            mRegion.getEsRegion(),
                            mRegion.getIdCreaRegion(),
                            mRegion.getFeCreaRegion(),
                            mRegion.getIdModRegion(),
                            mRegion.getFeModRegion(),
                            mRegion.getCoRegionSap()
                           };
       
           gravado = this.agregarRegistroPs(mRegion.getNombreTabla(),Region.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        mRegion = (Region)mdl;
        int gravado = 0;        
        
        Object[] valores = {mRegion.getCoRegion(),
                            mRegion.getDeCortaRegion(),
                            mRegion.getDeRegion(),
                            mRegion.getEsRegion(),
                            mRegion.getIdCreaRegion(),
                            mRegion.getFeCreaRegion(),
                            mRegion.getIdModRegion(),
                            mRegion.getFeModRegion(),
                            mRegion.getCoRegionSap()
                           };

        gravado = this.actualizarRegistroPs(mRegion.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(Region.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(Region.COLUMNA_PK, mRegion.getPrimaryKey()) , valores);
        return gravado;
    }

    public Region getMaestroRegion() {
        if(mRegion == null)
        {
            mRegion = new Region();
        }
        return mRegion;
    }

    public void setMaestroRegion(JAbstractModelBD prv) {
        this.mRegion = (Region)prv;
    }


}
