/**
 * Strengtabell.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Enkel bruk av strengtabell.
 */

import static javax.swing.JOptionPane.*;

class Strengtabell {
  public static void main(String[] args) {
    String[] navn = {"Toril", "Ole", "Petter", "Kari"};

    for (int i = 0; i < navn.length; i++) {
      System.out.println(navn[i]);   // skriver ut ett og ett navn
    }

    String s�k = showInputDialog("Skriv et navn: ");
    boolean funnet = false;
    int indeks = 0;
    while (!funnet && indeks < navn.length) {
      if (navn[indeks].equals(s�k)) {  // sammenligner et navn med innlest tekst
        funnet = true;
        showMessageDialog(null, "Navnet finnes p� indeks " + indeks);
      }
      indeks++;
    }
    if (!funnet) {
      showMessageDialog(null, "Navnet finnes ikke");
    }
  }
}

/* Eksempler p� kj�ring av programmet:
Kj�ring 1:
Skriv et navn: Hans
Navnet finnes ikke

Kj�ring 2:
Skriv et navn: Kari
Navnet finnes p� indeks 3
*/