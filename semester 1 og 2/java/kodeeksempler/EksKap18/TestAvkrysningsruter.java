/**
 * TestAvkrysningsruter.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Viser et vindu med to avkrysningsruter (JCheckBox) i en gruppeboks
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class AvkrysningsruteVindu extends JFrame {
  private JCheckBox middag = new JCheckBox("middag");
  private JCheckBox lunsj = new JCheckBox("lunsj");

  public AvkrysningsruteVindu(String tittel) {
    setTitle(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    /*
     * Vi legger et tomt panel i nord og et i sør for at
     * gruppeboksen rundt avmerkingsknappene skal vises.
     */
    add(new JPanel(), BorderLayout.NORTH);
    add(new JPanel(), BorderLayout.SOUTH);

    ValgPanel midten = new ValgPanel();
    add(midten, BorderLayout.CENTER);
    pack();
  }

  /* Beskriver panelet med avkrysningsrutene */
  private class ValgPanel extends JPanel {
    public ValgPanel() {
      add(middag);
      add(lunsj);
      CheckBoxLytter lytter = new CheckBoxLytter();
      middag.addActionListener(lytter);
      lunsj.addActionListener(lytter);

      /* Lager en ramme rundt panelet */
      SoftBevelBorder ramme = new SoftBevelBorder(BevelBorder.RAISED);
      Border gruppeboks = BorderFactory.createTitledBorder(ramme, "Måltider");
      setBorder(gruppeboks);
    }
  }

  /* Lytterobjekter som lytter til alle endringer i avkrysningsrutene */
  private class CheckBoxLytter implements ActionListener {
    public void actionPerformed(ActionEvent hendelse) {
      System.out.println();
      if (middag.isSelected()) {
        System.out.println("Skal ha middag.");
      }
      if (lunsj.isSelected()) {
        System.out.println("Skal ha lunsj.");
      }
    }
  }
}

class TestAvkrysningsruter {
  public static void main(String[] args) {
    AvkrysningsruteVindu etVindu =
               new AvkrysningsruteVindu("Middag og/eller lunsj?");
    etVindu.setVisible(true);
  }
}