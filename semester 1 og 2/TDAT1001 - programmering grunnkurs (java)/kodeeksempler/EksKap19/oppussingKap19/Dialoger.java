/**
 * Dialoger.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Dialoger til bruk i klassen OppussingKap19GUI.
 * Alle dialogene er subklasser til MinDialog, og de har sine egne
 * utgaver av okData() der det kontrolleres at inndata kan tolkes
 * som tall der tall forventes.
 * Alle dialogene brukes til innlesing av data til nye objekter.
 */

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import static javax.swing.JOptionPane.*;
import mittBibliotek.MinDialog;
import oppussingsprosjekt.*;

class FlateDialog extends MinDialog {
  private NumberFormat innformat = NumberFormat.getNumberInstance();
  private JTextField navneFelt = new JTextField(Konstanter.TEKSTLENGDE);
  private JTextField lengdeFelt = new JTextField(Konstanter.TEKSTLENGDE);
  private JTextField breddeFelt = new JTextField(Konstanter.TEKSTLENGDE);
  private double lengde;
  private double bredde;

  public FlateDialog(JFrame foreldre) {
    super(foreldre, "Flate");
    add(new JPanel(), BorderLayout.NORTH);
    add(new FlateDatapanel(), BorderLayout.CENTER);
    add(getKnappepanel(), BorderLayout.SOUTH);
    pack();
  }

  private class FlateDatapanel extends JPanel {
    public FlateDatapanel() {
      setLayout(new GridLayout(3,2));
      add(new JLabel("Flatenavn: "));
      add(navneFelt);
      add(new JLabel("Bredden på flaten: "));
      add(breddeFelt);
      add(new JLabel("Lengden på flaten: "));
      add(lengdeFelt);
    }
  }

  protected boolean okData() {
    String lengdeS = lengdeFelt.getText();
    String breddeS = breddeFelt.getText();
    try {
      lengde = innformat.parse(lengdeS).doubleValue();
      bredde = innformat.parse(breddeS).doubleValue();
    } catch (ParseException e) {
      showMessageDialog(this, "Tallinput kan ikke tolkes. Prøv igjen.");
      breddeFelt.requestFocusInWindow();
      return false;
    }
    return true;
  }

  public Flate visDialog() {
    navneFelt.setText("");
    breddeFelt.setText("");
    lengdeFelt.setText("");
    setOk(false);
    navneFelt.requestFocusInWindow();
    setVisible(true);
    return isOk() ? new Flate(navneFelt.getText(), lengde, bredde) : null;
  }
}

class MalingDialog extends MinDialog {
  private NumberFormat innformat = NumberFormat.getNumberInstance();
  private JTextField navneFelt  = new JTextField(Konstanter.TEKSTLENGDE);
  private JTextField prisFelt = new JTextField(Konstanter.TEKSTLENGDE);
  private JTextField antStrøkFelt = new JTextField(Konstanter.TEKSTLENGDE);
  private JTextField antKvmPrLFelt = new JTextField(Konstanter.TEKSTLENGDE);
  private double pris;
  private int antStrøk;
  private double antLiter;

  public MalingDialog(JFrame foreldre) {
    super(foreldre, "Maling");
    add(new JPanel(), BorderLayout.NORTH);
    add(new MalingDatapanel(), BorderLayout.CENTER);
    add(getKnappepanel(), BorderLayout.SOUTH);
    pack();
  }

  private class MalingDatapanel extends JPanel {
    public MalingDatapanel() {
      setLayout(new GridLayout(4,2));
      add(new JLabel("Malingnavn: "));
      add(navneFelt);
      add(new JLabel("Pris pr. liter: "));
      add(prisFelt);
      add(new JLabel("Antall strøk: "));
      add(antStrøkFelt);
      add(new JLabel("Antall kvm pr. liter: "));
      add(antKvmPrLFelt);
    }
  }

  protected boolean okData() {
    String prisS = prisFelt.getText();
    String antStrøkS = antStrøkFelt.getText();
    String antLiterS = antKvmPrLFelt.getText();
    try {
      pris = innformat.parse(prisS).doubleValue();
      antStrøk = innformat.parse(antStrøkS).intValue();
      antLiter = innformat.parse(antLiterS).doubleValue();
    } catch (ParseException e) {
      showMessageDialog(this, "Tall-input kan ikke tolkes. Prøv igjen.");
      prisFelt.requestFocusInWindow();
      return false;
    }
    return true;
  }

  public Maling visDialog() {
    navneFelt.setText("");
    prisFelt.setText("");
    antStrøkFelt.setText("");
    antKvmPrLFelt.setText("");
    setOk(false);
    navneFelt.requestFocusInWindow();
    setVisible(true);
    return isOk() ? new Maling(navneFelt.getText(), pris, antStrøk, antLiter) : null;
  }
}

class TapetDialog extends MinDialog {
  private NumberFormat innformat = NumberFormat.getNumberInstance();
  private JTextField navneFelt = new JTextField(Konstanter.TEKSTLENGDE);
  private JTextField prisFelt = new JTextField(Konstanter.TEKSTLENGDE);
  private JTextField lengdeFelt = new JTextField(Konstanter.TEKSTLENGDE);
  private JTextField breddeFelt = new JTextField(Konstanter.TEKSTLENGDE);
  private double pris;
  private double lengde;
  private double bredde;

  public TapetDialog(JFrame foreldre) {
    super(foreldre, "Tapet");
    add(new JPanel(), BorderLayout.NORTH);
    add(new TapetDatapanel(), BorderLayout.CENTER);
    add(getKnappepanel(), BorderLayout.SOUTH);
    pack();
  }

  private class TapetDatapanel extends JPanel {
    public TapetDatapanel() {
      setLayout(new GridLayout(4,2));
      add(new JLabel("Tapetnavn: "));
      add(navneFelt);
      add(new JLabel("Pris pr. rull: "));
      add(prisFelt);
      add(new JLabel("Tapetrullens bredde: "));
      add(breddeFelt);
      add(new JLabel("Tapetrullens lengde: "));
      add(lengdeFelt);
    }
  }

  protected boolean okData() {
    String prisS = prisFelt.getText();
    String lengdeS = lengdeFelt.getText();
    String breddeS = breddeFelt.getText();
    try {
      pris = innformat.parse(prisS).doubleValue();
      lengde = innformat.parse(lengdeS).doubleValue();
      bredde = innformat.parse(breddeS).doubleValue();
    } catch (ParseException e) {
      showMessageDialog(this, "Tall-input kan ikke tolkes. Prøv igjen.");
      prisFelt.requestFocusInWindow();
      return false;
    }
    return true;
  }

  public Tapet visDialog() {
    navneFelt.setText("");
    prisFelt.setText("");
    breddeFelt.setText("");
    lengdeFelt.setText("");
    setOk(false);
    navneFelt.requestFocusInWindow();
    setVisible(true);
    return isOk() ? new Tapet(navneFelt.getText(), pris, lengde, bredde) : null;
  }
}

class BeleggDialog extends MinDialog {
  private NumberFormat innformat = NumberFormat.getNumberInstance();
  private JTextField navneFelt = new JTextField(Konstanter.TEKSTLENGDE);
  private JTextField prisFelt = new JTextField(Konstanter.TEKSTLENGDE);
  private JTextField breddeFelt = new JTextField(Konstanter.TEKSTLENGDE);
  private double pris;
  private double bredde;

  public BeleggDialog(JFrame foreldre) {
    super(foreldre, "Belegg");
    add(new JPanel(), BorderLayout.NORTH);
    add(new BeleggDatapanel(), BorderLayout.CENTER);
    add(getKnappepanel(), BorderLayout.SOUTH);
    pack();
  }

  private class BeleggDatapanel extends JPanel {
    public BeleggDatapanel() {
      setLayout(new GridLayout(3,2));
      add(new JLabel("Beleggnavn: "));
      add(navneFelt);
      add(new JLabel("Pris pr. meter belegg: "));
      add(prisFelt);
      add(new JLabel("Beleggets bredde: "));
      add(breddeFelt);
    }
  }

  protected boolean okData() {
    String prisS = prisFelt.getText();
    String breddeS = breddeFelt.getText();
    try {
      pris = innformat.parse(prisS).doubleValue();
      bredde = innformat.parse(breddeS).doubleValue();
    } catch (ParseException e) {
      showMessageDialog(this, "Tall-input kan ikke tolkes. Prøv igjen.");
      prisFelt.requestFocusInWindow();
      return false;
    }
    return true;
  }

  public Belegg visDialog() {
    navneFelt.setText("");
    prisFelt.setText("");
    breddeFelt.setText("");
    setOk(false);
    navneFelt.requestFocusInWindow();
    setVisible(true);
    return isOk() ? new Belegg(navneFelt.getText(), pris, bredde) : null;
  }
}