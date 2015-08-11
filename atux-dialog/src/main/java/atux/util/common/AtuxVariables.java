package atux.util.common;


import java.util.ArrayList;


public class AtuxVariables {

    public static final String MONEDA_SOLES     = "01";
    public static final String MONEDA_DOLARES   = "02";

    public static final String IMPUESTO_EXONERADO = "00";
    public static final String IMPUESTO_IGV       = "01";

    public static final int FORMATO_FECHA       = 1;
    public static final int FORMATO_FECHA_HORA  = 2;

    public static String TIPO_PEDIDO_NORMAL     = "1";
    public static String TIPO_PEDIDO_DELIVERY   = "2";
    public static String TIPO_PEDIDO_MANUAL     = "3";
    public static String TIPO_PEDIDO_ESPECIAL   = "5";
    public static String TIPO_PEDIDO_CREDITO    = "8";

    public static String TICKETERA_STAR         = "STAR";
    public static String TICKETERA_EPSON        = "EPSON";

    public static String TIPO_CAJA_TRADICIONAL    = "T";
    public static String TIPO_CAJA_MULTIFUNCIONAL = "M";

    public static final String TIPO_BOLETA         = "01";
    public static final String TIPO_FACTURA        = "02";
    public static final String TIPO_GUIA           = "03";
    public static final String TIPO_NOTA_CREDITO   = "04";
    public static final String TIPO_TICKET_BOLETA  = "05";
    public static final String TIPO_TICKET_FACTURA = "06";
    public static final String TIPO_BOLETA_MANUAL  = "15";
    public static final String TIPO_FACTURA_MANUAL = "16";

    public static String PERSONA_NATURAL  = "Natural";
    public static String PERSONA_JURIDICA = "Juridica";

    public static String NUMERACION_PEDIDO        = "001";
    public static String NUMERACION_PEDIDO_DIARIO = "012";

    public static final String TIPO_DOCUMENTO_PEDIDO = "06";

    public static final String PRODUCTO_LABORATORIO_VIRTUAL = "A";

    public static String PEDIDO_COBRADO   = "COBRADO";

    /**
     * Movimientos de Caja
     */
    public static String NUMERACION_MOVIMIENTO_CAJA    = "011";
    public static String TIPO_MOVIMIENTO_CAJA_APERTURA = "A";
    public static String TIPO_MOVIMIENTO_CAJA_ARQUEO   = "R";
    public static String TIPO_MOVIMIENTO_CAJA_CIERRE   = "C";
    public static String TIPO_MOVIMIENTO_CAJA_ENTREGA_PARCIAL = "E";

    /**
     * Grupos de Motivo
     */
    public static final String GRUPO_MOTIVO_KARDEX           = "01";
    public static final String GRUPO_MOTIVO_KARDEX_KINETICA  = "14";
    public static final String GRUPO_MOTIVO_PEDIDO_ADICIONAL = "02";
    public static final String GRUPO_MOTIVO_TRANSF_PRODUCTO  = "03";
    public static final String GRUPO_MOTIVO_AJUSTE_INVENTARIO= "04";
    /**
     * Motivos Kardex
     */
    public static final String MOTIVO_KARDEX_GUIA_MODEM_MATRIZ = "050";
    public static final String MOTIVO_KARDEX_VENTA             = "001";
    public static final String MOTIVO_KARDEX_KINETICA          = "001";
    public static final String MOTIVO_KARDEX_DEVOLUCION_VENTA  = "054";
    public static final String MOTIVO_KARDEX_ANULACION_PEDIDO  = "053";
    public static final String MOTIVO_KARDEX_GUIA_SALIDA       = "002";
    public static final String MOTIVO_KARDEX_ANULA_GUIA_SALIDA = "066";
    public static final String MOTIVO_KARDEX_ANULA_GUIA_ENTRADA= "028";
    public static final String MOTIVO_KARDEX_GUIA_MANUAL       = "051";
    public static final String MOTIVO_KARDEX_STOCK_NEGATIVO    = "003";
    public static final String MOTIVO_KARDEX_STOCK_POSITIVO    = "052";
    public static final String MOTIVO_KARDEX_VENTA_DELIVERY    = "007";
    public static final String MOTIVO_KARDEX_VENTA_ESPECIAL    = "008";

    public static final String GRUPO_MOTIVO_ANULACION_PEDIDO     = "07";
    public static final String MOTIVO_ANULACION_NO_COBRADO       = "001";
    public static final String MOTIVO_ANULACION_UNIR_PEDIDO      = "002";
    public static final String MOTIVO_ANULACION_DEVOLUCION_VENTA = "003";

    public static final String FORMA_PAGO_EFECTIVO                = "00000";
    public static final String FORMA_PAGO_DOLARES                 = "00007";
    public static final String FORMA_PAGO_CREDITO                 = "01376";
    public static final String FORMA_PAGO_VALE_FID                = "00580";
    public static final String FORMA_PAGO_PROMO_VALE              = "00530";
    public static final String FORMA_PAGO_CRED_LOS_ANDES          = "00065";
    public static final String FORMA_PAGO_REGULARIZACION          = "00470";
    public static final String FORMA_PAGO_REGULARIZACION_AMAZONIA = "00471";
    public static final String FORMA_PAGO_REGULARIZACION_ORIENTE  = "00505";

    public static String vTipoPedido         = AtuxVariables.TIPO_PEDIDO_NORMAL;
    public static String vNumeroPedidoDiario = new String("0000");
    public static String vNumeroPedido       = new String("");

    public static String vCoFormaPagoConvenio  = new String("");
    public static String vVaMontoCoPagoCliente = new String("");

    /**
     * Roles del Sistema
     */
    public static final String ROL_QUIMICO    = "01";
    public static final String ROL_VENDEDOR   = "02";
    public static final String ROL_RUTEADOR   = "03";
    public static final String ROL_CAJERO     = "04";
    public static final String ROL_INVENTARIO = "05";
    public static final String ROL_AUDITORIA  = "06";
    public static final String ROL_OPERADOR_SISTEMAS = "07";
    public static final String ROL_OPERADOR_VENTAS   = "08";
    public static final String ROL_OPERADOR_DELIVERY = "09";
    public static final String ROL_INV_CICLICO       = "56";

    /* Almacena el Indicador de un Pedido Unido*/
    public static String vInPedidoUnido = new String("");

    /** Almacena el valor del Total a Pagar por un Pedido determinado */
    public static double vTotalPagar = 0.00;

    /** Almacena el valor del Redondeo aplicado al Total a Pagar en un Pedido */
    public static double vRedondeo = 0.00;

    /** Almacena el Número de Comprobante que general el Pedido */
    public static String vNuComprobantePago = "";

    /** Almacena el Número de Impresora para Boleta */
    public static String vNuImpresoraBoleta = "";

    /** Almacena la Cola de Impresión para Boletas */
    public static String vDeColaImpresoraBoleta = "";

    /** Almacena el Número de Impresora para Factura */
    public static String vNuImpresoraFactura = "";

    /** Almacena la Cola de Impresión para Facturas */
    public static String vDeColaImpresoraFactura = "";

    /** Almacena el Número de Impresora para Guias */
    public static String vNuImpresoraGuia = "";

    /** Almacena la Cola de Impresión para Guias */
    public static String vDeColaImpresoraGuia = "";

    //almacena si tienen o no lectora
    public static boolean vTieneLectorTarjetas=false;

    /** Almacena los nombres de las formas de pago asociados a un Pedido **/
    public static String vNombresFormasPago = "";

    /** Almacena el Número de la Caja que realiza la Cobranza */
    public static String vNuCaja = "";

    /** Almacena el Número de Turno de la Caja aperturada */
    public static String vNuTurno = "";

    /** Almacena el Indicador si la Caja estÃ¡ abierta */
    public static String vInCajaAbierta = "";

    public static final int ITEMS_POR_COMPROBANTE = 10;

    //Almacena el Tipo de Cliente
    public static String vTipoCliente = new String("");

    // Almacena el Nombre que aparecerÃ¡ en el Comprobante de Pago    
    public static String vANombreDe = new String("");

    public static String vNumeroTelefono = new String("");

    /**
     * Almacena el Código del Cliente al que se Factura
     */
    public static String vCoClienteLocalFact = new String("");

    //Almacena el RUC del Cliente al que se Factura    
    public static String vRUCFacturar = new String("");

    // Almacena la Dirección que aparecerÃ¡ en el Comprobante de Pago    
    public static String vDireccion = new String("");

    /** Almacena el Tipo de Comprobante que genera el Pedido */
    public static String vTiComprobante = "";

    public static ArrayList vFormasPago = new ArrayList();

    /**
     * Indica si el producto esta afectado por venta por tarjeta electrÃ³nica, sus
     * valores son A= tiene laboratorio virtual,
     * N = no tiene laboratorio virtual,
     */
    public static String vIndicaProductoTarjElec = new String("");

    /**
     * Almacena el modo en el que fuÃ© cerrada una Ventana.
     * Si la ventana es cerrada Aceptando se almacena TRUE, en caso contrario,
     * es decir, se cierra la ventana pulsando la tecla [Esc] se almacena FALSE
     */
    public static boolean vAceptar = false;

    /**
     * Almacena el Código de la Compañía a la cual se pertenece
     */
    public static String vCodigoCompania = "";

    /**
     * Almacena el Código de Compañía usado por la Oficina de Personal
     */
    public static String vCodigoCompaniaRRHH = "";

    /**
     * Almacena el Local donde se ejecuta la Aplicación
     */
    public static String vCodigoLocal = "";

    /**
     * Almacena la descripción corta del Local
     */
    public static String vDescripcionCortaLocal = "";

    /**
     * Almacena la descripción del Local
     */
    public static String vDescripcionLocal = "";

    /**
     * Almacena el Tipo de Caja establecido según disposición del Local.
     * Los tipos existentes son :
     * Tradicional : Existen vendedores y un determinado Número de cajeros
     * Multifuncional : Existen vendedores - cajeros (ambas funciones)
     */
    public static String vTipoCaja = "";

    /**
     * Almacena el Secuencial del Usuario logueado a la Aplicación
     */
    public static String vNuSecUsuario = "";

    /**
     * Numero de pedido de reposición
     */
    public static String vNumeroPedidoProd = "0";

    /**
     * Almacena Secuencial - Codigo del Cajero
     */
    public static String vNuSecUsuarioCajero = "";

    /**
     * Almacena el Id del Usuario logueado a la Aplicación
     */
    public static String vIdUsuario = "";

    /**
     * Almacena el Id del Usuario logueado a Caja Electronica
     */
    public static String vIdUsuarioCajaElectronica = "";

    /**
     * Almacena el Nombre del Usuario logueado a la Aplicación
     */
    public static String vNoUsuario = "";

    /**
     * Alamacena el codigo de delivery*
     */
    public static String vCoLocalDelivery = "";

    /**
     * Alamacena el codigo de delivery*
     */
    public static String vParamVentaDelivery = "";

    //agregado el 04-12-2007
    public static boolean vConectadoADelivery = false;

    /**
     * Alamacena el si es posible el INBKDIRECTO*
     */
    public static String vParamInbkDirecto = "";


    /**
     * Almacena el Apellido Paterno del Usuario logueado a la Aplicación
     */
    public static String vPaternoUsuario = "";

    /**
     * Almacena el Apellido Materno del Usuario logueado a la Aplicación
     */
    public static String vMaternoUsuario = "";

    /**
     * Almacena el Tipo de Cambio usado por la Aplicación
     */
    public static double vTipoCambio = 0.00;

    /**
     * Almacena el tipo de cambio con el que se trabaja el Pedido
     */
    public static double vTipoCambioPedido = 0.00;

    /**
     * Almacena el Código de la Moneda
     */
    public static String vCodigoMoneda = "01"; //Moneda en soles

    /**
     * Almacena la ruta donde se ubican las imÃ¡genes de los productos
     */
    public static String vImagenProducto = "";


    public static String vPaginaWeb = "";

    /**
     * Almacena la ruta donde se ubican el explorador de Internet
     */
    public static String vBrowser1 = "";
    public static String vBrowser2 = "";

    /**
     * Almacena el valor del Impuesto General a las Ventas - 19.00%
     */
    public static double vIgvPorcentaje = 0.00;

    /**
     * Almacena el valor del Impuesto General a las Ventas para Cálculo - 1.19
     */
    public static double vIgvCalculo = 0.00;

    public static int vTipoAccion;
    public static String vImpresoraReporte = "LPT1";
    public static String vImpresoraTestigo = "LPT1";
    public static String vImpresoraComanda = "LPT1";
    public static String vImpresoraTicketBoleta= "";
    public static String vImpresoraBoleta      = "";
    public static String vImpresoraFactura     = "";
    public static String vImpresoraGuia        = "";
    public static String vImpresoraGuiaLote    = "LPT1";

    public static ArrayList vDataMantenimiento = new ArrayList();
    public static String vSQL = "";

    public static String vNombrePC = "";
    public static String vIP_PC = "";
    public static String vServidorImpresion = "N";

    public static String vMultipleFormasPago = "N";
    public static String vMultipleComprobantes = "N";

    /**
     * Indicador de descuento adicional
     */
    public static boolean vDsctoAdicional = false;

    /**
     * Valor de descuento adicional
     */
    public static double vValorDescuento = 0.0;

    /**
     * Valor del departamento del local
     */
    public static String vDepartamentoLocal = "";

    /**
     * Valor del codigo departamento del local
     */
    public static String vCodigoDepartamentoLocal = "";

    /**
     * Valor de la provincia del local
     */
    public static String vProvinciaLocal = "";
    /**
     * Valor de la codigo provincia del local
     */
    public static String vCodigoProvinciaLocal = "";

    /**
     * Valor del numero de RUC de la compania del local
     */
    public static String vNuRucCompania = "";

    public static String vCodigoMonedaPais = "01";

    public static String vDescripcionCompania = new String("");

    /**
     * Indica si se está corriendo la aplicación en un Local o en Matriz
     */
    public static boolean vInkVenta_Matriz = false;

    /**
     * Indica como se realiza el redondeo de los pedidos
     */
    public static int vCifraRedondeo = 5;
    /**
     * Cantidad de pedidos por el cual se realizara la reconexion a BD
     */
    public static String vCantPedConn = "";

    /**
     * Margen de pedidos por el cual se reconectara a un punto de venta
     */
    public static String vRangPedConn = "";
    /* ************************************************************************ */

    /**
     * Parametro de activacion / desactivación de opciones en locales
     */
    public static boolean vActivarComplementariosF8 = false;
    public static boolean vActivarFidelizacionF11 = false;

    /**
     * Parametros para la etiquetera
     */
    //public static String vInImprimeTicket = "S";
    public static String vDeMensajeTicket = "";
    public static String vDeImpresoraBoleta = "";
    public static String vCortaTicket = "S";
    public static String vLocalTicket = "ATUX FARMA";
    public static int vCantMaxPorProductoVirtual = 5;
    public static double vRedondearTo = 0.1;
    public static double vMaximoMontoDonacion = 10;
    //public static String vRealizarPeticion = AdmPedidosVirtuales.REALIZA_PETICION;

    //Agregado el 10-10-2006 para impresion de datos empresa
    public static String vCompaniaFono = "";
    public static String vCompaniaFax = "";
    public static String vCompaniaDireccion = "Jr. Elias Aguirre Nº141 Of 308"; //maximo 32 caracteres
    public static String vCompaniaDireccionWeb = "www.atux.com";

    /* ************************************************************************ */
    /*  JGalarza 29-03-2006                                                     */
    public static String vDireccionLocal = "";
    public static String vDistritoLocal = "";

    public static String vInComprobanteManual = "N";
    public static String vInComprobanteIngresado = "N";
    public static String vSerieNumeroComprobanteManual = "";
    //
    public static String vInTicketBoleta = "N";
    public static String vInTicketFactura = "N";

    public static boolean donacionInCaja = false;
    //
    public static boolean vCobroConRedondeo = true;

    public static boolean vCreditoConConexionMatriz = true;

    public static boolean vPermitePedidoSoloDonacion = false;

    public static String vTicketera = "STAR";
    public static String vCoSucursal = "001";

    public static String vFormaPagoFidelizacion = "CHEQUE FID";

    //Inicio ID: 002
    public static boolean vBusquedaClientesInicioVenta = false;
    //Fin ID: 002

    //Inicio ID: 003
    public static boolean vMuestraMensajeInkaClub = false;
    //Fin ID: 003

    //Inicio ID: 004
    public static boolean vUsaLineaCreditoPorEmpresa = false;
    //Fin ID: 004

    //Inicio ID: 005
    public static boolean vSolicitaVBQuimicoCupones = true;
    //Fin ID: 005

    //Inicio ID: 007
    public static boolean vLocalImplementaSAP = false;

    public static boolean vUsaTransferenciaAnterior = false;

    public static boolean vModoTest = false;

    public static boolean vMuestraCambioProducto = false;

    public static boolean vPermiteConvenioCambioProducto = false;

    public static boolean vModoSAPActivado = false;
    //Fin ID: 007  

    //Inicio ID: 008
    public static boolean vOcultarAnularDevolucionRecetario = true;
    //Fin ID: 008

    //Inicio ID: 009
    public static boolean vOcultarReimpresionGuias = true;
    //Fin ID: 009


    public static boolean vMuestraOpcionInkaTest = true;

    public static boolean vMuestraOpcionRecetario = true;


    /* ************************************************************************ */
    /*  Cambios realizados por YCarranza el 28/01/2010                           */
    /* ************************************************************************ */
    /**
     * Modelo de la caja electronica NCR
     */
    public static String  vModelCashDrawer= "";

    /**
     * Indica si se maneja el puslso o no
     */
    public static  boolean pulsoCashDrawer = false;

    /**
     * Indica la ruta del archivo donde esta configurado los dispositivos
     */
    public static String  dirConfigDispositivo= "";
    /* ************************************************************************ */

    //Inicio ID: 010
    public static String vCodigoComercioFullCarga = "";
    //Fin ID: 010

    // Inicio ID: 011
    public static boolean vImplementaLogFire = false;
    // Fin ID: 011

    //Inicio ID: 012
    public static boolean vImplementaVisa = false;

    public static boolean vImplementaMcProcesos = false;

    public static String vCodComercio = "00000001";

    public static String vCodCaja = "0000001";

    public static String vFormaVisa = "";

    public static String vFormaVisaCredito = "";

    public static String vFormaVisaDebito = "";

    public static String vFormaMc = "";
    //Fin ID: 012

    // Inicio ID: 013
    public static boolean vMuestraCorreccionComprobantes = false;
    // Fin ID: 013

    // Inicio ID: 014
    public static boolean vSoloAdelantarCorrelativos = false;
    // Fin ID: 014

    // Inicio ID: 015
    public static boolean vMuestraMenuCajaElectronica = true;
    // INicio ID: 015

    // Inicio ID: 016
    public static boolean vMuestraMenuSaludSinFronteras = true;
    // INicio ID: 016

    // Inicio ID: 017
    public static String vVersionBD = "";
    // INicio ID: 017
    public static ArrayList arrayPedidos;

    public static ArrayList arrayProductos;

    public static ArrayList arrayClientes;

    public static int vDiasPagoCredito = 15;

    public static boolean vMuestraListaPrincipiosActivos = false;
    /**
     * Constructor
     */
    public AtuxVariables() {
    }

}
