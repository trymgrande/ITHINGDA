-- Utgave tilpasset MySQL

-- Sletter tabeller

DROP TABLE bok_forfatter;
DROP TABLE forfatter;
DROP TABLE bok;
DROP TABLE forlag;
DROP TABLE konsulent;

-- Oppretter tabeller med entitetsintegritet (prim�rn�kkel)

CREATE TABLE forlag(
 forlag_id INTEGER NOT NULL AUTO_INCREMENT,
 forlag_navn VARCHAR(30),
 adresse VARCHAR(30),
 telefon CHAR(15),
CONSTRAINT forlag_pk PRIMARY KEY(forlag_id));

CREATE TABLE bok(
 bok_id INTEGER NOT NULL AUTO_INCREMENT,
 tittel VARCHAR(30),
 utgitt_aar INTEGER,
 forlag_id INTEGER,
CONSTRAINT bok_pk PRIMARY KEY(bok_id));

CREATE TABLE forfatter(
 forfatter_id INTEGER NOT NULL AUTO_INCREMENT,
 fornavn VARCHAR(20),
 etternavn VARCHAR (30),
 fode_aar INTEGER,
 dod_aar INTEGER,
 nasjonalitet VARCHAR(20),
CONSTRAINT forfatter_pk PRIMARY KEY(forfatter_id));

CREATE TABLE bok_forfatter(
 bok_id INTEGER NOT NULL,
 forfatter_id INTEGER NOT NULL,
CONSTRAINT bok_forfatter_pk PRIMARY KEY(bok_id, forfatter_id));

CREATE TABLE konsulent(
  kons_id INTEGER NOT NULL AUTO_INCREMENT,
  fornavn VARCHAR(20),
  etternavn VARCHAR (30),
  epost VARCHAR(30),
CONSTRAINT konsulent_pk PRIMARY KEY(kons_id));      

-- Legger p� referanseintegritet (fremmedn�kler)

ALTER TABLE bok
 ADD CONSTRAINT bok_fk FOREIGN KEY(forlag_id)REFERENCES forlag(forlag_id);

ALTER TABLE bok_forfatter
 ADD CONSTRAINT bok_forfatter_fk1 FOREIGN KEY(bok_id)REFERENCES bok(bok_id);
 
ALTER TABLE bok_forfatter
 ADD CONSTRAINT bok_forfatter_fk2 FOREIGN KEY(forfatter_id)REFERENCES forfatter(forfatter_id);
 
-- Legger inn data i tabellene

INSERT INTO forlag VALUES(DEFAULT,'Tapir','Trondheim', '73590000');
INSERT INTO forlag VALUES(DEFAULT,'Gyldendal','Oslo', '22220000');
INSERT INTO forlag VALUES(DEFAULT,'Cappelen','Oslo', '22200000');
INSERT INTO forlag VALUES(DEFAULT,'Universitetsforlaget','Oslo', '23230000');
INSERT INTO forlag VALUES(DEFAULT,'Achehaug','Oslo', '22000000');
INSERT INTO forlag VALUES(DEFAULT,'Oktober','Oslo', '22002200');
INSERT INTO forlag VALUES(DEFAULT,'Tiden','Oslo', '22232223');
INSERT INTO forlag VALUES(DEFAULT,'Harper Collins','USA',NULL);

INSERT INTO bok VALUES(DEFAULT,'T�penes',1995,7);
INSERT INTO bok VALUES(DEFAULT,'Rebecca',1981,3);
INSERT INTO bok VALUES(DEFAULT,'Gutter er gutter',1995,5);
INSERT INTO bok VALUES(DEFAULT,'Microserfs',1991,8);
INSERT INTO bok VALUES(DEFAULT,'Generation X',1995,8);
INSERT INTO bok VALUES(DEFAULT,'Klosterkr�nike',1982,3);
INSERT INTO bok VALUES(DEFAULT,'Universet',1988,3);
INSERT INTO bok VALUES(DEFAULT,'N�len',1978,3);
INSERT INTO bok VALUES(DEFAULT,'Markens gr�de',1917,2);
INSERT INTO bok VALUES(DEFAULT,'Victoria',1898,2);
INSERT INTO bok VALUES(DEFAULT,'Sult',1890,2);
INSERT INTO bok VALUES(DEFAULT,'Benoni',1908,2);
INSERT INTO bok VALUES(DEFAULT,'Rosa',1908,2);
INSERT INTO bok VALUES(DEFAULT,'Et skritt',1997,2);
INSERT INTO bok VALUES(DEFAULT,'Den femte',1996,2);
INSERT INTO bok VALUES(DEFAULT,'Villspor',1995,2);
INSERT INTO bok VALUES(DEFAULT,'Silkeridderen',1994,2);
INSERT INTO bok VALUES(DEFAULT,'Den hvite hingsten',1992,2);
INSERT INTO bok VALUES(DEFAULT,'Hunder',1992,2);
INSERT INTO bok VALUES(DEFAULT,'Bridget Jones',1995,5);
INSERT INTO bok VALUES(DEFAULT,'Se terapeuten',1998,3);
INSERT INTO bok VALUES(DEFAULT,'Sa mor',1996,3);
INSERT INTO bok VALUES(DEFAULT,'Jubel',1995,3);
INSERT INTO bok VALUES(DEFAULT,'Tatt av kvinnen',1993,3);
INSERT INTO bok VALUES(DEFAULT,'Supernaiv',1996,3);

INSERT INTO forfatter VALUES(DEFAULT,'John','Tool',1937, 1969, 'USA');
INSERT INTO forfatter VALUES(DEFAULT,'Ken','Follet',NULL, NULL, 'Britisk');
INSERT INTO forfatter VALUES(DEFAULT,'Stephen','Hawking',NULL, NULL, 'Britisk');
INSERT INTO forfatter VALUES(DEFAULT,'Jose','Saramago',1942, NULL, 'Portugisisk');
INSERT INTO forfatter VALUES(DEFAULT,'Douglas','Coupland',1961, NULL, 'Canadisk');
INSERT INTO forfatter VALUES(DEFAULT,'Nick','Hornby',1857, NULL, 'Britisk');
INSERT INTO forfatter VALUES(DEFAULT,'Knut','Hamsun',1859, 1952, 'Norsk');
INSERT INTO forfatter VALUES(DEFAULT,'Henning','Mankell',1948, NULL, 'Svensk');
INSERT INTO forfatter VALUES(DEFAULT,'Helen','Fielding',NULL, NULL, 'Britisk');
INSERT INTO forfatter VALUES(DEFAULT,'Hal','Sirowitz',NULL, NULL, 'USA');
INSERT INTO forfatter VALUES(DEFAULT,'Lars S.','Christensen',NULL, NULL, 'Norsk');
INSERT INTO forfatter VALUES(DEFAULT,'Erlend','Loe',NULL, NULL, 'Norsk');

INSERT INTO bok_forfatter VALUES(1, 1);
INSERT INTO bok_forfatter VALUES(2, 2);
INSERT INTO bok_forfatter VALUES(3, 6);
INSERT INTO bok_forfatter VALUES(4, 5);
INSERT INTO bok_forfatter VALUES(5, 5);
INSERT INTO bok_forfatter VALUES(6, 4);
INSERT INTO bok_forfatter VALUES(7, 3);
INSERT INTO bok_forfatter VALUES(8, 2);
INSERT INTO bok_forfatter VALUES(9, 7);
INSERT INTO bok_forfatter VALUES(10, 7);
INSERT INTO bok_forfatter VALUES(11, 7);
INSERT INTO bok_forfatter VALUES(12, 1);
INSERT INTO bok_forfatter VALUES(13, 1);
INSERT INTO bok_forfatter VALUES(14, 8);
INSERT INTO bok_forfatter VALUES(15, 8);
INSERT INTO bok_forfatter VALUES(16, 8);
INSERT INTO bok_forfatter VALUES(17, 8);
INSERT INTO bok_forfatter VALUES(18, 8);
INSERT INTO bok_forfatter VALUES(19, 8);
INSERT INTO bok_forfatter VALUES(20, 9);
INSERT INTO bok_forfatter VALUES(21, 10);
INSERT INTO bok_forfatter VALUES(22, 10);
INSERT INTO bok_forfatter VALUES(23, 11);
INSERT INTO bok_forfatter VALUES(24, 12);
INSERT INTO bok_forfatter VALUES(25, 12);

INSERT INTO konsulent VALUES(DEFAULT, 'Anne', 'Hansen', 'anne.hansen@xxx.com');
INSERT INTO konsulent VALUES(DEFAULT, 'Bj�rn', 'Jensen', 'bjornj@yyy.com');
INSERT INTO konsulent VALUES(DEFAULT, 'Anne', '�s', 'anne.as@zzz.com');



#a
select * from bok;
SELECT bok_id, tittel from bok;
#b
SELECT * from forlag, bok;
#resultatet er alle mulig kombinasjoner av relasjonene
#c
SELECT * from forlag JOIN bok WHERE forlag.forlag_id = bok.forlag_id;
#resultatet er en ny relasjon der forlag_id-attributtet er felles for alle rader
SELECT * from forlag NATURAL JOIN bok where forlag.forlag_id = bok.forlag_id;
#gir samme som join, men dupliserte attributter er ikke inkludert
#d
select forlag.forlag_id from forlag UNION SELECT bok_id from bok;
#union lager ny relasjon, krever unionkompatibilitet, dvs. like mange attributter, og p� same domene
#gir en tabell med forlag_id

#a
SELECT forlag_navn FROM forlag;
#b
select * from forlag LEFT JOIN bok ON bok.forlag_id = forlag.forlag_id WHERE bok_id is NULL;
#setter sammen tabellene der forlag_id er like og lik null
#c
SELECT * from forfatter WHERE fode_aar = 1948;
#d
select (forlag.forlag_navn and forlag.adresse) from forlag where forlag_id = (select forlag_id from bok where bok.tittel = 'Generation X');
#e
SELECT * from bok_forfatter WHERE forfatter_id = (SELECT forfatter_id FROM forfatter WHERE forfatter.etternavn = 'Hamsun');
#f
select * from forlag join bokx