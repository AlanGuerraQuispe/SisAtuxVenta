package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.G3_SubDivision;
import atux.util.common.AtuxDBUtility;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CG3_SubDivision extends JAbstractController{
    private G3_SubDivision G3_gAnatomico;
   
    
    @Override
    public ArrayList<G3_SubDivision> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{G3_SubDivision.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<G3_SubDivision> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<G3_SubDivision> getRegistros() {                 
        ArrayList<G3_SubDivision> rgs = new ArrayList<G3_SubDivision>();
        try {                        
//            rs = this.getRegistros(G3_SubDivision.nt,G3_SubDivision.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(G3_SubDivision.nt, G3_SubDivision.FULL_NOM_CAMPOS, null, null, G3_SubDivision.COLUMNA_ORDER);
            while(rs.next())
            {
                G3_gAnatomico = new G3_SubDivision();                
                G3_gAnatomico.setPrimaryKey(new String[]{rs.getString("CO_NIVEL_03"),rs.getString("CO_NIVEL_SUPERIOR")});
                G3_gAnatomico.setCoGrupoAnatomico(rs.getString("CO_GRUPO_ANATOMICO"));
                G3_gAnatomico.setCoIms(rs.getString("CO_IMS"));
                G3_gAnatomico.setDeGrupoAnatomico(rs.getString("DE_GRUPO_ANATOMICO"));
                G3_gAnatomico.setEsGrupoAnatomico(rs.getString("ES_GRUPO_ANATOMICO"));
                G3_gAnatomico.setIdCreaGrupoAnatomico(rs.getString("ID_CREA_GRUPO_ANATOMICO"));
                G3_gAnatomico.setFeCreaGrupoAnatomico(rs.getDate("FE_CREA_GRUPO_ANATOMICO"));
                G3_gAnatomico.setIdModGrupoAnatomico(rs.getString("ID_MOD_GRUPO_ANATOMICO"));
                G3_gAnatomico.setFeModGrupoAnatomico(rs.getDate("FE_MOD_GRUPO_ANATOMICO"));
                G3_gAnatomico.setCoNivel03(rs.getString("CO_NIVEL_03"));
                G3_gAnatomico.setCoNivelSuperior(rs.getString("CO_NIVEL_SUPERIOR"));
                rgs.add(G3_gAnatomico);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public G3_SubDivision getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(G3_SubDivision.nt, G3_SubDivision.FULL_NOM_CAMPOS, G3_SubDivision.COLUMNA_PK , id);
            while(rs.next())
            {
                G3_gAnatomico = new G3_SubDivision();
                G3_gAnatomico.setPrimaryKey(new String[]{rs.getString("CO_NIVEL_03"),rs.getString("CO_NIVEL_SUPERIOR")});
                G3_gAnatomico.setCoGrupoAnatomico(rs.getString("CO_GRUPO_ANATOMICO"));
                G3_gAnatomico.setCoIms(rs.getString("CO_IMS"));
                G3_gAnatomico.setDeGrupoAnatomico(rs.getString("DE_GRUPO_ANATOMICO"));
                G3_gAnatomico.setEsGrupoAnatomico(rs.getString("ES_GRUPO_ANATOMICO"));
                G3_gAnatomico.setIdCreaGrupoAnatomico(rs.getString("ID_CREA_GRUPO_ANATOMICO"));
                G3_gAnatomico.setFeCreaGrupoAnatomico(rs.getDate("FE_CREA_GRUPO_ANATOMICO"));
                G3_gAnatomico.setIdModGrupoAnatomico(rs.getString("ID_MOD_GRUPO_ANATOMICO"));
                G3_gAnatomico.setFeModGrupoAnatomico(rs.getDate("FE_MOD_GRUPO_ANATOMICO"));
                G3_gAnatomico.setCoNivel03(rs.getString("CO_NIVEL_03"));
                G3_gAnatomico.setCoNivelSuperior(rs.getString("CO_NIVEL_SUPERIOR"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return G3_gAnatomico;
    }
    
    @Override
    public ArrayList<G3_SubDivision> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<G3_SubDivision> rgs = new ArrayList<G3_SubDivision>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(G3_SubDivision.nt, G3_SubDivision.COLUMNA_ACTIVO, G3_SubDivision.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(G3_SubDivision.nt, G3_SubDivision.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(G3_SubDivision.nt, campos, columnaId, id, G3_SubDivision.COLUMNA_ORDER);
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
                G3_gAnatomico= new G3_SubDivision();
                G3_gAnatomico.setPrimaryKey(new String[]{rs.getString("CO_NIVEL_03"),rs.getString("CO_NIVEL_SUPERIOR")});
                G3_gAnatomico.setCoGrupoAnatomico(rs.getString("CO_GRUPO_ANATOMICO"));
                G3_gAnatomico.setCoIms(rs.getString("CO_IMS"));
                G3_gAnatomico.setDeGrupoAnatomico(rs.getString("DE_GRUPO_ANATOMICO"));
                G3_gAnatomico.setEsGrupoAnatomico(rs.getString("ES_GRUPO_ANATOMICO"));
                G3_gAnatomico.setIdCreaGrupoAnatomico(rs.getString("ID_CREA_GRUPO_ANATOMICO"));
                G3_gAnatomico.setFeCreaGrupoAnatomico(rs.getDate("FE_CREA_GRUPO_ANATOMICO"));
                G3_gAnatomico.setIdModGrupoAnatomico(rs.getString("ID_MOD_GRUPO_ANATOMICO"));
                G3_gAnatomico.setFeModGrupoAnatomico(rs.getDate("FE_MOD_GRUPO_ANATOMICO"));
                G3_gAnatomico.setCoNivel03(rs.getString("CO_NIVEL_03"));
                G3_gAnatomico.setCoNivelSuperior(rs.getString("CO_NIVEL_SUPERIOR"));
                rgs.add(G3_gAnatomico);
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
        G3_gAnatomico = (G3_SubDivision)mdl;
        int gravado = 0;
        String campos = G3_SubDivision.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {G3_gAnatomico.getCoGrupoAnatomico(),
                            G3_gAnatomico.getCoIms(),
                            G3_gAnatomico.getDeGrupoAnatomico(),
                            G3_gAnatomico.getEsGrupoAnatomico(),
                            G3_gAnatomico.getIdCreaGrupoAnatomico(),
                            G3_gAnatomico.getFeCreaGrupoAnatomico(),
                            G3_gAnatomico.getIdModGrupoAnatomico(),
                            G3_gAnatomico.getFeModGrupoAnatomico(),
                            G3_gAnatomico.getCoNivel03(),
                            G3_gAnatomico.getCoNivelSuperior(),
                           };
       
           gravado = this.agregarRegistroPs(G3_gAnatomico.getNombreTabla(),G3_SubDivision.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        G3_gAnatomico = (G3_SubDivision)mdl;
        int gravado = 0;        
        
        Object[] valores = {G3_gAnatomico.getCoGrupoAnatomico(),
                            G3_gAnatomico.getCoIms(),
                            G3_gAnatomico.getDeGrupoAnatomico(),
                            G3_gAnatomico.getEsGrupoAnatomico(),
                            G3_gAnatomico.getIdCreaGrupoAnatomico(),
                            G3_gAnatomico.getFeCreaGrupoAnatomico(),
                            G3_gAnatomico.getIdModGrupoAnatomico(),
                            G3_gAnatomico.getFeModGrupoAnatomico(),
                            G3_gAnatomico.getCoNivel03(),
                            G3_gAnatomico.getCoNivelSuperior()
                           };

        gravado = this.actualizarRegistroPs(G3_gAnatomico.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(G3_SubDivision.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(G3_SubDivision.COLUMNA_PK, G3_gAnatomico.getPrimaryKey()) , valores);
        return gravado;
    }

    public G3_SubDivision getG3_SubDivision() {
        if(G3_gAnatomico == null)
        {
            G3_gAnatomico = new G3_SubDivision();
        }
        return G3_gAnatomico;
    }

    public void setG3_SubDivision(JAbstractModelBD prv) {
        this.G3_gAnatomico = (G3_SubDivision)prv;
    }

    public String getNuevoCodigoGrupo(){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(G3_SubDivision.nt,"rtrim(ltrim(to_char(max(CO_GRUPO_ANATOMICO) + 1,'0000')))"," CO_GRUPO_ANATOMICO is not null");
        } catch (SQLException ex) {
            Logger.getLogger(CG3_SubDivision.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }
    public String getNuevoCodigoSubDivision(String CodigoNivelSuperior){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(G3_SubDivision.nt,"'" + CodigoNivelSuperior + "' || trim(to_char(nvl(max(substr(co_nivel_03,4,2)),0) + 1,'00'))"," co_nivel_superior ='" + CodigoNivelSuperior + "'");
        } catch (SQLException ex) {
            Logger.getLogger(CG3_SubDivision.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }    
}
