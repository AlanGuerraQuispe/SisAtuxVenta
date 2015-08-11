package atux.modelbd;

import atux.core.JAbstractModelBD;
import com.atux.bean.promocion.PromocionDetalle;
import com.aw.support.collection.ListUtils;
import org.apache.commons.functor.UnaryPredicate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;


public class PedidoVenta extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;

    public static final String nt = "VTTC_PEDIDO_VENTA";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_LOCAL","NU_PEDIDO"};
    public static final String COLUMNA_ACTIVO = "ES_PEDIDO_VENTA";

    public static final String[] TIPOSDOC = {"FACTURA","BOLETA","GUIA","NOTA DE PEDIDO","DUA"};
    public static final String[] ESTADOS = {"CANCELADO","PENDIENTE"};

    public static final String[]
            FULL_CAMPOS ={"CO_COMPANIA, CO_LOCAL, NU_PEDIDO, CO_VENDEDOR, TI_COMPROBANTE, CO_MONEDA,"+
            "TI_PEDIDO, FE_PEDIDO, CO_LOCAL_ATENCION, CA_ITEM, NU_RUC_CLIENTE,"+
            "NO_IMPRESO_CLIENTE, DE_DIRECCION_CLIENTE, NU_PUNTO_VENTA, NU_TURNO, IN_CUADRE_CAJA,"+
            "FE_CONFIRMA_CAJA, VA_TOTAL_VENTA, VA_TOTAL_DESCUENTO, VA_TOTAL_CARGO_TARJETA,"+
            "VA_TOTAL_IMPUESTO, VA_TOTAL_PRECIO_VENTA, VA_SALDO_REDONDEO,PC_DCTO_CLIENTE_ESPECIAL,"+
            "ID_IMPRIME_PEDIDO, FE_IMPRIME_PEDIDO, ID_ANULA_PEDIDO,FE_ANULA_PEDIDO,"+
            "CO_GRUPO_MOTIVO, CO_MOTIVO_ANULACION, NU_PEDIDO_DIARIO,NU_PEDIDO_UNIDO,"+
            "NU_PEDIDO_DELIVERY, IN_PEDIDO_ORIGEN_DV, CO_CLIENTE_PACIENTE,HO_SALIDA_MOTORIZADO,"+
            "FE_ENTREGA_DELIVERY, HO_RETORNO_MOTORIZADO,HO_CONFIRMACION,HO_TIEMPO_ESTIMADO,"+
            "CO_USUARIO_CONFIRM, CO_RUTEADOR,CO_MOTORIZADO,VA_TASA_CAMBIO, DE_OBSERV_PEDIDO,"+
            "DE_OBSERV_PAGO,NO_IMPRESO_COMPROBANTE,DE_DIRECCION_COMPROBANTE, IN_VB_QF,"+
            "NU_SEC_QUIMICO,ES_PEDIDO_VENTA,ID_CREA_PEDIDO_VENTA, FE_CREA_PEDIDO_VENTA,"+
            "ID_MOD_PEDIDO_VENTA,FE_MOD_PEDIDO_VENTA, IN_PEDIDO_ANULADO,CO_FORMA_PAGO_CONVENIO,"+
            "VA_CO_PAGO, CO_CAJERO, NU_SEC_MOVIMIENTO,VA_MONTO_COPAGO_CLIENTE,TI_ORDEN_COPAGO,"+
            "IN_TICKET_MANUAL,VA_TOTAL_COSTO_VENTA, VA_TOTAL_PRECIO_INKACLUB,"+
            "VA_SALDO_REDONDEO_INKCLUB,IN_PERMITE_CAMBIO"};

    public static final String[]
            CAMPOS_NO_ID = {"CO_VENDEDOR, TI_COMPROBANTE, CO_MONEDA,"+
            "TI_PEDIDO, FE_PEDIDO, CO_LOCAL_ATENCION, CA_ITEM, NU_RUC_CLIENTE,"+
            "NO_IMPRESO_CLIENTE, DE_DIRECCION_CLIENTE, NU_PUNTO_VENTA, NU_TURNO, IN_CUADRE_CAJA,"+
            "FE_CONFIRMA_CAJA, VA_TOTAL_VENTA, VA_TOTAL_DESCUENTO, VA_TOTAL_CARGO_TARJETA,"+
            "VA_TOTAL_IMPUESTO, VA_TOTAL_PRECIO_VENTA, VA_SALDO_REDONDEO,PC_DCTO_CLIENTE_ESPECIAL,"+
            "ID_IMPRIME_PEDIDO, FE_IMPRIME_PEDIDO, ID_ANULA_PEDIDO,FE_ANULA_PEDIDO,"+
            "CO_GRUPO_MOTIVO, CO_MOTIVO_ANULACION, NU_PEDIDO_DIARIO,NU_PEDIDO_UNIDO,"+
            "NU_PEDIDO_DELIVERY, IN_PEDIDO_ORIGEN_DV, CO_CLIENTE_PACIENTE,HO_SALIDA_MOTORIZADO,"+
            "FE_ENTREGA_DELIVERY, HO_RETORNO_MOTORIZADO,HO_CONFIRMACION,HO_TIEMPO_ESTIMADO,"+
            "CO_USUARIO_CONFIRM, CO_RUTEADOR,CO_MOTORIZADO,VA_TASA_CAMBIO, DE_OBSERV_PEDIDO,"+
            "DE_OBSERV_PAGO,NO_IMPRESO_COMPROBANTE,DE_DIRECCION_COMPROBANTE, IN_VB_QF,"+
            "NU_SEC_QUIMICO,ES_PEDIDO_VENTA,ID_CREA_PEDIDO_VENTA, FE_CREA_PEDIDO_VENTA,"+
            "ID_MOD_PEDIDO_VENTA,FE_MOD_PEDIDO_VENTA, IN_PEDIDO_ANULADO,CO_FORMA_PAGO_CONVENIO,"+
            "VA_CO_PAGO, CO_CAJERO, NU_SEC_MOVIMIENTO,VA_MONTO_COPAGO_CLIENTE,TI_ORDEN_COPAGO,"+
            "IN_TICKET_MANUAL,VA_TOTAL_COSTO_VENTA, VA_TOTAL_PRECIO_INKACLUB,"+
            "VA_SALDO_REDONDEO_INKCLUB,IN_PERMITE_CAMBIO"};

    public static final String
            CAMPOS_INSERTS[] ={"CO_COMPANIA","CO_LOCAL","NU_PEDIDO","TI_PEDIDO","CO_LOCAL_ATENCION","FE_PEDIDO","CA_ITEM",
            "VA_TOTAL_VENTA","VA_TOTAL_PRECIO_VENTA","NO_IMPRESO_CLIENTE","DE_DIRECCION_CLIENTE","VA_TOTAL_DESCUENTO",
            "VA_TOTAL_IMPUESTO","VA_SALDO_REDONDEO","IN_CUADRE_CAJA","VA_TASA_CAMBIO","TI_COMPROBANTE",
            "CO_MONEDA","CO_VENDEDOR","NU_RUC_CLIENTE","NO_IMPRESO_COMPROBANTE","DE_DIRECCION_COMPROBANTE",
            "DE_OBSERV_PEDIDO","IN_PEDIDO_ANULADO","ES_PEDIDO_VENTA","ID_CREA_PEDIDO_VENTA",
            "FE_CREA_PEDIDO_VENTA","NU_PEDIDO_DIARIO","NU_PUNTO_VENTA","NU_TURNO"};

    private String coCompania;
    private String coLocal;
    private String nuPedido;
    private String coClienteLocal;
    private String coVendedor;
    private String tiComprobante;
    private String coMoneda;
    private String tiPedido;
    private Date   fePedido;
    private String coLocalAtencion;
    private Integer caItem;
    private String nuRucCliente;
    private String noImpresoCliente;
    private String deDireccionCliente;
    private String nuPuntoVenta;
    private String nuTurno;
    private String inCuadreCaja;
    private Date   feConfirmaCaja;
    private Double vaTotalVenta;
    private Double vaTotalDescuento;
    private Double vaTotalCargoTarjeta;
    private Double vaTotalImpuesto;
    private Double vaTotalPrecioVenta;
    private Double vaSaldoRedondeo;
    private Double pcDctoClienteEspecial;
    private String idImprimePedido;
    private Date   feImprimePedido;
    private String idAnulaPedido;
    private Date   feAnulaPedido;
    private String coGrupoMotivo;
    private String coMotivoAnulacion;
    private String nuPedidoDiario;
    private String nuPedidoUnido;
    private String nuPedidoDelivery;
    private String inPedidoOrigenDv;
    private String coClientePaciente;
    private String hoSalidaMotorizado;
    private Date   feEntregaDelivery;
    private String hoRetornoMotorizado;
    private String hoConfirmacion;
    private String hoTiempoEstimado;
    private String coUsuarioConfirm;
    private String coRuteador;
    private String coMotorizado;
    private Double vaTasaCambio;
    private String deObservPedido;
    private String deObservPago;
    private String noImpresoComprobante;
    private String deDireccionComprobante;
    private String inVbQf;
    private String nuSecQuimico;
    private String esPedidoVenta;
    private String idCreaPedidoVenta;
    private Date   feCreaPedidoVenta;
    private String idModPedidoVenta;
    private Date   feModPedidoVenta;
    private String inPedidoAnulado;
    private String coFormaPagoConvenio;
    private Double vaCoPago;
    private String coCajero;
    private String nuSecMovimiento;
    private Double vaMontoCopagoCliente;
    private String tiOrdenCopago;
    private String inTicketManual;
    private Double vaTotalCostoVenta;
    private Double vaTotalPrecioInkaclub;
    private Double vaSaldoRedondeoInkclub;
    private String inPermiteCambio;

    private Proveedores idProveedor;
    private String idUsuario;
    private Moneda idMoneda;
    private Usuario usuario;
    private Double  montoCredito;

    private ArrayList<DetallePedidoVenta> dp;

    //Para comprobante manual
    private String nuComprobanteManual;
    private FormaPagoPedidoCredito dPcred;

    public PedidoVenta() {
        initBasic();
    }

    public PedidoVenta(String coVendedor, String tiComprobante, String coMoneda, String tiPedido, Date fePedido, String coLocalAtencion, Integer caItem, String nuRucCliente, String noImpresoCliente, String deDireccionCliente, String nuPuntoVenta, String nuTurno, String inCuadreCaja, Date feConfirmaCaja, Double vaTotalVenta, Double vaTotalDescuento, Double vaTotalCargoTarjeta, Double vaTotalImpuesto, Double vaTotalPrecioVenta, Double vaSaldoRedondeo, Double pcDctoClienteEspecial, String idImprimePedido, Date feImprimePedido, String idAnulaPedido, Date feAnulaPedido, String coGrupoMotivo, String coMotivoAnulacion, String nuPedidoDiario, String nuPedidoUnido, String nuPedidoDelivery, String inPedidoOrigenDv, String coClientePaciente, String hoSalidaMotorizado, Date feEntregaDelivery, String hoRetornoMotorizado, String hoConfirmacion, String hoTiempoEstimado, String coUsuarioConfirm, String coRuteador, String coMotorizado, Double vaTasaCambio, String deObservPedido, String deObservPago, String noImpresoComprobante, String deDireccionComprobante, String inVbQf, String nuSecQuimico, String esPedidoVenta, String idCreaPedidoVenta, Date feCreaPedidoVenta, String idModPedidoVenta, Date feModPedidoVenta, String inPedidoAnulado, String coFormaPagoConvenio, Double vaCoPago, String coCajero, String nuSecMovimiento, Double vaMontoCopagoCliente, String tiOrdenCopago, String inTicketManual, Double vaTotalCostoVenta, Double vaTotalPrecioInkaclub, Double vaSaldoRedondeoInkclub, String inPermiteCambio, Proveedores idProveedor, String idUsuario, Moneda idMoneda, Integer numeroItems, String observaciones, Integer igv) {
        this.coVendedor = coVendedor;
        this.tiComprobante = tiComprobante;
        this.coMoneda = coMoneda;
        this.tiPedido = tiPedido;
        this.fePedido = fePedido;
        this.coLocalAtencion = coLocalAtencion;
        this.caItem = caItem;
        this.nuRucCliente = nuRucCliente;
        this.noImpresoCliente = noImpresoCliente;
        this.deDireccionCliente = deDireccionCliente;
        this.nuPuntoVenta = nuPuntoVenta;
        this.nuTurno = nuTurno;
        this.inCuadreCaja = inCuadreCaja;
        this.feConfirmaCaja = feConfirmaCaja;
        this.vaTotalVenta = vaTotalVenta;
        this.vaTotalDescuento = vaTotalDescuento;
        this.vaTotalCargoTarjeta = vaTotalCargoTarjeta;
        this.vaTotalImpuesto = vaTotalImpuesto;
        this.vaTotalPrecioVenta = vaTotalPrecioVenta;
        this.vaSaldoRedondeo = vaSaldoRedondeo;
        this.pcDctoClienteEspecial = pcDctoClienteEspecial;
        this.idImprimePedido = idImprimePedido;
        this.feImprimePedido = feImprimePedido;
        this.idAnulaPedido = idAnulaPedido;
        this.feAnulaPedido = feAnulaPedido;
        this.coGrupoMotivo = coGrupoMotivo;
        this.coMotivoAnulacion = coMotivoAnulacion;
        this.nuPedidoDiario = nuPedidoDiario;
        this.nuPedidoUnido = nuPedidoUnido;
        this.nuPedidoDelivery = nuPedidoDelivery;
        this.inPedidoOrigenDv = inPedidoOrigenDv;
        this.coClientePaciente = coClientePaciente;
        this.hoSalidaMotorizado = hoSalidaMotorizado;
        this.feEntregaDelivery = feEntregaDelivery;
        this.hoRetornoMotorizado = hoRetornoMotorizado;
        this.hoConfirmacion = hoConfirmacion;
        this.hoTiempoEstimado = hoTiempoEstimado;
        this.coUsuarioConfirm = coUsuarioConfirm;
        this.coRuteador = coRuteador;
        this.coMotorizado = coMotorizado;
        this.vaTasaCambio = vaTasaCambio;
        this.deObservPedido = deObservPedido;
        this.deObservPago = deObservPago;
        this.noImpresoComprobante = noImpresoComprobante;
        this.deDireccionComprobante = deDireccionComprobante;
        this.inVbQf = inVbQf;
        this.nuSecQuimico = nuSecQuimico;
        this.esPedidoVenta = esPedidoVenta;
        this.idCreaPedidoVenta = idCreaPedidoVenta;
        this.feCreaPedidoVenta = feCreaPedidoVenta;
        this.idModPedidoVenta = idModPedidoVenta;
        this.feModPedidoVenta = feModPedidoVenta;
        this.inPedidoAnulado = inPedidoAnulado;
        this.coFormaPagoConvenio = coFormaPagoConvenio;
        this.vaCoPago = vaCoPago;
        this.coCajero = coCajero;
        this.nuSecMovimiento = nuSecMovimiento;
        this.vaMontoCopagoCliente = vaMontoCopagoCliente;
        this.tiOrdenCopago = tiOrdenCopago;
        this.inTicketManual = inTicketManual;
        this.vaTotalCostoVenta = vaTotalCostoVenta;
        this.vaTotalPrecioInkaclub = vaTotalPrecioInkaclub;
        this.vaSaldoRedondeoInkclub = vaSaldoRedondeoInkclub;
        this.inPermiteCambio = inPermiteCambio;
        this.idProveedor = idProveedor;
        this.idUsuario = idUsuario;
        this.idMoneda = idMoneda;
        initBasic();
    }

    private void initBasic()
    {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
    }

    public Integer getCaItem() {
        return caItem;
    }

    public void setCaItem(Integer caItem) {
        this.caItem = caItem;
    }

    public String getCoCajero() {
        return coCajero;
    }

    public void setCoCajero(String coCajero) {
        this.coCajero = coCajero;
    }

    public String getCoClientePaciente() {
        return coClientePaciente;
    }

    public void setCoClientePaciente(String coClientePaciente) {
        this.coClientePaciente = coClientePaciente;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoFormaPagoConvenio() {
        return coFormaPagoConvenio;
    }

    public void setCoFormaPagoConvenio(String coFormaPagoConvenio) {
        this.coFormaPagoConvenio = coFormaPagoConvenio;
    }

    public String getCoGrupoMotivo() {
        return coGrupoMotivo;
    }

    public void setCoGrupoMotivo(String coGrupoMotivo) {
        this.coGrupoMotivo = coGrupoMotivo;
    }

    public String getCoLocal() {
        return coLocal;
    }

    public void setCoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public String getCoLocalAtencion() {
        return coLocalAtencion;
    }

    public void setCoLocalAtencion(String coLocalAtencion) {
        this.coLocalAtencion = coLocalAtencion;
    }

    public String getCoMoneda() {
        return coMoneda;
    }

    public void setCoMoneda(String coMoneda) {
        this.coMoneda = coMoneda;
    }

    public String getCoMotivoAnulacion() {
        return coMotivoAnulacion;
    }

    public void setCoMotivoAnulacion(String coMotivoAnulacion) {
        this.coMotivoAnulacion = coMotivoAnulacion;
    }

    public String getCoMotorizado() {
        return coMotorizado;
    }

    public void setCoMotorizado(String coMotorizado) {
        this.coMotorizado = coMotorizado;
    }

    public String getCoRuteador() {
        return coRuteador;
    }

    public void setCoRuteador(String coRuteador) {
        this.coRuteador = coRuteador;
    }

    public String getCoUsuarioConfirm() {
        return coUsuarioConfirm;
    }

    public void setCoUsuarioConfirm(String coUsuarioConfirm) {
        this.coUsuarioConfirm = coUsuarioConfirm;
    }

    public String getCoVendedor() {
        return coVendedor;
    }

    public void setCoVendedor(String coVendedor) {
        this.coVendedor = coVendedor;
    }

    public String getDeDireccionCliente() {
        return deDireccionCliente;
    }

    public void setDeDireccionCliente(String deDireccionCliente) {
        this.deDireccionCliente = deDireccionCliente;
    }

    public String getDeDireccionComprobante() {
        return deDireccionComprobante;
    }

    public void setDeDireccionComprobante(String deDireccionComprobante) {
        this.deDireccionComprobante = deDireccionComprobante;
    }

    public String getDeObservPago() {
        return deObservPago;
    }

    public void setDeObservPago(String deObservPago) {
        this.deObservPago = deObservPago;
    }

    public String getDeObservPedido() {
        return deObservPedido;
    }

    public void setDeObservPedido(String deObservPedido) {
        this.deObservPedido = deObservPedido;
    }

    public ArrayList<DetallePedidoVenta> getDetallePedidoVenta() {
        return dp;
    }

    public void setDetallePedidoVenta(ArrayList<DetallePedidoVenta> dp) {
        this.dp = dp;
    }

    public String getEsPedidoVenta() {
        return esPedidoVenta;
    }

    public void setEsPedidoVenta(String esPedidoVenta) {
        this.esPedidoVenta = esPedidoVenta;
    }

    public Date getFeAnulaPedido() {
        return feAnulaPedido;
    }

    public void setFeAnulaPedido(Date feAnulaPedido) {
        this.feAnulaPedido = feAnulaPedido;
    }

    public Date getFeConfirmaCaja() {
        return feConfirmaCaja;
    }

    public void setFeConfirmaCaja(Date feConfirmaCaja) {
        this.feConfirmaCaja = feConfirmaCaja;
    }

    public Date getFeCreaPedidoVenta() {
        return feCreaPedidoVenta;
    }

    public void setFeCreaPedidoVenta(Date feCreaPedidoVenta) {
        this.feCreaPedidoVenta = feCreaPedidoVenta;
    }

    public Date getFeEntregaDelivery() {
        return feEntregaDelivery;
    }

    public void setFeEntregaDelivery(Date feEntregaDelivery) {
        this.feEntregaDelivery = feEntregaDelivery;
    }

    public Date getFeImprimePedido() {
        return feImprimePedido;
    }

    public void setFeImprimePedido(Date feImprimePedido) {
        this.feImprimePedido = feImprimePedido;
    }

    public Date getFeModPedidoVenta() {
        return feModPedidoVenta;
    }

    public void setFeModPedidoVenta(Date feModPedidoVenta) {
        this.feModPedidoVenta = feModPedidoVenta;
    }

    public Date getFePedido() {
        return fePedido;
    }

    public void setFePedido(Date fePedido) {
        this.fePedido = fePedido;
    }

    public String getHoConfirmacion() {
        return hoConfirmacion;
    }

    public void setHoConfirmacion(String hoConfirmacion) {
        this.hoConfirmacion = hoConfirmacion;
    }

    public String getHoRetornoMotorizado() {
        return hoRetornoMotorizado;
    }

    public void setHoRetornoMotorizado(String hoRetornoMotorizado) {
        this.hoRetornoMotorizado = hoRetornoMotorizado;
    }

    public String getHoSalidaMotorizado() {
        return hoSalidaMotorizado;
    }

    public void setHoSalidaMotorizado(String hoSalidaMotorizado) {
        this.hoSalidaMotorizado = hoSalidaMotorizado;
    }

    public String getHoTiempoEstimado() {
        return hoTiempoEstimado;
    }

    public void setHoTiempoEstimado(String hoTiempoEstimado) {
        this.hoTiempoEstimado = hoTiempoEstimado;
    }

    public String getIdAnulaPedido() {
        return idAnulaPedido;
    }

    public void setIdAnulaPedido(String idAnulaPedido) {
        this.idAnulaPedido = idAnulaPedido;
    }

    public String getIdCreaPedidoVenta() {
        return idCreaPedidoVenta;
    }

    public void setIdCreaPedidoVenta(String idCreaPedidoVenta) {
        this.idCreaPedidoVenta = idCreaPedidoVenta;
    }

    public String getIdImprimePedido() {
        return idImprimePedido;
    }

    public void setIdImprimePedido(String idImprimePedido) {
        this.idImprimePedido = idImprimePedido;
    }

    public String getIdModPedidoVenta() {
        return idModPedidoVenta;
    }

    public void setIdModPedidoVenta(String idModPedidoVenta) {
        this.idModPedidoVenta = idModPedidoVenta;
    }

    public Moneda getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(Moneda idMoneda) {
        this.idMoneda = idMoneda;
    }

    public Proveedores getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedores idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getInCuadreCaja() {
        return inCuadreCaja;
    }

    public void setInCuadreCaja(String inCuadreCaja) {
        this.inCuadreCaja = inCuadreCaja;
    }

    public String getInPedidoAnulado() {
        return inPedidoAnulado;
    }

    public void setInPedidoAnulado(String inPedidoAnulado) {
        this.inPedidoAnulado = inPedidoAnulado;
    }

    public String getInPedidoOrigenDv() {
        return inPedidoOrigenDv;
    }

    public void setInPedidoOrigenDv(String inPedidoOrigenDv) {
        this.inPedidoOrigenDv = inPedidoOrigenDv;
    }

    public String getInPermiteCambio() {
        return inPermiteCambio;
    }

    public void setInPermiteCambio(String inPermiteCambio) {
        this.inPermiteCambio = inPermiteCambio;
    }

    public String getInTicketManual() {
        return inTicketManual;
    }

    public void setInTicketManual(String inTicketManual) {
        this.inTicketManual = inTicketManual;
    }

    public String getInVbQf() {
        return inVbQf;
    }

    public void setInVbQf(String inVbQf) {
        this.inVbQf = inVbQf;
    }

    public String getNoImpresoCliente() {
        return noImpresoCliente;
    }

    public void setNoImpresoCliente(String noImpresoCliente) {
        this.noImpresoCliente = noImpresoCliente;
    }

    public String getNoImpresoComprobante() {
        return noImpresoComprobante;
    }

    public void setNoImpresoComprobante(String noImpresoComprobante) {
        this.noImpresoComprobante = noImpresoComprobante;
    }

    public String getNuPedido() {
        return nuPedido;
    }

    public void setNuPedido(String nuPedido) {
        this.nuPedido = nuPedido;
    }

    public String getNuPedidoDelivery() {
        return nuPedidoDelivery;
    }

    public void setNuPedidoDelivery(String nuPedidoDelivery) {
        this.nuPedidoDelivery = nuPedidoDelivery;
    }

    public String getNuPedidoDiario() {
        return nuPedidoDiario;
    }

    public void setNuPedidoDiario(String nuPedidoDiario) {
        this.nuPedidoDiario = nuPedidoDiario;
    }

    public String getNuPedidoUnido() {
        return nuPedidoUnido;
    }

    public void setNuPedidoUnido(String nuPedidoUnido) {
        this.nuPedidoUnido = nuPedidoUnido;
    }

    public String getNuRucCliente() {
        return nuRucCliente;
    }

    public void setNuRucCliente(String nuRucCliente) {
        this.nuRucCliente = nuRucCliente;
    }

    public String getNuSecMovimiento() {
        return nuSecMovimiento;
    }

    public void setNuSecMovimiento(String nuSecMovimiento) {
        this.nuSecMovimiento = nuSecMovimiento;
    }

    public String getNuSecQuimico() {
        return nuSecQuimico;
    }

    public void setNuSecQuimico(String nuSecQuimico) {
        this.nuSecQuimico = nuSecQuimico;
    }

    public String getNuTurno() {
        return nuTurno;
    }

    public void setNuTurno(String nuTurno) {
        this.nuTurno = nuTurno;
    }

    public Double getPcDctoClienteEspecial() {
        return pcDctoClienteEspecial;
    }

    public void setPcDctoClienteEspecial(Double pcDctoClienteEspecial) {
        this.pcDctoClienteEspecial = pcDctoClienteEspecial;
    }

    public String getTiComprobante() {
        return tiComprobante;
    }

    public void setTiComprobante(String tiComprobante) {
        this.tiComprobante = tiComprobante;
    }

    public String getTiOrdenCopago() {
        return tiOrdenCopago;
    }

    public void setTiOrdenCopago(String tiOrdenCopago) {
        this.tiOrdenCopago = tiOrdenCopago;
    }

    public String getTiPedido() {
        return tiPedido;
    }

    public void setTiPedido(String tiPedido) {
        this.tiPedido = tiPedido;
    }

    public Double getVaCoPago() {
        return vaCoPago;
    }

    public void setVaCoPago(Double vaCoPago) {
        this.vaCoPago = vaCoPago;
    }

    public Double getVaMontoCopagoCliente() {
        return vaMontoCopagoCliente;
    }

    public void setVaMontoCopagoCliente(Double vaMontoCopagoCliente) {
        this.vaMontoCopagoCliente = vaMontoCopagoCliente;
    }

    public Double getVaSaldoRedondeo() {
        return vaSaldoRedondeo;
    }

    public void setVaSaldoRedondeo(Double vaSaldoRedondeo) {
        this.vaSaldoRedondeo = vaSaldoRedondeo;
    }

    public Double getVaSaldoRedondeoInkclub() {
        return vaSaldoRedondeoInkclub;
    }

    public void setVaSaldoRedondeoInkclub(Double vaSaldoRedondeoInkclub) {
        this.vaSaldoRedondeoInkclub = vaSaldoRedondeoInkclub;
    }

    public Double getVaTasaCambio() {
        return vaTasaCambio;
    }

    public void setVaTasaCambio(Double vaTasaCambio) {
        this.vaTasaCambio = vaTasaCambio;
    }

    public Double getVaTotalCargoTarjeta() {
        return vaTotalCargoTarjeta;
    }

    public void setVaTotalCargoTarjeta(Double vaTotalCargoTarjeta) {
        this.vaTotalCargoTarjeta = vaTotalCargoTarjeta;
    }

    public Double getVaTotalCostoVenta() {
        return vaTotalCostoVenta;
    }

    public void setVaTotalCostoVenta(Double vaTotalCostoVenta) {
        this.vaTotalCostoVenta = vaTotalCostoVenta;
    }

    public Double getVaTotalDescuento() {
        return vaTotalDescuento;
    }

    public void setVaTotalDescuento(Double vaTotalDescuento) {
        this.vaTotalDescuento = vaTotalDescuento;
    }

    public Double getVaTotalImpuesto() {
        return vaTotalImpuesto;
    }

    public void setVaTotalImpuesto(Double vaTotalImpuesto) {
        this.vaTotalImpuesto = vaTotalImpuesto;
    }

    public Double getVaTotalPrecioInkaclub() {
        return vaTotalPrecioInkaclub;
    }

    public void setVaTotalPrecioInkaclub(Double vaTotalPrecioInkaclub) {
        this.vaTotalPrecioInkaclub = vaTotalPrecioInkaclub;
    }

    public Double getVaTotalPrecioVenta() {
        return vaTotalPrecioVenta;
    }

    public void setVaTotalPrecioVenta(Double vaTotalPrecioVenta) {
        this.vaTotalPrecioVenta = vaTotalPrecioVenta;
    }

    public Double getVaTotalVenta() {
        return vaTotalVenta;
    }

    public void setVaTotalVenta(Double vaTotalVenta) {
        this.vaTotalVenta = vaTotalVenta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNuComprobanteManual() {
        return nuComprobanteManual;
    }

    public void setNuComprobanteManual(String nuComprobanteManual) {
        this.nuComprobanteManual = nuComprobanteManual;
    }

    public String getNuPuntoVenta() {
        return nuPuntoVenta;
    }

    public void setNuPuntoVenta(String nuPuntoVenta) {
        this.nuPuntoVenta = nuPuntoVenta;
    }

    public String getCoClienteLocal() {
        return coClienteLocal;
    }

    public void setCoClienteLocal(String coClienteLocal) {
        this.coClienteLocal = coClienteLocal;
    }

    public FormaPagoPedidoCredito getdPcred() {
        return dPcred;
    }

    public void setdPcred(FormaPagoPedidoCredito dPcred) {
        this.dPcred = dPcred;
    }

    public Double getMontoCredito() {
        return montoCredito;
    }

    public void setMontoCredito(Double montoCredito) {
        this.montoCredito = montoCredito;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.nuPedido != null ? this.nuPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PedidoVenta other = (PedidoVenta) obj;
        if (this.nuPedido != other.nuPedido && (this.nuPedido == null || !this.nuPedido.equals(other.nuPedido))) {
            return false;
        }
        return true;
    }

    public boolean tienePromocion(final PromocionDetalle promocion) {

        return ListUtils.getSubList(dp, new UnaryPredicate() {
            public boolean test(Object o) {
                return  ((DetallePedidoVenta)o).esPromocionDe(promocion);
            }
        }).size()>0;
    }

    public DetallePedidoVenta getItemPromocion(PromocionDetalle promocion) {

        for (DetallePedidoVenta detallePedidoVenta : dp) {
            if(detallePedidoVenta.esPromocionDe(promocion)){
                return detallePedidoVenta;
            }
        }
        return null;
    }

    public double addDonacion(BigDecimal totalDonacion) {
        return getVaTotalPrecioVenta()-getVaSaldoRedondeo()+totalDonacion.doubleValue();
    }

    @Override
    public String toString() {
        return "PedidoVenta{" +
                "coCompania='" + coCompania + '\'' +
                ", coLocal='" + coLocal + '\'' +
                ", nuPedido='" + nuPedido + '\'' +
                ", nuPedidoDiario='" + nuPedidoDiario + '\'' +
                ", tiComprobante='" + tiComprobante + '\'' +
                ", vaTotalPrecioVenta=" + vaTotalPrecioVenta +
                ", vaSaldoRedondeo=" + vaSaldoRedondeo +
                ", esPedidoVenta='" + esPedidoVenta + '\'' +
                '}';
    }
}
