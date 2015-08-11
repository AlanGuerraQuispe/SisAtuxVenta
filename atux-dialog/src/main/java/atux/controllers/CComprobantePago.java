package atux.controllers;

import atux.controllers.repository.AtuxException;
import atux.modelbd.DetallePedidoVenta;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CComprobantePago implements Cloneable {

    private final Log logger = LogFactory.getLog(getClass());

    private String tiComprobante;
    private String nuComprobantePago;    
    private String nuPedido;    
    private String noImpresoCliente;
    private String deDireccionCliente;
    private String idImpresionComprobante;
    private String feImpresionComprobante;
    private String idAnulaComprobante;
    private double vaTotalVenta;
    private double vaTotalDescuento;    
    private double vaTotalImpuesto;
    private double vaTotalPrecioVenta;
    private double vaSaldoRedondeo;
    private String nuNotaCreditoConv;
    private String nuTransfAnulConv;
    private String feTransfAnulConv;
    private String tiComprobantePrincipal;
    private String nuComprobantePrincipal;
    private String caItem;
    private String coFormaPagoConvenio;
    private double vaTotalPrecioVentaConvenio;
    
    List detalleComprobante;

    private String pagComprobante;
    private boolean incluyeIgv;
    private double igv;
    private String nombreVendedor;
    private boolean isVentaPorConvenio;
    private double porcentajePagoCliente;
    private double totalDctoConvenio;
    private double totalDctoFidelizacion;
    private double saldoACuenta;
    private double saldoPendiente;
    private double deducible;
    private String imprimeDcto = "";
    private String detalleImpresion = "";
    private String nombrePaciente = "";
    private String nonbreTitular = "";
    private boolean isComprobantePrincipal;
    private String deComprobante;
    private boolean imprimeDonacion;

    private String deInstitucion;

    private double donacion;

    private String redondeoPedido;
    private List donacionList=new ArrayList();

    private String mensajeComprobante = "";
    private String mensajeComprobanteDetail = "";

    private boolean variosDsctoConvenio = false;

    private String inComprobanteConvenio = new String(""); // B = Beneficiario   E = Empresa

    private String deVoucher = "";
    
    private String tiPedido="";
    private String nuComprobanteManual="";
    
    public String getMensajeComprobante() {
        return mensajeComprobante;
    }

    public void setMensajeComprobante(String mensajeComprobante) {
        this.mensajeComprobante = mensajeComprobante;
    }

    public String getMensajeComprobanteDetail() {
        return mensajeComprobanteDetail;
    }

    public void setMensajeComprobanteDetail(String mensajeComprobanteDetail) {
        this.mensajeComprobanteDetail = mensajeComprobanteDetail;
    }

    public CComprobantePago(List detalleComprobante) {
        this.detalleComprobante = detalleComprobante;
    }

    public String getNuComprobanteManual() {
        return nuComprobanteManual;
    }

    public void setNuComprobanteManual(String nuComprobanteManual) {
        this.nuComprobanteManual = nuComprobanteManual;
    }

    public String getTiPedido() {
        return tiPedido;
    }

    public void setTiPedido(String tiPedido) {
        this.tiPedido = tiPedido;
    }
    
    public String getTiComprobante() {
        return tiComprobante;
    }

    public void setTiComprobante(String tiComprobante) {
        this.tiComprobante = tiComprobante;
    }

    public String getNuComprobantePago() {
        return nuComprobantePago;
    }


    public void setNuComprobantePago(String nuComprobantePago) {
        this.nuComprobantePago = nuComprobantePago;
    }

    public String getNuPedido() {
        return nuPedido;
    }

    public void setNuPedido(String nuPedido) {
        this.nuPedido = nuPedido;
    }

    public String getNoImpresoCliente() {
        return noImpresoCliente;
    }

    public void setNoImpresoCliente(String noImpresoCliente) {
        this.noImpresoCliente = noImpresoCliente;
    }

    public String getDeDireccionCliente() {
        return deDireccionCliente;
    }

    public void setDeDireccionCliente(String deDireccionCliente) {
        this.deDireccionCliente = deDireccionCliente;
    }

    public String getIdImpresionComprobante() {
        return idImpresionComprobante;
    }

    public void setIdImpresionComprobante(String idImpresionComprobante) {
        this.idImpresionComprobante = idImpresionComprobante;
    }

    public String getFeImpresionComprobante() {
        return feImpresionComprobante;
    }

    public void setFeImpresionComprobante(String feImpresionComprobante) {
        this.feImpresionComprobante = feImpresionComprobante;
    }

    public String getIdAnulaComprobante() {
        return idAnulaComprobante;
    }

    public void setIdAnulaComprobante(String idAnulaComprobante) {
        this.idAnulaComprobante = idAnulaComprobante;
    }

    public double getVaTotalVenta() {
        return vaTotalVenta;
    }

    public void setVaTotalVenta(double vaTotalVenta) {
        this.vaTotalVenta = vaTotalVenta;
    }

    public double getVaTotalDescuento() {
        return vaTotalDescuento;
    }

    public void setVaTotalDescuento(double vaTotalDescuento) {
        this.vaTotalDescuento = vaTotalDescuento;
    }

    public double getVaTotalImpuesto() {
        return vaTotalImpuesto;
    }

    public void setVaTotalImpuesto(double vaTotalImpuesto) {
        this.vaTotalImpuesto = vaTotalImpuesto;
    }

    public double getVaTotalPrecioVenta() {
        return vaTotalPrecioVenta;
    }

    public void setVaTotalPrecioVenta(double vaTotalPrecioVenta) {
        this.vaTotalPrecioVenta = vaTotalPrecioVenta;
    }

    public double getVaSaldoRedondeo() {
        return vaSaldoRedondeo;
    }

    public void setVaSaldoRedondeo(double vaSaldoRedondeo) {
        this.vaSaldoRedondeo = vaSaldoRedondeo;
    }

    public String getNuNotaCreditoConv() {
        return nuNotaCreditoConv;
    }

    public void setNuNotaCreditoConv(String nuNotaCreditoConv) {
        this.nuNotaCreditoConv = nuNotaCreditoConv;
    }

    public String getNuTransfAnulConv() {
        return nuTransfAnulConv;
    }

    public void setNuTransfAnulConv(String nuTransfAnulConv) {
        this.nuTransfAnulConv = nuTransfAnulConv;
    }

    public String getFeTransfAnulConv() {
        return feTransfAnulConv;
    }

    public void setFeTransfAnulConv(String feTransfAnulConv) {
        this.feTransfAnulConv = feTransfAnulConv;
    }

    public String getTiComprobantePrincipal() {
        return tiComprobantePrincipal;
    }

    public void setTiComprobantePrincipal(String tiComprobantePrincipal) {
        this.tiComprobantePrincipal = tiComprobantePrincipal;
    }

    public String getNuComprobantePrincipal() {
        return nuComprobantePrincipal;
    }

    public void setNuComprobantePrincipal(String nuComprobantePrincipal) {
        this.nuComprobantePrincipal = nuComprobantePrincipal;
    }

    public String getCaItem() {
        return caItem;
    }

    public void setCaItem(String caItem) {
        this.caItem = caItem;
    }

    public String getCoFormaPagoConvenio() {
        return coFormaPagoConvenio;
    }

    public void setCoFormaPagoConvenio(String coFormaPagoConvenio) {
        this.coFormaPagoConvenio = coFormaPagoConvenio;
    }

    public List getDetalleComprobante() {
        return detalleComprobante;
    }

    public void setDetalleComprobante(List detalleComprobante) {
        this.detalleComprobante = detalleComprobante;
    }

    public String getPagComprobante() {
        return pagComprobante;
    }

    public void setPagComprobante(String pagComprobante) {
        this.pagComprobante = pagComprobante;
    }

    public boolean isIncluyeIgv() {
        return incluyeIgv;
    }

    public void setIncluyeIgv(boolean incluyeIgv) {
        this.incluyeIgv = incluyeIgv;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public void isVentaPorConvenio(boolean flag) {
        isVentaPorConvenio = flag;
    }

    public void setPorcentajePagoCliente(double porcentajePagoCliente) {
        this.porcentajePagoCliente = porcentajePagoCliente;
    }

    public double getTotalDctoConvenio() {
        return totalDctoConvenio;
    }

    public void setTotalDctoConvenio(double totalDctoConvenio) {
        this.totalDctoConvenio = totalDctoConvenio;
    }

    public double getTotalDctoFidelizacion() {
        return totalDctoFidelizacion;
    }

    public void setTotalDctoFidelizacion(double totalDctoFidelizacion) {
        this.totalDctoFidelizacion = totalDctoFidelizacion;
    }

    public double getSaldoACuenta() {
        return saldoACuenta;
    }

    public void setSaldoACuenta(double saldoACuenta) {
        this.saldoACuenta = saldoACuenta;
    }

    public double getSaldoPendiente() {
        return saldoPendiente;
    }

    public void setSaldoPendiente(double saldoPendiente) {
        this.saldoPendiente = saldoPendiente;
    }

    public String getImprimeDcto() {
        return imprimeDcto;
    }

    public void setImprimeDcto(String imprimeDcto) {
        this.imprimeDcto = imprimeDcto;
    }

    public String getDetalleImpresion() {
        return detalleImpresion;
    }

    public void setDetalleImpresion(String detalleImpresion) {
        this.detalleImpresion = detalleImpresion;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public String getgetNombrePacienteTicketera() {

        if (nombrePaciente.length() > 25)
            return nombrePaciente.substring(0, 24);
        else
            return nombrePaciente;

    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getNonbreTitular() {
        return nonbreTitular;
    }

    public String getNonbreTitularTicketera() {
        if (nonbreTitular.length() > 25)
            return nonbreTitular.substring(0, 24);
        else
            return nonbreTitular;

    }

    public void setNonbreTitular(String nonbreTitular) {
        this.nonbreTitular = nonbreTitular;
    }

    public double getDeducible() {      
      return AtuxUtility.getDecimalNumber(AtuxVariables.vVaMontoCoPagoCliente);
    }

    public void setDeducible(double deducible) {
        this.deducible = deducible;
    }

    public boolean isVentaPorConvenio() {
        return isVentaPorConvenio;
    }

    public boolean isComprobantePrincipal() {
        return isComprobantePrincipal;
    }

    public void setComprobantePrincipal(boolean comprobantePrincipal) {
        isComprobantePrincipal = comprobantePrincipal;
    }

    public String getDeComprobante() {
        return deComprobante;
    }

    public void setDeComprobante(String deComprobante) {
        this.deComprobante = deComprobante;
    }

    public boolean isImprimeDonacion() {
        return imprimeDonacion;
    }

    public void setImprimeDonacion(boolean imprimeDonacion) {
        this.imprimeDonacion = imprimeDonacion;
    }

    public String getDeInstitucion() {
        return deInstitucion;
    }

    public void setDeInstitucion(String deInstitucion) {
        this.deInstitucion = deInstitucion;
    }

    public double getDonacion() {
        return donacion;
    }

    public void setDonacion(double donacion) {
        this.donacion = donacion;
    }

    public String getRedondeoPedido() {
        return redondeoPedido;
    }

    public void setRedondeoPedido(String redondeoPedido) {
        this.redondeoPedido = redondeoPedido;
    }


    public List getDonacionList() {
        return donacionList;
    }

    public void setDonacionList(List donacionList) {
        this.donacionList = donacionList;
    }

    public boolean isVariosDsctoConvenio() {
        return variosDsctoConvenio;
    }

    public void setVariosDsctoConvenio(boolean variosDsctoConvenio) {
        this.variosDsctoConvenio = variosDsctoConvenio;
    }
    
    public String getInComprobanteConvenio() {
        return inComprobanteConvenio;
    }

    public void setInComprobanteConvenio(String inComprobanteConvenio) {
        this.inComprobanteConvenio = inComprobanteConvenio;
    }

    public String getDeVoucher() {
        return deVoucher;
    }

    public void setDeVoucher(String deVoucher) {
        this.deVoucher = deVoucher;
    }
            
    public String getFechaBD() {
        String fecha = "";
        try {
            fecha = AtuxSearch.getFechaHoraMinutoBD(AtuxVariables.FORMATO_FECHA_HORA);
        } catch (SQLException e) {
            throw new AtuxException(e.getMessage());
        }

        return fecha;
    }

    public void calcularRedondeo(double pRedondeo) {
        vaSaldoRedondeo = pRedondeo;
    }

    public void calcularTotales() {

        logger.info("-- Inicio del proceso del proceso de calulo");        

        if (getTiPedido().equalsIgnoreCase(AtuxVariables.TIPO_PEDIDO_MANUAL))
            nuComprobantePago = getNuComprobanteManual();
        else
            nuComprobantePago = getNuComprobantePagoFromDB();

        double tmpVaTotalVenta = 0;
        double tmpVaTotalPrecioVenta = 0;

        for (int i = 0; i < detalleComprobante.size(); i++) {
            DetallePedidoVenta item = (DetallePedidoVenta) detalleComprobante.get(i);

            if (item.getInProductoPrincipal().equals("S")) {
                tmpVaTotalVenta += (item.getVaVenta() * item.getCaAtendida());
                tmpVaTotalPrecioVenta += item.getVaPrecioVenta();
            }

        }

        // precio bruto
        vaTotalVenta = incluyeIgv ? AtuxUtility.getDecimalNumberRedondeado(tmpVaTotalVenta / igv) :
                AtuxUtility.getDecimalNumberRedondeado(tmpVaTotalVenta);

        // descuento
        vaTotalDescuento = incluyeIgv ? AtuxUtility.getDecimalNumberRedondeado(tmpVaTotalVenta / igv) - AtuxUtility.getDecimalNumberRedondeado(tmpVaTotalPrecioVenta / igv) :
                AtuxUtility.getDecimalNumberRedondeado(tmpVaTotalVenta) - AtuxUtility.getDecimalNumberRedondeado(tmpVaTotalPrecioVenta);

        // igv
        vaTotalImpuesto = incluyeIgv ? tmpVaTotalPrecioVenta - AtuxUtility.getDecimalNumberRedondeado(tmpVaTotalPrecioVenta / igv) : 0;

        // precio venta
        vaTotalPrecioVenta = tmpVaTotalPrecioVenta;

        // redondeo
        vaSaldoRedondeo = 0;

        String mensaje = "Suma de los comprobantes: " + tmpVaTotalVenta + " y " + tmpVaTotalPrecioVenta + "\n";
        mensaje += " IGV?            : " + incluyeIgv +       "\n";
        mensaje += " IGV             : " + igv +              "\n";
        mensaje += " Precio Bruto    : " + vaTotalVenta +     "\n";
        mensaje += " Total Descuento : " + vaTotalDescuento + "\n";
        mensaje += " Total Impuesto  : " + vaTotalImpuesto  + "\n";
        mensaje += " Precio Venta    : " + vaTotalPrecioVenta+"\n";
        mensaje += " Redondeo        : " + vaSaldoRedondeo;

        logger.info(mensaje);
        
        calcularDescuentos();

        if (AtuxVariables.vInComprobanteManual.equalsIgnoreCase("S"))
                System.out.println("NO ACTUALIZA NADA PORQUE ES TICKET MANUAL");
        else actualizarNumeracion();

        logger.info("-- Fin del proceso del proceso de calulo");
    }

    private void calcularDescuentos() {
        double cantidadAtendida = 0.00;
        double pcDctoBaseLocal = 0.00;
        //double pcDctoUnConvenio = 0.00;
        double precioBaseProducto = 0.00;
        double precioVentaSinDcto = 0.00;
        double precioVentaPublico = 0.00;
        //double dsctoInkClub = 0.00;

        for (int i = 0; i < detalleComprobante.size(); i++) {
            String mensaje = "";
            mensaje = "Datos de Descuentos por Convenio: \n";

            DetallePedidoVenta detallePedido = (DetallePedidoVenta) detalleComprobante.get(i);

            if (detallePedido.getInProductoPrincipal().equals("S")) {
                    precioBaseProducto = detallePedido.getVaVenta();
                    pcDctoBaseLocal = detallePedido.getPcDescuentoBaseLocal();
                    precioVentaSinDcto = AtuxUtility.getDecimalNumberRedondeado(precioBaseProducto * (1 - pcDctoBaseLocal / 100));

                    mensaje += " Precio Base Producto : " + precioBaseProducto + "\n";
                    mensaje += " Descuento Base Local : " + pcDctoBaseLocal + "\n";
                    mensaje += " Precio Venta Sin Descuento : " + precioVentaSinDcto + "\n";
                    mensaje += " Precio Venta Sin Descuento (Dscto. InkaClub) : " + precioVentaSinDcto + "\n";
    //            mensaje += " Descuento InkaClub : " + dsctoInkClub + "\n";

    //            pcDctoUnConvenio = detallePedido.getPcDescuentoConvenio();
                    precioVentaPublico = detallePedido.getVaPrecioPublico();
                    cantidadAtendida = detallePedido.getCaAtendida();

    //            mensaje += " Descuento Convenio : " + pcDctoUnConvenio + "\n";
                    mensaje += " Precio Venta Publico : " + precioVentaPublico + "\n";
                    mensaje += " Cantidad Atendida : " + cantidadAtendida + "\n";
            }

            logger.warn(mensaje);
        }
    }
    
    @Override
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        
        str.append("Comprobante : ");
        str.append("nuComprobante <").append(nuComprobantePago).append("> ");
        str.append("tiComprobante <").append(tiComprobante).append("> ");
        str.append("vaTotalVenta <").append(vaTotalVenta).append("> ");
        str.append("vaTotalPrecioVenta <").append(vaTotalPrecioVenta).append("> ");
        str.append("vaTotalPrecioVentaConvenio <").append(vaTotalPrecioVentaConvenio).append("> ");
        str.append("vaTotalDescuento <").append(vaTotalDescuento).append("> ");
        str.append("vaTotalImpuesto <").append(vaTotalImpuesto).append("> ");
        str.append("isVentaConvenio<").append(isVentaPorConvenio).append("> ");
        str.append("saldoACuenta<").append(saldoACuenta).append("> ");
        str.append("saldoPendiente<").append(saldoPendiente).append("> ");
        str.append("formaPagoConvenio<").append(coFormaPagoConvenio).append("> ");
        str.append("tiComprobantePrincipal<").append(tiComprobantePrincipal).append("> ");
        str.append("nuComprobantePrincipal<").append(nuComprobantePrincipal).append("> ");
        str.append("inComprobanteConvenio<").append(inComprobanteConvenio).append("> ");

        return str.toString();
    }

    public void grabar() {
        logger.info("-- Inicio del proceso del grabacion del comprobante");
        try {
            ArrayList parametros = new ArrayList();
            parametros.add(AtuxVariables.vCodigoCompania);
            parametros.add(AtuxVariables.vCodigoLocal);
            parametros.add(AtuxVariables.vNumeroPedido);
            parametros.add(tiComprobante);
            parametros.add(nuComprobantePago);
            parametros.add(AtuxVariables.vNuSecUsuario);
            parametros.add(AtuxVariables.vNuCaja);
            parametros.add(AtuxVariables.vNuTurno);
            parametros.add(new Double(vaTotalVenta));     
            parametros.add(new Double(vaTotalDescuento)); 
            parametros.add(new Double(vaTotalImpuesto));  
            parametros.add(new Double(vaTotalPrecioVenta));
            parametros.add(new Double(vaTotalPrecioVentaConvenio));
            parametros.add(new Double(vaSaldoRedondeo));

            logger.info("GRABAR_VTTV_COMPROBANTE_PAGO: NuPedido<"+nuPedido+">,NuComprobante<"+nuComprobantePago+">, InComprobantePago <"+inComprobanteConvenio+">");

            parametros.add(tiComprobante.equals(AtuxVariables.TIPO_BOLETA) ? new Double(0.00) : new Double(vaTotalPrecioVenta));
            double vaTotalAfecto = vaTotalVenta - vaTotalDescuento;
            parametros.add(new Double(AtuxUtility.getDecimalNumber(AtuxUtility.formatNumber(vaTotalAfecto))));
            parametros.add(new Integer(detalleComprobante.size()));
            parametros.add(AtuxVariables.vIdUsuario);
            parametros.add(inComprobanteConvenio);
            logger.info("Grabando " + this);
            AtuxDBUtility.executeSQLStoredProcedure(null, "PTOVTA_CAJA.GRABAR_COMPROBANTE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", parametros, false);
            
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw new AtuxException(e.getMessage());
        }
        logger.info("-- Fin del proceso del grabacion del comprobante");
    }
    
    public void actualizarDetallePedido() {
        logger.info("-- Inicio del proceso del actualizacion del detalle de Pedido");
        try {
            StringBuilder items = new StringBuilder();
            
            for (int i = 0; i < detalleComprobante.size(); i++) {
                if (i != 0) items.append(",");
                logger.info("--agregando item Nro " + ((DetallePedidoVenta) detalleComprobante.get(i)).getNuItemPedido() + " del pedido");
                items.append(((DetallePedidoVenta) detalleComprobante.get(i)).getNuItemPedido());                
            }
            
            AtuxDBUtility.executeSQLUpdate(getQueryActualizaPedido(items.toString()), false);                        

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new AtuxException("Error al actualizar detalle del Pedido");
        }
        logger.info("-- Fin del proceso del actualizacion del detalle de Pedido");
    }

    private String getQueryActualizaPedido(String nuItemsPedidos) {
        StringBuilder str = new StringBuilder();
        str.append("UPDATE VTTD_PEDIDO_VENTA");
        str.append("   SET TI_COMPROBANTE = '").append(tiComprobante).append("',");
        str.append("       NU_COMPROBANTE_PAGO = '").append(nuComprobantePago).append("',");
        str.append("       IN_REPLICA = 0,");
        str.append("   FE_MOD_DET_PEDIDO_VENTA = SYSDATE ");
        str.append(" WHERE CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("'");
        str.append("   AND CO_LOCAL = '").append(AtuxVariables.vCodigoLocal).append("'");
        str.append("   AND NU_PEDIDO = '").append(nuPedido).append("'");
        str.append("   AND NU_ITEM_PEDIDO IN (").append(nuItemsPedidos).append(")");
        return str.toString();
    }

    private String getNuComprobantePagoFromDB() {
        String numero = "";
        try {
           
            if (tiComprobante.equalsIgnoreCase(AtuxVariables.TIPO_BOLETA) || tiComprobante.equalsIgnoreCase(AtuxVariables.TIPO_TICKET_BOLETA)) {
                numero = AtuxSearch.getNumeracionComprobante(AtuxVariables.vNuImpresoraBoleta);
           
            } else if (tiComprobante.equalsIgnoreCase(AtuxVariables.TIPO_FACTURA) || tiComprobante.equalsIgnoreCase(AtuxVariables.TIPO_TICKET_FACTURA)) {
                numero = AtuxSearch.getNumeracionComprobante(AtuxVariables.vNuImpresoraFactura);
            } else if (tiComprobante.equalsIgnoreCase(AtuxVariables.TIPO_GUIA)) {
                numero = AtuxSearch.getNumeracionComprobante(AtuxVariables.vNuImpresoraGuia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new AtuxException(e.getMessage());
        }
        return numero;
    }

    public void actualizarNumeracion() {
        try {
            if (tiComprobante.equalsIgnoreCase(AtuxVariables.TIPO_BOLETA) || tiComprobante.equalsIgnoreCase(AtuxVariables.TIPO_TICKET_BOLETA)) {
                AtuxSearch.setNumeracionComprobante(AtuxVariables.vNuImpresoraBoleta);
             
            } else if (tiComprobante.equalsIgnoreCase(AtuxVariables.TIPO_FACTURA) || tiComprobante.equalsIgnoreCase(AtuxVariables.TIPO_TICKET_FACTURA))
                AtuxSearch.setNumeracionComprobante(AtuxVariables.vNuImpresoraFactura);
            else if (tiComprobante.equalsIgnoreCase(AtuxVariables.TIPO_GUIA)) {
                AtuxSearch.setNumeracionComprobante(AtuxVariables.vNuImpresoraGuia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new AtuxException(e.getMessage());
        }
    }   

    public String getDeConvenioVenta() {
        try {
            return AtuxDBUtility.getValueAt("CVTM_CONVENIO", "NVL(DE_CORTA_CONVENIO,' ')", "CO_FORMA_PAGO = '" + coFormaPagoConvenio + "'");
        } catch (SQLException sqlerr) {
            logger.error("Error al obtener descripcion convenio de venta  - ", sqlerr);
            sqlerr.printStackTrace();
            return "";
        }
    }

    private String getQueryActualizaDetalleCambioProducto(List parametros) {
        StringBuilder str = new StringBuilder();
        str.append("UPDATE VTTD_PEDIDO_VENTA ");
        str.append("   SET TI_COMPROBANTE_CAMBIO  = '").append(parametros.get(1)).append("',");
        str.append("       NU_COMPROBANTE_CAMBIO  = '").append(parametros.get(2)).append("',");
        str.append("       ID_MOD_DET_PEDIDO_VENTA = '").append(AtuxVariables.vIdUsuario).append("', ");
        str.append("       FE_MOD_DET_PEDIDO_VENTA = SYSDATE ");
        str.append(" WHERE CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("'");
        str.append("   AND CO_LOCAL = '").append(AtuxVariables.vCodigoLocal).append("'");
        str.append("   AND NU_PEDIDO = '").append(nuPedido).append("'");
        str.append("   AND NU_ITEM_PEDIDO =").append(parametros.get(0)).append(" ");
        return str.toString();
    }

    public void quitarRedondedo() {
        vaTotalPrecioVenta-=vaSaldoRedondeo;
    }
}
