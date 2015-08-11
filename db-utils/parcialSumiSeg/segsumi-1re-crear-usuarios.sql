drop user suministrosqa	cascade;
drop user seguridadqa	cascade;

create user suministrosqa		identified by suministrosqa;
create user seguridadqa		identified by seguridadqa;

grant all privileges to suministrosqa;								
grant all privileges to seguridadqa;

exit