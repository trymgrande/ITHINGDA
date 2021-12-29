/**
 * DirekteTilgFil.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Eksempel på direkte tilgang til data i en fil
 */

import java.io.*;
class DirekteTilgFil {
  public static void main(String[] args) throws IOException {
    RandomAccessFile fil = new RandomAccessFile("Direktefil.dat", "rw");

    /* Skriver 10 heltall til filen */
    for (int i = 0; i < 10; i++) {
      fil.writeInt(i);
    }
    long fillengde = fil.length();
    System.out.println("Filen har lengde: " + fillengde);
    /*
     * Flytter filpekeren til tall nr. 7, leser det,
     * ganger det med 10, og skriver det tilbake igjen
     */
    fil.seek(6 * 4); // flytter forbi 6 tall, hver på 4 byte
    int tall = fil.readInt();
    tall *= 10;
    fil.seek(6 * 4); // flytter filpekeren "tilbake"
    fil.writeInt(tall);

    /* Leser hele filen */
    fil.seek(0);  // flytter til begynnelsen av filen
    try {
      while (true) {  // stopper når EOFException kastes
        int t = fil.readInt();
        System.out.println(t);
      }
    }
    catch (EOFException e) {
    }
    fil.close();
  }
}

/* Utskrift fra programmet:
Filen har lengde: 40
0
1
2
3
4
5
60
7
8
9
*/
