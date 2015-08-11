package atux.modelgui;

import atux.controllers.CClienteCompania;
import atux.modelbd.ClienteCompania;


public class ModeloTablaClienteCompania extends ModeloTabla{

    String[] columnas = {"Codigo","Razon Social","RUC"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {100,700,400};
    
    public ModeloTablaClienteCompania() {
        cc = new CClienteCompania();
        this.nombreColumnas = columnas;       
        registros = ((CClienteCompania)cc).getRegistros();
    }
    
    public ModeloTablaClienteCompania(int opcion) {
        cc = new CClienteCompania();
        this.nombreColumnas = columnas;       
        registros = ((CClienteCompania)cc).getRegistros();
    }
    
    public ModeloTablaClienteCompania(String[] campo,Object[] valor) {
        cc = new CClienteCompania();
        this.nombreColumnas = columnas;       
        registros = ((CClienteCompania)cc).getRegistros(campo,valor);
    }

    public ModeloTablaClienteCompania(String Condicion) {
        cc = new CClienteCompania();
        this.nombreColumnas = columnas;       
        registros = ((CClienteCompania)cc).getRegistros(Condicion);
    }

    
    public ModeloTablaClienteCompania(int inicio,int finalPag) {
        cc = new CClienteCompania();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CClienteCompania)cc).getRegistros("");
    }
    public ModeloTablaClienteCompania(int opcion,int inicio,int finalPag) {
        cc = new CClienteCompania();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CClienteCompania)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CClienteCompania)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CClienteCompania)cc).getRegistros("");
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
            case 0: return ((ClienteCompania)registros.get(rowIndex)).getCoClienteCompania();
            case 1: return ((ClienteCompania)registros.get(rowIndex)).getDeRazonSocial();     
            case 2: return ((ClienteCompania)registros.get(rowIndex)).getNuRucCliente();  
            default: return null; 
        }
    }
    
}
