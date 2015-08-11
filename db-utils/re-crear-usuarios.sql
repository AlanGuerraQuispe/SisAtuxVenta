drop user suministrosqa	cascade;
drop user seguridadqa	cascade;
drop user contabilidad	cascade;
drop user costocont	cascade;
drop user ctaxpagar	cascade;
drop user ventas	cascade;
drop user rrhh		cascade;


create user suministrosqa		identified by suministrosqa;
create user seguridadqa		identified by seguridadqa;
create user contabilidad	identified by contabilidad;
create user costocont		identified by costocont;
create user ctaxpagar		identified by ctaxpagar;
create user ventas		identified by ventas;
create user rrhh		identified by rrhh;

grant all privileges to suministrosqa;								
grant all privileges to seguridadqa;
grant all privileges to contabilidad;
grant all privileges to costocont;
grant all privileges to ctaxpagar;
grant all privileges to ventas;
grant all privileges to rrhh;								

exit