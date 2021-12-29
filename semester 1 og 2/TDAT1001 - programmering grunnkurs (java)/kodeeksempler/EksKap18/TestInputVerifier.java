/**
 * TestInputVerifier.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Kontrollerer input-data. Bruker en subklasse til InputVerifier.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import javax.swing.event.*;

class Tallvindu extends JFrame {
  private JButton knapp = new JButton("Du har ennå ikke skrevet noe");

  public Tallvindu(String tittel) {
    setTitle(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel ledetekst = new JLabel("Skriv et firesifret tall");
    add(ledetekst, BorderLayout.NORTH);
    JTextField tekstfelt = new JTextField(20);
    add(tekstfelt, BorderLayout.CENTER);
    tekstfelt.setInputVerifier(new TallKontroll());
    add(knapp, BorderLayout.SOUTH);
    pack();
  }

  /*
   * Klasse for kontroll av datafelt. Datafeltet skal inneholde et firesifret heltall.
   * Datakontrollen ligger i metoden verify(), mens meldingen til bruker
   * ligger i metoden shouldYieldFocus().
   */
  private class TallKontroll extends InputVerifier {
    private int tall = 0;  // tallet som brukeren skriver inn

    public boolean verify(JComponent inndata) {
      JTextField data = (JTextField) inndata;
      String tekst = data.getText();
      boolean ok = false;
      try {
        tall = Integer.parseInt(tekst);
        if (tall >= 1000 && tall <= 9999) {
          ok = true;
        }
      } catch (NumberFormatException e) {
      }
      return ok;
    }

    public boolean shouldYieldFocus(JComponent inndata) {
      boolean ok = super.shouldYieldFocus(inndata);  // obs!
      if (ok) {
        knapp.setText("OK, tallet er " + tall);
      } else {
        knapp.setText("Ikke OK tall");
      }
      return ok;
    }
  }
}

class TestInputVerifier {
  public static void main(String[] args) {
    Tallvindu etVindu = new Tallvindu("Firesifret tall");
    etVindu.setVisible(true);
  }
}