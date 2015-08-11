package atux.modelbd;

import atux.core.JAbstractModelBD;
import atux.util.common.AtuxUtility;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class Paises extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    
    public static final String nt = "CMTR_PAIS";
    public static final String[] COLUMNA_PK ={"CO_PAIS"};
    public static final String COLUMNA_ACTIVO = "ES_PAIS";
    public static final String[] COLUMNA_ORDER ={"DE_PAIS"};
    
    private String  coPais;
    private Integer nuOrdenFila;
    private String  deCortaPais;
    private String  dePais;
    private String  esPais;
    private String  idCreaPais;
    private Date    feCreaPais;
    private String  idModPais;
    private Date    feModPais;
    
    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_PAIS, NU_ORDEN_FILA, DE_CORTA_PAIS, DE_PAIS, ES_PAIS, "
                      + "ID_CREA_PAIS, FE_CREA_PAIS, ID_MOD_PAIS, FE_MOD_PAIS"};

    public static final String[] 
      CAMPOS_NO_ID ={"NU_ORDEN_FILA, DE_CORTA_PAIS, DE_PAIS, ES_PAIS, "
                      + "ID_CREA_PAIS, FE_CREA_PAIS, ID_MOD_PAIS, FE_MOD_PAIS"};
        
    public static final String[]
        FULL_NOM_CAMPOS_BUSCAR ={"CO_PAIS, NU_ORDEN_FILA, DE_CORTA_PAIS, DE_PAIS, ES_PAIS"};

    public String getCoPais() {
        return coPais;
    }

    public void setCoPais(String coPais) {
        this.coPais = coPais;
    }

    public String getDeCortaPais() {
        return deCortaPais;
    }

    public void setDeCortaPais(String deCortaPais) {
        this.deCortaPais = deCortaPais;
    }

    public String getDePais() {
        return dePais;
    }

    public void setDePais(String dePais) {
        this.dePais = dePais;
    }

    public String getEsPais() {
        return esPais;
    }

    public void setEsPais(String esPais) {
        this.esPais = esPais;
    }

    public Date getFeCreaPais() {
        return feCreaPais;
    }

    public void setFeCreaPais(Date feCreaPais) {
        this.feCreaPais = feCreaPais;
    }

    public Date getFeModPais() {
        return feModPais;
    }

    public void setFeModPais(Date feModPais) {
        this.feModPais = feModPais;
    }

    public String getIdCreaPais() {
        return idCreaPais;
    }

    public void setIdCreaPais(String idCreaPais) {
        this.idCreaPais = idCreaPais;
    }

    public String getIdModPais() {
        return idModPais;
    }

    public void setIdModPais(String idModPais) {
        this.idModPais = idModPais;
    }

    public Integer getNuOrdenFila() {
        return nuOrdenFila;
    }

    public void setNuOrdenFila(Integer nuOrdenFila) {
        this.nuOrdenFila = nuOrdenFila;
    }
    
    

    public Paises() {
        initBasic();
    }
    
    private void initBasic()
    {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
    }    
    
//    @Override
//    public String toString() {
//        return "tCambio{" + "nu_Sec_Tipo_Cambio=" + nuSecTipoCambio + ", fe_tipo_cambio=" + feTipoCambio + '}';
//    } 
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (primaryKey != null ? primaryKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paises)) {
            return false;
        }
        Paises other = (Paises) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }          
    
}
