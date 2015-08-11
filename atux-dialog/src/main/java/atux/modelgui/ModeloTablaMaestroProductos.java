package atux.modelgui;

import atux.controllers.CProducto;
import atux.modelbd.Producto;

public class ModeloTablaMaestroProductos extends ModeloTabla{

    String[] columnas = {"CODIGO", "DESCRIPCION", "UNIDAD PRODUCTO", "LABORATORIO"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;
    public static final Integer[] anchoColumnas  = {140,600,200,300};
    public static final Integer[] anchoColumnasBusqueda  = {100,400,200,200};
    
    public ModeloTablaMaestroProductos(String Filtro) {
        cc = new CProducto();
        this.nombreColumnas = columnas;       
        registros = ((CProducto)cc).getProductosLaboratorio(Filtro);
    }
    
    public ModeloTablaMaestroProductos(int opcion) {
        cc = new CProducto();
        this.nombreColumnas = columnas;       
        registros = ((CProducto)cc).getRegistros();
    }
    
    public ModeloTablaMaestroProductos(String[] campo,Object[] valor) {
        cc = new CProducto();
        this.nombreColumnas = columnas;       
        registros = ((CProducto)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaMaestroProductos(int inicio,int finalPag) {
        cc = new CProducto();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CProducto)cc).getRegistros(null);
    }
    public ModeloTablaMaestroProductos(int opcion,int inicio,int finalPag) {
        cc = new CProducto();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CProducto)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CProducto)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CProducto)cc).getRegistros(null);
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
            case 0: return ((Producto)registros.get(rowIndex)).getCoProducto();
            case 1: return ((Producto)registros.get(rowIndex)).getDeProducto();     
            case 2: return ((Producto)registros.get(rowIndex)).getDeUnidadProducto();  
            case 3: return ((Producto)registros.get(rowIndex)).getDeLaboratorio(); 
            default: return null;   
        }
    }
}
