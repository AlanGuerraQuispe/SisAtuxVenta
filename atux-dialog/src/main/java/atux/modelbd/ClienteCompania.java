package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class ClienteCompania extends JAbstractModelBD implements Serializable,IModel{    
    private static final long serialVersionUID = 1L; 
    
    public static final String nt = "VTTM_CLIENTE_COMPANIA";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_CLIENTE_COMPANIA"};
    public static final String COLUMNA_ACTIVO = "ES_CLIENTE";
    public static final String[] COLUMNA_ORDER ={"DE_RAZON_SOCIAL"};
    
    private String coCompania;
    private String coClienteCompania;
    private String tiEmpresa;
    private String tiCliente;
    private String deRazonSocial;
    private String nuRucCliente;
    private String tiDocIdentidad;
    private String tipoDocIdentidadTitularTar;
    private String nuDocIdentidad;
    private String apPaternoCliente;
    private String apMaternoCliente;
    private String noCliente;
    private Date   feNacimiento;
    private String deObservCliente;
    private String nuTelReferencia;
    private String nuTelFax;
    private String nuTelMovil;
    private String deEmail;
    private String coFormaPago1;
    private String nuTarjeta1;
    private Date   feVenceTarjeta1;
    private String coFormaPago2;
    private String nuTarjeta2;
    private Date   feVenceTarjeta2;
    private String coEmpleadoInkafarma;
    private String inClienteEspecial;
    private Double pcDctoClienteEspecial;
    private Double vaSaldoCuenta;
    private String esCliente;
    private String idCreaCliente;
    private Date   feCreaCliente;
    private String idModCliente;
    private Date   feModCliente;
    private String nuTransferenciaErp;
    private Date   feTransferenciaErp;
    private String inLimiteCredito;
    private String apPaternoTitularTarj1;
    private String apMaternoTitularTarj1;
    private String noTitularTarj1;

        
    public static final String[]
        FULL_NOM_CAMPOS ={"CO_COMPANIA, CO_CLIENTE_COMPANIA, TI_EMPRESA, TI_CLIENTE, DE_RAZON_SOCIAL," +
                          "NU_RUC_CLIENTE, TI_DOC_IDENTIDAD, TIPO_DOC_IDENTIDAD_TITULAR_TAR, NU_DOC_IDENTIDAD," +
                          "AP_PATERNO_CLIENTE, AP_MATERNO_CLIENTE, NO_CLIENTE, FE_NACIMIENTO, DE_OBSERV_CLIENTE," +
                          "NU_TEL_REFERENCIA, NU_TEL_FAX, NU_TEL_MOVIL, DE_EMAIL, CO_FORMA_PAGO1, NU_TARJETA1," +
                          "FE_VENCE_TARJETA1, CO_FORMA_PAGO2, NU_TARJETA2, FE_VENCE_TARJETA2, CO_EMPLEADO_INKAFARMA," +
                          "IN_CLIENTE_ESPECIAL, PC_DCTO_CLIENTE_ESPECIAL, VA_SALDO_CUENTA, ES_CLIENTE, ID_CREA_CLIENTE," +
                          "FE_CREA_CLIENTE, ID_MOD_CLIENTE, FE_MOD_CLIENTE, NU_TRANSFERENCIA_ERP, FE_TRANSFERENCIA_ERP," +
                          "IN_LIMITE_CREDITO, AP_PATERNO_TITULAR_TARJ1, AP_MATERNO_TITULAR_TARJ1, NO_TITULAR_TARJ1"};
    
    public static final String[]
           CAMPOS_NO_ID ={"TI_EMPRESA, TI_CLIENTE, DE_RAZON_SOCIAL," +
                          "NU_RUC_CLIENTE, TI_DOC_IDENTIDAD, TIPO_DOC_IDENTIDAD_TITULAR_TAR, NU_DOC_IDENTIDAD," +
                          "AP_PATERNO_CLIENTE, AP_MATERNO_CLIENTE, NO_CLIENTE, FE_NACIMIENTO, DE_OBSERV_CLIENTE," +
                          "NU_TEL_REFERENCIA, NU_TEL_FAX, NU_TEL_MOVIL, DE_EMAIL, CO_FORMA_PAGO1, NU_TARJETA1," +
                          "FE_VENCE_TARJETA1, CO_FORMA_PAGO2, NU_TARJETA2, FE_VENCE_TARJETA2, CO_EMPLEADO_INKAFARMA," +
                          "IN_CLIENTE_ESPECIAL, PC_DCTO_CLIENTE_ESPECIAL, VA_SALDO_CUENTA, ES_CLIENTE, ID_CREA_CLIENTE," +
                          "FE_CREA_CLIENTE, ID_MOD_CLIENTE, FE_MOD_CLIENTE, NU_TRANSFERENCIA_ERP, FE_TRANSFERENCIA_ERP," +
                          "IN_LIMITE_CREDITO, AP_PATERNO_TITULAR_TARJ1, AP_MATERNO_TITULAR_TARJ1, NO_TITULAR_TARJ1"};

    public ClienteCompania() {
        initBasic();
    }

    
    public String getApMaternoCliente() {
        return apMaternoCliente;
    }

    public void setApMaternoCliente(String apMaternoCliente) {
        this.apMaternoCliente = apMaternoCliente;
    }

    public String getApMaternoTitularTarj1() {
        return apMaternoTitularTarj1;
    }

    public void setApMaternoTitularTarj1(String apMaternoTitularTarj1) {
        this.apMaternoTitularTarj1 = apMaternoTitularTarj1;
    }

    public String getApPaternoCliente() {
        return apPaternoCliente;
    }

    public void setApPaternoCliente(String apPaternoCliente) {
        this.apPaternoCliente = apPaternoCliente;
    }

    public String getApPaternoTitularTarj1() {
        return apPaternoTitularTarj1;
    }

    public void setApPaternoTitularTarj1(String apPaternoTitularTarj1) {
        this.apPaternoTitularTarj1 = apPaternoTitularTarj1;
    }

    public String getCoClienteCompania() {
        return coClienteCompania;
    }

    public void setCoClienteCompania(String coClienteCompania) {
        this.coClienteCompania = coClienteCompania;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoEmpleadoInkafarma() {
        return coEmpleadoInkafarma;
    }

    public void setCoEmpleadoInkafarma(String coEmpleadoInkafarma) {
        this.coEmpleadoInkafarma = coEmpleadoInkafarma;
    }

    public String getCoFormaPago1() {
        return coFormaPago1;
    }

    public void setCoFormaPago1(String coFormaPago1) {
        this.coFormaPago1 = coFormaPago1;
    }

    public String getCoFormaPago2() {
        return coFormaPago2;
    }

    public void setCoFormaPago2(String coFormaPago2) {
        this.coFormaPago2 = coFormaPago2;
    }

    public String getDeEmail() {
        return deEmail;
    }

    public void setDeEmail(String deEmail) {
        this.deEmail = deEmail;
    }

    public String getDeObservCliente() {
        return deObservCliente;
    }

    public void setDeObservCliente(String deObservCliente) {
        this.deObservCliente = deObservCliente;
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

    public Date getFeTransferenciaErp() {
        return feTransferenciaErp;
    }

    public void setFeTransferenciaErp(Date feTransferenciaErp) {
        this.feTransferenciaErp = feTransferenciaErp;
    }

    public Date getFeVenceTarjeta1() {
        return feVenceTarjeta1;
    }

    public void setFeVenceTarjeta1(Date feVenceTarjeta1) {
        this.feVenceTarjeta1 = feVenceTarjeta1;
    }

    public Date getFeVenceTarjeta2() {
        return feVenceTarjeta2;
    }

    public void setFeVenceTarjeta2(Date feVenceTarjeta2) {
        this.feVenceTarjeta2 = feVenceTarjeta2;
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

    public String getInLimiteCredito() {
        return inLimiteCredito;
    }

    public void setInLimiteCredito(String inLimiteCredito) {
        this.inLimiteCredito = inLimiteCredito;
    }

    public String getNoCliente() {
        return noCliente;
    }

    public void setNoCliente(String noCliente) {
        this.noCliente = noCliente;
    }

    public String getNoTitularTarj1() {
        return noTitularTarj1;
    }

    public void setNoTitularTarj1(String noTitularTarj1) {
        this.noTitularTarj1 = noTitularTarj1;
    }

    public String getNuDocIdentidad() {
        return nuDocIdentidad;
    }

    public void setNuDocIdentidad(String nuDocIdentidad) {
        this.nuDocIdentidad = nuDocIdentidad;
    }

    public String getNuRucCliente() {
        return nuRucCliente;
    }

    public void setNuRucCliente(String nuRucCliente) {
        this.nuRucCliente = nuRucCliente;
    }

    public String getNuTarjeta1() {
        return nuTarjeta1;
    }

    public void setNuTarjeta1(String nuTarjeta1) {
        this.nuTarjeta1 = nuTarjeta1;
    }

    public String getNuTarjeta2() {
        return nuTarjeta2;
    }

    public void setNuTarjeta2(String nuTarjeta2) {
        this.nuTarjeta2 = nuTarjeta2;
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

    public String getNuTransferenciaErp() {
        return nuTransferenciaErp;
    }

    public void setNuTransferenciaErp(String nuTransferenciaErp) {
        this.nuTransferenciaErp = nuTransferenciaErp;
    }

    public Double getPcDctoClienteEspecial() {
        return pcDctoClienteEspecial;
    }

    public void setPcDctoClienteEspecial(Double pcDctoClienteEspecial) {
        this.pcDctoClienteEspecial = pcDctoClienteEspecial;
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

    public String getTiEmpresa() {
        return tiEmpresa;
    }

    public void setTiEmpresa(String tiEmpresa) {
        this.tiEmpresa = tiEmpresa;
    }

    public String getTipoDocIdentidadTitularTar() {
        return tipoDocIdentidadTitularTar;
    }

    public void setTipoDocIdentidadTitularTar(String tipoDocIdentidadTitularTar) {
        this.tipoDocIdentidadTitularTar = tipoDocIdentidadTitularTar;
    }

    public Double getVaSaldoCuenta() {
        return vaSaldoCuenta;
    }

    public void setVaSaldoCuenta(Double vaSaldoCuenta) {
        this.vaSaldoCuenta = vaSaldoCuenta;
    }

    

    public ClienteCompania(String coCompania, String coClienteCompania, String tiEmpresa, String tiCliente, String deRazonSocial, String nuRucCliente, String tiDocIdentidad, String tipoDocIdentidadTitularTar, String nuDocIdentidad, String apPaternoCliente, String apMaternoCliente, String noCliente,Date feNacimiento, String deObservCliente,String nuTelReferencia,String nuTelFax, String nuTelMovil, String deEmail, String coFormaPago1, String nuTarjeta1, Date feVenceTarjeta1,String coFormaPago2, String nuTarjeta2, Date feVenceTarjeta2, String coEmpleadoInkafarma, String inClienteEspecial, Double pcDctoClienteEspecial, Double vaSaldoCuenta, String esCliente, String idCreaCliente, Date feCreaCliente, String idModCliente, Date feModCliente, String nuTransferenciaErp, Date feTransferenciaErp, String inLimiteCredito,String apPaternoTitularTarj1,String apMaternoTitularTarj1,String noTitularTarj1) {
        this.coCompania = coCompania;
        this.coClienteCompania = coClienteCompania;
        this.tiEmpresa = tiEmpresa;
        this.tiCliente = tiCliente;
        this.deRazonSocial = deRazonSocial;
        this.nuRucCliente = nuRucCliente;
        this.tiDocIdentidad = tiDocIdentidad;
        this.tipoDocIdentidadTitularTar = tipoDocIdentidadTitularTar;
        this.nuDocIdentidad = nuDocIdentidad;
        this.apPaternoCliente = apPaternoCliente;
        this.apMaternoCliente = apMaternoCliente;
        this.noCliente = noCliente;
        this.feNacimiento = feNacimiento;
        this.deObservCliente = deObservCliente;
        this.nuTelReferencia = nuTelReferencia;
        this.nuTelFax = nuTelFax;
        this.nuTelMovil = nuTelMovil;
        this.deEmail = deEmail;
        this.coFormaPago1 = coFormaPago1;
        this.nuTarjeta1 = nuTarjeta1;
        this.feVenceTarjeta1 = feVenceTarjeta1;
        this.coFormaPago2 = coFormaPago2;
        this.nuTarjeta2 = nuTarjeta2;
        this.feVenceTarjeta2 = feVenceTarjeta2;
        this.coEmpleadoInkafarma = coEmpleadoInkafarma;
        this.inClienteEspecial = inClienteEspecial;
        this.pcDctoClienteEspecial = pcDctoClienteEspecial;
        this.vaSaldoCuenta = vaSaldoCuenta;
        this.esCliente = esCliente;
        this.idCreaCliente = idCreaCliente;
        this.feCreaCliente = feCreaCliente;
        this.idModCliente = idModCliente;
        this.feModCliente = feModCliente;
        this.nuTransferenciaErp = nuTransferenciaErp;
        this.feTransferenciaErp = feTransferenciaErp;
        this.inLimiteCredito = inLimiteCredito;
        this.apPaternoTitularTarj1 = apPaternoTitularTarj1;
        this.apMaternoTitularTarj1 = apMaternoTitularTarj1;
        this.noTitularTarj1 = noTitularTarj1;
        initBasic();
    }        
    
    private void initBasic()
    {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
    }    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getPrimaryKey() != null ? getPrimaryKey().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {   
        
        if (!(object instanceof ClienteCompania)) {
            return false;
        }
        ClienteCompania other = (ClienteCompania) object;
        if ((this.getPrimaryKey() == null && other.getPrimaryKey() != null) || (this.getPrimaryKey() != null && !Arrays.equals(this.getPrimaryKey(), other.getPrimaryKey()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  deRazonSocial ;       
    }
    
}
