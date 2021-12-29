/**
 * BeleggKlient.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Enkel klient for klassen Belegg.
 * Finner hvor mange meter vegg-til-vegg-teppe som trengs for å dekke Berits golv
 * (5 x 6 meter). Finner også ut hva det koster. Teppet er 5 meter bredt,
 * og det koster kr 243,50 pr. meter.
 */

class BeleggKlient {
  public static void main(String[] args) {
    Flate flaten = new Flate("Berits golv", 5, 6);
    System.out.println(flaten.toString());
    Belegg belegget = new Belegg("Vegg-til-vegg teppe", 243.50, 5);
    System.out.println(belegget.toString());

    /* Samarbeid mellom belegg- og flateobjektet for å finne antall meter og pris */
    double antMeter = belegget.beregnAntMeter(flaten);
    double pris = belegget.beregnTotalpris(flaten);

    java.util.Formatter f = new java.util.Formatter();
    f.format("Berit trenger %.2f meter, pris kr %.2f.", antMeter, pris);
    System.out.println(f.toString());
  }
}

/* Kjøring av programmet:
Flate: Berits golv, bredde: 6,00 m, lengde: 5,00 m.
Belegg: Vegg-til-vegg teppe, pris kr 243,50 pr. m, bredde: 5,00 m.
Berit trenger 6,00 meter, pris kr 1461,00.
*/