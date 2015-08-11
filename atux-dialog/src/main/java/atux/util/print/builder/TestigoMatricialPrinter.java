package atux.util.print.builder;

import atux.modelbd.DetallePedidoVenta;
import atux.modelbd.PedidoVenta;
import atux.util.common.AtuxVariables;
import atux.util.common.AtuxDBUtility;
import atux.util.print.AtuxPrint;
import atux.util.print.AtuxPrintUtility;


public class TestigoMatricialPrinter extends TestigoBuilder {

    private int cantidadProductos = 0;
    private PedidoVenta pedido;
    
    public TestigoMatricialPrinter(PedidoVenta pedido) {
        this.pedido = pedido;
    }

    @Override
    public void printTiTle(AtuxPrint vPrint) {
        vPrint.activateCondensed();
        vPrint.printBold(AtuxPrintUtility.llenarBlancos(2) + "**NO VALIDO COMO COMPROBANTE DE PAGO**", true);
        vPrint.deactivateCondensed();

    }

    @Override
    public void printHeader(AtuxPrint vPrint) {
        vPrint.activateCondensed();
        vPrint.printLine(AtuxPrintUtility.alinearIzquierda(AtuxVariables.vDescripcionCompania, 21) + AtuxPrintUtility.llenarBlancos(9) + AtuxPrintUtility.alinearIzquierda("Ped : " + AtuxVariables.vNumeroPedidoDiario, 12), true);
        vPrint.printLine(AtuxPrintUtility.alinearIzquierda("RUC : " + AtuxVariables.vNuRucCompania, 21) + AtuxPrintUtility.llenarBlancos(9) + AtuxPrintUtility.alinearIzquierda("Ven : " + AtuxVariables.vNuSecUsuario, 12), true);
        vPrint.printLine(AtuxPrintUtility.alinearIzquierda(AtuxVariables.vDescripcionLocal, 42), true);
        vPrint.activateCondensed();
        vPrint.printLine("Fec:" + getFechaActual() + AtuxPrintUtility.llenarBlancos(1) + "Correl: " + AtuxVariables.vNumeroPedido, true);
        vPrint.printLine(AtuxPrintUtility.alinearIzquierda("Cliente :" + AtuxVariables.vANombreDe, 42), true);
        vPrint.deactivateCondensed();
    }

    @Override
    public void printColumnHeader(AtuxPrint vPrint) {
        vPrint.activateCondensed();
        vPrint.printLine(" Cant. Descripcion                        ", true);
        vPrint.printLine("====================================================", true);
        vPrint.deactivateCondensed();
    }

    @Override
    public void printDetail(AtuxPrint vPrint) {
        try {
            for (int i = 0; i < pedido.getCaItem(); i++) {
                vPrint.activateCondensed();
                String codigo   = ((DetallePedidoVenta)pedido.getDetallePedidoVenta().get(i)).getCoProducto();
                String producto = ((DetallePedidoVenta)pedido.getDetallePedidoVenta().get(i)).getProdLocal().getProducto().getDeProducto();
                String unidad   = ((DetallePedidoVenta)pedido.getDetallePedidoVenta().get(i)).getDeUnidadProducto();
                String cantidad = String.valueOf(((DetallePedidoVenta)pedido.getDetallePedidoVenta().get(i)).getCaAtendida());                
                String lab      = ((DetallePedidoVenta)pedido.getDetallePedidoVenta().get(i)).getProdLocal().getProducto().getDeLaboratorio();

                if (Integer.parseInt(cantidad) >= 0) {                    
                    String fraccion = AtuxDBUtility.getValueAt("LGTR_PRODUCTO_LOCAL", "DECODE(VA_FRACCION, 0, 1, VA_FRACCION)", "CO_COMPANIA = '" +
                            AtuxVariables.vCodigoCompania + "' AND CO_LOCAL = '" + AtuxVariables.vCodigoLocal +
                            "' AND CO_PRODUCTO = '" + codigo + "'");                    

                    if (producto.length() > 36) {
                        vPrint.printLine(
                                AtuxPrintUtility.alinearDerecha(cantidad, 4) +
                                (fraccion.trim().equals("1") ? " " : "F") + " " +
                                AtuxPrintUtility.alinearIzquierda(producto, 36), true);                                
                        vPrint.printLine(codigo + " " +
                                AtuxPrintUtility.alinearIzquierda(producto.substring(36), 4) + "/" +
                                AtuxPrintUtility.alinearIzquierda(unidad, 8) + "/" +
                                AtuxPrintUtility.alinearIzquierda(lab, 16), true);
                    } else {
                        vPrint.printLine(
                                AtuxPrintUtility.alinearDerecha(cantidad, 4) +
                                (fraccion.trim().equals("1") ? " " : "F") + " " +
                                AtuxPrintUtility.alinearIzquierda(producto, 36), true);
                        vPrint.printLine(codigo + " " +
                                AtuxPrintUtility.alinearIzquierda(unidad, 8) + "/" +
                                AtuxPrintUtility.alinearIzquierda(lab, 16), true);
                    }
                    
                    vPrint.deactivateCondensed();

                    cantidadProductos ++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void printPageFooter(AtuxPrint vPrint) {
        vPrint.activateCondensed();
        vPrint.printLine("", true);
        vPrint.printLine("-Productos : " + AtuxPrintUtility.alinearDerecha(cantidadProductos, 6), true);
        vPrint.printLine("====================================================", true);
        if (pedido.getTiPedido().equalsIgnoreCase("2"))
            vPrint.printBold("Reparto a Domicilio TELF  " + AtuxVariables.vNumeroTelefono, true);
        vPrint.deactivateCondensed();
    }

    @Override
    public void printFooter(AtuxPrint vPrint) {
        vPrint.activateCondensed();
        vPrint.printBold(AtuxPrintUtility.llenarBlancos(2) + "**NO VALIDO COMO COMPROBANTE DE PAGO**", true);
        vPrint.deactivateCondensed();
    }
}
