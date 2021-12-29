---
title: Nettverkslaget
updated: 2020-06-01 13:28:55Z
created: 2020-05-31 09:38:15Z
author: gtrym
---

**Nettverkslaget**
**
**

- sammendrag
    - 3. laget
    - ip
- nettverkslagets oppgaver
    - hovedansvar er ruting
        - bestemmer rute fra sender til mottaker
        - deler opp pakker
    - maskiner opererer i nettverk, et nettverk har samme nettverksadresse mot internett
    - rutere ruter pakker og kobler sammen 2+ nettverk (hjemme- og internett)
- ip
    - forbindelsesløs pakkeleveringstjeneste
    - hver pakke sendt uavhengig og uten kvittering (upålitelig)
    - logisk overføring, bryr seg ikke om transportmiddel osv.
    - brukes til adressering
        - adresser blir abstrahert med dns

**
**

**IP-nett**
**
**

- sammendrag
    - en mengde noder med felles nettadresse
    - nettadressen avgjøres av ip-en og nettmasken
    - alle noder i et ip-nett kan direkte kommunisere med hverandre mellom nettverkskort uten å passere ruteren
    - ip-nett  må ha ruter for å kobles til internett
- kjennetegn for ip-nett
    - sentralt for datakommunikasjon
        - all datatrafikk skjer over ip-nett og rutere
    - samling maskiner med lik nettadresse
    - maskiner på samme nett kan kommunisere uten ruter
    - ruter har minst 2 lan-porter med hvert sitt ip-nett
        - ruter binder sammen nettverkene
- adressering
    - ip-adresse er unik for hver enhet
    - skrives som fire heltall atskilt med punktum
        - hvert heltall: 0-255 - 32 bit
    - inni ip-en ligger nettadressen (nett-id) og nodeadressen (host-id)
    - nettmasken sier hvor skillet mellom nettadressen og nodeadressen er
        - lengde på nettmaske = nettadressen
    - finner nettadressen til nodene på ip-nett med logisk og
- ip-protokollen
    - upålitelig protokoll - sender ikke kvittering
    - opererer forbindelsesløst
    - ipv4 mest populært atm
        - tar tid pga. hardwarestøtte, spes. hos isp-er
    - ikke x-compatible, nye systemer støtter derfor begge
    - for store pakker som sendes gjennom en ruter blir fragmentert i ruter ved å endre id-, flag-, fragmentering-bitene i header på pakken

**IPv4-adressen**
**
**

- sammendrag
    - adresserer på 32 bit => 4 mlrd. adresser
    - finnes private reserverte nett som ikke kan aksesseres av andre
- ipv4-adressen
    - bruker best effor delivery
        - prøver å sende alt, men garanterer ikke
        - sjekker vil skje i andre lag
- nettadresse (nett-id)
    - logisk og på ip og nett-masken
- nodeadresse
    - flere bit til nettadressse gir færre bit til nodeadresse
    - nettadresse + nodeadresse = 32 bit
    - to reserverte nodeadresser
        - kun 0 refererer til selve nettadressen
        - kun 1 er ip-kringkastingsadresse på dette nettet
        - ruterkobling (default gateway) for ip-nett skal alltid ha laveste nodeadresse (nodeadresse +1)
- nettmaske
    - hvor lang offset skillet er
    - nettmasken maskerer nodeadressen med og
    - brukes for å sjekke tihørighet til samme ip-nett
- reserverte adresser
    - private  nettverk

**IPv4-pakkehodet**
**
**

- sammendrag

    -

- ipv4-pakkehodet
    - info om ruting og levering
    - format, innhold, adresser...
    - 20-60 byte
    - 13 felt
- sentrale felter
    - 4-bit versjon
        - versjon av ip (v4 eller v6)
    - 4-bit headerlengde
        - antallet 32-bit-grupper i headereren
    - 7-bit type of service (TOS)
        - 3 bit ubrukt, 4 bit tos, 1 bit 0
        - 4 bit tos brukes til å velge 1/4 tjenester
            - minimere forsinkelse
            - maksimere gjennomstrømming
            - maksimere pålitelighet
            - minimere kostnader
    - 16 bit totallengde (byte)
        - lengden til hele ip-pakken i byte
    - 16 bit id
        - id for hvert fragment av pakke
    - 3-bit flagg
        - for å fortelle at pakkene er fragmentert og identifisere fragmenter
    - 13-bit fragmentering
        - identifiserer hvert enkelt pakke-fragment
    - 8-bit time to live (ttl)
        - tall for maks antall rutere pakken kan passere
        - for å unngå at pakker sløyfer i nettverket
    - 8-bit protokoll
        - eks udp eller tcp
        - benyttes på mottakersiden for de-multipleksing (mottaker kan fordele nyttelast uten å se i pakken)
    - 16-bit headersjekksum
        - sjekksum kalkulert fra headerfeltene
    - 32-bit avsenderadresse
    - 32-bit mottakeradresse
    - tilleggsinfo (lite brukt)
        - tilleggstjenester

**IPv6-adresse**

- sammendrag
    - utvidet adresserom fra 32 til 128 bit
    - forenkling av pakkeheader
        - fast størrelse på 40 byte
    - avsender ansvarlig for pakkefragmentering
    - kan legg inn ekstra info i utvidede pakkeheadere
- adresseformatering
    - 128 bits heksadesimal
    - 8 grupper 16 bits hver separert med ":"
    - nettadressen er første, nodeadressen er resterende

**IPv6-pakkehodet**
**
**

- sammendrag
    - 2^128 adresser
    - pakkeodet kun 2x større
    - fast hode og alternative utvidelseshoder
    - faste hodet har kun nødvendig info for at protokollen skal funke
    - all annen info legges i utvidelseshode
    - hvert utvidelseshode har unik verdi
    - next-header-feltet i fast hode peker på første utvidelseshode
    - next-header-feltet i første utvidelseshode peker på neste header osv.
- fast hode
    - versjon
    - traffic class
    - flow label
    - payload
    - next header
    - hop limit (samme som ttl)
    - source address
    - destination address
- utvidelseshode
    - hodene er linked list
        - slutter med upper layer data (hodet fra transportlaget (første hodet i nyttelasten))

**IPv6 options**
**
**

- oppsummering
    - wtf
    - 2 av utvidelseshodene
        - hop-by-hop options header
        - destination option header

**CIDR**
**
**

- oppsummering
    - metode/notasjon for å tolke ip-adresser
    - kjennetegnes med /xx på slutten
    - tidligere var klasser brukt
        - 5 ulike klasser: a-e
        - lite valgfrihet
    - gjør det enklere å dele opp ip-er i mindre nett
    - kan deles i 32 forskjellige "klasser", som er bedre enn 5
- problemet med classfull ip addressing
    - mange ip-er i klasse a og b blir bortkastet
- cidr
    - en notasjon for å notere hvor i ip-en nettadressen finnes
    - bruker variabel lengde på submasken
        - mer effektiv bruk av adresserom
    - fordel: slå sammen prefix i ip (prefiksaggregering)

**Subnetting - hvordan et adresserom kan deles i ip-nett**
**
**

- sammendrag
    - logisk oppdeling av ip-adresserom i mindre, separate ip-nett som skilles med nettadresse
    - ønsker å bryte ned til mindre nettverk
        - håndterbart og effektivt
    - letter på opphopning i nettverk, forbedre ytelse, bedre sikkerhet
    - enheter i samme subnet har samme nettadresse
        - kan kommunisere sammen
    - oppdeling av nettadresse deles i subnett med subnettmasken
        - subnetting er standardisert
        - et av målene er å identifisere om en ip-adresse er i samme subnett som en annen
            - for å håndtere sending av pakker enten internt eller over internett
                - øker effektivitet
    - offentlig og privat adresserom
        - offentlige er rout-bare på internett

**Rutere**
**
**

- sammendrag
    - ruter data til rett sted effektivt
    - trafikkerer data optimalt
    - bruker rutingtabell for å sjekke ledige ruter
    - forwarding table for de beste rutene for lett tilgang
- virkemåte
    - ved pakkemottak gjøres 5 operasjoner (for ipv4, ipv6 bruker eks. ikke sjekksum)
        - kontrollere sjekksummen i ip-pakken
        - sjekke om pakken er til denne ruteren eller skal videresendes
        - sjekke ttl og beregner ny sjekksum
            - ttl -1 for hver ruterpassering
            - ttl=0 betyr forkast og returner icmp-melding
        - leser mottaker-ip og slår opp rutingtabellen for å finne videre vei
        - sjekker str. på pakken og fragmenterer dersom nødvendig
            - hvis veien videre er mindre
    - rutingtabell
        - består av
            - nettverksmål
            - nettverksmaske
            - gateway
                - adressen der datapakken må videresendes til - nettverkskort eller nærmeste ruter
            - grensesnitt
                - adressen til nettverkskortet som datapakkene må passere
            - metrikk (m)
                - antall hopp til destinasjonsnettverket
            - kan se egen rutingtabell med "route print"
    - forward tabell
        - finne ut hvor pakken bør videresendes
        - mer destillert versjon av routing tabellen
        - kun nødvendig info for hvor pakker skal sendes for rutene som faktisk brukes
        - ikke alle ip-adresser, kun info om selve nettverket, ip-adressens prefix

**Autonome systemer (as)**
**
**

- sammendrag
    - system av ip-rutingsprotokoller som benyttes av administrerende enheter (isp-er eller andre datasystemer) til å rute virksomhet på internett
    - tusenvis av autonome systemer i samarbeid om å rute pakker til destinasjonen
    - hver aktør som har autonome systemer må ha autonomous system number (ans)
    - hvert system på internett bruker protokollen bgp (border gateway protocol) til å reklamere for ip-ene de har en rute til
        - gjør at andre også får tilgang til adressen gjennom gitt system
    - rip og ospf er to rutingprotokoller som finner beste rute på hver sin måte
    - rip er enkel og gammel protokoll for mindre nettverk
    - ospf er forbedret versjon av rip for større nettverk
- border gateway protocol (bgp)
    - alle systemer benytter seg av bgp
    - funker ved at alle systemer periodisk kringkaster sin rutingtabell til naboene
    - slik kan alle noder bygge opp sin egen tabell
    - hver rutingtabell vil da ha info om hvilke noder systemet har "oppdaget"
- ospf vs rip
    - rip finner beste rute ved å telle antall hopp ti destinasjonen
    - ospf kalkulerer billigst pris (båndbredde)
    - ospf har ingen hopp-grense, rip har max 15
        - rip funker kun i små nettverk
    - ospf er bedre i større nettverk, og som regel raskere

**NIX - norwegian internet exchange**
**
**
**    **

- sammendrag
    - norges største internet exchange point
    - hovedoppgaver er å la 2 eller flere nettverk kommunisere direkte med hverandre
- oppgaver
    - fleste as i norge er koblet til nix
    - er en svitsj, tidligere plassert i oslo
        - nå flere punkt rundt i norge for bedre kapasitet og sikkerhet
    - flere ulike as kan ha forbindelse mellom seg uten å gå gjennom nix
    - et as kan kommunisere med et annet gjennom et transittnettverk
    - økonomiske interesser ved avtaler om utveksling mellom as (peering)
    - nix baserer seg på prinsippet om at man kan gjøre en avtale uten betaling hvis to system har like mye trafikk ut og inn - hvis man vil koble seg til transittnettverk, må man betale
    - opererer som utvekslingspunkt for ip-samtrafikk i norge
    - fleste isp i norge bruker dette for å distribuere sine tjenester til brukere i norge med god tilgjengelighet og hastighet
    - tilbyr datautveksling for store kringkastingsselskaper som nrk og tv2
    - kort sagt største utvekslingspunkt for datatrafikk, opererer som alternativ for mellomlaget mellom systemer når de skal kommunisere
- virkemåte
    - 6 knutepunkter i norge

**VPN (virtual private network)**
**
**

- tillater tilkobling til private nettverk til tross for ikke-direkte tilkobling (tunnel)
    - brukes for å utnytte tjenester som tilbys på gitt privat nettverk geografisk atskilt
- ingen standard, heller konsept
- 3 løsninger
    - IPSec (IP secutiry)
    - tunnelering av trafikk mellom virtuelle netverkskort
    - ssl-vpn (uekte vpn, ssl-kryptert web-proxy), oppnår noe av samme funksjonalitet
- beskrivelse
    - hovedprinsippet er at en klient utgir seg for å være noen andre ved å introdusere et lag i kommunikasjonen
    - 3 aktører: klient, server, nettsiden/tjeneste
    - klient sender pakker til vpn server som videresender, som blir ny virtuell klient med ny lokasjon
- bruksområder
    - kobling til private nettver til tross for geo-separering
    - autoritære land der staten har blokkert deler av internett
    - blokkert innhold grunnet komplikajsoner med opphavsrett (netflix)
    - sikkerhet uten https
        - gir https ut fra klient for å hindre sniffing av linja, og trafikk kan ikke spores til klient
- vanlige vpn-teknologier
    - ekte vpn-er (ipsec og tunnelering) krypterer på os-nivå og videresender kun til riktig mottaker
    - ipsec bruker konfigurering med nøkler
    - enklere med tunneling, en del av fordelene
        - udp/tcp med virtuelle nettverkskort med egen ip
    - ssl/proxy er middle man
        - ikke like sikkert, men netflix funker

**Sockets**
**
**

- sammendrag
    - endepunkt i toveis-kommunikasjon
    - grensesnitt mellom program og protokoll-stakk
    - basically kombinasjon av ip og port
    - overføringen administreres av tcp
        - tilbyr ende-til-ende-kom.
        - avsender får beskjed om mottatt melding
- socket
    - alternativer: udp, tcp, tcp/ip (brukes mest)

- hvordan sockets fungerer mellom tcp og appen
    - bruker ip/port for at tcp kan adressere hver ende

**DMZ (demilitarisert sone)**
**
**

- sammendrag
    - fysisk og logisk skille mellom delene av en virksomhets datanettverk som yter tjenester mot nettet
    - brannmur-sandwich med servere
- hensikt
    - eksponerer ikke bedriftens tjenester mot kundene
    - scenario 1: epost og web-server på lokalnettverk
        - alt innenfor sonen
    - scenario 2: epost og web-server utenfor lokalnettverkets brannmur
        - server flyttes ut, server-kall kan ikke føre til breach
    - scenario 3: 2 brannmurer
- moderne dmz
    - vanlig: epost og webserver i internnett, edge-server eller reverse proxy for kom. mellom dmz og internnett
        - proxy stopper angrep og abstraherer serveren

**NAT**
**
**

- sammendrag
    - oversette interne ip-er til eksterne offentlige adresser
    - gjør at (hostile) kommunikasjon ikke kan startes utenfra
    - ved serverkall vil nat oversette ip-en din fra intern til offentlig
        - så skjønner ruteren hvor svare skal
    - gjør at store lokalnett kan ha felles nettadresse
    - metoder innen nat
        - statisk nat, dynamisk nat, overloading, overlapping
    - metoder for oversetting av ip-er
        - statisk nat
            - hver enkelt adresse/maskin blir oversatt til samme hver gang
                - dersom maskinen skal være offentlig tilgjengelig, eks webserver
            - dynamisk nat
                - ogås 1-til-1 oversettelse, men forskjellig ip hver gang
            - overloading
                - flere interne ip-er får samme eksterne med forskjellig port
                    - øker sikkerhet og størrelse på nettet
            - overlapping

    - ipconfig viser egen intern ip
    - curl ipconfig.co gir ekstern ip
    - hvofor brukes nat
        - 32-bit begrensning i ipv4 gir ikke nok adresser, løses ved 1-til-flere

**
**
**
**
**ARP (address resolution protocol)**

- sammendrag
    - finne nodes mac-adresse gitt ipv4-adresse
- virkemåte
    - en node på lokalnettet bruker ip og vil ha mac-en ti mottaker
    - sender sender arp-pakke med ipv4 til mottaker
    - mottaker sender svar med egen mac
    - kommunikasjon er begynt
    - mac ønskes fordi det brukes i adressering i lokalnett
    - nettverkshost har buffer med ip/mac så arp-forespørsler slippes ved treff
    - arp -a viser arp-cace som kan modifiseres