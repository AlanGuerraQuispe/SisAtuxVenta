package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.G2_Division;
import atux.util.common.AtuxDBUtility;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CG2_Division extends JAbstractController{
    private G2_Division G2_Grupo;
   
    
    @Override
    public ArrayList<G2_Division> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{G2_Division.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<G2_Division> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<G2_Division> getRegistros() {                 
        ArrayList<G2_Division> rgs = new ArrayList<G2_Division>();
        try {                        
//            rs = this.getRegistros(G2_Division.nt,G2_Division.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(G2_Division.nt, G2_Division.FULL_NOM_CAMPOS, null, null, G2_Division.COLUMNA_ORDER);
            while(rs.next())
            {
                G2_Grupo = new G2_Division();                
                G2_Grupo.setPrimaryKey(new String[]{rs.getString("CO_NIVEL_02"),rs.getString("CO_NIVEL_SUPERIOR")});
                G2_Grupo.setCoGrupoProdErp(rs.getString("CO_GRUPO_PROD_ERP"));
                G2_Grupo.setDeGrupoProdErp(rs.getString("DE_GRUPO_PROD_ERP"));
                G2_Grupo.setEsGrupoProdErp(rs.getString("ES_GRUPO_PROD_ERP"));
                G2_Grupo.setIdCreaGrupoProdErp(rs.getString("ID_CREA_GRUPO_PROD_ERP"));
                G2_Grupo.setFeCreaGrupoProdErp(rs.getDate("FE_CREA_GRUPO_PROD_ERP"));
                G2_Grupo.setIdModGrupoProdErp(rs.getString("ID_MOD_GRUPO_PROD_ERP"));
                G2_Grupo.setFeModGrupoProdErp(rs.getDate("FE_MOD_GRUPO_PROD_ERP"));
                G2_Grupo.setCoNivel02(rs.getString("CO_NIVEL_02"));
                G2_Grupo.setCoNivelSuperior(rs.getString("CO_NIVEL_SUPERIOR"));
                rgs.add(G2_Grupo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public G2_Division getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(G2_Division.nt, G2_Division.FULL_NOM_CAMPOS, G2_Division.COLUMNA_PK , id);
            while(rs.next())
            {
                G2_Grupo = new G2_Division();
                G2_Grupo.setPrimaryKey(new String[]{rs.getString("CO_NIVEL_02"),rs.getString("CO_NIVEL_SUPERIOR")});
                G2_Grupo.setCoGrupoProdErp(rs.getString("CO_GRUPO_PROD_ERP"));
                G2_Grupo.setDeGrupoProdErp(rs.getString("DE_GRUPO_PROD_ERP"));
                G2_Grupo.setEsGrupoProdErp(rs.getString("ES_GRUPO_PROD_ERP"));
                G2_Grupo.setIdCreaGrupoProdErp(rs.getString("ID_CREA_GRUPO_PROD_ERP"));
                G2_Grupo.setFeCreaGrupoProdErp(rs.getDate("FE_CREA_GRUPO_PROD_ERP"));
                G2_Grupo.setIdModGrupoProdErp(rs.getString("ID_MOD_GRUPO_PROD_ERP"));
                G2_Grupo.setFeModGrupoProdErp(rs.getDate("FE_MOD_GRUPO_PROD_ERP"));
                G2_Grupo.setCoNivel02(rs.getString("CO_NIVEL_02"));
                G2_Grupo.setCoNivelSuperior(rs.getString("CO_NIVEL_SUPERIOR"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return G2_Grupo;
    }
    
    @Override
    public ArrayList<G2_Division> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<G2_Division> rgs = new ArrayList<G2_Division>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(G2_Division.nt, G2_Division.COLUMNA_ACTIVO, G2_Division.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(G2_Division.nt, G2_Division.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(G2_Division.nt, campos, columnaId, id, G2_Division.COLUMNA_ORDER);
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
                G2_Grupo= new G2_Division();
                G2_Grupo.setPrimaryKey(new String[]{rs.getString("CO_NIVEL_02"),rs.getString("CO_NIVEL_SUPERIOR")});
                G2_Grupo.setCoGrupoProdErp(rs.getString("CO_GRUPO_PROD_ERP"));
                G2_Grupo.setDeGrupoProdErp(rs.getString("DE_GRUPO_PROD_ERP"));
                G2_Grupo.setEsGrupoProdErp(rs.getString("ES_GRUPO_PROD_ERP"));
                G2_Grupo.setIdCreaGrupoProdErp(rs.getString("ID_CREA_GRUPO_PROD_ERP"));
                G2_Grupo.setFeCreaGrupoProdErp(rs.getDate("FE_CREA_GRUPO_PROD_ERP"));
                G2_Grupo.setIdModGrupoProdErp(rs.getString("ID_MOD_GRUPO_PROD_ERP"));
                G2_Grupo.setFeModGrupoProdErp(rs.getDate("FE_MOD_GRUPO_PROD_ERP"));
                G2_Grupo.setCoNivel02(rs.getString("CO_NIVEL_02"));
                G2_Grupo.setCoNivelSuperior(rs.getString("CO_NIVEL_SUPERIOR"));
                rgs.add(G2_Grupo);
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
        G2_Grupo = (G2_Division)mdl;
        int gravado = 0;
        String campos = G2_Division.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {G2_Grupo.getCoGrupoProdErp(),
                            G2_Grupo.getDeGrupoProdErp(),
                            G2_Grupo.getEsGrupoProdErp(),
                            G2_Grupo.getIdCreaGrupoProdErp(),
                            G2_Grupo.getFeCreaGrupoProdErp(),
                            G2_Grupo.getIdModGrupoProdErp(),
                            G2_Grupo.getFeModGrupoProdErp(),
                            G2_Grupo.getCoNivel02(),
                            G2_Grupo.getCoNivelSuperior()
                           };
       
           gravado = this.agregarRegistroPs(G2_Grupo.getNombreTabla(),G2_Division.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        G2_Grupo = (G2_Division)mdl;
        int gravado = 0;        
        
        Object[] valores = {G2_Grupo.getCoGrupoProdErp(),
                            G2_Grupo.getDeGrupoProdErp(),
                            G2_Grupo.getEsGrupoProdErp(),
                            G2_Grupo.getIdCreaGrupoProdErp(),
                            G2_Grupo.getFeCreaGrupoProdErp(),
                            G2_Grupo.getIdModGrupoProdErp(),
                            G2_Grupo.getFeModGrupoProdErp()
                           };

        gravado = this.actualizarRegistroPs(G2_Grupo.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(G2_Division.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(G2_Division.COLUMNA_PK, G2_Grupo.getPrimaryKey()) , valores);
        return gravado;
    }

    public G2_Division getG2_Division() {
        if(G2_Grupo == null)
        {
            G2_Grupo = new G2_Division();
        }
        return G2_Grupo;
    }

    public void setG2_Division(JAbstractModelBD prv) {
        this.G2_Grupo = (G2_Division)prv;
    }

    public String getNuevoCodigoGrupo(){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(G2_Division.nt,"rtrim(ltrim(to_char(max(CO_GRUPO_PROD_ERP) + 1,'000')))"," CO_GRUPO_PROD_ERP is not null");
        } catch (SQLException ex) {
            Logger.getLogger(CG2_Division.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }
    public String getNuevoCodigoDivision(String CodigoNivelSuperior){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(G2_Division.nt,"'" + CodigoNivelSuperior + "' || trim(to_char(max(nvl(substr(co_nivel_02,2,2),0)) + 1,'00'))"," co_nivel_superior ='" + CodigoNivelSuperior + "'");
        } catch (SQLException ex) {
            Logger.getLogger(CG2_Division.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }    
}
