package com.atux.config;


import java.awt.*;


/**
 * User: Kaiser
 * Date: 05/05/2009
 */
public class SPConstants {
    public static final Color COLOR_URGENTE = Color.ORANGE.darker();
    public static final Color COLOR_EMERGENCIA = Color.RED;
    public static final Color COLOR_NORMAL = Color.BLACK;

    public static String TI_CRITICIDAD_NORMAL = "001";
    public static String TI_CRITICIDAD_URGENTE = "002";
    public static String TI_CRITICIDAD_EMERGENCIA = "003";

    public static String TI_ALERTA_PLANIFICADOR = "001";


    public static final Long FK_PROCESO_SUNIMISTROS = new Long(1);
    public static final Long FK_PROCESO_FINANZAS = new Long(2);

    public static final Long PK_FORMA_PAGO_FACTURA_CONTRA_ENTREGA = new Long(1);



    public static final String TABLA_PROVEEDORES = "proveedores_mt";
    public static final String TABLA_SOLICITUD_PRODUCTO = "solicitud_producto_mt";
    public static final String TABLA_RC = "requisicion_compra_mt";
    public static final String TABLA_CARRITO_COMPRAS = "carrito_compras_tc";
    public static final String TABLA_ORDEN_COMPRA = "orden_compra_mt";
    public static final String TABLA_CONFORMIDAD_TECNICA = "conformidad_tecnica_mt";


    public static final Long FK_FUNCION_PROVEEDORES = new Long(28);
    public static final Long FK_FUNCION_SOLICITUD_PRODUCTO = new Long(2);
    public static final Long FK_FUNCION_SOLICITUD_COTIZACION = new Long(3);
    public static final Long FK_FUNCION_ORDEN_COMPRA_SUMINISTROS = new Long(4);
    public static final Long FK_FUNCION_ORDEN_COMPRA_EMERGENCIA = new Long(5);
    public static final Long FK_FUNCION_ORDEN_COMPRA_LOGISTICA = new Long(6);
    public static final Long FK_FUNCION_ORDEN_COMPRA_IMPORTACIONES = new Long(7);
    public static final Long FK_FUNCION_ORDEN_COMPRA_ADMINISTRACION = new Long(8);
    public static final Long FK_FUNCION_REQUISICION_COMPRA = new Long(35);
    public static final Long FK_FUNCION_ALERTA_PLANIFICADOR = new Long(3);
    public static final Long FK_FUNCION_CONFORMIDAD_TECNICA = new Long(37);


    public static final String CODIGO_PERU = "PE";
    public static final Long CODIGO_LIMA_DPTO = 5L;
    public static final Long CODIGO_LIMA_PROV = 127L;

    public static final String DB_TRUE = "1";
    public static final String DB_FALSE = "0";

    public static final String SI = "SI";
    public static final String NO = "NO";

    public static final boolean DB_TRUE_CHECK = true;
    public static final boolean DB_FALSE_CHECK = false;


    public static final String ES_APROBADO = "001";
    public static final String ES_PENDIENTE = "002";
    public static final String ES_RECHAZADO = "003";

    public static final Long FK_FUNCION_PRODUCTOS = new Long(30);

    public static final String IN_AGENTE_COMPRAS = "1";
    public static final String IN_AGENTE_SEGUIMIENTO = "2";
    public static final String IN_AGENTE_ACUERDO = "3";
    public static final String IN_AGENTE_IMPORTACION = "4";

    public static final Long MODULO_SOLICITUD_COTIZACION = new Long(5);
    public static final Long MODULO_COTIZACION = new Long(6);
    public static final Long MODULO_ORDEN_COMPRA = new Long(7);
    public static final Long MODULO_ALMACEN = new Long(9);

    public static final String DB_ACTIVO = "001";
    public static final String DB_INACTIVO = "002";

    public static final String CUENTAS_CONTABLES = "CUENTAS_CONTABLES";
    public static final String CENTROS_COSTOS = "CENTRO_COSTO_TM";
    public static final String SUBCTAS = "SUBCTAS";
    public static final String CUENTAS_PROVEEDORES = "CUENTAS_PROVEEDORES";
    public static final String CUENTAS_PROVEEDORES_IMPO = "CUENTAS_PROVEEDORES_IMPO";
    public static final String PROVEEDORES = "PROVEEDORES";
    public static final String TIPOS_MONEDAS = "TIPOS_MONEDAS";
    public static final String FUNCIONARIOS = "FUNCIONARIOS";
    public static final String ORDENES_COMPRA = "ORDENES_COMPRA";
    public static final String ITEMS_ORDEN_COMPRA = "ITEMS_ORDEN_COMPRA";
    public static final String DOCUMENTOS = "DOCUMENTOS";
    public static final String BANCOS = "BANCOS";
    public static final String CUENTAS_BANCOS = "CUENTAS_BANCOS";
    public static final String DEPARTAMENTOS = "DEPARTAMENTOS";
    public static final String DISTRITOS = "DISTRITOS";
    public static final String PAISES = "PAISES";
    public static final String PROVINCIAS = "PROVINCIAS";
    public static final String TIPO_CAMBIO = "TIPO_CAMBIO";

    public static final Long FK_OPERACION_NEGOCIO_CHIMBOTE = new Long(1);
    public static final Long FK_CRONOGRAMA_PAGO_ADELANTO = new Long(146);

    public static final String SELECCION_A = "A";
    public static final String SELECCION_B = "B";
    public static final String SELECCION_C = "C";
    public static final String SELECCION_D = "D";

    public static final String NO_AGENTE="AGENTE";
    public static final String NO_JEFE="JEFE";
}
