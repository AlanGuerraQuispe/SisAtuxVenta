package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class TipoPoblacion extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;

    public static final String nt = "CMTR_TIPO_POBLACION";
    public static final String[] COLUMNA_PK ={"TI_POBLACION"};
    public static final String COLUMNA_ACTIVO = "ES_TIPO_POBLACION";
    public static final String[] COLUMNA_ORDER ={"DE_POBLACION"};

    private String  tiPoblacion;
    private Integer nuOrdenFila;
    private String  deCortaPoblacion;
    private String  dePoblacion;
    private String  esTipoPoblacion;
    private String  idCreaTipoPoblacion;
    private Date    feCreaTipoPoblacion;
    private String  idModTipoPoblacion;
    private Date    feModTipoPoblacion;


    public static final String[] 
      FULL_NOM_CAMPOS ={"TI_POBLACION, NU_ORDEN_FILA, DE_CORTA_POBLACION, DE_POBLACION, ES_TIPO_POBLACION, ID_CREA_TIPO_POBLACION, FE_CREA_TIPO_POBLACION, ID_MOD_TIPO_POBLACION, FE_MOD_TIPO_POBLACION"};

    public static final String[] 
      CAMPOS_NO_ID ={"NU_ORDEN_FILA, DE_CORTA_POBLACION, DE_POBLACION, ES_TIPO_POBLACION, ID_CREA_TIPO_POBLACION, FE_CREA_TIPO_POBLACION, ID_MOD_TIPO_POBLACION, FE_MOD_TIPO_POBLACION"};

    public String getDeCortaPoblacion() {
        return deCortaPoblacion;
    }

    public void setDeCortaPoblacion(String deCortaPoblacion) {
        this.deCortaPoblacion = deCortaPoblacion;
    }

    public String getDePoblacion() {
        return dePoblacion;
    }

    public void setDePoblacion(String dePoblacion) {
        this.dePoblacion = dePoblacion;
    }

    public String getEsTipoPoblacion() {
        return esTipoPoblacion;
    }

    public void setEsTipoPoblacion(String esTipoPoblacion) {
        this.esTipoPoblacion = esTipoPoblacion;
    }

    public Date getFeCreaTipoPoblacion() {
        return feCreaTipoPoblacion;
    }

    public void setFeCreaTipoPoblacion(Date feCreaTipoPoblacion) {
        this.feCreaTipoPoblacion = feCreaTipoPoblacion;
    }

    public Date getFeModTipoPoblacion() {
        return feModTipoPoblacion;
    }

    public void setFeModTipoPoblacion(Date feModTipoPoblacion) {
        this.feModTipoPoblacion = feModTipoPoblacion;
    }

    public String getIdCreaTipoPoblacion() {
        return idCreaTipoPoblacion;
    }

    public void setIdCreaTipoPoblacion(String idCreaTipoPoblacion) {
        this.idCreaTipoPoblacion = idCreaTipoPoblacion;
    }

    public String getIdModTipoPoblacion() {
        return idModTipoPoblacion;
    }

    public void setIdModTipoPoblacion(String idModTipoPoblacion) {
        this.idModTipoPoblacion = idModTipoPoblacion;
    }

    public Integer getNuOrdenFila() {
        return nuOrdenFila;
    }

    public void setNuOrdenFila(Integer nuOrdenFila) {
        this.nuOrdenFila = nuOrdenFila;
    }

    public String getTiPoblacion() {
        return tiPoblacion;
    }

    public void setTiPoblacion(String tiPoblacion) {
        this.tiPoblacion = tiPoblacion;
    }
    
    public TipoPoblacion() {
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
        //return "tCambio{" + "nu_Sec_Tipo_Cambio=" + nuSecTipoCambio + ", fe_tipo_cambio=" + feTipoCambio + '}';
        return dePoblacion;
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
        if (!(object instanceof TipoPoblacion)) {
            return false;
        }
        TipoPoblacion other = (TipoPoblacion) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }              
}