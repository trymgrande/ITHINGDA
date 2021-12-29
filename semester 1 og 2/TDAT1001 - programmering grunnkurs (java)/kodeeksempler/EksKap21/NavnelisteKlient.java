/**
 * NavnelisteKlient.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Pr�ver klassen Navneliste.
 */

class NavnelisteKlient {
  public static void main(String[] args) {
    Navneliste enListe = new Navneliste();
    System.out.println("Utskrift av tom liste: ");
    System.out.println(enListe.toString());
    enListe.settInnNavnTilSlutt("Arne");
    enListe.settInnNavnTilSlutt("Krille");
    System.out.println("Utskrift av to gode kompiser: ");
    System.out.println(enListe.toString());
    System.out.println("Finnes Mikke i listen: " + enListe.s�kEtterNavn("Mikke"));
    System.out.println("Finnes Arne i listen: " + enListe.s�kEtterNavn("Arne"));
    System.out.println("Finnes Krille i listen: " + enListe.s�kEtterNavn("Krille"));
    System.out.println("Setter inn en ekstra Arne ...");
    enListe.settInnNavnTilSlutt("Arne");
    System.out.println("Utskrift med to Arne-er: ");
    System.out.println(enListe.toString());
    System.out.println("Sletter Arne og Krille ...");
    enListe.slettNavn("Arne");
    enListe.slettNavn("Krille");
    System.out.println("Utskrift av tom liste: ");
    System.out.println(enListe.toString());
  }
}

/* Utskrift:
Utskrift av tom liste:

Utskrift av to gode kompiser:
Arne
Krille

Finnes Mikke i listen: false
Finnes Arne i listen: true
Finnes Krille i listen: true
Setter inn en ekstra Arne ...
Utskrift med to Arne-er:
Arne
Krille
Arne

Sletter Arne og Krille ...
Utskrift av tom liste:

*/






