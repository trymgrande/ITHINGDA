/*
 * FlereStrengoperasjoner.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet prøver ut de ulike formene av indexOf(), lastIndexOf() og substring().
 */

class FlereStrengoperasjoner {
  public static void main(String[] args) {
    String tekst = "Dette er en testetekst med mange e-er";
    System.out.println("Tester teksten: " + tekst);
    System.out.println("Denne teksten har lengde: " + tekst.length());
    System.out.println("Posisjonene nummereres fra og med 0, til og med " +
                                    (tekst.length() - 1));

    /* Tester søk forfra med metoden indexOf() */
    int pos = tekst.indexOf('e');
    System.out.println("Den første e-en er på posisjon: " + pos);
    pos = tekst.indexOf('e', pos + 1);  // begynner søket etter første 'e'
    System.out.println("Den neste e-en er på posisjon: " + pos);
    pos = tekst.indexOf("tekst");
    System.out.println("Første forekomst av \"tekst\"  er på posisjon: " + pos);
    pos = tekst.indexOf("tekst", pos + 1);
    System.out.println("Neste forekomst av \"tekst\"  er på posisjon: " + pos);

    /* Tester søk bakfra med metoden lastIndexOf() */
    pos = tekst.lastIndexOf('e');
    System.out.println("Den siste e-en er på posisjon: " + pos);
    pos = tekst.lastIndexOf('e', pos - 1);  // begynner søket foran siste 'e'
    System.out.println("Nest siste e er på posisjon: " + pos);
    pos = tekst.lastIndexOf("tekst");
    System.out.println("Siste forekomst av \"tekst\" er på posisjon: " + pos);
    pos = tekst.lastIndexOf("tekst", pos - 1);
    System.out.println("Nest siste forekomst av \"tekst\"  er på posisjon: " + pos);

    /* Henter ut deltekster */
    String deltekst = tekst.substring(10);
    System.out.println("Fra og med posisjon 10: " + deltekst);
    deltekst = tekst.substring(7, 14);
    System.out.println("Fra og med posisjon 7 til og med posisjon 13: " + deltekst);

    /* Bytter ut alle e-er med a-er */
    String nyTekst = tekst.replace('e', 'a');
    System.out.println("Ny tekst uten e-er: " + nyTekst);

    System.out.println("Ingenting av dette har endret den opprinnelige teksten: ");
    System.out.println(tekst);
  }
}

/* Kjøring av programmet:
Tester teksten: Dette er en testetekst med mange e-er
Denne teksten har lengde: 37
Posisjonene nummereres fra og med 0, til og med 36
Den første e-en er på posisjon: 1
Den neste e-en er på posisjon: 4
Første forekomst av "tekst"  er på posisjon: 17
Neste forekomst av "tekst"  er på posisjon: -1
Den siste e-en er på posisjon: 35
Nest siste e er på posisjon: 33
Siste forekomst av "tekst"  er på posisjon: 17
Nest siste forekomst av "tekst" er på posisjon: -1
Fra og med posisjon 10: n testetekst med mange e-er
Fra og med posisjon 7 til og med posisjon 13: r en te
Ny tekst uten e-er: Datta ar an tastatakst mad manga a-ar
Ingenting av dette har endret den opprinnelige teksten:
Dette er en testetekst med mange e-er
*/