package atux.modelgui;

import atux.controllers.CCompania;
import atux.modelbd.Compania;

public class ModeloTablaCompania extends ModeloTabla{

    String[] columnas = {"Codigo","Descripción","Direccion","Estado"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {150,250,250,300};
    
    public ModeloTablaCompania() {
        cc = new CCompania();
        this.nombreColumnas = columnas;       
        registros = ((CCompania)cc).getRegistros();
    }
    
    public ModeloTablaCompania(int opcion) {
        cc = new CCompania();
        this.nombreColumnas = columnas;       
        registros = ((CCompania)cc).getRegistros();
    }
    
    public ModeloTablaCompania(String[] campo,Object[] valor) {
        cc = new CCompania();
        this.nombreColumnas = columnas;       
        registros = ((CCompania)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaCompania(int inicio,int finalPag) {
        cc = new CCompania();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CCompania)cc).getRegistros();
    }


    public ModeloTablaCompania(String Filtro) {
        cc = new CCompania();
        this.nombreColumnas = columnas;       
        registros = ((CCompania)cc).getRegistros(Filtro);
    }
    
    public ModeloTablaCompania(int opcion,int inicio,int finalPag) {
        cc = new CCompania();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CCompania)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CCompania)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CCompania)cc).getRegistros();
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
            case 0: return ((Compania)registros.get(rowIndex)).getCoCompania();
            case 1: return ((Compania)registros.get(rowIndex)).getDeCompania();
            case 2: return ((Compania)registros.get(rowIndex)).getDeDireccion();
            case 3: return ((Compania)registros.get(rowIndex)).getEsCompania().equals("A") ? "Activo" : "Inactivo";
            default: return null;   

        }
    }
    
}
