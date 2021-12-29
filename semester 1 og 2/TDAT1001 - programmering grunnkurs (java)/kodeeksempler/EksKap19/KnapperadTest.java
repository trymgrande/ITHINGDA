/**
 * KnapperadTest.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Viser en rad med tre knapper; en gul, en r�d og en bl�.
 * Et trykk p� en knapp setter bakgrunnsfargen til den valgte farge.
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class VinduMedKnapperad extends JFrame {
  private Container guiBeholder;  // for � sette bakgrunnsfargen
  private JButton gulKnapp;
  private JButton r�dKnapp;
  private JButton bl�Knapp;

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
    r�dKnapp = new JButton(ikon);
    r�dKnapp.addActionListener(lytteren);
    knapperad.add(r�dKnapp);

    ikon = new ImageIcon("blaa.gif");
    bl�Knapp = new JButton(ikon);
    bl�Knapp.addActionListener(lytteren);
    knapperad.add(bl�Knapp);

    add(knapperad, BorderLayout.NORTH);
  }

  private class KnappeLytter implements ActionListener {
    public void actionPerformed(ActionEvent hendelse) {
      JButton knapp = (JButton) hendelse.getSource();
      if (knapp == gulKnapp) {
        guiBeholder.setBackground(Color.YELLOW);
      } else if (knapp == r�dKnapp) {
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
    vindu.setSize(300, 200); // ellers vil det ikke vises p� skjermen
    vindu.setVisible(true);
  }
}