package atux.util.print.builder;

import atux.modelbd.DetallePedidoVenta;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.util.print.AtuxPRNUtility;
import atux.util.print.AtuxPrint;
import atux.util.print.builder.ComprobanteBuilder;
import atux.util.print.builder.Message;
import com.atux.bean.donacion.IngresarDonacionDetalle;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class TicketCreditoBuilder extends ComprobanteBuilder {

    double totalDescuentoTarjeta;
    
    @Override
    public void printTiTle(AtuxPrint vPrint) {
        
    }
    
    @Override
    public void printHeader(AtuxPrint vPrint) {
        
        if (AtuxVariables.vInComprobanteManual.equalsIgnoreCase("S")) {
            vPrint.printLine("----------------------------------------", true);
            vPrint.printLine("* REGULARIZACION DE COMPROBANTE MANUAL *", true);
            vPrint.printLine("        " + (super.tipoComprobante.equalsIgnoreCase(AtuxVariables.TIPO_BOLETA_MANUAL) ? "BOLETA" : "FACTURA") + " Nro " + comprobantePago.getNuComprobantePago().substring(0, 3) + "-" + comprobantePago.getNuComprobantePago().substring(3, 10), true);
            vPrint.printLine("----------------------------------------", true);
        }
                
        vPrint.printBold(AtuxPRNUtility.alinearIzquierda(" ", 13) +AtuxPRNUtility.alinearIzquierda(AtuxVariables.vDescripcionCompania, 25), true);
        vPrint.printBold(AtuxPRNUtility.alinearIzquierda(" ", 6) + "MENOR PRECIO Y MEJOR SERVICIO ", true);
        vPrint.printBold(AtuxPRNUtility.alinearIzquierda(" ", 14)+ "PARA TU SALUD", true);        
        
        vPrint.printLine(AtuxPRNUtility.alinearIzquierda(AtuxPRNUtility.alinearIzquierda(" ", 3)+"TEL: "+AtuxVariables.vCompaniaFono, 18) + AtuxPRNUtility.alinearDerecha("RUC: " + AtuxVariables.vNuRucCompania, 17), true);
        
        vPrint.printLine("  " + AtuxPRNUtility.alinearIzquierda(AtuxVariables.vCompaniaDireccion, 35), true);        

        vPrint.printLine(AtuxPRNUtility.alinearIzquierda(" ", 9)+"Local: " +AtuxVariables.vCodigoLocal+"-"+ AtuxVariables.vDescripcionLocal, true);

        vPrint.printLine(AtuxVariables.vDireccionLocal +" "+ AtuxVariables.vDistritoLocal +"-"+AtuxVariables.vProvinciaLocal, true); 
        vPrint.printLine(" ", true);

        if (AtuxVariables.vInComprobanteManual.equalsIgnoreCase("N")) {
            vPrint.printLine(AtuxPRNUtility.alinearIzquierda("Ticket de pedido a Crédito ", 35), true);
        }
        else{
            vPrint.printLine(AtuxPRNUtility.alinearIzquierda("Serie: " + AtuxVariables.vDeImpresoraBoleta, 40), true);            
        }

        vPrint.printLine("CORR. " + AtuxVariables.vNumeroPedido + "            Pedido:" + AtuxVariables.vNumeroPedidoDiario, true);
        try {
            vPrint.printLine("FECHA:" + AtuxSearch.getFechaHoraBD(AtuxVariables.FORMATO_FECHA) + " CAJA:" + AtuxPRNUtility.alinearDerecha(AtuxVariables.vNuCaja, 2) + " TURNO:" + AtuxPRNUtility.alinearDerecha(AtuxVariables.vNuTurno, 2), true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (AtuxVariables.vANombreDe.trim().length() != 0) {
            vPrint.printLine(AtuxPRNUtility.alinearIzquierda("Cliente: " + AtuxVariables.vANombreDe.trim(), 40), true);
        }

    }
    
    @Override
    public void printColumnHeader(AtuxPrint vPrint) {

    }
    
    @Override
    public void printDetail(AtuxPrint vPrint) {

    }
    
    @Override
    public void printPageFooter(AtuxPrint vPrint) {

        vPrint.printLine("----------------------------------------", true);
        for (int i = 0; i < tblPagos.getRowCount(); i++)
            vPrint.printLine((String) tblPagos.getValueAt(i, 1) + " " + (String) tblPagos.getValueAt(i, 3) + " (" + (String) tblPagos.getValueAt(i, 2) + ")", true);
        
        vPrint.printLine("VUELTO:  S/. " + vuelto, true);
        vPrint.printLine("CAJERO: " + AtuxVariables.vNoUsuario.trim() + " " + AtuxVariables.vPaternoUsuario.trim(), true);
        vPrint.printLine(" ", true);

//        if (mensajesAux.size() > 0) {
//            Iterator iterator = mensajesAux.iterator();
//            while (iterator.hasNext()) {
//                Message mensajes = new Message((String) iterator.next());
//                mensajes.readFileProperties();
//                List lineas = mensajes.getListaLineas();
//                for (int i = 0; i < lineas.size(); i++) {
//                    vPrint.printLine((String) lineas.get(i), true);
//                }
//                vPrint.printLine(" ", true);
//            }
//        }
//
//        if (StringUtils.isNotEmpty(comprobantePago.getDeVoucher())) {
//            vPrint.printLine(comprobantePago.getDeVoucher(), true);
//        }
        
        vPrint.printLine(AtuxPRNUtility.alinearIzquierda(" ", 7) + "NO HAY DEVOLUCION DE DINERO.", true);
        vPrint.printLine(AtuxPRNUtility.alinearIzquierda(" ", 0)+ "TODO CAMBIO DE MERCADERIA SE HARA DENTRO", true);
        vPrint.printLine(AtuxPRNUtility.alinearIzquierda(" ", 0) + "DE LAS 48 HORAS PREVIA PRESENTACION DEL", true);
        vPrint.printLine(AtuxPRNUtility.alinearIzquierda(" ", 0) + "COMPROBANTE Y VERIFICACION POR PARTE DEL", true);
        vPrint.printLine(AtuxPRNUtility.alinearIzquierda(" ", 10) + "QUIMICO FARMACEUTICO", true);
        vPrint.printLine(" ", true);
        
        vPrint.printLine(AtuxPRNUtility.alinearIzquierda(" ", 10)+AtuxVariables.vDeMensajeTicket.trim(), true);
        if (AtuxVariables.vCompaniaDireccionWeb.trim().length() > 0)
            vPrint.printLineSinEspacio(AtuxPRNUtility.alinearIzquierda(" ", 12) + AtuxVariables.vCompaniaDireccionWeb.trim(), true);
    }
    
    @Override
    public void printFooter(AtuxPrint vPrint) {       
        for(int u=0;u<=3;u++){            
            vPrint.printLine("\n", true);
        }
        
        if (AtuxVariables.vCortaTicket.equals("S")){
            vPrint.printLine((char) (27) + "i", true); //Para el corte
        }        
    }

}
