package atux.util.common;

import javax.swing.JLabel;


public class ConstantsTables {
  
  public ConstantsTables() {
  }
  
  public static final Object[] defaultValuesPagos = {" "," ", " ", " ", " ", " ", "", "", "", "", "", "", "", "", "", "", "", "", "","","","","","","","","",""};
  
  public static final AtuxColumnData columnsPagos[] = {
            new AtuxColumnData("Cod.", 60, JLabel.CENTER),
            new AtuxColumnData("Forma de Pago", 177, JLabel.LEFT),
            new AtuxColumnData("Moneda", 80, JLabel.LEFT),
            new AtuxColumnData("Monto", 76, JLabel.RIGHT),
            new AtuxColumnData("Total S/.", 76, JLabel.RIGHT),
            new AtuxColumnData("CoMoneda", 0, JLabel.LEFT),
            new AtuxColumnData("CoPago", 0, JLabel.LEFT),
            new AtuxColumnData("Tarjeta", 0, JLabel.LEFT),
            new AtuxColumnData("Vencimiento", 0, JLabel.LEFT),
            new AtuxColumnData("TitularPaterno", 0, JLabel.LEFT),
            new AtuxColumnData("TitularMaterno", 0, JLabel.LEFT),
            new AtuxColumnData("TitularNombres", 0, JLabel.LEFT),
            new AtuxColumnData("TitularTipoDocumento", 0, JLabel.LEFT),
            new AtuxColumnData("TitularNumeroDocumento", 0, JLabel.LEFT),
            new AtuxColumnData("Cuotas", 0, JLabel.LEFT),
            new AtuxColumnData("InPagoDiferido", 0, JLabel.LEFT),
            new AtuxColumnData("InOrigenFormaPago", 0, JLabel.LEFT),
            new AtuxColumnData("NuItem", 0, JLabel.LEFT),
            new AtuxColumnData("Pago Bono", 0, JLabel.CENTER),
            new AtuxColumnData("Apertura Gaveta", 0, JLabel.CENTER),
            // Inicio ID: 002
            new AtuxColumnData("Id Transaccion", 0, JLabel.CENTER),
            new AtuxColumnData("Cod Comercio", 0, JLabel.CENTER),
            new AtuxColumnData("Terminal", 0, JLabel.CENTER),
            new AtuxColumnData("Lote", 0, JLabel.CENTER),
            new AtuxColumnData("Referencia", 0, JLabel.CENTER),
            new AtuxColumnData("Aprobacion", 0, JLabel.CENTER),
            new AtuxColumnData("Refrendo", 0, JLabel.CENTER),
            new AtuxColumnData("Voucher", 0, JLabel.CENTER)
            // Fin ID: 002
    };    
  
  public static final Object[] defaultValuesListaPendientes = {" ", " ", " ", " ", " ", "", ""};
  
  public static final Object[] defaultValuesListaPedidosPendientes = {" ", " ", " ", " ", " ", " ", " ", " ", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};      
  
  public static final AtuxColumnData columnsListaPedidosPendientes[] = {
            new AtuxColumnData("Pedido", 50, JLabel.CENTER),
            new AtuxColumnData("Fecha", 110, JLabel.CENTER),
            new AtuxColumnData("Total S/.", 60, JLabel.RIGHT),
            new AtuxColumnData("Total USD", 60, JLabel.RIGHT),
            new AtuxColumnData("R.U.C.", 80, JLabel.CENTER),
            new AtuxColumnData("Cliente", 145, JLabel.LEFT),
            new AtuxColumnData("Vendedor", 145, JLabel.LEFT),
            new AtuxColumnData("Convenio", 100, JLabel.LEFT),
            new AtuxColumnData("NU_PEDIDO", 50, JLabel.LEFT),
            new AtuxColumnData("VA_TASA_CAMBIO", 20, JLabel.LEFT),
            new AtuxColumnData("TI_COMPROBANTE", 20, JLabel.LEFT),
            new AtuxColumnData("CO_CLIENTE_LOCAL_FACT", 0, JLabel.LEFT),
            new AtuxColumnData("NU_RUC_CLIENTE", 0, JLabel.LEFT),
            new AtuxColumnData("NO_IMPRESO_COMPROBANTE", 50, JLabel.LEFT),
            new AtuxColumnData("DE_DIRECCION_COMROBANTE", 50, JLabel.LEFT),
            new AtuxColumnData("CO_FORMA_PAGO_CONVENIO", 0, JLabel.LEFT),
            new AtuxColumnData("CO_PAGO", 0, JLabel.LEFT),
            new AtuxColumnData("PC_DCTO_CLIENTE_ESPECIAL", 0, JLabel.LEFT),
            new AtuxColumnData("TI_PEDIDO", 0, JLabel.LEFT),
            new AtuxColumnData("VA_MONTO_COPAGO_CLIENTE", 0, JLabel.RIGHT),
            new AtuxColumnData("TI_ORDEN_COPAGO", 0, JLabel.LEFT),
            //Inicio ID: 001
            new AtuxColumnData("VA_TOTAL_INKCLUB", 0, JLabel.LEFT),
            new AtuxColumnData("REDONDEO_INKCLUB", 0, JLabel.LEFT)
            //Inicio ID: 002
    };
  
    
  public static final AtuxColumnData columnsPedidoProductos[] = {
            new AtuxColumnData("Código", 50, JLabel.CENTER),
            new AtuxColumnData("Producto", 230, JLabel.LEFT),
            new AtuxColumnData("Unidad", 90, JLabel.LEFT),
            new AtuxColumnData("Precio", 60, JLabel.RIGHT),
            new AtuxColumnData("%Dscto", 50, JLabel.RIGHT),
            new AtuxColumnData("P.Venta", 60, JLabel.RIGHT),
            new AtuxColumnData("Cantidad", 60, JLabel.RIGHT),
            new AtuxColumnData("Total", 60, JLabel.RIGHT),
            new AtuxColumnData("Clase", 50, JLabel.CENTER),
            new AtuxColumnData("Laboratorio", 160, JLabel.LEFT),
            new AtuxColumnData("IN_ORIGEN_SELECCION", 0, JLabel.CENTER),
            new AtuxColumnData("CO_IMPUESTO_1", 0, JLabel.CENTER),
            new AtuxColumnData("PC_DCTO_VENTA_ESPECIAL", 0, JLabel.CENTER),
            new AtuxColumnData("NU_ITEM_PEDIDO", 0, JLabel.CENTER),
            new AtuxColumnData("PC_DESCUENTO_BASE_LOCAL", 0, JLabel.CENTER),
            new AtuxColumnData("PC_DESCUENTO_CONVENIO", 0, JLabel.CENTER),
            new AtuxColumnData("VA_PRECIO_BASE_LOCAL", 0, JLabel.CENTER),
            new AtuxColumnData("IN_VENTA_FIDELIZACION", 0, JLabel.CENTER),
            new AtuxColumnData("IN_PRODUCTO_PLAN", 0, JLabel.CENTER),       // PROMOCIONES
            new AtuxColumnData("CO_PRODUCTO_PRINCIPAL", 0, JLabel.CENTER),
            new AtuxColumnData("CO_CONVENIO_PLAN", 0, JLabel.CENTER),
            new AtuxColumnData("IN_ACUMULA", 0, JLabel.CENTER),
            // Inicio ID=004
            new AtuxColumnData("", 0, JLabel.CENTER),
            new AtuxColumnData("", 0, JLabel.CENTER)
            // Fin ID=004
    };

    public static final Object[] defaultValuesPedidoProductos = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "", "", "", "", ""};
    
    public static final AtuxColumnData columnsPedidoRepGen[] = {
            new AtuxColumnData("Código", 60, JLabel.LEFT),
            new AtuxColumnData("Descripción", 275, JLabel.LEFT),
            new AtuxColumnData("Unidad", 182, JLabel.LEFT),
            new AtuxColumnData("Min", 60, JLabel.RIGHT),
            new AtuxColumnData("Max", 60, JLabel.RIGHT),
            new AtuxColumnData("Stock", 80, JLabel.RIGHT),
            new AtuxColumnData("NoPedir", 50, JLabel.CENTER),
            new AtuxColumnData("Calc.", 60, JLabel.RIGHT),
            new AtuxColumnData("Solic.", 50, JLabel.RIGHT),
            new AtuxColumnData("Ultimo", 0, JLabel.LEFT),
            new AtuxColumnData("Rot", 0, JLabel.LEFT),
            new AtuxColumnData("Can Min Exh", 0, JLabel.RIGHT),
            new AtuxColumnData("CP No Aten", 0, JLabel.RIGHT),
    };
    public static final Object[] defaultValuesPedidoRepGen = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
}