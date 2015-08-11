CREATE OR REPLACE PACKAGE ecventa.INKVENTA_INVENTARIO AS

 /**
 * Copyright (c) 2003 Eckerd Peru S.A.
 *
 * Entorno de Desarrollo : Oracle9i
 * Nombre del Paquete : ECVENTA.INKVENTA_INVENTARIO
  *
  * Historico de Creacion/Modificacion
  * RCASTRO       20.01.2003   Creacion
  * RYUPANQUI   09.06.2004   Modificacion
  *
  * @author Rolando Castro
  * @author Rudy Yupanqui
  * @version 1.0
  * PP 10-06-2009
  * PP 18-02-2011
  * PP 03-05-2011
  */

/* version 2.0  GAZABACHE  02.02.2007 Modificado */

/*Declaracion de Variables*/
ESTADO_TRANSF_PROC CHAR(1):= 'P';
ESTADO_TRANSF_IMPR CHAR(1):= 'I';
ESTADO_TRANSF_REIM CHAR(1):= 'R';
ESTADO_TRANSF_ANUL CHAR(1):= 'N';
DESCRIP_TRANSF_PROC VARCHAR(10):= 'Proceso';
DESCRIP_TRANSF_IMPR VARCHAR(10):= 'Impreso';
DESCRIP_TRANSF_REIM VARCHAR(10):= 'Reimpreso';
DESCRIP_TRANSF_ANUL VARCHAR(10):= 'Anulado';

ESTADO_RECEP_PROD_PENDIENTE CHAR(1):= 'P';
ESTADO_RECEP_PROD_COMPLETO CHAR(1):= 'C';
DESCRIP_RECEP_PROD_PENDIENTE VARCHAR(15):= 'Pendiente';
DESCRIP_RECEP_PROD_COMPLETO VARCHAR(15):= 'Completo';

COD_MOT_GUIA_MODERN CHAR(3):= '004';
DESCRIP_MOT_GUIA_MODERN VARCHAR2(60):= 'Guia Modem Ajuste';

  -- Variable que almacenara el resultado de los query's
  TYPE EckerdCursor IS REF CURSOR;

  -- Retorna la relacion de Productos ordenados por descripcion.
  FUNCTION INV_RELACIONPRODUCTOS(codigocompania_in  IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
              codigolocal_in IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE)
       RETURN EckerdCursor;

  -- Graba datos de cabecera del Pedido Adicional
  FUNCTION INV_GRABA_PEDIDO_ADIC(codigocompania_in  IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                                codigolocal_in      IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
        tipopedidoprod_in   IN LGTC_PEDIDO_PRODUCTO.TI_PEDIDO_PRODUCTO%TYPE,
        grupomotivo_in  IN LGTC_PEDIDO_PRODUCTO.CO_GRUPO_MOTIVO%TYPE,
        motivopedido_in  IN LGTC_PEDIDO_PRODUCTO.CO_MOTIVO_PEDIDO%TYPE,
        localdestino_in     IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL_DESPACHO%TYPE,
        codigocliente_in    IN LGTC_PEDIDO_PRODUCTO.CO_CLIENTE_LOCAL%TYPE,
        fecharequerida_in   IN CHAR,
        idcreapedido_in     IN LGTC_PEDIDO_PRODUCTO.ID_CREA_PEDIDO_PRODUCTO%TYPE,
        atencioncliente_in  IN LGTC_PEDIDO_PRODUCTO.IN_ATENCION_CLIENTE%TYPE,
        despachopedido_in   IN LGTC_PEDIDO_PRODUCTO.IN_DESPACHO_PEDIDO%TYPE,
        nombrerecoje_in     IN LGTC_PEDIDO_PRODUCTO.NO_RECOJE_PEDIDO%TYPE,
        cantidaditems_in    IN LGTC_PEDIDO_PRODUCTO.CA_TOTAL_PRODUCTO%TYPE)
           RETURN LGTC_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE;

  -- Graba datos de detalle de un Pedido Adicional
  PROCEDURE INV_GRABA_DET_PEDIDO_ADIC(codigocompania_in   IN LGTD_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                                codigolocal_in      IN LGTD_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
        tipopedidoprod_in   IN LGTC_PEDIDO_PRODUCTO.TI_PEDIDO_PRODUCTO%TYPE,
        numeropedido_in  IN LGTD_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE,
        codigoproducto_in   IN LGTD_PEDIDO_PRODUCTO.CO_PRODUCTO%TYPE,
        cantidad_in         IN LGTD_PEDIDO_PRODUCTO.CA_PEDIDA%TYPE,
        numeroitem_in       IN LGTD_PEDIDO_PRODUCTO.NU_ITEM%TYPE,
        idcreapedido_in     IN LGTD_PEDIDO_PRODUCTO.ID_CREA_DET_PEDIDO_PRODUCTO%TYPE
        );

 FUNCTION OBTIENE_LISTA_PED_REP_REAL(codigocompania_in  IN LGTD_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
              codigolocal_in IN LGTD_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
                                numeroproducto_in IN LGTD_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE)
           RETURN EckerdCursor;

  FUNCTION INV_KARDEX_LISTA_PROD(codigocompania_in  IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
              codigolocal_in IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE)
           RETURN EckerdCursor;

  FUNCTION DESCRIP_LABORATORIO(codigocompania_in IN LGTM_PRODUCTO.CO_COMPANIA%TYPE,
                         codigoproducto_in IN LGTM_PRODUCTO.CO_PRODUCTO%TYPE)
           RETURN CMTR_LABORATORIO.DE_LABORATORIO%TYPE;

  -- Muestra lista de Guias Pendientes de Afectar Stock
  FUNCTION INV_LISTA_GUIAS_PEND(codigocompania_in IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
             codigolocal_in IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE)
           RETURN EckerdCursor;

  -- Muestra lista de Productos de un almacen de una Guia Pendiente de Afectar Stock
  FUNCTION INV_DETALLE_GUIA_ALMACEN(codigocompania_in IN LGTD_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
            codigolocal_in IN LGTD_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
       numerorecepcion_in IN  LGTD_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE)
           RETURN EckerdCursor;

  -- Muestra lista de Productos de un almacen de una Guia Pendiente de Afectar Stock
  FUNCTION INV_DETALLE_GUIA_LOCAL(codigocompania_in IN LGTD_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
            codigolocal_in IN LGTD_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
       numerorecepcion_in IN  LGTD_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE)
           RETURN EckerdCursor;

  -- Muestra lista de pedidos adicionales pendientes de Envio a Central
  FUNCTION INV_LISTA_PEDIDO_ADIC_PEND(codigocompania_in IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
             codigolocal_in IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE)
           RETURN EckerdCursor;

  -- Muestra lista de pedidos de reposicion realizados
  FUNCTION INV_LISTA_PEDIDO_REP_PEND(codigocompania_in IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                  codigolocal_in    IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE)
           RETURN EckerdCursor;

  -- Muestra el Detalle de un Pedido Adicional Seleccionado
  FUNCTION INV_DETALLE_PEDIDO_ADIC(codigocompania_in IN LGTD_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
             codigolocal_in LGTD_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
        numeropedido_in IN LGTD_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE)
           RETURN EckerdCursor;

  -- Graba datos de cabecera del Pedido Reposicion
  FUNCTION INV_GRABA_PEDIDO_REP(codigocompania_in  IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                                codigolocal_in      IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
        tipopedidoprod_in   IN LGTC_PEDIDO_PRODUCTO.TI_PEDIDO_PRODUCTO%TYPE,
        localdestino_in     IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL_DESPACHO%TYPE,
        idcreapedido_in     IN LGTC_PEDIDO_PRODUCTO.ID_CREA_PEDIDO_PRODUCTO%TYPE/*,
        cantidaditems_in    IN LGTC_PEDIDO_PRODUCTO.CA_TOTAL_PRODUCTO%TYPE*/,
        numerosemanasrot_in IN LGTC_PEDIDO_PRODUCTO.NU_DIAS_ROTACION_PROMEDIO%TYPE,
        numerominimodias_in IN LGTC_PEDIDO_PRODUCTO.NU_MINIMO_DIAS_REPOSICION%TYPE,
        numeromaximodias_in IN LGTC_PEDIDO_PRODUCTO.NU_MAXIMO_DIAS_REPOSICION%TYPE)
           RETURN LGTC_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE;
  -- Graba datos de cabecera del Pedido Reposicion sin hacer commit
  FUNCTION INV_GRABA_PEDIDO_REP_NO_COMMIT(codigocompania_in  IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                                codigolocal_in      IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
        tipopedidoprod_in   IN LGTC_PEDIDO_PRODUCTO.TI_PEDIDO_PRODUCTO%TYPE,
        localdestino_in     IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL_DESPACHO%TYPE,
        idcreapedido_in     IN LGTC_PEDIDO_PRODUCTO.ID_CREA_PEDIDO_PRODUCTO%TYPE/*,
        cantidaditems_in    IN LGTC_PEDIDO_PRODUCTO.CA_TOTAL_PRODUCTO%TYPE*/,
        numerosemanasrot_in IN LGTC_PEDIDO_PRODUCTO.NU_DIAS_ROTACION_PROMEDIO%TYPE,
        numerominimodias_in IN LGTC_PEDIDO_PRODUCTO.NU_MINIMO_DIAS_REPOSICION%TYPE,
        numeromaximodias_in IN LGTC_PEDIDO_PRODUCTO.NU_MAXIMO_DIAS_REPOSICION%TYPE)
           RETURN LGTC_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE;


  -- Graba TODOS LOS DATOS detalle de un Pedido Reposicion
  PROCEDURE INV_GRABA_TOTDET_PEDIDO_REP(codigocompania_in IN LGTD_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                                codigolocal_in         IN LGTD_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
        tipopedidoprod_in      IN LGTC_PEDIDO_PRODUCTO.TI_PEDIDO_PRODUCTO%TYPE,
        numeropedido_in     IN LGTD_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE,
        idcreapedido_in        IN LGTD_PEDIDO_PRODUCTO.ID_CREA_DET_PEDIDO_PRODUCTO%TYPE
        );
  -- Afecta una pagina dada
  FUNCTION INV_AFECTA_PAGINA_GUIA(codigocompania_in    IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                                codigolocal_in         IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
        numerorecepcion_in    IN LGTC_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE,
        numeropagina_in        IN LGTD_RECEPCION_PRODUCTO.NU_PAGINA_GUIA%TYPE,
        idmodrecepguia_in      IN LGTC_RECEPCION_PRODUCTO.ID_MOD_RECEPCION_PRODUCTO%TYPE,
        codigogrupomotivo_in   IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
        codigomotivo_in        IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE,
        tipodocumento_in       IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
        numerodocumento_in     IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
        inafectastock_in       IN LGTC_RECEPCION_PRODUCTO.IN_AFECTA_STOCK%TYPE
        ) RETURN CHAR;

  -- Afecta una guia de entrada
  PROCEDURE INV_AFECTA_TOTAL_GUIA(codigocompania_in    IN LGTD_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                                codigolocal_in         IN LGTD_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
        numerorecepcion_in    IN LGTD_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE,
        idmodrecepguia_in      IN LGTC_RECEPCION_PRODUCTO.ID_MOD_RECEPCION_PRODUCTO%TYPE,
        codigogrupomotivo_in   IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
        codigomotivo_in        IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE,
        tipodocumento_in       IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
        numerodocumento_in     IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
        inafectastock_in       IN LGTC_RECEPCION_PRODUCTO.IN_AFECTA_STOCK%TYPE
        );

  -- Devuelve los movimientos de un producto
  FUNCTION INV_DETALLE_KARDEX(codigocompania_in  IN LGTV_KARDEX.CO_COMPANIA%TYPE,
              codigolocal_in     IN LGTV_KARDEX.CO_LOCAL%TYPE,
         codigoproducto_in  IN LGTV_KARDEX.CO_PRODUCTO%TYPE,
         fechainicial_in    IN CHAR,
         fechafin_in        IN CHAR)
           RETURN EckerdCursor;

  -- Devuelve la unidad de fraccionamiento de un producto dado
  FUNCTION UNIDAD_FRAC_PRODUCTO(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
            codigolocal_in           IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
                         codigoproducto_in        IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
           RETURN LGTR_PRODUCTO_LOCAL.DE_UNIDAD_FRACCION%TYPE;

  -- Devuelve el indicador de producto fraccionado
  FUNCTION INDICADOR_PROD_FRACCIONADO(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
            codigolocal_in           IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
                         codigoproducto_in        IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
           RETURN LGTR_PRODUCTO_LOCAL.IN_PROD_FRACCIONADO%TYPE;

  -- Devuelve el valor de fraccionamiento de producto fraccionado
  FUNCTION VALOR_FRACCION(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
            codigolocal_in           IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
                         codigoproducto_in        IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
           RETURN LGTR_PRODUCTO_LOCAL.VA_FRACCION%TYPE;

  -- Devuelve indicador si afecta stock a guia seleccionada
  FUNCTION INDICADOR_AFECTA_STOCK_GUIA(codigocompania_in IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
            codigolocal_in           IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
                         numerorecepcion_in       IN LGTC_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE)
           RETURN LGTC_RECEPCION_PRODUCTO.IN_AFECTA_STOCK%TYPE;

  -- Graba ajuste de inventario
  FUNCTION INV_AJUSTE_INV(codigocompania_in   IN LGTV_KARDEX.CO_COMPANIA%TYPE,
       codigolocal_in        IN LGTV_KARDEX.CO_LOCAL%TYPE,
       codigoproducto_in        IN LGTV_KARDEX.CO_PRODUCTO%TYPE,
       cantunidadajuste_in      IN INTEGER,
       cantfraccionajuste_in    IN INTEGER,
       tipodocumento_in         IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
       numerodocumento_in       IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
       descglosa_in             IN LGTV_KARDEX.DE_GLOSA%TYPE,
       codigogrupomotivokardex_in IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
       codigomotivokardex_in  IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE,
       codigogrupomotivo_in       IN LGTV_KARDEX.CO_GRUPO_MOTIVO_AJUSTE%TYPE,
       codigomotivo_in          IN LGTV_KARDEX.CO_MOTIVO_AJUSTE%TYPE,
       idcreakardex_in          IN LGTV_KARDEX.ID_CREA_KARDEX%TYPE)
           RETURN LGTV_KARDEX.CA_MOVIMIENTO%TYPE;

  -- Graba registro en Kardex ESPECIAL
  PROCEDURE INV_GRABA_MOTIVO_KARDEX(codigocompania_in   IN LGTV_KARDEX.CO_COMPANIA%TYPE,
       codigolocal_in      IN LGTV_KARDEX.CO_LOCAL%TYPE,
       codigoproducto_in      IN LGTV_KARDEX.CO_PRODUCTO%TYPE,
       cantmovimiento_in      IN LGTV_KARDEX.CA_MOVIMIENTO%TYPE,
       tipodocumento_in       IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
       numerodocumento_in     IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
       descglosa_in           IN LGTV_KARDEX.DE_GLOSA%TYPE,
       codigogrupomotivokardex_in IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
       codigomotivokardex_in  IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE,
       codigogrupomotivo_in   IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
       codigomotivo_in        IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE,
       idcreakardex_in        IN LGTV_KARDEX.ID_CREA_KARDEX%TYPE
       );

  -- Graba registro en Kardex UNIVERSAL
  PROCEDURE INV_GRABA_KARDEX_STOCK_COM(codigocompania_in   IN LGTV_KARDEX.CO_COMPANIA%TYPE,
       codigolocal_in    IN LGTV_KARDEX.CO_LOCAL%TYPE,
       codigoproducto_in    IN LGTV_KARDEX.CO_PRODUCTO%TYPE,
       cantmovimiento_in    IN LGTV_KARDEX.CA_MOVIMIENTO%TYPE,
       tipodocumento_in     IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
       numerodocumento_in   IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
       descglosa_in         IN LGTV_KARDEX.DE_GLOSA%TYPE,
       codigogrupomotivo_in IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
       codigomotivo_in      IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE,
       idcreakardex_in      IN LGTV_KARDEX.ID_CREA_KARDEX%TYPE
       );
  /* ************************************************************************ */
  FUNCTION INV_GRABA_TRANSF_PROD(codigocompania_in  IN LGTM_TRANSF_PRODUCTO.CO_COMPANIA%TYPE,
                                codigolocal_in      IN LGTM_TRANSF_PRODUCTO.CO_LOCAL%TYPE,
        localdestino_in     IN LGTM_TRANSF_PRODUCTO.CO_LOCAL_DESTINO%TYPE,
        idcreatransf_in     IN LGTM_TRANSF_PRODUCTO.ID_CREA_TRANSF_PRODUCTO%TYPE,
        cantidaditems_in    IN LGTM_TRANSF_PRODUCTO.CA_PROD_TRANSFERIDO%TYPE,
        grupomotivo_in      IN LGTM_TRANSF_PRODUCTO.CO_GRUPO_MOTIVO%TYPE,
        codigomotivo_in     IN LGTM_TRANSF_PRODUCTO.CO_MOTIVO_TRANSFERENCIA%TYPE,
        empresadestino_in   IN LGTM_TRANSF_PRODUCTO.NO_EMPRESA_DESTINO%TYPE,
        direccionempresa_in IN LGTM_TRANSF_PRODUCTO.DE_DIRECCION_EMP_DESTINO%TYPE,
        nombretransportista_in IN LGTM_TRANSF_PRODUCTO.NO_TRANSPORTISTA%TYPE,
        directransportista_in IN LGTM_TRANSF_PRODUCTO.DE_DIRECCION_TRANS%TYPE,
        tipdoctransp_in IN LGTM_TRANSF_PRODUCTO.TI_DOC_IDENTIDAD_TRANS%TYPE,
        numdoctransp_in IN LGTM_TRANSF_PRODUCTO.NU_DOC_IDENTIDAD_TRANS%TYPE,
        numeroplaca_in  IN LGTM_TRANSF_PRODUCTO.NU_PLACA%TYPE,
        codigoproveedor_in IN LGTM_TRANSF_PRODUCTO.CO_PROVEEDOR_TRANSFERENCIA%TYPE DEFAULT NULL)
           RETURN LGTM_TRANSF_PRODUCTO.NU_SEC_TRANSFERENCIA%TYPE;

  /* ************************************************************************ */
  PROCEDURE INV_GRABA_DET_TRANSF_PROD(codigocompania_in IN LGTD_GUIA_TRANSFERENCIA.CO_COMPANIA%TYPE,
                                codigolocal_in          IN LGTD_GUIA_TRANSFERENCIA.CO_LOCAL%TYPE,
        numerotransferencia_in IN LGTD_GUIA_TRANSFERENCIA.NU_SEC_TRANSFERENCIA%TYPE,
        numeroitem_in           IN LGTD_GUIA_TRANSFERENCIA.NU_ITEM_TRANSFERENCIA%TYPE,
        codigoproducto_in       IN LGTD_GUIA_TRANSFERENCIA.CO_PRODUCTO%TYPE,
        unidadproducto_in       IN LGTD_GUIA_TRANSFERENCIA.DE_UNIDAD_PRODUCTO%TYPE,
        unidadfraccion_in       IN LGTD_GUIA_TRANSFERENCIA.DE_UNIDAD_FRACCION%TYPE,
        cantidad_in             IN LGTD_GUIA_TRANSFERENCIA.CA_TRANSFERIR_ENTERA%TYPE,
        cantidadfrac_in         IN LGTD_GUIA_TRANSFERENCIA.CA_TRANSFERIR_FRACCION%TYPE,
        fechavenc_in            IN CHAR,
        idcreatransf_in         IN LGTD_GUIA_TRANSFERENCIA.ID_CREA_DET_GUIA_TRANSFERENCIA%TYPE
        );

  /* ************************************************************************ */
  FUNCTION INV_LISTA_TRANSF_PROD(codigocompania_in IN LGTM_TRANSF_PRODUCTO.CO_COMPANIA%TYPE,
              codigolocal_in    IN LGTM_TRANSF_PRODUCTO.CO_LOCAL%TYPE,
         fechainicio_in    IN CHAR,
         fechafin_in    IN CHAR)
           RETURN EckerdCursor;

  /* ************************************************************************ */
  FUNCTION INV_GRABA_GUIA_TRANSF(codigocompania_in      IN LGTD_GUIA_TRANSFERENCIA.CO_COMPANIA%TYPE,
                                codigolocal_in          IN LGTD_GUIA_TRANSFERENCIA.CO_LOCAL%TYPE,
        numerotransferencia_in IN LGTD_GUIA_TRANSFERENCIA.NU_SEC_TRANSFERENCIA%TYPE,
        idcreatransf_in         IN LGTD_GUIA_TRANSFERENCIA.ID_CREA_DET_GUIA_TRANSFERENCIA%TYPE,
        cantidaditems_in        IN LGTM_TRANSF_PRODUCTO.CA_PROD_TRANSFERIDO%TYPE,
        tipodocumento_in        IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
        codigogrupomotivo_in    IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
        codigomotivo_in         IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE)
           RETURN LGTC_GUIA_TRANSFERENCIA.NU_GUIA_TRANSFERENCIA%TYPE;

  /* ************************************************************************ */
  FUNCTION INV_GRABA_CABECERA_GUIA_TRANSF(codigocompania_in  IN LGTC_GUIA_TRANSFERENCIA.CO_COMPANIA%TYPE,
                                codigolocal_in      IN LGTC_GUIA_TRANSFERENCIA.CO_LOCAL%TYPE,
        idcreatransf_in     IN LGTC_GUIA_TRANSFERENCIA.ID_CREA_GUIA_TRANSFERENCIA%TYPE,
           cantidaditems_in    IN LGTC_GUIA_TRANSFERENCIA.CA_PRODUCTO%TYPE,
           valorpreciocom_in   IN LGTC_GUIA_TRANSFERENCIA.VA_PRECIO_COMPRA%TYPE)
           RETURN LGTC_GUIA_TRANSFERENCIA.NU_GUIA_TRANSFERENCIA%TYPE;

  /* ************************************************************************ */
  FUNCTION INV_DETALLE_TRANSF_PRODUCTO(codigocompania_in IN LGTD_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
         codigolocal_in       IN LGTD_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
         numerorecepcion_in   IN LGTD_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE)
           RETURN EckerdCursor;

  /* ************************************************************************ */
  FUNCTION OBTIENE_PEDIDO_ADICIONAL(codigocompania_in IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
         codigolocal_in    IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
         tipopedido_in     IN LGTC_PEDIDO_PRODUCTO.TI_PEDIDO_PRODUCTO%TYPE,
         numeropedido_in   IN LGTC_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE)
           RETURN EckerdCursor;

  /* ************************************************************************ */
  PROCEDURE INV_CALCULA_MAX_MIN(codigocompania_in    IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                               codigolocal_in       IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE);

  /* ************************************************************************ */
  FUNCTION INV_OBTIENE_PROD_TRANSITO(codigocompania_in    IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
                               codigolocal_in             IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
          codigoproducto_in          IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
           RETURN LGTR_PRODUCTO_LOCAL.CA_STOCK_TRANSITO%TYPE;
    
   /* ************************************************************************ */
  FUNCTION INV_OBTIENE_PROD_COMPRA_PEND(codigocompania_in    IN LGTC_FARINV1.CO_COMPANIA%TYPE,                                                                        
                                                                        codigoproducto_in     IN LGTD_FARINV2.CO_PRODUCTO%TYPE)
           RETURN LGTD_FARINV2.VA_CANTIDAD%TYPE;           

  /* ************************************************************************ */
  PROCEDURE INV_ANULA_TRANSF_PRODUCTO(codigocompania_in  IN LGTD_GUIA_TRANSFERENCIA.CO_COMPANIA%TYPE,
                                    codigolocal_in       IN LGTD_GUIA_TRANSFERENCIA.CO_LOCAL%TYPE,
         numerotransferencia_in  IN LGTD_GUIA_TRANSFERENCIA.NU_SEC_TRANSFERENCIA%TYPE,
         idanulatransf_in        IN LGTM_TRANSF_PRODUCTO.ID_ANULA_TRANSFERENCIA%TYPE,
         tipodocumento_in        IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
         codigogrupomotivo_in    IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
         codigomotivo_in         IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE);

  /* ************************************************************************ */
  FUNCTION INV_REIMP_TRANSF_PRODUCTO(codigocompania_in      IN LGTM_TRANSF_PRODUCTO.CO_COMPANIA%TYPE,
              codigolocal_in         IN LGTM_TRANSF_PRODUCTO.CO_LOCAL%TYPE,
         numerotransferencia_in IN LGTM_TRANSF_PRODUCTO.NU_SEC_TRANSFERENCIA%TYPE,
         localdestino_in     IN LGTM_TRANSF_PRODUCTO.CO_LOCAL_DESTINO%TYPE,
         idcreatransf_in     IN LGTM_TRANSF_PRODUCTO.ID_CREA_TRANSF_PRODUCTO%TYPE,
         cantidaditems_in    IN LGTM_TRANSF_PRODUCTO.CA_PROD_TRANSFERIDO%TYPE,
         grupomotivo_in      IN LGTM_TRANSF_PRODUCTO.CO_GRUPO_MOTIVO%TYPE,
         codigomotivo_in     IN LGTM_TRANSF_PRODUCTO.CO_MOTIVO_TRANSFERENCIA%TYPE,
         empresadestino_in   IN LGTM_TRANSF_PRODUCTO.NO_EMPRESA_DESTINO%TYPE,
         direccionempresa_in IN LGTM_TRANSF_PRODUCTO.DE_DIRECCION_EMP_DESTINO%TYPE,
         nombretransportista_in IN LGTM_TRANSF_PRODUCTO.NO_TRANSPORTISTA%TYPE,
         directransportista_in IN LGTM_TRANSF_PRODUCTO.DE_DIRECCION_TRANS%TYPE,
         tipdoctransp_in IN LGTM_TRANSF_PRODUCTO.TI_DOC_IDENTIDAD_TRANS%TYPE,
         numdoctransp_in IN LGTM_TRANSF_PRODUCTO.NU_DOC_IDENTIDAD_TRANS%TYPE,
         numeroplaca_in  IN LGTM_TRANSF_PRODUCTO.NU_PLACA%TYPE)
            RETURN LGTM_TRANSF_PRODUCTO.NU_SEC_TRANSFERENCIA%TYPE;

  /* ************************************************************************ */
  FUNCTION OBTIENE_TRANSF_PRODUCTO(codigocompania_in  IN LGTM_TRANSF_PRODUCTO.CO_COMPANIA%TYPE,
        codigolocal_in     IN LGTM_TRANSF_PRODUCTO.CO_LOCAL%TYPE,
        numerotransferencia_in  IN LGTM_TRANSF_PRODUCTO.NU_SEC_TRANSFERENCIA%TYPE)
           RETURN EckerdCursor;

  /* ************************************************************************ */
  FUNCTION INV_OBTIENE_DATOS_REPOSICION(codigocompania_in    IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
                                    codigolocal_in       IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
          codigoproducto_in    IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
           RETURN EckerdCursor;

  /* ************************************************************************ */
  FUNCTION INV_OBTIENE_PROD_ROTACION(codigocompania_in    IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
                                    codigolocal_in       IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
               codigoproducto_in    IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE,
            semanasrotacion_in  IN NUMBER)
           RETURN NUMBER;

  /* ************************************************************************ */
  FUNCTION INV_OBTIENE_PROD_ROTACION_MES(codigocompania_in    IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
                               codigolocal_in             IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
          codigoproducto_in          IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE,
          numeromes_in               IN NUMBER)
           RETURN NUMBER;
  /* ************************************************************************ */
  FUNCTION INV_OBTIENE_DATOS_REPO_LOCAL(codigocompania_in    IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
                                    codigolocal_in       IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE)
           RETURN EckerdCursor;

  /* ************************************************************************ */
  FUNCTION INV_STOCK_PROD_UNIFRAC(codigocompania_in  IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
              codigolocal_in     IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
              codigoproducto_in  IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
           RETURN EckerdCursor;

   /* ************************************************************************ */
  FUNCTION INV_LISTA_AJUSTE_INV(codigocompania_in IN LGTV_KARDEX.CO_COMPANIA%TYPE,
         codigolocal_in   IN LGTV_KARDEX.CO_LOCAL%TYPE,
         grupomotivo_in   IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
         fechainicio_in   IN CHAR,
         fechafin_in      IN CHAR)
           RETURN EckerdCursor;
  /* ************************************************************************ */
  FUNCTION PRECIO_UNIT_PRODUCTO(codigocompania_in IN LGTM_PRODUCTO.CO_COMPANIA%TYPE,
                                codigoproducto_in  IN LGTM_PRODUCTO.CO_PRODUCTO%TYPE)
           RETURN LGTM_PRODUCTO.VA_PRECIO_COMPRA%TYPE;

  /* ************************************************************************ */
  FUNCTION CANTIDAD_MAXIMA_REPONER(codigocompania_in IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                                codigolocal_in       IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
        codigoproducto_in    IN LGTD_PEDIDO_PRODUCTO.CO_PRODUCTO%TYPE)
            RETURN LGTR_PRODUCTO_LOCAL.CA_STOCK_REPONER_CALCULA%TYPE;

  FUNCTION CANT_MAXIMA_MINIMA_REPONER(codigocompania_in IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                                      codigolocal_in    IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
                                      codigoproducto_in IN LGTD_PEDIDO_PRODUCTO.CO_PRODUCTO%TYPE)
     RETURN EckerdCursor;

  /* ************************************************************************ */
  FUNCTION OBTIENE_INFO_TRANSP_GUIA(codigocompania_in  IN LGTM_TRANSF_PRODUCTO.CO_COMPANIA%TYPE,
         codigolocaldest_in  IN LGTM_TRANSF_PRODUCTO.CO_LOCAL%TYPE)
           RETURN EckerdCursor;

  /* ************************************************************************ */
  FUNCTION VERIFICA_PEDIDO_REALIZADO(codigocompania_in   IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
       codigolocal_in    IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE)
            RETURN NUMBER;

  /* ************************************************************************ */
  FUNCTION OBTIENE_SUM_PED_REP_ANTERIOR(codigocompania_in   IN LGTD_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
               codigolocal_in     IN LGTD_PEDIDO_PRODUCTO.CO_LOCAL%TYPE)
          RETURN LGTD_PEDIDO_PRODUCTO.CA_PEDIDA%TYPE;

  /* ************************************************************************ */
  FUNCTION OBTIENE_CANT_PED_REP_ANTERIOR(codigocompania_in   IN LGTD_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
               codigolocal_in     IN LGTD_PEDIDO_PRODUCTO.CO_LOCAL%TYPE)
           RETURN LGTD_PEDIDO_PRODUCTO.CA_PEDIDA%TYPE;

  /* ************************************************************************ */
  FUNCTION OBTIENE_CANT_PED_REP_ANT_PROD(codigocompania_in   IN LGTD_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
               codigolocal_in     IN LGTD_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
          codigoproducto_in     IN LGTD_PEDIDO_PRODUCTO.CO_PRODUCTO%TYPE)
           RETURN LGTD_PEDIDO_PRODUCTO.CA_PEDIDA%TYPE;

  /* ************************************************************************ */
  FUNCTION OBTIENE_CANT_ITEM_PROD_ANT(codigocompania_in   IN LGTD_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
               codigolocal_in     IN LGTD_PEDIDO_PRODUCTO.CO_LOCAL%TYPE)
           RETURN EckerdCursor;

  /* ************************************************************************ */
  PROCEDURE ACTUALIZA_INDICADOR_PEDIDO(codigocompania_in   IN VTTM_LOCAL.CO_COMPANIA%TYPE,
       codigolocal_in    IN VTTM_LOCAL.CO_LOCAL%TYPE);

  /* ************************************************************************ */
  PROCEDURE ACTUALIZA_IND_PED_NO_COMMIT(codigocompania_in   IN VTTM_LOCAL.CO_COMPANIA%TYPE,
       codigolocal_in    IN VTTM_LOCAL.CO_LOCAL%TYPE);


  /* ************************************************************************ */
  FUNCTION DEVUELVE_INDICADOR_PED_LOCAL(codigocompania_in   IN VTTM_LOCAL.CO_COMPANIA%TYPE,
       codigolocal_in    IN VTTM_LOCAL.CO_LOCAL%TYPE)
            RETURN VTTM_LOCAL.IN_PED_REP_EN_PROC%TYPE;

  /* ************************************************************************ */
  FUNCTION RELACIONCLIENTES (codigocompania_in IN VTTC_PEDIDO_VENTA.CO_COMPANIA%TYPE,
                  codigolocal_in    IN VTTC_PEDIDO_VENTA.CO_LOCAL%TYPE,
            nombrecliente_in IN CHAR)
        RETURN EckerdCursor;

  /* ************************************************************************ */
  PROCEDURE UPDATE_NUMERACION_GUIA_SALIDA(codigocompania_in   IN CMTR_NUMERACION.CO_COMPANIA%TYPE,
       codigolocal_in    IN CMTR_NUMERACION.CO_LOCAL%TYPE,
       numerosecuencia_in    IN CHAR);

  /* ************************************************************************ */
  FUNCTION DEVUELVE_NUMERO_SEC_ANTERIOR(codigocompania_in   IN VTTM_LOCAL.CO_COMPANIA%TYPE,
       codigolocal_in    IN VTTM_LOCAL.CO_LOCAL%TYPE)
            RETURN CMTR_NUMERACION.DE_NUMERACION%TYPE;

  /* ***************************************************************************************************** */

  FUNCTION INV_GRABA_CABE_GUIA_INGRESO(codigocompania_in  IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                                     codigolocal_in      IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
             tipodocumento_in IN LGTC_RECEPCION_PRODUCTO.TI_DOCUMENTO_RECEPCION%TYPE,
             numerodocumento_in IN LGTC_RECEPCION_PRODUCTO.TI_DOCUMENTO_RECEPCION%TYPE,
             nombreproveedor_in IN LGTC_RECEPCION_PRODUCTO.NO_PROVEEDOR%TYPE,
             idrecepcionproducto_in IN LGTC_RECEPCION_PRODUCTO.ID_RECEPCION_PRODUCTO%TYPE,
             fecharecepcionproducto_in IN CHAR,
             cantidaditemrecepcion_in IN LGTC_RECEPCION_PRODUCTO.CA_ITEM_RECEPCION%TYPE,
             codigolocalorigen_in IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL_ORIGEN%TYPE,
             valortotalcompra_in IN LGTC_RECEPCION_PRODUCTO.VA_TOTAL_COMPRA%TYPE,
             indicadororigen_in IN LGTC_RECEPCION_PRODUCTO.IN_ORIGEN_GUIA_INGRESO%TYPE,
             nuSolictudPedido_in       IN LGTC_RECEPCION_PRODUCTO.NU_SOLICITUD_PEDIDO%TYPE DEFAULT NULL,
             cantRecepcion_in       IN  LGTC_RECEPCION_PRODUCTO.CA_GUIA_RECEPCION%TYPE DEFAULT NULL,
             coproveedor_in            IN LGTC_RECEPCION_PRODUCTO.CO_PROVEEDOR%TYPE DEFAULT NULL,
             intipocontrol_in          IN LGTC_RECEPCION_PRODUCTO.IN_TIPO_CONTROL%TYPE DEFAULT NULL)
  RETURN LGTC_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE;

  /* ***************************************************************************************************** */
    PROCEDURE INV_GRABA_DET_GUIA_INGRESO(codigocompania_in IN LGTD_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                                  codigolocal_in          IN LGTD_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
          numerorecepcion      IN LGTD_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE,
          numeroitem_in           IN LGTD_RECEPCION_PRODUCTO.NU_ITEM_RECEPCION_GUIA%TYPE,
          codigoproducto_in       IN LGTD_RECEPCION_PRODUCTO.CO_PRODUCTO%TYPE,
          cantidadentera_in       IN LGTD_RECEPCION_PRODUCTO.CA_ENVIADA_ENTERA%TYPE,
          cantidadfraccion_in     IN LGTD_RECEPCION_PRODUCTO.CA_ENVIADA_FRACCION%TYPE,
          fechavenc_in            IN CHAR,
          lote_in     IN LGTD_RECEPCION_PRODUCTO.NU_LOTE%TYPE,
          idcrearecepcion_in      IN LGTD_RECEPCION_PRODUCTO.ID_CREA_DET_RECEPCION_PRODUCTO%TYPE,
          preciocompra_in         IN LGTD_RECEPCION_PRODUCTO.VA_PRECIO_COMPRA%TYPE,
          totalitem_in   IN LGTD_RECEPCION_PRODUCTO.VA_TOTAL_ITEM%TYPE);

  /* ***************************************************************************************************** */
  PROCEDURE INV_GRABA_KARDEX_GUIA_IN(codigocompania_in   IN LGTV_KARDEX.CO_COMPANIA%TYPE,
       codigolocal_in    IN LGTV_KARDEX.CO_LOCAL%TYPE,
       codigoproducto_in    IN LGTV_KARDEX.CO_PRODUCTO%TYPE,
       cantmovimiento_in    IN LGTV_KARDEX.CA_MOVIMIENTO%TYPE,
       tipodocumento_in     IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
       numerodocumento_in   IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
       idcreakardex_in      IN LGTV_KARDEX.ID_CREA_KARDEX%TYPE,
       motivokardex_in   IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE);

  /* ***************************************************************************************************** */
  FUNCTION GET_PRECIO_COMPRA_PROD(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
              codigolocal_in    IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
                                  codigoproducto_in IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE,
          tipounidad_in     IN CHAR)
           RETURN LGTM_PRODUCTO.VA_PRECIO_COMPRA%TYPE;

  /* ***************************************************************************************************** */

 FUNCTION GET_STOCK_PRODUCTO(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
           codigolocal_in    IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
                             codigoproducto_in  IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
           RETURN LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;

  /* ***************************************************************************************************** */
  FUNCTION GET_STOCK_FRACC_PRODUCTO(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
             codigolocal_in    IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
                               codigoproducto_in  IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
           RETURN LGTR_PRODUCTO_LOCAL .CA_STOCK_DISPONIBLE%TYPE;

  /* ***************************************************************************************************** */
  FUNCTION GET_NAME_COMPANIA(codigocompania_in IN CMTM_COMPANIA.CO_COMPANIA%TYPE)
           RETURN CMTM_COMPANIA.DE_COMPANIA%TYPE;

  /* ***************************************************************************************************** */
  FUNCTION GET_GUIAS_MANUALES(codigocompania_in IN LGTD_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
        codigolocal_in    IN LGTD_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
        fechainicio_in    IN CHAR,
        fechafin_in    IN CHAR)
           RETURN EckerdCursor;

  /* ***************************************************************************************************** */
  FUNCTION GET_LOCAL_ORIGEN(codigocompania_in IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
       codlocal_in    IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
       num_recepcion_prod_in IN LGTC_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE)
           RETURN LGTC_RECEPCION_PRODUCTO.CO_LOCAL_ORIGEN%TYPE;

  /* ***************************************************************************************************** */
  FUNCTION GET_TIPO_INGRESO_MANUAL(codigocompania_in     IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
               codlocal_in        IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
               num_recepcion_prod_in IN LGTC_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE)
           RETURN LGTC_RECEPCION_PRODUCTO.IN_ORIGEN_GUIA_INGRESO%TYPE;

  /* ***************************************************************************************************** */
  FUNCTION GET_DETALLE_INGRESO_MANUAL(codigocompania_in IN LGTD_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                codigolocal_in     IN LGTD_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
          num_recepcion_prod_in IN LGTD_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE,
          tipodatos_in IN CHAR)
           RETURN EckerdCursor;

  /* ***************************************************************************************************** */
  FUNCTION GET_TIPO_DOC_INGRESO_MANUAL(codigocompania_in IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                codigolocal_in     IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
          num_recepcion_prod_in IN LGTC_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE)
           RETURN LGTC_RECEPCION_PRODUCTO.TI_DOCUMENTO_RECEPCION%TYPE;

  /* ***************************************************************************************************** */
  FUNCTION GET_PROVEEDOR_INGRESO_MANUAL(codigocompania_in IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                codigolocal_in     IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
          num_recepcion_prod_in IN LGTC_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE)
           RETURN LGTC_RECEPCION_PRODUCTO.CO_LOCAL_ORIGEN%TYPE;

/************************************/

  FUNCTION LISTA_DATA_TABLAS(codigocompania_in IN VTTM_LOCAL.CO_COMPANIA%TYPE,
             nombretabla_in IN VARCHAR2)
           RETURN EckerdCursor;

  FUNCTION OBTIENE_PROD_ROTACION_4_MESES(codigocompania_in    IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
                                   codigolocal_in             IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
              codigoproducto_in          IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
           RETURN EckerdCursor;

  FUNCTION INV_PEDIDO_REPOSICION(codigocompania_in  IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
             codigolocal_in IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
                                codigoLaboratorio_in IN LGTM_PRODUCTO.CO_LABORATORIO%TYPE,
        verTodosProductos IN CHAR)
           RETURN EckerdCursor;

  FUNCTION GET_STOCK_PRODUCTO_LOCAL(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
          codigolocal_in    IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
          producto_in   IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
        RETURN EckerdCursor;

  FUNCTION GET_VA_FRACCION_PRODUCTO_LOCAL(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
          codigolocal_in    IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
          producto_in   IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
        RETURN NUMBER;

  PROCEDURE INV_GRABA_KARDEX(codigocompania_in   IN LGTV_KARDEX.CO_COMPANIA%TYPE,
       codigolocal_in    IN LGTV_KARDEX.CO_LOCAL%TYPE,
       codigoproducto_in    IN LGTV_KARDEX.CO_PRODUCTO%TYPE,
       cantmovimiento_in    IN LGTV_KARDEX.CA_MOVIMIENTO%TYPE,
       tipodocumento_in     IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
       numerodocumento_in   IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
       descglosa_in         IN LGTV_KARDEX.DE_GLOSA%TYPE,
       codigogrupomotivo_in IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
       codigomotivo_in      IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE,
       idcreakardex_in      IN LGTV_KARDEX.ID_CREA_KARDEX%TYPE
       );

  FUNCTION RELACION_LOCALES(codigocompania_in IN VTTM_LOCAL.CO_COMPANIA%TYPE,
            codigolocal_in IN VTTM_LOCAL.CO_LOCAL%TYPE,
            tipolocal_in IN CHAR)
        RETURN EckerdCursor;

  FUNCTION INV_AFECTA_POR_PRODUCTO(codigocompania_in    IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                                   codigolocal_in       IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
           numerorecepcion_in IN LGTC_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE,
           numeropagina_in      IN LGTD_RECEPCION_PRODUCTO.NU_PAGINA_GUIA%TYPE,
           codigoproducto_in IN LGTD_RECEPCION_PRODUCTO.CO_PRODUCTO%TYPE,
           idmodrecepguia_in    IN LGTC_RECEPCION_PRODUCTO.ID_MOD_RECEPCION_PRODUCTO%TYPE,
           codigogrupomotivo_in IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
           codigomotivo_in      IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE,
           tipodocumento_in     IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
           numerodocumento_in   IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
           inafectastock_in     IN LGTC_RECEPCION_PRODUCTO.IN_AFECTA_STOCK%TYPE
        ) RETURN CHAR;

  /*************/

  FUNCTION LISTADATATABLAS(codigocompania_in IN CMTR_LABORATORIO.CO_COMPANIA%TYPE,
                           nombretabla_in    IN VARCHAR2)
           RETURN EckerdCursor;

  FUNCTION RELACIONPRECIOPRODUCTOSFILTRO(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
                                         codigolocal_in    IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
           tablafiltro_in    VARCHAR2,
           codigofiltro_in   CHAR)
           RETURN EckerdCursor;

  FUNCTION VALIDA_NUMERO_RECEP_PROD(codigocompania_in  IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
           codigolocal_in     IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
           numerorecepcion_in IN LGTC_RECEPCION_PRODUCTO.NU_DOCUMENTO_RECEPCION%TYPE,
         codigolocalorigen_in     IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL_origen%TYPE,
         tipodocumento_in IN LGTC_RECEPCION_PRODUCTO.ti_documento_recepcion%TYPE)
        RETURN NUMBER;

  FUNCTION STOCK_DISPONIBLE_PRODUCTO(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
             codigolocal_in  IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
           codigoproducto_in IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
 RETURN CHAR;

-- creado ryupanqui
-- fecha 20040627
  FUNCTION VERIFICACION_ANULACION_GUIA(codigocompania_in IN LGTD_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                 codigolocal_in    IN LGTD_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
            nurecepprod_in   IN LGTD_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE)
   RETURN CHAR;

  PROCEDURE ANULA_GUIA_INGRESO(codigocompania_in   IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
               codigolocal_in      IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
          nurecepprod_in     IN LGTC_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE,
          idanulaguia_in    IN LGTV_KARDEX.ID_CREA_KARDEX%TYPE,
          tipodocumento_in    IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
          codigomotivo_in    IN LGTV_KARDEX .CO_MOTIVO_KARDEX%TYPE,
          codigogrupomotivo_in  IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE);

  FUNCTION VERIFICACION_NUMERACION_GUIA (codigocompania_in IN CMTR_NUMERACION.CO_COMPANIA%TYPE,
                 codigolocal_in    IN CMTR_NUMERACION.CO_LOCAL%TYPE)
  RETURN CHAR;

  FUNCTION OBTIENE_DATOS_PEDIDOS_REALIZ (codigocompania_in IN LGTD_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
               codigolocal_in    IN LGTD_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
              numpedido_in    IN LGTD_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE)
  RETURN EckerdCursor;

  FUNCTION VERIFICA_ANUL_TRANSF_TOMAINV(codigocompania_in IN LGTD_GUIA_TRANSFERENCIA.CO_COMPANIA%TYPE,
              codigolocal_in    IN LGTD_GUIA_TRANSFERENCIA.CO_LOCAL%TYPE,
             numsectrans_in    IN LGTD_GUIA_TRANSFERENCIA.NU_SEC_TRANSFERENCIA%TYPE)
  RETURN CHAR;

  FUNCTION VERIFICA_ANUL_GUIAING_TOMAINV(codigocompania_in IN LGTD_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
               codigolocal_in    IN LGTD_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
              numrecepprod_in   IN LGTD_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE)
  RETURN CHAR;

/*------------------------------LUIS MESIA----------------------------------------------------------*/

 FUNCTION GET_MOV_GUIA_X_PRODUCTO (codigocompania_in  IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
             codigolocal_in     IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
                fechainicio_in     IN CHAR,
                       fechafin_in        IN CHAR)
       RETURN EckerdCursor;

  FUNCTION GET_MOV_GUIA_X_PRODUCTO_DET (codigocompania_in  IN VTTM_LOCAL.CO_COMPANIA%TYPE,
                  codigolocal_in     IN VTTM_LOCAL.CO_LOCAL%TYPE,
             codigoproducto_in  IN LGTM_PRODUCTO.CO_PRODUCTO%TYPE,
                     fechainicio_in     IN CHAR,
                            fechafin_in        IN CHAR)
       RETURN EckerdCursor;

  FUNCTION FILTRO_TRANSFERENCIA_PRODUCTOS(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
            tablafiltro_in    VARCHAR2)
           RETURN EckerdCursor;

  FUNCTION INV_LISTA_TRANSF_PROD_FILTRO(codigocompania_in IN LGTM_TRANSF_PRODUCTO.CO_COMPANIA%TYPE,
                codigolocal_in    IN LGTM_TRANSF_PRODUCTO.CO_LOCAL%TYPE,
           fechainicio_in    IN CHAR,
           fechafin_in    IN CHAR,
          codigofiltro_in   CHAR,
          tablafiltro_in    VARCHAR2)
           RETURN EckerdCursor;

  PROCEDURE ANULA_PEDIDO_REPOSICION(codigocompania_in  IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
               codigolocal_in     IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
               numeropedido_in    IN LGTC_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE,
         idmodpedido_in    IN LGTC_PEDIDO_PRODUCTO.ID_MOD_PEDIDO_PRODUCTO%TYPE);

  FUNCTION INV_CALCULA_ROTACION_PROD (codigocompania_in  IN VTTR_VENTAS_PRODUCTO_DIA.CO_COMPANIA%TYPE,
                  codigolocal_in     IN VTTR_VENTAS_PRODUCTO_DIA.CO_LOCAL%TYPE,
             codigoproducto_in  IN VTTR_VENTAS_PRODUCTO_DIA.CO_PRODUCTO%TYPE,
          semanasrotacion_in IN NUMBER,
          maximodias_in    IN NUMBER)
           RETURN NUMBER;


  PROCEDURE INV_GRABA_ERROR_CANT_PEDIDA(codigocompania_in IN LGTD_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                                        codigolocal_in    IN LGTD_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
                tipopedidoprod_in IN LGTC_PEDIDO_PRODUCTO.TI_PEDIDO_PRODUCTO%TYPE,
                numeropedido_in   IN LGTD_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE,
          numeroitem_in   IN LGTD_PEDIDO_PRODUCTO.NU_ITEM%TYPE,
          codigoproducto_in IN LGTD_PEDIDO_PRODUCTO.CO_PRODUCTO%TYPE,
          cantidadpedida_in IN LGTD_PEDIDO_PRODUCTO.CA_PEDIDA%TYPE,
          idcreapedido_in   IN LGTD_PEDIDO_PRODUCTO.ID_CREA_DET_PEDIDO_PRODUCTO%TYPE
          );

  FUNCTION INV_CALCULA_STOCK_REQUERIDO (stockmaximo_in     IN LGTR_PRODUCTO_LOCAL.CA_STOCK_MAXIMO%TYPE,
          stockrequerido_in  IN LGTR_PRODUCTO_LOCAL.CA_STOCK_REPONER_CALCULA%TYPE,
          stockdisponible_in IN NUMBER)
           RETURN NUMBER;

  FUNCTION RELACION_PROVEEDORES(codigocompania_in IN VTTM_PROVEEDOR.CO_COMPANIA%TYPE)
        RETURN EckerdCursor;
/* INICIO ID : 001 */

  PROCEDURE INV_GRABA_KARDEX_COSTEO(codigocompania_in   IN LGTV_KARDEX.CO_COMPANIA%TYPE,
       codigolocal_in    IN LGTV_KARDEX.CO_LOCAL%TYPE,
       codigoproducto_in    IN LGTV_KARDEX.CO_PRODUCTO%TYPE,
       cantmovimiento_in    IN LGTV_KARDEX.CA_MOVIMIENTO%TYPE,
       vacostoproducto_in    IN LGTV_KARDEX.VA_COSTO_PRODUCTO%TYPE,
       tipodocumento_in     IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
       numerodocumento_in   IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
       descglosa_in         IN LGTV_KARDEX.DE_GLOSA%TYPE,
       codigogrupomotivo_in IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
       codigomotivo_in      IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE,
       idcreakardex_in      IN LGTV_KARDEX.ID_CREA_KARDEX%TYPE,
       nu_documento_ref_in  IN LGTV_KARDEX.NU_DOCUMENTO_REF%TYPE DEFAULT NULL
       );
PROCEDURE INV_GRABA_KARDEX_STK_COSTEO(codigocompania_in   IN LGTV_KARDEX.CO_COMPANIA%TYPE,
       codigolocal_in    IN LGTV_KARDEX.CO_LOCAL%TYPE,
       codigoproducto_in    IN LGTV_KARDEX.CO_PRODUCTO%TYPE,
       cantmovimiento_in    IN LGTV_KARDEX.CA_MOVIMIENTO%TYPE,
       tipodocumento_in     IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
       numerodocumento_in   IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
       descglosa_in         IN LGTV_KARDEX.DE_GLOSA%TYPE,
       codigogrupomotivo_in IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
       codigomotivo_in      IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE,
       idcreakardex_in      IN LGTV_KARDEX.ID_CREA_KARDEX%TYPE
       ) ;

  PROCEDURE INV_GRABA_KARDEX_GUIA_IN_COST(codigocompania_in   IN LGTV_KARDEX.CO_COMPANIA%TYPE,
       codigolocal_in    IN LGTV_KARDEX.CO_LOCAL%TYPE,
       codigoproducto_in    IN LGTV_KARDEX.CO_PRODUCTO%TYPE,
       precio_promedio_in    IN LGTV_KARDEX.VA_COSTO_PRODUCTO%TYPE,
       cantmovimiento_in    IN LGTV_KARDEX.CA_MOVIMIENTO%TYPE,
       tipodocumento_in     IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
       numerodocumento_in   IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
       idcreakardex_in      IN LGTV_KARDEX.ID_CREA_KARDEX%TYPE,
       motivokardex_in   IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE);

/* FIN ID : 001 */

/* INICIO ID : 001 */

 FUNCTION OBTENER_MONTO_USADO_COTIZACION (codigocompania_in   IN LGTV_KARDEX.CO_COMPANIA%TYPE,
       codigolocal_in    IN LGTV_KARDEX.CO_LOCAL%TYPE)
       RETURN Number;
/* FIN ID : 001 */

  PROCEDURE INV_GRABA_DET_PSICOTROPICO(codigocompania_in         IN LGTD_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                                       codigolocal_in            IN LGTD_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
                                       numerorecepcion           IN LGTD_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE,
                                       numeroitem_in             IN LGTD_RECEPCION_PRODUCTO.NU_ITEM_RECEPCION_GUIA%TYPE,
                                       codigoproducto_in         IN LGTD_RECEPCION_PRODUCTO.CO_PRODUCTO%TYPE,
                                       cantenviadaentera_in      IN LGTD_RECEPCION_PRODUCTO.CA_ENVIADA_ENTERA%TYPE,
                                       cantconfirmadaentera_in   IN LGTD_RECEPCION_PRODUCTO.CA_CONFIRMADA_ENTERA%TYPE,
                                       cantdiferenciaentera_in   IN LGTD_RECEPCION_PRODUCTO.CA_DIFERENCIA_ENTERA%TYPE,
                                       fechavenc_in              IN CHAR,
                                       nulote_in                 IN LGTD_RECEPCION_PRODUCTO.NU_LOTE%TYPE,
                                       idcrearecepcion_in        IN LGTD_RECEPCION_PRODUCTO.ID_CREA_DET_RECEPCION_PRODUCTO%TYPE,
                                       preciocompra_in           IN LGTD_RECEPCION_PRODUCTO.VA_PRECIO_COMPRA%TYPE,
                                       totalitem_in              IN LGTD_RECEPCION_PRODUCTO.VA_TOTAL_ITEM%TYPE,
                                       nusolicitud_in            IN LGTC_RECEPCION_PRODUCTO.NU_SOLICITUD_PEDIDO%TYPE
                                       );

   PROCEDURE INV_ELIMINA_PEDIDO_REP(codigocompania_in   IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                                codigolocal_in      IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
                                tipopedidoprod_in   IN LGTC_PEDIDO_PRODUCTO.TI_PEDIDO_PRODUCTO%TYPE,
                                numpeiddoprod_in    IN LGTC_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE);

   FUNCTION GET_PRODUCTOSPSICOTROPICOS(codigocompania_in  IN LGTM_PRODUCTO.CO_COMPANIA%TYPE)
    RETURN EckerdCursor;

   FUNCTION OBTENER_NOMBRE_PRODUCTO(codigocompania_in IN LGTM_PRODUCTO.CO_COMPANIA%TYPE,
                                   codigoproducto_in IN LGTM_PRODUCTO.CO_PRODUCTO%TYPE)
           RETURN LGTM_PRODUCTO.DE_PRODUCTO%TYPE;

   FUNCTION GET_KARDEXPSICOTROPICOS(codigocompania_in IN LGTV_KARDEX.CO_COMPANIA%TYPE,
                                   codigolocal_in     IN LGTV_KARDEX.CO_LOCAL%TYPE,
                                   codigoproducto_in  IN LGTV_KARDEX.CO_PRODUCTO%TYPE,
                                   fechainicial_in    IN CHAR,
                                   fechafin_in        IN CHAR)
  RETURN EckerdCursor;

END INKVENTA_INVENTARIO;
/
CREATE OR REPLACE PACKAGE BODY ecventa.INKVENTA_INVENTARIO IS
/* version 2.0  GAZABACHE  02.02.2007 Modificado */
/* PP 10-06-2009 */

  FUNCTION INV_RELACIONPRODUCTOS(codigocompania_in  IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
             codigolocal_in      IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE)
       RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
 OPEN eckcur FOR SELECT NVL(PRODUCTO_LOCAL.CO_PRODUCTO,' ') || 'Ã' ||
                        NVL(RTRIM(PRODUCTO.DE_PRODUCTO) ,' ') || 'Ã' ||
                           NVL(RTRIM(PRODUCTO.DE_UNIDAD_PRODUCTO),' ') || 'Ã' ||
                           NVL(RTRIM(PRODUCTO_LOCAL.DE_UNIDAD_FRACCION),' ') || 'Ã' ||
         TO_CHAR(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
           'S',
                             NVL(TRUNC((PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE - NVL(PRODUCTO_LOCAL.CA_STOCK_COMPROMETIDO,0)) / DECODE(NVL(PRODUCTO_LOCAL.VA_FRACCION, 0), 0, 1,PRODUCTO_LOCAL.VA_FRACCION)),0),
           NVL(PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE - NVL(PRODUCTO_LOCAL.CA_STOCK_COMPROMETIDO,0),0)
           ),'999,999,999'
       ) || 'Ã' ||
         DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
             'S',
                                  NVL(MOD(PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE - NVL(PRODUCTO_LOCAL.CA_STOCK_COMPROMETIDO,0), DECODE(NVL(PRODUCTO_LOCAL.VA_FRACCION, 0), 0, 1,PRODUCTO_LOCAL.VA_FRACCION)),0),
                0) || 'Ã' ||
         NVL(PRODUCTO_LOCAL.IN_PROD_INVENTARIO, ' ')
       FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL,
            LGTM_PRODUCTO PRODUCTO,
            CMTR_LABORATORIO LABORATORIO
      WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
        AND PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
--        AND PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A'
        AND (PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A' OR PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE > 0)
        AND PRODUCTO.ES_PRODUCTO = 'A'
        AND (PRODUCTO_LOCAL.CO_COMPANIA = PRODUCTO.CO_COMPANIA
        AND PRODUCTO_LOCAL.CO_PRODUCTO = PRODUCTO.CO_PRODUCTO)
        AND PRODUCTO.CO_COMPANIA = LABORATORIO.CO_COMPANIA(+)
        AND PRODUCTO.CO_LABORATORIO = LABORATORIO.CO_LABORATORIO(+);
 --     ORDER BY PRODUCTO.DE_PRODUCTO;
    RETURN eckcur;
  END;

  /* ************************************************************************ */
  FUNCTION OBTIENE_LISTA_PED_REP_REAL(codigocompania_in  IN LGTD_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
              codigolocal_in IN LGTD_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
                                numeroproducto_in IN LGTD_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
   OPEN eckcur FOR SELECT NVL(PEDPRO.CO_PRODUCTO,' ') || 'Ã' ||
           NVL(PRODUCTO.DE_PRODUCTO,' ') || 'Ã' ||
           NVL(PRODUCTO.DE_UNIDAD_PRODUCTO,' ') || 'Ã' ||
           NVL(PEDPRO.CA_STOCK_MINIMO_PEDIDO,0) || 'Ã' ||
           NVL(PEDPRO.CA_STOCK_MAXIMO_PEDIDO,0) || 'Ã' ||
           NVL(PEDPRO.CA_PEDIDA,0)
       FROM   LGTD_PEDIDO_PRODUCTO PEDPRO,
           LGTM_PRODUCTO PRODUCTO
       WHERE  PEDPRO.CO_COMPANIA = codigocompania_in
       AND    PEDPRO.CO_LOCAL = codigolocal_in
       AND    PEDPRO.NU_PEDIDO_PRODUCTO = numeroproducto_in
       AND    PRODUCTO.CO_COMPANIA = PEDPRO.CO_COMPANIA
       AND    PRODUCTO.CO_PRODUCTO = PEDPRO.CO_PRODUCTO;
    RETURN eckcur;
  END;

  /* ************************************************************************ */
  FUNCTION INV_GRABA_PEDIDO_ADIC(codigocompania_in  IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                                codigolocal_in      IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
        tipopedidoprod_in   IN LGTC_PEDIDO_PRODUCTO.TI_PEDIDO_PRODUCTO%TYPE,
        grupomotivo_in  IN LGTC_PEDIDO_PRODUCTO.CO_GRUPO_MOTIVO%TYPE,
        motivopedido_in  IN LGTC_PEDIDO_PRODUCTO.CO_MOTIVO_PEDIDO%TYPE,
        localdestino_in     IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL_DESPACHO%TYPE,
        codigocliente_in    IN LGTC_PEDIDO_PRODUCTO.CO_CLIENTE_LOCAL%TYPE,
        fecharequerida_in   IN CHAR,
        idcreapedido_in     IN LGTC_PEDIDO_PRODUCTO.ID_CREA_PEDIDO_PRODUCTO%TYPE,
        atencioncliente_in  IN LGTC_PEDIDO_PRODUCTO.IN_ATENCION_CLIENTE%TYPE,
        despachopedido_in   IN LGTC_PEDIDO_PRODUCTO.IN_DESPACHO_PEDIDO%TYPE,
        nombrerecoje_in     IN LGTC_PEDIDO_PRODUCTO.NO_RECOJE_PEDIDO%TYPE,
        cantidaditems_in    IN LGTC_PEDIDO_PRODUCTO.CA_TOTAL_PRODUCTO%TYPE)
           RETURN LGTC_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE IS
    l_numeracion CMTR_NUMERACION.NU_SEC_NUMERACION%TYPE;
 l_numero_pedido LGTC_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE;
  BEGIN
    l_numeracion := Ptovta_Utility.OBTENER_NUMERACION(codigocompania_in,codigolocal_in,'004');
 l_numero_pedido := Eckerd_Utility.COMPLETAR_CON_SIMBOLO(l_numeracion, 8, '0', 'I');
 IF (l_numeracion > 0) THEN
      INSERT INTO LGTC_PEDIDO_PRODUCTO
      (CO_COMPANIA, CO_LOCAL, TI_PEDIDO_PRODUCTO, NU_PEDIDO_PRODUCTO,
              CO_LOCAL_DESPACHO, CO_GRUPO_MOTIVO, CO_MOTIVO_PEDIDO, CO_CLIENTE_LOCAL,
     FE_REQUERIDA_PRODUCTO, IN_ATENCION_CLIENTE, IN_DESPACHO_PEDIDO, NO_RECOJE_PEDIDO,
     CA_TOTAL_PRODUCTO, ID_SOLICITA_PEDIDO, FE_SOLICITA_PEDIDO, ES_PEDIDO_PRODUCTO,
              ID_CREA_PEDIDO_PRODUCTO, FE_CREA_PEDIDO_PRODUCTO)
      VALUES (codigocompania_in, codigolocal_in, tipopedidoprod_in, l_numero_pedido,
       localdestino_in, grupomotivo_in, motivopedido_in, codigocliente_in,
    TO_DATE(fecharequerida_in,'dd/MM/yyyy'), atencioncliente_in, despachopedido_in, nombrerecoje_in,
    cantidaditems_in, idcreapedido_in, SYSDATE, 'P',
             idcreapedido_in, SYSDATE);
      Ptovta_Utility.ACTUALIZAR_NUMERACION(codigocompania_in,codigolocal_in,'004',idcreapedido_in);
    END IF;
    RETURN l_numero_pedido;
  END;

  /* ************************************************************************ */
  PROCEDURE INV_GRABA_DET_PEDIDO_ADIC(codigocompania_in   IN LGTD_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                                codigolocal_in      IN LGTD_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
        tipopedidoprod_in   IN LGTC_PEDIDO_PRODUCTO.TI_PEDIDO_PRODUCTO%TYPE,
        numeropedido_in  IN LGTD_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE,
        codigoproducto_in   IN LGTD_PEDIDO_PRODUCTO.CO_PRODUCTO%TYPE,
        cantidad_in         IN LGTD_PEDIDO_PRODUCTO.CA_PEDIDA%TYPE,
        numeroitem_in       IN LGTD_PEDIDO_PRODUCTO.NU_ITEM%TYPE,
        idcreapedido_in     IN LGTD_PEDIDO_PRODUCTO.ID_CREA_DET_PEDIDO_PRODUCTO%TYPE
        ) IS
  BEGIN
    INSERT INTO LGTD_PEDIDO_PRODUCTO (CO_COMPANIA, CO_LOCAL, TI_PEDIDO_PRODUCTO, NU_PEDIDO_PRODUCTO,
           NU_ITEM, CO_PRODUCTO, NU_REVISION_PRODUCTO, CA_PEDIDA, ES_DET_PEDIDO_PRODUCTO,
                                   ID_CREA_DET_PEDIDO_PRODUCTO,
                                   FE_CREA_DET_PEDIDO_PRODUCTO
        )
                           VALUES (codigocompania_in, codigolocal_in, tipopedidoprod_in, numeropedido_in,
           numeroitem_in, codigoproducto_in, '0', cantidad_in,'A',
              idcreapedido_in, SYSDATE);
    COMMIT;
  END;

  /* ************************************************************************ */
  FUNCTION INV_KARDEX_LISTA_PROD(codigocompania_in  IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
              codigolocal_in IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
      OPEN eckcur FOR SELECT NVL(PRODUCTO_LOCAL.CO_PRODUCTO,' ') || 'Ã' ||
                  NVL(RTRIM(PRODUCTO.DE_PRODUCTO) ,' ') || 'Ã' ||
         NVL(RTRIM(PRODUCTO.DE_UNIDAD_PRODUCTO),' ') || 'Ã' ||
         NVL(RTRIM(PRODUCTO_LOCAL.DE_UNIDAD_FRACCION),' ') || 'Ã' ||
         TO_CHAR(NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
          'S',
        TRUNC(CA_STOCK_DISPONIBLE/VA_FRACCION),
        PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE),0), '999,999,990')  || 'Ã' ||
         TO_CHAR(NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
             'S',
        MOD(CA_STOCK_DISPONIBLE, VA_FRACCION),
        0), 0), '999,990')  || 'Ã' ||
            NVL(PRODUCTO_LOCAL.IN_PROD_INVENTARIO, ' ')  || 'Ã' ||
            NVL(PRODUCTO_LOCAL.ES_PROD_LOCAL, 'I')
    FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL,
         LGTM_PRODUCTO PRODUCTO
   WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
     AND PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
     AND PRODUCTO.CO_COMPANIA = PRODUCTO_LOCAL.CO_COMPANIA
     AND PRODUCTO.CO_PRODUCTO = PRODUCTO_LOCAL.CO_PRODUCTO;
   --ORDER BY TRIM(PRODUCTO.DE_PRODUCTO);
    RETURN eckcur;
  END;

  /* ************************************************************************ */
    FUNCTION DESCRIP_LABORATORIO(codigocompania_in IN LGTM_PRODUCTO.CO_COMPANIA%TYPE,
                           codigoproducto_in  IN LGTM_PRODUCTO.CO_PRODUCTO%TYPE)
           RETURN CMTR_LABORATORIO.DE_LABORATORIO%TYPE IS
    l_laboratorio CMTR_LABORATORIO.DE_LABORATORIO%TYPE;
  BEGIN
 SELECT NVL(RTRIM(LABORATORIO.DE_LABORATORIO),' ')
   INTO l_laboratorio
   FROM LGTM_PRODUCTO PRODUCTO,
        CMTR_LABORATORIO LABORATORIO
  WHERE (PRODUCTO.CO_COMPANIA = LABORATORIO.CO_COMPANIA(+)
    AND PRODUCTO.CO_LABORATORIO = LABORATORIO.CO_LABORATORIO(+))
    AND PRODUCTO.CO_COMPANIA = codigocompania_in
    AND PRODUCTO.CO_PRODUCTO = codigoproducto_in;
 RETURN l_laboratorio;
 EXCEPTION
   WHEN NO_DATA_FOUND THEN
  RETURN 'No determinado';
  END;

  /* ************************************************************************ */
  FUNCTION INV_LISTA_GUIAS_PEND(codigocompania_in IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
             codigolocal_in IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
          OPEN eckcur FOR SELECT NVL(GUIA.NU_RECEPCION_PRODUCTO, ' ') || 'Ã' ||
               NVL(GUIA.NU_DOCUMENTO_RECEPCION, ' ') || 'Ã' ||
         NVL(LOCAL_DEST.TI_LOCAL , ' ') || 'Ã' ||
                           NVL(RTRIM(LOCAL_DEST.DE_LOCAL), ' ') || 'Ã' ||
                        NVL(TO_CHAR(GUIA.FE_RECEPCION_PRODUCTO, 'dd/MM/yyyy hh24:mi:ss') ,' ') || 'Ã' ||
                           NVL(GUIA.CA_ITEM_RECEPCION , 0) || 'Ã' ||
                           TO_CHAR(NVL(GUIA.CA_PROD_RECEPCION , 0), '999,990')  || 'Ã' ||
                           DECODE(GUIA.ES_RECEPCION_PRODUCTO, ESTADO_RECEP_PROD_PENDIENTE, DESCRIP_RECEP_PROD_PENDIENTE, ESTADO_RECEP_PROD_COMPLETO, DESCRIP_RECEP_PROD_COMPLETO, ' ') || 'Ã' ||
                           NVL(RTRIM(GUIA.DE_OBSERVACION), ' ')
       FROM LGTC_RECEPCION_PRODUCTO GUIA,
            VTTM_LOCAL LOCAL_DEST
      WHERE GUIA.CO_COMPANIA = codigocompania_in AND
          GUIA.CO_LOCAL = codigolocal_in AND
         GUIA.CO_COMPANIA = LOCAL_DEST.CO_COMPANIA AND
          GUIA.CO_LOCAL_ORIGEN = LOCAL_DEST.CO_LOCAL AND
         GUIA.TI_RECEPCION = 'A' AND
         GUIA.ES_RECEPCION_PRODUCTO = 'P'
         --AND  GUIA.TI_DOCUMENTO_RECEPCION = '03'
      ORDER BY GUIA.FE_RECEPCION_PRODUCTO;
    RETURN eckcur;
  END;

  /* ************************************************************************ */
  FUNCTION INV_OBTIENE_GUIA_ENTRADA(codigocompania_in  IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                 codigolocal_in     IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
            numerorecepcion_in IN  LGTD_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
          OPEN eckcur FOR SELECT GUIA.CO_LOCAL_ORIGEN
       FROM LGTC_RECEPCION_PRODUCTO GUIA
      WHERE GUIA.CO_COMPANIA = codigocompania_in AND
          GUIA.CO_LOCAL = codigolocal_in AND
         GUIA.NU_RECEPCION_PRODUCTO = numerorecepcion_in;
    RETURN eckcur;
  END;

  /* ************************************************************************ */
  FUNCTION INV_DETALLE_GUIA_ALMACEN(codigocompania_in IN LGTD_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
            codigolocal_in    IN LGTD_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
       numerorecepcion_in IN  LGTD_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
          OPEN eckcur FOR SELECT NVL(PRODUCTO.CO_PRODUCTO,' ') || 'Ã' ||
               NVL(RTRIM(PRODUCTO.DE_PRODUCTO),' ') || 'Ã' ||
                        NVL(RTRIM(PRODUCTO.DE_UNIDAD_PRODUCTO),' ') || 'Ã' ||
                           NVL(DETALLE_GUIA.CA_ENVIADA_ENTERA ,0) || 'Ã' ||
                           --NVL(DETALLE_GUIA.CA_ENVIADA_FRACCION ,0) || 'Ã' ||
                           NVL(DETALLE_GUIA.CA_CONFIRMADA_ENTERA ,0)  || 'Ã' ||
                           --NVL(DETALLE_GUIA.CA_CONFIRMADA_FRACCION ,0)  || 'Ã' ||
                           NVL(DETALLE_GUIA.CA_DIFERENCIA_ENTERA, 0) || 'Ã' ||
                           --NVL(DETALLE_GUIA.CA_DIFERENCIA_FRACCION, 0) || 'Ã' ||
                           NVL(DETALLE_GUIA.IN_AFECTA_PRODUCTO,' ')  || 'Ã' ||
                           NVL(DETALLE_GUIA.NU_PAGINA_GUIA, 0)
       FROM LGTD_RECEPCION_PRODUCTO DETALLE_GUIA,
            LGTM_PRODUCTO PRODUCTO
      WHERE DETALLE_GUIA.CO_COMPANIA = codigocompania_in AND
          DETALLE_GUIA.CO_LOCAL = codigolocal_in AND
         DETALLE_GUIA.NU_RECEPCION_PRODUCTO = numerorecepcion_in AND
         DETALLE_GUIA.CO_COMPANIA = PRODUCTO.CO_COMPANIA AND
         DETALLE_GUIA.CO_PRODUCTO = PRODUCTO.CO_PRODUCTO
      ORDER BY DETALLE_GUIA.NU_PAGINA_GUIA,PRODUCTO.DE_PRODUCTO;
    RETURN eckcur;
  END;

  /* ************************************************************************ */
  FUNCTION INV_DETALLE_GUIA_LOCAL(codigocompania_in IN LGTD_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
            codigolocal_in    IN LGTD_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
       numerorecepcion_in IN  LGTD_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
          OPEN eckcur FOR SELECT NVL(PRODUCTO.CO_PRODUCTO,' ') || 'Ã' ||
               NVL(RTRIM(PRODUCTO.DE_PRODUCTO),' ') || 'Ã' ||
                        NVL(RTRIM(PRODUCTO.DE_UNIDAD_PRODUCTO),' ') || 'Ã' ||
                           NVL(DETALLE_GUIA.CA_ENVIADA_ENTERA ,0) || 'Ã' ||
                           NVL(DETALLE_GUIA.CA_ENVIADA_FRACCION ,0) || 'Ã' ||
                           NVL(DETALLE_GUIA.CA_CONFIRMADA_ENTERA ,0)  || 'Ã' ||
                           NVL(DETALLE_GUIA.CA_CONFIRMADA_FRACCION ,0)  || 'Ã' ||
                           NVL(DETALLE_GUIA.CA_DIFERENCIA_ENTERA, 0) || 'Ã' ||
                           NVL(DETALLE_GUIA.CA_DIFERENCIA_FRACCION, 0) || 'Ã' ||
                           NVL(DETALLE_GUIA.IN_AFECTA_PRODUCTO,' ')  || 'Ã' ||
                           NVL(DETALLE_GUIA.NU_PAGINA_GUIA, 0)
       FROM LGTD_RECEPCION_PRODUCTO DETALLE_GUIA,
         LGTM_PRODUCTO PRODUCTO
      WHERE DETALLE_GUIA.CO_COMPANIA = codigocompania_in AND
          DETALLE_GUIA.CO_LOCAL = codigolocal_in AND
         DETALLE_GUIA.NU_RECEPCION_PRODUCTO = numerorecepcion_in AND
         DETALLE_GUIA.CO_COMPANIA = PRODUCTO.CO_COMPANIA AND
         DETALLE_GUIA.CO_PRODUCTO = PRODUCTO.CO_PRODUCTO
      ORDER BY DETALLE_GUIA.NU_PAGINA_GUIA,PRODUCTO.DE_PRODUCTO;
    RETURN eckcur;
  END;


  /* ************************************************************************ */
  FUNCTION INV_LISTA_PEDIDO_ADIC_PEND(codigocompania_in IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                codigolocal_in    IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
          OPEN eckcur FOR SELECT NVL(ADIC.NU_PEDIDO_PRODUCTO,0) || 'Ã' ||
                        NVL(TO_CHAR(ADIC.FE_CREA_PEDIDO_PRODUCTO,'dd/MM/yyyy') ,' ') || 'Ã' ||
                           NVL(MOTIVO.DE_MOTIVO ,' ') || 'Ã' ||
                           TO_CHAR(NVL(ADIC.CA_TOTAL_PRODUCTO ,0), '99,990') || 'Ã' ||
                           DECODE(CLIENTE.TI_CLIENTE,
           '001',
           NVL(TRIM(CLIENTE.AP_PATERNO_CLIENTE),'...')||' '||NVL(TRIM(CLIENTE.AP_MATERNO_CLIENTE),'...')||' '||NVL(TRIM(CLIENTE.NO_CLIENTE),'...'),
           '002',
        NVL(TRIM(CLIENTE.DE_RAZON_SOCIAL),' '),
        ' ')
       FROM LGTC_PEDIDO_PRODUCTO ADIC,
            CMTR_MOTIVO MOTIVO,
         VTTR_CLIENTE_LOCAL CLIENTE
      WHERE ADIC.CO_COMPANIA = codigocompania_in AND
          ADIC.CO_LOCAL = codigolocal_in AND
         ADIC.CO_GRUPO_MOTIVO = MOTIVO.CO_GRUPO_MOTIVO AND
         ADIC.CO_MOTIVO_PEDIDO = MOTIVO.CO_MOTIVO AND
         ADIC.ES_PEDIDO_PRODUCTO = 'P' AND
         ADIC.CO_COMPANIA = CLIENTE.CO_COMPANIA(+) AND
         ADIC.CO_LOCAL = CLIENTE.CO_LOCAL(+) AND
         ADIC.CO_CLIENTE_LOCAL = CLIENTE.CO_CLIENTE_LOCAL(+)
      ORDER BY ADIC.NU_PEDIDO_PRODUCTO DESC;
    RETURN eckcur;
  END;

/* ************************************************************************ */
  FUNCTION INV_LISTA_PEDIDO_REP_PEND(codigocompania_in IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                  codigolocal_in    IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
       OPEN eckcur FOR
    SELECT   NVL(CAB_REP.NU_PEDIDO_PRODUCTO, 0) || 'Ã' ||
             NVL(TO_CHAR(CAB_REP.FE_SOLICITA_PEDIDO, 'dd/MM/yyyy HH24:MI'), ' ') || 'Ã' ||
             TO_CHAR(NVL(CAB_REP.CA_TOTAL_PRODUCTO, 0), '999,990') || 'Ã' ||
       TO_CHAR(NVL(SUM(DET_REP.CA_PEDIDA), 0), '999,990') || 'Ã' ||
       DECODE(CAB_REP.ES_PEDIDO_PRODUCTO,'P','PENDIENTE','N','ANULADO','T','PROCESADO') || 'Ã' ||
          NVL(CAB_REP.NU_MINIMO_DIAS_REPOSICION, 0) || 'Ã' ||
          NVL(CAB_REP.NU_MAXIMO_DIAS_REPOSICION, 0) || 'Ã' ||
          NVL(CAB_REP.NU_DIAS_ROTACION_PROMEDIO, 0) || 'Ã' ||
      NVL(CAB_REP.ES_PEDIDO_PRODUCTO,'P')
    FROM     LGTC_PEDIDO_PRODUCTO CAB_REP,
       LGTD_PEDIDO_PRODUCTO DET_REP
    WHERE    CAB_REP.CO_COMPANIA = codigocompania_in
    AND      CAB_REP.CO_LOCAL = codigolocal_in
    --AND      CAB_REP.ES_PEDIDO_PRODUCTO = 'P'
    AND      CAB_REP.TI_PEDIDO_PRODUCTO = 'RP'
    AND   CAB_REP.CO_COMPANIA = DET_REP.CO_COMPANIA
    AND      CAB_REP.CO_LOCAL = DET_REP.CO_LOCAL
    AND      CAB_REP.TI_PEDIDO_PRODUCTO = DET_REP.TI_PEDIDO_PRODUCTO
    AND      CAB_REP.NU_PEDIDO_PRODUCTO = DET_REP.NU_PEDIDO_PRODUCTO
    GROUP BY CAB_REP.NU_PEDIDO_PRODUCTO,
             CAB_REP.FE_SOLICITA_PEDIDO,
             CAB_REP.CA_TOTAL_PRODUCTO,
             CAB_REP.NU_MINIMO_DIAS_REPOSICION,
             CAB_REP.NU_MAXIMO_DIAS_REPOSICION,
             CAB_REP.NU_DIAS_ROTACION_PROMEDIO,
             CAB_REP.ES_PEDIDO_PRODUCTO
    ORDER BY CAB_REP.NU_PEDIDO_PRODUCTO DESC;
    RETURN eckcur;
  END;

  /* ************************************************************************ */
  FUNCTION INV_DETALLE_PEDIDO_ADIC(codigocompania_in IN LGTD_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
             codigolocal_in       IN LGTD_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
        numeropedido_in      IN LGTD_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
          OPEN eckcur FOR SELECT NVL(PRODUCTO.CO_PRODUCTO,0) || 'Ã' ||
               NVL(PRODUCTO.DE_PRODUCTO,' ') || 'Ã' ||
                        NVL(PRODUCTO.DE_UNIDAD_PRODUCTO,' ') || 'Ã' ||
                           TO_CHAR(NVL(DET_ADIC.CA_PEDIDA ,0)) || 'Ã' ||
         DECODE(PROD_LOCAL.IN_PROD_FRACCIONADO, 'S',
                           NVL(TRUNC(PROD_LOCAL.CA_STOCK_DISPONIBLE / PROD_LOCAL.VA_FRACCION),0),
         NVL(PROD_LOCAL.CA_STOCK_DISPONIBLE,0))
       FROM LGTD_PEDIDO_PRODUCTO DET_ADIC,
            LGTR_PRODUCTO_LOCAL PROD_LOCAL,
         LGTM_PRODUCTO PRODUCTO
      WHERE DET_ADIC.CO_COMPANIA = codigocompania_in AND
          DET_ADIC.CO_LOCAL = codigolocal_in AND
         DET_ADIC.NU_PEDIDO_PRODUCTO = numeropedido_in AND
         DET_ADIC.CO_COMPANIA = PROD_LOCAL.CO_COMPANIA AND
         DET_ADIC.CO_LOCAL = PROD_LOCAL.CO_LOCAL AND
         DET_ADIC.CO_PRODUCTO = PROD_LOCAL.CO_PRODUCTO AND
         PROD_LOCAL.CO_COMPANIA = PRODUCTO.CO_COMPANIA AND
         PROD_LOCAL.CO_PRODUCTO = PRODUCTO.CO_PRODUCTO
      ORDER BY DET_ADIC.CO_PRODUCTO;
    RETURN eckcur;
  END;

  /* ************************************************************************ */
  FUNCTION INV_GRABA_PEDIDO_REP(codigocompania_in   IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                                codigolocal_in      IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
        tipopedidoprod_in   IN LGTC_PEDIDO_PRODUCTO.TI_PEDIDO_PRODUCTO%TYPE,
        localdestino_in     IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL_DESPACHO%TYPE,
        idcreapedido_in     IN LGTC_PEDIDO_PRODUCTO.ID_CREA_PEDIDO_PRODUCTO%TYPE,/*
        cantidaditems_in    IN LGTC_PEDIDO_PRODUCTO.CA_TOTAL_PRODUCTO%TYPE,*/
        numerosemanasrot_in IN LGTC_PEDIDO_PRODUCTO.NU_DIAS_ROTACION_PROMEDIO%TYPE,
        numerominimodias_in IN LGTC_PEDIDO_PRODUCTO.NU_MINIMO_DIAS_REPOSICION%TYPE,
        numeromaximodias_in IN LGTC_PEDIDO_PRODUCTO.NU_MAXIMO_DIAS_REPOSICION%TYPE)
           RETURN LGTC_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE IS
    l_numeracion    CMTR_NUMERACION.NU_SEC_NUMERACION%TYPE;
 l_numero_pedido LGTC_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE;
 l_fecha_cal_max_min LGTC_PEDIDO_PRODUCTO.FE_CALCULO_MAX_MIN%TYPE;
  BEGIN
    l_numeracion := Ptovta_Utility.OBTENER_NUMERACION(codigocompania_in, codigolocal_in, '005');
 l_numero_pedido := Eckerd_Utility.COMPLETAR_CON_SIMBOLO(l_numeracion, 8, '0', 'I');
 IF (l_numeracion > 0) THEN

      INSERT INTO LGTC_PEDIDO_PRODUCTO
      (CO_COMPANIA, CO_LOCAL, TI_PEDIDO_PRODUCTO, NU_PEDIDO_PRODUCTO,
              CO_LOCAL_DESPACHO, /*CA_TOTAL_PRODUCTO,*/ ID_SOLICITA_PEDIDO, FE_SOLICITA_PEDIDO,
              ES_PEDIDO_PRODUCTO, NU_DIAS_ROTACION_PROMEDIO, NU_MINIMO_DIAS_REPOSICION, NU_MAXIMO_DIAS_REPOSICION,
              ID_CREA_PEDIDO_PRODUCTO, FE_CREA_PEDIDO_PRODUCTO)
       VALUES (codigocompania_in, codigolocal_in, tipopedidoprod_in, l_numero_pedido,
      localdestino_in, /*cantidaditems_in,*/ idcreapedido_in, SYSDATE,
              'P', numerosemanasrot_in, numerominimodias_in, numeromaximodias_in,
               idcreapedido_in, SYSDATE);

      Ptovta_Utility.ACTUALIZAR_NUMERACION_NOCOMMIT(codigocompania_in, codigolocal_in, '005', idcreapedido_in);

   INV_GRABA_TOTDET_PEDIDO_REP(codigocompania_in,
            codigolocal_in,
          tipopedidoprod_in,
          l_numero_pedido,
          idcreapedido_in);
   -- actualizar los productos donde CA_PROD_NO_ATENDIDO > 0
   UPDATE   LGTR_PRODUCTO_LOCAL
   SET    CA_PROD_NO_ATENDIDO = 0,
       FE_MOD_PROD_LOCAL = SYSDATE,
                   IN_REPLICA = 0
   WHERE    CO_COMPANIA = codigocompania_in
   AND    CO_LOCAL = codigolocal_in
   AND    CA_PROD_NO_ATENDIDO > 0;
   COMMIT;

   SELECT FE_CALCULO_MAX_MIN
   INTO  l_fecha_cal_max_min
   FROM   VTTM_LOCAL
   WHERE  CO_COMPANIA = codigocompania_in
   AND    CO_LOCAL = codigolocal_in;

   UPDATE LGTC_PEDIDO_PRODUCTO
     SET FE_CALCULO_MAX_MIN = l_fecha_cal_max_min
   WHERE CO_COMPANIA = codigocompania_in
   AND   CO_LOCAL = codigolocal_in
   AND   TI_PEDIDO_PRODUCTO = 'RP'
   AND   NU_PEDIDO_PRODUCTO = l_numero_pedido;

    END IF;
    RETURN l_numero_pedido;
  END;

  /* ************************************************************************ */
  FUNCTION INV_GRABA_PEDIDO_REP_NO_COMMIT(codigocompania_in   IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                                codigolocal_in      IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
        tipopedidoprod_in   IN LGTC_PEDIDO_PRODUCTO.TI_PEDIDO_PRODUCTO%TYPE,
        localdestino_in     IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL_DESPACHO%TYPE,
        idcreapedido_in     IN LGTC_PEDIDO_PRODUCTO.ID_CREA_PEDIDO_PRODUCTO%TYPE,/*
        cantidaditems_in    IN LGTC_PEDIDO_PRODUCTO.CA_TOTAL_PRODUCTO%TYPE,*/
        numerosemanasrot_in IN LGTC_PEDIDO_PRODUCTO.NU_DIAS_ROTACION_PROMEDIO%TYPE,
        numerominimodias_in IN LGTC_PEDIDO_PRODUCTO.NU_MINIMO_DIAS_REPOSICION%TYPE,
        numeromaximodias_in IN LGTC_PEDIDO_PRODUCTO.NU_MAXIMO_DIAS_REPOSICION%TYPE)
           RETURN LGTC_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE IS
    l_numeracion    CMTR_NUMERACION.NU_SEC_NUMERACION%TYPE;
 l_numero_pedido LGTC_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE;
 l_fecha_cal_max_min LGTC_PEDIDO_PRODUCTO.FE_CALCULO_MAX_MIN%TYPE;
  BEGIN
    l_numeracion := Ptovta_Utility.OBTENER_NUMERACION(codigocompania_in, codigolocal_in, '005');
    l_numero_pedido := Eckerd_Utility.COMPLETAR_CON_SIMBOLO(l_numeracion, 8, '0', 'I');
    IF (l_numeracion > 0) THEN

      INSERT INTO LGTC_PEDIDO_PRODUCTO
      (CO_COMPANIA, CO_LOCAL, TI_PEDIDO_PRODUCTO, NU_PEDIDO_PRODUCTO,
              CO_LOCAL_DESPACHO, /*CA_TOTAL_PRODUCTO,*/ ID_SOLICITA_PEDIDO, FE_SOLICITA_PEDIDO,
              ES_PEDIDO_PRODUCTO, NU_DIAS_ROTACION_PROMEDIO, NU_MINIMO_DIAS_REPOSICION, NU_MAXIMO_DIAS_REPOSICION,
              ID_CREA_PEDIDO_PRODUCTO, FE_CREA_PEDIDO_PRODUCTO)
       VALUES (codigocompania_in, codigolocal_in, tipopedidoprod_in, l_numero_pedido,
      localdestino_in, /*cantidaditems_in,*/ idcreapedido_in, SYSDATE,
              'P', numerosemanasrot_in, numerominimodias_in, numeromaximodias_in,
               idcreapedido_in, SYSDATE);

      Ptovta_Utility.ACTUALIZAR_NUMERACION_NOCOMMIT(codigocompania_in, codigolocal_in, '005', idcreapedido_in);

      INV_GRABA_TOTDET_PEDIDO_REP(codigocompania_in,
            codigolocal_in,
          tipopedidoprod_in,
          l_numero_pedido,
          idcreapedido_in);
   -- actualizar los productos donde CA_PROD_NO_ATENDIDO > 0
   UPDATE   LGTR_PRODUCTO_LOCAL
   SET    CA_PROD_NO_ATENDIDO = 0,
       FE_MOD_PROD_LOCAL = SYSDATE,
                   IN_REPLICA = 0
   WHERE    CO_COMPANIA = codigocompania_in
   AND    CO_LOCAL = codigolocal_in
   AND    CA_PROD_NO_ATENDIDO > 0;

   SELECT FE_CALCULO_MAX_MIN
   INTO  l_fecha_cal_max_min
   FROM   VTTM_LOCAL
   WHERE  CO_COMPANIA = codigocompania_in
   AND    CO_LOCAL = codigolocal_in;

   UPDATE LGTC_PEDIDO_PRODUCTO
     SET FE_CALCULO_MAX_MIN = l_fecha_cal_max_min
   WHERE CO_COMPANIA = codigocompania_in
   AND   CO_LOCAL = codigolocal_in
   AND   TI_PEDIDO_PRODUCTO = 'RP'
   AND   NU_PEDIDO_PRODUCTO = l_numero_pedido;

    END IF;
    RETURN l_numero_pedido;
  END;



  /* ************************************************************************ */
  PROCEDURE INV_GRABA_TOTDET_PEDIDO_REP(codigocompania_in IN LGTD_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                                        codigolocal_in    IN LGTD_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
                tipopedidoprod_in IN LGTC_PEDIDO_PRODUCTO.TI_PEDIDO_PRODUCTO%TYPE,
                numeropedido_in   IN LGTD_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE,
          idcreapedido_in   IN LGTD_PEDIDO_PRODUCTO.ID_CREA_DET_PEDIDO_PRODUCTO%TYPE
          ) IS
 codigoproducto_in       LGTD_PEDIDO_PRODUCTO.CO_PRODUCTO%TYPE;
 cantidadpedida_in       LGTD_PEDIDO_PRODUCTO.CA_PEDIDA%TYPE;
 cantidadminima_in       LGTD_PEDIDO_PRODUCTO.CA_STOCK_MINIMO_PEDIDO%TYPE;
 cantidadmaxima_in       LGTD_PEDIDO_PRODUCTO.CA_STOCK_MAXIMO_PEDIDO%TYPE;
 numeroitem_in           LGTD_PEDIDO_PRODUCTO.NU_ITEM%TYPE;
 precioventa_in    LGTD_PEDIDO_PRODUCTO.VA_PRECIO_VENTA%TYPE;
 unidadproducto_in  LGTD_PEDIDO_PRODUCTO.DE_UNIDAD_PRODUCTO%TYPE;
 rotacionproducto_in  LGTD_PEDIDO_PRODUCTO.VA_ROTACION_PRODUCTO%TYPE;
 cantidadpedidasistema_in LGTD_PEDIDO_PRODUCTO.CA_SUGERIDA_CALCULADA%TYPE;
 stock_disponible_calculo_in LGTD_PEDIDO_PRODUCTO.CA_STK_DISPONIBLE_PED_REP%TYPE;

 porcadicpedidorep_in  VTTM_LOCAL.PC_ADICIONAL_PEDIDO_REPOSICION%TYPE;

    CURSOR detallePedido IS
     SELECT PRODUCTO_LOCAL.CO_PRODUCTO,
                   PRODUCTO_LOCAL.CA_STOCK_MINIMO,
                   PRODUCTO_LOCAL.CA_STOCK_MAXIMO,
                     DECODE(PRODUCTO_LOCAL.CA_STOCK_REPONER,
                 NULL, PRODUCTO_LOCAL.CA_STOCK_REPONER_CALCULA,
      PRODUCTO_LOCAL.CA_STOCK_REPONER) CANTIDAD_PEDIDA,
       DECODE(PRODUCTO_LOCAL.VA_VENTA,
          NULL, 0,
              PRODUCTO_LOCAL.VA_VENTA) - (DECODE(PRODUCTO_LOCAL.VA_VENTA,
       NULL, 0,
       PRODUCTO_LOCAL.VA_VENTA) * (1-(1-(DECODE(PRODUCTO_LOCAL.PC_DESCUENTO_1,
                 NULL, 0,
                 PRODUCTO_LOCAL.PC_DESCUENTO_1)/100)))) PRECIO_VENTA,
     NVL(PRODUCTO.DE_UNIDAD_PRODUCTO, '') DE_UNIDAD_PRODUCTO,
     PRODUCTO_LOCAL.VA_ROTACION VA_ROTACION,
     PRODUCTO_LOCAL.CA_STOCK_REPONER_CALCULA CANTIDAD_SISTEMA,
     PRODUCTO_LOCAL.CA_STK_DISPONIBLE_PED_REP
     FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL,
         LGTM_PRODUCTO PRODUCTO
    WHERE  PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
     AND PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
--    AND PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A'
       AND (PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A' OR PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE > 0)
    AND PRODUCTO_LOCAL.IN_PRODUCTO_REPONER = 'S'
    AND PRODUCTO.ES_PRODUCTO = 'A'
   AND      (PRODUCTO_LOCAL.CA_STOCK_REPONER > 0 OR PRODUCTO_LOCAL.CA_STOCK_REPONER_CALCULA  > DECODE(PRODUCTO_LOCAL.CA_STOCK_REPONER,
                                              0, PRODUCTO_LOCAL.CA_STOCK_REPONER_CALCULA,
                                       0))--  modificacion 20050108
    AND PRODUCTO_LOCAL.CO_COMPANIA = PRODUCTO.CO_COMPANIA
    AND PRODUCTO_LOCAL.CO_PRODUCTO = PRODUCTO.CO_PRODUCTO;
  BEGIN
     numeroitem_in := 0;

   SELECT NVL(PC_ADICIONAL_PEDIDO_REPOSICION,0)
   INTO porcadicpedidorep_in
   FROM VTTM_LOCAL
   WHERE CO_COMPANIA = codigocompania_in AND CO_LOCAL = codigolocal_in;

    FOR detallePedido_rec IN detallePedido
   LOOP
       numeroitem_in := numeroitem_in + 1;
   codigoproducto_in := detallePedido_rec.CO_PRODUCTO;
   cantidadminima_in := detallePedido_rec.CA_STOCK_MINIMO;
   cantidadmaxima_in := detallePedido_rec.CA_STOCK_MAXIMO;
   cantidadpedida_in := detallePedido_rec.CANTIDAD_PEDIDA;
   precioventa_in := detallePedido_rec.PRECIO_VENTA;
   unidadproducto_in := detallePedido_rec.DE_UNIDAD_PRODUCTO;
   rotacionproducto_in := detallePedido_rec.VA_ROTACION;
   cantidadpedidasistema_in := detallePedido_rec.CANTIDAD_SISTEMA;
   stock_disponible_calculo_in := detallePedido_rec.CA_STK_DISPONIBLE_PED_REP;

   -- En el mejor de los casos,
   -- la "cantidadpedida_in" puede ser maximo un "porcadicpedidorep_in" mayor a "cantidadpedidasistema_in"

   -- ESTE CASO SOLO SE PRESENTA POR ERROR EN EL SISTEMA
   IF ( cantidadpedida_in > CEIL(cantidadpedidasistema_in * (1 + (porcadicpedidorep_in/100))) ) THEN
      INV_GRABA_ERROR_CANT_PEDIDA(codigocompania_in, codigolocal_in, tipopedidoprod_in,  numeropedido_in, numeroitem_in, codigoproducto_in, cantidadpedida_in, idcreapedido_in);

               -- AGREGADO POR CRAMIREZ - 20.04.2009
      IF (cantidadpedida_in>=1 AND CEIL(cantidadpedidasistema_in * (1 + (porcadicpedidorep_in/100)))=0 ) THEN
                  cantidadpedida_in := 1; -- FORZAMOS SOLO A PEDIR 1 A SOLICITUD DE GMERCADO
      ELSE
         cantidadpedida_in := cantidadpedidasistema_in;
               END IF;
      --cantidadpedida_in := cantidadpedidasistema_in; -- COMENTADO POR POR CRAMIREZ - 20.04.2009

      -- cantidadpedida_in := CEIL(cantidadpedidasistema_in * (1 + (porcadicpedidorep_in/100)));
   END IF;


   INSERT INTO LGTD_PEDIDO_PRODUCTO
         (CO_COMPANIA, CO_LOCAL, TI_PEDIDO_PRODUCTO, NU_PEDIDO_PRODUCTO,
       NU_ITEM, CO_PRODUCTO, NU_REVISION_PRODUCTO, CA_PEDIDA, CA_SUGERIDA_CALCULADA,
       CA_STOCK_MINIMO_PEDIDO, CA_STOCK_MAXIMO_PEDIDO, DE_UNIDAD_PRODUCTO,
       ES_DET_PEDIDO_PRODUCTO, ID_CREA_DET_PEDIDO_PRODUCTO,
                   FE_CREA_DET_PEDIDO_PRODUCTO, VA_PRECIO_VENTA, VA_ROTACION_PRODUCTO, CA_STK_DISPONIBLE_PED_REP)
            VALUES
       (codigocompania_in, codigolocal_in, tipopedidoprod_in,  numeropedido_in,
       numeroitem_in,  codigoproducto_in, '0', cantidadpedida_in, cantidadpedidasistema_in,
       cantidadminima_in,  cantidadmaxima_in, unidadproducto_in, 'A', idcreapedido_in,
       SYSDATE, precioventa_in, rotacionproducto_in, stock_disponible_calculo_in);
      UPDATE   LGTR_PRODUCTO_LOCAL
   SET      -- CA_STOCK_REPONER = NULL,
       CA_PROD_NO_ATENDIDO = 0,
      CA_ULTIMO_PEDIDO_REP = cantidadpedida_in,
     FE_MOD_PROD_LOCAL = SYSDATE,
                   IN_REPLICA = 0
      WHERE    CO_COMPANIA = codigocompania_in AND
      CO_LOCAL = codigolocal_in AND
      CO_PRODUCTO = codigoproducto_in;
   END LOOP;
   -- actualizando la cantidad de items por pedido
   UPDATE   LGTC_PEDIDO_PRODUCTO
   SET    CA_TOTAL_PRODUCTO = numeroitem_in,
       FE_MOD_PEDIDO_PRODUCTO = SYSDATE,
                   IN_REPLICA = 0
   WHERE    CO_COMPANIA = codigocompania_in
   AND    CO_LOCAL = codigolocal_in
   AND    NU_PEDIDO_PRODUCTO = numeropedido_in;
  END;

  /* ************************************************************************ */
  FUNCTION INV_AFECTA_PAGINA_GUIA(codigocompania_in    IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                                codigolocal_in         IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
        numerorecepcion_in    IN LGTC_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE,
        numeropagina_in        IN LGTD_RECEPCION_PRODUCTO.NU_PAGINA_GUIA%TYPE,
        idmodrecepguia_in      IN LGTC_RECEPCION_PRODUCTO.ID_MOD_RECEPCION_PRODUCTO%TYPE,
        codigogrupomotivo_in   IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
        codigomotivo_in        IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE,
        tipodocumento_in       IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
        numerodocumento_in     IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
        inafectastock_in       IN LGTC_RECEPCION_PRODUCTO.IN_AFECTA_STOCK%TYPE
        ) RETURN CHAR IS
  codigoProducto          LGTD_RECEPCION_PRODUCTO.CO_PRODUCTO%TYPE;
  cantenviadaentera       LGTD_RECEPCION_PRODUCTO.CA_ENVIADA_ENTERA%TYPE;
  cantenviadafraccion     LGTD_RECEPCION_PRODUCTO.CA_ENVIADA_FRACCION%TYPE;
  cantconfirmadaentera    LGTD_RECEPCION_PRODUCTO.CA_CONFIRMADA_ENTERA%TYPE;
  cantconfirmadafraccion  LGTD_RECEPCION_PRODUCTO.CA_CONFIRMADA_FRACCION%TYPE;
  cantdiferenciaentera    LGTD_RECEPCION_PRODUCTO.CA_DIFERENCIA_ENTERA%TYPE;
  cantdiferenciafraccion  LGTD_RECEPCION_PRODUCTO.CA_DIFERENCIA_FRACCION%TYPE;
  inprodfraccionado       LGTR_PRODUCTO_LOCAL.IN_PROD_FRACCIONADO%TYPE;
  valorfraccion           LGTR_PRODUCTO_LOCAL.VA_FRACCION%TYPE;
  cantmovimiento          LGTV_KARDEX.CA_MOVIMIENTO%TYPE;
  cantidad NUMBER;
  cerrarguia CHAR;
    CURSOR detalleGuia IS
         SELECT DET_RECEPCION.CO_PRODUCTO,
       DET_RECEPCION.CA_ENVIADA_ENTERA,
       DET_RECEPCION.CA_ENVIADA_FRACCION,
       DET_RECEPCION.CA_CONFIRMADA_ENTERA,
       DET_RECEPCION.CA_CONFIRMADA_FRACCION,
       DET_RECEPCION.CA_DIFERENCIA_ENTERA,
       DET_RECEPCION.CA_DIFERENCIA_FRACCION,
       DET_RECEPCION.NU_ITEM_RECEPCION_GUIA,
          PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
       PRODUCTO_LOCAL.VA_FRACCION
     FROM LGTD_RECEPCION_PRODUCTO DET_RECEPCION,
          LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL
    WHERE DET_RECEPCION.CO_COMPANIA       = codigocompania_in  AND
          DET_RECEPCION.CO_LOCAL          = codigolocal_in AND
       DET_RECEPCION.NU_RECEPCION_PRODUCTO = numerorecepcion_in AND
       DET_RECEPCION.IN_AFECTA_PRODUCTO IS NULL AND
       DET_RECEPCION.CO_COMPANIA       = PRODUCTO_LOCAL.CO_COMPANIA  AND
          DET_RECEPCION.CO_LOCAL          = PRODUCTO_LOCAL.CO_LOCAL AND
       DET_RECEPCION.CO_PRODUCTO       = PRODUCTO_LOCAL.CO_PRODUCTO AND
       DET_RECEPCION.NU_PAGINA_GUIA    = numeropagina_in;
  BEGIN
   FOR detalleGuia_rec IN detalleGuia
  LOOP
       codigoProducto := detalleGuia_rec.CO_PRODUCTO;
     cantenviadaentera := NVL(detalleGuia_rec.CA_ENVIADA_ENTERA, 0);
     cantenviadafraccion := NVL(detalleGuia_rec.CA_ENVIADA_FRACCION, 0);
     cantconfirmadaentera := NVL(detalleGuia_rec.CA_CONFIRMADA_ENTERA,0);
     cantconfirmadafraccion := NVL(detalleGuia_rec.CA_CONFIRMADA_FRACCION,0);
     cantdiferenciaentera := NVL(detalleGuia_rec.CA_DIFERENCIA_ENTERA, 0);
     cantdiferenciafraccion := NVL(detalleGuia_rec.CA_DIFERENCIA_FRACCION, 0);
     inprodfraccionado := detalleGuia_rec.IN_PROD_FRACCIONADO;
     valorfraccion := detalleGuia_rec.VA_FRACCION;

       IF cantdiferenciaentera <> 0 OR cantdiferenciafraccion <> 0 THEN
       -- 1er REGISTRO
       --afectando cantidad enviada
      IF (inprodfraccionado = 'S') THEN
         cantmovimiento := (cantenviadaentera * valorfraccion) + cantenviadafraccion;
      ELSE
         cantmovimiento := cantenviadaentera;
      END IF;
      --graba kardex y afecta stock
      IF (inafectastock_in = 'S') THEN
         INV_GRABA_KARDEX(codigocompania_in, codigolocal_in, codigoproducto, cantmovimiento,
                        tipodocumento_in, numerodocumento_in, 'Guia Matriz', codigogrupomotivo_in,
               codigomotivo_in, idmodrecepguia_in);
      END IF;
     -- 2do REGISTRO
       --afectando diferencia
      IF (inprodfraccionado = 'S') THEN
         cantmovimiento := (cantdiferenciaentera * valorfraccion) + cantdiferenciafraccion;
      ELSE
         cantmovimiento := cantdiferenciaentera;
      END IF;
      --graba kardex y afecta stock
      IF (inafectastock_in = 'S') THEN
         INV_GRABA_KARDEX(codigocompania_in, codigolocal_in, codigoproducto, cantmovimiento,
                        tipodocumento_in, numerodocumento_in, 'Guia Matriz', codigogrupomotivo_in,
               COD_MOT_GUIA_MODERN, idmodrecepguia_in);
      END IF;
     ELSE
     --afecta los productos que no se han cambiado la cantidad de recepcisn
     IF (inprodfraccionado = 'S') THEN
        cantmovimiento := (cantconfirmadaentera * valorfraccion) + cantconfirmadafraccion;
     ELSE
        cantmovimiento := cantconfirmadaentera;
     END IF;
      --graba Kardex y afecta stock
      IF (inafectastock_in = 'S') THEN
         INV_GRABA_KARDEX(codigocompania_in, codigolocal_in, codigoproducto, cantmovimiento,
                        tipodocumento_in, numerodocumento_in, 'Guia Matriz', codigogrupomotivo_in,
               codigomotivo_in, idmodrecepguia_in);
      END IF;
     END IF;

     --Actualiza el detalle de la Guia
     UPDATE LGTD_RECEPCION_PRODUCTO
        SET IN_AFECTA_PRODUCTO = 'S',
         ID_MOD_DET_RECEPCION_PRODUCTO = idmodrecepguia_in,
      FE_MOD_DET_RECEPCION_PRODUCTO = SYSDATE,
    IN_REPLICA = 0
      WHERE CO_COMPANIA = codigocompania_in  AND
            CO_LOCAL = codigolocal_in AND
      NU_RECEPCION_PRODUCTO = numerorecepcion_in AND
      CO_PRODUCTO = codigoProducto;
     COMMIT;
  END LOOP;

  -- Se verifica si no existen paginas pendientes de afectar stock
  -- para proceder a cerrar el total de la guia
  SELECT COUNT(*)
    INTO cantidad
    FROM LGTD_RECEPCION_PRODUCTO
   WHERE CO_COMPANIA =codigocompania_in  AND
          CO_LOCAL = codigolocal_in AND
    NU_RECEPCION_PRODUCTO = numerorecepcion_in AND
    IN_AFECTA_PRODUCTO IS NULL;
  IF (cantidad = 0) THEN
     UPDATE LGTC_RECEPCION_PRODUCTO
        SET ES_RECEPCION_PRODUCTO = 'C',
        FE_CIERRE_RECEPCION = SYSDATE,
         ID_MOD_RECEPCION_PRODUCTO = idmodrecepguia_in,
      FE_MOD_RECEPCION_PRODUCTO = SYSDATE,
    IN_REPLICA = 0
      WHERE CO_COMPANIA  = codigocompania_in  AND
            CO_LOCAL     = codigolocal_in AND
      NU_RECEPCION_PRODUCTO = numerorecepcion_in;
     COMMIT;
     cerrarguia := 'S';
  ELSE
     cerrarguia := 'N';
  END IF;
  RETURN cerrarguia;
  END;

  /* ************************************************************************ */
  PROCEDURE INV_AFECTA_TOTAL_GUIA(codigocompania_in    IN LGTD_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                                codigolocal_in         IN LGTD_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
        numerorecepcion_in    IN LGTD_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE,
        idmodrecepguia_in      IN LGTC_RECEPCION_PRODUCTO.ID_MOD_RECEPCION_PRODUCTO%TYPE,
        codigogrupomotivo_in   IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
        codigomotivo_in        IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE,
        tipodocumento_in       IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
        numerodocumento_in     IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
        inafectastock_in       IN LGTC_RECEPCION_PRODUCTO.IN_AFECTA_STOCK%TYPE
        ) IS
  codigoproducto         LGTD_RECEPCION_PRODUCTO.CO_PRODUCTO%TYPE;
  cantenviadaentera      LGTD_RECEPCION_PRODUCTO.CA_ENVIADA_ENTERA%TYPE;
  cantenviadafraccion    LGTD_RECEPCION_PRODUCTO.CA_ENVIADA_FRACCION%TYPE;
  cantconfirmadaentera   LGTD_RECEPCION_PRODUCTO.CA_CONFIRMADA_ENTERA%TYPE;
  cantconfirmadafraccion LGTD_RECEPCION_PRODUCTO.CA_CONFIRMADA_FRACCION%TYPE;
  cantdiferenciaentera   LGTD_RECEPCION_PRODUCTO.CA_DIFERENCIA_ENTERA%TYPE;
  cantdiferenciafraccion LGTD_RECEPCION_PRODUCTO.CA_DIFERENCIA_FRACCION%TYPE;
  numeroPagina           LGTC_GUIA_RECEPCION.NU_PAGINA_GUIA%TYPE;
  inprodfraccionado      LGTR_PRODUCTO_LOCAL.IN_PROD_FRACCIONADO%TYPE;
  valorfraccion          LGTR_PRODUCTO_LOCAL.VA_FRACCION%TYPE;
  cantmovimiento         LGTV_KARDEX.CA_MOVIMIENTO%TYPE;
    CURSOR detalleGuia IS
         SELECT DET_RECEPCION.CO_PRODUCTO,
       DET_RECEPCION.CA_ENVIADA_ENTERA,
       DET_RECEPCION.CA_ENVIADA_FRACCION,
       DET_RECEPCION.CA_CONFIRMADA_ENTERA,
       DET_RECEPCION.CA_CONFIRMADA_FRACCION,
       DET_RECEPCION.CA_DIFERENCIA_ENTERA,
       DET_RECEPCION.CA_DIFERENCIA_FRACCION,
          PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
       PRODUCTO_LOCAL.VA_FRACCION
     FROM LGTD_RECEPCION_PRODUCTO DET_RECEPCION,
          LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL
    WHERE DET_RECEPCION.CO_COMPANIA =codigocompania_in  AND
          DET_RECEPCION.CO_LOCAL = codigolocal_in AND
       DET_RECEPCION.NU_RECEPCION_PRODUCTO = numerorecepcion_in AND
       DET_RECEPCION.IN_AFECTA_PRODUCTO IS NULL AND
       DET_RECEPCION.CO_COMPANIA = PRODUCTO_LOCAL.CO_COMPANIA  AND
          DET_RECEPCION.CO_LOCAL = PRODUCTO_LOCAL.CO_LOCAL AND
       DET_RECEPCION.CO_PRODUCTO = PRODUCTO_LOCAL.CO_PRODUCTO;
  BEGIN
     FOR detalleGuia_rec IN detalleGuia
    LOOP
       codigoproducto := detalleGuia_rec.CO_PRODUCTO;
     cantenviadaentera := NVL(detalleGuia_rec.CA_ENVIADA_ENTERA, 0);
     cantenviadafraccion := NVL(detalleGuia_rec.CA_ENVIADA_FRACCION, 0);
     cantconfirmadaentera := NVL(detalleGuia_rec.CA_CONFIRMADA_ENTERA,0);
     cantconfirmadafraccion := NVL(detalleGuia_rec.CA_CONFIRMADA_FRACCION,0);
     cantdiferenciaentera := NVL(detalleGuia_rec.CA_DIFERENCIA_ENTERA, 0);
     cantdiferenciafraccion := NVL(detalleGuia_rec.CA_DIFERENCIA_FRACCION, 0);
     inprodfraccionado := detalleGuia_rec.IN_PROD_FRACCIONADO;
     valorfraccion := detalleGuia_rec.VA_FRACCION;
     IF cantdiferenciaentera <> 0 OR cantdiferenciafraccion <> 0 THEN
       -- 1er REGISTRO
       --afectando cantidad enviada
      IF (inprodfraccionado = 'S') THEN
         cantmovimiento := (cantenviadaentera * valorfraccion) + cantenviadafraccion;
      ELSE
         cantmovimiento := cantenviadaentera;
      END IF;
      --graba kardex y afecta stock
      IF (inafectastock_in = 'S') THEN
         INV_GRABA_KARDEX(codigocompania_in, codigolocal_in, codigoproducto, cantmovimiento,
                        tipodocumento_in, numerodocumento_in, 'Guia Matriz', codigogrupomotivo_in,
               codigomotivo_in, idmodrecepguia_in);
      END IF;
     -- 2do REGISTRO
       --afectando diferencia
      IF (inprodfraccionado = 'S') THEN
         cantmovimiento := (cantdiferenciaentera * valorfraccion) + cantdiferenciafraccion;
      ELSE
         cantmovimiento := cantdiferenciaentera;
      END IF;
      --graba kardex y afecta stock
      IF (inafectastock_in = 'S') THEN
         INV_GRABA_KARDEX(codigocompania_in, codigolocal_in, codigoproducto, cantmovimiento,
                        tipodocumento_in, numerodocumento_in, 'Guia Matriz', codigogrupomotivo_in,
               COD_MOT_GUIA_MODERN, idmodrecepguia_in);
      END IF;
     ELSE
     --afecta los productos que no se han cambiado la cantidad de recepcisn
      IF (inprodfraccionado = 'S') THEN
         cantmovimiento := (cantconfirmadaentera * valorfraccion) + cantconfirmadafraccion;
      ELSE
         cantmovimiento := cantconfirmadaentera;
      END IF;
      --graba Kardex y afecta stock
      IF (inafectastock_in = 'S') THEN
         INV_GRABA_KARDEX(codigocompania_in, codigolocal_in, codigoproducto, cantmovimiento,
                        tipodocumento_in, numerodocumento_in, 'Guia Matriz', codigogrupomotivo_in,
               codigomotivo_in, idmodrecepguia_in);
      END IF;
     END IF;

     --Actualiza el detalle de la Guia
     UPDATE LGTD_RECEPCION_PRODUCTO
        SET IN_AFECTA_PRODUCTO = 'S',
         ID_MOD_DET_RECEPCION_PRODUCTO = idmodrecepguia_in,
      FE_MOD_DET_RECEPCION_PRODUCTO = SYSDATE,
    IN_REPLICA = 0
      WHERE CO_COMPANIA = codigocompania_in  AND
            CO_LOCAL = codigolocal_in AND
      NU_RECEPCION_PRODUCTO = numerorecepcion_in AND
      CO_PRODUCTO = codigoproducto;
   END LOOP;
   UPDATE LGTC_RECEPCION_PRODUCTO
       SET ES_RECEPCION_PRODUCTO = 'C',
      FE_CIERRE_RECEPCION = SYSDATE,
    FE_MOD_RECEPCION_PRODUCTO = SYSDATE,
                         IN_REPLICA = 0
     WHERE CO_COMPANIA = codigocompania_in  AND
           CO_LOCAL = codigolocal_in AND
     NU_RECEPCION_PRODUCTO = numerorecepcion_in;
   COMMIT;
  END;

  /* ************************************************************************ */
  FUNCTION INV_DETALLE_KARDEX(codigocompania_in  IN LGTV_KARDEX.CO_COMPANIA%TYPE,
              codigolocal_in     IN LGTV_KARDEX.CO_LOCAL%TYPE,
         codigoproducto_in  IN LGTV_KARDEX.CO_PRODUCTO%TYPE,
         fechainicial_in    IN CHAR,
         fechafin_in        IN CHAR)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
       OPEN eckcur FOR
              SELECT  NVL(TO_CHAR(KARDEX.FE_KARDEX, 'dd/MM/yyyy HH24:MI'),' ') || 'Ã' ||
          NVL(MOTIVO.DE_MOTIVO,' ') || 'Ã' ||
          --NVL(KARDEX.CO_MOTIVO_AJUSTE,' ') || 'Ã' ||
             NVL(KARDEX.NU_DOCUMENTO, ' ') || 'Ã' ||
          TO_CHAR(NVL(DECODE(KARDEX.IN_PROD_FRACCIONADO,
              'S',
              TRUNC(KARDEX.CA_STOCK_INICIAL/DECODE(KARDEX.VA_FRACCION_INICIAL,NULL,1,0,1,KARDEX.VA_FRACCION_INICIAL)),
              KARDEX.CA_STOCK_INICIAL)
           ,0),
          '999,999,990') || 'Ã' ||
          TO_CHAR(NVL(DECODE(KARDEX.IN_PROD_FRACCIONADO,
                'S',
              MOD(KARDEX.CA_STOCK_INICIAL, KARDEX.VA_FRACCION_INICIAL),
              0)
           , 0),
            '999,990') || 'Ã' ||
          TO_CHAR(NVL(DECODE(KARDEX.IN_PROD_FRACCIONADO,
              'S',
              TRUNC(KARDEX.CA_MOVIMIENTO/DECODE(KARDEX.VA_FRACCION_INICIAL,NULL,1,0,1,KARDEX.VA_FRACCION_INICIAL)),
              KARDEX.CA_MOVIMIENTO)
           ,0),
          '999,999,990') || 'Ã' ||
          TO_CHAR(NVL(DECODE(KARDEX.IN_PROD_FRACCIONADO,
                'S',
              MOD(KARDEX.CA_MOVIMIENTO, KARDEX.VA_FRACCION_INICIAL),
              0)
           , 0),
            '999,990') || 'Ã' ||
          TO_CHAR(NVL(DECODE(KARDEX.IN_PROD_FRACCIONADO,
              'S',
              TRUNC(KARDEX.CA_STOCK_FINAL/DECODE(KARDEX.VA_FRACCION_FINAL,NULL,1,0,1,KARDEX.VA_FRACCION_FINAL)),
              KARDEX.CA_STOCK_FINAL)
           ,0),
          '999,999,990') || 'Ã' ||
          TO_CHAR(NVL(DECODE(KARDEX.IN_PROD_FRACCIONADO,
                'S',
              MOD(KARDEX.CA_STOCK_FINAL, KARDEX.VA_FRACCION_FINAL),
              0)
           , 0),
            '999,990') || 'Ã' ||
          NVL(KARDEX.VA_FRACCION_INICIAL,0) || 'Ã' ||
          NVL(KARDEX.VA_FRACCION_FINAL,0) || 'Ã' ||
          NVL(KARDEX.DE_GLOSA,' ') || 'Ã' ||
          NVL(KARDEX.ID_CREA_KARDEX,' ')
      FROM   LGTV_KARDEX KARDEX,
             CMTR_MOTIVO MOTIVO,
             LGTR_TIPO_DOCUMENTO TIPO_DOC_LG/*,
          LGTR_PRODUCTO_LOCAL PRO_LOC*/
      WHERE  KARDEX.CO_COMPANIA = codigocompania_in AND
           KARDEX.CO_LOCAL = codigolocal_in AND
          KARDEX.CO_PRODUCTO = codigoproducto_in AND
          KARDEX.FE_KARDEX BETWEEN TO_DATE(fechainicial_in || ' 00:00:00', 'dd/MM/yyyy HH24:MI:SS') AND
                 TO_DATE(fechafin_in     || ' 23:59:59', 'dd/MM/yyyy HH24:MI:SS')  AND
             KARDEX.CO_GRUPO_MOTIVO= MOTIVO.CO_GRUPO_MOTIVO AND
             KARDEX.CO_MOTIVO_KARDEX = MOTIVO.CO_MOTIVO AND
             KARDEX.CO_COMPANIA = TIPO_DOC_LG.CO_COMPANIA(+) AND
             KARDEX.TI_DOCUMENTO = TIPO_DOC_LG.TI_DOCUMENTO(+) /*AND
          KARDEX.CO_COMPANIA = PRO_LOC.CO_COMPANIA AND
          KARDEX.CO_LOCAL = PRO_LOC.CO_LOCAL AND
             KARDEX.CO_PRODUCTO = PRO_LOC.CO_PRODUCTO
          */
      ORDER BY KARDEX.FE_KARDEX ;
    RETURN eckcur;
  END;

  /* ************************************************************************ */
  FUNCTION UNIDAD_FRAC_PRODUCTO(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
            codigolocal_in           IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
                         codigoproducto_in        IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
           RETURN LGTR_PRODUCTO_LOCAL.DE_UNIDAD_FRACCION%TYPE IS
    l_unidad_frac LGTR_PRODUCTO_LOCAL.DE_UNIDAD_FRACCION%TYPE;
  BEGIN
 SELECT NVL(DE_UNIDAD_FRACCION,' ')
   INTO l_unidad_frac
   FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL
  WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
    AND PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
    AND PRODUCTO_LOCAL.CO_PRODUCTO = codigoproducto_in;
 RETURN l_unidad_frac;
 EXCEPTION
   WHEN NO_DATA_FOUND THEN
  RETURN 'No determinado';
  END;

  /* ************************************************************************ */
  FUNCTION INDICADOR_PROD_FRACCIONADO(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
            codigolocal_in           IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
                         codigoproducto_in        IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
           RETURN LGTR_PRODUCTO_LOCAL.IN_PROD_FRACCIONADO%TYPE IS
    l_inprodfraccionado LGTR_PRODUCTO_LOCAL.IN_PROD_FRACCIONADO%TYPE;
  BEGIN
 SELECT NVL(IN_PROD_FRACCIONADO,' ')
   INTO l_inprodfraccionado
   FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL
  WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
    AND PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
    AND PRODUCTO_LOCAL.CO_PRODUCTO = codigoproducto_in;
 RETURN l_inprodfraccionado;
 EXCEPTION
   WHEN NO_DATA_FOUND THEN
  RETURN 'No determinado';
  END;

  /* ************************************************************************ */
  FUNCTION VALOR_FRACCION(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
            codigolocal_in           IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
                         codigoproducto_in        IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
           RETURN LGTR_PRODUCTO_LOCAL.VA_FRACCION%TYPE IS
    l_valorfraccion LGTR_PRODUCTO_LOCAL.VA_FRACCION%TYPE;
  BEGIN
 SELECT NVL(VA_FRACCION,0)
   INTO l_valorfraccion
   FROM LGTR_PRODUCTO_LOCAL
  WHERE CO_COMPANIA = codigocompania_in
    AND CO_LOCAL = codigolocal_in
    AND CO_PRODUCTO = codigoproducto_in;
 RETURN l_valorfraccion;
 EXCEPTION
   WHEN NO_DATA_FOUND THEN
  RETURN 'No determinado';
  END;

  /* ************************************************************************ */
  -- Devuelve indicador si afecta stock a guia seleccionada
  FUNCTION INDICADOR_AFECTA_STOCK_GUIA(codigocompania_in IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
            codigolocal_in     IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
                         numerorecepcion_in IN LGTC_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE)
           RETURN LGTC_RECEPCION_PRODUCTO.IN_AFECTA_STOCK%TYPE IS
    l_inafectastock  LGTC_RECEPCION_PRODUCTO.IN_AFECTA_STOCK%TYPE;
  BEGIN
 SELECT NVL(IN_AFECTA_STOCK,' ')
   INTO l_inafectastock
   FROM LGTC_RECEPCION_PRODUCTO
  WHERE CO_COMPANIA = codigocompania_in
    AND CO_LOCAL = codigolocal_in
    AND NU_RECEPCION_PRODUCTO = numerorecepcion_in;
 RETURN l_inafectastock;
 EXCEPTION
   WHEN NO_DATA_FOUND THEN
  RETURN 'No determinado';
  END;

  /* ************************************************************************ */
  FUNCTION INV_AJUSTE_INV(codigocompania_in   IN LGTV_KARDEX.CO_COMPANIA%TYPE,
       codigolocal_in        IN LGTV_KARDEX.CO_LOCAL%TYPE,
       codigoproducto_in        IN LGTV_KARDEX.CO_PRODUCTO%TYPE,
       cantunidadajuste_in      IN INTEGER,
       cantfraccionajuste_in    IN INTEGER,
       tipodocumento_in         IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
       numerodocumento_in       IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
       descglosa_in             IN LGTV_KARDEX.DE_GLOSA%TYPE,
       codigogrupomotivokardex_in IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
       codigomotivokardex_in  IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE,
       codigogrupomotivo_in       IN LGTV_KARDEX.CO_GRUPO_MOTIVO_AJUSTE%TYPE,
       codigomotivo_in          IN LGTV_KARDEX.CO_MOTIVO_AJUSTE%TYPE,
       idcreakardex_in          IN LGTV_KARDEX.ID_CREA_KARDEX%TYPE)
  RETURN LGTV_KARDEX.CA_MOVIMIENTO%TYPE IS
    l_valorfraccion     LGTR_PRODUCTO_LOCAL.VA_FRACCION%TYPE;
    l_stockdisponible   LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;
    l_inprodfraccionado LGTR_PRODUCTO_LOCAL.IN_PROD_FRACCIONADO%TYPE;
    l_cantmovimiento    LGTV_KARDEX.CA_MOVIMIENTO%TYPE;
  BEGIN
    SELECT CA_STOCK_DISPONIBLE, IN_PROD_FRACCIONADO, VA_FRACCION
      INTO l_stockdisponible, l_inprodfraccionado, l_valorfraccion
      FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL
     WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
       AND PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
       AND PRODUCTO_LOCAL.CO_PRODUCTO = codigoproducto_in;

    -- calcula la cantidad de movimiento
    IF (l_inprodfraccionado = 'S') THEN
        l_cantmovimiento := ((cantunidadajuste_in * l_valorfraccion) + cantfraccionajuste_in) - l_stockdisponible;
    ELSE
        l_cantmovimiento := cantunidadajuste_in - l_stockdisponible;
    END IF;

    -- graba registro en Kardex
    IF (l_cantmovimiento <> 0 ) THEN
        INV_GRABA_MOTIVO_KARDEX(codigocompania_in, codigolocal_in, codigoproducto_in,l_cantmovimiento,
                            tipodocumento_in, numerodocumento_in, descglosa_in, codigogrupomotivokardex_in,
                            codigomotivokardex_in, codigogrupomotivo_in, codigomotivo_in, idcreakardex_in);
    END IF;

    RETURN l_cantmovimiento;
  END;

  /* ************************************************************************ */
  PROCEDURE INV_GRABA_MOTIVO_KARDEX(codigocompania_in   IN LGTV_KARDEX.CO_COMPANIA%TYPE,
       codigolocal_in      IN LGTV_KARDEX.CO_LOCAL%TYPE,
       codigoproducto_in      IN LGTV_KARDEX.CO_PRODUCTO%TYPE,
       cantmovimiento_in      IN LGTV_KARDEX.CA_MOVIMIENTO%TYPE,
       tipodocumento_in       IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
       numerodocumento_in     IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
       descglosa_in           IN LGTV_KARDEX.DE_GLOSA%TYPE,
       codigogrupomotivokardex_in IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
       codigomotivokardex_in  IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE,
       codigogrupomotivo_in   IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
       codigomotivo_in        IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE,
       idcreakardex_in        IN LGTV_KARDEX.ID_CREA_KARDEX%TYPE
       ) IS
    l_numeracion        CMTR_NUMERACION.NU_SEC_NUMERACION%TYPE;
    l_inprodfraccionado LGTR_PRODUCTO_LOCAL.IN_PROD_FRACCIONADO%TYPE;
    l_codigounidadventa LGTR_PRODUCTO_LOCAL.CO_UNIDAD_VENTA_FRACCION%TYPE;
    l_stockdisponible   LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;
    l_valorfraccion     LGTR_PRODUCTO_LOCAL.VA_FRACCION%TYPE;
    l_preciopromedio    LGTM_PRODUCTO.VA_PRECIO_PROMEDIO%TYPE;

    /* Inicio ID=002 */
    vlVaTotalInventario_ant LGTR_PRODUCTO_LOCAL.VA_TOTAL_INVENTARIO%TYPE;
    vlVaCostoProducto LGTR_PRODUCTO_LOCAL.VA_COSTO_PRODUCTO%TYPE;
    vlCaStockDisponible_ant LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;


    vlVaTotalInventario_act LGTR_PRODUCTO_LOCAL.VA_TOTAL_INVENTARIO%TYPE;
    vlCaMovimiento_act LGTV_KARDEX.CA_MOVIMIENTO%TYPE;
    vlVaCostoPromedio LGTV_KARDEX.VA_COSTO_PROMEDIO%TYPE;
    vlCaStockDisponible_act LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;
    /* Fin ID=002 */
  BEGIN
    l_numeracion := Ptovta_Utility.OBTENER_NUMERACION(codigocompania_in,codigolocal_in,'007');

    SELECT PRODUCTO_LOCAL.IN_PROD_FRACCIONADO, DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO, 'S',
           PRODUCTO_LOCAL.CO_UNIDAD_VENTA_FRACCION, PRODUCTO.CO_UNIDAD_VENTA), PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE,
           PRODUCTO_LOCAL.VA_FRACCION, PRODUCTO.VA_PRECIO_PROMEDIO, PRODUCTO_LOCAL.VA_COSTO_PRODUCTO, PRODUCTO_LOCAL.VA_TOTAL_INVENTARIO
      INTO l_inprodfraccionado, l_codigounidadventa, l_stockdisponible, l_valorfraccion, l_preciopromedio,
           vlVaCostoProducto, vlVaTotalInventario_ant
      FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL,
           LGTM_PRODUCTO PRODUCTO
     WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
       AND PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
       AND PRODUCTO_LOCAL.CO_PRODUCTO = codigoproducto_in
       AND PRODUCTO_LOCAL.CO_COMPANIA = PRODUCTO.CO_COMPANIA
       AND PRODUCTO_LOCAL.CO_PRODUCTO = PRODUCTO.CO_PRODUCTO FOR UPDATE;

    vlCaMovimiento_act:= cantmovimiento_in;
    vlCaStockDisponible_ant  := l_stockdisponible;
    vlCaStockDisponible_act := l_stockdisponible + cantmovimiento_in;

    vlVaTotalInventario_act := ECKERD_COSTEO.GET_TOTAL_IVENTARIO_KARDEX( vlCaStockDisponible_ant,vlCaMovimiento_act,
                                       vlVaCostoProducto,
                                       vlVaTotalInventario_ant,
                                       vlCaStockDisponible_act
                                    );
    vlVaCostoPromedio := ECKERD_COSTEO.GET_VA_COSTO_KARDEX( vlCaMovimiento_act,
                                       vlVaCostoProducto ,
                                       vlVaTotalInventario_act,
                                       vlCaStockDisponible_act
                                    );

    INSERT INTO LGTV_KARDEX
            (CO_COMPANIA, CO_LOCAL, CO_PRODUCTO, NU_REVISION_PRODUCTO,
             NU_SEC_KARDEX, TI_DOCUMENTO, NU_DOCUMENTO, IN_PROD_FRACCIONADO,
             CA_STOCK_INICIAL, CO_UNIDAD_VENTA_INICIAL, VA_FRACCION_INICIAL, CA_MOVIMIENTO,
             CO_UNIDAD_VENTA_FINAL, CA_STOCK_FINAL, VA_FRACCION_FINAL, FE_KARDEX,
             CO_GRUPO_MOTIVO, CO_MOTIVO_KARDEX, CO_GRUPO_MOTIVO_AJUSTE, CO_MOTIVO_AJUSTE,
             VA_PRECIO_PROMEDIO, DE_GLOSA, ES_KARDEX, ID_CREA_KARDEX, FE_CREA_KARDEX, VA_COSTO_PRODUCTO,
             VA_TOTAL_INVENTARIO, VA_COSTO_PROMEDIO)
     VALUES (codigocompania_in, codigolocal_in, codigoproducto_in, '0',
            l_numeracion, tipodocumento_in, numerodocumento_in,  l_inprodfraccionado,
            l_stockdisponible, l_codigounidadventa, l_valorfraccion, cantmovimiento_in,
            l_codigounidadventa, (l_stockdisponible + cantmovimiento_in), l_valorfraccion, SYSDATE,
            codigogrupomotivokardex_in, codigomotivokardex_in, codigogrupomotivo_in, codigomotivo_in,
            l_preciopromedio, descglosa_in, 'A', idcreakardex_in, SYSDATE, vlVaCostoProducto,
            vlVaTotalInventario_act, vlVaCostoPromedio);

    UPDATE LGTR_PRODUCTO_LOCAL
       SET CA_STOCK_DISPONIBLE = l_stockdisponible + cantmovimiento_in,
           VA_TOTAL_INVENTARIO = vlVaTotalInventario_act,
           VA_COSTO_PRODUCTO =  vlVaCostoPromedio,
           FE_MOD_PROD_LOCAL = SYSDATE,
           IN_REPLICA = 0
     WHERE CO_COMPANIA = codigocompania_in
       AND CO_LOCAL = codigolocal_in
       AND CO_PRODUCTO = codigoproducto_in;

    Ptovta_Utility.ACTUALIZAR_NUMERACION(codigocompania_in,codigolocal_in,'007',idcreakardex_in);

    COMMIT;
  END;

  /* ************************************************************************ */
  PROCEDURE INV_GRABA_KARDEX_STOCK_COM(codigocompania_in   IN LGTV_KARDEX.CO_COMPANIA%TYPE,
       codigolocal_in    IN LGTV_KARDEX.CO_LOCAL%TYPE,
       codigoproducto_in    IN LGTV_KARDEX.CO_PRODUCTO%TYPE,
       cantmovimiento_in    IN LGTV_KARDEX.CA_MOVIMIENTO%TYPE,
       tipodocumento_in     IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
       numerodocumento_in   IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
       descglosa_in         IN LGTV_KARDEX.DE_GLOSA%TYPE,
       codigogrupomotivo_in IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
       codigomotivo_in      IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE,
       idcreakardex_in      IN LGTV_KARDEX.ID_CREA_KARDEX%TYPE
       ) IS
    l_numeracion        CMTR_NUMERACION.NU_SEC_NUMERACION%TYPE;
    l_inprodfraccionado LGTR_PRODUCTO_LOCAL.IN_PROD_FRACCIONADO%TYPE;
    l_codigounidadventa LGTR_PRODUCTO_LOCAL.CO_UNIDAD_VENTA_FRACCION%TYPE;
    l_stockdisponible   LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;
 l_stockcomprometido LGTR_PRODUCTO_LOCAL.CA_STOCK_COMPROMETIDO%TYPE;
 l_valorfraccion     LGTR_PRODUCTO_LOCAL.VA_FRACCION%TYPE;
 l_preciopromedio    LGTM_PRODUCTO.VA_PRECIO_PROMEDIO%TYPE;
  BEGIN
    l_numeracion := Ptovta_Utility.OBTENER_NUMERACION(codigocompania_in,codigolocal_in,'007');
 --
 SELECT PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
     DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO, 'S',
         PRODUCTO_LOCAL.CO_UNIDAD_VENTA_FRACCION,
      PRODUCTO.CO_UNIDAD_VENTA),
     PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE,
     PRODUCTO_LOCAL.CA_STOCK_COMPROMETIDO,
     PRODUCTO_LOCAL.VA_FRACCION,
     PRODUCTO.VA_PRECIO_PROMEDIO
   INTO l_inprodfraccionado, l_codigounidadventa, l_stockdisponible, l_stockcomprometido, l_valorfraccion, l_preciopromedio
   FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL,
       LGTM_PRODUCTO PRODUCTO
  WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
    AND PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
    AND PRODUCTO_LOCAL.CO_PRODUCTO = codigoproducto_in
    AND PRODUCTO_LOCAL.CO_COMPANIA = PRODUCTO.CO_COMPANIA
    AND PRODUCTO_LOCAL.CO_PRODUCTO = PRODUCTO.CO_PRODUCTO FOR UPDATE;

    INSERT INTO LGTV_KARDEX (CO_COMPANIA, CO_LOCAL, CO_PRODUCTO, NU_REVISION_PRODUCTO,
           NU_SEC_KARDEX, TI_DOCUMENTO, NU_DOCUMENTO, IN_PROD_FRACCIONADO,
        CA_STOCK_INICIAL, CO_UNIDAD_VENTA_INICIAL, VA_FRACCION_INICIAL, CA_MOVIMIENTO,
        CO_UNIDAD_VENTA_FINAL, CA_STOCK_FINAL, VA_FRACCION_FINAL, FE_KARDEX,
        CO_GRUPO_MOTIVO, CO_MOTIVO_KARDEX, VA_PRECIO_PROMEDIO, DE_GLOSA,
        ES_KARDEX, ID_CREA_KARDEX, FE_CREA_KARDEX)
          VALUES (codigocompania_in, codigolocal_in, codigoproducto_in, '0',
          l_numeracion, tipodocumento_in, numerodocumento_in, l_inprodfraccionado,
       l_stockdisponible, l_codigounidadventa, l_valorfraccion, cantmovimiento_in,
       l_codigounidadventa, (l_stockdisponible + cantmovimiento_in), l_valorfraccion, SYSDATE,
       codigogrupomotivo_in, codigomotivo_in, l_preciopromedio, descglosa_in,
       'A', idcreakardex_in, SYSDATE);

 UPDATE LGTR_PRODUCTO_LOCAL
    SET CA_STOCK_DISPONIBLE =  l_stockdisponible + cantmovimiento_in,
        CA_STOCK_COMPROMETIDO =  l_stockcomprometido + cantmovimiento_in,
        FE_MOD_PROD_LOCAL = SYSDATE,
               IN_REPLICA = 0
  WHERE CO_COMPANIA = codigocompania_in
    AND CO_LOCAL = codigolocal_in
    AND CO_PRODUCTO = codigoproducto_in;
    Ptovta_Utility.ACTUALIZAR_NUMERACION_NOCOMMIT(codigocompania_in,codigolocal_in,'007',idcreakardex_in);
    --COMMIT;
  END;

  /* ************************************************************************ */
  FUNCTION INV_GRABA_TRANSF_PROD(codigocompania_in     IN LGTM_TRANSF_PRODUCTO.CO_COMPANIA%TYPE,
                                codigolocal_in         IN LGTM_TRANSF_PRODUCTO.CO_LOCAL%TYPE,
        localdestino_in        IN LGTM_TRANSF_PRODUCTO.CO_LOCAL_DESTINO%TYPE,
        idcreatransf_in        IN LGTM_TRANSF_PRODUCTO.ID_CREA_TRANSF_PRODUCTO%TYPE,
        cantidaditems_in       IN LGTM_TRANSF_PRODUCTO.CA_PROD_TRANSFERIDO%TYPE,
        grupomotivo_in         IN LGTM_TRANSF_PRODUCTO.CO_GRUPO_MOTIVO%TYPE,
        codigomotivo_in        IN LGTM_TRANSF_PRODUCTO.CO_MOTIVO_TRANSFERENCIA%TYPE,
        empresadestino_in      IN LGTM_TRANSF_PRODUCTO.NO_EMPRESA_DESTINO%TYPE,
        direccionempresa_in    IN LGTM_TRANSF_PRODUCTO.DE_DIRECCION_EMP_DESTINO%TYPE,
        nombretransportista_in IN LGTM_TRANSF_PRODUCTO.NO_TRANSPORTISTA%TYPE,
        directransportista_in  IN LGTM_TRANSF_PRODUCTO.DE_DIRECCION_TRANS%TYPE,
        tipdoctransp_in        IN LGTM_TRANSF_PRODUCTO.TI_DOC_IDENTIDAD_TRANS%TYPE,
        numdoctransp_in        IN LGTM_TRANSF_PRODUCTO.NU_DOC_IDENTIDAD_TRANS%TYPE,
        numeroplaca_in      IN LGTM_TRANSF_PRODUCTO.NU_PLACA%TYPE,
        codigoproveedor_in     IN LGTM_TRANSF_PRODUCTO.CO_PROVEEDOR_TRANSFERENCIA%TYPE DEFAULT NULL)
           RETURN LGTM_TRANSF_PRODUCTO.NU_SEC_TRANSFERENCIA%TYPE IS
    l_numeracion CMTR_NUMERACION.NU_SEC_NUMERACION%TYPE;
 l_numero_transf LGTM_TRANSF_PRODUCTO.NU_SEC_TRANSFERENCIA%TYPE;
  BEGIN
    l_numeracion := Ptovta_Utility.OBTENER_NUMERACION(codigocompania_in, codigolocal_in, '006');
 l_numero_transf := Eckerd_Utility.COMPLETAR_CON_SIMBOLO(l_numeracion, 8, '0', 'I');
 IF (l_numeracion > 0) THEN
      INSERT INTO LGTM_TRANSF_PRODUCTO
         (CO_COMPANIA, CO_LOCAL, NU_SEC_TRANSFERENCIA, CO_LOCAL_DESTINO,
       CA_PROD_TRANSFERIDO, CO_GRUPO_MOTIVO, CO_MOTIVO_TRANSFERENCIA, FE_EMISION_TRANSFERENCIA,
                   ES_TRANSF_PRODUCTO, ID_CREA_TRANSF_PRODUCTO, FE_CREA_TRANSF_PRODUCTO, NO_EMPRESA_DESTINO,
       DE_DIRECCION_EMP_DESTINO, NO_TRANSPORTISTA, DE_DIRECCION_TRANS, TI_DOC_IDENTIDAD_TRANS,
       NU_DOC_IDENTIDAD_TRANS, NU_PLACA, CO_PROVEEDOR_TRANSFERENCIA)
           VALUES (codigocompania_in, codigolocal_in, l_numero_transf, localdestino_in,
       cantidaditems_in, grupomotivo_in, codigomotivo_in, SYSDATE,
             'P', idcreatransf_in, SYSDATE, empresadestino_in,
       direccionempresa_in, nombretransportista_in, directransportista_in, tipdoctransp_in,
       numdoctransp_in, numeroplaca_in, codigoproveedor_in);
      Ptovta_Utility.ACTUALIZAR_NUMERACION_NOCOMMIT(codigocompania_in, codigolocal_in, '006', idcreatransf_in);
    END IF;
    RETURN l_numero_transf;
  END;

  /* ************************************************************************ */
  PROCEDURE INV_GRABA_DET_TRANSF_PROD(codigocompania_in IN LGTD_GUIA_TRANSFERENCIA.CO_COMPANIA%TYPE,
                                 codigolocal_in          IN LGTD_GUIA_TRANSFERENCIA.CO_LOCAL%TYPE,
         numerotransferencia_in IN LGTD_GUIA_TRANSFERENCIA.NU_SEC_TRANSFERENCIA%TYPE,
         numeroitem_in           IN LGTD_GUIA_TRANSFERENCIA.NU_ITEM_TRANSFERENCIA%TYPE,
         codigoproducto_in       IN LGTD_GUIA_TRANSFERENCIA.CO_PRODUCTO%TYPE,
         unidadproducto_in       IN LGTD_GUIA_TRANSFERENCIA.DE_UNIDAD_PRODUCTO%TYPE,
         unidadfraccion_in       IN LGTD_GUIA_TRANSFERENCIA.DE_UNIDAD_FRACCION%TYPE,
         cantidad_in             IN LGTD_GUIA_TRANSFERENCIA.CA_TRANSFERIR_ENTERA%TYPE,
         cantidadfrac_in         IN LGTD_GUIA_TRANSFERENCIA.CA_TRANSFERIR_FRACCION%TYPE,
         fechavenc_in            IN CHAR,
         idcreatransf_in         IN LGTD_GUIA_TRANSFERENCIA.ID_CREA_DET_GUIA_TRANSFERENCIA%TYPE
        ) IS
 fechavenc         LGTD_GUIA_TRANSFERENCIA.FE_VENCE_PRODUCTO%TYPE;
 inprodfraccionado LGTR_PRODUCTO_LOCAL.IN_PROD_FRACCIONADO%TYPE;
 valorfraccion     LGTR_PRODUCTO_LOCAL.VA_FRACCION%TYPE;
 valorpreciocompra LGTM_PRODUCTO.VA_PRECIO_COMPRA%TYPE;   -- modificacion 20041009
 l_valorpreciocom  LGTM_PRODUCTO.VA_PRECIO_COMPRA%TYPE;   -- modificacion 20041009

  BEGIN
  -- modificacion 20041009
   SELECT PROD_LOCAL.IN_PROD_FRACCIONADO IN_PROD_FRACCIONADO,
     PROD_LOCAL.VA_FRACCION VA_FRACCION,
     MAE_PROD.VA_PRECIO_COMPRA VA_PRECIO_COMPRA
   INTO inprodfraccionado,
       valorfraccion,
     valorpreciocompra
   FROM LGTR_PRODUCTO_LOCAL PROD_LOCAL,
       LGTM_PRODUCTO MAE_PROD
  WHERE PROD_LOCAL.CO_COMPANIA = codigocompania_in AND
        PROD_LOCAL.CO_LOCAL = codigolocal_in AND
     PROD_LOCAL.CO_PRODUCTO = codigoproducto_in AND
     PROD_LOCAL.CO_COMPANIA = MAE_PROD.CO_COMPANIA AND
     PROD_LOCAL.CO_PRODUCTO = MAE_PROD.CO_PRODUCTO;
    IF (fechavenc_in IS NOT NULL) THEN
       fechavenc := TO_DATE(fechavenc_in,'MM/yyyy');
 ELSE
    fechavenc := NULL;
 END IF;

 IF inprodfraccionado = 'S' THEN
    IF NVL(valorfraccion, 0) = 0 THEN
       valorfraccion:=1;
    END IF;
    l_valorpreciocom := NVL(valorpreciocompra, 0)*(NVL(cantidad_in, 0) + NVL(cantidadfrac_in, 0)/valorfraccion);
 ELSE
    l_valorpreciocom := NVL(valorpreciocompra, 0)*NVL(cantidad_in, 0);
 END IF;

    INSERT INTO LGTD_GUIA_TRANSFERENCIA
       (CO_COMPANIA, CO_LOCAL, NU_SEC_TRANSFERENCIA, NU_ITEM_TRANSFERENCIA,
                 CO_PRODUCTO, NU_REVISION_PRODUCTO, DE_UNIDAD_PRODUCTO, DE_UNIDAD_FRACCION, VA_PRECIO_COMPRA,
     CA_TRANSFERIR_ENTERA, CA_TRANSFERIR_FRACCION, FE_VENCE_PRODUCTO,IN_PROD_FRACCIONADO,
     VA_FRACCION, ES_DET_GUIA_TRANSFERENCIA, ID_CREA_DET_GUIA_TRANSFERENCIA, FE_CREA_DET_GUIA_TRANSFERENCIA)
         VALUES (codigocompania_in, codigolocal_in, numerotransferencia_in, numeroitem_in,
        codigoproducto_in, '0', unidadproducto_in, unidadfraccion_in, l_valorpreciocom,
        cantidad_in, cantidadfrac_in, fechavenc, inprodfraccionado,
        valorfraccion, 'A', idcreatransf_in, SYSDATE);
  END;

  /* ************************************************************************ */
  FUNCTION INV_GRABA_GUIA_TRANSF(codigocompania_in      IN LGTD_GUIA_TRANSFERENCIA.CO_COMPANIA%TYPE,
                                codigolocal_in          IN LGTD_GUIA_TRANSFERENCIA.CO_LOCAL%TYPE,
        numerotransferencia_in IN LGTD_GUIA_TRANSFERENCIA.NU_SEC_TRANSFERENCIA%TYPE,
        idcreatransf_in         IN LGTD_GUIA_TRANSFERENCIA.ID_CREA_DET_GUIA_TRANSFERENCIA%TYPE,
        cantidaditems_in        IN LGTM_TRANSF_PRODUCTO.CA_PROD_TRANSFERIDO%TYPE,
        tipodocumento_in        IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
        codigogrupomotivo_in    IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
        codigomotivo_in         IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE)
           RETURN LGTC_GUIA_TRANSFERENCIA.NU_GUIA_TRANSFERENCIA%TYPE IS
  codigoProducto        LGTD_GUIA_TRANSFERENCIA.CO_PRODUCTO%TYPE;
  cantransferirentera   LGTD_GUIA_TRANSFERENCIA.CA_TRANSFERIR_ENTERA%TYPE;
  cantransferirfraccion LGTD_GUIA_TRANSFERENCIA.CA_TRANSFERIR_FRACCION%TYPE;
  stockdisponible       LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;
  inprodfraccionado     LGTR_PRODUCTO_LOCAL.IN_PROD_FRACCIONADO%TYPE;
  valorfraccion         LGTR_PRODUCTO_LOCAL.VA_FRACCION%TYPE;
     l_numeracion          CMTR_NUMERACION.NU_SEC_NUMERACION%TYPE;
  l_numeroguiatransf    LGTC_GUIA_TRANSFERENCIA.NU_GUIA_TRANSFERENCIA%TYPE;
  cantidadtransferir    LGTV_KARDEX.CA_MOVIMIENTO%TYPE;
  -- modificacion 20041009
  valorpreciocompra    LGTD_GUIA_TRANSFERENCIA.VA_PRECIO_COMPRA%TYPE;
  totalvalorpreciocom   LGTC_GUIA_TRANSFERENCIA.VA_PRECIO_COMPRA%TYPE;
    CURSOR detalleGuia IS
         SELECT DETALLE.CO_PRODUCTO,
       DETALLE.CA_TRANSFERIR_ENTERA,
       DETALLE.CA_TRANSFERIR_FRACCION,
       PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE,
          PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
       PRODUCTO_LOCAL.VA_FRACCION,
       DETALLE.VA_PRECIO_COMPRA
     FROM LGTD_GUIA_TRANSFERENCIA DETALLE,
          LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL
    WHERE DETALLE.CO_COMPANIA = codigocompania_in  AND
          DETALLE.CO_LOCAL = codigolocal_in AND
       DETALLE.NU_SEC_TRANSFERENCIA = numerotransferencia_in AND
       DETALLE.CO_COMPANIA = PRODUCTO_LOCAL.CO_COMPANIA  AND
          DETALLE.CO_LOCAL = PRODUCTO_LOCAL.CO_LOCAL AND
       DETALLE.CO_PRODUCTO = PRODUCTO_LOCAL.CO_PRODUCTO;
  BEGIN
     totalvalorpreciocom:=0;
   -- Graba Kardex y Afecta Stock
    FOR detalleGuia_rec IN detalleGuia
   LOOP
      codigoproducto := detalleGuia_rec.CO_PRODUCTO;
      stockdisponible := detalleGuia_rec.CA_STOCK_DISPONIBLE;
    cantransferirentera := detalleGuia_rec.CA_TRANSFERIR_ENTERA;
    cantransferirfraccion := detalleGuia_rec.CA_TRANSFERIR_FRACCION;
    inprodfraccionado := detalleGuia_rec.IN_PROD_FRACCIONADO;
    valorfraccion := detalleGuia_rec.VA_FRACCION;
    valorpreciocompra := detalleGuia_rec.VA_PRECIO_COMPRA;
    -- Asigna cantidad a transferir
    IF (inprodfraccionado = 'S') THEN
       cantidadtransferir:= cantransferirentera * valorfraccion + cantransferirfraccion ;
    ELSE
        cantidadtransferir:= cantransferirentera;
    END IF;
    -- modificado 20041009
    -- Acumula el valor total de compra para la cabecera de la guia de transferencia
    totalvalorpreciocom :=  totalvalorpreciocom + valorpreciocompra;
      INV_GRABA_KARDEX_STOCK_COM(codigocompania_in, codigolocal_in, codigoproducto, cantidadtransferir*(-1),
         tipodocumento_in, numerotransferencia_in, 'Guia Matriz', codigogrupomotivo_in,
         codigomotivo_in, idcreatransf_in);
   --COMMIT;
   END LOOP;
   -- Agregar registro de la cabecera de la guia de transeferencia
   l_numeroguiatransf := INV_GRABA_CABECERA_GUIA_TRANSF(codigocompania_in,
                    codigolocal_in,
                    idcreatransf_in,
                 cantidaditems_in,
                 totalvalorpreciocom);

   -- Actualiza el numero de guia en la tabla de detalle
   UPDATE LGTD_GUIA_TRANSFERENCIA
    SET NU_GUIA_TRANSFERENCIA = l_numeroguiatransf,
        FE_MOD_DET_GUIA_TRANSFERENCIA = SYSDATE,
                      IN_REPLICA = 0
  WHERE CO_COMPANIA =codigocompania_in  AND
        CO_LOCAL = codigolocal_in AND
        NU_SEC_TRANSFERENCIA = numerotransferencia_in;
   --COMMIT;

   -- Actualiza el numero de guia en la tabla de detalle
   UPDATE LGTM_TRANSF_PRODUCTO
    SET ES_TRANSF_PRODUCTO = 'I',
        ID_MOD_TRANSF_PRODUCTO = idcreatransf_in,
     FE_MOD_TRANSF_PRODUCTO = SYSDATE,
   IN_REPLICA = 0
  WHERE CO_COMPANIA =codigocompania_in  AND
        CO_LOCAL = codigolocal_in AND
        NU_SEC_TRANSFERENCIA = numerotransferencia_in;
   --COMMIT;
    RETURN l_numeroguiatransf;
  END;

  /* ************************************************************************ */
  FUNCTION INV_GRABA_CABECERA_GUIA_TRANSF(codigocompania_in  IN LGTC_GUIA_TRANSFERENCIA.CO_COMPANIA%TYPE,
                                codigolocal_in      IN LGTC_GUIA_TRANSFERENCIA.CO_LOCAL%TYPE,
        idcreatransf_in     IN LGTC_GUIA_TRANSFERENCIA.ID_CREA_GUIA_TRANSFERENCIA%TYPE,
           cantidaditems_in    IN LGTC_GUIA_TRANSFERENCIA.CA_PRODUCTO%TYPE,
           valorpreciocom_in   IN LGTC_GUIA_TRANSFERENCIA.VA_PRECIO_COMPRA%TYPE)
           RETURN LGTC_GUIA_TRANSFERENCIA.NU_GUIA_TRANSFERENCIA%TYPE IS
     l_numeracion       CMTR_NUMERACION.NU_SEC_NUMERACION%TYPE;
  l_numeroguiatransf LGTC_GUIA_TRANSFERENCIA.NU_GUIA_TRANSFERENCIA%TYPE;
  BEGIN
    l_numeracion := Ptovta_Utility.OBTENER_NUMERACION(codigocompania_in, codigolocal_in, '008');
 l_numeroguiatransf := Eckerd_Utility.COMPLETAR_CON_SIMBOLO(l_numeracion, 10, '0', 'I');
 IF (l_numeracion > 0) THEN
   -- Agrega registro
      INSERT INTO LGTC_GUIA_TRANSFERENCIA
         (CO_COMPANIA, CO_LOCAL, NU_GUIA_TRANSFERENCIA, ID_EMISION_GUIA_TRANSFERENCIA,
       FE_EMISION_GUIA_TRANSFERENCIA, CA_PRODUCTO,ES_GUIA_TRANSFERENCIA, ID_CREA_GUIA_TRANSFERENCIA,
       FE_CREA_GUIA_TRANSFERENCIA, VA_PRECIO_COMPRA)
           VALUES (codigocompania_in, codigolocal_in, l_numeroguiatransf, idcreatransf_in,
        SYSDATE, cantidaditems_in, 'A', idcreatransf_in,
          SYSDATE, valorpreciocom_in);
      Ptovta_Utility.ACTUALIZAR_NUMERACION_NOCOMMIT(codigocompania_in,codigolocal_in,'008',idcreatransf_in);
    END IF;
    RETURN l_numeroguiatransf;
  END;

  /* ************************************************************************ */
  FUNCTION INV_LISTA_TRANSF_PROD(codigocompania_in IN LGTM_TRANSF_PRODUCTO.CO_COMPANIA%TYPE,
              codigolocal_in    IN LGTM_TRANSF_PRODUCTO.CO_LOCAL%TYPE,
         fechainicio_in    IN CHAR,
         fechafin_in    IN CHAR)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
   IF LENGTH(fechainicio_in) > 0 AND LENGTH(fechafin_in) > 0 THEN
    OPEN eckcur FOR SELECT   NVL(TRANSF.NU_SEC_TRANSFERENCIA, ' ') || 'Ã' ||
              NVL(LOCAL_DEST.DE_CORTA_LOCAL, ' ') || 'Ã' ||
                          NVL(TO_CHAR(TRANSF.FE_EMISION_TRANSFERENCIA, 'dd/MM/yyyy HH24:MI:SS'), ' ') || 'Ã' ||
           NVL(SUBSTR(GUIA.NU_GUIA_TRANSFERENCIA, 1, 3), ' ') || '-' || NVL(SUBSTR(GUIA.NU_GUIA_TRANSFERENCIA, 4, 7), ' ') || 'Ã' ||
              NVL(MOTIVO.DE_MOTIVO, ' ') || 'Ã' ||
                             NVL(TRANSF.CA_PROD_TRANSFERIDO, 0)  || 'Ã' ||
                             DECODE(TRANSF.ES_TRANSF_PRODUCTO,
                     ESTADO_TRANSF_PROC, DESCRIP_TRANSF_PROC,
                  ESTADO_TRANSF_IMPR, DESCRIP_TRANSF_IMPR,
                  ESTADO_TRANSF_REIM, DESCRIP_TRANSF_REIM,
                  ESTADO_TRANSF_ANUL, DESCRIP_TRANSF_ANUL, ' ')
        FROM     LGTM_TRANSF_PRODUCTO TRANSF,
           (
        SELECT   DISTINCT NU_GUIA_TRANSFERENCIA,
           NU_SEC_TRANSFERENCIA
        FROM  LGTD_GUIA_TRANSFERENCIA
        WHERE  CO_COMPANIA = codigocompania_in
        AND   CO_LOCAL = codigolocal_in
        ) GUIA,
        (SELECT CO_COMPANIA, CO_LOCAL, DE_CORTA_LOCAL
        FROM VTTM_LOCAL
        UNION
        SELECT CO_COMPANIA, CO_PROVEEDOR, DE_CORTA_PROVEEDOR
        FROM VTTM_PROVEEDOR
        ) LOCAL_DEST,
           CMTR_MOTIVO MOTIVO
        WHERE    TRANSF.CO_COMPANIA = codigocompania_in AND
            TRANSF.CO_LOCAL = codigolocal_in AND
         TRANSF.FE_EMISION_TRANSFERENCIA BETWEEN TO_DATE(fechainicio_in || ' 00:00:00', 'dd/MM/yyyy HH24:MI:SS')
                 AND  TO_DATE(fechafin_in || ' 23:59:59', 'dd/MM/yyyy HH24:MI:SS') AND
           TRANSF.NU_SEC_TRANSFERENCIA = GUIA.NU_SEC_TRANSFERENCIA(+) AND
           TRANSF.CO_COMPANIA = LOCAL_DEST.CO_COMPANIA AND
           (TRANSF.CO_LOCAL_DESTINO = LOCAL_DEST.CO_LOCAL OR  TRANSF.CO_PROVEEDOR_TRANSFERENCIA = LOCAL_DEST.CO_LOCAL ) AND
           TRANSF.CO_GRUPO_MOTIVO = MOTIVO.CO_GRUPO_MOTIVO(+) AND
           TRANSF.CO_MOTIVO_TRANSFERENCIA = MOTIVO.CO_MOTIVO(+)
        ORDER BY NVL(TRANSF.NU_SEC_TRANSFERENCIA, ' ') DESC;
   ELSE
     OPEN eckcur FOR SELECT   NVL(TRANSF.NU_SEC_TRANSFERENCIA, ' ') || 'Ã' ||
               NVL(LOCAL_DEST.DE_CORTA_LOCAL, ' ') || 'Ã' ||
                           NVL(TO_CHAR(TRANSF.FE_EMISION_TRANSFERENCIA, 'dd/MM/yyyy HH24:MI:SS'), ' ') || 'Ã' ||
            NVL(SUBSTR(GUIA.NU_GUIA_TRANSFERENCIA, 1, 3), ' ') || '-' || NVL(SUBSTR(GUIA.NU_GUIA_TRANSFERENCIA, 4, 7), ' ') || 'Ã' ||
               NVL(MOTIVO.DE_MOTIVO, ' ') || 'Ã' ||
                              NVL(TRANSF.CA_PROD_TRANSFERIDO, 0)  || 'Ã' ||
                              DECODE(TRANSF.ES_TRANSF_PRODUCTO,
                      ESTADO_TRANSF_PROC, DESCRIP_TRANSF_PROC,
                   ESTADO_TRANSF_IMPR, DESCRIP_TRANSF_IMPR,
                   ESTADO_TRANSF_REIM, DESCRIP_TRANSF_REIM,
                   ESTADO_TRANSF_ANUL, DESCRIP_TRANSF_ANUL, ' ')
         FROM     LGTM_TRANSF_PRODUCTO TRANSF,
            (
         SELECT   DISTINCT NU_GUIA_TRANSFERENCIA,
            NU_SEC_TRANSFERENCIA
         FROM  LGTD_GUIA_TRANSFERENCIA
         WHERE  CO_COMPANIA = codigocompania_in
         AND   CO_LOCAL = codigolocal_in
         ) GUIA,
         (SELECT CO_COMPANIA, CO_LOCAL, DE_CORTA_LOCAL
         FROM VTTM_LOCAL
         UNION
         SELECT CO_COMPANIA, CO_PROVEEDOR, DE_CORTA_PROVEEDOR
         FROM VTTM_PROVEEDOR
         ) LOCAL_DEST,
            CMTR_MOTIVO MOTIVO
         WHERE    TRANSF.CO_COMPANIA = codigocompania_in AND
             TRANSF.CO_LOCAL = codigolocal_in AND
            TRANSF.NU_SEC_TRANSFERENCIA = GUIA.NU_SEC_TRANSFERENCIA(+) AND
            TRANSF.CO_COMPANIA = LOCAL_DEST.CO_COMPANIA AND
         (TRANSF.CO_LOCAL_DESTINO = LOCAL_DEST.CO_LOCAL OR  TRANSF.CO_PROVEEDOR_TRANSFERENCIA = LOCAL_DEST.CO_LOCAL ) AND
            TRANSF.CO_GRUPO_MOTIVO = MOTIVO.CO_GRUPO_MOTIVO(+) AND
            TRANSF.CO_MOTIVO_TRANSFERENCIA = MOTIVO.CO_MOTIVO(+)
         ORDER BY NVL(TRANSF.NU_SEC_TRANSFERENCIA, ' ') DESC;
   END IF;
     RETURN eckcur;
  END;

  /* ************************************************************************ */
  FUNCTION INV_DETALLE_TRANSF_PRODUCTO(codigocompania_in    IN LGTD_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
            codigolocal_in       IN LGTD_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
            numerorecepcion_in   IN LGTD_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
          OPEN eckcur FOR SELECT NVL(PRODUCTO.CO_PRODUCTO,' ') || 'Ã' ||
               NVL(RTRIM(PRODUCTO.DE_PRODUCTO),' ') || 'Ã' ||
                        NVL(RTRIM(DETALLE.DE_UNIDAD_PRODUCTO),' ') || 'Ã' ||
                        NVL(DETALLE.DE_UNIDAD_FRACCION,' ') || 'Ã' ||
                           TO_CHAR(NVL(DETALLE.CA_TRANSFERIR_ENTERA ,0), '999,990') || 'Ã' ||
                           TO_CHAR(NVL(DETALLE.CA_TRANSFERIR_FRACCION ,0), '999,990') || 'Ã' ||
                           NVL(TO_CHAR(DETALLE.FE_VENCE_PRODUCTO,'MM/yyyy'), ' ') || 'Ã' ||
         NVL(LAB.DE_LABORATORIO ,' ')
       FROM LGTD_GUIA_TRANSFERENCIA DETALLE,
         LGTM_PRODUCTO PRODUCTO,
         CMTR_LABORATORIO LAB
      WHERE DETALLE.CO_COMPANIA = codigocompania_in AND
          DETALLE.CO_LOCAL = codigolocal_in AND
         DETALLE.NU_SEC_TRANSFERENCIA = numerorecepcion_in AND
         DETALLE.CO_COMPANIA = PRODUCTO.CO_COMPANIA AND
         DETALLE.CO_PRODUCTO = PRODUCTO.CO_PRODUCTO AND
         PRODUCTO.CO_COMPANIA = LAB.CO_COMPANIA(+) AND
         PRODUCTO.CO_LABORATORIO = LAB.CO_LABORATORIO(+)
      ORDER BY DETALLE.NU_ITEM_TRANSFERENCIA;
    RETURN eckcur;
  END;

  /* ************************************************************************ */
  FUNCTION OBTIENE_PEDIDO_ADICIONAL(codigocompania_in IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
         codigolocal_in    IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
         tipopedido_in     IN LGTC_PEDIDO_PRODUCTO.TI_PEDIDO_PRODUCTO%TYPE,
         numeropedido_in   IN LGTC_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
          OPEN eckcur FOR SELECT NVL(CO_MOTIVO_PEDIDO,' ') || 'Ã' ||
               NVL(IN_ATENCION_CLIENTE,' ') || 'Ã' ||
                        NVL(IN_DESPACHO_PEDIDO,' ') || 'Ã' ||
                           NVL(CO_CLIENTE_LOCAL ,' ') || 'Ã' ||
                           NVL(NO_RECOJE_PEDIDO ,' ') || 'Ã' ||
                        NVL(TO_CHAR(FE_REQUERIDA_PRODUCTO,'dd/mm/yyyy') ,' ')
       FROM LGTC_PEDIDO_PRODUCTO PEDIDO
      WHERE PEDIDO.CO_COMPANIA = codigocompania_in AND
          PEDIDO.CO_LOCAL = codigolocal_in AND
         PEDIDO.TI_PEDIDO_PRODUCTO = tipopedido_in AND
         PEDIDO.NU_PEDIDO_PRODUCTO = numeropedido_in;
    RETURN eckcur;
  END;

/* ************************************************************************ */
  PROCEDURE INV_CALCULA_MAX_MIN(codigocompania_in    IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                                codigolocal_in       IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE)
            IS
  codigoProducto     LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE;
  minimodias          LGTR_PRODUCTO_LOCAL.NU_MIN_DIAS_REPOSICION%TYPE;
  maximodias         LGTR_PRODUCTO_LOCAL.NU_MAX_DIAS_REPOSICION%TYPE;
  diasrotacion         LGTR_PRODUCTO_LOCAL.NU_DIAS_ROTACION_PROMEDIO%TYPE;
  caExhibicion         LGTR_PRODUCTO_LOCAL.CA_MIN_PROD_EXHIBICION%TYPE;
  stockreponercalc  LGTR_PRODUCTO_LOCAL.CA_STOCK_REPONER_CALCULA%TYPE;
  stockminimo         LGTR_PRODUCTO_LOCAL.CA_STOCK_MINIMO%TYPE;
  stockmaximo        LGTR_PRODUCTO_LOCAL.CA_STOCK_MAXIMO%TYPE;
  rotacionprod        NUMBER(10,3);
  restoprod            NUMBER(10,3);
  stockrequerido     NUMBER(10);
  factorbajarotacion   VTTM_LOCAL.VA_FACTOR_BAJA_ROTACION%TYPE;
  catransito               LGTR_PRODUCTO_LOCAL.CA_STOCK_TRANSITO%TYPE;  
  diasrotacionmaximo LGTR_PRODUCTO_LOCAL.NU_DIAS_ROTACION_PROMEDIO%TYPE;
  stockdisponible        NUMBER(9,3);    
  inIgnorarProdSinSaldo     VTTM_LOCAL.IN_IGNORAR_PROD_SIN_SALDO%TYPE;
  inSumTiempoSuministro  VTTM_LOCAL.IN_SUMAR_TIEMPO_SUMINISTRO%TYPE;
  inSumTransito                VTTM_LOCAL.IN_SUMAR_TRANSITO%TYPE;
  inSumMinExhibicion         VTTM_LOCAL.IN_SUMAR_MIN_EXHIBICION%TYPE;
  inSumComprasPend        VTTM_LOCAL.IN_SUMAR_COMPRAS_PENDIENTES%TYPE;
  inTipOperacion               VTTM_LOCAL.IN_TIPO_OPERACION%TYPE;
  inOrigenProducto            VTTM_LOCAL.IN_ORIGEN_PRODUCTOS%TYPE;
  inSoloProdActivos           VTTM_LOCAL.IN_SOLO_PROD_ACTIVOS%TYPE;
  inProdFraccionados         VTTM_LOCAL.IN_PRODUCTOS_FRACCIONADOS%TYPE;
  inFaltaCero                    VTTM_LOCAL.IN_FALTA_CERO%TYPE;
  caProdNoAtendido          LGTR_PRODUCTO_LOCAL.CA_PROD_NO_ATENDIDO%TYPE;
  ts_dias                          NUMBER;
  tr_dias                          NUMBER;
  caCompraPendiente       NUMBER;
  stockRegular                 NUMBER;
  caVendidaPorPeriodo     NUMBER;
  caRotacionxDia              NUMBER;     
  
    CURSOR detalleProdReposicion (pNumDiasMax NUMBER) IS  
    SELECT PROD_LOCAL.CO_PRODUCTO,
                DECODE(NVL(PROD_LOCAL.NU_MIN_DIAS_REPOSICION,0), 
                0,
                DECODE(NVL(LABORATORIO.NU_MIN_DIAS_REPOSICION,0), 
                0, 
                DECODE(NVL(LINEA_LOCAL.NU_MIN_DIAS_REPOSICION,0), 
                0, 
                NVL(LOCAL.NU_MIN_DIAS_REPOSICION,0), 
                LINEA_LOCAL.NU_MIN_DIAS_REPOSICION), 
                LABORATORIO.NU_MIN_DIAS_REPOSICION),
                PROD_LOCAL.NU_MIN_DIAS_REPOSICION) NU_MIN_DIAS_REPOSICION,        
                DECODE(NVL(PROD_LOCAL.NU_MAX_DIAS_REPOSICION,0),
                0,
                DECODE(NVL(LABORATORIO.NU_MAX_DIAS_REPOSICION,0), 
                0,
                DECODE(NVL(LINEA_LOCAL.NU_MAX_DIAS_REPOSICION,0),
                0,
                NVL(LOCAL.NU_MAX_DIAS_REPOSICION,0),
                LINEA_LOCAL.NU_MAX_DIAS_REPOSICION),
                LABORATORIO.NU_MAX_DIAS_REPOSICION),
                PROD_LOCAL.NU_MAX_DIAS_REPOSICION) NU_MAX_DIAS_REPOSICION,
                DECODE(NVL(PROD_LOCAL.NU_DIAS_ROTACION_PROMEDIO,0),
                0,
                DECODE(NVL(LABORATORIO.NU_DIAS_ROTACION_PROMEDIO,0),
                0,
                DECODE(NVL(LINEA_LOCAL.NU_DIAS_ROTACION_PROMEDIO,0),
                0,
                NVL(LOCAL.NU_DIAS_ROTACION_PROMEDIO,0),
                LINEA_LOCAL.NU_DIAS_ROTACION_PROMEDIO),
                LABORATORIO.NU_DIAS_ROTACION_PROMEDIO),
                PROD_LOCAL.NU_DIAS_ROTACION_PROMEDIO) NU_DIAS_ROTACION_PROMEDIO,             
                (NVL(CA_MIN_PROD_EXHIBICION,0)*-1) CA_MIN_PROD_EXHIBICION,
                DECODE(PROD_LOCAL.IN_PROD_FRACCIONADO, 'S',
                NVL(ROUND(PROD_LOCAL.CA_STOCK_DISPONIBLE / PROD_LOCAL.VA_FRACCION,3),0),
                NVL(PROD_LOCAL.CA_STOCK_DISPONIBLE,0)) STOCK_DISPONIBLE,
                LOCAL.IN_IGNORAR_PROD_SIN_SALDO,            
                LOCAL.IN_SUMAR_TIEMPO_SUMINISTRO,            
                LOCAL.IN_SUMAR_TRANSITO,            
                LOCAL.IN_SUMAR_MIN_EXHIBICION,            
                LOCAL.IN_SUMAR_COMPRAS_PENDIENTES,            
                LOCAL.IN_TIPO_OPERACION,            
                LOCAL.IN_ORIGEN_PRODUCTOS,            
                LOCAL.IN_SOLO_PROD_ACTIVOS,            
                LOCAL.IN_PRODUCTOS_FRACCIONADOS,            
                LOCAL.IN_FALTA_CERO,  
                DECODE(PROD_LOCAL.IN_PROD_FRACCIONADO, 'S',
                NVL(ROUND(NVL(PROD_LOCAL.CA_PROD_NO_ATENDIDO,0) / PROD_LOCAL.VA_FRACCION,3),0),
                NVL(PROD_LOCAL.CA_PROD_NO_ATENDIDO,0)) CA_PROD_NO_ATENDIDO,  
                DECODE(NVL(PROD_LOCAL.TS_DIAS,0), 
                0,
                DECODE(NVL(LABORATORIO.TS_DIAS,0), 
                0, 
                DECODE(NVL(LINEA_LOCAL.TS_DIAS,0), 
                0, 
                NVL(LOCAL.TS_DIAS,0), 
                LINEA_LOCAL.TS_DIAS), 
                LABORATORIO.TS_DIAS),
                PROD_LOCAL.TS_DIAS) TS_DIAS,                 
                DECODE(NVL(PROD_LOCAL.TR_DIAS,0), 
                0,
                DECODE(NVL(LABORATORIO.TR_DIAS,0), 
                0, 
                DECODE(NVL(LINEA_LOCAL.TR_DIAS,0), 
                0, 
                NVL(LOCAL.TR_DIAS,0), 
                LINEA_LOCAL.TR_DIAS), 
                LABORATORIO.TR_DIAS),
                PROD_LOCAL.TR_DIAS) TR_DIAS,
                NVL(LOCAL.VA_FACTOR_BAJA_ROTACION, 0.3) FACTOR
        FROM VTTM_LOCAL LOCAL,
                 LGTR_LINEA_LOCAL LINEA_LOCAL,
                 CMTR_LABORATORIO LABORATORIO,
                 LGTR_PRODUCTO_LOCAL PROD_LOCAL,             
                 LGTM_PRODUCTO PROD             
         WHERE PROD_LOCAL.CO_COMPANIA  = codigocompania_in
            AND   PROD_LOCAL.CO_LOCAL        = codigolocal_in                         
            AND   PROD_LOCAL.CO_COMPANIA  = LOCAL.CO_COMPANIA
            AND   PROD_LOCAL.CO_LOCAL        = LOCAL.CO_LOCAL
            AND   PROD_LOCAL.CO_COMPANIA  = PROD.CO_COMPANIA
            AND   PROD_LOCAL.CO_PRODUCTO = PROD.CO_PRODUCTO
            AND   PROD.CO_COMPANIA             = LINEA_LOCAL.CO_COMPANIA(+)
            AND   PROD.CO_LINEA_PRODUCTO  = LINEA_LOCAL.CO_LINEA_PRODUCTO(+)
            AND   PROD.CO_COMPANIA             = LABORATORIO.CO_COMPANIA (+)
            AND   PROD.CO_LABORATORIO        = LABORATORIO.CO_LABORATORIO(+)
            AND   LINEA_LOCAL.CO_LOCAL(+)    = codigolocal_in
            AND   PROD.ES_PRODUCTO              = 'A'        
           AND  (PROD_LOCAL.ES_PROD_LOCAL = 'A' OR PROD_LOCAL.CA_STOCK_DISPONIBLE > 0)
           AND  (EXISTS(SELECT   1
                               FROM  VTTR_VENTAS_PRODUCTO_DIA
                            WHERE CO_COMPANIA =   codigocompania_in
                                 AND CO_LOCAL       =   codigolocal_in
                                 --AND  FE_DIA_VENTA BETWEEN (SYSDATE - (3*30))
                                  AND FE_DIA_VENTA BETWEEN (SYSDATE - diasrotacionmaximo)
                                  AND SYSDATE
                                  AND PROD.CO_PRODUCTO = CO_PRODUCTO
                                  AND NU_REVISION_PRODUCTO = '0' )
                            OR
                             (PROD_LOCAL.CA_MIN_PROD_EXHIBICION IS NOT NULL AND PROD_LOCAL.CA_MIN_PROD_EXHIBICION != 0 )
                    );

 BEGIN
      UPDATE LGTR_PRODUCTO_LOCAL
      SET CA_STOCK_REPONER = NULL,
             CA_STOCK_REPONER_CALCULA = 0,
             CA_STOCK_MINIMO   = 0,
             CA_STOCK_MAXIMO  = 0,
             VA_ROTACION          = 0,
             CA_STK_DISPONIBLE_PED_REP = NULL,
             CA_STOCK_TRANSITO_PED_REP = NULL,
             FE_MOD_PROD_LOCAL = SYSDATE,
             IN_REPLICA                 = 0
        WHERE CO_COMPANIA = codigocompania_in AND
                    CO_LOCAL       = codigolocal_in;

  COMMIT;

      -- Actualiza el consolidado de ventas del dia de ayer
      Carga_Data_Consolidada.CALCULA_VENTAS_POR_PRODUCTO(codigocompania_in, codigolocal_in, TO_CHAR(SYSDATE-1,'dd/MM/yyyy'));
      -- Actualiza el consolidado de ventas del dia de hoy
      Carga_Data_Consolidada.CALCULA_VENTAS_POR_PRODUCTO(codigocompania_in, codigolocal_in, TO_CHAR(SYSDATE,'dd/MM/yyyy'));

       --factor de baja rotacion     
       SELECT NVL(VA_FACTOR_BAJA_ROTACION, 0.3), NVL(NU_DIAS_ROTACION_PROMEDIO,30)
         INTO factorbajarotacion, diasrotacionmaximo
         FROM VTTM_LOCAL
        WHERE CO_COMPANIA = codigocompania_in AND
                    CO_LOCAL       = codigolocal_in;

        FOR detalleProdReposicion_rec IN detalleProdReposicion(diasrotacionmaximo)
       LOOP
       
          stockRegular:=0;  stockdisponible:=0;  catransito:=0;
          caCompraPendiente:=0;    caExhibicion:=0;  caVendidaPorPeriodo:=0;
          rotacionprod:=0;  caProdNoAtendido:=0;  caRotacionxDia:=0;        
          diasrotacion:=0;  stockminimo:=0;  stockmaximo:=0;minimodias:=0;                
          maximodias:=0;  stockreponercalc:=0;        
              
          codigoproducto             := detalleProdReposicion_rec.CO_PRODUCTO;
          minimodias                   := detalleProdReposicion_rec.NU_MIN_DIAS_REPOSICION;
          maximodias                  := detalleProdReposicion_rec.NU_MAX_DIAS_REPOSICION;
          diasrotacion                  := detalleProdReposicion_rec.NU_DIAS_ROTACION_PROMEDIO;
          caExhibicion                  := detalleProdReposicion_rec.CA_MIN_PROD_EXHIBICION;
          stockdisponible              := detalleProdReposicion_rec.STOCK_DISPONIBLE;
          inIgnorarProdSinSaldo   := detalleProdReposicion_rec.IN_IGNORAR_PROD_SIN_SALDO;
          inSumTiempoSuministro:= detalleProdReposicion_rec.IN_SUMAR_TIEMPO_SUMINISTRO;
          inSumTransito              := detalleProdReposicion_rec.IN_SUMAR_TRANSITO;
          inSumMinExhibicion       := detalleProdReposicion_rec.IN_SUMAR_MIN_EXHIBICION;
          inSumComprasPend      := detalleProdReposicion_rec.IN_SUMAR_COMPRAS_PENDIENTES;
          inTipOperacion             := detalleProdReposicion_rec.IN_TIPO_OPERACION;
          inOrigenProducto          := detalleProdReposicion_rec.IN_ORIGEN_PRODUCTOS;
          inSoloProdActivos          := detalleProdReposicion_rec.IN_SOLO_PROD_ACTIVOS;
          inProdFraccionados       := detalleProdReposicion_rec.IN_PRODUCTOS_FRACCIONADOS;
          inFaltaCero                   := detalleProdReposicion_rec.IN_FALTA_CERO;
          caProdNoAtendido         := detalleProdReposicion_rec.CA_PROD_NO_ATENDIDO;
          ts_dias                         := detalleProdReposicion_rec.TS_DIAS;
          tr_dias                         := detalleProdReposicion_rec.TR_DIAS;          
      
        --Agregar el transito del producto  y la cantidad pendiente de compra
        --catransito               :=DECODE(inSumTransito,'S',INV_OBTIENE_PROD_TRANSITO(codigocompania_in, codigolocal_in, codigoproducto),0);
        --caCompraPendiente :=DECODE(inSumComprasPend,'S',INV_OBTIENE_PROD_COMPRA_PEND(codigocompania_in, codigolocal_in, codigoproducto),0);
        --caProdNoAtendido   :=DECODE(inFaltaCero,'S',caProdNoAtendido,0);
        
        SELECT DECODE(inSumTransito,'S',INV_OBTIENE_PROD_TRANSITO(codigocompania_in, codigolocal_in, codigoproducto),0),
                    DECODE(inSumComprasPend,'S',INV_OBTIENE_PROD_COMPRA_PEND(codigocompania_in, codigoproducto),0),
                    DECODE(inFaltaCero,'S',caProdNoAtendido,0) INTO  catransito, caCompraPendiente, caProdNoAtendido
                    FROM DUAL;
                     
        -- Calculando el stock stockRegular
        stockRegular:=stockdisponible +  catransito + caCompraPendiente+ caExhibicion;         
        
        SELECT SUM(CA_VENTA_DIA) INTO rotacionprod FROM VTTR_VENTAS_PRODUCTO_DIA      
            WHERE CO_COMPANIA = codigocompania_in
                 AND CO_LOCAL       = codigolocal_in
                 AND CO_PRODUCTO= codigoproducto
                 AND FE_DIA_VENTA BETWEEN (SYSDATE - (diasrotacion))  AND SYSDATE;
       
        caVendidaPorPeriodo:=rotacionprod + caProdNoAtendido;
        caRotacionxDia:= caVendidaPorPeriodo/diasrotacion;
        
         IF ((caRotacionxDia > 0) AND (caRotacionxDia < factorbajarotacion)) THEN
                stockminimo := 1;
                stockmaximo := 1;
         ELSIF ((caRotacionxDia >= factorbajarotacion) AND (caRotacionxDia < 1)) THEN
                stockminimo := 1;
                stockmaximo := 1;
        ELSE
             --Calcula stock minimo
             --stockminimo := CEIL(rotacionprod);
             stockminimo := CEIL(minimodias*caRotacionxDia);
             --Calcula stock maximo
             --stockmaximo := CEIL(rotacionprod);
             stockmaximo := CEIL( maximodias*caRotacionxDia);
        END IF;
        
         IF (stockmaximo = 1) THEN
            -- Si se tiene menos de 33% de entero de stock en el local --> Pedir 1 unidad
            IF (stockdisponible <= 1/3) THEN
                stockreponercalc := 1;
            ELSE
                stockreponercalc := 0;
            END IF;
        ELSE
            IF(stockRegular<=stockminimo)  THEN                       
                stockreponercalc:=  ABS(stockmaximo- stockRegular);
            END IF;
        END IF;
    
     -- Actualiza la cantidad de productos
         UPDATE  LGTR_PRODUCTO_LOCAL
          SET CA_STOCK_REPONER_CALCULA  = ROUND(stockreponercalc),
                 CA_STOCK_MINIMO                   = stockminimo,
                 CA_STOCK_MAXIMO                  = stockmaximo,
                 CA_STOCK_REPONER                 = NULL,
                 VA_ROTACION                           = ROUND(rotacionprod/maximodias, 2),
                 FE_MOD_PROD_LOCAL               = SYSDATE,
                 IN_REPLICA                               = 0,  
                 CA_STK_DISPONIBLE_PED_REP   = TRUNC(stockdisponible),
                 CA_STOCK_TRANSITO_PED_REP = catransito
        WHERE CO_COMPANIA = codigocompania_in  AND
                   CO_LOCAL        = codigolocal_in         AND
                   CO_PRODUCTO = codigoproducto       AND
                   NU_REVISION_PRODUCTO = '0';
        COMMIT;
        
        IF(inFaltaCero='S' AND caProdNoAtendido>0) THEN
            INSERT INTO ECVENTA.LGTR_PRODUCTOS_FALTA_CERO_HIST (
                        CO_COMPANIA, CO_LOCAL, CO_PRODUCTO, 
                        NU_REVISION_PRODUCTO, CA_STOCK_NO_ATENDIDO,CA_STOCK_DISPONIBLE, 
                        CA_STOCK_CALCULADO, ID_CREA, FE_CREA) 
            VALUES (codigocompania_in,codigolocal_in,codigoproducto,'0',caProdNoAtendido,stockdisponible,ROUND(stockreponercalc),'AGUERRA',TRUNC(SYSDATE));
        END IF;
        
   END LOOP;

       UPDATE VTTM_LOCAL
          SET FE_CALCULO_MAX_MIN = SYSDATE
        WHERE CO_COMPANIA = codigocompania_in
           AND  CO_LOCAL       = codigolocal_in;

        COMMIT;

  END;
  
--  /* ************************************************************************ */
--  PROCEDURE INV_CALCULA_MAX_MIN(codigocompania_in    IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
--                                codigolocal_in       IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE)
--            IS
--  codigoProducto     LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE;
--  minimodias          LGTR_PRODUCTO_LOCAL.NU_MIN_DIAS_REPOSICION%TYPE;
--  maximodias          LGTR_PRODUCTO_LOCAL.NU_MAX_DIAS_REPOSICION%TYPE;
--  diasrotacion          LGTR_PRODUCTO_LOCAL.NU_DIAS_ROTACION_PROMEDIO%TYPE;
--  cantidadexhibicion LGTR_PRODUCTO_LOCAL.CA_MIN_PROD_EXHIBICION%TYPE;
--  stockreponercalc   LGTR_PRODUCTO_LOCAL.CA_STOCK_REPONER_CALCULA%TYPE;
--  stockminimo         LGTR_PRODUCTO_LOCAL.CA_STOCK_MINIMO%TYPE;
--  stockmaximo        LGTR_PRODUCTO_LOCAL.CA_STOCK_MAXIMO%TYPE;
--  rotacionprod        NUMBER(10,3);
--  restoprod            NUMBER(10,3);
--  stockrequerido     NUMBER(10);
--  factorbajarotacion   VTTM_LOCAL.VA_FACTOR_BAJA_ROTACION%TYPE;
--  productotransito      LGTR_PRODUCTO_LOCAL.CA_STOCK_TRANSITO%TYPE;  
--  diasrotacionmaximo LGTR_PRODUCTO_LOCAL.NU_DIAS_ROTACION_PROMEDIO%TYPE;
--  stockdisponible        NUMBER(9,3);    
--  inIgnorarProdSinSaldo     VTTM_LOCAL.IN_IGNORAR_PROD_SIN_SALDO%TYPE;
--  inSumTiempoSuministro  VTTM_LOCAL.IN_SUMAR_TIEMPO_SUMINISTRO%TYPE;
--  inSumTransito                VTTM_LOCAL.IN_SUMAR_TRANSITO%TYPE;
--  inSumMinExhibicion         VTTM_LOCAL.IN_SUMAR_MIN_EXHIBICION%TYPE;
--  inSumComprasPend        VTTM_LOCAL.IN_SUMAR_COMPRAS_PENDIENTES%TYPE;
--  inTipOperacion               VTTM_LOCAL.IN_TIPO_OPERACION%TYPE;
--  inOrigenProducto            VTTM_LOCAL.IN_ORIGEN_PRODUCTOS%TYPE;
--  inSoloProdActivos           VTTM_LOCAL.IN_SOLO_PROD_ACTIVOS%TYPE;
--  inProdFraccionados         VTTM_LOCAL.IN_PRODUCTOS_FRACCIONADOS%TYPE;
--  inFaltaCero                    VTTM_LOCAL.IN_FALTA_CERO%TYPE;
--  caProdNoAtendido          LGTR_PRODUCTO_LOCAL.CA_PROD_NO_ATENDIDO%TYPE;
--  ts_dias                          NUMBER;
--  tr_dias                          NUMBER;
--
--    CURSOR detalleProdReposicion (pNumDiasMax NUMBER) IS  
--    SELECT PROD_LOCAL.CO_PRODUCTO,
--                DECODE(NVL(PROD_LOCAL.NU_MIN_DIAS_REPOSICION,0), 
--                0,
--                DECODE(NVL(LABORATORIO.NU_MIN_DIAS_REPOSICION,0), 
--                0, 
--                DECODE(NVL(LINEA_LOCAL.NU_MIN_DIAS_REPOSICION,0), 
--                0, 
--                NVL(LOCAL.NU_MIN_DIAS_REPOSICION,0), 
--                LINEA_LOCAL.NU_MIN_DIAS_REPOSICION), 
--                LABORATORIO.NU_MIN_DIAS_REPOSICION),
--                PROD_LOCAL.NU_MIN_DIAS_REPOSICION) NU_MIN_DIAS_REPOSICION,        
--                DECODE(NVL(PROD_LOCAL.NU_MAX_DIAS_REPOSICION,0),
--                0,
--                DECODE(NVL(LABORATORIO.NU_MAX_DIAS_REPOSICION,0), 
--                0,
--                DECODE(NVL(LINEA_LOCAL.NU_MAX_DIAS_REPOSICION,0),
--                0,
--                NVL(LOCAL.NU_MAX_DIAS_REPOSICION,0),
--                LINEA_LOCAL.NU_MAX_DIAS_REPOSICION),
--                LABORATORIO.NU_MAX_DIAS_REPOSICION),
--                PROD_LOCAL.NU_MAX_DIAS_REPOSICION) NU_MAX_DIAS_REPOSICION,
--                DECODE(NVL(PROD_LOCAL.NU_DIAS_ROTACION_PROMEDIO,0),
--                0,
--                DECODE(NVL(LABORATORIO.NU_DIAS_ROTACION_PROMEDIO,0),
--                0,
--                DECODE(NVL(LINEA_LOCAL.NU_DIAS_ROTACION_PROMEDIO,0),
--                0,
--                NVL(LOCAL.NU_DIAS_ROTACION_PROMEDIO,0),
--                LINEA_LOCAL.NU_DIAS_ROTACION_PROMEDIO),
--                LABORATORIO.NU_DIAS_ROTACION_PROMEDIO),
--                PROD_LOCAL.NU_DIAS_ROTACION_PROMEDIO) NU_DIAS_ROTACION_PROMEDIO,             
--                (NVL(CA_MIN_PROD_EXHIBICION,0)*-1) CA_MIN_PROD_EXHIBICION,
--                DECODE(PROD_LOCAL.IN_PROD_FRACCIONADO, 'S',
--                NVL(ROUND(PROD_LOCAL.CA_STOCK_DISPONIBLE / PROD_LOCAL.VA_FRACCION,3),0),
--                NVL(PROD_LOCAL.CA_STOCK_DISPONIBLE,0)) STOCK_DISPONIBLE,
--                LOCAL.IN_IGNORAR_PROD_SIN_SALDO,            
--                LOCAL.IN_SUMAR_TIEMPO_SUMINISTRO,            
--                LOCAL.IN_SUMAR_TRANSITO,            
--                LOCAL.IN_SUMAR_MIN_EXHIBICION,            
--                LOCAL.IN_SUMAR_COMPRAS_PENDIENTES,            
--                LOCAL.IN_TIPO_OPERACION,            
--                LOCAL.IN_ORIGEN_PRODUCTOS,            
--                LOCAL.IN_SOLO_PROD_ACTIVOS,            
--                LOCAL.IN_PRODUCTOS_FRACCIONADOS,            
--                LOCAL.IN_FALTA_CERO,  
--                NVL(PROD_LOCAL.CA_PROD_NO_ATENDIDO,0) AS CA_PROD_NO_ATENDIDO,              
--                DECODE(NVL(PROD_LOCAL.TS_DIAS,0), 
--                0,
--                DECODE(NVL(LABORATORIO.TS_DIAS,0), 
--                0, 
--                DECODE(NVL(LINEA_LOCAL.TS_DIAS,0), 
--                0, 
--                NVL(LOCAL.TS_DIAS,0), 
--                LINEA_LOCAL.TS_DIAS), 
--                LABORATORIO.TS_DIAS),
--                PROD_LOCAL.TS_DIAS) TS_DIAS,                 
--                DECODE(NVL(PROD_LOCAL.TR_DIAS,0), 
--                0,
--                DECODE(NVL(LABORATORIO.TR_DIAS,0), 
--                0, 
--                DECODE(NVL(LINEA_LOCAL.TR_DIAS,0), 
--                0, 
--                NVL(LOCAL.TR_DIAS,0), 
--                LINEA_LOCAL.TR_DIAS), 
--                LABORATORIO.TR_DIAS),
--                PROD_LOCAL.TR_DIAS) TR_DIAS
--        FROM VTTM_LOCAL LOCAL,
--                 LGTR_LINEA_LOCAL LINEA_LOCAL,
--                 CMTR_LABORATORIO LABORATORIO,
--                 LGTR_PRODUCTO_LOCAL PROD_LOCAL,             
--                 LGTM_PRODUCTO PROD             
--         WHERE PROD_LOCAL.CO_COMPANIA  = codigocompania_in
--            AND   PROD_LOCAL.CO_LOCAL        = codigolocal_in
--            AND   PROD_LOCAL.CO_COMPANIA  = LOCAL.CO_COMPANIA
--            AND   PROD_LOCAL.CO_LOCAL        = LOCAL.CO_LOCAL
--            AND   PROD_LOCAL.CO_COMPANIA  = PROD.CO_COMPANIA
--            AND   PROD_LOCAL.CO_PRODUCTO = PROD.CO_PRODUCTO
--            AND   PROD.CO_COMPANIA             = LINEA_LOCAL.CO_COMPANIA(+)
--            AND   PROD.CO_LINEA_PRODUCTO  = LINEA_LOCAL.CO_LINEA_PRODUCTO(+)
--            AND   PROD.CO_COMPANIA             = LABORATORIO.CO_COMPANIA (+)
--            AND   PROD.CO_LABORATORIO       = LABORATORIO.CO_LABORATORIO(+)
--            AND   LINEA_LOCAL.CO_LOCAL(+)   = codigolocal_in
--            AND   PROD.ES_PRODUCTO              = 'A'        
--           AND  (PROD_LOCAL.ES_PROD_LOCAL = 'A' OR PROD_LOCAL.CA_STOCK_DISPONIBLE > 0)
--           AND  (EXISTS(SELECT   1
--                               FROM  VTTR_VENTAS_PRODUCTO_DIA
--                            WHERE CO_COMPANIA =  codigocompania_in
--                                 AND CO_LOCAL       =  codigolocal_in
--                                 AND  FE_DIA_VENTA BETWEEN (SYSDATE - (3*30))
--                                 -- AND FE_DIA_VENTA BETWEEN (SYSDATE - (3*pNumDiasMax*7))
--                                 AND SYSDATE
--                                 AND PROD.CO_PRODUCTO = CO_PRODUCTO
--                                 AND NU_REVISION_PRODUCTO = '0' )
--                            OR
--                             (PROD_LOCAL.CA_MIN_PROD_EXHIBICION IS NOT NULL AND PROD_LOCAL.CA_MIN_PROD_EXHIBICION != 0 )
--                    );
--
-- BEGIN
--      UPDATE LGTR_PRODUCTO_LOCAL
--      SET CA_STOCK_REPONER = NULL,
--             CA_STOCK_REPONER_CALCULA = 0,
--             CA_STOCK_MINIMO   = 0,
--             CA_STOCK_MAXIMO  = 0,
--             VA_ROTACION          = 0,
--             CA_STK_DISPONIBLE_PED_REP = NULL,
--             CA_STOCK_TRANSITO_PED_REP = NULL,
--             FE_MOD_PROD_LOCAL = SYSDATE,
--             IN_REPLICA                 = 0
--        WHERE CO_COMPANIA = codigocompania_in AND
--                    CO_LOCAL       = codigolocal_in;
--
--  COMMIT;
--
--      -- Actualiza el consolidado de ventas del dia de ayer
--      Carga_Data_Consolidada.CALCULA_VENTAS_POR_PRODUCTO(codigocompania_in, codigolocal_in, TO_CHAR(SYSDATE-1,'dd/MM/yyyy'));
--      -- Actualiza el consolidado de ventas del dia de hoy
--      Carga_Data_Consolidada.CALCULA_VENTAS_POR_PRODUCTO(codigocompania_in, codigolocal_in, TO_CHAR(SYSDATE,'dd/MM/yyyy'));
--
--       --factor de baja rotacion     
--       SELECT NVL(VA_FACTOR_BAJA_ROTACION, 0.3), NVL(NU_DIAS_ROTACION_PROMEDIO,30)
--         INTO factorbajarotacion, diasrotacionmaximo
--         FROM VTTM_LOCAL
--        WHERE CO_COMPANIA = codigocompania_in AND
--                    CO_LOCAL       = codigolocal_in;
--
--        FOR detalleProdReposicion_rec IN detalleProdReposicion(diasrotacionmaximo)
--       LOOP
--          codigoproducto             := detalleProdReposicion_rec.CO_PRODUCTO;
--          minimodias                   := detalleProdReposicion_rec.NU_MIN_DIAS_REPOSICION;
--          maximodias                  := detalleProdReposicion_rec.NU_MAX_DIAS_REPOSICION;
--          diasrotacion                  := detalleProdReposicion_rec.NU_DIAS_ROTACION_PROMEDIO;
--          cantidadexhibicion         := detalleProdReposicion_rec.CA_MIN_PROD_EXHIBICION;
--          stockdisponible              := detalleProdReposicion_rec.STOCK_DISPONIBLE;
--          inIgnorarProdSinSaldo   := detalleProdReposicion_rec.IN_IGNORAR_PROD_SIN_SALDO;
--          inSumTiempoSuministro:= detalleProdReposicion_rec.IN_SUMAR_TIEMPO_SUMINISTRO;
--          inSumTransito              := detalleProdReposicion_rec.IN_SUMAR_TRANSITO;
--          inSumMinExhibicion       := detalleProdReposicion_rec.IN_SUMAR_MIN_EXHIBICION;
--          inSumComprasPend      := detalleProdReposicion_rec.IN_SUMAR_COMPRAS_PENDIENTES;
--          inTipOperacion             := detalleProdReposicion_rec.IN_TIPO_OPERACION;
--          inOrigenProducto          := detalleProdReposicion_rec.IN_ORIGEN_PRODUCTOS;
--          inSoloProdActivos          := detalleProdReposicion_rec.IN_SOLO_PROD_ACTIVOS;
--          inProdFraccionados       := detalleProdReposicion_rec.IN_PRODUCTOS_FRACCIONADOS;
--          inFaltaCero                   := detalleProdReposicion_rec.IN_FALTA_CERO;
--          caProdNoAtendido         := detalleProdReposicion_rec.CA_PROD_NO_ATENDIDO;
--          ts_dias                         := detalleProdReposicion_rec.TS_DIAS;
--          tr_dias                         := detalleProdReposicion_rec.TR_DIAS;     
--            
--    -- Calcula rotacion diaria de un producto
--/*
--    SELECT NVL( SUM(CA_VENTA_DIA) / (semanasrotacion*7), 0)
--           INTO rotacionprod
--      FROM VTTR_VENTAS_PRODUCTO_DIA
--   WHERE CO_COMPANIA = codigocompania_in AND
--         CO_LOCAL    = codigolocal_in   AND
--         CO_PRODUCTO = codigoproducto AND
--      NU_REVISION_PRODUCTO = '0' AND
--      FE_DIA_VENTA BETWEEN (SYSDATE - (semanasrotacion*7))
--              AND SYSDATE;
--*/
--    rotacionprod := INV_CALCULA_ROTACION_PROD(codigocompania_in, codigolocal_in, codigoproducto, semanasrotacion, maximodias);
--
----   IF (rotacionprod <> 0) THEN
--    IF ((rotacionprod > 0) AND (rotacionprod < factorbajarotacion)) THEN
----    IF ((rotacionprod*minimodias > 0) AND (rotacionprod*maximodias < factorbajarotacion)) THEN
----       stockminimo := 0;
--       stockminimo := 1;
--       stockmaximo := 1;
--    ELSIF ((rotacionprod >= factorbajarotacion) AND (rotacionprod < 1)) THEN
----    ELSIF ((rotacionprod*minimodias >= factorbajarotacion) AND (rotacionprod*maximodias < 1)) THEN
--       stockminimo := 1;
--       stockmaximo := 1;
----       stockmaximo := 2;
--    ELSE
--     --Calcula stock minimo
----     stockminimo := CEIL(rotacionprod*minimodias);
--     stockminimo := CEIL(rotacionprod);
--     --Calcula stock maximo
----     stockmaximo := CEIL(rotacionprod*maximodias);
--     stockmaximo := CEIL(rotacionprod);
--    END IF;
--
--    -- A?adir los productos en exhibicion
--       stockminimo := stockminimo + cantidadexhibicion;
--       stockmaximo := stockmaximo + cantidadexhibicion;
--
--    -- A?adir los productos en transito
--    productotransito :=INV_OBTIENE_PROD_TRANSITO(codigocompania_in, codigolocal_in, codigoproducto);
--    stockrequerido := stockmaximo - productotransito;
--
--    -- Calcula Stock a Reponer Automaticamente
--
--    stockreponercalc := INV_CALCULA_STOCK_REQUERIDO(stockmaximo, stockrequerido, stockdisponible);
--
--/*
--    IF (stockdisponible >= stockrequerido) THEN
--     stockreponercalc := 0;
--    ELSE
--     stockreponercalc := stockrequerido - stockdisponible;
--    END IF;
--*/
--
--     -- Actualiza la cantidad de productos
--         UPDATE  LGTR_PRODUCTO_LOCAL
--          SET CA_STOCK_REPONER_CALCULA = stockreponercalc,
--                 CA_STOCK_MINIMO = stockminimo,
--                 CA_STOCK_MAXIMO = stockmaximo,
--                 CA_STOCK_REPONER = NULL,
--                 VA_ROTACION = ROUND(rotacionprod/maximodias, 2),
--                 FE_MOD_PROD_LOCAL = SYSDATE,
--                 IN_REPLICA = 0,
--                 CA_STK_DISPONIBLE_PED_REP = TRUNC(stockdisponible),
--                 CA_STOCK_TRANSITO_PED_REP = productotransito
--        WHERE CO_COMPANIA = codigocompania_in  AND
--                   CO_LOCAL        = codigolocal_in AND
--                   CO_PRODUCTO = codigoproducto;
--        COMMIT;
--        
--   END LOOP;
--
--       UPDATE VTTM_LOCAL
--          SET FE_CALCULO_MAX_MIN = SYSDATE
--        WHERE CO_COMPANIA = codigocompania_in
--           AND  CO_LOCAL       = codigolocal_in;
--
--        COMMIT;
--
--  END;

  /* ************************************************************************ */      
  FUNCTION INV_OBTIENE_PROD_TRANSITO(codigocompania_in    IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
                                     codigolocal_in       IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
                codigoproducto_in    IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
           RETURN LGTR_PRODUCTO_LOCAL.CA_STOCK_TRANSITO%TYPE  IS
  productotransito   LGTR_PRODUCTO_LOCAL.CA_STOCK_TRANSITO%TYPE;
 BEGIN
   --Obtiene Cantidad de Productos en Guia de Transito
      SELECT NVL(SUM(CA_ENVIADA_ENTERA), 0)
           INTO productotransito
        FROM LGTD_RECEPCION_PRODUCTO
    WHERE CO_COMPANIA = codigocompania_in  AND
          CO_LOCAL    = codigolocal_in AND
          CO_PRODUCTO = codigoproducto_in AND
       IN_AFECTA_PRODUCTO IS NULL;
   RETURN productotransito;
 END;  
 
   /* ************************************************************************ */      
  FUNCTION INV_OBTIENE_PROD_COMPRA_PEND(codigocompania_in    IN LGTC_FARINV1.CO_COMPANIA%TYPE,                                                                        
                                                                        codigoproducto_in     IN LGTD_FARINV2.CO_PRODUCTO%TYPE)
           RETURN LGTD_FARINV2.VA_CANTIDAD%TYPE  IS
   caPendCom   LGTD_FARINV2.VA_CANTIDAD%TYPE;        
   nuGuia          LGTC_FARINV1.NU_GUIA%TYPE;
   nuOc             LGTC_FARINV1.NU_OC%TYPE;
   tiDocumento   LGTC_FARINV1.TI_DOCUMENTO%TYPE;
   nuDocumento LGTC_FARINV1.NU_DOCUMENTO%TYPE;
  
 BEGIN
 
    SELECT SUM(NVL(VA_CANTIDAD,0)) INTO caPendCom
       FROM LGTC_FARINV1 cab,
                LGTD_FARINV2  det
    WHERE 
           cab.CO_COMPANIA    = codigocompania_in   
    AND cab.ES_DOCUMENTO = 'P'
    AND det.CO_PRODUCTO   = codigoproducto_in
    AND cab.CO_COMPANIA   = det.CO_COMPANIA
    AND cab.CO_COMPANIA   = det.CO_COMPANIA    
    AND cab.NU_GUIA            = det.NU_GUIA
    AND cab.NU_OC               = det.NU_OC
    AND cab.TI_DOCUMENTO = det.TI_DOCUMENTO
    AND cab.NU_DOCUMENTO= det.NU_DOCUMENTO;
            
   RETURN NVL(caPendCom,0);
 END;  

  /**************************************************************************/
  PROCEDURE INV_ANULA_TRANSF_PRODUCTO(codigocompania_in  IN LGTD_GUIA_TRANSFERENCIA.CO_COMPANIA%TYPE,
                                      codigolocal_in     IN LGTD_GUIA_TRANSFERENCIA.CO_LOCAL%TYPE,
           numerotransferencia_in IN LGTD_GUIA_TRANSFERENCIA.NU_SEC_TRANSFERENCIA%TYPE,
           idanulatransf_in       IN LGTM_TRANSF_PRODUCTO.ID_ANULA_TRANSFERENCIA%TYPE,
           tipodocumento_in        IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
           codigogrupomotivo_in    IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
           codigomotivo_in         IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE)
            IS
  codigoProducto        LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE;
  cantransferirentera   LGTD_GUIA_TRANSFERENCIA.CA_TRANSFERIR_ENTERA%TYPE;
  cantransferirfraccion LGTD_GUIA_TRANSFERENCIA.CA_TRANSFERIR_FRACCION%TYPE;
  inprodfraccionado     LGTR_PRODUCTO_LOCAL.IN_PROD_FRACCIONADO%TYPE;
  valorfraccion         LGTR_PRODUCTO_LOCAL.VA_FRACCION%TYPE;
  stockdisponible       LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;
  cantidadtransferir    LGTV_KARDEX.CA_MOVIMIENTO%TYPE;
    CURSOR detalleGuia IS
         SELECT DET.CO_PRODUCTO,
       DET.CA_TRANSFERIR_ENTERA,
       DET.CA_TRANSFERIR_FRACCION,
       PROD_LOCAL.CA_STOCK_DISPONIBLE,
       PROD_LOCAL.IN_PROD_FRACCIONADO,
       PROD_LOCAL.VA_FRACCION
     FROM LGTD_GUIA_TRANSFERENCIA DET,
         LGTR_PRODUCTO_LOCAL PROD_LOCAL
    WHERE DET.CO_COMPANIA = codigocompania_in AND
          DET.CO_LOCAL = codigolocal_in AND
       DET.NU_SEC_TRANSFERENCIA = numerotransferencia_in AND
       DET.ES_DET_GUIA_TRANSFERENCIA = 'A' AND
       DET.CO_COMPANIA = PROD_LOCAL.CO_COMPANIA AND
          DET.CO_LOCAL = PROD_LOCAL.CO_LOCAL AND
          DET.CO_PRODUCTO = PROD_LOCAL.CO_PRODUCTO;
  BEGIN
    FOR detalleGuia_rec IN detalleGuia
   LOOP
      codigoproducto := detalleGuia_rec.CO_PRODUCTO;
      stockdisponible := detalleGuia_rec.CA_STOCK_DISPONIBLE;
    cantransferirentera := detalleGuia_rec.CA_TRANSFERIR_ENTERA;
    cantransferirfraccion := detalleGuia_rec.CA_TRANSFERIR_FRACCION;
    inprodfraccionado := detalleGuia_rec.IN_PROD_FRACCIONADO;
    valorfraccion := detalleGuia_rec.VA_FRACCION;
    IF (inprodfraccionado = 'S') THEN
       cantidadtransferir:= (cantransferirentera * valorfraccion) + cantransferirfraccion ;
    ELSE
        cantidadtransferir:= cantransferirentera;
    END IF;
      INV_GRABA_KARDEX(codigocompania_in, codigolocal_in, codigoproducto,
        cantidadtransferir, tipodocumento_in, numerotransferencia_in,
      'Guia Matriz', codigogrupomotivo_in, codigomotivo_in,
      idanulatransf_in);

   UPDATE LGTD_GUIA_TRANSFERENCIA
    SET ES_DET_GUIA_TRANSFERENCIA = 'N',
       ID_MOD_DET_GUIA_TRANSFERENCIA = idanulatransf_in,
       FE_MOD_DET_GUIA_TRANSFERENCIA = SYSDATE,
   IN_REPLICA = 0
    WHERE CO_COMPANIA = codigocompania_in AND
       CO_LOCAL = codigolocal_in AND
    NU_SEC_TRANSFERENCIA = numerotransferencia_in AND
    CO_PRODUCTO = codigoproducto AND
    ES_DET_GUIA_TRANSFERENCIA = 'A';
   --COMMIT;

   END LOOP;

   UPDATE LGTM_TRANSF_PRODUCTO
      SET ES_TRANSF_PRODUCTO = 'N',
        ID_ANULA_TRANSFERENCIA = idanulatransf_in,
       FE_ANULA_TRANSFERENCIA = SYSDATE,
       FE_MOD_TRANSF_PRODUCTO = SYSDATE,
                          IN_REPLICA = 0
    WHERE CO_COMPANIA = codigocompania_in AND
       CO_LOCAL = codigolocal_in AND
    NU_SEC_TRANSFERENCIA = numerotransferencia_in;
   COMMIT;

  END;

  /**************************************************************************/
  FUNCTION INV_REIMP_TRANSF_PRODUCTO(codigocompania_in      IN LGTM_TRANSF_PRODUCTO.CO_COMPANIA%TYPE,
              codigolocal_in         IN LGTM_TRANSF_PRODUCTO.CO_LOCAL%TYPE,
         numerotransferencia_in IN LGTM_TRANSF_PRODUCTO.NU_SEC_TRANSFERENCIA%TYPE,
         localdestino_in        IN LGTM_TRANSF_PRODUCTO.CO_LOCAL_DESTINO%TYPE,
         idcreatransf_in        IN LGTM_TRANSF_PRODUCTO.ID_CREA_TRANSF_PRODUCTO%TYPE,
         cantidaditems_in       IN LGTM_TRANSF_PRODUCTO.CA_PROD_TRANSFERIDO%TYPE,
         grupomotivo_in         IN LGTM_TRANSF_PRODUCTO.CO_GRUPO_MOTIVO%TYPE,
         codigomotivo_in        IN LGTM_TRANSF_PRODUCTO.CO_MOTIVO_TRANSFERENCIA%TYPE,
         empresadestino_in      IN LGTM_TRANSF_PRODUCTO.NO_EMPRESA_DESTINO%TYPE,
         direccionempresa_in    IN LGTM_TRANSF_PRODUCTO.DE_DIRECCION_EMP_DESTINO%TYPE,
         nombretransportista_in IN LGTM_TRANSF_PRODUCTO.NO_TRANSPORTISTA%TYPE,
         directransportista_in  IN LGTM_TRANSF_PRODUCTO.DE_DIRECCION_TRANS%TYPE,
         tipdoctransp_in     IN LGTM_TRANSF_PRODUCTO.TI_DOC_IDENTIDAD_TRANS%TYPE,
         numdoctransp_in     IN LGTM_TRANSF_PRODUCTO.NU_DOC_IDENTIDAD_TRANS%TYPE,
         numeroplaca_in  IN LGTM_TRANSF_PRODUCTO.NU_PLACA%TYPE)
            RETURN LGTM_TRANSF_PRODUCTO.NU_SEC_TRANSFERENCIA%TYPE IS
  newtransferencia      LGTM_TRANSF_PRODUCTO.NU_SEC_TRANSFERENCIA%TYPE;
  codigoProducto        LGTD_GUIA_TRANSFERENCIA.CO_PRODUCTO%TYPE;
  unidadproducto        LGTD_GUIA_TRANSFERENCIA.DE_UNIDAD_PRODUCTO%TYPE;
  unidadfraccion        LGTD_GUIA_TRANSFERENCIA.DE_UNIDAD_FRACCION%TYPE;
  cantransferirentera   LGTD_GUIA_TRANSFERENCIA.CA_TRANSFERIR_ENTERA%TYPE;
  cantransferirfraccion LGTD_GUIA_TRANSFERENCIA.CA_TRANSFERIR_FRACCION%TYPE;
  numeroitem            LGTD_GUIA_TRANSFERENCIA.NU_ITEM_TRANSFERENCIA%TYPE;
  fechavenc             LGTD_GUIA_TRANSFERENCIA.FE_VENCE_PRODUCTO%TYPE;
  newguiatransf         LGTC_GUIA_TRANSFERENCIA.NU_GUIA_TRANSFERENCIA%TYPE;
    CURSOR detalleGuia IS
         SELECT DETALLE.NU_ITEM_TRANSFERENCIA,
       DETALLE.CO_PRODUCTO,
       DETALLE.DE_UNIDAD_PRODUCTO,
       DETALLE.DE_UNIDAD_FRACCION,
       DETALLE.CA_TRANSFERIR_ENTERA,
       DETALLE.CA_TRANSFERIR_FRACCION,
       DETALLE.FE_VENCE_PRODUCTO
     FROM LGTD_GUIA_TRANSFERENCIA DETALLE
    WHERE DETALLE.CO_COMPANIA = codigocompania_in  AND
          DETALLE.CO_LOCAL = codigolocal_in AND
       DETALLE.NU_SEC_TRANSFERENCIA = numerotransferencia_in;
  BEGIN
      newtransferencia := INV_GRABA_TRANSF_PROD(codigocompania_in, codigolocal_in,
         localdestino_in, idcreatransf_in, cantidaditems_in,
        grupomotivo_in, codigomotivo_in,
        empresadestino_in, direccionempresa_in,
        nombretransportista_in,directransportista_in,
        tipdoctransp_in, numdoctransp_in, numeroplaca_in);

-- modificado 20041009
/*   newguiatransf := INV_GRABA_CABECERA_GUIA_TRANSF(codigocompania_in, codigolocal_in,
        idcreatransf_in, cantidaditems_in);*/
   newguiatransf := '9999999';

    FOR detalleGuia_rec IN detalleGuia
   LOOP
      codigoproducto := detalleGuia_rec.CO_PRODUCTO;
    unidadproducto := detalleGuia_rec.DE_UNIDAD_PRODUCTO;
    unidadfraccion := detalleGuia_rec.DE_UNIDAD_FRACCION;
    cantransferirentera := detalleGuia_rec.CA_TRANSFERIR_ENTERA;
    cantransferirfraccion := detalleGuia_rec.CA_TRANSFERIR_FRACCION;
    numeroitem := detalleGuia_rec.NU_ITEM_TRANSFERENCIA;
    fechavenc := detalleGuia_rec.FE_VENCE_PRODUCTO;
    INSERT INTO LGTD_GUIA_TRANSFERENCIA (CO_COMPANIA,
           CO_LOCAL,
                                   NU_SEC_TRANSFERENCIA,
           NU_ITEM_TRANSFERENCIA,
                                   CO_PRODUCTO, NU_REVISION_PRODUCTO,
           DE_UNIDAD_PRODUCTO,
           DE_UNIDAD_FRACCION,
                                   CA_TRANSFERIR_ENTERA,
                                   CA_TRANSFERIR_FRACCION,
           FE_VENCE_PRODUCTO,
           NU_GUIA_TRANSFERENCIA,
           ES_DET_GUIA_TRANSFERENCIA,
           ID_CREA_DET_GUIA_TRANSFERENCIA,
           FE_CREA_DET_GUIA_TRANSFERENCIA
        )
                           VALUES (codigocompania_in,
              codigolocal_in,
           newtransferencia,
           numeroitem,
           codigoproducto, '0',
           unidadproducto,
           unidadfraccion,
           cantransferirentera,
           cantransferirfraccion,
           fechavenc,
           newguiatransf,
           'A',
           idcreatransf_in,
           SYSDATE
         );
   COMMIT;

   END LOOP;
   UPDATE LGTM_TRANSF_PRODUCTO
      SET ES_TRANSF_PRODUCTO = 'R',
        ID_ANULA_TRANSFERENCIA = idcreatransf_in,
       FE_ANULA_TRANSFERENCIA = SYSDATE,
       FE_MOD_TRANSF_PRODUCTO = SYSDATE,
                          IN_REPLICA = 0
    WHERE CO_COMPANIA = codigocompania_in AND
       CO_LOCAL = codigolocal_in AND
    NU_SEC_TRANSFERENCIA = numerotransferencia_in;
   COMMIT;

   UPDATE LGTM_TRANSF_PRODUCTO
      SET ES_TRANSF_PRODUCTO = 'I',
        FE_MOD_TRANSF_PRODUCTO = SYSDATE,
                 IN_REPLICA = 0
    WHERE CO_COMPANIA = codigocompania_in AND
       CO_LOCAL = codigolocal_in AND
    NU_SEC_TRANSFERENCIA = newtransferencia;
   COMMIT;

   RETURN newtransferencia;

  END;
  /* ************************************************************************ */
  FUNCTION OBTIENE_TRANSF_PRODUCTO(codigocompania_in  IN LGTM_TRANSF_PRODUCTO.CO_COMPANIA%TYPE,
        codigolocal_in     IN LGTM_TRANSF_PRODUCTO.CO_LOCAL%TYPE,
        numerotransferencia_in  IN LGTM_TRANSF_PRODUCTO.NU_SEC_TRANSFERENCIA%TYPE)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
          OPEN eckcur FOR SELECT NVL(CO_LOCAL_DESTINO,NVL(CO_PROVEEDOR_TRANSFERENCIA,' ')) || 'Ã' ||
               NVL(CO_MOTIVO_TRANSFERENCIA,' ') || 'Ã' ||
                        NVL(TO_CHAR(FE_EMISION_TRANSFERENCIA,'dd/mm/yyyy HH24:MI') ,' ')  || 'Ã' ||
         NVL(ES_TRANSF_PRODUCTO,' ')  || 'Ã' ||
         NVL(T2.TI_LOCAL,' ')
       FROM LGTM_TRANSF_PRODUCTO T1,
           (SELECT CO_COMPANIA, CO_LOCAL, TI_LOCAL
         FROM VTTM_LOCAL
         UNION
         SELECT CO_COMPANIA, CO_PROVEEDOR, 'P'
         FROM VTTM_PROVEEDOR
         )T2
      WHERE T1.CO_COMPANIA = T2.CO_COMPANIA AND
          (T1.CO_LOCAL_DESTINO = T2.CO_LOCAL OR T1.CO_PROVEEDOR_TRANSFERENCIA = T2.CO_LOCAL) AND
          T1.CO_COMPANIA = codigocompania_in AND
          T1.CO_LOCAL = codigolocal_in AND
         T1.NU_SEC_TRANSFERENCIA = numerotransferencia_in;
    RETURN eckcur;
  END;

  /* ************************************************************************ */
  FUNCTION INV_OBTIENE_DATOS_REPOSICION(codigocompania_in    IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
                                    codigolocal_in       IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
          codigoproducto_in    IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
          OPEN eckcur FOR SELECT
       DECODE(NVL(PROD_LOCAL.NU_MIN_DIAS_REPOSICION,0),
         0,
         DECODE(NVL(LINEA_LOCAL.NU_MIN_DIAS_REPOSICION,0),
        0,
        NVL(LOCAL.NU_MIN_DIAS_REPOSICION,0),
        LINEA_LOCAL.NU_MIN_DIAS_REPOSICION),
      PROD_LOCAL.NU_MIN_DIAS_REPOSICION) || 'Ã' ||
       DECODE(NVL(PROD_LOCAL.NU_MAX_DIAS_REPOSICION,0),
         0,
         DECODE(NVL(LINEA_LOCAL.NU_MAX_DIAS_REPOSICION,0),
        0,
        NVL(LOCAL.NU_MAX_DIAS_REPOSICION,0),
        LINEA_LOCAL.NU_MAX_DIAS_REPOSICION),
      PROD_LOCAL.NU_MAX_DIAS_REPOSICION) || 'Ã' ||
       DECODE(NVL(PROD_LOCAL.NU_DIAS_ROTACION_PROMEDIO,0),
         0,
         DECODE(NVL(LINEA_LOCAL.NU_DIAS_ROTACION_PROMEDIO,0),
        0,
        NVL(LOCAL.NU_DIAS_ROTACION_PROMEDIO,0),
        LINEA_LOCAL.NU_DIAS_ROTACION_PROMEDIO),
      PROD_LOCAL.NU_DIAS_ROTACION_PROMEDIO) || 'Ã' ||
       TO_CHAR(NVL(PROD_LOCAL.CA_STOCK_MAXIMO,0),'999,999,990') || 'Ã' ||
       TO_CHAR(NVL(PROD_LOCAL.CA_MIN_PROD_EXHIBICION,0),'999,999,990')
     FROM LGTR_PRODUCTO_LOCAL PROD_LOCAL,
         VTTM_LOCAL LOCAL,
       LGTM_PRODUCTO PROD,
       LGTR_LINEA_LOCAL LINEA_LOCAL
    WHERE PROD_LOCAL.CO_COMPANIA = codigocompania_in  AND
          PROD_LOCAL.CO_LOCAL = codigolocal_in AND
       PROD_LOCAL.CO_COMPANIA = LOCAL.CO_COMPANIA AND
       PROD_LOCAL.CO_PRODUCTO = codigoproducto_in AND
       PROD_LOCAL.CO_LOCAL = LOCAL.CO_LOCAL  AND
       PROD_LOCAL.CO_COMPANIA = PROD.CO_COMPANIA AND
       PROD_LOCAL.CO_PRODUCTO = PROD.CO_PRODUCTO  AND
       PROD.CO_COMPANIA = LINEA_LOCAL.CO_COMPANIA(+) AND
       PROD.CO_LINEA_PRODUCTO = LINEA_LOCAL.CO_LINEA_PRODUCTO(+) AND
       LINEA_LOCAL.CO_LOCAL(+) = codigolocal_in;
    RETURN eckcur;
  END;

 /* ************************************************************************ */
 FUNCTION INV_OBTIENE_PROD_ROTACION(codigocompania_in    IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
                                    codigolocal_in       IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
               codigoproducto_in    IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE,
            semanasrotacion_in  IN NUMBER)
           RETURN NUMBER IS
  rotacionprod       NUMBER(10,2);
 BEGIN
    --Calcula rotacion de un producto (en las ultimas "semanasrotacion_in" semanas)

    SELECT NVL(SUM(CA_VENTA_DIA),0)
           INTO rotacionprod
      FROM VTTR_VENTAS_PRODUCTO_DIA
   WHERE CO_COMPANIA = codigocompania_in AND
         CO_LOCAL    = codigolocal_in   AND
         CO_PRODUCTO = codigoproducto_in AND
      NU_REVISION_PRODUCTO = '0' AND
      FE_DIA_VENTA BETWEEN (SYSDATE - (semanasrotacion_in)) AND SYSDATE;

/*
    SELECT NVL(SUM(DECODE(NVL(VA_FRACCION,0), 0, CA_ATENDIDA, CA_ATENDIDA/VA_FRACCION)),0)
           INTO rotacionprod
      FROM VTTD_PEDIDO_VENTA DET,
        VTTC_PEDIDO_VENTA PED
   WHERE DET.CO_COMPANIA = codigocompania_in  AND
         DET.CO_LOCAL    = codigolocal_in AND
         DET.CO_PRODUCTO = codigoproducto_in AND
      DET.CO_COMPANIA = PED.CO_COMPANIA AND
         DET.CO_LOCAL    = PED.CO_LOCAL  AND
      DET.NU_PEDIDO   = PED.NU_PEDIDO AND
      PED.FE_PEDIDO BETWEEN (SYSDATE - (semanasrotacion_in*7)) AND SYSDATE AND
      PED.ES_PEDIDO_VENTA = 'C';
   --
*/

   RETURN rotacionprod;
 END;

  /* ************************************************************************ */
  FUNCTION INV_OBTIENE_PROD_ROTACION_MES(codigocompania_in    IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
                                 codigolocal_in         IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
            codigoproducto_in      IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE,
            numeromes_in           IN NUMBER)
             RETURN NUMBER IS
  rotacionprod       NUMBER(10,2);
 BEGIN
    --Calcula rotacion diaria de un producto
    SELECT NVL(SUM(DECODE(VA_FRACCION, NULL, CA_ATENDIDA, 0, CA_ATENDIDA, CA_ATENDIDA/VA_FRACCION)),0)
          INTO rotacionprod
      FROM VTTC_PEDIDO_VENTA PED,
     VTTD_PEDIDO_VENTA DET
   WHERE PED.CO_COMPANIA = codigocompania_in  AND
         PED.CO_LOCAL    = codigolocal_in AND
      PED.FE_PEDIDO BETWEEN (SYSDATE - numeromes_in*30)
                        AND (SYSDATE - (numeromes_in-1)*30 ) AND
      PED.ES_PEDIDO_VENTA = 'C' AND
      PED.CO_COMPANIA = DET.CO_COMPANIA AND
         PED.CO_LOCAL    = DET.CO_LOCAL  AND
      PED.NU_PEDIDO   = DET.NU_PEDIDO AND
         DET.CO_PRODUCTO = codigoproducto_in;
   --
   RETURN rotacionprod;
 END;

   /* ************************************************************************ */
  FUNCTION INV_OBTIENE_DATOS_REPO_LOCAL(codigocompania_in    IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
                                    codigolocal_in       IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
          OPEN eckcur FOR SELECT
        NVL(LOCAL.NU_MIN_DIAS_REPOSICION,0) || 'Ã' ||
        NVL(LOCAL.NU_MAX_DIAS_REPOSICION,0) || 'Ã' ||
        NVL(LOCAL.NU_DIAS_ROTACION_PROMEDIO,0)
     FROM  VTTM_LOCAL LOCAL
    WHERE LOCAL.CO_COMPANIA = codigocompania_in  AND
          LOCAL.CO_LOCAL = codigolocal_in;
    RETURN eckcur;
  END;
  /* ************************************************************************ */
  FUNCTION INV_STOCK_PROD_UNIFRAC(codigocompania_in  IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
              codigolocal_in     IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
              codigoproducto_in  IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
       OPEN eckcur FOR SELECT
         NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
         'S',
       TRUNC(CA_STOCK_DISPONIBLE / VA_FRACCION),
       PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE),0)  || 'Ã' ||
        NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
         'S',
       MOD(CA_STOCK_DISPONIBLE,VA_FRACCION),
       0), 0)
    FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL
    WHERE
       PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in AND
          PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in AND
       PRODUCTO_LOCAL.CO_PRODUCTO = codigoproducto_in;
    RETURN eckcur;
  END;

   /* ************************************************************************ */
  FUNCTION INV_LISTA_AJUSTE_INV(codigocompania_in IN LGTV_KARDEX.CO_COMPANIA%TYPE,
        codigolocal_in   IN LGTV_KARDEX.CO_LOCAL%TYPE,
        grupomotivo_in   IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
        fechainicio_in   IN CHAR,
        fechafin_in      IN CHAR)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
       OPEN eckcur FOR SELECT NVL(TO_CHAR(KARDEX.FE_KARDEX,'dd/MM/yyyy HH24:MI'),' ') || 'Ã' ||
               NVL(PROD.CO_PRODUCTO,' ') || 'Ã' ||
               NVL(RTRIM(PROD.DE_PRODUCTO),' ') || 'Ã' ||
         NVL(RTRIM(PROD.DE_UNIDAD_PRODUCTO),' ') || 'Ã' ||
               NVL(RTRIM(MOTIVO.DE_MOTIVO),' ') || 'Ã' ||
           TO_CHAR(NVL(DECODE(KARDEX.IN_PROD_FRACCIONADO,
           'S',
         TRUNC(KARDEX.CA_MOVIMIENTO / KARDEX.VA_FRACCION_FINAL),
         KARDEX.CA_MOVIMIENTO),0), '999,999,990')  || 'Ã' ||
          TO_CHAR(NVL(DECODE(KARDEX.IN_PROD_FRACCIONADO,
           'S',
         MOD(KARDEX.CA_MOVIMIENTO,KARDEX.VA_FRACCION_FINAL),
         0), 0), '999,999,990') || 'Ã' ||
       NVL(TRIM(KARDEX.DE_GLOSA), ' ')
       FROM LGTV_KARDEX KARDEX,
         CMTR_MOTIVO MOTIVO,
         LGTM_PRODUCTO PROD
      WHERE KARDEX.CO_COMPANIA = codigocompania_in AND
          KARDEX.CO_LOCAL = codigolocal_in AND
         KARDEX.CO_GRUPO_MOTIVO_AJUSTE = grupomotivo_in AND
         KARDEX.FE_KARDEX BETWEEN TO_DATE(fechainicio_in,'dd/MM/yyyy')
              AND  TO_DATE(fechafin_in || ' 23:59:59','dd/MM/yyyy HH24:MI:SS')  AND
         KARDEX.CO_GRUPO_MOTIVO_AJUSTE= MOTIVO.CO_GRUPO_MOTIVO AND
         KARDEX.CO_MOTIVO_AJUSTE = MOTIVO.CO_MOTIVO AND
         KARDEX.CO_COMPANIA = PROD.CO_COMPANIA AND
         KARDEX.CO_PRODUCTO = PROD.CO_PRODUCTO
      ORDER BY FE_KARDEX DESC;
    RETURN eckcur;
  END;

  /* ************************************************************************ */
  FUNCTION PRECIO_UNIT_PRODUCTO(codigocompania_in IN LGTM_PRODUCTO.CO_COMPANIA%TYPE,
                                codigoproducto_in  IN LGTM_PRODUCTO.CO_PRODUCTO%TYPE)
           RETURN LGTM_PRODUCTO.VA_PRECIO_COMPRA%TYPE IS
    l_preciounitprod LGTM_PRODUCTO.VA_PRECIO_COMPRA%TYPE;
  BEGIN
 SELECT NVL(VA_PRECIO_COMPRA,0.00)
   INTO l_preciounitprod
   FROM LGTM_PRODUCTO PRODUCTO
  WHERE PRODUCTO.CO_COMPANIA = codigocompania_in
    AND PRODUCTO.CO_PRODUCTO = codigoproducto_in;
 RETURN l_preciounitprod;
 EXCEPTION
   WHEN NO_DATA_FOUND THEN
  RETURN 0.00;
  END;

  /* ************************************************************************ */
  FUNCTION CANTIDAD_MAXIMA_REPONER(codigocompania_in IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                                   codigolocal_in    IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
                                   codigoproducto_in IN LGTD_PEDIDO_PRODUCTO.CO_PRODUCTO%TYPE)
     RETURN LGTR_PRODUCTO_LOCAL.CA_STOCK_REPONER_CALCULA%TYPE IS
  PRAGMA AUTONOMOUS_TRANSACTION;

    minimodias         LGTR_PRODUCTO_LOCAL.NU_MIN_DIAS_REPOSICION%TYPE;
    maximodias         LGTR_PRODUCTO_LOCAL.NU_MAX_DIAS_REPOSICION%TYPE;
    semanasrotacion    LGTR_PRODUCTO_LOCAL.NU_DIAS_ROTACION_PROMEDIO%TYPE;
    cantidadexhibicion LGTR_PRODUCTO_LOCAL.CA_MIN_PROD_EXHIBICION%TYPE;
    stockreponercalc   LGTR_PRODUCTO_LOCAL.CA_STOCK_REPONER_CALCULA%TYPE;
    -- stockminimo        LGTR_PRODUCTO_LOCAL.CA_STOCK_MINIMO%TYPE;
    stockmaximo        LGTR_PRODUCTO_LOCAL.CA_STOCK_MAXIMO%TYPE;
    -- stockdisponible    LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;
    rotacionprod       NUMBER(10,3);
    -- restoprod          NUMBER(10,3);
    stockrequerido     NUMBER(10);
    porcadicpedidorep  VTTM_LOCAL.PC_ADICIONAL_PEDIDO_REPOSICION%TYPE;
    factorbajarotacion VTTM_LOCAL.VA_FACTOR_BAJA_ROTACION%TYPE;
    productotransito   LGTR_PRODUCTO_LOCAL.CA_STOCK_TRANSITO%TYPE;
    stockdisponible    NUMBER(9,3);
  BEGIN
    SELECT DECODE(NVL(PROD_LOCAL.NU_MIN_DIAS_REPOSICION,0), 0, DECODE(NVL(LINEA_LOCAL.NU_MIN_DIAS_REPOSICION,0), 0, NVL(LOCAL.NU_MIN_DIAS_REPOSICION,0), LINEA_LOCAL.NU_MIN_DIAS_REPOSICION), PROD_LOCAL.NU_MIN_DIAS_REPOSICION) NU_MIN_DIAS_REPOSICION,
           DECODE(NVL(PROD_LOCAL.NU_MAX_DIAS_REPOSICION,0), 0, DECODE(NVL(LINEA_LOCAL.NU_MAX_DIAS_REPOSICION,0), 0, NVL(LOCAL.NU_MAX_DIAS_REPOSICION,0), LINEA_LOCAL.NU_MAX_DIAS_REPOSICION), PROD_LOCAL.NU_MAX_DIAS_REPOSICION) NU_MAX_DIAS_REPOSICION,
           DECODE(NVL(PROD_LOCAL.NU_DIAS_ROTACION_PROMEDIO,0), 0, DECODE(NVL(LINEA_LOCAL.NU_DIAS_ROTACION_PROMEDIO,0), 0, NVL(LOCAL.NU_DIAS_ROTACION_PROMEDIO,0), LINEA_LOCAL.NU_DIAS_ROTACION_PROMEDIO), PROD_LOCAL.NU_DIAS_ROTACION_PROMEDIO) NU_DIAS_ROTACION_PROMEDIO,
           NVL(CA_MIN_PROD_EXHIBICION,0) CA_MIN_PROD_EXHIBICION,
           DECODE(PROD_LOCAL.IN_PROD_FRACCIONADO, 'S', NVL(ROUND(PROD_LOCAL.CA_STOCK_DISPONIBLE / PROD_LOCAL.VA_FRACCION,3),0), NVL(PROD_LOCAL.CA_STOCK_DISPONIBLE,0)) STOCK_DISPONIBLE,
           NVL(LOCAL.PC_ADICIONAL_PEDIDO_REPOSICION,0),
           NVL(LOCAL.VA_FACTOR_BAJA_ROTACION,0)
      INTO minimodias,
           maximodias,
           semanasrotacion,
           cantidadexhibicion,
           stockdisponible,
           porcadicpedidorep,
           factorbajarotacion
      FROM LGTR_PRODUCTO_LOCAL PROD_LOCAL,
           VTTM_LOCAL LOCAL,
           LGTM_PRODUCTO PROD,
           LGTR_LINEA_LOCAL LINEA_LOCAL
     WHERE PROD_LOCAL.CO_COMPANIA = codigocompania_in
       AND PROD_LOCAL.CO_LOCAL = codigolocal_in
       AND PROD_LOCAL.CO_PRODUCTO = codigoproducto_in
       AND PROD_LOCAL.CO_COMPANIA = LOCAL.CO_COMPANIA
       AND PROD_LOCAL.CO_LOCAL = LOCAL.CO_LOCAL
       AND PROD_LOCAL.CO_COMPANIA = PROD.CO_COMPANIA
       AND PROD_LOCAL.CO_PRODUCTO = PROD.CO_PRODUCTO
       AND PROD.CO_COMPANIA = LINEA_LOCAL.CO_COMPANIA(+)
       AND PROD.CO_LINEA_PRODUCTO = LINEA_LOCAL.CO_LINEA_PRODUCTO(+)
       AND LINEA_LOCAL.CO_LOCAL(+) = codigolocal_in;

    rotacionprod := INV_CALCULA_ROTACION_PROD(codigocompania_in, codigolocal_in, codigoproducto_in, semanasrotacion, maximodias);

    --Calcula stock maximo incluyendo porcentaje adicional
    IF (rotacionprod = 0) THEN
        stockmaximo := 0;
    ELSIF (((rotacionprod) > 0) AND ((rotacionprod) < factorbajarotacion)) THEN
        stockmaximo := 1;
    ELSIF (((rotacionprod) >= factorbajarotacion) AND ((rotacionprod) < 1)) THEN
        stockmaximo := 1;
    ELSE
        stockmaximo := CEIL(rotacionprod);
    END IF;

    --Añadir los productos en exhibicion
    stockmaximo := stockmaximo + cantidadexhibicion;

    --Añadir los productos en transito
    productotransito := INV_OBTIENE_PROD_TRANSITO(codigocompania_in, codigolocal_in, codigoproducto_in);
    stockrequerido := stockmaximo - productotransito;

    --StockMaximo a Reponer
    IF (stockdisponible >= stockrequerido) THEN
        IF (stockdisponible = 0) THEN
            stockreponercalc := 1;
        ELSE
            stockreponercalc := 0;
        END IF;
    ELSE
        IF (rotacionprod = 0 ) THEN
            stockreponercalc := 1;
        ELSE
            BEGIN
                stockreponercalc := INV_CALCULA_STOCK_REQUERIDO(stockmaximo, stockrequerido, stockdisponible);



                stockreponercalc := CEIL(stockreponercalc * (1 + (porcadicpedidorep/100)));
                IF (stockreponercalc = 0) THEN
                    stockreponercalc := 1;
                END IF;



            END;
        END IF;
    END IF;

    /*
    UPDATE LGTR_PRODUCTO_LOCAL
       SET CA_STK_DISPONIBLE_PED_REP = DECODE(IN_PROD_FRACCIONADO, 'S', NVL(TRUNC(CA_STOCK_DISPONIBLE / VA_FRACCION),0), NVL(CA_STOCK_DISPONIBLE,0)),
           CA_STOCK_TRANSITO_PED_REP = productotransito
     WHERE CO_COMPANIA = codigocompania_in
       AND CO_LOCAL    = codigolocal_in
       AND CO_PRODUCTO = codigoproducto_in;
    */

    COMMIT;

    RETURN stockreponercalc;
  EXCEPTION WHEN NO_DATA_FOUND THEN
    RETURN 0;
  END;


  FUNCTION CANT_MAXIMA_MINIMA_REPONER(codigocompania_in IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                                      codigolocal_in    IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
                                      codigoproducto_in IN LGTD_PEDIDO_PRODUCTO.CO_PRODUCTO%TYPE)
    RETURN EckerdCursor
  IS
    PRAGMA AUTONOMOUS_TRANSACTION;

    minimodias         LGTR_PRODUCTO_LOCAL.NU_MIN_DIAS_REPOSICION%TYPE;
    maximodias         LGTR_PRODUCTO_LOCAL.NU_MAX_DIAS_REPOSICION%TYPE;
    semanasrotacion    LGTR_PRODUCTO_LOCAL.NU_DIAS_ROTACION_PROMEDIO%TYPE;
    cantidadexhibicion LGTR_PRODUCTO_LOCAL.CA_MIN_PROD_EXHIBICION%TYPE;
    stockreponercalc   LGTR_PRODUCTO_LOCAL.CA_STOCK_REPONER_CALCULA%TYPE;
    stockmaximo        LGTR_PRODUCTO_LOCAL.CA_STOCK_MAXIMO%TYPE;
    rotacionprod       NUMBER(10,3);
    stockrequerido     NUMBER(10);
    porcadicpedidorep  VTTM_LOCAL.PC_ADICIONAL_PEDIDO_REPOSICION%TYPE;
    factorbajarotacion VTTM_LOCAL.VA_FACTOR_BAJA_ROTACION%TYPE;
    productotransito   LGTR_PRODUCTO_LOCAL.CA_STOCK_TRANSITO%TYPE;
    stockdisponible    NUMBER(9,3);
    porcminpedidorep   VTTM_LOCAL.PC_DCTO_ADICIONAL%TYPE;
    stockreponermin    LGTR_PRODUCTO_LOCAL.CA_STOCK_REPONER_CALCULA%TYPE;

    eckcur             EckerdCursor;
  BEGIN
    SELECT DECODE(NVL(PROD_LOCAL.NU_MIN_DIAS_REPOSICION,0), 0, DECODE(NVL(LINEA_LOCAL.NU_MIN_DIAS_REPOSICION,0), 0, NVL(LOCAL.NU_MIN_DIAS_REPOSICION,0), LINEA_LOCAL.NU_MIN_DIAS_REPOSICION), PROD_LOCAL.NU_MIN_DIAS_REPOSICION) NU_MIN_DIAS_REPOSICION,
           DECODE(NVL(PROD_LOCAL.NU_MAX_DIAS_REPOSICION,0), 0, DECODE(NVL(LINEA_LOCAL.NU_MAX_DIAS_REPOSICION,0), 0, NVL(LOCAL.NU_MAX_DIAS_REPOSICION,0), LINEA_LOCAL.NU_MAX_DIAS_REPOSICION), PROD_LOCAL.NU_MAX_DIAS_REPOSICION) NU_MAX_DIAS_REPOSICION,
           DECODE(NVL(PROD_LOCAL.NU_DIAS_ROTACION_PROMEDIO,0), 0, DECODE(NVL(LINEA_LOCAL.NU_DIAS_ROTACION_PROMEDIO,0), 0, NVL(LOCAL.NU_DIAS_ROTACION_PROMEDIO,0), LINEA_LOCAL.NU_DIAS_ROTACION_PROMEDIO), PROD_LOCAL.NU_DIAS_ROTACION_PROMEDIO) NU_DIAS_ROTACION_PROMEDIO,
           NVL(CA_MIN_PROD_EXHIBICION,0) CA_MIN_PROD_EXHIBICION,
           DECODE(PROD_LOCAL.IN_PROD_FRACCIONADO, 'S', NVL(ROUND(PROD_LOCAL.CA_STOCK_DISPONIBLE / PROD_LOCAL.VA_FRACCION,3),0), NVL(PROD_LOCAL.CA_STOCK_DISPONIBLE,0)) STOCK_DISPONIBLE,
           NVL(LOCAL.PC_ADICIONAL_PEDIDO_REPOSICION,0),
           NVL(LOCAL.VA_FACTOR_BAJA_ROTACION,0),
           NVL(LOCAL.PC_DCTO_ADICIONAL, 0)
      INTO minimodias,
           maximodias,
           semanasrotacion,
           cantidadexhibicion,
           stockdisponible,
           porcadicpedidorep,
           factorbajarotacion,
           porcminpedidorep
      FROM LGTR_PRODUCTO_LOCAL PROD_LOCAL,
           VTTM_LOCAL LOCAL,
           LGTM_PRODUCTO PROD,
           LGTR_LINEA_LOCAL LINEA_LOCAL
     WHERE PROD_LOCAL.CO_COMPANIA = codigocompania_in
       AND PROD_LOCAL.CO_LOCAL = codigolocal_in
       AND PROD_LOCAL.CO_PRODUCTO = codigoproducto_in
       AND PROD_LOCAL.CO_COMPANIA = LOCAL.CO_COMPANIA
       AND PROD_LOCAL.CO_LOCAL = LOCAL.CO_LOCAL
       AND PROD_LOCAL.CO_COMPANIA = PROD.CO_COMPANIA
       AND PROD_LOCAL.CO_PRODUCTO = PROD.CO_PRODUCTO
       AND PROD.CO_COMPANIA = LINEA_LOCAL.CO_COMPANIA(+)
       AND PROD.CO_LINEA_PRODUCTO = LINEA_LOCAL.CO_LINEA_PRODUCTO(+)
       AND LINEA_LOCAL.CO_LOCAL(+) = codigolocal_in;

    DBMS_OUTPUT.PUT_LINE('minimodias: ' || minimodias);
    DBMS_OUTPUT.PUT_LINE('maximodias: ' || maximodias);
    DBMS_OUTPUT.PUT_LINE('semanasrotacion: ' || semanasrotacion);
    DBMS_OUTPUT.PUT_LINE('cantidadexhibicion: ' || cantidadexhibicion);
    DBMS_OUTPUT.PUT_LINE('stockdisponible: ' || stockdisponible);
    DBMS_OUTPUT.PUT_LINE('porcadicpedidorep: ' || porcadicpedidorep);
    DBMS_OUTPUT.PUT_LINE('factorbajarotacion: ' || factorbajarotacion);
    DBMS_OUTPUT.PUT_LINE('porcminpedidorep: ' || porcminpedidorep);

    -- Calculo la rotación del producto
    rotacionprod := INV_CALCULA_ROTACION_PROD(codigocompania_in, codigolocal_in, codigoproducto_in, semanasrotacion, maximodias);
    DBMS_OUTPUT.PUT_LINE('rotacionprod: ' || rotacionprod);

    --Calcula stock maximo.
    IF (rotacionprod = 0) THEN
        stockmaximo := 0;
    ELSIF (((rotacionprod) > 0) AND ((rotacionprod) < factorbajarotacion)) THEN
        stockmaximo := 1;
    ELSIF (((rotacionprod) >= factorbajarotacion) AND ((rotacionprod) < 1)) THEN
        stockmaximo := 1;
    ELSE
        stockmaximo := CEIL(rotacionprod);
    END IF;

    DBMS_OUTPUT.PUT_LINE('stockmaximo: ' || stockmaximo);

    --Agrego el stock por cantidades en exhibición.
    stockmaximo := stockmaximo + cantidadexhibicion;

    DBMS_OUTPUT.PUT_LINE('stockmaximo + cantidadexhibicion: ' || stockmaximo);

    --Quito el stock que tengo en transito
    productotransito := INV_OBTIENE_PROD_TRANSITO(codigocompania_in, codigolocal_in, codigoproducto_in);
    stockrequerido := stockmaximo - productotransito;

    DBMS_OUTPUT.PUT_LINE('productotransito: ' || productotransito);
    DBMS_OUTPUT.PUT_LINE('stockrequerido: ' || stockrequerido);

    --StockMaximo a Reponer
    IF (stockdisponible >= stockrequerido) THEN
        IF (stockdisponible = 0) THEN
            stockreponercalc := 1;
            stockreponermin := 0;
        ELSE
            stockreponercalc := 0;
            stockreponermin := 0;
        END IF;
    ELSE
        IF (rotacionprod = 0 ) THEN
            stockreponercalc := 1;
            stockreponermin := 0;
        ELSE
            BEGIN
                stockreponercalc := INV_CALCULA_STOCK_REQUERIDO(stockmaximo, stockrequerido, stockdisponible);

                DBMS_OUTPUT.PUT_LINE('stockreponercalc: ' || stockreponercalc);

                stockreponermin := CEIL(stockreponercalc * (1 - (porcminpedidorep/100)));

                DBMS_OUTPUT.PUT_LINE('stockreponermin: ' || stockreponermin);

                stockreponercalc := CEIL(stockreponercalc * (1 + (porcadicpedidorep/100)));

                DBMS_OUTPUT.PUT_LINE('stockreponercalc: ' || stockreponercalc);
                IF (stockreponercalc = 0) THEN
                    stockreponercalc := 1;
                END IF;
            END;
        END IF;
    END IF;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('stockreponermin: ' || stockreponermin);
    DBMS_OUTPUT.PUT_LINE('stockreponercalc: ' || stockreponercalc);

    OPEN eckcur FOR SELECT stockreponercalc || 'Ã' || stockreponermin FROM DUAL;

    RETURN eckcur;
  EXCEPTION WHEN NO_DATA_FOUND THEN
    OPEN eckcur FOR SELECT 0 || 'Ã' || 0 FROM DUAL;

    RETURN eckcur;
  END;

  /* ************************************************************************ */
  FUNCTION OBTIENE_INFO_TRANSP_GUIA(codigocompania_in  IN LGTM_TRANSF_PRODUCTO.CO_COMPANIA%TYPE,
         codigolocaldest_in  IN LGTM_TRANSF_PRODUCTO.CO_LOCAL%TYPE)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
          OPEN eckcur FOR SELECT NVL(COMPANIA.DE_COMPANIA,' ') || 'Ã' ||
               NVL(COMPANIA.NU_RUC_COMPANIA,' ') || 'Ã' ||
               NVL(LOCAL.DE_DIRECCION_LOCAL,' ') || 'Ã' ||
               NVL(PROVEEDOR.DE_PROVEEDOR,' ') || 'Ã' ||
               NVL(PROVEEDOR.NU_DOC_IDENTIDAD,' ') || 'Ã' ||
               NVL(PROVEEDOR.DE_DIRECCION_PROVEEDOR,' ')  || 'Ã' ||
               NVL(LOCAL.NU_PLACA_TRANSPORTE,' ')
       FROM VTTM_LOCAL LOCAL,
           CMTM_COMPANIA COMPANIA,
         VTTM_PROVEEDOR PROVEEDOR
      WHERE LOCAL.CO_COMPANIA = codigocompania_in AND
          LOCAL.CO_LOCAL = codigolocaldest_in AND
         LOCAL.CO_COMPANIA = COMPANIA.CO_COMPANIA AND
         LOCAL.CO_COMPANIA = PROVEEDOR.CO_COMPANIA AND
         LOCAL.CO_PROVEEDOR_TRANSP = PROVEEDOR.CO_PROVEEDOR;
    RETURN eckcur;
  END;

  /* ************************************************************************* */
  FUNCTION INV_GRABA_CABE_GUIA_INGRESO(codigocompania_in         IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                                    codigolocal_in            IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
                                    tipodocumento_in          IN LGTC_RECEPCION_PRODUCTO.TI_DOCUMENTO_RECEPCION%TYPE,
                                    numerodocumento_in        IN LGTC_RECEPCION_PRODUCTO.TI_DOCUMENTO_RECEPCION%TYPE,
                                    nombreproveedor_in        IN LGTC_RECEPCION_PRODUCTO.NO_PROVEEDOR%TYPE,
                                    idrecepcionproducto_in    IN LGTC_RECEPCION_PRODUCTO.ID_RECEPCION_PRODUCTO%TYPE,
                                    fecharecepcionproducto_in IN CHAR,
                                    cantidaditemrecepcion_in  IN LGTC_RECEPCION_PRODUCTO.CA_ITEM_RECEPCION%TYPE,
                                    codigolocalorigen_in      IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL_ORIGEN%TYPE,
                                    valortotalcompra_in       IN LGTC_RECEPCION_PRODUCTO.VA_TOTAL_COMPRA%TYPE,
                                    coproveedor_in            IN LGTC_RECEPCION_PRODUCTO.CO_PROVEEDOR%TYPE,
                                    intipocontrol_in          IN LGTC_RECEPCION_PRODUCTO.IN_TIPO_CONTROL %TYPE,
                                    intipoorden_in          IN LGTC_RECEPCION_PRODUCTO.IN_TIPO_ORDEN %TYPE)
  RETURN LGTC_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE  IS
     l_numeracion       CMTR_NUMERACION.NU_SEC_NUMERACION%TYPE;
     l_numeroguiaingreso LGTC_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE;
     l_local VTTM_LOCAL.CO_LOCAL_SAP%TYPE;
  BEGIN
     l_numeracion := Ptovta_Utility.OBTENER_NUMERACION(codigocompania_in, codigolocal_in, '013');
     l_numeroguiaingreso := Eckerd_Utility.COMPLETAR_CON_SIMBOLO(l_numeracion, 6, '0', 'I');

     IF (l_numeracion > 0) THEN

        SELECT CO_LOCAL_SAP INTO l_local
          FROM VTTM_LOCAL
         WHERE CO_COMPANIA = codigocompania_in
           AND CO_LOCAL = codigolocal_in;

        l_numeroguiaingreso := l_local || l_numeroguiaingreso;

       -- Agrega registro
          INSERT INTO LGTC_RECEPCION_PRODUCTO
              (CO_COMPANIA, CO_LOCAL, NU_RECEPCION_PRODUCTO, TI_DOCUMENTO_RECEPCION,
               NU_DOCUMENTO_RECEPCION, NO_PROVEEDOR, ID_RECEPCION_PRODUCTO, FE_RECEPCION_PRODUCTO,
               CA_GUIA_RECEPCION, CA_ITEM_RECEPCION, CO_LOCAL_ORIGEN, IN_AFECTA_STOCK,
               ES_RECEPCION_PRODUCTO, ID_CREA_RECEPCION_PRODUCTO, FE_CREA_RECEPCION_PRODUCTO, VA_TOTAL_COMPRA, CO_PROVEEDOR,
               IN_TIPO_CONTROL,IN_TIPO_ORDEN)
          VALUES (codigocompania_in, codigolocal_in, l_numeroguiaingreso, tipodocumento_in,
                  numerodocumento_in, nombreproveedor_in, idrecepcionproducto_in, TO_DATE(fecharecepcionproducto_in,'dd/MM/yyyy'),
                  1, cantidaditemrecepcion_in, codigolocalorigen_in, 'S',
                  'C', idrecepcionproducto_in, SYSDATE, valortotalcompra_in, coproveedor_in,
                  intipocontrol_in,intipoorden_in);

     --COMMIT;
        Ptovta_Utility.ACTUALIZAR_NUMERACION(codigocompania_in,codigolocal_in,'013',idrecepcionproducto_in);
     END IF;

     RETURN l_numeroguiaingreso;
  END;

  /* ************************************************************************ */
  PROCEDURE INV_GRABA_DET_GUIA_INGRESO( codigocompania_in       IN LGTD_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                                  codigolocal_in          IN LGTD_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
          numerorecepcion      IN LGTD_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE,
          numeroitem_in           IN LGTD_RECEPCION_PRODUCTO.NU_ITEM_RECEPCION_GUIA%TYPE,
          codigoproducto_in       IN LGTD_RECEPCION_PRODUCTO.CO_PRODUCTO%TYPE,
          cantidadentera_in       IN LGTD_RECEPCION_PRODUCTO.CA_ENVIADA_ENTERA%TYPE,
          cantidadfraccion_in     IN LGTD_RECEPCION_PRODUCTO.CA_ENVIADA_FRACCION%TYPE,
          fechavenc_in            IN CHAR,
          idcrearecepcion_in      IN LGTD_RECEPCION_PRODUCTO.ID_CREA_DET_RECEPCION_PRODUCTO%TYPE,
          preciocompra_in         IN LGTD_RECEPCION_PRODUCTO.VA_PRECIO_COMPRA%TYPE
          ) IS
 fechavenc         LGTD_RECEPCION_PRODUCTO.FE_VENCE_PRODUCTO%TYPE;
 inprodfraccionado LGTR_PRODUCTO_LOCAL.IN_PROD_FRACCIONADO%TYPE;
 valorfraccion     LGTR_PRODUCTO_LOCAL.VA_FRACCION%TYPE;
 preciopromedio    LGTM_PRODUCTO.VA_PRECIO_PROMEDIO%TYPE;
 unidadproducto    LGTM_PRODUCTO.DE_UNIDAD_PRODUCTO%TYPE;
  BEGIN
   SELECT PLOCAL.IN_PROD_FRACCIONADO, PLOCAL.VA_FRACCION, PMAESTRO.VA_PRECIO_PROMEDIO,
          DECODE(PLOCAL.IN_PROD_FRACCIONADO,'S',NVL(PLOCAL.DE_UNIDAD_FRACCION,' '),NVL(PMAESTRO.DE_UNIDAD_PRODUCTO,' '))
     INTO inprodfraccionado, valorfraccion, preciopromedio, unidadproducto
   FROM LGTR_PRODUCTO_LOCAL PLOCAL,
        LGTM_PRODUCTO PMAESTRO
  WHERE plocal.CO_COMPANIA = codigocompania_in AND
        plocal.CO_LOCAL = codigolocal_in AND
     plocal.CO_PRODUCTO = codigoproducto_in AND
     plocal.CO_COMPANIA = pmaestro.CO_COMPANIA AND
     plocal.CO_PRODUCTO = pmaestro.CO_PRODUCTO FOR UPDATE;


    IF (fechavenc_in IS NOT NULL) THEN
       fechavenc := TO_DATE(fechavenc_in, 'dd/MM/yyyy');
     ELSE
        fechavenc := NULL;
     END IF;
    INSERT INTO LGTD_RECEPCION_PRODUCTO
       (CO_COMPANIA, CO_LOCAL,
     NU_RECEPCION_PRODUCTO, NU_ITEM_RECEPCION_GUIA,
     CO_PRODUCTO, NU_REVISION_PRODUCTO, CA_ENVIADA_ENTERA,
     CA_ENVIADA_FRACCION, CA_CONFIRMADA_ENTERA,
     CA_CONFIRMADA_FRACCION, IN_PROD_FRACCIONADO,
     VA_FRACCION, VA_PRECIO_PROMEDIO,
     IN_CONFIRMA_RECEPCION,IN_AFECTA_PRODUCTO,
     FE_VENCE_PRODUCTO, ES_DET_RECEPCION_PRODUCTO,
     ID_CREA_DET_RECEPCION_PRODUCTO, FE_CREA_DET_RECEPCION_PRODUCTO,
     va_precio_compra,DE_UNIDAD_PRODUCTO)
         VALUES (codigocompania_in, codigolocal_in,
     numerorecepcion, numeroitem_in,
      codigoproducto_in, '0', cantidadentera_in,
      cantidadfraccion_in, cantidadentera_in,
      cantidadfraccion_in, inprodfraccionado,
      valorfraccion, preciopromedio,
      'S','S',
      TO_DATE(fechavenc_in,'dd/MM/yyyy'), 'A',
      idcrearecepcion_in, SYSDATE,
      preciocompra_in, unidadproducto);

 COMMIT;
  END;

  /* ************************************************************************ */
  FUNCTION VERIFICA_PEDIDO_REALIZADO(codigocompania_in   IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                codigolocal_in    IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE)
            RETURN NUMBER IS
    pedidoRealizado       NUMBER;
  BEGIN
      SELECT COUNT(TO_DATE(fe_solicita_pedido,'dd/MM/yyyy'))
   INTO pedidoRealizado
   FROM LGTC_PEDIDO_PRODUCTO
   WHERE CO_COMPANIA = codigocompania_in
     AND CO_LOCAL = codigolocal_in
     AND TI_PEDIDO_PRODUCTO = 'RP'
     AND TO_CHAR(FE_SOLICITA_PEDIDO,'dd/MM/yyyy') = TO_CHAR(SYSDATE,'dd/MM/yyyy');
  RETURN pedidoRealizado;
  END;

/* ******************************************************************************** */
  FUNCTION OBTIENE_SUM_PED_REP_ANTERIOR(codigocompania_in   IN LGTD_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
               codigolocal_in     IN LGTD_PEDIDO_PRODUCTO.CO_LOCAL%TYPE)
           RETURN LGTD_PEDIDO_PRODUCTO.CA_PEDIDA%TYPE IS
 cantidadPedida LGTD_PEDIDO_PRODUCTO.CA_PEDIDA%TYPE;
    CURSOR cantidad IS
    SELECT SUM(ca_pedida) pedido
     FROM LGTD_PEDIDO_PRODUCTO pedpro
    WHERE CO_COMPANIA = codigocompania_in
      AND CO_LOCAL    = codigolocal_in
      AND TI_PEDIDO_PRODUCTO = 'RP'
   GROUP BY pedpro.NU_PEDIDO_PRODUCTO
   ORDER BY pedpro.NU_PEDIDO_PRODUCTO DESC;
  BEGIN
      cantidadPedida := 1;
    FOR cantPed IN cantidad
   LOOP
      cantidadPedida := cantPed.pedido;
    EXIT;
   END LOOP;
   RETURN cantidadPedida;
  END;

  /* ******************************************************************************** */
  FUNCTION OBTIENE_CANT_PED_REP_ANTERIOR(codigocompania_in   IN LGTD_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
               codigolocal_in     IN LGTD_PEDIDO_PRODUCTO.CO_LOCAL%TYPE)
           RETURN LGTD_PEDIDO_PRODUCTO.CA_PEDIDA%TYPE IS
 cantidadPedida LGTD_PEDIDO_PRODUCTO.CA_PEDIDA%TYPE;
    CURSOR cantidad IS
    SELECT COUNT(ca_pedida)pedido
   FROM LGTD_PEDIDO_PRODUCTO pedpro
   WHERE co_compania=codigocompania_in
   AND   co_local=codigolocal_in
   AND ti_pedido_producto = 'RP'
   GROUP BY pedpro.NU_PEDIDO_PRODUCTO
   ORDER BY pedpro.NU_PEDIDO_PRODUCTO DESC;
  BEGIN
      cantidadPedida := 1;
    FOR cantPed IN cantidad
   LOOP
      cantidadPedida := cantPed.pedido;
    EXIT;
   END LOOP;
   RETURN cantidadPedida;
  END;

  /* ******************************************************************************** */
 FUNCTION OBTIENE_CANT_PED_REP_ANT_PROD(codigocompania_in   IN LGTD_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                        codigolocal_in      IN LGTD_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
                   codigoproducto_in   IN LGTD_PEDIDO_PRODUCTO.CO_PRODUCTO%TYPE)
           RETURN LGTD_PEDIDO_PRODUCTO.CA_PEDIDA%TYPE IS
 cantidadPedida LGTD_PEDIDO_PRODUCTO.CA_PEDIDA%TYPE;
    CURSOR cantidad IS
    SELECT NVL(ca_pedida,0)pedido
     FROM LGTD_PEDIDO_PRODUCTO pedpro
    WHERE CO_COMPANIA = codigocompania_in
      AND CO_LOCAL    = codigolocal_in
      AND TI_PEDIDO_PRODUCTO = 'RP'
      AND CO_PRODUCTO = codigoproducto_in
   ORDER BY pedpro.NU_PEDIDO_PRODUCTO DESC;
  BEGIN
      cantidadPedida := 0;
    FOR cantPed IN cantidad
   LOOP
      cantidadPedida := cantPed.pedido;
    EXIT;
   END LOOP;
   RETURN cantidadPedida;
  END;

  /* ******************************************************************************** */
  FUNCTION OBTIENE_CANT_ITEM_PROD_ANT(codigocompania_in  IN LGTD_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                   codigolocal_in     IN LGTD_PEDIDO_PRODUCTO.CO_LOCAL%TYPE)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
 v_fechaUltPedido LGTC_PEDIDO_PRODUCTO.FE_SOLICITA_PEDIDO%TYPE;
 BEGIN
   --OBTIENE LA ULTIMA FECHA DEL PEDIDO DE REPOSICION
  SELECT MAX(FE_SOLICITA_PEDIDO)
    INTO v_fechaultpedido
    FROM LGTC_PEDIDO_PRODUCTO
   WHERE CO_COMPANIA = CODIGOCOMPANIA_IN
     AND CO_LOCAL = CODIGOLOCAL_IN
     AND TI_PEDIDO_PRODUCTO = 'RP';

  --OBTIENE LA CANTIDAD QUE SE HA VENDIDO ENTRE EL ULTIMO PEDIDO DE REPOSICION A LA FECHA
  OPEN eckcur FOR SELECT  TO_CHAR(COUNT(*), '999,990') || 'Ã' ||
            TO_CHAR(NVL(SUM(DECODE(NVL(DET_VEN.VA_FRACCION, 0),
                   0,
                DET_VEN.CA_ATENDIDA,
                DET_VEN.CA_ATENDIDA / DET_VEN.VA_FRACCION)
               ), 0), '999,999,990')|| 'Ã' ||
            v_fechaultpedido
      FROM    VTTC_PEDIDO_VENTA PED_VEN,
           VTTD_PEDIDO_VENTA DET_VEN
      WHERE  PED_VEN.CO_COMPANIA = DET_VEN.CO_COMPANIA AND
        PED_VEN.CO_LOCAL = DET_VEN.CO_LOCAL AND
        PED_VEN.NU_PEDIDO = DET_VEN.NU_PEDIDO AND
        PED_VEN.CO_COMPANIA = codigocompania_in AND
        PED_VEN.CO_LOCAL = codigolocal_in AND
        PED_VEN.ES_PEDIDO_VENTA = 'C' AND
        PED_VEN.IN_PEDIDO_ANULADO = 'N' AND
        PED_VEN.FE_PEDIDO BETWEEN v_fechaultpedido AND SYSDATE;
   RETURN eckcur;
  END;

  /* ************************************************************************ */
  PROCEDURE ACTUALIZA_INDICADOR_PEDIDO(codigocompania_in   IN VTTM_LOCAL.CO_COMPANIA%TYPE,
                  codigolocal_in    IN VTTM_LOCAL.CO_LOCAL%TYPE)
             IS
  BEGIN
      UPDATE VTTM_LOCAL
      SET IN_PED_REP_EN_PROC = DECODE(IN_PED_REP_EN_PROC,'N','S','N'),
          FE_MOD_LOCAL = SYSDATE
    WHERE CO_COMPANIA = codigocompania_in
      AND CO_LOCAL    = codigolocal_in;
   COMMIT;
  END;

  /* ************************************************************************ */
  PROCEDURE ACTUALIZA_IND_PED_NO_COMMIT(codigocompania_in   IN VTTM_LOCAL.CO_COMPANIA%TYPE,
                  codigolocal_in    IN VTTM_LOCAL.CO_LOCAL%TYPE)
             IS
  BEGIN
      UPDATE VTTM_LOCAL
      SET IN_PED_REP_EN_PROC = DECODE(IN_PED_REP_EN_PROC,'N','S','N'),
          FE_MOD_LOCAL = SYSDATE
    WHERE CO_COMPANIA = codigocompania_in
      AND CO_LOCAL    = codigolocal_in;
  END;

  /* ************************************************************************ */
  FUNCTION DEVUELVE_INDICADOR_PED_LOCAL(codigocompania_in   IN VTTM_LOCAL.CO_COMPANIA%TYPE,
                   codigolocal_in     IN VTTM_LOCAL.CO_LOCAL%TYPE)
            RETURN VTTM_LOCAL.IN_PED_REP_EN_PROC%TYPE IS
  indicador VTTM_LOCAL.IN_PED_REP_EN_PROC%TYPE;
  BEGIN
    SELECT IN_PED_REP_EN_PROC
   INTO indicador
   FROM   VTTM_LOCAL
   WHERE  co_compania = codigocompania_in
   AND    co_local = codigolocal_in;
  RETURN indicador;
 END;

 /* ************************************************************************ */
 FUNCTION RELACIONCLIENTES (codigocompania_in IN VTTC_PEDIDO_VENTA.CO_COMPANIA%TYPE,
                  codigolocal_in    IN VTTC_PEDIDO_VENTA.CO_LOCAL%TYPE,
            nombrecliente_in IN CHAR)
        RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
 OPEN eckcur FOR SELECT CLIENTE.CO_CLIENTE_LOCAL || 'Ã' ||
                           NVL(TIPOCLIENTE.DE_CORTA_TIPO_CLIENTE,' ') || 'Ã' ||
                           DECODE(CLIENTE.TI_CLIENTE,'001',NVL(TRIM(CLIENTE.AP_PATERNO_CLIENTE),'...')||' '||NVL(TRIM(CLIENTE.AP_MATERNO_CLIENTE),'...')||' '||NVL(TRIM(CLIENTE.NO_CLIENTE),'...'),NVL(TRIM(CLIENTE.DE_RAZON_SOCIAL),'...')) || 'Ã' ||
                           'R.U.C.' || 'Ã' ||
                           NVL(NU_DOC_IDENTIDAD,' ') || 'Ã' ||
                           NVL(DOCUMENTO.DE_CORTA_TIPO_DOC_IDENTIDAD,' ') || 'Ã' ||
                           NVL(NU_DOC_IDENTIDAD,' ')
                      FROM VTTR_CLIENTE_LOCAL CLIENTE,
                        VTTR_TIPO_CLIENTE TIPOCLIENTE,
                           CMTR_TIPO_DOC_IDENTIDAD DOCUMENTO
                     WHERE cliente.CO_COMPANIA = codigocompania_in
        AND cliente.CO_LOCAL = codigolocal_in
        AND CLIENTE.CO_COMPANIA = TIPOCLIENTE.CO_COMPANIA
        AND CLIENTE.TI_CLIENTE = TIPOCLIENTE.TI_CLIENTE
                       AND CLIENTE.TI_DOC_IDENTIDAD = DOCUMENTO.TI_DOC_IDENTIDAD(+)
        AND DECODE(CLIENTE.TI_CLIENTE,'001',NVL(TRIM(CLIENTE.AP_PATERNO_CLIENTE),'...')||' '||NVL(TRIM(CLIENTE.AP_MATERNO_CLIENTE),'...')||' '||NVL(TRIM(CLIENTE.NO_CLIENTE),'...'),NVL(TRIM(CLIENTE.DE_RAZON_SOCIAL),'...')) LIKE nombrecliente_in||'%';
    RETURN eckcur;
  END;

  /* ************************************************************************ */
  PROCEDURE UPDATE_NUMERACION_GUIA_SALIDA(codigocompania_in   IN CMTR_NUMERACION.CO_COMPANIA%TYPE,
                     codigolocal_in    IN CMTR_NUMERACION.CO_LOCAL%TYPE,
                     numerosecuencia_in  IN  CHAR)
             IS
  BEGIN
    UPDATE CMTR_NUMERACION
    SET nu_sec_numeracion = TO_NUMBER(numerosecuencia_in),
        FE_MOD_NUMERACION = SYSDATE
  WHERE co_compania = codigocompania_in
    AND co_local = codigolocal_in
    AND co_numeracion = '008';
  COMMIT;
  END;

  /* ************************************************************************ */
  /*DEVUELVE EL ULTIMO NUMERO DE SECUENCIA DE GUIA DE SALIDA*/
  FUNCTION DEVUELVE_NUMERO_SEC_ANTERIOR(codigocompania_in   IN VTTM_LOCAL.CO_COMPANIA%TYPE,
                     codigolocal_in   IN VTTM_LOCAL.CO_LOCAL%TYPE)
            RETURN CMTR_NUMERACION.DE_NUMERACION%TYPE IS
  vnumero CMTR_NUMERACION.DE_NUMERACION%TYPE;
  BEGIN
   SELECT NU_SEC_NUMERACION
     INTO vnumero
    FROM CMTR_NUMERACION
   WHERE CO_COMPANIA = codigocompania_in
     AND CO_LOCAL = codigolocal_in
     AND CO_NUMERACION = '008';
  vnumero := Eckerd_Utility.COMPLETAR_CON_SIMBOLO(vnumero, 10, '0', 'I');
  RETURN vnumero;
  END;


  /* ************************************************************************* */
  FUNCTION INV_GRABA_CABE_GUIA_INGRESO(codigocompania_in         IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                                    codigolocal_in            IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
                                    tipodocumento_in          IN LGTC_RECEPCION_PRODUCTO.TI_DOCUMENTO_RECEPCION%TYPE,
                                    numerodocumento_in        IN LGTC_RECEPCION_PRODUCTO.TI_DOCUMENTO_RECEPCION%TYPE,
                                    nombreproveedor_in        IN LGTC_RECEPCION_PRODUCTO.NO_PROVEEDOR%TYPE,
                                    idrecepcionproducto_in    IN LGTC_RECEPCION_PRODUCTO.ID_RECEPCION_PRODUCTO%TYPE,
                                    fecharecepcionproducto_in IN CHAR,
                                    cantidaditemrecepcion_in  IN LGTC_RECEPCION_PRODUCTO.CA_ITEM_RECEPCION%TYPE,
                                    codigolocalorigen_in      IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL_ORIGEN%TYPE,
                                    valortotalcompra_in       IN LGTC_RECEPCION_PRODUCTO.VA_TOTAL_COMPRA%TYPE,
                                    indicadororigen_in        IN LGTC_RECEPCION_PRODUCTO.IN_ORIGEN_GUIA_INGRESO%TYPE,
                                    nuSolictudPedido_in       IN LGTC_RECEPCION_PRODUCTO.NU_SOLICITUD_PEDIDO%TYPE DEFAULT NULL,
                                    cantRecepcion_in          IN LGTC_RECEPCION_PRODUCTO.CA_GUIA_RECEPCION%TYPE DEFAULT NULL,
                                    coproveedor_in            IN LGTC_RECEPCION_PRODUCTO.CO_PROVEEDOR%TYPE DEFAULT NULL,
                                    intipocontrol_in          IN LGTC_RECEPCION_PRODUCTO.IN_TIPO_CONTROL%TYPE DEFAULT NULL)
    RETURN LGTC_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE  IS
     l_numeracion       CMTR_NUMERACION.NU_SEC_NUMERACION%TYPE;
     l_numeroguiaingreso LGTC_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE;
  BEGIN
    l_numeracion := Ptovta_Utility.OBTENER_NUMERACION(codigocompania_in, codigolocal_in, '013');

    IF nuSolictudPedido_in IS NOT NULL THEN
        l_numeroguiaingreso := '099' || Eckerd_Utility.COMPLETAR_CON_SIMBOLO(l_numeracion, 7, '0', 'I');
    ELSE
        l_numeroguiaingreso := Eckerd_Utility.COMPLETAR_CON_SIMBOLO(l_numeracion, 10, '0', 'I');
    END IF;

    IF (l_numeracion > 0) THEN
      INSERT INTO LGTC_RECEPCION_PRODUCTO (
                        CO_COMPANIA, CO_LOCAL, NU_RECEPCION_PRODUCTO, TI_DOCUMENTO_RECEPCION,
                        NU_DOCUMENTO_RECEPCION, NO_PROVEEDOR, ID_RECEPCION_PRODUCTO, FE_RECEPCION_PRODUCTO,
                        CA_GUIA_RECEPCION, CA_ITEM_RECEPCION, CO_LOCAL_ORIGEN, IN_AFECTA_STOCK,
                        ES_RECEPCION_PRODUCTO, ID_CREA_RECEPCION_PRODUCTO, FE_CREA_RECEPCION_PRODUCTO,
                        VA_TOTAL_COMPRA, TI_RECEPCION, IN_ORIGEN_GUIA_INGRESO, NU_SOLICITUD_PEDIDO,
                        CA_PROD_RECEPCION, CO_PROVEEDOR, IN_TIPO_CONTROL)
                VALUES (codigocompania_in, codigolocal_in, l_numeroguiaingreso, tipodocumento_in,
                        numerodocumento_in, nombreproveedor_in, idrecepcionproducto_in, TO_DATE(fecharecepcionproducto_in,'dd/MM/yyyy'),
                        1, cantidaditemrecepcion_in, codigolocalorigen_in, 'S',
                        'C', idrecepcionproducto_in, SYSDATE,
                        valortotalcompra_in, 'M', indicadororigen_in, nuSolictudPedido_in,
                        cantRecepcion_in, coproveedor_in, intipocontrol_in );

      UPDATE LGTC_SOLICITUD_PEDIDO
         SET FE_MOD_SOLICITUD_PEDIDO = SYSDATE,
             ID_MOD_SOLICITUD_PEDIDO = idrecepcionproducto_in,
             ES_SOLICITUD_PEDIDO = 'R'
       WHERE CO_COMPANIA = '001'
         AND CO_LOCAL = '099'
         AND NU_SOLICITUD_PEDIDO = nuSolictudPedido_in;

      UPDATE LGTD_SOLICITUD_PEDIDO
         SET FE_MOD_DET_SOLICITUD_PEDIDO = SYSDATE,
             ID_MOD_DET_SOLICITUD_PEDIDO = idrecepcionproducto_in,
             ES_DET_SOLICITUD_PEDIDO = 'R'
       WHERE CO_COMPANIA = '001'
         AND CO_LOCAL = '099'
         AND NU_SOLICITUD_PEDIDO = nuSolictudPedido_in;

      Ptovta_Utility.ACTUALIZAR_NUMERACION_NOCOMMIT(codigocompania_in,codigolocal_in,'013',idrecepcionproducto_in);
    END IF;

    RETURN l_numeroguiaingreso;
  END;

  /* ************************************************************************ */
  PROCEDURE INV_GRABA_DET_GUIA_INGRESO(codigocompania_in IN LGTD_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                                  codigolocal_in          IN LGTD_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
          numerorecepcion      IN LGTD_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE,
          numeroitem_in           IN LGTD_RECEPCION_PRODUCTO.NU_ITEM_RECEPCION_GUIA%TYPE,
          codigoproducto_in       IN LGTD_RECEPCION_PRODUCTO.CO_PRODUCTO%TYPE,
          cantidadentera_in       IN LGTD_RECEPCION_PRODUCTO.CA_ENVIADA_ENTERA%TYPE,
          cantidadfraccion_in     IN LGTD_RECEPCION_PRODUCTO.CA_ENVIADA_FRACCION%TYPE,
          fechavenc_in            IN CHAR,
          lote_in     IN LGTD_RECEPCION_PRODUCTO.NU_LOTE%TYPE,
          idcrearecepcion_in      IN LGTD_RECEPCION_PRODUCTO.ID_CREA_DET_RECEPCION_PRODUCTO%TYPE,
          preciocompra_in         IN LGTD_RECEPCION_PRODUCTO.VA_PRECIO_COMPRA%TYPE,
          totalitem_in   IN LGTD_RECEPCION_PRODUCTO.VA_TOTAL_ITEM%TYPE)
           IS
 fechavenc         LGTD_RECEPCION_PRODUCTO.FE_VENCE_PRODUCTO%TYPE;
 inprodfraccionado LGTR_PRODUCTO_LOCAL.IN_PROD_FRACCIONADO%TYPE;
 valorfraccion     LGTR_PRODUCTO_LOCAL.VA_FRACCION%TYPE;
 preciopromedio    LGTM_PRODUCTO.VA_PRECIO_PROMEDIO%TYPE;
 unidadproducto    LGTM_PRODUCTO.DE_UNIDAD_PRODUCTO%TYPE;
  BEGIN
   SELECT PLOCAL.IN_PROD_FRACCIONADO, PLOCAL.VA_FRACCION, PMAESTRO.VA_PRECIO_PROMEDIO,
          DECODE(PLOCAL.IN_PROD_FRACCIONADO,'S',NVL(PLOCAL.DE_UNIDAD_FRACCION,' '),NVL(PMAESTRO.DE_UNIDAD_PRODUCTO,' '))
   INTO inprodfraccionado, valorfraccion, preciopromedio, unidadproducto
   FROM LGTR_PRODUCTO_LOCAL PLOCAL,
        LGTM_PRODUCTO PMAESTRO
  WHERE plocal.CO_COMPANIA = codigocompania_in AND
        plocal.CO_LOCAL = codigolocal_in AND
     plocal.CO_PRODUCTO = codigoproducto_in AND
     plocal.CO_COMPANIA = pmaestro.CO_COMPANIA AND
     plocal.CO_PRODUCTO = pmaestro.CO_PRODUCTO FOR UPDATE;


    IF (fechavenc_in IS NOT NULL) THEN
       fechavenc := TO_DATE(fechavenc_in,'MM/yyyy');
 ELSE
    fechavenc := NULL;
 END IF;
    INSERT INTO LGTD_RECEPCION_PRODUCTO
       (CO_COMPANIA, CO_LOCAL,
     NU_recepcion_producto, NU_ITEM_RECEPCION_GUIA,
     CO_PRODUCTO, CA_ENVIADA_ENTERA,
     CA_ENVIADA_FRACCION, CA_CONFIRMADA_ENTERA,
     CA_CONFIRMADA_FRACCION, IN_PROD_FRACCIONADO,
     VA_FRACCION, VA_PRECIO_PROMEDIO,
     IN_CONFIRMA_RECEPCION,IN_AFECTA_PRODUCTO,
     FE_VENCE_PRODUCTO, NU_LOTE, ES_DET_RECEPCION_PRODUCTO,
     ID_CREA_DET_RECEPCION_PRODUCTO, FE_CREA_DET_RECEPCION_PRODUCTO,
     va_precio_compra,
     nu_pagina_guia,
     va_total_item,DE_UNIDAD_PRODUCTO)
         VALUES (codigocompania_in, codigolocal_in,
     numerorecepcion, numeroitem_in,
      codigoproducto_in, cantidadentera_in,
      cantidadfraccion_in, cantidadentera_in,
      cantidadfraccion_in, inprodfraccionado,
      valorfraccion, preciopromedio,
      'S','S',
      TO_DATE(fechavenc_in,'MM/yyyy'), lote_in, 'A',
      idcrearecepcion_in, SYSDATE,
      preciocompra_in,
      1,
      totalitem_in, unidadproducto);

 --COMMIT;
  END;


  /* ************************************************************************ */
  PROCEDURE INV_GRABA_KARDEX_GUIA_IN(codigocompania_in   IN LGTV_KARDEX.CO_COMPANIA%TYPE,
       codigolocal_in    IN LGTV_KARDEX.CO_LOCAL%TYPE,
       codigoproducto_in    IN LGTV_KARDEX.CO_PRODUCTO%TYPE,
       cantmovimiento_in    IN LGTV_KARDEX.CA_MOVIMIENTO%TYPE,
       tipodocumento_in     IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
       numerodocumento_in   IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
       idcreakardex_in      IN LGTV_KARDEX.ID_CREA_KARDEX%TYPE,
       motivokardex_in   IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE
       ) IS
    l_numeracion        CMTR_NUMERACION.NU_SEC_NUMERACION%TYPE;
    l_inprodfraccionado LGTR_PRODUCTO_LOCAL.IN_PROD_FRACCIONADO%TYPE;
    l_codigounidadventa LGTR_PRODUCTO_LOCAL.CO_UNIDAD_VENTA_FRACCION%TYPE;
    l_stockdisponible   LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;
 l_valorfraccion     LGTR_PRODUCTO_LOCAL.VA_FRACCION%TYPE;
 l_preciopromedio    LGTM_PRODUCTO.VA_PRECIO_PROMEDIO%TYPE;
  BEGIN
    l_numeracion := Ptovta_Utility.OBTENER_NUMERACION(codigocompania_in,codigolocal_in,'007');
 --
 SELECT PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
     DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO, 'S',
         PRODUCTO_LOCAL.CO_UNIDAD_VENTA_FRACCION,
      PRODUCTO.CO_UNIDAD_VENTA),
     PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE,
     PRODUCTO_LOCAL.VA_FRACCION,
     PRODUCTO.VA_PRECIO_PROMEDIO
   INTO l_inprodfraccionado, l_codigounidadventa, l_stockdisponible, l_valorfraccion, l_preciopromedio
   FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL,
       LGTM_PRODUCTO PRODUCTO
  WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
    AND PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
    AND PRODUCTO_LOCAL.CO_PRODUCTO = codigoproducto_in
    AND PRODUCTO_LOCAL.CO_COMPANIA = PRODUCTO.CO_COMPANIA
    AND PRODUCTO_LOCAL.CO_PRODUCTO = PRODUCTO.CO_PRODUCTO FOR UPDATE;
 --
    INSERT INTO LGTV_KARDEX
     (CO_COMPANIA, CO_LOCAL, CO_PRODUCTO, NU_SEC_KARDEX,
   TI_DOCUMENTO, NU_DOCUMENTO, IN_PROD_FRACCIONADO, CA_STOCK_INICIAL,
      CO_UNIDAD_VENTA_INICIAL, VA_FRACCION_INICIAL, CA_MOVIMIENTO, CA_STOCK_FINAL,
   VA_FRACCION_FINAL, FE_KARDEX, CO_GRUPO_MOTIVO, CO_MOTIVO_KARDEX,
   VA_PRECIO_PROMEDIO, ES_KARDEX, ID_CREA_KARDEX, FE_CREA_KARDEX)
     VALUES (codigocompania_in, codigolocal_in,     codigoproducto_in,   l_numeracion,
    tipodocumento_in,  numerodocumento_in, l_inprodfraccionado, l_stockdisponible,
    l_codigounidadventa, l_valorfraccion,  cantmovimiento_in,  (l_stockdisponible + cantmovimiento_in),
    l_valorfraccion,     SYSDATE,          '01',               motivokardex_in,
    l_preciopromedio,    'A',              idcreakardex_in,    SYSDATE);

 UPDATE LGTR_PRODUCTO_LOCAL
    SET CA_STOCK_DISPONIBLE =  l_stockdisponible + cantmovimiento_in,
        ID_MOD_PROD_LOCAL= idcreakardex_in,
     FE_MOD_PROD_LOCAL = SYSDATE,
   IN_REPLICA = 0
  WHERE CO_COMPANIA = codigocompania_in
    AND CO_LOCAL = codigolocal_in
    AND CO_PRODUCTO = codigoproducto_in;
 --
    Ptovta_Utility.ACTUALIZAR_NUMERACION_NOCOMMIT(codigocompania_in,codigolocal_in,'007',idcreakardex_in);
    --COMMIT;
  END;

  /* ***************************************************************************************************** */
  FUNCTION GET_PRECIO_COMPRA_PROD(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
              codigolocal_in    IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
                                  codigoproducto_in IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE,
          tipounidad_in     IN CHAR)
           RETURN LGTM_PRODUCTO.VA_PRECIO_COMPRA%TYPE IS
    l_preciounitprod LGTM_PRODUCTO.VA_PRECIO_COMPRA%TYPE;
  BEGIN
   IF tipounidad_in = 'E' THEN
  SELECT NVL(PRODUCTO.VA_PRECIO_COMPRA, 0.00)
    INTO l_preciounitprod
    FROM LGTM_PRODUCTO PRODUCTO
   WHERE PRODUCTO.CO_COMPANIA = codigocompania_in
     AND PRODUCTO.CO_PRODUCTO = codigoproducto_in;
   ELSE
      SELECT NVL(PRODUCTO.VA_VENTA, 0.00)
    INTO l_preciounitprod
    FROM LGTR_PRODUCTO_LOCAL PRODUCTO
   WHERE PRODUCTO.CO_COMPANIA = codigocompania_in
     AND PRODUCTO.CO_LOCAL = codigolocal_in
     AND PRODUCTO.CO_PRODUCTO = codigoproducto_in;
   END IF;
      RETURN l_preciounitprod;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
         RETURN 0.00;
  END;

 /* ******************************************************************************** */
  FUNCTION GET_STOCK_PRODUCTO(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
            codigolocal_in    IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
                              codigoproducto_in IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
         RETURN LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE IS
    l_stockdisponible    LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;
  BEGIN
   SELECT NVL(DECODE(IN_PROD_FRACCIONADO,
              'S',
        TRUNC((CA_STOCK_DISPONIBLE - NVL(CA_STOCK_COMPROMETIDO,0)) /VA_FRACCION),
           CA_STOCK_DISPONIBLE - NVL(CA_STOCK_COMPROMETIDO,0)
       ),0)
    INTO l_stockdisponible
   FROM LGTR_PRODUCTO_LOCAL
   WHERE CO_COMPANIA = codigocompania_in AND
         CO_LOCAL = codigolocal_in AND
   CO_PRODUCTO = codigoproducto_in;
   RETURN l_stockdisponible;
  END;


  /* ***************************************************************************************************** */
  FUNCTION GET_STOCK_FRACC_PRODUCTO(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
             codigolocal_in    IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
                               codigoproducto_in  IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
           RETURN LGTR_PRODUCTO_LOCAL .CA_STOCK_DISPONIBLE%TYPE IS
    l_stockfraccion LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;
  BEGIN
   SELECT NVL(DECODE(IN_PROD_FRACCIONADO,
            'S',
      MOD((CA_STOCK_DISPONIBLE - NVL(CA_STOCK_COMPROMETIDO,0)),VA_FRACCION),
            0), 0)
     INTO l_stockfraccion
     FROM LGTR_PRODUCTO_LOCAL
    WHERE CO_COMPANIA = codigocompania_in AND
          CO_LOCAL = codigolocal_in AND
    CO_PRODUCTO = codigoproducto_in;
   RETURN l_stockfraccion;
  END;


  /* ***************************************************************************************************** */
 FUNCTION GET_GUIAS_MANUALES(codigocompania_in IN LGTD_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
        codigolocal_in    IN LGTD_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
        fechainicio_in    IN CHAR,
        fechafin_in    IN CHAR)
 RETURN EckerdCursor IS
    eckcur EckerdCursor;
 BEGIN
    IF LENGTH(fechainicio_in) > 0 AND LENGTH(fechafin_in) > 0 THEN
       OPEN eckcur FOR
           SELECT NVL(RECEP_PROD.NU_RECEPCION_PRODUCTO, ' ') || 'Ã' ||
                  NVL(TO_CHAR(RECEP_PROD.FE_RECEPCION_PRODUCTO, 'dd/MM/yyyy'), ' ') || 'Ã' ||
                  DECODE(RECEP_PROD.TI_RECEPCION, 'A',
                     DECODE(RECEP_PROD.CO_LOCAL_ORIGEN, NULL, NVL(RECEP_PROD.NO_PROVEEDOR, ' '), NVL(LOCAL.DE_CORTA_LOCAL, ' ')),
                     CASE
                        WHEN (RECEP_PROD.CO_LOCAL_ORIGEN IS NULL OR RECEP_PROD.CO_LOCAL_ORIGEN = '099') AND RECEP_PROD.CO_PROVEEDOR = '00' THEN
                            NVL(RECEP_PROD.NO_PROVEEDOR, ' ')
                        WHEN (RECEP_PROD.CO_LOCAL_ORIGEN IS NULL OR RECEP_PROD.CO_LOCAL_ORIGEN = '099') AND RECEP_PROD.CO_PROVEEDOR != '00' THEN
                            (SELECT Z.DE_PROVEEDOR_COTIZACION FROM LOGISTICA.LGTM_PROVEEDOR_COTIZACION Z WHERE Z.CO_PROVEEDOR_COTIZACION = RECEP_PROD.CO_PROVEEDOR)
                        ELSE
                            NVL(LOCAL.DE_CORTA_LOCAL, ' ')
                     END
                  ) || 'Ã' ||
                  --DECODE(RECEP_PROD.CO_LOCAL_ORIGEN, NULL , NVL(RECEP_PROD.NO_PROVEEDOR, ' '), NVL(LOCAL.DE_CORTA_LOCAL, ' '))|| 'Ã' ||
                  NVL(DOCUMENTO.DE_CORTA_TIPO_DOCUMENTO, ' ') || 'Ã' ||
                  NVL(SUBSTR(RECEP_PROD.NU_DOCUMENTO_RECEPCION, 0, 3) || '-' || SUBSTR(RECEP_PROD.NU_DOCUMENTO_RECEPCION, 4),  ' ') || 'Ã' ||
                  NVL(RECEP_PROD.CA_ITEM_RECEPCION, 0) || 'Ã' ||
                  TO_CHAR(NVL(RECEP_PROD.VA_TOTAL_COMPRA, 0), '999,990.00') || 'Ã' ||
                  DECODE(RECEP_PROD.ES_RECEPCION_PRODUCTO, 'C', DECODE(RECEP_PROD.TI_RECEPCION, 'A', 'Cerrado', 'Impreso'), 'N', 'Anulado', ' ') || 'Ã' ||
                  NVL(RECEP_PROD.ES_RECEPCION_PRODUCTO, ' ')|| 'Ã' ||
                  NVL(RECEP_PROD.TI_RECEPCION, ' ') || 'Ã' ||
                  NVL(RECEP_PROD.CO_PROVEEDOR, '00') || 'Ã' ||
                  NVL(RECEP_PROD.IN_TIPO_CONTROL, 'P')
             FROM LGTC_RECEPCION_PRODUCTO RECEP_PROD,
                  VTTM_LOCAL LOCAL,
                  LGTR_TIPO_DOCUMENTO DOCUMENTO
            WHERE RECEP_PROD.CO_COMPANIA = codigocompania_in
              AND RECEP_PROD.CO_LOCAL = codigolocal_in
              AND RECEP_PROD.FE_RECEPCION_PRODUCTO BETWEEN TO_DATE(fechainicio_in||' 00:00:00', 'dd/MM/yyyy HH24:MI:SS')
                                                       AND TO_DATE(fechafin_in||' 23:59:59', 'dd/MM/yyyy HH24:MI:SS')
              AND RECEP_PROD.ES_RECEPCION_PRODUCTO <> 'P'
              AND RECEP_PROD.CO_COMPANIA = LOCAL.CO_COMPANIA (+)
              AND RECEP_PROD.CO_LOCAL_ORIGEN = LOCAL.CO_LOCAL (+)
              AND RECEP_PROD.CO_COMPANIA = DOCUMENTO.CO_COMPANIA
              AND RECEP_PROD.TI_DOCUMENTO_RECEPCION = DOCUMENTO.TI_DOCUMENTO
            ORDER BY RECEP_PROD.NU_RECEPCION_PRODUCTO DESC;
    ELSE
       OPEN eckcur FOR
           SELECT NVL(RECEP_PROD.NU_RECEPCION_PRODUCTO, ' ') || 'Ã' ||
                  NVL(TO_CHAR(RECEP_PROD.FE_RECEPCION_PRODUCTO, 'dd/MM/yyyy'), ' ') || 'Ã' ||
                  DECODE(RECEP_PROD.TI_RECEPCION, 'A',
                     DECODE(RECEP_PROD.CO_LOCAL_ORIGEN, NULL, NVL(RECEP_PROD.NO_PROVEEDOR, ' '), NVL(LOCAL.DE_CORTA_LOCAL, ' ')),
                     CASE
                        WHEN (RECEP_PROD.CO_LOCAL_ORIGEN IS NULL OR RECEP_PROD.CO_LOCAL_ORIGEN = '099') AND RECEP_PROD.CO_PROVEEDOR = '00' THEN
                            NVL(RECEP_PROD.NO_PROVEEDOR, ' ')
                        WHEN (RECEP_PROD.CO_LOCAL_ORIGEN IS NULL OR RECEP_PROD.CO_LOCAL_ORIGEN = '099') AND RECEP_PROD.CO_PROVEEDOR != '00' THEN
                            (SELECT Z.DE_PROVEEDOR_COTIZACION FROM LOGISTICA.LGTM_PROVEEDOR_COTIZACION Z WHERE Z.CO_PROVEEDOR_COTIZACION = RECEP_PROD.CO_PROVEEDOR)
                        ELSE
                            NVL(LOCAL.DE_CORTA_LOCAL, ' ')
                     END
                 ) || 'Ã' ||
                  --DECODE(RECEP_PROD.CO_LOCAL_ORIGEN, NULL, NVL(RECEP_PROD.NO_PROVEEDOR, ' '), NVL(LOCAL.DE_CORTA_LOCAL, ' '))|| 'Ã' ||
                 NVL(DOCUMENTO.DE_CORTA_TIPO_DOCUMENTO, ' ') || 'Ã' ||
                 NVL(SUBSTR(RECEP_PROD.NU_DOCUMENTO_RECEPCION, 0, 3) || '-' || SUBSTR(RECEP_PROD.NU_DOCUMENTO_RECEPCION, 4),  ' ') || 'Ã' ||
                 NVL(RECEP_PROD.CA_ITEM_RECEPCION, 0) || 'Ã' ||
                 TO_CHAR(NVL(RECEP_PROD.VA_TOTAL_COMPRA, 0), '999,990.00') || 'Ã' ||
                 DECODE(RECEP_PROD.ES_RECEPCION_PRODUCTO, 'C', DECODE(RECEP_PROD.TI_RECEPCION, 'A', 'Cerrado', 'Impreso'), 'N', 'Anulado', ' ') || 'Ã' ||
                 NVL(RECEP_PROD.ES_RECEPCION_PRODUCTO, ' ')|| 'Ã' ||
                 NVL(RECEP_PROD.TI_RECEPCION, ' ') || 'Ã' ||
                 NVL(RECEP_PROD.CO_PROVEEDOR, '00') || 'Ã' ||
                 NVL(RECEP_PROD.IN_TIPO_CONTROL, 'P')
            FROM LGTC_RECEPCION_PRODUCTO RECEP_PROD,
                 VTTM_LOCAL LOCAL,
                 LGTR_TIPO_DOCUMENTO DOCUMENTO
           WHERE RECEP_PROD.CO_COMPANIA = codigocompania_in
             AND RECEP_PROD.CO_LOCAL = codigolocal_in
             AND RECEP_PROD.ES_RECEPCION_PRODUCTO <> 'P'
             AND RECEP_PROD.CO_COMPANIA = LOCAL.CO_COMPANIA (+)
             AND RECEP_PROD.CO_LOCAL_ORIGEN = LOCAL.CO_LOCAL (+)
             AND RECEP_PROD.CO_COMPANIA = DOCUMENTO.CO_COMPANIA
             AND RECEP_PROD.TI_DOCUMENTO_RECEPCION = DOCUMENTO.TI_DOCUMENTO
           ORDER BY RECEP_PROD.NU_RECEPCION_PRODUCTO DESC;
    END IF;

    RETURN eckcur;
  END;

  /* ******************************************************************************** */
  FUNCTION GET_NAME_COMPANIA(codigocompania_in IN CMTM_COMPANIA.CO_COMPANIA%TYPE)
           RETURN CMTM_COMPANIA.DE_COMPANIA%TYPE IS
    l_namecompany CMTM_COMPANIA.DE_COMPANIA%TYPE;
  BEGIN
  SELECT NVL(de_compania, ' ' )
    INTO l_namecompany
    FROM CMTM_COMPANIA
   WHERE co_compania = codigocompania_in;
     RETURN l_namecompany;
  END;


/* ******************************************************************************** */
FUNCTION GET_LOCAL_ORIGEN(codigocompania_in IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
         codlocal_in    IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
         num_recepcion_prod_in IN LGTC_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE)
   RETURN LGTC_RECEPCION_PRODUCTO.CO_LOCAL_ORIGEN%TYPE IS
 l_codorigen LGTC_RECEPCION_PRODUCTO.CO_LOCAL_ORIGEN%TYPE;
BEGIN
   SELECT NVL(CO_LOCAL_ORIGEN,'000')
  INTO l_codorigen
  FROM LGTC_RECEPCION_PRODUCTO
 WHERE co_compania = codigocompania_in AND
       CO_LOCAL = codlocal_in AND
    NU_RECEPCION_PRODUCTO = num_recepcion_prod_in;
  RETURN  l_codorigen;
END;

/* ******************************************************************************** */
FUNCTION GET_TIPO_INGRESO_MANUAL(codigocompania_in     IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
            codlocal_in     IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
            num_recepcion_prod_in IN LGTC_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE)
    RETURN LGTC_RECEPCION_PRODUCTO.IN_ORIGEN_GUIA_INGRESO%TYPE IS
 l_tipoingreso LGTC_RECEPCION_PRODUCTO.IN_ORIGEN_GUIA_INGRESO%TYPE;
BEGIN
  SELECT NVL(IN_ORIGEN_GUIA_INGRESO, 'A')
  INTO  l_tipoingreso
  FROM LGTC_RECEPCION_PRODUCTO
  WHERE  CO_COMPANIA = codigocompania_in AND
         CO_LOCAL    = codlocal_in AND
   NU_RECEPCION_PRODUCTO = num_recepcion_prod_in;
     RETURN  l_tipoingreso;
END;


/* ******************************************************************************** */
 FUNCTION GET_DETALLE_INGRESO_MANUAL(codigocompania_in     IN LGTD_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                codigolocal_in        IN LGTD_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
          num_recepcion_prod_in IN LGTD_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE,
          tipodatos_in          IN CHAR)
          RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
      IF tipodatos_in = 'L'  THEN
          OPEN eckcur FOR
                SELECT NVL(D.CO_PRODUCTO,' ') || 'Ã' ||
                       NVL(TRIM(PM.DE_PRODUCTO),' ') || 'Ã' ||
                       NVL(TRIM(PM.DE_UNIDAD_PRODUCTO),' ') || 'Ã' ||
                       NVL(TRIM(PL.DE_UNIDAD_FRACCION),' ') || 'Ã' ||
                       NVL(D.CA_ENVIADA_ENTERA, 0 ) || 'Ã' ||
                       NVL(D.CA_ENVIADA_FRACCION, 0 ) || 'Ã' ||
                       NVL(TO_CHAR(D.VA_PRECIO_COMPRA,'999,999,990.00'), 0 ) || 'Ã' ||
                       NVL(TO_CHAR(D.VA_TOTAL_ITEM,'999,999,990.00' ), 0) || 'Ã' ||
                       NVL(TO_CHAR(D.FE_VENCE_PRODUCTO,'MM/yyyy'),' ') || 'Ã' ||
                       NVL(D.NU_LOTE,' ')
                  FROM LGTD_RECEPCION_PRODUCTO D,
                       LGTM_PRODUCTO PM,
                       LGTR_PRODUCTO_LOCAL  PL
                WHERE  D.CO_COMPANIA = PM.CO_COMPANIA AND
                       D.CO_PRODUCTO = PM.CO_PRODUCTO AND
                       D.CO_COMPANIA = PL.CO_COMPANIA AND
                       D.CO_LOCAL = PL.CO_LOCAL AND
                       D.CO_PRODUCTO = PL.CO_PRODUCTO AND
                       D.CO_COMPANIA = codigocompania_in AND
                       D.CO_LOCAL = codigolocal_in AND
                       D.NU_RECEPCION_PRODUCTO = num_recepcion_prod_in;
      ELSIF tipodatos_in = 'C'  THEN -- inicio 003
          OPEN eckcur FOR
                SELECT NVL(D.CO_PRODUCTO,' ') || 'Ã' ||
                 NVL(TRIM(PM.DE_PRODUCTO),' ') || 'Ã' ||
                 --NVL(TRIM(PM.DE_UNIDAD_PRODUCTO),' ') || 'Ã' ||
                 DECODE(D.DE_UNIDAD_PRODUCTO, NULL, NVL(TRIM(PM.DE_UNIDAD_PRODUCTO),' '), NVL(TRIM(D.DE_UNIDAD_PRODUCTO),' ')) || 'Ã' ||
                 NVL(DECODE (PL.IN_PROD_FRACCIONADO,'S',D.CA_ENVIADA_FRACCION+ D.CA_ENVIADA_ENTERA *PL.VA_FRACCION,D.CA_ENVIADA_ENTERA ), 0 ) || 'Ã' ||
                 NVL(TO_CHAR(D.VA_PRECIO_COMPRA,'999,999,990.00'), 0 ) || 'Ã' ||
                 NVL(TO_CHAR(D.VA_TOTAL_ITEM,'999,999,990.00' ), 0) || 'Ã' ||
                 NVL(TO_CHAR(D.FE_VENCE_PRODUCTO,'MM/yyyy'),' ') || 'Ã' ||
                 NVL(D.NU_LOTE,' ')
             FROM   LGTD_RECEPCION_PRODUCTO D,
                 LGTM_PRODUCTO PM,
                 LGTR_PRODUCTO_LOCAL  PL
             WHERE  D.CO_COMPANIA = PM.CO_COMPANIA AND
                 D.CO_PRODUCTO = PM.CO_PRODUCTO AND
                 D.CO_COMPANIA = PL.CO_COMPANIA AND
                 D.CO_LOCAL = PL.CO_LOCAL AND
                 D.CO_PRODUCTO = PL.CO_PRODUCTO AND
                 D.CO_COMPANIA = codigocompania_in AND
                 D.CO_LOCAL = codigolocal_in AND
                 D.NU_RECEPCION_PRODUCTO = num_recepcion_prod_in; -- fin 003
      ELSIF tipodatos_in = 'S'  THEN
          OPEN eckcur FOR
                SELECT NVL(D.CO_PRODUCTO,' ') || 'Ã' ||
                       NVL(TRIM(PM.DE_PRODUCTO),' ') || 'Ã' ||
                       --NVL(TRIM(PM.DE_UNIDAD_PRODUCTO),' ') || 'Ã' ||
                       DECODE(D.DE_UNIDAD_PRODUCTO, NULL, NVL(TRIM(PM.DE_UNIDAD_PRODUCTO),' '), NVL(TRIM(D.DE_UNIDAD_PRODUCTO),' ')) || 'Ã' ||
                       NVL(TO_CHAR(D.CA_ENVIADA_ENTERA,'999,990'), 0) || 'Ã' ||
                       NVL(TO_CHAR(D.CA_CONFIRMADA_ENTERA,'999,990'), 0) || 'Ã' ||
                       NVL(TO_CHAR(D.CA_CONFIRMADA_ENTERA,'999,990'), 0) || 'Ã' ||
                       NVL(TO_CHAR(D.VA_PRECIO_COMPRA,'999,999,990.000'), 0 ) || 'Ã' ||
                       NVL(TO_CHAR(D.VA_TOTAL_ITEM,'999,999,990.000' ), 0) || 'Ã' ||
                       NVL(TO_CHAR(D.FE_VENCE_PRODUCTO,'MM/yyyy'),' ') || 'Ã' ||
                       NVL(D.NU_LOTE,' ') LOTE
                  FROM LGTD_RECEPCION_PRODUCTO D,
                       LGTM_PRODUCTO PM,
                       LGTR_PRODUCTO_LOCAL  PL
                 WHERE D.CO_COMPANIA = PM.CO_COMPANIA AND
                       D.CO_PRODUCTO = PM.CO_PRODUCTO AND
                       D.CO_COMPANIA = PL.CO_COMPANIA AND
                       D.CO_LOCAL = PL.CO_LOCAL AND
                       D.CO_PRODUCTO = PL.CO_PRODUCTO AND
                       D.CO_COMPANIA = codigocompania_in AND
                       D.CO_LOCAL = codigolocal_in AND
                       D.NU_RECEPCION_PRODUCTO = num_recepcion_prod_in;
      ELSE
         OPEN eckcur FOR
                SELECT NVL(D.CO_PRODUCTO,' ') || 'Ã' ||
                 NVL(TRIM(PM.DE_PRODUCTO),' ') || 'Ã' ||
                 NVL(TRIM(PM.DE_UNIDAD_PRODUCTO),' ') || 'Ã' ||
                 NVL(D.CA_ENVIADA_ENTERA, 0 ) || 'Ã' ||
                 NVL(TO_CHAR(D.VA_PRECIO_COMPRA,'999,999,990.00'), 0 ) || 'Ã' ||
                 NVL(TO_CHAR(D.VA_TOTAL_ITEM,'999,999,990.00' ), 0) || 'Ã' ||
                 NVL(TO_CHAR(D.FE_VENCE_PRODUCTO,'MM/yyyy'),' ') || 'Ã' ||
                 NVL(D.NU_LOTE,' ')
             FROM   LGTD_RECEPCION_PRODUCTO D,
                 LGTM_PRODUCTO PM
             WHERE  D.CO_COMPANIA = PM.co_compania AND
                 D.CO_PRODUCTO = PM.CO_PRODUCTO AND
                 D.CO_COMPANIA = codigocompania_in AND
                 D.CO_LOCAL = codigolocal_in AND
                 D.NU_RECEPCION_PRODUCTO = num_recepcion_prod_in;
      END IF;
    RETURN eckcur;
  END;

/* ******************************************************************************** */
FUNCTION GET_TIPO_DOC_INGRESO_MANUAL(codigocompania_in IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                codigolocal_in     IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
          num_recepcion_prod_in IN LGTC_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE)
        RETURN LGTC_RECEPCION_PRODUCTO.TI_DOCUMENTO_RECEPCION%TYPE IS
  tipodocrecepcion LGTC_RECEPCION_PRODUCTO.TI_DOCUMENTO_RECEPCION%TYPE;
BEGIN
    SELECT TI_DOCUMENTO_RECEPCION
   INTO tipodocrecepcion
   FROM LGTC_RECEPCION_PRODUCTO
  WHERE CO_COMPANIA = codigocompania_in AND
     CO_LOCAL = codigolocal_in AND
     NU_RECEPCION_PRODUCTO = num_recepcion_prod_in;
    RETURN tipodocrecepcion;
END;

/* ******************************************************************************** */
FUNCTION GET_PROVEEDOR_INGRESO_MANUAL(codigocompania_in IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                codigolocal_in     IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
          num_recepcion_prod_in IN LGTC_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE)
        RETURN LGTC_RECEPCION_PRODUCTO.CO_LOCAL_ORIGEN%TYPE IS
   l_codlocalproveedor LGTC_RECEPCION_PRODUCTO.CO_LOCAL_ORIGEN%TYPE;
BEGIN
   SELECT CO_LOCAL_ORIGEN
     INTO l_codlocalproveedor
     FROM LGTC_RECEPCION_PRODUCTO
    WHERE CO_COMPANIA = codigocompania_in AND
    CO_LOCAL = codigolocal_in AND
    NU_RECEPCION_PRODUCTO = num_recepcion_prod_in;
   RETURN l_codlocalproveedor;
END;

FUNCTION LISTA_DATA_TABLAS(codigocompania_in IN VTTM_LOCAL.CO_COMPANIA%TYPE,
              nombretabla_in IN VARCHAR2)
           RETURN EckerdCursor  IS
    eckcur EckerdCursor;
  BEGIN
 IF (nombretabla_in = 'LABORATORIO') THEN
    OPEN eckcur FOR SELECT DECODE(CO_LABORATORIO,NULL,' ',CO_LABORATORIO) || 'Ã' ||
                           DECODE(DE_LABORATORIO,NULL,' ',DE_LABORATORIO)
       FROM CMTR_LABORATORIO
       WHERE CO_COMPANIA = codigocompania_in
      ORDER BY DE_LABORATORIO;
 ELSIF (nombretabla_in = 'GRUPOPRODUCTO') THEN
    OPEN eckcur FOR SELECT NVL(CO_GRUPO_PRODUCTO,' ') || 'Ã' ||
                           NVL(DE_GRUPO_PRODUCTO,' ')
       FROM LGTR_GRUPO_PRODUCTO
       WHERE CO_COMPANIA = codigocompania_in
      ORDER BY DE_GRUPO_PRODUCTO;
 ELSIF (nombretabla_in = 'ACCIONTERAPEUTICA') THEN
    OPEN eckcur FOR SELECT NVL(CO_ACCION_TERAPEUTICA,' ') || 'Ã' ||
                           NVL(DE_ACCION_TERAPEUTICA,' ')
       FROM VTTR_ACCION_TERAPEUTICA
       WHERE CO_COMPANIA = codigocompania_in
         ORDER BY DE_ACCION_TERAPEUTICA;
 END IF;
    RETURN eckcur;
  END;

/**********************************************************************************************************************/
FUNCTION OBTIENE_PROD_ROTACION_4_MESES(codigocompania_in    IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
                                 codigolocal_in       IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
            codigoproducto_in    IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
           RETURN EckerdCursor IS
     eckcur EckerdCursor;
  --rotacionprod       NUMBER(10,2);
 BEGIN
    --Calcula rotacion diaria de un producto
  OPEN eckcur FOR
     SELECT TO_CHAR(MES1.VALOR, '999,999,990.00') || 'Ã' ||
       TO_CHAR(MES2.VALOR, '999,999,990.00') || 'Ã' ||
       TO_CHAR(MES3.VALOR, '999,999,990.00') || 'Ã' ||
       TO_CHAR(MES4.VALOR, '999,999,990.00')
   FROM   (
     SELECT NVL(SUM(CA_VENTA_DIA),0) VALOR
     FROM VTTR_VENTAS_PRODUCTO_DIA
     WHERE CO_COMPANIA = codigocompania_in AND
        CO_LOCAL    = codigolocal_in   AND
        CO_PRODUCTO = codigoproducto_in AND
        NU_REVISION_PRODUCTO = '0' AND
        FE_DIA_VENTA BETWEEN (SYSDATE - 1*30) AND SYSDATE
         ) MES1,
       (
     SELECT NVL(SUM(CA_VENTA_DIA),0) VALOR
     FROM VTTR_VENTAS_PRODUCTO_DIA
     WHERE CO_COMPANIA = codigocompania_in AND
        CO_LOCAL    = codigolocal_in   AND
        CO_PRODUCTO = codigoproducto_in AND
        NU_REVISION_PRODUCTO = '0' AND
          FE_DIA_VENTA BETWEEN TO_DATE(TO_CHAR(SYSDATE - 2*30 + 1,'dd/MM/yyyy') || ' 00:00:00','dd/MM/yyyy HH24:MI:SS')
                AND TO_DATE(TO_CHAR(SYSDATE - 1*30,'dd/MM/yyyy') || ' 23:59:59','dd/MM/yyyy HH24:MI:SS')
      ) MES2,
       (
     SELECT NVL(SUM(CA_VENTA_DIA),0) VALOR
     FROM VTTR_VENTAS_PRODUCTO_DIA
     WHERE CO_COMPANIA = codigocompania_in AND
        CO_LOCAL    = codigolocal_in   AND
        CO_PRODUCTO = codigoproducto_in AND
        NU_REVISION_PRODUCTO = '0' AND
          FE_DIA_VENTA BETWEEN TO_DATE(TO_CHAR(SYSDATE - 3*30 + 1,'dd/MM/yyyy') || ' 00:00:00','dd/MM/yyyy HH24:MI:SS')
                AND TO_DATE(TO_CHAR(SYSDATE - 2*30,'dd/MM/yyyy') || ' 23:59:59','dd/MM/yyyy HH24:MI:SS')
      ) MES3,
       (
     SELECT NVL(SUM(CA_VENTA_DIA),0) VALOR
     FROM VTTR_VENTAS_PRODUCTO_DIA
     WHERE CO_COMPANIA = codigocompania_in AND
        CO_LOCAL    = codigolocal_in   AND
        CO_PRODUCTO = codigoproducto_in AND
        NU_REVISION_PRODUCTO = '0' AND
          FE_DIA_VENTA BETWEEN TO_DATE(TO_CHAR(SYSDATE - 4*30 + 1,'dd/MM/yyyy') || ' 00:00:00','dd/MM/yyyy HH24:MI:SS')
                AND TO_DATE(TO_CHAR(SYSDATE - 3*30,'dd/MM/yyyy') || ' 23:59:59','dd/MM/yyyy HH24:MI:SS')
      ) MES4;

/*
     SELECT TO_CHAR(MES1.VALOR, '999,999,990.00') || 'Ã' ||
       TO_CHAR(MES2.VALOR, '999,999,990.00') || 'Ã' ||
       TO_CHAR(MES3.VALOR, '999,999,990.00') || 'Ã' ||
       TO_CHAR(MES4.VALOR, '999,999,990.00')
   FROM   (
     SELECT NVL(SUM(DECODE(IN_PRODUCTO_FRACCION, 'N', CA_ATENDIDA, CA_ATENDIDA/VA_FRACCION)),0) VALOR
     FROM   VTTC_PEDIDO_VENTA PED,
         VTTD_PEDIDO_VENTA DET
     WHERE  PED.CO_COMPANIA = codigocompania_in  AND
         PED.CO_LOCAL    = codigolocal_in AND
           PED.FE_PEDIDO BETWEEN (SYSDATE - 1*30) AND SYSDATE  AND
           PED.ES_PEDIDO_VENTA = 'C' AND
         DET.CO_COMPANIA = codigocompania_in AND
         DET.CO_LOCAL    = codigolocal_in    AND
           DET.CO_PRODUCTO = codigoproducto_in AND
         PED.CO_COMPANIA = DET.CO_COMPANIA   AND
         PED.CO_LOCAL    = DET.CO_LOCAL      AND
         PED.NU_PEDIDO   = DET.NU_PEDIDO
         ) MES1,
       (
     SELECT NVL(SUM(DECODE(IN_PRODUCTO_FRACCION, 'N', CA_ATENDIDA, CA_ATENDIDA/VA_FRACCION)),0) VALOR
     FROM   VTTC_PEDIDO_VENTA PED,
         VTTD_PEDIDO_VENTA DET
     WHERE  PED.CO_COMPANIA = codigocompania_in  AND
         PED.CO_LOCAL    = codigolocal_in AND
           PED.FE_PEDIDO BETWEEN TO_DATE(TO_CHAR(SYSDATE - 2*30,'dd/MM/yyyy') || ' 00:00:00','dd/MM/yyyy HH24:MI:SS')
                  AND TO_DATE(TO_CHAR(SYSDATE - 1*30,'dd/MM/yyyy') || ' 23:59:59','dd/MM/yyyy HH24:MI:SS') AND
           PED.ES_PEDIDO_VENTA = 'C' AND
         DET.CO_COMPANIA = codigocompania_in AND
         DET.CO_LOCAL    = codigolocal_in    AND
           DET.CO_PRODUCTO = codigoproducto_in AND
         PED.CO_COMPANIA = DET.CO_COMPANIA   AND
         PED.CO_LOCAL    = DET.CO_LOCAL      AND
         PED.NU_PEDIDO   = DET.NU_PEDIDO) MES2,
       (
     SELECT NVL(SUM(DECODE(IN_PRODUCTO_FRACCION, 'N', CA_ATENDIDA, CA_ATENDIDA/VA_FRACCION)),0) VALOR
     FROM   VTTC_PEDIDO_VENTA PED,
         VTTD_PEDIDO_VENTA DET
     WHERE  PED.CO_COMPANIA = codigocompania_in  AND
         PED.CO_LOCAL    = codigolocal_in AND
           PED.FE_PEDIDO BETWEEN TO_DATE(TO_CHAR(SYSDATE - 3*30,'dd/MM/yyyy') || ' 00:00:00','dd/MM/yyyy HH24:MI:SS')
                  AND TO_DATE(TO_CHAR(SYSDATE - 2*30,'dd/MM/yyyy') || ' 23:59:59','dd/MM/yyyy HH24:MI:SS') AND
           PED.ES_PEDIDO_VENTA = 'C' AND
         DET.CO_COMPANIA = codigocompania_in AND
         DET.CO_LOCAL    = codigolocal_in    AND
           DET.CO_PRODUCTO = codigoproducto_in AND
         PED.CO_COMPANIA = DET.CO_COMPANIA   AND
         PED.CO_LOCAL    = DET.CO_LOCAL      AND
         PED.NU_PEDIDO   = DET.NU_PEDIDO) MES3,
       (
     SELECT NVL(SUM(DECODE(IN_PRODUCTO_FRACCION, 'N', CA_ATENDIDA, CA_ATENDIDA/VA_FRACCION)),0) VALOR
     FROM   VTTC_PEDIDO_VENTA PED,
         VTTD_PEDIDO_VENTA DET
     WHERE  PED.CO_COMPANIA = codigocompania_in  AND
         PED.CO_LOCAL    = codigolocal_in AND
           PED.FE_PEDIDO BETWEEN TO_DATE(TO_CHAR(SYSDATE - 4*30,'dd/MM/yyyy') || ' 00:00:00','dd/MM/yyyy HH24:MI:SS')
                  AND TO_DATE(TO_CHAR(SYSDATE - 3*30,'dd/MM/yyyy') || ' 23:59:59','dd/MM/yyyy HH24:MI:SS') AND
           PED.ES_PEDIDO_VENTA = 'C' AND
         DET.CO_COMPANIA = codigocompania_in AND
         DET.CO_LOCAL    = codigolocal_in    AND
           DET.CO_PRODUCTO = codigoproducto_in AND
         PED.CO_COMPANIA = DET.CO_COMPANIA   AND
         PED.CO_LOCAL    = DET.CO_LOCAL      AND
         PED.NU_PEDIDO   = DET.NU_PEDIDO) MES4;
*/
   RETURN eckcur;
 END;

/* ********************************************************************************************************************/
  FUNCTION INV_PEDIDO_REPOSICION(codigocompania_in    IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
              codigolocal_in       IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
                                 codigoLaboratorio_in IN LGTM_PRODUCTO.CO_LABORATORIO%TYPE,
         verTodosProductos    IN CHAR)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
      IF (verTodosProductos = 'S') THEN
     IF (codigoLaboratorio_in IS NULL) THEN--1 codigo laboratorio is null
   OPEN eckcur FOR
       SELECT NVL(V2.CO_PRODUCTO, ' ')|| 'Ã' ||
             NVL(RTRIM(V2.DE_PRODUCTO), ' ') || 'Ã' ||
             NVL(RTRIM(V2.DE_UNIDAD_PRODUCTO), ' ') || 'Ã' ||
             TO_CHAR(V2.CA_STOCK_MINIMO, '999,990') || 'Ã' ||
             TO_CHAR(V2.CA_STOCK_MAXIMO, '999,990') || 'Ã' ||
             V2.STOCK || 'Ã' ||
             DECODE(NVL(V2.IN_PRODUCTO_REPONER, 'S'), 'S', ' ', 'N') || 'Ã' ||
             TO_CHAR(V2.CA_STOCK_REPONER_CALCULA, '999,990') || 'Ã' ||
             V2.CA_STOCK_REPONER || 'Ã' ||
          V1.ULTIMO || 'Ã' ||
          V1.ROTACION || 'Ã' ||
          TO_CHAR(V2.CA_MIN_PROD_EXHIBICION, '999,990') || 'Ã' ||
          V2.CA_PROD_NO_ATENDIDO
      FROM   (SELECT /*+ CHOOSE */
               PROD.CO_PRODUCTO PRODUCTO,
            DECODE(NVL(PROD_LOCAL.NU_MIN_DIAS_REPOSICION, 0),
                          0, DECODE(NVL(LINEA_LOCAL.NU_MIN_DIAS_REPOSICION,0),
                                     0, NVL(LOCAL.NU_MIN_DIAS_REPOSICION, 0),
                                     LINEA_LOCAL.NU_MIN_DIAS_REPOSICION),
                       PROD_LOCAL.NU_MIN_DIAS_REPOSICION) || '-' ||
            DECODE(NVL(PROD_LOCAL.NU_MAX_DIAS_REPOSICION, 0),
                                                           0, DECODE(NVL(LINEA_LOCAL.NU_MAX_DIAS_REPOSICION,0),
                                               0, NVL(LOCAL.NU_MAX_DIAS_REPOSICION, 0),
                                     LINEA_LOCAL.NU_MAX_DIAS_REPOSICION),
                          PROD_LOCAL.NU_MAX_DIAS_REPOSICION) || '-' ||
            DECODE(NVL(PROD_LOCAL.NU_DIAS_ROTACION_PROMEDIO, 0),
                            0, DECODE(NVL(LINEA_LOCAL.NU_DIAS_ROTACION_PROMEDIO, 0),
                                       0, NVL(LOCAL.NU_DIAS_ROTACION_PROMEDIO, 0),
                                       LINEA_LOCAL.NU_DIAS_ROTACION_PROMEDIO),
                            PROD_LOCAL.NU_DIAS_ROTACION_PROMEDIO) ROTACION,
            NVL(CA_ULTIMO_PEDIDO_REP,0) ULTIMO
          FROM LGTR_PRODUCTO_LOCAL PROD_LOCAL,
              VTTM_LOCAL LOCAL,
            LGTM_PRODUCTO PROD,
            LGTR_LINEA_LOCAL LINEA_LOCAL
         WHERE PROD_LOCAL.CO_COMPANIA = codigocompania_in  AND
               PROD_LOCAL.CO_LOCAL = codigolocal_in AND
            PROD_LOCAL.CO_COMPANIA = LOCAL.CO_COMPANIA AND
            PROD_LOCAL.CO_LOCAL = LOCAL.CO_LOCAL  AND
            PROD_LOCAL.CO_COMPANIA = PROD.CO_COMPANIA AND
            PROD_LOCAL.CO_PRODUCTO = PROD.CO_PRODUCTO  AND
            PROD.CO_COMPANIA = LINEA_LOCAL.CO_COMPANIA(+) AND
            PROD.CO_LINEA_PRODUCTO = LINEA_LOCAL.CO_LINEA_PRODUCTO(+) AND
            LINEA_LOCAL.CO_LOCAL(+) = codigolocal_in) V1,
             (SELECT /*+ CHOOSE */
               NVL(PRODUCTO_LOCAL.CO_PRODUCTO,' ') CO_PRODUCTO,
               NVL(RTRIM(PRODUCTO.DE_PRODUCTO) ,' ') DE_PRODUCTO,
               NVL(RTRIM(PRODUCTO.DE_UNIDAD_PRODUCTO),' ') DE_UNIDAD_PRODUCTO,
               NVL(PRODUCTO_LOCAL.CA_STOCK_MINIMO ,0) CA_STOCK_MINIMO,
               NVL(PRODUCTO_LOCAL.CA_STOCK_MAXIMO ,0) CA_STOCK_MAXIMO,
               DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
                  'S',
                      TO_CHAR(TRUNC((PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE) / PRODUCTO_LOCAL.VA_FRACCION),'99,990'),
                  TO_CHAR(PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE,'99,990')
               ) || ' / ' ||
               DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
                 'S',
                      TO_CHAR(MOD(PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE, PRODUCTO_LOCAL.VA_FRACCION),'99,990'),
              '   ') STOCK,
               PRODUCTO_LOCAL.IN_PRODUCTO_REPONER IN_PRODUCTO_REPONER,
               NVL(PRODUCTO_LOCAL.CA_STOCK_REPONER_CALCULA, 0) CA_STOCK_REPONER_CALCULA,
               NVL(PRODUCTO_LOCAL.CA_STOCK_REPONER||'' , ' ') CA_STOCK_REPONER,
            NVL(CA_MIN_PROD_EXHIBICION, 0) CA_MIN_PROD_EXHIBICION,
            NVL(CA_PROD_NO_ATENDIDO, 0) CA_PROD_NO_ATENDIDO
         FROM LGTM_PRODUCTO PRODUCTO,
             LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL
        WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
           AND   PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
        AND   PRODUCTO_LOCAL.IN_PRODUCTO_REPONER = 'S' --agregado 20040902
--        AND   PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A'
           AND  (PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A' OR PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE > 0)
        AND   PRODUCTO_LOCAL.CO_COMPANIA = PRODUCTO.CO_COMPANIA
           AND   PRODUCTO_LOCAL.CO_PRODUCTO = PRODUCTO.CO_PRODUCTO
        AND   PRODUCTO.ES_PRODUCTO = 'A'
        --ORDER BY PRODUCTO.DE_PRODUCTO
        ) V2
      WHERE  V1.PRODUCTO = V2.CO_PRODUCTO;
      --ORDER BY V2.DE_PRODUCTO;
   ELSE
    OPEN eckcur FOR--2 codigo laboratorio is not null
       SELECT NVL(V2.CO_PRODUCTO, ' ') || 'Ã' ||
             NVL(RTRIM(V2.DE_PRODUCTO), ' ') || 'Ã' ||
             NVL(RTRIM(V2.DE_UNIDAD_PRODUCTO), ' ') || 'Ã' ||
             TO_CHAR(V2.CA_STOCK_MINIMO, '999,990') || 'Ã' ||
             TO_CHAR(V2.CA_STOCK_MAXIMO, '999,990') || 'Ã' ||
             V2.STOCK || 'Ã' ||
             DECODE(NVL(V2.IN_PRODUCTO_REPONER, 'S'), 'S', ' ', 'N') || 'Ã' ||
             TO_CHAR(V2.CA_STOCK_REPONER_CALCULA, '999,990') || 'Ã' ||
             V2.CA_STOCK_REPONER || 'Ã' ||
          V1.ULTIMO || 'Ã' ||
          V1.ROTACION || 'Ã' ||
          TO_CHAR(V2.CA_MIN_PROD_EXHIBICION, '999,990') || 'Ã' ||
          V2.CA_PROD_NO_ATENDIDO
      FROM   (SELECT /*+ CHOOSE */
               PROD.CO_PRODUCTO PRODUCTO,
            DECODE(NVL(PROD_LOCAL.NU_MIN_DIAS_REPOSICION,0),
              0,
              DECODE(NVL(LINEA_LOCAL.NU_MIN_DIAS_REPOSICION,0),
             0,
             NVL(LOCAL.NU_MIN_DIAS_REPOSICION,0),
             LINEA_LOCAL.NU_MIN_DIAS_REPOSICION),
           PROD_LOCAL.NU_MIN_DIAS_REPOSICION) || '-' ||
            DECODE(NVL(PROD_LOCAL.NU_MAX_DIAS_REPOSICION,0),
              0,
              DECODE(NVL(LINEA_LOCAL.NU_MAX_DIAS_REPOSICION,0),
             0,
             NVL(LOCAL.NU_MAX_DIAS_REPOSICION,0),
             LINEA_LOCAL.NU_MAX_DIAS_REPOSICION),
           PROD_LOCAL.NU_MAX_DIAS_REPOSICION) || '-' ||
            DECODE(NVL(PROD_LOCAL.NU_DIAS_ROTACION_PROMEDIO,0),
              0,
              DECODE(NVL(LINEA_LOCAL.NU_DIAS_ROTACION_PROMEDIO,0),
             0,
             NVL(LOCAL.NU_DIAS_ROTACION_PROMEDIO,0),
             LINEA_LOCAL.NU_DIAS_ROTACION_PROMEDIO),
           PROD_LOCAL.NU_DIAS_ROTACION_PROMEDIO) ROTACION,
            NVL(CA_ULTIMO_PEDIDO_REP,0) ULTIMO
          FROM LGTR_PRODUCTO_LOCAL PROD_LOCAL,
              VTTM_LOCAL LOCAL,
            LGTM_PRODUCTO PROD,
            LGTR_LINEA_LOCAL LINEA_LOCAL
         WHERE PROD_LOCAL.CO_COMPANIA = codigocompania_in  AND
               PROD_LOCAL.CO_LOCAL = codigolocal_in AND
            PROD_LOCAL.CO_COMPANIA = LOCAL.CO_COMPANIA AND
            --PROD_LOCAL.CO_PRODUCTO = codigoproducto_in AND
            PROD_LOCAL.CO_LOCAL = LOCAL.CO_LOCAL  AND
            PROD_LOCAL.CO_COMPANIA = PROD.CO_COMPANIA AND
            PROD_LOCAL.CO_PRODUCTO = PROD.CO_PRODUCTO  AND
            PROD.CO_COMPANIA = LINEA_LOCAL.CO_COMPANIA(+) AND
            PROD.CO_LINEA_PRODUCTO = LINEA_LOCAL.CO_LINEA_PRODUCTO(+) AND
            LINEA_LOCAL.CO_LOCAL(+) = codigolocal_in) V1,
             (SELECT /*+ CHOOSE */
               NVL(PRODUCTO_LOCAL.CO_PRODUCTO,' ') CO_PRODUCTO,
               NVL(RTRIM(PRODUCTO.DE_PRODUCTO) ,' ') DE_PRODUCTO,
               NVL(RTRIM(PRODUCTO.DE_UNIDAD_PRODUCTO),' ') DE_UNIDAD_PRODUCTO,
               NVL(PRODUCTO_LOCAL.CA_STOCK_MINIMO ,0) CA_STOCK_MINIMO,
               NVL(PRODUCTO_LOCAL.CA_STOCK_MAXIMO ,0) CA_STOCK_MAXIMO,
               DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
                  'S',
                      TO_CHAR(TRUNC((PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE) / PRODUCTO_LOCAL.VA_FRACCION),'99,990'),
                  TO_CHAR(PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE,'99,990')
               ) || ' / ' ||
               DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
                 'S',
                      TO_CHAR(MOD(PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE, PRODUCTO_LOCAL.VA_FRACCION),'99,990'),
              '   ') STOCK,
               PRODUCTO_LOCAL.IN_PRODUCTO_REPONER IN_PRODUCTO_REPONER,
               NVL(PRODUCTO_LOCAL.CA_STOCK_REPONER_CALCULA ,0) CA_STOCK_REPONER_CALCULA,
               NVL(PRODUCTO_LOCAL.CA_STOCK_REPONER||'' ,' ') CA_STOCK_REPONER,
            NVL(CA_MIN_PROD_EXHIBICION, 0) CA_MIN_PROD_EXHIBICION,
            NVL(CA_PROD_NO_ATENDIDO, 0) CA_PROD_NO_ATENDIDO
         FROM LGTM_PRODUCTO PRODUCTO,
             LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL
        WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
           AND   PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
        AND   PRODUCTO_LOCAL.IN_PRODUCTO_REPONER = 'S' --agregado 20040902
--        AND   PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A'
           AND  (PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A' OR PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE > 0)
        AND   PRODUCTO.CO_LABORATORIO = codigoLaboratorio_in
        AND   PRODUCTO_LOCAL.CO_COMPANIA = PRODUCTO.CO_COMPANIA
           AND   PRODUCTO_LOCAL.CO_PRODUCTO = PRODUCTO.CO_PRODUCTO
        AND   PRODUCTO.ES_PRODUCTO = 'A'
        --ORDER BY PRODUCTO.DE_PRODUCTO
        ) V2
      WHERE  V1.PRODUCTO = V2.CO_PRODUCTO;
      --ORDER BY V2.DE_PRODUCTO;
   END IF;
  ELSE
   IF (codigoLaboratorio_in IS NULL) THEN--3 codigo laboratorio es null y vertodos es N
    OPEN eckcur FOR
         SELECT V2.CO_PRODUCTO || 'Ã' ||
               V2.DE_PRODUCTO || 'Ã' ||
               V2.DE_UNIDAD_PRODUCTO || 'Ã' ||
               V2.CA_STOCK_MINIMO || 'Ã' ||
               V2.CA_STOCK_MAXIMO || 'Ã' ||
               V2.STOCK || 'Ã' ||
            DECODE(NVL(V2.IN_PRODUCTO_REPONER, 'S'), 'S',' ','N') || 'Ã' ||
               V2.CA_STOCK_REPONER_CALCULA || 'Ã' ||
               V2.CA_STOCK_REPONER || 'Ã' ||
            V1.ULTIMO || 'Ã' ||
            V1.ROTACION || 'Ã' ||
            TO_CHAR(V2.CA_MIN_PROD_EXHIBICION, '999,990') || 'Ã' ||
            V2.CA_PROD_NO_ATENDIDO
        FROM   (SELECT /*+ CHOOSE */
                 PROD.CO_PRODUCTO PRODUCTO,
              DECODE(NVL(PROD_LOCAL.NU_MIN_DIAS_REPOSICION,0),
                0,
                DECODE(NVL(LINEA_LOCAL.NU_MIN_DIAS_REPOSICION,0),
               0,
               NVL(LOCAL.NU_MIN_DIAS_REPOSICION,0),
               LINEA_LOCAL.NU_MIN_DIAS_REPOSICION),
             PROD_LOCAL.NU_MIN_DIAS_REPOSICION) || '-' ||
              DECODE(NVL(PROD_LOCAL.NU_MAX_DIAS_REPOSICION,0),
                0,
                DECODE(NVL(LINEA_LOCAL.NU_MAX_DIAS_REPOSICION,0),
               0,
               NVL(LOCAL.NU_MAX_DIAS_REPOSICION,0),
               LINEA_LOCAL.NU_MAX_DIAS_REPOSICION),
             PROD_LOCAL.NU_MAX_DIAS_REPOSICION) || '-' ||
              DECODE(NVL(PROD_LOCAL.NU_DIAS_ROTACION_PROMEDIO,0),
                0,
                DECODE(NVL(LINEA_LOCAL.NU_DIAS_ROTACION_PROMEDIO,0),
               0,
               NVL(LOCAL.NU_DIAS_ROTACION_PROMEDIO,0),
               LINEA_LOCAL.NU_DIAS_ROTACION_PROMEDIO),
             PROD_LOCAL.NU_DIAS_ROTACION_PROMEDIO) ROTACION,
              NVL(CA_ULTIMO_PEDIDO_REP,0) ULTIMO
            FROM LGTR_PRODUCTO_LOCAL PROD_LOCAL,
                VTTM_LOCAL LOCAL,
              LGTM_PRODUCTO PROD,
              LGTR_LINEA_LOCAL LINEA_LOCAL
           WHERE PROD_LOCAL.CO_COMPANIA = codigocompania_in  AND
                 PROD_LOCAL.CO_LOCAL = codigolocal_in AND
              PROD_LOCAL.CO_COMPANIA = LOCAL.CO_COMPANIA AND
              PROD_LOCAL.CO_LOCAL = LOCAL.CO_LOCAL  AND
              PROD_LOCAL.CO_COMPANIA = PROD.CO_COMPANIA AND
              PROD_LOCAL.CO_PRODUCTO = PROD.CO_PRODUCTO  AND
              PROD.CO_COMPANIA = LINEA_LOCAL.CO_COMPANIA(+) AND
              PROD.CO_LINEA_PRODUCTO = LINEA_LOCAL.CO_LINEA_PRODUCTO(+) AND
              LINEA_LOCAL.CO_LOCAL(+) = codigolocal_in) V1,
               (SELECT /*+ CHOOSE */
                 NVL(PRODUCTO_LOCAL.CO_PRODUCTO, ' ') CO_PRODUCTO,
                 NVL(RTRIM(PRODUCTO.DE_PRODUCTO), ' ') DE_PRODUCTO,
                 NVL(RTRIM(PRODUCTO.DE_UNIDAD_PRODUCTO), ' ') DE_UNIDAD_PRODUCTO,
                 NVL(PRODUCTO_LOCAL.CA_STOCK_MINIMO, 0) CA_STOCK_MINIMO,
                 NVL(PRODUCTO_LOCAL.CA_STOCK_MAXIMO, 0) CA_STOCK_MAXIMO,
                 DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
                    'S',
                        TO_CHAR(TRUNC((PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE) / PRODUCTO_LOCAL.VA_FRACCION),'99,990'),
                    TO_CHAR(PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE ,'99,990')
                 ) || ' / ' ||
                 DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
                   'S',
                        TO_CHAR(MOD(PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE, PRODUCTO_LOCAL.VA_FRACCION),'99,990'),
                '   ') STOCK,
                 PRODUCTO_LOCAL.IN_PRODUCTO_REPONER IN_PRODUCTO_REPONER,
                 NVL(PRODUCTO_LOCAL.CA_STOCK_REPONER_CALCULA, 0) CA_STOCK_REPONER_CALCULA,
                 NVL(PRODUCTO_LOCAL.CA_STOCK_REPONER||'' ,' ') CA_STOCK_REPONER,
              NVL(CA_MIN_PROD_EXHIBICION, 0) CA_MIN_PROD_EXHIBICION,
              NVL(CA_PROD_NO_ATENDIDO, 0) CA_PROD_NO_ATENDIDO
           FROM LGTM_PRODUCTO PRODUCTO,
               LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL
          WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
             AND   PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
--             AND   PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A'
             AND  (PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A' OR PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE > 0)
          AND   PRODUCTO.ES_PRODUCTO = 'A'
             AND   (PRODUCTO_LOCAL.CA_STOCK_REPONER > 0 OR PRODUCTO_LOCAL.CA_STOCK_REPONER_CALCULA  > 0)
             AND   PRODUCTO_LOCAL.IN_PRODUCTO_REPONER = 'S'
          AND   PRODUCTO_LOCAL.CO_COMPANIA = PRODUCTO.CO_COMPANIA
             AND   PRODUCTO_LOCAL.CO_PRODUCTO = PRODUCTO.CO_PRODUCTO
           --ORDER BY PRODUCTO.DE_PRODUCTO
          ) V2
        WHERE  V1.PRODUCTO = V2.CO_PRODUCTO;
        --ORDER BY V2.DE_PRODUCTO;
   ELSE
    OPEN eckcur FOR--4 codigo laboratorio no es null y vertodos es N
         SELECT V2.CO_PRODUCTO || 'Ã' ||
               V2.DE_PRODUCTO || 'Ã' ||
               V2.DE_UNIDAD_PRODUCTO || 'Ã' ||
               V2.CA_STOCK_MINIMO || 'Ã' ||
               V2.CA_STOCK_MAXIMO || 'Ã' ||
               V2.STOCK || 'Ã' ||
            DECODE(NVL(V2.IN_PRODUCTO_REPONER, 'S'), 'S',' ','N') || 'Ã' ||
               V2.CA_STOCK_REPONER_CALCULA || 'Ã' ||
               V2.CA_STOCK_REPONER || 'Ã' ||
            V1.ULTIMO || 'Ã' ||
            V1.ROTACION || 'Ã' ||
            TO_CHAR(V2.CA_MIN_PROD_EXHIBICION, '999,990') || 'Ã' ||
            V2.CA_PROD_NO_ATENDIDO
        FROM   (SELECT /*+ CHOOSE */
                 PROD.CO_PRODUCTO PRODUCTO,
              DECODE(NVL(PROD_LOCAL.NU_MIN_DIAS_REPOSICION,0),
                0,
                DECODE(NVL(LINEA_LOCAL.NU_MIN_DIAS_REPOSICION,0),
               0,
               NVL(LOCAL.NU_MIN_DIAS_REPOSICION,0),
               LINEA_LOCAL.NU_MIN_DIAS_REPOSICION),
             PROD_LOCAL.NU_MIN_DIAS_REPOSICION) || '-' ||
              DECODE(NVL(PROD_LOCAL.NU_MAX_DIAS_REPOSICION,0),
                0,
                DECODE(NVL(LINEA_LOCAL.NU_MAX_DIAS_REPOSICION,0),
               0,
               NVL(LOCAL.NU_MAX_DIAS_REPOSICION,0),
               LINEA_LOCAL.NU_MAX_DIAS_REPOSICION),
             PROD_LOCAL.NU_MAX_DIAS_REPOSICION) || '-' ||
              DECODE(NVL(PROD_LOCAL.NU_DIAS_ROTACION_PROMEDIO,0),
                0,
                DECODE(NVL(LINEA_LOCAL.NU_DIAS_ROTACION_PROMEDIO,0),
               0,
               NVL(LOCAL.NU_DIAS_ROTACION_PROMEDIO,0),
               LINEA_LOCAL.NU_DIAS_ROTACION_PROMEDIO),
             PROD_LOCAL.NU_DIAS_ROTACION_PROMEDIO) ROTACION,
              NVL(CA_ULTIMO_PEDIDO_REP,0) ULTIMO
            FROM LGTR_PRODUCTO_LOCAL PROD_LOCAL,
                VTTM_LOCAL LOCAL,
              LGTM_PRODUCTO PROD,
              LGTR_LINEA_LOCAL LINEA_LOCAL
           WHERE PROD_LOCAL.CO_COMPANIA = codigocompania_in  AND
                 PROD_LOCAL.CO_LOCAL = codigolocal_in AND
              PROD_LOCAL.CO_COMPANIA = LOCAL.CO_COMPANIA AND
              PROD_LOCAL.CO_LOCAL = LOCAL.CO_LOCAL  AND
              PROD_LOCAL.CO_COMPANIA = PROD.CO_COMPANIA AND
              PROD_LOCAL.CO_PRODUCTO = PROD.CO_PRODUCTO  AND
              PROD.CO_COMPANIA = LINEA_LOCAL.CO_COMPANIA(+) AND
              PROD.CO_LINEA_PRODUCTO = LINEA_LOCAL.CO_LINEA_PRODUCTO(+) AND
              LINEA_LOCAL.CO_LOCAL(+) = codigolocal_in) V1,
               (SELECT /*+ CHOOSE */
                 NVL(PRODUCTO_LOCAL.CO_PRODUCTO, ' ') CO_PRODUCTO,
                 NVL(RTRIM(PRODUCTO.DE_PRODUCTO), ' ') DE_PRODUCTO,
                 NVL(RTRIM(PRODUCTO.DE_UNIDAD_PRODUCTO), ' ') DE_UNIDAD_PRODUCTO,
                 NVL(PRODUCTO_LOCAL.CA_STOCK_MINIMO, 0) CA_STOCK_MINIMO,
                 NVL(PRODUCTO_LOCAL.CA_STOCK_MAXIMO, 0) CA_STOCK_MAXIMO,
                 DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
                    'S',
                        TO_CHAR(TRUNC((PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE) / PRODUCTO_LOCAL.VA_FRACCION),'99,990'),
                    TO_CHAR(PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE ,'99,990')
                 ) || ' / ' ||
                 DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
                   'S',
                        TO_CHAR(MOD(PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE , PRODUCTO_LOCAL.VA_FRACCION),'99,990'),
                '   ') STOCK,
                 PRODUCTO_LOCAL.IN_PRODUCTO_REPONER IN_PRODUCTO_REPONER,
                 NVL(PRODUCTO_LOCAL.CA_STOCK_REPONER_CALCULA ,0) CA_STOCK_REPONER_CALCULA,
                 NVL(PRODUCTO_LOCAL.CA_STOCK_REPONER||'' ,' ') CA_STOCK_REPONER,
              NVL(CA_MIN_PROD_EXHIBICION, 0) CA_MIN_PROD_EXHIBICION,
              NVL(CA_PROD_NO_ATENDIDO, 0) CA_PROD_NO_ATENDIDO
           FROM LGTM_PRODUCTO PRODUCTO,
               LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL
          WHERE  PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
             AND   PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
--             AND   PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A'
             AND  (PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A' OR PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE > 0)
          AND   PRODUCTO_LOCAL.IN_PRODUCTO_REPONER = 'S' --agregado 20040902
             AND   PRODUCTO.CO_LABORATORIO = codigoLaboratorio_in
             AND   (PRODUCTO_LOCAL.CA_STOCK_REPONER > 0 OR PRODUCTO_LOCAL.CA_STOCK_REPONER_CALCULA  > 0)
             AND   PRODUCTO_LOCAL.IN_PRODUCTO_REPONER = 'S'
                AND   PRODUCTO_LOCAL.CO_COMPANIA = PRODUCTO.CO_COMPANIA
             AND   PRODUCTO_LOCAL.CO_PRODUCTO = PRODUCTO.CO_PRODUCTO
          AND   PRODUCTO.ES_PRODUCTO = 'A'
           --ORDER BY PRODUCTO.DE_PRODUCTO
          ) V2
        WHERE  V1.PRODUCTO = V2.CO_PRODUCTO;
        --ORDER BY V2.DE_PRODUCTO;
   END IF;
  END IF;
    RETURN eckcur;
  END;

/**********************************************************************************************************************/
FUNCTION GET_STOCK_PRODUCTO_LOCAL(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
          codigolocal_in    IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
          producto_in   IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
        RETURN EckerdCursor IS
    eckcur EckerdCursor;
BEGIN
  OPEN eckcur FOR
    SELECT NVL(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,'N') || 'Ã' ||
        TO_CHAR((NVL(PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE,0) - NVL(PRODUCTO_LOCAL.CA_STOCK_COMPROMETIDO,0)),'999,990') || 'Ã' ||
        TO_CHAR( TRUNC( ( NVL(PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE,0) - NVL(PRODUCTO_LOCAL.CA_STOCK_COMPROMETIDO,0))
        / DECODE( NVL(PRODUCTO_LOCAL.VA_FRACCION, 0), 0, 1, PRODUCTO_LOCAL.VA_FRACCION)), '999,990') || 'Ã' ||
        TO_CHAR( MOD( ( NVL(PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE, 0) - NVL(PRODUCTO_LOCAL.CA_STOCK_COMPROMETIDO,0)),
           DECODE( NVL(PRODUCTO_LOCAL.VA_FRACCION, 0), 0, 1, PRODUCTO_LOCAL.VA_FRACCION)), '999,990') || 'Ã' ||
     DECODE(NVL(PRODUCTO_LOCAL.VA_FRACCION,0),0,1,PRODUCTO_LOCAL.VA_FRACCION)
    FROM   LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL
    WHERE  PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
    AND    PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
    AND    PRODUCTO_LOCAL.CO_PRODUCTO = producto_in;
    RETURN eckcur;
END;

/* ******************************************************************************** */
FUNCTION GET_VA_FRACCION_PRODUCTO_LOCAL(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
          codigolocal_in    IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
          producto_in   IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
        RETURN NUMBER IS
    fraccion NUMBER;
 BEGIN
     SELECT TO_NUMBER(DECODE(NVL(PRODUCTO_LOCAL.VA_FRACCION,0),0,1,PRODUCTO_LOCAL.VA_FRACCION))
    INTO   fraccion
    FROM   LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL
    WHERE  PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
    AND    PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
    AND    PRODUCTO_LOCAL.CO_PRODUCTO = producto_in;
     RETURN fraccion;
 END;

/* ************************************************************************ */
  PROCEDURE INV_GRABA_KARDEX(codigocompania_in   IN LGTV_KARDEX.CO_COMPANIA%TYPE,
        codigolocal_in    IN LGTV_KARDEX.CO_LOCAL%TYPE,
        codigoproducto_in    IN LGTV_KARDEX.CO_PRODUCTO%TYPE,
        cantmovimiento_in    IN LGTV_KARDEX.CA_MOVIMIENTO%TYPE,
        tipodocumento_in     IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
        numerodocumento_in   IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
        descglosa_in         IN LGTV_KARDEX.DE_GLOSA%TYPE,
        codigogrupomotivo_in IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
        codigomotivo_in      IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE,
        idcreakardex_in      IN LGTV_KARDEX.ID_CREA_KARDEX%TYPE )
  IS
    l_numeracion        CMTR_NUMERACION.NU_SEC_NUMERACION%TYPE;
    l_inprodfraccionado LGTR_PRODUCTO_LOCAL.IN_PROD_FRACCIONADO%TYPE;
    l_codigounidadventa LGTR_PRODUCTO_LOCAL.CO_UNIDAD_VENTA_FRACCION%TYPE;
    l_stockdisponible   LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;
    l_valorfraccion     LGTR_PRODUCTO_LOCAL.VA_FRACCION%TYPE;
    l_preciopromedio    LGTM_PRODUCTO.VA_PRECIO_PROMEDIO%TYPE;
    l_costoproducto     LGTR_PRODUCTO_LOCAL.VA_COSTO_PRODUCTO%TYPE;
    l_totalinventario   LGTR_PRODUCTO_LOCAL.VA_TOTAL_INVENTARIO%TYPE;
  BEGIN
    l_numeracion := Ptovta_Utility.OBTENER_NUMERACION(codigocompania_in,codigolocal_in,'007');

    SELECT PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
           DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO, 'S', PRODUCTO_LOCAL.CO_UNIDAD_VENTA_FRACCION, PRODUCTO.CO_UNIDAD_VENTA),
           PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE, PRODUCTO_LOCAL.VA_FRACCION, PRODUCTO.VA_PRECIO_PROMEDIO,
           PRODUCTO_LOCAL.VA_COSTO_PRODUCTO
      INTO l_inprodfraccionado, l_codigounidadventa, l_stockdisponible, l_valorfraccion, l_preciopromedio,
           l_costoproducto
      FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL,
           LGTM_PRODUCTO PRODUCTO
     WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
       AND PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
       AND PRODUCTO_LOCAL.CO_PRODUCTO = codigoproducto_in
       AND PRODUCTO_LOCAL.CO_COMPANIA = PRODUCTO.CO_COMPANIA
       AND PRODUCTO_LOCAL.CO_PRODUCTO = PRODUCTO.CO_PRODUCTO FOR UPDATE;

    l_totalinventario := l_costoproducto * (l_stockdisponible + cantmovimiento_in);

    INSERT INTO LGTV_KARDEX (CO_COMPANIA, CO_LOCAL, CO_PRODUCTO, NU_REVISION_PRODUCTO, NU_SEC_KARDEX,
                    TI_DOCUMENTO, NU_DOCUMENTO, IN_PROD_FRACCIONADO, CA_STOCK_INICIAL, CO_UNIDAD_VENTA_INICIAL,
                    VA_FRACCION_INICIAL, CA_MOVIMIENTO, CO_UNIDAD_VENTA_FINAL, CA_STOCK_FINAL, VA_FRACCION_FINAL,
                    FE_KARDEX, CO_GRUPO_MOTIVO, CO_MOTIVO_KARDEX, VA_PRECIO_PROMEDIO, DE_GLOSA, ES_KARDEX,
                    ID_CREA_KARDEX, FE_CREA_KARDEX, VA_COSTO_PRODUCTO, VA_TOTAL_INVENTARIO, VA_COSTO_PROMEDIO)
            VALUES (codigocompania_in, codigolocal_in, codigoproducto_in, '0', l_numeracion,
                    tipodocumento_in, numerodocumento_in, l_inprodfraccionado, l_stockdisponible, l_codigounidadventa,
                    l_valorfraccion, cantmovimiento_in, l_codigounidadventa, (l_stockdisponible + cantmovimiento_in), l_valorfraccion,
                    SYSDATE, codigogrupomotivo_in, codigomotivo_in, l_preciopromedio, descglosa_in,
                    'A', idcreakardex_in, SYSDATE, l_costoproducto, l_totalinventario, l_costoproducto);

    UPDATE LGTR_PRODUCTO_LOCAL
       SET CA_STOCK_DISPONIBLE =  l_stockdisponible + cantmovimiento_in,
           FE_MOD_PROD_LOCAL = SYSDATE,
           IN_REPLICA = 0,
           VA_COSTO_PRODUCTO = l_costoproducto,
           VA_TOTAL_INVENTARIO = l_totalinventario
     WHERE CO_COMPANIA = codigocompania_in
       AND CO_LOCAL = codigolocal_in
       AND CO_PRODUCTO = codigoproducto_in;

    Ptovta_Utility.ACTUALIZAR_NUMERACION(codigocompania_in,codigolocal_in,'007',idcreakardex_in);

  END;

  /* ************************************************************************ */

  FUNCTION RELACION_LOCALES(codigocompania_in IN VTTM_LOCAL.CO_COMPANIA%TYPE,
            codigolocal_in IN VTTM_LOCAL.CO_LOCAL%TYPE,
            tipolocal_in IN CHAR)
        RETURN EckerdCursor IS
    eckcur EckerdCursor;
 v_codigosucursal VTTM_LOCAL.CO_SUCURSAL%TYPE;
  BEGIN

   SELECT   CO_SUCURSAL
 INTO  v_codigosucursal
 FROM   VTTM_LOCAL
 WHERE   CO_COMPANIA = codigocompania_in
 AND   CO_LOCAL = codigolocal_in
--    AND   TI_LOCAL = tipolocal_in
 AND   ES_LOCAL = 'A';

    OPEN eckcur FOR SELECT LOCAL.CO_LOCAL || 'Ã' ||
                        LOCAL.DE_LOCAL
                      FROM VTTM_LOCAL LOCAL
                     WHERE LOCAL.CO_COMPANIA = codigocompania_in
        AND LOCAL.ES_LOCAL = 'A'
        AND LOCAL.TI_LOCAL = tipolocal_in
        AND ( ((tipolocal_in != 'A') AND LOCAL.CO_SUCURSAL = v_codigosucursal)
              OR
        (tipolocal_in = 'A')
       )
      ORDER BY LOCAL.DE_LOCAL;

 RETURN eckcur;
  END;

  /* ************************************************************************ */
  FUNCTION INV_AFECTA_POR_PRODUCTO(codigocompania_in    IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                                   codigolocal_in       IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
           numerorecepcion_in IN LGTC_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE,
           numeropagina_in      IN LGTD_RECEPCION_PRODUCTO.NU_PAGINA_GUIA%TYPE,
           codigoproducto_in IN LGTD_RECEPCION_PRODUCTO.CO_PRODUCTO%TYPE,
           idmodrecepguia_in    IN LGTC_RECEPCION_PRODUCTO.ID_MOD_RECEPCION_PRODUCTO%TYPE,
           codigogrupomotivo_in IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
           codigomotivo_in      IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE,
           tipodocumento_in     IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
           numerodocumento_in   IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
           inafectastock_in     IN LGTC_RECEPCION_PRODUCTO.IN_AFECTA_STOCK%TYPE
        ) RETURN CHAR IS
  codigoProducto          LGTD_RECEPCION_PRODUCTO.CO_PRODUCTO%TYPE;
  cantenviadaentera       LGTD_RECEPCION_PRODUCTO.CA_ENVIADA_ENTERA%TYPE;
  cantenviadafraccion     LGTD_RECEPCION_PRODUCTO.CA_ENVIADA_FRACCION%TYPE;
  cantconfirmadaentera    LGTD_RECEPCION_PRODUCTO.CA_CONFIRMADA_ENTERA%TYPE;
  cantconfirmadafraccion  LGTD_RECEPCION_PRODUCTO.CA_CONFIRMADA_FRACCION%TYPE;
  cantdiferenciaentera    LGTD_RECEPCION_PRODUCTO.CA_DIFERENCIA_ENTERA%TYPE;
  cantdiferenciafraccion  LGTD_RECEPCION_PRODUCTO.CA_DIFERENCIA_FRACCION%TYPE;
  inprodfraccionado       LGTR_PRODUCTO_LOCAL.IN_PROD_FRACCIONADO%TYPE;
  valorfraccion           LGTR_PRODUCTO_LOCAL.VA_FRACCION%TYPE;
  cantmovimiento          LGTV_KARDEX.CA_MOVIMIENTO%TYPE;
  cantidad NUMBER;
  cerrarguia CHAR;
  --cargando el cursor de detalle de guma
    CURSOR detalleGuia IS
         SELECT DET_RECEPCION.CO_PRODUCTO,
       DET_RECEPCION.CA_ENVIADA_ENTERA,
       DET_RECEPCION.CA_ENVIADA_FRACCION,
       DET_RECEPCION.CA_CONFIRMADA_ENTERA,
       DET_RECEPCION.CA_CONFIRMADA_FRACCION,
       DET_RECEPCION.CA_DIFERENCIA_ENTERA,
       DET_RECEPCION.CA_DIFERENCIA_FRACCION,
       DET_RECEPCION.NU_ITEM_RECEPCION_GUIA,
          PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
       PRODUCTO_LOCAL.VA_FRACCION
     FROM LGTD_RECEPCION_PRODUCTO DET_RECEPCION,
          LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL
    WHERE DET_RECEPCION.CO_COMPANIA       = codigocompania_in  AND
          DET_RECEPCION.CO_LOCAL          = codigolocal_in AND
       DET_RECEPCION.NU_RECEPCION_PRODUCTO = numerorecepcion_in AND
       DET_RECEPCION.CO_PRODUCTO = codigoproducto_in AND
       DET_RECEPCION.IN_AFECTA_PRODUCTO IS NULL AND
       DET_RECEPCION.CO_COMPANIA       = PRODUCTO_LOCAL.CO_COMPANIA  AND
          DET_RECEPCION.CO_LOCAL          = PRODUCTO_LOCAL.CO_LOCAL AND
       DET_RECEPCION.CO_PRODUCTO       = PRODUCTO_LOCAL.CO_PRODUCTO AND
       DET_RECEPCION.NU_PAGINA_GUIA    = numeropagina_in;
  BEGIN
   FOR detalleGuia_rec IN detalleGuia
  LOOP
       codigoProducto := detalleGuia_rec.CO_PRODUCTO;
     cantenviadaentera := NVL(detalleGuia_rec.CA_ENVIADA_ENTERA, 0);
     cantenviadafraccion := NVL(detalleGuia_rec.CA_ENVIADA_FRACCION, 0);
     cantconfirmadaentera := NVL(detalleGuia_rec.CA_CONFIRMADA_ENTERA,0);
     cantconfirmadafraccion := NVL(detalleGuia_rec.CA_CONFIRMADA_FRACCION,0);
     cantdiferenciaentera := NVL(detalleGuia_rec.CA_DIFERENCIA_ENTERA, 0);
     cantdiferenciafraccion := NVL(detalleGuia_rec.CA_DIFERENCIA_FRACCION, 0);
     --variables de fraccisn del prodcuto
     inprodfraccionado := detalleGuia_rec.IN_PROD_FRACCIONADO;
     valorfraccion := detalleGuia_rec.VA_FRACCION;
     --afectando los productos que se ha cambiado la cantidad de recepcisn
     IF cantdiferenciaentera <> 0 OR cantdiferenciafraccion <> 0 THEN
       -- 1er REGISTRO
       --afectando cantidad enviada
      IF (inprodfraccionado = 'S') THEN
         cantmovimiento := (cantenviadaentera * valorfraccion) + cantenviadafraccion;
      ELSE
         cantmovimiento := cantenviadaentera;
      END IF;
      --graba kardex y afecta stock
      IF (inafectastock_in = 'S') THEN
         INV_GRABA_KARDEX(codigocompania_in, codigolocal_in, codigoproducto, cantmovimiento,
                        tipodocumento_in, numerodocumento_in, 'Guia Matriz', codigogrupomotivo_in,
               codigomotivo_in, idmodrecepguia_in);
      END IF;
     -- 2do REGISTRO
       --afectando diferencia
      IF (inprodfraccionado = 'S') THEN
         cantmovimiento := (cantdiferenciaentera * valorfraccion) + cantdiferenciafraccion;
      ELSE
         cantmovimiento := cantdiferenciaentera;
      END IF;
      --graba kardex y afecta stock
      IF (inafectastock_in = 'S') THEN
         INV_GRABA_KARDEX(codigocompania_in, codigolocal_in, codigoproducto, cantmovimiento,
                        tipodocumento_in, numerodocumento_in, 'Guia Matriz', codigogrupomotivo_in,
               COD_MOT_GUIA_MODERN, idmodrecepguia_in);
      END IF;
     ELSE
     --afecta los productos que no se han cambiado la cantidad de recepcisn
      IF (inprodfraccionado = 'S') THEN
         cantmovimiento := (cantconfirmadaentera * valorfraccion) + cantconfirmadafraccion;
      ELSE
         cantmovimiento := cantconfirmadaentera;
      END IF;
      --Graba Kardex y afecta stock
      IF (inafectastock_in = 'S') THEN
         INV_GRABA_KARDEX(codigocompania_in, codigolocal_in, codigoproducto, cantmovimiento,
                        tipodocumento_in, numerodocumento_in, 'Guia Matriz', codigogrupomotivo_in,
               codigomotivo_in, idmodrecepguia_in);
      END IF;
     END IF;

     --Actualiza el detalle de la Guia-->actualiza el indicador de afecta producto
     UPDATE LGTD_RECEPCION_PRODUCTO
        SET IN_AFECTA_PRODUCTO = 'S',
         ID_MOD_DET_RECEPCION_PRODUCTO = idmodrecepguia_in,
      FE_MOD_DET_RECEPCION_PRODUCTO = SYSDATE,
      IN_REPLICA = 0
      WHERE CO_COMPANIA = codigocompania_in  AND
            CO_LOCAL = codigolocal_in AND
      NU_RECEPCION_PRODUCTO = numerorecepcion_in AND
      CO_PRODUCTO = codigoProducto;
     COMMIT;
  END LOOP;

  -- Se verifica si no existen paginas pendientes de afectar stock
  -- para proceder a cerrar el total de la guia
  SELECT COUNT(*)
    INTO cantidad
    FROM LGTD_RECEPCION_PRODUCTO
   WHERE CO_COMPANIA =codigocompania_in  AND
          CO_LOCAL = codigolocal_in AND
    NU_RECEPCION_PRODUCTO = numerorecepcion_in AND
    IN_AFECTA_PRODUCTO IS NULL;
  IF (cantidad = 0) THEN
     UPDATE LGTC_RECEPCION_PRODUCTO
        SET ES_RECEPCION_PRODUCTO = 'C',
       FE_CIERRE_RECEPCION = SYSDATE,
         ID_MOD_RECEPCION_PRODUCTO = idmodrecepguia_in,
      FE_MOD_RECEPCION_PRODUCTO = SYSDATE,
      IN_REPLICA = 0
      WHERE CO_COMPANIA  = codigocompania_in  AND
            CO_LOCAL     = codigolocal_in AND
      NU_RECEPCION_PRODUCTO = numerorecepcion_in;
     COMMIT;
     cerrarguia := 'S';
  ELSE
     cerrarguia := 'N';
  END IF;
  RETURN cerrarguia;
  END;

  /***********************************************************************/

  FUNCTION LISTADATATABLAS(codigocompania_in IN CMTR_LABORATORIO.CO_COMPANIA%TYPE,
                           nombretabla_in    IN VARCHAR2)
           RETURN EckerdCursor  IS
    eckcur EckerdCursor;
  BEGIN
 IF (nombretabla_in = 'L') THEN  --LABORATORIO
    OPEN eckcur FOR SELECT NVL(LABORATORIO.CO_LABORATORIO,' ') || 'Ã' ||
                           DECODE(LABORATORIO.DE_LABORATORIO,NULL,' ',DE_LABORATORIO)
       FROM CMTR_LABORATORIO LABORATORIO
      WHERE LABORATORIO.CO_COMPANIA = codigocompania_in
      ORDER BY DE_LABORATORIO;
 ELSIF (nombretabla_in = 'G') THEN  --GRUPO PRODUCTO
    OPEN eckcur FOR SELECT NVL(GRUPO.CO_GRUPO_PRODUCTO,' ') || 'Ã' ||
                           NVL(GRUPO.DE_GRUPO_PRODUCTO,' ')
       FROM LGTR_GRUPO_PRODUCTO GRUPO
      WHERE GRUPO.CO_COMPANIA = codigocompania_in
      ORDER BY DE_GRUPO_PRODUCTO;
 ELSIF (nombretabla_in = 'A') THEN  --ACCION TERAPEUTICA
    OPEN eckcur FOR SELECT NVL(ACCION.CO_ACCION_TERAPEUTICA,' ') || 'Ã' ||
                           NVL(ACCION.DE_ACCION_TERAPEUTICA,' ')
       FROM VTTR_ACCION_TERAPEUTICA ACCION
      WHERE ACCION.CO_COMPANIA = codigocompania_in
         ORDER BY DE_ACCION_TERAPEUTICA;
 ELSIF (nombretabla_in = 'P') THEN  --PRINCIPIO ACTIVO
    OPEN eckcur FOR SELECT NVL(PRINCIPIO.CO_PRINCIPIO_ACTIVO,' ') || 'Ã' ||
                           NVL(PRINCIPIO.DE_PRINCIPIO_ACTIVO,' ')
       FROM LGTM_PRINCIPIO_ACTIVO PRINCIPIO
      WHERE PRINCIPIO.ES_PRINCIPIO_ACTIVO = 'A'
         ORDER BY PRINCIPIO.DE_PRINCIPIO_ACTIVO;
 END IF;
    IF (nombretabla_in = 'Z') THEN  --LGTR_GRUPO_PROD_ERP
    OPEN eckcur FOR SELECT NVL(GRU.CO_GRUPO_PROD_ERP,' ') || 'Ã' ||
         NVL(GRU.DE_GRUPO_PROD_ERP,' ')
        FROM  LGTR_GRUPO_PROD_ERP GRU
        ORDER BY NVL(GRU.DE_GRUPO_PROD_ERP,' ');
 ELSIF (nombretabla_in = 'Y') THEN  --LGTR_LINEA_PROD_ERP
    OPEN eckcur FOR SELECT NVL(LIN.CO_LINEA_PROD_ERP,' ') || 'Ã' ||
         NVL(LIN.DE_LINEA_PROD_ERP,' ')
      FROM   LGTR_LINEA_PROD_ERP LIN
      ORDER BY NVL(LIN.DE_LINEA_PROD_ERP,' ');
 ELSIF (nombretabla_in = 'X') THEN  --LGTR_GRUPO_TERAPEUTICO
    OPEN eckcur FOR SELECT NVL(GRU.CO_GRUPO_TERAPEUTICO,' ') || 'Ã' ||
         NVL(GRU.DE_GRUPO_TERAPEUTICO,' ')
      FROM   LGTR_GRUPO_TERAPEUTICO GRU
      ORDER BY NVL(GRU.DE_GRUPO_TERAPEUTICO,' ');
 ELSIF (nombretabla_in = 'W') THEN  --LGTR_GRUPO_ANATOMICO
    OPEN eckcur FOR SELECT NVL(GRU.CO_GRUPO_ANATOMICO,' ') || 'Ã' ||
         NVL(GRU.DE_GRUPO_ANATOMICO,' ')
      FROM   LGTR_GRUPO_ANATOMICO GRU
      ORDER BY NVL(GRU.DE_GRUPO_ANATOMICO,' ');
 /*ELSIF (nombretabla_in = 'V') THEN  --LGTR_ACCION_TERAPEUTICA
    OPEN eckcur FOR SELECT NVL(ACC.CO_ACCION_TERAPEUTICA,' ') || 'Ã' ||
         NVL(ACC.DE_ACCION_TERAPEUTICA,' ')
      FROM   LGTR_ACCION_TERAPEUTICA ACC
      ORDER BY NVL(ACC.DE_ACCION_TERAPEUTICA,' ');*/
 ELSIF (nombretabla_in = 'U') THEN  --LGTR_ACCION_FARMACEUTICA
    OPEN eckcur FOR SELECT NVL(ACC.CO_ACCION_FARMACEUTICA,' ') || 'Ã' ||
         NVL(ACC.DE_ACCION_FARMACEUTICA,' ')
      FROM   LGTR_ACCION_FARMACEUTICA ACC
      ORDER BY NVL(ACC.DE_ACCION_FARMACEUTICA,' ');
 END IF;
    RETURN eckcur;
  END;

  /****************************************************/

  FUNCTION RELACIONPRECIOPRODUCTOSFILTRO(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
                                         codigolocal_in    IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
           tablafiltro_in    VARCHAR2,
           codigofiltro_in   CHAR)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
 IF ( tablafiltro_in = 'L' ) THEN  --LABORATORIO
     OPEN eckcur FOR SELECT NVL(PRODUCTO_LOCAL.CO_PRODUCTO,' ') || 'Ã' ||
                  NVL(RTRIM(PRODUCTO.DE_PRODUCTO) ,' ') || 'Ã' ||
          NVL(RTRIM(PRODUCTO.DE_UNIDAD_PRODUCTO),' ') || 'Ã' ||
        NVL(RTRIM(PRODUCTO_LOCAL.DE_UNIDAD_FRACCION),' ') || 'Ã' ||
        TO_CHAR(NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
                          'S',
            TRUNC(CA_STOCK_DISPONIBLE/VA_FRACCION),
            PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE),0),
          '999,999,990')  || 'Ã' ||
           TO_CHAR(NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
               'S',
            MOD(CA_STOCK_DISPONIBLE, VA_FRACCION),
            0), 0),
             '999,990') || 'Ã' ||
         NVL(PRODUCTO_LOCAL.IN_PROD_INVENTARIO,' ')
       FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL,
            LGTM_PRODUCTO PRODUCTO
       WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
        AND  PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
        AND  PRODUCTO.CO_LABORATORIO = codigofiltro_in
--        AND  PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A'
        AND (PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A' OR PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE > 0)
        AND  PRODUCTO.CO_COMPANIA = PRODUCTO_LOCAL.CO_COMPANIA
        AND  PRODUCTO.CO_PRODUCTO = PRODUCTO_LOCAL.CO_PRODUCTO
        AND PRODUCTO.ES_PRODUCTO = 'A';
       --ORDER BY TRIM(PRODUCTO.DE_PRODUCTO);
    ELSIF ( tablafiltro_in = 'G' ) THEN  --GRUPO PRODUCTO
     OPEN eckcur FOR SELECT NVL(PRODUCTO_LOCAL.CO_PRODUCTO,' ') || 'Ã' ||
                  NVL(RTRIM(PRODUCTO.DE_PRODUCTO) ,' ') || 'Ã' ||
          NVL(RTRIM(PRODUCTO.DE_UNIDAD_PRODUCTO),' ') || 'Ã' ||
        NVL(RTRIM(PRODUCTO_LOCAL.DE_UNIDAD_FRACCION),' ') || 'Ã' ||
        TO_CHAR(NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
                          'S',
            TRUNC(CA_STOCK_DISPONIBLE/VA_FRACCION),
            PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE),0),
          '999,999,990')  || 'Ã' ||
           TO_CHAR(NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
               'S',
            MOD(CA_STOCK_DISPONIBLE, VA_FRACCION),
            0), 0),
             '999,990') || 'Ã' ||
         NVL(PRODUCTO_LOCAL.IN_PROD_INVENTARIO, ' ')
       FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL,
            LGTM_PRODUCTO PRODUCTO
       WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
        AND  PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
        AND  PRODUCTO.CO_GRUPO_PRODUCTO = codigofiltro_in
--        AND  PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A'
        AND (PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A' OR PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE > 0)
        AND  PRODUCTO.CO_COMPANIA = PRODUCTO_LOCAL.CO_COMPANIA
        AND  PRODUCTO.CO_PRODUCTO = PRODUCTO_LOCAL.CO_PRODUCTO
        AND PRODUCTO.ES_PRODUCTO = 'A';
       --ORDER BY TRIM(PRODUCTO.DE_PRODUCTO);
    ELSIF ( tablafiltro_in = 'A') THEN  --ACCION TERAPEUTICA
     OPEN eckcur FOR SELECT NVL(PRODUCTO_LOCAL.CO_PRODUCTO,' ') || 'Ã' ||
                  NVL(RTRIM(PRODUCTO.DE_PRODUCTO) ,' ') || 'Ã' ||
          NVL(RTRIM(PRODUCTO.DE_UNIDAD_PRODUCTO),' ') || 'Ã' ||
        NVL(RTRIM(PRODUCTO_LOCAL.DE_UNIDAD_FRACCION),' ') || 'Ã' ||
        TO_CHAR(NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
                          'S',
            TRUNC(CA_STOCK_DISPONIBLE/VA_FRACCION),
            PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE),0),
          '999,999,990')  || 'Ã' ||
           TO_CHAR(NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
               'S',
            MOD(CA_STOCK_DISPONIBLE, VA_FRACCION),
            0), 0),
             '999,990') || 'Ã' ||
         NVL(PRODUCTO_LOCAL.IN_PROD_INVENTARIO, ' ')
       FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL,
            LGTM_PRODUCTO PRODUCTO,
         VTTR_ACCION_TERAPEUTICA ACCION
       WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
        AND  PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
        AND  PRODUCTO.CO_COMPANIA = ACCION.CO_COMPANIA
         AND  PRODUCTO.CO_PRODUCTO = ACCION.CO_PRODUCTO
            AND  ACCION.CO_ACCION_TERAPEUTICA = codigofiltro_in
--        AND  PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A'
        AND (PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A' OR PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE > 0)
        AND  PRODUCTO.CO_COMPANIA = PRODUCTO_LOCAL.CO_COMPANIA
        AND  PRODUCTO.CO_PRODUCTO = PRODUCTO_LOCAL.CO_PRODUCTO
        AND  PRODUCTO.ES_PRODUCTO = 'A';
       --ORDER BY TRIM(PRODUCTO.DE_PRODUCTO);
 ELSIF ( tablafiltro_in = 'P') THEN  --PRINCIPIO ACTIVO
     OPEN eckcur FOR SELECT NVL(PRODUCTO_LOCAL.CO_PRODUCTO,' ') || 'Ã' ||
                  NVL(RTRIM(PRODUCTO.DE_PRODUCTO) ,' ') || 'Ã' ||
          NVL(RTRIM(PRODUCTO.DE_UNIDAD_PRODUCTO),' ') || 'Ã' ||
        NVL(RTRIM(PRODUCTO_LOCAL.DE_UNIDAD_FRACCION),' ') || 'Ã' ||
        TO_CHAR(NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
                          'S',
            TRUNC(CA_STOCK_DISPONIBLE/VA_FRACCION),
            PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE),0),
          '999,999,990')  || 'Ã' ||
           TO_CHAR(NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
               'S',
            MOD(CA_STOCK_DISPONIBLE, VA_FRACCION),
            0), 0),
             '999,990') || 'Ã' ||
         NVL(PRODUCTO_LOCAL.IN_PROD_INVENTARIO, ' ')
       FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL,
            LGTM_PRODUCTO PRODUCTO,
         LGTR_PRODUCTO_PRINCIPIO PRODUCTO_PRINCIPIO
       WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
        AND  PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
        AND  PRODUCTO_PRINCIPIO.CO_PRINCIPIO_ACTIVO = codigofiltro_in
        AND  PRODUCTO_PRINCIPIO.CO_PRODUCTO = PRODUCTO.CO_PRODUCTO
--        AND  PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A'
        AND (PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A' OR PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE > 0)
        AND  PRODUCTO.CO_COMPANIA = PRODUCTO_LOCAL.CO_COMPANIA
        AND  PRODUCTO.CO_PRODUCTO = PRODUCTO_LOCAL.CO_PRODUCTO
        AND  PRODUCTO.ES_PRODUCTO = 'A';
       --ORDER BY TRIM(PRODUCTO.DE_PRODUCTO);
 ELSIF ( tablafiltro_in = 'Z' ) THEN  --LGTR_GRUPO_PROD_ERP
     OPEN eckcur FOR SELECT NVL(PRODUCTO_LOCAL.CO_PRODUCTO,' ') || 'Ã' ||
                  NVL(RTRIM(PRODUCTO.DE_PRODUCTO) ,' ') || 'Ã' ||
          NVL(RTRIM(PRODUCTO.DE_UNIDAD_PRODUCTO),' ') || 'Ã' ||
        NVL(RTRIM(PRODUCTO_LOCAL.DE_UNIDAD_FRACCION),' ') || 'Ã' ||
        TO_CHAR(NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
                          'S',
            TRUNC(CA_STOCK_DISPONIBLE/VA_FRACCION),
            PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE),0),
          '999,999,990')  || 'Ã' ||
           TO_CHAR(NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
               'S',
            MOD(CA_STOCK_DISPONIBLE, VA_FRACCION),
            0), 0),
             '999,990') || 'Ã' ||
         NVL(PRODUCTO_LOCAL.IN_PROD_INVENTARIO, ' ')
       FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL,
            LGTM_PRODUCTO PRODUCTO
       WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
        AND  PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
        AND  PRODUCTO.CO_GRUPO_PROD_ERP = codigofiltro_in
--        AND  PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A'
        AND (PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A' OR PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE > 0)
        AND  PRODUCTO.CO_COMPANIA = PRODUCTO_LOCAL.CO_COMPANIA
        AND  PRODUCTO.CO_PRODUCTO = PRODUCTO_LOCAL.CO_PRODUCTO
        AND  PRODUCTO.ES_PRODUCTO = 'A';
       --ORDER BY TRIM(PRODUCTO.DE_PRODUCTO);
 ELSIF ( tablafiltro_in = 'Y' ) THEN  --LGTR_LINEA_PROD_ERP
     OPEN eckcur FOR SELECT NVL(PRODUCTO_LOCAL.CO_PRODUCTO,' ') || 'Ã' ||
                  NVL(RTRIM(PRODUCTO.DE_PRODUCTO) ,' ') || 'Ã' ||
          NVL(RTRIM(PRODUCTO.DE_UNIDAD_PRODUCTO),' ') || 'Ã' ||
        NVL(RTRIM(PRODUCTO_LOCAL.DE_UNIDAD_FRACCION),' ') || 'Ã' ||
        TO_CHAR(NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
                          'S',
            TRUNC(CA_STOCK_DISPONIBLE/VA_FRACCION),
            PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE),0),
          '999,999,990')  || 'Ã' ||
           TO_CHAR(NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
               'S',
            MOD(CA_STOCK_DISPONIBLE, VA_FRACCION),
            0), 0),
             '999,990') || 'Ã' ||
         NVL(PRODUCTO_LOCAL.IN_PROD_INVENTARIO, ' ')
       FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL,
            LGTM_PRODUCTO PRODUCTO
       WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
        AND  PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
        AND  PRODUCTO.CO_LINEA_PROD_ERP = codigofiltro_in
--        AND  PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A'
        AND (PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A' OR PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE > 0)
        AND  PRODUCTO.CO_COMPANIA = PRODUCTO_LOCAL.CO_COMPANIA
        AND  PRODUCTO.CO_PRODUCTO = PRODUCTO_LOCAL.CO_PRODUCTO
        AND  PRODUCTO.ES_PRODUCTO = 'A';
       --ORDER BY TRIM(PRODUCTO.DE_PRODUCTO);
    ELSIF ( tablafiltro_in = 'X') THEN  --LGTR_GRUPO_TERAPEUTICO
     OPEN eckcur FOR SELECT NVL(PRODUCTO_LOCAL.CO_PRODUCTO,' ') || 'Ã' ||
                  NVL(RTRIM(PRODUCTO.DE_PRODUCTO) ,' ') || 'Ã' ||
          NVL(RTRIM(PRODUCTO.DE_UNIDAD_PRODUCTO),' ') || 'Ã' ||
        NVL(RTRIM(PRODUCTO_LOCAL.DE_UNIDAD_FRACCION),' ') || 'Ã' ||
        TO_CHAR(NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
                          'S',
            TRUNC(CA_STOCK_DISPONIBLE/VA_FRACCION),
            PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE),0),
          '999,999,990')  || 'Ã' ||
           TO_CHAR(NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
               'S',
            MOD(CA_STOCK_DISPONIBLE, VA_FRACCION),
            0), 0),
             '999,990') || 'Ã' ||
         NVL(PRODUCTO_LOCAL.IN_PROD_INVENTARIO, ' ')
       FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL,
            LGTM_PRODUCTO PRODUCTO
       WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
        AND  PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
        AND  PRODUCTO.CO_GRUPO_TERAPEUTICO = codigofiltro_in
--        AND  PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A'
        AND (PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A' OR PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE > 0)
        AND  PRODUCTO.CO_COMPANIA = PRODUCTO_LOCAL.CO_COMPANIA
        AND  PRODUCTO.CO_PRODUCTO = PRODUCTO_LOCAL.CO_PRODUCTO
        AND  PRODUCTO.ES_PRODUCTO = 'A';
       --ORDER BY TRIM(PRODUCTO.DE_PRODUCTO);
 ELSIF ( tablafiltro_in = 'W' ) THEN  --LGTR_GRUPO_ANATOMICO
     OPEN eckcur FOR SELECT NVL(PRODUCTO_LOCAL.CO_PRODUCTO,' ') || 'Ã' ||
                  NVL(RTRIM(PRODUCTO.DE_PRODUCTO) ,' ') || 'Ã' ||
          NVL(RTRIM(PRODUCTO.DE_UNIDAD_PRODUCTO),' ') || 'Ã' ||
        NVL(RTRIM(PRODUCTO_LOCAL.DE_UNIDAD_FRACCION),' ') || 'Ã' ||
        TO_CHAR(NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
                          'S',
            TRUNC(CA_STOCK_DISPONIBLE/VA_FRACCION),
            PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE),0),
          '999,999,990')  || 'Ã' ||
           TO_CHAR(NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
               'S',
            MOD(CA_STOCK_DISPONIBLE, VA_FRACCION),
            0), 0),
             '999,990') || 'Ã' ||
         NVL(PRODUCTO_LOCAL.IN_PROD_INVENTARIO, ' ')
       FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL,
            LGTM_PRODUCTO PRODUCTO
       WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
        AND  PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
        AND  PRODUCTO.CO_GRUPO_ANATOMICO = codigofiltro_in
--        AND  PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A'
        AND (PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A' OR PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE > 0)
        AND  PRODUCTO.CO_COMPANIA = PRODUCTO_LOCAL.CO_COMPANIA
        AND  PRODUCTO.CO_PRODUCTO = PRODUCTO_LOCAL.CO_PRODUCTO
        AND  PRODUCTO.ES_PRODUCTO = 'A';
       --ORDER BY TRIM(PRODUCTO.DE_PRODUCTO);
 ELSIF ( tablafiltro_in = 'U') THEN  --LGTR_ACCION_FARMACEUTICA
     OPEN eckcur FOR SELECT NVL(PRODUCTO_LOCAL.CO_PRODUCTO,' ') || 'Ã' ||
                  NVL(RTRIM(PRODUCTO.DE_PRODUCTO) ,' ') || 'Ã' ||
          NVL(RTRIM(PRODUCTO.DE_UNIDAD_PRODUCTO),' ') || 'Ã' ||
        NVL(RTRIM(PRODUCTO_LOCAL.DE_UNIDAD_FRACCION),' ') || 'Ã' ||
        TO_CHAR(NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
                          'S',
            TRUNC(CA_STOCK_DISPONIBLE/VA_FRACCION),
            PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE),0),
          '999,999,990')  || 'Ã' ||
           TO_CHAR(NVL(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
               'S',
            MOD(CA_STOCK_DISPONIBLE, VA_FRACCION),
            0), 0),
             '999,990') || 'Ã' ||
         NVL(PRODUCTO_LOCAL.IN_PROD_INVENTARIO, ' ')
       FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL,
            LGTM_PRODUCTO PRODUCTO
       WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
        AND  PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
        AND  PRODUCTO.CO_ACCION_FARMACEUTICA = codigofiltro_in
--        AND  PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A'
        AND (PRODUCTO_LOCAL.ES_PROD_LOCAL = 'A' OR PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE > 0)
        AND  PRODUCTO.CO_COMPANIA = PRODUCTO_LOCAL.CO_COMPANIA
        AND  PRODUCTO.CO_PRODUCTO = PRODUCTO_LOCAL.CO_PRODUCTO
        AND  PRODUCTO.ES_PRODUCTO = 'A';
       --ORDER BY TRIM(PRODUCTO.DE_PRODUCTO);
 END IF;
    RETURN eckcur;
  END;

  FUNCTION VALIDA_NUMERO_RECEP_PROD(codigocompania_in  IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
           codigolocal_in     IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
           numerorecepcion_in IN LGTC_RECEPCION_PRODUCTO.NU_DOCUMENTO_RECEPCION%TYPE,
         codigolocalorigen_in     IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL_ORIGEN%TYPE,
         tipodocumento_in IN LGTC_RECEPCION_PRODUCTO.TI_DOCUMENTO_RECEPCION%TYPE)
        RETURN NUMBER IS
    indicador NUMBER;
 numerodocumento VARCHAR2(20);
 BEGIN
    SELECT DISTINCT NU_DOCUMENTO_RECEPCION
   INTO   numerodocumento
   FROM   LGTC_RECEPCION_PRODUCTO REC_PRO
   WHERE  REC_PRO.CO_COMPANIA = codigocompania_in
   AND    REC_PRO.CO_LOCAL = codigolocal_in
   AND    REC_PRO.TI_DOCUMENTO_RECEPCION = tipodocumento_in
   AND    REC_PRO.NU_DOCUMENTO_RECEPCION = numerorecepcion_in
   AND    REC_PRO.CO_LOCAL_ORIGEN = codigolocalorigen_in;

   indicador := 1;--existe el documento, por lo tanto no debe permitir grabar la guia

   RETURN indicador;
 EXCEPTION
  WHEN NO_DATA_FOUND THEN
   indicador := 2;--no existe el documento, por lo tanto debe permitir grabar la guia
  RETURN indicador;
 END;

-- modificado ryupanqui
-- fecha 20040618
-- estado activo
  FUNCTION STOCK_DISPONIBLE_PRODUCTO(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
             codigolocal_in  IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
           codigoproducto_in IN LGTR_PRODUCTO_LOCAL.CO_PRODUCTO%TYPE)
 RETURN CHAR IS
 vstockdispproducto  CHAR(20);
 BEGIN
  SELECT   TRIM(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
                   'S',TO_CHAR(TRUNC((PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE) / PRODUCTO_LOCAL.VA_FRACCION), '999,990'),
               TO_CHAR(PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE , '999,990'))
               )|| ' '  ||
     TRIM(DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
                  'S',
                 ' / ' || TO_CHAR(MOD(PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE, PRODUCTO_LOCAL.VA_FRACCION), '999,990'),
                 '  ')) STOCK
  INTO  vstockdispproducto
  FROM  LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL
  WHERE  PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
  AND   PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
  AND   PRODUCTO_LOCAL.CO_PRODUCTO = codigoproducto_in;
  RETURN   vStockDispProducto;
 EXCEPTION
  WHEN NO_DATA_FOUND THEN
    vstockdispproducto := '';
  RETURN   vstockdispproducto;
 END;

-- creado ryupanqui
-- fecha 20040629
  FUNCTION VERIFICACION_ANULACION_GUIA(codigocompania_in IN LGTD_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                 codigolocal_in    IN LGTD_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
            nurecepprod_in   IN LGTD_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE)
   RETURN CHAR IS
 vcodigoproducto    LGTD_RECEPCION_PRODUCTO.CO_PRODUCTO%TYPE;
 vcantidadguia  NUMBER(5);
 vstockreal   NUMBER;
 vflag    CHAR;
 CURSOR detalleguia IS
  SELECT   CO_PRODUCTO,
        DECODE(IN_PROD_FRACCIONADO,
            'S', NVL(VA_FRACCION, 0)*NVL(CA_ENVIADA_ENTERA, 0) + NVL(CA_ENVIADA_FRACCION, 0),
           NVL(CA_ENVIADA_ENTERA, 0)) CANTIDAD_GUIA
     FROM     LGTD_RECEPCION_PRODUCTO
  WHERE  CO_COMPANIA = codigocompania_in
  AND   CO_LOCAL = codigolocal_in
  AND   NU_RECEPCION_PRODUCTO = nurecepprod_in;
 BEGIN
   vflag := 'S';
   FOR detalleguia_rec IN detalleguia
   LOOP
     vcodigoproducto  := detalleguia_rec.CO_PRODUCTO;
    vcantidadguia  := detalleguia_rec.CANTIDAD_GUIA;
    SELECT   NVL(PROD_LOCAL.CA_STOCK_DISPONIBLE, 0) - NVL(PROD_LOCAL.CA_STOCK_COMPROMETIDO, 0)
       INTO   vstockreal
    FROM    LGTR_PRODUCTO_LOCAL PROD_LOCAL
    WHERE   PROD_LOCAL.CO_COMPANIA = codigocompania_in
    AND   PROD_LOCAL.CO_LOCAL = codigolocal_in
    AND   PROD_LOCAL.CO_PRODUCTO = vcodigoproducto;

    UPDATE   LGTR_PRODUCTO_LOCAL
    SET   CA_STOCK_COMPROMETIDO = NVL(CA_STOCK_COMPROMETIDO, 0) + vcantidadguia,
       FE_MOD_PROD_LOCAL = SYSDATE,
                                  IN_REPLICA = 0
    WHERE   CO_COMPANIA = codigocompania_in
    AND   CO_LOCAL = codigolocal_in
    AND   CO_PRODUCTO = vcodigoproducto;
    IF (vcantidadguia > vstockreal) THEN
     BEGIN
      vflag := 'N';
     EXIT;
    END;
    END IF;
   END LOOP;

   /*IF (vflag = 'S') THEN
     COMMIT;
   ELSE
     ROLLBACK;
   END IF;*/

   RETURN vflag;
 END;

  PROCEDURE ANULA_GUIA_INGRESO(codigocompania_in   IN LGTC_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
               codigolocal_in      IN LGTC_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
          nurecepprod_in     IN LGTC_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE,
          idanulaguia_in    IN LGTV_KARDEX.ID_CREA_KARDEX%TYPE,
          tipodocumento_in    IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
          codigomotivo_in    IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE,
          codigogrupomotivo_in  IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE) IS
 vcodigoproducto      LGTV_KARDEX.CO_PRODUCTO%TYPE;
 vcantidadkardex      LGTV_KARDEX.CA_MOVIMIENTO%TYPE;
 CURSOR detalleguia IS
  SELECT   RECEP_PROD.CO_PRODUCTO,
        DECODE(RECEP_PROD.IN_PROD_FRACCIONADO,
               'S', RECEP_PROD.VA_FRACCION*RECEP_PROD.CA_ENVIADA_ENTERA + RECEP_PROD.CA_ENVIADA_FRACCION,
              RECEP_PROD.CA_ENVIADA_ENTERA) CANTIDAD_GUIA
     FROM     LGTD_RECEPCION_PRODUCTO RECEP_PROD
  WHERE  RECEP_PROD.CO_COMPANIA = codigocompania_in
  AND   RECEP_PROD.CO_LOCAL = codigolocal_in
  AND   RECEP_PROD.NU_RECEPCION_PRODUCTO = nurecepprod_in;
 BEGIN
   FOR detalleguia_rec IN detalleguia
   LOOP
     vcodigoproducto := detalleguia_rec.CO_PRODUCTO;
    vcantidadkardex := detalleguia_rec.CANTIDAD_GUIA;
/*    dbms_output.put_line('vcodigoproducto: '||vcodigoproducto||' vcantidadkardex: '||vcantidadkardex);
    dbms_output.put_line('codigocompania_in: '||codigocompania_in||' codigolocal_in: '||codigolocal_in||' vcodigoproducto: '||vcodigoproducto);
    dbms_output.put_line('vcantidadkardex: '||vcantidadkardex*(-1)||' tipodocumento_in: '||tipodocumento_in||' nurecepprod_in: '||nurecepprod_in);
    dbms_output.put_line('codigogrupomotivo_in: '||codigogrupomotivo_in||' codigomotivo_in: '||codigomotivo_in||' idanulaguia_in: '||idanulaguia_in);*/
    INV_GRABA_KARDEX_STOCK_COM(codigocompania_in,
              codigolocal_in,
          vcodigoproducto,
            vcantidadkardex*(-1),
          tipodocumento_in,
          nurecepprod_in,
          'Guia Matriz',
          codigogrupomotivo_in,
          codigomotivo_in,
          idanulaguia_in);
    UPDATE   LGTD_RECEPCION_PRODUCTO
    SET   ES_DET_RECEPCION_PRODUCTO = 'N',
        ID_MOD_DET_RECEPCION_PRODUCTO = idanulaguia_in,
       FE_MOD_DET_RECEPCION_PRODUCTO = SYSDATE,
       IN_REPLICA = 0
    WHERE   CO_COMPANIA = codigocompania_in
    AND   CO_LOCAL = codigolocal_in
    AND   NU_RECEPCION_PRODUCTO = nurecepprod_in;
    --COMMIT;
   END LOOP;
   UPDATE   LGTC_RECEPCION_PRODUCTO
   SET   ES_RECEPCION_PRODUCTO = 'N',
       ID_MOD_RECEPCION_PRODUCTO = idanulaguia_in,
      FE_MOD_RECEPCION_PRODUCTO = SYSDATE,
      IN_REPLICA = 0
   WHERE   CO_COMPANIA = codigocompania_in
   AND   CO_LOCAL = codigolocal_in
   AND   NU_RECEPCION_PRODUCTO = nurecepprod_in;
   --COMMIT;
 END;

 FUNCTION VERIFICACION_NUMERACION_GUIA (codigocompania_in IN CMTR_NUMERACION.CO_COMPANIA%TYPE,
                codigolocal_in   IN CMTR_NUMERACION.CO_LOCAL%TYPE)
 RETURN CHAR IS
 vflagverif   CHAR;
 vnumguiatemp  LGTC_GUIA_TRANSFERENCIA.NU_GUIA_TRANSFERENCIA%TYPE;
 BEGIN
  vnumguiatemp := Eckerd_Utility.COMPLETAR_CON_SIMBOLO(Ptovta_Utility.OBTENER_NUMERACION(codigocompania_in, codigolocal_in, '008'),
                     10,
                  '0',
                  'I');
  COMMIT;
        vflagverif := 'S';
  SELECT   'N'
  INTO   vflagverif
  FROM   LGTC_GUIA_TRANSFERENCIA
  WHERE  CO_COMPANIA = codigocompania_in
  AND   CO_LOCAL = codigolocal_in
  AND   NU_GUIA_TRANSFERENCIA = vnumguiatemp;
  RETURN   vflagverif;
  EXCEPTION
  WHEN   NO_DATA_FOUND THEN
  RETURN  vflagverif;
 END;

 FUNCTION OBTIENE_DATOS_PEDIDOS_REALIZ (codigocompania_in IN LGTD_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
              codigolocal_in    IN LGTD_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
             numpedido_in      IN LGTD_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE)
  RETURN EckerdCursor IS
     eckcur EckerdCursor;
  BEGIN
  OPEN eckcur FOR
      SELECT   TO_CHAR(COUNT(*), '999,999,990') || 'Ã' ||
         TO_CHAR(NVL(SUM(CA_PEDIDA), 0), '999,999,990')
      FROM   LGTD_PEDIDO_PRODUCTO
      WHERE  CO_COMPANIA = codigocompania_in
      AND   CO_LOCAL = codigolocal_in
      AND   NU_PEDIDO_PRODUCTO = numpedido_in
      AND   TI_PEDIDO_PRODUCTO = 'RP';
  RETURN eckcur;
  END;

   FUNCTION VERIFICA_ANUL_TRANSF_TOMAINV(codigocompania_in IN LGTD_GUIA_TRANSFERENCIA.CO_COMPANIA%TYPE,
               codigolocal_in    IN LGTD_GUIA_TRANSFERENCIA.CO_LOCAL%TYPE,
              numsectrans_in    IN LGTD_GUIA_TRANSFERENCIA.NU_SEC_TRANSFERENCIA%TYPE)
  RETURN CHAR IS
  vflagverif CHAR;
     BEGIN
  vflagverif := 'N';
  SELECT   'S'
  INTO  vflagverif
  FROM   DUAL
  WHERE  EXISTS(
       SELECT   *
      FROM   LGTD_GUIA_TRANSFERENCIA GUIA_TRANS,
         LGTR_PRODUCTO_LOCAL PROD_LOCAL
      WHERE    GUIA_TRANS.CO_COMPANIA = codigocompania_in
      AND   GUIA_TRANS.CO_LOCAL = codigolocal_in
      AND   GUIA_TRANS.NU_SEC_TRANSFERENCIA = numsectrans_in
      AND   PROD_LOCAL.IN_PROD_INVENTARIO = 'S'
      AND   GUIA_TRANS.CO_COMPANIA = PROD_LOCAL.CO_COMPANIA
      AND   GUIA_TRANS.CO_LOCAL = PROD_LOCAL.CO_LOCAL
      AND   GUIA_TRANS.CO_PRODUCTO = PROD_LOCAL.CO_PRODUCTO
      );
  RETURN  vflagverif;
  EXCEPTION
  WHEN  NO_DATA_FOUND THEN
  RETURN  vflagverif;
  END;

   FUNCTION VERIFICA_ANUL_GUIAING_TOMAINV(codigocompania_in IN LGTD_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                codigolocal_in    IN LGTD_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
               numrecepprod_in   IN LGTD_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE)
  RETURN CHAR IS
  vflagverif CHAR;
     BEGIN
  vflagverif := 'N';
  SELECT   'S'
  INTO  vflagverif
  FROM   DUAL
  WHERE  EXISTS(
       SELECT   *
      FROM   LGTD_RECEPCION_PRODUCTO GUIA_ING,
         LGTR_PRODUCTO_LOCAL PROD_LOCAL
      WHERE    GUIA_ING.CO_COMPANIA = codigocompania_in
      AND   GUIA_ING.CO_LOCAL = codigolocal_in
      AND   GUIA_ING.NU_RECEPCION_PRODUCTO = numrecepprod_in
      AND   PROD_LOCAL.IN_PROD_INVENTARIO = 'S'
      AND   GUIA_ING.CO_COMPANIA = PROD_LOCAL.CO_COMPANIA
      AND   GUIA_ING.CO_LOCAL = PROD_LOCAL.CO_LOCAL
      AND   GUIA_ING.CO_PRODUCTO = PROD_LOCAL.CO_PRODUCTO
      );
  RETURN  vflagverif;
  EXCEPTION
  WHEN  NO_DATA_FOUND THEN
  RETURN  vflagverif;
  END;


/*------------------------------LUIS MESIA----------------------------------------------------------*/

 FUNCTION GET_MOV_GUIA_X_PRODUCTO (codigocompania_in  IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
             codigolocal_in     IN LGTR_PRODUCTO_LOCAL.CO_LOCAL%TYPE,
                fechainicio_in     IN CHAR,
                       fechafin_in        IN CHAR)
       RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
  OPEN eckcur FOR SELECT V1.PRODUCTO ||'Ã'||
          V1.DESCRIPCION ||'Ã'||
          V1.UNIDAD ||'Ã'||
          TO_CHAR(SUM(CANTIDAD),'999,999') ||'Ã'||
          TO_CHAR(V1.STOCK,'999,999')
      FROM   (SELECT PRO_LOC.CO_PRODUCTO PRODUCTO,
            PRO.DE_PRODUCTO DESCRIPCION,
            PRO.DE_UNIDAD_PRODUCTO UNIDAD,
            COUNT(REC_PRO.NU_RECEPCION_PRODUCTO) CANTIDAD,
            PRO_LOC.CA_STOCK_DISPONIBLE STOCK
        FROM   LGTM_PRODUCTO PRO,
            LGTR_PRODUCTO_LOCAL PRO_LOC,
            LGTD_RECEPCION_PRODUCTO GUI_REC,
            LGTC_RECEPCION_PRODUCTO REC_PRO
        WHERE  PRO_LOC.CO_COMPANIA = PRO.CO_COMPANIA
        AND    PRO_LOC.CO_PRODUCTO = PRO.CO_PRODUCTO
        AND    PRO_LOC.CO_COMPANIA = GUI_REC.CO_COMPANIA
        AND    PRO_LOC.CO_LOCAL = GUI_REC.CO_LOCAL
        AND    PRO_LOC.CO_PRODUCTO = GUI_REC.CO_PRODUCTO
        AND    REC_PRO.CO_COMPANIA = GUI_REC.CO_COMPANIA
        AND    REC_PRO.CO_LOCAL = GUI_REC.CO_LOCAL
        AND    REC_PRO.NU_RECEPCION_PRODUCTO = GUI_REC.NU_RECEPCION_PRODUCTO
        AND    PRO_LOC.CO_COMPANIA = codigocompania_in
        AND    PRO_LOC.CO_LOCAL = codigolocal_in
        AND    REC_PRO.FE_RECEPCION_PRODUCTO BETWEEN TO_DATE(fechainicio_in,'dd/MM/yyyy')
                     AND  TO_DATE(fechafin_in,'dd/MM/yyyy')
        GROUP BY PRO_LOC.CO_PRODUCTO, PRO.DE_PRODUCTO, PRO.DE_UNIDAD_PRODUCTO, PRO_LOC.CA_STOCK_DISPONIBLE
           UNION
           SELECT PRO_LOC.CO_PRODUCTO PRODUCTO,
            PRO.DE_PRODUCTO DESCRIPCION,
            PRO.DE_UNIDAD_PRODUCTO UNIDAD,
            COUNT(TRA_PRO.NU_GUIA_TRANSFERENCIA) CANTIDAD,
            PRO_LOC.CA_STOCK_DISPONIBLE STOCK
        FROM   LGTM_PRODUCTO PRO,
            LGTR_PRODUCTO_LOCAL PRO_LOC,
            LGTD_GUIA_TRANSFERENCIA GUI_TRA,
            LGTC_GUIA_TRANSFERENCIA TRA_PRO
        WHERE  PRO_LOC.CO_COMPANIA = PRO.CO_COMPANIA
        AND    PRO_LOC.CO_PRODUCTO = PRO.CO_PRODUCTO
        AND    PRO_LOC.CO_COMPANIA = GUI_TRA.CO_COMPANIA
        AND    PRO_LOC.CO_LOCAL = GUI_TRA.CO_LOCAL
        AND    PRO_LOC.CO_PRODUCTO = GUI_TRA.CO_PRODUCTO
        AND    TRA_PRO.CO_COMPANIA = GUI_TRA.CO_COMPANIA
        AND    TRA_PRO.CO_LOCAL = GUI_TRA.CO_LOCAL
        AND    TRA_PRO.NU_GUIA_TRANSFERENCIA = GUI_TRA.NU_GUIA_TRANSFERENCIA
        AND    PRO_LOC.CO_COMPANIA = codigocompania_in
        AND    PRO_LOC.CO_LOCAL = codigolocal_in
        AND    TRA_PRO.FE_EMISION_GUIA_TRANSFERENCIA BETWEEN TO_DATE(fechainicio_in || ' 00:00:00','dd/MM/yyyy HH24:MI:SS')
                        AND  TO_DATE(fechafin_in    || ' 23:59:59','dd/MM/yyyy HH24:MI:SS')
        GROUP BY PRO_LOC.CO_PRODUCTO, PRO.DE_PRODUCTO, PRO.DE_UNIDAD_PRODUCTO, PRO_LOC.CA_STOCK_DISPONIBLE) V1
      GROUP BY V1.PRODUCTO, V1.DESCRIPCION, V1.UNIDAD, V1.STOCK;
 RETURN eckcur;
  END;


  FUNCTION GET_MOV_GUIA_X_PRODUCTO_DET (codigocompania_in  IN VTTM_LOCAL.CO_COMPANIA%TYPE,
                  codigolocal_in     IN VTTM_LOCAL.CO_LOCAL%TYPE,
             codigoproducto_in  IN LGTM_PRODUCTO.CO_PRODUCTO%TYPE,
                     fechainicio_in     IN CHAR,
                            fechafin_in        IN CHAR)
       RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
  OPEN eckcur FOR
      SELECT
          --DECODE(REC_PRO.CO_LOCAL_ORIGEN, NULL, 'NO ESPECIFICADO', REC_PRO.CO_LOCAL_ORIGEN || ' - ' || TRIM(LOC.DE_LOCAL)) ||'Ã'||
          DECODE(REC_PRO.TI_RECEPCION, 'A',
                DECODE(REC_PRO.CO_LOCAL_ORIGEN, NULL, 'NO ESPECIFICADO', REC_PRO.CO_LOCAL_ORIGEN || ' - ' || TRIM(LOC.DE_LOCAL)),
                CASE WHEN REC_PRO.CO_LOCAL_ORIGEN IS NULL OR REC_PRO.CO_LOCAL_ORIGEN = '099'
                    THEN 'NO ESPECIFICADO'
                    ELSE REC_PRO.CO_LOCAL_ORIGEN || ' - ' || TRIM(LOC.DE_LOCAL) END) ||'Ã'||
          'E' ||'Ã'||
          NVL(SUBSTR(REC_PRO.NU_DOCUMENTO_RECEPCION, 0, 3) || '-' || SUBSTR(REC_PRO.NU_DOCUMENTO_RECEPCION, 4, 7),' ') ||'Ã'||
          NVL(TO_CHAR(REC_PRO.FE_RECEPCION_PRODUCTO,'dd/MM/yyyy'),' ') ||'Ã'||
          TO_CHAR(NVL(GUI_REC.CA_CONFIRMADA_ENTERA,0),'999,999') ||'Ã'||
          TO_CHAR(NVL(GUI_REC.CA_CONFIRMADA_FRACCION,0),'999,999')
      FROM   LGTM_PRODUCTO PRO,
          LGTD_RECEPCION_PRODUCTO GUI_REC,
          LGTC_RECEPCION_PRODUCTO REC_PRO,
          VTTM_LOCAL LOC
      WHERE  GUI_REC.CO_COMPANIA = PRO.CO_COMPANIA
      AND    GUI_REC.CO_PRODUCTO = PRO.CO_PRODUCTO
      AND    REC_PRO.CO_COMPANIA = GUI_REC.CO_COMPANIA
      AND    REC_PRO.CO_LOCAL = GUI_REC.CO_LOCAL
      AND    REC_PRO.NU_RECEPCION_PRODUCTO = GUI_REC.NU_RECEPCION_PRODUCTO
      AND    REC_PRO.CO_COMPANIA = LOC.CO_COMPANIA(+)
      AND    REC_PRO.CO_LOCAL_ORIGEN = LOC.CO_LOCAL(+)
      AND    REC_PRO.CO_COMPANIA = codigocompania_in
      AND    REC_PRO.CO_LOCAL = codigolocal_in
      AND    REC_PRO.FE_RECEPCION_PRODUCTO BETWEEN TO_DATE(fechainicio_in,'dd/MM/yyyy')
                   AND  TO_DATE(fechafin_in,'dd/MM/yyyy')
      AND    PRO.CO_PRODUCTO = codigoproducto_in
      UNION
      SELECT DECODE(M_TRA_PRO.CO_LOCAL_DESTINO,NULL,'NO ESPECIFICADO',M_TRA_PRO.CO_LOCAL_DESTINO || ' - ' || TRIM(LOC.DE_LOCAL)) ||'Ã'||
          'S' ||'Ã'||
          NVL(SUBSTR(TRA_PRO.NU_GUIA_TRANSFERENCIA, 0, 3) || '-' || SUBSTR(TRA_PRO.NU_GUIA_TRANSFERENCIA, 4, 7),' ') ||'Ã'||
          NVL(TO_CHAR(TRA_PRO.FE_EMISION_GUIA_TRANSFERENCIA,'dd/MM/yyyy'),' ') ||'Ã'||
          TO_CHAR(NVL(GUI_TRA.CA_TRANSFERIR_ENTERA,0),'999,999') ||'Ã'||
          TO_CHAR(NVL(GUI_TRA.CA_TRANSFERIR_FRACCION,0),'999,999')
      FROM   LGTM_PRODUCTO PRO,
          LGTD_GUIA_TRANSFERENCIA GUI_TRA,
          LGTC_GUIA_TRANSFERENCIA TRA_PRO,
          LGTM_TRANSF_PRODUCTO M_TRA_PRO,
          VTTM_LOCAL LOC
      WHERE  GUI_TRA.CO_COMPANIA = PRO.CO_COMPANIA
      AND    GUI_TRA.CO_PRODUCTO = PRO.CO_PRODUCTO
      AND    TRA_PRO.CO_COMPANIA = GUI_TRA.CO_COMPANIA
      AND    TRA_PRO.CO_LOCAL = GUI_TRA.CO_LOCAL
      AND    TRA_PRO.NU_GUIA_TRANSFERENCIA = GUI_TRA.NU_GUIA_TRANSFERENCIA
      AND    GUI_TRA.CO_COMPANIA = M_TRA_PRO.CO_COMPANIA
      AND    GUI_TRA.CO_LOCAL = M_TRA_PRO.CO_LOCAL
      AND    GUI_TRA.NU_SEC_TRANSFERENCIA = M_TRA_PRO.NU_SEC_TRANSFERENCIA
      AND    M_TRA_PRO.CO_COMPANIA = LOC.CO_COMPANIA(+)
      AND    M_TRA_PRO.CO_LOCAL_DESTINO = LOC.CO_LOCAL(+)
      AND    TRA_PRO.CO_COMPANIA = codigocompania_in
      AND    TRA_PRO.CO_LOCAL = codigolocal_in
      AND    TRA_PRO.FE_EMISION_GUIA_TRANSFERENCIA BETWEEN TO_DATE(fechainicio_in || ' 00:00:00','dd/MM/yyyy HH24:MI:SS')
                      AND  TO_DATE(fechafin_in    || ' 23:59:59','dd/MM/yyyy HH24:MI:SS')
      AND    PRO.CO_PRODUCTO = codigoproducto_in;
 RETURN eckcur;
  END;

  /****************************************************/

  FUNCTION FILTRO_TRANSFERENCIA_PRODUCTOS(codigocompania_in IN LGTR_PRODUCTO_LOCAL.CO_COMPANIA%TYPE,
            tablafiltro_in    VARCHAR2)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
 IF ( tablafiltro_in = 'L' ) THEN  --LOCAL
     OPEN eckcur FOR SELECT LOC.CO_LOCAL || 'Ã' ||
           LOC.DE_LOCAL
       FROM   VTTM_LOCAL LOC
       WHERE  LOC.CO_COMPANIA = codigocompania_in;
 ELSIF ( tablafiltro_in = 'M') THEN  --MOTIVO DE TRANSFERENCIA
     OPEN eckcur FOR SELECT MOV.CO_MOTIVO || 'Ã' ||
           MOV.DE_MOTIVO
       FROM   CMTR_MOTIVO MOV
       WHERE  MOV.CO_GRUPO_MOTIVO = '03'
       AND    MOV.ES_MOTIVO = 'A';
 END IF;
    RETURN eckcur;
  END;

  /* ************************************************************************ */
  FUNCTION INV_LISTA_TRANSF_PROD_FILTRO(codigocompania_in IN LGTM_TRANSF_PRODUCTO.CO_COMPANIA%TYPE,
                codigolocal_in    IN LGTM_TRANSF_PRODUCTO.CO_LOCAL%TYPE,
           fechainicio_in    IN CHAR,
           fechafin_in    IN CHAR,
          codigofiltro_in   CHAR,
          tablafiltro_in    VARCHAR2)
           RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
   IF LENGTH(fechainicio_in) > 0 AND LENGTH(fechafin_in) > 0 THEN
   IF ( tablafiltro_in = 'L' ) THEN  --LOCAL
     OPEN eckcur FOR SELECT   NVL(TRANSF.NU_SEC_TRANSFERENCIA, ' ') || 'Ã' ||
               NVL(LOCAL_DEST.DE_CORTA_LOCAL, ' ') || 'Ã' ||
                           NVL(TO_CHAR(TRANSF.FE_EMISION_TRANSFERENCIA, 'dd/MM/yyyy HH24:MI:SS'), ' ') || 'Ã' ||
            NVL(SUBSTR(GUIA.NU_GUIA_TRANSFERENCIA, 1, 3), ' ') || '-' || NVL(SUBSTR(GUIA.NU_GUIA_TRANSFERENCIA, 4, 7), ' ') || 'Ã' ||
               NVL(MOTIVO.DE_MOTIVO, ' ') || 'Ã' ||
                              NVL(TRANSF.CA_PROD_TRANSFERIDO, 0)  || 'Ã' ||
                              DECODE(TRANSF.ES_TRANSF_PRODUCTO,
                      ESTADO_TRANSF_PROC, DESCRIP_TRANSF_PROC,
                   ESTADO_TRANSF_IMPR, DESCRIP_TRANSF_IMPR,
                   ESTADO_TRANSF_REIM, DESCRIP_TRANSF_REIM,
                   ESTADO_TRANSF_ANUL, DESCRIP_TRANSF_ANUL, ' ')
         FROM     LGTM_TRANSF_PRODUCTO TRANSF,
            (
         SELECT   DISTINCT NU_GUIA_TRANSFERENCIA,
            NU_SEC_TRANSFERENCIA
         FROM  LGTD_GUIA_TRANSFERENCIA
         WHERE  CO_COMPANIA = codigocompania_in
         AND   CO_LOCAL = codigolocal_in
         ) GUIA,
              VTTM_LOCAL LOCAL_DEST,
            CMTR_MOTIVO MOTIVO
         WHERE    TRANSF.CO_COMPANIA = codigocompania_in AND
             TRANSF.CO_LOCAL = codigolocal_in AND
         TRANSF.FE_EMISION_TRANSFERENCIA BETWEEN TO_DATE(fechainicio_in || ' 00:00:00', 'dd/MM/yyyy HH24:MI:SS')
                 AND  TO_DATE(fechafin_in || ' 23:59:59', 'dd/MM/yyyy HH24:MI:SS') AND
            TRANSF.NU_SEC_TRANSFERENCIA = GUIA.NU_SEC_TRANSFERENCIA(+) AND
            TRANSF.CO_COMPANIA = LOCAL_DEST.CO_COMPANIA AND
            TRANSF.CO_LOCAL_DESTINO = LOCAL_DEST.CO_LOCAL AND
         TRANSF.CO_GRUPO_MOTIVO = MOTIVO.CO_GRUPO_MOTIVO(+) AND
            TRANSF.CO_MOTIVO_TRANSFERENCIA = MOTIVO.CO_MOTIVO(+) AND
            TRANSF.CO_LOCAL_DESTINO = codigofiltro_in;
   ELSIF ( tablafiltro_in = 'M') THEN  --MOTIVO DE TRANSFERENCIA
     OPEN eckcur FOR SELECT   NVL(TRANSF.NU_SEC_TRANSFERENCIA, ' ') || 'Ã' ||
               NVL(LOCAL_DEST.DE_CORTA_LOCAL, ' ') || 'Ã' ||
                           NVL(TO_CHAR(TRANSF.FE_EMISION_TRANSFERENCIA, 'dd/MM/yyyy HH24:MI:SS'), ' ') || 'Ã' ||
            NVL(SUBSTR(GUIA.NU_GUIA_TRANSFERENCIA, 1, 3), ' ') || '-' || NVL(SUBSTR(GUIA.NU_GUIA_TRANSFERENCIA, 4, 7), ' ') || 'Ã' ||
               NVL(MOTIVO.DE_MOTIVO, ' ') || 'Ã' ||
                              NVL(TRANSF.CA_PROD_TRANSFERIDO, 0)  || 'Ã' ||
                              DECODE(TRANSF.ES_TRANSF_PRODUCTO,
                      ESTADO_TRANSF_PROC, DESCRIP_TRANSF_PROC,
                   ESTADO_TRANSF_IMPR, DESCRIP_TRANSF_IMPR,
                   ESTADO_TRANSF_REIM, DESCRIP_TRANSF_REIM,
                   ESTADO_TRANSF_ANUL, DESCRIP_TRANSF_ANUL, ' ')
         FROM     LGTM_TRANSF_PRODUCTO TRANSF,
            (
         SELECT   DISTINCT NU_GUIA_TRANSFERENCIA,
            NU_SEC_TRANSFERENCIA
         FROM  LGTD_GUIA_TRANSFERENCIA
         WHERE  CO_COMPANIA = codigocompania_in
         AND   CO_LOCAL = codigolocal_in
         ) GUIA,
              VTTM_LOCAL LOCAL_DEST,
            CMTR_MOTIVO MOTIVO
         WHERE    TRANSF.CO_COMPANIA = codigocompania_in AND
             TRANSF.CO_LOCAL = codigolocal_in AND
         TRANSF.FE_EMISION_TRANSFERENCIA BETWEEN TO_DATE(fechainicio_in || ' 00:00:00', 'dd/MM/yyyy HH24:MI:SS')
                 AND  TO_DATE(fechafin_in || ' 23:59:59', 'dd/MM/yyyy HH24:MI:SS') AND
            TRANSF.NU_SEC_TRANSFERENCIA = GUIA.NU_SEC_TRANSFERENCIA(+) AND
            TRANSF.CO_COMPANIA = LOCAL_DEST.CO_COMPANIA AND
            TRANSF.CO_LOCAL_DESTINO = LOCAL_DEST.CO_LOCAL AND
            TRANSF.CO_GRUPO_MOTIVO = MOTIVO.CO_GRUPO_MOTIVO(+) AND
            TRANSF.CO_MOTIVO_TRANSFERENCIA = MOTIVO.CO_MOTIVO(+) AND
         TRANSF.CO_GRUPO_MOTIVO = '03' AND
         TRANSF.CO_MOTIVO_TRANSFERENCIA = codigofiltro_in;
   END IF;
   ELSE
     IF ( tablafiltro_in = 'L' ) THEN  --LOCAL
     OPEN eckcur FOR SELECT   NVL(TRANSF.NU_SEC_TRANSFERENCIA, ' ') || 'Ã' ||
               NVL(LOCAL_DEST.DE_CORTA_LOCAL, ' ') || 'Ã' ||
                           NVL(TO_CHAR(TRANSF.FE_EMISION_TRANSFERENCIA, 'dd/MM/yyyy HH24:MI:SS'), ' ') || 'Ã' ||
            NVL(SUBSTR(GUIA.NU_GUIA_TRANSFERENCIA, 1, 3), ' ') || '-' || NVL(SUBSTR(GUIA.NU_GUIA_TRANSFERENCIA, 4, 7), ' ') || 'Ã' ||
               NVL(MOTIVO.DE_MOTIVO, ' ') || 'Ã' ||
                              NVL(TRANSF.CA_PROD_TRANSFERIDO, 0)  || 'Ã' ||
                              DECODE(TRANSF.ES_TRANSF_PRODUCTO,
                      ESTADO_TRANSF_PROC, DESCRIP_TRANSF_PROC,
                   ESTADO_TRANSF_IMPR, DESCRIP_TRANSF_IMPR,
                   ESTADO_TRANSF_REIM, DESCRIP_TRANSF_REIM,
                   ESTADO_TRANSF_ANUL, DESCRIP_TRANSF_ANUL, ' ')
         FROM     LGTM_TRANSF_PRODUCTO TRANSF,
            (
         SELECT   DISTINCT NU_GUIA_TRANSFERENCIA,
            NU_SEC_TRANSFERENCIA
         FROM  LGTD_GUIA_TRANSFERENCIA
         WHERE  CO_COMPANIA = codigocompania_in
         AND   CO_LOCAL = codigolocal_in
         ) GUIA,
              VTTM_LOCAL LOCAL_DEST,
            CMTR_MOTIVO MOTIVO
         WHERE    TRANSF.CO_COMPANIA = codigocompania_in AND
             TRANSF.CO_LOCAL = codigolocal_in AND
            TRANSF.NU_SEC_TRANSFERENCIA = GUIA.NU_SEC_TRANSFERENCIA(+) AND
            TRANSF.CO_COMPANIA = LOCAL_DEST.CO_COMPANIA AND
            TRANSF.CO_LOCAL_DESTINO = LOCAL_DEST.CO_LOCAL AND
            TRANSF.CO_GRUPO_MOTIVO = MOTIVO.CO_GRUPO_MOTIVO(+) AND
            TRANSF.CO_MOTIVO_TRANSFERENCIA = MOTIVO.CO_MOTIVO(+) AND
            TRANSF.CO_LOCAL_DESTINO = codigofiltro_in;
      ELSIF ( tablafiltro_in = 'M') THEN  --MOTIVO DE TRANSFERENCIA
     OPEN eckcur FOR SELECT   NVL(TRANSF.NU_SEC_TRANSFERENCIA, ' ') || 'Ã' ||
               NVL(LOCAL_DEST.DE_CORTA_LOCAL, ' ') || 'Ã' ||
                           NVL(TO_CHAR(TRANSF.FE_EMISION_TRANSFERENCIA, 'dd/MM/yyyy HH24:MI:SS'), ' ') || 'Ã' ||
            NVL(SUBSTR(GUIA.NU_GUIA_TRANSFERENCIA, 1, 3), ' ') || '-' || NVL(SUBSTR(GUIA.NU_GUIA_TRANSFERENCIA, 4, 7), ' ') || 'Ã' ||
               NVL(MOTIVO.DE_MOTIVO, ' ') || 'Ã' ||
                              NVL(TRANSF.CA_PROD_TRANSFERIDO, 0)  || 'Ã' ||
                              DECODE(TRANSF.ES_TRANSF_PRODUCTO,
                      ESTADO_TRANSF_PROC, DESCRIP_TRANSF_PROC,
                   ESTADO_TRANSF_IMPR, DESCRIP_TRANSF_IMPR,
                   ESTADO_TRANSF_REIM, DESCRIP_TRANSF_REIM,
                   ESTADO_TRANSF_ANUL, DESCRIP_TRANSF_ANUL, ' ')
         FROM     LGTM_TRANSF_PRODUCTO TRANSF,
            (
         SELECT   DISTINCT NU_GUIA_TRANSFERENCIA,
            NU_SEC_TRANSFERENCIA
         FROM  LGTD_GUIA_TRANSFERENCIA
         WHERE  CO_COMPANIA = codigocompania_in
         AND   CO_LOCAL = codigolocal_in
         ) GUIA,
              VTTM_LOCAL LOCAL_DEST,
            CMTR_MOTIVO MOTIVO
         WHERE    TRANSF.CO_COMPANIA = codigocompania_in AND
             TRANSF.CO_LOCAL = codigolocal_in AND
            TRANSF.NU_SEC_TRANSFERENCIA = GUIA.NU_SEC_TRANSFERENCIA(+) AND
            TRANSF.CO_COMPANIA = LOCAL_DEST.CO_COMPANIA AND
            TRANSF.CO_LOCAL_DESTINO = LOCAL_DEST.CO_LOCAL AND
            TRANSF.CO_GRUPO_MOTIVO = MOTIVO.CO_GRUPO_MOTIVO(+) AND
            TRANSF.CO_MOTIVO_TRANSFERENCIA = MOTIVO.CO_MOTIVO(+) AND
         TRANSF.CO_GRUPO_MOTIVO = '03' AND
         TRANSF.CO_MOTIVO_TRANSFERENCIA = codigofiltro_in;
      END IF;
   END IF;
   RETURN eckcur;
  END;

/* ************************************************************************ */

  PROCEDURE ANULA_PEDIDO_REPOSICION(codigocompania_in  IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
               codigolocal_in     IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
               numeropedido_in    IN LGTC_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE,
         idmodpedido_in    IN LGTC_PEDIDO_PRODUCTO.ID_MOD_PEDIDO_PRODUCTO%TYPE)
             IS
  BEGIN
    UPDATE LGTC_PEDIDO_PRODUCTO
    SET ES_PEDIDO_PRODUCTO = 'N',
       ID_MOD_PEDIDO_PRODUCTO = idmodpedido_in,
     FE_MOD_PEDIDO_PRODUCTO = SYSDATE
  WHERE CO_COMPANIA = codigocompania_in
    AND CO_LOCAL = codigolocal_in
    AND TI_PEDIDO_PRODUCTO = 'RP'
    AND NU_PEDIDO_PRODUCTO = numeropedido_in;

    UPDATE LGTD_PEDIDO_PRODUCTO
    SET ES_DET_PEDIDO_PRODUCTO = 'N',
       ID_MOD_DET_PEDIDO_PRODUCTO = idmodpedido_in,
     FE_MOD_DET_PEDIDO_PRODUCTO = SYSDATE
  WHERE CO_COMPANIA = codigocompania_in
    AND CO_LOCAL = codigolocal_in
    AND TI_PEDIDO_PRODUCTO = 'RP'
    AND NU_PEDIDO_PRODUCTO = numeropedido_in;
  END;

/* ************************************************************************ */

  FUNCTION INV_CALCULA_ROTACION_PROD (codigocompania_in  IN VTTR_VENTAS_PRODUCTO_DIA.CO_COMPANIA%TYPE,
                                      codigolocal_in     IN VTTR_VENTAS_PRODUCTO_DIA.CO_LOCAL%TYPE,
                                      codigoproducto_in  IN VTTR_VENTAS_PRODUCTO_DIA.CO_PRODUCTO%TYPE,
                                      semanasrotacion_in IN NUMBER,
                                      maximodias_in    IN NUMBER)
  RETURN NUMBER IS
    rotacionprod       NUMBER(10,3);
 CURSOR ROTACIONES (codigocompania_in  IN VTTR_VENTAS_PRODUCTO_DIA.CO_COMPANIA%TYPE,
                  codigolocal_in     IN VTTR_VENTAS_PRODUCTO_DIA.CO_LOCAL%TYPE,
             codigoproducto_in  IN VTTR_VENTAS_PRODUCTO_DIA.CO_PRODUCTO%TYPE,
          semanasrotacion_in IN NUMBER,
          maximodias_in     IN NUMBER) IS
/*
    SELECT TRUNC((SYSDATE - fe_dia_venta)/(semanasrotacion_in*7)) MES,
       NVL( SUM(CA_VENTA_DIA) / (semanasrotacion_in*7), 0) ROTACION
      FROM VTTR_VENTAS_PRODUCTO_DIA
   WHERE CO_COMPANIA = codigocompania_in AND
         CO_LOCAL    = codigolocal_in   AND
         CO_PRODUCTO = codigoproducto_in AND
      NU_REVISION_PRODUCTO = '0' AND
      FE_DIA_VENTA BETWEEN (SYSDATE - (3*semanasrotacion_in*7))
              AND SYSDATE
    GROUP BY TRUNC((SYSDATE - fe_dia_venta)/(semanasrotacion_in*7))
    HAVING NVL( SUM(CA_VENTA_DIA) / (semanasrotacion_in*7), 0) > 0
    ORDER BY 1 ASC;
*/
    SELECT TRUNC((SYSDATE - fe_dia_venta)/(30)) MES,
       NVL( SUM(CA_VENTA_DIA) * maximodias_in / (30), 0) ROTACION
    FROM VTTR_VENTAS_PRODUCTO_DIA
    WHERE CO_COMPANIA = codigocompania_in AND
         CO_LOCAL    = codigolocal_in   AND
         CO_PRODUCTO = codigoproducto_in AND
      NU_REVISION_PRODUCTO = '0' AND
      FE_DIA_VENTA BETWEEN (SYSDATE - (3*30))
              AND (SYSDATE - 30)
    GROUP BY TRUNC((SYSDATE - fe_dia_venta)/(30))
    HAVING NVL( SUM(CA_VENTA_DIA) / 30, 0) > 0
    UNION
    SELECT 0 MES,
       NVL( SUM(CA_VENTA_DIA)* maximodias_in / (semanasrotacion_in), 0) ROTACION
    FROM VTTR_VENTAS_PRODUCTO_DIA
    WHERE CO_COMPANIA = codigocompania_in AND
         CO_LOCAL    = codigolocal_in   AND
         CO_PRODUCTO = codigoproducto_in AND
      NU_REVISION_PRODUCTO = '0' AND
      FE_DIA_VENTA BETWEEN (SYSDATE - semanasrotacion_in) AND SYSDATE
    HAVING NVL( SUM(CA_VENTA_DIA) / (semanasrotacion_in), 0) > 0
    ORDER BY 1 ASC;

  BEGIN
      rotacionprod := 0;
   FOR ROTACIONES_REC IN ROTACIONES(codigocompania_in, codigolocal_in, codigoproducto_in, semanasrotacion_in, maximodias_in)
   LOOP
     rotacionprod := ROTACIONES_REC.ROTACION;
--     RETURN rotacionprod;
    EXIT;
   END LOOP;

   RETURN rotacionprod;
 EXCEPTION
   WHEN NO_DATA_FOUND THEN
  RETURN 0;
   WHEN OTHERS THEN
  RETURN 0;

  END;

/* ************************************************************************ */

  PROCEDURE INV_GRABA_ERROR_CANT_PEDIDA(codigocompania_in IN LGTD_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                                        codigolocal_in    IN LGTD_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
                tipopedidoprod_in IN LGTC_PEDIDO_PRODUCTO.TI_PEDIDO_PRODUCTO%TYPE,
                numeropedido_in   IN LGTD_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE,
          numeroitem_in   IN LGTD_PEDIDO_PRODUCTO.NU_ITEM%TYPE,
          codigoproducto_in IN LGTD_PEDIDO_PRODUCTO.CO_PRODUCTO%TYPE,
          cantidadpedida_in IN LGTD_PEDIDO_PRODUCTO.CA_PEDIDA%TYPE,
          idcreapedido_in   IN LGTD_PEDIDO_PRODUCTO.ID_CREA_DET_PEDIDO_PRODUCTO%TYPE
          ) IS
  BEGIN
      INSERT INTO LGTD_ERROR_CANT_PEDIDA (CO_COMPANIA, CO_LOCAL, TI_PEDIDO_PRODUCTO, NU_PEDIDO_PRODUCTO, NU_ITEM, CO_PRODUCTO, NU_REVISION_PRODUCTO, CA_PEDIDA, ID_CREA_DET_PEDIDO_PRODUCTO)
    VALUES (codigocompania_in, codigolocal_in, tipopedidoprod_in, numeropedido_in, numeroitem_in, codigoproducto_in, '0', cantidadpedida_in, idcreapedido_in);
  END;

/* ************************************************************************ */

  FUNCTION INV_CALCULA_STOCK_REQUERIDO (stockmaximo_in     IN LGTR_PRODUCTO_LOCAL.CA_STOCK_MAXIMO%TYPE,
          stockrequerido_in  IN LGTR_PRODUCTO_LOCAL.CA_STOCK_REPONER_CALCULA%TYPE,
          stockdisponible_in IN NUMBER)
           RETURN NUMBER IS
  stockreponercalc   LGTR_PRODUCTO_LOCAL.CA_STOCK_REPONER_CALCULA%TYPE;
  BEGIN
      stockreponercalc := 0;

  IF (stockdisponible_in >= stockrequerido_in) THEN
    stockreponercalc := 0;
  ELSE
    IF (stockmaximo_in = 1) THEN
      -- Si se tiene menos de 33% de entero de stock en el local --> Pedir 1 unidad
      IF (stockdisponible_in <= 1/3) THEN
        stockreponercalc := 1;
      ELSE
        stockreponercalc := 0;
      END IF;
    ELSE
        stockreponercalc := stockrequerido_in - TRUNC(stockdisponible_in);
    END IF;
  END IF;

/*
    IF (stockdisponible_in >= stockrequerido_in) THEN
     stockreponercalc := 0;
    ELSE
     stockreponercalc := stockrequerido_in - stockdisponible_in;
    END IF;
*/
  RETURN stockreponercalc;

 END;

/* ************************************************************************ */

  FUNCTION RELACION_PROVEEDORES(codigocompania_in IN VTTM_PROVEEDOR.CO_COMPANIA%TYPE)
        RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN

    OPEN eckcur FOR SELECT T1.CO_PROVEEDOR || 'Ã' ||
                        T1.DE_PROVEEDOR
                      FROM VTTM_PROVEEDOR T1, VTTR_PROVEEDOR_SERVICIO T2
                     WHERE T1.CO_COMPANIA = codigocompania_in
        AND T1.ES_PROVEEDOR = 'A'
        AND T2.CO_SERVICIO = '007'
        AND T2.CO_PROVEEDOR_SERVICIO = T1.CO_PROVEEDOR
      ORDER BY T1.DE_PROVEEDOR;

 RETURN eckcur;
  END;

/* ************************************************************************ */
/* INICIO ID = 001 */
  PROCEDURE INV_GRABA_KARDEX_COSTEO (codigocompania_in    IN LGTV_KARDEX.CO_COMPANIA%TYPE,
                                     codigolocal_in       IN LGTV_KARDEX.CO_LOCAL%TYPE,
                                     codigoproducto_in    IN LGTV_KARDEX.CO_PRODUCTO%TYPE,
                                     cantmovimiento_in    IN LGTV_KARDEX.CA_MOVIMIENTO%TYPE,
                                     vacostoproducto_in   IN LGTV_KARDEX.VA_COSTO_PRODUCTO%TYPE,
                                     tipodocumento_in     IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
                                     numerodocumento_in   IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
                                     descglosa_in         IN LGTV_KARDEX.DE_GLOSA%TYPE,
                                     codigogrupomotivo_in IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
                                     codigomotivo_in      IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE,
                                     idcreakardex_in      IN LGTV_KARDEX.ID_CREA_KARDEX%TYPE,
                                     nu_documento_ref_in  IN LGTV_KARDEX.NU_DOCUMENTO_REF%TYPE DEFAULT NULL)
  IS
    l_numeracion        CMTR_NUMERACION.NU_SEC_NUMERACION%TYPE;
    l_inprodfraccionado LGTR_PRODUCTO_LOCAL.IN_PROD_FRACCIONADO%TYPE;
    l_codigounidadventa LGTR_PRODUCTO_LOCAL.CO_UNIDAD_VENTA_FRACCION%TYPE;
    l_stockdisponible   LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;
    l_valorfraccion     LGTR_PRODUCTO_LOCAL.VA_FRACCION%TYPE;
    l_preciopromedio    LGTM_PRODUCTO.VA_PRECIO_PROMEDIO%TYPE;

    vlVaTotalInventario_ant LGTR_PRODUCTO_LOCAL.VA_TOTAL_INVENTARIO%TYPE;
    vlVaTotalInventario_act LGTR_PRODUCTO_LOCAL.VA_TOTAL_INVENTARIO%TYPE;
    vlCaStockDisponible_act LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;
    vlCaStockDisponible_ant LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;
    vlVaCostoProducto       LGTR_PRODUCTO_LOCAL.VA_COSTO_PRODUCTO%TYPE;
    vlCaMovimiento_act      LGTV_KARDEX.CA_MOVIMIENTO%TYPE;
    vlVaCostoPromedio       LGTV_KARDEX.VA_COSTO_PROMEDIO%TYPE;
  BEGIN
    l_numeracion := Ptovta_Utility.OBTENER_NUMERACION(codigocompania_in,codigolocal_in,'007');

    SELECT PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
           DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO, 'S', PRODUCTO_LOCAL.CO_UNIDAD_VENTA_FRACCION, PRODUCTO.CO_UNIDAD_VENTA),
           PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE,
           PRODUCTO_LOCAL.VA_FRACCION,
           PRODUCTO.VA_PRECIO_PROMEDIO,
           PRODUCTO_LOCAL.VA_TOTAL_INVENTARIO
      INTO l_inprodfraccionado,
           l_codigounidadventa,
           l_stockdisponible,
           l_valorfraccion,
           l_preciopromedio,
           vlVaTotalInventario_ant
      FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL,
           LGTM_PRODUCTO PRODUCTO
     WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
       AND PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
       AND PRODUCTO_LOCAL.CO_PRODUCTO = codigoproducto_in
       AND PRODUCTO_LOCAL.CO_COMPANIA = PRODUCTO.CO_COMPANIA
       AND PRODUCTO_LOCAL.CO_PRODUCTO = PRODUCTO.CO_PRODUCTO FOR UPDATE;

    vlVaCostoProducto   := vacostoproducto_in ;
    vlCaMovimiento_act  := cantmovimiento_in;
    vlCaStockDisponible_ant := l_stockdisponible;
    vlCaStockDisponible_act := l_stockdisponible + cantmovimiento_in;

    vlVaTotalInventario_act := ECKERD_COSTEO.GET_TOTAL_IVENTARIO_KARDEX(vlCaStockDisponible_ant,
                                        vlCaMovimiento_act, vlVaCostoProducto, vlVaTotalInventario_ant,
                                        vlCaStockDisponible_act);

    vlVaCostoPromedio := ECKERD_COSTEO.GET_VA_COSTO_KARDEX( vlCaMovimiento_act, vlVaCostoProducto,
                                        vlVaTotalInventario_act, vlCaStockDisponible_act);

    UPDATE LGTR_PRODUCTO_LOCAL
       SET CA_STOCK_DISPONIBLE = l_stockdisponible + cantmovimiento_in,
           VA_TOTAL_INVENTARIO = vlVaTotalInventario_act,
           VA_COSTO_PRODUCTO=  vlVaCostoPromedio ,
           FE_MOD_PROD_LOCAL = SYSDATE,
           IN_REPLICA = 0
     WHERE CO_COMPANIA = codigocompania_in
       AND CO_LOCAL = codigolocal_in
       AND CO_PRODUCTO = codigoproducto_in;

    INSERT INTO LGTV_KARDEX (CO_COMPANIA, CO_LOCAL, CO_PRODUCTO, NU_REVISION_PRODUCTO, NU_SEC_KARDEX,
                             TI_DOCUMENTO, NU_DOCUMENTO, IN_PROD_FRACCIONADO, CA_STOCK_INICIAL,
                             CO_UNIDAD_VENTA_INICIAL, VA_FRACCION_INICIAL, CA_MOVIMIENTO, CO_UNIDAD_VENTA_FINAL,
                             CA_STOCK_FINAL, VA_FRACCION_FINAL, FE_KARDEX, CO_GRUPO_MOTIVO,
                             CO_MOTIVO_KARDEX, VA_PRECIO_PROMEDIO, DE_GLOSA, ES_KARDEX, ID_CREA_KARDEX,
                             FE_CREA_KARDEX, VA_COSTO_PROMEDIO, VA_COSTO_PRODUCTO, VA_TOTAL_INVENTARIO, NU_DOCUMENTO_REF)
                     VALUES (codigocompania_in, codigolocal_in, codigoproducto_in, '0', l_numeracion,
                             tipodocumento_in, numerodocumento_in, l_inprodfraccionado, l_stockdisponible,
                             l_codigounidadventa, l_valorfraccion, cantmovimiento_in, l_codigounidadventa,
                             (l_stockdisponible + cantmovimiento_in), l_valorfraccion, SYSDATE, codigogrupomotivo_in,
                             codigomotivo_in, l_preciopromedio, descglosa_in, 'A', idcreakardex_in,
                             SYSDATE, vlVaCostoPromedio, vlVaCostoProducto,vlVaTotalInventario_act, nu_documento_ref_in  );

    Ptovta_Utility.ACTUALIZAR_NUMERACION(codigocompania_in,codigolocal_in,'007',idcreakardex_in);
  END;

  PROCEDURE INV_GRABA_KARDEX_STK_COSTEO(codigocompania_in   IN LGTV_KARDEX.CO_COMPANIA%TYPE,
       codigolocal_in    IN LGTV_KARDEX.CO_LOCAL%TYPE,
       codigoproducto_in    IN LGTV_KARDEX.CO_PRODUCTO%TYPE,
       cantmovimiento_in    IN LGTV_KARDEX.CA_MOVIMIENTO%TYPE,
       tipodocumento_in     IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
       numerodocumento_in   IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
       descglosa_in         IN LGTV_KARDEX.DE_GLOSA%TYPE,
       codigogrupomotivo_in IN LGTV_KARDEX.CO_GRUPO_MOTIVO%TYPE,
       codigomotivo_in      IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE,
       idcreakardex_in      IN LGTV_KARDEX.ID_CREA_KARDEX%TYPE
       ) IS
    l_numeracion        CMTR_NUMERACION.NU_SEC_NUMERACION%TYPE;
    l_inprodfraccionado LGTR_PRODUCTO_LOCAL.IN_PROD_FRACCIONADO%TYPE;
    l_codigounidadventa LGTR_PRODUCTO_LOCAL.CO_UNIDAD_VENTA_FRACCION%TYPE;
    l_stockdisponible   LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;
    l_stockcomprometido LGTR_PRODUCTO_LOCAL.CA_STOCK_COMPROMETIDO%TYPE;
    l_valorfraccion     LGTR_PRODUCTO_LOCAL.VA_FRACCION%TYPE;
    l_preciopromedio    LGTM_PRODUCTO.VA_PRECIO_PROMEDIO%TYPE;

    /* Inicio ID=001 */
    vlVaTotalInventario_ant LGTR_PRODUCTO_LOCAL.VA_TOTAL_INVENTARIO%TYPE;
    vlVaCostoProducto LGTR_PRODUCTO_LOCAL.VA_COSTO_PRODUCTO%TYPE;
    vlCaStockDisponible_ant LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;


    vlVaTotalInventario_act LGTR_PRODUCTO_LOCAL.VA_TOTAL_INVENTARIO%TYPE;
    vlCaMovimiento_act LGTV_KARDEX.CA_MOVIMIENTO%TYPE;
    vlVaCostoPromedio LGTV_KARDEX.VA_COSTO_PROMEDIO%TYPE;
    vlCaStockDisponible_act LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;

    /* Fin ID=001 */

  BEGIN
    l_numeracion := Ptovta_Utility.OBTENER_NUMERACION(codigocompania_in,codigolocal_in,'007');
 --LGTD_RECEPCION_PRODUCTO
 SELECT PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
     DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO, 'S',
         PRODUCTO_LOCAL.CO_UNIDAD_VENTA_FRACCION,
      PRODUCTO.CO_UNIDAD_VENTA),
     PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE,
     PRODUCTO_LOCAL.CA_STOCK_COMPROMETIDO,
     PRODUCTO_LOCAL.VA_FRACCION,
     PRODUCTO.VA_PRECIO_PROMEDIO,
     PRODUCTO_LOCAL.VA_COSTO_PRODUCTO,
     PRODUCTO_LOCAL.VA_TOTAL_INVENTARIO
   INTO l_inprodfraccionado, l_codigounidadventa, l_stockdisponible, l_stockcomprometido, l_valorfraccion, l_preciopromedio,vlVaCostoProducto,vlVaTotalInventario_ant
   FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL,
       LGTM_PRODUCTO PRODUCTO
  WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
    AND PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
    AND PRODUCTO_LOCAL.CO_PRODUCTO = codigoproducto_in
    AND PRODUCTO_LOCAL.CO_COMPANIA = PRODUCTO.CO_COMPANIA
    AND PRODUCTO_LOCAL.CO_PRODUCTO = PRODUCTO.CO_PRODUCTO FOR UPDATE;



    vlCaMovimiento_act:= cantmovimiento_in;
    vlCaStockDisponible_ant  := l_stockdisponible;
    vlCaStockDisponible_act := l_stockdisponible + cantmovimiento_in;


    vlVaTotalInventario_act := ECKERD_COSTEO.GET_TOTAL_IVENTARIO_KARDEX( vlCaStockDisponible_ant,vlCaMovimiento_act,
                                       vlVaCostoProducto,
                                       vlVaTotalInventario_ant,
                                       vlCaStockDisponible_act
                                    );
    vlVaCostoPromedio := ECKERD_COSTEO.GET_VA_COSTO_KARDEX( vlCaMovimiento_act,
                                       vlVaCostoProducto ,
                                       vlVaTotalInventario_act,
                                       vlCaStockDisponible_act
                                    );




    INSERT INTO LGTV_KARDEX (CO_COMPANIA, CO_LOCAL, CO_PRODUCTO, NU_REVISION_PRODUCTO,
           NU_SEC_KARDEX, TI_DOCUMENTO, NU_DOCUMENTO, IN_PROD_FRACCIONADO,
        CA_STOCK_INICIAL, CO_UNIDAD_VENTA_INICIAL, VA_FRACCION_INICIAL, CA_MOVIMIENTO,
        CO_UNIDAD_VENTA_FINAL, CA_STOCK_FINAL, VA_FRACCION_FINAL, FE_KARDEX,
        CO_GRUPO_MOTIVO, CO_MOTIVO_KARDEX, VA_PRECIO_PROMEDIO, DE_GLOSA,
        ES_KARDEX, ID_CREA_KARDEX, FE_CREA_KARDEX,VA_COSTO_PROMEDIO,VA_COSTO_PRODUCTO,VA_TOTAL_INVENTARIO)
          VALUES (codigocompania_in, codigolocal_in, codigoproducto_in, '0',
          l_numeracion, tipodocumento_in, numerodocumento_in, l_inprodfraccionado,
       l_stockdisponible, l_codigounidadventa, l_valorfraccion, cantmovimiento_in,
       l_codigounidadventa, (l_stockdisponible + cantmovimiento_in), l_valorfraccion, SYSDATE,
       codigogrupomotivo_in, codigomotivo_in, l_preciopromedio, descglosa_in,
       'A', idcreakardex_in, SYSDATE, vlVaCostoPromedio , vlVaCostoProducto, vlVaTotalInventario_act);




 UPDATE LGTR_PRODUCTO_LOCAL
    SET CA_STOCK_DISPONIBLE =  l_stockdisponible + cantmovimiento_in,
        CA_STOCK_COMPROMETIDO =  l_stockcomprometido + cantmovimiento_in,
       VA_TOTAL_INVENTARIO = vlVaTotalInventario_act,
        VA_COSTO_PRODUCTO =  vlVaCostoPromedio ,
        FE_MOD_PROD_LOCAL = SYSDATE,
               IN_REPLICA = 0
  WHERE CO_COMPANIA = codigocompania_in
    AND CO_LOCAL = codigolocal_in
    AND CO_PRODUCTO = codigoproducto_in;
    Ptovta_Utility.ACTUALIZAR_NUMERACION_NOCOMMIT(codigocompania_in,codigolocal_in,'007',idcreakardex_in);
    --COMMIT;
  END;


  PROCEDURE INV_GRABA_KARDEX_GUIA_IN_COST(codigocompania_in   IN LGTV_KARDEX.CO_COMPANIA%TYPE,
       codigolocal_in    IN LGTV_KARDEX.CO_LOCAL%TYPE,
       codigoproducto_in    IN LGTV_KARDEX.CO_PRODUCTO%TYPE,
       precio_promedio_in    IN LGTV_KARDEX.VA_COSTO_PRODUCTO%TYPE,
       cantmovimiento_in    IN LGTV_KARDEX.CA_MOVIMIENTO%TYPE,
       tipodocumento_in     IN LGTV_KARDEX.TI_DOCUMENTO%TYPE,
       numerodocumento_in   IN LGTV_KARDEX.NU_DOCUMENTO%TYPE,
       idcreakardex_in      IN LGTV_KARDEX.ID_CREA_KARDEX%TYPE,
       motivokardex_in   IN LGTV_KARDEX.CO_MOTIVO_KARDEX%TYPE
       ) IS
    l_numeracion        CMTR_NUMERACION.NU_SEC_NUMERACION%TYPE;
    l_inprodfraccionado LGTR_PRODUCTO_LOCAL.IN_PROD_FRACCIONADO%TYPE;
    l_codigounidadventa LGTR_PRODUCTO_LOCAL.CO_UNIDAD_VENTA_FRACCION%TYPE;
    l_stockdisponible   LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;
    l_valorfraccion     LGTR_PRODUCTO_LOCAL.VA_FRACCION%TYPE;
    l_preciopromedio    LGTM_PRODUCTO.VA_PRECIO_PROMEDIO%TYPE;

    vlVaTotalInventario_ant LGTR_PRODUCTO_LOCAL.VA_TOTAL_INVENTARIO%TYPE;
    vlVaCostoProducto LGTR_PRODUCTO_LOCAL.VA_COSTO_PRODUCTO%TYPE;
    vlCaStockDisponible_ant LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;

    vlVaTotalInventario_act LGTR_PRODUCTO_LOCAL.VA_TOTAL_INVENTARIO%TYPE;
    vlCaMovimiento_act LGTV_KARDEX.CA_MOVIMIENTO%TYPE;
    vlVaCostoPromedio LGTV_KARDEX.VA_COSTO_PROMEDIO%TYPE;
    vlCaStockDisponible_act LGTR_PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE%TYPE;

  BEGIN
    l_numeracion := Ptovta_Utility.OBTENER_NUMERACION(codigocompania_in,codigolocal_in,'007');
 --
 SELECT PRODUCTO_LOCAL.IN_PROD_FRACCIONADO,
     DECODE(PRODUCTO_LOCAL.IN_PROD_FRACCIONADO, 'S',
         PRODUCTO_LOCAL.CO_UNIDAD_VENTA_FRACCION,
      PRODUCTO.CO_UNIDAD_VENTA),
     PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE,
     PRODUCTO_LOCAL.VA_FRACCION,
     PRODUCTO.VA_PRECIO_PROMEDIO,
     PRODUCTO_LOCAL.CA_STOCK_DISPONIBLE,VA_TOTAL_INVENTARIO
   INTO l_inprodfraccionado, l_codigounidadventa, l_stockdisponible, l_valorfraccion, l_preciopromedio,vlCaStockDisponible_ant,vlVaTotalInventario_ant
   FROM LGTR_PRODUCTO_LOCAL PRODUCTO_LOCAL,
       LGTM_PRODUCTO PRODUCTO
  WHERE PRODUCTO_LOCAL.CO_COMPANIA = codigocompania_in
    AND PRODUCTO_LOCAL.CO_LOCAL = codigolocal_in
    AND PRODUCTO_LOCAL.CO_PRODUCTO = codigoproducto_in
    AND PRODUCTO_LOCAL.CO_COMPANIA = PRODUCTO.CO_COMPANIA
    AND PRODUCTO_LOCAL.CO_PRODUCTO = PRODUCTO.CO_PRODUCTO FOR UPDATE;
 --

         vlCaMovimiento_act:= cantmovimiento_in ;
         vlVaCostoProducto:=precio_promedio_in;
         vlCaStockDisponible_act := vlCaStockDisponible_ant + vlCaMovimiento_act;


        vlVaTotalInventario_act := ECKERD_COSTEO.GET_TOTAL_IVENTARIO_KARDEX( vlCaStockDisponible_ant,vlCaMovimiento_act,
                                       vlVaCostoProducto,
                                       vlVaTotalInventario_ant,
                                       vlCaStockDisponible_act
                                    );
        vlVaCostoPromedio := ECKERD_COSTEO.GET_VA_COSTO_KARDEX( vlCaMovimiento_act,
                                       vlVaCostoProducto ,
                                       vlVaTotalInventario_act,
                                       vlCaStockDisponible_act
                                    );



    INSERT INTO LGTV_KARDEX
     (CO_COMPANIA, CO_LOCAL, CO_PRODUCTO, NU_SEC_KARDEX,
   TI_DOCUMENTO, NU_DOCUMENTO, IN_PROD_FRACCIONADO, CA_STOCK_INICIAL,
      CO_UNIDAD_VENTA_INICIAL, VA_FRACCION_INICIAL, CA_MOVIMIENTO, CA_STOCK_FINAL,
   VA_FRACCION_FINAL, FE_KARDEX, CO_GRUPO_MOTIVO, CO_MOTIVO_KARDEX,
   VA_PRECIO_PROMEDIO, ES_KARDEX, ID_CREA_KARDEX, FE_CREA_KARDEX,                               VA_COSTO_PROMEDIO,
                               VA_COSTO_PRODUCTO,
                               VA_TOTAL_INVENTARIO)
     VALUES (codigocompania_in, codigolocal_in,     codigoproducto_in,   l_numeracion,
    tipodocumento_in,  numerodocumento_in, l_inprodfraccionado, l_stockdisponible,
    l_codigounidadventa, l_valorfraccion,  cantmovimiento_in,  (l_stockdisponible + cantmovimiento_in),
    l_valorfraccion,     SYSDATE,          '01',               motivokardex_in,
    l_preciopromedio,    'A',              idcreakardex_in,    SYSDATE,
                               vlVaCostoPromedio       ,
                               vlVaCostoProducto       ,
                               vlVaTotalInventario_act);

 UPDATE LGTR_PRODUCTO_LOCAL
    SET CA_STOCK_DISPONIBLE =  l_stockdisponible + cantmovimiento_in,
              VA_TOTAL_INVENTARIO = vlVaTotalInventario_act,
                    VA_COSTO_PRODUCTO =  vlVaCostoPromedio ,
                    ID_MOD_PROD_LOCAL= idcreakardex_in,
     FE_MOD_PROD_LOCAL = SYSDATE,
   IN_REPLICA = 0
  WHERE CO_COMPANIA = codigocompania_in
    AND CO_LOCAL = codigolocal_in
    AND CO_PRODUCTO = codigoproducto_in;
 --
    Ptovta_Utility.ACTUALIZAR_NUMERACION_NOCOMMIT(codigocompania_in,codigolocal_in,'007',idcreakardex_in);
    --COMMIT;
  END;




 /* FIN ID = 001 */

/* INICIO ID : 002 */

 FUNCTION OBTENER_MONTO_USADO_COTIZACION (codigocompania_in   IN LGTV_KARDEX.CO_COMPANIA%TYPE,
       codigolocal_in    IN LGTV_KARDEX.CO_LOCAL%TYPE)
       RETURN Number is
    l_primer_dia        DATE;
    l_ultimo_dia        DATE;
    l_total_usado       NUMBER;
  BEGIN


    select trunc(sysdate, 'mm') ,
            trunc(last_day(sysdate))
            into
            l_primer_dia,
            l_ultimo_dia
            from dual;

    SELECT NVL(SUM (va_total_compra),0)
      INTO l_total_usado
      FROM lgtc_recepcion_producto
     WHERE in_origen_guia_ingreso = 'C'
       AND es_recepcion_producto = 'C'
       AND fe_crea_recepcion_producto BETWEEN TO_DATE ( TO_CHAR ( l_primer_dia,'dd/MM/yyyy')|| ' 00:00:00','dd/MM/yyyy HH24:MI:SS')
                                          AND TO_DATE ( TO_CHAR ( l_ultimo_dia ,'dd/MM/yyyy')|| ' 23:59:59','dd/MM/yyyy HH24:MI:SS');


      RETURN l_total_usado;
  END;

  PROCEDURE INV_GRABA_DET_PSICOTROPICO(codigocompania_in         IN LGTD_RECEPCION_PRODUCTO.CO_COMPANIA%TYPE,
                                       codigolocal_in            IN LGTD_RECEPCION_PRODUCTO.CO_LOCAL%TYPE,
                                       numerorecepcion           IN LGTD_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE,
                                       numeroitem_in             IN LGTD_RECEPCION_PRODUCTO.NU_ITEM_RECEPCION_GUIA%TYPE,
                                       codigoproducto_in         IN LGTD_RECEPCION_PRODUCTO.CO_PRODUCTO%TYPE,
                                       cantenviadaentera_in      IN LGTD_RECEPCION_PRODUCTO.CA_ENVIADA_ENTERA%TYPE,
                                       cantconfirmadaentera_in   IN LGTD_RECEPCION_PRODUCTO.CA_CONFIRMADA_ENTERA%TYPE,
                                       cantdiferenciaentera_in   IN LGTD_RECEPCION_PRODUCTO.CA_DIFERENCIA_ENTERA%TYPE,
                                       fechavenc_in              IN CHAR,
                                       nulote_in                 IN LGTD_RECEPCION_PRODUCTO.NU_LOTE%TYPE,
                                       idcrearecepcion_in        IN LGTD_RECEPCION_PRODUCTO.ID_CREA_DET_RECEPCION_PRODUCTO%TYPE,
                                       preciocompra_in           IN LGTD_RECEPCION_PRODUCTO.VA_PRECIO_COMPRA%TYPE,
                                       totalitem_in              IN LGTD_RECEPCION_PRODUCTO.VA_TOTAL_ITEM%TYPE,
                                       nusolicitud_in            IN LGTC_RECEPCION_PRODUCTO.NU_SOLICITUD_PEDIDO%TYPE
                                       ) IS
    fechavenc         LGTD_RECEPCION_PRODUCTO.FE_VENCE_PRODUCTO%TYPE;
    inprodfraccionado LGTR_PRODUCTO_LOCAL.IN_PROD_FRACCIONADO%TYPE;
    valorfraccion     LGTR_PRODUCTO_LOCAL.VA_FRACCION%TYPE;
    preciopromedio    LGTM_PRODUCTO.VA_PRECIO_PROMEDIO%TYPE;
    unidadproducto    LGTM_PRODUCTO.DE_UNIDAD_PRODUCTO%TYPE;
    vItemSolicitud    LGTD_RECEPCION_PRODUCTO.NU_RECEPCION_PRODUCTO%TYPE;
  BEGIN
    SELECT PMAESTRO.VA_COSTO_PRODUCTO,
           NVL(PMAESTRO.DE_UNIDAD_PRODUCTO,' ')
      INTO preciopromedio, unidadproducto
      FROM LGTR_PRODUCTO_LOCAL PLOCAL,
           LGTM_PRODUCTO PMAESTRO
     WHERE plocal.CO_COMPANIA = codigocompania_in AND
           plocal.CO_LOCAL = codigolocal_in AND
           plocal.CO_PRODUCTO = codigoproducto_in AND
           plocal.CO_COMPANIA = pmaestro.CO_COMPANIA AND
           plocal.CO_PRODUCTO = pmaestro.CO_PRODUCTO FOR UPDATE;

    IF (fechavenc_in IS NOT NULL) THEN
       IF (LENGTH(TRIM(fechavenc_in))) > 0 THEN
          fechavenc := TO_DATE('01/' || fechavenc_in, 'dd/MM/yyyy');
       ELSE
          fechavenc := NULL;
       END IF;
    ELSE
       fechavenc := NULL;
    END IF;

    SELECT NU_ITEM_SOLICITUD_PEDIDO INTO vItemSolicitud
      FROM LGTD_SOLICITUD_PEDIDO
     WHERE CO_COMPANIA = '001'
       AND CO_LOCAL = '099'
       AND NU_SOLICITUD_PEDIDO = nusolicitud_in
       AND CO_PRODUCTO = codigoproducto_in;

    INSERT INTO LGTD_RECEPCION_PRODUCTO(CO_COMPANIA, CO_LOCAL, NU_RECEPCION_PRODUCTO, NU_ITEM_RECEPCION_GUIA,
                                        CO_PRODUCTO, NU_REVISION_PRODUCTO, CA_ENVIADA_ENTERA, CA_ENVIADA_FRACCION,
                                        CA_CONFIRMADA_ENTERA, CA_CONFIRMADA_FRACCION, CA_DIFERENCIA_ENTERA,
                                        CA_DIFERENCIA_FRACCION, IN_PROD_FRACCIONADO, VA_FRACCION,
                                        VA_PRECIO_PROMEDIO, IN_CONFIRMA_RECEPCION,IN_AFECTA_PRODUCTO,
                                        FE_VENCE_PRODUCTO, NU_LOTE, ES_DET_RECEPCION_PRODUCTO,
                                        ID_CREA_DET_RECEPCION_PRODUCTO, FE_CREA_DET_RECEPCION_PRODUCTO,
                                        VA_PRECIO_COMPRA, NU_PAGINA_GUIA, VA_TOTAL_ITEM, DE_UNIDAD_PRODUCTO,
                                        NU_ITEM_SOLICITUD_PEDIDO)
                                VALUES (codigocompania_in, codigolocal_in, numerorecepcion, numeroitem_in,
                                        codigoproducto_in, '0', cantenviadaentera_in, 0,
                                        cantconfirmadaentera_in, 0, cantdiferenciaentera_in,
                                        0, 'N', 0,
                                        preciopromedio, 'S','S',
                                        fechavenc, nulote_in, 'A',
                                        idcrearecepcion_in, SYSDATE,
                                        preciocompra_in, 1, totalitem_in, unidadproducto,
                                        vItemSolicitud);
  END;

/* FIN ID : 002 */
  PROCEDURE INV_ELIMINA_PEDIDO_REP(codigocompania_in   IN LGTC_PEDIDO_PRODUCTO.CO_COMPANIA%TYPE,
                                codigolocal_in      IN LGTC_PEDIDO_PRODUCTO.CO_LOCAL%TYPE,
                                tipopedidoprod_in   IN LGTC_PEDIDO_PRODUCTO.TI_PEDIDO_PRODUCTO%TYPE,
                                numpeiddoprod_in    IN LGTC_PEDIDO_PRODUCTO.NU_PEDIDO_PRODUCTO%TYPE)
  IS
    l_contador NUMBER;
    l_numeracion NUMBER;
  BEGIN
     SELECT COUNT(1) INTO l_contador
       FROM LGTC_PEDIDO_PRODUCTO
      WHERE CO_COMPANIA = codigocompania_in
        AND CO_LOCAL = codigolocal_in
        AND TI_PEDIDO_PRODUCTO = tipopedidoprod_in
        AND NU_PEDIDO_PRODUCTO = numpeiddoprod_in;

     IF (l_contador > 0) THEN
        UPDATE CMTR_NUMERACION
           SET NU_SEC_NUMERACION = NU_SEC_NUMERACION - 1
         WHERE CO_COMPANIA = codigocompania_in
           AND CO_LOCAL = codigolocal_in
           AND CO_NUMERACION = '005';

        DELETE LGTD_PEDIDO_PRODUCTO
         WHERE CO_COMPANIA = codigocompania_in
           AND CO_LOCAL = codigolocal_in
           AND TI_PEDIDO_PRODUCTO = tipopedidoprod_in
           AND NU_PEDIDO_PRODUCTO = numpeiddoprod_in;

        DELETE LGTC_PEDIDO_PRODUCTO
         WHERE CO_COMPANIA = codigocompania_in
           AND CO_LOCAL = codigolocal_in
           AND TI_PEDIDO_PRODUCTO = tipopedidoprod_in
           AND NU_PEDIDO_PRODUCTO = numpeiddoprod_in;

        DELETE LGTD_ERROR_CANT_PEDIDA
         WHERE CO_COMPANIA = codigocompania_in
           AND CO_LOCAL = codigolocal_in
           AND TI_PEDIDO_PRODUCTO = tipopedidoprod_in
           AND NU_PEDIDO_PRODUCTO = numpeiddoprod_in;

        COMMIT;
     END IF;

  END;

  FUNCTION GET_PRODUCTOSPSICOTROPICOS(codigocompania_in  IN LGTM_PRODUCTO.CO_COMPANIA%TYPE)
  RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN
    OPEN eckcur FOR
        SELECT CO_PRODUCTO || 'Ã' || DE_PRODUCTO
          FROM LGTM_PRODUCTO
         WHERE CO_COMPANIA = '001'
           AND IN_CONTROLADO = 'S'
         ORDER BY DE_PRODUCTO;

    RETURN eckcur;
  END;

  FUNCTION OBTENER_NOMBRE_PRODUCTO(codigocompania_in IN LGTM_PRODUCTO.CO_COMPANIA%TYPE,
                                   codigoproducto_in IN LGTM_PRODUCTO.CO_PRODUCTO%TYPE)
           RETURN LGTM_PRODUCTO.DE_PRODUCTO%TYPE IS
    l_producto LGTM_PRODUCTO.DE_PRODUCTO%TYPE;
  BEGIN
    SELECT NVL(TRIM(PRODUCTO.DE_PRODUCTO),' ') INTO l_producto
      FROM LGTM_PRODUCTO PRODUCTO
     WHERE PRODUCTO.CO_COMPANIA = codigocompania_in
       AND PRODUCTO.CO_PRODUCTO = codigoproducto_in;

    RETURN l_producto;
  EXCEPTION
   WHEN NO_DATA_FOUND THEN
     RETURN ' ';
  END;

  FUNCTION GET_KARDEXPSICOTROPICOS(codigocompania_in  IN LGTV_KARDEX.CO_COMPANIA%TYPE,
                                   codigolocal_in     IN LGTV_KARDEX.CO_LOCAL%TYPE,
                                   codigoproducto_in  IN LGTV_KARDEX.CO_PRODUCTO%TYPE,
                                   fechainicial_in    IN CHAR,
                                   fechafin_in        IN CHAR)
  RETURN EckerdCursor IS
    eckcur EckerdCursor;
  BEGIN

    IF (codigoproducto_in IS NOT NULL) THEN
        OPEN eckcur FOR
            SELECT NVL(PRODUCTO.CO_PRODUCTO, ' ')|| 'Ã' ||
                   NVL(PRODUCTO.DE_PRODUCTO, ' ')|| 'Ã' ||
                   NVL(TO_CHAR(KARDEX.FE_KARDEX, 'dd/MM/yyyy HH24:MI'),' ')|| 'Ã' ||
                   TRIM(TO_CHAR(NVL(DECODE(KARDEX.IN_PROD_FRACCIONADO, 'S', TRUNC(KARDEX.CA_MOVIMIENTO/DECODE(KARDEX.VA_FRACCION_INICIAL,NULL,1,0,1,KARDEX.VA_FRACCION_INICIAL)), KARDEX.CA_MOVIMIENTO) ,0), '999,999,990')) || 'Ã' ||
                   TRIM(TO_CHAR(NVL(DECODE(KARDEX.IN_PROD_FRACCIONADO, 'S', MOD(KARDEX.CA_MOVIMIENTO, KARDEX.VA_FRACCION_INICIAL), 0) , 0), '999,990')) || 'Ã' ||
                   NVL(KARDEX.DE_NOMBRE_MEDICO,' ') || 'Ã' ||
                   NVL(KARDEX.DE_NOMBRE_PACIENTE,' ') || 'Ã' ||
                   NVL(KARDEX.DE_NUMERO_RECETA,' ') || 'Ã' ||
                   NVL(TO_CHAR(KARDEX.FE_RECETA, 'dd/MM/yyyy'),' ')|| 'Ã' ||
                   CASE WHEN PRODUCTO.CO_CLASE_VENTA = '03' THEN 'Receta Triplicada'
                    WHEN PRODUCTO.CO_CLASE_VENTA = '04' THEN 'Receta Retenida'
                   ELSE ' ' END || 'Ã' ||
                   CASE WHEN KARDEX.IN_RECETA_PARCIAL = 'S' THEN 'SI'
                    WHEN KARDEX.IN_RECETA_PARCIAL = 'N' THEN 'NO'
                   ELSE ' ' END || 'Ã' ||
                   NVL(KARDEX.VA_FRACCION_INICIAL,0) || 'Ã' ||
                   NVL(KARDEX.VA_FRACCION_FINAL,0) || 'Ã' ||
                   NVL(KARDEX.DE_GLOSA,' ') || 'Ã' ||
                   NVL(KARDEX.ID_CREA_KARDEX,' ')|| 'Ã' ||
                   NVL(LABORATORIO.DE_LABORATORIO,' ') || 'Ã' ||
                   NVL(PRODUCTO.DE_UNIDAD_PRODUCTO ,' ')  || 'Ã' ||
                   DECODE(PROD_LOCAL.IN_PROD_FRACCIONADO, 'S', NVL(PROD_LOCAL.DE_UNIDAD_FRACCION,' '), ' ') || 'Ã' ||
                   NVL(MOTIVO.DE_MOTIVO,' ')|| 'Ã' ||
                   NVL(KARDEX.NU_DOCUMENTO, ' ')|| 'Ã' ||
                   TRIM(TO_CHAR(NVL(DECODE(KARDEX.IN_PROD_FRACCIONADO, 'S', TRUNC(KARDEX.CA_STOCK_INICIAL/DECODE(KARDEX.VA_FRACCION_INICIAL,NULL,1,0,1,KARDEX.VA_FRACCION_INICIAL)), KARDEX.CA_STOCK_INICIAL) ,0), '999,999,990')) || 'Ã' ||
                   TRIM(TO_CHAR(NVL(DECODE(KARDEX.IN_PROD_FRACCIONADO, 'S', MOD(KARDEX.CA_STOCK_INICIAL, KARDEX.VA_FRACCION_INICIAL), 0) , 0), '999,990'))|| 'Ã' ||
                   TRIM(TO_CHAR(NVL(DECODE(KARDEX.IN_PROD_FRACCIONADO, 'S', TRUNC(KARDEX.CA_STOCK_FINAL/DECODE(KARDEX.VA_FRACCION_FINAL,NULL,1,0,1,KARDEX.VA_FRACCION_FINAL)), KARDEX.CA_STOCK_FINAL) ,0), '999,999,990')) || 'Ã' ||
                   TRIM(TO_CHAR(NVL(DECODE(KARDEX.IN_PROD_FRACCIONADO, 'S', MOD(KARDEX.CA_STOCK_FINAL, KARDEX.VA_FRACCION_FINAL), 0) , 0), '999,990'))
              FROM LGTV_KARDEX KARDEX,
                   CMTR_MOTIVO MOTIVO,
                   LGTM_PRODUCTO PRODUCTO,
                   LGTR_PRODUCTO_LOCAL PROD_LOCAL,
                   LGTR_TIPO_DOCUMENTO TIPO_DOC_LG,
                   CMTR_LABORATORIO LABORATORIO
             WHERE KARDEX.CO_COMPANIA = codigocompania_in
                   AND KARDEX.CO_LOCAL = codigolocal_in
                   AND KARDEX.CO_PRODUCTO = codigoproducto_in
                   AND KARDEX.FE_KARDEX BETWEEN TO_DATE(fechainicial_in || ' 00:00:00', 'dd/MM/yyyy HH24:MI:SS')
                                            AND TO_DATE(fechafin_in     || ' 23:59:59', 'dd/MM/yyyy HH24:MI:SS')
                   AND PRODUCTO.IN_CONTROLADO = 'S'
                   AND KARDEX.CO_GRUPO_MOTIVO= MOTIVO.CO_GRUPO_MOTIVO
                   AND KARDEX.CO_MOTIVO_KARDEX = MOTIVO.CO_MOTIVO
                   AND KARDEX.CO_COMPANIA = PRODUCTO.CO_COMPANIA
                   AND KARDEX.CO_PRODUCTO = PRODUCTO.CO_PRODUCTO
                   AND KARDEX.NU_REVISION_PRODUCTO = PRODUCTO.NU_REVISION_PRODUCTO
                   AND KARDEX.CO_COMPANIA = PROD_LOCAL.CO_COMPANIA
                   AND KARDEX.CO_LOCAL = PROD_LOCAL.CO_LOCAL
                   AND KARDEX.CO_PRODUCTO = PROD_LOCAL.CO_PRODUCTO
                   AND KARDEX.NU_REVISION_PRODUCTO = PROD_LOCAL.NU_REVISION_PRODUCTO
                   AND PRODUCTO.CO_COMPANIA = LABORATORIO.CO_COMPANIA (+)
                   AND PRODUCTO.CO_LABORATORIO = LABORATORIO.CO_LABORATORIO (+)
                   AND KARDEX.CO_COMPANIA = TIPO_DOC_LG.CO_COMPANIA(+)
                   AND KARDEX.TI_DOCUMENTO = TIPO_DOC_LG.TI_DOCUMENTO(+);
    ELSE
        OPEN eckcur FOR
            SELECT NVL(PRODUCTO.CO_PRODUCTO, ' ')|| 'Ã' ||
                   NVL(PRODUCTO.DE_PRODUCTO, ' ')|| 'Ã' ||
                   NVL(TO_CHAR(KARDEX.FE_KARDEX, 'dd/MM/yyyy HH24:MI'),' ')|| 'Ã' ||
                   TRIM(TO_CHAR(NVL(DECODE(KARDEX.IN_PROD_FRACCIONADO, 'S', TRUNC(KARDEX.CA_MOVIMIENTO/DECODE(KARDEX.VA_FRACCION_INICIAL,NULL,1,0,1,KARDEX.VA_FRACCION_INICIAL)), KARDEX.CA_MOVIMIENTO) ,0), '999,999,990')) || 'Ã' ||
                   TRIM(TO_CHAR(NVL(DECODE(KARDEX.IN_PROD_FRACCIONADO, 'S', MOD(KARDEX.CA_MOVIMIENTO, KARDEX.VA_FRACCION_INICIAL), 0) , 0), '999,990')) || 'Ã' ||
                   NVL(KARDEX.DE_NOMBRE_MEDICO,' ') || 'Ã' ||
                   NVL(KARDEX.DE_NOMBRE_PACIENTE,' ') || 'Ã' ||
                   NVL(KARDEX.DE_NUMERO_RECETA,' ') || 'Ã' ||
                   NVL(TO_CHAR(KARDEX.FE_RECETA, 'dd/MM/yyyy'),' ')|| 'Ã' ||
                   CASE WHEN PRODUCTO.CO_CLASE_VENTA = '03' THEN 'Receta Triplicada'
                    WHEN PRODUCTO.CO_CLASE_VENTA = '04' THEN 'Receta Retenida'
                   ELSE ' ' END || 'Ã' ||
                   CASE WHEN KARDEX.IN_RECETA_PARCIAL = 'S' THEN 'SI'
                    WHEN KARDEX.IN_RECETA_PARCIAL = 'N' THEN 'NO'
                   ELSE ' ' END || 'Ã' ||
                   NVL(KARDEX.VA_FRACCION_INICIAL,0) || 'Ã' ||
                   NVL(KARDEX.VA_FRACCION_FINAL,0) || 'Ã' ||
                   NVL(KARDEX.DE_GLOSA,' ') || 'Ã' ||
                   NVL(KARDEX.ID_CREA_KARDEX,' ')|| 'Ã' ||
                   NVL(LABORATORIO.DE_LABORATORIO,' ') || 'Ã' ||
                   NVL(PRODUCTO.DE_UNIDAD_PRODUCTO ,' ')  || 'Ã' ||
                   DECODE(PROD_LOCAL.IN_PROD_FRACCIONADO, 'S', NVL(PROD_LOCAL.DE_UNIDAD_FRACCION,' '), ' ') || 'Ã' ||
                   NVL(MOTIVO.DE_MOTIVO,' ')|| 'Ã' ||
                   NVL(KARDEX.NU_DOCUMENTO, ' ')|| 'Ã' ||
                   TRIM(TO_CHAR(NVL(DECODE(KARDEX.IN_PROD_FRACCIONADO, 'S', TRUNC(KARDEX.CA_STOCK_INICIAL/DECODE(KARDEX.VA_FRACCION_INICIAL,NULL,1,0,1,KARDEX.VA_FRACCION_INICIAL)), KARDEX.CA_STOCK_INICIAL) ,0), '999,999,990')) || 'Ã' ||
                   TRIM(TO_CHAR(NVL(DECODE(KARDEX.IN_PROD_FRACCIONADO, 'S', MOD(KARDEX.CA_STOCK_INICIAL, KARDEX.VA_FRACCION_INICIAL), 0) , 0), '999,990'))|| 'Ã' ||
                   TRIM(TO_CHAR(NVL(DECODE(KARDEX.IN_PROD_FRACCIONADO, 'S', TRUNC(KARDEX.CA_STOCK_FINAL/DECODE(KARDEX.VA_FRACCION_FINAL,NULL,1,0,1,KARDEX.VA_FRACCION_FINAL)), KARDEX.CA_STOCK_FINAL) ,0), '999,999,990')) || 'Ã' ||
                   TRIM(TO_CHAR(NVL(DECODE(KARDEX.IN_PROD_FRACCIONADO, 'S', MOD(KARDEX.CA_STOCK_FINAL, KARDEX.VA_FRACCION_FINAL), 0) , 0), '999,990'))
              FROM LGTV_KARDEX KARDEX,
                   CMTR_MOTIVO MOTIVO,
                   LGTM_PRODUCTO PRODUCTO,
                   LGTR_PRODUCTO_LOCAL PROD_LOCAL,
                   LGTR_TIPO_DOCUMENTO TIPO_DOC_LG,
                   CMTR_LABORATORIO LABORATORIO
             WHERE KARDEX.CO_COMPANIA = codigocompania_in
                   AND KARDEX.CO_LOCAL = codigolocal_in
                   AND KARDEX.FE_KARDEX BETWEEN TO_DATE(fechainicial_in || ' 00:00:00', 'dd/MM/yyyy HH24:MI:SS')
                                            AND TO_DATE(fechafin_in     || ' 23:59:59', 'dd/MM/yyyy HH24:MI:SS')
                   AND PRODUCTO.IN_CONTROLADO = 'S'
                   AND KARDEX.CO_GRUPO_MOTIVO= MOTIVO.CO_GRUPO_MOTIVO
                   AND KARDEX.CO_MOTIVO_KARDEX = MOTIVO.CO_MOTIVO
                   AND KARDEX.CO_COMPANIA = PRODUCTO.CO_COMPANIA
                   AND KARDEX.CO_PRODUCTO = PRODUCTO.CO_PRODUCTO
                   AND KARDEX.NU_REVISION_PRODUCTO = PRODUCTO.NU_REVISION_PRODUCTO
                   AND KARDEX.CO_COMPANIA = PROD_LOCAL.CO_COMPANIA
                   AND KARDEX.CO_LOCAL = PROD_LOCAL.CO_LOCAL
                   AND KARDEX.CO_PRODUCTO = PROD_LOCAL.CO_PRODUCTO
                   AND KARDEX.NU_REVISION_PRODUCTO = PROD_LOCAL.NU_REVISION_PRODUCTO
                   AND PRODUCTO.CO_COMPANIA = LABORATORIO.CO_COMPANIA (+)
                   AND PRODUCTO.CO_LABORATORIO = LABORATORIO.CO_LABORATORIO (+)
                   AND KARDEX.CO_COMPANIA = TIPO_DOC_LG.CO_COMPANIA(+)
                   AND KARDEX.TI_DOCUMENTO = TIPO_DOC_LG.TI_DOCUMENTO(+);
    END IF;

    RETURN eckcur;
  END;

END Inkventa_Inventario;
/
