package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class G4_Familia extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;

    public static final String nt = "LGTR_GRUPO_TERAPEUTICO";
    public static final String[] COLUMNA_PK ={"CO_NIVEL_04", "CO_NIVEL_SUPERIOR"};
    public static final String[] Filtro_Clave ={"CO_NIVEL_SUPERIOR"};
    public static final String COLUMNA_ACTIVO = "ES_GRUPO_TERAPEUTICO";
    public static final String[] COLUMNA_ORDER ={"DE_GRUPO_TERAPEUTICO"};

    private String coGrupoTerapeutico;
    private String coIms;
    private String deGrupoTerapeutico;
    private String esGrupoTerapeutico;
    private String idCreaGrupoTerapeutico;
    private Date feCreaGrupoTerapeutico;
    private String idModGrupoTerapeutico;
    private Date feModGrupoTerapeutico;
    private String coNivel04;
    private String coNivelSuperior;


    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_GRUPO_TERAPEUTICO, CO_IMS, DE_GRUPO_TERAPEUTICO, ES_GRUPO_TERAPEUTICO, ID_CREA_GRUPO_TERAPEUTICO, "
                      + "FE_CREA_GRUPO_TERAPEUTICO, ID_MOD_GRUPO_TERAPEUTICO, FE_MOD_GRUPO_TERAPEUTICO, CO_NIVEL_04, "
                      + "CO_NIVEL_SUPERIOR"};

    public static final String[] 
      CAMPOS_NO_ID  ={"CO_GRUPO_TERAPEUTICO, CO_IMS, DE_GRUPO_TERAPEUTICO, ES_GRUPO_TERAPEUTICO, ID_CREA_GRUPO_TERAPEUTICO, "
                    + "FE_CREA_GRUPO_TERAPEUTICO, ID_MOD_GRUPO_TERAPEUTICO, FE_MOD_GRUPO_TERAPEUTICO"};
    
    public static final String[]
        FULL_NOM_CAMPOS_BUSCAR ={"CO_GRUPO_TERAPEUTICO, CO_IMS, DE_GRUPO_TERAPEUTICO, ES_GRUPO_TERAPEUTICO, "+
                                 "CO_NIVEL_04, CO_NIVEL_SUPERIOR"};

    public String getCoGrupoTerapeutico() {
        return coGrupoTerapeutico;
    }

    public void setCoGrupoTerapeutico(String coGrupoTerapeutico) {
        this.coGrupoTerapeutico = coGrupoTerapeutico;
    }

    public String getCoIms() {
        return coIms;
    }

    public void setCoIms(String coIms) {
        this.coIms = coIms;
    }

    public String getCoNivel04() {
        return coNivel04;
    }

    public void setCoNivel04(String coNivel04) {
        this.coNivel04 = coNivel04;
    }

    public String getCoNivelSuperior() {
        return coNivelSuperior;
    }

    public void setCoNivelSuperior(String coNivelSuperior) {
        this.coNivelSuperior = coNivelSuperior;
    }

    public String getDeGrupoTerapeutico() {
        return deGrupoTerapeutico;
    }

    public void setDeGrupoTerapeutico(String deGrupoTerapeutico) {
        this.deGrupoTerapeutico = deGrupoTerapeutico;
    }

    public String getEsGrupoTerapeutico() {
        return esGrupoTerapeutico;
    }

    public void setEsGrupoTerapeutico(String esGrupoTerapeutico) {
        this.esGrupoTerapeutico = esGrupoTerapeutico;
    }

    public Date getFeCreaGrupoTerapeutico() {
        return feCreaGrupoTerapeutico;
    }

    public void setFeCreaGrupoTerapeutico(Date feCreaGrupoTerapeutico) {
        this.feCreaGrupoTerapeutico = feCreaGrupoTerapeutico;
    }

    public Date getFeModGrupoTerapeutico() {
        return feModGrupoTerapeutico;
    }

    public void setFeModGrupoTerapeutico(Date feModGrupoTerapeutico) {
        this.feModGrupoTerapeutico = feModGrupoTerapeutico;
    }

    public String getIdCreaGrupoTerapeutico() {
        return idCreaGrupoTerapeutico;
    }

    public void setIdCreaGrupoTerapeutico(String idCreaGrupoTerapeutico) {
        this.idCreaGrupoTerapeutico = idCreaGrupoTerapeutico;
    }

    public String getIdModGrupoTerapeutico() {
        return idModGrupoTerapeutico;
    }

    public void setIdModGrupoTerapeutico(String idModGrupoTerapeutico) {
        this.idModGrupoTerapeutico = idModGrupoTerapeutico;
    }
    
    public G4_Familia() {
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
        if (!(object instanceof G4_Familia)) {
            return false;
        }
        G4_Familia other = (G4_Familia) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }    
}