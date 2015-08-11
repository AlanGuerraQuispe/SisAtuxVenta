package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class Medicos extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    
    public static final String nt = "CMTM_MEDICO";
    public static final String[] COLUMNA_PK ={"CO_MEDICO"};
    public static final String COLUMNA_ACTIVO = "ES_MEDICO";
    public static final String[] COLUMNA_ORDER ={"DE_MEDICO"};

    private String coMedico;
    private String deMedico;
    private String apPaternoMedico;
    private String apMaternoMedico;
    private String noMedico;
    private String nuCmp;
    private String tiDocIdentidad;
    private String nuDocIdentidad;
    private String esMedico;
    private String nuTelefono;
    private String noEmail;
    private String idCreaMedico;
    private Date   feCreaMedico;
    private String idModMedico;
    private Date   feModMedico;

    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_MEDICO, DE_MEDICO, AP_PATERNO_MEDICO, AP_MATERNO_MEDICO, NO_MEDICO, NU_CMP, TI_DOC_IDENTIDAD, " 
                      + "NU_DOC_IDENTIDAD, ES_MEDICO, NU_TELEFONO, NO_EMAIL, ID_CREA_MEDICO, FE_CREA_MEDICO,"
                      + "ID_MOD_MEDICO, FE_MOD_MEDICO"};

    public static final String[] 
      CAMPOS_NO_ID ={"DE_MEDICO, AP_PATERNO_MEDICO, AP_MATERNO_MEDICO, NO_MEDICO, NU_CMP, TI_DOC_IDENTIDAD, " 
                   + "NU_DOC_IDENTIDAD, ES_MEDICO, NU_TELEFONO, NO_EMAIL, ID_CREA_MEDICO, FE_CREA_MEDICO,"
                   + "ID_MOD_MEDICO, FE_MOD_MEDICO"};
        
    public static final String[]
        FULL_NOM_CAMPOS_BUSCAR ={"DE_MEDICO, AP_PATERNO_MEDICO, AP_MATERNO_MEDICO, NO_MEDICO, NU_CMP, TI_DOC_IDENTIDAD, " 
                               + "NU_DOC_IDENTIDAD, ES_MEDICO, NU_TELEFONO, NO_EMAIL"};

    public String getApMaternoMedico() {
        return apMaternoMedico;
    }

    public void setApMaternoMedico(String apMaternoMedico) {
        this.apMaternoMedico = apMaternoMedico;
    }

    public String getApPaternoMedico() {
        return apPaternoMedico;
    }

    public void setApPaternoMedico(String apPaternoMedico) {
        this.apPaternoMedico = apPaternoMedico;
    }

    public String getCoMedico() {
        return coMedico;
    }

    public void setCoMedico(String coMedico) {
        this.coMedico = coMedico;
    }

    public String getDeMedico() {
        return deMedico;
    }

    public void setDeMedico(String deMedico) {
        this.deMedico = deMedico;
    }

    public String getEsMedico() {
        return esMedico;
    }

    public void setEsMedico(String esMedico) {
        this.esMedico = esMedico;
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

    public String getNoEmail() {
        return noEmail;
    }

    public void setNoEmail(String noEmail) {
        this.noEmail = noEmail;
    }

    public String getNoMedico() {
        return noMedico;
    }

    public void setNoMedico(String noMedico) {
        this.noMedico = noMedico;
    }

    public String getNuCmp() {
        return nuCmp;
    }

    public void setNuCmp(String nuCmp) {
        this.nuCmp = nuCmp;
    }

    public String getNuDocIdentidad() {
        return nuDocIdentidad;
    }

    public void setNuDocIdentidad(String nuDocIdentidad) {
        this.nuDocIdentidad = nuDocIdentidad;
    }

    public String getNuTelefono() {
        return nuTelefono;
    }

    public void setNuTelefono(String nuTelefono) {
        this.nuTelefono = nuTelefono;
    }

    public String getTiDocIdentidad() {
        return tiDocIdentidad;
    }

    public void setTiDocIdentidad(String tiDocIdentidad) {
        this.tiDocIdentidad = tiDocIdentidad;
    }

    public Medicos() {
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
        return "Med{" + "CO_MEDICO=" + coMedico + ", DE_MEDICO=" + deMedico + '}';
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
        if (!(object instanceof Medicos)) {
            return false;
        }
        Medicos other = (Medicos) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }          
    
}
