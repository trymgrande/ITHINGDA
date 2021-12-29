/**
 * FargeDemo.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Filen inneholder tre klasser:
 * Vindu: Et vindu med en tegning
 * Tegning: Viser de 13 standardfargene i klassen Color
 * FargeDemo: main()-metode som viser fram vinduet med tegningen
 */

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Vindu extends JFrame {
  public Vindu(String tittel) {
    setTitle(tittel);
    setSize(500, 250); // bredde, høyde
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Tegning tegningen = new Tegning();
    add(tegningen);
  }
}

class Tegning extends JPanel {
  public void paintComponent(Graphics tegneflate) {
    super.paintComponent(tegneflate);
    tegneflate.drawString(
            "Smiley:)", 40, 30);
    Color[] farger = {Color.BLACK, Color.GRAY, Color.ORANGE, Color.YELLOW,
               Color.BLUE, Color.GREEN, Color.PINK, Color.CYAN, Color.LIGHT_GRAY,
               Color.RED, Color.DARK_GRAY, Color.MAGENTA, Color.WHITE};

	//ansikt
    tegneflate.setColor(Color.BLACK);
    tegneflate.fillOval(50, 50, 200, 200);
    //øyne
    tegneflate.setColor(Color.WHITE);
    tegneflate.fillOval(100, 100, 30, 30);
    tegneflate.fillOval(150, 100, 30, 30);
    //munn
    tegneflate.drawArc(100, 150, 75, 50, 180, 180);

    //for (int i = 0; i < farger.length; i++) {
    //  tegneflate.setColor(farger[i]);
    //  tegneflate.fillRect(40 + 30 * i, 50, 30, 120);
    //}
  }
}

class FargeDemo {
  public static void main(String[] args) {
    Vindu etVindu = new Vindu("Vindu med smiley");
    etVindu.setVisible(true);
  }
}