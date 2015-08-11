package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class ClienteDireccion extends JAbstractModelBD implements Serializable,IModel {
    
    private static final long serialVersionUID = 1L;
    public static final String nt = "VTTX_CLIENTE_DIRECCION";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_LOCAL","CO_CLIENTE_LOCAL"};
    public static final String COLUMNA_ACTIVO = "ES_DIRECCION_CLIENTE";
    public static final String[] COLUMNA_ORDER ={"CO_CLIENTE_LOCAL"};
    
    private String  coCompania;
    private String  coLocal;
    private String  coClienteLocal;
    private Integer nuSecDireccion;
    private String  coPais;
    private String  coDepartamento;
    private String  coProvincia;
    private String  coDistrito;
    private String  coLocalRef1;
    private String  coLocalRef2;
    private String  tiVia;
    private String  noVia;
    private String  nuVia;
    private String  nuInteriorVia;
    private String  nuDptoVia;
    private String  nuManzanaVia;
    private String  nuLoteVia;
    private String  tiPoblacion;
    private String  noPoblacion;
    private String  deDireccion;
    private String  deReferencia;
    private String  coTelPais;
    private String  coTelCiudad;
    private String  nuTelNormal;
    private String  nuTelExtension;
    private String  nuTelReferencia;
    private String  coPlano;
    private String  coUbicacionPlano;
    private String  inDireccionNueva;
    private String  esDireccionCliente;
    private String  idCreaDireccionCliente;
    private Date    feCreaDireccionCliente;
    private String  idModDireccionCliente;
    private Date    feModDireccionCliente;
    private String  inReplica;
    private Date    feReplica;
    private Integer nuSecReplica;
    private String  tiDireccion;
    private Integer  nuSector;
    private String  nuBlock;

    
    
    public static final String[] 
      FULL_NOM_CAMPOS ={"CO_COMPANIA, CO_LOCAL, CO_CLIENTE_LOCAL, NU_SEC_DIRECCION, CO_PAIS, CO_DEPARTAMENTO, CO_PROVINCIA, CO_DISTRITO, CO_LOCAL_REF_1,  "
                      + "CO_LOCAL_REF_2, TI_VIA, NO_VIA, NU_VIA, NU_INTERIOR_VIA, NU_DPTO_VIA, NU_MANZANA_VIA, NU_LOTE_VIA, TI_POBLACION, NO_POBLACION, DE_DIRECCION,  "
                      + "DE_REFERENCIA, CO_TEL_PAIS, CO_TEL_CIUDAD, NU_TEL_NORMAL, NU_TEL_EXTENSION, NU_TEL_REFERENCIA, CO_PLANO, CO_UBICACION_PLANO, IN_DIRECCION_NUEVA, "
                      + "ES_DIRECCION_CLIENTE, ID_CREA_DIRECCION_CLIENTE, FE_CREA_DIRECCION_CLIENTE, ID_MOD_DIRECCION_CLIENTE, FE_MOD_DIRECCION_CLIENTE, IN_REPLICA, "
                      + "FE_REPLICA, NU_SEC_REPLICA, TI_DIRECCION, NU_SECTOR, NU_BLOCK"};


    public static final String[] 
      CAMPOS_NO_ID ={"NU_SEC_DIRECCION, CO_PAIS, CO_DEPARTAMENTO, CO_PROVINCIA, CO_DISTRITO, CO_LOCAL_REF_1,  "
                   + "CO_LOCAL_REF_2, TI_VIA, NO_VIA, NU_VIA, NU_INTERIOR_VIA, NU_DPTO_VIA, NU_MANZANA_VIA, NU_LOTE_VIA, TI_POBLACION, NO_POBLACION, DE_DIRECCION,  "
                   + "DE_REFERENCIA, CO_TEL_PAIS, CO_TEL_CIUDAD, NU_TEL_NORMAL, NU_TEL_EXTENSION, NU_TEL_REFERENCIA, CO_PLANO, CO_UBICACION_PLANO, IN_DIRECCION_NUEVA, "
                   + "ES_DIRECCION_CLIENTE, ID_CREA_DIRECCION_CLIENTE, FE_CREA_DIRECCION_CLIENTE, ID_MOD_DIRECCION_CLIENTE, FE_MOD_DIRECCION_CLIENTE, IN_REPLICA, "
                   + "FE_REPLICA, NU_SEC_REPLICA, TI_DIRECCION, NU_SECTOR, NU_BLOCK"};

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

    public String getCoDepartamento() {
        return coDepartamento;
    }

    public void setCoDepartamento(String coDepartamento) {
        this.coDepartamento = coDepartamento;
    }

    public String getCoDistrito() {
        return coDistrito;
    }

    public void setCoDistrito(String coDistrito) {
        this.coDistrito = coDistrito;
    }

    public String getCoLocal() {
        return coLocal;
    }

    public void setCoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public String getCoLocalRef1() {
        return coLocalRef1;
    }

    public void setCoLocalRef1(String coLocalRef1) {
        this.coLocalRef1 = coLocalRef1;
    }

    public String getCoLocalRef2() {
        return coLocalRef2;
    }

    public void setCoLocalRef2(String coLocalRef2) {
        this.coLocalRef2 = coLocalRef2;
    }

    public String getCoPais() {
        return coPais;
    }

    public void setCoPais(String coPais) {
        this.coPais = coPais;
    }

    public String getCoPlano() {
        return coPlano;
    }

    public void setCoPlano(String coPlano) {
        this.coPlano = coPlano;
    }

    public String getCoProvincia() {
        return coProvincia;
    }

    public void setCoProvincia(String coProvincia) {
        this.coProvincia = coProvincia;
    }

    public String getCoTelCiudad() {
        return coTelCiudad;
    }

    public void setCoTelCiudad(String coTelCiudad) {
        this.coTelCiudad = coTelCiudad;
    }

    public String getCoTelPais() {
        return coTelPais;
    }

    public void setCoTelPais(String coTelPais) {
        this.coTelPais = coTelPais;
    }

    public String getCoUbicacionPlano() {
        return coUbicacionPlano;
    }

    public void setCoUbicacionPlano(String coUbicacionPlano) {
        this.coUbicacionPlano = coUbicacionPlano;
    }

    public String getDeDireccion() {
        return deDireccion;
    }

    public void setDeDireccion(String deDireccion) {
        this.deDireccion = deDireccion;
    }

    public String getDeReferencia() {
        return deReferencia;
    }

    public void setDeReferencia(String deReferencia) {
        this.deReferencia = deReferencia;
    }

    public String getEsDireccionCliente() {
        return esDireccionCliente;
    }

    public void setEsDireccionCliente(String esDireccionCliente) {
        this.esDireccionCliente = esDireccionCliente;
    }

    public Date getFeCreaDireccionCliente() {
        return feCreaDireccionCliente;
    }

    public void setFeCreaDireccionCliente(Date feCreaDireccionCliente) {
        this.feCreaDireccionCliente = feCreaDireccionCliente;
    }

    public Date getFeModDireccionCliente() {
        return feModDireccionCliente;
    }

    public void setFeModDireccionCliente(Date feModDireccionCliente) {
        this.feModDireccionCliente = feModDireccionCliente;
    }

    public Date getFeReplica() {
        return feReplica;
    }

    public void setFeReplica(Date feReplica) {
        this.feReplica = feReplica;
    }

    public String getIdCreaDireccionCliente() {
        return idCreaDireccionCliente;
    }

    public void setIdCreaDireccionCliente(String idCreaDireccionCliente) {
        this.idCreaDireccionCliente = idCreaDireccionCliente;
    }

    public String getIdModDireccionCliente() {
        return idModDireccionCliente;
    }

    public void setIdModDireccionCliente(String idModDireccionCliente) {
        this.idModDireccionCliente = idModDireccionCliente;
    }

    public String getInDireccionNueva() {
        return inDireccionNueva;
    }

    public void setInDireccionNueva(String inDireccionNueva) {
        this.inDireccionNueva = inDireccionNueva;
    }

    public String getInReplica() {
        return inReplica;
    }

    public void setInReplica(String inReplica) {
        this.inReplica = inReplica;
    }

    public String getNoPoblacion() {
        return noPoblacion;
    }

    public void setNoPoblacion(String noPoblacion) {
        this.noPoblacion = noPoblacion;
    }

    public String getNoVia() {
        return noVia;
    }

    public void setNoVia(String noVia) {
        this.noVia = noVia;
    }

    public String getNuDptoVia() {
        return nuDptoVia;
    }

    public void setNuDptoVia(String nuDptoVia) {
        this.nuDptoVia = nuDptoVia;
    }

    public String getNuInteriorVia() {
        return nuInteriorVia;
    }

    public void setNuInteriorVia(String nuInteriorVia) {
        this.nuInteriorVia = nuInteriorVia;
    }

    public String getNuLoteVia() {
        return nuLoteVia;
    }

    public void setNuLoteVia(String nuLoteVia) {
        this.nuLoteVia = nuLoteVia;
    }

    public String getNuManzanaVia() {
        return nuManzanaVia;
    }

    public void setNuManzanaVia(String nuManzanaVia) {
        this.nuManzanaVia = nuManzanaVia;
    }

    public Integer getNuSecDireccion() {
        return nuSecDireccion;
    }

    public void setNuSecDireccion(Integer nuSecDireccion) {
        this.nuSecDireccion = nuSecDireccion;
    }

    public Integer getNuSecReplica() {
        return nuSecReplica;
    }

    public void setNuSecReplica(Integer nuSecReplica) {
        this.nuSecReplica = nuSecReplica;
    }

    public String getNuTelExtension() {
        return nuTelExtension;
    }

    public void setNuTelExtension(String nuTelExtension) {
        this.nuTelExtension = nuTelExtension;
    }

    public String getNuTelNormal() {
        return nuTelNormal;
    }

    public void setNuTelNormal(String nuTelNormal) {
        this.nuTelNormal = nuTelNormal;
    }

    public String getNuTelReferencia() {
        return nuTelReferencia;
    }

    public void setNuTelReferencia(String nuTelReferencia) {
        this.nuTelReferencia = nuTelReferencia;
    }

    public String getNuVia() {
        return nuVia;
    }

    public void setNuVia(String nuVia) {
        this.nuVia = nuVia;
    }

    public String getTiDireccion() {
        return tiDireccion;
    }

    public void setTiDireccion(String tiDireccion) {
        this.tiDireccion = tiDireccion;
    }

    public String getTiPoblacion() {
        return tiPoblacion;
    }

    public void setTiPoblacion(String tiPoblacion) {
        this.tiPoblacion = tiPoblacion;
    }

    public String getTiVia() {
        return tiVia;
    }

    public void setTiVia(String tiVia) {
        this.tiVia = tiVia;
    }

    public String getNuBlock() {
        return nuBlock;
    }

    public void setNuBlock(String nuBlock) {
        this.nuBlock = nuBlock;
    }

    public Integer getNuSector() {
        return nuSector;
    }

    public void setNuSector(Integer nuSector) {
        this.nuSector = nuSector;
    }
  
    
    
    public ClienteDireccion() {
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
       return  deDireccion ;
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
        if (!(object instanceof ClienteDireccion)) {
            return false;
        }
        
        ClienteDireccion other = (ClienteDireccion) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }
}
