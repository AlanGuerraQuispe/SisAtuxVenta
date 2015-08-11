drop user rrhh	cascade;
drop user contabilidad	cascade;
drop user costocont	cascade;
drop user ctaxpagar	cascade;
drop user ventas	cascade;

create user rrhh		identified by rrhh;
create user contabilidad		identified by contabilidad;
create user costocont		identified by costocont;
create user ctaxpagar		identified by ctaxpagar;
create user ventas		identified by ventas;

grant all privileges to rrhh;								
grant all privileges to contabilidad;
grant all privileges to costocont;
grant all privileges to ctaxpagar;
grant all privileges to ventas;

exit