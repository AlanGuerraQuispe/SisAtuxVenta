package atux.modelgui;

import atux.controllers.CCodigoBarra;
import atux.modelbd.CodigoBarra;
import atux.util.common.AtuxUtility;

public class ModeloTablaCodigoBarra extends ModeloTabla{

    String[] columnas = {"Barra","Estado","Fecha"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {600,200,300};
    
    public ModeloTablaCodigoBarra() {
        cc = new CCodigoBarra();
        this.nombreColumnas = columnas;       
        registros = ((CCodigoBarra)cc).getRegistros();
    }
    
    public ModeloTablaCodigoBarra(int opcion) {
        cc = new CCodigoBarra();
        this.nombreColumnas = columnas;       
        registros = ((CCodigoBarra)cc).getRegistros();
    }
    
    public ModeloTablaCodigoBarra(String[] campo,Object[] valor) {
        cc = new CCodigoBarra();
        this.nombreColumnas = columnas;       
        registros = ((CCodigoBarra)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaCodigoBarra(int inicio,int finalPag) {
        cc = new CCodigoBarra();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CCodigoBarra)cc).getRegistros(null);
    }
    public ModeloTablaCodigoBarra(int opcion,int inicio,int finalPag) {
        cc = new CCodigoBarra();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CCodigoBarra)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CCodigoBarra)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CCodigoBarra)cc).getRegistros(null);
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
            case 0: return ((CodigoBarra)registros.get(rowIndex)).getCoBarra();
            case 1: return ((CodigoBarra)registros.get(rowIndex)).getEsProdCodBarra();     
            case 2: return AtuxUtility.getDateToString(((CodigoBarra)registros.get(rowIndex)).getFeCreaProdCodBarra(), "dd/MM/yyyy");  
            default: return null;   
        }
    }
    
}
