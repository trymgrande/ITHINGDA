/**
 * Livslop.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Applet som demonstrerer metoder som kalles av nettleser i løpet av dens liv
 *
 * Filen inneholder to klasser:
 * Livslop: Selve appleten
 * Tegning: Tegneflaten til appleten
 * Utskriftene vises i nettleseren i Java console.
 */
import java.awt.*;
import javax.swing.*;

public class Livslop extends JApplet {
  public void init() {
    add(new Tegning());
    System.out.println("Appleten er lastet inn i nettleseren.");
  }

  public void start() {
    System.out.println("Appleten starter.");
  }

  public void stop() {
    System.out.println("Appleten stopper.");
  }

  public void destroy() {
    System.out.println("Appleten avslutter.");
  }
}

class Tegning extends JPanel {
  public void paintComponent(Graphics tegneflate) {
    super.paintComponent(tegneflate);
    System.out.println("Appleten kjører paint().");
    tegneflate.drawString("Studer Java console i nettleseren din!", 5, 50);
  }
}
