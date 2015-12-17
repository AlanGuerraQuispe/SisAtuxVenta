package atux.modelgui;

import atux.controllers.CProducto;
import atux.modelbd.Producto;
import atux.modelbd.ProductoFull;
import atux.modelbd.ProductoLocal;

public class ModeloTablaMaestroProductosFull extends ModeloTabla{

    String[] columnas = {"CODIGO", "DESCRIPCION", "UNIDAD PRODUCTO", "LABORATORIO"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;
    public static final Integer[] anchoColumnas  = {140,600,200,300};
    public static final Integer[] anchoColumnasBusqueda  = {100,400,200,200};

    public ModeloTablaMaestroProductosFull (Producto producto, ProductoLocal productoLocal, ProductoFull productoFull) {
        cc = new CProducto();
        this.nombreColumnas = columnas;
        registros = ((CProducto)cc).getProductosMaestrosFull(producto, productoLocal, productoFull );

    }


    public ModeloTablaMaestroProductosFull(String Filtro) {
        cc = new CProducto();
        this.nombreColumnas = columnas;
        registros = ((CProducto)cc).getProductosMaestrosFull(Filtro);
    }

    public ModeloTablaMaestroProductosFull(int opcion) {
        cc = new CProducto();
        this.nombreColumnas = columnas;
        registros = ((CProducto)cc).getRegistros();
    }

    public ModeloTablaMaestroProductosFull(String[] campo, Object[] valor) {
        cc = new CProducto();
        this.nombreColumnas = columnas;
        registros = ((CProducto)cc).getRegistros(campo,valor);
    }

    public ModeloTablaMaestroProductosFull(int inicio, int finalPag) {
        cc = new CProducto();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CProducto)cc).getRegistros(null);
    }
    public ModeloTablaMaestroProductosFull(int opcion, int inicio, int finalPag) {
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
            case 0: return ((ProductoFull)registros.get(rowIndex)).getTmpCoProducto();
            case 1: return ((ProductoFull)registros.get(rowIndex)).getTmpDeProducto();
            case 2: return ((ProductoFull)registros.get(rowIndex)).getTmpDeUnidadProducto();
            case 3: return ((ProductoFull)registros.get(rowIndex)).getTlbDeLaboratorio();
            default: return null;   
        }
    }

}
