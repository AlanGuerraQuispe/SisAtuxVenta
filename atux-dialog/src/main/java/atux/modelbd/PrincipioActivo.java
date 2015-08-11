package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class PrincipioActivo extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    
    public static final String nt = "LGTM_PRINCIPIO_ACTIVO";
    public static final String[] COLUMNA_PK ={"CO_PRINCIPIO_ACTIVO"};
    public static final String COLUMNA_ACTIVO = "ES_PRINCIPIO_ACTIVO";
    public static final String[] COLUMNA_ORDER ={"DE_PRINCIPIO_ACTIVO"};
    private String coPrincipioActivo;
    private String dePrincipioActivo;
    private String esPrincipioActivo;
    private String idCreaPrincipioActivo;
    private Date feCreaPrincipioActivo;
    private String idModPrincipioActivo;
    private Date feModPrincipioActivo;
    
    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_PRINCIPIO_ACTIVO, DE_PRINCIPIO_ACTIVO, ES_PRINCIPIO_ACTIVO, ID_CREA_PRINCIPIO_ACTIVO,"
                      + "FE_CREA_PRINCIPIO_ACTIVO, ID_MOD_PRINCIPIO_ACTIVO, FE_MOD_PRINCIPIO_ACTIVO"};

    public static final String[] 
      CAMPOS_NO_ID ={"DE_PRINCIPIO_ACTIVO, ES_PRINCIPIO_ACTIVO, ID_CREA_PRINCIPIO_ACTIVO,"
                   + "FE_CREA_PRINCIPIO_ACTIVO, ID_MOD_PRINCIPIO_ACTIVO, FE_MOD_PRINCIPIO_ACTIVO"};
        
    public static final String[]
        FULL_NOM_CAMPOS_BUSCAR ={"CO_PRINCIPIO_ACTIVO, DE_PRINCIPIO_ACTIVO, ES_PRINCIPIO_ACTIVO, ID_CREA_PRINCIPIO_ACTIVO,"
                               + "FE_CREA_PRINCIPIO_ACTIVO, ID_MOD_PRINCIPIO_ACTIVO, FE_MOD_PRINCIPIO_ACTIVO"};

    public String getCoPrincipioActivo() {
        return coPrincipioActivo;
    }

    public void setCoPrincipioActivo(String coPrincipioActivo) {
        this.coPrincipioActivo = coPrincipioActivo;
    }

    public String getDePrincipioActivo() {
        return dePrincipioActivo;
    }

    public void setDePrincipioActivo(String dePrincipioActivo) {
        this.dePrincipioActivo = dePrincipioActivo;
    }

    public String getEsPrincipioActivo() {
        return esPrincipioActivo;
    }

    public void setEsPrincipioActivo(String esPrincipioActivo) {
        this.esPrincipioActivo = esPrincipioActivo;
    }

    public Date getFeCreaPrincipioActivo() {
        return feCreaPrincipioActivo;
    }

    public void setFeCreaPrincipioActivo(Date feCreaPrincipioActivo) {
        this.feCreaPrincipioActivo = feCreaPrincipioActivo;
    }

    public Date getFeModPrincipioActivo() {
        return feModPrincipioActivo;
    }

    public void setFeModPrincipioActivo(Date feModPrincipioActivo) {
        this.feModPrincipioActivo = feModPrincipioActivo;
    }

    public String getIdCreaPrincipioActivo() {
        return idCreaPrincipioActivo;
    }

    public void setIdCreaPrincipioActivo(String idCreaPrincipioActivo) {
        this.idCreaPrincipioActivo = idCreaPrincipioActivo;
    }

    public String getIdModPrincipioActivo() {
        return idModPrincipioActivo;
    }

    public void setIdModPrincipioActivo(String idModPrincipioActivo) {
        this.idModPrincipioActivo = idModPrincipioActivo;
    }
    
    public PrincipioActivo() {
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
        return "pActivo{" + "CO_PRINCIPIO_ACTIVO=" + coPrincipioActivo + ", DE_PRINCIPIO_ACTIVO=" + dePrincipioActivo + '}';
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
        if (!(object instanceof PrincipioActivo)) {
            return false;
        }
        PrincipioActivo other = (PrincipioActivo) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }          
    
}
