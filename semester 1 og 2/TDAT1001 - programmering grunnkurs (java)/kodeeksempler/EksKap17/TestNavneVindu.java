/**
 * TestNavneVindu.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Filen inneholder tre klasser:
 * NavneVindu: Et vindu der du kan skrive inn 15navnet ditt og trykke på en knapp
 * Knappelytter: Håndterer den hendelsen at brukeren trykker på knappen
 * TestNavneVindu: main()-metode som viser fram vinduet
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class NavneVindu extends JFrame {
  private JTextField navnefelt = new JTextField(20);
  private JLabel hilsen = new JLabel("Her kommer det en hilsen");

  public NavneVindu(String tittel) {
    setTitle(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setLayout(new FlowLayout());

    JLabel ledetekst = new JLabel("Skriv navnet ditt:");
    add(ledetekst);
    add(navnefelt);

    JButton knapp = new JButton("Trykk her");
    add(knapp);
    Knappelytter knappelytteren = new Knappelytter();
    knapp.addActionListener(knappelytteren);

    add(hilsen);
    pack();
  }

  private class Knappelytter implements ActionListener {
    public void actionPerformed(ActionEvent hendelse) {
      String navn = navnefelt.getText();
      hilsen.setText("Hei på deg, " + navn + "!");
    }
  }
}

class TestNavneVindu {
  public static void main(String[] args) {
    NavneVindu etVindu = new NavneVindu("Et vindu med navnefelt");
    etVindu.setVisible(true);
  }
}