package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class G2_Division extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;

    public static final String nt = "LGTR_GRUPO_PROD_ERP";
    public static final String[] COLUMNA_PK ={"CO_NIVEL_02", "CO_NIVEL_SUPERIOR"};
    public static final String[] Filtro_Clave ={"CO_NIVEL_SUPERIOR"};
    public static final String COLUMNA_ACTIVO = "ES_GRUPO_PROD_ERP";
    public static final String[] COLUMNA_ORDER ={"DE_GRUPO_PROD_ERP"};

    private String coGrupoProdErp;
    private String deGrupoProdErp;
    private String esGrupoProdErp;
    private String idCreaGrupoProdErp;
    private Date   feCreaGrupoProdErp;
    private String idModGrupoProdErp;
    private Date   feModGrupoProdErp;
    private String coNivel02;
    private String coNivelSuperior;
    
    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_GRUPO_PROD_ERP, DE_GRUPO_PROD_ERP, ES_GRUPO_PROD_ERP, ID_CREA_GRUPO_PROD_ERP, "
                      + "FE_CREA_GRUPO_PROD_ERP, ID_MOD_GRUPO_PROD_ERP, FE_MOD_GRUPO_PROD_ERP, CO_NIVEL_02, "
                      + "CO_NIVEL_SUPERIOR"};

    public static final String[] 
      CAMPOS_NO_ID ={"CO_GRUPO_PROD_ERP, DE_GRUPO_PROD_ERP, ES_GRUPO_PROD_ERP, ID_CREA_GRUPO_PROD_ERP, "
                      + "FE_CREA_GRUPO_PROD_ERP, ID_MOD_GRUPO_PROD_ERP, FE_MOD_GRUPO_PROD_ERP"};
    
    public static final String[]
        FULL_NOM_CAMPOS_BUSCAR ={"CO_GRUPO_PROD_ERP, DE_GRUPO_PROD_ERP, ES_GRUPO_PROD_ERP, "+
                                 "CO_NIVEL_02, CO_NIVEL_SUPERIOR"};

    public String getCoGrupoProdErp() {
        return coGrupoProdErp;
    }

    public void setCoGrupoProdErp(String coGrupoProdErp) {
        this.coGrupoProdErp = coGrupoProdErp;
    }

    public String getCoNivel02() {
        return coNivel02;
    }

    public void setCoNivel02(String coNivel02) {
        this.coNivel02 = coNivel02;
    }

    public String getCoNivelSuperior() {
        return coNivelSuperior;
    }

    public void setCoNivelSuperior(String coNivelSuperior) {
        this.coNivelSuperior = coNivelSuperior;
    }

    public String getDeGrupoProdErp() {
        return deGrupoProdErp;
    }

    public void setDeGrupoProdErp(String deGrupoProdErp) {
        this.deGrupoProdErp = deGrupoProdErp;
    }

    public String getEsGrupoProdErp() {
        return esGrupoProdErp;
    }

    public void setEsGrupoProdErp(String esGrupoProdErp) {
        this.esGrupoProdErp = esGrupoProdErp;
    }

    public Date getFeCreaGrupoProdErp() {
        return feCreaGrupoProdErp;
    }

    public void setFeCreaGrupoProdErp(Date feCreaGrupoProdErp) {
        this.feCreaGrupoProdErp = feCreaGrupoProdErp;
    }

    public Date getFeModGrupoProdErp() {
        return feModGrupoProdErp;
    }

    public void setFeModGrupoProdErp(Date feModGrupoProdErp) {
        this.feModGrupoProdErp = feModGrupoProdErp;
    }

    public String getIdCreaGrupoProdErp() {
        return idCreaGrupoProdErp;
    }

    public void setIdCreaGrupoProdErp(String idCreaGrupoProdErp) {
        this.idCreaGrupoProdErp = idCreaGrupoProdErp;
    }

    public String getIdModGrupoProdErp() {
        return idModGrupoProdErp;
    }

    public void setIdModGrupoProdErp(String idModGrupoProdErp) {
        this.idModGrupoProdErp = idModGrupoProdErp;
    }

    public G2_Division() {
        initBasic();
    }
    
    private void initBasic()
    {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
    }    
    
    @Override
//    public String toString() {
//        return "tCambio{" + "nu_Sec_Tipo_Cambio=" + nuSecTipoCambio + ", fe_tipo_cambio=" + feTipoCambio + '}';
//    } 

    public int hashCode() {
        int hash = 0;
        hash += (primaryKey != null ? primaryKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof G2_Division)) {
            return false;
        }
        G2_Division other = (G2_Division) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }          
    
}
