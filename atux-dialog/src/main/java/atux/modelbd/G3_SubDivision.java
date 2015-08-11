package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class G3_SubDivision extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;

    public static final String nt = "LGTR_GRUPO_ANATOMICO";
    public static final String[] COLUMNA_PK ={"CO_NIVEL_03", "CO_NIVEL_SUPERIOR"};
    public static final String[] Filtro_Clave ={"CO_NIVEL_SUPERIOR"};
    public static final String COLUMNA_ACTIVO = "ES_GRUPO_ANATOMICO";
    public static final String[] COLUMNA_ORDER ={"DE_GRUPO_ANATOMICO"};

    private String coGrupoAnatomico;
    private String coIms;
    private String deGrupoAnatomico;
    private String esGrupoAnatomico;
    private String idCreaGrupoAnatomico;
    private Date   feCreaGrupoAnatomico;
    private String idModGrupoAnatomico;
    private Date   feModGrupoAnatomico;
    private String coNivel03;
    private String coNivelSuperior;

    
    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_GRUPO_ANATOMICO, CO_IMS, DE_GRUPO_ANATOMICO, ES_GRUPO_ANATOMICO, ID_CREA_GRUPO_ANATOMICO, "
                      + "FE_CREA_GRUPO_ANATOMICO, ID_MOD_GRUPO_ANATOMICO, FE_MOD_GRUPO_ANATOMICO, CO_NIVEL_03, "
                      + "CO_NIVEL_SUPERIOR"};

    public static final String[] 
      CAMPOS_NO_ID ={"CO_GRUPO_ANATOMICO, CO_IMS, DE_GRUPO_ANATOMICO, ES_GRUPO_ANATOMICO, ID_CREA_GRUPO_ANATOMICO, "
                   + "FE_CREA_GRUPO_ANATOMICO, ID_MOD_GRUPO_ANATOMICO, FE_MOD_GRUPO_ANATOMICO"};
    
    public static final String[]
        FULL_NOM_CAMPOS_BUSCAR ={"CO_GRUPO_ANATOMICO, CO_IMS, DE_GRUPO_ANATOMICO, ES_GRUPO_ANATOMICO, "+
                                 "CO_NIVEL_03, CO_NIVEL_SUPERIOR"};

    public String getCoGrupoAnatomico() {
        return coGrupoAnatomico;
    }

    public void setCoGrupoAnatomico(String coGrupoAnatomico) {
        this.coGrupoAnatomico = coGrupoAnatomico;
    }

    public String getCoIms() {
        return coIms;
    }

    public void setCoIms(String coIms) {
        this.coIms = coIms;
    }

    public String getCoNivel03() {
        return coNivel03;
    }

    public void setCoNivel03(String coNivel03) {
        this.coNivel03 = coNivel03;
    }

    public String getCoNivelSuperior() {
        return coNivelSuperior;
    }

    public void setCoNivelSuperior(String coNivelSuperior) {
        this.coNivelSuperior = coNivelSuperior;
    }

    public String getDeGrupoAnatomico() {
        return deGrupoAnatomico;
    }

    public void setDeGrupoAnatomico(String deGrupoAnatomico) {
        this.deGrupoAnatomico = deGrupoAnatomico;
    }

    public String getEsGrupoAnatomico() {
        return esGrupoAnatomico;
    }

    public void setEsGrupoAnatomico(String esGrupoAnatomico) {
        this.esGrupoAnatomico = esGrupoAnatomico;
    }

    public Date getFeCreaGrupoAnatomico() {
        return feCreaGrupoAnatomico;
    }

    public void setFeCreaGrupoAnatomico(Date feCreaGrupoAnatomico) {
        this.feCreaGrupoAnatomico = feCreaGrupoAnatomico;
    }

    public Date getFeModGrupoAnatomico() {
        return feModGrupoAnatomico;
    }

    public void setFeModGrupoAnatomico(Date feModGrupoAnatomico) {
        this.feModGrupoAnatomico = feModGrupoAnatomico;
    }

    public String getIdCreaGrupoAnatomico() {
        return idCreaGrupoAnatomico;
    }

    public void setIdCreaGrupoAnatomico(String idCreaGrupoAnatomico) {
        this.idCreaGrupoAnatomico = idCreaGrupoAnatomico;
    }

    public String getIdModGrupoAnatomico() {
        return idModGrupoAnatomico;
    }

    public void setIdModGrupoAnatomico(String idModGrupoAnatomico) {
        this.idModGrupoAnatomico = idModGrupoAnatomico;
    }

    public G3_SubDivision() {
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
        if (!(object instanceof G3_SubDivision)) {
            return false;
        }
        G3_SubDivision other = (G3_SubDivision) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }          
    
}
