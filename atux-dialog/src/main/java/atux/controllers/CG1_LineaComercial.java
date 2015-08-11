package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.G1_LineaComercial;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxVariables;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CG1_LineaComercial extends JAbstractController{
    private G1_LineaComercial G1_LComercial;
   
    
    @Override
    public ArrayList<G1_LineaComercial> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{G1_LineaComercial.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<G1_LineaComercial> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<G1_LineaComercial> getRegistros() {                 
        ArrayList<G1_LineaComercial> rgs = new ArrayList<G1_LineaComercial>();
        try {                        
//            rs = this.getRegistros(G1_LineaComercial.nt,G1_LineaComercial.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(G1_LineaComercial.nt, G1_LineaComercial.FULL_NOM_CAMPOS, null, null, G1_LineaComercial.COLUMNA_ORDER);
            while(rs.next())
            {
                G1_LComercial = new G1_LineaComercial();                
                G1_LComercial.setPrimaryKey(new String[]{rs.getString("CO_NIVEL_01"),rs.getString("CO_NIVEL_SUPERIOR")});
                G1_LComercial.setCoLineaProdErp(rs.getString("CO_LINEA_PROD_ERP"));
                G1_LComercial.setDeLineaProdErp(rs.getString("DE_LINEA_PROD_ERP"));
                G1_LComercial.setEsLineaProdErp(rs.getString("ES_LINEA_PROD_ERP"));
                G1_LComercial.setIdCreaLineaProdErp(rs.getString("ID_CREA_LINEA_PROD_ERP"));
                G1_LComercial.setFeCreaLineaProdErp(rs.getDate("FE_CREA_LINEA_PROD_ERP"));
                G1_LComercial.setIdModLineaProdErp(rs.getString("ID_MOD_LINEA_PROD_ERP"));
                G1_LComercial.setFeModLineaProdErp(rs.getDate("FE_MOD_LINEA_PROD_ERP"));
                G1_LComercial.setCoNivel01(rs.getString("CO_NIVEL_01"));
                G1_LComercial.setCoNivelSuperior(rs.getString("CO_NIVEL_SUPERIOR"));
                rgs.add(G1_LComercial);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public G1_LineaComercial getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(G1_LineaComercial.nt, G1_LineaComercial.FULL_NOM_CAMPOS, G1_LineaComercial.COLUMNA_PK , id);
            while(rs.next())
            {
                G1_LComercial = new G1_LineaComercial();
                G1_LComercial.setPrimaryKey(new String[]{rs.getString("CO_NIVEL_01"),rs.getString("CO_NIVEL_SUPERIOR")});
                G1_LComercial.setCoLineaProdErp(rs.getString("CO_LINEA_PROD_ERP"));
                G1_LComercial.setDeLineaProdErp(rs.getString("DE_LINEA_PROD_ERP"));
                G1_LComercial.setEsLineaProdErp(rs.getString("ES_LINEA_PROD_ERP"));
                G1_LComercial.setIdCreaLineaProdErp(rs.getString("ID_CREA_LINEA_PROD_ERP"));
                G1_LComercial.setFeCreaLineaProdErp(rs.getDate("FE_CREA_LINEA_PROD_ERP"));
                G1_LComercial.setIdModLineaProdErp(rs.getString("ID_MOD_LINEA_PROD_ERP"));
                G1_LComercial.setFeModLineaProdErp(rs.getDate("FE_MOD_LINEA_PROD_ERP"));
                G1_LComercial.setCoNivel01(rs.getString("CO_NIVEL_01"));
                G1_LComercial.setCoNivelSuperior(rs.getString("CO_NIVEL_SUPERIOR"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return G1_LComercial;
    }
    
    @Override
    public ArrayList<G1_LineaComercial> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<G1_LineaComercial> rgs = new ArrayList<G1_LineaComercial>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(G1_LineaComercial.nt, G1_LineaComercial.COLUMNA_ACTIVO, G1_LineaComercial.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(G1_LineaComercial.nt, G1_LineaComercial.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(G1_LineaComercial.nt, campos, columnaId, id, G1_LineaComercial.COLUMNA_ORDER);
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
                G1_LComercial= new G1_LineaComercial();
                G1_LComercial.setPrimaryKey(new String[]{rs.getString("CO_NIVEL_01"),rs.getString("CO_NIVEL_SUPERIOR")});
                G1_LComercial.setCoLineaProdErp(rs.getString("CO_LINEA_PROD_ERP"));
                G1_LComercial.setDeLineaProdErp(rs.getString("DE_LINEA_PROD_ERP"));
                G1_LComercial.setEsLineaProdErp(rs.getString("ES_LINEA_PROD_ERP"));
                G1_LComercial.setIdCreaLineaProdErp(rs.getString("ID_CREA_LINEA_PROD_ERP"));
                G1_LComercial.setFeCreaLineaProdErp(rs.getDate("FE_CREA_LINEA_PROD_ERP"));
                G1_LComercial.setIdModLineaProdErp(rs.getString("ID_MOD_LINEA_PROD_ERP"));
                G1_LComercial.setFeModLineaProdErp(rs.getDate("FE_MOD_LINEA_PROD_ERP"));
                G1_LComercial.setCoNivel01(rs.getString("CO_NIVEL_01"));
                G1_LComercial.setCoNivelSuperior(rs.getString("CO_NIVEL_SUPERIOR"));
                rgs.add(G1_LComercial);
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
        G1_LComercial = (G1_LineaComercial)mdl;
        int gravado = 0;
        String campos = G1_LineaComercial.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {G1_LComercial.getCoLineaProdErp(),
                            G1_LComercial.getDeLineaProdErp(),
                            G1_LComercial.getEsLineaProdErp(),
                            G1_LComercial.getIdCreaLineaProdErp(),
                            G1_LComercial.getFeCreaLineaProdErp(),
                            G1_LComercial.getIdModLineaProdErp(),
                            G1_LComercial.getFeModLineaProdErp(),
                            G1_LComercial.getCoNivel01(),
                            G1_LComercial.getCoNivelSuperior()
                           };
       
           gravado = this.agregarRegistroPs(G1_LComercial.getNombreTabla(),G1_LineaComercial.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        G1_LComercial = (G1_LineaComercial)mdl;
        int gravado = 0;        
        
        Object[] valores = {G1_LComercial.getCoLineaProdErp(),
                            G1_LComercial.getDeLineaProdErp(),
                            G1_LComercial.getEsLineaProdErp(),
                            G1_LComercial.getIdCreaLineaProdErp(),
                            G1_LComercial.getFeCreaLineaProdErp(),
                            G1_LComercial.getIdModLineaProdErp(),
                            G1_LComercial.getFeModLineaProdErp()
                           };

        gravado = this.actualizarRegistroPs(G1_LComercial.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(G1_LineaComercial.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(G1_LineaComercial.COLUMNA_PK, G1_LComercial.getPrimaryKey()) , valores);
        return gravado;
    }

    public G1_LineaComercial getG1_LineaComercial() {
        if(G1_LComercial == null)
        {
            G1_LComercial = new G1_LineaComercial();
        }
        return G1_LComercial;
    }

    public void setG1_LineaComercial(JAbstractModelBD prv) {
        this.G1_LComercial = (G1_LineaComercial)prv;
    }

    public String getNuevoCodigoLinea(){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(G1_LineaComercial.nt,"rtrim(ltrim(to_char(max(CO_LINEA_PROD_ERP) + 1,'000')))"," CO_LINEA_PROD_ERP is not null");
        } catch (SQLException ex) {
            Logger.getLogger(CG1_LineaComercial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }
}
