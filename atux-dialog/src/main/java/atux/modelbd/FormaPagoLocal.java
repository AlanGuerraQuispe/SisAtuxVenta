/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author user
 */
public class FormaPagoLocal extends JAbstractModelBD implements Serializable,IModel{    
    private static final long serialVersionUID = 1L; 
    
    public static final String nt = "VTTR_FORMA_PAGO_LOCAL";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_LOCAL","CO_FORMA_PAGO"};
    public static final String COLUMNA_ACTIVO = "ES_FORMA_PAGO";
    public static final String[] COLUMNA_ORDER = {"CO_LOCAL"};
    
    private String coCompania;
    private String coLocal;
    private String coFormaPago;
    private String esFormaPago;
    private String idCreaFormaPagoLocal;
    private Date feCreaFormaPagoLocal;
    private String idModFormaPagoLocal;
    private Date feModFormaPagoLocal;
    private String deLocal;
    
    public static final String[]
        FULL_NOM_CAMPOS ={"CO_COMPANIA, CO_LOCAL, CO_FORMA_PAGO, ES_FORMA_PAGO, ID_CREA_FORMA_PAGO_LOCAL, "
                        + "FE_CREA_FORMA_PAGO_LOCAL, ID_MOD_FORMA_PAGO_LOCAL, FE_MOD_FORMA_PAGO_LOCAL"};

    public static final String[]
           CAMPOS_NO_ID ={"ES_FORMA_PAGO, ID_CREA_FORMA_PAGO_LOCAL, "
                        + "FE_CREA_FORMA_PAGO_LOCAL, ID_MOD_FORMA_PAGO_LOCAL, FE_MOD_FORMA_PAGO_LOCAL"};
    
    

    public FormaPagoLocal() {
         initBasic();
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoFormaPago() {
        return coFormaPago;
    }

    public void setCoFormaPago(String coFormaPago) {
        this.coFormaPago = coFormaPago;
    }

    public String getCoLocal() {
        return coLocal;
    }

    public void setCoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public String getEsFormaPago() {
        return esFormaPago;
    }

    public void setEsFormaPago(String esFormaPago) {
        this.esFormaPago = esFormaPago;
    }

    public Date getFeCreaFormaPagoLocal() {
        return feCreaFormaPagoLocal;
    }

    public void setFeCreaFormaPagoLocal(Date feCreaFormaPagoLocal) {
        this.feCreaFormaPagoLocal = feCreaFormaPagoLocal;
    }

    public Date getFeModFormaPagoLocal() {
        return feModFormaPagoLocal;
    }

    public void setFeModFormaPagoLocal(Date feModFormaPagoLocal) {
        this.feModFormaPagoLocal = feModFormaPagoLocal;
    }

    public String getIdCreaFormaPagoLocal() {
        return idCreaFormaPagoLocal;
    }

    public void setIdCreaFormaPagoLocal(String idCreaFormaPagoLocal) {
        this.idCreaFormaPagoLocal = idCreaFormaPagoLocal;
    }

    public String getIdModFormaPagoLocal() {
        return idModFormaPagoLocal;
    }

    public void setIdModFormaPagoLocal(String idModFormaPagoLocal) {
        this.idModFormaPagoLocal = idModFormaPagoLocal;
    }

    public String getDeLocal() {
        return deLocal;
    }

    public void setDeLocal(String deLocal) {
        this.deLocal = deLocal;
    }

    
    
    public FormaPagoLocal(String coCompania, String coLocal, String coFormaPago, String esFormaPago, String idCreaFormaPagoLocal, Date feCreaFormaPagoLocal, String idModFormaPagoLocal, Date feModFormaPagoLocal) {
        this.coCompania = coCompania;
        this.coLocal = coLocal;
        this.coFormaPago = coFormaPago;
        this.esFormaPago = esFormaPago;
        this.idCreaFormaPagoLocal = idCreaFormaPagoLocal;
        this.feCreaFormaPagoLocal = feCreaFormaPagoLocal;
        this.idModFormaPagoLocal = idModFormaPagoLocal;
        this.feModFormaPagoLocal = feModFormaPagoLocal;
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
        
        if (!(object instanceof FormaPagoLocal)) {
            return false;
        }
        FormaPagoLocal other = (FormaPagoLocal) object;
        if ((this.getPrimaryKey() == null && other.getPrimaryKey() != null) || (this.getPrimaryKey() != null && !Arrays.equals(this.getPrimaryKey(), other.getPrimaryKey()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  coLocal +" "+ coFormaPago ;        
    }
}
