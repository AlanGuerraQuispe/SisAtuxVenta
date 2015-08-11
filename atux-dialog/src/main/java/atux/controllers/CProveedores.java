package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.FormaPago;
import atux.modelbd.Proveedores;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxVariables;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CProveedores extends JAbstractController{
    private Proveedores prv;   
    private FormaPago formaPago; 
    
    @Override
    public ArrayList<Proveedores> getRegistros(Object[] op) {  
        return this.getRegistros(new String[]{}, op!=null?new String[]{Proveedores.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<Proveedores> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<Proveedores> getRegistros() {                 
        ArrayList<Proveedores> rgs = new ArrayList<Proveedores>();
        try {                        
            rs = this.getRegistros(Proveedores.nt,Proveedores.FULL_NOM_CAMPOS);                       
            while(rs.next())
            {
                prv  = new Proveedores();
                prv.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_PROVEEDOR")});
                prv.setCoCompania(rs.getString("CO_COMPANIA"));
                prv.setCoProveedor(rs.getString("CO_PROVEEDOR"));
                prv.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                prv.setDeProveedor(rs.getString("DE_PROVEEDOR"));
                prv.setDeDireccion(rs.getString("DE_DIRECCION"));
                prv.setDeCiudad(rs.getString("DE_CIUDAD"));
                prv.setDeEmailProveedor(rs.getString("DE_EMAIL_PROVEEDOR"));
                prv.setDeTelefonoProveedor(rs.getString("DE_TELEFONO_PROVEEDOR"));
                prv.setDeFaxProveedor(rs.getString("DE_FAX_PROVEEDOR"));
                prv.setDeContacto(rs.getString("DE_CONTACTO"));
                prv.setDeTelefono01Contacto(rs.getString("DE_TELEFONO01_CONTACTO"));
                prv.setDeTelefono02Contacto(rs.getString("DE_TELEFONO02_CONTACTO"));
                prv.setDeEmailContacto(rs.getString("DE_EMAIL_CONTACTO"));
                prv.setTiProveedor(rs.getString("TI_PROVEEDOR"));
                prv.setEsProveedor(rs.getString("ES_PROVEEDOR"));
                prv.setDeBancoProveedor(rs.getString("DE_BANCO_PROVEEDOR"));
                prv.setNuCuentaBanco(rs.getString("NU_CUENTA_BANCO"));
                prv.setIdCreaProveedor(rs.getString("ID_CREA_PROVEEDOR"));
                prv.setFeCreaProveedor(rs.getDate("FE_CREA_PROVEEDOR"));
                prv.setIdModProveedor(rs.getString("ID_MOD_PROVEEDOR"));
                prv.setFeModProveedor(rs.getDate("FE_MOD_PROVEEDOR"));
                rgs.add(prv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public ArrayList<Proveedores> getRegistros(String Filtro) {                 
        ArrayList<Proveedores> rgs = new ArrayList<Proveedores>();
        StringBuffer  query;
        try {            
            query = new StringBuffer();
            
            query.append("select * ");
            query.append("  from VTTM_PROVEEDOR ");
            query.append(" WHERE CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("' ");

            if ("A".equals(Filtro) || "I".equals(Filtro)){
                query.append("   AND ES_PROVEEDOR = '").append(Filtro).append("' ");
            }else if (!"T".equals(Filtro)){
                query.append(Filtro);
            }
            
            query.append(" ORDER BY DE_PROVEEDOR ");
            
            rs =  this.getRegistrosSinFiltro(query);                      
            while(rs.next())
            {
                prv  = new Proveedores();
                prv.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_PROVEEDOR")});
                prv.setCoCompania(rs.getString("CO_COMPANIA"));
                prv.setCoProveedor(rs.getString("CO_PROVEEDOR"));
                prv.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                prv.setDeProveedor(rs.getString("DE_PROVEEDOR"));
                prv.setDeDireccion(rs.getString("DE_DIRECCION"));
                prv.setDeCiudad(rs.getString("DE_CIUDAD"));
                prv.setDeEmailProveedor(rs.getString("DE_EMAIL_PROVEEDOR"));
                prv.setDeTelefonoProveedor(rs.getString("DE_TELEFONO_PROVEEDOR"));
                prv.setDeFaxProveedor(rs.getString("DE_FAX_PROVEEDOR"));
                prv.setDeContacto(rs.getString("DE_CONTACTO"));
                prv.setDeTelefono01Contacto(rs.getString("DE_TELEFONO01_CONTACTO"));
                prv.setDeTelefono02Contacto(rs.getString("DE_TELEFONO02_CONTACTO"));
                prv.setDeEmailContacto(rs.getString("DE_EMAIL_CONTACTO"));
                prv.setTiProveedor(rs.getString("TI_PROVEEDOR"));
                prv.setEsProveedor(rs.getString("ES_PROVEEDOR"));
                prv.setDeBancoProveedor(rs.getString("DE_BANCO_PROVEEDOR"));
                prv.setNuCuentaBanco(rs.getString("NU_CUENTA_BANCO"));
                prv.setIdCreaProveedor(rs.getString("ID_CREA_PROVEEDOR"));
                prv.setFeCreaProveedor(rs.getDate("FE_CREA_PROVEEDOR"));
                prv.setIdModProveedor(rs.getString("ID_MOD_PROVEEDOR"));
                prv.setFeModProveedor(rs.getDate("FE_MOD_PROVEEDOR"));
                rgs.add(prv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public Proveedores getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(Proveedores.nt, Proveedores.FULL_NOM_CAMPOS, Proveedores.COLUMNA_PK , id);
            while(rs.next())
            {
                prv = new Proveedores();
                prv.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_PROVEEDOR")});
                prv.setCoCompania(rs.getString("CO_COMPANIA"));
                prv.setCoProveedor(rs.getString("CO_PROVEEDOR"));
                prv.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                prv.setDeProveedor(rs.getString("DE_PROVEEDOR"));
                prv.setDeDireccion(rs.getString("DE_DIRECCION"));
                prv.setDeCiudad(rs.getString("DE_CIUDAD"));
                prv.setDeEmailProveedor(rs.getString("DE_EMAIL_PROVEEDOR"));
                prv.setDeTelefonoProveedor(rs.getString("DE_TELEFONO_PROVEEDOR"));
                prv.setDeFaxProveedor(rs.getString("DE_FAX_PROVEEDOR"));
                prv.setDeContacto(rs.getString("DE_CONTACTO"));
                prv.setDeTelefono01Contacto(rs.getString("DE_TELEFONO01_CONTACTO"));
                prv.setDeTelefono02Contacto(rs.getString("DE_TELEFONO02_CONTACTO"));
                prv.setDeEmailContacto(rs.getString("DE_EMAIL_CONTACTO"));
                prv.setTiProveedor(rs.getString("TI_PROVEEDOR"));
                prv.setEsProveedor(rs.getString("ES_PROVEEDOR"));
                prv.setDeBancoProveedor(rs.getString("DE_BANCO_PROVEEDOR"));
                prv.setNuCuentaBanco(rs.getString("NU_CUENTA_BANCO"));
                prv.setIdCreaProveedor(rs.getString("ID_CREA_PROVEEDOR"));
                prv.setFeCreaProveedor(rs.getDate("FE_CREA_PROVEEDOR"));
                prv.setIdModProveedor(rs.getString("ID_MOD_PROVEEDOR"));
                prv.setFeModProveedor(rs.getDate("FE_MOD_PROVEEDOR"));                 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return prv;
    }
    
    @Override
    public ArrayList<Proveedores> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<Proveedores> rgs = new ArrayList<Proveedores>();        
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(Proveedores.nt, Proveedores.COLUMNA_ACTIVO, Proveedores.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(Proveedores.nt, Proveedores.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
//            rs = this.getRegistros(Proveedores.nt, campos, columnaId, id,null);
            rs = this.getRegistros(Proveedores.nt, campos, columnaId, id, Proveedores.COLUMNA_ORDER);
            if(this.numRegistros<finalPag){
              finalPag =  this.numRegistros;
            }
            if(finalPag>inicioPag){
                inicioPag -= (finalPag-inicioPag)-1;
            }
            while(rs.next()){
                prv = new Proveedores();
                prv.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_PROVEEDOR")});
                prv.setCoCompania(rs.getString("CO_COMPANIA"));
                prv.setCoProveedor(rs.getString("CO_PROVEEDOR"));
                prv.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                prv.setDeProveedor(rs.getString("DE_PROVEEDOR"));
                prv.setDeDireccion(rs.getString("DE_DIRECCION"));
                prv.setDeCiudad(rs.getString("DE_CIUDAD"));
                prv.setDeEmailProveedor(rs.getString("DE_EMAIL_PROVEEDOR"));
                prv.setDeTelefonoProveedor(rs.getString("DE_TELEFONO_PROVEEDOR"));
                prv.setDeFaxProveedor(rs.getString("DE_FAX_PROVEEDOR"));
                prv.setDeContacto(rs.getString("DE_CONTACTO"));
                prv.setDeTelefono01Contacto(rs.getString("DE_TELEFONO01_CONTACTO"));
                prv.setDeTelefono02Contacto(rs.getString("DE_TELEFONO02_CONTACTO"));
                prv.setDeEmailContacto(rs.getString("DE_EMAIL_CONTACTO"));
                prv.setTiProveedor(rs.getString("TI_PROVEEDOR"));
                prv.setEsProveedor(rs.getString("ES_PROVEEDOR"));
                prv.setDeBancoProveedor(rs.getString("DE_BANCO_PROVEEDOR"));
                prv.setNuCuentaBanco(rs.getString("NU_CUENTA_BANCO"));
                prv.setIdCreaProveedor(rs.getString("ID_CREA_PROVEEDOR"));
                prv.setFeCreaProveedor(rs.getDate("FE_CREA_PROVEEDOR"));
                prv.setIdModProveedor(rs.getString("ID_MOD_PROVEEDOR"));
                prv.setFeModProveedor(rs.getDate("FE_MOD_PROVEEDOR"));
                 rgs.add(prv);
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
        prv = (Proveedores)mdl;
        int gravado = 0;
        String campos = Proveedores.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {prv.getCoCompania(),
                            prv.getCoProveedor(),
                            prv.getNuDocIdentidad(),
                            prv.getDeProveedor(),
                            prv.getDeDireccion(),
                            prv.getDeCiudad(),
                            prv.getDeEmailProveedor(),
                            prv.getDeTelefonoProveedor(),
                            prv.getDeFaxProveedor(),
                            prv.getDeContacto(),
                            prv.getDeTelefono01Contacto(),
                            prv.getDeTelefono02Contacto(),
                            prv.getDeEmailContacto(),
                            prv.getTiProveedor(),
                            prv.getEsProveedor(),
                            prv.getDeBancoProveedor(),
                            prv.getNuCuentaBanco(),
                            prv.getIdCreaProveedor(),
                            prv.getFeCreaProveedor(),
                            prv.getIdModProveedor(),
                            prv.getFeModProveedor()
                           };
       
           gravado = this.agregarRegistroPs(prv.getNombreTabla(),Proveedores.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        prv = (Proveedores)mdl;
        int gravado = 0;        
        
        Object[] valores = {prv.getNuDocIdentidad(),
                            prv.getDeProveedor(),
                            prv.getDeDireccion(),
                            prv.getDeCiudad(),
                            prv.getDeEmailProveedor(),
                            prv.getDeTelefonoProveedor(),
                            prv.getDeFaxProveedor(),
                            prv.getDeContacto(),
                            prv.getDeTelefono01Contacto(),
                            prv.getDeTelefono02Contacto(),
                            prv.getDeEmailContacto(),
                            prv.getTiProveedor(),
                            prv.getEsProveedor(),
                            prv.getDeBancoProveedor(),
                            prv.getNuCuentaBanco(),
                            prv.getIdCreaProveedor(),
                            prv.getFeCreaProveedor(),
                            prv.getIdModProveedor(),
                            prv.getFeModProveedor()};
       
        
        gravado = this.actualizarRegistroPs(prv.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(Proveedores.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(Proveedores.COLUMNA_PK,prv.getPrimaryKey()) , valores);

   
        return gravado;
    }

    public Proveedores getProveedor() {
        if(prv == null)
        {
            prv = new Proveedores();
        }
        return prv;
    }

    public void setProveedor(JAbstractModelBD prv) {
        this.prv = (Proveedores)prv;
    }
    public String getNuevoCodigoProveedor(){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(Proveedores.nt,"rtrim(ltrim(to_char(max(co_proveedor) + 1,'000000')))"," co_compania = '" + AtuxVariables.vCodigoCompania + "'");
        } catch (SQLException ex) {
            Logger.getLogger(CProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }
}
