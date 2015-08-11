package atux.modelgui;

import atux.controllers.CLocales;
import atux.modelbd.Locales;
import atux.util.common.AtuxUtility;

public class ModeloTablaLocales extends ModeloTabla{

    String[] columnas = {"Codigo","Descripción","Fecha Apertura","Estado"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {140,600,300,200};
    
    public ModeloTablaLocales() {
        cc = new CLocales();
        this.nombreColumnas = columnas;       
        registros = ((CLocales)cc).getRegistros();
    }
    
    public ModeloTablaLocales(int opcion) {
        cc = new CLocales();
        this.nombreColumnas = columnas;       
        registros = ((CLocales)cc).getRegistros();
    }
    
    public ModeloTablaLocales(String[] campo,Object[] valor) {
        cc = new CLocales();
        this.nombreColumnas = columnas;       
        registros = ((CLocales)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaLocales(int inicio,int finalPag) {
        cc = new CLocales();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CLocales)cc).getRegistros(null);
    }
    public ModeloTablaLocales(String Filtro) {
        cc = new CLocales();
        this.nombreColumnas = columnas;
        registros = ((CLocales)cc).getBusquedaLocales(Filtro);
    }        
    public ModeloTablaLocales(int opcion,int inicio,int finalPag) {
        cc = new CLocales();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CLocales)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CLocales)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CLocales)cc).getRegistros(null);
        }
        
    }
    
    public int getCantidadRegistros(){
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
            case 0: return ((Locales)registros.get(rowIndex)).getCoLocal();
            case 1: return ((Locales)registros.get(rowIndex)).getDeLocal();     
            case 2: return AtuxUtility.getDateToString(((Locales)registros.get(rowIndex)).getFeApertura(), "dd/mm/yyyy");
            case 3: return ((Locales)registros.get(rowIndex)).getEsLocal().equals("A") ? "Activo" : "Inactivo";  
            default: return null;   
        }
    }
    
}
