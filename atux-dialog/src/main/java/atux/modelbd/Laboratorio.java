package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class Laboratorio extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    
    public static final String nt = "CMTR_LABORATORIO";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_LABORATORIO"};
    public static final String COLUMNA_ACTIVO = "ES_LABORATORIO";
    public static final String[] COLUMNA_ORDER ={"DE_LABORATORIO"};
    
    private String coCompania;
    private String coLaboratorio;
    private String deAbrevLab;
    private String deLaboratorio;
    private String deDirecLaboratorio;
    private String deCiudad;
    private String noEmailLab;
    private String nuTelefLaboratorio;
    private String nuFaxLaboratorio;
    private String noContacto;
    private String nuTelContacto;
    private String nuMovilContacto;
    private String noEmailContacto;
    private String inLabPropio;
    private String esLaboratorio;
    private String inLabInventario;
    private String idCreaLaboratorio;
    private Date feCreaLaboratorio;
    private String idModLaboratorio;
    private Date feModLaboratorio;

    
    


    
    public static final String[] 
      FULL_NOM_CAMPOS ={"Co_Compania, Co_Laboratorio, De_Abrev_Lab, De_Laboratorio, De_Direc_Laboratorio, De_Ciudad, " +
                        "No_Email_Lab, Nu_Telef_Laboratorio, Nu_Fax_Laboratorio, No_Contacto, Nu_Tel_Contacto, " +
                        "Nu_Movil_Contacto, No_Email_Contacto, in_Lab_Propio, Es_Laboratorio, in_Lab_Inventario, " +
                        "Id_Crea_Laboratorio, Fe_Crea_Laboratorio, Id_Mod_Laboratorio, Fe_Mod_Laboratorio"};

    public static final String[] 
      CAMPOS_NO_ID ={"De_Abrev_Lab, De_Laboratorio, De_Direc_Laboratorio, De_Ciudad, " +
                     "No_Email_Lab, Nu_Telef_Laboratorio, Nu_Fax_Laboratorio, No_Contacto, Nu_Tel_Contacto, " +
                     "Nu_Movil_Contacto, No_Email_Contacto, in_Lab_Propio, Es_Laboratorio, in_Lab_Inventario, " +
                     "Id_Crea_Laboratorio, Fe_Crea_Laboratorio, Id_Mod_Laboratorio, Fe_Mod_Laboratorio"};
        
    public static final String[]
        FULL_NOM_CAMPOS_BUSCAR ={"Co_Compania, Co_Laboratorio,De_Laboratorio,De_Direc_Laboratorio ,"+
                                 "Nu_Telef_Laboratorio, Es_Laboratorio"};

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoLaboratorio() {
        return coLaboratorio;
    }

    public void setCoLaboratorio(String coLaboratorio) {
        this.coLaboratorio = coLaboratorio;
    }

    public String getDeAbrevLab() {
        return deAbrevLab;
    }

    public void setDeAbrevLab(String deAbrevLab) {
        this.deAbrevLab = deAbrevLab;
    }

    public String getDeCiudad() {
        return deCiudad;
    }

    public void setDeCiudad(String deCiudad) {
        this.deCiudad = deCiudad;
    }

    public String getDeDirecLaboratorio() {
        return deDirecLaboratorio;
    }

    public void setDeDirecLaboratorio(String deDirecLaboratorio) {
        this.deDirecLaboratorio = deDirecLaboratorio;
    }

    public String getDeLaboratorio() {
        return deLaboratorio;
    }

    public void setDeLaboratorio(String deLaboratorio) {
        this.deLaboratorio = deLaboratorio;
    }

    public String getEsLaboratorio() {
        return esLaboratorio;
    }

    public void setEsLaboratorio(String esLaboratorio) {
        this.esLaboratorio = esLaboratorio;
    }

    public Date getFeCreaLaboratorio() {
        return feCreaLaboratorio;
    }

    public void setFeCreaLaboratorio(Date feCreaLaboratorio) {
        this.feCreaLaboratorio = feCreaLaboratorio;
    }

    public Date getFeModLaboratorio() {
        return feModLaboratorio;
    }

    public void setFeModLaboratorio(Date feModLaboratorio) {
        this.feModLaboratorio = feModLaboratorio;
    }

    public String getIdCreaLaboratorio() {
        return idCreaLaboratorio;
    }

    public void setIdCreaLaboratorio(String idCreaLaboratorio) {
        this.idCreaLaboratorio = idCreaLaboratorio;
    }

    public String getIdModLaboratorio() {
        return idModLaboratorio;
    }

    public void setIdModLaboratorio(String idModLaboratorio) {
        this.idModLaboratorio = idModLaboratorio;
    }

    public String getInLabInventario() {
        return inLabInventario;
    }

    public void setInLabInventario(String inLabInventario) {
        this.inLabInventario = inLabInventario;
    }

    public String getInLabPropio() {
        return inLabPropio;
    }

    public void setInLabPropio(String inLabPropio) {
        this.inLabPropio = inLabPropio;
    }

    public String getNoContacto() {
        return noContacto;
    }

    public void setNoContacto(String noContacto) {
        this.noContacto = noContacto;
    }

    public String getNoEmailContacto() {
        return noEmailContacto;
    }

    public void setNoEmailContacto(String noEmailContacto) {
        this.noEmailContacto = noEmailContacto;
    }

    public String getNoEmailLab() {
        return noEmailLab;
    }

    public void setNoEmailLab(String noEmailLab) {
        this.noEmailLab = noEmailLab;
    }

    public String getNuFaxLaboratorio() {
        return nuFaxLaboratorio;
    }

    public void setNuFaxLaboratorio(String nuFaxLaboratorio) {
        this.nuFaxLaboratorio = nuFaxLaboratorio;
    }

    public String getNuMovilContacto() {
        return nuMovilContacto;
    }

    public void setNuMovilContacto(String nuMovilContacto) {
        this.nuMovilContacto = nuMovilContacto;
    }

    public String getNuTelContacto() {
        return nuTelContacto;
    }

    public void setNuTelContacto(String nuTelContacto) {
        this.nuTelContacto = nuTelContacto;
    }

    public String getNuTelefLaboratorio() {
        return nuTelefLaboratorio;
    }

    public void setNuTelefLaboratorio(String nuTelefLaboratorio) {
        this.nuTelefLaboratorio = nuTelefLaboratorio;
    }        
    
    public Laboratorio() {
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
        return "Laboratorio{" + "coLaboratorio=" + coLaboratorio + ", deLaboratorio=" + deLaboratorio + '}';
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
        if (!(object instanceof Laboratorio)) {
            return false;
        }
        Laboratorio other = (Laboratorio) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }          
    
}
