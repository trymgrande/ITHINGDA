/**
 * OppussingKap19GUI.java  - "Programmering i Java", 4.utgave - 2012-02-29
 *
 * Dataene lagres i ArrayLister i et objekt av klassen Oppussingsprosjekt.
 * Dette objektet kalles "prosjektet".
 *
 * Tabellen og listen er hovedkomponentene i vinduet:
 *
 * Tabellen:
 * GUI-komponenten heter flateliste og tilhører klassen JTable.
 * Datamodellobjektet heter flatedata og tilhører klassen EgenTabellModell.
 *
 * Listen:
 * GUI-komponenten heter materialliste og tilhører klassen JList.
 * Datamodellobjektet heter materialdata og tilhører klassen EgenListeModell.
 *
 * Indeksene i ArrayListene i prosjektet er de samme som radnummer
 * i flateliste og materialliste.
 */

import java.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Formatter;  // for utskrift i flatetabellen og tekstboksen nederst
import oppussingsprosjekt.*;
import static javax.swing.JOptionPane.*;

class OppussingKap19GUI extends JFrame {
  private JToolBar knapperad = new JToolBar();
  private JButton knappKombiner; // se lagKnapperad()

  private Oppussingsprosjekt prosjektet;

  /* Materialdata */
  private EgenListeModell materialdata;
  private JList<Materiale> materialliste;
  private JScrollPane rulleMaterialliste;

  /* Flatedata */
  private EgenTabellModell flatedata;
  private JTable flateliste;

  private JTextField sum = new JTextField(Konstanter.TEKSTLENGDE_SUM);

  private FlateDialog flateDialogen = new FlateDialog(this);
  private MalingDialog malingDialogen = new MalingDialog(this);
  private BeleggDialog beleggDialogen = new BeleggDialog(this);
  private TapetDialog tapetDialogen = new TapetDialog(this);

  public OppussingKap19GUI(Oppussingsprosjekt prosjektet) {
    setTitle("Oppussing");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.prosjektet = prosjektet; // OBS! forbindelse til problemområdeklassene

    /* Knytter forbindelser fra GUI-komponent til datamodell til prosjektet */
    materialdata = new EgenListeModell(prosjektet);
    materialliste = new JList<Materiale>(materialdata);
    rulleMaterialliste = new JScrollPane(materialliste);
    flatedata = new EgenTabellModell(prosjektet);
    flateliste = new JTable(flatedata);

    initierFlateOgMaterialliste();
    lagKnapperad();
    lagMenyen();

    setLayout(new GridBagLayout());
    leggUtGUIkomponentene();
    pack();

    flateDialogen.setLocation(Konstanter.DIALOG_X, Konstanter.DIALOG_Y);
    malingDialogen.setLocation(Konstanter.DIALOG_X, Konstanter.DIALOG_Y);
    beleggDialogen.setLocation(Konstanter.DIALOG_X, Konstanter.DIALOG_Y);
    tapetDialogen.setLocation(Konstanter.DIALOG_X, Konstanter.DIALOG_Y);
    setLocation(Konstanter.VINDU_X, Konstanter.VINDU_Y);
    setResizable(false); // brukeren kan ikke endre størrelsen på vinduet
  }

  /* Klassen beskriver lyttere til knappene i knapperaden. */
  private class Knappelytter implements ActionListener  {
    public void actionPerformed(ActionEvent hendelse) {
      String kommando = hendelse.getActionCommand();
      KnappeInfo valg = KnappeInfo.valueOf(kommando);

      switch (valg) {
      case Flate:
        Flate nyFlate = flateDialogen.visDialog();
        if (nyFlate != null) {
          Flate flaten = prosjektet.registrerNyFlate(nyFlate);
          if (flaten == nyFlate) {
            flatedata.fireTableDataChanged();
          } else {
            showMessageDialog(OppussingKap19GUI.this,
                nyFlate.getNavn() + " er registrert fra før.");
          }
        }
        break;
      case Maling:
        Maling nyMaling = malingDialogen.visDialog();
        registrerMateriale(nyMaling);
        break;
      case Belegg:  // nytt belegg
        Belegg nyttBelegg = beleggDialogen.visDialog();
        registrerMateriale(nyttBelegg);
        break;
      case Tapet:  // ny tapet
        Tapet nyTapet = tapetDialogen.visDialog();
        registrerMateriale(nyTapet);
        break;
      case Kombiner: // kombiner flate og materiale, og beregn ny sum
        int flateindeks = flateliste.getSelectedRow();
        Materiale materiale =
             prosjektet.finnMateriale(materialliste.getSelectedIndex());
        if (flateindeks < 0 || materiale == null) {
          showMessageDialog(OppussingKap19GUI.this,
            "Du må velge både flate og materiale før du kan kombinere dem!");
        } else {
          Flate flaten = prosjektet.finnFlate(flateindeks);
          flaten.setMaterialtype(materiale);
          flatedata.fireTableDataChanged();
          Formatter utformat = new Formatter();
          String totalprisS = utformat.format(
                Konstanter.FORMAT, prosjektet.beregnTotalpris()).toString();
          sum.setText(totalprisS);
        }
        break;
      case Avslutt:
        System.exit(0);
        break;
      default:
        System.out.println("Hit skal ikke programmet komme!");
        break;
      }
      if (flatedata.getRowCount() > 0 && materialdata.getSize() > 0) {
        knappKombiner.setEnabled(true);
      }
    }

    /* Hjelpemetode, brukes i switch-setningen foran */
    private void registrerMateriale(Materiale nyttMateriale) {
      if (nyttMateriale != null) {
        Materiale materialet = prosjektet.registrerNyttMateriale(nyttMateriale);
        if (materialet == nyttMateriale) {
          int indeks = prosjektet.finnAntMaterialer() - 1;
          materialdata.registrerNyttElement(this, indeks);
        } else {
          showMessageDialog(OppussingKap19GUI.this,
                           nyttMateriale.getNavn() + " er registrert fra før.");
        }
      }
    }
  }

  /* Klassen beskriver lyttere til menyvalgene. */
  private class Menylytter implements ActionListener  {
    public void actionPerformed(ActionEvent hendelse) {
      String kommando = hendelse.getActionCommand();
      Hjelpemeny valg = Hjelpemeny.finnValg(kommando);
      if (valg == Hjelpemeny.VALG_HJELP) {
        showMessageDialog(OppussingKap19GUI.this, valg.getInnhold());
      } else {
        showMessageDialog(OppussingKap19GUI.this, valg.getInnhold());
      }
    }
  }

  /* Metoden initierer flateliste og materialliste*/
  private void initierFlateOgMaterialliste() {

    /* Setter en ikke-proporsjonal skrifttype */
    Font standardFont = flateliste.getFont();
    Font nyFont = new Font("Monospaced",
        standardFont.getStyle(), standardFont.getSize());
    flateliste.setFont(nyFont);
    sum.setFont(nyFont);

    flateliste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    materialliste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    /* Setter størrelsen på vinduet som flateliste vises i*/
    flateliste.setPreferredScrollableViewportSize(
                                                Konstanter.STR_FLATELISTEVINDU);
    /* Overskrift for materialtabellen */
    JViewport jvp = new JViewport(); // se online API-dokumentasjonen
    jvp.setView(new JLabel("Materialer"));
    rulleMaterialliste.setColumnHeader(jvp);
  }

  private void lagKnapperad() {
    knapperad.setFloatable(false);
    Knappelytter knappelytteren = new Knappelytter();
    for (KnappeInfo info : KnappeInfo.values()) {
      Icon ikon = new ImageIcon(Konstanter.IKONKATALOG + info.getIkonfil());
      JButton knapp = new JButton(info.toString(), ikon);
      knapp.setToolTipText(info.getBeskrivelse());
      knapp.setMnemonic(info.getMnemonic());
      knapp.addActionListener(knappelytteren);
      knapperad.add(knapp);
      if (info == KnappeInfo.Kombiner) {
        knappKombiner = knapp;
      }
    }
    knappKombiner.setEnabled(false);
  }

  private void lagMenyen() {
    Menylytter menylytteren = new Menylytter();
    JMenu meny = new JMenu(Hjelpemeny.MENYNAVN);
    meny.setMnemonic(Hjelpemeny.MENYNAVN_MNEMONIC);
    for (Hjelpemeny menypost : Hjelpemeny.values()) {
      JMenuItem menylinje = meny.add(menypost.toString());
      menylinje.setMnemonic(menypost.getMnemonic());
      menylinje.addActionListener(menylytteren);
    }
    JMenuBar mbar = new JMenuBar();

    /* Endrer layouthåndterer slik at menyen kommer til høyre */
    mbar.setLayout(new FlowLayout(FlowLayout.RIGHT));
    mbar.add(meny);
    setJMenuBar(mbar);
  }

  private void leggUtGUIkomponentene() {
    GridBagConstraints krav = new GridBagConstraints();
    krav.insets = new Insets(5, 5, 5, 5);  // luft rundt en komponent
    krav.weightx = 0.5;
    krav.weighty = 0.5;

    /* Knapperaden */
    krav.gridx = 0;
    krav.gridy = 0;
    krav.gridwidth = 4;
    krav.gridheight = 1;
    krav.fill = GridBagConstraints.NONE;
    krav.anchor = GridBagConstraints.WEST;
    add(knapperad, krav);

    /* Tabellen flateliste */
    krav.gridx = 0;
    krav.gridy = 1;
    krav.gridwidth = 3;
    krav.gridheight = 1;
    krav.fill = GridBagConstraints.BOTH;
    krav.anchor = GridBagConstraints.CENTER;
    add(new JScrollPane(flateliste), krav);

    /* Tabellen materialliste */
    krav.gridx = 3;
    krav.gridy = 1;
    krav.gridwidth = 1;
    krav.gridheight = 1;
    krav.fill = GridBagConstraints.BOTH;
    krav.anchor = GridBagConstraints.CENTER;
    add(rulleMaterialliste, krav);


    /* Ledeteksten */
    krav.gridx = 1;
    krav.gridy = 2;
    krav.gridwidth = 1;
    krav.gridheight = 1;
    krav.fill = GridBagConstraints.NONE;
    krav.anchor = GridBagConstraints.EAST;
    add(new JLabel("Totalpris: "), krav);

    /* Tekstboksen sum */
    krav.gridx = 2;
    krav.gridy = 2;
    krav.gridwidth = 1;
    krav.gridheight = 1;
    krav.fill = GridBagConstraints.HORIZONTAL;
    krav.anchor = GridBagConstraints.WEST;
    add(sum, krav);
    sum.setEditable(false); // ikke mulig for brukeren å redigere
  }
}