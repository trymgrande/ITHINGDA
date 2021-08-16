/**
 * EnkelKalkulator.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet leser inn to tall og lar brukeren velge mellom addisjon,
 * subtraksjon, multiplikasjon og divisjon. Regnestykket og resultatet skrives ut.
 * Programmet går i løkke og kan utføre flere regnestykker.
 *
 * Filen inneholder to klasser:
 * KalkulatorBGS: Klassen sørger for all brukerkommunikasjon.
 * EnkelKalkulator: Inneholder main(), som viser hovedflyten i programmet.
 */

import static javax.swing.JOptionPane.*;
class EnkelKalkulator {

  public static void main(String[] args) {
    KalkulatorBGS kalkulatorBGS = new KalkulatorBGS();
    int valg = kalkulatorBGS.lesValg();
    while (valg != CLOSED_OPTION) {
      kalkulatorBGS.lesData();
      kalkulatorBGS.visRegnestykke(valg);
      valg = kalkulatorBGS.lesValg();
    }
  }
}

class KalkulatorBGS {
  private static final String[] MULIGHETER = {"pluss", "minus", "gange", "dele"};
  private static final int PLUSS = 0;    // verdiene svarer til
  private static final int MINUS = 1;    // plasseringen i tabellen muligheter
  private static final int GANGE = 2;   // over
  private static final int DELE = 3;

  private int tall1;
  private int tall2;

  public void lesData() {
    String tall1Lest = showInputDialog("Første tall: ");
    String tall2Lest = showInputDialog("Andre tall: ");
    tall1 = Integer.parseInt(tall1Lest);
    tall2 = Integer.parseInt(tall2Lest);
  }

  public int lesValg() {
    int valg = showOptionDialog(null, "Velg operator", "Fire regneoperasjoner",
                         0, PLAIN_MESSAGE, null, MULIGHETER, MULIGHETER[0]);
    return valg;
  }

  public void visRegnestykke(int operator) {
    String resultat;
    switch(operator) {
      case PLUSS:
        resultat = tall1 + " + " + tall2 + " = " + (tall1 + tall2);
        break;
      case MINUS:
        resultat = tall1 + " - " + tall2 + " = " + (tall1 - tall2);
        break;
      case GANGE:
        resultat = tall1 + " * " + tall2 + " = " + (tall1 * tall2);
        break;
      case DELE:
        if (tall2 != 0) { // må unngå divisjon med 0
          resultat = "Heltallsdivisjon: " + tall1 + " / " + tall2 + " = " + (tall1 / tall2)
                         + "\nRest: " + tall1 + " % " + tall2 + " = " + (tall1 % tall2);
        } else {
          resultat = "Kan ikke beregne resultat (divisjon med null).";
        }
        break;
      default:
        resultat = "Kan ikke beregne resultat.";
        break;
    }
    showMessageDialog(null, resultat);
  }
}

/* Kjøre-eksempler:

Første tall: 12
Andre tall: 4
Velger operasjonen "gange"
Resultatutskrift: 12 * 4 = 48

Første tall: 12
Andre tall: 0
Velger operasjonen "dele"
Resultatutskrift: Kan ikke beregne resultat.
*/