package atux.util.print.builder;

import atux.modelbd.DetallePedidoVenta;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.util.print.AtuxPRNUtility;
import atux.util.print.AtuxPrint;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import com.atux.bean.donacion.IngresarDonacionDetalle;
import org.apache.commons.lang.StringUtils;

public class TicketBuilder extends ComprobanteBuilder {

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
            vPrint.printLine(AtuxPRNUtility.alinearIzquierda("Serie: " + AtuxVariables.vDeImpresoraBoleta, 40), true);
            vPrint.printLine(AtuxPRNUtility.alinearIzquierda("Ticket Nro " + comprobantePago.getNuComprobantePago().substring(0, 3) + "-" + comprobantePago.getNuComprobantePago().substring(3, 10), 35), true);
        }
        else{
            vPrint.printLine(AtuxPRNUtility.alinearIzquierda("Serie: " + AtuxVariables.vDeImpresoraBoleta, 40), true);            
        }

        vPrint.printLine("CORR. " + AtuxVariables.vNumeroPedido + "            Pedido:" + AtuxVariables.vNumeroPedidoDiario, true);
        vPrint.printLine("FECHA:" + comprobantePago.getFechaBD() + " CAJA:" + AtuxPRNUtility.alinearDerecha(AtuxVariables.vNuCaja, 2) + " TURNO:" + AtuxPRNUtility.alinearDerecha(AtuxVariables.vNuTurno, 2), true);

        if (AtuxVariables.vANombreDe.trim().length() != 0) {
            vPrint.printLine(AtuxPRNUtility.alinearIzquierda("Cliente: " + AtuxVariables.vANombreDe.trim(), 40), true);
        }

        if (comprobantePago.getMensajeComprobanteDetail() != null && comprobantePago.getMensajeComprobanteDetail().trim().length() > 0) {
            vPrint.printLine("------------------------------------------", true);
            vPrint.printLine(comprobantePago.getMensajeComprobanteDetail(), true);
            vPrint.printLine("------------------------------------------", true);
        }
    }
    
    @Override
    public void printColumnHeader(AtuxPrint vPrint) {
        vPrint.printLine(" ", true);        
        vPrint.printLine(" Cant.  Descripcion             Importe", true);        
        vPrint.printLine("----------------------------------------", true);
    }
    
    @Override
    public void printDetail(AtuxPrint vPrint) {
        boolean pEsConvenio       = false;
        String  pImprimeDescuento = "N";        
        String  descripcionAdicional;

        List detalle = comprobantePago.getDetalleComprobante();
        numeroLineas = 0;

        for (int i = 0; i < detalle.size(); i++) {
            DetallePedidoVenta detallePedido = (DetallePedidoVenta) detalle.get(i);

            descripcionAdicional = "";            
            double precioMostrar = 0;

            precioMostrar = detallePedido.getVaPrecioPublico();                    
            
            double precioUnitarioIncrementado = precioMostrar;            
            
            double totalItem = 0;
            totalItem = detallePedido.getInProductoPrincipal().equals("S") ? (detallePedido.getCaAtendida() * precioUnitarioIncrementado):0;

//            if (detallePedido.getCoProducto().equals("421003")) {
//                if (totalItem != (comprobantePago.getVaTotalPrecioVenta() - comprobantePago.getVaSaldoRedondeo())) {
//                    totalItem = comprobantePago.getVaTotalPrecioVenta() - comprobantePago.getVaSaldoRedondeo();
//                }
//
//                if (totalItem != 0) {
//                    totalItem += comprobantePago.getTotalDctoConvenio();
//                }
//            }
     
            totalDescuentoTarjeta = totalDescuentoTarjeta + totalItem;

            descripcionAdicional += detallePedido.getProdLocal().getProducto().getDeProducto();

            if(detallePedido.getInProductoPrincipal().equals("S")){

                if (descripcionAdicional.length() > 26) {
                    vPrint.printLine(
                            AtuxPRNUtility.alinearDerecha(detallePedido.getCaAtendida(), 2) +
                                    (detallePedido.getVaFraccion() > 1 ? "F" : " ") + " " +
                                    AtuxPRNUtility.alinearIzquierda(descripcionAdicional, 28) + " " +
                                    AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(totalItem), 6), true);

                    vPrint.printLine("                      " +
                            AtuxPRNUtility.alinearIzquierda(detallePedido.getDeUnidadProducto(), 3) + "/" +
                            AtuxPRNUtility.alinearIzquierda(detallePedido.getProdLocal().getProducto().getLaboratorio().getDeLaboratorio(), 6), true);

                } else {
                    vPrint.printLine(
                            AtuxPRNUtility.alinearDerecha(detallePedido.getCaAtendida(), 2) +
                                    (detallePedido.getVaFraccion() > 1 ? "F" : " ") + " " +
                                    AtuxPRNUtility.alinearIzquierda(detallePedido.getProdLocal().getProducto().getDeProducto(), 26) + " " +
                                    AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(totalItem), 6), true);

                    vPrint.printLine("                      " +
                            AtuxPRNUtility.alinearIzquierda(detallePedido.getDeUnidadProducto(), 3) + "/" +
                            AtuxPRNUtility.alinearIzquierda(detallePedido.getProdLocal().getProducto().getLaboratorio().getDeLaboratorio(), 6), true);
                }
            }
            else{
                vPrint.printLine(AtuxPRNUtility.alinearDerecha("  ", 2) +
                        AtuxPRNUtility.alinearIzquierda(detallePedido.getProdLocal().getProducto().getDeProducto(), 26) + " ", true);
            }
            numeroLineas++;
        }
    }
    
    @Override
    public void printPageFooter(AtuxPrint vPrint) {

        vPrint.printLine("----------------------------------------", true);
        if (comprobantePago.isImprimeDonacion()) {

            vPrint.printLine(AtuxPRNUtility.alinearDerecha("Total S/. ", 30), false);
            vPrint.printLine(AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(comprobantePago.getVaTotalPrecioVenta() - comprobantePago.getVaSaldoRedondeo()), 9), true);
            BigDecimal totalDonacion=BigDecimal.ZERO;
            for (int i = 0; i < comprobantePago.getDonacionList().size(); i++) {
                IngresarDonacionDetalle donacionDetalle = (IngresarDonacionDetalle) comprobantePago.getDonacionList().get(i);
                vPrint.printLine(AtuxPRNUtility.alinearDerecha("DONACION A " + donacionDetalle.getDonacion().getDeCortaInstitucion() + " S/. ", 30), false);
                vPrint.printLine(AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(donacionDetalle.getMonto().doubleValue()), 9), true);
                totalDonacion=totalDonacion.add(donacionDetalle.getMonto());
            }

            vPrint.printLine(AtuxPRNUtility.alinearDerecha("Redo: S/.", 29), false);
            vPrint.printLine(AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(comprobantePago.getVaSaldoRedondeo()), 10), true);

            vPrint.printLine(AtuxPRNUtility.alinearDerecha("Total S/. ", 30), false);
            vPrint.printLine(AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(comprobantePago.getVaTotalPrecioVenta() + totalDonacion.doubleValue() /*+ vttvComprobantePago.getVaSaldoRedondeo()*/), 9), true);

        }else{
            vPrint.printLine(AtuxPRNUtility.alinearDerecha("Redo : S/.", 30), false);
            vPrint.printLine(AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(comprobantePago.getVaSaldoRedondeo()), 10), true);
            vPrint.printLine(AtuxPRNUtility.alinearDerecha("Total S/. ", 30), false);
            vPrint.printLine(AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(comprobantePago.getVaTotalPrecioVenta()), 10), true);

        }

        for (int i = 0; i < tblPagos.getRowCount(); i++)
            vPrint.printLine((String) tblPagos.getValueAt(i, 1) + " " + (String) tblPagos.getValueAt(i, 3) + " (" + (String) tblPagos.getValueAt(i, 2) + ")", true);
        
        vPrint.printLine("VUELTO:  S/. " + vuelto, true);
        vPrint.printLine("CAJERO: " + AtuxVariables.vNoUsuario.trim() + " " + AtuxVariables.vPaternoUsuario.trim(), true);
        vPrint.printLine("VENDED: " + comprobantePago.getNombreVendedor() + ((AtuxVariables.vTipoPedido.equalsIgnoreCase(AtuxVariables.TIPO_PEDIDO_DELIVERY)) ? " -DELIVERY-" : " "), true);
        vPrint.printLine(" ", true);

        if (mensajesAux.size() > 0) {
            Iterator iterator = mensajesAux.iterator();
            while (iterator.hasNext()) {
                Message mensajes = new Message((String) iterator.next());
                mensajes.readFileProperties();
                List lineas = mensajes.getListaLineas();
                for (int i = 0; i < lineas.size(); i++) {
                    vPrint.printLine((String) lineas.get(i), true);
                }
                vPrint.printLine(" ", true);
            }
        }
        
        if (StringUtils.isNotEmpty(comprobantePago.getDeVoucher())) {
            vPrint.printLine(comprobantePago.getDeVoucher(), true);
        }
        
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
