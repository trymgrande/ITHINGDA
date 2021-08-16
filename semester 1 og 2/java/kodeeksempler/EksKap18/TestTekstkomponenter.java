/**
 * TestTekstkomponenter.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Brukeren gis tilgang til å redigere i et større tekstområde dersom riktig
 * brukernavn og passord skrives inn. Subklasser til InputVerifier brukes for
 * å kontrollere inndataene.
 *
 * Filen inneholder klassen TekstVindu med indre klasser for de ulike delene
 * av vinduet, samt lytterklasser. Klassen TestTekstkomponenter inneholder main().
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class TekstVindu extends JFrame {

  private static final String STANDARDTEKST =
    "Du kan skrive noe her dersom du skriver inn riktig passord.";
  private JTextField brukernavnFelt = new JTextField(15);
  private JPasswordField passordFelt = new JPasswordField(15);
  private JTextArea tekstfelt = new JTextArea(10, 20);
  private JButton skriveknapp = new JButton("Skriv");
  private JLabel meldingsfelt = new JLabel("Her kommer meldinger ...");

  public TekstVindu(String tittel) {
    setTitle(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout(5, 5)); // mellomrom mellom panelene
    add(new InnloggingsPanel(), BorderLayout.NORTH);
    add(new TekstomraadePanel(), BorderLayout.CENTER);
    add(new KnappePanel(), BorderLayout.SOUTH);
    pack();
  }

  /* Beskriver nordre panel */
  private class InnloggingsPanel extends JPanel {
    public InnloggingsPanel() {
      setLayout(new GridLayout(2, 2, 5, 5));
      add(new JLabel("Brukernavn:", JLabel.RIGHT));
      add(brukernavnFelt);
      brukernavnFelt.setInputVerifier(new BrukernavnKontroll());
      add(new JLabel("Passord:", JLabel.RIGHT));
      add(passordFelt);
      passordFelt.setInputVerifier(new PassordKontroll());
      passordFelt.addFocusListener(new Fokuslytter());
    }
  }

  /* Beskriver midtre panel */
  private class TekstomraadePanel extends JPanel {
    public TekstomraadePanel() {
      tekstfelt.setLineWrap(true);
      tekstfelt.setWrapStyleWord(true);
      tekstfelt.setEditable(false);
      tekstfelt.setText(STANDARDTEKST);
      JScrollPane rullefelt = new JScrollPane(tekstfelt);
      add(rullefelt);
    }
  }

  /* Beskriver søndre panel */
  private class KnappePanel extends JPanel {
    public KnappePanel() {
      KnappeLytter knappelytteren = new KnappeLytter();
      skriveknapp.addActionListener(knappelytteren);
      skriveknapp.setEnabled(false);
      skriveknapp.setMnemonic('S');
      add(skriveknapp);
      add(meldingsfelt);
    }
  }

  /* Kontrollerer brukernavn */
  class BrukernavnKontroll extends InputVerifier {
    public boolean verify(JComponent inndata) {
      JTextField tekstfelt = (JTextField) inndata;
      String data = tekstfelt.getText();
      return (data.equals("test"));
    }

    public boolean shouldYieldFocus(JComponent inndata) {
      boolean ok = super.shouldYieldFocus(inndata);
      if (ok) {
        meldingsfelt.setText("Brukernavn ok");
      } else {
        meldingsfelt.setText("Ugyldig brukernavn");
      }
      return ok;
    }
  }

  /* Kontrollerer passordet */
  class PassordKontroll extends InputVerifier {
    public boolean verify(JComponent inndata) {
      char[] riktigPassord = {'t', 'e', 's', 't'};
      JPasswordField tekstfelt = (JPasswordField) inndata;
      char[] passord = tekstfelt.getPassword();
      return (Arrays.equals(passord, riktigPassord));
    }

    public boolean shouldYieldFocus(JComponent inndata) {
      boolean ok = super.shouldYieldFocus(inndata);
      if (ok) {
        meldingsfelt.setText("Ok. Du kan begynne å skrive.");
      } else {
        meldingsfelt.setText("Ugyldig passord");
      }
      return ok;
    }
  }

  /*
   * Dersom brukernavn og passord er riktig, skal tekstområdet åpnes. Nå er det ikke
   * mulig å forlate brukernavnfeltet før brukernavnet er ok, og det er heller ikke
   * mulig å forlate passordfeltet før passordet er ok. Vi lager derfor en lytter
   * som lytter etter den hendelsen at fokus forlater passordfeltet.
   */
  private class Fokuslytter implements FocusListener {
    public void focusLost(FocusEvent hendelse) {
      skriveknapp.setEnabled(true);
      tekstfelt.setEditable(true);
      tekstfelt.requestFocusInWindow();
    }
    public void focusGained(FocusEvent hendelse) {
    }
  }

  /* Beskriver objekter som lytter etter knappetrykk */
  private class KnappeLytter implements ActionListener {
    public void actionPerformed(ActionEvent hendelse) {
      String tekst = tekstfelt.getText();
      System.out.println("Teksten ser slik ut: ");
      System.out.println(tekst);
    }
  }
}

class TestTekstkomponenter {
  public static void main(String[] args) {
    TekstVindu etVindu = new TekstVindu("Ulike tekstkomponenter");
    etVindu.setVisible(true);
  }
}