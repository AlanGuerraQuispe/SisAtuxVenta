package atux.modelgui;

import atux.controllers.CPaises;
import atux.modelbd.Paises;

public class ModeloTablaPaises extends ModeloTabla{

    String[] columnas = {"CODIGO", "DESCRIPCION", "ABREVIATURA"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {140,600,200};
    
    public ModeloTablaPaises() {
        cc = new CPaises();
        this.nombreColumnas = columnas;       
        registros = ((CPaises)cc).getRegistros();
    }
    
    public ModeloTablaPaises(int opcion) {
        cc = new CPaises();
        this.nombreColumnas = columnas;       
        registros = ((CPaises)cc).getRegistros();
    }
    
    public ModeloTablaPaises(String[] campo,Object[] valor) {
        cc = new CPaises();
        this.nombreColumnas = columnas;       
        registros = ((CPaises)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaPaises(int inicio,int finalPag) {
        cc = new CPaises();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CPaises)cc).getRegistros(null);
    }
    public ModeloTablaPaises(int opcion,int inicio,int finalPag) {
        cc = new CPaises();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CPaises)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CPaises)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CPaises)cc).getRegistros(null);
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
            case 0: return ((Paises)registros.get(rowIndex)).getCoPais();
            case 1: return ((Paises)registros.get(rowIndex)).getDePais();     
            case 2: return ((Paises)registros.get(rowIndex)).getDeCortaPais();  
            default: return null;   
        }
    }
}
