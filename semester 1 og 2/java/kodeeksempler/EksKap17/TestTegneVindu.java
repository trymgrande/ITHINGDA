/**
 * TestTegneVindu.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Tegner en ellipse der brukeren bestemmer størrelse, plassering og farge.
 * Ingen kontroll på at ellipsen havner innenfor vinduet.
 *
 * Filen inneholder flere klasser:
 * VinduMedtegning: Inneholder tre paneler, ett for inndata, ett for ellipsen, og ett
 * med knapper for fargevalg. Panelene er beskrevet i indre klasser. Vi har også en
 * indre klasse som beskriver en knappelytter.
 * Tegning: Tegner en ellipse etter gitte spesifikasjoner.
 * TestTegneVindu: Inneholder main()-metode som viser fram vinduet.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class VinduMedTegning extends JFrame {

  /* Ellipseattributter som brukes første gang ellipsen tegnes ut */
  private static final String X = "70";
  private static final String Y = "50";
  private static final String BREDDE = "50";
  private static final String HØYDE = "60";

  /*
   * Følgende variabler brukes i konstruktøren, i lytter-klassen,
   * og i BrukerInputPanel-klassen:
   */
  private Tegning tegningen;
  private JTextField xFelt = new JTextField(X);
  private JTextField yFelt = new JTextField(Y);
  private JTextField breddeFelt = new JTextField(BREDDE);
  private JTextField høydeFelt = new JTextField(HØYDE);

  public VinduMedTegning(String tittel) {  // Standard layouthåndterer BorderLayout
  JFrame.setDefaultLookAndFeelDecorated(true);
    setTitle(tittel);
    setSize(400, 300); // på grunn av tegningen kan vi ikke bruke pack()
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    BrukerInputPanel nord = new BrukerInputPanel();
    add(nord, BorderLayout.NORTH);

    tegningen = new Tegning(X, Y, BREDDE, HØYDE);
    add(tegningen, BorderLayout.CENTER);

    Knappepanel sør = new Knappepanel();
    add(sør, BorderLayout.SOUTH);
  }

  /* Lytter etter knappetrykk */
  private class Knappelytter implements ActionListener {
    public void actionPerformed(ActionEvent hendelse) {

      /* Henter nye ellipseattributter og sender dem til tegningen */
      tegningen.setX(xFelt.getText());
      tegningen.setY(yFelt.getText());
      tegningen.setBredde(breddeFelt.getText());
      tegningen.setHøyde(høydeFelt.getText());

      /* Finner fargen til knappen */
      JButton valgtKnapp = (JButton) hendelse.getSource();
      String fargenavn = valgtKnapp.getText();
      Color farge;
      if (fargenavn.equals("Rød")) {
        farge = Color.RED;
      } else if (fargenavn.equals("Blå")) {
          farge = Color.BLUE;
      } else {
        farge = Color.CYAN;
      }

      /* Setter forgrunnsfargen på tegningen */
      tegningen.setForeground(farge);

      /* Tegner hele vinduet på nytt */
      VinduMedTegning.this.repaint();
    }
  }

  /* Beskriver nordre panel */
  private class BrukerInputPanel extends JPanel {
    public BrukerInputPanel() {
      /* Arg. til GridLayout: Ant. rader og kolonner, horisontal og vertikal avstand */
      setLayout(new GridLayout(4, 2, 5, 5));
      JLabel ledetekst = new JLabel("X-verdi:", JLabel.RIGHT);
      add(ledetekst);
      add(xFelt);

      ledetekst = new JLabel("Y-verdi:", JLabel.RIGHT);
      add(ledetekst);
      add(yFelt);

      ledetekst = new JLabel("Bredde:", JLabel.RIGHT);
      add(ledetekst);
      add(breddeFelt);

      ledetekst = new JLabel("Høyde:", JLabel.RIGHT);
      add(ledetekst);
      add(høydeFelt);
    }
  }

  /* Beskriver søndre panel */
  private class Knappepanel extends JPanel {
    public Knappepanel() {  // Standard layouthåndterer FlowLayout
      Knappelytter knappelytteren = new Knappelytter();

      JButton knappRød = new JButton("Rød");
      knappRød.setBackground(Color.RED);
      knappRød.addActionListener(knappelytteren);
      add(knappRød);

      JButton knappBlå = new JButton("Blå");
      knappBlå.setBackground(Color.BLUE);
      knappBlå.addActionListener(knappelytteren);
      add(knappBlå);

      JButton knappTurkis = new JButton("Turkis");
      knappTurkis.setBackground(Color.CYAN);
      knappTurkis.addActionListener(knappelytteren);
      add(knappTurkis);
    }
  }
}

/**
 * En tegning av en ellipse, der ellipseattributtene kan forandres.
 * Klassen tar inn attributtene som tekster. Dersom en tekst ikke
 * kan omformes til et heltall, settes attributtet lik 0.
 * En liten melding skrives til kommandovinduet.
 */
class Tegning extends JPanel {
  private int x;
  private int y;
  private int bredde;
  private int høyde;
  public Tegning(String x, String y, String bredde, String høyde) {
    this.x = omformTekst(x);
    this.y = omformTekst(y);
    this.bredde = omformTekst(bredde);
    this.høyde = omformTekst(høyde);
  }

  public void setX(String nyX) {
    x = omformTekst(nyX);
  }

  public void setY(String nyY) {
    y = omformTekst(nyY);
  }

  public void setBredde(String nyBredde) {
    bredde = omformTekst(nyBredde);
  }

  public void setHøyde(String nyHøyde) {
    høyde = omformTekst(nyHøyde);
  }

  public void paintComponent(Graphics vindu) {
    vindu.fillOval(x, y, bredde, høyde);
  }

  private int omformTekst(String tekst) {
    try {
      return Integer.parseInt(tekst);
    } catch (NumberFormatException e) {
      System.out.println("Kan ikke omforme " + tekst + " til heltall.");
      return 0;
    }
  }
}

class TestTegneVindu {
  public static void main(String[] args) {
    VinduMedTegning etVindu =
       new VinduMedTegning("Ellipse med brukerbestemt størrelse og plassering");
    etVindu.setVisible(true);
  }
}