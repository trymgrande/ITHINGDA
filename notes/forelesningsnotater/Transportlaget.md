---
title: Transportlaget
updated: 2020-06-03 17:38:27Z
created: 2020-05-30 10:07:28Z
author: gtrym
---

**TCP**
**
**

- Sammendrag
    - Protokoll på transportlaget som sørger for pålitelig overføring for applikasjonslaget
        - oppnås ved kvitteringer og tilstandsfull forbindelse
    - sender meldinger fra applikasjon til rett mottaker
    - sørger for rett innhold uten bitfeil og tillater flere applikasjoner kjørende samtidig
    - forskjellige tilstander i protokollen
        - ledig, oppkobling, dataoverføring, nedkobling
    - 3 flagg og 2 tellevariabler for å holde pålitelig overføring
    - kan overføre store datamengder fra applikasjonen i data-segmenter
        - krever buffer hos sender/mottaker
- egenskaper
    - pålitelig - pakkene kommer i riktig rekkefølge
        - krever kvitteringer
            - tcp må være tilstandsfull
                - tilstander: ledig, oppkobling, dataoverføring, nedkobling
                    - 3 flagg: syn, ack, fin
                        - syn: synkronisering av oppkoblingsdata mellom avsender og mottaker
                        - ack: pakkehodet inneholder tellende kvittering
                        - fin: nedkobling av forbindelse
            - kvitteringer trenger sekvensnummer og kvitteringsnummer
                - sekvensnummer gir posisjon til første byte i pakkens nyttelast
                - kvitteringsnummer er posisjon til siste byte +1 (neste sekvensnummer)
    - punkt til punkt-forbindelse - går data to veier - ingen multi
    - bitfeil kontrolleres med sjekksum, sender ny pakke ved tap
    - full duplex - 2-veis samtidig
    - addressering for kjøring av flere applikasjoner på samme maskin
    - 16 bit portnummer gir 64k adresser
    - kjente porter: 0-1023
    - systemproter: 1024-49151
    - dynamiske kortlevde porter: 49152-65536 (nettleser)
    - kjente på tjenersiden
        - http: 80
    - pakkehodet inneholder sender- og mottakerport
- bruk av tcp
    - tryggere, men tregere
        - eks. mail
        - ikke spill, udp brukes her
- opprettelse av forbindelse
    - 3-way handshake
        - P1 sender pakke med syn-flagg til P2 for å prøve å lage forbindelse
        - P2 returnerer syn-flagg for å danne forbindelse dersom P2 er ledig, i tillegg til ack-flagg som kvitterer for å ha mottat syn
        - P2 returnerer ack-flagg for å kvittere tilbake

**Oppkobling 3WHS**

- Sammendrag
    - P1 ber om oppkobling SYN
    - P2 bekrefter med SYN-ACK
        - ack bekrefter og syn ber om oppkobling andre vei
    - P1 bekrefter oppkobling i dens retning
    - tekker for sekvensnummer er satt, sende-mottakerbuffere opprettet
- transportlaget
    - logisk ende-til-ende forbindelse mellom program på forskjellige maskiner
    - må opprettes med handshake
    - 2 måter å sende data:
    - tcp: connection oriented
        - pålitelig, alle pakker blir levert riktig
        - sender videre til ip, som sender til tcp i andre enden
        - ledig
        - oppkobling:  handshake
        - dataoverføring: kontinuerlig byte-strøm
        - nedkobling: fin-flagg sendes
    - udp: connectionless
        - upålitelig, ingen garanti
        - ingen tilstand om meldinger lagres hos avsender
        - ingen handshake
        - raskere
- etablering av forbindelse
    - forberedelser
        - allocate buffer
        - kontrollvariabler initialiserers
            - holder styr på hvilde data som er sendt og mottatt
            - 3whs

**Pålitelig dataoverføring**
**
**

- Sammendrag
    - telle bytes
        - sendt (sekvensnummer)
        - mottatt (kvitteringsnummeret)
    - sjekknummer, tellere, timere
    - pakker sendes på nytt ved feil
- hovedprinsipper
    - hva har kommet fram?
        - nummerere data, bruke sjekksum, timere, kvitteringer
        - tilstandsfull forbindelsesorientert kommunikasjon gir påliteligheten
        - de 4 fasene sørger for at begge parter har kontroll på dataoverføringen
- telling
    - flagg og tellevariabler i pakkehodet med data sendt, mottatt, underveis
    - sekvensnummeret er en tellervariable som viser posisjon til første byte i pakkens nyttelast
        - første sekvensnummer er til feldig tall på 4 byte generert ved oppkobling (ISN - Initial Sequence Number)
        - grunnlaget for å kunne returnere kvittering
    - kvitteringsnummeret viser neste sekvensnummer som forventes
        - lagres som siste gyldige byte som mottas + 1
        - ack-flagget indikerer om det er tellende kvitteringsnummer (acknowledgement)
- hvordan feil oppdages og håndteres
    - avsender sender på nytt ved timeout (retransmisjon)
    - timer settes ut ifra Round Trip Time (RTT), bør være litt lenger

**Nedkobling**
**
**

- Sammendrag
    - gjøres for å frigi ressurser og unngå låsing
    - ved 2 par fin-ack-pakker kalt 4-way handshake
- Beskrivelse
    - bør koble ned før sende-vindu er tomt for å unngå låsing
    - fin og ack kan sendes samlet for å spare båndbredde

**TCP pakkehode**
**
**

- Sammendrag
    - holde styr på
        - tilstanden til kommunikasjonen
        - dataoverføringen mellom endepunktene
    - flere (10) felt i pakkehodet
        - kilde- og destinasjonsport
            - hvor pakken kommer fra og skal
        - sekvensnummer
            - hvor mye data er sendt og bekreftelsesnummer for handshake
        - flere flagg for f.eks. høy prioritet
        - vindusstørrelse
            - hvor mye data mottakeren kan ta imot
        - sjeksum
- oppgaven til pakkehodet
    - holde styr på tilstanden til kommunikasjonen mellom tcp-endepunktene
        - ved å lagre portene til hver øverst i pakkehodet
    - holde oversikt over dataoverføringen ved å bekrefte med 3whs
- lengde på pakkehode
    - feltet offset (DO) forteller lengde på header
    - kan brukes til å hoppe rett til dataen
- RSV og tcp-flagg
    - rsv (reserved) ubrukt felt for å skille andre felt
    - flagg som kan brukes
        - urg: urgent høy pri
        - ack: bekreftelse på pakke mottatt
        - psh: pus - høy pri
        - rst: reset - avsender har reseta tilkobling
        - syn: sync - for å utføre 3whs og sette sekvensnummer
        - fin: finished - koble ned
- vindusstørrelse og urgent pointer
    - vindufeltet er flagg for antall bytes mottaker kan motta
    - urgent pointer er ofte ignorert, men brukes hvis urg-flagget er satt, og sier hvor urgent dataen slutter
- feildeteksjon med sjekksum
    - summerer et tall med dataen og inverserer den
    - mottaker gjør samme utregning og sjekker om summen er lik

**Portadresser**
**
**

- Sammendrag
    - logisk forbinde mellom to programmer
    - kalles portnummer i tcp og udp
    - 3 hovedgrupper: velkjente, registrerte, kortlivde
    - kommunikasjonen foregår med forespørsler av pakker med data og metadata
    - transportlaget gir pakker videre til riktig prosess i applikasjonslaget basert på portnummeret i forespørselen
    - portaddresse er assosiert med en ip
    - kun 1 prosess kan knyttes til en ip-port-kombinasjon (socket)
    - portnummeret er 16-bits heltall (0-65535)
- beskrivelse
    - portadressene brukes kun dersom en applikasjon kjører/lytter på den
    - brukes for å skille mellom prosesser for hver innkommende pakke
    - portnummer til avsender og mottaker ligger i hver sine felt i tcp-pakkehodet
    - gir en socket kombinert med ip-en, som er et endepunkt i ip-kommunikasjon
    - portnummer skilles i 3 typer av (IANA) som skal holde oversikt over offisielle porter
    - 3 typer portnummer:
- velkjente/systemporter (0-1023)
    - tjenersiden
    - krever su for bruk
    - eks. FTP, HTTP, HTTPS
- registrerte porter (1024-49151)
    - ikke like strengt
    - tilgjengelig uten su
    - eks. battle.net chat, openvpn
- kortlivde/dynamiske/private (49152-65535)
    - klient
    - opprettes etter behov
    - dør etter kommunikajsonen

**Digitalt sertifikat**
**
**

- sammendrag
    - samling troverdige opplysninger med tilstrekkelig info til å kommunisere pålitelig
    - opplysninger om bl.a. navn på innehaver, offentlig nøkkel. signering fra sertifikat-autoritet, etc.
    - offentlig nøkkel brukes for å kontrollere at privat nøkkel er ekte
    - når man sjekker for gyldighet, vet man at vedkommende er riktig person og innholdet er uendret
    - brukes for digital signering
    - digital signatur kommer i tillegg til dokumentet og inneholder kryptert sjekksum og digitalt sertifikat
    - brukes for å bevise legitimitet for netsider og e-post
    - brukes for plastkort eller underskrifter
- beskrivelse
    - fleste sertifikater blir validert av en 3.part kalt certificate authority (CA)
        - tilbyr flere typer sertifikater
        - forskjellig priser for ulike prosedyrer
        - NKOM kontrollerer utstedere
        - alle CA har egne sertifikater, med unntak av høyeste ranken som har rotsertifikat
        - rotsertifikater er ofte installert i nettlesere  for å forenkle kontrollering
    - bruker har ansvar for utløpsdatoen
    - mest vanlige standarden er X.509
        - leses av fleste programmer
    - PKI (Public Key Infrastructure) er rammeverk for å lage, bruke, lagre, distribuere, trekke tilbake sertifikater
        - binder sertifikater og brukere
    - land og organisasjoner kan ha sin egen PKI
        - norge har eks. BankID, ikke helt PKI
    - sjekksummen (avtrykket) brukes for å sjekke ektheten til sertifikatet
    - beregnes med fellesnøkkel til utsteder fra rotsertifikat

**TLS**
**
**

- Sammendrag
    - tidligere SSL
    - for trygg kommunikasjon, utføres handshake
        - eniger om parametre og algoritmer
            - eks. krypteringsnøkler
    - server identifiserer seg
    - sesjonsnøkler blir opprettet
- ssl til tls
    - tildligere kunne man nedgradere fra tls til ssl
        - exploit kalt "poodle attack"
- hvordan tls fungerer
    - klient og tjener blir enige om å bruke tls
        - enten ved egen port for ssl, eller forespørsel om å bruke det
    - handshake
    - tjener velger sikreste valg felles tilgjengelig
    - tjener sender digitalt sertifikat
    - klient krypterer tilfeldig tall med offentlig nøkkel og sender til tjener
        - brukes til nye nøkler for kryptering videre
- tls i applikasjonslaget
    - tls ligger mellom applikasjonsprotokollene og transportprotokollene i en applikasjon
    - applikasjonsprotokoller blir brukt i applikasjoner til å utveksle info mellom klient og tjener
        - blir gjort gjennom req/res-kall
            - eks http
    - tcp er en mye brukt transportprotokoll
    - tls kommer inn i mellom disse
    - tls kan brukes med hvilken som helst annen protokoll for å sikre kryptering
    - ofte brukt med http, kalles da https
        - markeres i url

**UDP**
**
**

- sammendrag
    - User Diagram Protocol
    - transportprotokoll
    - upålitelig
    - ingen verifikasjon
    - ingen forbindelse til mottaker
    - rask, brukes til sanntid
    - lite (8 byte) simpelt pakkehode gir ytelse
- beskrivelse
    - ip-datapakke
    - enkelt grensesnitt mellom nettverks- og applikasjonslaget
    - legger til applikasjonsmultipleksing og sjekksum på toppen av en ip-pakke
- pakkehodet
    - fire 2-byte deler
        - avsenderport (ikke påkrevd)
        - mottakerport
        - lengde: lengden på hele pakken
        - sjekksum: kreves for IPv6
- bruk
    - brukes for å redusere bruk av maskinressurser, som ved multicasting eller sanntidsoverføring
        - eks spill (støtter pakketap), gruppekommunikasjon (pakkesvitsjing), strømming (pakketap)
        - nettverksapplikasjoner:
            - dns
            - network time protocol
            - dynamic host configuration protocol
            - simple network management protocol
            - routing information protocol
            - vpn (feilhåndtering på app-nivå)