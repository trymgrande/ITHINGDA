/**
 * GrafikkEksempel.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Filen inneholder tre klasser:
 * Vindu: Et vindu med en tegning
 * Tegning: En oransje oval med teksten "Hei hei"
 * GrafikkEksempel: main()-metode som viser fram vinduet med tegningen
 */

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Vindu extends JFrame {
  public Vindu(String tittel) {
    setTitle(tittel);
    setSize(200, 120); // bredde, høyde
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Tegning tegningen = new Tegning();
    add(tegningen);
  }
}

class Tegning extends JPanel {
  public void paintComponent(Graphics tegneflate) {
    super.paintComponent(tegneflate);  // husk denne!
    setBackground(Color.ORANGE);
    tegneflate.drawString("Hei hei", 50, 50);
    tegneflate.drawOval(40, 30, 55, 40);
  }
}

class GrafikkEksempel {
  public static void main(String[] args) {
    Vindu etVindu = new Vindu("Enkel grafikk");
    etVindu.setVisible(true);
  }
}