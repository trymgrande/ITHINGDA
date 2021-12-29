---
title: Lenkelaget
updated: 2020-06-02 13:58:24Z
created: 2020-06-01 13:29:10Z
author: gtrym
---

**Lenkelaget**
**
**

- oppsummering
    - lag 2 i forenklet osi
    - delt i llc (logical link control) og mac (media access controll)
    - llc ligger over mac og gir felles grensesnitt mot protokoller på nettverkslaget, skjuler ulike medienes karakteristikker
    - mac gir tilpassing til ulike overføringsmedier med forskjellige elektriske egenskaper
        - overfører pakker mellom nettverkslagene på tilstøtende noder
        - klargjør fysisk sending med innramming osv.
        - kan korrigere fysiske feil
        - mac har mekanikk for å forhindre kollisjon under pakkeoverføring
        - unik adresse
        - mac har ansvar for sjekksum i rammehalen under sending
        - iee802 har protokoller for lan, eks ethernet-protokollsammendrag
- lenkelaget
    - skjuler kompleksiteten sin og protokollene fra nettverkslaget
- llc
    - grensesnitt mellom nettverkslaget og mac
- mac
    - styrer hvordan data er overført mellom overføringsteknoloier (trådløst, kablet, fiber osv)
    - forhindrer enheter fra å overføre samtidig for å unngå kræsj
    - adressen består av 6 heksadesimale tall
    - identifikator for enheter på nettet
    - oppretter rammeodet og rammehalen
        - ramme er form for pakke i lenkelaget
        - rammehodet har mac-adressen og kontroleringsbytes
        - rammehalen har sjekksum for hhele rammen
        - mac og fysisk lag overfører rammen
        - mac aksepterer rammen fra fysisk lag
            - sjekker mac-adresse og sjekksum
        - ramming og avramming krever prosessorkraft
            - bruker nic-kortet
- protokoller
    - ethernet
    - wlan
    - arp
        - oversetter ip/mac ved å be om mac gitt ip

**
**
**
**
**
**
**LLC (Logical Link Control)**
**
**

- sammendrag
    - gjør at flere protokoller kan eksistere samtidig
    - tilbyr flow controll og error management
- beskrivelse
    - lenkelaget ar todelt struktur
        - llc: øverste laget, felles grensesnitt mot nettlaget
        - mac:  grensesnitt mot ulike overføringsteknologier, eks fiber, kablet, trådløst
        - skjuler/abstraherer protokoll mot netverkslaget
    - hensikten er å multiplekse protokoller over mac-laget (kombinere flere signaler til et signal på delt ressurs)
        - sørger for at flere typer pakker kan sendes over samme nettverk
    - flow control
        - begrenser mengden data
        - brukes lite pga. håndtering i transportlaget
    - error management (automatic repeat request)
        - resender pakker
        - brukes lite pga. håndtering i transportlaget
    - llc legger til llc-header
        - 3 felter
            - destination service access point
                - mottakeradresse
            - source service access point
                - avsenderadresse
            - kontrollfelt
                - koble enheter sammen

**mac-adressen**
**
**

- sammendrag
    - direkte maskinvareadresse
    - vanligvis knyttet til nettverkskortet
    - vanligvis unik id, kan vanligvis ikke endres
    - halve adressen viser til produsent (oui - organizationally unique identifier), andre halvdel er unik for kortet
- format
    - adresser på 48 bit
    - vanligvis kolon-hex
    - produsent må søke hos iee registration authoraty for å få oui
    - resten 24 bit fritt
- ulike typer adresser
    - unicast
        - mest vanlig
        - identifiserer enkelt nettverkskort
        - avsenderen i ramme er alltid unicast-adresse
        - brukes for å sende ramme fra node-til-node
    - multicast
        - identifiserer en gruppe noder
        - en node kan ta imot rammer til en gitt adresse ved å behandle alle rammene
        - adresse starter med 1 i første oktett
    - broadcast
        - alltid 12 f-er
        - alle noder er medlem
        - rammer sendt hit når alle noder i broadcasting-domenet
        - alle nettverkskort som mottar, sender opp til os for behandling
    - mac-filtrering
        - prosess i trådløse rutere ment for å motvirke hacking av nettverket
        - ruter godtar kun white-lista mac-adresser
        - kan gå rundt med mac-spoofing

**Ethernet-rammen**
**
**

- sammendrag
    - en del av innkapslingen av pakker
    - første feltet er preamble
        - for å signalisere at man vil sende data og synkronisere klokkene til nettverksenhetene som er involvert
        - 8 byte
    - 2 nyeste feltene: destination mac og source mac
        - 6 byte hver
        - mottaker-avsenderadresse
        - mottakeradresse endres til neste node
    - ethertype/size-feltet
        - 2 byte
        - hvilken protokoll som er innkapslet i nyttelasten/størrelsen til nyttelasten
    - sjekksum
        - 2 byte
        - baseres på alle feltene utenom preamble
- hovedprinsipper
    - en av delene av en pakke
    - utgjøres av de 5 blokker (alle utenom payload)
    - feltene styrer overføringen av data via synkronisering, hvem som sender og hvor pakken skal
- preamble

**CRC-sjekksum**
**
**

- sammendrag
    - sjekksum på lenkelaget kan detektere feil ved dataoverføringer
    - crc: cyclic redundancy check (algoritme)
        - treffsikker med lite overhead
        - vanlig sjekksum-algoritme
        - bruker xor
- hva er sjekksum-feltet i ip?
    - grupperer data som 16-bit-tall og summerer dem
    - inverterer bitverdien til slutt (komplementerer)
    - mottaker beregner sjekksum, og nye sjekksummen bør gi kun 1-ere
    - sjekksummen finnnes i hodet på ip-pakken

**wifi - bss og ess**
**
**

- sammendrag
    - basic service set er gruppen enheter koblet til samme access point ved å bruke samme ssid
    - extened service set er en eller flere bss og den fysiske koblingen mellom disse ap-ene
    - gjør at en enhet enkelt kan bytte mellom ap dersom alle ap bruker ess
- beskrivelse
    - ap kobler fysisk og trådløst nett
    - enheter som kommuniserer direkte uten mellomledd bruker independent basic service set
    - i bss-nettverk, har alle ap unik bssid
    - i ess-nettverk, har alle ap samme ssid

**WPA2**
**
**
**     **

- sammendrag
    - krypteringsprotokoll for trådløse nett
    - ved trådløs tilkobling kan enheter fange opp pakker til andre
    - krypterer derfor
    - bruker 4-way handshake mellom klient og ap
    - klient genererer privat nøkkel for dekryptering
- hva er wpa2
    - wi-fi protected access 2
    - erstattet wep og wpa (forgjenger) med patches
    - brukt med meste av enheter på trådløst nett siden 04
    - alle nye nettverkskort har støtte
- hvorfor har vi wpa2
    - sikre info
    - wpa3 i 2018 med patches, men dårlig kompatibilitet
- hvordan fungerer wpa2
    - 128-bit kryptering og passord under handshake
    - bruker skiftende nøkkel i motsetning til wpa
    - nyere krypteringsalgoritme (aes og ccmp)
- 4whhs
    - ap sender random tall til klient
    - klient bruker tallet sammen med forhåndsbestemt nøkkel, passordet til ap, mac-adressene, eget tilfeldig tall
        - genererer nøkkel
    - klient sender tilfeldig tall og identitetsbekreftelse
    - ap kan generere samme nøkkel som klient og sjekke melding med identitetsbekreftelsen
    - ap sender ny nøkkel med id-bekr. til klient
    - klient sender melding til ap som bekrefter at klient hhar mottatt alle nøklene
- svakheter
    - krack (key reinstallation attacks
    - oppdaget 2017
    - kan lese data sendt av bruker over nettverket uten å knekke passord eller nøkkel
        - poserer som ap, dekoder info
    - oppdaget og nettverk ble oppdatert
    - angriper må være innen rekkeviddet til nettet

**wifi-access - rts/cts og skjult node-problemet**
**
**

- sammendrag
    - enhet med trådløs radio for internettilgang
    - vanligste ap er rutere, repeatere, selvstendige ap
    - skjult node-problem oppstår når noder som vil kommunisere sammen sender data til ap samtidig og kollisjon oppstår
    - problemløsning er signalene request to send og clear to send for å administrere trafikk
- beskrivelse
    - rts og cts er flyt-kontroll-mekanismer benyttet av ieee 802.11
    - om flere noder sender datapakker til ap samtidig kan interferens gjøre at ingen av pakkene kommer gjennom
    - noder for langt unna hverandre kan ikke vite om ap allerede kommuniserer med andre
- virkemåte
    - node a sender rts til b
    - b sender cts dersom klar
    - a sender data til b
    - b sender ack til a
    - andre noder som hører rts, stopper sending fram til cts eller timeout
        - samme for cts inntil ack, timeout ift. forventet transfer-tid med info i rts/cts

**Kollisjonsdomene og kringkastingsdomene**
**
**

- sammendrag
    - kollisjonsdomene beskriver nettverksområde hvor data overføres gjennom delt medium
        - fysiske laget (1)
    - kringkastingsdomene er logisk inndeling av et nettverk hvor alle noder kan motta data
        - hovedsakelig i nettverkslaget (3), noen enheter kan gjøre dette i lenkelaget (2)
    - kollisjonsdomene
        - nettverksområde hvor alle enheter er fysisk sammenkoblet, eks hub/trådløs kom.
        - fysiske kollisjoner er uaktuelle pga. svitsjer, kun eldre nett
        - datahastighet lik for alle enheter fordi kun 1 får sende om gangen
        - carrier sense multiple acess with collision detection
            - for å starte overføring:
            - lag pakken
            - send om mediet er ledig
            - observer om kollisjon oppstår
            - håndter kollisjon om nødvendig
            - nullstill pakkegjentakelsesteller, fullfør pakkeoverføring
            - håndtere kollisjon:
            - fortsett overføring med støysignal i stedet for pakkehode/data/crc til minimal pakketid er oppnådd for å forsikre at alle mottakere ved om kollisjjonen
            - øk pakkegjentakelsesteller
            - avslutt overføring hvis maks gentakelser er nådd
            - regn ut tilfeldig back-off-periode basert på antall kollisjoner
            - tilbake til håndteringsstart
        - trådløse kollisjonsdomener bruker også cms/cd, men skjult-node-problemet oppstår da
    - kringkastingsdomene
        - flere kollisjonsdomener avgrenset med svitsjer
            - enheter koblet til samme repeater/svitsj er i samme kringkastingsdomene
        - ligger i lan-område, rutere osv. avgrenser kringkastingsdomenene ved å ikke videresende kringkastingstrafikk
        - forskjellen mot kollisjonsdomene
            - pakker blir analysert av svitsjen og videresendt kun til området som fører til adressen i pakken
            - blir sendt ut til alle
            - kollisjonsdomener er mindre, ligger inni kringkastingsdomener

**Svitsjer**
**
**

- sammendrag
    - nettverkskomponent på lenkelaget (2)
    - svitsjen kobler sammen enheter på lan ved å assigne porter og sender pakker til riktig mac-adresse
- beskrivelse
    - sørger for kommunikasjon mellom enheter på samme nett
    - hub mottar og sender videre på alle porter, svitsj sender kun til riktig mottaker
        - kommunikasjon blir ikke kringkastet
        - forhindrer kollisjon
        - bedre ytelse
        - sikkerhet
    - lagrer mac-adresser med porten pakken kom fra i tabell
    - ved ukjent mottaker, blir pakken kringkastet til alle porter
    - 2 pakker til samme mottaker sendes sekvensielt uten kollisjon
    - flere typer svitsjer
    - flere ved større nett
    - svitsjer bruker lenkelaget, rutere nettverkslaget
    - lag 3 switch/multilayer switch har ruterfunksjonalitet integrert
        - hjemmerutere er rutere med integrert svitsj

**Transmisjonsmedier**
**
**

- sammendrag
    - 3 typer
        - fiber
            - optisk, glass-/plastsylindere
            - lys
            - raskt (C)
        - kobber
            - elektrisk
            - billig
        - luft
            - krever ingen kabel
    - fiber
        - lyset går langs aksen med totalrefleksjon
        - kjernen i fibrene har belegg med mindre ledeevne, så lyset ikke går ut av kjernen
        - tykkelsen på kjernen gir forskjellig karakteristikk
    - kobber
        - gode egenskaper
            - levetid, styrke, korrosjonsresistanse, billig
        - elektriske signaler som pulser
            - jevner seg ut på distanse, dårlig range
    - luft
        - ulike bølgelengder, ulik rekkevidde
        - wifi: 120nm 2.4ghz, 60mm 5ghz
        - kort rekkevidde pga. mye korter bølger enn fiber og kabel
        - krever ikke kabel
        - omnidireksjonelt felt m/ kanalmultipleksing
            - flere mottakere per sender

**Ping**
**
**

- sammendrag
    - nettverksprogram som sjekker koblingen mellom to noder i nett
    - sjekker svartid til node
    - benytter icmp-protokollen
- hva er ping?
    - verktøy for å teste node-kobling på ip-nett med ekko
    - sender ping-request, får ping-response
    - kan være flere noder imellom
    - utbredt kommando
    - viser info om koblingen
        - svartid
        - pakketap
        - altså hastighet og kvalitet på kobling
- hvilken oppgave løser ping
    - sjekke om kommunikasjon er mulig
    - sjekker svartid
    - feilsøking i nettverk
    - speed checker
- hvordan løses dette
    - internet control message protocoll
        - nettverkslaget
        - sjekker kommunikasjon mellom noder
        - brukes i ping og traceroute
        - sender ikke data, men type, kode, sjekksum, timestamp osv.

**Nslookup**
**
**

- sammendrag
    - name server lookup
    - for å gjøre spørringer mot  dns-tjenester
    - vil få ut navn og adresse på server man slår opp avh. av type
        - enten ipv4, ipv6, mail, navneserver
- virkemåte
    - nslookup -type=example vg.no
    - non-authorative svar betyr at dns-serveren ikke er riktig tjeneste, men vet hvor den er
    - spesifisere dns som skal brukes
        - nslookup [domeneurl] [dns server ip]

**Ipconfig**
**
**

- sammendrag
    - brukes uten parametre for å se egne ip-er, nettmaske, og ruture (default gateway)
    - kan brukes med flere parametre
        - /all
            - kort beskrivelse av tcp/ip-config for alle net.kort
        - /release
            - frigir dhcp- og ip-konfig for alle net.kort
        - /renew
            - fornyer dhcp-konfig for alle net.kort

**Traceroute**
**
**

- sammendrag
    - sporer banen en ip-pakke tar
    - diagnosere nettverksfeil, ser hvor pakken stopper
    - overfører pakker med små ttl-verdier (time to live)
    - identifiserer alle besøkte noder
- virkemåte
    - tracert

**inSSIDer**
**
**

- sammendrag
    - wi-fi-scanning
    - info om hvert enkelt nettverk
        - rssi, sikkerhet, maks ytelse, kanaler
    - brukes til å optimalisere og feilsøke wifi

**wireshark localloop**
**
**

- oppsummering
    - trafikk med samme maskin som sender og mottaker
    - loopback er interface uavhengig av maskinvare og nettverk
    - alltid samme adresse, alltid tilgjengelig
- hvilken oppgave løser det
    - teste distribuerte systemer som kjører lokalt, eks http-server
    - localhost ipv4: 127.0.0.1
- hvordan løses det
    - virtuelt interface