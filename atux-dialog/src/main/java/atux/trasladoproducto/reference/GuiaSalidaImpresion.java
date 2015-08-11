package atux.trasladoproducto.reference;

import javax.swing.JDialog;
import javax.swing.JTable;

import atux.inventario.reference.DBInventario;
import atux.inventario.reference.VariablesInventario;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.util.print.AtuxPRNUtility;
import atux.util.print.AtuxPrintService;

/**
 * ID     PROGRAMADOR    FECHA/HORA            TIPO                 OBSERVACIoN
 * 000    GMATEO         15/05/2010 10:00:00   Creacion <br>
 * 001    JREBATTA       05/10/2010 14:45:00   Modificacion         Mostrar en la Guía de Remision el Tipo y Número de Solicitud
 */
public class GuiaSalidaImpresion {
    private static final int COL_IN_PROD_FRACCIONADO = 9;
    private static final int COL_VA_FRACCION = 10;

    public static void imprimirTransporteProducto_old(JDialog pDialog, String tiPedido, JTable vTable, String pDeLocalOrigen,
                                                      String pDia, String pMesLetra, String pAoo, String nuRecepcionProducto) {
        AtuxUtility.showMessage(pDialog, "Preporese para la impresion de la Guía de Remision", null);
        AtuxPrintService vPrint = new AtuxPrintService(65, AtuxVariables.vImpresoraReporte, true);

        // inicio del Servicio de Impresion
        if (!vPrint.startPrintService()) {
            AtuxUtility.showMessage(pDialog, "No se pudo determinar la existencia de la Impresora. Verifique !!!", null);
            return;
        }
        
        try {
            boolean usarUnidadBase = ConstantsTrasladoProducto.debeUsarseUnidadBase(tiPedido, "");
            double dblTotalCantEntera = 0,
                    dblTotalCantFracc = 0;

            vPrint.setStartHeader();
            vPrint.activateCondensed();
            vPrint.printLine("", true);
            vPrint.printLine("", true);
            vPrint.printLine("", true);
            vPrint.printLine("", true);
            vPrint.printBold(AtuxPRNUtility.alinearIzquierda("Local: " + AtuxVariables.vDescripcionCortaLocal + " - Ref : " + nuRecepcionProducto, 48), true);
            vPrint.printLine("", true);
            vPrint.printBold("        " + pDia + "          " + pMesLetra + "                 " + pAoo, true);
            vPrint.printBold("                  " + pDeLocalOrigen, true);
            vPrint.printBold("                  ", true);
            vPrint.printBold("                  ", true);
            vPrint.printBold("                  ", true);
            vPrint.printBold("                  ", true);
            vPrint.printLine("", true);

            vPrint.printLine("", true);
            vPrint.printLine("", true);
            vPrint.deactivateCondensed();
            vPrint.setEndHeader();
            int nuRows = 0;
            boolean noHayLaboratorioCol = vTable.getColumnCount() <= ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_DE_LABORATORIO;
            for (int i = 0; i < vTable.getRowCount(); i++) {
                String caAtendidaStr = ((String) vTable.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_CANTIDAD_ATENDIDA)).trim();
                String inProdFraccionado = ((String) vTable.getValueAt(i, COL_IN_PROD_FRACCIONADO)).trim();
                String vaFraccion = ((String) vTable.getValueAt(i, COL_VA_FRACCION)).trim();
                int caAtendida = ("".equals(caAtendidaStr)) ? 0 : Integer.parseInt(caAtendidaStr);
                if (caAtendida <= 0) {
                    continue;
                }
                int caEntera = 0;
                int caFraccion = 0;
                if (usarUnidadBase) {
                    caEntera = caAtendida;
                } else {
                    int vaFraccionInt = "".equals(vaFraccion) ? 0 : Integer.parseInt(vaFraccion);
                    if (("S".equalsIgnoreCase(inProdFraccionado)) && (vaFraccionInt > 1)) {
                        caFraccion = caAtendida;
                    } else {
                        caEntera = caAtendida;
                    }
                }

                nuRows++;
                dblTotalCantEntera += caEntera;
                dblTotalCantFracc += caFraccion;
                String coProducto = ((String) vTable.getValueAt(i, 0)).trim();
                String deLaboratorio;
                if (noHayLaboratorioCol) {
                    VariablesInventario.vCodigoProducto = coProducto;
                    deLaboratorio = DBInventario.getDescLaboratorio();
                } else {
                    deLaboratorio = (String) vTable.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_DE_LABORATORIO);
                    if (deLaboratorio == null || "".equals(deLaboratorio)) {
                        VariablesInventario.vCodigoProducto = coProducto;
                        deLaboratorio = DBInventario.getDescLaboratorio();
                    }
                }
                if ((i > 1) && ((i + 1) % 36) == 1) {
                    vPrint.pageBreak();
                }
                vPrint.printCondensed("   " + AtuxPRNUtility.alinearIzquierda(coProducto, 7) + "          " +
                        AtuxPRNUtility.alinearIzquierda(((String) vTable.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_DE_PRODUCTO)).trim(), 39) + "   " +
                        AtuxPRNUtility.alinearIzquierda(((String) vTable.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_UN_PRODUCTO_DESTINO)).trim(), 19) + "  " +
                        AtuxPRNUtility.alinearIzquierda(deLaboratorio, 30) + AtuxPRNUtility.llenarBlancos(7) + "     " +
                        AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(caEntera, "###,##0"), 5) + " / " +
                        AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(caFraccion, "###,##0"), 4), true);
            }
            vPrint.printLine("", true);
            vPrint.activateCondensed();
            vPrint.printBold(AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(nuRows, "###,##0"), 7)
                    + AtuxPRNUtility.llenarBlancos(109)
                    + AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(dblTotalCantEntera, "###,##0"), 7)
                    + AtuxPRNUtility.llenarBlancos(2)
                    + AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(dblTotalCantFracc, "###,##0"), 7)
                    , true);
            vPrint.deactivateCondensed();

            vPrint.endPrintService();
        } catch (Exception sqlerr) {
            sqlerr.printStackTrace();
        }
    }

    public static void imprimirTransporteProducto(JDialog pDialog, String tiPedido, JTable vTable, String pDeLocalOrigen,
                                                  String pDia, String pMesLetra, String pAoo, String nuRecepcionProducto,
                                                  String pNuSolicitudPedido, String pSerie, String pNumeral) {
    	
        AtuxUtility.showMessage(pDialog, "Preporese para la impresion de la Guía de Remision", null);
        AtuxPrintService vPrint = new AtuxPrintService(65, AtuxVariables.vImpresoraReporte, true);
        int numeralInt = Integer.parseInt(pNumeral);

        if (!vPrint.startPrintService()) {
            AtuxUtility.showMessage(pDialog, "No se pudo determinar la existencia de la Impresora. Verifique !!!", null);
            return;
        }

        try {
            boolean usarUnidadBase = ConstantsTrasladoProducto.debeUsarseUnidadBase(tiPedido, "");
            double dblTotalCantEntera = 0, dblTotalCantFracc = 0;

            vPrint.setStartHeader();
            vPrint.activateCondensed();
            vPrint.printLine("", true);
            vPrint.printLine("", true);
            vPrint.printLine("", true);
            vPrint.printLine("", true);
            vPrint.printBold(AtuxPRNUtility.alinearIzquierda("Local: " + AtuxVariables.vDescripcionCortaLocal + " - Ref : " + nuRecepcionProducto, 48), true);
            vPrint.printLine("", true);
            vPrint.printBold("        " + pDia + "          " + pMesLetra + "                     " + pAoo, true);
            vPrint.printBold("                    " + pDeLocalOrigen , true);
            vPrint.setEndHeader();
            vPrint.printBold( AtuxPRNUtility.llenarBlancos(100) + pSerie + " - " + AtuxUtility.caracterIzquierda(String.valueOf(numeralInt), 7, "0")  , true); //RUC
            vPrint.printBold("                  ", true); //Direccion
            vPrint.printBold("                  ", true); //Transportista
            vPrint.printBold("                            Pedido: " + tiPedido + " - " + pNuSolicitudPedido, true);
            vPrint.printLine("", true);
            vPrint.printLine("", true);
            vPrint.printLine("", true);
            vPrint.deactivateCondensed();

            int nuRows = 0;
            boolean noHayLaboratorioCol = vTable.getColumnCount() <= ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_DE_LABORATORIO;

            for (int i = 0; i < vTable.getRowCount(); i++) {
                String caAtendidaStr = ((String) vTable.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_CANTIDAD_ATENDIDA)).trim();

                int caAtendida = ("".equals(caAtendidaStr)) ? 0 : Integer.parseInt(caAtendidaStr);
                if (caAtendida <= 0) {
                    continue;
                }

                nuRows++;
                dblTotalCantEntera += caAtendida;
                String coProducto = ((String) vTable.getValueAt(i, 0)).trim();
                String deLaboratorio;
                if (noHayLaboratorioCol) {
                    VariablesInventario.vCodigoProducto = coProducto;
                    deLaboratorio = DBInventario.getDescLaboratorio();
                } else {
                    deLaboratorio = (String) vTable.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_DE_LABORATORIO);
                    if (deLaboratorio == null || "".equals(deLaboratorio)) {
                        VariablesInventario.vCodigoProducto = coProducto;
                        deLaboratorio = DBInventario.getDescLaboratorio();
                    }
                }
                if ((i > 1) && ((i + 1) % 36) == 1) {
                    numeralInt ++;
                    vPrint.pageBreak();
                    vPrint.printBold( AtuxPRNUtility.llenarBlancos(100) + pSerie + " - " + AtuxUtility.caracterIzquierda(String.valueOf(numeralInt), 7, "0"), true); //RUC
                    vPrint.printBold("                  ", true); //Direccion
                    vPrint.printBold("                  ", true); //Transportista
                    vPrint.printBold("                            Pedido: " + tiPedido + " - " + pNuSolicitudPedido, true);
                    vPrint.printLine("", true);
                    vPrint.printLine("", true);
                    vPrint.printLine("", true);
                    vPrint.deactivateCondensed();
                }

                String unidad = "";
                if (usarUnidadBase) {
                    unidad = (String) vTable.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_UN_PRODUCTO_DESTINO);
                } else {
                    if (((String) vTable.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_UN_FRACCION_DESTINO)).trim().length() == 0) {
                        unidad = (String) vTable.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_UN_PRODUCTO_DESTINO);
                    } else {
                        unidad = (String) vTable.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_UN_FRACCION_DESTINO);
                    }
                }

                vPrint.printCondensed("   " + AtuxPRNUtility.alinearIzquierda(coProducto, 7) + "          " +
                        AtuxPRNUtility.alinearIzquierda(((String) vTable.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_DE_PRODUCTO)).trim(), 39) + "   " +
                        AtuxPRNUtility.alinearIzquierda((unidad).trim(), 19) + "  " +
                        AtuxPRNUtility.alinearIzquierda(deLaboratorio, 30) + AtuxPRNUtility.llenarBlancos(7) + "     " +
                        AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(caAtendida, "###,##0"), 9), true);

            }
            vPrint.printLine("", true);
            vPrint.activateCondensed();
            vPrint.printBold(AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(nuRows, "###,##0"), 7)
                    + AtuxPRNUtility.llenarBlancos(115)
                    + AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(dblTotalCantEntera, "###,##0"), 10), true);
            vPrint.deactivateCondensed();
            vPrint.endPrintService();
        } catch (Exception sqlerr) {
            sqlerr.printStackTrace();
        }
    }


}
