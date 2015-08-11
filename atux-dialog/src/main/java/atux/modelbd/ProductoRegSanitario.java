package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class ProductoRegSanitario extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    public static final String nt = "LGTR_PRODUCTO_REG_SANITARIO";

    public static final String[] COLUMNA_PK ={"CO_COMPANIA", "CO_LOCAL", "CO_PRODUCTO", "NU_REG_SANITARIO"};
    public static final String COLUMNA_ACTIVO = "ES_REG_SANITARIO";

    private String  coCompania;
    private String  coLocal;
    private String  coProducto;
    private String  nuRegSanitario;
    private Date    FeVenRegSanitario;
    private String  esRegSanitario;
    private String  idCreaRegSanitario;
    private Date    feCreaRegSanitario;
    private String  idModRegSanitario;
    private Date    feModRegSanitario;

    public static final String[]
            FULL_CAMPOS ={"CO_COMPANIA, CO_LOCAL, CO_PRODUCTO, NU_REG_SANITARIO, FE_VEN_REG_SANITARIO, ES_REG_SANITARIO, " +
            "ID_CREA_REG_SANITARIO, FE_CREA_REG_SANITARIO, ID_MOD_REG_SANITARIO, FE_MOD_REG_SANITARIO"};

    public static final String []
            CAMPOS_SIN_ID = {"FE_VEN_REG_SANITARIO, ES_REG_SANITARIO, " +
            "ID_CREA_REG_SANITARIO, FE_CREA_REG_SANITARIO, ID_MOD_REG_SANITARIO, FE_MOD_REG_SANITARIO"};

    public Date getFeVenRegSanitario() {
        return FeVenRegSanitario;
    }

    public void setFeVenRegSanitario(Date FeVenRegSanitario) {
        this.FeVenRegSanitario = FeVenRegSanitario;
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

    public String getCoProducto() {
        return coProducto;
    }

    public void setCoProducto(String coProducto) {
        this.coProducto = coProducto;
    }

    public String getEsRegSanitario() {
        return esRegSanitario;
    }

    public void setEsRegSanitario(String esRegSanitario) {
        this.esRegSanitario = esRegSanitario;
    }

    public Date getFeCreaRegSanitario() {
        return feCreaRegSanitario;
    }

    public void setFeCreaRegSanitario(Date feCreaRegSanitario) {
        this.feCreaRegSanitario = feCreaRegSanitario;
    }

    public Date getFeModRegSanitario() {
        return feModRegSanitario;
    }

    public void setFeModRegSanitario(Date feModRegSanitario) {
        this.feModRegSanitario = feModRegSanitario;
    }

    public String getIdCreaRegSanitario() {
        return idCreaRegSanitario;
    }

    public void setIdCreaRegSanitario(String idCreaRegSanitario) {
        this.idCreaRegSanitario = idCreaRegSanitario;
    }

    public String getIdModRegSanitario() {
        return idModRegSanitario;
    }

    public void setIdModRegSanitario(String idModRegSanitario) {
        this.idModRegSanitario = idModRegSanitario;
    }

    public String getNuRegSanitario() {
        return nuRegSanitario;
    }

    public void setNuRegSanitario(String nuRegSanitario) {
        this.nuRegSanitario = nuRegSanitario;
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
        final ProductoRegSanitario other = (ProductoRegSanitario) obj;
        if (this.getPrimaryKey() != other.getPrimaryKey() && (this.getPrimaryKey() == null || !Arrays.equals(this.getPrimaryKey(), other.getPrimaryKey()))) {
            return false;
        }
        System.out.println("Pks de Producto = "+this.getPrimaryKey() +" = "+other.getPrimaryKey());
        return true;
    }


}
