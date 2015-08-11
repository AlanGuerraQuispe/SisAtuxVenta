package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author user
 */
public class ComprobantePago extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
   
    public static final String nt = "VTTV_COMPROBANTE_PAGO";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_LOCAL","TI_COMPROBANTE","NU_COMPROBANTE_PAGO"};
    public static final String COLUMNA_ACTIVO = "ES_COMPROBANTE_PAGO";        
    
    private String coCompania;
    private String coLocal;
    private String tiComprobante;
    private String nuComprobantePago;
    private String coMoneda;
    private String coCajero;
    private Date   feComprobante;
    private String nuPedido;
    private String nuCaja;
    private String nuTurno;
    private String inCuadreCaja;
    private String noImpresoCliente;
    private String deDireccionDliente;
    private String tiComprobantePrincipal;
    private String nuComprobantePrincipal;
    private String idAnulaComprobante;
    private Date   feAnulaComprobante;
    private String nuCajaAnulacion;
    private String nuTurnoAnulacion;
    private String coCajeroAnulacion;
    private String coGrupoMotivo;
    private String coMotivoAnulacion;
    private Double vaTotalVenta;
    private Double vaTotalDescuento;
    private Double vaTotalCargoTarjeta;
    private Double vaTotalImpuesto;
    private Double vaTotalPrecioVenta;
    private Double vaSaldoRedondeo;
    private String esComprobantePago;
    private String idCreaComprobantePago;
    private Date   feCreaComprobantePago;
    private String idModComprobantePago;
    private Date   feModComprobantePago;
    private Double vaTotalAfecto;
    private Double vaCopagoEmpresa;
    private String coClienteLocal;
    private Double vaTasaCambio;
    private String nuRucCliente;
    private boolean imprimeDonacion;
    private Integer caItem;
    private Double vaTotalPrecioVentaConvenio;
    private String nuPedidoAnul;
    private String coFormaPagoConvenio;
    private String nuPedidoDevolucion;
    private Double vaMontoCopagoCliente;
    private String tiOrdenCopago;
    private String nuSecReplica;        
    
    private ArrayList<DetallePedidoVenta> detallePedido;
    
    public static final String[]
      FULL_CAMPOS ={"CO_COMPANIA,CO_LOCAL,TI_COMPROBANTE,NU_COMPROBANTE_PAGO,CO_MONEDA,CO_CAJERO,FE_COMPROBANTE," +
                    "NU_PEDIDO,NU_CAJA,NU_TURNO,IN_CUADRE_CAJA,NO_IMPRESO_CLIENTE,DE_DIRECCION_CLIENTE,"+ 
                    "TI_COMPROBANTE_PRINCIPAL,NU_COMPROBANTE_PRINCIPAL,ID_ANULA_COMPROBANTE,FE_ANULA_COMPROBANTE," +
                    "NU_CAJA_ANULACION,NU_TURNO_ANULACION,CO_CAJERO_ANULACION,CO_GRUPO_MOTIVO, "+
                    "CO_MOTIVO_ANULACION,VA_TOTAL_VENTA,VA_TOTAL_DESCUENTO,VA_TOTAL_CARGO_TARJETA,VA_TOTAL_IMPUESTO,"+
                    "VA_TOTAL_PRECIO_VENTA,VA_SALDO_REDONDEO,ES_COMPROBANTE_PAGO,ID_CREA_COMPROBANTE_PAGO,"+
                    "FE_CREA_COMPROBANTE_PAGO,ID_MOD_COMPROBANTE_PAGO,FE_MOD_COMPROBANTE_PAGO,VA_TOTAL_AFECTO,"+
                    "VA_COPAGO_EMPRESA,CO_CLIENTE_LOCAL,VA_TASA_CAMBIO,NU_RUC_CLIENTE,CA_ITEM,"+ 
                    "VA_TOTAL_PRECIO_VENTA_CONVENIO,NU_PEDIDO_ANUL,CO_FORMA_PAGO_CONVENIO,NU_PEDIDO_DEVOLUCION,"+
                    "IN_REPLICA,FE_REPLICA,VA_MONTO_COPAGO_CLIENTE,TI_ORDEN_COPAGO,NU_SEC_REPLICA"};
    
    public static final String[]
     NO_FULL_CAMPOS ={"CO_COMPANIA,CO_LOCAL,TI_COMPROBANTE,NU_COMPROBANTE_PAGO,CO_MONEDA,CO_CAJERO,FE_COMPROBANTE,NU_PEDIDO,NU_CAJA,  "+
                   "NU_TURNO,IN_CUADRE_CAJA,NO_IMPRESO_CLIENTE,DE_DIRECCION_CLIENTE,CA_ITEM, "+
                   "VA_TOTAL_VENTA,VA_TOTAL_DESCUENTO,VA_TOTAL_CARGO_TARJETA,VA_TOTAL_IMPUESTO,VA_TOTAL_PRECIO_VENTA,"+ 
                   "VA_SALDO_REDONDEO,ES_COMPROBANTE_PAGO,ID_CREA_COMPROBANTE_PAGO,FE_CREA_COMPROBANTE_PAGO,ID_MOD_COMPROBANTE_PAGO,	    "+
                   "FE_MOD_COMPROBANTE_PAGO,VA_TOTAL_AFECTO,VA_COPAGO_EMPRESA,CO_CLIENTE_LOCAL,VA_TASA_CAMBIO,NU_RUC_CLIENTE,					  "+ 
                   "VA_TOTAL_PRECIO_VENTA_CONVENIO,NU_PEDIDO_ANUL,CO_FORMA_PAGO_CONVENIO,NU_PEDIDO_DEVOLUCION,IN_REPLICA,FE_REPLICA,		  "+
                   "VA_MONTO_COPAGO_CLIENTE,TI_ORDEN_COPAGO,NU_SEC_REPLICA"};
    
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

    public String getCoCajeroAnulacion() {
        return coCajeroAnulacion;
    }

    public void setCoCajeroAnulacion(String coCajeroAnulacion) {
        this.coCajeroAnulacion = coCajeroAnulacion;
    }

    public String getCoClienteLocal() {
        return coClienteLocal;
    }

    public void setCoClienteLocal(String coClienteLocal) {
        this.coClienteLocal = coClienteLocal;
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

    public String getDeDireccionDliente() {
        return deDireccionDliente;
    }

    public void setDeDireccionDliente(String deDireccionDliente) {
        this.deDireccionDliente = deDireccionDliente;
    }

    public String getEsComprobantePago() {
        return esComprobantePago;
    }

    public void setEsComprobantePago(String esComprobantePago) {
        this.esComprobantePago = esComprobantePago;
    }

    public Date getFeAnulaComprobante() {
        return feAnulaComprobante;
    }

    public void setFeAnulaComprobante(Date feAnulaComprobante) {
        this.feAnulaComprobante = feAnulaComprobante;
    }

    public Date getFeComprobante() {
        return feComprobante;
    }

    public void setFeComprobante(Date feComprobante) {
        this.feComprobante = feComprobante;
    }

    public Date getFeCreaComprobantePago() {
        return feCreaComprobantePago;
    }

    public void setFeCreaComprobantePago(Date feCreaComprobantePago) {
        this.feCreaComprobantePago = feCreaComprobantePago;
    }

    public Date getFeModComprobantePago() {
        return feModComprobantePago;
    }

    public void setFeModComprobantePago(Date feModComprobantePago) {
        this.feModComprobantePago = feModComprobantePago;
    }

    public String getIdAnulaComprobante() {
        return idAnulaComprobante;
    }

    public void setIdAnulaComprobante(String idAnulaComprobante) {
        this.idAnulaComprobante = idAnulaComprobante;
    }

    public String getIdCreaComprobantePago() {
        return idCreaComprobantePago;
    }

    public void setIdCreaComprobantePago(String idCreaComprobantePago) {
        this.idCreaComprobantePago = idCreaComprobantePago;
    }

    public String getIdModComprobantePago() {
        return idModComprobantePago;
    }

    public void setIdModComprobantePago(String idModComprobantePago) {
        this.idModComprobantePago = idModComprobantePago;
    }

    public String getInCuadreCaja() {
        return inCuadreCaja;
    }

    public void setInCuadreCaja(String inCuadreCaja) {
        this.inCuadreCaja = inCuadreCaja;
    }

    public String getNoImpresoCliente() {
        return noImpresoCliente;
    }

    public void setNoImpresoCliente(String noImpresoCliente) {
        this.noImpresoCliente = noImpresoCliente;
    }

    public String getNuCaja() {
        return nuCaja;
    }

    public void setNuCaja(String nuCaja) {
        this.nuCaja = nuCaja;
    }

    public String getNuCajaAnulacion() {
        return nuCajaAnulacion;
    }

    public void setNuCajaAnulacion(String nuCajaAnulacion) {
        this.nuCajaAnulacion = nuCajaAnulacion;
    }

    public String getNuComprobantePago() {
        return nuComprobantePago;
    }

    public void setNuComprobantePago(String nuComprobantePago) {
        this.nuComprobantePago = nuComprobantePago;
    }

    public String getNuComprobantePrincipal() {
        return nuComprobantePrincipal;
    }

    public void setNuComprobantePrincipal(String nuComprobantePrincipal) {
        this.nuComprobantePrincipal = nuComprobantePrincipal;
    }

    public String getNuPedido() {
        return nuPedido;
    }

    public void setNuPedido(String nuPedido) {
        this.nuPedido = nuPedido;
    }

    public String getNuPedidoAnul() {
        return nuPedidoAnul;
    }

    public void setNuPedidoAnul(String nuPedidoAnul) {
        this.nuPedidoAnul = nuPedidoAnul;
    }

    public String getNuPedidoDevolucion() {
        return nuPedidoDevolucion;
    }

    public void setNuPedidoDevolucion(String nuPedidoDevolucion) {
        this.nuPedidoDevolucion = nuPedidoDevolucion;
    }

    public String getNuRucCliente() {
        return nuRucCliente;
    }

    public void setNuRucCliente(String nuRucCliente) {
        this.nuRucCliente = nuRucCliente;
    }

    public String getNuSecReplica() {
        return nuSecReplica;
    }

    public void setNuSecReplica(String nuSecReplica) {
        this.nuSecReplica = nuSecReplica;
    }

    public String getNuTurno() {
        return nuTurno;
    }

    public void setNuTurno(String nuTurno) {
        this.nuTurno = nuTurno;
    }

    public String getNuTurnoAnulacion() {
        return nuTurnoAnulacion;
    }

    public void setNuTurnoAnulacion(String nuTurnoAnulacion) {
        this.nuTurnoAnulacion = nuTurnoAnulacion;
    }

    public String getTiComprobante() {
        return tiComprobante;
    }

    public void setTiComprobante(String tiComprobante) {
        this.tiComprobante = tiComprobante;
    }

    public String getTiComprobantePrincipal() {
        return tiComprobantePrincipal;
    }

    public void setTiComprobantePrincipal(String tiComprobantePrincipal) {
        this.tiComprobantePrincipal = tiComprobantePrincipal;
    }

    public String getTiOrdenCopago() {
        return tiOrdenCopago;
    }

    public void setTiOrdenCopago(String tiOrdenCopago) {
        this.tiOrdenCopago = tiOrdenCopago;
    }

    public Double getVaCopagoEmpresa() {
        return vaCopagoEmpresa;
    }

    public void setVaCopagoEmpresa(Double vaCopagoEmpresa) {
        this.vaCopagoEmpresa = vaCopagoEmpresa;
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

    public Double getVaTasaCambio() {
        return vaTasaCambio;
    }

    public void setVaTasaCambio(Double vaTasaCambio) {
        this.vaTasaCambio = vaTasaCambio;
    }

    public Double getVaTotalAfecto() {
        return vaTotalAfecto;
    }

    public void setVaTotalAfecto(Double vaTotalAfecto) {
        this.vaTotalAfecto = vaTotalAfecto;
    }

    public Double getVaTotalCargoTarjeta() {
        return vaTotalCargoTarjeta;
    }

    public void setVaTotalCargoTarjeta(Double vaTotalCargoTarjeta) {
        this.vaTotalCargoTarjeta = vaTotalCargoTarjeta;
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

    public Double getVaTotalPrecioVenta() {
        return vaTotalPrecioVenta;
    }

    public void setVaTotalPrecioVenta(Double vaTotalPrecioVenta) {
        this.vaTotalPrecioVenta = vaTotalPrecioVenta;
    }

    public Double getVaTotalPrecioVentaConvenio() {
        return vaTotalPrecioVentaConvenio;
    }

    public void setVaTotalPrecioVentaConvenio(Double vaTotalPrecioVentaConvenio) {
        this.vaTotalPrecioVentaConvenio = vaTotalPrecioVentaConvenio;
    }

    public Double getVaTotalVenta() {
        return vaTotalVenta;
    }

    public void setVaTotalVenta(Double vaTotalVenta) {
        this.vaTotalVenta = vaTotalVenta;
    }

    public ArrayList<DetallePedidoVenta> getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(ArrayList<DetallePedidoVenta> detallePedido) {
        this.detallePedido = detallePedido;
    }

    public boolean isImprimeDonacion() {
        return imprimeDonacion;
    }

    public void setImprimeDonacion(boolean imprimeDonacion) {
        this.imprimeDonacion = imprimeDonacion;
    }
}
