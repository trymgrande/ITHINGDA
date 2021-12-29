/**
 * MenyTest.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Viser en meny med tre valg: Gul, r�d og bl�
 * Et menyvalg f�rer til at bakgrunnsfargen skifter til den valgte farge.
 *
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class VinduMedMeny extends JFrame {
  private Container guiBeholder;  // for � sette bakgrunnsfargen

  public VinduMedMeny() {
    setTitle("Menytest");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    guiBeholder = getContentPane();

    MenyLytter lytteren = new MenyLytter();

    JMenu menyen = new JMenu("Farge");
    JMenuItem menypost = new JMenuItem("Gul");
    menyen.add(menypost);
    menypost.addActionListener(lytteren);

    menypost = new JMenuItem("R�d");
    menyen.add(menypost);
    menypost.addActionListener(lytteren);

    menypost = new JMenuItem("Bl�");
    menyen.add(menypost);
    menypost.addActionListener(lytteren);

    JMenuBar menylinje = new JMenuBar();
    menylinje.add(menyen);
    setJMenuBar(menylinje);
  }

  private class MenyLytter implements ActionListener {
    public void actionPerformed(ActionEvent hendelse) {
      String kommando = hendelse.getActionCommand();
      if (kommando.equals("Gul")) {
        guiBeholder.setBackground(Color.YELLOW);
      } else if (kommando.equals("R�d")) {
        guiBeholder.setBackground(Color.RED);
      } else {
        guiBeholder.setBackground(Color.BLUE);
      }
    }
  }
}

class MenyTest {
  public static void main(String[] args) {
    VinduMedMeny vindu = new VinduMedMeny();
    vindu.setSize(300, 200); // ellers vil det ikke vises p� skjermen
    vindu.setVisible(true);
  }
}