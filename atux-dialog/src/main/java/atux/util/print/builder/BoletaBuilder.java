package atux.util.print.builder;

import atux.modelbd.DetallePedidoVenta;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.util.print.AtuxPRNUtility;
import atux.util.print.AtuxPrint;
import atux.util.print.AtuxPrintService;
import com.atux.bean.donacion.IngresarDonacionDetalle;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

public class BoletaBuilder extends ComprobanteBuilder {

    double totalDescuentoTarjeta;

    @Override
    public void printTiTle(AtuxPrint vPrint) {

    }

    @Override
    public void printHeader(AtuxPrint vPrint) {
        vPrint.activateCondensed();

        vPrint.printLine(" ", true);
        vPrint.printLine(AtuxPRNUtility.llenarBlancos(11) + comprobantePago.getFechaBD() + "   CORR." + comprobantePago.getNuPedido(), true);
        String mensajeNombre = AtuxVariables.vANombreDe.trim();
        vPrint.printLine(AtuxPRNUtility.llenarBlancos(11) + AtuxPRNUtility.alinearIzquierda(mensajeNombre, 55) +
                " CAJA:"  + AtuxVariables.vNuCaja  +
                " TURNO:" + AtuxVariables.vNuTurno +
                " No. " + comprobantePago.getNuComprobantePago().substring(0, 3) + "-" + comprobantePago.getNuComprobantePago().substring(3, 10), true);

        if (comprobantePago.getMensajeComprobanteDetail() != null && comprobantePago.getMensajeComprobante() != null) {
            String mensajePuntos = comprobantePago.getMensajeComprobanteDetail().length() > 80 ?
                    comprobantePago.getMensajeComprobante() : comprobantePago.getMensajeComprobanteDetail();

            vPrint.printLine(mensajePuntos, true);
        } else {
            vPrint.printLine(" ", true);
        }
        vPrint.printLine(" ", true);
    }

    @Override
    public void printColumnHeader(AtuxPrint vPrint) {

    }

    @Override
    public void printDetail(AtuxPrint vPrint) {

        List detalle = comprobantePago.getDetalleComprobante();
        numeroLineas = 0;
//        double pDescuentoConvenio = 0;

        for (int i = 0; i < detalle.size(); i++) {

            DetallePedidoVenta vttdPedidoVenta = (DetallePedidoVenta) detalle.get(i);

            double precioMostrar = 0;

//            if (vttdPedidoVenta.getCaAtendida() >= 0) {
//                    precioMostrar = vttdPedidoVenta.getVaPrecioBaseLocal();
//            } else {
//                    precioMostrar = vttdPedidoVenta.getVaPrecioPublico();
//            }

            precioMostrar = vttdPedidoVenta.getVaPrecioPublico();

            double precioUnitarioIncrementado = precioMostrar;

            double totalItem = 0;

            totalItem = vttdPedidoVenta.getCaAtendida() * precioUnitarioIncrementado;

            if (vttdPedidoVenta.getCoProducto().equals("421003")) {
                if (totalItem > (comprobantePago.getVaTotalPrecioVenta() - comprobantePago.getVaSaldoRedondeo())) {
                    totalItem = comprobantePago.getVaTotalPrecioVenta() - comprobantePago.getVaSaldoRedondeo();
                }

                if (totalItem != 0) {
                    totalItem += comprobantePago.getTotalDctoConvenio();
                }
            }

            totalDescuentoTarjeta = totalDescuentoTarjeta + totalItem;

//            if (i == 0) {
//                pDescuentoConvenio = vttdPedidoVenta.getPcDescuentoConvenio();
//            } else if (!comprobantePago.isVariosDsctoConvenio()) {
//                comprobantePago.setVariosDsctoConvenio(pDescuentoConvenio != vttdPedidoVenta.getPcDescuentoConvenio());
//            }                        

            vPrint.printLine(" " +
                    AtuxPRNUtility.alinearDerecha(vttdPedidoVenta.getCaAtendida(), 4) +
                    (vttdPedidoVenta.getVaFraccion() > 1 ? "F" : " ") + " " +
                    //AtuxPRNUtility.alinearIzquierda(((vttdPedidoVenta.getInProductoPlan() != null && vttdPedidoVenta.getInProductoPlan().equals("S")) ? vttdPedidoVenta.getDeMensajePromocion() + "-" : "") + ((vttdPedidoVenta.getInAcumulaPromo() != null && vttdPedidoVenta.getInAcumulaPromo().equals("S")) ? "*ACUM-" : "") + vttdPedidoVenta.getProdLocal().getProducto().getDeProducto(), 40) + " " +
                    AtuxPRNUtility.alinearIzquierda(vttdPedidoVenta.getDeUnidadProducto(), 10) + " " +
                    AtuxPRNUtility.alinearIzquierda(vttdPedidoVenta.getProdLocal().getProducto().getLaboratorio().getDeLaboratorio(), 11) + " " +
                    AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(precioUnitarioIncrementado), 9) + " " +
                    AtuxPRNUtility.alinearDerecha((comprobantePago.getImprimeDcto().equals("S") ? vttdPedidoVenta.getPcDescuento_1() + "%" : "      "), 7) + " " +
                    AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(totalItem), 9) + " ", true);

            numeroLineas++;

        }
    }

    @Override
    public void printPageFooter(AtuxPrint vPrint) {
        for (int j = numeroLineas; j < numeroMaximoItems - lineasMensaje; j++) {
            vPrint.printLine(" ", true);
        }
    }

    @Override
    public void printFooter(AtuxPrint vPrint) {

        if (mensajesAux.size() > 0) {
            Iterator iterator = mensajesAux.iterator();
            while (iterator.hasNext()) {
                Message mensajes = new Message((String) iterator.next());
                mensajes.readFileProperties();
                List lineas = mensajes.getListaLineasCustom(espacioLineas);
                for (int i = 0; i < lineas.size(); i++) {
                    vPrint.printLine(" " + (String) lineas.get(i), true);
                }
            }
        }
        StringBuffer mensajeDonacion = new StringBuffer();
        StringBuffer montoDonacion = new StringBuffer();

        if (comprobantePago.getDonacionList().size()>0) {
            BigDecimal totalDonacion=BigDecimal.ZERO;
            for (int i = 0; i < comprobantePago.getDonacionList().size(); i++) {
                IngresarDonacionDetalle donacionDetalle = (IngresarDonacionDetalle) comprobantePago.getDonacionList().get(i);
                mensajeDonacion.append("Gracias x donar a ").append(donacionDetalle.getDonacion().getDeCortaInstitucion());
                totalDonacion=totalDonacion.add(donacionDetalle.getMonto());
            }

            montoDonacion.append(AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(totalDonacion.doubleValue()), 10));
            montoDonacion.append("      Total       S/. ").append(AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(totalDonacion.doubleValue()), 10));

            //70
            vPrint.printLine(" SON: " + AtuxPRNUtility.alinearIzquierda(AtuxPRNUtility.montoEnLetras(AtuxUtility.getDecimalNumber(AtuxUtility.formatNumber(comprobantePago.getVaTotalPrecioVenta()))), 40) + ";", false);
            vPrint.printLine(AtuxPRNUtility.alinearIzquierda("", 30), false);
            vPrint.printLine(AtuxPRNUtility.alinearDerecha(" Total S/. ", 12), false);
            vPrint.printLine(AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(comprobantePago.getVaTotalPrecioVenta()), 10), true);

            vPrint.printLine(AtuxPRNUtility.alinearIzquierda(" Forma(s) de pago: " + AtuxVariables.vNombresFormasPago + "  ", 35), false);
            vPrint.printLine(AtuxPRNUtility.alinearIzquierda(mensajeDonacion.toString(), 42), false);
            vPrint.printLine(AtuxPRNUtility.alinearDerecha(" Total S/. ", 12), false);
            vPrint.printLine(AtuxPRNUtility.alinearDerecha(montoDonacion.toString(), 10), true);

            vPrint.printLine(" REDO:" + AtuxUtility.formatNumber(comprobantePago.getVaSaldoRedondeo()) +
                    AtuxPRNUtility.alinearIzquierda(" CAJERO:" + AtuxVariables.vNoUsuario.trim() + " " + AtuxVariables.vPaternoUsuario.trim() + " ", 25) +
                    AtuxPRNUtility.alinearIzquierda(" VEND:" + comprobantePago.getNombreVendedor(), 25) + AtuxPRNUtility.alinearIzquierda(((AtuxVariables.vTipoPedido.equalsIgnoreCase(AtuxVariables.TIPO_PEDIDO_DELIVERY)) ? "-DELIV-" : " "), 8), false);
            vPrint.printLine(AtuxPRNUtility.alinearDerecha(" ToTal a Pagar S/. ", 19), false);
            vPrint.printLine(AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(comprobantePago.getVaTotalPrecioVenta() + totalDonacion.doubleValue() /*+ vttvComprobantePago.getVaSaldoRedondeo()*/), 10), true);

        } else {
            vPrint.printLine(" SON: " + AtuxPRNUtility.alinearIzquierda(AtuxPRNUtility.montoEnLetras(AtuxUtility.getDecimalNumber(AtuxUtility.formatNumber(comprobantePago.getVaTotalPrecioVenta()))), 72) + " ", true);

            vPrint.printLine(" Forma(s) de pago: " + AtuxVariables.vNombresFormasPago, true);
            vPrint.printLine(" REDO:" + AtuxUtility.formatNumber(comprobantePago.getVaSaldoRedondeo()) +
                    AtuxPRNUtility.alinearIzquierda(" CAJERO:" + AtuxVariables.vNoUsuario.trim() + " " + AtuxVariables.vPaternoUsuario.trim() + " ", 25) +
                    AtuxPRNUtility.alinearIzquierda(" VEND:" + comprobantePago.getNombreVendedor(), 25) + AtuxPRNUtility.alinearIzquierda(((AtuxVariables.vTipoPedido.equalsIgnoreCase(AtuxVariables.TIPO_PEDIDO_DELIVERY)) ? "-DELIV-" : " "), 8), false);
            vPrint.printLine(AtuxPRNUtility.alinearDerecha(" ToTal a Pagar S/. ", 19), false);
            vPrint.printLine(AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(comprobantePago.getVaTotalPrecioVenta()), 10), true);

            vPrint.deactivateCondensed();

        }

    }

    public static void main(String[] args) {
        //AtuxPrintService vPrint = new AtuxPrintService(36, "//192.166.0.26/OKIDATAM", false);
        AtuxPrintService vPrint = new AtuxPrintService(36, "E://PRUEBAS//CUPON.txt", false);

        vPrint.startPrintService();
        vPrint.activateCondensed();

        vPrint.printLine(" ", true);
        vPrint.printLine(AtuxPRNUtility.llenarBlancos(11) + "05/03/2012 12:48:34" + "   CORR." + "0002505664", true);
        vPrint.printLine(AtuxPRNUtility.llenarBlancos(11) + AtuxPRNUtility.alinearIzquierda(" ", 55) +
                " CAJA:" + "1" + " TURNO:" + "1" + " No. " + "023" + "-" + "0457153", true);

        vPrint.printLine("CHIROQUE GANO 45 PTO(S), ACUM 618 PTO(S) inkapunto(s)", true);
        vPrint.printLine(" ", true);

        vPrint.printLine(" " +
                AtuxPRNUtility.alinearDerecha("2F", 4) + " " +
                AtuxPRNUtility.alinearIzquierda("AMOXICILINA BW 500MG CAP CJAx100UND", 40) + " " +
                AtuxPRNUtility.alinearIzquierda("CAPSULA", 10) + " " +
                AtuxPRNUtility.alinearIzquierda("BRAWN", 11) + " " +
                AtuxPRNUtility.alinearDerecha("15.00", 9) + " " +
                AtuxPRNUtility.alinearDerecha("      ", 7) + " " +
                AtuxPRNUtility.alinearDerecha("15.00", 9) + " ", true);

        vPrint.printLine(" ", true);
        vPrint.printLine(" ", true);
        vPrint.printLine(" ", true);

        vPrint.printLine(" SON: " + AtuxPRNUtility.alinearIzquierda(AtuxPRNUtility.montoEnLetras(AtuxUtility.getDecimalNumber(AtuxUtility.formatNumber(15.00))), 40) + ";", false);
        vPrint.printLine(" Forma(s) de pago: " + "EFECTIVO S/., VISA ELECTRON", true);
        vPrint.printLine(" REDO:" + AtuxUtility.formatNumber(0.00) +
                AtuxPRNUtility.alinearIzquierda(" CAJERO:" + "VENTAS" + " " + "USUARIO" + " ", 25) +
                AtuxPRNUtility.alinearIzquierda(" VEND:" + "VENTAS USUARIO", 25), false);
        vPrint.printLine(AtuxPRNUtility.alinearDerecha(" ToTal a Pagar S/. ", 19), false);
        vPrint.printLine(AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(15.00), 10), true);

        vPrint.deactivateCondensed();
        vPrint.endPrintService();

          /*mainPRN.startPrintService();
            mainPRN.setStartHeader();
            mainPRN.printLine(" ", true);
            mainPRN.printLine("     ECKERD AMAZONIA S.A.C - INKAFARMA   ", true);
            mainPRN.printLine("Central: Av. Guillermo Dansey # 1845-Lima", true);
            mainPRN.printLine("3159000 - 3142020         RUC 20331066703", true);
            mainPRN.printLine("Tienda MAGDALENA                         ", true);
            mainPRN.printLine("JR. CASTILLA # 718 - MAGDALENA           ", true);
            mainPRN.printLine("Serie BOLETACAJA10                       ", true);
            mainPRN.printLine("Ticket Nro 024-99195759                  ", true);
            mainPRN.printLine("CORR. 0002505664             Pedido: 0001", true);
            mainPRN.printLine("FECHA:05/03/2012 12:04:39 CAJA:1 TURNO: 1", true);
            mainPRN.printLine("-----------------------------------------", true);
            mainPRN.printLine("SR(A). CHIROQUE CARNERO JULIO ANTONIO, UD.", true);
            mainPRN.printLine(" GANO 30 PUNO(S), ACUM 438 PUNTO(S) INKAP", true);
            mainPRN.printLine("UNTO(S)                                  ", true);
            mainPRN.printLine("-----------------------------------------", true);
            mainPRN.printLine("                                         ", true);
            mainPRN.printLine("Cant. Descripcion                 Importe", true);
            mainPRN.printLine("-----------------------------------------", true);
            mainPRN.printLine("  1   3-A OFTENO 0.1% SOL GOT.      55.00", true);
            mainPRN.printLine("        FCOx5ML - LAB.OFTALMICOS S.A.C   ", true);
            mainPRN.printLine("-----------------------------------------", true);
            mainPRN.printLine("                       Redo : S/.   -0.00", true);
            mainPRN.printLine("                       Total  S/.   55.00", true);
            mainPRN.printLine("EFECTIVO S/. 30.00 (Soles)               ", true);
            mainPRN.printLine("VISA ELECTRON S/. 25.00 (Soles)          ", true);
            mainPRN.printLine("VUELTO: S/. 0.00                         ", true);
            mainPRN.printLine("CAJERO: VENTAS USUARIO                   ", true);
            mainPRN.printLine("VENDED: VENTAS USUARIO                   ", true);
            mainPRN.printLine("                                         ", true);
            mainPRN.printLine("      NO HAY DEVOLUCION DE DINERO        ", true);
            mainPRN.printLine("TODO CAMBIO DE MERCADERIA SE HARA DENTRO ", true);
            mainPRN.printLine("DE LAS 48 HORAS PREVIA PRESENTACION DEL  ", true);
            mainPRN.printLine("COMPROBANTE Y VERIFICACION POR PARTE DEL ", true);
            mainPRN.printLine("          QUIMICO FARMACEUTICO           ", true);
            mainPRN.printLine("-----------------------------------------", true);
            mainPRN.printLine("  PAGO CON VISA          TOTAL: S/. 25.00", true);
            mainPRN.printLine("4213********7533           VENCE: 1605   ", true);
            mainPRN.printLine("AP:046678 TER:013825655 REF:0056 LOTE:018", true);
            mainPRN.printLine("          ID: 992120609257918            ", true);
            mainPRN.printLine("-----------------------------------------", true);
            mainPRN.setEndHeader();
            mainPRN.endPrintService();*/
    }

}
