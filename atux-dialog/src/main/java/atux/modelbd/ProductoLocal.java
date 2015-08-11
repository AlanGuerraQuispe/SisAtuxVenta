package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class ProductoLocal extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
    public static final String nt = "LGTR_PRODUCTO_LOCAL";

    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_LOCAL","CO_PRODUCTO","NU_REVISION_PRODUCTO"};
    public static final String   COLUMNA_ACTIVO = "ES_PROD_LOCAL";
    public static final String[]
            FULL_NOM_CAMPOS = {"CO_COMPANIA, CO_LOCAL, CO_PRODUCTO, NU_REVISION_PRODUCTO, VA_VENTA, CO_UNIDAD_VENTA_FRACCION, CO_MONEDA, VA_FRACCION, "
                            + "IN_PROD_FRACCIONADO, CO_UNIDAD_CONTENIDO, VA_CONTENIDO_FRACCION, CA_STOCK_DISPONIBLE, CA_STOCK_TRANSITO, CA_STOCK_COMPROMETIDO, "
                            + "CA_STOCK_MINIMO, CA_STOCK_MAXIMO, CO_DESCUENTO_1, PC_DESCUENTO_1, CO_DESCUENTO_2, PC_DESCUENTO_2, CO_DESCUENTO_3, PC_DESCUENTO_3, "
                            + "PC_DCTO_VENTA_ESPECIAL, IN_PROD_INVENTARIO, IN_PRODUCTO_REPONER, CA_STOCK_REPONER, CA_STOCK_REPONER_CALCULA, DE_UNIDAD_FRACCION, "
                            + "NU_DIAS_ROTACION_PROMEDIO, NU_MIN_DIAS_REPOSICION, NU_MAX_DIAS_REPOSICION, CA_MIN_PROD_EXHIBICION, CA_PROD_NO_ATENDIDO, "
                            + "ES_PROD_LOCAL, ID_CREA_PROD_LOCAL, FE_CREA_PROD_LOCAL, ID_MOD_PROD_LOCAL, FE_MOD_PROD_LOCAL, VA_ROTACION, CA_ULTIMO_PEDIDO_REP, "
                            + "IN_REPLICA, FE_REPLICA, NU_SEC_REPLICA, CA_STK_DISPONIBLE_PED_REP, CA_STOCK_TRANSITO_PED_REP, CO_CENTRO_BENEFICIO_SAP, "
                            + "VA_TOTAL_INVENTARIO, VA_COSTO_PRODUCTO, VA_PRECIO_PUBLICO, CO_UNIDAD_LOCAL_SAP, IN_MOD_CAMBIO, "
                            + "FE_ULT_MOD_PRECIO, TS_DIAS, TR_DIAS"};

    public static final String[]
            FULL_MAESTROS = {"CO_COMPANIA, CO_LOCAL, CO_PRODUCTO, NU_REVISION_PRODUCTO, VA_VENTA, CO_MONEDA, VA_FRACCION, IN_PROD_FRACCIONADO, "
            + "CA_STOCK_DISPONIBLE, CA_STOCK_TRANSITO, CA_STOCK_COMPROMETIDO, CA_STOCK_MINIMO, CA_STOCK_MAXIMO, PC_DESCUENTO_1, "
            + "DE_UNIDAD_FRACCION, ES_PROD_LOCAL, ID_CREA_PROD_LOCAL, FE_CREA_PROD_LOCAL"};

    public static final String[]
            CAMPOS_MAESTROS_SINPK = {"VA_VENTA, CO_MONEDA, VA_FRACCION, IN_PROD_FRACCIONADO, "
            + "CA_STOCK_DISPONIBLE, CA_STOCK_TRANSITO, CA_STOCK_COMPROMETIDO, CA_STOCK_MINIMO, CA_STOCK_MAXIMO, PC_DESCUENTO_1, "
            + "DE_UNIDAD_FRACCION, ES_PROD_LOCAL, ID_MOD_PROD_LOCAL, FE_MOD_PROD_LOCAL"};


    public static final String[]
            FULL_NOM_CAMPOS_Update = {"VA_VENTA,CO_MONEDA,IN_PROD_FRACCIONADO,VA_FRACCION,PC_DESCUENTO_1,DE_UNIDAD_FRACCION,ES_PROD_LOCAL,ID_MOD_PROD_LOCAL,FE_MOD_PROD_LOCAL"};

    private Producto producto;

    private String coCompania;
    private String coLocal;
    private String coProducto;
    private String nuRevisionProducto;
    private Double vaVenta;
    private String coUnidadVentaFraccion;
    private String coMoneda;
    private Integer vaFraccion;
    private String inProdFraccionado;
    private String coUnidadContenido;
    private Integer vaContenidoFraccion;
    private Integer caStockDisponible;
    private Integer caStockTransito;
    private Integer caStockComprometido;
    private Integer caStockMinimo;
    private Integer caStockMaximo;
    private String coDescuento_1;
    private Double pcDescuento_1;
    private String coDescuento_2;
    private Double pcDescuento_2;
    private String coDescuento_3;
    private Double pcDescuento_3;
    private Double pcDctoVentaEspecial;
    private String inProdInventario;
    private String inProductoReponer;
    private Integer caStockReponer;
    private Integer caStockReponerCalcula;
    private String deUnidadFraccion;
    private Integer caMinProdExhibicion;
    private Integer caProdNoAtendido;
    private String esProdLocal;
    private String idCreaProdLocal;
    private Date   feCreaProdLocal;
    private String idModProdLocal;
    private Date   feModProdLocal;
    private Double vaRotacion;
    private Integer caUltimoPedidoRep;
    private Integer caStkDisponiblePedRep;
    private Integer caStockTransitoPedRep;
    private String coCentroBeneficioSap;
    private Double vaTotalInventario;
    private Double vaCostoProducto;
    private Double vaPrecioPublico;
    private String coUnidadLocalSap;

    private String  coDescuento1;
    private Double  pcDescuento1;
    private String  coDescuento2;
    private Double  pcDescuento2;
    private String  coDescuento3;
    private Double  pcDescuento3;
    private Integer nuDiasRotacionPromedio;
    private Integer nuMinDiasReposicion;
    private Integer nuMaxDiasReposicion;
    private String  inReplica;
    private Date    feReplica;
    private Integer nuSecReplica;
    private String  inModCambio;
    private Date    feUltModPrecio;
    private Double  tsDias;
    private Double  trDias;
    private Double  CalculoPrecioPublico;

    private String coJ5;
    private String deJ5;
    private String coJ4;
    private String deJ4;
    private String coJ3;
    private String deJ3;
    private String coJ2;
    private String deJ2;
    private String coJ1;
    private String deJ1;

    private Boolean sel;
    private Integer cantidad;

    //Datos para el pedido reposición
    private String deProducto;
    private String deUnidadProducto;
    private String stockEnCajas;
    private String caTransito;
    private String caComprasPendientes;

    private List<ProductoLocal> insumosProducto = new ArrayList();
    private Boolean inInsumos;

    public static final String[]
           FULL_CAMPOS ={"CO_COMPANIA, CO_LOCAL, CO_PRODUCTO, NU_REVISION_PRODUCTO, VA_VENTA,"+
                         "CO_UNIDAD_VENTA_FRACCION, CO_MONEDA, VA_FRACCION, IN_PROD_FRACCIONADO,"+
                         "CO_UNIDAD_CONTENIDO, VA_CONTENIDO_FRACCION, CA_STOCK_DISPONIBLE,"+
                         "CA_STOCK_TRANSITO, CA_STOCK_COMPROMETIDO, CA_STOCK_MINIMO, CA_STOCK_MAXIMO,"+
                         "CO_DESCUENTO_1, PC_DESCUENTO_1, CO_DESCUENTO_2, PC_DESCUENTO_2, CO_DESCUENTO_3,"+
                         "PC_DESCUENTO_3, PC_DCTO_VENTA_ESPECIAL, IN_PROD_INVENTARIO, IN_PRODUCTO_REPONER,"+
                         "CA_STOCK_REPONER, CA_STOCK_REPONER_CALCULA, DE_UNIDAD_FRACCION,"+
                         "NU_DIAS_ROTACION_PROMEDIO, NU_MIN_DIAS_REPOSICION, NU_MAX_DIAS_REPOSICION,"+
                         "CA_MIN_PROD_EXHIBICION, CA_PROD_NO_ATENDIDO, ES_PROD_LOCAL, ID_CREA_PROD_LOCAL,"+
                         "FE_CREA_PROD_LOCAL, ID_MOD_PROD_LOCAL, FE_MOD_PROD_LOCAL, VA_ROTACION,CA_ULTIMO_PEDIDO_REP,"+
                         "CA_STK_DISPONIBLE_PED_REP, CA_STOCK_TRANSITO_PED_REP, CO_CENTRO_BENEFICIO_SAP,"+
                         "VA_TOTAL_INVENTARIO, VA_COSTO_PRODUCTO, VA_PRECIO_PUBLICO, CO_UNIDAD_LOCAL_SAP"};

    public static final String[]
        NO_FULL_CAMPOS ={"CO_COMPANIA, CO_LOCAL, CO_PRODUCTO, NU_REVISION_PRODUCTO, VA_VENTA,"+
                         "CO_UNIDAD_VENTA_FRACCION, CO_MONEDA, VA_FRACCION, IN_PROD_FRACCIONADO,"+
                         "CO_UNIDAD_CONTENIDO, VA_CONTENIDO_FRACCION, CA_STOCK_DISPONIBLE,"+
                         "CA_STOCK_TRANSITO, CA_STOCK_COMPROMETIDO, CA_STOCK_MINIMO, CA_STOCK_MAXIMO,"+
                         "CO_DESCUENTO_1, PC_DESCUENTO_1, CO_DESCUENTO_2, PC_DESCUENTO_2, CO_DESCUENTO_3,"+
                         "PC_DESCUENTO_3, PC_DCTO_VENTA_ESPECIAL, IN_PROD_INVENTARIO, IN_PRODUCTO_REPONER,"+
                         "CA_STOCK_REPONER, CA_STOCK_REPONER_CALCULA, DE_UNIDAD_FRACCION,"+
                         "NU_DIAS_ROTACION_PROMEDIO, NU_MIN_DIAS_REPOSICION, NU_MAX_DIAS_REPOSICION,"+
                         "CA_MIN_PROD_EXHIBICION, CA_PROD_NO_ATENDIDO, ES_PROD_LOCAL, ID_CREA_PROD_LOCAL,"+
                         "FE_CREA_PROD_LOCAL, ID_MOD_PROD_LOCAL, FE_MOD_PROD_LOCAL, VA_ROTACION,CA_ULTIMO_PEDIDO_REP,"+
                         "CA_STK_DISPONIBLE_PED_REP, CA_STOCK_TRANSITO_PED_REP, CO_CENTRO_BENEFICIO_SAP,"+
                         "VA_TOTAL_INVENTARIO, VA_COSTO_PRODUCTO, VA_PRECIO_PUBLICO, CO_UNIDAD_LOCAL_SAP"};

    public ProductoLocal() {
        initBasic();
    }

    public Integer getCaMinProdExhibicion() {
        return caMinProdExhibicion;
    }

    public void setCaMinProdExhibicion(Integer caMinProdExhibicion) {
        this.caMinProdExhibicion = caMinProdExhibicion;
    }

    public Integer getCaProdNoAtendido() {
        return caProdNoAtendido;
    }

    public void setCaProdNoAtendido(Integer caProdNoAtendido) {
        this.caProdNoAtendido = caProdNoAtendido;
    }

    public Integer getCaStkDisponiblePedRep() {
        return caStkDisponiblePedRep;
    }

    public void setCaStkDisponiblePedRep(Integer caStkDisponiblePedRep) {
        this.caStkDisponiblePedRep = caStkDisponiblePedRep;
    }

    public Integer getCaStockComprometido() {
        return caStockComprometido;
    }

    public void setCaStockComprometido(Integer caStockComprometido) {
        this.caStockComprometido = caStockComprometido;
    }

    public Integer getCaStockDisponible() {
        return caStockDisponible;
    }

    public void setCaStockDisponible(Integer caStockDisponible) {
        this.caStockDisponible = caStockDisponible;
    }

    public Integer getCaStockMaximo() {
        return caStockMaximo;
    }

    public void setCaStockMaximo(Integer caStockMaximo) {
        this.caStockMaximo = caStockMaximo;
    }

    public Integer getCaStockMinimo() {
        return caStockMinimo;
    }

    public void setCaStockMinimo(Integer caStockMinimo) {
        this.caStockMinimo = caStockMinimo;
    }

    public Integer getCaStockReponer() {
        return caStockReponer;
    }

    public void setCaStockReponer(Integer caStockReponer) {
        this.caStockReponer = caStockReponer;
    }

    public Integer getCaStockReponerCalcula() {
        return caStockReponerCalcula;
    }

    public void setCaStockReponerCalcula(Integer caStockReponerCalcula) {
        this.caStockReponerCalcula = caStockReponerCalcula;
    }

    public Integer getCaStockTransito() {
        return caStockTransito;
    }

    public void setCaStockTransito(Integer caStockTransito) {
        this.caStockTransito = caStockTransito;
    }

    public Integer getCaStockTransitoPedRep() {
        return caStockTransitoPedRep;
    }

    public void setCaStockTransitoPedRep(Integer caStockTransitoPedRep) {
        this.caStockTransitoPedRep = caStockTransitoPedRep;
    }

    public Integer getCaUltimoPedidoRep() {
        return caUltimoPedidoRep;
    }

    public void setCaUltimoPedidoRep(Integer caUltimoPedidoRep) {
        this.caUltimoPedidoRep = caUltimoPedidoRep;
    }

    public String getCoCentroBeneficioSap() {
        return coCentroBeneficioSap;
    }

    public void setCoCentroBeneficioSap(String coCentroBeneficioSap) {
        this.coCentroBeneficioSap = coCentroBeneficioSap;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
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

    public String getCoProducto() {
        return coProducto;
    }

    public void setCoProducto(String coProducto) {
        this.coProducto = coProducto;
    }

    public String getCoUnidadContenido() {
        return coUnidadContenido;
    }

    public void setCoUnidadContenido(String coUnidadContenido) {
        this.coUnidadContenido = coUnidadContenido;
    }

    public String getCoUnidadLocalSap() {
        return coUnidadLocalSap;
    }

    public void setCoUnidadLocalSap(String coUnidadLocalSap) {
        this.coUnidadLocalSap = coUnidadLocalSap;
    }

    public String getCoUnidadVentaFraccion() {
        return coUnidadVentaFraccion;
    }

    public void setCoUnidadVentaFraccion(String coUnidadVentaFraccion) {
        this.coUnidadVentaFraccion = coUnidadVentaFraccion;
    }

    public String getDeUnidadFraccion() {
        return deUnidadFraccion;
    }

    public void setDeUnidadFraccion(String deUnidadFraccion) {
        this.deUnidadFraccion = deUnidadFraccion;
    }

    public String getEsProdLocal() {
        return esProdLocal;
    }

    public void setEsProdLocal(String esProdLocal) {
        this.esProdLocal = esProdLocal;
    }

    public Date getFeCreaProdLocal() {
        return feCreaProdLocal;
    }

    public void setFeCreaProdLocal(Date feCreaProdLocal) {
        this.feCreaProdLocal = feCreaProdLocal;
    }

    public Date getFeModProdLocal() {
        return feModProdLocal;
    }

    public void setFeModProdLocal(Date feModProdLocal) {
        this.feModProdLocal = feModProdLocal;
    }

    public String getIdCreaProdLocal() {
        return idCreaProdLocal;
    }

    public void setIdCreaProdLocal(String idCreaProdLocal) {
        this.idCreaProdLocal = idCreaProdLocal;
    }

    public String getIdModProdLocal() {
        return idModProdLocal;
    }

    public void setIdModProdLocal(String idModProdLocal) {
        this.idModProdLocal = idModProdLocal;
    }

    public String getInProdFraccionado() {
        return inProdFraccionado;
    }

    public void setInProdFraccionado(String inProdFraccionado) {
        this.inProdFraccionado = inProdFraccionado;
    }

    public String getInProdInventario() {
        return inProdInventario;
    }

    public void setInProdInventario(String inProdInventario) {
        this.inProdInventario = inProdInventario;
    }

    public String getInProductoReponer() {
        return inProductoReponer;
    }

    public void setInProductoReponer(String inProductoReponer) {
        this.inProductoReponer = inProductoReponer;
    }

    public String getNuRevisionProducto() {
        return nuRevisionProducto;
    }

    public void setNuRevisionProducto(String nuRevisionProducto) {
        this.nuRevisionProducto = nuRevisionProducto;
    }

    public Double getPcDctoVentaEspecial() {
        return pcDctoVentaEspecial;
    }

    public void setPcDctoVentaEspecial(Double pcDctoVentaEspecial) {
        this.pcDctoVentaEspecial = pcDctoVentaEspecial;
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getVaContenidoFraccion() {
        return vaContenidoFraccion;
    }

    public void setVaContenidoFraccion(Integer vaContenidoFraccion) {
        this.vaContenidoFraccion = vaContenidoFraccion;
    }

    public Double getVaCostoProducto() {
        return vaCostoProducto;
    }

    public void setVaCostoProducto(Double vaCostoProducto) {
        this.vaCostoProducto = vaCostoProducto;
    }

    public Integer getVaFraccion() {
        return vaFraccion;
    }

    public void setVaFraccion(Integer vaFraccion) {
        this.vaFraccion = vaFraccion;
    }

    public Double getVaPrecioPublico() {
        return vaPrecioPublico;
    }

    public void setVaPrecioPublico(Double vaPrecioPublico) {
        this.vaPrecioPublico = vaPrecioPublico;
    }

    public Double getVaRotacion() {
        return vaRotacion;
    }

    public void setVaRotacion(Double vaRotacion) {
        this.vaRotacion = vaRotacion;
    }

    public Double getVaTotalInventario() {
        return vaTotalInventario;
    }

    public void setVaTotalInventario(Double vaTotalInventario) {
        this.vaTotalInventario = vaTotalInventario;
    }

    public Double getVaVenta() {
        return vaVenta;
    }

    public void setVaVenta(Double vaVenta) {
        this.vaVenta = vaVenta;
    }

    public String getCoJ1() {
        return coJ1;
    }

    public void setCoJ1(String coJ1) {
        this.coJ1 = coJ1;
    }

    public String getCoJ2() {
        return coJ2;
    }

    public void setCoJ2(String coJ2) {
        this.coJ2 = coJ2;
    }

    public String getCoJ3() {
        return coJ3;
    }

    public void setCoJ3(String coJ3) {
        this.coJ3 = coJ3;
    }

    public String getCoJ4() {
        return coJ4;
    }

    public void setCoJ4(String coJ4) {
        this.coJ4 = coJ4;
    }

    public String getCoJ5() {
        return coJ5;
    }

    public void setCoJ5(String coJ5) {
        this.coJ5 = coJ5;
    }

    public String getDeJ1() {
        return deJ1;
    }

    public void setDeJ1(String deJ1) {
        this.deJ1 = deJ1;
    }

    public String getDeJ2() {
        return deJ2;
    }

    public void setDeJ2(String deJ2) {
        this.deJ2 = deJ2;
    }

    public String getDeJ3() {
        return deJ3;
    }

    public void setDeJ3(String deJ3) {
        this.deJ3 = deJ3;
    }

    public String getDeJ4() {
        return deJ4;
    }

    public void setDeJ4(String deJ4) {
        this.deJ4 = deJ4;
    }

    public String getDeJ5() {
        return deJ5;
    }

    public void setDeJ5(String deJ5) {
        this.deJ5 = deJ5;
    }

    public Boolean getSel() {
        return sel;
    }

    public void setSel(Boolean sel) {
        this.sel = sel;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDeProducto() {
        return deProducto;
    }

    public void setDeProducto(String deProducto) {
        this.deProducto = deProducto;
    }

    public String getDeUnidadProducto() {
        return deUnidadProducto;
    }

    public void setDeUnidadProducto(String deUnidadProducto) {
        this.deUnidadProducto = deUnidadProducto;
    }

    public String getStockEnCajas() {
        return stockEnCajas;
    }

    public void setStockEnCajas(String stockEnCajas) {
        this.stockEnCajas = stockEnCajas;
    }

    public String getCaComprasPendientes() {
        return caComprasPendientes;
    }

    public void setCaComprasPendientes(String caComprasPendientes) {
        this.caComprasPendientes = caComprasPendientes;
    }

    public String getCaTransito() {
        return caTransito;
    }

    public void setCaTransito(String caTransito) {
        this.caTransito = caTransito;
    }

    public String getCoDescuento1() {
        return coDescuento1;
    }

    public void setCoDescuento1(String coDescuento1) {
        this.coDescuento1 = coDescuento1;
    }

    public String getCoDescuento2() {
        return coDescuento2;
    }

    public void setCoDescuento2(String coDescuento2) {
        this.coDescuento2 = coDescuento2;
    }

    public String getCoDescuento3() {
        return coDescuento3;
    }

    public void setCoDescuento3(String coDescuento3) {
        this.coDescuento3 = coDescuento3;
    }

    public Date getFeReplica() {
        return feReplica;
    }

    public void setFeReplica(Date feReplica) {
        this.feReplica = feReplica;
    }

    public Date getFeUltModPrecio() {
        return feUltModPrecio;
    }

    public void setFeUltModPrecio(Date feUltModPrecio) {
        this.feUltModPrecio = feUltModPrecio;
    }

    public String getInModCambio() {
        return inModCambio;
    }

    public void setInModCambio(String inModCambio) {
        this.inModCambio = inModCambio;
    }

    public String getInReplica() {
        return inReplica;
    }

    public void setInReplica(String inReplica) {
        this.inReplica = inReplica;
    }

    public Integer getNuDiasRotacionPromedio() {
        return nuDiasRotacionPromedio;
    }

    public void setNuDiasRotacionPromedio(Integer nuDiasRotacionPromedio) {
        this.nuDiasRotacionPromedio = nuDiasRotacionPromedio;
    }

    public Integer getNuMaxDiasReposicion() {
        return nuMaxDiasReposicion;
    }

    public void setNuMaxDiasReposicion(Integer nuMaxDiasReposicion) {
        this.nuMaxDiasReposicion = nuMaxDiasReposicion;
    }

    public Integer getNuMinDiasReposicion() {
        return nuMinDiasReposicion;
    }

    public void setNuMinDiasReposicion(Integer nuMinDiasReposicion) {
        this.nuMinDiasReposicion = nuMinDiasReposicion;
    }

    public Integer getNuSecReplica() {
        return nuSecReplica;
    }

    public void setNuSecReplica(Integer nuSecReplica) {
        this.nuSecReplica = nuSecReplica;
    }

    public Double getPcDescuento1() {
        return pcDescuento1;
    }

    public void setPcDescuento1(Double pcDescuento1) {
        this.pcDescuento1 = pcDescuento1;
    }

    public Double getPcDescuento2() {
        return pcDescuento2;
    }

    public void setPcDescuento2(Double pcDescuento2) {
        this.pcDescuento2 = pcDescuento2;
    }

    public Double getPcDescuento3() {
        return pcDescuento3;
    }

    public void setPcDescuento3(Double pcDescuento3) {
        this.pcDescuento3 = pcDescuento3;
    }

    public Double getTrDias() {
        return trDias;
    }

    public void setTrDias(Double trDias) {
        this.trDias = trDias;
    }

    public Double getTsDias() {
        return tsDias;
    }

    public void setTsDias(Double tsDias) {
        this.tsDias = tsDias;
    }

    public List<ProductoLocal> getInsumosProducto() {
        return insumosProducto;
    }

    public void setInsumosProducto(List<ProductoLocal> insumosProducto) {
        this.insumosProducto = insumosProducto;
    }

    public Boolean getInInsumos() {
        return inInsumos;
    }

    public void setInInsumos(Boolean inInsumos) {
        this.inInsumos = inInsumos;
    }

    public Double getCalculoPrecioPublico() {
        double CPP= vaVenta - (vaVenta * (1-(1-(pcDescuento1/100))));
        BigDecimal bd = new BigDecimal(CPP);
        BigDecimal CPPD = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
        CalculoPrecioPublico = CPPD.doubleValue();

        return CalculoPrecioPublico;
    }

    private void initBasic()
    {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
    }

    public void sumarCantidad(Integer cantidad)
    {
        this.caStockDisponible += cantidad;
    }

    public void restarCantidad(Integer cantidad)
    {
         this.caStockDisponible -= cantidad;
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

        final ProductoLocal other = (ProductoLocal) obj;
        if (this.getPrimaryKey() != other.getPrimaryKey() && (this.getPrimaryKey() == null || !Arrays.equals(this.getPrimaryKey(), other.getPrimaryKey()))) {
            return false;
        }
        return true;
    }

}
