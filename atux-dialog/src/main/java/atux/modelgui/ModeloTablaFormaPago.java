package atux.modelgui;

import atux.controllers.CFormaPago;
import atux.modelbd.FormaPago;

public class ModeloTablaFormaPago extends ModeloTabla{

    String[] columnas = {"Código","Descripción","Estado","Tipo"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {140,600,200,300};
    
    public ModeloTablaFormaPago() {
        cc = new CFormaPago();
        this.nombreColumnas = columnas;       
        registros = ((CFormaPago)cc).getRegistros();
    }
    
    public ModeloTablaFormaPago(int opcion) {
        cc = new CFormaPago();
        this.nombreColumnas = columnas;       
        registros = ((CFormaPago)cc).getRegistros();
    }
    
    public ModeloTablaFormaPago(String[] campo,Object[] valor) {
        cc = new CFormaPago();
        this.nombreColumnas = columnas;       
        registros = ((CFormaPago)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaFormaPago(int inicio,int finalPag) {
        cc = new CFormaPago();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CFormaPago)cc).getRegistros(null);
    }
    public ModeloTablaFormaPago(int opcion,int inicio,int finalPag) {
        cc = new CFormaPago();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CFormaPago)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CFormaPago)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CFormaPago)cc).getRegistros(null);
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
            case 0: return ((FormaPago)registros.get(rowIndex)).getCoFormaPago();
            case 1: return ((FormaPago)registros.get(rowIndex)).getDeFormaPago();     
            case 2: return ((FormaPago)registros.get(rowIndex)).getEsFormaPago();  
            case 3: if("D".equals(((FormaPago)registros.get(rowIndex)).getTiTransaccionTarjeta())){
                        return "DELIVERY";
                    }else if("P".equals(((FormaPago)registros.get(rowIndex)).getTiTransaccionTarjeta())){
                        return "POS";
                    }else if("M".equals(((FormaPago)registros.get(rowIndex)).getTiTransaccionTarjeta())){
                        return "MANUAL";
                    }else {
                        return ((FormaPago)registros.get(rowIndex)).getTiTransaccionTarjeta();  
                    }
            default: return null;
        }
    }
}
