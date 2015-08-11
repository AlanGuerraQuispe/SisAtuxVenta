package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class G1_LineaComercial extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;

    public static final String nt = "LGTR_LINEA_PROD_ERP";
    public static final String[] COLUMNA_PK ={"CO_NIVEL_01", "CO_NIVEL_SUPERIOR"};
    public static final String COLUMNA_ACTIVO = "ES_LINEA_PROD_ERP";
    public static final String[] COLUMNA_ORDER ={"DE_LINEA_PROD_ERP"};

    private String coLineaProdErp;
    private String deLineaProdErp;
    private String esLineaProdErp;
    private String idCreaLineaProdErp;
    private Date   feCreaLineaProdErp;
    private String idModLineaProdErp;
    private Date   feModLineaProdErp;
    private String coNivel01;
    private String coNivelSuperior;
    
    
            
    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_LINEA_PROD_ERP, DE_LINEA_PROD_ERP, ES_LINEA_PROD_ERP, ID_CREA_LINEA_PROD_ERP, "
                      + "FE_CREA_LINEA_PROD_ERP, ID_MOD_LINEA_PROD_ERP, FE_MOD_LINEA_PROD_ERP, CO_NIVEL_01, "
                      + "CO_NIVEL_SUPERIOR"};

    public static final String[] 
      CAMPOS_NO_ID ={"CO_LINEA_PROD_ERP, DE_LINEA_PROD_ERP, ES_LINEA_PROD_ERP, ID_CREA_LINEA_PROD_ERP, "
                      + "FE_CREA_LINEA_PROD_ERP, ID_MOD_LINEA_PROD_ERP, FE_MOD_LINEA_PROD_ERP"};
        
    public static final String[]
        FULL_NOM_CAMPOS_BUSCAR ={"CO_LINEA_PROD_ERP, DE_LINEA_PROD_ERP, ES_LINEA_PROD_ERP, "+
                                 "CO_NIVEL_01, CO_NIVEL_SUPERIOR"};

    public String getCoLineaProdErp() {
        return coLineaProdErp;
    }

    public void setCoLineaProdErp(String coLineaProdErp) {
        this.coLineaProdErp = coLineaProdErp;
    }

    public String getCoNivel01() {
        return coNivel01;
    }

    public void setCoNivel01(String coNivel01) {
        this.coNivel01 = coNivel01;
    }

    public String getCoNivelSuperior() {
        return coNivelSuperior;
    }

    public void setCoNivelSuperior(String coNivelSuperior) {
        this.coNivelSuperior = coNivelSuperior;
    }

    public String getDeLineaProdErp() {
        return deLineaProdErp;
    }

    public void setDeLineaProdErp(String deLineaProdErp) {
        this.deLineaProdErp = deLineaProdErp;
    }

    public String getEsLineaProdErp() {
        return esLineaProdErp;
    }

    public void setEsLineaProdErp(String esLineaProdErp) {
        this.esLineaProdErp = esLineaProdErp;
    }

    public Date getFeCreaLineaProdErp() {
        return feCreaLineaProdErp;
    }

    public void setFeCreaLineaProdErp(Date feCreaLineaProdErp) {
        this.feCreaLineaProdErp = feCreaLineaProdErp;
    }

    public Date getFeModLineaProdErp() {
        return feModLineaProdErp;
    }

    public void setFeModLineaProdErp(Date feModLineaProdErp) {
        this.feModLineaProdErp = feModLineaProdErp;
    }

    public String getIdCreaLineaProdErp() {
        return idCreaLineaProdErp;
    }

    public void setIdCreaLineaProdErp(String idCreaLineaProdErp) {
        this.idCreaLineaProdErp = idCreaLineaProdErp;
    }

    public String getIdModLineaProdErp() {
        return idModLineaProdErp;
    }

    public void setIdModLineaProdErp(String idModLineaProdErp) {
        this.idModLineaProdErp = idModLineaProdErp;
    }
    
    public G1_LineaComercial() {
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
        if (!(object instanceof G1_LineaComercial)) {
            return false;
        }
        G1_LineaComercial other = (G1_LineaComercial) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }          
    
}
