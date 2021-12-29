DROP TABLE IF EXISTS andelseier;
DROP TABLE IF EXISTS leilighet;
DROP TABLE IF EXISTS bygning;
DROP TABLE IF EXISTS borettslag;




CREATE TABLE `borettslag` (
  `borettslagNavn` varchar(50) not null primary key,
  `gatenavnBorettslag` varchar(50),
  `antallBygninger` int,
  `etableringsar` int
);


CREATE TABLE bygning (
  adresseBygning int not null primary key ,
  `antallEtasjer` int,
  `antallLeiligheter` int,
  `borettslagNavn` varchar(50)
);

alter table bygning
    add foreign key (borettslagNavn) references borettslag(borettslagNavn);


CREATE TABLE `leilighet` (
  `leilighetsnummer` int not null primary key ,
  adresseBygning int,
  `antallRom` int,
  `antallKvadratmeter` double,
  `etasje` int
);

alter table leilighet
    add foreign key (adresseBygning) references bygning(adresseBygning);

CREATE TABLE `andelseier` (
  `fodselsnummer` bigint not null primary key,
  `navn` varchar(50),
  `borettslagNavn` varchar(50),
  adresseBygning int,
  `leilighetsnummer` int
);

alter table andelseier
    add foreign key (borettslagNavn) references borettslag(borettslagNavn),
    add foreign key (leilighetsnummer) references leilighet(leilighetsnummer),
    add foreign key (adresseBygning) references bygning(adresseBygning);


insert into borettslag values('borettslag1', 'borettslag1gata', 5, 1999);
insert into bygning values (100, 5, 5, 'borettslag1');
insert into leilighet values (100, 100, 4, 70.0, 2);
insert into andelseier values (300499, 'Trym', 'borettslag1', 100, 100);


#b
#borettslag(borettslagnavn(pk), gatenavnBorettslag, antallBygninger, etableringsar)
#bygning(adresseBygning(pk), antallEtasjer, antallLeiligheter, borettslagNavn(fk))
#leilighet(leilighetsnummer(pk), adresseBygning(fk, pk), antallRom, antallKvadratmeter, etasje)
#andelseier(fodselsnummer(pk), borettslagNanv(fk), adressseBygning(fk), leilighetsnummer(fk)

#e
#ja, vi kan la fremmednøkler være null, men ikke primærnøkler

#f
#kan bruke på bygning fordi alle leiligheter skal slettes dersom bygning slettes
