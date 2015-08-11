package atux.modelbd;

import atux.controllers.CProducto;
import atux.core.JAbstractModelBD;
import atux.util.common.AtuxVariables;
import com.atux.bean.promocion.PromocionDetalle;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class DetallePedidoVenta extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
   
    public static final String nt = "VTTD_PEDIDO_VENTA";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_LOCAL","NU_PEDIDO","NU_ITEM_PEDIDO"};
    public static final String   COLUMNA_ACTIVO = "ES_DET_PEDIDO_VENTA";        
    
    public static final String[] 
           FULL_CAMPOS ={"CO_COMPANIA, CO_LOCAL, NU_PEDIDO, NU_ITEM_PEDIDO, TI_COMPROBANTE,"+
                         "NU_COMPROBANTE_PAGO, CO_VENDEDOR, CO_PRODUCTO, NU_REVISION_PRODUCTO,"+
                         "CO_UNIDAD_VENTA, VA_FACTOR_FORMA_PAGO, VA_VENTA, VA_PRECIO_PUBLICO, CA_ATENDIDA,"+
                         "VA_PRECIO_VENTA, VA_PRECIO_PROMEDIO, IN_PRODUCTO_FRACCION, VA_FRACCION,"+
                         "CO_IMPUESTO_1, PC_IMPUESTO_1, CO_IMPUESTO_2, PC_IMPUESTO_2, CO_DESCUENTO_1,"+
                         "PC_DESCUENTO_1, CO_DESCUENTO_2, PC_DESCUENTO_2, CO_DESCUENTO_3, PC_DESCUENTO_3,"+
                         "PC_DCTO_VENTA_ESPECIAL, DE_UNIDAD_PRODUCTO, VA_BONO, IN_ORIGEN_SELECCION,"+
                         "ES_DET_PEDIDO_VENTA, ID_CREA_DET_PEDIDO_VENTA, FE_CREA_DET_PEDIDO_VENTA,"+
                         "ID_MOD_DET_PEDIDO_VENTA, FE_MOD_DET_PEDIDO_VENTA,NU_TRANSFERENCIA_ERP,"+
                         "VA_PRECIO_COMPRA, PC_DESCUENTO_CONVENIO, PC_DESCUENTO_BASE_LOCAL,"+
                         "IN_VENTA_FIDELIZACION, IN_PRODUCTO_PLAN, CO_PRODUCTO_PRINCIPAL,"+
                         "CO_CONVENIO_PLAN, CO_VENTA_PROMOCION, IN_ACUMULA_PROMO, PC_DESCUENTO_INKCLUB,"+
                         "NU_PEDIDO_CAMBIO, NU_ITEM_PEDIDO_CAMBIO, CA_DEVUELTO, CA_CAMBIO,"+
                         "TI_COMPROBANTE_CAMBIO, NU_COMPROBANTE_CAMBIO, CO_UNIDAD_LOCAL_SAP,"+
                         "IN_CONSIGNADO_SAP, VA_PRECIO_ORIGINAL, IN_INAFECTO"};
    
    public static final String[]
          CAMPOS_INSERTS ={"CO_COMPANIA","CO_LOCAL","NU_PEDIDO","NU_ITEM_PEDIDO","CO_VENDEDOR","CO_PRODUCTO","NU_REVISION_PRODUCTO",
                           "VA_VENTA","VA_PRECIO_PUBLICO","CA_ATENDIDA","VA_PRECIO_VENTA","IN_PRODUCTO_FRACCION","VA_FRACCION","CO_IMPUESTO_1",
                           "PC_IMPUESTO_1","PC_DESCUENTO_1","DE_UNIDAD_PRODUCTO","VA_BONO","ES_DET_PEDIDO_VENTA","ID_CREA_DET_PEDIDO_VENTA", 
                           "FE_CREA_DET_PEDIDO_VENTA","PC_DESCUENTO_CONVENIO","PC_DESCUENTO_BASE_LOCAL","VA_PRECIO_ORIGINAL",
                           "VA_PRECIO_PROMEDIO","CO_PRODUCTO_PRINCIPAL","IN_PRODUCTO_PRINCIPAL"};
    
    private PedidoVenta idPedidoVenta;
    private ProductoLocal prodLocal;    
    
    private String coCompania;
    private String coLocal;
    private String nuPedido;
    private Integer nuItemPedido=0;
    private String tiComprobante;
    private String nuComprobantePago;
    private String coVendedor;
    private String coProducto;
    private String nuRevisionProducto;
    private String coUnidadVenta;
    private Double vaFactorFormaPago;
    private Double vaVenta;
    private Double vaPrecioPublico;
    private Integer caAtendida;
    private Double vaPrecioVenta;
    private Double vaPrecioPromedio;
    private String inProductoFraccion;
    private Integer vaFraccion;
    private String coImpuesto_1;
    private Double pcImpuesto_1;
    private String coImpuesto_2;
    private Double pcImpuesto_2;
    private String coDescuento_1;
    private Double pcDescuento_1;
    private String coDescuento_2;
    private Double pcDescuento_2;
    private String coDescuento_3;
    private Double pcDescuento_3;
    private Double pcDctoVentaEspecial;
    private String deUnidadProducto;
    private Double vaBono;
    private String inOrigenSeleccion;
    private String esDetPedidoVenta;
    private String idCreaDetPedidoVenta;
    private Date   feCreaDetPedidoVenta;
    private String idModDetPedidoVenta;
    private Date   feModDetPedidoVenta;
    private String nuTransferenciaErp;
    private Date   feTransferenciaErp;
    private Double vaPrecioCompra;
    private Double pcDescuentoConvenio;
    private Double pcDescuentoBaseLocal;
    private String inVentaFidelizacion;
    private String inProductoPlan;
    private String coProductoPrincipal;
    private String coConvenioPlan;
    private String coVentaPromocion;
    private String inAcumulaPromo;
    private Double pcDescuentoInkclub;
    private String nuPedidoCambio;
    private String nuItemPedidoCambio;
    private Integer caDevuelto;
    private Integer caCambio;
    private String tiComprobanteCambio;
    private String nuComprobanteCambio;
    private String coUnidadLocalSap;
    private String inConsignadoSap;
    private Double vaPrecioOriginal;
    private String inInafecto;
    private String inProductoPrincipal;

    private int lineasmensaje;
    private String deMensajePromocion;
    private List detalleItemVirtual = new ArrayList();
    
    public DetallePedidoVenta() {
        initBasic();
    }

    public DetallePedidoVenta(Integer id, PedidoVenta idPedidoVenta, ProductoLocal prodLocal, Integer cantidad, Double importe, Double descuento, Integer activo) {
        this.idPedidoVenta = idPedidoVenta;
        this.prodLocal = prodLocal;        
        initBasic();
    }

    public DetallePedidoVenta(PedidoVenta idPedidoVenta, ProductoLocal prodLocal, String coCompania, String coLocal, String nuPedido, Integer nuItemPedido, String tiComprobante, String nuComprobantePago, String coVendedor, String coProducto, String nuRevisionProducto, String coUnidadVenta, Double vaFactorFormaPago, Double vaVenta, Double vaPrecioPublico, Integer caAtendida, Double vaPrecioVenta, Double vaPrecioPromedio, String inProductoFraccion, Integer vaFraccion, String coImpuesto_1, Double pcImpuesto_1, String coImpuesto_2, Double pcImpuesto_2, String coDescuento_1, Double pcDescuento_1, String coDescuento_2, Double pcDescuento_2, String coDescuento_3, Double pcDescuento_3, Double pcDctoVentaEspecial, String deUnidadProducto, Double vaBono, String inOrigenSeleccion, String esDetPedidoVenta, String idCreaDetPedidoVenta, Date feCreaDetPedidoVenta, String nuTransferenciaErp, Date feTransferenciaErp, Double vaPrecioCompra, Double pcDescuentoConvenio, Double pcDescuentoBaseLocal, String inVentaFidelizacion, String inProductoPlan, String coProductoPrincipal, String coConvenioPlan, String coVentaPromocion, String inAcumulaPromo, Double pcDescuentoInkclub, String nuPedidoCambio, String nuItemPedidoCambio, Integer caDevuelto, Integer caCambio, String tiComprobanteCambio, String nuComprobanteCambio, String coUnidadLocalSap, String inConsignadoSap, Double vaPrecioOriginal, String inInafecto) {
        this.idPedidoVenta = idPedidoVenta;
        this.prodLocal = prodLocal;
        this.coCompania = coCompania;
        this.coLocal = coLocal;
        this.nuPedido = nuPedido;
        this.nuItemPedido = nuItemPedido;
        this.tiComprobante = tiComprobante;
        this.nuComprobantePago = nuComprobantePago;
        this.coVendedor = coVendedor;
        this.coProducto = coProducto;
        this.nuRevisionProducto = nuRevisionProducto;
        this.coUnidadVenta = coUnidadVenta;
        this.vaFactorFormaPago = vaFactorFormaPago;
        this.vaVenta = vaVenta;
        this.vaPrecioPublico = vaPrecioPublico;
        this.caAtendida = caAtendida;
        this.vaPrecioVenta = vaPrecioVenta;
        this.vaPrecioPromedio = vaPrecioPromedio;
        this.inProductoFraccion = inProductoFraccion;
        this.vaFraccion = vaFraccion;
        this.coImpuesto_1 = coImpuesto_1;
        this.pcImpuesto_1 = pcImpuesto_1;
        this.coImpuesto_2 = coImpuesto_2;
        this.pcImpuesto_2 = pcImpuesto_2;
        this.coDescuento_1 = coDescuento_1;
        this.pcDescuento_1 = pcDescuento_1;
        this.coDescuento_2 = coDescuento_2;
        this.pcDescuento_2 = pcDescuento_2;
        this.coDescuento_3 = coDescuento_3;
        this.pcDescuento_3 = pcDescuento_3;
        this.pcDctoVentaEspecial = pcDctoVentaEspecial;
        this.deUnidadProducto = deUnidadProducto;
        this.vaBono = vaBono;
        this.inOrigenSeleccion = inOrigenSeleccion;
        this.esDetPedidoVenta = esDetPedidoVenta;
        this.idCreaDetPedidoVenta = idCreaDetPedidoVenta;
        this.feCreaDetPedidoVenta = feCreaDetPedidoVenta;
        this.nuTransferenciaErp = nuTransferenciaErp;
        this.feTransferenciaErp = feTransferenciaErp;
        this.vaPrecioCompra = vaPrecioCompra;
        this.pcDescuentoConvenio = pcDescuentoConvenio;
        this.pcDescuentoBaseLocal = pcDescuentoBaseLocal;
        this.inVentaFidelizacion = inVentaFidelizacion;
        this.inProductoPlan = inProductoPlan;
        this.coProductoPrincipal = coProductoPrincipal;
        this.coConvenioPlan = coConvenioPlan;
        this.coVentaPromocion = coVentaPromocion;
        this.inAcumulaPromo = inAcumulaPromo;
        this.pcDescuentoInkclub = pcDescuentoInkclub;
        this.nuPedidoCambio = nuPedidoCambio;
        this.nuItemPedidoCambio = nuItemPedidoCambio;
        this.caDevuelto = caDevuelto;
        this.caCambio = caCambio;
        this.tiComprobanteCambio = tiComprobanteCambio;
        this.nuComprobanteCambio = nuComprobanteCambio;
        this.coUnidadLocalSap = coUnidadLocalSap;
        this.inConsignadoSap = inConsignadoSap;
        this.vaPrecioOriginal = vaPrecioOriginal;
        this.inInafecto = inInafecto;
        initBasic();
    }
    
    
     private void initBasic()
    {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
    }

    public ProductoLocal getProdLocal() {
        return prodLocal;
    }

    public void setProdLocal(ProductoLocal prodLocal) {
        this.prodLocal = prodLocal;
    }        
     
    public Integer getCaAtendida() {
        return caAtendida;
    }

    public void setCaAtendida(Integer caAtendida) {
        this.caAtendida = caAtendida;
    }

    public Integer getCaCambio() {
        return caCambio;
    }

    public void setCaCambio(Integer caCambio) {
        this.caCambio = caCambio;
    }

    public Integer getCaDevuelto() {
        return caDevuelto;
    }

    public void setCaDevuelto(Integer caDevuelto) {
        this.caDevuelto = caDevuelto;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoConvenioPlan() {
        return coConvenioPlan;
    }

    public void setCoConvenioPlan(String coConvenioPlan) {
        this.coConvenioPlan = coConvenioPlan;
    }

    public String getCoDescuento_1() {
        return coDescuento_1;
    }

    public void setCoDescuento_1(String coDescuento_1) {
        this.coDescuento_1 = coDescuento_1;
    }

    public String getCoDescuento_2() {
        return coDescuento_2;
    }

    public void setCoDescuento_2(String coDescuento_2) {
        this.coDescuento_2 = coDescuento_2;
    }

    public String getCoDescuento_3() {
        return coDescuento_3;
    }

    public void setCoDescuento_3(String coDescuento_3) {
        this.coDescuento_3 = coDescuento_3;
    }

    public String getCoImpuesto_1() {
        return coImpuesto_1;
    }

    public void setCoImpuesto_1(String coImpuesto_1) {
        this.coImpuesto_1 = coImpuesto_1;
    }

    public String getCoImpuesto_2() {
        return coImpuesto_2;
    }

    public void setCoImpuesto_2(String coImpuesto_2) {
        this.coImpuesto_2 = coImpuesto_2;
    }

    public String getCoLocal() {
        return coLocal;
    }

    public void setCoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public String getCoProducto() {
        return coProducto;
    }

    public void setCoProducto(String coProducto) {
        this.coProducto = coProducto;
    }

    public String getCoProductoPrincipal() {
        return coProductoPrincipal;
    }

    public void setCoProductoPrincipal(String coProductoPrincipal) {
        this.coProductoPrincipal = coProductoPrincipal;
    }

    public String getCoUnidadLocalSap() {
        return coUnidadLocalSap;
    }

    public void setCoUnidadLocalSap(String coUnidadLocalSap) {
        this.coUnidadLocalSap = coUnidadLocalSap;
    }

    public String getCoUnidadVenta() {
        return coUnidadVenta;
    }

    public void setCoUnidadVenta(String coUnidadVenta) {
        this.coUnidadVenta = coUnidadVenta;
    }

    public String getCoVendedor() {
        return coVendedor;
    }

    public void setCoVendedor(String coVendedor) {
        this.coVendedor = coVendedor;
    }

    public String getCoVentaPromocion() {
        return coVentaPromocion;
    }

    public void setCoVentaPromocion(String coVentaPromocion) {
        this.coVentaPromocion = coVentaPromocion;
    }

    public String getDeUnidadProducto() {
        return deUnidadProducto;
    }

    public void setDeUnidadProducto(String deUnidadProducto) {
        this.deUnidadProducto = deUnidadProducto;
    }

    public String getEsDetPedidoVenta() {
        return esDetPedidoVenta;
    }

    public void setEsDetPedidoVenta(String esDetPedidoVenta) {
        this.esDetPedidoVenta = esDetPedidoVenta;
    }

    public Date getFeCreaDetPedidoVenta() {
        return feCreaDetPedidoVenta;
    }

    public void setFeCreaDetPedidoVenta(Date feCreaDetPedidoVenta) {
        this.feCreaDetPedidoVenta = feCreaDetPedidoVenta;
    }

    public Date getFeModDetPedidoVenta() {
        return feModDetPedidoVenta;
    }

    public void setFeModDetPedidoVenta(Date feModDetPedidoVenta) {
        this.feModDetPedidoVenta = feModDetPedidoVenta;
    }

    public Date getFeTransferenciaErp() {
        return feTransferenciaErp;
    }

    public void setFeTransferenciaErp(Date feTransferenciaErp) {
        this.feTransferenciaErp = feTransferenciaErp;
    }

    public String getIdCreaDetPedidoVenta() {
        return idCreaDetPedidoVenta;
    }

    public void setIdCreaDetPedidoVenta(String idCreaDetPedidoVenta) {
        this.idCreaDetPedidoVenta = idCreaDetPedidoVenta;
    }

    public String getIdModDetPedidoVenta() {
        return idModDetPedidoVenta;
    }

    public void setIdModDetPedidoVenta(String idModDetPedidoVenta) {
        this.idModDetPedidoVenta = idModDetPedidoVenta;
    }

    public PedidoVenta getIdPedidoVenta() {
        return idPedidoVenta;
    }

    public void setIdPedidoVenta(PedidoVenta idPedidoVenta) {
        this.idPedidoVenta = idPedidoVenta;
    }   

    public String getInAcumulaPromo() {
        return inAcumulaPromo;
    }

    public void setInAcumulaPromo(String inAcumulaPromo) {
        this.inAcumulaPromo = inAcumulaPromo;
    }

    public String getInConsignadoSap() {
        return inConsignadoSap;
    }

    public void setInConsignadoSap(String inConsignadoSap) {
        this.inConsignadoSap = inConsignadoSap;
    }

    public String getInInafecto() {
        return inInafecto;
    }

    public void setInInafecto(String inInafecto) {
        this.inInafecto = inInafecto;
    }

    public String getInOrigenSeleccion() {
        return inOrigenSeleccion;
    }

    public void setInOrigenSeleccion(String inOrigenSeleccion) {
        this.inOrigenSeleccion = inOrigenSeleccion;
    }

    public String getInProductoFraccion() {
        return inProductoFraccion;
    }

    public void setInProductoFraccion(String inProductoFraccion) {
        this.inProductoFraccion = inProductoFraccion;
    }

    public String getInProductoPlan() {
        return inProductoPlan;
    }

    public void setInProductoPlan(String inProductoPlan) {
        this.inProductoPlan = inProductoPlan;
    }

    public String getInVentaFidelizacion() {
        return inVentaFidelizacion;
    }

    public void setInVentaFidelizacion(String inVentaFidelizacion) {
        this.inVentaFidelizacion = inVentaFidelizacion;
    }

    public String getNuComprobanteCambio() {
        return nuComprobanteCambio;
    }

    public void setNuComprobanteCambio(String nuComprobanteCambio) {
        this.nuComprobanteCambio = nuComprobanteCambio;
    }

    public String getNuComprobantePago() {
        return nuComprobantePago;
    }

    public void setNuComprobantePago(String nuComprobantePago) {
        this.nuComprobantePago = nuComprobantePago;
    }

    public Integer getNuItemPedido() {
        return nuItemPedido;
    }

    public void setNuItemPedido(Integer nuItemPedido) {
        this.nuItemPedido = nuItemPedido;
    }

    public String getNuItemPedidoCambio() {
        return nuItemPedidoCambio;
    }

    public void setNuItemPedidoCambio(String nuItemPedidoCambio) {
        this.nuItemPedidoCambio = nuItemPedidoCambio;
    }

    public String getNuPedido() {
        return nuPedido;
    }

    public void setNuPedido(String nuPedido) {
        this.nuPedido = nuPedido;
    }

    public String getNuPedidoCambio() {
        return nuPedidoCambio;
    }

    public void setNuPedidoCambio(String nuPedidoCambio) {
        this.nuPedidoCambio = nuPedidoCambio;
    }

    public String getNuRevisionProducto() {
        return nuRevisionProducto;
    }

    public void setNuRevisionProducto(String nuRevisionProducto) {
        this.nuRevisionProducto = nuRevisionProducto;
    }

    public String getNuTransferenciaErp() {
        return nuTransferenciaErp;
    }

    public void setNuTransferenciaErp(String nuTransferenciaErp) {
        this.nuTransferenciaErp = nuTransferenciaErp;
    }

    public Double getPcDctoVentaEspecial() {
        return pcDctoVentaEspecial;
    }

    public void setPcDctoVentaEspecial(Double pcDctoVentaEspecial) {
        this.pcDctoVentaEspecial = pcDctoVentaEspecial;
    }

    public Double getPcDescuentoBaseLocal() {
        return pcDescuentoBaseLocal;
    }

    public void setPcDescuentoBaseLocal(Double pcDescuentoBaseLocal) {
        this.pcDescuentoBaseLocal = pcDescuentoBaseLocal;
    }

    public Double getPcDescuentoConvenio() {
        return pcDescuentoConvenio;
    }

    public void setPcDescuentoConvenio(Double pcDescuentoConvenio) {
        this.pcDescuentoConvenio = pcDescuentoConvenio;
    }

    public Double getPcDescuentoInkclub() {
        return pcDescuentoInkclub;
    }

    public void setPcDescuentoInkclub(Double pcDescuentoInkclub) {
        this.pcDescuentoInkclub = pcDescuentoInkclub;
    }

    public Double getPcDescuento_1() {
        return pcDescuento_1;
    }

    public void setPcDescuento_1(Double pcDescuento_1) {
        this.pcDescuento_1 = pcDescuento_1;
    }

    public Double getPcDescuento_2() {
        return pcDescuento_2;
    }

    public void setPcDescuento_2(Double pcDescuento_2) {
        this.pcDescuento_2 = pcDescuento_2;
    }

    public Double getPcDescuento_3() {
        return pcDescuento_3;
    }

    public void setPcDescuento_3(Double pcDescuento_3) {
        this.pcDescuento_3 = pcDescuento_3;
    }

    public Double getPcImpuesto_1() {
        return pcImpuesto_1;
    }

    public void setPcImpuesto_1(Double pcImpuesto_1) {
        this.pcImpuesto_1 = pcImpuesto_1;
    }

    public Double getPcImpuesto_2() {
        return pcImpuesto_2;
    }

    public void setPcImpuesto_2(Double pcImpuesto_2) {
        this.pcImpuesto_2 = pcImpuesto_2;
    }

    public String getTiComprobante() {
        return tiComprobante;
    }

    public void setTiComprobante(String tiComprobante) {
        this.tiComprobante = tiComprobante;
    }

    public String getTiComprobanteCambio() {
        return tiComprobanteCambio;
    }

    public void setTiComprobanteCambio(String tiComprobanteCambio) {
        this.tiComprobanteCambio = tiComprobanteCambio;
    }

    public Double getVaBono() {
        return vaBono;
    }

    public void setVaBono(Double vaBono) {
        this.vaBono = vaBono;
    }

    public Double getVaFactorFormaPago() {
        return vaFactorFormaPago;
    }

    public void setVaFactorFormaPago(Double vaFactorFormaPago) {
        this.vaFactorFormaPago = vaFactorFormaPago;
    }

    public Integer getVaFraccion() {
        return vaFraccion;
    }

    public void setVaFraccion(Integer vaFraccion) {
        this.vaFraccion = vaFraccion;
    }

    public Double getVaPrecioCompra() {
        return vaPrecioCompra;
    }

    public void setVaPrecioCompra(Double vaPrecioCompra) {
        this.vaPrecioCompra = vaPrecioCompra;
    }

    public Double getVaPrecioOriginal() {
        return vaPrecioOriginal;
    }

    public void setVaPrecioOriginal(Double vaPrecioOriginal) {
        this.vaPrecioOriginal = vaPrecioOriginal;
    }

    public Double getVaPrecioPromedio() {
        return vaPrecioPromedio;
    }

    public void setVaPrecioPromedio(Double vaPrecioPromedio) {
        this.vaPrecioPromedio = vaPrecioPromedio;
    }

    public Double getVaPrecioPublico() {
        return vaPrecioPublico;
    }

    public void setVaPrecioPublico(Double vaPrecioPublico) {
        this.vaPrecioPublico = vaPrecioPublico;
    }

    public Double getVaPrecioVenta() {
        return vaPrecioVenta;
    }

    public void setVaPrecioVenta(Double vaPrecioVenta) {
        this.vaPrecioVenta = vaPrecioVenta;
    }

    public Double getVaVenta() {
        return vaVenta;
    }

    public void setVaVenta(Double vaVenta) {
        this.vaVenta = vaVenta;
    }

    public List getDetalleItemVirtual() {
        return detalleItemVirtual;
    }

    public void setDetalleItemVirtual(List detalleItemVirtual) {
        this.detalleItemVirtual = detalleItemVirtual;
    }

    public int getLineasmensaje() {
        return lineasmensaje;
    }

    public void setLineasmensaje(int lineasmensaje) {
        this.lineasmensaje = lineasmensaje;
    }

    public String getDeMensajePromocion() {
        return deMensajePromocion;
    }

    public void setDeMensajePromocion(String deMensajePromocion) {
        this.deMensajePromocion = deMensajePromocion;
    }

    public String getInProductoPrincipal() {
        return inProductoPrincipal;
    }

    public void setInProductoPrincipal(String inProductoPrincipal) {
        this.inProductoPrincipal = inProductoPrincipal;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (this.getPrimaryKey() != null ? this.getPrimaryKey().hashCode() : 0);
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
        final DetallePedidoVenta other = (DetallePedidoVenta) obj;
        if (this.getPrimaryKey() != other.getPrimaryKey() && (this.getPrimaryKey() == null || !Arrays.equals(this.getPrimaryKey(), other.getPrimaryKey()))) {
            return false;
        }
        return true;
    }
    
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
        return "DetallePedidoVenta{" + "coCompania=" + coCompania + ", coLocal=" + coLocal + ", nuPedido=" + nuPedido + ", nuItemPedido=" + nuItemPedido + '}';
    }
    
    public DetallePedidoVenta agregarItem(ProductoLocal prdLoc) throws SQLException{
        setProdLocal(prdLoc);
        
        setCoCompania(prdLoc.getCoCompania());
        setCoLocal(prdLoc.getCoLocal());
        setNuPedido(AtuxVariables.vNumeroPedido);   
        setNuItemPedido(nuItemPedido + 1);
        setTiComprobante(AtuxVariables.TIPO_BOLETA);
        setNuPedido(AtuxVariables.vNuComprobantePago);
        setCoProducto(prdLoc.getCoProducto());
        setNuRevisionProducto(prdLoc.getNuRevisionProducto());
        setVaVenta(prdLoc.getVaVenta());
        setVaPrecioPublico(prdLoc.getVaPrecioPublico());              
        setCaAtendida(1);        
        setVaPrecioVenta(1*prdLoc.getVaPrecioPublico());
        setVaPrecioPromedio(prdLoc.getVaCostoProducto());
        setInProductoFraccion(prdLoc.getInProdFraccionado());
        setVaFraccion(prdLoc.getVaFraccion());
        setCoImpuesto_1(prdLoc.getProducto().getCoImpuesto_1());
        setPcImpuesto_1(new CProducto().getImpuesto(prdLoc.getProducto().getCoImpuesto_1()));
        setPcDescuento_1(prdLoc.getPcDescuento_1());
        setDeUnidadProducto(prdLoc.getDeUnidadFraccion());
        setVaBono(prdLoc.getProducto().getVaBono());
        setEsDetPedidoVenta("P"); //Pedido pendiente de cobrar
        setIdCreaDetPedidoVenta(AtuxVariables.vIdUsuario);
        setPcDescuentoBaseLocal(prdLoc.getPcDescuento_1());
        setVaPrecioOriginal(prdLoc.getVaPrecioPublico());

        return this;
    }

    public DetallePedidoVenta agregarItemInsumo(ProductoLocal prdLoc) throws SQLException{
        setProdLocal(prdLoc);
        setCoCompania(prdLoc.getCoCompania());
        setCoLocal(prdLoc.getCoLocal());
        setNuPedido(AtuxVariables.vNumeroPedido);
        //setNuItemPedido(nuItemPedido + 1);
        setTiComprobante(AtuxVariables.TIPO_BOLETA);
        setNuPedido(AtuxVariables.vNuComprobantePago);
        setCoProducto(prdLoc.getCoProducto());
        setNuRevisionProducto(prdLoc.getNuRevisionProducto());
        setVaVenta(prdLoc.getVaVenta());
        setVaPrecioPublico(prdLoc.getVaPrecioPublico());
        setVaPrecioVenta(getCaAtendida()*prdLoc.getVaPrecioPublico());
        setVaPrecioPromedio(prdLoc.getVaCostoProducto());
        setInProductoFraccion(prdLoc.getInProdFraccionado());
        setVaFraccion(prdLoc.getVaFraccion());
        setCoImpuesto_1(prdLoc.getProducto().getCoImpuesto_1());
        setPcImpuesto_1(new CProducto().getImpuesto(prdLoc.getProducto().getCoImpuesto_1()));
        setPcDescuento_1(prdLoc.getPcDescuento_1());
        setDeUnidadProducto(prdLoc.getDeUnidadFraccion());
        setVaBono(prdLoc.getProducto().getVaBono());
        //setEsDetPedidoVenta("P"); //Pedido pendiente de cobrar
        //setIdCreaDetPedidoVenta(AtuxVariables.vIdUsuario);
        setPcDescuentoBaseLocal(prdLoc.getPcDescuento_1());
        setVaPrecioOriginal(prdLoc.getVaPrecioPublico());
        return this;
    }

    public DetallePedidoVenta agregarItemPromocion(String coPromocion, String coProductoPrincipal,ProductoLocal prdLoc,int caAtendida) throws SQLException{
        setProdLocal(prdLoc);

        setCoCompania(prdLoc.getCoCompania());
        setCoLocal(prdLoc.getCoLocal());
        setNuPedido(AtuxVariables.vNumeroPedido);
        setNuItemPedido(nuItemPedido + 1);
        setTiComprobante(AtuxVariables.TIPO_BOLETA);
        setNuPedido(AtuxVariables.vNuComprobantePago);
        setCoProducto(prdLoc.getCoProducto());
        setCoProductoPrincipal(coProductoPrincipal);
        setCoVentaPromocion(coPromocion);
        setNuRevisionProducto(prdLoc.getNuRevisionProducto());
        setVaVenta(0d);
        setVaPrecioPublico(0d);
        setCaAtendida(caAtendida);
        setVaPrecioVenta(0d);
        setVaPrecioPromedio(0d);
        setInProductoFraccion(prdLoc.getInProdFraccionado());
        setVaFraccion(prdLoc.getVaFraccion());
        setCoImpuesto_1(prdLoc.getProducto().getCoImpuesto_1());
        setPcImpuesto_1(new CProducto().getImpuesto(prdLoc.getProducto().getCoImpuesto_1()));
        setPcDescuento_1(0d);
        setDeUnidadProducto(prdLoc.getDeUnidadFraccion());

        setVaBono(0d);
        setEsDetPedidoVenta("P"); //Pedido pendiente de cobrar
        setIdCreaDetPedidoVenta(AtuxVariables.vIdUsuario);
        setPcDescuentoBaseLocal(prdLoc.getPcDescuento_1());
        setVaPrecioOriginal(0d);
        return this;
    }

    public boolean esPromocionDe(PromocionDetalle promocionDetalle) {
        return promocionDetalle.getCoProductoP().equals(getCoProducto())
                && promocionDetalle.getCoPromocion().equals(getCoVentaPromocion())
                && promocionDetalle.getCoProducto().equals(getCoProductoPrincipal());

    }
}
