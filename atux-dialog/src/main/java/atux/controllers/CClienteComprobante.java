package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.ClienteComprobante;
import java.sql.SQLException;
import java.util.ArrayList;

public class CClienteComprobante extends JAbstractController{
    private ClienteComprobante clienteComprobante;

    @Override
    public ArrayList<ClienteComprobante> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{ClienteComprobante.COLUMNA_ACTIVO}:null,op);
    }

    public ArrayList<ClienteComprobante> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       

    @Override
    public ArrayList<ClienteComprobante> getRegistros() {                 
        ArrayList<ClienteComprobante> rgs = new ArrayList<ClienteComprobante>();
        try {                        
//            rs = this.getRegistros(TipoDeCambio.nt,TipoDeCambio.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(ClienteComprobante.nt, ClienteComprobante.FULL_NOM_CAMPOS, null, null, ClienteComprobante.COLUMNA_ORDER);
            while(rs.next())
            {
                clienteComprobante = new ClienteComprobante();
                clienteComprobante.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("NU_COMPROBANTE")});
                clienteComprobante.setCoCompania(rs.getString("CO_COMPANIA"));
                clienteComprobante.setCoLocal(rs.getString("CO_LOCAL"));
                clienteComprobante.setCoClienteLocal(rs.getString("CO_CLIENTE_LOCAL"));
                clienteComprobante.setNuComprobante(rs.getString("NU_COMPROBANTE"));
                rgs.add(clienteComprobante);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public ClienteComprobante getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(ClienteComprobante.nt, ClienteComprobante.FULL_NOM_CAMPOS, ClienteComprobante.COLUMNA_PK , id);
            while(rs.next())
            {
                clienteComprobante = new ClienteComprobante();
                clienteComprobante.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("NU_COMPROBANTE")});
                clienteComprobante.setCoCompania(rs.getString("CO_COMPANIA"));
                clienteComprobante.setCoLocal(rs.getString("CO_LOCAL"));
                clienteComprobante.setCoClienteLocal(rs.getString("CO_CLIENTE_LOCAL"));
                clienteComprobante.setNuComprobante(rs.getString("NU_COMPROBANTE"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clienteComprobante;
    }

    public boolean  getExisteComprobante(Object[] id){
        try {            
            rs = this.selectPorPk(ClienteComprobante.nt, ClienteComprobante.FULL_NOM_CAMPOS, ClienteComprobante.COLUMNA_PK , id);
            while(rs.next()){
                clienteComprobante = new ClienteComprobante();
                clienteComprobante.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("NU_COMPROBANTE")});
                clienteComprobante.setCoCompania(rs.getString("CO_COMPANIA"));
                clienteComprobante.setCoLocal(rs.getString("CO_LOCAL"));
                clienteComprobante.setCoClienteLocal(rs.getString("CO_CLIENTE_LOCAL"));
                clienteComprobante.setNuComprobante(rs.getString("NU_COMPROBANTE"));
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }
    
    @Override
    public ArrayList<ClienteComprobante> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<ClienteComprobante> rgs = new ArrayList<ClienteComprobante>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(ClienteComprobante.nt, ClienteComprobante.COLUMNA_ACTIVO, ClienteComprobante.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(ClienteComprobante.nt, ClienteComprobante.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(ClienteComprobante.nt, campos, columnaId, id, ClienteComprobante.COLUMNA_ORDER);
            if(this.numRegistros<finalPag)
           {
              finalPag =  this.numRegistros;
           }
            if(finalPag>inicioPag)
            {
                inicioPag -= (finalPag-inicioPag)-1;
            }
            while(rs.next())
            {
                clienteComprobante = new ClienteComprobante();
                clienteComprobante.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("NU_COMPROBANTE")});
                clienteComprobante.setCoCompania(rs.getString("CO_COMPANIA"));
                clienteComprobante.setCoLocal(rs.getString("CO_LOCAL"));
                clienteComprobante.setCoClienteLocal(rs.getString("CO_CLIENTE_LOCAL"));
                clienteComprobante.setNuComprobante(rs.getString("NU_COMPROBANTE"));
                rgs.add(clienteComprobante);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
        
    }

//    public ArrayList getCliente(String Estado)
//     {
//        ArrayList<ClienteComprobante> rgs = new ArrayList<ClienteComprobante>();
//        try {                        
//            StringBuffer  query = new StringBuffer();
//
//            query.append("SELECT T1.*,t2.de_abr_documento ");
//            query.append("  FROM VTTR_CLIENTE_COMPROBANTE ");
//            query.append(" WHERE t1.ti_doc_identidad = t2.co_documento_identidad ");
//            if (!"T".equals(Estado)){
//                query.append("   AND ES_CLIENTE ='").append(Estado).append("' ");
//            }            
//            query.append(" ORDER BY CO_CLIENTE_LOCAL");
//            rs =  this.getRegistrosSinFiltro(query);
//            
//            while(rs.next())
//            {
//                clienteLocal = new Cliente();
//                clienteLocal.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_CLIENTE_LOCAL")});
//                clienteLocal.setCoCompania(rs.getString("CO_COMPANIA"));
//                clienteLocal.setCoLocal(rs.getString("CO_LOCAL"));
//                clienteLocal.setCoClienteLocal(rs.getString("CO_CLIENTE_LOCAL"));
//                clienteLocal.setTiCliente(rs.getString("TI_CLIENTE"));
//                clienteLocal.setCoCategoriaCliente(rs.getString("CO_CATEGORIA_CLIENTE"));
//                clienteLocal.setDeRazonSocial(rs.getString("DE_RAZON_SOCIAL"));
//                clienteLocal.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
//                clienteLocal.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
//                clienteLocal.setApPaternoCliente(rs.getString("AP_PATERNO_CLIENTE"));
//                clienteLocal.setApMaternoCliente(rs.getString("AP_MATERNO_CLIENTE"));
//                clienteLocal.setNoCliente(rs.getString("NO_CLIENTE"));
//                clienteLocal.setFeNacimiento(rs.getDate("FE_NACIMIENTO"));
//                clienteLocal.setNuTelReferencia(rs.getString("NU_TEL_REFERENCIA"));
//                clienteLocal.setNuTelFax(rs.getString("NU_TEL_FAX"));
//                clienteLocal.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
//                clienteLocal.setDeEmail(rs.getString("DE_EMAIL"));
//                clienteLocal.setInClienteEspecial(rs.getString("IN_CLIENTE_ESPECIAL"));
//                clienteLocal.setCoClienteCompania(rs.getString("CO_CLIENTE_COMPANIA"));
//                clienteLocal.setEsCliente(rs.getString("ES_CLIENTE"));
//                clienteLocal.setIdCreaCliente(rs.getString("ID_CREA_CLIENTE"));
//                clienteLocal.setFeCreaCliente(rs.getDate("FE_CREA_CLIENTE"));
//                clienteLocal.setIdModCliente(rs.getString("ID_MOD_CLIENTE"));
//                clienteLocal.setFeModCliente(rs.getDate("FE_MOD_CLIENTE"));
//                clienteLocal.setDeTipoDocumento(rs.getString("DE_ABR_DOCUMENTO"));
//                rgs.add(clienteLocal);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return rgs;
//    }    
    
//    public ArrayList getBuscarClienteComprobante(String Condicion)
//     {
//        ArrayList<ClienteComprobante> rgs = new ArrayList<ClienteComprobante>();
//        try {                        
//            StringBuffer  query = new StringBuffer();
//
//            query.append("SELECT T1.*,t2.de_abr_documento ");
//            query.append("  FROM VTTR_CLIENTE_LOCAL T1, ");
//            query.append("       COMUN.CMTM_DOCUMENTO_IDENTIDAD T2 ");
//            query.append(" WHERE t1.ti_doc_identidad = t2.co_documento_identidad ");
//                query.append("   AND ").append(Condicion);
//
//            query.append(" ORDER BY CO_CLIENTE_LOCAL");
//            rs =  this.getRegistrosSinFiltro(query);
//            
//            while(rs.next()){
//                clienteLocal = new Cliente();
//                clienteLocal.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_CLIENTE_LOCAL")});
//                clienteLocal.setCoCompania(rs.getString("CO_COMPANIA"));
//                clienteLocal.setCoLocal(rs.getString("CO_LOCAL"));
//                clienteLocal.setCoClienteLocal(rs.getString("CO_CLIENTE_LOCAL"));
//                clienteLocal.setTiCliente(rs.getString("TI_CLIENTE"));
//                clienteLocal.setCoCategoriaCliente(rs.getString("CO_CATEGORIA_CLIENTE"));
//                clienteLocal.setDeRazonSocial(rs.getString("DE_RAZON_SOCIAL"));
//                clienteLocal.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
//                clienteLocal.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
//                clienteLocal.setApPaternoCliente(rs.getString("AP_PATERNO_CLIENTE"));
//                clienteLocal.setApMaternoCliente(rs.getString("AP_MATERNO_CLIENTE"));
//                clienteLocal.setNoCliente(rs.getString("NO_CLIENTE"));
//                clienteLocal.setFeNacimiento(rs.getDate("FE_NACIMIENTO"));
//                clienteLocal.setNuTelReferencia(rs.getString("NU_TEL_REFERENCIA"));
//                clienteLocal.setNuTelFax(rs.getString("NU_TEL_FAX"));
//                clienteLocal.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
//                clienteLocal.setDeEmail(rs.getString("DE_EMAIL"));
//                clienteLocal.setInClienteEspecial(rs.getString("IN_CLIENTE_ESPECIAL"));
//                clienteLocal.setCoClienteCompania(rs.getString("CO_CLIENTE_COMPANIA"));
//                clienteLocal.setEsCliente(rs.getString("ES_CLIENTE"));
//                clienteLocal.setIdCreaCliente(rs.getString("ID_CREA_CLIENTE"));
//                clienteLocal.setFeCreaCliente(rs.getDate("FE_CREA_CLIENTE"));
//                clienteLocal.setIdModCliente(rs.getString("ID_MOD_CLIENTE"));
//                clienteLocal.setFeModCliente(rs.getDate("FE_MOD_CLIENTE"));
//                clienteLocal.setDeTipoDocumento(rs.getString("DE_ABR_DOCUMENTO"));
//                rgs.add(clienteLocal);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return rgs;
//    }    
//    
    
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
        clienteComprobante = (ClienteComprobante)mdl;
        int gravado = 0;
        String campos = ClienteComprobante.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {clienteComprobante.getCoCompania(),
                            clienteComprobante.getCoLocal(),
                            clienteComprobante.getCoClienteLocal(),
                            clienteComprobante.getNuComprobante()
                           };
       
           gravado = this.agregarRegistroPs(clienteComprobante.getNombreTabla(),ClienteComprobante.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        clienteComprobante = (ClienteComprobante)mdl;
        int gravado = 0;        
        
        Object[] valores = {clienteComprobante.getCoCompania(),
                            clienteComprobante.getCoLocal(),
                            clienteComprobante.getCoClienteLocal(),
                            clienteComprobante.getNuComprobante()
                           };

        gravado = this.actualizarRegistroPs(clienteComprobante.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(ClienteComprobante.FULL_NOM_CAMPOS), ",", "?")+Ex.WHERE+ unirColumnasValores(ClienteComprobante.COLUMNA_PK, clienteComprobante.getPrimaryKey()) , valores);
        return gravado;
    }

    public ClienteComprobante getClienteComprobante() {
        if(clienteComprobante == null)
        {
            clienteComprobante = new ClienteComprobante();
        }
        return clienteComprobante;
    }

    public void setClienteComprobante(JAbstractModelBD prv) {
        this.clienteComprobante = (ClienteComprobante)prv;
    }

   
}
