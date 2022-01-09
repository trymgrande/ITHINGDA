---
title: programmering
updated: 2019-05-20 13:38:31Z
created: 2019-05-19 11:57:39Z
author: trym.grande@gmail.com
---

- assembly er utvidelse av symbolsk kode (med variabler)
- assembler oversetter assembly til maskinkode
- C er lavnivå som er egnet for ytelse, men er mer tidkrevende
- oversetting av kildekode til maskinkode
    - tre metoder:
        - tolking
            - oversettes samtidig som kjøring
                - hver linje oversettes og kjøres
                - - tregere fordi kompilator må oversette
                - + lettvint og hurtig å endre kode for å så teste
                - Basic og diverse script tolkes vanligvis
        - oversetting (kompilering)
            - kompilerer til fil før kjøring
            - filen kan gjenbrukes
            - C kompileres
            - - tar tid
            - - maskinavhengig (kompileres for bestemt cpu)
        - bruk av mellomspråk (bytekode)
            - kombinasjon av kompilering og tolking
            - kompileres til bytekode, som maskinuavhengig
            - for å kjøre på bestemt maskin, må bytekoden tolke/oversette hver linje til maskinkode
            - gjør kjøringen mindre tidkrevende og maskinuavhengig
            - java bruker bytekode
- java
    - kompilerer til maskinkode for JVM
    - kan kjøres i nettlesere og på vanlige maskiner
    - sandkasser hindrer skadevare i nettlesere fra å få maskinvareaksess
    - finnes java-prosessor som kan kjøre java direkte i innebygde systemer (tv, radio...)

høynivåspråk er maskinuavhengige i form av at de ikke er er bygd opp av instruksjoner for spesifikke maskiner (cpuer), men heller består av generelle instruksjoner som kan kompileres videre til lavere nivå kode. hver cpu trenger hver sin kompilator for å kunne forstå koden. høynivå er lett å programmere i og mindre tidkrevende, men det må til gjengjeld oversettes så cpuen kan forstå det.

assemblyspråk er ikke maskinuavhengig fordi det er tilpasset cpu, og ikke mennesker.

symbolsk kode er lavnivå kode som fortsatt kan forstås av mennesker fordi det er bygd opp av felter bestående av op-koder og adresser skrevet heksadesimalt

assembly er en videreføring av symbolsk kode med bl.annet variabler istedenfor adresser som gjør det enklere å programmere

maskinkode er rene bits som kun forstås av cpuer