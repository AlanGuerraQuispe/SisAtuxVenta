package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class TipoDeCliente extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;

    public static final String nt = "VTTR_TIPO_CLIENTE";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","TI_CLIENTE"};
    public static final String COLUMNA_ACTIVO = "ES_TIPO_CLIENTE";
    public static final String[] COLUMNA_ORDER ={"DE_TIPO_CLIENTE"};
    
    private String  coCompania;
    private String  tiCliente;
    private Integer nuOrdenFila;
    private String  deCortaTipoCliente;
    private String  deTipoCliente;
    private String  tiPantalla;
    private String  esTipoCliente;
    private String  idCreaTipoCliente;
    private Date    feCreaTipoCliente;
    private String  idModTipoCliente;
    private Date    feModTipoCliente;

    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_COMPANIA,TI_CLIENTE,NU_ORDEN_FILA,DE_CORTA_TIPO_CLIENTE,DE_TIPO_CLIENTE,TI_PANTALLA,ES_TIPO_CLIENTE,"
                      + "ID_CREA_TIPO_CLIENTE,FE_CREA_TIPO_CLIENTE,ID_MOD_TIPO_CLIENTE,FE_MOD_TIPO_CLIENTE"};

    public static final String[] 
      CAMPOS_NO_ID ={"NU_ORDEN_FILA,DE_CORTA_TIPO_CLIENTE,DE_TIPO_CLIENTE,TI_PANTALLA,ES_TIPO_CLIENTE,"
                      + "ID_CREA_TIPO_CLIENTE,FE_CREA_TIPO_CLIENTE,ID_MOD_TIPO_CLIENTE,FE_MOD_TIPO_CLIENTE"};

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getDeCortaTipoCliente() {
        return deCortaTipoCliente;
    }

    public void setDeCortaTipoCliente(String deCortaTipoCliente) {
        this.deCortaTipoCliente = deCortaTipoCliente;
    }

    public String getDeTipoCliente() {
        return deTipoCliente;
    }

    public void setDeTipoCliente(String deTipoCliente) {
        this.deTipoCliente = deTipoCliente;
    }

    public String getEsTipoCliente() {
        return esTipoCliente;
    }

    public void setEsTipoCliente(String esTipoCliente) {
        this.esTipoCliente = esTipoCliente;
    }

    public Date getFeCreaTipoCliente() {
        return feCreaTipoCliente;
    }

    public void setFeCreaTipoCliente(Date feCreaTipoCliente) {
        this.feCreaTipoCliente = feCreaTipoCliente;
    }

    public Date getFeModTipoCliente() {
        return feModTipoCliente;
    }

    public void setFeModTipoCliente(Date feModTipoCliente) {
        this.feModTipoCliente = feModTipoCliente;
    }

    public String getIdCreaTipoCliente() {
        return idCreaTipoCliente;
    }

    public void setIdCreaTipoCliente(String idCreaTipoCliente) {
        this.idCreaTipoCliente = idCreaTipoCliente;
    }

    public String getIdModTipoCliente() {
        return idModTipoCliente;
    }

    public void setIdModTipoCliente(String idModTipoCliente) {
        this.idModTipoCliente = idModTipoCliente;
    }

    public Integer getNuOrdenFila() {
        return nuOrdenFila;
    }

    public void setNuOrdenFila(Integer nuOrdenFila) {
        this.nuOrdenFila = nuOrdenFila;
    }

    public String getTiCliente() {
        return tiCliente;
    }

    public void setTiCliente(String tiCliente) {
        this.tiCliente = tiCliente;
    }

    public String getTiPantalla() {
        return tiPantalla;
    }

    public void setTiPantalla(String tiPantalla) {
        this.tiPantalla = tiPantalla;
    }
            
    public TipoDeCliente() {
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
        return deTipoCliente;
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
        if (!(object instanceof TipoDeCliente)) {
            return false;
        }
        TipoDeCliente other = (TipoDeCliente) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }          
    
}
