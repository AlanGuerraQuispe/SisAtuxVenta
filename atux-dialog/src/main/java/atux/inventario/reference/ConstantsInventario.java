package atux.inventario.reference;

import javax.swing.JLabel;

import atux.util.common.AtuxColumnData;

public class ConstantsInventario {
    /**
     * Tipos de Pedido
     */
    public static final String TIPO_PEDIDO_ADICIONAL = "AD";
    public static final String TIPO_PEDIDO_REPOSICION = "RP";

//   public static final int vCantMaximaItemsxGuiaTransf = 30;

    /** Cogigo de Locales */
//   public static final String CODIGO_LOCAL_MATRIZ = "098";

    /**
     * Grupos de Motivo
     */
    public static final String GRUPO_MOTIVO_KARDEX = "01";
    public static final String GRUPO_MOTIVO_KARDEX_KINETICA = "14";
    public static final String GRUPO_MOTIVO_PEDIDO_ADICIONAL = "02";
    public static final String GRUPO_MOTIVO_TRANSF_PRODUCTO = "03";
    public static final String GRUPO_MOTIVO_AJUSTE_INVENTARIO = "04";

    /**
     * Motivos Kardex
     */
    public static final String MOTIVO_KARDEX_GUIA_MODEM_MATRIZ = "050";
    //   public static final String MOTIVO_KARDEX_GUIA_MANUAL_SALIDA = "005";
    public static final String MOTIVO_KARDEX_VENTA = "001";
    public static final String MOTIVO_KARDEX_KINETICA = "001";
    public static final String MOTIVO_KARDEX_DEVOLUCION_VENTA = "054";
    public static final String MOTIVO_KARDEX_ANULACION_PEDIDO = "053";
    public static final String MOTIVO_KARDEX_GUIA_SALIDA = "002";
    public static final String MOTIVO_KARDEX_ANULA_GUIA_SALIDA = "066";
    public static final String MOTIVO_KARDEX_ANULA_GUIA_ENTRADA = "028";
    //   public static final String MOTIVO_KARDEX_GUIA_INGRESO = "067";
    public static final String MOTIVO_KARDEX_GUIA_MANUAL = "051";
    public static final String MOTIVO_KARDEX_STOCK_NEGATIVO = "003";
    public static final String MOTIVO_KARDEX_STOCK_POSITIVO = "052";
    public static final String MOTIVO_KARDEX_VENTA_DELIVERY = "007";
    public static final String MOTIVO_KARDEX_VENTA_ESPECIAL = "008";

    /**
     * Tipos de Documentos
     */
//   public static final String TIPO_BOLETA    = "01";
//   public static final String TIPO_FACTURA   = "02";
    public static final String TIPO_DOCUMENTO_GUIA_MATRIZ = "03";
    public static final String TIPO_DOCUMENTO_GUIA_TRANSF = "04";

    /**
     * Transferencia de Productos : Tipo de Loc
     */
    public static String[] TIPO_LOCAL_DESC = {"Local Venta", "Almacen", "Proveedor"};
    public static String[] TIPO_LOCAL_CODIGO = {"V", "A", "P"};
    public static String TIPO_LOCAL_VENTA = "V";
    public static String TIPO_LOCAL_ALMACEN = "A";
    public static String TIPO_LOCAL_PROVEEDOR = "P";

    /**
     * PEDIDO ADICIONAL: Atencion Cliente / Despacho de Pedido
     */
    public static String[] ATENCION_CLIENTE = {"Local", "Delivery"};
    public static String[] ATENCION_CLIENTE_CODIGO = {"L", "D"};

    public static String[] DESPACHO_PEDIDO = {"En Pedido", "Recojo"};
    public static String[] DESPACHO_PEDIDO_CODIGO = {"P", "R"};

    /**
     * Motivos Pedido Adicional
     */
    public static final String MOTIVO_PED_ADIC_CLIENTE = "001";
    public static final String MOTIVO_PED_ADIC_ENCARTE = "002";
    public static final String MOTIVO_PED_ADIC_ADICIONAL = "003";

    public static String NO_ESPECIFICADO = "*** No Especificado ***";

    public static final int PANT_PEDIDO_ADICIONAL = 0;
    public static final int PANT_TRANSFERENCIA_PROD = 1;

    public static final int COL_SELECCION = 0;
    public static final int PRECPROD_COL_CODIGO = 1;
    public static final int PRECPROD_COL_DESC = 2;

    public static final int PED_REP_CODIGO = 0;
    public static final int PED_REP_CANTSOLIC = 8;

    public static final String JTABLE_RELACIONCLIENTES = "CLIENTES";

    //public static final String FILTRO_LABORATORIO = "LABORATORIO";
    //public static final String FILTRO_GRUPO_PRODUCTO = "GRUPOPRODUCTO";
    //public static final String FILTRO_ACCION_TERAPEUTICA = "ACCIONTERAPEUTICA";

    public ConstantsInventario() {
    }

    /**
     * Inventario - Lista de Productos en un Pedido Adional
     */
    public static final AtuxColumnData columnsPedidoAdic[] = {
            new AtuxColumnData("Codigo", 80, JLabel.LEFT),
            new AtuxColumnData("Descripcion", 300, JLabel.LEFT),
            new AtuxColumnData("Unidad", 120, JLabel.LEFT),
            new AtuxColumnData("Cantidad", 80, JLabel.RIGHT),
            new AtuxColumnData("Stock", 80, JLabel.RIGHT),
    };
    public static final Object[] defaultValuesPedidoAdic = {" ", " ", " ", " ", " "};
    public static final Boolean[] isEditColsPedidoAdic = {new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(true), new Boolean(false)};
    public static final Object[] dataTypePedidoAdic = {new String(""), new String(""), new String(""), new Integer(0), new String("")};

    /**
     * INVENTARIO PEDIDOS ADICIONALES - Lista de Pedidos Adicionales Pendientes
     */
    public static final AtuxColumnData columnsListaPedAdic[] = {
            new AtuxColumnData("Nro.Pedido", 100, JLabel.CENTER),
            new AtuxColumnData("Fecha", 100, JLabel.LEFT),
            new AtuxColumnData("Motivo", 160, JLabel.LEFT),
            new AtuxColumnData("Items", 60, JLabel.RIGHT),
            new AtuxColumnData("Cliente", 180, JLabel.LEFT),
    };
    public static final Object[] defaultValuesListaPedAdic = {" ", " ", " ", " ", " "};


    /**
     * Kardex: Movimiento de Productos
     */
    public static final AtuxColumnData columnsKardexMovProd[] = {   //todo aguerra
            new AtuxColumnData("Fecha", 95, JLabel.LEFT),
            new AtuxColumnData("Descripcion", 129, JLabel.LEFT),
            //new AtuxColumnData( "Mot", 40, JLabel.LEFT ),
            new AtuxColumnData("Documento", 73, JLabel.CENTER),
            new AtuxColumnData("Entero", 50, JLabel.RIGHT),
            new AtuxColumnData("Fraccion", 55, JLabel.RIGHT),
            new AtuxColumnData("Entero", 50, JLabel.RIGHT),
            new AtuxColumnData("Fraccion", 55, JLabel.RIGHT),
            new AtuxColumnData("Entero", 50, JLabel.RIGHT),
            new AtuxColumnData("Fraccion", 55, JLabel.RIGHT),
            new AtuxColumnData("Frac.Inicial", 70, JLabel.RIGHT),
            new AtuxColumnData("Frac.final", 63, JLabel.RIGHT),
            new AtuxColumnData("Glosa", 140, JLabel.LEFT),
            new AtuxColumnData("Creador", 0, JLabel.LEFT),
    };
    public static final Object[] defaultValuesKardexMovProd = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};

    /**
     * Kardex: Ajuste Inventario
     */
    public static final AtuxColumnData columnsAjusteInv[] = {
            new AtuxColumnData("Codigo", 70, JLabel.LEFT),
            new AtuxColumnData("Nombre", 300, JLabel.LEFT),
            new AtuxColumnData("Unidad", 100, JLabel.LEFT),
            new AtuxColumnData("Unid.Frac.", 100, JLabel.LEFT),
            new AtuxColumnData("Cant.Ent", 70, JLabel.RIGHT),
            new AtuxColumnData("Cant.Frac", 70, JLabel.RIGHT),
    };
    public static final Object[] defaultValuesAjusteInv = {" ", " ", " ", " ", " "};

    /**
     * Kardex: Lista de Productos
     */
    public static final AtuxColumnData columnsKardexList[] = {
            new AtuxColumnData("Codigo", 60, JLabel.LEFT),
            new AtuxColumnData("Descripcion", 260, JLabel.LEFT),
            new AtuxColumnData("Unidad", 110, JLabel.LEFT),
            new AtuxColumnData("Unidad Frac.", 90, JLabel.LEFT),
            new AtuxColumnData("Cant.Ent", 70, JLabel.RIGHT),
            new AtuxColumnData("Cant.Frac", 60, JLabel.RIGHT),
            new AtuxColumnData("", 0, JLabel.LEFT),
            new AtuxColumnData("", 0, JLabel.LEFT),
    };
    public static final Object[] defaultValuesKardexList = {" ", " ", " ", " ", " ", "", ""};
    /**
     * Transferencia de Productos
     */
    public static final AtuxColumnData columnsTransProd[] = {
            new AtuxColumnData("Codigo", 60, JLabel.LEFT),
            new AtuxColumnData("Descripcion", 220, JLabel.LEFT),
            new AtuxColumnData("Unidad", 100, JLabel.LEFT),
            new AtuxColumnData("Unidad Frac", 90, JLabel.LEFT),
            new AtuxColumnData("P. Entero", 70, JLabel.RIGHT),
            new AtuxColumnData("P. Frac.", 60, JLabel.RIGHT),
            new AtuxColumnData("Fec. Vcto.", 70, JLabel.RIGHT),
            new AtuxColumnData("Laboratorio", 250, JLabel.LEFT),
    };
    public static final Object[] defaultValuesTransProd = {" ", " ", " ", " ", " ", " ", " ", " "};

    /**
     * Recepcion de Productos - Lista de Productos de Almacen x Guia
     */
    public static final AtuxColumnData columnsListaProdAlmxGuia[] = {
            new AtuxColumnData("Codigo", 70, JLabel.LEFT),
            new AtuxColumnData("Descripcion", 255, JLabel.LEFT),
            new AtuxColumnData("Unidad", 120, JLabel.LEFT),
            new AtuxColumnData("Cantidad", 65, JLabel.RIGHT),
            new AtuxColumnData("OK", 65, JLabel.RIGHT),
            new AtuxColumnData("Diferencia", 65, JLabel.RIGHT),
            new AtuxColumnData("Afec.", 45, JLabel.RIGHT),
            new AtuxColumnData("Pag.", 45, JLabel.RIGHT),
    };
    public static final Object[] defaultValuesListaProdAlmxGuia = {" ", " ", " ", " ", " ", " ", " ", " "};

    /**
     * Recepcion de Productos - Lista de Productos de Local x Guia
     */
    public static final AtuxColumnData columnsListaProdLocxGuia[] = {
            new AtuxColumnData("Codigo", 60, JLabel.LEFT),
            new AtuxColumnData("Descripcion", 220, JLabel.LEFT),
            new AtuxColumnData("Unidad", 100, JLabel.LEFT),
            new AtuxColumnData("Cant.", 50, JLabel.RIGHT),
            new AtuxColumnData("Frac.", 50, JLabel.RIGHT),
            new AtuxColumnData("OK", 50, JLabel.RIGHT),
            new AtuxColumnData("Frac.", 50, JLabel.RIGHT),
            new AtuxColumnData("Difer", 50, JLabel.RIGHT),
            new AtuxColumnData("Frac.", 50, JLabel.RIGHT),
            new AtuxColumnData("Afec.", 30, JLabel.RIGHT),
            new AtuxColumnData("Pag.", 30, JLabel.RIGHT),
    };
    public static final Object[] defaultValuesListaProdLocxGuia = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};

    /**
     * Recepcion de Productos - Lista de Guias
     */
    public static final AtuxColumnData columnsRecListaGuias[] = {
            new AtuxColumnData("R", 5, JLabel.LEFT),
            new AtuxColumnData("Guía Matriz", 100, JLabel.LEFT),
            new AtuxColumnData("Tipo", 30, JLabel.LEFT),
            new AtuxColumnData("Local", 160, JLabel.LEFT),
            new AtuxColumnData("Fecha", 120, JLabel.LEFT),
            new AtuxColumnData("Items", 60, JLabel.RIGHT),
            new AtuxColumnData("Cant.P.", 60, JLabel.RIGHT),
            new AtuxColumnData("Cargado", 60, JLabel.LEFT),
            new AtuxColumnData("Observacion", 100, JLabel.LEFT),
    };
    public static final Object[] defaultValuesRecListaGuias = {" ", " ", " ", " ", " ", " ", " ", " ", " "};

    /**
     * Almacenaro datos de los clientes los que seron mostrados en un JTable :
     * Codigo del Cliente, Tipo de Cliente, Descripcion (puede ser Apellidos,
     * Nombres o Razon Social; dependiendo del Tipo de Cliente), Tipo de Documento
     * y Número de Documento
     */
    public static final AtuxColumnData columnsRelacionClientes[] = {
            new AtuxColumnData("Codigo", 60, JLabel.CENTER),
            new AtuxColumnData("Tipo", 60, JLabel.LEFT),
            new AtuxColumnData("Descripcion", 280, JLabel.LEFT),
            new AtuxColumnData("Doc. 1", 60, JLabel.LEFT),
            new AtuxColumnData("Nro.Doc.", 74, JLabel.LEFT),
            new AtuxColumnData("Doc. 2", 60, JLabel.LEFT),
            new AtuxColumnData("Nro.Doc.", 74, JLabel.LEFT)
    };
    public static final Object[] defaultValuesRelacionClientes = {" ", " ", " ", " ", " ", " ", " "};

// INVENTARIO : Pedido Reposicion
// modificado 20040621
    // ryupanqui
    public static final AtuxColumnData columnsPedidoRepGen[] = {
            new AtuxColumnData("Codigo", 60, JLabel.LEFT),
            new AtuxColumnData("Descripcion", 230, JLabel.LEFT),
            new AtuxColumnData("Unidad", 120, JLabel.LEFT),
            new AtuxColumnData("Min", 50, JLabel.RIGHT),
            new AtuxColumnData("Max", 50, JLabel.RIGHT),
            new AtuxColumnData("Stock", 80, JLabel.RIGHT),
            new AtuxColumnData("NoPedir", 50, JLabel.CENTER),
            new AtuxColumnData("Calc.", 50, JLabel.RIGHT),
            new AtuxColumnData("Solic.", 50, JLabel.RIGHT),
            new AtuxColumnData("Ultimo", 0, JLabel.LEFT),
            new AtuxColumnData("Rot", 0, JLabel.LEFT),
            new AtuxColumnData("Can Min Exh", 0, JLabel.RIGHT),
            new AtuxColumnData("CP No Aten", 0, JLabel.RIGHT),
    };
    public static final Object[] defaultValuesPedidoRepGen = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};


    /**
     * INVENTARIO : Guia de Ingreso
     * <br>
     *
     * @autor Jorge Ruiz Adrianzon
     * @Version 1.0<br>
     */
    public static final AtuxColumnData columnsGuiaIngresoEnteros[] = {
            new AtuxColumnData("Codigo", 60, JLabel.LEFT),
            new AtuxColumnData("Descripcion", 260, JLabel.LEFT),
            new AtuxColumnData("Unidad", 100, JLabel.LEFT),
            new AtuxColumnData("Cant.", 80, JLabel.RIGHT),
            new AtuxColumnData("Prec.Unit.", 100, JLabel.RIGHT),
            new AtuxColumnData("Sub.Tot.", 100, JLabel.RIGHT),
            new AtuxColumnData("Fecha Venc.", 100, JLabel.RIGHT),
            new AtuxColumnData("Lote", 90, JLabel.RIGHT),
    };
    public static final Object[] defaultValuesGuiaIngresoEnteros = {" ", " ", " ", " ", " ", " ", " ", " "};


    public static final AtuxColumnData columnsGuiaIngresoFraccion[] = {
            new AtuxColumnData("Codigo", 45, JLabel.LEFT),
            new AtuxColumnData("Descripcion", 205, JLabel.LEFT),
            new AtuxColumnData("Unidad Ent.", 90, JLabel.LEFT),
            new AtuxColumnData("Unidad.Frac.", 90, JLabel.LEFT),
            new AtuxColumnData("Cant.Ent.", 60, JLabel.RIGHT),
            new AtuxColumnData("Cant.Frac.", 60, JLabel.RIGHT),
            new AtuxColumnData("Prec.Unit.", 65, JLabel.RIGHT),
            new AtuxColumnData("Subtotal", 90, JLabel.RIGHT),
            new AtuxColumnData("Fec. Vcto.", 90, JLabel.RIGHT),
            new AtuxColumnData("Lote", 80, JLabel.RIGHT),
    };
    public static final Object[] defaultValuesGuiaIngresoFraccion = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "};

    public static final AtuxColumnData columnsListaAjusteInv[] = {
            new AtuxColumnData("Fecha", 120, JLabel.LEFT),
            new AtuxColumnData("Codigo", 60, JLabel.LEFT),
            new AtuxColumnData("Descripcion", 170, JLabel.LEFT),
            new AtuxColumnData("Unidad", 90, JLabel.LEFT),
            new AtuxColumnData("Motivo", 150, JLabel.LEFT),
            new AtuxColumnData("Entero", 65, JLabel.RIGHT),
            new AtuxColumnData("Fraccion", 60, JLabel.RIGHT),
            new AtuxColumnData("Observacion", 120, JLabel.LEFT),
    };
    public static final Object[] defaultValuesListaAjusteInv = {" ", " ", " ", " ", " ", " ", " ", " ", " "};


    public static final AtuxColumnData columnsConsultaGM[] = {
            new AtuxColumnData("No.Referencia", 90, JLabel.CENTER),
            new AtuxColumnData("Fecha", 70, JLabel.CENTER),
            new AtuxColumnData("Origen", 145, JLabel.LEFT),
            new AtuxColumnData("Tip.Doc.", 70, JLabel.LEFT),
            new AtuxColumnData("No.Pedido", 100, JLabel.LEFT),
            new AtuxColumnData("Items", 60, JLabel.RIGHT),
            new AtuxColumnData("Total S/.", 80, JLabel.RIGHT),
            new AtuxColumnData("Estado", 55, JLabel.LEFT),
            new AtuxColumnData("E", 0, JLabel.LEFT),
            new AtuxColumnData("T", 0, JLabel.LEFT),
    };
    public static final Object[] defaultValuesConsultaGM = {" ", " ", " ", " ", " ", " ", " ", " ", " "};

    public static final AtuxColumnData columnsProdListInv[] =
            {
                    new AtuxColumnData("Sel.", 30, JLabel.CENTER),
                    new AtuxColumnData("Codigo", 50, JLabel.CENTER),
                    new AtuxColumnData("Descripcion", 260, JLabel.LEFT),
                    new AtuxColumnData("Unidad", 100, JLabel.LEFT),
                    new AtuxColumnData("Unidad Frac.", 100, JLabel.LEFT),
                    new AtuxColumnData("Stock", 75, JLabel.RIGHT),
                    new AtuxColumnData("St.Frac.", 60, JLabel.RIGHT),
                    new AtuxColumnData("In. Prod. Inv.", 0, JLabel.LEFT),
            };
    public static final Object[] defaultValuesProdListInv = {new Boolean(false), " ", " ", " ", " ", " ", " ", " "};

    public static final AtuxColumnData columnsListaTransProd[] = {
            new AtuxColumnData("Nro.Transf.", 70, JLabel.CENTER),
            new AtuxColumnData("Local Destino", 100, JLabel.LEFT),
            new AtuxColumnData("Fecha", 120, JLabel.LEFT),
            new AtuxColumnData("Nro.Docum.", 80, JLabel.LEFT),
            new AtuxColumnData("Motivo", 180, JLabel.LEFT),
            new AtuxColumnData("Items", 60, JLabel.RIGHT),
            new AtuxColumnData("Estado", 80, JLabel.LEFT),
    };
    public static final Object[] defaultValuesListaTransProd = {" ", " ", " ", " ", " ", " ", " "};

    public static final AtuxColumnData columnsPedidoRepRealizado[] = {
            new AtuxColumnData("Codigo", 80, JLabel.CENTER),
            new AtuxColumnData("Descripcion", 280, JLabel.LEFT),
            new AtuxColumnData("Unidad", 120, JLabel.LEFT),
            new AtuxColumnData("Cant.Mon.", 65, JLabel.RIGHT),
            new AtuxColumnData("Cant.Mox.", 65, JLabel.RIGHT),
            new AtuxColumnData("Cant.Solic.", 65, JLabel.RIGHT),
    };
    public static final Object[] defaultValuesPedidoRepRealizado = {" ", " ", " ", " ", " ", " "};

    public static final AtuxColumnData columnsListaPedRep[] = {
            new AtuxColumnData("Nro.Pedido", 90, JLabel.CENTER),
            new AtuxColumnData("Fecha", 110, JLabel.CENTER),
            new AtuxColumnData("Items", 70, JLabel.RIGHT),
            new AtuxColumnData("Productos", 70, JLabel.RIGHT),
            new AtuxColumnData("Estado", 70, JLabel.LEFT),
            new AtuxColumnData("Mon. Doas", 60, JLabel.RIGHT),
            new AtuxColumnData("Mox. Doas", 60, JLabel.RIGHT),
            new AtuxColumnData("No.Sem.Rot", 80, JLabel.RIGHT),
            new AtuxColumnData("", 0, JLabel.LEFT),
    };
    public static final Object[] defaultValuesListaPedRep = {" ", " ", " ", " ", " ", " ", " ", " ", ""};
    /**
     * TIPOS DE PANTALLA EN GUIA DE INGRESO MANUAL
     */
    public static final int PANT_GUIA_INGRESO_LOCAL = 0;
    public static final int PANT_GUIA_INGRESO_MATRIZ = 1;
    public static final int PANT_GUIA_INGRESO_COTIZACION = 2;
    public static final int PANT_GUIA_INGRESO_PROVEEDOR = 3;

    /**
     * TIPOS DE INGRESOS MANUALES
     */
    public static final String INGRESO_LOCAL_CADENA = "L";
    public static final String INGRESO_ALMACEN_CENTRAL = "A";
    public static final String INGRESO_COTIZACION = "C";
    public static final String INGRESO_PROVEEDOR = "P";

    /**
     * ******************
     */

    public static final AtuxColumnData columnsRelacionCodigoDescripcion[] = {
            new AtuxColumnData("Codigo", 60, JLabel.CENTER),
            new AtuxColumnData("Descripcion", 350, JLabel.LEFT)
    };
    public static final Object[] defaultValuesRelacionCodigoDescripcion = {" ", " "};

    /****************************LUIS MESIA RIVERA****************************/

    /**
     * Lista de Movimientos de Porductos x Guias
     */
    public static final AtuxColumnData columnsListaMovGuiaXProducto[] = {
            new AtuxColumnData("Codigo", 65, JLabel.LEFT),
            new AtuxColumnData("Descripcion", 240, JLabel.LEFT),
            new AtuxColumnData("Unidad", 115, JLabel.LEFT),
            new AtuxColumnData("Cantidad", 85, JLabel.RIGHT),
            new AtuxColumnData("Stock", 80, JLabel.RIGHT),
    };
    public static final Object[] defaultValuesListaMovGuiaXProducto = {" ", " ", " ", " ", " "};

    /**
     * Lista Detalle de Movimiento de Producto x guias
     */
    public static final AtuxColumnData columnsListaDetalleMovimientoXProductoxGuia[] = {
            new AtuxColumnData("Local", 160, JLabel.LEFT),
            new AtuxColumnData("Guia", 50, JLabel.LEFT),
            new AtuxColumnData("Nu. Documento", 110, JLabel.LEFT),
            new AtuxColumnData("Fecha", 90, JLabel.LEFT),
            new AtuxColumnData("Entera", 70, JLabel.RIGHT),
            new AtuxColumnData("Fraccion", 85, JLabel.RIGHT),
    };
    public static final Object[] defaultValuesListaDetalleMovimientoXProductoxGuia = {" ", " ", " ", " ", " ", " "};

    public static final AtuxColumnData columnsFiltroTransferencia[] = {
            new AtuxColumnData("Codigo", 80, JLabel.CENTER),
            new AtuxColumnData("Descripcion", 380, JLabel.LEFT),
    };
    public static final Object[] defaultValuesFiltroTransferencia = {" ", " "};

    /**
     * **********************************
     */

    public static final String FILTRO_LABORATORIO = "L";
    public static final String FILTRO_GRUPO_PRODUCTO = "G";
    public static final String FILTRO_ACCION_TERAPEUTICA = "A";
    public static final String FILTRO_PRINCIPIO_ACTIVO = "P";
    public static final String FILTRO_LGTR_GRUPO_PROD_ERP = "Z";
    public static final String FILTRO_LGTR_LINEA_PROD_ERP = "Y";
    public static final String FILTRO_LGTR_GRUPO_TERAPEUTICO = "X";
    public static final String FILTRO_LGTR_GRUPO_ANATOMICO = "W";
    //public static final String FILTRO_ACCION_TERAPEUTICA = "V";
    public static final String FILTRO_LGTR_ACCION_FARMACEUTICA = "U";

    public static final String FILTRO_LOCAL = "L";
    public static final String FILTRO_MOTIVO = "M";


    public static final AtuxColumnData columnsGuiaIngresoPsicotropicos[] = {
            new AtuxColumnData("Codigo", 50, JLabel.LEFT),
            new AtuxColumnData("Descripcion", 200, JLabel.LEFT),
            new AtuxColumnData("Unidad", 90, JLabel.LEFT),
            new AtuxColumnData("Cant. O/C", 70, JLabel.RIGHT),
            new AtuxColumnData("Cant. Doc.", 70, JLabel.RIGHT),
            new AtuxColumnData("Cant. Fis.", 70, JLabel.RIGHT),
            new AtuxColumnData("Prec.Unit.", 90, JLabel.RIGHT),
            new AtuxColumnData("Sub.Tot.", 100, JLabel.RIGHT),
            new AtuxColumnData("Fecha Venc.", 100, JLabel.RIGHT),
            new AtuxColumnData("Lote", 90, JLabel.RIGHT)
    };

    public static final Object[] defaultValuesGuiaIngresoPsicotropicos = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "};

}

