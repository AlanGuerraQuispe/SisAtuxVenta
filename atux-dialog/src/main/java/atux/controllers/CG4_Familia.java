package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.G4_Familia;
import atux.util.common.AtuxDBUtility;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CG4_Familia extends JAbstractController{
    private G4_Familia G4_gTerapeutico;
   
    
    @Override
    public ArrayList<G4_Familia> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{G4_Familia.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<G4_Familia> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<G4_Familia> getRegistros() {                 
        ArrayList<G4_Familia> rgs = new ArrayList<G4_Familia>();
        try {                        
//            rs = this.getRegistros(G4_Familia.nt,G4_Familia.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(G4_Familia.nt, G4_Familia.FULL_NOM_CAMPOS, null, null, G4_Familia.COLUMNA_ORDER);
            while(rs.next())
            {
                G4_gTerapeutico = new G4_Familia();                
                G4_gTerapeutico.setPrimaryKey(new String[]{rs.getString("CO_NIVEL_04"),rs.getString("CO_NIVEL_SUPERIOR")});
                G4_gTerapeutico.setCoGrupoTerapeutico(rs.getString("CO_GRUPO_TERAPEUTICO"));
                G4_gTerapeutico.setCoIms(rs.getString("CO_IMS"));
                G4_gTerapeutico.setDeGrupoTerapeutico(rs.getString("DE_GRUPO_TERAPEUTICO"));
                G4_gTerapeutico.setEsGrupoTerapeutico(rs.getString("ES_GRUPO_TERAPEUTICO"));
                G4_gTerapeutico.setIdCreaGrupoTerapeutico(rs.getString("ID_CREA_GRUPO_TERAPEUTICO"));
                G4_gTerapeutico.setFeCreaGrupoTerapeutico(rs.getDate("FE_CREA_GRUPO_TERAPEUTICO"));
                G4_gTerapeutico.setIdModGrupoTerapeutico(rs.getString("ID_MOD_GRUPO_TERAPEUTICO"));
                G4_gTerapeutico.setFeModGrupoTerapeutico(rs.getDate("FE_MOD_GRUPO_TERAPEUTICO"));
                G4_gTerapeutico.setCoNivel04(rs.getString("CO_NIVEL_04"));
                G4_gTerapeutico.setCoNivelSuperior(rs.getString("CO_NIVEL_SUPERIOR"));
                rgs.add(G4_gTerapeutico);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public G4_Familia getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(G4_Familia.nt, G4_Familia.FULL_NOM_CAMPOS, G4_Familia.COLUMNA_PK , id);
            while(rs.next())
            {
                G4_gTerapeutico = new G4_Familia();
                G4_gTerapeutico.setPrimaryKey(new String[]{rs.getString("CO_NIVEL_04"),rs.getString("CO_NIVEL_SUPERIOR")});
                G4_gTerapeutico.setCoGrupoTerapeutico(rs.getString("CO_GRUPO_TERAPEUTICO"));
                G4_gTerapeutico.setCoIms(rs.getString("CO_IMS"));
                G4_gTerapeutico.setDeGrupoTerapeutico(rs.getString("DE_GRUPO_TERAPEUTICO"));
                G4_gTerapeutico.setEsGrupoTerapeutico(rs.getString("ES_GRUPO_TERAPEUTICO"));
                G4_gTerapeutico.setIdCreaGrupoTerapeutico(rs.getString("ID_CREA_GRUPO_TERAPEUTICO"));
                G4_gTerapeutico.setFeCreaGrupoTerapeutico(rs.getDate("FE_CREA_GRUPO_TERAPEUTICO"));
                G4_gTerapeutico.setIdModGrupoTerapeutico(rs.getString("ID_MOD_GRUPO_TERAPEUTICO"));
                G4_gTerapeutico.setFeModGrupoTerapeutico(rs.getDate("FE_MOD_GRUPO_TERAPEUTICO"));
                G4_gTerapeutico.setCoNivel04(rs.getString("CO_NIVEL_04"));
                G4_gTerapeutico.setCoNivelSuperior(rs.getString("CO_NIVEL_SUPERIOR"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return G4_gTerapeutico;
    }
    
    @Override
    public ArrayList<G4_Familia> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<G4_Familia> rgs = new ArrayList<G4_Familia>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(G4_Familia.nt, G4_Familia.COLUMNA_ACTIVO, G4_Familia.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(G4_Familia.nt, G4_Familia.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(G4_Familia.nt, campos, columnaId, id, G4_Familia.COLUMNA_ORDER);
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
                G4_gTerapeutico= new G4_Familia();
                G4_gTerapeutico.setPrimaryKey(new String[]{rs.getString("CO_NIVEL_04"),rs.getString("CO_NIVEL_SUPERIOR")});
                G4_gTerapeutico.setCoGrupoTerapeutico(rs.getString("CO_GRUPO_TERAPEUTICO"));
                G4_gTerapeutico.setCoIms(rs.getString("CO_IMS"));
                G4_gTerapeutico.setDeGrupoTerapeutico(rs.getString("DE_GRUPO_TERAPEUTICO"));
                G4_gTerapeutico.setEsGrupoTerapeutico(rs.getString("ES_GRUPO_TERAPEUTICO"));
                G4_gTerapeutico.setIdCreaGrupoTerapeutico(rs.getString("ID_CREA_GRUPO_TERAPEUTICO"));
                G4_gTerapeutico.setFeCreaGrupoTerapeutico(rs.getDate("FE_CREA_GRUPO_TERAPEUTICO"));
                G4_gTerapeutico.setIdModGrupoTerapeutico(rs.getString("ID_MOD_GRUPO_TERAPEUTICO"));
                G4_gTerapeutico.setFeModGrupoTerapeutico(rs.getDate("FE_MOD_GRUPO_TERAPEUTICO"));
                G4_gTerapeutico.setCoNivel04(rs.getString("CO_NIVEL_04"));
                G4_gTerapeutico.setCoNivelSuperior(rs.getString("CO_NIVEL_SUPERIOR"));
                rgs.add(G4_gTerapeutico);
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
        G4_gTerapeutico = (G4_Familia)mdl;
        int gravado = 0;
        String campos = G4_Familia.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {G4_gTerapeutico.getCoGrupoTerapeutico(),
                            G4_gTerapeutico.getCoIms(),
                            G4_gTerapeutico.getDeGrupoTerapeutico(),
                            G4_gTerapeutico.getEsGrupoTerapeutico(),
                            G4_gTerapeutico.getIdCreaGrupoTerapeutico(),
                            G4_gTerapeutico.getFeCreaGrupoTerapeutico(),
                            G4_gTerapeutico.getIdModGrupoTerapeutico(),
                            G4_gTerapeutico.getFeModGrupoTerapeutico(),
                            G4_gTerapeutico.getCoNivel04(),
                            G4_gTerapeutico.getCoNivelSuperior()
                           };
       
           gravado = this.agregarRegistroPs(G4_gTerapeutico.getNombreTabla(),G4_Familia.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        G4_gTerapeutico = (G4_Familia)mdl;
        int gravado = 0;        
        
        Object[] valores = {G4_gTerapeutico.getCoGrupoTerapeutico(),
                            G4_gTerapeutico.getCoIms(),
                            G4_gTerapeutico.getDeGrupoTerapeutico(),
                            G4_gTerapeutico.getEsGrupoTerapeutico(),
                            G4_gTerapeutico.getIdCreaGrupoTerapeutico(),
                            G4_gTerapeutico.getFeCreaGrupoTerapeutico(),
                            G4_gTerapeutico.getIdModGrupoTerapeutico(),
                            G4_gTerapeutico.getFeModGrupoTerapeutico()
                           };

        gravado = this.actualizarRegistroPs(G4_gTerapeutico.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(G4_Familia.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(G4_Familia.COLUMNA_PK, G4_gTerapeutico.getPrimaryKey()) , valores);
        return gravado;
    }

    public G4_Familia getG4_Familia() {
        if(G4_gTerapeutico == null)
        {
            G4_gTerapeutico = new G4_Familia();
        }
        return G4_gTerapeutico;
    }

    public void setG4_Familia(JAbstractModelBD prv) {
        this.G4_gTerapeutico = (G4_Familia)prv;
    }

    public String getNuevoCodigoGrupo(){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(G4_Familia.nt,"rtrim(ltrim(to_char(max(CO_GRUPO_TERAPEUTICO) + 1,'0000')))"," CO_GRUPO_TERAPEUTICO is not null");
        } catch (SQLException ex) {
            Logger.getLogger(CG4_Familia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }
    public String getNuevoCodigoFamilia(String CodigoNivelSuperior){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(G4_Familia.nt,"'" + CodigoNivelSuperior + "' || trim(to_char(nvl(max(substr(co_nivel_04,6,2)),0) + 1,'00'))"," co_nivel_superior ='" + CodigoNivelSuperior + "'");
        } catch (SQLException ex) {
            Logger.getLogger(CG4_Familia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }    
}
