package atux.modelbd;

import atux.core.DatoArchivo;
import atux.core.JAbstractModelBD;
import java.io.FileInputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import oracle.sql.BLOB;
import org.springframework.util.StringUtils;


public class Usuario extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    
    public static final String nt = "CMTS_USUARIO";    
    public static final String tablaFoto = "CMTS_FOTO_USUARIO";    
    
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_LOCAL","ID_USUARIO"};
    public static final String[] COLUMNA_PK2 ={"CO_COMPANIA","CO_LOCAL","NU_SEC_USUARIO"};
    public static final String COLUMNA_ACTIVO = "ES_USUARIO";
    
    public static final String[] tCargos ={"Seleccionar Cargo","VENDEDOR","QUIMICO","AUDITORIA","CAJERO"};
    public static final String[] sexos={"Seleccionar Sexo","Hombre","Mujer"};              
    
    private String coCompania;
    private String coLocal;
    private String nuSecUsuario;
    private String idUsuario;
    private String deClave;
    private String nuEmpleado;
    private String apPaterno;
    private String apMaterno;
    private String nombre;
    private String  tiDocIdentidad;
    private String  nuDocIdentidad;
    private String inSexo;
    private Date    feNacimiento;
    private Date    feIngreso;
    private Date    feCese;
    private String  nuTelNormal;
    private String  nuTelMovil;
    private String  nuTelRef;
    private String  deDireccionUsuario;
    private String esUsuario;
    private String  idCreaUsuario;
    private Date    feCreaUsuario;
    private String  idModUsuario;
    private Date    feModUsuario;
    private String  noEmail;
    private String  deUbigeoDireccion;
    private String  deNuevaClave;
    
    private DatoArchivo dat;    
    private FileInputStream foto;
    private Blob deFotoUsuario;
    
    //Datos de cajero
    private String nuCaja;
    private String nuTurno;
    
    public static final String[]
          CAMPOS_ID ={"CO_COMPANIA, CO_LOCAL, NU_SEC_USUARIO, ID_USUARIO, DE_CLAVE_USUARIO, NU_EMPLEADO, AP_PATERNO_USUARIO, AP_MATERNO_USUARIO, "
                          + "NO_USUARIO, TI_DOC_IDENTIDAD, NU_DOC_IDENTIDAD, IN_SEXO, FE_NACIMIENTO, FE_INGRESO, FE_CESE, NU_TEL_NORMAL, NU_TEL_MOVIL, NU_TEL_REF, "
                          + "DE_DIRECCION_USUARIO, ES_USUARIO, ID_CREA_USUARIO, FE_CREA_USUARIO, ID_MOD_USUARIO, FE_MOD_USUARIO, NO_EMAIL, DE_UBIGEO_DIRECCION"};
    
    public static final String[] 
         CAMPOS_NO_ID ={"ID_USUARIO, DE_CLAVE_USUARIO, NU_EMPLEADO, AP_PATERNO_USUARIO, AP_MATERNO_USUARIO, "
                            + "NO_USUARIO, TI_DOC_IDENTIDAD, NU_DOC_IDENTIDAD, IN_SEXO, FE_NACIMIENTO, FE_INGRESO, FE_CESE, NU_TEL_NORMAL, NU_TEL_MOVIL, NU_TEL_REF, "
                            + "DE_DIRECCION_USUARIO, ES_USUARIO, ID_CREA_USUARIO, FE_CREA_USUARIO, ID_MOD_USUARIO, FE_MOD_USUARIO, NO_EMAIL, DE_UBIGEO_DIRECCION"};

    public static final String[] 
         CAMPO_FOTO ={"CO_COMPANIA, CO_LOCAL, NU_SEC_USUARIO, DE_FOTO_USUARIO"};
    
    public static final String[] 
         CAMPO_FOTO_NO_ID ={"DE_FOTO_USUARIO"};
                          
    
    public static final String[] OBLIGATORIOS_NOM_CAMPOS ={"NU_DOC_IDENTIDAD,NU_SEC_USUARIO,DE_CLAVE_USUARIO,NO_USUARIO,AP_PATERNO_USUARIO"
            + "AP_MATERNO_USUARIO,NU_TEL_MOVIL,IN_SEXO,DE_DIRECCION_USUARIO,FE_CREA_USUARIO"};
   

    public String getTablaFoto(){
        return tablaFoto;
    }
    
    public Usuario() {
        initBasic();
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
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

    public DatoArchivo getDat() {
        return dat;
    }

    public void setDat(DatoArchivo dat) {
        this.dat = dat;
    }

    public String getDeClave() {
        return deClave;
    }

    public void setDeClave(String deClave) {
        this.deClave = deClave;
    }

    public String getDeDireccionUsuario() {
        return deDireccionUsuario;
    }

    public void setDeDireccionUsuario(String deDireccionUsuario) {
        this.deDireccionUsuario = deDireccionUsuario;
    }

    public String getDeUbigeoDireccion() {
        return deUbigeoDireccion;
    }

    public void setDeUbigeoDireccion(String deUbigeoDireccion) {
        this.deUbigeoDireccion = deUbigeoDireccion;
    }

    public String getEsUsuario() {
        return esUsuario;
    }

    public void setEsUsuario(String esUsuario) {
        this.esUsuario = esUsuario;
    }

    public Date getFeCese() {
        return feCese;
    }

    public void setFeCese(Date feCese) {
        this.feCese = feCese;
    }

    public Date getFeCreaUsuario() {
        return feCreaUsuario;
    }

    public void setFeCreaUsuario(Date feCreaUsuario) {
        this.feCreaUsuario = feCreaUsuario;
    }

    public Date getFeIngreso() {
        return feIngreso;
    }

    public void setFeIngreso(Date feIngreso) {
        this.feIngreso = feIngreso;
    }

    public Date getFeModUsuario() {
        return feModUsuario;
    }

    public void setFeModUsuario(Date feModUsuario) {
        this.feModUsuario = feModUsuario;
    }

    public Date getFeNacimiento() {
        return feNacimiento;
    }

    public void setFeNacimiento(Date feNacimiento) {
        this.feNacimiento = feNacimiento;
    }

    public String getIdCreaUsuario() {
        return idCreaUsuario;
    }

    public void setIdCreaUsuario(String idCreaUsuario) {
        this.idCreaUsuario = idCreaUsuario;
    }

    public String getIdModUsuario() {
        return idModUsuario;
    }

    public void setIdModUsuario(String idModUsuario) {
        this.idModUsuario = idModUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getInSexo() {
        return inSexo;
    }

    public void setInSexo(String inSexo) {
        this.inSexo = inSexo;
    }

    public String getNoEmail() {
        return noEmail;
    }

    public void setNoEmail(String noEmail) {
        this.noEmail = noEmail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNuDocIdentidad() {
        return nuDocIdentidad;
    }

    public void setNuDocIdentidad(String nuDocIdentidad) {
        this.nuDocIdentidad = nuDocIdentidad;
    }

    public String getNuEmpleado() {
        return nuEmpleado;
    }

    public void setNuEmpleado(String nuEmpleado) {
        this.nuEmpleado = nuEmpleado;
    }

    public String getNuSecUsuario() {
        return nuSecUsuario;
    }

    public void setNuSecUsuario(String nuSecUsuario) {
        this.nuSecUsuario = nuSecUsuario;
    }   

    public String getNuTelMovil() {
        return nuTelMovil;
    }

    public void setNuTelMovil(String nuTelMovil) {
        this.nuTelMovil = nuTelMovil;
    }

    public String getNuTelNormal() {
        return nuTelNormal;
    }

    public void setNuTelNormal(String nuTelNormal) {
        this.nuTelNormal = nuTelNormal;
    }

    public String getNuTelRef() {
        return nuTelRef;
    }

    public void setNuTelRef(String nuTelRef) {
        this.nuTelRef = nuTelRef;
    }

    public String getTiDocIdentidad() {
        return tiDocIdentidad;
    }

    public void setTiDocIdentidad(String tiDocIdentidad) {
        this.tiDocIdentidad = tiDocIdentidad;
    }    

    public String getDeNuevaClave() {
        return deNuevaClave;
    }     

    public void setDeNuevaClave(String deNuevaClave) {
        this.deNuevaClave = deNuevaClave;
    }
 
    
    private void initBasic()
    {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
    }

    

    public FileInputStream getFoto() {
        return foto;
    }

    public void setFoto(FileInputStream foto) {
        this.foto = foto;
    }
    
    public void setFotoDA(FileInputStream foto,int longitud)
    {
        dat = new DatoArchivo(foto,longitud);
        this.foto = foto;
    }
    
     public void setFotoDA(DatoArchivo dat)
    {
        this.dat = dat;
        this.foto = dat.getFis();
    }
    
    public DatoArchivo getFoto(String s)
    {
        return dat;
    }

    public String getNuCaja() {
        return nuCaja;
    }

    public void setNuCaja(String nuCaja) {
        this.nuCaja = nuCaja;
    }

    public String getNuTurno() {
        return nuTurno;
    }

    public void setNuTurno(String nuTurno) {
        this.nuTurno = nuTurno;
    }        

    public String getSexo(Object obj) {
        if(obj == null)
        {
           return inSexo; 
        }else
        {
          if(inSexo.equalsIgnoreCase("M"))
            {
                return "Masculino";
            }else
            {
                return "Femenino";
            }  
        }
        
    }

    public void setSexo(String sexo) {
        this.inSexo = sexo;
        if(inSexo.length()>1)
        {
           if(inSexo.equalsIgnoreCase("Masculino"))
            {
               this.inSexo = "M"; 
            }else{
                this.inSexo = "F";
            } 
        }
            } 

    public Blob getDeFotoUsuario() {
        return deFotoUsuario;
        }
        
    public void setDeFotoUsuario(Blob deFotoUsuario) {
        this.deFotoUsuario = deFotoUsuario;
    }    
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (nuSecUsuario != null ? nuSecUsuario.hashCode() : 0);
        return hash;
    }          

    @Override
    public boolean equals(Object object) {       
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.nuSecUsuario == null && other.nuSecUsuario != null) || (this.nuSecUsuario != null && !this.nuSecUsuario.equals(other.nuSecUsuario))) {
            return false;
        }
        return true;
    }        
    
    @Override
    public String toString() {
        return getNombreCompleto();
    }
    
    public String getNombreCompleto() {
        return StringUtils.trimWhitespace(nombre+" "+ apPaterno+" "+apMaterno);
    }
}
