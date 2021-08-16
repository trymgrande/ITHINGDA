/**
 * KnapperadTest.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Viser en rad med tre knapper; en gul, en rød og en blå.
 * Et trykk på en knapp setter bakgrunnsfargen til den valgte farge.
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class VinduMedKnapperad extends JFrame {
  private Container guiBeholder;  // for å sette bakgrunnsfargen
  private JButton gulKnapp;
  private JButton rødKnapp;
  private JButton blåKnapp;

  public VinduMedKnapperad(){
    setTitle("Knapperad-test");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    guiBeholder = getContentPane();

    KnappeLytter lytteren = new KnappeLytter();
    JToolBar knapperad = new JToolBar();
    Icon ikon = new ImageIcon("gul.gif");
    gulKnapp = new JButton(ikon);
    gulKnapp.addActionListener(lytteren);
    knapperad.add(gulKnapp);

    ikon = new ImageIcon("roed.gif");
    rødKnapp = new JButton(ikon);
    rødKnapp.addActionListener(lytteren);
    knapperad.add(rødKnapp);

    ikon = new ImageIcon("blaa.gif");
    blåKnapp = new JButton(ikon);
    blåKnapp.addActionListener(lytteren);
    knapperad.add(blåKnapp);

    add(knapperad, BorderLayout.NORTH);
  }

  private class KnappeLytter implements ActionListener {
    public void actionPerformed(ActionEvent hendelse) {
      JButton knapp = (JButton) hendelse.getSource();
      if (knapp == gulKnapp) {
        guiBeholder.setBackground(Color.YELLOW);
      } else if (knapp == rødKnapp) {
        guiBeholder.setBackground(Color.RED);
      } else {
        guiBeholder.setBackground(Color.BLUE);
      }
    }
  }
}

class KnapperadTest {
  public static void main(String[] args) {
    VinduMedKnapperad vindu = new VinduMedKnapperad();
    vindu.setSize(300, 200); // ellers vil det ikke vises på skjermen
    vindu.setVisible(true);
  }
}