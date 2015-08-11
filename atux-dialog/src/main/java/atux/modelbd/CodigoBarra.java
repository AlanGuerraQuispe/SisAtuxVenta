package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class CodigoBarra extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    private String coCompania;
    private String coProducto;
    private String nuRevisionProducto;
    private String coBarra;
    private String esProdCodBarra;
    private String idCreaProdCodBarra;
    private Date   feCreaProdCodBarra;
    private String idModProdCodBarra;
    private Date   feModProdCodBarra;
    
    public static final String nt = "LGTR_PROD_CODIGO_BARRA";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_PRODUCTO","NU_REVISION_PRODUCTO","CO_BARRA"};
    public static final String COLUMNA_ACTIVO = "ES_PROD_COD_BARRA";
    public static final String[] COLUMNA_ORDER ={"CO_BARRA"};
    
    
    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_COMPANIA,CO_PRODUCTO,NU_REVISION_PRODUCTO,CO_BARRA,ES_PROD_COD_BARRA,ID_CREA_PROD_COD_BARRA,"
                      + "FE_CREA_PROD_COD_BARRA,ID_MOD_PROD_COD_BARRA,FE_MOD_PROD_COD_BARRA"};

    public static final String[] 
      CAMPOS_NO_ID ={"ES_PROD_COD_BARRA,ID_CREA_PROD_COD_BARRA,"
                      + "FE_CREA_PROD_COD_BARRA,ID_MOD_PROD_COD_BARRA,FE_MOD_PROD_COD_BARRA"};
        
    public static final String[]
        FULL_NOM_CAMPOS_BUSCAR ={"CO_COMPANIA,CO_PRODUCTO,NU_REVISION_PRODUCTO,CO_BARRA,ES_PROD_COD_BARRA,ID_CREA_PROD_COD_BARRA,"
                               + "FE_CREA_PROD_COD_BARRA,ID_MOD_PROD_COD_BARRA,FE_MOD_PROD_COD_BARRA"};

    public String getCoBarra() {
        return coBarra;
    }

    public void setCoBarra(String coBarra) {
        this.coBarra = coBarra;
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

    public String getEsProdCodBarra() {
        return esProdCodBarra;
    }

    public void setEsProdCodBarra(String esProdCodBarra) {
        this.esProdCodBarra = esProdCodBarra;
    }

    public Date getFeCreaProdCodBarra() {
        return feCreaProdCodBarra;
    }

    public void setFeCreaProdCodBarra(Date feCreaProdCodBarra) {
        this.feCreaProdCodBarra = feCreaProdCodBarra;
    }

    public Date getFeModProdCodBarra() {
        return feModProdCodBarra;
    }

    public void setFeModProdCodBarra(Date feModProdCodBarra) {
        this.feModProdCodBarra = feModProdCodBarra;
    }

    public String getIdCreaProdCodBarra() {
        return idCreaProdCodBarra;
    }

    public void setIdCreaProdCodBarra(String idCreaProdCodBarra) {
        this.idCreaProdCodBarra = idCreaProdCodBarra;
    }

    public String getIdModProdCodBarra() {
        return idModProdCodBarra;
    }

    public void setIdModProdCodBarra(String idModProdCodBarra) {
        this.idModProdCodBarra = idModProdCodBarra;
    }

    public String getNuRevisionProducto() {
        return nuRevisionProducto;
    }

    public void setNuRevisionProducto(String nuRevisionProducto) {
        this.nuRevisionProducto = nuRevisionProducto;
    }
    
    

    public CodigoBarra() {
        initBasic();
    }
    
    private void initBasic()
    {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
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
        if (!(object instanceof CodigoBarra)) {
            return false;
        }
        CodigoBarra other = (CodigoBarra) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }          
    
}
