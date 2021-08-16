/**
 * TestRadioknapper.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Viser et vindu med to radioknapper i en gruppeboks
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class RadioknappVindu extends JFrame {
  /* Knappen merket "kvinne" er trykket inn ved oppstart av programmet. */
  private JRadioButton kvinne = new JRadioButton("kvinne", true);
  private JRadioButton mann = new JRadioButton("mann", false);

  public RadioknappVindu(String tittel) {
    setTitle(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    /* Beholderen har samme oppbygning som i TestAvkrysningsruter.java */
    add(new JPanel(), BorderLayout.NORTH); // litt luft
    add(new JPanel(), BorderLayout.SOUTH); // litt luft
    ValgPanel midten = new ValgPanel();
    add(midten, BorderLayout.CENTER);
    pack();
  }

  /* Beskriver panelet med radioknappene */
  private class ValgPanel extends JPanel {
    public ValgPanel() {
      /*
       * Radioknappene må knyttes til en radioknappgruppe for at de skal
       * virke riktig. Det vil si at bare én knapp kan være trykket inn av gangen.
       */
      ButtonGroup gruppe = new ButtonGroup();
      gruppe.add(kvinne);
      gruppe.add(mann);
      add(kvinne);
      add(mann);

      RadioknappLytter lytter = new RadioknappLytter();
      kvinne.addActionListener(lytter);
      mann.addActionListener(lytter);

      /* Lager en ramme rundt panelet */
      SoftBevelBorder ramme = new SoftBevelBorder(BevelBorder.RAISED);
      Border gruppeboks = BorderFactory.createTitledBorder(ramme, "Kjønn");
      setBorder(gruppeboks);
    }
  }

  /* Lytterobjekter som lytter til alle endringer i radioknappene */
  private class RadioknappLytter implements ActionListener {
    public void actionPerformed(ActionEvent hendelse) {
      String kjønn = hendelse.getActionCommand();
      if (kjønn.equals("kvinne")) {
        System.out.println("Kvinne er valgt");
      } else {
        System.out.println("Mann er valgt");
      }
    }
  }
}

class TestRadioknapper {
  public static void main(String[] args) {
    RadioknappVindu etVindu = new RadioknappVindu("Kvinne eller mann?");
    etVindu.setVisible(true);
  }
}