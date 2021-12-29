-- 1
select * from borettslag where etabl_aar >= 1975 and etabl_aar <= 1985
-- 2
SELECT andelseier.fornavn, andelseier.etternavn, andelseier.ansiennitet from andelseier order by andelseier.ansiennitet desc
-- 3
select MIN(borettslag.etabl_aar) from borettslag
-- 4?
SELECT bygning.bygn_adr from bygning join leilighet
where leilighet.ant_rom >= 3
-- 5 Finn antall bygninger i borettslaget "Tertitten".
SELECT count(bolag_navn) "Tertitten" from bygning
-- 6    Lag en liste som viser antall bygninger i hvert enkelt borettslag. Listen skal være sortert på borettslagsnavn. Husk at det kan finnes borettslag uten bygninger - de skal også med.
SELECT COUNT(bygning.bolag_navn) as "Antall bygninger",borettslag.bolag_navn FROM borettslag LEFT JOIN bygning on bygning.bolag_navn = borettslag.bolag_navn GROUP BY borettslag.bolag_navn ORDER by bolag_navn;
-- 7    Finn antall leiligheter i borettslaget "Tertitten".
SELECT COUNT(*)
FROM leilighet
JOIN bygning
ON leilighet.bygn_id=bygning.bygn_id
JOIN borettslag
ON borettslag.bolag_navn=bygning.bolag_navn
WHERE borettslag.bolag_navn="Tertitten"
-- 8    Hvor høyt kan du bo i borettslaget "Tertitten"?
SELECT MAX(leilighet.etasje) FROM leilighet JOIN bygning ON bygning.bygn_id = leilighet.bygn_id WHERE bygning.bolag_navn = "Tertitten"
-- 9    Finn navn og nummer til andelseiere som ikke har leilighet.
SELECT * FROM andelseier LEFT JOIN leilighet ON andelseier.and_eier_nr = leilighet.and_eier_nr
-- 10    Finn antall andelseiere pr borettslag, sortert etter antallet. Husk at det kan finnes borettslag uten andelseiere - de skal også med.
select count(and_eier_nr) as 'antall andelseiere', borettslag.bolag_navn
from borettslag
left join andelseier on andelseier.bolag_navn = borettslag.bolag_navn
group by borettslag.bolag_navn
order by borettslag.bolag_navn
-- 11 Skriv ut en liste over alle andelseiere. For de som har leilighet, skal leilighetsnummeret skrives ut.
SELECT fornavn,etternavn, leil_nr FROM andelseier LEFT JOIN leilighet ON andelseier.and_eier_nr=leilighet.and_eier_nr
-- 12    Hvilke borettslag har leiligheter med eksakt 4 rom?
SELECT leilighet.ant_rom, COUNT(ant_rom) AS "Antall leiligheter" FROM leilighet LEFT JOIN bygning ON leilighet.bygn_id=bygning.bygn_id WHERE ant_rom = 4 GROUP BY ant_rom


--
select COUNT(*)
FROM leilighet JOIN andelseier
         ON andelseier.and_eier_nr = leilighet.and_eier_nr
where leilighet.ant_rom = 4
-- 13    Skriv ut en liste over antall andelseiere pr postnr og poststed, begrenset til de som bor i leiligheter tilknyttet et borettslag. Husk at postnummeret til disse er postnummeret til bygningen de bor i, og ikke postnummeret til borettslaget. Du trenger ikke ta med poststeder med 0 andelseiere. (Ekstraoppgave: Hva hvis vi vil ha med poststeder med 0 andelseiere?)
SELECT poststed.postnr, poststed.poststed, COUNT(leilighet.and_eier_nr) FROM poststed LEFT JOIN bygning ON poststed.postnr = bygning.postnr JOIN leilighet ON leilighet.bygn_id GROUP BY poststed.postnr
