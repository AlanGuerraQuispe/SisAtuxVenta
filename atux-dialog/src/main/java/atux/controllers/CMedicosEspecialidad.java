package atux.controllers;

import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.MedicosEspecialidad;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CMedicosEspecialidad extends JAbstractController{
    private MedicosEspecialidad MedEsp;
   
    
    @Override
    public ArrayList<MedicosEspecialidad> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{MedicosEspecialidad.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<MedicosEspecialidad> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<MedicosEspecialidad> getRegistros() {                 
        ArrayList<MedicosEspecialidad> rgs = new ArrayList<MedicosEspecialidad>();
        try {                        
//            rs = this.getRegistros(TipoDeCambio.nt,TipoDeCambio.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(MedicosEspecialidad.nt, MedicosEspecialidad.FULL_NOM_CAMPOS, null, null, MedicosEspecialidad.COLUMNA_ORDER);
            while(rs.next())
            {
                MedEsp = new MedicosEspecialidad();
                MedEsp.setCoMedico(rs.getString("CO_MEDICO"));
                MedEsp.setCoEspecialidad(rs.getString("CO_ESPECIALIDAD"));
                MedEsp.setIdCreaMedico(rs.getString("ID_CREA_MEDICO"));
                MedEsp.setFeCreaMedico(rs.getDate("FE_CREA_MEDICO"));
                MedEsp.setIdModMedico(rs.getString("ID_MOD_MEDICO"));
                MedEsp.setFeModMedico(rs.getDate("FE_MOD_MEDICO"));
                rgs.add(MedEsp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public MedicosEspecialidad getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(MedicosEspecialidad.nt, MedicosEspecialidad.FULL_NOM_CAMPOS, MedicosEspecialidad.COLUMNA_PK , id);
            while(rs.next())
            {
                MedEsp.setPrimaryKey(new String[]{rs.getString("CO_MEDICO")});    
                MedEsp.setCoMedico(rs.getString("CO_MEDICO"));
                MedEsp.setCoEspecialidad(rs.getString("CO_ESPECIALIDAD"));
                MedEsp.setIdCreaMedico(rs.getString("ID_CREA_MEDICO"));
                MedEsp.setFeCreaMedico(rs.getDate("FE_CREA_MEDICO"));
                MedEsp.setIdModMedico(rs.getString("ID_MOD_MEDICO"));
                MedEsp.setFeModMedico(rs.getDate("FE_MOD_MEDICO"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return MedEsp;
    }

    
    public ArrayList<MedicosEspecialidad> getRegistros(String vCodigoMedico){
        ArrayList<MedicosEspecialidad> rgs = new ArrayList<MedicosEspecialidad>();
        MedicosEspecialidad      medicosEspecialidad      = null;
        StringBuffer  query;

        query = new StringBuffer();

        query.append("select TME.*, TE.DE_ESPECIALIDAD ");
        query.append("  from comun.CMTM_MEDICO_ESPECIALIDAD TME,");
        query.append("       comun.CMTM_ESPECIALIDAD TE ");
        query.append(" where TME.CO_ESPECIALIDAD = TE.CO_ESPECIALIDAD ");
        query.append(" AND co_medico = '").append(vCodigoMedico).append("'");

        try {            
            rs =  this.getRegistrosSinFiltro(query);
            while(rs.next())
            {
                MedEsp = new MedicosEspecialidad();
                MedEsp.setPrimaryKey(new String[]{rs.getString("CO_MEDICO")});    
                MedEsp.setCoMedico(rs.getString("CO_MEDICO"));
                MedEsp.setCoEspecialidad(rs.getString("CO_ESPECIALIDAD"));
                MedEsp.setDeEspecialidad(rs.getString("DE_ESPECIALIDAD"));
                MedEsp.setIdCreaMedico(rs.getString("ID_CREA_MEDICO"));
                MedEsp.setFeCreaMedico(rs.getDate("FE_CREA_MEDICO"));
                MedEsp.setIdModMedico(rs.getString("ID_MOD_MEDICO"));
                MedEsp.setFeModMedico(rs.getDate("FE_MOD_MEDICO"));
                rgs.add(MedEsp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicosEspecialidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rgs;
    }    
    
    @Override
    public ArrayList<MedicosEspecialidad> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<MedicosEspecialidad> rgs = new ArrayList<MedicosEspecialidad>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(MedicosEspecialidad.nt, MedicosEspecialidad.COLUMNA_ACTIVO, MedicosEspecialidad.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(MedicosEspecialidad.nt, MedicosEspecialidad.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(MedicosEspecialidad.nt, campos, columnaId, id, MedicosEspecialidad.COLUMNA_ORDER);
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
                MedEsp= new MedicosEspecialidad();
                MedEsp.setPrimaryKey(new String[]{rs.getString("CO_MEDICO")});    
                MedEsp.setCoMedico(rs.getString("CO_MEDICO"));
                MedEsp.setCoEspecialidad(rs.getString("CO_ESPECIALIDAD"));
                MedEsp.setIdCreaMedico(rs.getString("ID_CREA_MEDICO"));
                MedEsp.setFeCreaMedico(rs.getDate("FE_CREA_MEDICO"));
                MedEsp.setIdModMedico(rs.getString("ID_MOD_MEDICO"));
                MedEsp.setFeModMedico(rs.getDate("FE_MOD_MEDICO"));
                rgs.add(MedEsp);
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
        MedEsp = (MedicosEspecialidad)mdl;
        int gravado = 0;
        String campos = MedicosEspecialidad.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {MedEsp.getCoMedico(),
                            MedEsp.getCoEspecialidad(),
                            MedEsp.getIdCreaMedico(),
                            MedEsp.getFeCreaMedico(),
                            MedEsp.getIdModMedico(),
                            MedEsp.getFeModMedico()
                           };
       
           gravado = this.agregarRegistroPs(MedEsp.getNombreTabla(),MedicosEspecialidad.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }


    public MedicosEspecialidad getMedicosEspecialidad() {
        if(MedEsp == null)
        {
            MedEsp = new MedicosEspecialidad();
        }
        return MedEsp;
    }

    public void setMedicosEspecialidad(JAbstractModelBD prv) {
        this.MedEsp = (MedicosEspecialidad)prv;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
