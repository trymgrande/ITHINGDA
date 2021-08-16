/*
 * Bue.java - "Programmering i Java", 4.utgave - 2009-07-01
 * Dette er programmet som lager figur 5.21 i boka.
 * Ved å sende inn ulike argumentverdier til drawArc() kan en
 * prøve ut forskjellige typer buer.
 */

import java.awt.*;
import javax.swing.*;

class Vindu extends JFrame {
  public Vindu(String tittel) {
    setTitle(tittel);
    setSize(300, 300); // bredde, høyde
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Tegning tegningen = new Tegning();
    add(tegningen);
  }
}

class Tegning extends JPanel {
  public void paintComponent(Graphics vindu) {
    super.paintComponent(vindu);
    setBackground(Color.white);
    vindu.setColor(Color.black);
    vindu.drawArc(40, 50, 200, 150, 45, 180);
    //vindu.drawArc(40, 80, 200, 150, -45, -180);
    vindu.drawRect(40, 50, 200, 150);
  }
}

class Bue {
  public static void main(String[] args) {
    Vindu etVindu = new Vindu("Metoden drawArc()");
    etVindu.setVisible(true);
  }
}