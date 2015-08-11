package atux.modelgui;

import atux.controllers.CParametroReposicion;
import atux.modelbd.ParametroReposicion;
import atux.util.common.AtuxUtility;

public class ModeloTablaParametroReposicion extends ModeloTabla{

    String[] columnas = {"Secuencial","Minimo","Maximo","Periodo","Inicio","Fin"};
    public static final String[] FILTRO_GRID_SIN_ESTADO ={"CO_COMPANIA","CO_CODIGO","TI_PARAMETRO"};
    public static final String[] FILTRO_GRID_CON_ESTADO ={"CO_COMPANIA","CO_CODIGO","TI_PARAMETRO","ES_PARAMETRO"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {150,150,150,150,200,200};
    
    public ModeloTablaParametroReposicion() {
        cc = new CParametroReposicion();
        this.nombreColumnas = columnas;       
        registros = ((CParametroReposicion)cc).getRegistros();
    }
    
    public ModeloTablaParametroReposicion(int opcion) {
        cc = new CParametroReposicion();
        this.nombreColumnas = columnas;       
        registros = ((CParametroReposicion)cc).getRegistros();
    }
    
    public ModeloTablaParametroReposicion(String[] campo,Object[] valor) {
        cc = new CParametroReposicion();
        this.nombreColumnas = columnas;       
        registros = ((CParametroReposicion)cc).getRegistros(campo,valor);
    }

    public ModeloTablaParametroReposicion(Object[] valor, String opcion) {
        cc = new CParametroReposicion();
        this.nombreColumnas = columnas;
        if ("T".equals(opcion)){
            registros = ((CParametroReposicion)cc).getRegistros(FILTRO_GRID_SIN_ESTADO,valor);
        }else{
            registros = ((CParametroReposicion)cc).getRegistros(FILTRO_GRID_CON_ESTADO,valor);
        }
    }

    
    
    public ModeloTablaParametroReposicion(int inicio,int finalPag) {
        cc = new CParametroReposicion();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CParametroReposicion)cc).getRegistros(null);
    }
    
    
    public ModeloTablaParametroReposicion(int opcion,int inicio,int finalPag) {
        cc = new CParametroReposicion();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CParametroReposicion)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CParametroReposicion)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CParametroReposicion)cc).getRegistros(null);
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
            case 0: return ((ParametroReposicion)registros.get(rowIndex)).getNuSecuencia();
            case 1: return ((ParametroReposicion)registros.get(rowIndex)).getNuMinimo();     
            case 2: return ((ParametroReposicion)registros.get(rowIndex)).getNuMaximo();  
            case 3: return ((ParametroReposicion)registros.get(rowIndex)).getNuPeriodo(); 
            case 4: return AtuxUtility.getDateToString(((ParametroReposicion)registros.get(rowIndex)).getFeInicio(), "dd/MM/yyyy");  
            case 5: return AtuxUtility.getDateToString(((ParametroReposicion)registros.get(rowIndex)).getFeFinal(), "dd/MM/yyyy");    
            default: return null;   
        }
    }
    
}
