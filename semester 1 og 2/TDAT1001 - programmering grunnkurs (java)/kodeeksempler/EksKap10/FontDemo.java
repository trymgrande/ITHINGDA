/**
 * FontDemo.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Filen inneholder tre klasser:
 * Vindu: Et vindu med en tegning
 * Tegning: Viser ulike skrifttyper
 * FontDemo: main()-metode som viser fram vinduet med tegningen
 */
import java.awt.Graphics;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.JOptionPane.*;

class Vindu extends JFrame {
  public Vindu(String tittel) {
    setTitle(tittel);
    setSize(500, 250); // bredde, høyde
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    String høydeS = showInputDialog("Oppgi skriftstørrelse: ");
    int høyde = Integer.parseInt(høydeS);
    Tegning tegningen = new Tegning(høyde);
    add(tegningen);
  }
}

class Tegning extends JPanel {
  private final int høyde;

  public Tegning(int høyde) {
    this.høyde = høyde;
  }

  public void paintComponent(Graphics tegneflate) {
    super.paintComponent(tegneflate);
    tegneflate.drawString("Først en tekst med standardfonten.", 40, 60);
    Font skrift = new Font("SansSerif", Font.BOLD, høyde);
    tegneflate.setFont(skrift);
    tegneflate.drawString(
      "Dette er skrevet med skrifttype " + skrift.getName(), 40, 100);
    skrift = new Font("Monospaced", Font.ITALIC, høyde);
    tegneflate.setFont(skrift);
    tegneflate.drawString(
      "Dette er skrevet med skrifttype " + skrift.getName(), 40, 140);
    skrift = new Font("Dialog", Font.BOLD + Font.ITALIC, høyde);
    tegneflate.setFont(skrift);
    tegneflate.drawString(
      "Dette er skrevet med skrifttype " + skrift.getName(), 40, 180);
  }
}

class FontDemo {
  public static void main(String[] args) {
    Vindu etVindu = new Vindu("Ulike skrifttyper");
    etVindu.setVisible(true);
  }
}