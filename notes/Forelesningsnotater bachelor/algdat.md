---
title: algdat
updated: 2019-09-02 08:13:05Z
created: 2019-09-02 06:28:53Z
author: trym.grande@gmail.com
---

- dobbeltlenka liste har referanse til neste OG forrige element
    - ulempe: tar litt mer plass med bakover-referanser i tillegg
- lenka liste
    - legge inn fremst: O(1) i motsetning til objektliste, som må forskyve alle elementer
- binærtrær
    - traværsering
        - pre: noden, venstre subtre, høyre subtre
        - post: venstre subtre, høyre subtre, noden
        - inordentraversering: venstre subtre, noden, høyre subtre
        - nivåorden: rota, barna, barnebarna...
    - sletting - spesialtilfeller
        - ingen barn - kan fjernes uten problemer
        - ett barn - barnet flyttes opp til noden som slettes
        - to barn - noden fjernes ved å erstatte dden med neste i sorteringsrekkefølgen
    - kjøretid
        - innsetting, søk, sletting går nedover, proporsjonal med høyden
        - høyden i balansert tre er log2(n)
        - helt ubalansert tre gir kjøretid n
- B-trær (søketrær)
    - strukturer osm kan være for store for minnet
    - flere verdier i hver node (blokk)
    - gir få trinn for aksess

    -