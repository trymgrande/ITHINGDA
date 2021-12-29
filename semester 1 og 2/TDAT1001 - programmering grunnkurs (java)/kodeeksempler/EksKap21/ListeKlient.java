/**
 * ListeKlient.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
  * Klient som prøver klassen ElementListe.
 *
 */
class ListeKlient {
  public static void main(String[] args) {
    ElementListe<String> enListe = new ElementListe<String>();
    System.out.println("Utskrift av tom liste: ");
    System.out.println(enListe.toString());
    enListe.settInnElementTilSlutt("Arne");
    enListe.settInnElementTilSlutt("Krille");
    System.out.println("Utskrift av to gode kompiser: ");
    System.out.println(enListe.toString());
    System.out.println("Finnes Mikke i listen: " + enListe.søkEtterElement("Mikke"));
    System.out.println("Finnes Arne i listen: " + enListe.søkEtterElement("Arne"));
    System.out.println("Finnes Krille i listen: " + enListe.søkEtterElement("Krille"));
    System.out.println("Setter inn en ekstra Arne ...");
    enListe.settInnElementTilSlutt("Arne");
    System.out.println("Utskrift med to Arne-er: ");
    System.out.println(enListe.toString());
    System.out.println("Sletter Arne og Krille ...");
    enListe.slettElement("Arne");
    enListe.slettElement("Krille");
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
Setter inn en ekstra Arne …
Utskrift med to Arne-er:
Arne
Krille
Arne
Sletter Arne og Krille …
Utskrift av tom liste:
*/