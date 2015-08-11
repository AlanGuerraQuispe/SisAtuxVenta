package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.Medicos;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxVariables;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CMedicos extends JAbstractController{
    private Medicos Med;
   
    
    @Override
    public ArrayList<Medicos> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{Medicos.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<Medicos> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       

    public ArrayList getBusquedaMedicos(String Filtro){
        ArrayList<Medicos> rgs = new ArrayList<Medicos>();
        Medicos      usr      = null;
        StringBuffer  query;
        try {            
            query = new StringBuffer();
            
            query.append("select * ");
            query.append("  from CMTM_MEDICO ");
            query.append(" WHERE CO_MEDICO is not null ");

            if ("A".equals(Filtro) || "I".equals(Filtro)){
                query.append("   AND ES_MEDICO = '").append(Filtro).append("' ");
            }else if (!"T".equals(Filtro)){
                query.append(Filtro);
            }
            
            query.append(" ORDER BY NO_MEDICO ");
            
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next()){
                Med = new Medicos();
                Med.setPrimaryKey(new String[]{rs.getString("CO_MEDICO")});    
                Med.setCoMedico(rs.getString("CO_MEDICO"));
                Med.setDeMedico(rs.getString("DE_MEDICO"));
                Med.setApPaternoMedico(rs.getString("AP_PATERNO_MEDICO"));
                Med.setApMaternoMedico(rs.getString("AP_MATERNO_MEDICO"));
                Med.setNoMedico(rs.getString("NO_MEDICO"));
                Med.setNuCmp(rs.getString("NU_CMP"));
                Med.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                Med.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                Med.setEsMedico(rs.getString("ES_MEDICO"));
                Med.setNuTelefono(rs.getString("NU_TELEFONO"));
                Med.setNoEmail(rs.getString("NO_EMAIL"));
                Med.setIdCreaMedico(rs.getString("ID_CREA_MEDICO"));
                Med.setFeCreaMedico(rs.getDate("FE_CREA_MEDICO"));
                Med.setIdModMedico(rs.getString("ID_MOD_MEDICO"));
                Med.setFeModMedico(rs.getDate("FE_MOD_MEDICO"));
                rgs.add(Med);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
        // Se cierran los recursos de base de datos.
        try {
        if (rs != null) {
            rs.close();
            }
        } catch (SQLException e) {
                System.out.println("No ha podido cerrar ResultSet.");
            }
        } 
        return rgs;
    }
   
    
    @Override
    public ArrayList<Medicos> getRegistros() {                 
        ArrayList<Medicos> rgs = new ArrayList<Medicos>();
        try {                        
//            rs = this.getRegistros(TipoDeCambio.nt,TipoDeCambio.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(Medicos.nt, Medicos.FULL_NOM_CAMPOS, null, null, Medicos.COLUMNA_ORDER);
            while(rs.next())
            {
                Med = new Medicos();
                Med.setPrimaryKey(new String[]{rs.getString("CO_MEDICO")});    
                Med.setCoMedico(rs.getString("CO_MEDICO"));
                Med.setDeMedico(rs.getString("DE_MEDICO"));
                Med.setApPaternoMedico(rs.getString("AP_PATERNO_MEDICO"));
                Med.setApMaternoMedico(rs.getString("AP_MATERNO_MEDICO"));
                Med.setNoMedico(rs.getString("NO_MEDICO"));
                Med.setNuCmp(rs.getString("NU_CMP"));
                Med.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                Med.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                Med.setEsMedico(rs.getString("ES_MEDICO"));
                Med.setNuTelefono(rs.getString("NU_TELEFONO"));
                Med.setNoEmail(rs.getString("NO_EMAIL"));
                Med.setIdCreaMedico(rs.getString("ID_CREA_MEDICO"));
                Med.setFeCreaMedico(rs.getDate("FE_CREA_MEDICO"));
                Med.setIdModMedico(rs.getString("ID_MOD_MEDICO"));
                Med.setFeModMedico(rs.getDate("FE_MOD_MEDICO"));
                rgs.add(Med);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public Medicos getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(Medicos.nt, Medicos.FULL_NOM_CAMPOS, Medicos.COLUMNA_PK , id);
            while(rs.next())
            {
                Med.setPrimaryKey(new String[]{rs.getString("CO_MEDICO")});    
                Med.setCoMedico(rs.getString("CO_MEDICO"));
                Med.setDeMedico(rs.getString("DE_MEDICO"));
                Med.setApPaternoMedico(rs.getString("AP_PATERNO_MEDICO"));
                Med.setApMaternoMedico(rs.getString("AP_MATERNO_MEDICO"));
                Med.setNoMedico(rs.getString("NO_MEDICO"));
                Med.setNuCmp(rs.getString("NU_CMP"));
                Med.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                Med.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                Med.setEsMedico(rs.getString("ES_MEDICO"));
                Med.setNuTelefono(rs.getString("NU_TELEFONO"));
                Med.setNoEmail(rs.getString("NO_EMAIL"));
                Med.setIdCreaMedico(rs.getString("ID_CREA_MEDICO"));
                Med.setFeCreaMedico(rs.getDate("FE_CREA_MEDICO"));
                Med.setIdModMedico(rs.getString("ID_MOD_MEDICO"));
                Med.setFeModMedico(rs.getDate("FE_MOD_MEDICO"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Med;
    }
    
    @Override
    public ArrayList<Medicos> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<Medicos> rgs = new ArrayList<Medicos>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(Medicos.nt, Medicos.COLUMNA_ACTIVO, Medicos.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(Medicos.nt, Medicos.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(Medicos.nt, campos, columnaId, id, Medicos.COLUMNA_ORDER);
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
                Med= new Medicos();
                Med.setPrimaryKey(new String[]{rs.getString("CO_MEDICO")});    
                Med.setCoMedico(rs.getString("CO_MEDICO"));
                Med.setDeMedico(rs.getString("DE_MEDICO"));
                Med.setApPaternoMedico(rs.getString("AP_PATERNO_MEDICO"));
                Med.setApMaternoMedico(rs.getString("AP_MATERNO_MEDICO"));
                Med.setNoMedico(rs.getString("NO_MEDICO"));
                Med.setNuCmp(rs.getString("NU_CMP"));
                Med.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                Med.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                Med.setEsMedico(rs.getString("ES_MEDICO"));
                Med.setNuTelefono(rs.getString("NU_TELEFONO"));
                Med.setNoEmail(rs.getString("NO_EMAIL"));
                Med.setIdCreaMedico(rs.getString("ID_CREA_MEDICO"));
                Med.setFeCreaMedico(rs.getDate("FE_CREA_MEDICO"));
                Med.setIdModMedico(rs.getString("ID_MOD_MEDICO"));
                Med.setFeModMedico(rs.getDate("FE_MOD_MEDICO"));
                rgs.add(Med);
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
        Med = (Medicos)mdl;
        int gravado = 0;
        String campos = Medicos.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {Med.getCoMedico(),
                            Med.getDeMedico(),
                            Med.getApPaternoMedico(),
                            Med.getApMaternoMedico(),
                            Med.getNoMedico(),
                            Med.getNuCmp(),
                            Med.getTiDocIdentidad(),
                            Med.getNuDocIdentidad(),
                            Med.getEsMedico(),
                            Med.getNuTelefono(),
                            Med.getNoEmail(),
                            Med.getIdCreaMedico(),
                            Med.getFeCreaMedico(),
                            Med.getIdModMedico(),
                            Med.getFeModMedico()
                           };
       
           gravado = this.agregarRegistroPs(Med.getNombreTabla(),Medicos.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        Med = (Medicos)mdl;
        int gravado = 0;        
        
        Object[] valores = {Med.getDeMedico(),
                            Med.getApPaternoMedico(),
                            Med.getApMaternoMedico(),
                            Med.getNoMedico(),
                            Med.getNuCmp(),
                            Med.getTiDocIdentidad(),
                            Med.getNuDocIdentidad(),
                            Med.getEsMedico(),
                            Med.getNuTelefono(),
                            Med.getNoEmail(),
                            Med.getIdCreaMedico(),
                            Med.getFeCreaMedico(),
                            Med.getIdModMedico(),
                            Med.getFeModMedico()
                           };

        gravado = this.actualizarRegistroPs(Med.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(Medicos.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(Medicos.COLUMNA_PK, Med.getPrimaryKey()) , valores);
        return gravado;
    }

    public Medicos getMedicos() {
        if(Med == null)
        {
            Med = new Medicos();
        }
        return Med;
    }

    public void setMedicos(JAbstractModelBD prv) {
        this.Med = (Medicos)prv;
    }

    public String getNuevoCodigo(){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(Medicos.nt,"rtrim(ltrim(to_char(max(CO_MEDICO) + 1,'000000')))"," co_medico is not null");
        } catch (SQLException ex) {
            Logger.getLogger(CMedicos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }
}
