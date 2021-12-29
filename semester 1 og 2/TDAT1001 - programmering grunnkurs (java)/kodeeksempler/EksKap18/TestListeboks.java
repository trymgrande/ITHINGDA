/**
 * TestListeboks.java  - "Programmering i Java", 4.utgave - 2012-02-29
 *
 * Viser en flervalgsliste der brukeren kan velge byer.
 * Valgene vises under listen.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class ListeboksVindu extends JFrame {
  private static final String [] BYER =
    {"Arendal", "Bergen", "Bodø", "Gjøvik", "Hamar", "Hammerfest", "Kristiansund",
     "Moss", "Stavanger", "Tromsø", "Trondheim", "Ålesund"};
  private JTextField tekst = new JTextField("Du har ennå ikke valgt byer.     ");
  private JList<String> byliste = new JList<String>(BYER);  // Nå er listen laget!

  public ListeboksVindu(String tittel) {
    setTitle(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JLabel ledetekst = new JLabel("Velg en eller flere byer.");
    add(ledetekst, BorderLayout.NORTH);

    /* Legger på rullefelt */
    JScrollPane rullefeltMedListe = new JScrollPane(byliste);
    add(rullefeltMedListe, BorderLayout.CENTER);

    ListeboksLytter lytter = new ListeboksLytter();
    byliste.addListSelectionListener(lytter);

    tekst.setEditable(false); // brukeren skal ikke kunne redigere i feltet
    add(tekst, BorderLayout.SOUTH);
    pack();
  }

  /* Lytteren fanger opp alle klikk på linjer i listeboksen */
  private class ListeboksLytter implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent hendelse) {
      // Object[] verdier = byliste.getSelectedValues();  - deprecated i Java 7
      Object[] verdier = byliste.getSelectedValuesList().toArray();
      String nyTekst = "Du har nå valgt " + verdier.length;
      nyTekst += (verdier.length == 1) ? " by." :  " byer.";
      tekst.setText(nyTekst);
    }
  }
}

class TestListeboks {
  public static void main(String[] args) {
    ListeboksVindu etVindu = new ListeboksVindu("Valg av byer");
    etVindu.setVisible(true);
  }
}