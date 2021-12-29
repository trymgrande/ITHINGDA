---
title: Applikasjonslaget
updated: 2020-06-03 16:11:57Z
created: 2020-04-23 10:06:56Z
author: gtrym
---

**HTTP (Hypertext Transfer Protocol)**
**
**

- sammendrag
    - protokoll for utveksling av meldinger mellom klient og tjener
    - tjener lytter kontinuerlig, klient forespør, tjener svarer
    - forespørsel består av: forespørselslinje, headerlinjer, kropp med data
    - forespørselslinjen har eget format med felt for metode, filnavn, http-versjon
    - svar består av: statuslinje, diverse headerlinjer, kropp med data
    - nyere http har flere metoder og komprimering
- hva er http?
    - protokoll som spesifiserer hvordan meldinger utveksles
- http-flyten
    - kommunikasjon består av 3 hoveddeler
        - tcp-kobling etableres (evt gjenbrukes)
        - forespørsel sendes
        - svaret sendes
- http-forespørsel
    - består av 3 deler
        - forespørselslinje
            - format: <metode> <filnavn> HTTP/<versjon> (1.1 vanlig)
        - div headerlinjer
            - domene
        - kropp med data
- http-svar
    - statuslinje
        - format: HTTP/<versjon><statuskode><statustekst>
    - kan deles opp i flere pakker
- HTTP/2
    - introduserer header-komprimering og klient-side-multipleksing for bedre ytelse

**HTTP Connections**
**
**
**
**

- Sammendrag
    - forbindelsen er metoden for forespørselen
    - pipelining effektiviserer forbindelsen ved å unngå venting på forespørsler
    - parallelle koblinger gjør det mulig å laste ned flere dokumenter samtidig
    - responser kan caches på nettsiden og gjenbrukes ved å sjekke for samme versjon på klient og server
- vedvarende forbindelser (keep-alive)
    - en type connection som hindrer time-out og opprettholder samme tcp-connection
- andre optimaliseringer
    - pipeline
    - parallelle forbindelser
    - caching
        - fungerer ikke for dynamiske objekter

**HTTP Cookies**
**
**

- Sammendrag
    - liten pakke data kommunisert gjennom klient og tjener som beskriver klienttilstand
    - blir satt av server og lagret hos klient
    - blir mest brukt til å lagre aktivitet og identifisere brukere
        - handlekurv, sider tidligere besøkt, innstillinger på siden, analytikk (telle hvor lenge/hva brukeren gjør), tilpasse annonser
- Implementasjon
    - kan opprettes på klient med JavaScript (document.cookie-egenskapen), men kan da ikke inneholde HttpOnly-attributtet
    - http-headeren som lager cookies er "Set-Cookie"
    - attributter: domene, secure, httponly
- type cookies
    - session cookie
        - midlertidig bruk (handlekurv), slettes når nettleser lukkes
    - persistent cookie
        - preferanser, innstillinger, info
            - bokmerker osv
        - tekstfiler i hdd
        - utløpsdato eks 1 år
    - secure cookie flag
        - begrenser bruk til kanaler definert av nettleser eks. HTTPS
        - persistent/session
        - sensitiv info bør fortsatt ikke lagres her til tross for bedre sikkerhet
        - kryptert data blir ofte lagret her eks. innloggingsdetaljer
    - http-only cookie
        - avviser klient-script for å hindre xss

**HTTP if-modified-since**
**
**

- Sammendrag
    - felt i http-header i get-request
    - cacher svar lokalt
    - bruker feltet for å sjekke for oppdatert versjon av ressursen
    - status 304 not modified betyr klienten har nyeste versjon

**Host**
**
**

- Sammendrag
    - http-header "host" sendes i hver request
    - forteller tjener hvilket domenenavn som skal benyttes
    - muligjør flere domenenavn under samme ip
        - kalles navnebasert virtuell hosting

**Domener og domenenavn**

- Sammendrag
    - brukes for bl.a. web og epost
    - erstatter IP med navn
    - domener er hierarkisk oppbygd
    - håndteres globalt av Internet Corporation for Assigned Names and Numbers (ICANN)
        - ICANN deligerer ansvar for IP-allokering til Internet Assigned Numbers Authority (IANA)
            - IANA deligerer arbeid til flere Regianal Internet Registry (RIR)
                - NORID deler ut domener under .no
- Domenenavnets hensikt
    - applikasjonslaget virker direkte til applikasjonen og utfører tjenester for den, bl.a. tjenesten dns
- domenenavnets oppbygning
    - subdomene: www, innsida (www peker nesten alltid til samme som root, så "www" er unødvendig for å nå fram)
        - domene: ntnu, vg
            - toppnivå: no, se com, org (nasjonale (2 chars) og organisatoriske (3 chars))
                - rotnivå: hvilke tjenester som er knyttet til hvilket toppnivå
- administrajon av domener

    -
**Navnetjenere**
**
**

- Sammendrag
    - rot videresender til andre navnetjenere avhengig av endelsen (.com/.org)
    - navnetjener er en maskin som oversetter mellom domenenavn og IP
        - lokal navnetjener ligger i nærheten  og bruker cache for rask responstid
    - rekursivitet gjør at navnetjeneren spør på vegne av en annen helt til svar er funnet, kontra iterativitet som kun svarer med henvisning til annen tjener som kan spørre videre
- hva er en navnetjener?
    - datamaskin med fast kobling som oversetter mellom navn og IP
    - gjør at brukeren slipper å huske IP-er
    - gjør navneendring enklere fordi endring kun skjer i tjeneren kontra alle brukere
- lokal navnetjener
    - mange lokaliserte navnetjenere for rask respons
- når navnetjeneren ikke har svaret
    - iterativt oppslag
        - tjeneren videresender deg til annen tjener til du får en sluttadresse
    - rekursivt oppslag
        - istedenfor å sende svaret til klient hver gang, spør tjener selv videre, og returnerer kun sluttadressen
        - bedre for caching
- navnetjener-hierarkiet
    - rotnavntjener peker deg til rett toppnivåtjener ved å se på url-ending
    - toppnivåtjener sender deg til navnetjener for gitt domene (eks. microsoft.com) sender videre til Azure sin navnetjener
    - Azure sin navnetjener svarer med IP til domenet/subdomenet ditt
    - finnes i praksis flere kloner av tjenerne rundt i verden, men kunne vært kun èn rotnavnetjener
- cache
    - nettleser
    - DNS resolver i OS
    - DNS resolver hos ISP
- cache fordeler/ulemper
    - +
        - responstid
        - ytelse
        - trafikk
    - -
        - må ryddes opp hver gang den oppdateres eller konfigurasjon endres
        - (info i cache fjernes hvis den rebootes)
- rekursivitet
    - distribuerer arbeid mot flere lag, og utnytter cache

**DNS Resource Records (RR)**
**
**

- Sammendrag
    - infoen som hentes av et DNS lookup
    - inkluderer korresponderende IP/navn, men også annen info om domenet
    - infoen lagres i SQL
    - struktur og typer RR bestemmes av IETF
- formål
    - koble IP til navn
- address record og quad-a record
- A og AAAA records er de mest brukte journalene som holder på info om IP/navn
    - A brukes for IPv4
    - AAAA brukes for IPv6
- MX record - eXchanger
    - navn og prioritet på smtp-tjenere som behandler post for domenet (10 prioriteringsgrader)
    - viser hvor mail skal bli adressert for å nå nettstedet til IP-en
    - styrer med navn og prioritet på SMTP-tjenere
        - SMTP-tjenere beskriver hvordan e-post skal sendes mellom maskiner
            - (behandler post for et domene)
- NS - NameServer
    - navnetjener for et domene, eks ns1.ntnu.no

-

    - NS records er lagret ved domenets registreringsfirma
    - NS finner nameservers til et domene, hvert domene har nameservers
    - vanlig med flere nameservers til backup som fallback
    - "dig ns ntnu.no" i linux gir tilknyttede nameservers
- CNAME - Canonical name
    - faktiske navnet  til et domene fordi domenenavn kan ha alias
- PTR - pointer
    - domenenavnet for reversoppslag
    - omvendt av A og AAAA, gir reverse dns lookup - søke opp navn gitt ip

**Reversoppslag**
**
**

- Sammendrag
    - rDNS
    - standard definert av IETF
    - definert i RFC 1035 som beskriver de forskjellige dns-oppslagene
    - hvordan rDNS funker og bruksområder, eksempler for bruk
- Hvordan funker det?
    - vanlig rDNS bruker PTR-oppslag hvor den nødvendige infoen lagres
    - PTR-oppslag består av info som identifiserer domenet - hovedsak navnet
    - reversoppslag bruker et spesielt domene in-addr.arpa for å gejnnomføre oppslaget, hvor .arpa-domenet brukes til generell internett-infrastruktur, og in-addr-subdomenet spesielt til reversoppslag
    - tjenesten søker opp revers-ip-en etterfulgt av .in-addr.arpa, eks ip a.b.c.d => d.c.b.a.in-addr.arpa
        - returnerer url-en
- hvorfor brukes reversoppslag?
    - ble laget for å feilsøke med eks traceroute og ping
    - brukes av epost for å bekrefte at ip stemmer
    - validering imot phishing/spamming med Forward confirmed reverse dns (FCrDNS)
- må man bruke det?
    - er ikke krav fra IETF, men står i RFC 1912 at alle tjenere som kan nås bør ha navn, og for alle ip-er bør det være PTR-oppslag for god kommunikasjon, for å ungå at tjeneren forbyr forespørsel pga. ingen leg.
- eksempel på bruk
    - nsloookup eller dig

**SMTP**
**
**

- Sammendrag
    - Simple Mail Transfer Protocol
    - standardprotokoll for sending av epost siden 80-tallet
    - videreutvikling kalt extended smtp
        - tillot andre data som vedlegg i tillegg til ascii
    - protokoll som er effektiv, trygg, pålitelig
    - sending mellom klient/tjener, ikke henting

-

    - smtp-sesjon starter med initialisering som avhenger av om det brukes smtp eller esmtp
    - 3 kommandoer utføres i rekkefølge for å sende mail:
        - MAIL, RCPT, DATA
- initialisering
    - klienten etablerer kobling mellom seg og server
    - serveren responderer klienten med velkomstmelding
    - klienten sender kommandoen "HELO" for å begynne sesjon, alternativt "EHLO" for extended
        - gir info om utvidelser serveren støtter
    - 3 kommandoer må nå utføres sekvensielt for å sende mail
- MAIL
    - starter ny epost-transaksjon, og fjerner data som ligger igjen på server
    - "reverse path" i kommandoen er sender sin mail og mottar evt. feilmeldinger
    - returnerer 250 ok eller feilmelding
- RCPT
    - spesifiserer sender
    - kan repeteres ved flere mottakere
    - returnerer 250 ok og lagrer adresse eller 550 eks "no such user"
- DATA
    - starter overføring av melding
    - server svarer 354
    - deretter kan innholdet sendes til server
    - 250 ok returneres
- Avslutning
    - klienten sender QUIT for å avslutte sesjon
    - respons lik 221 ok, avslutter sesjonen
    - dersom sesjon avsluttes ved timeout e.l., må server ikke forkaste aksepterte meldinger

**MIME**
**
**

- Sammendrag
    - Multipurpose Internet Mail Extensions
    - muligjør sending av mer en kun US-ascii-tegn over epost
        - eks bilder
    - utvidelse av smtp
    - gjør sending av epost mer avansert
    - eposter inneholder headerfiler med info om evt. vedlegg etc.
    - us-ascii (7-bit) er standard, men ofte er det behov for 8-bit data
        - base64 brukes som enkel omkoding på binærfiler, eks. bilder
        - muligjør eks. æøå
        - mer datavolum, men slipper ny standard
- MIME og SMTP
    - vanlig epostsystem har ulike protokoller for mottak og sending
    - smtp for sending
    - mime utvider smtp, muligjør sending av vedlegg og bredere tegnsett
- hvorfor brukes mime?
    - smtp ble utviklet for enkle tekstmeldinger
    - mime overtok med teknologiutviklingen som en utvidelse for å unngå fullstendig erstatning
- virkemåte
    - hver mail inneholder headere  om mime-versjon, vedlegg, avsender, mottaker, dato
    - opererer likt som http
    - tegn utenfor us-ascii defineres i headerfelter for å dekode på mottakersiden
- base64-koding
    - mime bruker koding for dataoverføring
    - kan gjøres ulikt avhengig av  datatype
    - base64 brukes for binærfiler
        - eks. bilder, nasjonale tegn
    - tar 3 første byte (3*8=24 bit)
    - leser 6 bit av gangen som gir 4 tegn
    - leser av tabell for hvert tegn for konvertering til us-ascii
    - us-ascii-tegnet overføres med 8 bit