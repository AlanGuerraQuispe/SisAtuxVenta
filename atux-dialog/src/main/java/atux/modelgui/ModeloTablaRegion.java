package atux.modelgui;

import atux.controllers.CRegion;
import atux.modelbd.Region;

public class ModeloTablaRegion extends ModeloTabla{

    String[] columnas = {"Codigo","Descripción","Estado"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {150,650,250};
    
    public ModeloTablaRegion() {
        cc = new CRegion();
        this.nombreColumnas = columnas;       
        registros = ((CRegion)cc).getRegistros();
    }
    
    public ModeloTablaRegion(int opcion) {
        cc = new CRegion();
        this.nombreColumnas = columnas;       
        registros = ((CRegion)cc).getRegistros();
    }
    
    public ModeloTablaRegion(String[] campo,Object[] valor) {
        cc = new CRegion();
        this.nombreColumnas = columnas;       
        registros = ((CRegion)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaRegion(int inicio,int finalPag) {
        cc = new CRegion();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CRegion)cc).getRegistros();
    }


    public ModeloTablaRegion(String Filtro) {
        cc = new CRegion();
        this.nombreColumnas = columnas;       
        registros = ((CRegion)cc).getRegistros(Filtro);
    }
    
    public ModeloTablaRegion(int opcion,int inicio,int finalPag) {
        cc = new CRegion();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CRegion)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CRegion)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CRegion)cc).getRegistros();
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
            case 0: return ((Region)registros.get(rowIndex)).getCoRegion();
            case 1: return ((Region)registros.get(rowIndex)).getDeRegion();
            case 2: return ((Region)registros.get(rowIndex)).getEsRegion().equals("A") ? "Activo" : "Inactivo";
            default: return null;   

        }
    }
    
}
