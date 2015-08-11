package atux.modelgui;

import atux.modelbd.*;
import atux.util.common.AtuxUtility;
import java.util.ArrayList;

public class ModeloTablaSimple extends ModeloTabla{

    public final static int ACTIVOS     = 1;
    public final static int SALDOS      = 2;
    public final static int IMPRESORAS  = 3;
    public final static int CAJAS       = 4;
    public final static int REPOSICION  = 5;
    public final static int FALTA_CERO  = 6;
    public final static int CORRELATIVO = 7;
    public final static int LST_PED_REPOSICION      = 8;
    public final static int LST_DETPED_REPOSICION   = 9;
    public final static int LST_FORMA_PAGO_CREDITO  = 10;
    public final static int LST_TOTALES_PEDIDO_CREDITO  = 11;
    public final static int COLUMNA_DESCRIPCION = 1;

    String[] columnasSaldo = {"Código","Local","Saldo","Departamento","Provincia","Distrito"};

    public static final Integer[] anchoColumnasSaldo = {50,130,40,90,100,100};

    String[] columnasImpresoras = {"Nro","Caja","Tipo","Comprobante","Cola Impres.","Estado"};

    public static final Integer[] anchoColumnasImpresoras = {13,38,20,110,160,35};

    String[] columnasCajas = {"Caja","Orden","Indicador","Estado","Nro Caja"};

    public static final Integer[] anchoColumnasCajas = {25,20,40,40,25};

    String[] columnasPedidoReposicion = {"Cod.","Descripción","Unidad","Ca.Min","Ca.Max","Stock","NoPedir","Calculado","Solicitado","Transito","Compras Pendientes","Falta CERO"};

    public static final Integer[] anchoColumnasPedidoReposicion = {60,340,120,50,50,80,50,50,50,50,50,50};

    String[] columnasProdFaltaCero = {"Cod.","Descripción","Unidad","Ca.StockNoAtendido","Ca.StockCalculado","FeCrea"};

    public static final Integer[] anchoColumnasProdFaltaCero = {60,340,120,50,50,50};

    String[] columnasNumComprobante = {"Tip.Comprobante","Comprobante","Serie","Correlativo"};

    public static final Integer[] anchoColumnasNumComprobante = {30,100,30,60};

    String[] columnasPedidosReposicion = {"Nro.Pedido","Tipo.Pedido","Fec.Pedido","Items","MinDiasRep","MaxDiasRep","Rotación"};

    public static final Integer[] anchoColumnasPedidosReposicion = {60,50,80,60,40,40,40};

    String[] columnasDetPedidosReposicion = {"Nro.Pedido","Item","Producto","Descripción","Unidad","CaPedida","CaSugerida"};

    public static final Integer[] anchoColumnasDetPedidosReposicion = {50,25,30,140,60,30,30};

    String[] columnasFormaPagoPedCredito = {"Nro.Pedido","Fecha Ped.","Item","Forma Pago","Pagado","Vuelto","Descripción","Saldo Pendiente","Fecha Pago"};

    public static final Integer[] anchoColumnasFormaPagoPedCredito = {50,40,20,110,30,30,100,50,40};

    String[] columnasTotalesPedCredito = {"Cliente","RUC","Nombres","Monto total","Monto Crédito","Saldo Pendiente","Items"};

    public static final Integer[] anchoColumnasTotalesPedCredito = {40,50,100,40,40,40,20};

    private int tipoTabla;

    public ModeloTablaSimple(ArrayList lista, int tipoFiltro) {
        if(tipoFiltro==SALDOS){
            this.nombreColumnas = columnasSaldo;
        }
        else if(tipoFiltro==IMPRESORAS){
            this.nombreColumnas = columnasImpresoras;
        }
        else if(tipoFiltro==CAJAS){
            this.nombreColumnas = columnasCajas;
        }
        else if(tipoFiltro==REPOSICION){
            this.nombreColumnas = columnasPedidoReposicion;
        }
        else if(tipoFiltro==FALTA_CERO){
            this.nombreColumnas = columnasProdFaltaCero;
        }
        else if(tipoFiltro==CORRELATIVO){
            this.nombreColumnas = columnasNumComprobante;
        }
        else if(tipoFiltro==LST_PED_REPOSICION){
            this.nombreColumnas = columnasPedidosReposicion;
        }
        else if(tipoFiltro==LST_DETPED_REPOSICION){
            this.nombreColumnas = columnasDetPedidosReposicion;
        }
        else if(tipoFiltro==LST_FORMA_PAGO_CREDITO){
            this.nombreColumnas = columnasFormaPagoPedCredito;
        }
        else if(tipoFiltro==LST_TOTALES_PEDIDO_CREDITO){
            this.nombreColumnas = columnasTotalesPedCredito;
        }
        registros = lista;
        this.tipoTabla = tipoFiltro;
    }

    public int getCantidadRegistros()
    {
        return cc.getCantidadRegistros();
    }

    public void agregar(ProductoLocal prd)
    {
        this.registros.add(prd);
        this.fireTableRowsInserted(this.registros.size(), this.registros.size());
    }

    @Override
    public void quitarFila(int fila)
    {
        registros.remove(fila);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(tipoTabla)
        {
            case ACTIVOS:
                switch(columnIndex)
                {
                    case 0: return ((SimpleModelo)registros.get(rowIndex)).getNombre();
                    case 1: return ((SimpleModelo)registros.get(rowIndex)).getActivoString();
                }
                break;
            case SALDOS:
                switch(columnIndex)
                {
                    case 0:return ((Local)registros.get(rowIndex)).getCoLocal();
                    case 1:return ((Local)registros.get(rowIndex)).getDeLocal();
                    case 2:return ((Local)registros.get(rowIndex)).getSaldo();
                    case 3:return ((Local)registros.get(rowIndex)).getDeDepartamento();
                    case 4:return ((Local)registros.get(rowIndex)).getDeProvincia();
                    case 5:return ((Local)registros.get(rowIndex)).getDeDistrito();
                }
                break;
            case IMPRESORAS:
                switch(columnIndex)
                {
                    case 0:return ((CajaPago)registros.get(rowIndex)).getNuCaja();
                    case 1:return ((CajaPago)registros.get(rowIndex)).getDeCaja();
                    case 2:return ((CajaPago)registros.get(rowIndex)).getTiComprobante();
                    case 3:return ((CajaPago)registros.get(rowIndex)).getDeComprobante();
                    case 4:return ((CajaPago)registros.get(rowIndex)).getDeColaImpresion();
                    case 5:return ((CajaPago)registros.get(rowIndex)).getEstado();
                }
                break;
            case CAJAS:
                switch(columnIndex)
                {
                    case 0:return ((CajaPago)registros.get(rowIndex)).getDeCorta();
                    case 1:return ((CajaPago)registros.get(rowIndex)).getNuOrdenFila();
                    case 2:return ((CajaPago)registros.get(rowIndex)).getInTipoCaja();
                    case 3:return ((CajaPago)registros.get(rowIndex)).getEstado();
                    case 4:return ((CajaPago)registros.get(rowIndex)).getNuCaja();
                }
                break;
            case REPOSICION:
                switch(columnIndex)
                {
                    case 0:return ((ProductoLocal)registros.get(rowIndex)).getCoProducto();
                    case 1:return ((ProductoLocal)registros.get(rowIndex)).getDeProducto();
                    case 2:return ((ProductoLocal)registros.get(rowIndex)).getDeUnidadProducto();
                    case 3:return ((ProductoLocal)registros.get(rowIndex)).getCaStockMinimo();
                    case 4:return ((ProductoLocal)registros.get(rowIndex)).getCaStockMaximo();
                    case 5:return ((ProductoLocal)registros.get(rowIndex)).getStockEnCajas();
                    case 6:return ((ProductoLocal)registros.get(rowIndex)).getInProductoReponer();
                    case 7:return ((ProductoLocal)registros.get(rowIndex)).getCaStockReponerCalcula();
                    case 8 :return ((ProductoLocal)registros.get(rowIndex)).getCaStockReponer(); //CaReponer
                    case 9 :return " ";   //CaTransito
                    case 10:return " ";   //CaComprasPendientes
                }
                break;
            case FALTA_CERO:
                switch(columnIndex)
                {
                    case 0:return ((ProductoLocal)registros.get(rowIndex)).getCoProducto();
                    case 1:return ((ProductoLocal)registros.get(rowIndex)).getDeProducto();
                    case 2:return ((ProductoLocal)registros.get(rowIndex)).getDeUnidadProducto();
                    case 3:return ((ProductoLocal)registros.get(rowIndex)).getCaProdNoAtendido();
                    case 4:return ((ProductoLocal)registros.get(rowIndex)).getCaStockReponerCalcula();
                    case 5:return  AtuxUtility.getDateToString(((ProductoLocal)registros.get(rowIndex)).getFeCreaProdLocal(),"dd/MM/yyyy");
                }
                break;
            case CORRELATIVO:
                switch(columnIndex)
                {
                    case 0:return ((CajaPago)registros.get(rowIndex)).getTiComprobante();
                    case 1:return ((CajaPago)registros.get(rowIndex)).getDeComprobante();
                    case 2:return ((CajaPago)registros.get(rowIndex)).getNuSerie();
                    case 3:return ((CajaPago)registros.get(rowIndex)).getNuComprobante();
                }
                break;
            case LST_PED_REPOSICION:
                switch(columnIndex)
                {
                    case 0:return ((ListaPedidosReposicion)registros.get(rowIndex)).getNuPedidoProducto();
                    case 1:return ((ListaPedidosReposicion)registros.get(rowIndex)).getTiPedidoProducto();
                    case 2:return AtuxUtility.getDateToString(((ListaPedidosReposicion)registros.get(rowIndex)).getFeSolicitaPedido(), "dd/MM/yyyy");
                    case 3:return ((ListaPedidosReposicion)registros.get(rowIndex)).getCaTotalProducto();
                    case 4:return ((ListaPedidosReposicion)registros.get(rowIndex)).getNuDiasRotacion();
                    case 5:return ((ListaPedidosReposicion)registros.get(rowIndex)).getNuMinDiasReposicion();
                    case 6:return ((ListaPedidosReposicion)registros.get(rowIndex)).getNuMaxDiasReposicion();
                }
                break;
            case LST_DETPED_REPOSICION:
                switch(columnIndex)
                {
                    case 0:return ((ListaPedidosReposicion)registros.get(rowIndex)).getNuPedidoProducto();
                    case 1:return ((ListaPedidosReposicion)registros.get(rowIndex)).getNuItem();
                    case 2:return ((ListaPedidosReposicion)registros.get(rowIndex)).getCoProducto();
                    case 3:return ((ListaPedidosReposicion)registros.get(rowIndex)).getDeProducto();
                    case 4:return ((ListaPedidosReposicion)registros.get(rowIndex)).getDeUnidad();
                    case 5:return ((ListaPedidosReposicion)registros.get(rowIndex)).getCaPedida();
                    case 6:return ((ListaPedidosReposicion)registros.get(rowIndex)).getCaSugerida();
                }
                break;
            case LST_FORMA_PAGO_CREDITO:
                switch(columnIndex)
                {
                    case 0:return ((FormaPagoPedidoCredito)registros.get(rowIndex)).getNuPedido();
                    case 1:return AtuxUtility.getDateToString(((FormaPagoPedidoCredito)registros.get(rowIndex)).getFePedido(), "dd/MM/yyyy");
                    case 2:return ((FormaPagoPedidoCredito)registros.get(rowIndex)).getNuItemFormaPago();
                    case 3:return ((FormaPagoPedidoCredito)registros.get(rowIndex)).getDeFormaPago();
                    case 4:return ((FormaPagoPedidoCredito)registros.get(rowIndex)).getImTotalPago();
                    case 5:return ((FormaPagoPedidoCredito)registros.get(rowIndex)).getVaVuelto();
                    case 6:return ((FormaPagoPedidoCredito)registros.get(rowIndex)).getDeVentaCredito();
                    case 7:return ((FormaPagoPedidoCredito)registros.get(rowIndex)).getVaSaldo();
                    case 8:return AtuxUtility.getDateToString(((FormaPagoPedidoCredito)registros.get(rowIndex)).getFePago(), "dd/MM/yyyy");
                }
                break;
            case LST_TOTALES_PEDIDO_CREDITO:
                switch(columnIndex)
                {
                    case 0:return ((PedidoVenta)registros.get(rowIndex)).getCoClienteLocal();
                    case 1:return ((PedidoVenta)registros.get(rowIndex)).getNuRucCliente();
                    case 2:return ((PedidoVenta)registros.get(rowIndex)).getNoImpresoComprobante();
                    case 3:return ((PedidoVenta)registros.get(rowIndex)).getVaTotalPrecioVenta();
                    case 4:return ((PedidoVenta)registros.get(rowIndex)).getVaTotalVenta();
                    case 5:return ((PedidoVenta)registros.get(rowIndex)).getVaSaldoRedondeo();
                    case 6:return ((PedidoVenta)registros.get(rowIndex)).getCaItem();
                }
                break;
            default: return null;
        }
        return null;
    }
}