package atux.modelgui;

import atux.controllers.CUsuario;
import atux.modelbd.Usuario;
import atux.util.Helper;
import java.lang.String;
import atux.util.common.AtuxVariables;

public class ModeloTablaUsuario extends ModeloTabla{
    private CUsuario  cUser;
    private String[] nColumnas = {"Dni","Codigo","Login","Nombre","Apellidos","Telefono","Cargo","Fecha Registro"};
    private String[] columnasBusqueda = {"Dni","Ap. Paterno","Ap. Materno","Nombres"};
    public static final Integer[] anchoColumnasBusqueda  = {150,250,250,300};
    public static int tipoconsulta = -1;
    public static final int POR_TIPO_CARGO = 1;
    public static final int POR_DNI = 2;
    public static final int POR_CODIGO = 3;
    public static final int POR_LOGIN = 4;
    
    public ModeloTablaUsuario() {
        cUser = new CUsuario();
        this.nombreColumnas = nColumnas;
        registros = cUser.getRegistros();
        
    }
    public ModeloTablaUsuario(String Filtro) {
        cUser = new CUsuario();
        this.nombreColumnas = columnasBusqueda;
        registros = cUser.getBusquedaVendedores(Filtro);
    }    
    
   public ModeloTablaUsuario(int opcion,int inicio,int finalPag) {
    cUser = new CUsuario();
    this.nombreColumnas = nColumnas;
    cUser.setNumPaginador(inicio, finalPag);
    switch(opcion)
    {
        case 0:                         
            registros = cUser.getRegistros(new Object[]{AtuxVariables.vCodigoCompania, AtuxVariables.vCodigoLocal, "I"});
            break;
        case 1:
            registros = cUser.getRegistros(new Object[]{AtuxVariables.vCodigoCompania, AtuxVariables.vCodigoLocal, "A"});
            break;
        default:
            registros = cUser.getRegistros(null);
    }
        
    }

    public ModeloTablaUsuario(int opcion,String valor) {
        cUser = new CUsuario();
        this.nombreColumnas = nColumnas;
        switch(opcion)
        {
            case POR_TIPO_CARGO:
                registros = cUser.getRegistrosPorTipoCargo(valor);
                break;
            case  POR_DNI:
                registros = cUser.getRegistrosDni(valor);
                break;
            case  POR_CODIGO:
                registros = cUser.getRegistrosCodigo(valor);
                break;
            case  POR_LOGIN:
                registros = cUser.getRegistrosLogin(valor);
                break;
        }        
    }
    
    
    public ModeloTablaUsuario(int inicioPag,int finalPag,String valor) {
        cUser = new CUsuario();
        this.nombreColumnas = nColumnas;
       cUser.setNumPaginador(inicioPag, finalPag);
       registros = cUser.getRegistrosPorTipoCargo(valor); 
    }

    public ModeloTablaUsuario(long fec) {
        cUser = new CUsuario();
        this.nombreColumnas = nColumnas;
        registros = cUser.getRegistrosFecha(fec);
    }
    
    public ModeloTablaUsuario(long inicio,long fin) {
        cUser = new CUsuario();
        this.nombreColumnas = nColumnas;
        registros = cUser.getRegistrosFecha(inicio,fin);
    }
    
    public ModeloTablaUsuario(int inicioPag,int finalPag,long inicio,long fin) {
        cUser = new CUsuario();
        this.nombreColumnas = nColumnas;
        cUser.setNumPaginador(inicioPag, finalPag);
        registros = cUser.getRegistrosFecha(inicio,fin);
    }
    
    public ModeloTablaUsuario(int inicioPag,int finalPag,long inicio) {
        cUser = new CUsuario();
        this.nombreColumnas = nColumnas;
        cUser.setNumPaginador(inicioPag, finalPag);
        registros = cUser.getRegistrosFecha(inicio);
    }

    public ModeloTablaUsuario(int opcion) {
        cUser = new CUsuario();
        this.nombreColumnas = nColumnas;       
        registros = cUser.getRegistros(new Object[]{new Integer(opcion)});
    }
    
    public int getCantidadRegistros()
    {
        return cUser.getCantidadRegistros();
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex)
        {
            case 0: return ((Usuario)registros.get(rowIndex)).getNuDocIdentidad();
            case 1: return ((Usuario)registros.get(rowIndex)).getApPaterno();
            case 2: return ((Usuario)registros.get(rowIndex)).getApMaterno();
            case 3: return ((Usuario)registros.get(rowIndex)).getNombre();
            case 4: return ((Usuario)registros.get(rowIndex)).getEsUsuario().equals("A") ? "Activo" : "Inactivo";
            default: return null;   
        }
    }
    
}
