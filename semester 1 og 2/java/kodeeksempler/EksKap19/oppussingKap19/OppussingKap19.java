/*
 * OppussingKap19.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Hovedprogrammet som kjører applikasjonen.
 */
import oppussingsprosjekt.*;
class OppussingKap19 {
  public static void main(String[] args) {
    Oppussingsprosjekt mittProsjekt
        = new Oppussingsprosjekt("Siste utgave av prosjektet!");
    OppussingKap19GUI vindu = new OppussingKap19GUI(mittProsjekt);
    vindu.setVisible(true);
  }
}