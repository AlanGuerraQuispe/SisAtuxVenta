package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class Proveedores extends JAbstractModelBD implements Serializable,IModel{    
    private static final long serialVersionUID = 1L; 
    
    public static final String nt = "VTTM_PROVEEDOR";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_PROVEEDOR"};
    public static final String COLUMNA_ACTIVO = "ES_PROVEEDOR";
    public static final String[] COLUMNA_ORDER ={"DE_PROVEEDOR"};
    
    private String coCompania;
    private String coProveedor;
    private String nuDocIdentidad;
    private String deProveedor;
    private String deDireccion;
    private String deCiudad;
    private String deEmailProveedor;
    private String deTelefonoProveedor;
    private String deFaxProveedor;
    private String deContacto;
    private String deTelefono01Contacto;
    private String deTelefono02Contacto;
    private String deEmailContacto;
    private String tiProveedor;
    private String esProveedor;
    private String deBancoProveedor;
    private String nuCuentaBanco;
    private String idCreaProveedor;
    private Date feCreaProveedor;
    private String idModProveedor;
    private Date feModProveedor;

        
    public static final String[]
        FULL_NOM_CAMPOS ={"CO_COMPANIA, CO_PROVEEDOR, NU_DOC_IDENTIDAD, DE_PROVEEDOR," +
                          "DE_DIRECCION, DE_CIUDAD, DE_EMAIL_PROVEEDOR, DE_TELEFONO_PROVEEDOR, " +
                          "DE_FAX_PROVEEDOR, DE_CONTACTO, DE_TELEFONO01_CONTACTO, " +
                          "DE_TELEFONO02_CONTACTO, DE_EMAIL_CONTACTO, TI_PROVEEDOR, ES_PROVEEDOR, " +
                          "DE_BANCO_PROVEEDOR, NU_CUENTA_BANCO, ID_CREA_PROVEEDOR, FE_CREA_PROVEEDOR, " +
                          "ID_MOD_PROVEEDOR, FE_MOD_PROVEEDOR"};
    
    public static final String[]
           CAMPOS_NO_ID ={"NU_DOC_IDENTIDAD, DE_PROVEEDOR," +
                          "DE_DIRECCION, DE_CIUDAD, DE_EMAIL_PROVEEDOR, DE_TELEFONO_PROVEEDOR, " +
                          "DE_FAX_PROVEEDOR, DE_CONTACTO, DE_TELEFONO01_CONTACTO, " +
                          "DE_TELEFONO02_CONTACTO, DE_EMAIL_CONTACTO, TI_PROVEEDOR, ES_PROVEEDOR, " +
                          "DE_BANCO_PROVEEDOR, NU_CUENTA_BANCO, ID_CREA_PROVEEDOR, FE_CREA_PROVEEDOR, " +
                          "ID_MOD_PROVEEDOR, FE_MOD_PROVEEDOR"};

    
    public Proveedores() {
        initBasic();
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoProveedor() {
        return coProveedor;
    }

    public void setCoProveedor(String coProveedor) {
        this.coProveedor = coProveedor;
    }

    public String getDeBancoProveedor() {
        return deBancoProveedor;
    }

    public void setDeBancoProveedor(String deBancoProveedor) {
        this.deBancoProveedor = deBancoProveedor;
    }

    public String getDeCiudad() {
        return deCiudad;
    }

    public void setDeCiudad(String deCiudad) {
        this.deCiudad = deCiudad;
    }

    public String getDeContacto() {
        return deContacto;
    }

    public void setDeContacto(String deContacto) {
        this.deContacto = deContacto;
    }

    public String getDeDireccion() {
        return deDireccion;
    }

    public void setDeDireccion(String deDireccion) {
        this.deDireccion = deDireccion;
    }

    public String getDeEmailContacto() {
        return deEmailContacto;
    }

    public void setDeEmailContacto(String deEmailContacto) {
        this.deEmailContacto = deEmailContacto;
    }

    public String getDeEmailProveedor() {
        return deEmailProveedor;
    }

    public void setDeEmailProveedor(String deEmailProveedor) {
        this.deEmailProveedor = deEmailProveedor;
    }

    public String getDeFaxProveedor() {
        return deFaxProveedor;
    }

    public void setDeFaxProveedor(String deFaxProveedor) {
        this.deFaxProveedor = deFaxProveedor;
    }

    public String getDeProveedor() {
        return deProveedor;
    }

    public void setDeProveedor(String deProveedor) {
        this.deProveedor = deProveedor;
    }

    public String getDeTelefono01Contacto() {
        return deTelefono01Contacto;
    }

    public void setDeTelefono01Contacto(String deTelefono01Contacto) {
        this.deTelefono01Contacto = deTelefono01Contacto;
    }

    public String getDeTelefono02Contacto() {
        return deTelefono02Contacto;
    }

    public void setDeTelefono02Contacto(String deTelefono02Contacto) {
        this.deTelefono02Contacto = deTelefono02Contacto;
    }

    public String getDeTelefonoProveedor() {
        return deTelefonoProveedor;
    }

    public void setDeTelefonoProveedor(String deTelefonoProveedor) {
        this.deTelefonoProveedor = deTelefonoProveedor;
    }

    public String getEsProveedor() {
        return esProveedor;
    }

    public void setEsProveedor(String esProveedor) {
        this.esProveedor = esProveedor;
    }

    public Date getFeCreaProveedor() {
        return feCreaProveedor;
    }

    public void setFeCreaProveedor(Date feCreaProveedor) {
        this.feCreaProveedor = feCreaProveedor;
    }

    public Date getFeModProveedor() {
        return feModProveedor;
    }

    public void setFeModProveedor(Date feModProveedor) {
        this.feModProveedor = feModProveedor;
    }

    public String getIdCreaProveedor() {
        return idCreaProveedor;
    }

    public void setIdCreaProveedor(String idCreaProveedor) {
        this.idCreaProveedor = idCreaProveedor;
    }

    public String getIdModProveedor() {
        return idModProveedor;
    }

    public void setIdModProveedor(String idModProveedor) {
        this.idModProveedor = idModProveedor;
    }

    public String getNuCuentaBanco() {
        return nuCuentaBanco;
    }

    public void setNuCuentaBanco(String nuCuentaBanco) {
        this.nuCuentaBanco = nuCuentaBanco;
    }

    public String getNuDocIdentidad() {
        return nuDocIdentidad;
    }

    public void setNuDocIdentidad(String nuDocIdentidad) {
        this.nuDocIdentidad = nuDocIdentidad;
    }

    public String getTiProveedor() {
        return tiProveedor;
    }

    public void setTiProveedor(String tiProveedor) {
        this.tiProveedor = tiProveedor;
    }    

    public Proveedores(String coCompania, String coProveedor, String nuDocIdentidad, String deProveedor, String deDireccion, String deCiudad, String deEmailProveedor, String deTelefonoProveedor, String deFaxProveedor, String deContacto, String deTelefono01Contacto, String deTelefono02Contacto, String deEmailContacto, String tiProveedor, String esProveedor, String deBancoProveedor, String nuCuentaBanco, String idCreaProveedor, Date feCreaProveedor, String idModProveedor, Date feModProveedor) {
        this.coCompania = coCompania;
        this.coProveedor = coProveedor;
        this.nuDocIdentidad = nuDocIdentidad;
        this.deProveedor = deProveedor;
        this.deDireccion = deDireccion;
        this.deCiudad = deCiudad;
        this.deEmailProveedor = deEmailProveedor;
        this.deTelefonoProveedor = deTelefonoProveedor;
        this.deFaxProveedor = deFaxProveedor;
        this.deContacto = deContacto;
        this.deTelefono01Contacto = deTelefono01Contacto;
        this.deTelefono02Contacto = deTelefono02Contacto;
        this.deEmailContacto = deEmailContacto;
        this.tiProveedor = tiProveedor;
        this.esProveedor = esProveedor;
        this.deBancoProveedor = deBancoProveedor;
        this.nuCuentaBanco = nuCuentaBanco;
        this.idCreaProveedor = idCreaProveedor;
        this.feCreaProveedor = feCreaProveedor;
        this.idModProveedor = idModProveedor;
        this.feModProveedor = feModProveedor;
        initBasic();
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
        
        if (!(object instanceof Proveedores)) {
            return false;
        }
        Proveedores other = (Proveedores) object;
        if ((this.getPrimaryKey() == null && other.getPrimaryKey() != null) || (this.getPrimaryKey() != null && !Arrays.equals(this.getPrimaryKey(), other.getPrimaryKey()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  deProveedor ;       
    }
    
}
