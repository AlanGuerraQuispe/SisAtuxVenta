package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class Factores extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;

    public static final String nt = "VTTR_FACTOR_PRECIO";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA", "CO_FACTOR_PRECIO"};
    public static final String COLUMNA_ACTIVO = "ES_FACTOR_PRECIO";
    public static final String[] COLUMNA_ORDER ={"DE_FACTOR_PRECIO"};

    private String  coCompania;
    private String  coFactorPrecio;
    private String  deFactorPrecio;
    private Double  pcFactorPrecio;
    private String  esFactorPrecio;
    private String  idCreaFactorPrecio;
    private Date    feCreaFactorPrecio;
    private String  idModFactorPrecio;
    private Date    feModFactorPrecio;
    
    
            
    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_COMPANIA, CO_FACTOR_PRECIO, DE_FACTOR_PRECIO, PC_FACTOR_PRECIO, ES_FACTOR_PRECIO, "
                      + "ID_CREA_FACTOR_PRECIO,FE_CREA_FACTOR_PRECIO, ID_MOD_FACTOR_PRECIO, FE_MOD_FACTOR_PRECIO"};

    public static final String[] 
      CAMPOS_NO_ID = {"DE_FACTOR_PRECIO, PC_FACTOR_PRECIO, ES_FACTOR_PRECIO, "
                      + "ID_CREA_FACTOR_PRECIO,FE_CREA_FACTOR_PRECIO, ID_MOD_FACTOR_PRECIO, FE_MOD_FACTOR_PRECIO"};
        
    public static final String[]
        FULL_NOM_CAMPOS_BUSCAR ={"CO_LINEA_PROD_ERP, DE_LINEA_PROD_ERP, ES_LINEA_PROD_ERP, "+
                                 "CO_NIVEL_01, CO_NIVEL_SUPERIOR"};

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoFactorPrecio() {
        return coFactorPrecio;
    }

    public void setCoFactorPrecio(String coFactorPrecio) {
        this.coFactorPrecio = coFactorPrecio;
    }

    public String getDeFactorPrecio() {
        return deFactorPrecio;
    }

    public void setDeFactorPrecio(String deFactorPrecio) {
        this.deFactorPrecio = deFactorPrecio;
    }

    public String getEsFactorPrecio() {
        return esFactorPrecio;
    }

    public void setEsFactorPrecio(String esFactorPrecio) {
        this.esFactorPrecio = esFactorPrecio;
    }

    public Date getFeCreaFactorPrecio() {
        return feCreaFactorPrecio;
    }

    public void setFeCreaFactorPrecio(Date feCreaFactorPrecio) {
        this.feCreaFactorPrecio = feCreaFactorPrecio;
    }

    public Date getFeModFactorPrecio() {
        return feModFactorPrecio;
    }

    public void setFeModFactorPrecio(Date feModFactorPrecio) {
        this.feModFactorPrecio = feModFactorPrecio;
    }

    public String getIdCreaFactorPrecio() {
        return idCreaFactorPrecio;
    }

    public void setIdCreaFactorPrecio(String idCreaFactorPrecio) {
        this.idCreaFactorPrecio = idCreaFactorPrecio;
    }

    public String getIdModFactorPrecio() {
        return idModFactorPrecio;
    }

    public void setIdModFactorPrecio(String idModFactorPrecio) {
        this.idModFactorPrecio = idModFactorPrecio;
    }

    public Double getPcFactorPrecio() {
        return pcFactorPrecio;
    }

    public void setPcFactorPrecio(Double pcFactorPrecio) {
        this.pcFactorPrecio = pcFactorPrecio;
    }

    public Factores() {
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
        if (!(object instanceof Factores)) {
            return false;
        }
        Factores other = (Factores) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }          
    
}
