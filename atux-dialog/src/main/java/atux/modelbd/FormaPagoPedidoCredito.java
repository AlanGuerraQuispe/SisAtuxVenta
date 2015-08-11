package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Date;


public class FormaPagoPedidoCredito extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;

    public static final String nt = "VTTX_FORMA_PAGO_PEDIDO_CREDITO";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_LOCAL","NU_PEDIDO","NU_ITEM_FORMA_PAGO"};
    public static final String COLUMNA_ACTIVO = "ES_FORMA_PAGO_PEDIDO";

    private String coCompania;
    private String coLocal;
    private String nuPedido;
    private Date   fePedido;
    private String coFormaPagoCredito;
    private Integer nuItemFormaPago;
    private String coMoneda;
    private String coFormaPago;
    private Date   fePago;
    private Double vaTasaCambio;
    private Double imPago;
    private Double imTotalPago;
    private Double vaSaldo;
    private String inPagoDiferido;
    private String deVentaCredito;
    private Double vaVuelto;
    private String esFormaPagoPedido;
    private String idCreaFormaPagoPedido;
    private Date   feCreaFormaPagoPedido;
    private String inPagoBono;
    private String inAnulado;
    private String deFormaPago;

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

    public static final String
            CAMPOS_INSERTS[] ={"CO_COMPANIA","CO_LOCAL","NU_PEDIDO","FE_PEDIDO",
            "CO_FORMA_PAGO_CREDITO","NU_ITEM_FORMA_PAGO","CO_MONEDA",
            "CO_FORMA_PAGO","FE_PAGO","VA_TASA_CAMBIO","IM_PAGO",
            "IM_TOTAL_PAGO","VA_SALDO","IN_PAGO_DIFERIDO","DE_VENTA_CREDITO",
            "VA_VUELTO","ES_FORMA_PAGO_PEDIDO","ID_CREA_FORMA_PAGO_PEDIDO",
            "FE_CREA_FORMA_PAGO_PEDIDO","IN_PAGO_BONO","IN_ANULADO"};

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoLocal() {
        return coLocal;
    }

    public void setCoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public String getNuPedido() {
        return nuPedido;
    }

    public void setNuPedido(String nuPedido) {
        this.nuPedido = nuPedido;
    }

    public Date getFePedido() {
        return fePedido;
    }

    public void setFePedido(Date fePedido) {
        this.fePedido = fePedido;
    }

    public String getCoFormaPagoCredito() {
        return coFormaPagoCredito;
    }

    public void setCoFormaPagoCredito(String coFormaPagoCredito) {
        this.coFormaPagoCredito = coFormaPagoCredito;
    }

    public Integer getNuItemFormaPago() {
        return nuItemFormaPago;
    }

    public void setNuItemFormaPago(Integer nuItemFormaPago) {
        this.nuItemFormaPago = nuItemFormaPago;
    }

    public String getCoMoneda() {
        return coMoneda;
    }

    public void setCoMoneda(String coMoneda) {
        this.coMoneda = coMoneda;
    }

    public String getCoFormaPago() {
        return coFormaPago;
    }

    public void setCoFormaPago(String coFormaPago) {
        this.coFormaPago = coFormaPago;
    }

    public Date getFePago() {
        return fePago;
    }

    public void setFePago(Date fePago) {
        this.fePago = fePago;
    }

    public Double getVaTasaCambio() {
        return vaTasaCambio;
    }

    public void setVaTasaCambio(Double vaTasaCambio) {
        this.vaTasaCambio = vaTasaCambio;
    }

    public Double getImPago() {
        return imPago;
    }

    public void setImPago(Double imPago) {
        this.imPago = imPago;
    }

    public Double getImTotalPago() {
        return imTotalPago;
    }

    public void setImTotalPago(Double imTotalPago) {
        this.imTotalPago = imTotalPago;
    }

    public Double getVaSaldo() {
        return vaSaldo;
    }

    public void setVaSaldo(Double vaSaldo) {
        this.vaSaldo = vaSaldo;
    }

    public String getInPagoDiferido() {
        return inPagoDiferido;
    }

    public void setInPagoDiferido(String inPagoDiferido) {
        this.inPagoDiferido = inPagoDiferido;
    }

    public String getDeVentaCredito() {
        return deVentaCredito;
    }

    public void setDeVentaCredito(String deVentaCredito) {
        this.deVentaCredito = deVentaCredito;
    }

    public Double getVaVuelto() {
        return vaVuelto;
    }

    public void setVaVuelto(Double vaVuelto) {
        this.vaVuelto = vaVuelto;
    }

    public String getEsFormaPagoPedido() {
        return esFormaPagoPedido;
    }

    public void setEsFormaPagoPedido(String esFormaPagoPedido) {
        this.esFormaPagoPedido = esFormaPagoPedido;
    }

    public String getIdCreaFormaPagoPedido() {
        return idCreaFormaPagoPedido;
    }

    public void setIdCreaFormaPagoPedido(String idCreaFormaPagoPedido) {
        this.idCreaFormaPagoPedido = idCreaFormaPagoPedido;
    }

    public Date getFeCreaFormaPagoPedido() {
        return feCreaFormaPagoPedido;
    }

    public void setFeCreaFormaPagoPedido(Date feCreaFormaPagoPedido) {
        this.feCreaFormaPagoPedido = feCreaFormaPagoPedido;
    }

    public String getInPagoBono() {
        return inPagoBono;
    }

    public void setInPagoBono(String inPagoBono) {
        this.inPagoBono = inPagoBono;
    }

    public String getInAnulado() {
        return inAnulado;
    }

    public void setInAnulado(String inAnulado) {
        this.inAnulado = inAnulado;
    }

    public String getDeFormaPago() {
        return deFormaPago;
    }

    public void setDeFormaPago(String deFormaPago) {
        this.deFormaPago = deFormaPago;
    }

    public FormaPagoPedidoCredito() {
        initBasic();
    }

    private void initBasic()
    {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
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
        final FormaPagoPedidoCredito other = (FormaPagoPedidoCredito) obj;
        if (this.nuPedido != other.nuPedido && (this.nuPedido == null || !this.nuPedido.equals(other.nuPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  "  PedidoVenta{" +
                ", coCompania='" + coCompania + '\'' +
                ", coLocal='" + coLocal + '\'' +
                ", nuPedido='" + nuPedido + '\'' +
                ", fePedido='" + fePedido+ '\'' +
                ", coFormaPago='" + coFormaPago + '\'' +
                ", fePago="    + fePago + '\'' +
                ", vaSaldo=" + vaSaldo + '\'' +
                ", esFormaPagoPedido='" + esFormaPagoPedido + '\'' +
                '}';
    }
}
