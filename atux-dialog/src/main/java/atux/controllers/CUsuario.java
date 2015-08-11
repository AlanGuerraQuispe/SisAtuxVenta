package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.managerbd.BaseConexion;
import atux.modelbd.Bitacora;
import atux.modelbd.Usuario;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxVariables;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class CUsuario extends JAbstractController{
    private Usuario us;
    private InputStream itmp;

    public CUsuario(){        
        
    }
    
    @Override
    public ArrayList<Usuario> getRegistros() {
        return this.getRegistros(new String[]{}, null,null);
    }

    @Override
    public ArrayList<Usuario> getRegistros(Object[] cvl) {
        return this.getRegistros(new String[]{}, cvl!=null?new String[]{"CO_COMPANIA", "CO_LOCAL", Usuario.COLUMNA_ACTIVO}:null,cvl);
    }
    
    public ArrayList<Usuario> getRegistros(String[] columnas,Object[] cvl) {
        return this.getRegistros(new String[]{}, columnas,cvl);
    }
    
    public ArrayList<Usuario> getRegistrosPorTipoCargo(String cargo) {
        return this.getRegistros(new String[]{}, new String[]{"tipo_cargo"},new Object[]{cargo});
    }
    
    public ArrayList<Usuario> getRegistrosDni(String dni) {
        return this.getRegistros(new String[]{}, new String[]{"dni"},new Object[]{dni});
    }
    
    public ArrayList<Usuario> getRegistrosCodigo(String codigo) {
        return this.getRegistros(new String[]{}, new String[]{"codigo"},new Object[]{codigo});
    }
    
    public ArrayList<Usuario> getRegistrosLogin(String login) {
        return this.getRegistros(new String[]{}, new String[]{"login"},new Object[]{login});
    }
    
    public boolean existeLogin(String login)
    {
        return this.existe(Usuario.nt, "login", login);
    }
    
    @Override
    public JAbstractModelBD buscarRegistro(Object cvl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public Usuario getRegistro() {
        ArrayList<Usuario> registros = this.getRegistros();
        return registros.get(0);
    }
        
    public Usuario getRegitro(String id)
    {
        ArrayList<Usuario> registros = this.getRegistros(new String[]{"dni"},new Object[]{id});
        return registros.get(0);
    }
    
    public ArrayList<Usuario> getRegistrosFecha(long valor) {
        return this.getRegistrosMn(new String[]{}, new String[]{"fec_registro"},new Object[]{valor});
    }
    
    public ArrayList<Usuario> getRegistrosFecha(long valor1,long valor) {
        return this.getRegistrosMn(new String[]{}, new String[]{"fec_registro","fec_registro"},new Object[]{valor1,valor});
    }
    
    public boolean existeCodigo(String codigo)
    {
        return this.existe(Usuario.nt, "NU_EMPLEADO", codigo);
    }
    
    public boolean existeDni(String dni)
    {
        return this.existe(Usuario.nt, "NU_DOC_IDENTIDAD", dni);
    }    
    
    public String getCodigoUsuario()
    {
        return this.getCodigo(Usuario.nt, "codigo");
    }
    
    public InputStream getDatoFoto()
    {        
        return this.itmp;
    }
    
    public int getNumeroRegistros()
    {
        return this.getNumeroRegistros(Usuario.nt, Usuario.COLUMNA_ACTIVO);
    }
    
    public int activarUsuario(Usuario us)
    {
        return this.eliminacionTemporal(Usuario.nt, Usuario.COLUMNA_ACTIVO, Usuario.COLUMNA_PK, us.getPrimaryKey(), 1);
    }
          
    public ArrayList getBusquedaVendedores(String Filtro){
        ArrayList<Usuario> rgs = new ArrayList<Usuario>();
        Usuario      usr      = null;
        StringBuffer  query;
        try {            
            query = new StringBuffer();
            
            query.append("select * ");
            query.append("  from cmts_usuario ");
            query.append(" WHERE CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("' ");
            query.append("   AND CO_LOCAL= '").append(AtuxVariables.vCodigoLocal).append("' ");

            if ("A".equals(Filtro) || "I".equals(Filtro)){
                query.append("   AND T1.ES_PRODUCTO = '").append(Filtro).append("' ");
            }else if (!"T".equals(Filtro)){
                query.append(Filtro);
            }
            
            query.append(" ORDER BY AP_PATERNO_USUARIO, AP_MATERNO_USUARIO, NO_USUARIO ");
            
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next()){
                usr = new Usuario();
                usr.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("NU_SEC_USUARIO")});
                usr.setCoCompania(rs.getString("CO_COMPANIA"));
                usr.setCoLocal(rs.getString("CO_LOCAL"));
                usr.setNuSecUsuario(rs.getString("NU_SEC_USUARIO"));
                usr.setIdUsuario(rs.getString("ID_USUARIO"));
                usr.setDeClave(rs.getString("DE_CLAVE_USUARIO"));
                usr.setNuEmpleado(rs.getString("NU_EMPLEADO"));
                usr.setApPaterno(rs.getString("AP_PATERNO_USUARIO"));
                usr.setApMaterno(rs.getString("AP_MATERNO_USUARIO"));
                usr.setNombre(rs.getString("NO_USUARIO"));
                usr.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                usr.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                usr.setInSexo(rs.getString("IN_SEXO"));
                usr.setFeNacimiento(rs.getDate("FE_NACIMIENTO"));
                usr.setFeIngreso(rs.getDate("FE_INGRESO"));
                usr.setFeCese(rs.getDate("FE_CESE"));
                usr.setNuTelNormal(rs.getString("NU_TEL_NORMAL"));
                usr.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                usr.setNuTelRef(rs.getString("NU_TEL_REF"));
                usr.setDeDireccionUsuario(rs.getString("DE_DIRECCION_USUARIO"));
                usr.setEsUsuario(rs.getString("ES_USUARIO"));
                usr.setIdCreaUsuario(rs.getString("ID_CREA_USUARIO"));
                usr.setFeCreaUsuario(rs.getDate("FE_CREA_USUARIO"));
                usr.setIdModUsuario(rs.getString("ID_MOD_USUARIO"));
                usr.setFeModUsuario(rs.getDate("FE_MOD_USUARIO"));
                usr.setNoEmail(rs.getString("NO_EMAIL"));
                usr.setDeUbigeoDireccion(rs.getString("DE_UBIGEO_DIRECCION"));
                rgs.add(usr);
            }
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


    public Usuario getRegistroPorPk(Object[] id)
    {
            Usuario usr = null;
        try {
            
            rs =  this.selectPorPk(Usuario.nt,Usuario.CAMPOS_ID, Usuario.COLUMNA_PK, id);
            
            while(rs.next()){
               usr = new Usuario();
                usr.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("NU_SEC_USUARIO")});
                usr.setCoCompania(rs.getString("CO_COMPANIA"));
                usr.setCoLocal(rs.getString("CO_LOCAL"));
                usr.setNuSecUsuario(rs.getString("NU_SEC_USUARIO"));
                usr.setIdUsuario(rs.getString("ID_USUARIO"));
                usr.setDeClave(rs.getString("DE_CLAVE_USUARIO"));
                usr.setNuEmpleado(rs.getString("NU_EMPLEADO"));
                usr.setApPaterno(rs.getString("AP_PATERNO_USUARIO"));
                usr.setApMaterno(rs.getString("AP_MATERNO_USUARIO"));
                usr.setNombre(rs.getString("NO_USUARIO"));
                usr.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                usr.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                usr.setInSexo(rs.getString("IN_SEXO"));
                usr.setFeNacimiento(rs.getDate("FE_NACIMIENTO"));
                usr.setFeIngreso(rs.getDate("FE_INGRESO"));
                usr.setFeCese(rs.getDate("FE_CESE"));
                usr.setNuTelNormal(rs.getString("NU_TEL_NORMAL"));
                usr.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                usr.setNuTelRef(rs.getString("NU_TEL_REF"));
                usr.setDeDireccionUsuario(rs.getString("DE_DIRECCION_USUARIO"));
                usr.setEsUsuario(rs.getString("ES_USUARIO"));
                usr.setIdCreaUsuario(rs.getString("ID_CREA_USUARIO"));
                usr.setFeCreaUsuario(rs.getDate("FE_CREA_USUARIO"));
                usr.setIdModUsuario(rs.getString("ID_MOD_USUARIO"));
                usr.setFeModUsuario(rs.getDate("FE_MOD_USUARIO"));
                usr.setNoEmail(rs.getString("NO_EMAIL"));
                usr.setDeUbigeoDireccion(rs.getString("DE_UBIGEO_DIRECCION"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usr;
    }
    
    public Usuario getRegistroPorPk2(Object[] id)
    {
            Usuario usr = null;
        try {
            
            rs =  this.selectPorPk(Usuario.nt,Usuario.CAMPOS_ID, Usuario.COLUMNA_PK2, id);
            
            while(rs.next()){
               usr = new Usuario();
                usr.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("NU_SEC_USUARIO")});
                usr.setCoCompania(rs.getString("CO_COMPANIA"));
                usr.setCoLocal(rs.getString("CO_LOCAL"));
                usr.setNuSecUsuario(rs.getString("NU_SEC_USUARIO"));
                usr.setIdUsuario(rs.getString("ID_USUARIO"));
                usr.setDeClave(rs.getString("DE_CLAVE_USUARIO"));
                usr.setNuEmpleado(rs.getString("NU_EMPLEADO"));
                usr.setApPaterno(rs.getString("AP_PATERNO_USUARIO"));
                usr.setApMaterno(rs.getString("AP_MATERNO_USUARIO"));
                usr.setNombre(rs.getString("NO_USUARIO"));
                usr.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                usr.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                usr.setInSexo(rs.getString("IN_SEXO"));
                usr.setFeNacimiento(rs.getDate("FE_NACIMIENTO"));
                usr.setFeIngreso(rs.getDate("FE_INGRESO"));
                usr.setFeCese(rs.getDate("FE_CESE"));
                usr.setNuTelNormal(rs.getString("NU_TEL_NORMAL"));
                usr.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                usr.setNuTelRef(rs.getString("NU_TEL_REF"));
                usr.setDeDireccionUsuario(rs.getString("DE_DIRECCION_USUARIO"));
                usr.setEsUsuario(rs.getString("ES_USUARIO"));
                usr.setIdCreaUsuario(rs.getString("ID_CREA_USUARIO"));
                usr.setFeCreaUsuario(rs.getDate("FE_CREA_USUARIO"));
                usr.setIdModUsuario(rs.getString("ID_MOD_USUARIO"));
                usr.setFeModUsuario(rs.getDate("FE_MOD_USUARIO"));
                usr.setNoEmail(rs.getString("NO_EMAIL"));
                usr.setDeUbigeoDireccion(rs.getString("DE_UBIGEO_DIRECCION"));                 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usr;
    }
                 
    public Usuario getFotoPersonal(Object[] id){
        Usuario usr = null;
        try {
            rs =  this.selectPorPk(Usuario.tablaFoto,Usuario.CAMPO_FOTO, Usuario.COLUMNA_PK2, id);
            while(rs.next()){
                usr = new Usuario();
                usr.setCoCompania(rs.getString("CO_COMPANIA"));
                usr.setCoLocal(rs.getString("CO_LOCAL"));
                usr.setNuSecUsuario(rs.getString("NU_SEC_USUARIO"));
                usr.setDeFotoUsuario(rs.getBlob("DE_FOTO_USUARIO"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usr;
    }
    
    /**
     * 
     * @param mdl modelo
     * @param opcion si true llenara el modelos con todos los datos, delocontrario solo obtendra la clave primaria.
     * @return 
     */
    @Override
    public JAbstractModelBD getRegistro(JAbstractModelBD mdl,boolean opcion) {
        us = (Usuario)mdl;        
                
        sql = "select" + generarArrayAString(Usuario.CAMPOS_ID) + Usuario.nt +  " where NU_DOC_IDENTIDAD = '"+us.getNuDocIdentidad()+"'";
        
        try {
            rs =BaseConexion.getConexion().createStatement().executeQuery(sql);
            if(rs.next())
            {
                us.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("NU_SEC_USUARIO")});
                us.setCoCompania(rs.getString("CO_COMPANIA"));
                us.setCoLocal(rs.getString("CO_LOCAL"));
                us.setNuSecUsuario(rs.getString("NU_SEC_USUARIO"));
                us.setIdUsuario(rs.getString("ID_USUARIO"));
                us.setDeClave(rs.getString("DE_CLAVE_USUARIO"));
                us.setNuEmpleado(rs.getString("NU_EMPLEADO"));
                us.setApPaterno(rs.getString("AP_PATERNO_USUARIO"));
                us.setApMaterno(rs.getString("AP_MATERNO_USUARIO"));
                us.setNombre(rs.getString("NO_USUARIO"));
                us.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                us.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                us.setInSexo(rs.getString("IN_SEXO"));
                us.setFeNacimiento(rs.getDate("FE_NACIMIENTO"));
                us.setFeIngreso(rs.getDate("FE_INGRESO"));
                us.setFeCese(rs.getDate("FE_CESE"));
                us.setNuTelNormal(rs.getString("NU_TEL_NORMAL"));
                us.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                us.setNuTelRef(rs.getString("NU_TEL_REF"));
                us.setDeDireccionUsuario(rs.getString("DE_DIRECCION_USUARIO"));
                us.setEsUsuario(rs.getString("ES_USUARIO"));
                us.setIdCreaUsuario(rs.getString("ID_CREA_USUARIO"));
                us.setFeCreaUsuario(rs.getDate("FE_CREA_USUARIO"));
                us.setIdModUsuario(rs.getString("ID_MOD_USUARIO"));
                us.setFeModUsuario(rs.getDate("FE_MOD_USUARIO"));
                us.setNoEmail(rs.getString("NO_EMAIL"));
                us.setDeUbigeoDireccion(rs.getString("DE_UBIGEO_DIRECCION"));
                rs.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return us;
    }   

    @Override
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException {
      
        Usuario usr = (Usuario)mdl;
        int gravado = 0;                               
                            
        Object[] valores ={usr.getCoCompania(),
                            usr.getCoLocal(),
                           usr.getNuSecUsuario(),
                           usr.getIdUsuario(),
                            usr.getDeClave(),
                           usr.getNuEmpleado(),                           
                           usr.getApPaterno(),
                           usr.getApMaterno(),
                           usr.getNombre(),
                            usr.getTiDocIdentidad(),
                            usr.getNuDocIdentidad(),
                           usr.getInSexo(),
                            usr.getFeNacimiento(),
                            usr.getFeIngreso(),
                            usr.getFeCese(),
                            usr.getNuTelNormal(),
                            usr.getNuTelMovil(),
                            usr.getNuTelRef(),
                            usr.getDeDireccionUsuario(),
                           usr.getEsUsuario(),
                            usr.getIdCreaUsuario(),
                            usr.getFeCreaUsuario(),
                            usr.getIdModUsuario(),
                            usr.getFeModUsuario(),
                            usr.getNoEmail(),
                            usr.getDeUbigeoDireccion()
                        };
       
           gravado = this.agregarRegistroPs(Usuario.nt, Usuario.CAMPOS_ID, valores);
       
        if(gravado==1)
        {            
         //  try{
         //    this.grabarBitacora(usr);
         //   }catch(SQLException ex){ex.printStackTrace();}            
            return true;
        }
                    
        return false;
    }

    public boolean guardarFotoPersonal(JAbstractModelBD mdl) throws SQLException{
       Usuario usr = (Usuario)mdl;
        int gravado = 0;        
        
        Object[] valores ={usr.getCoCompania(), usr.getCoLocal(), usr.getNuSecUsuario(), usr.getFoto(null)};
        gravado = this.agregarRegistroPs(Usuario.tablaFoto, Usuario.CAMPO_FOTO, valores);
        
        if(gravado==1){            
            return true;
        }
        return false;
    }
        
    public int eliminarFotoPersonal(JAbstractModelBD mdl){
        Usuario usr = (Usuario)mdl;
        int gravado = 0;
           
        gravado = this.eliminacionFoto(usr.getTablaFoto(),usr.getCoCompania(),usr.getCoLocal(),usr.getNuSecUsuario());
        return gravado;
    }
    
    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
       Usuario usr = (Usuario)mdl;
        int gravado = 0;
        String campos = Usuario.CAMPOS_NO_ID.toString();
        
        Object[] valores = {usr.getIdUsuario(),
                            usr.getDeClave(),
                           usr.getNuEmpleado(),                           
                           usr.getApPaterno(),
                           usr.getApMaterno(),
                           usr.getNombre(),
                            usr.getTiDocIdentidad(),
                            usr.getNuDocIdentidad(),
                           usr.getInSexo(),
                            usr.getFeNacimiento(),
                            usr.getFeIngreso(),
                            usr.getFeCese(),
                            usr.getNuTelNormal(),
                            usr.getNuTelMovil(),
                            usr.getNuTelRef(),
                            usr.getDeDireccionUsuario(),
                           usr.getEsUsuario(),
                            usr.getIdCreaUsuario(),
                            usr.getFeCreaUsuario(),
                            usr.getIdModUsuario(),
                            usr.getFeModUsuario(),
                            usr.getNoEmail(),
                            usr.getDeUbigeoDireccion()
                            };
   
        gravado = this.actualizarRegistroPs(usr.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(Usuario.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(Usuario.COLUMNA_PK2, usr.getPrimaryKey()) , valores);
        return gravado;
    }
    
    public int actualizarPass(JAbstractModelBD mdl) {
       Usuario usr = (Usuario)mdl;
        int gravado = 0;
        String campos = "ID_USUARIO,DE_CLAVE_USUARIO";
        
        Object[] valores = {usr.getIdUsuario(),usr.getDeNuevaClave(),usr.getPrimaryKey()};
        
        gravado = this.actualizarRegistroPs(Usuario.nt, this.adjuntarSimbolo(campos, ",", "?")+Ex.WHERE+ unirColumnasValores(Usuario.COLUMNA_PK2, usr.getPrimaryKey()) , valores);
        return gravado;
    }
    
    public int grabarEnBitacora(Usuario us)
    {
        try{
            return this.grabarBitacora(us);
        }catch(SQLException ex){ex.printStackTrace();}
        return -1;
    }
    private int grabarBitacora(Usuario us) throws SQLException 
    {
        
            Calendar cr = Calendar.getInstance();
            Bitacora bt = getBitacora(us);
            sql = "INSERT INTO gv_bitacora ("+Bitacora.COLUMNAS_NO_ID+")"+
                  " VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            this.ps = BaseConexion.getConexion().prepareStatement(sql);
            
            ps.setString(1,us.getCoCompania());  
            ps.setString(2,us.getCoLocal());
            ps.setString(3,us.getNuSecUsuario());
            ps.setString(4, System.getProperty("os.name"));
            ps.setString(5, System.getProperty("os.arch"));
            ps.setString(6, System.getProperty("os.version"));
            ps.setString(7, System.getProperty("user.name"));
            if(bt!=null)
            {
                ps.setLong(8, bt.getFechaActividad());
            }else{
                ps.setLong(8, cr.getTime().getTime());
            }
            ps.setLong(9, cr.getTime().getTime());
            return ps.executeUpdate();            
        
    }
    
    private Bitacora getBitacora(Usuario us) 
    {
        try {
            Bitacora bt = new Bitacora();
            sql = "select * from gv_bitacora where idusuario = "+us.getPrimaryKey()+" order by fecha_actividad desc limit 1";
            rs =BaseConexion.getConexion().createStatement().executeQuery(sql);
            if(rs.next())
            {
                bt.setIdbitacora(rs.getLong(1));
                bt.setIdusuario(rs.getInt(2));
                bt.setOs(rs.getString(3));
                bt.setArquitectura(rs.getString(4));
                bt.setVersion(rs.getString(5));
                bt.setUsuario(rs.getString(6));
                bt.setUltimaActividad(rs.getLong(7));
                bt.setFechaActividad(rs.getLong(8));
                return bt;
            }
                
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return null;
    }          
    
    public ImageIcon getFoto(String[] id)
    {
        InputStream archivo = this.getArchivo(Usuario.nt, "foto", Usuario.COLUMNA_PK, id); 
        itmp = archivo;
        ImageIcon ii = null;
        if(archivo!= null)
        {
            try {
                 BufferedImage read = ImageIO.read(archivo);
                  ii = new ImageIcon(read);
            } catch (IOException ex) {
                Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ii;        
    } 

    @Override
    public ArrayList<Usuario> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<Usuario> rgs = new ArrayList<Usuario>();        
        try {
            
            if(id != null)
            { 
               this.numRegistros = this.getNumeroRegistros(Usuario.nt, Usuario.COLUMNA_ACTIVO, Usuario.COLUMNA_ACTIVO, id);
            }else
            {
               this.numRegistros = this.getNumeroRegistros(Usuario.nt, Usuario.COLUMNA_ACTIVO);               
               if(rs.isClosed())
               {
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(Usuario.nt,campos,columnaId,id,null);
            if(this.numRegistros<finalPag)
           {
              finalPag =  this.numRegistros;
           }
            if(finalPag>inicioPag)
            {
                inicioPag -= (finalPag-inicioPag)-1;
            }
            Usuario usr = null;
            while(rs.next())
            {
                usr = new Usuario();
                usr.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("NU_SEC_USUARIO")});
                usr.setCoCompania(rs.getString("CO_COMPANIA"));
                usr.setCoLocal(rs.getString("CO_LOCAL"));
                usr.setNuSecUsuario(rs.getString("NU_SEC_USUARIO"));
                usr.setIdUsuario(rs.getString("ID_USUARIO"));
                usr.setDeClave(rs.getString("DE_CLAVE_USUARIO"));
                usr.setNuEmpleado(rs.getString("NU_EMPLEADO"));
                usr.setApPaterno(rs.getString("AP_PATERNO_USUARIO"));
                usr.setApMaterno(rs.getString("AP_MATERNO_USUARIO"));
                usr.setNombre(rs.getString("NO_USUARIO"));
                usr.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                usr.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                usr.setInSexo(rs.getString("IN_SEXO"));
                usr.setFeNacimiento(rs.getDate("FE_NACIMIENTO"));
                usr.setFeIngreso(rs.getDate("FE_INGRESO"));
                usr.setFeCese(rs.getDate("FE_CESE"));
                usr.setNuTelNormal(rs.getString("NU_TEL_NORMAL"));
                usr.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                usr.setNuTelRef(rs.getString("NU_TEL_REF"));
                usr.setDeDireccionUsuario(rs.getString("DE_DIRECCION_USUARIO"));
                usr.setEsUsuario(rs.getString("ES_USUARIO"));
                usr.setIdCreaUsuario(rs.getString("ID_CREA_USUARIO"));
                usr.setFeCreaUsuario(rs.getDate("FE_CREA_USUARIO"));
                usr.setIdModUsuario(rs.getString("ID_MOD_USUARIO"));
                usr.setFeModUsuario(rs.getDate("FE_MOD_USUARIO"));
                usr.setNoEmail(rs.getString("NO_EMAIL"));
                usr.setDeUbigeoDireccion(rs.getString("DE_UBIGEO_DIRECCION"));
                 rgs.add(usr);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }
     
     
    public Usuario getUltimoUsuario() throws SQLException
    {
       Usuario usr = null;
       rs = this.getUltimoRegistro(Usuario.nt, "NU_SEC_USUARIO");
       try{
           
            while(rs.next())
            {
                   usr = new Usuario();
                usr.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("NU_SEC_USUARIO")});
                usr.setCoCompania(rs.getString("CO_COMPANIA"));
                usr.setCoLocal(rs.getString("CO_LOCAL"));
                usr.setNuSecUsuario(rs.getString("NU_SEC_USUARIO"));
                usr.setIdUsuario(rs.getString("ID_USUARIO"));
                usr.setDeClave(rs.getString("DE_CLAVE_USUARIO"));
                usr.setNuEmpleado(rs.getString("NU_EMPLEADO"));
                usr.setApPaterno(rs.getString("AP_PATERNO_USUARIO"));
                usr.setApMaterno(rs.getString("AP_MATERNO_USUARIO"));
                usr.setNombre(rs.getString("NO_USUARIO"));
                usr.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                usr.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                usr.setInSexo(rs.getString("IN_SEXO"));
                usr.setFeNacimiento(rs.getDate("FE_NACIMIENTO"));
                usr.setFeIngreso(rs.getDate("FE_INGRESO"));
                usr.setFeCese(rs.getDate("FE_CESE"));
                usr.setNuTelNormal(rs.getString("NU_TEL_NORMAL"));
                usr.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                usr.setNuTelRef(rs.getString("NU_TEL_REF"));
                usr.setDeDireccionUsuario(rs.getString("DE_DIRECCION_USUARIO"));
                usr.setEsUsuario(rs.getString("ES_USUARIO"));
                usr.setIdCreaUsuario(rs.getString("ID_CREA_USUARIO"));
                usr.setFeCreaUsuario(rs.getDate("FE_CREA_USUARIO"));
                usr.setIdModUsuario(rs.getString("ID_MOD_USUARIO"));
                usr.setFeModUsuario(rs.getDate("FE_MOD_USUARIO"));
                usr.setNoEmail(rs.getString("NO_EMAIL"));
                usr.setDeUbigeoDireccion(rs.getString("DE_UBIGEO_DIRECCION"));
                 
            }
       }catch(SQLException ex){
           ex.printStackTrace();
       }
       return usr;
    }
       
     public ArrayList<Usuario> getRegistrosMn(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<Usuario> rgs = new ArrayList<Usuario>();        
        try {
            
            if(id != null)
            { 
               this.numRegistros = this.getNumeroRegistros(Usuario.nt, Usuario.COLUMNA_ACTIVO, Usuario.COLUMNA_ACTIVO, id);
            }else
            {
               this.numRegistros = this.getNumeroRegistros(Usuario.nt, Usuario.COLUMNA_ACTIVO);               
               if(rs.isClosed())
               {
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(Usuario.nt,campos,id);
            if(this.numRegistros<finalPag)
           {
              finalPag =  this.numRegistros;
           }
            if(finalPag>inicioPag)
            {
                inicioPag -= (finalPag-inicioPag)-1;
            }
            Usuario usr = null;
            while(rs.next())
            {
                usr = new Usuario();
                usr.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("NU_SEC_USUARIO")});
                usr.setCoCompania(rs.getString("CO_COMPANIA"));
                usr.setCoLocal(rs.getString("CO_LOCAL"));
                usr.setNuSecUsuario(rs.getString("NU_SEC_USUARIO"));
                usr.setIdUsuario(rs.getString("ID_USUARIO"));
                usr.setDeClave(rs.getString("DE_CLAVE_USUARIO"));
                usr.setNuEmpleado(rs.getString("NU_EMPLEADO"));
                usr.setApPaterno(rs.getString("AP_PATERNO_USUARIO"));
                usr.setApMaterno(rs.getString("AP_MATERNO_USUARIO"));
                usr.setNombre(rs.getString("NO_USUARIO"));
                usr.setTiDocIdentidad(rs.getString("TI_DOC_IDENTIDAD"));
                usr.setNuDocIdentidad(rs.getString("NU_DOC_IDENTIDAD"));
                usr.setInSexo(rs.getString("IN_SEXO"));
                usr.setFeNacimiento(rs.getDate("FE_NACIMIENTO"));
                usr.setFeIngreso(rs.getDate("FE_INGRESO"));
                usr.setFeCese(rs.getDate("FE_CESE"));
                usr.setNuTelNormal(rs.getString("NU_TEL_NORMAL"));
                usr.setNuTelMovil(rs.getString("NU_TEL_MOVIL"));
                usr.setNuTelRef(rs.getString("NU_TEL_REF"));
                usr.setDeDireccionUsuario(rs.getString("DE_DIRECCION_USUARIO"));
                usr.setEsUsuario(rs.getString("ES_USUARIO"));
                usr.setIdCreaUsuario(rs.getString("ID_CREA_USUARIO"));
                usr.setFeCreaUsuario(rs.getDate("FE_CREA_USUARIO"));
                usr.setIdModUsuario(rs.getString("ID_MOD_USUARIO"));
                usr.setFeModUsuario(rs.getDate("FE_MOD_USUARIO"));
                usr.setNoEmail(rs.getString("NO_EMAIL"));
                usr.setDeUbigeoDireccion(rs.getString("DE_UBIGEO_DIRECCION"));                
                 rgs.add(usr);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }
    public void setUsuario(JAbstractModelBD prv) {
        this.us = (Usuario)prv;
    }
    
    public Usuario getUsuario() {
        if(us == null)
        {
            us = new Usuario();
        }
        return us;
    }
    public String getNuevoCodigo(){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(Usuario.nt,"rtrim(ltrim(to_char(max(NU_SEC_USUARIO) + 1,'000000')))"," CO_COMPANIA = '" + us.getCoCompania() + "' and CO_LOCAL = '" + us.getCoLocal() + "'");
        } catch (SQLException ex) {
            Logger.getLogger(CTipoVia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }    
}
