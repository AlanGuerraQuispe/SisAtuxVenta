package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.ClienteDireccion;
import java.sql.SQLException;
import java.util.ArrayList;


public class CClienteDireccion extends JAbstractController{
    private ClienteDireccion clienteDireccion;
   
    
    @Override
    public ArrayList<ClienteDireccion> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{ClienteDireccion.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<ClienteDireccion> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<ClienteDireccion> getRegistros() {                 
        ArrayList<ClienteDireccion> rgs = new ArrayList<ClienteDireccion>();
        try {                        
//            rs = this.getRegistros(TipoDeCambio.nt,TipoDeCambio.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(ClienteDireccion.nt, ClienteDireccion.FULL_NOM_CAMPOS, null, null, ClienteDireccion.COLUMNA_ORDER);
            while(rs.next())
            {
                clienteDireccion = new ClienteDireccion();
                clienteDireccion.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_Cliente_LOCAL")});
                clienteDireccion.setCoCompania(rs.getString("CO_COMPANIA"));
                clienteDireccion.setCoLocal(rs.getString("CO_LOCAL"));
                clienteDireccion.setCoClienteLocal(rs.getString("CO_CLIENTE_LOCAL"));
                clienteDireccion.setNuSecDireccion(rs.getInt("NU_SEC_DIRECCION"));
                clienteDireccion.setCoPais(rs.getString("CO_PAIS"));
                clienteDireccion.setCoDepartamento(rs.getString("CO_DEPARTAMENTO"));
                clienteDireccion.setCoProvincia(rs.getString("CO_PROVINCIA"));
                clienteDireccion.setCoDistrito(rs.getString("CO_DISTRITO"));
                clienteDireccion.setCoLocalRef1(rs.getString("CO_LOCAL_REF_1"));
                clienteDireccion.setCoLocalRef2(rs.getString("CO_LOCAL_REF_2"));
                clienteDireccion.setTiVia(rs.getString("TI_VIA"));
                clienteDireccion.setNoVia(rs.getString("NO_VIA"));
                clienteDireccion.setNuVia(rs.getString("NU_VIA"));
                clienteDireccion.setNuInteriorVia(rs.getString("NU_INTERIOR_VIA"));
                clienteDireccion.setNuDptoVia(rs.getString("NU_DPTO_VIA"));
                clienteDireccion.setNuManzanaVia(rs.getString("NU_MANZANA_VIA"));
                clienteDireccion.setNuLoteVia(rs.getString("NU_LOTE_VIA"));
                clienteDireccion.setTiPoblacion(rs.getString("TI_POBLACION"));
                clienteDireccion.setNoPoblacion(rs.getString("NO_POBLACION"));
                clienteDireccion.setDeDireccion(rs.getString("DE_DIRECCION"));
                clienteDireccion.setDeReferencia(rs.getString("DE_REFERENCIA"));
                clienteDireccion.setCoTelPais(rs.getString("CO_TEL_PAIS"));
                clienteDireccion.setCoTelCiudad(rs.getString("CO_TEL_CIUDAD"));
                clienteDireccion.setNuTelNormal(rs.getString("NU_TEL_NORMAL"));
                clienteDireccion.setNuTelExtension(rs.getString("NU_TEL_EXTENSION"));
                clienteDireccion.setNuTelReferencia(rs.getString("NU_TEL_REFERENCIA"));
                clienteDireccion.setCoPlano(rs.getString("CO_PLANO"));
                clienteDireccion.setCoUbicacionPlano(rs.getString("CO_UBICACION_PLANO"));
                clienteDireccion.setInDireccionNueva(rs.getString("IN_DIRECCION_NUEVA"));
                clienteDireccion.setEsDireccionCliente(rs.getString("ES_DIRECCION_CLIENTE"));
                clienteDireccion.setIdCreaDireccionCliente(rs.getString("ID_CREA_DIRECCION_CLIENTE"));
                clienteDireccion.setFeCreaDireccionCliente(rs.getDate("FE_CREA_DIRECCION_CLIENTE"));
                clienteDireccion.setIdModDireccionCliente(rs.getString("ID_MOD_DIRECCION_CLIENTE"));
                clienteDireccion.setFeModDireccionCliente(rs.getDate("FE_MOD_DIRECCION_CLIENTE"));
                clienteDireccion.setInReplica(rs.getString("IN_REPLICA"));
                clienteDireccion.setFeReplica(rs.getDate("FE_REPLICA"));
                clienteDireccion.setNuSecReplica(rs.getInt("NU_SEC_REPLICA"));
                clienteDireccion.setTiDireccion(rs.getString("TI_DIRECCION"));
                clienteDireccion.setNuSector(rs.getInt("NU_SECTOR"));
                clienteDireccion.setNuBlock(rs.getString("NU_BLOCK"));                
                rgs.add(clienteDireccion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public ClienteDireccion getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(ClienteDireccion.nt, ClienteDireccion.FULL_NOM_CAMPOS, ClienteDireccion.COLUMNA_PK , id);
            while(rs.next())
            {
                clienteDireccion = new ClienteDireccion();
                clienteDireccion.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_Cliente_LOCAL")});
                clienteDireccion.setCoCompania(rs.getString("CO_COMPANIA"));
                clienteDireccion.setCoLocal(rs.getString("CO_LOCAL"));
                clienteDireccion.setCoClienteLocal(rs.getString("CO_CLIENTE_LOCAL"));
                clienteDireccion.setNuSecDireccion(rs.getInt("NU_SEC_DIRECCION"));
                clienteDireccion.setCoPais(rs.getString("CO_PAIS"));
                clienteDireccion.setCoDepartamento(rs.getString("CO_DEPARTAMENTO"));
                clienteDireccion.setCoProvincia(rs.getString("CO_PROVINCIA"));
                clienteDireccion.setCoDistrito(rs.getString("CO_DISTRITO"));
                clienteDireccion.setCoLocalRef1(rs.getString("CO_LOCAL_REF_1"));
                clienteDireccion.setCoLocalRef2(rs.getString("CO_LOCAL_REF_2"));
                clienteDireccion.setTiVia(rs.getString("TI_VIA"));
                clienteDireccion.setNoVia(rs.getString("NO_VIA"));
                clienteDireccion.setNuVia(rs.getString("NU_VIA"));
                clienteDireccion.setNuInteriorVia(rs.getString("NU_INTERIOR_VIA"));
                clienteDireccion.setNuDptoVia(rs.getString("NU_DPTO_VIA"));
                clienteDireccion.setNuManzanaVia(rs.getString("NU_MANZANA_VIA"));
                clienteDireccion.setNuLoteVia(rs.getString("NU_LOTE_VIA"));
                clienteDireccion.setTiPoblacion(rs.getString("TI_POBLACION"));
                clienteDireccion.setNoPoblacion(rs.getString("NO_POBLACION"));
                clienteDireccion.setDeDireccion(rs.getString("DE_DIRECCION"));
                clienteDireccion.setDeReferencia(rs.getString("DE_REFERENCIA"));
                clienteDireccion.setCoTelPais(rs.getString("CO_TEL_PAIS"));
                clienteDireccion.setCoTelCiudad(rs.getString("CO_TEL_CIUDAD"));
                clienteDireccion.setNuTelNormal(rs.getString("NU_TEL_NORMAL"));
                clienteDireccion.setNuTelExtension(rs.getString("NU_TEL_EXTENSION"));
                clienteDireccion.setNuTelReferencia(rs.getString("NU_TEL_REFERENCIA"));
                clienteDireccion.setCoPlano(rs.getString("CO_PLANO"));
                clienteDireccion.setCoUbicacionPlano(rs.getString("CO_UBICACION_PLANO"));
                clienteDireccion.setInDireccionNueva(rs.getString("IN_DIRECCION_NUEVA"));
                clienteDireccion.setEsDireccionCliente(rs.getString("ES_DIRECCION_CLIENTE"));
                clienteDireccion.setIdCreaDireccionCliente(rs.getString("ID_CREA_DIRECCION_CLIENTE"));
                clienteDireccion.setFeCreaDireccionCliente(rs.getDate("FE_CREA_DIRECCION_CLIENTE"));
                clienteDireccion.setIdModDireccionCliente(rs.getString("ID_MOD_DIRECCION_CLIENTE"));
                clienteDireccion.setFeModDireccionCliente(rs.getDate("FE_MOD_DIRECCION_CLIENTE"));
                clienteDireccion.setInReplica(rs.getString("IN_REPLICA"));
                clienteDireccion.setFeReplica(rs.getDate("FE_REPLICA"));
                clienteDireccion.setNuSecReplica(rs.getInt("NU_SEC_REPLICA"));
                clienteDireccion.setTiDireccion(rs.getString("TI_DIRECCION"));
                clienteDireccion.setNuSector(rs.getInt("NU_SECTOR"));
                clienteDireccion.setNuBlock(rs.getString("NU_BLOCK"));                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clienteDireccion;
    }
    
    @Override
    public ArrayList<ClienteDireccion> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<ClienteDireccion> rgs = new ArrayList<ClienteDireccion>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(ClienteDireccion.nt, ClienteDireccion.COLUMNA_ACTIVO, ClienteDireccion.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(ClienteDireccion.nt, ClienteDireccion.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(ClienteDireccion.nt, campos, columnaId, id, ClienteDireccion.COLUMNA_ORDER);
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
                clienteDireccion = new ClienteDireccion();
                clienteDireccion.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_Cliente_LOCAL")});
                clienteDireccion.setCoCompania(rs.getString("CO_COMPANIA"));
                clienteDireccion.setCoLocal(rs.getString("CO_LOCAL"));
                clienteDireccion.setCoClienteLocal(rs.getString("CO_CLIENTE_LOCAL"));
                clienteDireccion.setNuSecDireccion(rs.getInt("NU_SEC_DIRECCION"));
                clienteDireccion.setCoPais(rs.getString("CO_PAIS"));
                clienteDireccion.setCoDepartamento(rs.getString("CO_DEPARTAMENTO"));
                clienteDireccion.setCoProvincia(rs.getString("CO_PROVINCIA"));
                clienteDireccion.setCoDistrito(rs.getString("CO_DISTRITO"));
                clienteDireccion.setCoLocalRef1(rs.getString("CO_LOCAL_REF_1"));
                clienteDireccion.setCoLocalRef2(rs.getString("CO_LOCAL_REF_2"));
                clienteDireccion.setTiVia(rs.getString("TI_VIA"));
                clienteDireccion.setNoVia(rs.getString("NO_VIA"));
                clienteDireccion.setNuVia(rs.getString("NU_VIA"));
                clienteDireccion.setNuInteriorVia(rs.getString("NU_INTERIOR_VIA"));
                clienteDireccion.setNuDptoVia(rs.getString("NU_DPTO_VIA"));
                clienteDireccion.setNuManzanaVia(rs.getString("NU_MANZANA_VIA"));
                clienteDireccion.setNuLoteVia(rs.getString("NU_LOTE_VIA"));
                clienteDireccion.setTiPoblacion(rs.getString("TI_POBLACION"));
                clienteDireccion.setNoPoblacion(rs.getString("NO_POBLACION"));
                clienteDireccion.setDeDireccion(rs.getString("DE_DIRECCION"));
                clienteDireccion.setDeReferencia(rs.getString("DE_REFERENCIA"));
                clienteDireccion.setCoTelPais(rs.getString("CO_TEL_PAIS"));
                clienteDireccion.setCoTelCiudad(rs.getString("CO_TEL_CIUDAD"));
                clienteDireccion.setNuTelNormal(rs.getString("NU_TEL_NORMAL"));
                clienteDireccion.setNuTelExtension(rs.getString("NU_TEL_EXTENSION"));
                clienteDireccion.setNuTelReferencia(rs.getString("NU_TEL_REFERENCIA"));
                clienteDireccion.setCoPlano(rs.getString("CO_PLANO"));
                clienteDireccion.setCoUbicacionPlano(rs.getString("CO_UBICACION_PLANO"));
                clienteDireccion.setInDireccionNueva(rs.getString("IN_DIRECCION_NUEVA"));
                clienteDireccion.setEsDireccionCliente(rs.getString("ES_DIRECCION_CLIENTE"));
                clienteDireccion.setIdCreaDireccionCliente(rs.getString("ID_CREA_DIRECCION_CLIENTE"));
                clienteDireccion.setFeCreaDireccionCliente(rs.getDate("FE_CREA_DIRECCION_CLIENTE"));
                clienteDireccion.setIdModDireccionCliente(rs.getString("ID_MOD_DIRECCION_CLIENTE"));
                clienteDireccion.setFeModDireccionCliente(rs.getDate("FE_MOD_DIRECCION_CLIENTE"));
                clienteDireccion.setInReplica(rs.getString("IN_REPLICA"));
                clienteDireccion.setFeReplica(rs.getDate("FE_REPLICA"));
                clienteDireccion.setNuSecReplica(rs.getInt("NU_SEC_REPLICA"));
                clienteDireccion.setTiDireccion(rs.getString("TI_DIRECCION"));
                clienteDireccion.setNuSector(rs.getInt("NU_SECTOR"));
                clienteDireccion.setNuBlock(rs.getString("NU_BLOCK"));                
                rgs.add(clienteDireccion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
        
    }

    public ArrayList getClienteDireccion(String Estado)
     {
        ArrayList<ClienteDireccion> rgs = new ArrayList<ClienteDireccion>();
        try {                        
            StringBuffer  query = new StringBuffer();

            query.append("SELECT T1.*,t2.de_abr_documento ");
            query.append("  FROM VTTR_ClienteDireccion_LOCAL T1, ");
            query.append("       COMUN.CMTM_DOCUMENTO_IDENTIDAD T2 ");
            query.append(" WHERE t1.ti_doc_identidad = t2.co_documento_identidad ");
            if (!"T".equals(Estado)){
                query.append("   AND ES_ClienteDireccion_LOCAL ='").append(Estado).append("' ");
            }            
            query.append(" ORDER BY CO_ClienteDireccion_LOCAL");
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                clienteDireccion = new ClienteDireccion();
                clienteDireccion.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_Cliente_LOCAL")});
                clienteDireccion.setCoCompania(rs.getString("CO_COMPANIA"));
                clienteDireccion.setCoLocal(rs.getString("CO_LOCAL"));
                clienteDireccion.setCoClienteLocal(rs.getString("CO_CLIENTE_LOCAL"));
                clienteDireccion.setNuSecDireccion(rs.getInt("NU_SEC_DIRECCION"));
                clienteDireccion.setCoPais(rs.getString("CO_PAIS"));
                clienteDireccion.setCoDepartamento(rs.getString("CO_DEPARTAMENTO"));
                clienteDireccion.setCoProvincia(rs.getString("CO_PROVINCIA"));
                clienteDireccion.setCoDistrito(rs.getString("CO_DISTRITO"));
                clienteDireccion.setCoLocalRef1(rs.getString("CO_LOCAL_REF_1"));
                clienteDireccion.setCoLocalRef2(rs.getString("CO_LOCAL_REF_2"));
                clienteDireccion.setTiVia(rs.getString("TI_VIA"));
                clienteDireccion.setNoVia(rs.getString("NO_VIA"));
                clienteDireccion.setNuVia(rs.getString("NU_VIA"));
                clienteDireccion.setNuInteriorVia(rs.getString("NU_INTERIOR_VIA"));
                clienteDireccion.setNuDptoVia(rs.getString("NU_DPTO_VIA"));
                clienteDireccion.setNuManzanaVia(rs.getString("NU_MANZANA_VIA"));
                clienteDireccion.setNuLoteVia(rs.getString("NU_LOTE_VIA"));
                clienteDireccion.setTiPoblacion(rs.getString("TI_POBLACION"));
                clienteDireccion.setNoPoblacion(rs.getString("NO_POBLACION"));
                clienteDireccion.setDeDireccion(rs.getString("DE_DIRECCION"));
                clienteDireccion.setDeReferencia(rs.getString("DE_REFERENCIA"));
                clienteDireccion.setCoTelPais(rs.getString("CO_TEL_PAIS"));
                clienteDireccion.setCoTelCiudad(rs.getString("CO_TEL_CIUDAD"));
                clienteDireccion.setNuTelNormal(rs.getString("NU_TEL_NORMAL"));
                clienteDireccion.setNuTelExtension(rs.getString("NU_TEL_EXTENSION"));
                clienteDireccion.setNuTelReferencia(rs.getString("NU_TEL_REFERENCIA"));
                clienteDireccion.setCoPlano(rs.getString("CO_PLANO"));
                clienteDireccion.setCoUbicacionPlano(rs.getString("CO_UBICACION_PLANO"));
                clienteDireccion.setInDireccionNueva(rs.getString("IN_DIRECCION_NUEVA"));
                clienteDireccion.setEsDireccionCliente(rs.getString("ES_DIRECCION_CLIENTE"));
                clienteDireccion.setIdCreaDireccionCliente(rs.getString("ID_CREA_DIRECCION_CLIENTE"));
                clienteDireccion.setFeCreaDireccionCliente(rs.getDate("FE_CREA_DIRECCION_CLIENTE"));
                clienteDireccion.setIdModDireccionCliente(rs.getString("ID_MOD_DIRECCION_CLIENTE"));
                clienteDireccion.setFeModDireccionCliente(rs.getDate("FE_MOD_DIRECCION_CLIENTE"));
                clienteDireccion.setInReplica(rs.getString("IN_REPLICA"));
                clienteDireccion.setFeReplica(rs.getDate("FE_REPLICA"));
                clienteDireccion.setNuSecReplica(rs.getInt("NU_SEC_REPLICA"));
                clienteDireccion.setTiDireccion(rs.getString("TI_DIRECCION"));
                clienteDireccion.setNuSector(rs.getInt("NU_SECTOR"));
                clienteDireccion.setNuBlock(rs.getString("NU_BLOCK"));                
                rgs.add(clienteDireccion);
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
        clienteDireccion = (ClienteDireccion)mdl;
        int gravado = 0;
        String campos = ClienteDireccion.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {clienteDireccion.getCoCompania(),
                            clienteDireccion.getCoLocal(),
                            clienteDireccion.getCoClienteLocal(),
                            clienteDireccion.getNuSecDireccion(),
                            clienteDireccion.getCoPais(),
                            clienteDireccion.getCoDepartamento(),
                            clienteDireccion.getCoProvincia(),
                            clienteDireccion.getCoDistrito(),
                            clienteDireccion.getCoLocalRef1(),
                            clienteDireccion.getCoLocalRef2(),
                            clienteDireccion.getTiVia(),
                            clienteDireccion.getNoVia(),
                            clienteDireccion.getNuVia(),
                            clienteDireccion.getNuInteriorVia(),
                            clienteDireccion.getNuDptoVia(),
                            clienteDireccion.getNuManzanaVia(),
                            clienteDireccion.getNuLoteVia(),
                            clienteDireccion.getTiPoblacion(),
                            clienteDireccion.getNoPoblacion(),
                            clienteDireccion.getDeDireccion(),
                            clienteDireccion.getDeReferencia(),
                            clienteDireccion.getCoTelPais(),
                            clienteDireccion.getCoTelCiudad(),
                            clienteDireccion.getNuTelNormal(),
                            clienteDireccion.getNuTelExtension(),
                            clienteDireccion.getNuTelReferencia(),
                            clienteDireccion.getCoPlano(),
                            clienteDireccion.getCoUbicacionPlano(),
                            clienteDireccion.getInDireccionNueva(),
                            clienteDireccion.getEsDireccionCliente(),
                            clienteDireccion.getIdCreaDireccionCliente(),
                            clienteDireccion.getFeCreaDireccionCliente(),
                            clienteDireccion.getIdModDireccionCliente(),
                            clienteDireccion.getFeModDireccionCliente(),
                            clienteDireccion.getInReplica(),
                            clienteDireccion.getFeReplica(),
                            clienteDireccion.getNuSecReplica(),
                            clienteDireccion.getTiDireccion(),
                            clienteDireccion.getNuSector(),
                            clienteDireccion.getNuBlock()
                           };
       
           gravado = this.agregarRegistroPs(clienteDireccion.getNombreTabla(),ClienteDireccion.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        clienteDireccion = (ClienteDireccion)mdl;
        int gravado = 0;        
        
        Object[] valores = {clienteDireccion.getNuSecDireccion(),
                            clienteDireccion.getCoPais(),
                            clienteDireccion.getCoDepartamento(),
                            clienteDireccion.getCoProvincia(),
                            clienteDireccion.getCoDistrito(),
                            clienteDireccion.getCoLocalRef1(),
                            clienteDireccion.getCoLocalRef2(),
                            clienteDireccion.getTiVia(),
                            clienteDireccion.getNoVia(),
                            clienteDireccion.getNuVia(),
                            clienteDireccion.getNuInteriorVia(),
                            clienteDireccion.getNuDptoVia(),
                            clienteDireccion.getNuManzanaVia(),
                            clienteDireccion.getNuLoteVia(),
                            clienteDireccion.getTiPoblacion(),
                            clienteDireccion.getNoPoblacion(),
                            clienteDireccion.getDeDireccion(),
                            clienteDireccion.getDeReferencia(),
                            clienteDireccion.getCoTelPais(),
                            clienteDireccion.getCoTelCiudad(),
                            clienteDireccion.getNuTelNormal(),
                            clienteDireccion.getNuTelExtension(),
                            clienteDireccion.getNuTelReferencia(),
                            clienteDireccion.getCoPlano(),
                            clienteDireccion.getCoUbicacionPlano(),
                            clienteDireccion.getInDireccionNueva(),
                            clienteDireccion.getEsDireccionCliente(),
                            clienteDireccion.getIdCreaDireccionCliente(),
                            clienteDireccion.getFeCreaDireccionCliente(),
                            clienteDireccion.getIdModDireccionCliente(),
                            clienteDireccion.getFeModDireccionCliente(),
                            clienteDireccion.getInReplica(),
                            clienteDireccion.getFeReplica(),
                            clienteDireccion.getNuSecReplica(),
                            clienteDireccion.getTiDireccion(),
                            clienteDireccion.getNuSector(),
                            clienteDireccion.getNuBlock()
                           };

        gravado = this.actualizarRegistroPs(clienteDireccion.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(ClienteDireccion.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(ClienteDireccion.COLUMNA_PK, clienteDireccion.getPrimaryKey()) , valores);
        return gravado;
    }

    public ClienteDireccion getClienteDireccionLocal() {
        if(clienteDireccion == null)
        {
            clienteDireccion = new ClienteDireccion();
        }
        return clienteDireccion;
    }

    public void setClienteDireccion(JAbstractModelBD prv) {
        this.clienteDireccion = (ClienteDireccion)prv;
    }
}
