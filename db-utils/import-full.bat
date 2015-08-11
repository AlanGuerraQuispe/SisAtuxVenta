title Re creando BD 

copy /Y \\192.168.1.100\transfer\backup-db\orcl2\*.* .\tmp\

call sqlplus system/awpassword@orcl @re-crear-usuarios.sql

call import-db.bat


@echo Ejecutar manualmente (o
@echo select 'create synonym '||table_name ||' for '|| owner||'.'||table_name ||';' from DBA_ALL_TABLES where owner in
@echo  ('SEGURIDAD', 'CONTABILIDAD', 'COSTOCONT', 'CTAXPAGAR', 'VENTAS')
@echo  and table_name not like 'BIN$%'
@echo  and table_name != 'TABLA_MULTIPLE_TM'
 
@echo  Ejecutar manualmente 
@echo   select 'grant references on ' ||owner||'.'||table_name ||' to SUMIDESA;' from DBA_ALL_TABLES where owner in
@echo  ('SEGURIDAD', 'CONTABILIDAD', 'COSTOCONT', 'CTAXPAGAR', 'VENTAS')
@echo  and table_name not like 'BIN$%'
@echo  and table_name != 'TABLA_MULTIPLE_TM'

grant references on COSTOCONT.CENTROS_COSTOS to SEGURIDADQA;
grant references on RRHH.TRABAJADORES to SEGURIDADQA;


grant references on CONTABILIDAD.CUENTAS_CONTABLES to SUMINISTROSQA;
grant references on COSTOCONT.CENTROS_COSTOS to SUMINISTROSQA;
grant references on COSTOCONT.SUBCTAS to SUMINISTROSQA;
grant references on CTAXPAGAR.CUENTAS_PROVEEDORES to SUMINISTROSQA;
grant references on CTAXPAGAR.PROVEEDORES to SUMINISTROSQA;
grant references on CTAXPAGAR.TIPOS_MONEDAS to SUMINISTROSQA;
grant references on CTAXPAGAR.TIPOS_PAGOS_ORDEN_COMPRA to SUMINISTROSQA;
grant references on VENTAS.BANCOS to SUMINISTROSQA;
grant references on CTAXPAGAR.CUENTAS_PROVEEDORES_IMPO to SUMINISTROSQA;
grant references on VENTAS.CUENTAS_BANCOS to SUMINISTROSQA;
grant references on VENTAS.DEPARTAMENTOS to SUMINISTROSQA;
grant references on VENTAS.DISTRITOS to SUMINISTROSQA;
grant references on VENTAS.FUNCIONARIOS to SUMINISTROSQA;
grant references on VENTAS.PAISES to SUMINISTROSQA;
grant references on VENTAS.PROVINCIAS to SUMINISTROSQA;
grant references on VENTAS.TIPO_CAMBIO to SUMINISTROSQA;


grant references on SEGURIDADQA.PERFIL_TM to SUMINISTROSQA;
grant references on SEGURIDADQA.PERFIL_VENTANA_TR to SUMINISTROSQA;
grant references on SEGURIDADQA.APROBADOR_TM to SUMINISTROSQA;
grant references on SEGURIDADQA.AREA_TM to SUMINISTROSQA;
grant references on SEGURIDADQA.CARGO_TM to SUMINISTROSQA;
grant references on SEGURIDADQA.CLIENTE_TM to SUMINISTROSQA;
grant references on SEGURIDADQA.FUNCION_MATRIZ_TC to SUMINISTROSQA;
grant references on SEGURIDADQA.FUNCION_MATRIZ_TR to SUMINISTROSQA;
grant references on SEGURIDADQA.FUNCION_NIVEL_APROBADOR_TR to SUMINISTROSQA;
grant references on SEGURIDADQA.FUNCION_NIVEL_TR to SUMINISTROSQA;
grant references on SEGURIDADQA.NUMERACION_TM to SUMINISTROSQA;
grant references on SEGURIDADQA.OPERACION_NEGOCIO_TM to SUMINISTROSQA;
grant references on SEGURIDADQA.ORG_COMPRA_TM to SUMINISTROSQA;
grant references on SEGURIDADQA.PERFIL_ACCION_TR to SUMINISTROSQA;
grant references on SEGURIDADQA.FUNCION_MATRIZ_AREA_TR to SUMINISTROSQA;
grant references on SEGURIDADQA.FUNCION_NIVEL_USU_TR to SUMINISTROSQA;
grant references on SEGURIDADQA.FUNCION_TM to SUMINISTROSQA;
grant references on SEGURIDADQA.PERFIL_APLICACION_MENU_TR to SUMINISTROSQA;
grant references on SEGURIDADQA.ACCION_TM to SUMINISTROSQA;
grant references on SEGURIDADQA.APLICACION_MENU_TM to SUMINISTROSQA;
grant references on SEGURIDADQA.APLICACION_TM to SUMINISTROSQA;
grant references on SEGURIDADQA.PERFIL_ZONA_TR to SUMINISTROSQA;
grant references on SEGURIDADQA.PROCESO_TM to SUMINISTROSQA;
grant references on SEGURIDADQA.PROVEEDOR_TM to SUMINISTROSQA;
grant references on SEGURIDADQA.ROL_TM to SUMINISTROSQA;
grant references on SEGURIDADQA.SERVIDOR_TM to SUMINISTROSQA;
grant references on SEGURIDADQA.SETH_HISTORIAL_BITACORA to SUMINISTROSQA;
grant references on SEGURIDADQA.SETM_HISTORIAL_CONFIG to SUMINISTROSQA;
grant references on SEGURIDADQA.SQLEXPERT_PLAN1 to SUMINISTROSQA;
grant references on SEGURIDADQA.TIPO_AREA_TM to SUMINISTROSQA;
grant references on SEGURIDADQA.USUARIO_AREA_TR to SUMINISTROSQA;
grant references on SEGURIDADQA.USUARIO_PERFIL_TR to SUMINISTROSQA;
grant references on SEGURIDADQA.USUARIO_PROCESO_TR to SUMINISTROSQA;
grant references on SEGURIDADQA.USUARIO_SSO_TR to SUMINISTROSQA;
grant references on SEGURIDADQA.USUARIO_SUSTITUTO_TR to SUMINISTROSQA;
grant references on SEGURIDADQA.USUARIO_TM to SUMINISTROSQA;
grant references on SEGURIDADQA.VENTANA_TM to SUMINISTROSQA;
grant references on SEGURIDADQA.ZONA_TM to SUMINISTROSQA;


pause