/**
 * StaticBlokkEksempel.java - "Programmering i Java", 4.utgave - 2009-07-01
 * Initierer klassevariabel i static-blokk
 */

class TilfeldigeTall {
  public static int ANT_TALL = 10;
  private static int[] tabell = new int[ANT_TALL];

  static {
    java.util.Random rand = new java.util.Random(17);
    for (int i = 0; i < ANT_TALL; i++) {
      tabell[i] = rand.nextInt(100);
    }
  }

  public static String lagTallstreng() {
    String res = "";
    for (int i = 0; i < ANT_TALL - 1; i++) {
      res += tabell[i] + ", ";
    }
    res += tabell[ANT_TALL - 1];
    return res;
  }
}

class StaticBlokkEksempel {
  public static void main(String[] args) {
    System.out.println(TilfeldigeTall.lagTallstreng());
  }
}

/* Kjøring av programmet:
76, 20, 94, 16, 92, 93, 4, 15, 62, 8
*/