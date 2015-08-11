package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;


public class PrincipioActivoProducto extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    public static final String nt = "LGTR_PRODUCTO_INSUMO";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_PRODUCTO","NU_REVISION_PRODUCTO","CO_PRODUCTO_INSUMO"};
    public static final String COLUMNA_ACTIVO = "ES_PRODUCTO_INSUMO";
    public static final String[] COLUMNA_ORDER ={"IN_PRODUCTO_INSUMO_PRINCIPAL"};

    private String  coCompania;
    private String  coProducto;
    private String  nuRevisionProducto;
    private String  coProductoInsumo;
    private String  inProductoInsumoPrincipal;
    private String  inImpresion;
    private String  deObservacion;
    private BigDecimal vaCosto;
    private BigDecimal  vaPrecioPromedio;
    private String  deUnidad;
    private String  esProductoInsumo;

    private String  idCreaProductoInsumo;
    private Date    feCreaProductoInsumo;
    private String  idModProductoInsumo;
    private Date    feModProductoInsumo;

    private String deProductoInsumo;

    public static final String[]
    FULL_NOM_CAMPOS ={" CO_COMPANIA,CO_PRODUCTO,NU_REVISION_PRODUCTO, CO_PRODUCTO_INSUMO,IN_PRODUCTO_INSUMO_PRINCIPAL,"
            + "IN_IMPRESION, DE_OBSERVACION, VA_COSTO, VA_PRECIO_PROMEDIO, DE_UNIDAD_INSUMO,ES_PRODUCTO_INSUMO,"
            + "ID_CREA_PRODUCTO_INSUMO, FE_CREA_PRODUCTO_INSUMO, ID_MOD_PRODUCTO_INSUMO, FE_MOD_PRODUCTO_INSUMO"};

    public static final String[]
      CAMPOS_NO_ID ={"IN_PRODUCTO_INSUMO_PRINCIPAL,"
            + "IN_IMPRESION, DE_OBSERVACION, VA_COSTO, VA_PRECIO_PROMEDIO, DE_UNIDAD_INSUMO,ES_PRODUCTO_INSUMO,"
            + "ID_CREA_PRODUCTO_INSUMO, FE_CREA_PRODUCTO_INSUMO, ID_MOD_PRODUCTO_INSUMO, FE_MOD_PRODUCTO_INSUMO"};


    public PrincipioActivoProducto() {
        initBasic();
    }
    
    private void initBasic()
    {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoProducto() {
        return coProducto;
    }

    public void setCoProducto(String coProducto) {
        this.coProducto = coProducto;
    }

    public String getNuRevisionProducto() {
        return nuRevisionProducto;
    }

    public void setNuRevisionProducto(String nuRevisionProducto) {
        this.nuRevisionProducto = nuRevisionProducto;
    }

    public String getCoProductoInsumo() {
        return coProductoInsumo;
    }

    public void setCoProductoInsumo(String coProductoInsumo) {
        this.coProductoInsumo = coProductoInsumo;
    }

    public String getInProductoInsumoPrincipal() {
        return inProductoInsumoPrincipal;
    }

    public void setInProductoInsumoPrincipal(String inProductoInsumoPrincipal) {
        this.inProductoInsumoPrincipal = inProductoInsumoPrincipal;
    }

    public String getInImpresion() {
        return inImpresion;
    }

    public void setInImpresion(String inImpresion) {
        this.inImpresion = inImpresion;
    }

    public String getDeObservacion() {
        return deObservacion;
    }

    public void setDeObservacion(String deObservacion) {
        this.deObservacion = deObservacion;
    }

    public BigDecimal getVaCosto() {
        return vaCosto;
    }

    public void setVaCosto(BigDecimal vaCosto) {
        this.vaCosto = vaCosto;
    }

    public BigDecimal getVaPrecioPromedio() {
        return vaPrecioPromedio;
    }

    public void setVaPrecioPromedio(BigDecimal vaPrecioPromedio) {
        this.vaPrecioPromedio = vaPrecioPromedio;
    }

    public String getDeUnidad() {
        return deUnidad;
    }

    public void setDeUnidad(String deUnidad) {
        this.deUnidad = deUnidad;
    }

    public String getEsProductoInsumo() {
        return esProductoInsumo;
    }

    public void setEsProductoInsumo(String esProductoInsumo) {
        this.esProductoInsumo = esProductoInsumo;
    }

    public String getIdCreaProductoInsumo() {
        return idCreaProductoInsumo;
    }

    public void setIdCreaProductoInsumo(String idCreaProductoInsumo) {
        this.idCreaProductoInsumo = idCreaProductoInsumo;
    }

    public Date getFeCreaProductoInsumo() {
        return feCreaProductoInsumo;
    }

    public void setFeCreaProductoInsumo(Date feCreaProductoInsumo) {
        this.feCreaProductoInsumo = feCreaProductoInsumo;
    }

    public String getIdModProductoInsumo() {
        return idModProductoInsumo;
    }

    public void setIdModProductoInsumo(String idModProductoInsumo) {
        this.idModProductoInsumo = idModProductoInsumo;
    }

    public Date getFeModProductoInsumo() {
        return feModProductoInsumo;
    }

    public void setFeModProductoInsumo(Date feModProductoInsumo) {
        this.feModProductoInsumo = feModProductoInsumo;
    }

    public String getDeProductoInsumo() {
        return deProductoInsumo;
    }

    public void setDeProductoInsumo(String deProductoInsumo) {
        this.deProductoInsumo = deProductoInsumo;
    }
//    @Override

//    public String toString() {
//        return "tCambio{" + "nu_Sec_Tipo_Cambio=" + nuSecTipoCambio + ", fe_tipo_cambio=" + feTipoCambio + '}';
//    } 
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (primaryKey != null ? primaryKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrincipioActivoProducto)) {
            return false;
        }
        PrincipioActivoProducto other = (PrincipioActivoProducto) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }          
    
}
