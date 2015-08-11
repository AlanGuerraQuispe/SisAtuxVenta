package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class ParametroReposicion extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    
    public static final String nt = "LGTR_PARAMETROS_REPOSICION";
    
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_LOCAL","NU_ORDEN_PARAMETRO","TI_PARAMETRO","CO_CODIGO","NU_SECUENCIA"};
    public static final String COLUMNA_ACTIVO = "ES_PARAMETRO";
    public static final String[] COLUMNA_ORDER ={"NU_SECUENCIA"};
    
    
    private String  coCompania;
    private String  coLocal;
    private Integer nuOrdenParametro;
    private String  tiParametro;
    private String  coCodigo;
    private Integer nuSecuencia;
    private Integer nuMinimo;
    private Integer nuMaximo;
    private Integer nuPeriodo;
    private Date    feInicio;
    private Date    feFinal;
    private String  esParametro;
    private String  idCreaParametro;
    private Date    feCreaParametro;
    private String  idModParametro;
    private Date    feModParametro;

    
    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_COMPANIA, CO_LOCAL, NU_ORDEN_PARAMETRO, TI_PARAMETRO, CO_CODIGO, NU_SECUENCIA, NU_MINIMO, NU_MAXIMO, NU_PERIODO, "
                      + "FE_INICIO, FE_FINAL, ES_PARAMETRO, ID_CREA_PARAMETRO, FE_CREA_PARAMETRO,ID_MOD_PARAMETRO, FE_MOD_PARAMETRO "};
    
    public static final String[] 
      CAMPOS_NO_ID ={"NU_MINIMO,NU_MAXIMO,NU_PERIODO,FE_INICIO,FE_FINAL, "
                      + "ES_PARAMETRO,ID_CREA_PARAMETRO,FE_CREA_PARAMETRO,ID_MOD_PARAMETRO,FE_MOD_PARAMETRO "};
        
    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    
    public String getCoCodigo() {
        return coCodigo;
    }

    public void setCoCodigo(String coCodigo) {
        this.coCodigo = coCodigo;
    }

    public String getEsParametro() {
        return esParametro;
    }

    public void setEsParametro(String esParametro) {
        this.esParametro = esParametro;
    }

    public Date getFeCreaParametro() {
        return feCreaParametro;
    }

    public void setFeCreaParametro(Date feCreaParametro) {
        this.feCreaParametro = feCreaParametro;
    }

    public Date getFeFinal() {
        return feFinal;
    }

    public void setFeFinal(Date feFinal) {
        this.feFinal = feFinal;
    }

    public Date getFeInicio() {
        return feInicio;
    }

    public void setFeInicio(Date feInicio) {
        this.feInicio = feInicio;
    }

    public Date getFeModParametro() {
        return feModParametro;
    }

    public void setFeModParametro(Date feModParametro) {
        this.feModParametro = feModParametro;
    }

    public String getIdCreaParametro() {
        return idCreaParametro;
    }

    public void setIdCreaParametro(String idCreaParametro) {
        this.idCreaParametro = idCreaParametro;
    }

    public String getIdModParametro() {
        return idModParametro;
    }

    public void setIdModParametro(String idModParametro) {
        this.idModParametro = idModParametro;
    }

    public Integer getNuMaximo() {
        return nuMaximo;
    }

    public void setNuMaximo(Integer nuMaximo) {
        this.nuMaximo = nuMaximo;
    }

    public Integer getNuMinimo() {
        return nuMinimo;
    }

    public void setNuMinimo(Integer nuMinimo) {
        this.nuMinimo = nuMinimo;
    }

    public Integer getNuPeriodo() {
        return nuPeriodo;
    }

    public void setNuPeriodo(Integer nuPeriodo) {
        this.nuPeriodo = nuPeriodo;
    }

    public Integer getNuSecuencia() {
        return nuSecuencia;
    }

    public void setNuSecuencia(Integer nuSecuencia) {
        this.nuSecuencia = nuSecuencia;
    }

    public String getTiParametro() {
        return tiParametro;
    }

    public void setTiParametro(String tiParametro) {
        this.tiParametro = tiParametro;
    }

    public String getCoLocal() {
        return coLocal;
    }

    public void setCoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public Integer getNuOrdenParametro() {
        return nuOrdenParametro;
    }

    public void setNuOrdenParametro(Integer nuOrdenParametro) {
        this.nuOrdenParametro = nuOrdenParametro;
    }


    public ParametroReposicion() {
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
        hash += (primaryKey != null ? primaryKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroReposicion)) {
            return false;
        }
        ParametroReposicion other = (ParametroReposicion) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }
}