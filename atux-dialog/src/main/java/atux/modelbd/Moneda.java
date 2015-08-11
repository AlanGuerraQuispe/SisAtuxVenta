package atux.modelbd;


public class Moneda extends SimpleModelo{
    public static final String TABLA = "CMTR_MONEDA";
    public static final String[] PK_COLUMNA ={"CO_MONEDA"};
    public static final String COLUMNA_ACTIVO = "ES_MONEDA";
    
    private String simbolo;
    private int predeterminado;
    private String coMoneda;
    
     public static final String[]
          FULL_NOM_CAMPOS ={"CO_MONEDA, NU_ORDEN_FILA, DE_CORTA_MONEDA, DE_MONEDA, DE_SIMBOLO_MONEDA,"+
                            "ES_MONEDA, ID_CREA_MONEDA, FE_CREA_MONEDA, ID_MOD_MONEDA, FE_MOD_MONEDA,"+
                            "CO_MONEDA_SAP"};
     
    public static final String[] 
           OBLIGATORIOS_NOM_CAMPOS ={"NU_DOC_IDENTIDAD,NU_SEC_USUARIO,DE_CLAVE_USUARIO,NO_USUARIO,AP_PATERNO_USUARIO"
           + "AP_MATERNO_USUARIO,NU_TEL_MOVIL,IN_SEXO,DE_DIRECCION_USUARIO,FE_CREA_USUARIO"};
    
    public Moneda() {        
        this.setCampoClavePrimaria(PK_COLUMNA);
        this.setNombreTabla(TABLA);
    }
    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    @Override
    public String toString() {
        return simbolo.trim() + " "+this.nombre.trim();
    }

    public int getPredeterminado() {
        return predeterminado;
    }

    public void setPredeterminado(int predeterminado) {
        this.predeterminado = predeterminado;
    }

    public String getCoMoneda() {
        return coMoneda;
    }

    public void setCoMoneda(String coMoneda) {
        this.coMoneda = coMoneda;
    }        
    
    public boolean getPredeterminadoBool()
    {
        if(predeterminado == 1)
        {
            return true;
        }else
        {
            return false;
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Moneda other = (Moneda) obj;
        if ((this.primaryKey == null) ? (other.primaryKey  != null) : !this.primaryKey .equals(other.primaryKey)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.primaryKey  != null ? this.primaryKey.hashCode() : 0);
        return hash;
    }
    
    
    
}
