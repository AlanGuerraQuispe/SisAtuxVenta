package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class ClienteComprobante extends JAbstractModelBD implements Serializable,IModel {

    private static final long serialVersionUID = 1L;
    public static final String nt = "VTTR_CLIENTE_COMPROBANTE";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_LOCAL","NU_COMPROBANTE"};
    public static final String COLUMNA_ACTIVO = "ES_CLIENTE";
    public static final String[] COLUMNA_ORDER ={"CO_CLIENTE_LOCAL"};

    private String  coCompania;
    private String  coLocal;
    private String  coClienteLocal;
    private String  nuComprobante;


    public static final String[]
            FULL_NOM_CAMPOS ={"CO_COMPANIA, CO_LOCAL, CO_CLIENTE_LOCAL, NU_COMPROBANTE"};


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

    public String getNuComprobante() {
        return nuComprobante;
    }

    public void setNuComprobante(String nuComprobante) {
        this.nuComprobante = nuComprobante;
    }

    public ClienteComprobante() {
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
        return  nuComprobante ;
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
        if (!(object instanceof ClienteComprobante)) {
            return false;
        }

        ClienteComprobante other = (ClienteComprobante) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }
}
