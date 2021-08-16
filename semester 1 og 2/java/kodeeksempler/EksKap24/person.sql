drop table person;

create table person(
 persnr    integer primary key,
 fornavn   varchar(30) not null,
 etternavn varchar(30) not null);

insert into person values (100, 'Ole', 'Hansen');
insert into person values (101, 'Anne Grethe', 'Ås');
insert into person values (102, 'Jonny', 'Hansen');

