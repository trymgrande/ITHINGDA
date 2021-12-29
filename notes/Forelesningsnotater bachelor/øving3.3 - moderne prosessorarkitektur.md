---
title: øving3.3 - moderne prosessorarkitektur
updated: 2019-01-30 09:24:15Z
created: 2019-01-16 11:06:22Z
author: trym.grande@gmail.com
---

1 Institutt for informatikk og e-læring, NTNU Teoriøving i Datateknikk Geir Ove Rosvold

Moderne prosessorarkitektur

Parallellitet på instruksjonsnivå
a) Hva er pipeline, og hvorfor brukes det i de fleste moderne prosessorer?

et samlebånd som lar instruksjoner utføres parallelt. fordel: raskere, ulempe: hasards:

strukturell: flere instruksjoner trenger samme ressurs samtidig
data: trenger verdi fra tidligere instruksjon
kontroll: vet ikke rekkefølge på fremtidige instruksjoner
b) Hva betyr det at en prosessor er superskalar?
utfører flere instruksjoner parallelt (flere pipelines)

Pipeline

a) En instruksjon i et program sørger for at følgende 5 hendelser ( i., ii.,…,v. ) skjer:

hent (eng: ”fetch”) instruksjonen fra minnet og til instruksjonsregisteret.
dekod instruksjonen.
hent operandene fra minnet (om nødvendig).
utfør (eng: ”execute”) operasjonen.
lagre resultatene på egnet sted.
 Ordne disse ( i),.., v) ) i riktig rekkefølge.

b) En prosessor bruker en 5-trinns pipeline med trinnene som er angitt i oppgave a). Det skal utføres ti instruksjoner på denne prosessoren. Hvor mange klokkesykluser tar dette under ideelle forhold? (Ideelle forhold vil si at vi får maksimal uttelling ved bruk av pipeline). Vis tydelig hvordan du finner svaret.

14
![](../_resources/31d8ac81606e828c7777d6b929689e90.png)

c) Vanligvis vil en prosessor bruke flere klokkesykluser enn det antall du beregnet i oppgave b). Dette skyldes helt fundamentale problemer med pipeline som vi har diskutert i kurset. Hvilke problemer er det snakk om?

hasards:

kontroll: vet ikke hvilken instruksjon som vil bli utført neste pga. hopp i programmet

data: instruksjon krever data fra tidligere instruksjon
strukturell: to instruksjoner krever tilgang på samme type ressurs