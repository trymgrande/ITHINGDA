/*
 * OppussingsKlient.java   - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Prøver noen av metodene i klassen Oppussingsprosjekt, kapittel 14.
 */

 /* Data:
 Tak: 6 x 1 kvm, maling: Heimdal super,2 strøk, 12 kvm/l, 80 kr/l ==> 1.0 liter kr 80
 Vegg: 7.2 x 2.35 kvm, tapet: Solgull, 0.54 m x 12.5 m, 100 kr/rull ==> 3 ruller, kr 300
 Golv: lengde 3 m, bredde 4 m, belegg: Tappa, kr 250/m, bredde 3 m ==> 4 meter, kr 1000
 Totalpris kr 1380.
*/
class OppussingsKlient {
  public static void main(String[] args) {

    /* Registrerer noen materialtyper og flater */
    Materiale maling = new Maling("Heimdal Super", 80, 2, 12);
    Materiale tapet = new Tapet("Solgull", 100, 12.5, 0.54);
    Materiale belegg = new Belegg("Tappa", 250, 3);
    Flate vegg = new Flate("Vegg i barnerom", 7.2, 2.35);
    Flate tak = new Flate("Taket i gangen", 6, 1);
    Flate golv = new Flate("Golv", 3, 4);
    vegg.setMaterialtype(tapet);
    tak.setMaterialtype(maling);
    golv.setMaterialtype(belegg);

    Oppussingsprosjekt enLeilighet = new Oppussingsprosjekt("Min leilighet");
    enLeilighet.registrerNyttMateriale(maling);
    enLeilighet.registrerNyttMateriale(tapet);
    enLeilighet.registrerNyttMateriale(belegg);
    enLeilighet.registrerNyFlate(vegg);
    enLeilighet.registrerNyFlate(golv);
    enLeilighet.registrerNyFlate(tak);

    System.out.println("Registrerte data:\n" + enLeilighet.toString());
    System.out.printf("Totalpris: %.2f\n", enLeilighet.beregnTotalpris());

    /* Skriver ut alle detaljene */
    System.out.println("\nDetaljer:\n" + enLeilighet.lagPrisOgBehovDetaljer());

    /* Endrer materialet for vegg fra tapet til maling */
    vegg.setMaterialtype(maling);
    System.out.println("\nDetaljer2:\n" + enLeilighet.lagPrisOgBehovDetaljer());
  }
}

/* Utskrift:
Registrerte data:
Alle flater:
Flate: Vegg i barnerom, bredde: 2,35 m, lengde: 7,20 m, dekkes med Solgull
Flate: Golv, bredde: 4,00 m, lengde: 3,00 m, dekkes med Tappa
Flate: Taket i gangen, bredde: 1,00 m, lengde: 6,00 m, dekkes med Heimdal Super

Alle materialtyper:
Maling: Heimdal Super, pris kr 80,00 pr. liter, ant. strøk: 2, ant. kvm/l: 12,00
Tapet: Solgull, pris kr 100,00 pr. rull, lengde rull: 12,50 m, bredde rull: 0,54 m
Belegg: Tappa, pris kr 250,00 pr. m, bredde: 3,00 m.

Totalpris: 1380,00

Detaljer:
Flate: Vegg i barnerom, bredde: 2,35 m, lengde: 7,20 m, dekkes med Solgull, behov: 3,00 liter, pris 300,00 kr.
Flate: Golv, bredde: 4,00 m, lengde: 3,00 m, dekkes med Tappa, behov: 4,00 liter, pris 1000,00 kr.
Flate: Taket i gangen, bredde: 1,00 m, lengde: 6,00 m, dekkes med Heimdal Super, behov: 1,00 liter, pris 80,00 kr.
Totalpris 1380,00 kr.

Detaljer2:
Flate: Vegg i barnerom, bredde: 2,35 m, lengde: 7,20 m, dekkes med Heimdal Super, behov: 3,00 liter, pris 240,00 kr.
Flate: Golv, bredde: 4,00 m, lengde: 3,00 m, dekkes med Tappa, behov: 4,00 liter, pris 1000,00 kr.
Flate: Taket i gangen, bredde: 1,00 m, lengde: 6,00 m, dekkes med Heimdal Super, behov: 1,00 liter, pris 80,00 kr.
Totalpris 1320,00 kr.
*/

