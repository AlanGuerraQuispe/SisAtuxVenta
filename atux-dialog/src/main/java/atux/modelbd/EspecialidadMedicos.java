package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class EspecialidadMedicos extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    
    public static final String nt = "CMTM_ESPECIALIDAD";
    public static final String[] COLUMNA_PK ={"CO_ESPECIALIDAD"};
    public static final String COLUMNA_ACTIVO = "";
    public static final String[] COLUMNA_ORDER ={"DE_ESPECIALIDAD"};

    private String coEspecialidad;
    private String deEspecialidad;
    private String idCreaEsp;
    private Date   feCreaEsp;
    private String idModEsp;
    private Date   feModEsp;

    
    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_ESPECIALIDAD, DE_ESPECIALIDAD, ID_CREA_ESP, FE_CREA_ESP, ID_MOD_ESP, FE_MOD_ESP"};

    public static final String[] 
      CAMPOS_NO_ID ={"DE_ESPECIALIDAD, ID_CREA_ESP, FE_CREA_ESP, ID_MOD_ESP, FE_MOD_ESP"};
        
    public static final String[]
        FULL_NOM_CAMPOS_BUSCAR ={"DE_ESPECIALIDAD, ID_CREA_ESP, FE_CREA_ESP, ID_MOD_ESP, FE_MOD_ESP"};

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

    public Date getFeCreaEsp() {
        return feCreaEsp;
    }

    public void setFeCreaEsp(Date feCreaEsp) {
        this.feCreaEsp = feCreaEsp;
    }

    public Date getFeModEsp() {
        return feModEsp;
    }

    public void setFeModEsp(Date feModEsp) {
        this.feModEsp = feModEsp;
    }

    public String getIdCreaEsp() {
        return idCreaEsp;
    }

    public void setIdCreaEsp(String idCreaEsp) {
        this.idCreaEsp = idCreaEsp;
    }

    public String getIdModEsp() {
        return idModEsp;
    }

    public void setIdModEsp(String idModEsp) {
        this.idModEsp = idModEsp;
    }

    public EspecialidadMedicos() {
        initBasic();
    }
    
    private void initBasic()
    {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
    }    
    
    @Override
    public String toString() {
        return "Med{" + "CO_ESPECIALIDAD=" + coEspecialidad + ", DE_ESPECIALIDAD=" + deEspecialidad + '}';
    } 
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (primaryKey != null ? primaryKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EspecialidadMedicos)) {
            return false;
        }
        EspecialidadMedicos other = (EspecialidadMedicos) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }          
    
}
