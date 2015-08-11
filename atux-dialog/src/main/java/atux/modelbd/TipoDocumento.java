package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;

public class TipoDocumento extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    
    public static final String nt = "CMTM_DOCUMENTO_IDENTIDAD";
    public static final String[] COLUMNA_PK ={"CO_DOCUMENTO_IDENTIDAD"};
    public static final String COLUMNA_ACTIVO = "ES_DOCUEMNTO_IDENTIDAD";
    public static final String[] COLUMNA_ORDER ={"CO_DOCUMENTO_IDENTIDAD"};

    private String coDocumentoIdentidad;
    private String deDocumentoIdentidad;
    private String deAbrDocumento;
    private String nuCaracteres;
    private String esDocumentoIdentidad;
    
    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_DOCUMENTO_IDENTIDAD, DE_DOCUMENTO_IDENTIDAD, DE_ABR_DOCUMENTO, NU_CARACTERES, ES_DOCUMENTO_IDENTIDAD"};

    public static final String[] 
      CAMPOS_NO_ID ={"DE_DOCUMENTO_IDENTIDAD, DE_ABR_DOCUMENTO, NU_CARACTERES, ES_DOCUMENTO_IDENTIDAD"};
        
    public static final String[]
        FULL_NOM_CAMPOS_BUSCAR ={"CO_DOCUMENTO_IDENTIDAD, DE_DOCUMENTO_IDENTIDAD, DE_ABR_DOCUMENTO, NU_CARACTERES, ES_DOCUMENTO_IDENTIDAD"};

    public String getCoDocumentoIdentidad() {
        return coDocumentoIdentidad;
    }

    public void setCoDocumentoIdentidad(String coDocumentoIdentidad) {
        this.coDocumentoIdentidad = coDocumentoIdentidad;
    }

    public String getDeAbrDocumento() {
        return deAbrDocumento;
    }

    public void setDeAbrDocumento(String deAbrDocumento) {
        this.deAbrDocumento = deAbrDocumento;
    }

    public String getDeDocumentoIdentidad() {
        return deDocumentoIdentidad;
    }

    public void setDeDocumentoIdentidad(String deDocumentoIdentidad) {
        this.deDocumentoIdentidad = deDocumentoIdentidad;
    }

    public String getEsDocumentoIdentidad() {
        return esDocumentoIdentidad;
    }

    public void setEsDocumentoIdentidad(String esDocuemntoIdentidad) {
        this.esDocumentoIdentidad = esDocuemntoIdentidad;
    }

    public String getNuCaracteres() {
        return nuCaracteres;
    }

    public void setNuCaracteres(String nuCaracteres) {
        this.nuCaracteres = nuCaracteres;
    }

    public TipoDocumento(){
        initBasic();
    }
    
    private void initBasic(){
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
    }    

    @Override
    public String toString() {
//        return  deDocumentoIdentidad;
        return deAbrDocumento;
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
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }
}