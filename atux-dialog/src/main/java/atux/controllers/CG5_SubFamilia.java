package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.G5_SubFamilia;
import atux.util.common.AtuxDBUtility;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CG5_SubFamilia extends JAbstractController{
    private G5_SubFamilia G5_aTerapeutica;
   
    
    @Override
    public ArrayList<G5_SubFamilia> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{G5_SubFamilia.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<G5_SubFamilia> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<G5_SubFamilia> getRegistros() {                 
        ArrayList<G5_SubFamilia> rgs = new ArrayList<G5_SubFamilia>();
        try {                        
//            rs = this.getRegistros(G5_SubFamilia.nt,G5_SubFamilia.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(G5_SubFamilia.nt, G5_SubFamilia.FULL_NOM_CAMPOS, null, null, G5_SubFamilia.COLUMNA_ORDER);
            while(rs.next())
            {
                G5_aTerapeutica = new G5_SubFamilia();                
                G5_aTerapeutica.setPrimaryKey(new String[]{rs.getString("CO_NIVEL_05"),rs.getString("CO_NIVEL_SUPERIOR")});
                G5_aTerapeutica.setCoAccionTerapeutica(rs.getString("CO_ACCION_TERAPEUTICA"));
                G5_aTerapeutica.setCoIms(rs.getString("CO_IMS"));
                G5_aTerapeutica.setDeAccionTerapeutica(rs.getString("DE_ACCION_TERAPEUTICA"));
                G5_aTerapeutica.setEsAccionTerapeutica(rs.getString("ES_ACCION_TERAPEUTICA"));
                G5_aTerapeutica.setIdCreaAccionTerapeutica(rs.getString("ID_CREA_ACCION_TERAPEUTICA"));
                G5_aTerapeutica.setFeCreaAccionTerapeutica(rs.getDate("FE_CREA_ACCION_TERAPEUTICA"));
                G5_aTerapeutica.setIdModAccionTerapeutica(rs.getString("ID_MOD_ACCION_TERAPEUTICA"));
                G5_aTerapeutica.setFeModAccionTerapeutica(rs.getDate("FE_MOD_ACCION_TERAPEUTICA"));
                G5_aTerapeutica.setCoNivel05(rs.getString("CO_NIVEL_05"));
                G5_aTerapeutica.setCoNivelSuperior(rs.getString("CO_NIVEL_SUPERIOR"));
                rgs.add(G5_aTerapeutica);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public G5_SubFamilia getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(G5_SubFamilia.nt, G5_SubFamilia.FULL_NOM_CAMPOS, G5_SubFamilia.COLUMNA_PK , id);
            while(rs.next())
            {
                G5_aTerapeutica = new G5_SubFamilia();
                G5_aTerapeutica.setPrimaryKey(new String[]{rs.getString("CO_NIVEL_05"),rs.getString("CO_NIVEL_SUPERIOR")});
                G5_aTerapeutica.setCoAccionTerapeutica(rs.getString("CO_ACCION_TERAPEUTICA"));
                G5_aTerapeutica.setCoIms(rs.getString("CO_IMS"));
                G5_aTerapeutica.setDeAccionTerapeutica(rs.getString("DE_ACCION_TERAPEUTICA"));
                G5_aTerapeutica.setEsAccionTerapeutica(rs.getString("ES_ACCION_TERAPEUTICA"));
                G5_aTerapeutica.setIdCreaAccionTerapeutica(rs.getString("ID_CREA_ACCION_TERAPEUTICA"));
                G5_aTerapeutica.setFeCreaAccionTerapeutica(rs.getDate("FE_CREA_ACCION_TERAPEUTICA"));
                G5_aTerapeutica.setIdModAccionTerapeutica(rs.getString("ID_MOD_ACCION_TERAPEUTICA"));
                G5_aTerapeutica.setFeModAccionTerapeutica(rs.getDate("FE_MOD_ACCION_TERAPEUTICA"));
                G5_aTerapeutica.setCoNivel05(rs.getString("CO_NIVEL_05"));
                G5_aTerapeutica.setCoNivelSuperior(rs.getString("CO_NIVEL_SUPERIOR"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return G5_aTerapeutica;
    }
    
    @Override
    public ArrayList<G5_SubFamilia> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<G5_SubFamilia> rgs = new ArrayList<G5_SubFamilia>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(G5_SubFamilia.nt, G5_SubFamilia.COLUMNA_ACTIVO, G5_SubFamilia.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(G5_SubFamilia.nt, G5_SubFamilia.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(G5_SubFamilia.nt, campos, columnaId, id, G5_SubFamilia.COLUMNA_ORDER);
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
                G5_aTerapeutica= new G5_SubFamilia();
                G5_aTerapeutica.setPrimaryKey(new String[]{rs.getString("CO_NIVEL_05"),rs.getString("CO_NIVEL_SUPERIOR")});
                G5_aTerapeutica.setCoAccionTerapeutica(rs.getString("CO_ACCION_TERAPEUTICA"));
                G5_aTerapeutica.setCoIms(rs.getString("CO_IMS"));
                G5_aTerapeutica.setDeAccionTerapeutica(rs.getString("DE_ACCION_TERAPEUTICA"));
                G5_aTerapeutica.setEsAccionTerapeutica(rs.getString("ES_ACCION_TERAPEUTICA"));
                G5_aTerapeutica.setIdCreaAccionTerapeutica(rs.getString("ID_CREA_ACCION_TERAPEUTICA"));
                G5_aTerapeutica.setFeCreaAccionTerapeutica(rs.getDate("FE_CREA_ACCION_TERAPEUTICA"));
                G5_aTerapeutica.setIdModAccionTerapeutica(rs.getString("ID_MOD_ACCION_TERAPEUTICA"));
                G5_aTerapeutica.setFeModAccionTerapeutica(rs.getDate("FE_MOD_ACCION_TERAPEUTICA"));
                G5_aTerapeutica.setCoNivel05(rs.getString("CO_NIVEL_05"));
                G5_aTerapeutica.setCoNivelSuperior(rs.getString("CO_NIVEL_SUPERIOR"));
                rgs.add(G5_aTerapeutica);
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
        G5_aTerapeutica = (G5_SubFamilia)mdl;
        int gravado = 0;
        String campos = G5_SubFamilia.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {G5_aTerapeutica.getCoAccionTerapeutica(),
                            G5_aTerapeutica.getCoIms(),
                            G5_aTerapeutica.getDeAccionTerapeutica(),
                            G5_aTerapeutica.getEsAccionTerapeutica(),
                            G5_aTerapeutica.getIdCreaAccionTerapeutica(),
                            G5_aTerapeutica.getFeCreaAccionTerapeutica(),
                            G5_aTerapeutica.getIdModAccionTerapeutica(),
                            G5_aTerapeutica.getFeModAccionTerapeutica(),
                            G5_aTerapeutica.getCoNivel05(),
                            G5_aTerapeutica.getCoNivelSuperior()
                           };
       
           gravado = this.agregarRegistroPs(G5_aTerapeutica.getNombreTabla(),G5_SubFamilia.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        G5_aTerapeutica = (G5_SubFamilia)mdl;
        int gravado = 0;        
        
        Object[] valores = {G5_aTerapeutica.getCoAccionTerapeutica(),
                            G5_aTerapeutica.getCoIms(),
                            G5_aTerapeutica.getDeAccionTerapeutica(),
                            G5_aTerapeutica.getEsAccionTerapeutica(),
                            G5_aTerapeutica.getIdCreaAccionTerapeutica(),
                            G5_aTerapeutica.getFeCreaAccionTerapeutica(),
                            G5_aTerapeutica.getIdModAccionTerapeutica(),
                            G5_aTerapeutica.getFeModAccionTerapeutica()
                           };

        gravado = this.actualizarRegistroPs(G5_aTerapeutica.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(G5_SubFamilia.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(G5_SubFamilia.COLUMNA_PK, G5_aTerapeutica.getPrimaryKey()) , valores);
        return gravado;
    }

    public G5_SubFamilia getG5_SubFamilia() {
        if(G5_aTerapeutica == null)
        {
            G5_aTerapeutica = new G5_SubFamilia();
        }
        return G5_aTerapeutica;
    }

    public void setG5_SubFamilia(JAbstractModelBD prv) {
        this.G5_aTerapeutica = (G5_SubFamilia)prv;
    }

    public String getNuevoCodigoGrupo(){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(G5_SubFamilia.nt,"rtrim(ltrim(to_char(max(CO_ACCION_TERAPEUTICA) + 1,'0000')))"," CO_ACCION_TERAPEUTICA is not null");
        } catch (SQLException ex) {
            Logger.getLogger(CG5_SubFamilia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }
    public String getNuevoCodigoSubFamilia(String CodigoNivelSuperior){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(G5_SubFamilia.nt,"'" + CodigoNivelSuperior + "' || trim(to_char(nvl(max(substr(co_nivel_05,8,2)),0) + 1,'00'))"," co_nivel_superior ='" + CodigoNivelSuperior + "'");
        } catch (SQLException ex) {
            Logger.getLogger(CG5_SubFamilia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }    
}
