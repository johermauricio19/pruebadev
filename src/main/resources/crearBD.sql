create schema pruebadev;

use pruebadev;

create table tbl_tarjeta (id_tarjeta int(6), numero bigint(10) not null, titular varchar(50),
	creada datetime not null, vence date, activa boolean not null, 
	bloqueada boolean not null, saldo int(9) not null, primary key (id_tarjeta));
	
create table tbl_transaccion (id_transaccion int(8) auto_increment, id_tarjeta int(6), valor int(9),
	fecha datetime not null, anulada boolean default false,
	primary key (id_transaccion), foreign key (id_tarjeta) references tbl_tarjeta (id_tarjeta));
	
