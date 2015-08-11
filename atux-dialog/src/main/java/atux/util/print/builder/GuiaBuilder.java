package atux.util.print.builder;

import atux.modelbd.DetallePedidoVenta;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.util.print.AtuxPRNUtility;
import atux.util.print.AtuxPrint;
import java.util.List;

public class GuiaBuilder extends ComprobanteBuilder {
    
    @Override
    public void printTiTle(AtuxPrint vPrint) {
        String fechaBD = comprobantePago.getFechaBD();
        String dia = fechaBD.substring(0, 2);
        String mesLetra = AtuxUtility.devuelveMesEnLetras(Integer.parseInt(fechaBD.substring(3, 5)));
        String ano = fechaBD.substring(6, 10);
        String hora = fechaBD.substring(11, 19);
        vPrint.setStartHeader();
        vPrint.activateCondensed();
        vPrint.printLine("", true);
        vPrint.printLine("", true);
        vPrint.printLine("", true);
        vPrint.printLine("", true);
        vPrint.printLine("LOCAL: " + AtuxVariables.vDescripcionCortaLocal +
                "          CORR."  + comprobantePago.getNuPedido() +
                "          No. "   + comprobantePago.getNuComprobantePago().substring(0, 3) + "-" + comprobantePago.getNuComprobantePago().substring(3, 10), true);
        vPrint.printLine("        "+ dia + "          " + mesLetra + "                    " + ano + "     " + hora, true);
        vPrint.printLine("                  " + AtuxVariables.vANombreDe.trim(), true);
        vPrint.printLine("                  " + AtuxVariables.vRUCFacturar.trim(), true);
        vPrint.printLine("                  " + AtuxVariables.vDireccion.trim(), true);
        vPrint.printLine("                  " + "", true);
        vPrint.printLine(" ", true);
        vPrint.printLine(" ", true);
        vPrint.printLine(" ", true);
        vPrint.printLine(" ", true);
        vPrint.deactivateCondensed();
        vPrint.setEndHeader();
    }
    
    @Override
    public void printHeader(AtuxPrint vPrint) {
        
    }
    
    @Override
    public void printColumnHeader(AtuxPrint vPrint) {
        
    }
    
    @Override
    public void printDetail(AtuxPrint vPrint) {
        numeroLineas = 0;
        List detalle = comprobantePago.getDetalleComprobante();
        vPrint.activateCondensed();
        
        for (int i = 0; i < detalle.size(); i++) {

            DetallePedidoVenta vttdPedidoVenta = (DetallePedidoVenta) detalle.get(i);

            vPrint.printLine(" " +
                    AtuxPRNUtility.alinearDerecha(vttdPedidoVenta.getCoProducto(), 7) + " " +
                    AtuxPRNUtility.alinearIzquierda( (( vttdPedidoVenta.getInProductoPlan()!=null && vttdPedidoVenta.getInProductoPlan().equals("S")) ? "[PROMO]" : "") + vttdPedidoVenta.getProdLocal().getProducto().getDeProducto(), 37) + " " +
                    AtuxPRNUtility.alinearIzquierda(vttdPedidoVenta.getDeUnidadProducto(), 17) + " " +
                    AtuxPRNUtility.alinearIzquierda(vttdPedidoVenta.getProdLocal().getProducto().getLaboratorio().getDeLaboratorio(), 13) + AtuxPRNUtility.llenarBlancos(3) +
                    AtuxPRNUtility.alinearDerecha((comprobantePago.getImprimeDcto().equals("S") ? vttdPedidoVenta.getPcDescuento_1() + "%" : "      "), 7) + " " +
                    
                    AtuxPRNUtility.alinearDerecha(vttdPedidoVenta.getCaAtendida(), 5) +
                    (vttdPedidoVenta.getVaFraccion() > 1 ? "F" : " ") + " " +
                    
                    AtuxPRNUtility.alinearDerecha(vttdPedidoVenta.getVaPrecioPublico(), 9) + "  " +
                    AtuxPRNUtility.alinearDerecha(vttdPedidoVenta.getVaPrecioVenta(), 10) + " ", true);

            numeroLineas++;

        }
        vPrint.deactivateCondensed();
    }
    
    @Override
    public void printPageFooter(AtuxPrint vPrint) {
        for (int j = numeroLineas; j <= numeroMaximoItems; j++) vPrint.printLine(" ", true);
    }
    
    @Override
    public void printFooter(AtuxPrint vPrint) {
        vPrint.activateCondensed();
        vPrint.printLine(" SON: " + AtuxPRNUtility.alinearIzquierda(AtuxPRNUtility.montoEnLetras(AtuxUtility.getDecimalNumber(AtuxUtility.formatNumber(comprobantePago.getVaTotalPrecioVenta()))), 67), true);
        vPrint.printLine(" REDO:" + AtuxUtility.formatNumber(comprobantePago.getVaSaldoRedondeo()) +
                         " CAJERO:" + AtuxVariables.vNoUsuario.trim() + " " + AtuxVariables.vPaternoUsuario.trim() + " " +
                         " CAJA:"   + AtuxVariables.vNuCaja +
                         " TURNO:"  + AtuxVariables.vNuTurno +
                         " VEND:"   + comprobantePago.getNombreVendedor() , true);
        vPrint.printLine(" Forma(s) de pago: " + AtuxVariables.vNombresFormasPago, true);
        vPrint.printLine(AtuxPRNUtility.llenarBlancos(50) + "VALOR VENTA NETO           % I.G.V.           IMPORTE I.G.V.             TOTAL A PAGAR", true);
        vPrint.printLine(AtuxPRNUtility.llenarBlancos(50) +
                AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(comprobantePago.getVaTotalVenta() - comprobantePago.getVaTotalDescuento()), 10) + "                 " +
                String.valueOf(AtuxVariables.vIgvPorcentaje) + "           " +
                AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(comprobantePago.getVaTotalImpuesto()), 12) + "                " +
                "S/. " + AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(comprobantePago.getVaTotalPrecioVenta()), 10), true);

        vPrint.deactivateCondensed();
        vPrint.endPrintService();
    }
    
}
