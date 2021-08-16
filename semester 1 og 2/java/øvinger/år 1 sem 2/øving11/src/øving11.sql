Gjør deg kjent med databasen ved f.eks. å tegne et ER-diagram.

    -- List ut all informasjon (ordrehode og ordredetalj) om ordrer for leverandør nr 44.

    Finn navn og by ("LevBy") for leverandører som kan levere del nummer 1.
    Finn nummer, navn og pris for den leverandør som kan levere del nummer 201 til billigst pris.
    Lag fullstendig oversikt over ordre nr 16, med ordrenr, dato, delnr, beskrivelse, kvantum, (enhets-)pris og beregnet beløp (=pris*kvantum).
    Finn delnummer og leverandørnummer for deler som har en pris som er høyere enn prisen for del med katalognr X7770.
    i) Tenk deg at tabellen levinfo skal deles i to. Sammenhengen mellom by og fylke skal tas ut av tabellen. Det er unødvendig å lagre fylketilhørigheten for hver forekomst av by. Lag én ny tabell som inneholder byer og fylker. Fyll denne med data fra levinfo. Lag også en tabell som er lik levinfo unntatt kolonnen Fylke. (Denne splittingen av tabellen levinfo gjelder bare i denne oppgaven. I resten av oppgavesettet antar du at du har den opprinnelige levinfo-tabellen.)

    ii) Lag en virtuell tabell (view) slik at brukerne i størst mulig grad kan jobbe på samme måte mot de to nye tabellene som den gamle. Prøv ulike kommandoer mot tabellen (select, update, delete, insert). Hvilke begrensninger, hvis noen, har brukerne i forhold til tidligere?
    Anta at en vurderer å slette opplysningene om de leverandørene som ikke er representert i Prisinfo-tabellen. Finn ut hvilke byer en i tilfelle ikke får leverandør i. (Du skal ikke utføre slettingen.) (Tips: Svaret skal bli kun én by, "Ål".)
    Finn leverandørnummer for den leverandør som kan levere ordre nr 18 til lavest totale beløp (vanskelig).
    Hint: Løs oppgaven i tre steg:

    Lag en virtuell tabell (view) som viser hvem som kan levere hele eller deler av ordren.
    Fra denne velger du så ut de leverandørene som kan levere like mange deler til ordren som ordren krever. Det vil si, kan levere hele ordren.
    Til slutt finner du ut hvem som leverer billigst.
    Svaret skal bli at leverandør 6 kan levere ordren for 6798 kroner.
    Oppgave 2
    Bruk Bok-databasen fra Øving 6. Gå gjennom datasettet og finn ut hvor det ligger NULL-verdier.

    Sett opp en SELECT-setning som er UNION mellom alle forlag med Oslo-nummer (telefonnummer begynner med 2) og alle som ikke er Oslo-nummer. Får du med forlaget med NULL-verdi på telefonnummer? Hvis ikke, utvid unionen med en mengde til.
    Sett opp SQL-setninger som finner gjennomsnittlig alder på forfattere der fødselsåret er oppgitt. For forfattere der dødsåret ikke er oppgitt, skal du kun ta med de som er født etter 1900. Tips for å få ut året i år:
    MySQL: SELECT YEAR(CURRENT_DATE) FROM ... hvilken tabell som helst ...
    Sett opp SQL-setninger som finner hvor stor andel av forfatterne som ble med i beregningene under b).

