SET ORACLE_SID=ORCL 
SET IMPDIR=../dumps

title Re creando BD en %ORACLE_SID% desde %IMPDIR%

call sqlplus system/awpassword@%ORACLE_SID% @segsumi-1re-crear-usuarios.sql

call sqlplus system/awpassword@%ORACLE_SID% @segsumi-3grants-seg.sql

imp system/awpassword@%ORACLE_SID%    fromuser=seguridadqa       touser=seguridadqa        file=%IMPDIR%/seguridadqa.dmp 

call sqlplus system/awpassword@%ORACLE_SID% @segsumi-5grants-sumi.sql

imp system/awpassword@%ORACLE_SID%    fromuser=suministrosqa        touser=suministrosqa         file=%IMPDIR%/suministrosqa.dmp indexfile=index.sql full=y

call sqlplus suministrosqa/suministrosqa@%ORACLE_SID% @segsumi-9reconfigurar.sql

pause