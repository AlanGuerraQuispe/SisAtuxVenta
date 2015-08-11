package atux.trasladoproducto.reference;

import java.sql.SQLException;

import javax.swing.JLabel;

import atux.util.common.AtuxColumnData;

/**
 *
 * @version 1.0<br>
 */
public class ConstantsTrasladoProducto {
    public static final int COL_SELECCION = 0;

    public static final String CO_LOCAL_CENTRAL = "099";
    public static final String CO_FORMULARIO_MAGISTRAL = "460";

    public static final String GRUPO_MOTIVO_TRAS_PROD = "03";

    public static final String TIPO_DOCUMENTO_GUIA_TRANSF = "04";
    public static final String GRUPO_MOTIVO_KARDEX = "01";
    public static final String MOTIVO_KARDEX_GUIA_SALIDA = "002";

    //    public static final String TIPO_DOCUMENTO_RECEPCION = "03";
    public static final String TIPO_DOCUMENTO_RECEPCION = "05";

    public static final String NUMERACION_PEDIDO_TRASLADO = "028";
    public static final String NUMERACION_RECEPCION_PRODUCTO = "013";

    public static final String NUMERACION_GUIA_SALIDA = "008";

    public static final String PEDIDO_TRASLADO_ES_PENDIENTE = "P";
    public static final String PEDIDO_TRASLADO_ES_ATENDIDO = "A";
    public static final String PEDIDO_TRASLADO_ES_RECIBIDO = "R";
    public static final String PEDIDO_TRASLADO_ES_ANULADO = "N";

    public static String[] PEDIDO_TRASLADO_ESTADO_DESC = {"Pendiente", "Atendido", "Recibido", "Anulado"};
    public static String[] PEDIDO_TRASLADO_ESTADO_CODIGO = {PEDIDO_TRASLADO_ES_PENDIENTE, PEDIDO_TRASLADO_ES_ATENDIDO, PEDIDO_TRASLADO_ES_RECIBIDO, PEDIDO_TRASLADO_ES_ANULADO};

    /**
     * Pedidos de traslados que se pueden dar entre tiendas
     */
    public static final String PEDIDO_TRASLADO_TIPO_TRASLADO = "ZTTT";
    public static final String PEDIDO_TRASLADO_TIPO_SOBRANTE = "ZTTS";
    public static final String PEDIDO_TRASLADO_TIPO_FALTANTE = "ZTTF";

    /**
     * Pedidos de tipo reposicion
     */
    public static final String PEDIDO_TRASLADO_TIPO_REPOSICION_AUTOMATICA = "ZTRA";
    public static final String PEDIDO_TRASLADO_TIPO_REPOSICION_MANUAL = "ZTRM";
    public static final String PEDIDO_TRASLADO_TIPO_REPOSICION_AUTOMATICA_INTERCOMPANIA = "ZP15";
    public static final String PEDIDO_TRASLADO_TIPO_REPOSICION_MANUAL_INTERCOMPANIA = "ZP16";

    /**
     * Pedidos de traslado que se pueden dar entre tienda y CD
     */
    public static final String PEDIDO_TRASLADO_TIPO_SOBRANTE_CD = "ZTRS";
    public static final String PEDIDO_TRASLADO_TIPO_FALTANTE_CD = "ZDRF";
    public static final String PEDIDO_TRASLADO_TIPO_SOBRANTE_CD_INTERCOMPANIA = "ZP17";
    public static final String PEDIDO_TRASLADO_TIPO_SOBRANTE_CD_TIENDA = "ZDRS";

    /**
     * Pedido de traslado de tipo devolucion
     */
    public static final String PEDIDO_TRASLADO_TIPO_DEVOLUCION = "ZDRM";
    public static final String PEDIDO_TRASLADO_TIPO_DEVOLUCION_INTERCOMPANIA = "ZTTD";

    public static final String RECEPCION_PRODUCTO_ES_CERRADO = "C";

    /**
     * Pedidos de Franquicia
     */
    public static final String PEDIDO_TRASLADO_TIPO_DEVOLUCION_CD_FRANQUICIA = "ZNC5";
    public static final String PEDIDO_TRASLADO_TIPO_DEV_REG_FALTANTE_TIENDA_FRANQUICIA = "ZNC6";
    public static final String PEDIDO_TRASLADO_TIPO_DEV_REG_DEV_SOBRANTE_CD_FRANQUICIA = "ZNC7";

    // Pantalla Pedido de traslado
    public static final int PEDIDO_TRASLADO_COL_CO_PRODUCTO = 0;
    public static final int PEDIDO_TRASLADO_COL_DE_UNIDAD = 2;
    public static final int PEDIDO_TRASLADO_COL_DE_UNIDAD_FRACCION = 3;
    public static final int PEDIDO_TRASLADO_COL_CA_SOLICITADA = 4;
    public static final int PEDIDO_TRASLADO_COL_IN_PROD_INV = 6;
    public static final int PEDIDO_TRASLADO_COL_IN_PROD_FRACCIONADO = 7;
    public static final int PEDIDO_TRASLADO_COL_VA_FRACCION = 8;
    public static final int PEDIDO_TRASLADO_COL_CA_RECEPCIONADA = 9;

    /**
     * Columnas de los Productos incluidos en el Pedido de Traslado
     */
    public static final AtuxColumnData columnsPedidoTrasladoDetalle[] = {
            new AtuxColumnData("Codigo", 60, JLabel.LEFT),
            new AtuxColumnData("Descripcion", 220, JLabel.LEFT),
            new AtuxColumnData("Unidad", 100, JLabel.LEFT),
            new AtuxColumnData("Unidad Frac", 90, JLabel.LEFT),
            new AtuxColumnData("Cantidad", 70, JLabel.RIGHT),
            new AtuxColumnData("Laboratorio", 185, JLabel.LEFT),
            new AtuxColumnData("IN_PROD_INV", 0, JLabel.RIGHT),
            new AtuxColumnData("IN_PROD_FRACCIONADO", 0, JLabel.RIGHT),
            new AtuxColumnData("VA_FRACCION", 0, JLabel.RIGHT),
            new AtuxColumnData("CA_RECEPCIONADA", 0, JLabel.RIGHT)
    };

    public static final Object[] defaultValuesPedidoTrasladoDetalle = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "};


    public static final int LISTA_PRODUCTOS_COL_CO_PRODUCTO = 1;
    public static final int LISTA_PRODUCTOS_COL_DE_PRODUCTO = 2;
    public static final int LISTA_PRODUCTOS_COL_UN_PRODUCTO = 3;
    public static final int LISTA_PRODUCTOS_COL_UN_FRACCION_PRODUCTO = 4;
    public static final int LISTA_PRODUCTOS_COL_IN_PROD_INV = 7;
    public static final int LISTA_PRODUCTOS_COL_IN_PROD_FRACCIONADO = 8;
    public static final int LISTA_PRODUCTOS_COL_VA_FRACCION = 9;
    public static final int LISTA_PRODUCTOS_COL_CA_RECEPCIONADA = 10;
    /**
     * Columnas de la lista de productos
     */
    public static final AtuxColumnData columnsListaProductos[] = {
            new AtuxColumnData("Sel.", 30, JLabel.CENTER),
            new AtuxColumnData("Codigo", 50, JLabel.CENTER),
            new AtuxColumnData("Descripcion", 260, JLabel.LEFT),
            new AtuxColumnData("Unidad", 100, JLabel.LEFT),
            new AtuxColumnData("Unidad Frac.", 100, JLabel.LEFT),
            new AtuxColumnData("Stock", 75, JLabel.RIGHT),
            new AtuxColumnData("St.Frac.", 60, JLabel.RIGHT),
            new AtuxColumnData("IN_PROD_INV", 0, JLabel.LEFT),
            new AtuxColumnData("IN_PROD_FRACCIONADO", 0, JLabel.LEFT),
            new AtuxColumnData("VA_FRACCION", 0, JLabel.LEFT),
            new AtuxColumnData("CA_RECEPCIONADA", 0, JLabel.LEFT)
    };
    public static final Object[] defaultValuesListaProductos = {new Boolean(false), " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};

    // Pantalla seleccion de local
    public static final AtuxColumnData columnsListaLocalCodigoDescripcion[] = {
            new AtuxColumnData("Codigo", 60, JLabel.CENTER),
            new AtuxColumnData("Descripcion", 350, JLabel.LEFT)
    };
    public static final Object[] defaultValuesListaLocalCodigoDescripcion = {" ", " "};

    public static final int LISTA_PEDIDO_COL_NU_PEDIDO = 2;
    public static final int LISTA_PEDIDO_COL_TI_PEDIDO = 1;
    public static final int LISTA_PEDIDO_COL_NU_RECEPCION_PRODUCTO = 9;
    public static final int LISTA_PEDIDO_COL_CO_ESTADO = 10;
    public static final int LISTA_PEDIDO_COL_CO_LOCAL = 7;
    public static final int LISTA_PEDIDO_COL_DE_LOCAL = 3;
    public static final int LISTA_PEDIDO_COL_CO_LOCAL_DESTINO = 8;
    public static final int LISTA_PEDIDO_COL_DE_LOCAL_DESTINO = 4;
    public static final int LISTA_PEDIDO_COL_FE_EMISION = 0;
    public static final AtuxColumnData columnsListaPedidoTraslados[] =
            {
                    new AtuxColumnData("Fecha", 120, JLabel.CENTER),
                    new AtuxColumnData("Tipo", 60, JLabel.LEFT),
                    new AtuxColumnData("Número", 80, JLabel.LEFT),
                    new AtuxColumnData("Origen", 100, JLabel.LEFT),
                    new AtuxColumnData("Destino", 100, JLabel.LEFT),
                    new AtuxColumnData("QF", 210, JLabel.LEFT),
                    new AtuxColumnData("Estado", 80, JLabel.LEFT),
                    new AtuxColumnData("CO_LOCAL", 0, JLabel.LEFT),
                    new AtuxColumnData("CO_LOCAL_DESTINO", 0, JLabel.LEFT),
                    new AtuxColumnData("NU_RECEPCION_PRODUCTO", 0, JLabel.LEFT),
                    new AtuxColumnData("CO_ESTADO", 0, JLabel.LEFT),
            };
    public static final Object[] defaultValuesListaPedidoTraslados = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};


    public static final AtuxColumnData columnsListaPedidoTrasladoDetalle[] =
            {
                    new AtuxColumnData("Producto", 55, JLabel.CENTER),
                    new AtuxColumnData("Descripcion", 205, JLabel.LEFT),
                    new AtuxColumnData("Unidad", 80, JLabel.LEFT),
                    new AtuxColumnData("C.Solicitada", 80, JLabel.RIGHT),
                    new AtuxColumnData("C.Atendida", 80, JLabel.RIGHT),
                    new AtuxColumnData("Un.Frac.", 90, JLabel.LEFT),
                    new AtuxColumnData("Un.Frac.Destino", 90, JLabel.RIGHT),
                    new AtuxColumnData("Estado", 70, JLabel.RIGHT),
                    new AtuxColumnData("NU_ITEM", 0, JLabel.RIGHT),
                    new AtuxColumnData("IN_PROD_FRACCIONADO", 0, JLabel.RIGHT),
                    new AtuxColumnData("VA_FRACCION", 0, JLabel.RIGHT),
                    new AtuxColumnData("IN_PROD_FRACCIONADO_DESTINO", 0, JLabel.RIGHT),
                    new AtuxColumnData("VA_FRACCION_DESTINO", 0, JLabel.RIGHT),
            };
    public static final Object[] defaultValuesListaPedidoTrasladoDetalle = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};

    public static final int DESPACHO_PEDIDO_DETALLE_DE_PRODUCTO = 1;
    public static final int DESPACHO_PEDIDO_DETALLE_UN_PRODUCTO_DESTINO = 2;
    public static final int DESPACHO_PEDIDO_DETALLE_CANTIDAD = 3;
    public static final int DESPACHO_PEDIDO_DETALLE_CANTIDAD_ATENDIDA = 4;
    public static final int DESPACHO_PEDIDO_DETALLE_UN_FRACCION_ORIGEN = 5;
    public static final int DESPACHO_PEDIDO_DETALLE_UN_FRACCION_DESTINO = 6;
    public static final int DESPACHO_PEDIDO_DETALLE_NU_ITEM = 8;
    public static final int DESPACHO_PEDIDO_DETALLE_IN_PRODUCTO_FRACCIONADO = 9;
    public static final int DESPACHO_PEDIDO_DETALLE_VA_FRACCION = 10;
    public static final int DESPACHO_PEDIDO_DETALLE_IN_PRODUCTO_FRACCIONADO_DESTINO = 11;
    public static final int DESPACHO_PEDIDO_DETALLE_VA_FRACCION_DESTINO = 12;
    public static final int DESPACHO_PEDIDO_DETALLE_DE_LABORATORIO = 13;


    public static final AtuxColumnData columnsDespachoPedidoTrasladoDetalle[] =
            {
                    new AtuxColumnData("Producto", 50, JLabel.CENTER),
                    new AtuxColumnData("Descripcion", 215, JLabel.LEFT),
                    new AtuxColumnData("Unidad", 80, JLabel.LEFT),
                    new AtuxColumnData("C.Solicitada", 70, JLabel.RIGHT),
                    new AtuxColumnData("C.Atendida", 70, JLabel.RIGHT),
                    new AtuxColumnData("Un.Frac.", 80, JLabel.LEFT),
                    new AtuxColumnData("Un.Frac.Destino", 80, JLabel.LEFT),
                    new AtuxColumnData("Estado", 80, JLabel.RIGHT),
                    new AtuxColumnData("NU_ITEM", 0, JLabel.RIGHT),
                    new AtuxColumnData("IN_PROD_FRACCIONADO", 0, JLabel.RIGHT),
                    new AtuxColumnData("VA_FRACCION", 0, JLabel.RIGHT),
                    new AtuxColumnData("IN_PROD_FRACCIONADO_DESTINO", 0, JLabel.RIGHT),
                    new AtuxColumnData("VA_FRACCION_DESTINO", 0, JLabel.RIGHT),
                    new AtuxColumnData("DE_LABORATORIO", 0, JLabel.RIGHT),
            };
    public static final Object[] defaultValuesDespachoPedidoTrasladoDetalle = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};


    public static final int LISTA_GUIA_RECEPCION_PRODUCTO_NU_RECEPCION_PRODUCTO = 0;
    public static final int LISTA_GUIA_RECEPCION_PRODUCTO_GUIA_MATRIZ = 1;
    public static final int LISTA_GUIA_RECEPCION_PRODUCTO_TIPO_ORIGEN = 2;
    public static final int LISTA_GUIA_RECEPCION_PRODUCTO_FECHA = 4;
    public static final int LISTA_GUIA_RECEPCION_PRODUCTO_ITEMS = 5;
    public static final int LISTA_GUIA_RECEPCION_PRODUCTO_CA_PRODUCTOS = 6;
    public static final int LISTA_GUIA_RECEPCION_PRODUCTO_CARGADO = 7;
    public static final int LISTA_GUIA_RECEPCION_PRODUCTO_NU_SOLICITUD_PEDIDO = 9;
    public static final int LISTA_GUIA_RECEPCION_PRODUCTO_IN_AFECTA_STOCK = 10;
    public static final int LISTA_GUIA_RECEPCION_PRODUCTO_CO_LOCAL_ORIGEN = 11;

    /**
     * Recepcion de Productos - Lista de Guias
     */
    public static final AtuxColumnData columnsListaGuiaRecepcionProducto[] = {
            new AtuxColumnData("R", 5, JLabel.LEFT),
//            new AtuxColumnData("Guía Matriz", 100, JLabel.LEFT),
            new AtuxColumnData("Nro. Entrega", 100, JLabel.LEFT),
            new AtuxColumnData("Tipo", 30, JLabel.LEFT),
            new AtuxColumnData("Local", 160, JLabel.LEFT),
            new AtuxColumnData("Fecha", 120, JLabel.LEFT),
            new AtuxColumnData("Items", 60, JLabel.RIGHT),
            new AtuxColumnData("Cant.P.", 60, JLabel.RIGHT),
            new AtuxColumnData("Cargado", 60, JLabel.LEFT),
            new AtuxColumnData("Observacion", 100, JLabel.LEFT),
            new AtuxColumnData("NU_SOLICITUD_PEDIDO", 0, JLabel.RIGHT),
            new AtuxColumnData("IN_AFECTA_STOCK", 0, JLabel.RIGHT),
            new AtuxColumnData("CO_LOCAL_ORIGEN", 0, JLabel.RIGHT),
            new AtuxColumnData("IN_ORIGEN_GUIA_INGRESO", 0, JLabel.LEFT),
    };
    public static final Object[] defaultValuesListaGuiaRecepcionProducto = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};


    public static final int LISTA_PRODUCTOS_DE_GUIA_CO_PRODUCTO = 0;
    public static final int LISTA_PRODUCTOS_DE_GUIA_DE_PRODUCTO = 1;
    public static final int LISTA_PRODUCTOS_DE_GUIA_STK_AFECTADO = 6;
    public static final int LISTA_PRODUCTOS_DE_GUIA_VA_FRACCION_GUIA = 8;
    public static final int LISTA_PRODUCTOS_DE_GUIA_VA_FRACCION_LOCAL = 9;

    /**
     * Recepcion de Productos - Lista de Productos x Guia
     */
    public static final AtuxColumnData columnsListaProductosDeGuia[] = {
            new AtuxColumnData("Codigo", 70, JLabel.LEFT),
            new AtuxColumnData("Descripcion", 275, JLabel.LEFT),
            new AtuxColumnData("Unidad", 120, JLabel.LEFT),
            new AtuxColumnData("Cantidad", 65, JLabel.RIGHT),
            new AtuxColumnData("OK", 65, JLabel.RIGHT),
            new AtuxColumnData("Diferencia", 65, JLabel.RIGHT),
            new AtuxColumnData("Afec.", 45, JLabel.RIGHT),
            new AtuxColumnData("Pag.", 45, JLabel.RIGHT),
            new AtuxColumnData("VaFraccionGuia", 0, JLabel.RIGHT),
            new AtuxColumnData("VaFraccionLocal", 0, JLabel.RIGHT)
    };
    public static final Object[] defaultValuesListaProductosDeGuia = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "};

	public static final int FORMATO_FECHA = 1;


    public static boolean esPedidoXSobranteOSobranteCD(String tiSolicitudPedido) {
        return PEDIDO_TRASLADO_TIPO_SOBRANTE.equals(tiSolicitudPedido) ||
               PEDIDO_TRASLADO_TIPO_SOBRANTE_CD.equals(tiSolicitudPedido);
    }

    public static boolean esPedidoXSobranteOFaltante(String tiSolicitudPedido) {
        return ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_SOBRANTE.equals(tiSolicitudPedido) ||
                ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_FALTANTE.equals(tiSolicitudPedido);
    }

    public static boolean esPedidoXSobranteOFaltanteCD(String tiSolicitudPedido) {
        return PEDIDO_TRASLADO_TIPO_SOBRANTE_CD.equals(tiSolicitudPedido) ||
                PEDIDO_TRASLADO_TIPO_FALTANTE_CD.equals(tiSolicitudPedido) ||
                PEDIDO_TRASLADO_TIPO_SOBRANTE_CD_INTERCOMPANIA.equals(tiSolicitudPedido);
    }

    public static boolean esLocalCentral(String coLocal) {
        return CO_LOCAL_CENTRAL.equals(coLocal);
    }

    public static boolean esPedidoDevolucion(String tipoPedido) {
        return PEDIDO_TRASLADO_TIPO_DEVOLUCION.equals(tipoPedido) ||
                PEDIDO_TRASLADO_TIPO_DEVOLUCION_INTERCOMPANIA.equals(tipoPedido)
                //Inicio ID: 001
                || PEDIDO_TRASLADO_TIPO_DEVOLUCION_CD_FRANQUICIA.equals(tipoPedido)
                //FIn ID:001
                ;
    }

    public static boolean esPedidoReposicionManual(String tipoPedido) {
        return PEDIDO_TRASLADO_TIPO_REPOSICION_MANUAL.equals(tipoPedido) ||
                PEDIDO_TRASLADO_TIPO_REPOSICION_MANUAL_INTERCOMPANIA.equals(tipoPedido) ||
                PEDIDO_TRASLADO_TIPO_DEV_REG_DEV_SOBRANTE_CD_FRANQUICIA.equals(tipoPedido) ||
                PEDIDO_TRASLADO_TIPO_DEV_REG_FALTANTE_TIENDA_FRANQUICIA.equals(tipoPedido);
                //Inicio ID: 001
//                ||
//                PEDIDO_TRASLADO_TIPO_DEVOLUCION_CD_FRANQUICIA.equals(tipoPedido);
               //Fin ID: 001
    }

    public static boolean esPedidoFranquicia(String tipoPedido) {
        return PEDIDO_TRASLADO_TIPO_DEVOLUCION_CD_FRANQUICIA.equals(tipoPedido) ||
               PEDIDO_TRASLADO_TIPO_DEV_REG_DEV_SOBRANTE_CD_FRANQUICIA.equals(tipoPedido) ||
               PEDIDO_TRASLADO_TIPO_DEV_REG_FALTANTE_TIENDA_FRANQUICIA.equals(tipoPedido);
    }

    public static boolean debeUsarseUnidadBase(String tipoPedido, String coLocalDevolucion) throws SQLException {
        boolean usarUnidadBase = false;
        usarUnidadBase = PEDIDO_TRASLADO_TIPO_REPOSICION_MANUAL.equals(tipoPedido) ||
                PEDIDO_TRASLADO_TIPO_REPOSICION_MANUAL_INTERCOMPANIA.equals(tipoPedido) ||
                PEDIDO_TRASLADO_TIPO_SOBRANTE_CD_INTERCOMPANIA.equals(tipoPedido) ||
                PEDIDO_TRASLADO_TIPO_SOBRANTE_CD.equals(tipoPedido) ||
                PEDIDO_TRASLADO_TIPO_FALTANTE_CD.equals(tipoPedido)  ||
                PEDIDO_TRASLADO_TIPO_DEV_REG_FALTANTE_TIENDA_FRANQUICIA.equals(tipoPedido);
        return usarUnidadBase;
    }

    public static boolean esPedidoCompaniaEckerd(String pTipoPedido) {
        return PEDIDO_TRASLADO_TIPO_TRASLADO.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_FALTANTE.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_SOBRANTE.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_FALTANTE_CD.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_SOBRANTE_CD.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_REPOSICION_AUTOMATICA.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_REPOSICION_MANUAL.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_DEVOLUCION.equals(pTipoPedido);
    }

    public static boolean esPedidoCompaniaBoticasOAmazonia(String pTipoPedido) {
        return PEDIDO_TRASLADO_TIPO_TRASLADO.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_FALTANTE.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_SOBRANTE.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_SOBRANTE_CD_INTERCOMPANIA.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_REPOSICION_AUTOMATICA_INTERCOMPANIA.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_REPOSICION_MANUAL_INTERCOMPANIA.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_DEVOLUCION.equals(pTipoPedido) ||
                //Inicio ID: 001
                PEDIDO_TRASLADO_TIPO_DEVOLUCION_CD_FRANQUICIA.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_DEV_REG_DEV_SOBRANTE_CD_FRANQUICIA.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_DEV_REG_FALTANTE_TIENDA_FRANQUICIA.equals(pTipoPedido)
                // Fin ID: 001
                ;
    }

    public static boolean esPedidoCompaniaFranquicia(String pTipoPedido) {
        return PEDIDO_TRASLADO_TIPO_SOBRANTE_CD_INTERCOMPANIA.equals(pTipoPedido) ||
               PEDIDO_TRASLADO_TIPO_REPOSICION_MANUAL_INTERCOMPANIA.equals(pTipoPedido) ||
               PEDIDO_TRASLADO_TIPO_DEVOLUCION_CD_FRANQUICIA.equals(pTipoPedido) ||
               PEDIDO_TRASLADO_TIPO_DEV_REG_DEV_SOBRANTE_CD_FRANQUICIA.equals(pTipoPedido) ||
               PEDIDO_TRASLADO_TIPO_DEV_REG_FALTANTE_TIENDA_FRANQUICIA.equals(pTipoPedido) ||
               PEDIDO_TRASLADO_TIPO_DEVOLUCION.equals(pTipoPedido);
    }

    public static boolean esPedidoEnUnidadBase(String pTipoPedido) {
        System.out.println("Tipo de Pedido : " + pTipoPedido);

        return PEDIDO_TRASLADO_TIPO_REPOSICION_AUTOMATICA_INTERCOMPANIA.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_REPOSICION_MANUAL_INTERCOMPANIA.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_REPOSICION_AUTOMATICA.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_REPOSICION_MANUAL.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_SOBRANTE_CD.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_FALTANTE_CD.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_SOBRANTE_CD_INTERCOMPANIA.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_SOBRANTE_CD_TIENDA.equals(pTipoPedido) ||
                //PEDIDO_TRASLADO_TIPO_DEV_REG_DEV_SOBRANTE_CD_FRANQUICIA.equals(pTipoPedido) ||
                PEDIDO_TRASLADO_TIPO_DEV_REG_FALTANTE_TIENDA_FRANQUICIA.equals(pTipoPedido);
    }

}
