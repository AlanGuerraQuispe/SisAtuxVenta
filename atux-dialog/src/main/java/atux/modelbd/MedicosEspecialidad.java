package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class MedicosEspecialidad extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    
    public static final String nt = "CMTM_MEDICO_ESPECIALIDAD";
    public static final String[] COLUMNA_PK ={"CO_MEDICO","CO_ESPECIALIDAD"};
    public static final String COLUMNA_ACTIVO = "";
    public static final String[] COLUMNA_ORDER ={"CO_ESPECIALIDAD"};

    private String coMedico;
    private String coEspecialidad;
    private String deEspecialidad;
    private String idCreaMedico;
    private Date feCreaMedico;
    private String idModMedico;
    private Date feModMedico;



    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_MEDICO, CO_ESPECIALIDAD, ID_CREA_MEDICO, FE_CREA_MEDICO, ID_MOD_MEDICO, FE_MOD_MEDICO"};

//    public static final String[] 
//      CAMPOS_NO_ID ={"DE_MEDICO, AP_PATERNO_MEDICO, AP_MATERNO_MEDICO, NO_MEDICO, NU_CMP, TI_DOC_IDENTIDAD, " 
//                   + "NU_DOC_IDENTIDAD, ES_MEDICO, NU_TELEFONO, NO_EMAIL, ID_CREA_MEDICO, FE_CREA_MEDICO,"
//                   + "ID_MOD_MEDICO, FE_MOD_MEDICO"};
//        
//    public static final String[]
//        FULL_NOM_CAMPOS_BUSCAR ={"DE_MEDICO, AP_PATERNO_MEDICO, AP_MATERNO_MEDICO, NO_MEDICO, NU_CMP, TI_DOC_IDENTIDAD, " 
//                               + "NU_DOC_IDENTIDAD, ES_MEDICO, NU_TELEFONO, NO_EMAIL"};

    public String getCoEspecialidad() {
        return coEspecialidad;
    }

    public void setCoEspecialidad(String coEspecialidad) {
        this.coEspecialidad = coEspecialidad;
    }

    public String getDeEspecialidad() {
        return deEspecialidad;
    }

    public void setDeEspecialidad(String deEspecialidad) {
        this.deEspecialidad = deEspecialidad;
    }

    public String getCoMedico() {
        return coMedico;
    }

    public void setCoMedico(String coMedico) {
        this.coMedico = coMedico;
    }

    public Date getFeCreaMedico() {
        return feCreaMedico;
    }

    public void setFeCreaMedico(Date feCreaMedico) {
        this.feCreaMedico = feCreaMedico;
    }

    public Date getFeModMedico() {
        return feModMedico;
    }

    public void setFeModMedico(Date feModMedico) {
        this.feModMedico = feModMedico;
    }

    public String getIdCreaMedico() {
        return idCreaMedico;
    }

    public void setIdCreaMedico(String idCreaMedico) {
        this.idCreaMedico = idCreaMedico;
    }

    public String getIdModMedico() {
        return idModMedico;
    }

    public void setIdModMedico(String idModMedico) {
        this.idModMedico = idModMedico;
    }

    
    public MedicosEspecialidad() {
        initBasic();
    }
    
    private void initBasic()
    {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
    }    
    
//    @Override
//    public String toString() {
//        return "Med{" + "CO_MEDICO=" + coMedico + ", DE_MEDICO=" + deMedico + '}';
//    } 
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (primaryKey != null ? primaryKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicosEspecialidad)) {
            return false;
        }
        MedicosEspecialidad other = (MedicosEspecialidad) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }          
    
}
