/**
 * TestFargeknappVindu.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Filen inneholder tre klasser:
 * FargeknappVindu: Et vindu med en knapp som endrer farge
 *                                når man trykker på den
 * Knappelytter: Håndterer den hendelsen at brukeren trykker på knappen
 * TestFargeknappVindu: main()-metode som viser fram vinduet
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class FargeknappVindu extends JFrame {
  public FargeknappVindu(String tittel) {
    setTitle(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout());
    JButton knapp = new JButton("Trykk her");
    knapp.setBackground(Color.BLUE); // startfargen
    add(knapp);
    Knappelytter knappelytteren = new Knappelytter();
    knapp.addActionListener(knappelytteren);
    pack();
  }
}

class Knappelytter implements ActionListener {
  public void actionPerformed(ActionEvent hendelse) {
    JButton knapp = (JButton) hendelse.getSource();
    Color farge = knapp.getBackground();
    if (farge == Color.RED) {
      knapp.setBackground(Color.BLUE);
    } else {
      knapp.setBackground(Color.RED);
    }
  }
}

class TestFargeknappVindu {
  public static void main(String[] args) {
    FargeknappVindu etVindu = new FargeknappVindu("Vindu som lytter");
    etVindu.setVisible(true);
  }
}