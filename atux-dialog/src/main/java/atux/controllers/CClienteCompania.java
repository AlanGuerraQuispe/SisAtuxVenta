package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.ClienteCompania;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxVariables;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Fecha creación : 01/09/2014  v1.0
 * @author aguerra
 */
public class CClienteCompania extends JAbstractController{
    private ClienteCompania CCia;   

    public ArrayList<ClienteCompania> getRegistros(String vCondicion){
        ArrayList<ClienteCompania> rgs = new ArrayList<ClienteCompania>();
        StringBuffer  query;

        query = new StringBuffer();
        query.append("SELECT *");
        query.append("  FROM VTTM_CLIENTE_COMPANIA ");
        query.append(" where ").append(vCondicion);

        try {            
            rs =  this.getRegistrosSinFiltro(query);
            while(rs.next())
            {
                CCia  = new ClienteCompania();
                CCia.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_CLIENTE_COMPANIA")});
                CCia.setCoCompania(rs.getString("CO_COMPANIA"));
                CCia.setCoClienteCompania(rs.getString("CO_CLIENTE_COMPANIA"));
                CCia.setTiEmpresa(rs.getString("TI_EMPRESA"));
                CCia.setTiCliente(rs.getString("TI_CLIENTE"));
                CCia.setDeRazonSocial(rs.getString("DE_RAZON_SOCIAL"));
                CCia.setNuRucCliente(rs.getString("NU_RUC_CLIENTE"));
                CCia.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                CCia.setTipoDocIdentidadTitularTar(rs.getString("TIPO_DOC_IDENTIDAD_TITULAR_TAR"));
                CCia.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                CCia.setApPaternoCliente(rs.getString("AP_PATERNO_CLIENTE"));
                CCia.setApMaternoCliente(rs.getString("AP_MATERNO_CLIENTE"));
                CCia.setNoCliente(rs.getString("NO_CLIENTE"));
                CCia.setFeNacimiento(rs.getDate("FE_NACIMIENTO"));
                CCia.setDeObservCliente(rs.getString("DE_OBSERV_CLIENTE"));
                CCia.setNuTelReferencia(rs.getString("NU_TEL_REFERENCIA"));
                CCia.setNuTelFax(rs.getString("NU_TEL_FAX"));
                CCia.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                CCia.setDeEmail(rs.getString("DE_EMAIL"));
                CCia.setCoFormaPago1(rs.getString("CO_FORMA_PAGO1"));
                CCia.setNuTarjeta1(rs.getString("NU_TARJETA1"));
                CCia.setFeVenceTarjeta1(rs.getDate("FE_VENCE_TARJETA1"));
                CCia.setCoFormaPago2(rs.getString("CO_FORMA_PAGO2"));
                CCia.setNuTarjeta2(rs.getString("NU_TARJETA2"));
                CCia.setFeVenceTarjeta2(rs.getDate("FE_VENCE_TARJETA2"));
                CCia.setCoEmpleadoInkafarma(rs.getString("CO_EMPLEADO_INKAFARMA"));
                CCia.setInClienteEspecial(rs.getString("IN_CLIENTE_ESPECIAL"));
                CCia.setPcDctoClienteEspecial(rs.getDouble("PC_DCTO_CLIENTE_ESPECIAL"));
                CCia.setVaSaldoCuenta(rs.getDouble("VA_SALDO_CUENTA"));
                CCia.setEsCliente(rs.getString("ES_CLIENTE"));
                CCia.setIdCreaCliente(rs.getString("ID_CREA_CLIENTE"));
                CCia.setFeCreaCliente(rs.getDate("FE_CREA_CLIENTE"));
                CCia.setIdModCliente(rs.getString("ID_MOD_CLIENTE"));
                CCia.setFeModCliente(rs.getDate("FE_MOD_CLIENTE"));
                CCia.setNuTransferenciaErp(rs.getString("NU_TRANSFERENCIA_ERP"));
                CCia.setFeTransferenciaErp(rs.getDate("FE_TRANSFERENCIA_ERP"));
                CCia.setInLimiteCredito(rs.getString("IN_LIMITE_CREDITO"));
                CCia.setApPaternoTitularTarj1(rs.getString("AP_PATERNO_TITULAR_TARJ1"));
                CCia.setApMaternoTitularTarj1(rs.getString("AP_MATERNO_TITULAR_TARJ1"));
                CCia.setNoTitularTarj1(rs.getString("NO_TITULAR_TARJ1"));
                rgs.add(CCia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CClienteCompania.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rgs;
    }
    
    
    @Override
    public ArrayList<ClienteCompania> getRegistros(Object[] op) {  
        return this.getRegistros(new String[]{}, op!=null?new String[]{ClienteCompania.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<ClienteCompania> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<ClienteCompania> getRegistros() {                 
        ArrayList<ClienteCompania> rgs = new ArrayList<ClienteCompania>();
        try {                        
            rs = this.getRegistros(ClienteCompania.nt,ClienteCompania.FULL_NOM_CAMPOS);                       
            while(rs.next())
            {
                CCia  = new ClienteCompania();
                CCia.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_CLIENTE_COMPANIA")});
                CCia.setCoCompania(rs.getString("CO_COMPANIA"));
                CCia.setCoClienteCompania(rs.getString("CO_CLIENTE_COMPANIA"));
                CCia.setTiEmpresa(rs.getString("TI_EMPRESA"));
                CCia.setTiCliente(rs.getString("TI_CLIENTE"));
                CCia.setDeRazonSocial(rs.getString("DE_RAZON_SOCIAL"));
                CCia.setNuRucCliente(rs.getString("NU_RUC_CLIENTE"));
                CCia.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                CCia.setTipoDocIdentidadTitularTar(rs.getString("TIPO_DOC_IDENTIDAD_TITULAR_TAR"));
                CCia.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                CCia.setApPaternoCliente(rs.getString("AP_PATERNO_CLIENTE"));
                CCia.setApMaternoCliente(rs.getString("AP_MATERNO_CLIENTE"));
                CCia.setNoCliente(rs.getString("NO_CLIENTE"));
                CCia.setFeNacimiento(rs.getDate("FE_NACIMIENTO"));
                CCia.setDeObservCliente(rs.getString("DE_OBSERV_CLIENTE"));
                CCia.setNuTelReferencia(rs.getString("NU_TEL_REFERENCIA"));
                CCia.setNuTelFax(rs.getString("NU_TEL_FAX"));
                CCia.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                CCia.setDeEmail(rs.getString("DE_EMAIL"));
                CCia.setCoFormaPago1(rs.getString("CO_FORMA_PAGO1"));
                CCia.setNuTarjeta1(rs.getString("NU_TARJETA1"));
                CCia.setFeVenceTarjeta1(rs.getDate("FE_VENCE_TARJETA1"));
                CCia.setCoFormaPago2(rs.getString("CO_FORMA_PAGO2"));
                CCia.setNuTarjeta2(rs.getString("NU_TARJETA2"));
                CCia.setFeVenceTarjeta2(rs.getDate("FE_VENCE_TARJETA2"));
                CCia.setCoEmpleadoInkafarma(rs.getString("CO_EMPLEADO_INKAFARMA"));
                CCia.setInClienteEspecial(rs.getString("IN_CLIENTE_ESPECIAL"));
                CCia.setPcDctoClienteEspecial(rs.getDouble("PC_DCTO_CLIENTE_ESPECIAL"));
                CCia.setVaSaldoCuenta(rs.getDouble("VA_SALDO_CUENTA"));
                CCia.setEsCliente(rs.getString("ES_CLIENTE"));
                CCia.setIdCreaCliente(rs.getString("ID_CREA_CLIENTE"));
                CCia.setFeCreaCliente(rs.getDate("FE_CREA_CLIENTE"));
                CCia.setIdModCliente(rs.getString("ID_MOD_CLIENTE"));
                CCia.setFeModCliente(rs.getDate("FE_MOD_CLIENTE"));
                CCia.setNuTransferenciaErp(rs.getString("NU_TRANSFERENCIA_ERP"));
                CCia.setFeTransferenciaErp(rs.getDate("FE_TRANSFERENCIA_ERP"));
                CCia.setInLimiteCredito(rs.getString("IN_LIMITE_CREDITO"));
                CCia.setApPaternoTitularTarj1(rs.getString("AP_PATERNO_TITULAR_TARJ1"));
                CCia.setApMaternoTitularTarj1(rs.getString("AP_MATERNO_TITULAR_TARJ1"));
                CCia.setNoTitularTarj1(rs.getString("NO_TITULAR_TARJ1"));
                rgs.add(CCia);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public ClienteCompania getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(ClienteCompania.nt, ClienteCompania.FULL_NOM_CAMPOS, ClienteCompania.COLUMNA_PK , id);
            CCia  = new ClienteCompania();
            while(rs.next())
            {   CCia.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_CLIENTE_COMPANIA")});
                CCia.setCoCompania(rs.getString("CO_COMPANIA"));
                CCia.setCoClienteCompania(rs.getString("CO_CLIENTE_COMPANIA"));
                CCia.setTiEmpresa(rs.getString("TI_EMPRESA"));
                CCia.setTiCliente(rs.getString("TI_CLIENTE"));
                CCia.setDeRazonSocial(rs.getString("DE_RAZON_SOCIAL"));
                CCia.setNuRucCliente(rs.getString("NU_RUC_CLIENTE"));
                CCia.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                CCia.setTipoDocIdentidadTitularTar(rs.getString("TIPO_DOC_IDENTIDAD_TITULAR_TAR"));
                CCia.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                CCia.setApPaternoCliente(rs.getString("AP_PATERNO_CLIENTE"));
                CCia.setApMaternoCliente(rs.getString("AP_MATERNO_CLIENTE"));
                CCia.setNoCliente(rs.getString("NO_CLIENTE"));
                CCia.setFeNacimiento(rs.getDate("FE_NACIMIENTO"));
                CCia.setDeObservCliente(rs.getString("DE_OBSERV_CLIENTE"));
                CCia.setNuTelReferencia(rs.getString("NU_TEL_REFERENCIA"));
                CCia.setNuTelFax(rs.getString("NU_TEL_FAX"));
                CCia.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                CCia.setDeEmail(rs.getString("DE_EMAIL"));
                CCia.setCoFormaPago1(rs.getString("CO_FORMA_PAGO1"));
                CCia.setNuTarjeta1(rs.getString("NU_TARJETA1"));
                CCia.setFeVenceTarjeta1(rs.getDate("FE_VENCE_TARJETA1"));
                CCia.setCoFormaPago2(rs.getString("CO_FORMA_PAGO2"));
                CCia.setNuTarjeta2(rs.getString("NU_TARJETA2"));
                CCia.setFeVenceTarjeta2(rs.getDate("FE_VENCE_TARJETA2"));
                CCia.setCoEmpleadoInkafarma(rs.getString("CO_EMPLEADO_INKAFARMA"));
                CCia.setInClienteEspecial(rs.getString("IN_CLIENTE_ESPECIAL"));
                CCia.setPcDctoClienteEspecial(rs.getDouble("PC_DCTO_CLIENTE_ESPECIAL"));
                CCia.setVaSaldoCuenta(rs.getDouble("VA_SALDO_CUENTA"));
                CCia.setEsCliente(rs.getString("ES_CLIENTE"));
                CCia.setIdCreaCliente(rs.getString("ID_CREA_CLIENTE"));
                CCia.setFeCreaCliente(rs.getDate("FE_CREA_CLIENTE"));
                CCia.setIdModCliente(rs.getString("ID_MOD_CLIENTE"));
                CCia.setFeModCliente(rs.getDate("FE_MOD_CLIENTE"));
                CCia.setNuTransferenciaErp(rs.getString("NU_TRANSFERENCIA_ERP"));
                CCia.setFeTransferenciaErp(rs.getDate("FE_TRANSFERENCIA_ERP"));
                CCia.setInLimiteCredito(rs.getString("IN_LIMITE_CREDITO"));
                CCia.setApPaternoTitularTarj1(rs.getString("AP_PATERNO_TITULAR_TARJ1"));
                CCia.setApMaternoTitularTarj1(rs.getString("AP_MATERNO_TITULAR_TARJ1"));
                CCia.setNoTitularTarj1(rs.getString("NO_TITULAR_TARJ1"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return CCia;
    }
    
    @Override
    public ArrayList<ClienteCompania> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<ClienteCompania> rgs = new ArrayList<ClienteCompania>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(ClienteCompania.nt, ClienteCompania.COLUMNA_ACTIVO, ClienteCompania.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(ClienteCompania.nt, ClienteCompania.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
//            rs = this.getRegistros(Proveedores.nt, campos, columnaId, id,null);
            rs = this.getRegistros(ClienteCompania.nt, campos, columnaId, id, ClienteCompania.COLUMNA_ORDER);
            if(this.numRegistros<finalPag){
              finalPag =  this.numRegistros;
            }
            if(finalPag>inicioPag){
                inicioPag -= (finalPag-inicioPag)-1;
            }
            while(rs.next()){
                CCia  = new ClienteCompania();
                CCia.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_CLIENTE_COMPANIA")});
                CCia.setCoCompania(rs.getString("CO_COMPANIA"));
                CCia.setCoClienteCompania(rs.getString("CO_CLIENTE_COMPANIA"));
                CCia.setTiEmpresa(rs.getString("TI_EMPRESA"));
                CCia.setTiCliente(rs.getString("TI_CLIENTE"));
                CCia.setDeRazonSocial(rs.getString("DE_RAZON_SOCIAL"));
                CCia.setNuRucCliente(rs.getString("NU_RUC_CLIENTE"));
                CCia.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                CCia.setTipoDocIdentidadTitularTar(rs.getString("TIPO_DOC_IDENTIDAD_TITULAR_TAR"));
                CCia.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                CCia.setApPaternoCliente(rs.getString("AP_PATERNO_CLIENTE"));
                CCia.setApMaternoCliente(rs.getString("AP_MATERNO_CLIENTE"));
                CCia.setNoCliente(rs.getString("NO_CLIENTE"));
                CCia.setFeNacimiento(rs.getDate("FE_NACIMIENTO"));
                CCia.setDeObservCliente(rs.getString("DE_OBSERV_CLIENTE"));
                CCia.setNuTelReferencia(rs.getString("NU_TEL_REFERENCIA"));
                CCia.setNuTelFax(rs.getString("NU_TEL_FAX"));
                CCia.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                CCia.setDeEmail(rs.getString("DE_EMAIL"));
                CCia.setCoFormaPago1(rs.getString("CO_FORMA_PAGO1"));
                CCia.setNuTarjeta1(rs.getString("NU_TARJETA1"));
                CCia.setFeVenceTarjeta1(rs.getDate("FE_VENCE_TARJETA1"));
                CCia.setCoFormaPago2(rs.getString("CO_FORMA_PAGO2"));
                CCia.setNuTarjeta2(rs.getString("NU_TARJETA2"));
                CCia.setFeVenceTarjeta2(rs.getDate("FE_VENCE_TARJETA2"));
                CCia.setCoEmpleadoInkafarma(rs.getString("CO_EMPLEADO_INKAFARMA"));
                CCia.setInClienteEspecial(rs.getString("IN_CLIENTE_ESPECIAL"));
                CCia.setPcDctoClienteEspecial(rs.getDouble("PC_DCTO_CLIENTE_ESPECIAL"));
                CCia.setVaSaldoCuenta(rs.getDouble("VA_SALDO_CUENTA"));
                CCia.setEsCliente(rs.getString("ES_CLIENTE"));
                CCia.setIdCreaCliente(rs.getString("ID_CREA_CLIENTE"));
                CCia.setFeCreaCliente(rs.getDate("FE_CREA_CLIENTE"));
                CCia.setIdModCliente(rs.getString("ID_MOD_CLIENTE"));
                CCia.setFeModCliente(rs.getDate("FE_MOD_CLIENTE"));
                CCia.setNuTransferenciaErp(rs.getString("NU_TRANSFERENCIA_ERP"));
                CCia.setFeTransferenciaErp(rs.getDate("FE_TRANSFERENCIA_ERP"));
                CCia.setInLimiteCredito(rs.getString("IN_LIMITE_CREDITO"));
                CCia.setApPaternoTitularTarj1(rs.getString("AP_PATERNO_TITULAR_TARJ1"));
                CCia.setApMaternoTitularTarj1(rs.getString("AP_MATERNO_TITULAR_TARJ1"));
                CCia.setNoTitularTarj1(rs.getString("NO_TITULAR_TARJ1"));
                rgs.add(CCia);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
        
    }

    @Override
    public JAbstractModelBD getRegistro() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JAbstractModelBD getRegistro(JAbstractModelBD mdl, boolean opcion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JAbstractModelBD buscarRegistro(Object cvl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException {
        CCia = (ClienteCompania)mdl;
        int gravado = 0;
        String campos = ClienteCompania.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {CCia.getCoCompania(),
                            CCia.getCoClienteCompania(),
                            CCia.getTiEmpresa(),
                            CCia.getTiCliente(),
                            CCia.getDeRazonSocial(),
                            CCia.getNuRucCliente(),
                            CCia.getTiDocIdentidad(),
                            CCia.getTipoDocIdentidadTitularTar(),
                            CCia.getNuDocIdentidad(),
                            CCia.getApPaternoCliente(),
                            CCia.getApMaternoCliente(),
                            CCia.getNoCliente(),
                            CCia.getFeNacimiento(),
                            CCia.getDeObservCliente(),
                            CCia.getNuTelReferencia(),
                            CCia.getNuTelFax(),
                            CCia.getNuTelMovil(),
                            CCia.getDeEmail(),
                            CCia.getCoFormaPago1(),
                            CCia.getNuTarjeta1(),
                            CCia.getFeVenceTarjeta1(),
                            CCia.getCoFormaPago2(),
                            CCia.getNuTarjeta2(),
                            CCia.getFeVenceTarjeta2(),
                            CCia.getCoEmpleadoInkafarma(),
                            CCia.getInClienteEspecial(),
                            CCia.getPcDctoClienteEspecial(),
                            CCia.getVaSaldoCuenta(),
                            CCia.getEsCliente(),
                            CCia.getIdCreaCliente(),
                            CCia.getFeCreaCliente(),
                            CCia.getIdModCliente(),
                            CCia.getFeModCliente(),
                            CCia.getNuTransferenciaErp(),
                            CCia.getFeTransferenciaErp(),
                            CCia.getInLimiteCredito(),
                            CCia.getApPaternoTitularTarj1(),
                            CCia.getApMaternoTitularTarj1(),
                            CCia.getNoTitularTarj1()
                           };
       
           gravado = this.agregarRegistroPs(CCia.getNombreTabla(),ClienteCompania.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        CCia = (ClienteCompania)mdl;
        int gravado = 0;        
        
        Object[] valores = {CCia.getCoCompania(),
                            CCia.getCoClienteCompania(),
                            CCia.getTiEmpresa(),
                            CCia.getTiCliente(),
                            CCia.getDeRazonSocial(),
                            CCia.getNuRucCliente(),
                            CCia.getTiDocIdentidad(),
                            CCia.getTipoDocIdentidadTitularTar(),
                            CCia.getNuDocIdentidad(),
                            CCia.getApPaternoCliente(),
                            CCia.getApMaternoCliente(),
                            CCia.getNoCliente(),
                            CCia.getFeNacimiento(),
                            CCia.getDeObservCliente(),
                            CCia.getNuTelReferencia(),
                            CCia.getNuTelFax(),
                            CCia.getNuTelMovil(),
                            CCia.getDeEmail(),
                            CCia.getCoFormaPago1(),
                            CCia.getNuTarjeta1(),
                            CCia.getFeVenceTarjeta1(),
                            CCia.getCoFormaPago2(),
                            CCia.getNuTarjeta2(),
                            CCia.getFeVenceTarjeta2(),
                            CCia.getCoEmpleadoInkafarma(),
                            CCia.getInClienteEspecial(),
                            CCia.getPcDctoClienteEspecial(),
                            CCia.getVaSaldoCuenta(),
                            CCia.getEsCliente(),
                            CCia.getIdCreaCliente(),
                            CCia.getFeCreaCliente(),
                            CCia.getIdModCliente(),
                            CCia.getFeModCliente(),
                            CCia.getNuTransferenciaErp(),
                            CCia.getFeTransferenciaErp(),
                            CCia.getInLimiteCredito(),
                            CCia.getApPaternoTitularTarj1(),
                            CCia.getApMaternoTitularTarj1(),
                            CCia.getNoTitularTarj1()};
       
        
//      gravado = this.agregarRegistroPs   (prv.getNombreTabla(),Proveedores.FULL_NOM_CAMPOS, valores);        

        //gravado = this.actualizarRegistroPs(prv.getNombreTabla(), this.adjuntarSimbolo(campos, ",", "?")                                       +Ex.WHERE+ unirColumnasValores(mdl.getCampoClavePrimaria(),prv.getPrimaryKey())+" ", valores);        
        gravado = this.actualizarRegistroPs(CCia.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(ClienteCompania.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(ClienteCompania.COLUMNA_PK,CCia.getPrimaryKey()) , valores);

   
        return gravado;
    }

    
    
    
    
    public ClienteCompania getClienteCompania() {
        if(CCia == null)
        {
            CCia = new ClienteCompania();
        }
        return CCia;
    }

    public void setClienteCompania(JAbstractModelBD prv) {
        this.CCia = (ClienteCompania)CCia;
    }
    public String getNuevoCodigo(){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(ClienteCompania.nt,"rtrim(ltrim(to_char(max(co_Cliente_Compania) + 1,'00000000')))"," co_compania = '" + AtuxVariables.vCodigoCompania + "'");
        } catch (SQLException ex) {
            Logger.getLogger(CClienteCompania.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }
}
