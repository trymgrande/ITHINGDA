/**
 * TestNavneVindu3Knapper.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Filen inneholder tre klasser:
 * NavneVindu3Knapp: Et vindu med tekstfelt og tre trykknapper. Knappene endrer
 * fargen p� en av tekstene som vises i vinduet.
 * Knappelytter: Indre klasse som h�ndterer den hendelsen at brukeren trykker
 * p� en av knappene
 * TestNavneVindu3Knapper: main()-metode som viser fram vinduet
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class NavneVindu3Knapp extends JFrame {
  private JTextField navnefelt = new JTextField(20);
  private JLabel hilsen = new JLabel("Her kommer en hilsen til deg");

  public NavneVindu3Knapp(String tittel) {
    setTitle(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout());

    JLabel ledetekst = new JLabel("Skriv navnet ditt:");
    add(ledetekst);
    add(navnefelt);
    /*
     * Lager tre knapper, setter bakgrunnsfarge i samsvar med teksten
     * p� knappene og legger dem i beholderen
     */
    JButton knappR�d = new JButton("R�d");
    knappR�d.setBackground(Color.RED);
    add(knappR�d);

    JButton knappBl� = new JButton("Bl�");
    knappBl�.setBackground(Color.BLUE);
    add(knappBl�);

    JButton knappTurkis = new JButton("Turkis");
    knappTurkis.setBackground(Color.CYAN);
    add(knappTurkis);
    /*
     * Lager en lytter og knytter alle tre
     * knappene til denne lytteren
     */
    Knappelytter knappelytteren = new Knappelytter();
    knappR�d.addActionListener(knappelytteren);
    knappBl�.addActionListener(knappelytteren);
    knappTurkis.addActionListener(knappelytteren);

    add(hilsen);
    pack();
  }

  private class Knappelytter implements ActionListener {
    public void actionPerformed(ActionEvent hendelse) {
      JButton valgtKnapp = (JButton) hendelse.getSource();
      String fargenavn = valgtKnapp.getText();
      Color farge;
      if (fargenavn.equals("R�d")) {
        farge = Color.RED;
      } else if (fargenavn.equals("Bl�")) {
        farge = Color.BLUE;
      } else {
        farge = Color.CYAN;
      }
      hilsen.setForeground(farge);
      String navn = navnefelt.getText();
      hilsen.setText("Hei p� deg, " + navn + "!");
    }
  }
}

class TestNavneVindu3Knapper {
  public static void main(String[] args) {
    NavneVindu3Knapp etVindu =
                   new NavneVindu3Knapp("Tekster og tre knapper");
    etVindu.setVisible(true);
  }
}