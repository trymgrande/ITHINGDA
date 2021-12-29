/**
 * TestSpinner.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Viser en spinner med månedsnavn
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import javax.swing.event.*;

class SpinnerVindu extends JFrame {
  private DateFormatSymbols kalenderdata = new DateFormatSymbols();
  private String [] mnd = kalenderdata.getMonths();
  private JLabel melding = new JLabel("Du har ennå ikke valgt måned.");
  private SpinnerModel spinnerInnhold = new SpinnerListModel(mnd);
  private JSpinner mndSpinner = new JSpinner(spinnerInnhold);

  public SpinnerVindu(String tittel) {
    setTitle(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel ledetekst = new JLabel("Velg måned");
    add(ledetekst, BorderLayout.NORTH);
    add(mndSpinner, BorderLayout.CENTER);
    mndSpinner.addChangeListener(new Spinnerlytter());
    add(melding, BorderLayout.SOUTH);
    pack();
  }

  /* Lytteren fanger opp valg på spinneren. */
  private class Spinnerlytter implements ChangeListener {
    public void stateChanged(ChangeEvent hendelse) {
      melding.setText("Du har valgt " + mndSpinner.getValue());
    }
  }
}

class TestSpinner {
  public static void main(String[] args) {
    SpinnerVindu etVindu = new SpinnerVindu("Valg av måned");
    etVindu.setVisible(true);
  }
}