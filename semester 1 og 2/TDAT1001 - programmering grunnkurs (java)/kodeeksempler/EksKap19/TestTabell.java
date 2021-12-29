/**
 * TestTabell.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Viser byer og folketall i en tabell (JTable). Brukeren kan velge en linje
 * av gangen fra tabellen. Valget vises under listen.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class TabellVindu extends JFrame {
  static final String [][] BYER = {{"Arendal", "39247"}, {"Bergen", "227276"},
  {"Bodø", "40767"}, {"Gjøvik", "26968"}, {"Hamar", "26424"}, {"Hammerfest",
  "9151"}, {"Kristiansund", "16928"}, {"Moss", "26242"}, {"Stavanger", "108019"},
  {"Tromsø", "58121"}, {"Trondheim", "147187"}, {"Ålesund", "38251"}};

  static final String [] KOLONNENAVN = {"By", "Folketall"};

  private JLabel valget = new JLabel("Du har ennå ikke valgt byer.");
  private JTable bytabell = new JTable(BYER, KOLONNENAVN);  // lager tabellen

  public TabellVindu(String tittel) {
    setTitle(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JLabel ledetekst = new JLabel("Velg en by.");
    add(ledetekst, BorderLayout.NORTH);

    /* Legger tabellen inn i et rullefelt */
    JScrollPane rullefeltMedTabell = new JScrollPane(bytabell);
    add(rullefeltMedTabell, BorderLayout.CENTER);

    /* Setter størrelsen på vinduet tabellen vises i */
    bytabell.setPreferredScrollableViewportSize(new Dimension(300, 100));

    /* Setter valgmodellen slik at brukeren kun kan velge én linje av gangen */
    bytabell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    /* Lytteren må kobles til den tilhørende valgmodellen. */
    ListSelectionModel linjevalg = bytabell.getSelectionModel();
    LinjeLytter lytter = new LinjeLytter();
    linjevalg.addListSelectionListener(lytter);

    add(valget, BorderLayout.SOUTH);
    pack();
  }

  /* Lytteren fanger opp alle klikk på linjer i tabellen. */
  private class LinjeLytter implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent hendelse) {
      int linje = bytabell.getSelectedRow();
      valget.setText("Du har nå valgt " + bytabell.getValueAt(linje, 0) +
                     " med " + bytabell.getValueAt(linje, 1) + " innbyggere.");
    }
  }
}

class TestTabell {
  public static void main(String[] args) {
    TabellVindu etVindu = new TabellVindu("Valg av by");
    etVindu.setVisible(true);
  }
}