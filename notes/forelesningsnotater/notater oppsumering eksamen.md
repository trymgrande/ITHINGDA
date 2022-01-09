---
title: notater oppsumering eksamen
updated: 2021-09-06 14:33:12Z
created: 2019-05-20 14:54:00Z
author: trym.grande@gmail.com
---

- ved 1 kjerne er det cpu sin jobb å bestemme rekkefølge på instruksjoner
- ved flere kjerner er det os sin jobb å fordele jobber på kjernene
- synk
- krav for mulig deadlock
    - mutex
        - felles dataområde skal bare aksesseres en om gangen
    - wait on hold
        - når prosess har fått tak i  ressurs kan den holde ressurs til den har fått tak i den
    - circular waiting
        - en prosess kan vente på annen prosess som venter på tredje prosess
    - no pre-emption
        - har ikke implementert metode for avbrudd
- unngå deadlocks
    - unngå
    - forhindre (fjerne en av de fire kravene)
    - forutse (bankers algorithm) (sjekke maks krav) (vil alltid være i safe state)
    - detect and recover
- round robin
    - gir lik tidskvant om gangen til hver prosess
- hard link
    - navn til filnr
- soft link
    - filnavn til filnavn
- security mål: integritet, tilgjengelitet, konfidensialitet, pålitelighet
    - cia

-