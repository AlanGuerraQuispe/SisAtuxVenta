package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;

public class MotivoControlDigemid extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    
    public static final String nt = "CMTR_MOTIVO_CONTROL_DIGEMID";
    public static final String[] COLUMNA_PK ={"CO_MOTIVO"};
    public static final String COLUMNA_ACTIVO = "";
    public static final String[] COLUMNA_ORDER ={"DE_MOTIVO"};

    private String  coMotivo;
    private String  deMotivo;
    private String  deMotivoCorta;
    

    
    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_MOTIVO, DE_MOTIVO, DE_MOTIVO_CORTA"};

    public static final String[] 
      CAMPOS_NO_ID ={"DE_MOTIVO, DE_MOTIVO_CORTA"};

    public String getCoMotivo() {
        return coMotivo;
    }

    public void setCoMotivo(String coMotivo) {
        this.coMotivo = coMotivo;
    }

    public String getDeMotivo() {
        return deMotivo;
    }

    public void setDeMotivo(String deMotivo) {
        this.deMotivo = deMotivo;
    }

    public String getDeMotivoCorta() {
        return deMotivoCorta;
    }

    public void setDeMotivoCorta(String deMotivoCorta) {
        this.deMotivoCorta = deMotivoCorta;
    }
        


    public MotivoControlDigemid() {
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
       return  deMotivoCorta ;
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
        if (!(object instanceof MotivoControlDigemid)) {
            return false;
        }
        MotivoControlDigemid other = (MotivoControlDigemid) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }
}