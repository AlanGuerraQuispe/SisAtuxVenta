package atux.util.print.builder;

import atux.controllers.repository.AtuxException;
import atux.controllers.CComprobantePago;
import atux.modelbd.ComprobantePago;
import atux.modelbd.DetallePedidoVenta;
import atux.modelbd.PedidoVenta;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.util.print.AtuxPrintTicket;
import atux.vistas.venta.caja.INumeroComprobanteManual;
import com.atux.bean.donacion.IngresarDonacionDetalle;
import com.atux.comun.context.AppCtx;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;


public class ServicioEmisionComprobantes {

    private final String BEAN_VTTD_PEDIDO_VENTA = "DetallePedidoVenta";

    private List detallePedido;

    PedidoVenta pedido;

    int espacioLineas;

    /**
     * Establece el numero maximos de items por comprobante
     */
    private int numeroItemsPorComprobante;

    private JTable tblPagos;
    private String vuelto;

    private List listaComprobanteConvenio;

    private List comprobantesConvenio;

    private boolean isVentaPorConvenio;

    private double saldoCliente;

    private double porcentajePagoCliente;


    private String deInstitucion;

    private List listaInstituciones;

    private double donacion;

    private String redondeoPedido = "0.00";

    private Frame parentFrame;

    private boolean convenioMayorista;

    private final Log logger = LogFactory.getLog(getClass());

    // mensajes promocion
    private String mensajeComprobante = "";
    private String mensajeComprobanteDetails = "";
    private String mensajeChequeCupon = "";

    private boolean isPedidoCanjePromocion;

    private boolean recalcularRedondeo = true;

    private String deFormasPago = "";

    private boolean promocionCupon = false;

    private List listVouchersDebito;

    private List listVouchersCredito;

    private List listRefrendos;
    private List<IngresarDonacionDetalle> donacionList;

    /**
     * Constructores
     */
    public ServicioEmisionComprobantes(PedidoVenta pedido, List detallePedido, Set detalleRecargaVirtual, List listaProductosVirutales, String pDeFormasPago,
                                       ArrayList pPedidoProducto, List pFormasPago) {
        this.pedido = pedido;
        // Obtenemos los item del Pedido en forma de beans.
        this.detallePedido = getItemsBean(detallePedido, BEAN_VTTD_PEDIDO_VENTA);
        //this.detallePedidoRecargaVirtaul = detalleRecargaVirtual;
        //this.listaProductosVirtuales = listaProductosVirutales;
        deFormasPago = pDeFormasPago;

        loadImpresionesPagosElectronicos(pFormasPago);
    }

    private void loadImpresionesPagosElectronicos(List pFormasPago) {
        listRefrendos = new ArrayList();
        listVouchersCredito = new ArrayList();
        listVouchersDebito = new ArrayList();

        for (int i = 0; i < pFormasPago.size(); i++) {
            ArrayList temp = (ArrayList) pFormasPago.get(i);

            if (StringUtils.isNotEmpty(((String) temp.get(26)).trim())) {
                listRefrendos.add(temp.get(26));
            }

            if (StringUtils.isNotEmpty(((String) temp.get(27)).trim())) {
                if (((String) temp.get(27)).indexOf("FIRMA") > -1) {
                    listVouchersCredito.add(temp.get(27));
                } else {
                    listVouchersDebito.add(temp.get(27));
                }
            }
        }

        System.out.println("Formas de Pago: " + pFormasPago.size());
        System.out.println("Refrendos: " + listRefrendos.size());
        System.out.println("Creditos: " + listVouchersCredito.size());
        System.out.println("Debitos: " + listVouchersDebito.size());
    }

    public void ejecutar() {
        logger.info("-- Inicio del Servicio de Emision de Comprobantes --");

        try {
            if (pedido.getTiComprobante().equals(AtuxVariables.TIPO_BOLETA) || pedido.getTiComprobante().equals(AtuxVariables.TIPO_TICKET_BOLETA))
                espacioLineas = 100;
            else {
                espacioLineas = 130;
            }

            List listaDeComprobantesPrincipales = obtenerListaDeComprobantes();

            calcularTotales(listaDeComprobantesPrincipales, 100, true);


            asignarVoucher(listaDeComprobantesPrincipales);

            asignarRedondeo(listaDeComprobantesPrincipales);

            if (donacionList.size() > 0) {
                logger.info("El pedido cuenta con donaciones");
                asignarDonacion(listaDeComprobantesPrincipales);
            }

            procesarComprobantes(pedido.getTiComprobante(), listaDeComprobantesPrincipales);

            AtuxUtility.setCajaTurno();

            if (donacionList.size() > 0) {
                CComprobantePago comprobantePago=(CComprobantePago) listaDeComprobantesPrincipales.get(listaDeComprobantesPrincipales.size() - 1);
                asignarComprobanteDonacion(pedido.getNuPedido(), (CComprobantePago) listaDeComprobantesPrincipales.get(listaDeComprobantesPrincipales.size() - 1));
                logger.info("Donacion asignada al comprobante <"+comprobantePago.getNuComprobantePago()+">");
            }

            AtuxSearch.updatePedido(pedido.getNuPedido());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new AtuxException(e.getMessage());
        }

        logger.info("-- Fin del Servicio de Emision de Comprobantes --");
    }

    private void asignarComprobanteDonacion(String nuPedido, CComprobantePago comprobantePago) throws SQLException {
        String queryUpdate = "UPDATE VTTR_PEDIDO_DONACION " +
                "   SET NU_COMPROBANTE_PAGO = '" + comprobantePago.getNuComprobantePago() + "', " +
                "    TI_PEDIDO = '" + comprobantePago.getTiPedido() + "'" +
                " WHERE CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" +
                "   AND CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "'" +
                "   AND NU_PEDIDO = '" + nuPedido + "'";
        AtuxDBUtility.executeSQLUpdate(queryUpdate, false);
        logger.info("Se Asigno el comprobante a la donacion en BD ");
    }

    private void asignarDonacion(List listaComprobantes) {
        if (listaComprobantes.size() == 0)
            return;

        CComprobantePago comprobante = (CComprobantePago) listaComprobantes.get(listaComprobantes.size() - 1);
        logger.info("Ajustando totales por donacion del comprobante - antes VaTotalPrecioVenta <"+comprobante.getVaTotalPrecioVenta()+"> redondedo <"+comprobante.getVaSaldoRedondeo()+">");
        comprobante.quitarRedondedo();
        comprobante.setDonacionList(donacionList);
        comprobante.setImprimeDonacion(true);
        comprobante.calcularRedondeo(AtuxUtility.getDecimalNumber(redondeoPedido));
        logger.info("Ajustando totales por donacion del comprobante - despues VaTotalPrecioVenta <"+comprobante.getVaTotalPrecioVenta()+"> redondedo <"+comprobante.getVaSaldoRedondeo()+">");
    }


    private void asignarVoucher(List listaComprobantes) {
        if (listaComprobantes.isEmpty()) {
            return;
        }

        CComprobantePago comprobante = (CComprobantePago) listaComprobantes.get(listaComprobantes.size() - 1);

        if (comprobante.getTiComprobante().equals(AtuxVariables.TIPO_TICKET_BOLETA) || comprobante.getTiComprobante().equals(AtuxVariables.TIPO_TICKET_FACTURA)) {
            System.out.println("Asignando la impresion de vouchers");
            comprobante.setDeVoucher(listRefrendoToString());
        }
    }

    private String listRefrendoToString() {
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < listRefrendos.size(); i++) {
            buffer.append(StringUtils.repeat("*", 42)).append("\n");
            buffer.append(listRefrendos.get(i));
        }

        if (StringUtils.isNotEmpty(buffer.toString())) {
            buffer.append(StringUtils.repeat("*", 42));
        }

        return buffer.toString();
    }

    private void printPagosElectronicos(List pDatosImprimir, String pRutaImpresora, boolean pIncluirCabecera) {
        for (int i = 0; i < pDatosImprimir.size(); i++) {
            String imprimir = (String) pDatosImprimir.get(i);

            if (pIncluirCabecera) {
                imprimir = "Local: " + AtuxVariables.vDescripcionLocal + "\n" + "Dirección:" + AtuxVariables.vDireccionLocal + "\n" + imprimir;
            }

            printRefrendoVoucher(imprimir, pRutaImpresora);
        }
    }

    private void printRefrendoVoucher(String pContenido, String pRutaImpresora) {
        AtuxPrintTicket print = new AtuxPrintTicket(pRutaImpresora);

        if (!print.startPrintService()) {
            throw new AtuxException("Error en Impresora. Verifique !!!");
        }

        print.printLine(pContenido, true);

        print.endPrintService();
    }

    /*
     * En esta funcion podemos indicar cuales son comprobantes principales o secundarios
     */
    private void calcularTotales(List lista, double porcentaje, boolean flag) {
        for (int i = 0; i < lista.size(); i++) {
            CComprobantePago comprobantePago = (CComprobantePago) lista.get(i);
            {
                comprobantePago.setTiComprobante(pedido.getTiComprobante());
            }
            comprobantePago.setComprobantePrincipal(flag);
            if (pedido.getTiPedido().equalsIgnoreCase(AtuxVariables.TIPO_PEDIDO_MANUAL)) {
                comprobantePago.setTiPedido(pedido.getTiPedido());
                showNumeroComprobanteManual(pedido.getTiComprobante());
                comprobantePago.setNuComprobanteManual(pedido.getNuComprobanteManual());
            }
            comprobantePago.calcularTotales();
        }
    }

    private void showNumeroComprobanteManual(String pTiComprobantePago) {
        INumeroComprobanteManual iNumeroComprobanteManual = new INumeroComprobanteManual(this.parentFrame,
                pTiComprobantePago.equals(AtuxVariables.TIPO_BOLETA_MANUAL) ? "Ticket Manual [BOLETA]" : "Ticket Manual [FACTURA O GUIA]",
                true);
        iNumeroComprobanteManual.setTiComprobante(pTiComprobantePago);
        iNumeroComprobanteManual.setVisible(true);

        if (AtuxVariables.vAceptar) {
            pedido.setNuComprobanteManual(iNumeroComprobanteManual.getComprobanteManual());
        } else throw new AtuxException("ERROR EN EL COMPROBANTE INGRESADO");
    }

    private void procesarComprobantes(String tipoComprobante, List listaDeComprobantes) throws SQLException {
        ComprobanteBuilder comprobantebuilder = null;
        String rutaImpresora = null;
        int espacios = 0;
        boolean usarTicketera = false;

        if (tipoComprobante.equals(AtuxVariables.TIPO_TICKET_BOLETA) || tipoComprobante.equals(AtuxVariables.TIPO_BOLETA_MANUAL)) {
            comprobantebuilder = new TicketBuilder();
            comprobantebuilder.setTblPagos(tblPagos);
            comprobantebuilder.setVuelto(vuelto);
            comprobantebuilder.setTipoComprobante(tipoComprobante);
            espacios = 0;
            usarTicketera = true;
            rutaImpresora = AtuxVariables.vImpresoraTicketBoleta;//rutaImpresora = "C://PRUEBAS//TICKET_BOLETA.txt";
        } else if (tipoComprobante.equals(AtuxVariables.TIPO_BOLETA) || tipoComprobante.equals(AtuxVariables.TIPO_BOLETA_MANUAL)) {
            comprobantebuilder = new BoletaBuilder();
            espacios = 24;
            rutaImpresora = AtuxVariables.vImpresoraBoleta;      //rutaImpresora = "C://PRUEBAS//BOLETA.txt";
        } else if (tipoComprobante.equals(AtuxVariables.TIPO_FACTURA) || tipoComprobante.equals(AtuxVariables.TIPO_TICKET_FACTURA)
                || tipoComprobante.equals(AtuxVariables.TIPO_FACTURA_MANUAL)) {
            if (tipoComprobante.equals(AtuxVariables.TIPO_TICKET_FACTURA)) {
                usarTicketera = true;
            }
            comprobantebuilder = new FacturaBuilder();
            rutaImpresora = AtuxVariables.vImpresoraFactura;    //rutaImpresora = "C://PRUEBAS//FACTURA_MANUAL.txt";
            espacios = 36;
        } else if (tipoComprobante.equals(AtuxVariables.TIPO_GUIA)) {
            comprobantebuilder = new GuiaBuilder();
            rutaImpresora = AtuxVariables.vImpresoraGuia;
            espacios = 66;
        } else {
            return;
        }


        comprobantebuilder.setNumeroMaximoItems(numeroItemsPorComprobante);
        comprobantebuilder.setEspacioLineas(espacioLineas);
        String nombreVendedor = AtuxSearch.getNombreVendedor();

        ComprobanteGenerator reporteador = new ComprobanteGenerator(comprobantebuilder, rutaImpresora, espacios);
        reporteador.setTicketera(usarTicketera);

        logger.info("Se va a usar la " + (usarTicketera ? "TICKETERA" : "IMPRESORA"));

        for (int i = 0; i < listaDeComprobantes.size(); i++) {

            CComprobantePago comprobantePago = (CComprobantePago) listaDeComprobantes.get(i);
            comprobantePago.setNuPedido(pedido.getNuPedido());
            comprobantePago.setTiComprobante(tipoComprobante);
            comprobantePago.setNombreVendedor(nombreVendedor);

            if (i == listaDeComprobantes.size() - 1) {
                comprobantePago.setMensajeComprobante(mensajeComprobante);
                comprobantePago.setMensajeComprobanteDetail(mensajeComprobanteDetails);
            }

            comprobantePago.grabar();
            comprobantePago.actualizarDetallePedido();

            comprobantebuilder.setComprobantePago(comprobantePago);
            comprobantebuilder.limpiarVariables();
            comprobantebuilder.setPedidoCanje(isPedidoCanjePromocion);
            reporteador.generarComprobante();
        }
    }

    private List obtenerListaDeComprobantes() {
        List listaItemConIgv = new ArrayList();
        List listaItemSinIgv = new ArrayList();

        for (int i = 0; i < detallePedido.size(); i++) {
            DetallePedidoVenta ped = ((DetallePedidoVenta) detallePedido.get(i));

                if (ped.getCoImpuesto_1().equals(AtuxVariables.IMPUESTO_IGV)) {
                    listaItemConIgv.add(ped);
                } else if (ped.getCoImpuesto_1().equals(AtuxVariables.IMPUESTO_EXONERADO)) {
                    listaItemSinIgv.add(ped);

            }
        }

        List listaDeComprobantes = new ArrayList();

        logger.info("Existen <" + listaItemConIgv.size() + "> con IGV");
        if (!listaItemConIgv.isEmpty())
            listaDeComprobantes.addAll(obtenerComprobantes(listaItemConIgv, true));


        logger.info("Existen <" + listaItemSinIgv.size() + "> exonerados de IGV");
        if (!listaItemSinIgv.isEmpty())
            listaDeComprobantes.addAll(obtenerComprobantes(listaItemSinIgv, false));

        return listaDeComprobantes;
    }

    private void asignarRedondeo(List listaComprobantes) {
        double totalTotal = 0.00;
        double redondeo = 0.00;
        double tempo = 0.00;
        CComprobantePago comprobante = null;

        for (int i = 0; i < listaComprobantes.size(); i++) {
            comprobante = (CComprobantePago) listaComprobantes.get(i);
            totalTotal += comprobante.getVaTotalPrecioVenta();
            redondeo += comprobante.getVaSaldoRedondeo();
            if (i + 1 < listaComprobantes.size()) tempo = totalTotal;
        }

        redondeo = AtuxUtility.getRedondeo(totalTotal);

        comprobante.setVaTotalPrecioVenta(totalTotal - tempo + redondeo);
        comprobante.setVaSaldoRedondeo(redondeo);
        logger.info("Asignando al ult. comprobante VaTotalPrecioVenta <"+(totalTotal - tempo + redondeo)+"> VaSaldoRedondeo <"+redondeo+">");
    }

    private List obtenerComprobantes(List listaItems, boolean incluyeIGV) {

        logger.info("-- Inicio del Proceso de creacion de comprobantes --");
        logger.info("se van a procesar <" + listaItems.size() + "> items ");

        List listaComprobantes = new ArrayList();

        ItemsBuilder itemsBuilder = new ItemsBuilder(listaItems);
        itemsBuilder.setNumItemsPorComprobante(numeroItemsPorComprobante);

        itemsBuilder.setTicketera(AtuxVariables.vInTicketBoleta.equals("S") || AtuxVariables.vInTicketFactura.equals("S"));

        itemsBuilder.ejecutar();
        List listaItemsPorComprobante = itemsBuilder.getItemsPorComprobante();

        logger.info("Se van a crear <" + listaItemsPorComprobante.size() + "> comprobantes");

        for (int i = 0; i < listaItemsPorComprobante.size(); i++) {
            CComprobantePago comprobantePago = new CComprobantePago((List) listaItemsPorComprobante.get(i));
            comprobantePago.setIncluyeIgv(incluyeIGV);
            comprobantePago.setIgv(AtuxVariables.vIgvCalculo);
            comprobantePago.setCoFormaPagoConvenio(AtuxVariables.vCoFormaPagoConvenio);
            listaComprobantes.add(comprobantePago);
        }

        logger.info("-- Fin del Proceso de creacion de comprobantes --");
        return listaComprobantes;
    }

    private List getItemsBean(List detalle, String tipo) {
        List lista = new ArrayList();
        for (int i = 0; i < detalle.size(); i++) {
            Object bean = new Object();

            if (tipo.equals(BEAN_VTTD_PEDIDO_VENTA)) {
                bean = ((DetallePedidoVenta) detalle.get(i));
            }

            lista.add(bean);
        }
        return lista;
    }

    public List getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(List detallePedido) {
        this.detallePedido = detallePedido;
    }

    public void setParentFrame(Frame pFrame) {
        this.parentFrame = pFrame;
    }

    public Frame getParentFrame() {
        return parentFrame;
    }

    public void setNumeroItemsPorComprobante(int numeroItemsPorComprobante) {
        this.numeroItemsPorComprobante = numeroItemsPorComprobante;
    }

    public void setTblPagos(JTable tblPagos) {
        this.tblPagos = tblPagos;
    }

    public void setVuelto(String vuelto) {
        this.vuelto = vuelto;
    }

    public void setDonacionList(List<IngresarDonacionDetalle> donacionList) {
        this.donacionList = donacionList;
    }

    public List<IngresarDonacionDetalle> getDonacionList() {
        return donacionList;
    }
}

