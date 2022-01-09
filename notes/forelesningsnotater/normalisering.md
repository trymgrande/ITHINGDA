---
title: normalisering
updated: 2019-05-25 12:09:09Z
created: 2019-02-25 09:14:31Z
author: trym.grande@gmail.com
---

- 1nf
    - no repeating groups (only one attribute pr felt)
    - every row unique by setting pk
    - ingen del av pk = null
- 2nf
    - no partial key dependencies (no attributes dependent on the key) (en ikke-nøkkel-attributt er f.a. av en del av pk)
    - hele pk determinerer resten av kolonnene
    - 1nf til 2nf: splitt opp relasjonen i flere nye relasjoner slik at partielle avhengigheter unngås (ingen piler krysser bokser)
- 3nf
    - removes transitive dependencies (split things that don't belong together) (transitiv avhengighet: A > B > C, ikke transitiv avhengighet: A > C) (alle piler utgår fra pk) (pk bestemmer fk)
- BCNF
    - enhver determinant er en kandidatnøkkel
    - hvis en tabell har bare èn kandidatnøkkel, er 3nf = bcnf