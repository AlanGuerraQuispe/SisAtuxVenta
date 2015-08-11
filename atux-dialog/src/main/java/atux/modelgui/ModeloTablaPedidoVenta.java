package atux.modelgui;

import atux.modelbd.PedidoVenta;
import atux.util.common.AtuxUtility;
import java.util.ArrayList;


public class ModeloTablaPedidoVenta extends ModeloTabla{

    public static final Integer[] anchoColumnas  = {18,51,50,40,130,130,35,22,10,28,20};

    public final static int CONSULTA_PEDIDO_NORMAL  = 1;
    public final static int CONSULTA_PEDIDO_CREDITO = 2;
    private int tipoTabla;

    public ModeloTablaPedidoVenta(ArrayList<PedidoVenta> pedidos,int tipo) {
        if(tipo==CONSULTA_PEDIDO_NORMAL){
            this.nombreColumnas = new String[]{"Nro Ped.","Correlativo","Estado","Fecha Ped.","Vendedor","Cliente","%SubTotal","Dscto",
                    "IGV","Redondeo","Total"};
        }
        else if(tipo==CONSULTA_PEDIDO_CREDITO){
            this.nombreColumnas = new String[]{"Nro Ped.","Correlativo","Estado","Fecha Ped.","Vendedor","Cliente","Dscto","IGV",
                    "Redondeo","Total","Credito"};
        }

        this.registros = pedidos;
        this.tipoTabla = tipo;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(tipoTabla)
        {
            case CONSULTA_PEDIDO_NORMAL:
                switch(columnIndex)
                {
                    case 0:return ((PedidoVenta)registros.get(rowIndex)).getNuPedidoDiario();
                    case 1:return ((PedidoVenta)registros.get(rowIndex)).getNuPedido();
                    case 2:return ((PedidoVenta)registros.get(rowIndex)).getEsPedidoVenta();
                    case 3:return  AtuxUtility.getDateToString(((PedidoVenta)registros.get(rowIndex)).getFePedido(),"dd/MM/yyyy");
                    case 4:return ((PedidoVenta)registros.get(rowIndex)).getUsuario().getNombreCompleto();
                    case 5:return ((PedidoVenta)registros.get(rowIndex)).getNoImpresoComprobante();
                    case 6:return ((PedidoVenta)registros.get(rowIndex)).getVaTotalVenta();
                    case 7:return ((PedidoVenta)registros.get(rowIndex)).getVaTotalDescuento();
                    case 8:return ((PedidoVenta)registros.get(rowIndex)).getVaTotalImpuesto();
                    case 9:return ((PedidoVenta)registros.get(rowIndex)).getVaSaldoRedondeo();
                    case 10:return ((PedidoVenta)registros.get(rowIndex)).getVaTotalPrecioVenta();
                }
                break;
            case CONSULTA_PEDIDO_CREDITO:
                switch(columnIndex)
                {
                    case 0:return ((PedidoVenta)registros.get(rowIndex)).getNuPedidoDiario();
                    case 1:return ((PedidoVenta)registros.get(rowIndex)).getNuPedido();
                    case 2:return ((PedidoVenta)registros.get(rowIndex)).getEsPedidoVenta();
                    case 3:return  AtuxUtility.getDateToString(((PedidoVenta)registros.get(rowIndex)).getFePedido(),"dd/MM/yyyy");
                    case 4:return ((PedidoVenta)registros.get(rowIndex)).getUsuario().getNombreCompleto();
                    case 5:return ((PedidoVenta)registros.get(rowIndex)).getNoImpresoComprobante();
                    case 6:return ((PedidoVenta)registros.get(rowIndex)).getVaTotalDescuento();
                    case 7:return ((PedidoVenta)registros.get(rowIndex)).getVaTotalImpuesto();
                    case 8:return ((PedidoVenta)registros.get(rowIndex)).getVaSaldoRedondeo();
                    case 9:return ((PedidoVenta)registros.get(rowIndex)).getVaTotalPrecioVenta();
                    case 10:return ((PedidoVenta)registros.get(rowIndex)).getMontoCredito();
                }
                break;
            default: return null;
        }
        return null;
    }
}
