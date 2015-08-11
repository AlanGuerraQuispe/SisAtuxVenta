package atux.modelgui;

import atux.controllers.CFactores;
import atux.modelbd.Factores;

public class ModeloTablaFactores extends ModeloTabla{

    String[] columnas = {"Código","Descripción","Valor","Estado"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {140,600,200,300};
    
    public ModeloTablaFactores() {
        cc = new CFactores();
        this.nombreColumnas = columnas;       
        registros = ((CFactores)cc).getRegistros();
    }
    
    public ModeloTablaFactores(int opcion) {
        cc = new CFactores();
        this.nombreColumnas = columnas;       
        registros = ((CFactores)cc).getRegistros();
    }
    
    public ModeloTablaFactores(String[] campo,Object[] valor) {
        cc = new CFactores();
        this.nombreColumnas = columnas;       
        registros = ((CFactores)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaFactores(int inicio,int finalPag) {
        cc = new CFactores();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CFactores)cc).getRegistros(null);
    }
    public ModeloTablaFactores(int opcion,int inicio,int finalPag) {
        cc = new CFactores();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CFactores)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CFactores)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CFactores)cc).getRegistros(null);
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
            case 0: return ((Factores)registros.get(rowIndex)).getCoFactorPrecio();
            case 1: return ((Factores)registros.get(rowIndex)).getDeFactorPrecio();     
            case 2: return ((Factores)registros.get(rowIndex)).getPcFactorPrecio();  
            case 3: return ((Factores)registros.get(rowIndex)).getEsFactorPrecio();  
            default: return null;   
        }
    }
    
}
