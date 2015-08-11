exp system/sider file=suminetref.dmp log=suminetref.log tables=CONTABILIDAD.CUENTAS_CONTABLES,COSTOCONT.CENTROS_COSTOS,COSTOCONT.SUBCTAS,CTAXPAGAR.CUENTAS_PROVEEDORES,CTAXPAGAR.PROVEEDORES,CTAXPAGAR.TIPOS_MONEDAS,CTAXPAGAR.TIPOS_PAGOS_ORDEN_COMPRA,CTAXPAGAR.FUNCIONARIOS,CTAXPAGAR.CUENTAS_PROVEEDORES_IMPO,VENTAS.BANCOS,VENTAS.CUENTAS_BANCOS,VENTAS.DEPARTAMENTOS,VENTAS.DISTRITOS,VENTAS.PAISES,VENTAS.PROVINCIAS,VENTAS.TIPO_CAMBIO,RRHH.TRABAJADORES

exp system/sider    owner=seguridadqa       file=./seguridadqaOLD.dmp 
exp system/sider    owner=suministrosqa     file=./suministrosqaOLD.dmp 

imp system/awpassword@AWORCL100    fromuser=contabilidad    touser=contabilidad  file=./suminetref.dmp  tables=CUENTAS_CONTABLES
imp system/awpassword@AWORCL100    fromuser=costocont       touser=costocont     file=./suminetref.dmp  tables=CENTROS_COSTOS,SUBCTAS
imp system/awpassword@AWORCL100    fromuser=ctaxpagar       touser=ctaxpagar     file=./suminetref.dmp  tables=CUENTAS_PROVEEDORES,PROVEEDORES,TIPOS_MONEDAS,TIPOS_PAGOS_ORDEN_COMPRA,CUENTAS_PROVEEDORES_IMPO,FUNCIONARIOS
imp system/awpassword@AWORCL100    fromuser=ventas          touser=ventas        file=./suminetref.dmp  tables=BANCOS,CUENTAS_BANCOS,DEPARTAMENTOS,DISTRITOS,PAISES,PROVINCIAS,TIPO_CAMBIO
imp system/awpassword@AWORCL100    fromuser=rrhh            touser=rrhh          file=./suminetref.dmp  tables=TRABAJADORES

imp system/awpassword@AWORCL100    fromuser=contabilidad    touser=contabilidad  file=./contabilidad.dmp 
imp system/awpassword@AWORCL100    fromuser=costocont       touser=costocont     file=./costocont.dmp 
imp system/awpassword@AWORCL100    fromuser=ctaxpagar       touser=ctaxpagar     file=./ctaxpagar.dmp 
imp system/awpassword@AWORCL100    fromuser=ventas          touser=ventas        file=./ventas.dmp 
imp system/awpassword@AWORCL100    fromuser=rrhh            touser=rrhh          file=./rrhh.dmp 

imp system/awpassword@AWORCL100    fromuser=seguridadqa     touser=seguridadqa   file=./seguridad.dmp 
imp system/awpassword@AWORCL100    fromuser=suministrosqa   touser=suministrosqa file=./suministrosqa.dmp 

