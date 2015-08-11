SET ORACLE_SID=AWORCLCT100 
SET IMPDIR=../dumps

title Re creando BD en %ORACLE_SID% desde %IMPDIR%

call sqlplus system/awpassword@%ORACLE_SID% @segsumi-1re-crear-usuarios.sql

imp system/awpassword@%ORACLE_SID% fromuser=rrhh         touser=rrhh          file=%IMPDIR%/rrhh.dmp 
imp system/awpassword@%ORACLE_SID% fromuser=contabilidad touser=contabilidad  file=%IMPDIR%/contabilidad.dmp 
imp system/awpassword@%ORACLE_SID% fromuser=costocont    touser=costocont     file=%IMPDIR%/costocont.dmp 
imp system/awpassword@%ORACLE_SID% fromuser=ctaxpagar    touser=ctaxpagar     file=%IMPDIR%/ctaxpagar.dmp 
imp system/awpassword@%ORACLE_SID% fromuser=ventas       touser=ventas        file=%IMPDIR%/ventas.dmp 

pause