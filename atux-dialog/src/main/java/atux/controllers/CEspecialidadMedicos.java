package atux.controllers;

import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.EspecialidadMedicos;
import java.sql.SQLException;
import java.util.ArrayList;


public class CEspecialidadMedicos extends JAbstractController{
    private EspecialidadMedicos Espec;
   
    
    @Override
    public ArrayList<EspecialidadMedicos> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{EspecialidadMedicos.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<EspecialidadMedicos> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<EspecialidadMedicos> getRegistros() {                 
        ArrayList<EspecialidadMedicos> rgs = new ArrayList<EspecialidadMedicos>();
        try {                        
//            rs = this.getRegistros(TipoDeCambio.nt,TipoDeCambio.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(EspecialidadMedicos.nt, EspecialidadMedicos.FULL_NOM_CAMPOS, null, null, EspecialidadMedicos.COLUMNA_ORDER);
            while(rs.next())
            {
                Espec = new EspecialidadMedicos();
                Espec.setCoEspecialidad(rs.getString("CO_ESPECIALIDAD"));
                Espec.setDeEspecialidad(rs.getString("DE_ESPECIALIDAD"));
                Espec.setIdCreaEsp(rs.getString("ID_CREA_ESP"));
                Espec.setFeCreaEsp(rs.getDate("FE_CREA_ESP"));
                Espec.setIdModEsp(rs.getString("ID_MOD_ESP"));
                Espec.setFeModEsp(rs.getDate("FE_MOD_ESP"));
                rgs.add(Espec);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public EspecialidadMedicos getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(EspecialidadMedicos.nt, EspecialidadMedicos.FULL_NOM_CAMPOS, EspecialidadMedicos.COLUMNA_PK , id);
            while(rs.next())
            {
                Espec = new EspecialidadMedicos();
                Espec.setPrimaryKey(new String[]{rs.getString("CO_ESPECIALIDAD")});    
                Espec.setCoEspecialidad(rs.getString("CO_ESPECIALIDAD"));
                Espec.setDeEspecialidad(rs.getString("DE_ESPECIALIDAD"));
                Espec.setIdCreaEsp(rs.getString("ID_CREA_ESP"));
                Espec.setFeCreaEsp(rs.getDate("FE_CREA_ESP"));
                Espec.setIdModEsp(rs.getString("ID_MOD_ESP"));
                Espec.setFeModEsp(rs.getDate("FE_MOD_ESP"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Espec;
    }

    
    @Override
    public ArrayList<EspecialidadMedicos> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<EspecialidadMedicos> rgs = new ArrayList<EspecialidadMedicos>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(EspecialidadMedicos.nt, EspecialidadMedicos.COLUMNA_ACTIVO, EspecialidadMedicos.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(EspecialidadMedicos.nt, EspecialidadMedicos.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(EspecialidadMedicos.nt, campos, columnaId, id, EspecialidadMedicos.COLUMNA_ORDER);
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
                Espec = new EspecialidadMedicos();
                Espec.setPrimaryKey(new String[]{rs.getString("CO_ESPECIALIDAD")});    
                Espec.setCoEspecialidad(rs.getString("CO_ESPECIALIDAD"));
                Espec.setDeEspecialidad(rs.getString("DE_ESPECIALIDAD"));
                Espec.setIdCreaEsp(rs.getString("ID_CREA_ESP"));
                Espec.setFeCreaEsp(rs.getDate("FE_CREA_ESP"));
                Espec.setIdModEsp(rs.getString("ID_MOD_ESP"));
                Espec.setFeModEsp(rs.getDate("FE_MOD_ESP"));
                rgs.add(Espec);
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
        Espec = (EspecialidadMedicos)mdl;
        int gravado = 0;
        String campos = EspecialidadMedicos.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {Espec.getCoEspecialidad(),
                            Espec.getDeEspecialidad(),
                            Espec.getIdCreaEsp(),
                            Espec.getFeCreaEsp(),
                            Espec.getIdModEsp(),
                            Espec.getFeModEsp()
                           };
       
           gravado = this.agregarRegistroPs(Espec.getNombreTabla(),EspecialidadMedicos.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }


    public EspecialidadMedicos getEspecialidadMedicos() {
        if(Espec == null)
        {
            Espec = new EspecialidadMedicos();
        }
        return Espec;
    }

    public void setEspecialidadMedicos(JAbstractModelBD prv) {
        this.Espec = (EspecialidadMedicos)prv;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
