/**
 * OppussingsKlient.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Prøver noen av metodene i klassen Oppussingsprosjekt.
 */

class OppussingsKlient {
  public static void main(String[] args) {

    /* Registrerer to malingstyper og to flater */
    Maling m1 = new Maling("Heimdal Super", 80, 2, 12);
    Maling m2 = new Maling("Heimdal Extra", 100, 3, 10);
    Flate f1 = new Flate("Vegg i barnerom", 4, 3);
    Flate f2 = new Flate("Taket i gangen", 6, 1);
    f1.setMalingstype(m1);
    f2.setMalingstype(m2);

    Oppussingsprosjekt enLeilighet = new Oppussingsprosjekt("Min leilighet");
    enLeilighet.registrerNyMaling(m1);
    enLeilighet.registrerNyMaling(m2);
    enLeilighet.registrerNyFlate(f1);
    enLeilighet.registrerNyFlate(f2);

    System.out.println("Utskrift 1:\n" + enLeilighet.toString());

    /* Totalprisen for seg */
    java.util.Formatter f = new java.util.Formatter();
    f.format("Totalpris: %.2f",  enLeilighet.beregnTotalpris());
    System.out.println("Totalpris: " + f.toString());

    /* Og alle detaljene */
    System.out.println("\nDetaljer:\n" + enLeilighet.lagPrisOgBehovDetaljer());

    /* Setter samme malingstype på alle flater */
    for (int i = 0; i < enLeilighet.finnAntFlater(); i++) {
      Flate flate = enLeilighet.finnFlate(i);
      flate.setMalingstype(m2);
    }
    System.out.println("\nUtskrift 2:\n" + enLeilighet.lagPrisOgBehovDetaljer());
  }
}

/* Utskrift:
Utskrift 1:
Alle flater:
Flate: Vegg i barnerom, bredde: 3,00 m, lengde: 4,00 m.
Flate: Taket i gangen, bredde: 1,00 m, lengde: 6,00 m.

Alle malingstyper:
Maling: Heimdal Super, pris kr  80,00  pr. liter, ant. strøk: 2, ant. kvm/l 12,00
Maling: Heimdal Extra, pris kr  100,00  pr. liter, ant. strøk: 3, ant. kvm/l 10,00

Totalpris: Totalpris: 360,00

Detaljer:
Flate: Vegg i barnerom, bredde: 3,00 m, lengde: 4,00 m., behov: 2,00 liter, pris 160,00 kr.
Flate: Taket i gangen, bredde: 1,00 m, lengde: 6,00 m., behov: 2,00 liter, pris 200,00 kr.
Totalpris 360,00 kr.

Utskrift 2:
Flate: Vegg i barnerom, bredde: 3,00 m, lengde: 4,00 m., behov: 4,00 liter, pris 400,00 kr.
Flate: Taket i gangen, bredde: 1,00 m, lengde: 6,00 m., behov: 2,00 liter, pris 200,00 kr.
Totalpris 600,00 kr.
*/