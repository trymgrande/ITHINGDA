/**
 * TestListeboks2.java  - "Programmering i Java", 4.utgave - 2012-02-29
 *
 * Viser en listeboks der nye linjer kan legges inn, og eksisterende linjer kan fjernes.
 * For � f� til dette m� en oppdatere modellen som ligger bak JList. Modellobjektet
 * inneholder de dataene som vises fram i en JList.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import static javax.swing.JOptionPane.*;

class ListeboksVindu2 extends JFrame {
  private DefaultListModel<String> listeinnhold = new DefaultListModel<String>(); // "datamodellen"
  private JList<String> liste = new JList<String>(listeinnhold);

  public ListeboksVindu2(String tittel) {
    setTitle(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(new TekstPanel(), BorderLayout.NORTH);
    add(new ListePanel(), BorderLayout.CENTER);
    add(new JButton(), BorderLayout.SOUTH);  // for � f� litt luft
    pack();
  }

  /* Beskriver ledetekstene �verst i vinduet */
  private class TekstPanel extends JPanel {
    public TekstPanel() {
      setLayout(new GridLayout(4, 1, 2, 2));
      add(new JLabel(""));  // for � f� inn litt luft
      add(new JLabel("Velg \"Nytt navn\" for � legge inn nytt navn."));
      add(new JLabel("Klikk p� et navn fjerner dette navnet fra listen."));
      add(new JLabel(""));  // for � f� inn litt luft
    }
  }

  /* Bygger opp listen som i begynnelsen best�r av bare en linje: "Nytt navn" */
  private class ListePanel extends JPanel {
    public ListePanel() {
      /* Det er vanskelig � styre bredden p� en liste. Vi jukser litt ... */
      setLayout(new BorderLayout());
      add(new JButton(), BorderLayout.WEST);  // for � fylle opp p� venstre side ...
      listeinnhold.addElement("Nytt navn");  // legger linjen inn i datamodellen
      liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      JScrollPane rullefeltMedListe = new JScrollPane(liste); // rullefelt
      add(rullefeltMedListe, BorderLayout.CENTER);
      liste.addListSelectionListener(new ListeboksLytter()); // lytter til listevalg
      add(new JButton(), BorderLayout.EAST);  // ... og p� h�yre side
    }
  }

  /* Lytter etter valg som gj�res i listen */
  private class ListeboksLytter implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent hendelse) {
      int valg = liste.getSelectedIndex();
      if (valg >= 0) {
        liste.clearSelection();
        if (valg == 0) {  // nytt navn er valgt
          String nyttNavn = showInputDialog("Skriv nytt navn: ");
          if (nyttNavn != null) {
            listeinnhold.addElement(nyttNavn);
          }
        } else {  // skal fjerne eksisterende navn
          String navnSomFjernes = (String) listeinnhold.get(valg);
          listeinnhold.remove(valg);
          showMessageDialog(ListeboksVindu2.this,
                         "N� er " + navnSomFjernes + " fjernet fra listen.");
        }
      }
    }
  }
}

class TestListeboks2 {
  public static void main(String[] args) {
    ListeboksVindu2 etVindu = new ListeboksVindu2("Dynamisk navneliste");
    etVindu.setVisible(true);
  }
}