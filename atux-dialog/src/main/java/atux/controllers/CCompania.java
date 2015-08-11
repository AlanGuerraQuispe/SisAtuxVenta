package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.Compania;
import atux.util.common.AtuxDBUtility;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CCompania extends JAbstractController{
    private Compania mCompania;
   
    
    @Override
    public ArrayList<Compania> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{Compania.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<Compania> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       

    public ArrayList<Compania> getRegistros(String Filtro) {                 
        ArrayList<Compania> rgs = new ArrayList<Compania>();
        StringBuffer  query;
        try {            
            query = new StringBuffer();
            
            query.append("select * ");
            query.append("  from CMTM_COMPANIA ");
            query.append(" WHERE CO_COMPANIA IS NOT NULL ");

            if ("A".equals(Filtro) || "I".equals(Filtro)){
                query.append("   AND ES_PROVEEDOR = '").append(Filtro).append("' ");
            }else if (!"T".equals(Filtro)){
                query.append("   AND ").append(Filtro);
            }
            
            query.append(" ORDER BY DE_COMPANIA ");
            
            rs =  this.getRegistrosSinFiltro(query);                      
            while(rs.next())
            {
                mCompania = new Compania();
                mCompania.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA")});
                mCompania.setCoCompania(rs.getString("CO_COMPANIA"));
                mCompania.setDeCortaCompania(rs.getString("DE_CORTA_COMPANIA"));
                mCompania.setDeCompania(rs.getString("DE_COMPANIA"));
                mCompania.setNuRucCompania(rs.getString("NU_RUC_COMPANIA"));
                mCompania.setCoPais(rs.getString("CO_PAIS"));
                mCompania.setCoDepartamento(rs.getString("CO_DEPARTAMENTO"));
                mCompania.setCoProvincia(rs.getString("CO_PROVINCIA"));
                mCompania.setCoDistrito(rs.getString("CO_DISTRITO"));
                mCompania.setTiVia(rs.getString("TI_VIA"));
                mCompania.setNoVia(rs.getString("NO_VIA"));
                mCompania.setNuVia(rs.getInt("NU_VIA"));
                mCompania.setNuInteriorVia(rs.getString("NU_INTERIOR_VIA"));
                mCompania.setNuManzanaVia(rs.getString("NU_MANZANA_VIA"));
                mCompania.setNuLoteVia(rs.getString("NU_LOTE_VIA"));
                mCompania.setTiPoblacion(rs.getString("TI_POBLACION"));
                mCompania.setNoPoblacion(rs.getString("NO_POBLACION"));
                mCompania.setCoTelPais(rs.getString("CO_TEL_PAIS"));
                mCompania.setCoTelCiudad(rs.getString("CO_TEL_CIUDAD"));
                mCompania.setNuTelNormal(rs.getString("NU_TEL_NORMAL"));
                mCompania.setNuTelFax(rs.getString("NU_TEL_FAX"));
                mCompania.setApPaternoRespCompania(rs.getString("AP_PATERNO_RESP_COMPANIA"));
                mCompania.setApMaternoRespCompania(rs.getString("AP_MATERNO_RESP_COMPANIA"));
                mCompania.setNoRespCompania(rs.getString("NO_RESP_COMPANIA"));
                mCompania.setEsCompania(rs.getString("ES_COMPANIA"));
                mCompania.setIdCreaCompania(rs.getString("ID_CREA_COMPANIA"));
                mCompania.setFeCreaCompania(rs.getDate("FE_CREA_COMPANIA"));
                mCompania.setIdModCompania(rs.getString("ID_MOD_COMPANIA"));
                mCompania.setFeModCompania(rs.getDate("FE_MOD_COMPANIA"));
                mCompania.setCoSucursal(rs.getString("CO_SUCURSAL"));
                mCompania.setDeDireccion(rs.getString("DE_DIRECCION"));
                mCompania.setDeDireccionWeb(rs.getString("DE_DIRECCION_WEB"));
                mCompania.setDeEmail(rs.getString("DE_EMAIL"));
                rgs.add(mCompania);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }    
    
    @Override
    public ArrayList<Compania> getRegistros() {                 
        ArrayList<Compania> rgs = new ArrayList<Compania>();
        try {                        
            rs = this.getRegistros(Compania.nt, Compania.FULL_NOM_CAMPOS, null, null, Compania.COLUMNA_ORDER);
            while(rs.next())
            {
                mCompania = new Compania();
                mCompania.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA")});
                mCompania.setCoCompania(rs.getString("CO_COMPANIA"));
                mCompania.setDeCortaCompania(rs.getString("DE_CORTA_COMPANIA"));
                mCompania.setDeCompania(rs.getString("DE_COMPANIA"));
                mCompania.setNuRucCompania(rs.getString("NU_RUC_COMPANIA"));
                mCompania.setCoPais(rs.getString("CO_PAIS"));
                mCompania.setCoDepartamento(rs.getString("CO_DEPARTAMENTO"));
                mCompania.setCoProvincia(rs.getString("CO_PROVINCIA"));
                mCompania.setCoDistrito(rs.getString("CO_DISTRITO"));
                mCompania.setTiVia(rs.getString("TI_VIA"));
                mCompania.setNoVia(rs.getString("NO_VIA"));
                mCompania.setNuVia(rs.getInt("NU_VIA"));
                mCompania.setNuInteriorVia(rs.getString("NU_INTERIOR_VIA"));
                mCompania.setNuManzanaVia(rs.getString("NU_MANZANA_VIA"));
                mCompania.setNuLoteVia(rs.getString("NU_LOTE_VIA"));
                mCompania.setTiPoblacion(rs.getString("TI_POBLACION"));
                mCompania.setNoPoblacion(rs.getString("NO_POBLACION"));
                mCompania.setCoTelPais(rs.getString("CO_TEL_PAIS"));
                mCompania.setCoTelCiudad(rs.getString("CO_TEL_CIUDAD"));
                mCompania.setNuTelNormal(rs.getString("NU_TEL_NORMAL"));
                mCompania.setNuTelFax(rs.getString("NU_TEL_FAX"));
                mCompania.setApPaternoRespCompania(rs.getString("AP_PATERNO_RESP_COMPANIA"));
                mCompania.setApMaternoRespCompania(rs.getString("AP_MATERNO_RESP_COMPANIA"));
                mCompania.setNoRespCompania(rs.getString("NO_RESP_COMPANIA"));
                mCompania.setEsCompania(rs.getString("ES_COMPANIA"));
                mCompania.setIdCreaCompania(rs.getString("ID_CREA_COMPANIA"));
                mCompania.setFeCreaCompania(rs.getDate("FE_CREA_COMPANIA"));
                mCompania.setIdModCompania(rs.getString("ID_MOD_COMPANIA"));
                mCompania.setFeModCompania(rs.getDate("FE_MOD_COMPANIA"));
                mCompania.setCoSucursal(rs.getString("CO_SUCURSAL"));
                mCompania.setDeDireccion(rs.getString("DE_DIRECCION"));
                mCompania.setDeDireccionWeb(rs.getString("DE_DIRECCION_WEB"));
                mCompania.setDeEmail(rs.getString("DE_EMAIL"));
                rgs.add(mCompania);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public Compania getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(Compania.nt, Compania.FULL_NOM_CAMPOS, Compania.COLUMNA_PK , id);
            while(rs.next())
            {
                mCompania = new Compania();
                mCompania.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA")});
                mCompania.setCoCompania(rs.getString("CO_COMPANIA"));
                mCompania.setDeCortaCompania(rs.getString("DE_CORTA_COMPANIA"));
                mCompania.setDeCompania(rs.getString("DE_COMPANIA"));
                mCompania.setNuRucCompania(rs.getString("NU_RUC_COMPANIA"));
                mCompania.setCoPais(rs.getString("CO_PAIS"));
                mCompania.setCoDepartamento(rs.getString("CO_DEPARTAMENTO"));
                mCompania.setCoProvincia(rs.getString("CO_PROVINCIA"));
                mCompania.setCoDistrito(rs.getString("CO_DISTRITO"));
                mCompania.setTiVia(rs.getString("TI_VIA"));
                mCompania.setNoVia(rs.getString("NO_VIA"));
                mCompania.setNuVia(rs.getInt("NU_VIA"));
                mCompania.setNuInteriorVia(rs.getString("NU_INTERIOR_VIA"));
                mCompania.setNuManzanaVia(rs.getString("NU_MANZANA_VIA"));
                mCompania.setNuLoteVia(rs.getString("NU_LOTE_VIA"));
                mCompania.setTiPoblacion(rs.getString("TI_POBLACION"));
                mCompania.setNoPoblacion(rs.getString("NO_POBLACION"));
                mCompania.setCoTelPais(rs.getString("CO_TEL_PAIS"));
                mCompania.setCoTelCiudad(rs.getString("CO_TEL_CIUDAD"));
                mCompania.setNuTelNormal(rs.getString("NU_TEL_NORMAL"));
                mCompania.setNuTelFax(rs.getString("NU_TEL_FAX"));
                mCompania.setApPaternoRespCompania(rs.getString("AP_PATERNO_RESP_COMPANIA"));
                mCompania.setApMaternoRespCompania(rs.getString("AP_MATERNO_RESP_COMPANIA"));
                mCompania.setNoRespCompania(rs.getString("NO_RESP_COMPANIA"));
                mCompania.setEsCompania(rs.getString("ES_COMPANIA"));
                mCompania.setIdCreaCompania(rs.getString("ID_CREA_COMPANIA"));
                mCompania.setFeCreaCompania(rs.getDate("FE_CREA_COMPANIA"));
                mCompania.setIdModCompania(rs.getString("ID_MOD_COMPANIA"));
                mCompania.setFeModCompania(rs.getDate("FE_MOD_COMPANIA"));
                mCompania.setCoSucursal(rs.getString("CO_SUCURSAL"));
                mCompania.setDeDireccion(rs.getString("DE_DIRECCION"));
                mCompania.setDeDireccionWeb(rs.getString("DE_DIRECCION_WEB"));
                mCompania.setDeEmail(rs.getString("DE_EMAIL"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return mCompania;
    }
    
    @Override
    public ArrayList<Compania> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<Compania> rgs = new ArrayList<Compania>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(Compania.nt, Compania.COLUMNA_ACTIVO, Compania.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(Compania.nt, Compania.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(Compania.nt, campos, columnaId, id, Compania.COLUMNA_ORDER);
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
                mCompania = new Compania();
                mCompania.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA")});
                mCompania.setCoCompania(rs.getString("CO_COMPANIA"));
                mCompania.setDeCortaCompania(rs.getString("DE_CORTA_COMPANIA"));
                mCompania.setDeCompania(rs.getString("DE_COMPANIA"));
                mCompania.setNuRucCompania(rs.getString("NU_RUC_COMPANIA"));
                mCompania.setCoPais(rs.getString("CO_PAIS"));
                mCompania.setCoDepartamento(rs.getString("CO_DEPARTAMENTO"));
                mCompania.setCoProvincia(rs.getString("CO_PROVINCIA"));
                mCompania.setCoDistrito(rs.getString("CO_DISTRITO"));
                mCompania.setTiVia(rs.getString("TI_VIA"));
                mCompania.setNoVia(rs.getString("NO_VIA"));
                mCompania.setNuVia(rs.getInt("NU_VIA"));
                mCompania.setNuInteriorVia(rs.getString("NU_INTERIOR_VIA"));
                mCompania.setNuManzanaVia(rs.getString("NU_MANZANA_VIA"));
                mCompania.setNuLoteVia(rs.getString("NU_LOTE_VIA"));
                mCompania.setTiPoblacion(rs.getString("TI_POBLACION"));
                mCompania.setNoPoblacion(rs.getString("NO_POBLACION"));
                mCompania.setCoTelPais(rs.getString("CO_TEL_PAIS"));
                mCompania.setCoTelCiudad(rs.getString("CO_TEL_CIUDAD"));
                mCompania.setNuTelNormal(rs.getString("NU_TEL_NORMAL"));
                mCompania.setNuTelFax(rs.getString("NU_TEL_FAX"));
                mCompania.setApPaternoRespCompania(rs.getString("AP_PATERNO_RESP_COMPANIA"));
                mCompania.setApMaternoRespCompania(rs.getString("AP_MATERNO_RESP_COMPANIA"));
                mCompania.setNoRespCompania(rs.getString("NO_RESP_COMPANIA"));
                mCompania.setEsCompania(rs.getString("ES_COMPANIA"));
                mCompania.setIdCreaCompania(rs.getString("ID_CREA_COMPANIA"));
                mCompania.setFeCreaCompania(rs.getDate("FE_CREA_COMPANIA"));
                mCompania.setIdModCompania(rs.getString("ID_MOD_COMPANIA"));
                mCompania.setFeModCompania(rs.getDate("FE_MOD_COMPANIA"));
                mCompania.setCoSucursal(rs.getString("CO_SUCURSAL"));
                mCompania.setDeDireccion(rs.getString("DE_DIRECCION"));
                mCompania.setDeDireccionWeb(rs.getString("DE_DIRECCION_WEB"));
                mCompania.setDeEmail(rs.getString("DE_EMAIL"));
                rgs.add(mCompania);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
        
    }

    public ArrayList getCompania()
     {
        ArrayList<Compania> rgs = new ArrayList<Compania>();
        try {                        
            StringBuffer  query = new StringBuffer();

            query.append("SELECT *  ");            
            query.append("  FROM CMTR_TIPO_VIA ");
            query.append(" WHERE ES_TIPO_VIA ='A' ");
            query.append(" ORDER BY DE_TIPO_VIA");
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                mCompania = new Compania();
                mCompania.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA")});
                mCompania.setCoCompania(rs.getString("CO_COMPANIA"));
                mCompania.setDeCortaCompania(rs.getString("DE_CORTA_COMPANIA"));
                mCompania.setDeCompania(rs.getString("DE_COMPANIA"));
                mCompania.setNuRucCompania(rs.getString("NU_RUC_COMPANIA"));
                mCompania.setCoPais(rs.getString("CO_PAIS"));
                mCompania.setCoDepartamento(rs.getString("CO_DEPARTAMENTO"));
                mCompania.setCoProvincia(rs.getString("CO_PROVINCIA"));
                mCompania.setCoDistrito(rs.getString("CO_DISTRITO"));
                mCompania.setTiVia(rs.getString("TI_VIA"));
                mCompania.setNoVia(rs.getString("NO_VIA"));
                mCompania.setNuVia(rs.getInt("NU_VIA"));
                mCompania.setNuInteriorVia(rs.getString("NU_INTERIOR_VIA"));
                mCompania.setNuManzanaVia(rs.getString("NU_MANZANA_VIA"));
                mCompania.setNuLoteVia(rs.getString("NU_LOTE_VIA"));
                mCompania.setTiPoblacion(rs.getString("TI_POBLACION"));
                mCompania.setNoPoblacion(rs.getString("NO_POBLACION"));
                mCompania.setCoTelPais(rs.getString("CO_TEL_PAIS"));
                mCompania.setCoTelCiudad(rs.getString("CO_TEL_CIUDAD"));
                mCompania.setNuTelNormal(rs.getString("NU_TEL_NORMAL"));
                mCompania.setNuTelFax(rs.getString("NU_TEL_FAX"));
                mCompania.setApPaternoRespCompania(rs.getString("AP_PATERNO_RESP_COMPANIA"));
                mCompania.setApMaternoRespCompania(rs.getString("AP_MATERNO_RESP_COMPANIA"));
                mCompania.setNoRespCompania(rs.getString("NO_RESP_COMPANIA"));
                mCompania.setEsCompania(rs.getString("ES_COMPANIA"));
                mCompania.setIdCreaCompania(rs.getString("ID_CREA_COMPANIA"));
                mCompania.setFeCreaCompania(rs.getDate("FE_CREA_COMPANIA"));
                mCompania.setIdModCompania(rs.getString("ID_MOD_COMPANIA"));
                mCompania.setFeModCompania(rs.getDate("FE_MOD_COMPANIA"));
                mCompania.setCoSucursal(rs.getString("CO_SUCURSAL"));
                mCompania.setDeDireccion(rs.getString("DE_DIRECCION"));
                mCompania.setDeDireccionWeb(rs.getString("DE_DIRECCION_WEB"));
                mCompania.setDeEmail(rs.getString("DE_EMAIL"));
                rgs.add(mCompania);
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
        mCompania = (Compania)mdl;
        int gravado = 0;
        String campos = Compania.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {mCompania.getCoCompania(),
                            mCompania.getDeCortaCompania(),
                            mCompania.getDeCompania(),
                            mCompania.getNuRucCompania(),
                            mCompania.getCoPais(),
                            mCompania.getCoDepartamento(),
                            mCompania.getCoProvincia(),
                            mCompania.getCoDistrito(),
                            mCompania.getTiVia(),
                            mCompania.getNoVia(),
                            mCompania.getNuVia(),
                            mCompania.getNuInteriorVia(),
                            mCompania.getNuManzanaVia(),
                            mCompania.getNuLoteVia(),
                            mCompania.getTiPoblacion(),
                            mCompania.getNoPoblacion(),
                            mCompania.getCoTelPais(),
                            mCompania.getCoTelCiudad(),
                            mCompania.getNuTelNormal(),
                            mCompania.getNuTelFax(),
                            mCompania.getApPaternoRespCompania(),
                            mCompania.getApMaternoRespCompania(),
                            mCompania.getNoRespCompania(),
                            mCompania.getEsCompania(),
                            mCompania.getIdCreaCompania(),
                            mCompania.getFeCreaCompania(),
                            mCompania.getIdModCompania(),
                            mCompania.getFeModCompania(),
                            mCompania.getCoSucursal(),
                            mCompania.getDeDireccion(),
                            mCompania.getDeDireccionWeb(),
                            mCompania.getDeEmail()
                           };
       
           gravado = this.agregarRegistroPs(mCompania.getNombreTabla(),Compania.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        mCompania = (Compania)mdl;
        int gravado = 0;        
        
        Object[] valores = {mCompania.getDeCortaCompania(),
                            mCompania.getDeCompania(),
                            mCompania.getNuRucCompania(),
                            mCompania.getCoPais(),
                            mCompania.getCoDepartamento(),
                            mCompania.getCoProvincia(),
                            mCompania.getCoDistrito(),
                            mCompania.getTiVia(),
                            mCompania.getNoVia(),
                            mCompania.getNuVia(),
                            mCompania.getNuInteriorVia(),
                            mCompania.getNuManzanaVia(),
                            mCompania.getNuLoteVia(),
                            mCompania.getTiPoblacion(),
                            mCompania.getNoPoblacion(),
                            mCompania.getCoTelPais(),
                            mCompania.getCoTelCiudad(),
                            mCompania.getNuTelNormal(),
                            mCompania.getNuTelFax(),
                            mCompania.getApPaternoRespCompania(),
                            mCompania.getApMaternoRespCompania(),
                            mCompania.getNoRespCompania(),
                            mCompania.getEsCompania(),
                            mCompania.getIdCreaCompania(),
                            mCompania.getFeCreaCompania(),
                            mCompania.getIdModCompania(),
                            mCompania.getFeModCompania(),
                            mCompania.getCoSucursal(),
                            mCompania.getDeDireccion(),
                            mCompania.getDeDireccionWeb(),
                            mCompania.getDeEmail()
                           };

        gravado = this.actualizarRegistroPs(mCompania.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(Compania.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(Compania.COLUMNA_PK, mCompania.getPrimaryKey()) , valores);
        return gravado;
    }

    public Compania getMaestroCompania() {
        if(mCompania == null)
        {
            mCompania = new Compania();
        }
        return mCompania;
    }

    public void setMaestroCompania(JAbstractModelBD prv) {
        this.mCompania = (Compania)prv;
    }

    public String getNuevoCodigo(){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(Compania.nt,"rtrim(ltrim(to_char(max(CO_COMPANIA) + 1,'000')))"," CO_COMPANIA is not null");
        } catch (SQLException ex) {
            Logger.getLogger(CCompania.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }
}
