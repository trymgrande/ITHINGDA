/**
 * TelleVindu.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Vinduet brukes på klientsiden til å registrere data.
 * Klienten må være koblet opp på forhånd. Klienten kobles ned når vinduet lukkes.
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
class TelleVindu extends JFrame {
  private static final int STATUSVINDU_X = 200;
  private static final int STATUSVINDU_Y = 500;
  private static final int BREDDE_STATUSVINDU = 500;
  private static final int HØYDE_STATUSVINDU = 80;
  private static final int TELLEVINDU_X = 200;
  private static final int TELLEVINDU_Y = 200;

  private JLabel statuslinje1 =
     new JLabel("Her kommer oppdateringer fra tjeneren.");
  private JLabel statuslinje2 = new JLabel("");
  private JLabel statuslinje3 = new JLabel("");

  private JTextField antall = new JTextField(8);
  private JRadioButton jaKnapp = new JRadioButton("Ja-stemmer", true);
  private JRadioButton neiKnapp = new JRadioButton("Nei-stemmer", false);
  private JButton lagreknapp = new JButton("Lagre");
  private TellemaskinFront tellemaskin = null;
  private String klientnavn = null;

  public TelleVindu(TellemaskinFront tellemaskin, String klientnavn) {
    try {
      this.tellemaskin = tellemaskin;
      this.klientnavn = klientnavn;

      setTitle("Klient: " + klientnavn);
      addWindowListener(new Vinduslytter());
      setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

      add(new StatusPanel(), BorderLayout.NORTH);
      add(new InputPanel(), BorderLayout.CENTER);
      add(lagreknapp, BorderLayout.SOUTH);
      pack();

      setLocation(TELLEVINDU_X, TELLEVINDU_Y);

      Knappelytter knappelytter = new Knappelytter();
      lagreknapp.setMnemonic('L');
      lagreknapp.addActionListener(knappelytter);

      antall.requestFocusInWindow();
    } catch (Exception e) {
      System.out.println("Feil i Tellevindu-konstruktøren: " + e);
      e.printStackTrace();
    }
  }

  public void setStatus(String[] status) {
    statuslinje1.setText(status[0]);
    statuslinje2.setText(status[1]);
    statuslinje3.setText(status[2]);
  }

  /* Beskriver nordre panel */
  private class StatusPanel extends JPanel {
    public StatusPanel() {
      setLayout(new GridLayout(3, 1));
      add(statuslinje1);
      add(statuslinje2);
      add(statuslinje3);
      SoftBevelBorder ramme = new SoftBevelBorder(BevelBorder.RAISED);
      Border boks = BorderFactory.createTitledBorder(ramme,
                            "Status fra tjenersiden");
      setBorder(boks);
    }
  }

  /* Beskriver midtre panel */
  private class InputPanel extends JPanel {
    public InputPanel() {
      setLayout(new GridLayout(2, 2));
      add(new JLabel("Antall stemmer: "));
      add(antall);
      ButtonGroup gruppe = new ButtonGroup();
      gruppe.add(jaKnapp);
      gruppe.add(neiKnapp);
      add(jaKnapp);
      add(neiKnapp);
      jaKnapp.setMnemonic('J');
      neiKnapp.setMnemonic('N');
      SoftBevelBorder ramme = new SoftBevelBorder(BevelBorder.RAISED);
      Border boks = BorderFactory.createTitledBorder(ramme, "Registrer stemmer");
      setBorder(boks);
    }
  }

  private class Knappelytter implements ActionListener {
    public void actionPerformed(ActionEvent hendelse) {
      int antallStemmer = 0;
      try {
        antallStemmer = Integer.parseInt(antall.getText());
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Ugyldig tallformat");
        antall.requestFocusInWindow();
      }
      try {
        if (jaKnapp.isSelected()) {
          tellemaskin.økAntallJa(antallStemmer);
        } else {
          tellemaskin.økAntallNei(antallStemmer);
        }
      } catch (Exception e) {
        System.out.println("Feil oppstått i lytteren til lagreknappen: " + e);
        e.printStackTrace();
      }
      antall.setText("");
      antall.requestFocusInWindow();
    }
  }

  private class Vinduslytter extends WindowAdapter {
    public void windowClosing(WindowEvent hendelse) {
      System.out.println("Prøver utmelding");
      try {
        tellemaskin.meldMegUt(klientnavn);
        System.out.println("Utmelding ok");
      } catch (Exception e) {
        System.out.println("Feil i Vinduslytter: " + e);
        e.printStackTrace();
      }
      dispose();
      System.exit(0);
    }
  }
}