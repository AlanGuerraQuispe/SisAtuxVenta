DROP TABLE VENTA.LGTR_PRODUCTO_LOTE CASCADE CONSTRAINTS;

CREATE TABLE VENTA.LGTR_PRODUCTO_LOTE
(
  CO_COMPANIA              CHAR(3 BYTE),
  CO_LOCAL                    CHAR(3 BYTE)  NOT NULL,
  CO_PRODUCTO             CHAR(6 BYTE)  NOT NULL,
  FE_INGRESO                 DATE,
  FE_VENCIMIENTO          DATE,
  LOTE                            VARCHAR2(10 BYTE) NOT NULL,
  TI_DOCUMENTO_RECEPCION  CHAR(2 BYTE),
  NU_DOCUMENTO_RECEPCION CHAR(10 BYTE),
  ID_CREA_FORMA_PAGO_PEDIDO VARCHAR2(15 BYTE) NOT NULL,
  FE_CREA_FORMA_PAGO_PEDIDO DATE    NOT NULL  
)
TABLESPACE TSDATA01
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          320K
            NEXT             2M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

DROP PUBLIC SYNONYM LGTR_PRODUCTO_LOTE;

CREATE OR REPLACE PUBLIC SYNONYM LGTR_PRODUCTO_LOTE FOR VENTA.LGTR_PRODUCTO_LOTE;


GRANT SELECT ON VENTA.LGTR_PRODUCTO_LOTE TO ATUXREPLICA;

GRANT DELETE, INSERT, SELECT, UPDATE ON VENTA.LGTR_PRODUCTO_LOTE TO ECVENTA;