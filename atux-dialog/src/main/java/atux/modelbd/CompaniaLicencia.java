package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class CompaniaLicencia extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    
    public static final String nt = "CMTM_COMPANIA_LICENCIA";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA, NU_LICENCIA"};
    public static final String COLUMNA_ACTIVO = "ES_LICENCIA";
    public static final String[] COLUMNA_ORDER ={"NU_LICENCIA"};

    private String  coCompania;
    private String  nuLicencia;
    private String  nuSerieLicencia;
    private String  esLicencia;
    private String  idCreaLicencia;
    private Date    feCreaLicencia;
    private String  idModLicencia;
    private Date    feModLicencia;


    public static final String[] 
      FULL_NOM_CAMPOS = {"CO_COMPANIA, NU_LICENCIA, NU_SERIE_LICENCIA, ES_LICENCIA, ID_CREA_LICENCIA, FE_CREA_LICENCIA, " 
                      +  "ID_MOD_LICENCIA, FE_MOD_LICENCIA "};

    public static final String[] 
      CAMPOS_NO_ID = {"NU_LICENCIA, NU_SERIE_LICENCIA, ES_LICENCIA, ID_CREA_LICENCIA, FE_CREA_LICENCIA, " 
                   +  "ID_MOD_LICENCIA, FE_MOD_LICENCIA "};
        
    public static final String[]
        FULL_NOM_CAMPOS_BUSCAR = {"DE_MEDICO, AP_PATERNO_MEDICO, AP_MATERNO_MEDICO, NO_MEDICO, NU_CMP, TI_DOC_IDENTIDAD, " 
                               +  "NU_DOC_IDENTIDAD, ES_MEDICO, NU_TELEFONO, NO_EMAIL"};

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getEsLicencia() {
        return esLicencia;
    }

    public void setEsLicencia(String esLicencia) {
        this.esLicencia = esLicencia;
    }

    public Date getFeCreaLicencia() {
        return feCreaLicencia;
    }

    public void setFeCreaLicencia(Date feCreaLicencia) {
        this.feCreaLicencia = feCreaLicencia;
    }

    public Date getFeModLicencia() {
        return feModLicencia;
    }

    public void setFeModLicencia(Date feModLicencia) {
        this.feModLicencia = feModLicencia;
    }

    public String getIdCreaLicencia() {
        return idCreaLicencia;
    }

    public void setIdCreaLicencia(String idCreaLicencia) {
        this.idCreaLicencia = idCreaLicencia;
    }

    public String getIdModLicencia() {
        return idModLicencia;
    }

    public void setIdModLicencia(String idModLicencia) {
        this.idModLicencia = idModLicencia;
    }

    public String getNuLicencia() {
        return nuLicencia;
    }

    public void setNuLicencia(String nuLicencia) {
        this.nuLicencia = nuLicencia;
    }

    public String getNuSerieLicencia() {
        return nuSerieLicencia;
    }

    public void setNuSerieLicencia(String nuSerieLicencia) {
        this.nuSerieLicencia = nuSerieLicencia;
    }

    public CompaniaLicencia() {
        initBasic();
    }
    
    private void initBasic(){
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
        if (!(object instanceof CompaniaLicencia)) {
            return false;
        }
        CompaniaLicencia other = (CompaniaLicencia) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }          
    
}
