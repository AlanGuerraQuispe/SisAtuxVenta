package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class Region extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    
    public static final String nt = "vttr_region";
    public static final String[] COLUMNA_PK ={"CO_REGION"};
    public static final String COLUMNA_ACTIVO = "ES_REGION";
    public static final String[] COLUMNA_ORDER ={"DE_REGION"};
    
    private String  coRegion;
    private String  deCortaRegion;
    private String  deRegion;
    private String  esRegion;
    private String  idCreaRegion;
    private Date    feCreaRegion;
    private String  idModRegion;
    private Date    feModRegion;
    private String  coRegionSap;
    
    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_REGION, DE_CORTA_REGION, DE_REGION, ES_REGION, ID_CREA_REGION, FE_CREA_REGION, ID_MOD_REGION, FE_MOD_REGION, CO_REGION_SAP"};

    public static final String[] 
      CAMPOS_NO_ID  ={"DE_CORTA_REGION, DE_REGION, ES_REGION, ID_CREA_REGION, FE_CREA_REGION, ID_MOD_REGION, FE_MOD_REGION, CO_REGION_SAP"};
        
    public static final String[]
        FULL_NOM_CAMPOS_BUSCAR ={"Co_REGION, De_REGION"};

    public String getCoRegion() {
        return coRegion;
    }

    public void setCoRegion(String coRegion) {
        this.coRegion = coRegion;
    }

    public String getCoRegionSap() {
        return coRegionSap;
    }

    public void setCoRegionSap(String coRegionSap) {
        this.coRegionSap = coRegionSap;
    }

    public String getDeCortaRegion() {
        return deCortaRegion;
    }

    public void setDeCortaRegion(String deCortaRegion) {
        this.deCortaRegion = deCortaRegion;
    }

    public String getDeRegion() {
        return deRegion;
    }

    public void setDeRegion(String deRegion) {
        this.deRegion = deRegion;
    }

    public String getEsRegion() {
        return esRegion;
    }

    public void setEsRegion(String esRegion) {
        this.esRegion = esRegion;
    }

    public Date getFeCreaRegion() {
        return feCreaRegion;
    }

    public void setFeCreaRegion(Date feCreaRegion) {
        this.feCreaRegion = feCreaRegion;
    }

    public Date getFeModRegion() {
        return feModRegion;
    }

    public void setFeModRegion(Date feModRegion) {
        this.feModRegion = feModRegion;
    }

    public String getIdCreaRegion() {
        return idCreaRegion;
    }

    public void setIdCreaRegion(String idCreaRegion) {
        this.idCreaRegion = idCreaRegion;
    }

    public String getIdModRegion() {
        return idModRegion;
    }

    public void setIdModRegion(String idModRegion) {
        this.idModRegion = idModRegion;
    }

    
    
    public Region() {
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
        return "mCompania{" + "CO_REGION=" + coRegion + ", DE_REGION=" + deRegion + '}';
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
        if (!(object instanceof Region)) {
            return false;
        }
        Region other = (Region) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }          
    
}
