---
title: øving 14 - normalisering
updated: 2019-05-25 12:08:46Z
created: 2019-02-25 10:18:20Z
author: trym.grande@gmail.com
---

a)
1nf
PK: (ssn, kursid), ikke null
1nf tilfredsstilt
deler av pk determinerer andre kolonner (ssn > personalia, kursid > kursnavn)
ikke 2nf
b)
kan føre til unødvendig dobbeltlagring

dataintegriteten oppretholdes ikke (f.eks. hvis et fag slettes, vil også studenten slettes)

c)
1nf > 2nf:
deler opp i tre tabeller

personalia

|     |     |     |     |     |
| --- | --- | --- | --- | --- |
| ssn | navn | adresse | klnavn | st_retning |

kurs

|     |     |     |
| --- | --- | --- |
| kursid | kursnavn | eksdato |

student

|     |     |
| --- | --- |
| kursid | ssn |

2nf > 3nf

|     |     |
| --- | --- |
| kl.navn | studieretning |

personalia

|     |     |     |
| --- | --- | --- |
| ssn | navn | adresse |

kurs

|     |     |     |
| --- | --- | --- |
| kursid | kursnavn | eksdato |

student

|     |     |
| --- | --- |
| kursid | ssn |

BCNF = 3nf fordi kun èn kandidatnøkkel

2

kandidatnøkler kan være: kunde_id, eier_id, eiendoms_id

fordi hvis du f.eks. sletter utleieforhold vil også data om eiendommen og eieren slettes

|     |     |     |     |
| --- | --- | --- | --- |
| kunde_id | navn | telefon | adresse |

|     |     |     |     |
| --- | --- | --- | --- |
| eiendoms_id | eier_id | adr. | pris_pr_uke |

|     |     |     |     |
| --- | --- | --- | --- |
| eier_id | navn | adr. | tlf. |

|     |     |     |     |
| --- | --- | --- | --- |
| kunde_id | eiendoms_id | fra_uke | til_uke |