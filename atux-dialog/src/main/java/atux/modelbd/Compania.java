package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class Compania extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    
    public static final String nt = "CMTM_COMPANIA";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA"};
    public static final String COLUMNA_ACTIVO = "ES_COMPANIA";
    public static final String[] COLUMNA_ORDER ={"DE_COMPANIA"};
    
    private String  coCompania;
    private String  deCortaCompania;
    private String  deCompania;
    private String  nuRucCompania;
    private String  coPais;
    private String  coDepartamento;
    private String  coProvincia;
    private String  coDistrito;
    private String  tiVia;
    private String  noVia;
    private Integer nuVia;
    private String  nuInteriorVia;
    private String  nuManzanaVia;
    private String  nuLoteVia;
    private String  tiPoblacion;
    private String  noPoblacion;
    private String  coTelPais;
    private String  coTelCiudad;
    private String  nuTelNormal;
    private String  nuTelFax;
    private String  apPaternoRespCompania;
    private String  apMaternoRespCompania;
    private String  noRespCompania;
    private String  esCompania;
    private String  idCreaCompania;
    private Date    feCreaCompania;
    private String  idModCompania;
    private Date    feModCompania;
    private String  coSucursal;
    private String  deDireccion;
    private String  deDireccionWeb;
    private String  deEmail;
    
    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_COMPANIA, DE_CORTA_COMPANIA, DE_COMPANIA, NU_RUC_COMPANIA, CO_PAIS, CO_DEPARTAMENTO, CO_PROVINCIA, CO_DISTRITO, "
                      + "TI_VIA, NO_VIA, NU_VIA, NU_INTERIOR_VIA, NU_MANZANA_VIA, NU_LOTE_VIA, TI_POBLACION, NO_POBLACION, CO_TEL_PAIS, "
                      + "CO_TEL_CIUDAD, NU_TEL_NORMAL, NU_TEL_FAX, AP_PATERNO_RESP_COMPANIA, AP_MATERNO_RESP_COMPANIA, NO_RESP_COMPANIA, ES_COMPANIA, "
                      + "ID_CREA_COMPANIA, FE_CREA_COMPANIA, ID_MOD_COMPANIA, FE_MOD_COMPANIA, CO_SUCURSAL, DE_DIRECCION, DE_DIRECCION_WEB, DE_EMAIL"};

    public static final String[] 
      CAMPOS_NO_ID  ={"DE_CORTA_COMPANIA, DE_COMPANIA, NU_RUC_COMPANIA, CO_PAIS, CO_DEPARTAMENTO, CO_PROVINCIA, CO_DISTRITO, "
                    + "TI_VIA, NO_VIA, NU_VIA, NU_INTERIOR_VIA, NU_MANZANA_VIA, NU_LOTE_VIA, TI_POBLACION, NO_POBLACION, CO_TEL_PAIS, "
                    + "CO_TEL_CIUDAD, NU_TEL_NORMAL, NU_TEL_FAX, AP_PATERNO_RESP_COMPANIA, AP_MATERNO_RESP_COMPANIA, NO_RESP_COMPANIA, ES_COMPANIA, "
                    + "ID_CREA_COMPANIA, FE_CREA_COMPANIA, ID_MOD_COMPANIA, FE_MOD_COMPANIA, CO_SUCURSAL, DE_DIRECCION, DE_DIRECCION_WEB, DE_EMAIL"};
        
    public static final String[]
        FULL_NOM_CAMPOS_BUSCAR ={"Co_Compania, DE_LOCAL"};

    public String getApMaternoRespCompania() {
        return apMaternoRespCompania;
    }

    public void setApMaternoRespCompania(String apMaternoRespCompania) {
        this.apMaternoRespCompania = apMaternoRespCompania;
    }

    public String getApPaternoRespCompania() {
        return apPaternoRespCompania;
    }

    public void setApPaternoRespCompania(String apPaternoRespCompania) {
        this.apPaternoRespCompania = apPaternoRespCompania;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoDepartamento() {
        return coDepartamento;
    }

    public void setCoDepartamento(String coDepartamento) {
        this.coDepartamento = coDepartamento;
    }

    public String getCoDistrito() {
        return coDistrito;
    }

    public void setCoDistrito(String coDistrito) {
        this.coDistrito = coDistrito;
    }

    public String getCoPais() {
        return coPais;
    }

    public void setCoPais(String coPais) {
        this.coPais = coPais;
    }

    public String getCoProvincia() {
        return coProvincia;
    }

    public void setCoProvincia(String coProvincia) {
        this.coProvincia = coProvincia;
    }

    public String getCoSucursal() {
        return coSucursal;
    }

    public void setCoSucursal(String coSucursal) {
        this.coSucursal = coSucursal;
    }

    public String getCoTelCiudad() {
        return coTelCiudad;
    }

    public void setCoTelCiudad(String coTelCiudad) {
        this.coTelCiudad = coTelCiudad;
    }

    public String getCoTelPais() {
        return coTelPais;
    }

    public void setCoTelPais(String coTelPais) {
        this.coTelPais = coTelPais;
    }

    public String getDeCompania() {
        return deCompania;
    }

    public void setDeCompania(String deCompania) {
        this.deCompania = deCompania;
    }

    public String getDeCortaCompania() {
        return deCortaCompania;
    }

    public void setDeCortaCompania(String deCortaCompania) {
        this.deCortaCompania = deCortaCompania;
    }

    public String getDeDireccion() {
        return deDireccion;
    }

    public void setDeDireccion(String deDireccion) {
        this.deDireccion = deDireccion;
    }

    public String getDeDireccionWeb() {
        return deDireccionWeb;
    }

    public void setDeDireccionWeb(String deDireccionWeb) {
        this.deDireccionWeb = deDireccionWeb;
    }

    public String getEsCompania() {
        return esCompania;
    }

    public void setEsCompania(String esCompania) {
        this.esCompania = esCompania;
    }

    public Date getFeCreaCompania() {
        return feCreaCompania;
    }

    public void setFeCreaCompania(Date feCreaCompania) {
        this.feCreaCompania = feCreaCompania;
    }

    public Date getFeModCompania() {
        return feModCompania;
    }

    public void setFeModCompania(Date feModCompania) {
        this.feModCompania = feModCompania;
    }

    public String getIdCreaCompania() {
        return idCreaCompania;
    }

    public void setIdCreaCompania(String idCreaCompania) {
        this.idCreaCompania = idCreaCompania;
    }

    public String getIdModCompania() {
        return idModCompania;
    }

    public void setIdModCompania(String idModCompania) {
        this.idModCompania = idModCompania;
    }

    public String getNoPoblacion() {
        return noPoblacion;
    }

    public void setNoPoblacion(String noPoblacion) {
        this.noPoblacion = noPoblacion;
    }

    public String getNoRespCompania() {
        return noRespCompania;
    }

    public void setNoRespCompania(String noRespCompania) {
        this.noRespCompania = noRespCompania;
    }

    public String getNoVia() {
        return noVia;
    }

    public void setNoVia(String noVia) {
        this.noVia = noVia;
    }

    public String getNuInteriorVia() {
        return nuInteriorVia;
    }

    public void setNuInteriorVia(String nuInteriorVia) {
        this.nuInteriorVia = nuInteriorVia;
    }

    public String getNuLoteVia() {
        return nuLoteVia;
    }

    public void setNuLoteVia(String nuLoteVia) {
        this.nuLoteVia = nuLoteVia;
    }

    public String getNuManzanaVia() {
        return nuManzanaVia;
    }

    public void setNuManzanaVia(String nuManzanaVia) {
        this.nuManzanaVia = nuManzanaVia;
    }

    public String getNuRucCompania() {
        return nuRucCompania;
    }

    public void setNuRucCompania(String nuRucCompania) {
        this.nuRucCompania = nuRucCompania;
    }

    public String getNuTelFax() {
        return nuTelFax;
    }

    public void setNuTelFax(String nuTelFax) {
        this.nuTelFax = nuTelFax;
    }

    public String getNuTelNormal() {
        return nuTelNormal;
    }

    public void setNuTelNormal(String nuTelNormal) {
        this.nuTelNormal = nuTelNormal;
    }

    public Integer getNuVia() {
        return nuVia;
    }

    public void setNuVia(Integer nuVia) {
        this.nuVia = nuVia;
    }

    public String getTiPoblacion() {
        return tiPoblacion;
    }

    public void setTiPoblacion(String tiPoblacion) {
        this.tiPoblacion = tiPoblacion;
    }

    public String getTiVia() {
        return tiVia;
    }

    public void setTiVia(String tiVia) {
        this.tiVia = tiVia;
    }

    public String getDeEmail() {
        return deEmail;
    }

    public void setDeEmail(String deEmail) {
        this.deEmail = deEmail;
    }
    
    public Compania() {
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
        return "mCompania{" + "CO_COMPANIA=" + coCompania + ", DE_COMPANIA=" + deCompania + '}';
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
        if (!(object instanceof Compania)) {
            return false;
        }
        Compania other = (Compania) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }          
    
}
