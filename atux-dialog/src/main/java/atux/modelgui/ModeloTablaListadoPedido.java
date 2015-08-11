package atux.modelgui;

import atux.modelbd.ListadoPedido;
import java.util.ArrayList;


public class ModeloTablaListadoPedido extends ModeloTabla{

    private Class[] tipoColumnas;    
        
    public static final Integer[] anchoColumnas  = {10,40,80,90,10,200,50,50};

    public ModeloTablaListadoPedido(ArrayList<ListadoPedido> pedidos) {
        this.registros = pedidos;

        this.nombreColumnas = new String[]{"#Pedido","#Correlativo","Fecha","Estado","Caja","Vendedor","#Sunat","Venta"};
        
        tipoColumnas = new Class[]{String.class,String.class,String.class,String.class,String.class,String.class,String.class,Double.class};
    } 

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return tipoColumnas[columnIndex];
    }                    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         switch(columnIndex)
           {
                case 0:return ((ListadoPedido)registros.get(rowIndex)).getnuPedidoDiario();
                case 1:return ((ListadoPedido)registros.get(rowIndex)).getnuPedido();
                case 2:return ((ListadoPedido)registros.get(rowIndex)).getfePedido(); 
                case 3:return ((ListadoPedido)registros.get(rowIndex)).getesPedidoVenta();
                case 4:return ((ListadoPedido)registros.get(rowIndex)).getnuCaja(); 
                case 5:return ((ListadoPedido)registros.get(rowIndex)).getdeVendedor();
                case 6:return ((ListadoPedido)registros.get(rowIndex)).getnuComprobantePago(); 
                case 7:return ((ListadoPedido)registros.get(rowIndex)).getvaTotalPrecioVenta();

            default:return null;    
        }       
    }       
}
