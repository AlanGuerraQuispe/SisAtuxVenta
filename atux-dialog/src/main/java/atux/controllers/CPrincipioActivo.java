package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.PrincipioActivo;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxVariables;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CPrincipioActivo extends JAbstractController{
    private PrincipioActivo pActivo;
   
    
    @Override
    public ArrayList<PrincipioActivo> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{PrincipioActivo.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<PrincipioActivo> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<PrincipioActivo> getRegistros() {                 
        ArrayList<PrincipioActivo> rgs = new ArrayList<PrincipioActivo>();
        try {                        
//            rs = this.getRegistros(TipoDeCambio.nt,TipoDeCambio.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(PrincipioActivo.nt, PrincipioActivo.FULL_NOM_CAMPOS, null, null, PrincipioActivo.COLUMNA_ORDER);
            while(rs.next())
            {
                pActivo = new PrincipioActivo();
                pActivo.setPrimaryKey(new String[]{rs.getString("CO_PRINCIPIO_ACTIVO")});    
                pActivo.setCoPrincipioActivo(rs.getString("CO_PRINCIPIO_ACTIVO"));
                pActivo.setDePrincipioActivo(rs.getString("DE_PRINCIPIO_ACTIVO"));
                pActivo.setEsPrincipioActivo(rs.getString("ES_PRINCIPIO_ACTIVO"));
                pActivo.setIdCreaPrincipioActivo(rs.getString("ID_CREA_PRINCIPIO_ACTIVO"));
                pActivo.setFeCreaPrincipioActivo(rs.getDate("FE_CREA_PRINCIPIO_ACTIVO"));
                pActivo.setIdModPrincipioActivo(rs.getString("ID_MOD_PRINCIPIO_ACTIVO"));
                pActivo.setFeModPrincipioActivo(rs.getDate("FE_MOD_PRINCIPIO_ACTIVO"));
                rgs.add(pActivo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public PrincipioActivo getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(PrincipioActivo.nt, PrincipioActivo.FULL_NOM_CAMPOS, PrincipioActivo.COLUMNA_PK , id);
            while(rs.next())
            {
                pActivo.setPrimaryKey(new String[]{rs.getString("CO_PRINCIPIO_ACTIVO")});    
                pActivo.setCoPrincipioActivo(rs.getString("CO_PRINCIPIO_ACTIVO"));
                pActivo.setDePrincipioActivo(rs.getString("DE_PRINCIPIO_ACTIVO"));
                pActivo.setEsPrincipioActivo(rs.getString("ES_PRINCIPIO_ACTIVO"));
                pActivo.setIdCreaPrincipioActivo(rs.getString("ID_CREA_PRINCIPIO_ACTIVO"));
                pActivo.setFeCreaPrincipioActivo(rs.getDate("FE_CREA_PRINCIPIO_ACTIVO"));
                pActivo.setIdModPrincipioActivo(rs.getString("ID_MOD_PRINCIPIO_ACTIVO"));
                pActivo.setFeModPrincipioActivo(rs.getDate("FE_MOD_PRINCIPIO_ACTIVO"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pActivo;
    }
    
    @Override
    public ArrayList<PrincipioActivo> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<PrincipioActivo> rgs = new ArrayList<PrincipioActivo>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(PrincipioActivo.nt, PrincipioActivo.COLUMNA_ACTIVO, PrincipioActivo.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(PrincipioActivo.nt, PrincipioActivo.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(PrincipioActivo.nt, campos, columnaId, id, PrincipioActivo.COLUMNA_ORDER);
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
                pActivo = new PrincipioActivo();
                pActivo.setPrimaryKey(new String[]{rs.getString("CO_PRINCIPIO_ACTIVO")});    
                pActivo.setCoPrincipioActivo(rs.getString("CO_PRINCIPIO_ACTIVO"));
                pActivo.setDePrincipioActivo(rs.getString("DE_PRINCIPIO_ACTIVO"));
                pActivo.setEsPrincipioActivo(rs.getString("ES_PRINCIPIO_ACTIVO"));
                pActivo.setIdCreaPrincipioActivo(rs.getString("ID_CREA_PRINCIPIO_ACTIVO"));
                pActivo.setFeCreaPrincipioActivo(rs.getDate("FE_CREA_PRINCIPIO_ACTIVO"));
                pActivo.setIdModPrincipioActivo(rs.getString("ID_MOD_PRINCIPIO_ACTIVO"));
                pActivo.setFeModPrincipioActivo(rs.getDate("FE_MOD_PRINCIPIO_ACTIVO"));
                rgs.add(pActivo);
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
        pActivo = (PrincipioActivo)mdl;
        int gravado = 0;
        String campos = PrincipioActivo.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {pActivo.getCoPrincipioActivo(),
                            pActivo.getDePrincipioActivo(),
                            pActivo.getEsPrincipioActivo(),
                            pActivo.getIdCreaPrincipioActivo(),
                            pActivo.getFeCreaPrincipioActivo(),
                            pActivo.getIdModPrincipioActivo(),
                            pActivo.getFeModPrincipioActivo()
                           };
       
           gravado = this.agregarRegistroPs(pActivo.getNombreTabla(),PrincipioActivo.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        pActivo = (PrincipioActivo)mdl;
        int gravado = 0;        
        
        Object[] valores = {pActivo.getDePrincipioActivo(),
                            pActivo.getEsPrincipioActivo(),
                            pActivo.getIdCreaPrincipioActivo(),
                            pActivo.getFeCreaPrincipioActivo(),
                            pActivo.getIdModPrincipioActivo(),
                            pActivo.getFeModPrincipioActivo()
                           };

        gravado = this.actualizarRegistroPs(pActivo.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(PrincipioActivo.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(PrincipioActivo.COLUMNA_PK, pActivo.getPrimaryKey()) , valores);
        return gravado;
    }

    public PrincipioActivo getPrincipioActivo() {
        if(pActivo == null)
        {
            pActivo = new PrincipioActivo();
        }
        return pActivo;
    }

    public void setPrincipioActivo(JAbstractModelBD prv) {
        this.pActivo = (PrincipioActivo)prv;
    }

    public String getNuevoCodigo(){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(PrincipioActivo.nt,"rtrim(ltrim(to_char(max(CO_PRINCIPIO_ACTIVO) + 1,'00000')))"," co_principio_activo is not null");
        } catch (SQLException ex) {
            Logger.getLogger(CPrincipioActivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }
}
