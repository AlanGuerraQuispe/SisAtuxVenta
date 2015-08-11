package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class ProductoLote extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;   
    public static final String nt = "LGTR_PRODUCTO_LOTE";

    public static final String[] COLUMNA_PK ={"CO_COMPANIA", "CO_LOCAL", "CO_PRODUCTO", "NU_LOTE"};
    public static final String COLUMNA_ACTIVO = "ES_LOTE";
    
    private String  coCompania;
    private String  coLocal;
    private String  coProducto;
    private Date    feIngreso;
    private Date    feVencimiento;
    private String  nuLote;
    private String  tiDocumentoRecepcion;
    private String  nuDocumentoRecepcion;
    private String  idCreaLote;
    private Date    feCreaLote;
    private String  esLote;
    private String  idModLote;
    private Date    feModLote;


    public static final String[]
          FULL_CAMPOS ={"CO_COMPANIA, CO_LOCAL, CO_PRODUCTO, FE_INGRESO, FE_VENCIMIENTO, NU_LOTE, TI_DOCUMENTO_RECEPCION, " +
                        "NU_DOCUMENTO_RECEPCION, ID_CREA_LOTE, FE_CREA_LOTE, ES_LOTE, ID_MOD_LOTE, FE_MOD_LOTE"};

    public static final String []
        CAMPOS_SIN_ID = {"FE_INGRESO, FE_VENCIMIENTO, TI_DOCUMENTO_RECEPCION, " +
                        "NU_DOCUMENTO_RECEPCION, ID_CREA_LOTE, FE_CREA_LOTE, ES_LOTE, ID_MOD_LOTE, FE_MOD_LOTE"};

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

    public String getCoProducto() {
        return coProducto;
    }

    public void setCoProducto(String coProducto) {
        this.coProducto = coProducto;
    }

    public String getEsLote() {
        return esLote;
    }

    public void setEsLote(String esLote) {
        this.esLote = esLote;
    }

    public Date getFeCreaLote() {
        return feCreaLote;
    }

    public void setFeCreaLote(Date feCreaLote) {
        this.feCreaLote = feCreaLote;
    }

    public Date getFeIngreso() {
        return feIngreso;
    }

    public void setFeIngreso(Date feIngreso) {
        this.feIngreso = feIngreso;
    }

    public Date getFeVencimiento() {
        return feVencimiento;
    }

    public void setFeVencimiento(Date feVencimiento) {
        this.feVencimiento = feVencimiento;
    }

    public String getIdCreaLote() {
        return idCreaLote;
    }

    public void setIdCreaLote(String idCreaLote) {
        this.idCreaLote = idCreaLote;
    }

    public String getNuLote() {
        return nuLote;
    }

    public void setNuLote(String nuLote) {
        this.nuLote = nuLote;
    }

    public String getNuDocumentoRecepcion() {
        return nuDocumentoRecepcion;
    }

    public void setNuDocumentoRecepcion(String nuDocumentoRecepcion) {
        this.nuDocumentoRecepcion = nuDocumentoRecepcion;
    }

    public String getTiDocumentoRecepcion() {
        return tiDocumentoRecepcion;
    }

    public void setTiDocumentoRecepcion(String tiDocumentoRecepcion) {
        this.tiDocumentoRecepcion = tiDocumentoRecepcion;
    }

    public Date getFeModLote() {
        return feModLote;
    }

    public void setFeModLote(Date feModLote) {
        this.feModLote = feModLote;
    }

    public String getIdModLote() {
        return idModLote;
    }

    public void setIdModLote(String idModLote) {
        this.idModLote = idModLote;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.getPrimaryKey() != null ? this.getPrimaryKey().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductoLote other = (ProductoLote) obj;
        if (this.getPrimaryKey() != other.getPrimaryKey() && (this.getPrimaryKey() == null || !Arrays.equals(this.getPrimaryKey(), other.getPrimaryKey()))) {
            return false;
        }
        System.out.println("Pks de Producto Lote = "+this.getPrimaryKey() +" = "+other.getPrimaryKey());
        return true;
    }  
    
    
}
