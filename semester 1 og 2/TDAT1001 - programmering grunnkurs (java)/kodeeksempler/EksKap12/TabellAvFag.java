/**
 * TabellAvFag.java  - "Programmering i Java", 4.utgave - 2009-07-01
 */

class TabellAvFag {
  public static void main(String[] args) {
    Fag[] gruppeA = {new Fag("LV172D", "Programmering i Java"),
                                 new Fag("LC191D", "Videregående programmering"),
                                 new Fag("LO347D", "Web-applikasjoner med Java EE")};

    System.out.println("Utskrift 1:");
    for (int i = 0; i < gruppeA.length; i++) {
      System.out.println(gruppeA[i]); // toString() blir brukt
    }

    /* Setter antall studenter til hhv. 10, 15 og 20 og skriver ut */
    gruppeA[0].setAntStud(10);
    gruppeA[1].setAntStud(15);
    gruppeA[2].setAntStud(20);
    System.out.println("\nUtskrift 2, skal gi antall studenter lik hhv 10, 15 og 20:");
    for (int i = 0; i < gruppeA.length; i++) {
      System.out.println(gruppeA[i]); // toString() blir brukt
    }
  }
}

/* Kjøring av programmet:

Utskrift 1:
Kode: LV172D, Navn: Programmering i Java, 0 studenter.
Kode: LC191D, Navn: Videregsende programmering, 0 studenter.
Kode: LO347D, Navn: Web-applikasjoner med Java EE, 0 studenter.

Utskrift 2, skal gi antall studenter lik hhv 10, 15 og 20:
Kode: LV172D, Navn: Programmering i Java, 10 studenter.
Kode: LC191D, Navn: Videregående programmering, 15 studenter.
Kode: LO347D, Navn: Web-applikasjoner med Java EE, 20 studenter.
*/