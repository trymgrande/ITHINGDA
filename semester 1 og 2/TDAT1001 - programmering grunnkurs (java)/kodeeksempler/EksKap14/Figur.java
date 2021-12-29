/**
 * Figur.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Filen inneholder fire klasser:
 * Figur: En abstrakt klasse med den abstrakte operasjonen beregnAreal()
 * Sirkel, Trekant og Kvadrat: Subklasser til Figur,
 *         med hver sin implementasjon av beregnAreal()
 */

abstract class Figur {
  /* Alle figurer kan beregne sitt eget areal */
  public abstract double beregnAreal();
}

/**
 * Kvadrat: Arealet er lik side * side
 */
class Kvadrat extends Figur {
  private final double side;

  public Kvadrat(int side) {
    this.side = side;
  }

  public double getSide() {
    return side;
  }

  public double beregnAreal() {
    return side * side;
  }
}

/**
 * Trekant: Arealet er lik 0,5 * grunnlinje * høyde
 */
class Trekant extends Figur {
  private final double grunnlinje;
  private final double høyde;

  public Trekant(double grunnlinje, double høyde) {
    this.grunnlinje = grunnlinje;
    this.høyde = høyde;
  }

  public double getGrunnlinje() {
    return grunnlinje;
  }

  public double getHøyde() {
    return høyde;
  }

  public double beregnAreal() {
    return 0.5 * grunnlinje * høyde;
  }
}

/**
 * Sirkel: Arealet er lik pi * radius * radius
 */
class Sirkel extends Figur {
  private final double radius;

  public Sirkel(double radius) {
    this.radius = radius;
  }

  public double getRadius() {
    return radius;
  }

  public double beregnAreal() {
    return Math.PI * radius * radius ;
  }
}