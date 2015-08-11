package atux.core;


public class JAbstractModelBD {
    
    protected String[] primaryKey;   
    private String campoExistencial;
    private String nombreTabla;
    private String[] CampoClavePrimaria;    
    
    public String[] getCampoClavePrimaria() {
        return CampoClavePrimaria;
    }

    public void setCampoClavePrimaria(String [] CampoClavePrimaria) {
        this.CampoClavePrimaria = CampoClavePrimaria;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }
    
    public String[] getPrimaryKey()
    {
        return primaryKey;
    }
    
    public void setPrimaryKey(String[] pk)
    {
       primaryKey = pk; 
    }   

    public String getCampoExistencial() {
        return campoExistencial;
    }

    public void setCampoExistencial(String campoExistencial) {
        this.campoExistencial = campoExistencial;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.primaryKey != null ? this.primaryKey.hashCode() : 0);
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
        final JAbstractModelBD other = (JAbstractModelBD) obj;
        if (this.primaryKey != other.primaryKey && (this.primaryKey == null || !this.primaryKey.equals(other.primaryKey))) {
            return false;
        }
        return true;
    }
}
