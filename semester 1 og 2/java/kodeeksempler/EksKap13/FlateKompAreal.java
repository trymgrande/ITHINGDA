/**
 * FlateKompAreal.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Komparator for klassen Flate der arealet sammenlignes.
 */

class FlateKompAreal implements java.util.Comparator<Flate> {
  public final static double TOLERANSE = 0.001;
  public int compare(Flate flate1, Flate flate2) {
    double areal1 = flate1.beregnAreal();
    double areal2 = flate2.beregnAreal();
    if (Math.abs(areal2 - areal1) < TOLERANSE) {
      return 0;
    } else if (areal1 < areal2) {
      return -1;
    } else {
      return 1;
    }
  }
}