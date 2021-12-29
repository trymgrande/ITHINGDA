/**
 * TestPersonDialog.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Filen inneholder klasser for å prøve ut mittBibliotek.PersonDialog:
 * en klasse for foreldrevinduet og en klasse med main()
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import mittBibliotek.Person;
import mittBibliotek.PersonDialog;

class Foreldrevindu extends JFrame {
  private Person enPerson = new Person(100, "Anne", "Johansen");
  private PersonDialog persondialog = new PersonDialog(this);

  public Foreldrevindu() {
    setTitle("Dialogtest");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout());
    JButton knapp = new JButton("Trykk her!");
    add(knapp);
    knapp.addActionListener(new KnappeLytter());
    setLocation(300, 300); // plasserer foreldrevinduet
    persondialog.setLocation(350, 350);  // plasserer dialogen
  }

  private class KnappeLytter implements ActionListener {
    public void actionPerformed(ActionEvent hendelse) {
      if (persondialog.visDialog(enPerson)) {
        System.out.println("OK trykket ...");
      } else {
        System.out.println("Avbryt trykket ...");
      }
      System.out.println(enPerson); // bruker toString()
    }
  }
}

class TestPersonDialog {
  static public void main(String[] args) {
    Foreldrevindu test = new Foreldrevindu();
    test.setSize(300, 200);  // for å få litt størrelse på vinduet
    test.setVisible(true);
  }
}