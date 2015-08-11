package atux.modelgui;

import atux.modelbd.ComprobantePago;
import atux.util.common.AtuxUtility;
import java.util.ArrayList;

public class ModeloTablaComprobantePago extends ModeloTabla{

    private Class[] tipoColumnas;    
        
    public static final Integer[] anchoColumnas  = {40,10,60,100,80,35,22,
                                                    10,40,22};            

    public ModeloTablaComprobantePago(ArrayList<ComprobantePago> comprobantes) {
        this.registros = comprobantes;
        
        this.nombreColumnas = new String[]{"Correlativo","Tipo","Comprobante","Cliente","Fecha","%SubTotal","Dscto",
                                           "IGV","Redondeo","Total"};
        
        tipoColumnas = new Class[]{String.class,String.class,String.class,String.class,String.class,Double.class,Double.class,
                                   Double.class,Double.class,Double.class};
    }    

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return tipoColumnas[columnIndex];
    }                    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         switch(columnIndex)
           {             
                case 0:return ((ComprobantePago)registros.get(rowIndex)).getNuPedido(); 
                case 1:return ((ComprobantePago)registros.get(rowIndex)).getTiComprobante(); 
                case 2:return ((ComprobantePago)registros.get(rowIndex)).getNuComprobantePago(); 
                case 3:return ((ComprobantePago)registros.get(rowIndex)).getNoImpresoCliente(); 
                case 4:return  AtuxUtility.getDateToString(((ComprobantePago)registros.get(rowIndex)).getFeComprobante(),"dd/MM/yyyy");         
                case 5:return ((ComprobantePago)registros.get(rowIndex)).getVaTotalVenta();
                case 6:return ((ComprobantePago)registros.get(rowIndex)).getVaTotalDescuento();
                case 7:return ((ComprobantePago)registros.get(rowIndex)).getVaTotalImpuesto();
                case 8:return ((ComprobantePago)registros.get(rowIndex)).getVaSaldoRedondeo();        
                case 9:return ((ComprobantePago)registros.get(rowIndex)).getVaTotalPrecioVenta();                      
            default:return null;    
        }       
    }   
        
}
