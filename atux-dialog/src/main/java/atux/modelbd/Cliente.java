package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class Cliente extends JAbstractModelBD implements Serializable,IModel {

    private static final long serialVersionUID = 1L;
    public static final String nt = "VTTR_CLIENTE_LOCAL";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_LOCAL","CO_CLIENTE_LOCAL"};
    public static final String[] COLUMNA_PK2 ={"TI_DOC_IDENTIDAD", "NU_DOC_IDENTIDAD"};
    public static final String COLUMNA_ACTIVO = "ES_CLIENTE";
    public static final String[] COLUMNA_ORDER ={"CO_CLIENTE_LOCAL"};

    private String  coCompania;
    private String  coLocal;
    private String  coClienteLocal;
    private String  tiCliente;
    private String  coCategoriaCliente;
    private String  deRazonSocial;
    private String  tiDocIdentidad;
    private String  nuDocIdentidad;
    private String  apPaternoCliente;
    private String  apMaternoCliente;
    private String  noCliente;
    private Date    feNacimiento;
    private String  nuTelReferencia;
    private String  nuTelFax;
    private String  nuTelMovil;
    private String  deEmail;
    private String  inClienteEspecial;
    private String  coClienteCompania;
    private String  esCliente;
    private String  idCreaCliente;
    private Date    feCreaCliente;
    private String  idModCliente;
    private Date    feModCliente;
    private String  deTipoDocumento;
    private String  nuComprobante;

    public String getNuComprobante() {
        return nuComprobante;
    }

    public void setNuComprobante(String nuComprobante) {
        this.nuComprobante = nuComprobante;
    }

    public static final String[]
            FULL_NOM_CAMPOS ={"CO_COMPANIA, CO_LOCAL, CO_CLIENTE_LOCAL, TI_CLIENTE, CO_CATEGORIA_CLIENTE, DE_RAZON_SOCIAL, TI_DOC_IDENTIDAD, "
            + "NU_DOC_IDENTIDAD, AP_PATERNO_CLIENTE, AP_MATERNO_CLIENTE, NO_CLIENTE, FE_NACIMIENTO, NU_TEL_REFERENCIA, "
            + "NU_TEL_FAX, NU_TEL_MOVIL,DE_EMAIL, IN_CLIENTE_ESPECIAL, CO_CLIENTE_COMPANIA, ES_CLIENTE, ID_CREA_CLIENTE, "
            + "FE_CREA_CLIENTE, ID_MOD_CLIENTE, FE_MOD_CLIENTE"};

    public static final String[]
            CAMPOS_NO_ID ={"TI_CLIENTE, CO_CATEGORIA_CLIENTE, DE_RAZON_SOCIAL, TI_DOC_IDENTIDAD, "
            + "NU_DOC_IDENTIDAD, AP_PATERNO_CLIENTE, AP_MATERNO_CLIENTE, NO_CLIENTE, FE_NACIMIENTO, NU_TEL_REFERENCIA, "
            + "NU_TEL_FAX, NU_TEL_MOVIL,DE_EMAIL, IN_CLIENTE_ESPECIAL, CO_CLIENTE_COMPANIA, ES_CLIENTE, ID_CREA_CLIENTE, "
            + "FE_CREA_CLIENTE, ID_MOD_CLIENTE, FE_MOD_CLIENTE"};

    public String getApMaternoCliente() {
        return apMaternoCliente;
    }

    public void setApMaternoCliente(String apMaternoCliente) {
        this.apMaternoCliente = apMaternoCliente;
    }

    public String getApPaternoCliente() {
        return apPaternoCliente;
    }

    public void setApPaternoCliente(String apPaternoCliente) {
        this.apPaternoCliente = apPaternoCliente;
    }

    public String getCoCategoriaCliente() {
        return coCategoriaCliente;
    }

    public void setCoCategoriaCliente(String coCategoriaCliente) {
        this.coCategoriaCliente = coCategoriaCliente;
    }

    public String getCoClienteCompania() {
        return coClienteCompania;
    }

    public void setCoClienteCompania(String coClienteCompania) {
        this.coClienteCompania = coClienteCompania;
    }

    public String getCoClienteLocal() {
        return coClienteLocal;
    }

    public void setCoClienteLocal(String coClienteLocal) {
        this.coClienteLocal = coClienteLocal;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoLocal() {
        return coLocal;
    }

    public void setCoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public String getDeEmail() {
        return deEmail;
    }

    public void setDeEmail(String deEmail) {
        this.deEmail = deEmail;
    }

    public String getDeRazonSocial() {
        return deRazonSocial;
    }

    public void setDeRazonSocial(String deRazonSocial) {
        this.deRazonSocial = deRazonSocial;
    }

    public String getEsCliente() {
        return esCliente;
    }

    public void setEsCliente(String esCliente) {
        this.esCliente = esCliente;
    }

    public Date getFeCreaCliente() {
        return feCreaCliente;
    }

    public void setFeCreaCliente(Date feCreaCliente) {
        this.feCreaCliente = feCreaCliente;
    }

    public Date getFeModCliente() {
        return feModCliente;
    }

    public void setFeModCliente(Date feModCliente) {
        this.feModCliente = feModCliente;
    }

    public Date getFeNacimiento() {
        return feNacimiento;
    }

    public void setFeNacimiento(Date feNacimiento) {
        this.feNacimiento = feNacimiento;
    }


    public String getIdCreaCliente() {
        return idCreaCliente;
    }

    public void setIdCreaCliente(String idCreaCliente) {
        this.idCreaCliente = idCreaCliente;
    }

    public String getIdModCliente() {
        return idModCliente;
    }

    public void setIdModCliente(String idModCliente) {
        this.idModCliente = idModCliente;
    }

    public String getInClienteEspecial() {
        return inClienteEspecial;
    }

    public void setInClienteEspecial(String inClienteEspecial) {
        this.inClienteEspecial = inClienteEspecial;
    }

    public String getNoCliente() {
        return noCliente;
    }

    public void setNoCliente(String noCliente) {
        this.noCliente = noCliente;
    }

    public String getNuDocIdentidad() {
        return nuDocIdentidad;
    }

    public void setNuDocIdentidad(String nuDocIdentidad) {
        this.nuDocIdentidad = nuDocIdentidad;
    }

    public String getNuTelFax() {
        return nuTelFax;
    }

    public void setNuTelFax(String nuTelFax) {
        this.nuTelFax = nuTelFax;
    }

    public String getNuTelMovil() {
        return nuTelMovil;
    }

    public void setNuTelMovil(String nuTelMovil) {
        this.nuTelMovil = nuTelMovil;
    }

    public String getNuTelReferencia() {
        return nuTelReferencia;
    }

    public void setNuTelReferencia(String nuTelReferencia) {
        this.nuTelReferencia = nuTelReferencia;
    }

    public String getTiCliente() {
        return tiCliente;
    }

    public void setTiCliente(String tiCliente) {
        this.tiCliente = tiCliente;
    }

    public String getTiDocIdentidad() {
        return tiDocIdentidad;
    }

    public void setTiDocIdentidad(String tiDocIdentidad) {
        this.tiDocIdentidad = tiDocIdentidad;
    }

    public String getDeTipoDocumento() {
        return deTipoDocumento;
    }

    public void setDeTipoDocumento(String deTipoDocumento) {
        this.deTipoDocumento = deTipoDocumento;
    }

    public Cliente() {
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
        return  deRazonSocial ;
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
        if (!(object instanceof Cliente)) {
            return false;
        }

        Cliente other = (Cliente) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }
}
