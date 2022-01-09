---
title: forelesning 1 algoritmer og datastrukturer
updated: 2019-08-19 08:21:49Z
created: 2019-08-19 06:30:06Z
author: trym.grande@gmail.com
---

- kompleksitetsanalyse
    - kjøretid(datamengde)
        - best/worst case med gitt data/hardware/software
            - ikke nødvendigvis lineær forhold mellom tid/data
- tidskompleksitet
    - antar at hver enkel operasjon tar ca. like lang tid, regner ut eksekveringstid fra det
    - lineært ved kun en løkke, kvadratisk ved 2 etc.
- asymptitisk analyse
    - ser på tilfeller ved n -> uendelig
        - teller kun antall løkker/metodekall
        - forenkler gjerne uttrykket til det dominerende leddet
        - mest interessant med graden av n
- matematikken bak asymptitisk analyse
    - øvre grense (O)
        - interessant fordi det sier maks. tid
- måleteknikk
    - minst 3, helst 4-5 verdier for n
    - varier n systematisk f.eks. n = 10k, n = 33k, n = 100k
    - kjør målinger flere ganger, unngå avvik (eks. bakgrunnsprogrammer)
    - ikke utskrift/grafikk/filbehandling inni målingen
    - skriv ut verdier til slutt, etter målingen
    - for små n gir avvik
    - for stor n kan gi avvik i form av swapping
    - maskinklokka har ikke nok oppløsning for korte kjøringer
- korte kjøringer
- ikke gi klassen navn etter algoritmen. algoritmer er metoder
- klasser får navn etter innhold av data, ikke hva vi kan gjøre
- nedtelling kan bruke rekursjon istedenfor løkker
- overhead i løkker er mindre enn ved rekursjon
- bruker rekursjon når metoden kaller seg selv mer enn en gang
- (skriv ned generell formel til eksamen)

-