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
                NVL(PROD_LOCAL.CA_PROD_NO_ATENDIDO,0) AS CA_PROD_NO_ATENDIDO,              
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
                PROD_LOCAL.TR_DIAS) TR_DIAS
        FROM VTTM_LOCAL LOCAL,
                 LGTR_LINEA_LOCAL LINEA_LOCAL,
                 CMTR_LABORATORIO LABORATORIO,
                 LGTR_PRODUCTO_LOCAL PROD_LOCAL,             
                 LGTM_PRODUCTO PROD             
         WHERE PROD_LOCAL.CO_COMPANIA  = '001'--codigocompania_in
            AND   PROD_LOCAL.CO_LOCAL        = '005'--codigolocal_in
            AND   PROD_LOCAL.CO_COMPANIA  = LOCAL.CO_COMPANIA
            AND   PROD_LOCAL.CO_LOCAL        = LOCAL.CO_LOCAL
            AND   PROD_LOCAL.CO_COMPANIA  = PROD.CO_COMPANIA
            AND   PROD_LOCAL.CO_PRODUCTO = PROD.CO_PRODUCTO
            AND   PROD.CO_COMPANIA             = LINEA_LOCAL.CO_COMPANIA(+)
            AND   PROD.CO_LINEA_PRODUCTO  = LINEA_LOCAL.CO_LINEA_PRODUCTO(+)
            AND   PROD.CO_COMPANIA             = LABORATORIO.CO_COMPANIA (+)
            AND   PROD.CO_LABORATORIO       = LABORATORIO.CO_LABORATORIO(+)
            AND   LINEA_LOCAL.CO_LOCAL(+)   = '005'--codigolocal_in
            AND   PROD.ES_PRODUCTO              = 'A'        
           AND  (PROD_LOCAL.ES_PROD_LOCAL = 'A' OR PROD_LOCAL.CA_STOCK_DISPONIBLE > 0)
           AND  (EXISTS(SELECT   1
                               FROM  VTTR_VENTAS_PRODUCTO_DIA
                            WHERE CO_COMPANIA =  '001'--codigocompania_in
                                 AND CO_LOCAL       =  '005'--codigolocal_in
                                 AND  FE_DIA_VENTA BETWEEN (SYSDATE - (3*30))
                                 -- AND FE_DIA_VENTA BETWEEN (SYSDATE - (3*pNumDiasMax*7))
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
      
    -- Agregar el transito del producto  y la cantidad pendiente de compra
        --catransito               :=DECODE(inSumTransito,'S',INV_OBTIENE_PROD_TRANSITO(codigocompania_in, codigolocal_in, codigoproducto),0);
        --caCompraPendiente :=DECODE(inSumComprasPend,'S',INV_OBTIENE_PROD_COMPRA_PEND(codigocompania_in, codigolocal_in, codigoproducto),0);
        --caProdNoAtendido   :=DECODE(inFaltaCero,'S',caProdNoAtendido,0);
        
        SELECT DECODE(inSumTransito,'S',INV_OBTIENE_PROD_TRANSITO(codigocompania_in, codigolocal_in, codigoproducto),0),
                    DECODE(inSumComprasPend,'S',INV_OBTIENE_PROD_COMPRA_PEND(codigocompania_in, codigolocal_in, codigoproducto),0),
                    DECODE(inFaltaCero,'S',caProdNoAtendido,0) INTO  catransito, caCompraPendiente, caProdNoAtendido
                    FROM DUAL;
                     
    -- Calculando el stock stockRegular
        stockRegular:=stockdisponible +  catransito + caCompraPendiente+ caExhibicion;         
        
        SELECT SUM(CA_VENTA_DIA) INTO rotacionprod FROM VTTR_VENTAS_PRODUCTO_DIA      
            WHERE CO_COMPANIA='001'
                 AND CO_LOCAL='005'
                 AND CO_PRODUCTO='429396' 
                 AND FE_DIA_VENTA BETWEEN (SYSDATE - (diasrotacion))  AND SYSDATE;
       
        caVendidaPorPeriodo:=rotacionprod + caProdNoAtendido;
        caRotacionxDia:= caVendidaPorPeriodo/diasrotacion;
            
        stockminimo := minimodias*caRotacionxDia;
        stockmaximo:= maximodias*caRotacionxDia;
        
        IF(stockRegular<=caMinReponer)  THEN
              stockreponercalc:=  ABS(caMaxReponer- stockRegular);             
        END IF;             
        
     -- Actualiza la cantidad de productos
         UPDATE  LGTR_PRODUCTO_LOCAL
          SET CA_STOCK_REPONER_CALCULA = stockreponercalc,
                 CA_STOCK_MINIMO                  = stockminimo,
                 CA_STOCK_MAXIMO                 = stockmaximo,
                 CA_STOCK_REPONER                = NULL,
                 VA_ROTACION = ROUND(rotacionprod/maximodias, 2),
                 FE_MOD_PROD_LOCAL = SYSDATE,
                 IN_REPLICA = 0,
                 CA_STK_DISPONIBLE_PED_REP = TRUNC(stockdisponible),
                 CA_STOCK_TRANSITO_PED_REP = catransito
        WHERE CO_COMPANIA = codigocompania_in  AND
                   CO_LOCAL        = codigolocal_in AND
                   CO_PRODUCTO = codigoproducto;
        COMMIT;
        
   END LOOP;

       UPDATE VTTM_LOCAL
          SET FE_CALCULO_MAX_MIN = SYSDATE
        WHERE CO_COMPANIA = codigocompania_in
           AND  CO_LOCAL       = codigolocal_in;

        COMMIT;

  END;