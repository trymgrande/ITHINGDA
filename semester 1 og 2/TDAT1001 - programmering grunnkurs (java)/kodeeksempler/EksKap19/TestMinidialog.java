/**
 * TestMinidialog.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Filen inneholder et vindu med én trykknapp (klassen ForeldreVindu).
 * Et trykk på knappen fører til at en dialog (klassen MiniDialog) med to knapper
 * (OK og Avbryt) kommer opp. Trykk på disse knappene medfører retur til
 * foreldrevinduet etterfulgt av utskrift i kommandovinduet.
 * Lukking av dialogen fungerer som om Avbryt-knappen var trykket.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * En dialog av enkleste type. Den inneholder en OK- og en Avbryt-knapp.
 * Knappene ligger i et eget knappepanel.
 */
class MiniDialog extends JDialog {
  private boolean ok;

  public MiniDialog(JFrame forelder) {
    /*
     * Konstruktøren til superklassen, JDialog, tar tre argumenter:
     * en referanse til foreldrevinduet, dialogens tittel og hvorvidt dialogen
     * skal være modal eller ikke. Alle våre dialoger er modale.
     */
    super(forelder, "Minidialog", true);
    add(new JLabel(), BorderLayout.NORTH);
    add(new JLabel("Vi tester en dialog."), BorderLayout.CENTER);
    add(new KnappePanel(), BorderLayout.SOUTH);
    pack();
  }

  private class KnappePanel extends JPanel {
    public KnappePanel() {
      KnappelytterDialog knappelytter = new KnappelytterDialog();

      JButton okKnapp = new JButton("OK");
      add(okKnapp);
      okKnapp.addActionListener(knappelytter);

      JButton avbrytKnapp = new JButton("Avbryt");
      add(avbrytKnapp);
      avbrytKnapp.addActionListener(knappelytter);
    }
  }

  private class KnappelytterDialog implements ActionListener {
    public void actionPerformed(ActionEvent hendelse) {
      String kommando = hendelse.getActionCommand();
      ok = kommando.equals("OK");
      setVisible(false);
    }
  }

  /*
   * Dialogen vises fram når foreldrevinduet kaller denne metoden.
   */
  public boolean visDialog() {
    setVisible(true);  // venter her til setVisible(false) er kalt
    return ok;
  }
}

/**
 * Foreldrevinduet til dialogen. Dette vinduet inneholder kun én knapp.
 */
class ForeldreVindu extends JFrame {
  private MiniDialog dialogboks = new MiniDialog(this);

  public ForeldreVindu() {
    setTitle("Dialogtest");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout());
    JButton knapp = new JButton("Trykk her!");
    add(knapp);
    KnappeLytterForeldre knappelytter = new KnappeLytterForeldre();
    knapp.addActionListener(knappelytter);
  }

  private class KnappeLytterForeldre implements ActionListener {
    public void actionPerformed(ActionEvent hendelse) {
      if (dialogboks.visDialog()) {
        System.out.println("OK trykket ...");
      } else {
        System.out.println("Avbryt trykket ...");
      }
    }
  }
}

class TestMinidialog {
  static public void main(String[] args) {
    ForeldreVindu foreldrevindu = new ForeldreVindu();
    foreldrevindu.setSize(300, 200);
    foreldrevindu.setVisible(true);
  }
}
