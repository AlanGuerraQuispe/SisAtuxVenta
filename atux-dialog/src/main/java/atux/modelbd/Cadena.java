package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class Cadena extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    
    public static final String nt = "CMTM_CADENA";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_CADENA"};
    public static final String COLUMNA_ACTIVO = "ES_CADENA";
    public static final String[] COLUMNA_ORDER ={"DE_CADENA"};
    
    private String  coCadena;
    private String  coCompania;
    private String  deCadena;
    private String  esCadena;
    private String  idCreaCadena;
    private Date    feCreaCadena;
    private String  idModCadena;
    private Date    feModCadena;

    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_COMPANIA, CO_CADENA, DE_CADENA, ES_CADENA, ID_CREA_CADENA, FE_CREA_CADENA, ID_MOD_CADENA, FE_MOD_CADENA"};

    public static final String[] 
      CAMPOS_NO_ID  ={"DE_CADENA, ES_CADENA, ID_CREA_CADENA, FE_CREA_CADENA, ID_MOD_CADENA, FE_MOD_CADENA"};
        
    public static final String[]
        FULL_NOM_CAMPOS_BUSCAR ={"Co_Cadena, De_Cadena"};

    public String getCoCadena() {
        return coCadena;
    }

    public void setCoCadena(String coCadena) {
        this.coCadena = coCadena;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getDeCadena() {
        return deCadena;
    }

    public void setDeCadena(String deCadena) {
        this.deCadena = deCadena;
    }

    public String getEsCadena() {
        return esCadena;
    }

    public void setEsCadena(String esCadena) {
        this.esCadena = esCadena;
    }

    public Date getFeCreaCadena() {
        return feCreaCadena;
    }

    public void setFeCreaCadena(Date feCreaCadena) {
        this.feCreaCadena = feCreaCadena;
    }

    public Date getFeModCadena() {
        return feModCadena;
    }

    public void setFeModCadena(Date feModCadena) {
        this.feModCadena = feModCadena;
    }

    public String getIdCreaCadena() {
        return idCreaCadena;
    }

    public void setIdCreaCadena(String idCreaCadena) {
        this.idCreaCadena = idCreaCadena;
    }

    public String getIdModCadena() {
        return idModCadena;
    }

    public void setIdModCadena(String idModCadena) {
        this.idModCadena = idModCadena;
    }

    
    public Cadena() {
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
        return "mCompania{" + "CO_Cadena=" + coCadena + ", DE_Cadena=" + deCadena + '}';
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
        if (!(object instanceof Cadena)) {
            return false;
        }
        Cadena other = (Cadena) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }          
    
}
