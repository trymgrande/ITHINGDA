-- b)
-- arbeidshistorikk(_oppdragsnummer_, startdato, sluttdato, timer, sluttattest, bedrifts_id*, kandidat_id*)
-- kandidat(_kandidat_id_, fornavn, etternavn, telefon, epost)
-- bedrift(_bedrifts_id_, navn, telefon, epost)
-- oppdrag(_oppdrags_id_, bedrifts_id* startdato, sluttdato)
-- kvalifikasjoner(_kval_id_*, _kandidat_id_*)
-- kvalifikasjonstyper(_kval_id_, navn, beskrivelse)

-- c)

drop table if exists oppdrag;
drop table if exists arbeidshistorikk;
drop table if exists bedrift;
drop table if exists kvalifikasjoner;
drop table if exists kandidat;
drop table if exists kvalifikasjonstyper;



create table arbeidshistorikk(
  oppdragsnummer integer primary key,
  startdato DATE,
  sluttdato DATE,
  timer integer,
  sluttattest varchar(30),
  bedrifts_id integer,
  kandidat_id integer
);



create table kandidat(
  kandidat_id integer primary key,
  fornavn varchar(30),
  etternavn varchar(30),
  telefon integer,
  epost varchar(30)
);

-- alter table kandidat(
--  add foreign key(kandidat_id)
--  references  kvalifikasjoner(kval_id)

create table bedrift(
  navn varchar(30),
  telefon integer,
  epost varchar(30),
  bedrifts_id integer primary key
);

create table oppdrag(
  oppdrags_id integer primary key,
  bedrifts_id integer,
  startdato DATE,
  sluttdato DATE
);

alter table oppdrag
  add foreign key(bedrifts_id)
  references bedrift(bedrifts_id);

alter table arbeidshistorikk
  add foreign key(bedrifts_id)
  references bedrift(bedrifts_id);

alter table arbeidshistorikk
    add foreign key(kandidat_id)
    references kandidat(kandidat_id);

create table kvalifikasjoner(
  kval_id integer,
  kandidat_id integer,
  primary key (kval_id, kandidat_id)
);


create table kvalifikasjonstyper(
  kval_id integer primary key,
  navn varchar(30),
  beskrivelse varchar(30)
);

alter table kvalifikasjoner
  add foreign key(kval_id)
  references kvalifikasjonstyper(kval_id);

alter table kvalifikasjoner
  add foreign key(kandidat_id)
  references kandidat(kandidat_id)
;
-- );

-- alter table kvalifikasjonstyper
--   add foreign key(kval_id)
--   references kvalifikasjoner(

-- );


insert into arbeidshistorikk values(1, DATE('2003-10-18'), DATE('2003-10-20'), 10, 'jobb utført', 12345, 300499);
insert into kandidat values (300499, 'trym', 'grande', 40475830, 'trym@123');
insert into bedrift values('nyBedrift', 00123, 'nyBedrift@123', 123123);
insert into oppdrag values(1, 123123, DATE('2003-10-18'), DATE('2003-10-20'));
insert into kvalifikasjoner values(1, 'lærer', 300499);
insert into kvalifikasjonstyper values(300499, 'lærer', 'lærer bort');




-- kan ha 0 kvalifikasjoner

-- d)
-- 1.  SELECT navn, telefon, epost FROM bedrift;

-- 2. SELECT * FROM oppdrag LEFT JOIN bedrift ON oppdrag.bedrifts_id = bedrift.bedrifts_id;

-- 3. SELECT * FROM kandidat JOIN kvalifikasjoner ON kandidat.kandidat_id = kvalifikasjoner.kandidat_id JOIN kvalifikasjonstyper ON kvalifikasjoner.kval_id = kvalifikasjonstyper.kval_id;

-- 4. SELECT * FROM kandidat LEFT JOIN kvalifikasjoner ON kandidat.kandidat_id = kvalifikasjoner.kandidat_id LEFT JOIN kvalifikasjonstyper ON kvalifikasjoner.kval_id = kvalifikasjonstyper.kval_id;

-- 5. SELECT * FROM arbeidshistorikk JOIN bedrift ON arbeidshistorikk.bedrift_id = bedrift.bedrift_id JOIN kandidat on kandidat.kandidat_id = arbeidshistorikk.kandidat_id WHERE kandidat.kandidat_id = 300499;