package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.managerbd.BaseConexion;
import atux.modelbd.Cliente;
import atux.util.common.AtuxDBUtility;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CCliente extends JAbstractController{
    private Cliente clienteLocal;


    @Override
    public ArrayList<Cliente> getRegistros(Object[] op) {
        return this.getRegistros(new String[]{}, op!=null?new String[]{Cliente.COLUMNA_ACTIVO}:null,op);
    }

    public ArrayList<Cliente> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }

    public boolean  getExisteDocumento(Object[] id){
        try {
            rs = this.selectPorPk(Cliente.nt, Cliente.FULL_NOM_CAMPOS, Cliente.COLUMNA_PK2 , id);
            while(rs.next()){
                clienteLocal = new Cliente();
                clienteLocal.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_CLIENTE_LOCAL")});
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }


    @Override
    public ArrayList<Cliente> getRegistros() {
        ArrayList<Cliente> rgs = new ArrayList<Cliente>();
        try {
            rs = this.getRegistros(Cliente.nt, Cliente.FULL_NOM_CAMPOS, null, null, Cliente.COLUMNA_ORDER);
            while(rs.next())
            {
                clienteLocal = new Cliente();
                clienteLocal.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_CLIENTE_LOCAL")});
                clienteLocal.setCoCompania(rs.getString("CO_COMPANIA"));
                clienteLocal.setCoLocal(rs.getString("CO_LOCAL"));
                clienteLocal.setCoClienteLocal(rs.getString("CO_CLIENTE_LOCAL"));
                clienteLocal.setTiCliente(rs.getString("TI_CLIENTE"));
                clienteLocal.setCoCategoriaCliente(rs.getString("CO_CATEGORIA_CLIENTE"));
                clienteLocal.setDeRazonSocial(rs.getString("DE_RAZON_SOCIAL"));
                clienteLocal.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                clienteLocal.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                clienteLocal.setApPaternoCliente(rs.getString("AP_PATERNO_CLIENTE"));
                clienteLocal.setApMaternoCliente(rs.getString("AP_MATERNO_CLIENTE"));
                clienteLocal.setNoCliente(rs.getString("NO_CLIENTE"));
                clienteLocal.setFeNacimiento(rs.getDate("FE_NACIMIENTO"));
                clienteLocal.setNuTelReferencia(rs.getString("NU_TEL_REFERENCIA"));
                clienteLocal.setNuTelFax(rs.getString("NU_TEL_FAX"));
                clienteLocal.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                clienteLocal.setDeEmail(rs.getString("DE_EMAIL"));
                clienteLocal.setInClienteEspecial(rs.getString("IN_CLIENTE_ESPECIAL"));
                clienteLocal.setCoClienteCompania(rs.getString("CO_CLIENTE_COMPANIA"));
                clienteLocal.setEsCliente(rs.getString("ES_CLIENTE"));
                clienteLocal.setIdCreaCliente(rs.getString("ID_CREA_CLIENTE"));
                clienteLocal.setFeCreaCliente(rs.getDate("FE_CREA_CLIENTE"));
                clienteLocal.setIdModCliente(rs.getString("ID_MOD_CLIENTE"));
                clienteLocal.setFeModCliente(rs.getDate("FE_MOD_CLIENTE"));
                clienteLocal.setDeTipoDocumento("");
                rgs.add(clienteLocal);
            }
            rs.close();
            BaseConexion.closeConnection();
            BaseConexion.setConnectionNull();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            // Se cierran los recursos de base de datos.
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println("No ha podido cerrar ResultSet.");
            }
        }
        return rgs;
    }

    public Cliente getRegistroPorPk(Object[] id)
    {
        try {
            rs = this.selectPorPk(Cliente.nt, Cliente.FULL_NOM_CAMPOS, Cliente.COLUMNA_PK , id);
            while(rs.next())
            {
                clienteLocal = new Cliente();
                clienteLocal.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_CLIENTE_LOCAL")});
                clienteLocal.setCoCompania(rs.getString("CO_COMPANIA"));
                clienteLocal.setCoLocal(rs.getString("CO_LOCAL"));
                clienteLocal.setCoClienteLocal(rs.getString("CO_CLIENTE_LOCAL"));
                clienteLocal.setTiCliente(rs.getString("TI_CLIENTE"));
                clienteLocal.setCoCategoriaCliente(rs.getString("CO_CATEGORIA_CLIENTE"));
                clienteLocal.setDeRazonSocial(rs.getString("DE_RAZON_SOCIAL"));
                clienteLocal.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                clienteLocal.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                clienteLocal.setApPaternoCliente(rs.getString("AP_PATERNO_CLIENTE"));
                clienteLocal.setApMaternoCliente(rs.getString("AP_MATERNO_CLIENTE"));
                clienteLocal.setNoCliente(rs.getString("NO_CLIENTE"));
                clienteLocal.setFeNacimiento(rs.getDate("FE_NACIMIENTO"));
                clienteLocal.setNuTelReferencia(rs.getString("NU_TEL_REFERENCIA"));
                clienteLocal.setNuTelFax(rs.getString("NU_TEL_FAX"));
                clienteLocal.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                clienteLocal.setDeEmail(rs.getString("DE_EMAIL"));
                clienteLocal.setInClienteEspecial(rs.getString("IN_CLIENTE_ESPECIAL"));
                clienteLocal.setCoClienteCompania(rs.getString("CO_CLIENTE_COMPANIA"));
                clienteLocal.setEsCliente(rs.getString("ES_CLIENTE"));
                clienteLocal.setIdCreaCliente(rs.getString("ID_CREA_CLIENTE"));
                clienteLocal.setFeCreaCliente(rs.getDate("FE_CREA_CLIENTE"));
                clienteLocal.setIdModCliente(rs.getString("ID_MOD_CLIENTE"));
                clienteLocal.setFeModCliente(rs.getDate("FE_MOD_CLIENTE"));
                clienteLocal.setDeTipoDocumento("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clienteLocal;
    }

    public Cliente getRegistroPorPk2(Object[] id)
    {
        try {
            rs = this.selectPorPk(Cliente.nt, Cliente.FULL_NOM_CAMPOS, Cliente.COLUMNA_PK2 , id);
            while(rs.next())
            {
                clienteLocal = new Cliente();
                clienteLocal.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_CLIENTE_LOCAL")});
                clienteLocal.setCoCompania(rs.getString("CO_COMPANIA"));
                clienteLocal.setCoLocal(rs.getString("CO_LOCAL"));
                clienteLocal.setCoClienteLocal(rs.getString("CO_CLIENTE_LOCAL"));
                clienteLocal.setTiCliente(rs.getString("TI_CLIENTE"));
                clienteLocal.setCoCategoriaCliente(rs.getString("CO_CATEGORIA_CLIENTE"));
                clienteLocal.setDeRazonSocial(rs.getString("DE_RAZON_SOCIAL"));
                clienteLocal.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                clienteLocal.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                clienteLocal.setApPaternoCliente(rs.getString("AP_PATERNO_CLIENTE"));
                clienteLocal.setApMaternoCliente(rs.getString("AP_MATERNO_CLIENTE"));
                clienteLocal.setNoCliente(rs.getString("NO_CLIENTE"));
                clienteLocal.setFeNacimiento(rs.getDate("FE_NACIMIENTO"));
                clienteLocal.setNuTelReferencia(rs.getString("NU_TEL_REFERENCIA"));
                clienteLocal.setNuTelFax(rs.getString("NU_TEL_FAX"));
                clienteLocal.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                clienteLocal.setDeEmail(rs.getString("DE_EMAIL"));
                clienteLocal.setInClienteEspecial(rs.getString("IN_CLIENTE_ESPECIAL"));
                clienteLocal.setCoClienteCompania(rs.getString("CO_CLIENTE_COMPANIA"));
                clienteLocal.setEsCliente(rs.getString("ES_CLIENTE"));
                clienteLocal.setIdCreaCliente(rs.getString("ID_CREA_CLIENTE"));
                clienteLocal.setFeCreaCliente(rs.getDate("FE_CREA_CLIENTE"));
                clienteLocal.setIdModCliente(rs.getString("ID_MOD_CLIENTE"));
                clienteLocal.setFeModCliente(rs.getDate("FE_MOD_CLIENTE"));
                clienteLocal.setDeTipoDocumento("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clienteLocal;
    }

    @Override
    public ArrayList<Cliente> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<Cliente> rgs = new ArrayList<Cliente>();
        try {
            if(id != null){
                this.numRegistros = this.getNumeroRegistros(Cliente.nt, Cliente.COLUMNA_ACTIVO, Cliente.COLUMNA_ACTIVO, id);
            }else{
                this.numRegistros = this.getNumeroRegistros(Cliente.nt, Cliente.COLUMNA_ACTIVO);
                if(rs.isClosed()){
                    System.out.println("resultset esta cerrado...");
                }
            }
            rs = this.getRegistros(Cliente.nt, campos, columnaId, id, Cliente.COLUMNA_ORDER);
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
                clienteLocal = new Cliente();
                clienteLocal.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_CLIENTE_LOCAL")});
                clienteLocal.setCoCompania(rs.getString("CO_COMPANIA"));
                clienteLocal.setCoLocal(rs.getString("CO_LOCAL"));
                clienteLocal.setCoClienteLocal(rs.getString("CO_CLIENTE_LOCAL"));
                clienteLocal.setTiCliente(rs.getString("TI_CLIENTE"));
                clienteLocal.setCoCategoriaCliente(rs.getString("CO_CATEGORIA_CLIENTE"));
                clienteLocal.setDeRazonSocial(rs.getString("DE_RAZON_SOCIAL"));
                clienteLocal.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                clienteLocal.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                clienteLocal.setApPaternoCliente(rs.getString("AP_PATERNO_CLIENTE"));
                clienteLocal.setApMaternoCliente(rs.getString("AP_MATERNO_CLIENTE"));
                clienteLocal.setNoCliente(rs.getString("NO_CLIENTE"));
                clienteLocal.setFeNacimiento(rs.getDate("FE_NACIMIENTO"));
                clienteLocal.setNuTelReferencia(rs.getString("NU_TEL_REFERENCIA"));
                clienteLocal.setNuTelFax(rs.getString("NU_TEL_FAX"));
                clienteLocal.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                clienteLocal.setDeEmail(rs.getString("DE_EMAIL"));
                clienteLocal.setInClienteEspecial(rs.getString("IN_CLIENTE_ESPECIAL"));
                clienteLocal.setCoClienteCompania(rs.getString("CO_CLIENTE_COMPANIA"));
                clienteLocal.setEsCliente(rs.getString("ES_CLIENTE"));
                clienteLocal.setIdCreaCliente(rs.getString("ID_CREA_CLIENTE"));
                clienteLocal.setFeCreaCliente(rs.getDate("FE_CREA_CLIENTE"));
                clienteLocal.setIdModCliente(rs.getString("ID_MOD_CLIENTE"));
                clienteLocal.setFeModCliente(rs.getDate("FE_MOD_CLIENTE"));
                clienteLocal.setDeTipoDocumento("");
                rgs.add(clienteLocal);
            }
            rs.close();
            BaseConexion.closeConnection();
            BaseConexion.setConnectionNull();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            // Se cierran los recursos de base de datos.
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println("No ha podido cerrar ResultSet.");
            }
        }
        return rgs;
    }

    public ArrayList getCliente(String Estado)
    {
        ArrayList<Cliente> rgs = new ArrayList<Cliente>();
        try {
            StringBuffer  query = new StringBuffer();

            query.append("SELECT T1.*,t2.de_abr_documento ");
            query.append("  FROM VTTR_CLIENTE_LOCAL T1, ");
            query.append("       CMTM_DOCUMENTO_IDENTIDAD T2 ");
            query.append(" WHERE t1.ti_doc_identidad = t2.co_documento_identidad ");
            //query.append("   AND CO_CLIENTE_LOCAL ='00061056'");
            if (!"T".equals(Estado)){
                query.append("   AND ES_CLIENTE ='").append(Estado).append("' ");
            }
            query.append(" ORDER BY DE_RAZON_SOCIAL");
            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())
            {
                clienteLocal = new Cliente();
                clienteLocal.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_CLIENTE_LOCAL")});
                clienteLocal.setCoCompania(rs.getString("CO_COMPANIA"));
                clienteLocal.setCoLocal(rs.getString("CO_LOCAL"));
                clienteLocal.setCoClienteLocal(rs.getString("CO_CLIENTE_LOCAL"));
                clienteLocal.setTiCliente(rs.getString("TI_CLIENTE"));
                clienteLocal.setCoCategoriaCliente(rs.getString("CO_CATEGORIA_CLIENTE"));
                clienteLocal.setDeRazonSocial(rs.getString("DE_RAZON_SOCIAL"));
                clienteLocal.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                clienteLocal.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                clienteLocal.setApPaternoCliente(rs.getString("AP_PATERNO_CLIENTE"));
                clienteLocal.setApMaternoCliente(rs.getString("AP_MATERNO_CLIENTE"));
                clienteLocal.setNoCliente(rs.getString("NO_CLIENTE"));
                clienteLocal.setFeNacimiento(rs.getDate("FE_NACIMIENTO"));
                clienteLocal.setNuTelReferencia(rs.getString("NU_TEL_REFERENCIA"));
                clienteLocal.setNuTelFax(rs.getString("NU_TEL_FAX"));
                clienteLocal.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                clienteLocal.setDeEmail(rs.getString("DE_EMAIL"));
                clienteLocal.setInClienteEspecial(rs.getString("IN_CLIENTE_ESPECIAL"));
                clienteLocal.setCoClienteCompania(rs.getString("CO_CLIENTE_COMPANIA"));
                clienteLocal.setEsCliente(rs.getString("ES_CLIENTE"));
                clienteLocal.setIdCreaCliente(rs.getString("ID_CREA_CLIENTE"));
                clienteLocal.setFeCreaCliente(rs.getDate("FE_CREA_CLIENTE"));
                clienteLocal.setIdModCliente(rs.getString("ID_MOD_CLIENTE"));
                clienteLocal.setFeModCliente(rs.getDate("FE_MOD_CLIENTE"));
                clienteLocal.setDeTipoDocumento(rs.getString("DE_ABR_DOCUMENTO"));
                rgs.add(clienteLocal);
            }
            rs.close();
            BaseConexion.closeConnection();
            BaseConexion.setConnectionNull();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            // Se cierran los recursos de base de datos.
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println("No ha podido cerrar ResultSet.");
            }
        }
        return rgs;
    }

    public ArrayList getClienteValesArcangel(String Estado)
    {
        ArrayList<Cliente> rgs = new ArrayList<Cliente>();
        try {
            StringBuffer  query = new StringBuffer();

            query.append("SELECT T1.*,t2.de_abr_documento, t3.nu_comprobante ");
            query.append("  FROM venta.VTTR_CLIENTE_LOCAL T1, ");
            query.append("       COMUN.CMTM_DOCUMENTO_IDENTIDAD T2, ");
            query.append("       VENTA.VTTR_CLIENTE_COMPROBANTE T3 ");
            query.append(" WHERE t1.ti_doc_identidad = t2.co_documento_identidad ");
            query.append("   AND T1.co_compania      = t3.co_compania");
            query.append("   and T1.co_local         = t3.co_local");
            query.append("   and T1.co_cliente_local = t3.co_cliente_local");
            if (!"T".equals(Estado)){
                query.append("   AND ES_CLIENTE ='").append(Estado).append("' ");
            }
            query.append(" ORDER BY t1.CO_CLIENTE_LOCAL");
            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next())
            {
                clienteLocal = new Cliente();
                clienteLocal.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_CLIENTE_LOCAL")});
                clienteLocal.setCoCompania(rs.getString("CO_COMPANIA"));
                clienteLocal.setCoLocal(rs.getString("CO_LOCAL"));
                clienteLocal.setCoClienteLocal(rs.getString("CO_CLIENTE_LOCAL"));
                clienteLocal.setTiCliente(rs.getString("TI_CLIENTE"));
                clienteLocal.setCoCategoriaCliente(rs.getString("CO_CATEGORIA_CLIENTE"));
                clienteLocal.setDeRazonSocial(rs.getString("DE_RAZON_SOCIAL"));
                clienteLocal.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                clienteLocal.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                clienteLocal.setApPaternoCliente(rs.getString("AP_PATERNO_CLIENTE"));
                clienteLocal.setApMaternoCliente(rs.getString("AP_MATERNO_CLIENTE"));
                clienteLocal.setNoCliente(rs.getString("NO_CLIENTE"));
                clienteLocal.setFeNacimiento(rs.getDate("FE_NACIMIENTO"));
                clienteLocal.setNuTelReferencia(rs.getString("NU_TEL_REFERENCIA"));
                clienteLocal.setNuTelFax(rs.getString("NU_TEL_FAX"));
                clienteLocal.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                clienteLocal.setDeEmail(rs.getString("DE_EMAIL"));
                clienteLocal.setInClienteEspecial(rs.getString("IN_CLIENTE_ESPECIAL"));
                clienteLocal.setCoClienteCompania(rs.getString("CO_CLIENTE_COMPANIA"));
                clienteLocal.setEsCliente(rs.getString("ES_CLIENTE"));
                clienteLocal.setIdCreaCliente(rs.getString("ID_CREA_CLIENTE"));
                clienteLocal.setFeCreaCliente(rs.getDate("FE_CREA_CLIENTE"));
                clienteLocal.setIdModCliente(rs.getString("ID_MOD_CLIENTE"));
                clienteLocal.setFeModCliente(rs.getDate("FE_MOD_CLIENTE"));
                clienteLocal.setDeTipoDocumento(rs.getString("DE_ABR_DOCUMENTO"));
                clienteLocal.setNuComprobante(rs.getString("NU_COMPROBANTE"));
                rgs.add(clienteLocal);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }

    public ArrayList getBuscarCliente(String Condicion){
        ArrayList<Cliente> rgs = new ArrayList<Cliente>();
        try {
            StringBuffer  query = new StringBuffer();

            query.append("SELECT T1.*,t2.de_abr_documento ");
            query.append("  FROM VTTR_CLIENTE_LOCAL T1, ");
            query.append("       CMTM_DOCUMENTO_IDENTIDAD T2 ");
            query.append(" WHERE t1.ti_doc_identidad = t2.co_documento_identidad ");
            query.append("   AND ").append(Condicion);

            query.append(" ORDER BY CO_CLIENTE_LOCAL");
            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next()){
                clienteLocal = new Cliente();
                clienteLocal.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_CLIENTE_LOCAL")});
                clienteLocal.setCoCompania(rs.getString("CO_COMPANIA"));
                clienteLocal.setCoLocal(rs.getString("CO_LOCAL"));
                clienteLocal.setCoClienteLocal(rs.getString("CO_CLIENTE_LOCAL"));
                clienteLocal.setTiCliente(rs.getString("TI_CLIENTE"));
                clienteLocal.setCoCategoriaCliente(rs.getString("CO_CATEGORIA_CLIENTE"));
                clienteLocal.setDeRazonSocial(rs.getString("DE_RAZON_SOCIAL"));
                clienteLocal.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                clienteLocal.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                clienteLocal.setApPaternoCliente(rs.getString("AP_PATERNO_CLIENTE"));
                clienteLocal.setApMaternoCliente(rs.getString("AP_MATERNO_CLIENTE"));
                clienteLocal.setNoCliente(rs.getString("NO_CLIENTE"));
                clienteLocal.setFeNacimiento(rs.getDate("FE_NACIMIENTO"));
                clienteLocal.setNuTelReferencia(rs.getString("NU_TEL_REFERENCIA"));
                clienteLocal.setNuTelFax(rs.getString("NU_TEL_FAX"));
                clienteLocal.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                clienteLocal.setDeEmail(rs.getString("DE_EMAIL"));
                clienteLocal.setInClienteEspecial(rs.getString("IN_CLIENTE_ESPECIAL"));
                clienteLocal.setCoClienteCompania(rs.getString("CO_CLIENTE_COMPANIA"));
                clienteLocal.setEsCliente(rs.getString("ES_CLIENTE"));
                clienteLocal.setIdCreaCliente(rs.getString("ID_CREA_CLIENTE"));
                clienteLocal.setFeCreaCliente(rs.getDate("FE_CREA_CLIENTE"));
                clienteLocal.setIdModCliente(rs.getString("ID_MOD_CLIENTE"));
                clienteLocal.setFeModCliente(rs.getDate("FE_MOD_CLIENTE"));
                clienteLocal.setDeTipoDocumento(rs.getString("DE_ABR_DOCUMENTO"));
                rgs.add(clienteLocal);
            }
            rs.close();
            BaseConexion.closeConnection();
            BaseConexion.setConnectionNull();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            // Se cierran los recursos de base de datos.
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println("No ha podido cerrar ResultSet.");
            }
        }
        return rgs;
    }

    public ArrayList getBuscarClienteValesArcangel(String Condicion){
        ArrayList<Cliente> rgs = new ArrayList<Cliente>();
        try {
            StringBuffer  query = new StringBuffer();
            query.append("SELECT T1.*,t2.de_abr_documento, t3.nu_comprobante ");
            query.append("  FROM venta.VTTR_CLIENTE_LOCAL T1, ");
            query.append("       COMUN.CMTM_DOCUMENTO_IDENTIDAD T2, ");
            query.append("       VENTA.VTTR_CLIENTE_COMPROBANTE T3 ");
            query.append(" WHERE t1.ti_doc_identidad = t2.co_documento_identidad ");
            query.append("   AND T1.co_compania      = t3.co_compania");
            query.append("   and T1.co_local         = t3.co_local");
            query.append("   and T1.co_cliente_local = t3.co_cliente_local");
            query.append("   AND ").append(Condicion);

            query.append(" ORDER BY CO_CLIENTE_LOCAL");
            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next()){
                clienteLocal = new Cliente();
                clienteLocal.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_CLIENTE_LOCAL")});
                clienteLocal.setCoCompania(rs.getString("CO_COMPANIA"));
                clienteLocal.setCoLocal(rs.getString("CO_LOCAL"));
                clienteLocal.setCoClienteLocal(rs.getString("CO_CLIENTE_LOCAL"));
                clienteLocal.setTiCliente(rs.getString("TI_CLIENTE"));
                clienteLocal.setCoCategoriaCliente(rs.getString("CO_CATEGORIA_CLIENTE"));
                clienteLocal.setDeRazonSocial(rs.getString("DE_RAZON_SOCIAL"));
                clienteLocal.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                clienteLocal.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                clienteLocal.setApPaternoCliente(rs.getString("AP_PATERNO_CLIENTE"));
                clienteLocal.setApMaternoCliente(rs.getString("AP_MATERNO_CLIENTE"));
                clienteLocal.setNoCliente(rs.getString("NO_CLIENTE"));
                clienteLocal.setFeNacimiento(rs.getDate("FE_NACIMIENTO"));
                clienteLocal.setNuTelReferencia(rs.getString("NU_TEL_REFERENCIA"));
                clienteLocal.setNuTelFax(rs.getString("NU_TEL_FAX"));
                clienteLocal.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                clienteLocal.setDeEmail(rs.getString("DE_EMAIL"));
                clienteLocal.setInClienteEspecial(rs.getString("IN_CLIENTE_ESPECIAL"));
                clienteLocal.setCoClienteCompania(rs.getString("CO_CLIENTE_COMPANIA"));
                clienteLocal.setEsCliente(rs.getString("ES_CLIENTE"));
                clienteLocal.setIdCreaCliente(rs.getString("ID_CREA_CLIENTE"));
                clienteLocal.setFeCreaCliente(rs.getDate("FE_CREA_CLIENTE"));
                clienteLocal.setIdModCliente(rs.getString("ID_MOD_CLIENTE"));
                clienteLocal.setFeModCliente(rs.getDate("FE_MOD_CLIENTE"));
                clienteLocal.setDeTipoDocumento(rs.getString("DE_ABR_DOCUMENTO"));
                clienteLocal.setNuComprobante(rs.getString("NU_COMPROBANTE"));
                rgs.add(clienteLocal);
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
        clienteLocal = (Cliente)mdl;
        int gravado = 0;
        String campos = Cliente.FULL_NOM_CAMPOS.toString();

        Object[] valores = {clienteLocal.getCoCompania(),
                clienteLocal.getCoLocal(),
                clienteLocal.getCoClienteLocal(),
                clienteLocal.getTiCliente(),
                clienteLocal.getCoCategoriaCliente(),
                clienteLocal.getDeRazonSocial(),
                clienteLocal.getTiDocIdentidad(),
                clienteLocal.getNuDocIdentidad(),
                clienteLocal.getApPaternoCliente(),
                clienteLocal.getApMaternoCliente(),
                clienteLocal.getNoCliente(),
                clienteLocal.getFeNacimiento(),
                clienteLocal.getNuTelReferencia(),
                clienteLocal.getNuTelFax(),
                clienteLocal.getNuTelMovil(),
                clienteLocal.getDeEmail(),
                clienteLocal.getInClienteEspecial(),
                clienteLocal.getCoClienteCompania(),
                clienteLocal.getEsCliente(),
                clienteLocal.getIdCreaCliente(),
                clienteLocal.getFeCreaCliente(),
                clienteLocal.getIdModCliente(),
                clienteLocal.getFeModCliente()
        };

        gravado = this.agregarRegistroPs(clienteLocal.getNombreTabla(),Cliente.FULL_NOM_CAMPOS, valores);

        if(gravado==1)
            return true;

        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        clienteLocal = (Cliente)mdl;
        int gravado = 0;

        Object[] valores = {clienteLocal.getTiCliente(),
                clienteLocal.getCoCategoriaCliente(),
                clienteLocal.getDeRazonSocial(),
                clienteLocal.getTiDocIdentidad(),
                clienteLocal.getNuDocIdentidad(),
                clienteLocal.getApPaternoCliente(),
                clienteLocal.getApMaternoCliente(),
                clienteLocal.getNoCliente(),
                clienteLocal.getFeNacimiento(),
                clienteLocal.getNuTelReferencia(),
                clienteLocal.getNuTelFax(),
                clienteLocal.getNuTelMovil(),
                clienteLocal.getDeEmail(),
                clienteLocal.getInClienteEspecial(),
                clienteLocal.getCoClienteCompania(),
                clienteLocal.getEsCliente(),
                clienteLocal.getIdCreaCliente(),
                clienteLocal.getFeCreaCliente(),
                clienteLocal.getIdModCliente(),
                clienteLocal.getFeModCliente()
        };

        gravado = this.actualizarRegistroPs(clienteLocal.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(Cliente.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(Cliente.COLUMNA_PK, clienteLocal.getPrimaryKey()) , valores);
        return gravado;
    }

    public Cliente getClienteLocal() {
        if(clienteLocal == null)
        {
            clienteLocal = new Cliente();
        }
        return clienteLocal;
    }

    public void setCliente(JAbstractModelBD prv) {
        this.clienteLocal = (Cliente)prv;
    }

    public String getNuevoCodigo(String coCompania, String coLocal){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(Cliente.nt,"rtrim(ltrim(to_char(max(CO_CLIENTE_LOCAL) + 1,'00000000')))"," CO_COMPANIA = '"+coCompania +"' AND CO_LOCAL = '" + coLocal + "'");
        } catch (SQLException ex) {
            Logger.getLogger(CCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }
}
