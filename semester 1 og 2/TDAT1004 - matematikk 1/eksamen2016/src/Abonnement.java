public abstract class Abonnement {
    //Klassen Abonnement skal ha en referanse til kunden som betaler, samt objektvariabler for forbruket
    //(tale i minutter, antall SMS, antall MMS, antall GB data) siste måned. Et objekt av klassen må også
    //ha informasjon om telefonnummeret og eventuell rabatt knyttet til abonnementet. Vi antar at når
    //måneden er slutt, lages faktura (som eksporteres, du ser bort fra dette i denne oppgaven) og forbruket
    //nullstilles.
    private int antallMmsBrukt;
    private int antallGbBrukt;

    Abonnement(Kunde nyKunde) {

    }

}
