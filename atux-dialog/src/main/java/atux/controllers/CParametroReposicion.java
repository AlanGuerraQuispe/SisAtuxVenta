package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.ParametroReposicion;
import atux.util.common.AtuxDBUtility;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CParametroReposicion extends JAbstractController{
    private ParametroReposicion pReposicion;
   
    
    @Override
    public ArrayList<ParametroReposicion> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{ParametroReposicion.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<ParametroReposicion> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<ParametroReposicion> getRegistros() {                 
        ArrayList<ParametroReposicion> rgs = new ArrayList<ParametroReposicion>();
        try {                        
            rs = this.getRegistros(ParametroReposicion.nt, ParametroReposicion.FULL_NOM_CAMPOS, null, null, ParametroReposicion.COLUMNA_ORDER);
            while(rs.next())
            {
                pReposicion = new ParametroReposicion();
                pReposicion.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("NU_ORDEN_PARAMETRO"),rs.getString("TI_PARAMETRO"), rs.getString("CO_CODIGO"), rs.getString("NU_SECUENCIA")});    
                pReposicion.setCoCompania(rs.getString("CO_COMPANIA"));
                pReposicion.setCoLocal(rs.getString("CO_LOCAL"));
                pReposicion.setNuOrdenParametro(rs.getInt("NU_ORDEN_PARAMETRO"));
                pReposicion.setTiParametro(rs.getString("TI_PARAMETRO"));
                pReposicion.setCoCodigo(rs.getString("CO_CODIGO"));
                pReposicion.setNuSecuencia(rs.getInt("NU_SECUENCIA"));
                pReposicion.setNuMinimo(rs.getInt("NU_MINIMO"));
                pReposicion.setNuMaximo(rs.getInt("NU_MAXIMO"));
                pReposicion.setNuPeriodo(rs.getInt("NU_PERIODO"));
                pReposicion.setFeInicio(rs.getDate("FE_INICIO"));
                pReposicion.setFeFinal(rs.getDate("FE_FINAL"));
                pReposicion.setEsParametro(rs.getString("ES_PARAMETRO"));
                pReposicion.setIdCreaParametro(rs.getString("ID_CREA_PARAMETRO"));
                pReposicion.setFeCreaParametro(rs.getDate("FE_CREA_PARAMETRO"));
                pReposicion.setIdModParametro(rs.getString("ID_MOD_PARAMETRO"));
                pReposicion.setFeModParametro(rs.getDate("FE_MOD_PARAMETRO"));
                rgs.add(pReposicion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public ParametroReposicion getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(ParametroReposicion.nt, ParametroReposicion.FULL_NOM_CAMPOS, ParametroReposicion.COLUMNA_PK , id);
            while(rs.next()){
                pReposicion.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("NU_ORDEN_PARAMETRO"),rs.getString("TI_PARAMETRO"), rs.getString("CO_CODIGO"), rs.getString("NU_SECUENCIA")});    
                pReposicion.setCoCompania(rs.getString("CO_COMPANIA"));
                pReposicion.setCoLocal(rs.getString("CO_LOCAL"));
                pReposicion.setNuOrdenParametro(rs.getInt("NU_ORDEN_PARAMETRO"));
                pReposicion.setTiParametro(rs.getString("TI_PARAMETRO"));
                pReposicion.setCoCodigo(rs.getString("CO_CODIGO"));
                pReposicion.setNuSecuencia(rs.getInt("NU_SECUENCIA"));
                pReposicion.setNuMinimo(rs.getInt("NU_MINIMO"));
                pReposicion.setNuMaximo(rs.getInt("NU_MAXIMO"));
                pReposicion.setNuPeriodo(rs.getInt("NU_PERIODO"));
                pReposicion.setFeInicio(rs.getDate("FE_INICIO"));
                pReposicion.setFeFinal(rs.getDate("FE_FINAL"));
                pReposicion.setEsParametro(rs.getString("ES_PARAMETRO"));
                pReposicion.setIdCreaParametro(rs.getString("ID_CREA_PARAMETRO"));
                pReposicion.setFeCreaParametro(rs.getDate("FE_CREA_PARAMETRO"));
                pReposicion.setIdModParametro(rs.getString("ID_MOD_PARAMETRO"));
                pReposicion.setFeModParametro(rs.getDate("FE_MOD_PARAMETRO"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pReposicion;
    }
    
    @Override
    public ArrayList<ParametroReposicion> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<ParametroReposicion> rgs = new ArrayList<ParametroReposicion>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(ParametroReposicion.nt, ParametroReposicion.COLUMNA_ACTIVO, ParametroReposicion.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(ParametroReposicion.nt, ParametroReposicion.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(ParametroReposicion.nt, campos, columnaId, id, ParametroReposicion.COLUMNA_ORDER);
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
                pReposicion= new ParametroReposicion();
                pReposicion.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("NU_ORDEN_PARAMETRO"),rs.getString("TI_PARAMETRO"), rs.getString("CO_CODIGO"), rs.getString("NU_SECUENCIA")});    
                pReposicion.setCoCompania(rs.getString("CO_COMPANIA"));
                pReposicion.setCoLocal(rs.getString("CO_LOCAL"));
                pReposicion.setNuOrdenParametro(rs.getInt("NU_ORDEN_PARAMETRO"));
                pReposicion.setTiParametro(rs.getString("TI_PARAMETRO"));
                pReposicion.setCoCodigo(rs.getString("CO_CODIGO"));
                pReposicion.setNuSecuencia(rs.getInt("NU_SECUENCIA"));
                pReposicion.setNuMinimo(rs.getInt("NU_MINIMO"));
                pReposicion.setNuMaximo(rs.getInt("NU_MAXIMO"));
                pReposicion.setNuPeriodo(rs.getInt("NU_PERIODO"));
                pReposicion.setFeInicio(rs.getDate("FE_INICIO"));
                pReposicion.setFeFinal(rs.getDate("FE_FINAL"));
                pReposicion.setEsParametro(rs.getString("ES_PARAMETRO"));
                pReposicion.setIdCreaParametro(rs.getString("ID_CREA_PARAMETRO"));
                pReposicion.setFeCreaParametro(rs.getDate("FE_CREA_PARAMETRO"));
                pReposicion.setIdModParametro(rs.getString("ID_MOD_PARAMETRO"));
                pReposicion.setFeModParametro(rs.getDate("FE_MOD_PARAMETRO"));
                rgs.add(pReposicion);
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
        pReposicion = (ParametroReposicion)mdl;
        int gravado = 0;
        String campos = ParametroReposicion.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {pReposicion.getCoCompania(),
                            pReposicion.getCoLocal(),
                            pReposicion.getNuOrdenParametro(),
                            pReposicion.getTiParametro(),
                            pReposicion.getCoCodigo(),
                            pReposicion.getNuSecuencia(),
                            pReposicion.getNuMinimo(),
                            pReposicion.getNuMaximo(),
                            pReposicion.getNuPeriodo(),
                            pReposicion.getFeInicio(),
                            pReposicion.getFeFinal(),
                            pReposicion.getEsParametro(),
                            pReposicion.getIdCreaParametro(),
                            pReposicion.getFeCreaParametro(),
                            pReposicion.getIdModParametro(),
                            pReposicion.getFeModParametro()
                           };
       
           gravado = this.agregarRegistroPs(pReposicion.getNombreTabla(),ParametroReposicion.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        pReposicion = (ParametroReposicion)mdl;
        int gravado = 0;        
        
        Object[] valores = {pReposicion.getNuMinimo(),
                            pReposicion.getNuMaximo(),
                            pReposicion.getNuPeriodo(),
                            pReposicion.getFeInicio(),
                            pReposicion.getFeFinal(),
                            pReposicion.getEsParametro(),
                            pReposicion.getIdCreaParametro(),
                            pReposicion.getFeCreaParametro(),
                            pReposicion.getIdModParametro(),
                            pReposicion.getFeModParametro()
                           };

        gravado = this.actualizarRegistroPs(pReposicion.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(ParametroReposicion.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(ParametroReposicion.COLUMNA_PK, pReposicion.getPrimaryKey()) , valores);
        return gravado;
    }

    public ParametroReposicion getParametroReposicion() {
        if(pReposicion == null)
        {
            pReposicion = new ParametroReposicion();
        }
        return pReposicion;
    }

    public void setParametroReposicion(JAbstractModelBD prv) {
        this.pReposicion = (ParametroReposicion)prv;
    }

    public int getNuevoCodigo(String coCompania, String coLocal, int nuOrdenParametro, String tiParametro, String coCodigo){                                                  
        int Codigo=-1;

        try {
            return Integer.parseInt(AtuxDBUtility.getValueAt(ParametroReposicion.nt,"nvl(max(NU_SECUENCIA),0) + 1"," CO_COMPANIA = '" + coCompania + "' " 
                                                                                                                 + " and CO_LOCAL ='" + coLocal + "'"
                                                                                                                 + " and NU_ORDEN_PARAMETRO =" + nuOrdenParametro                                                                                                              + " and TI_PARAMETRO ='" + tiParametro + "'"
                                                                                                                 + " and CO_CODIGO ='" + coCodigo + "'"));
        } catch (SQLException ex) {
            Logger.getLogger(CParametroReposicion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo;
    }
}
