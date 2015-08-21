package atux.modelbd;

import atux.core.JAbstractModelBD;
import atux.util.common.AtuxUtility;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class TipoDeCambio extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    
    public static final String nt = "CNTR_TIPO_CAMBIO";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_MONEDA","NU_SEC_TIPO_CAMBIO"};
    public static final String COLUMNA_ACTIVO = "ES_TIPO_CAMBIO";
    public static final String[] COLUMNA_ORDER ={"FE_TIPO_CAMBIO desc"};
    
    private String  coCompania;
    private String  coMoneda;
    private String  nuSecTipoCambio;
    private String  coMonedaPais;
    private Date    feTipoCambio;
    private double  vaCambioCompraInka;
    private double  vaCambioVentaInka;
    private String  esTipoCambio;
    private String  idCreaTipoCambio;
    private Date    feCreaTipoCambio;
    private String  idModTipoCambio;
    private Date    feModTipoCambio;
    
    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_COMPANIA, CO_MONEDA, NU_SEC_TIPO_CAMBIO, CO_MONEDA_PAIS, FE_TIPO_CAMBIO,"
                      + "VA_CAMBIO_COMPRA_INKA, VA_CAMBIO_VENTA_INKA, ES_TIPO_CAMBIO, "
                      + "ID_CREA_TIPO_CAMBIO, FE_CREA_TIPO_CAMBIO, ID_MOD_TIPO_CAMBIO, FE_MOD_TIPO_CAMBIO"};

    public static final String[] 
      CAMPOS_NO_ID ={"CO_MONEDA_PAIS, FE_TIPO_CAMBIO,"
                      + "VA_CAMBIO_COMPRA_INKA, VA_CAMBIO_VENTA_INKA, ES_TIPO_CAMBIO, "
                      + "ID_CREA_TIPO_CAMBIO, FE_CREA_TIPO_CAMBIO, ID_MOD_TIPO_CAMBIO, FE_MOD_TIPO_CAMBIO"};
        
    public static final String[]
        FULL_NOM_CAMPOS_BUSCAR ={"Co_Compania, CO_MONEDA, NU_SEC_TIPO_CAMBIO, CO_MONEDA_PAIS, FE_TIPO_CAMBIO"+
                                 "VA_CAMBIO_COMPRA_INKA, VA_CAMBIO_VENTA_INKA"};
    
    
    public String getcoCompania() {
        return coCompania;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoMoneda() {
        return coMoneda;
    }

    public void setCoMoneda(String coMoneda) {
        this.coMoneda = coMoneda;
    }

    public String getCoMonedaPais() {
        return coMonedaPais;
    }

    public void setCoMonedaPais(String coMonedaPais) {
        this.coMonedaPais = coMonedaPais;
    }

    public String getEsTipoCambio() {
        return esTipoCambio;
    }

    public void setEsTipoCambio(String esTipoCambio) {
        this.esTipoCambio = esTipoCambio;
    }

    public Date getFeCreaTipoCambio() {
        return feCreaTipoCambio;
    }

    public void setFeCreaTipoCambio(Date feCreaTipoCambio) {
        this.feCreaTipoCambio = feCreaTipoCambio;
    }

    public Date getFeModTipoCambio() {
        return feModTipoCambio;
    }

    public void setFeModTipoCambio(Date feModTipoCambio) {
        this.feModTipoCambio = feModTipoCambio;
    }

    public String getFeTipoCambio() {
        return AtuxUtility.getDateToString(feTipoCambio, "dd/MM/yyyy");
    }

    public void setFeTipoCambio(Date feTipoCambio) {
        this.feTipoCambio = feTipoCambio;
    }

    public String getIdCreaTipoCambio() {
        return idCreaTipoCambio;
    }

    public void setIdCreaTipoCambio(String idCreaTipoCambio) {
        this.idCreaTipoCambio = idCreaTipoCambio;
    }

    public String getIdModTipoCambio() {
        return idModTipoCambio;
    }

    public void setIdModTipoCambio(String idModTipoCambio) {
        this.idModTipoCambio = idModTipoCambio;
    }

    public String getNuSecTipoCambio() {
        return nuSecTipoCambio;
    }

    public void setNuSecTipoCambio(String nuSecTipoCambio) {
        this.nuSecTipoCambio = nuSecTipoCambio;
    }


    public double getVaCambioCompraInka() {
        return vaCambioCompraInka;
    }

    public void setVaCambioCompraInka(Double vaCambioCompraInka) {
        this.vaCambioCompraInka = vaCambioCompraInka;
    }

    public double getVaCambioVentaInka() {
        return vaCambioVentaInka;
    }

    public void setVaCambioVentaInka(Double vaCambioVentaInka) {
        this.vaCambioVentaInka = vaCambioVentaInka;
    }

    public TipoDeCambio() {
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
        return "tCambio{" + "nu_Sec_Tipo_Cambio=" + nuSecTipoCambio + ", fe_tipo_cambio=" + feTipoCambio + '}';
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
        if (!(object instanceof TipoDeCambio)) {
            return false;
        }
        TipoDeCambio other = (TipoDeCambio) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }          
    
}
