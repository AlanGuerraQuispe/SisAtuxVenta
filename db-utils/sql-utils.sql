declare
    cursor c is 
        select * from DBA_ALL_TABLES where owner in
        (/*'SEGURIDAD', 'CONTABILIDAD', 'COSTOCONT', 'CTAXPAGAR', */'VENTAS')
        and table_name not like 'BIN$%'
        and table_name != 'TABLA_MULTIPLE_TM';
        
    str varchar2(1000);
begin
  DBMS_OUTPUT.ENABLE;
  --DBMS_OUTPUT.SERVEROUTPUT(TRUE);

  for r in c
  loop
     begin
            str := 'drop synonym SUMIDESA.'||r.table_name ;
            DBMS_OUTPUT.PUT_LINE(str || ';' );
            execute immediate str ;
     EXCEPTION            
        WHEN OTHERS THEN
          NULL;
     end;   
    
     begin
            str := 'create synonym SUMIDESA.'||r.table_name  ||' for '|| r.owner || '.'||r.table_name  ||' ';
            DBMS_OUTPUT.PUT_LINE(str || ';' );
            execute immediate str using r.table_name, r.owner, r.table_name;
     EXCEPTION            
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE(SQLERRM);
     end;   
  end loop;
end;        
 
