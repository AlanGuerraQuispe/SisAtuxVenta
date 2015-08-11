package atux.modelgui;

import atux.controllers.CProductoLote;
import atux.modelbd.ProductoLote;
import atux.util.common.AtuxUtility;

public class ModeloTablaProductoLote extends ModeloTabla{

    String[] columnas = {"Lote","Fecha Ingreso","Fecha Vencimiento","Estado"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {400,300,300,200};
    
    public ModeloTablaProductoLote() {
        cc = new CProductoLote();
        this.nombreColumnas = columnas;       
        registros = ((CProductoLote)cc).getRegistros();
    }
    
    public ModeloTablaProductoLote(int opcion) {
        cc = new CProductoLote();
        this.nombreColumnas = columnas;       
        registros = ((CProductoLote)cc).getRegistros();
    }
    
    public ModeloTablaProductoLote(String[] campo,Object[] valor) {
        cc = new CProductoLote();
        this.nombreColumnas = columnas;       
        registros = ((CProductoLote)cc).getRegistros(campo,valor);
    }

//    public ModeloTablaProductoLote(String Filtro) {
//        cc = new CProductoLote();
//        this.nombreColumnas = columnas;       
//        registros = ((CProductoLote)cc).getRegistros(Filtro);
//    }

    public ModeloTablaProductoLote(int inicio,int finalPag) {
        cc = new CProductoLote();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CProductoLote)cc).getRegistros();
    }

    public ModeloTablaProductoLote(int opcion,int inicio,int finalPag) {
        cc = new CProductoLote();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CProductoLote)cc).getRegistros(new Object[]{"I"});
                break;
            case 1:
                registros = ((CProductoLote)cc).getRegistros(new Object[]{"A"});
                break;
            default:
                registros = ((CProductoLote)cc).getRegistros();
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
            case 0: return ((ProductoLote)registros.get(rowIndex)).getNuLote();
            case 1: return AtuxUtility.getDateToString(((ProductoLote)registros.get(rowIndex)).getFeIngreso(), "dd/MM/yyyy");
            case 2: return AtuxUtility.getDateToString(((ProductoLote)registros.get(rowIndex)).getFeVencimiento(), "dd/MM/yyyy");
            case 3: if ("A".equals(((ProductoLote)registros.get(rowIndex)).getEsLote())){
                        return "Activo";
                    }else{
                        return "Inactivo";
                    }
            default: return null;   
                
        }
    }
}
