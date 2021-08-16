/**
 * TestTrykknappVindu.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Filen inneholder tre klasser:
 * Vindu: Et vindu med en trykknapp
 * Knappelytter: H�ndterer den hendelsen at brukeren trykker p� knappen
 * TestTrykknappVindu: main()-metode som viser fram vinduet
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TrykknappVindu extends JFrame {
  public TrykknappVindu(String tittel) {
    setTitle(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    LayoutManager layout = new FlowLayout();
    setLayout(layout);

    JButton knapp = new JButton("Trykk her");  // lager knappen
    add(knapp);  // legger knappen i beholderen
    Knappelytter knappelytteren = new Knappelytter(); // lager en lytter
    knapp.addActionListener(knappelytteren); // knytter lytteren til knappen
    pack();  // tilpasser vindusst�rrelsen
  }
}

class Knappelytter implements ActionListener {
  public void actionPerformed(ActionEvent hendelse) {
    System.out.println("Du trykket p� knappen.");
  }
}

class TestTrykknappVindu {
  public static void main(String[] args) {
    TrykknappVindu etVindu = new TrykknappVindu("Et vindu med en knapp");
    etVindu.setVisible(true);
  }
}