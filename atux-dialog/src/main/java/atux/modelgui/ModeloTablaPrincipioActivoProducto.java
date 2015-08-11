package atux.modelgui;

import atux.controllers.CPrincipioActivoProducto;
import atux.modelbd.PrincipioActivoProducto;
import atux.util.common.AtuxUtility;

public class ModeloTablaPrincipioActivoProducto extends ModeloTabla{

    String[] columnas = {"Codigo","Descripción","Unidad","Costo","Precio","Imprime?"};
    public static final Integer[] anchoColumnas  = {60,160,80,30,40,40};

    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;
    public static String[] Columnas_Sin_Estado = new String[]{"CO_COMPANIA", "CO_PRODUCTO", "NU_REVISION_PRODUCTO"};
    public static String[] Columnas_Con_Estado = new String[]{"CO_COMPANIA", "CO_PRODUCTO", "NU_REVISION_PRODUCTO","ES_PRODUCTO_PRINCIPIO"};
    

    public ModeloTablaPrincipioActivoProducto() {
        cc = new CPrincipioActivoProducto();
        this.nombreColumnas = columnas;       
        registros = ((CPrincipioActivoProducto)cc).getRegistros();
    }
    
    public ModeloTablaPrincipioActivoProducto(int opcion) {
        cc = new CPrincipioActivoProducto();
        this.nombreColumnas = columnas;       
        registros = ((CPrincipioActivoProducto)cc).getRegistros();
    }
    
    public ModeloTablaPrincipioActivoProducto(String[] campo,Object[] valor) {
        cc = new CPrincipioActivoProducto();
        this.nombreColumnas = columnas;       
        registros = ((CPrincipioActivoProducto)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaPrincipioActivoProducto(String Compania,String Producto, String Estado) {
        cc = new CPrincipioActivoProducto();
        this.nombreColumnas = columnas;
        registros = ((CPrincipioActivoProducto)cc).getPrincipioActivoPrincipio(Compania, Producto, Estado);

    }

    
    public ModeloTablaPrincipioActivoProducto(int inicio,int finalPag) {
        cc = new CPrincipioActivoProducto();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CPrincipioActivoProducto)cc).getRegistros(null);
    }
    public ModeloTablaPrincipioActivoProducto(int opcion,int inicio,int finalPag) {
        cc = new CPrincipioActivoProducto();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CPrincipioActivoProducto)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CPrincipioActivoProducto)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CPrincipioActivoProducto)cc).getRegistros(null);
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
            case 0: return ((PrincipioActivoProducto)registros.get(rowIndex)).getCoProductoInsumo();
            case 1: return ((PrincipioActivoProducto)registros.get(rowIndex)).getDeProductoInsumo();
            case 2: return ((PrincipioActivoProducto)registros.get(rowIndex)).getDeUnidad();
            case 3: return ((PrincipioActivoProducto)registros.get(rowIndex)).getVaCosto();
            case 4: return ((PrincipioActivoProducto)registros.get(rowIndex)).getVaPrecioPromedio();
            case 5: return ((PrincipioActivoProducto)registros.get(rowIndex)).getInImpresion();
            default: return null;   
        }
    }
}
