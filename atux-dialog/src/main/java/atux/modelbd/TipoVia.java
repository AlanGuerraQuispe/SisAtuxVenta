package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class TipoVia extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;

    public static final String nt = "CMTR_TIPO_VIA";
    public static final String[] COLUMNA_PK ={"TI_VIA"};
    public static final String COLUMNA_ACTIVO = "ES_TIPO_VIA";
    public static final String[] COLUMNA_ORDER ={"DE_TIPO_VIA"};

    private String  tiVia;
    private Integer nuOrdenFila;
    private String  deCortaTipoVia;
    private String  deTipoVia;
    private String  esTipoVia;
    private String  idCreaTipoVia;
    private Date    feCreaTipoVia;
    private String  idModTipoVia;
    private Date    feModTipoVia;


    public static final String[] 
      FULL_NOM_CAMPOS ={"TI_VIA, NU_ORDEN_FILA, DE_CORTA_TIPO_VIA, DE_TIPO_VIA, ES_TIPO_VIA, ID_CREA_TIPO_VIA, FE_CREA_TIPO_VIA, ID_MOD_TIPO_VIA, FE_MOD_TIPO_VIA"};

    public static final String[] 
      CAMPOS_NO_ID ={"NU_ORDEN_FILA, DE_CORTA_TIPO_VIA, DE_TIPO_VIA, ES_TIPO_VIA, ID_CREA_TIPO_VIA, FE_CREA_TIPO_VIA, ID_MOD_TIPO_VIA, FE_MOD_TIPO_VIA"};

    public String getDeCortaTipoVia() {
        return deCortaTipoVia;
    }

    public void setDeCortaTipoVia(String deCortaTipoVia) {
        this.deCortaTipoVia = deCortaTipoVia;
    }

    public String getDeTipoVia() {
        return deTipoVia;
    }

    public void setDeTipoVia(String deTipoVia) {
        this.deTipoVia = deTipoVia;
    }

    public String getEsTipoVia() {
        return esTipoVia;
    }

    public void setEsTipoVia(String esTipoVia) {
        this.esTipoVia = esTipoVia;
    }

    public Date getFeCreaTipoVia() {
        return feCreaTipoVia;
    }

    public void setFeCreaTipoVia(Date feCreaTipoVia) {
        this.feCreaTipoVia = feCreaTipoVia;
    }

    public Date getFeModTipoVia() {
        return feModTipoVia;
    }

    public void setFeModTipoVia(Date feModTipoVia) {
        this.feModTipoVia = feModTipoVia;
    }

    public String getIdCreaTipoVia() {
        return idCreaTipoVia;
    }

    public void setIdCreaTipoVia(String idCreaTipoVia) {
        this.idCreaTipoVia = idCreaTipoVia;
    }

    public String getIdModTipoVia() {
        return idModTipoVia;
    }

    public void setIdModTipoVia(String idModTipoVia) {
        this.idModTipoVia = idModTipoVia;
    }

    public Integer getNuOrdenFila() {
        return nuOrdenFila;
    }

    public void setNuOrdenFila(Integer nuOrdenFila) {
        this.nuOrdenFila = nuOrdenFila;
    }

    public String getTiVia() {
        return tiVia;
    }

    public void setTiVia(String tiVia) {
        this.tiVia = tiVia;
    }

    public TipoVia() {
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
        return deTipoVia;
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
        if (!(object instanceof TipoVia)) {
            return false;
        }
        TipoVia other = (TipoVia) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }              
}