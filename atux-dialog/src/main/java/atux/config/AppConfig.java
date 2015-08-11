package atux.config;

import atux.controllers.CLocal;
import atux.controllers.CUsuario;
import atux.modelbd.Almacen;
import atux.modelbd.Local;
import atux.modelbd.Moneda;
import atux.modelbd.Usuario;
import atux.util.common.AtuxVariables;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author aguerra
 */
public class  AppConfig implements Serializable{
    private static Usuario usuario;
    private static Local   local;
    private static Almacen almacen;
    private static Moneda moneda;
    private static ArrayList<Almacen> almacenes;   
    
    public static enum Estado{ERROR,NO_EXISTE,ERROR_CLAVE,ACCESO_OK};
    
    
    public AppConfig() //throws ParserConfigurationException
    {
      //DocumentBuilderFactory dbc = DocumentBuilderFactory.newInstance();
      //DocumentBuilder db = dbc.newDocumentBuilder();
      //Document doc = db.newDocument();
        
    }
    
    public static Estado configUsuario(String login,String clave)
    {
        //if(usuario == null)
        //{
            return initUsuario(login,clave);
        //}else{
            //return Estado.ERROR;
        //}
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        AppConfig.usuario = usuario;
    }

    public static Almacen getAlmacen() {
        return almacen;
    }

    public static void setAlmacen(Almacen almacen) {
        AppConfig.almacen = almacen;
    }

    public static Moneda getMoneda() {
        return moneda;
    }

    public static void setMoneda(Moneda moneda) {
        AppConfig.moneda = moneda;
    }

    public static Local getLocal(String coLocal) {
        if(local == null)
        {
            return initLocal(coLocal);
        }else{
            return local;
        }
    }

    public static void setLocal(Local local) {
        AppConfig.local = local;
    }        

    public static ArrayList<Almacen> getAlmacenes() {
        return almacenes;
    }

    public static void setAlmacenes(ArrayList<Almacen> almacenes) {
        AppConfig.almacenes = almacenes;
    }
    
    private static Estado initUsuario(String login,String clave)
    {
        CUsuario cuser = new CUsuario();
        usuario = new Usuario();        
        usuario = cuser.getRegistroPorPk(new Object[]{AtuxVariables.vCodigoCompania,AtuxVariables.vCodigoLocal,login});
        
        if(usuario == null)
        {
            return Estado.NO_EXISTE;
        }else{            
            if(clave.equals(usuario.getDeClave())) 
            {
                //cuser.grabarEnBitacora(usuario); TODO ver grabado en bitacora
                //if(moneda==null)
                //{
                    //CMoneda controllerMoneda = new CMoneda();
                    //controllerMoneda.getRegistros();
                    //moneda = controllerMoneda.getPredeterminado(); 
                //} 
                AtuxVariables.vNuSecUsuario = usuario.getNuSecUsuario();
                AtuxVariables.vIdUsuario = usuario.getIdUsuario();
                AtuxVariables.vNoUsuario = usuario.getNombre();
                AtuxVariables.vPaternoUsuario = usuario.getApPaterno();
                return Estado.ACCESO_OK;
                
            }else{
                return Estado.ERROR_CLAVE;
            }
        }
    }
    
    private static Local initLocal(String coLocal) {
        CLocal cLocal = new CLocal();
        local = new Local();        
        local = cLocal.getRegistrosCodigo(coLocal);
        return local;
    }
    
}
