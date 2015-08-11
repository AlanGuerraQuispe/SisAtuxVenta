package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class ImpuestoIGV extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    
    public static final String nt = "VTTR_IMPUESTO";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_IMPUESTO"};
    public static final String COLUMNA_ACTIVO = "ES_IMPUESTO";
    public static final String[] COLUMNA_ORDER ={"DE_IMPUESTO"};
    
    private String  coCompania;
    private String  coImpuesto;
    private Integer nuOrdenFila;
    private String  deCortaImpuesto;
    private String  deImpuesto;
    private Integer pcImpuesto;
    private String  inOperacionImpuesto;
    private String  esImpuesto;
    private String  idCreaImpuesto;
    private Date    feCreaImpuesto;
    private String  idModImpuesto;
    private Date    feModImpuesto;


    
    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_COMPANIA, CO_IMPUESTO, NU_ORDEN_FILA, DE_CORTA_IMPUESTO, DE_IMPUESTO, PC_IMPUESTO," +
                        "IN_OPERACION_IMPUESTO, ES_IMPUESTO, ID_CREA_IMPUESTO, FE_CREA_IMPUESTO, ID_MOD_IMPUESTO, FE_MOD_IMPUESTO"};

    public static final String[] 
      CAMPOS_NO_ID ={"NU_ORDEN_FILA, DE_CORTA_IMPUESTO, DE_IMPUESTO, PC_IMPUESTO, IN_OPERACION_IMPUESTO, " +
                     "ES_IMPUESTO, ID_CREA_IMPUESTO, FE_CREA_IMPUESTO, ID_MOD_IMPUESTO, FE_MOD_IMPUESTO"};
        
    public static final String[]
        FULL_NOM_CAMPOS_BUSCAR ={"CO_COMPANIA, CO_IMPUESTO, DE_IMPUESTO, PC_IMPUESTO, ES_IMPUESTO"};

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoImpuesto() {
        return coImpuesto;
    }

    public void setCoImpuesto(String coImpuesto) {
        this.coImpuesto = coImpuesto;
    }

    public String getDeCortaImpuesto() {
        return deCortaImpuesto;
    }

    public void setDeCortaImpuesto(String deCortaImpuesto) {
        this.deCortaImpuesto = deCortaImpuesto;
    }

    public String getDeImpuesto() {
        return deImpuesto;
    }

    public void setDeImpuesto(String deImpuesto) {
        this.deImpuesto = deImpuesto;
    }

    public String getEsImpuesto() {
        return esImpuesto;
    }

    public void setEsImpuesto(String esImpuesto) {
        this.esImpuesto = esImpuesto;
    }

    public Date getFeCreaImpuesto() {
        return feCreaImpuesto;
    }

    public void setFeCreaImpuesto(Date feCreaImpuesto) {
        this.feCreaImpuesto = feCreaImpuesto;
    }

    public Date getFeModImpuesto() {
        return feModImpuesto;
    }

    public void setFeModImpuesto(Date feModImpuesto) {
        this.feModImpuesto = feModImpuesto;
    }

    public String getIdCreaImpuesto() {
        return idCreaImpuesto;
    }

    public void setIdCreaImpuesto(String idCreaImpuesto) {
        this.idCreaImpuesto = idCreaImpuesto;
    }

    public String getIdModImpuesto() {
        return idModImpuesto;
    }

    public void setIdModImpuesto(String idModImpuesto) {
        this.idModImpuesto = idModImpuesto;
    }

    public String getInOperacionImpuesto() {
        return inOperacionImpuesto;
    }

    public void setInOperacionImpuesto(String inOperacionImpuesto) {
        this.inOperacionImpuesto = inOperacionImpuesto;
    }

    public Integer getNuOrdenFila() {
        return nuOrdenFila;
    }

    public void setNuOrdenFila(Integer nuOrdenFila) {
        this.nuOrdenFila = nuOrdenFila;
    }

    public Integer getPcImpuesto() {
        return pcImpuesto;
    }

    public void setPcImpuesto(Integer pcImpuesto) {
        this.pcImpuesto = pcImpuesto;
    }
    
    public ImpuestoIGV() {
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
        //return "Impuesto{" + "coImpuesto=" + coImpuesto + ", deImpuesto=" + deImpuesto + '}';
        return " " + pcImpuesto + " %";
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
        if (!(object instanceof ImpuestoIGV)) {
            return false;
        }
        ImpuestoIGV other = (ImpuestoIGV) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }          
    
}
