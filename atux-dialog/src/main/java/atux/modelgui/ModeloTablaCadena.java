package atux.modelgui;

import atux.controllers.CCadena;
import atux.modelbd.Cadena;

public class ModeloTablaCadena extends ModeloTabla{

    String[] columnas = {"Codigo","Descripción","Estado"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {150,450,250};
    
    public ModeloTablaCadena() {
        cc = new CCadena();
        this.nombreColumnas = columnas;       
        registros = ((CCadena)cc).getRegistros();
    }
    
    public ModeloTablaCadena(int opcion) {
        cc = new CCadena();
        this.nombreColumnas = columnas;       
        registros = ((CCadena)cc).getRegistros();
    }
    
    public ModeloTablaCadena(String[] campo,Object[] valor) {
        cc = new CCadena();
        this.nombreColumnas = columnas;       
        registros = ((CCadena)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaCadena(int inicio,int finalPag) {
        cc = new CCadena();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CCadena)cc).getRegistros();
    }


    public ModeloTablaCadena(String Filtro) {
        cc = new CCadena();
        this.nombreColumnas = columnas;       
        registros = ((CCadena)cc).getRegistros(Filtro);
    }
    
    public ModeloTablaCadena(int opcion,int inicio,int finalPag) {
        cc = new CCadena();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CCadena)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CCadena)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CCadena)cc).getRegistros();
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
            case 0: return ((Cadena)registros.get(rowIndex)).getCoCadena();
            case 1: return ((Cadena)registros.get(rowIndex)).getDeCadena();
            case 2: return ((Cadena)registros.get(rowIndex)).getEsCadena().equals("A") ? "Activo" : "Inactivo";
            default: return null;   

        }
    }
    
}
