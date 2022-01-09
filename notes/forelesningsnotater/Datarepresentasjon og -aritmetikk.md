---
title: Datarepresentasjon og -aritmetikk
updated: 2019-02-18 18:22:28Z
created: 2019-02-06 09:20:40Z
author: trym.grande@gmail.com
---

- maskering

-

    - sette eller lese enkeltbits i et bitmønster uten å påvirke de andre bitene i bitmønsteret
- tegn

-

    - ascii

    -

        - 7 bit
        - amerikanske tegn + kontrollsignaler
        - extended ascii

        -

            - ibm
            - 8 bit
            - standard i DOS
        - 33 første reservert til kontrolltegn
    - unicode

    -

        - ibm, microsoft...
        - internasjonal standard
        - 16 bit
        - hebraisk, japansk, norsk
        - java
    - strenger

    -

        - ![](../_resources/f7d5b7f9df395c55b1d24191be00374a.png)
        - to metoder:
        - første element antall chars
        - siste element termineres med 0
- heltall

-

    - heltall uten fortegn (unsigned)
    - heltall med fortegn

    -

        - fortegnsbit

        -

            - 0 gir +
            - 1 gir -
            - ulempe: 2 representasjoner for 0
        - bias (forskyvning)

        -
            -

        - toerkompleks

        -

- toerkomplement

-

    - positive verdier er like representativt
    - negative: 11111 = -1, teller oppover til tomt for bits
    - første tallet 1 gir -, + gir +

    -