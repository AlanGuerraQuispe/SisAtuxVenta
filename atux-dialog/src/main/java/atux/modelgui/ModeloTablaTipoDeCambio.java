package atux.modelgui;

import atux.controllers.CTipoDeCambio;
import atux.modelbd.TipoDeCambio;

public class ModeloTablaTipoDeCambio extends ModeloTabla{

    String[] columnas = {"Moneda","Secuencial","Fecha","Valor"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {140,300,600,200};
    
    public ModeloTablaTipoDeCambio() {
        cc = new CTipoDeCambio();
        this.nombreColumnas = columnas;       
        registros = ((CTipoDeCambio)cc).getRegistros();
    }
    
    public ModeloTablaTipoDeCambio(int opcion) {
        cc = new CTipoDeCambio();
        this.nombreColumnas = columnas;       
        registros = ((CTipoDeCambio)cc).getRegistros();
    }
    
    public ModeloTablaTipoDeCambio(String[] campo,Object[] valor) {
        cc = new CTipoDeCambio();
        this.nombreColumnas = columnas;       
        registros = ((CTipoDeCambio)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaTipoDeCambio(int inicio,int finalPag) {
        cc = new CTipoDeCambio();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CTipoDeCambio)cc).getRegistros(null);
    }
    public ModeloTablaTipoDeCambio(int opcion,int inicio,int finalPag) {
        cc = new CTipoDeCambio();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CTipoDeCambio)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CTipoDeCambio)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CTipoDeCambio)cc).getRegistros(null);
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
            case 0: return ((TipoDeCambio)registros.get(rowIndex)).getCoMoneda();
            case 1: return ((TipoDeCambio)registros.get(rowIndex)).getNuSecTipoCambio();     
            case 2: return ((TipoDeCambio)registros.get(rowIndex)).getFeTipoCambio();  
            case 3: return ((TipoDeCambio)registros.get(rowIndex)).getVaCambioVentaInka();  
            default: return null;   
        }
    }
    
}
