package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.FormaPago;
import atux.modelbd.Laboratorio;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxVariables;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CLaboratorio extends JAbstractController{
    private Laboratorio lab;   
    private FormaPago formaPago; 
    
    @Override
    public ArrayList<Laboratorio> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{Laboratorio.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<Laboratorio> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<Laboratorio> getRegistros() {                 
        ArrayList<Laboratorio> rgs = new ArrayList<Laboratorio>();
        try {                        
            rs = this.getRegistros(Laboratorio.nt,Laboratorio.FULL_NOM_CAMPOS);                       
            while(rs.next())
            {
                lab = new Laboratorio();
                lab.setPrimaryKey(new String[]{rs.getString("Co_Compania"),rs.getString("co_laboratorio")});    
                lab.setCoCompania(rs.getString("Co_Compania"));
                lab.setCoLaboratorio(rs.getString("co_laboratorio"));
                lab.setDeAbrevLab(rs.getString("de_abrev_lab"));
                lab.setDeLaboratorio(rs.getString("de_laboratorio"));
                lab.setDeDirecLaboratorio(rs.getString("de_direc_laboratorio"));
                lab.setDeCiudad(rs.getString("de_ciudad"));
                lab.setNoEmailLab(rs.getString("no_email_lab"));
                lab.setNuTelefLaboratorio(rs.getString("nu_telef_laboratorio"));
                lab.setNuFaxLaboratorio(rs.getString("nu_fax_laboratorio"));
                lab.setNoContacto(rs.getString("no_contacto"));
                lab.setNuTelContacto(rs.getString("nu_tel_contacto"));
                lab.setNuMovilContacto(rs.getString("nu_movil_contacto"));
                lab.setNoEmailContacto(rs.getString("no_email_contacto"));
                lab.setInLabPropio(rs.getString("in_Lab_Propio"));
                lab.setEsLaboratorio(rs.getString("es_laboratorio"));
                lab.setInLabInventario(rs.getString("in_Lab_Inventario"));
                lab.setIdCreaLaboratorio(rs.getString("ID_CREA_LABORATORIO"));
                lab.setFeCreaLaboratorio(rs.getDate("FE_CREA_LABORATORIO"));
                lab.setIdModLaboratorio(rs.getString("ID_MOD_LABORATORIO"));
                lab.setFeModLaboratorio(rs.getDate("FE_MOD_LABORATORIO"));
                rgs.add(lab);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public Laboratorio getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(Laboratorio.nt, Laboratorio.FULL_NOM_CAMPOS, Laboratorio.COLUMNA_PK , id);
            while(rs.next())
            {
                lab = new Laboratorio();
                lab.setPrimaryKey(new String[]{rs.getString("Co_Compania"),rs.getString("co_laboratorio")});    
                lab.setCoCompania(rs.getString("Co_Compania"));
                lab.setCoLaboratorio(rs.getString("co_laboratorio"));
                lab.setDeAbrevLab(rs.getString("de_abrev_lab"));
                lab.setDeLaboratorio(rs.getString("de_laboratorio"));
                lab.setDeDirecLaboratorio(rs.getString("de_direc_laboratorio"));
                lab.setDeCiudad(rs.getString("de_ciudad"));
                lab.setNoEmailLab(rs.getString("no_email_lab"));
                lab.setNuTelefLaboratorio(rs.getString("nu_telef_laboratorio"));
                lab.setNuFaxLaboratorio(rs.getString("nu_fax_laboratorio"));
                lab.setNoContacto(rs.getString("no_contacto"));
                lab.setNuTelContacto(rs.getString("nu_tel_contacto"));
                lab.setNuMovilContacto(rs.getString("nu_movil_contacto"));
                lab.setNoEmailContacto(rs.getString("no_email_contacto"));
                lab.setInLabPropio(rs.getString("in_Lab_Propio"));
                lab.setEsLaboratorio(rs.getString("es_laboratorio"));
                lab.setInLabInventario(rs.getString("in_Lab_Inventario"));
                lab.setIdCreaLaboratorio(rs.getString("ID_CREA_LABORATORIO"));
                lab.setFeCreaLaboratorio(rs.getDate("FE_CREA_LABORATORIO"));
                lab.setIdModLaboratorio(rs.getString("ID_MOD_LABORATORIO"));
                lab.setFeModLaboratorio(rs.getDate("FE_MOD_LABORATORIO"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lab;
    }
    
    @Override
    public ArrayList<Laboratorio> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<Laboratorio> rgs = new ArrayList<Laboratorio>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(Laboratorio.nt, Laboratorio.COLUMNA_ACTIVO, Laboratorio.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(Laboratorio.nt, Laboratorio.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(Laboratorio.nt, campos, columnaId, id, columnaId);
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
                lab = new Laboratorio();
                lab.setPrimaryKey(new String[]{rs.getString("Co_Compania"),rs.getString("co_laboratorio")});    
                lab.setCoCompania(rs.getString("Co_Compania"));
                lab.setCoLaboratorio(rs.getString("co_laboratorio"));
                lab.setDeAbrevLab(rs.getString("de_abrev_lab"));
                lab.setDeLaboratorio(rs.getString("de_laboratorio"));
                lab.setDeDirecLaboratorio(rs.getString("de_direc_laboratorio"));
                lab.setDeCiudad(rs.getString("de_ciudad"));
                lab.setNoEmailLab(rs.getString("no_email_lab"));
                lab.setNuTelefLaboratorio(rs.getString("nu_telef_laboratorio"));
                lab.setNuFaxLaboratorio(rs.getString("nu_fax_laboratorio"));
                lab.setNoContacto(rs.getString("no_contacto"));
                lab.setNuTelContacto(rs.getString("nu_tel_contacto"));
                lab.setNuMovilContacto(rs.getString("nu_movil_contacto"));
                lab.setNoEmailContacto(rs.getString("no_email_contacto"));
                lab.setInLabPropio(rs.getString("in_Lab_Propio"));
                lab.setEsLaboratorio(rs.getString("es_laboratorio"));
                lab.setInLabInventario(rs.getString("in_Lab_Inventario"));
                lab.setIdCreaLaboratorio(rs.getString("ID_CREA_LABORATORIO"));
                lab.setFeCreaLaboratorio(rs.getDate("FE_CREA_LABORATORIO"));
                lab.setIdModLaboratorio(rs.getString("ID_MOD_LABORATORIO"));
                lab.setFeModLaboratorio(rs.getDate("FE_MOD_LABORATORIO"));
                rgs.add(lab);
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
        lab = (Laboratorio)mdl;
        int gravado = 0;
        String campos = Laboratorio.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {lab.getCoCompania(),
                            lab.getCoLaboratorio(),
                            lab.getDeAbrevLab(),
                            lab.getDeLaboratorio(),
                            lab.getDeDirecLaboratorio(),
                            lab.getDeCiudad(),
                            lab.getNoEmailLab(),
                            lab.getNuTelefLaboratorio(),
                            lab.getNuFaxLaboratorio(),
                            lab.getNoContacto(),
                            lab.getNuTelContacto(),
                            lab.getNuMovilContacto(),
                            lab.getNoEmailContacto(),
                            lab.getInLabPropio(),
                            lab.getEsLaboratorio(),
                            lab.getInLabInventario(),
                            lab.getIdCreaLaboratorio(),
                            lab.getFeCreaLaboratorio(),
                            lab.getIdModLaboratorio(),
                            lab.getFeModLaboratorio()
                           };
       
           gravado = this.agregarRegistroPs(lab.getNombreTabla(),Laboratorio.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        lab = (Laboratorio)mdl;
        int gravado = 0;        
        
        Object[] valores = {lab.getDeAbrevLab(),
                            lab.getDeLaboratorio(),
                            lab.getDeDirecLaboratorio(),
                            lab.getDeCiudad(),
                            lab.getNoEmailLab(),
                            lab.getNuTelefLaboratorio(),
                            lab.getNuFaxLaboratorio(),
                            lab.getNoContacto(),
                            lab.getNuTelContacto(),
                            lab.getNuMovilContacto(),
                            lab.getNoEmailContacto(),
                            lab.getInLabPropio(),
                            lab.getEsLaboratorio(),
                            lab.getInLabInventario(),
                            lab.getIdCreaLaboratorio(),
                            lab.getFeCreaLaboratorio(),
                            lab.getIdModLaboratorio(),
                            lab.getFeModLaboratorio()
                           };

        gravado = this.actualizarRegistroPs(lab.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(Laboratorio.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(Laboratorio.COLUMNA_PK, lab.getPrimaryKey()) , valores);
        return gravado;
    }

    public Laboratorio getLaboratorio() {
        if(lab == null)
        {
            lab = new Laboratorio();
        }
        return lab;
    }

    public void setLaboratorio(JAbstractModelBD prv) {
        this.lab = (Laboratorio)prv;
    }
    public String getNuevoCodigoLaboratorio(){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(Laboratorio.nt,"rtrim(ltrim(to_char(max(co_laboratorio) + 1,'0000')))"," co_compania = '" + AtuxVariables.vCodigoCompania + "'");
        } catch (SQLException ex) {
            Logger.getLogger(CLaboratorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }
}
