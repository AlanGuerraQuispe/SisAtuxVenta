package atux.util.print.builder;

import atux.modelbd.DetallePedidoVenta;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.util.print.AtuxPRNUtility;
import atux.util.print.AtuxPrint;
import com.atux.bean.donacion.IngresarDonacionDetalle;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

public class FacturaBuilder extends ComprobanteBuilder {

    double totalDescuentoTarjeta;
    
    @Override
    public void printTiTle(AtuxPrint vPrint) {
        
    }
    
    @Override
    public void printHeader(AtuxPrint vPrint) {
        String fechaBD = comprobantePago.getFechaBD();
        String dia = fechaBD.substring(0, 2);
        String mesLetra = AtuxUtility.devuelveMesEnLetras(Integer.parseInt(fechaBD.substring(3, 5)));
        String ano  = fechaBD.substring(6, 10);
        String hora = fechaBD.substring(11, 13);
        vPrint.activateCondensed();
        vPrint.printLine(" ", true);
        vPrint.printLine(" ", true);
        vPrint.printLine(AtuxPRNUtility.llenarBlancos(50) + "CORR." + comprobantePago.getNuPedido(), true);
        vPrint.printLine(AtuxPRNUtility.llenarBlancos(17) + AtuxPRNUtility.alinearIzquierda(AtuxVariables.vANombreDe.trim(), 70) + AtuxPRNUtility.llenarBlancos(10) + "Nuevo Ruc : " + AtuxVariables.vNuRucCompania, true);
        vPrint.printLine(AtuxPRNUtility.llenarBlancos(17) + AtuxVariables.vDireccion.trim(), true);
        vPrint.printLine(AtuxPRNUtility.llenarBlancos(12) + "     " + AtuxVariables.vRUCFacturar.trim() + AtuxPRNUtility.llenarBlancos(35) + "No. " + comprobantePago.getNuComprobantePago().substring(0, 3) + "-" + comprobantePago.getNuComprobantePago().substring(3, 10), true);
        vPrint.printLine(AtuxPRNUtility.llenarBlancos(17) + dia + " de " + mesLetra + " del " + ano + "     " + hora, true);
        vPrint.printLine(" ", true);
        vPrint.printLine(" ", true);
    }
    
    @Override
    public void printColumnHeader(AtuxPrint vPrint) {
        
    }
    
    @Override
    public void printDetail(AtuxPrint vPrint) {

        numeroLineas = 0;        

        List detalle = comprobantePago.getDetalleComprobante();
        for (int i = 0; i < detalle.size(); i++) {

            DetallePedidoVenta vttdPedidoVenta = (DetallePedidoVenta) detalle.get(i);

            //if (vttdPedidoVenta.getCoProducto().equals(ServicioPedido.CODIGO_PRODUCTO_DONACION))
            //return;            
            
            double precioMostrar = 0;

//            if (AtuxVariables.vTipoPedido.equals(AtuxVariables.TIPO_PEDIDO_ESPECIAL)) {
//                precioMostrar = vttdPedidoVenta.getVaPrecioPublico();
//            } else {
//                if (vttdPedidoVenta.getCaAtendida() >= 0) {
//                    precioMostrar = vttdPedidoVenta.getVaPrecioBaseLocal();
//                } else {
//                    precioMostrar = vttdPedidoVenta.getVaPrecioPublico();
//                }
//            }
            
            precioMostrar = vttdPedidoVenta.getVaPrecioPublico();
            double precioUnitarioIncrementado = 0.0;
            precioUnitarioIncrementado = precioMostrar;
            
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
                
            vPrint.printLine(" " +
                    AtuxPRNUtility.alinearDerecha(vttdPedidoVenta.getCoProducto(), 12) + " " +
                    
                    AtuxPRNUtility.alinearDerecha(vttdPedidoVenta.getCaAtendida(), 7) + 
                    (vttdPedidoVenta.getVaFraccion() > 1 ? "F" : " ") + "  " +                    

                    AtuxPRNUtility.alinearIzquierda(((vttdPedidoVenta.getInProductoPlan() != null && vttdPedidoVenta.getInProductoPlan().equals("S")) ? vttdPedidoVenta.getDeMensajePromocion() + "-" : "") + ((vttdPedidoVenta.getInAcumulaPromo() != null && vttdPedidoVenta.getInAcumulaPromo().equals("S")) ? "*ACUM-" : "") + vttdPedidoVenta.getProdLocal().getProducto().getDeProducto(), 40) + " " +
                    AtuxPRNUtility.alinearIzquierda(vttdPedidoVenta.getDeUnidadProducto(), 20) + " " +
                    AtuxPRNUtility.alinearIzquierda(vttdPedidoVenta.getProdLocal().getProducto().getLaboratorio().getDeLaboratorio(), 13) + AtuxPRNUtility.llenarBlancos(3) +
                    AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(precioUnitarioIncrementado), 8) + AtuxPRNUtility.llenarBlancos(7) +
                    AtuxPRNUtility.alinearDerecha((comprobantePago.getImprimeDcto().equals("S") ? vttdPedidoVenta.getPcDescuento_1() + "%" : "      "), 7) + " " +
                    AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(totalItem), 10) + " ", true);
            numeroLineas++;
 
        }
    }
    
    @Override
    public void printPageFooter(AtuxPrint vPrint) {
        for (int j = numeroLineas; j <= numeroMaximoItems - lineasMensaje; j++) vPrint.printLine(" ", true);
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
            BigDecimal totalDonacion = BigDecimal.ZERO;
            for (int i = 0; i < comprobantePago.getDonacionList().size(); i++) {
                IngresarDonacionDetalle donacionDetalle = (IngresarDonacionDetalle) comprobantePago.getDonacionList().get(i);
                mensajeDonacion.append("Gracias x donar a ").append(donacionDetalle.getDonacion().getDeCortaInstitucion());
                totalDonacion = totalDonacion.add(donacionDetalle.getMonto());
            }
            montoDonacion.append(AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(totalDonacion.doubleValue()), 10));
            montoDonacion.append("      Total         S/. ").append(AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(totalDonacion.doubleValue()), 10));
        }


        String mensajeMonto = AtuxPRNUtility.montoEnLetras(AtuxUtility.getDecimalNumber(AtuxUtility.formatNumber(comprobantePago.getVaTotalPrecioVenta())));

        vPrint.printLine(" SON: " + AtuxPRNUtility.alinearIzquierda(mensajeMonto, 108), true);

        vPrint.printLine(" REDO:"   + AtuxUtility.formatNumber(comprobantePago.getVaSaldoRedondeo()) +
                         " CAJERO:" + AtuxVariables.vNoUsuario.trim() + " " + AtuxVariables.vPaternoUsuario.trim() + " " +
                         " CAJA:"   + AtuxVariables.vNuCaja +
                         " TURNO:"  + AtuxVariables.vNuTurno +
                         " VEND:"   + comprobantePago.getNombreVendedor() + ((comprobantePago.getTiComprobante().equalsIgnoreCase(AtuxVariables.TIPO_PEDIDO_DELIVERY)) ? " -DELIVERY-" : " "), true);

        if (comprobantePago.getMensajeComprobanteDetail() != null && comprobantePago.getMensajeComprobante() != null) {
            String mensajePuntos = comprobantePago.getMensajeComprobanteDetail().length() > 80 ?
                    comprobantePago.getMensajeComprobante() : comprobantePago.getMensajeComprobanteDetail();
            vPrint.printLine(mensajePuntos, true);
        } else {
            vPrint.printLine(" ", true);
        }

        vPrint.printLine(" ", true);
        vPrint.printLine(" ", true);
        vPrint.printLine("     " +
                "00000" + AtuxPRNUtility.llenarBlancos(13) +
                AtuxPRNUtility.alinearDerecha((comprobantePago.getVaTotalVenta() < 0 || comprobantePago.getVaTotalDescuento() < 0) ? "" : AtuxUtility.formatNumber(comprobantePago.getVaTotalVenta()), 10) +
                AtuxPRNUtility.llenarBlancos(10) +
                AtuxPRNUtility.alinearDerecha((comprobantePago.getVaTotalVenta() < 0 || comprobantePago.getVaTotalDescuento() < 0) ? "" :AtuxUtility.formatNumber(comprobantePago.getVaTotalDescuento()), 10) +
                AtuxPRNUtility.llenarBlancos(10) +
                AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(comprobantePago.getVaTotalVenta() - comprobantePago.getVaTotalDescuento()), 10) +
                AtuxPRNUtility.llenarBlancos(12) +
                AtuxPRNUtility.alinearDerecha(String.valueOf(AtuxVariables.vIgvPorcentaje), 6) +
                AtuxPRNUtility.llenarBlancos(12) +
                AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(comprobantePago.getVaTotalImpuesto()), 10) +
                AtuxPRNUtility.llenarBlancos(8) +
                "S/. " +
                AtuxPRNUtility.alinearDerecha(AtuxUtility.formatNumber(comprobantePago.getVaTotalPrecioVenta()), 10), true);

        vPrint.printLine(" ", true);
        vPrint.printLine(" ", true);       

        vPrint.deactivateCondensed();

    }
}
