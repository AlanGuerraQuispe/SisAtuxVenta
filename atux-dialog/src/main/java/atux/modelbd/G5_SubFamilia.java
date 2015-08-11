package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class G5_SubFamilia extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;

    public static final String nt = "LGTR_ACCION_TERAPEUTICA";
    public static final String[] COLUMNA_PK ={"CO_NIVEL_05", "CO_NIVEL_SUPERIOR"};
    public static final String[] Filtro_Clave ={"CO_NIVEL_SUPERIOR"};
    public static final String COLUMNA_ACTIVO = "ES_ACCION_TERAPEUTICA";
    public static final String[] COLUMNA_ORDER ={"DE_ACCION_TERAPEUTICA"};

    private String coAccionTerapeutica;
    private String coIms;
    private String deAccionTerapeutica;
    private String esAccionTerapeutica;
    private String idCreaAccionTerapeutica;
    private Date   feCreaAccionTerapeutica;
    private String idModAccionTerapeutica;
    private Date   feModAccionTerapeutica;
    private String coNivel05;
    private String coNivelSuperior;
    
    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_ACCION_TERAPEUTICA, CO_IMS, DE_ACCION_TERAPEUTICA, ES_ACCION_TERAPEUTICA, ID_CREA_ACCION_TERAPEUTICA, "
                      + "FE_CREA_ACCION_TERAPEUTICA, ID_MOD_ACCION_TERAPEUTICA, FE_MOD_ACCION_TERAPEUTICA, CO_NIVEL_05, "
                      + "CO_NIVEL_SUPERIOR"};

    public static final String[] 
      CAMPOS_NO_ID  ={"CO_ACCION_TERAPEUTICA, CO_IMS, DE_ACCION_TERAPEUTICA, ES_ACCION_TERAPEUTICA, ID_CREA_ACCION_TERAPEUTICA, "
                    + "FE_CREA_ACCION_TERAPEUTICA, ID_MOD_ACCION_TERAPEUTICA, FE_MOD_ACCION_TERAPEUTICA"};
    
    public static final String[]
        FULL_NOM_CAMPOS_BUSCAR ={"CO_ACCION_TERAPEUTICA, CO_IMS, DE_ACCION_TERAPEUTICA, ES_ACCION_TERAPEUTICA, "+
                                 "CO_NIVEL_05, CO_NIVEL_SUPERIOR"};

    public String getCoAccionTerapeutica() {
        return coAccionTerapeutica;
    }

    public void setCoAccionTerapeutica(String coAccionTerapeutica) {
        this.coAccionTerapeutica = coAccionTerapeutica;
    }

    public String getCoIms() {
        return coIms;
    }

    public void setCoIms(String coIms) {
        this.coIms = coIms;
    }

    public String getCoNivel05() {
        return coNivel05;
    }

    public void setCoNivel05(String coNivel05) {
        this.coNivel05 = coNivel05;
    }

    public String getCoNivelSuperior() {
        return coNivelSuperior;
    }

    public void setCoNivelSuperior(String coNivelSuperior) {
        this.coNivelSuperior = coNivelSuperior;
    }

    public String getDeAccionTerapeutica() {
        return deAccionTerapeutica;
    }

    public void setDeAccionTerapeutica(String deAccionTerapeutica) {
        this.deAccionTerapeutica = deAccionTerapeutica;
    }

    public String getEsAccionTerapeutica() {
        return esAccionTerapeutica;
    }

    public void setEsAccionTerapeutica(String esAccionTerapeutica) {
        this.esAccionTerapeutica = esAccionTerapeutica;
    }

    public Date getFeCreaAccionTerapeutica() {
        return feCreaAccionTerapeutica;
    }

    public void setFeCreaAccionTerapeutica(Date feCreaAccionTerapeutica) {
        this.feCreaAccionTerapeutica = feCreaAccionTerapeutica;
    }

    public Date getFeModAccionTerapeutica() {
        return feModAccionTerapeutica;
    }

    public void setFeModAccionTerapeutica(Date feModAccionTerapeutica) {
        this.feModAccionTerapeutica = feModAccionTerapeutica;
    }

    public String getIdCreaAccionTerapeutica() {
        return idCreaAccionTerapeutica;
    }

    public void setIdCreaAccionTerapeutica(String idCreaAccionTerapeutica) {
        this.idCreaAccionTerapeutica = idCreaAccionTerapeutica;
    }

    public String getIdModAccionTerapeutica() {
        return idModAccionTerapeutica;
    }

    public void setIdModAccionTerapeutica(String idModAccionTerapeutica) {
        this.idModAccionTerapeutica = idModAccionTerapeutica;
    }

    public G5_SubFamilia() {
        initBasic();
    }
    
    private void initBasic()
    {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
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
        if (!(object instanceof G5_SubFamilia)) {
            return false;
        }
        G5_SubFamilia other = (G5_SubFamilia) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }    
}