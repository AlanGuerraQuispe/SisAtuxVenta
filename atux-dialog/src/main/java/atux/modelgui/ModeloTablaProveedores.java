package atux.modelgui;

import atux.controllers.CProveedores;
import atux.modelbd.Proveedores;

public class ModeloTablaProveedores extends ModeloTabla{

    String[] columnas = {"Ruc","Proveedor","Direccion","Telefono"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {140,500,600,200};
    
    public ModeloTablaProveedores() {
        cc = new CProveedores();
        this.nombreColumnas = columnas;       
        registros = ((CProveedores)cc).getRegistros();
    }
    
    public ModeloTablaProveedores(int opcion) {
        cc = new CProveedores();
        this.nombreColumnas = columnas;       
        registros = ((CProveedores)cc).getRegistros();
    }
    
    public ModeloTablaProveedores(String[] campo,Object[] valor) {
        cc = new CProveedores();
        this.nombreColumnas = columnas;       
        registros = ((CProveedores)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaProveedores(String Filtro) {
        cc = new CProveedores();
        this.nombreColumnas = columnas;       
        registros = ((CProveedores)cc).getRegistros(Filtro);
    }

    
    public ModeloTablaProveedores(int inicio,int finalPag) {
        cc = new CProveedores();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CProveedores)cc).getRegistros();
    }
    public ModeloTablaProveedores(int opcion,int inicio,int finalPag) {
        cc = new CProveedores();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CProveedores)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CProveedores)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CProveedores)cc).getRegistros();
        }
        
    }
    
    public int getCantidadRegistros()
    {
        return cc.getCantidadRegistros();
    }  

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {        
        switch(columnIndex)
        {
            case 0: return ((Proveedores)registros.get(rowIndex)).getNuDocIdentidad();
            case 1: return ((Proveedores)registros.get(rowIndex)).getDeProveedor();     
            case 2: return ((Proveedores)registros.get(rowIndex)).getDeDireccion();  
            case 3: return ((Proveedores)registros.get(rowIndex)).getDeTelefonoProveedor();  
            default: return null;   
        }
    }
    
}
