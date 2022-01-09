---
title: moderne primærminne
updated: 2019-01-30 11:17:37Z
created: 2018-12-01 13:02:24Z
---

egen (tregere) klokkefrekvens på bussen over en av kontroll-linjene
klokkefrekvens på buss avgjør hastighet på burst

synkron ram har styreelektronikk som synkroniserer med bussfrekvensen, gjør at vi kan øke bussfrekvensen (eks. Syncronous Dynamic RAM)

DDRAM oppgis i overføringer/s og er 2x bussfrekvens

aksess:
Row Access Strobe: kontrollsignal som varsler gyldig row
RAS-to-CAS-delay (Trcd): antall klokkesykluser å klargjøre row access
Column Access Strobe: kontrollsignal som varsler gyldig column

CAS-latency (CL): antall klokkesykluser å klargjøre column access (2-11 klokkesykluser)

Trp: antall klokkesykluser forsinkelse dersom feil rekke aktivert
Tras: antall klokkesykluser forsinkelse dersom feil kolonne aktivert
Trcd + CL + Trp + Tras = tot klokkesykluser
nye kort har rom med info om anbefalte parameter