/**
 * EksempelRefleksjon.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Program som demonstrerer refleksjon ved å skrive ut alle offentlige
 * metoder i klassen String
 *
 * Filen inneholder to klasser:
 * MetodeLister: Har en metode som gjør denne listingen
 * EksempelRefleksjon: Inneholder main()
 */

import java.lang.reflect.*;
class MetodeLister {
  public void listMetoder() {
    String klasseNavn = "java.lang.String";
    try {
      Class stringKlasse = Class.forName(klasseNavn);
      Method[] metoder = stringKlasse.getMethods();
      System.out.println("Metoder i klassen " + klasseNavn + ":");
      for (int i = 0 ; i < metoder.length ; i++) {
        System.out.println(metoder[i]);
      }
    }
    catch (ClassNotFoundException e) {
      System.out.println("Klassenavn ikke funnet: " + e);
    }
  }
}

class EksempelRefleksjon {
  public static void main(String[] args) {
    MetodeLister lister = new MetodeLister();
    lister.listMetoder();
  }
}

/* Utskrift:
Metoder i klassen String:
public int java.lang.String.hashCode()
public int java.lang.String.compareTo(java.lang.Object)
public int java.lang.String.compareTo(java.lang.String)
public boolean java.lang.String.equals(java.lang.Object)
public java.lang.String java.lang.String.toString()
public int java.lang.String.length()
public char java.lang.String.charAt(int)
public void java.lang.String.getChars(int,int,char[],int)
... med mer, samtlige offentlige metoder i String, inklusive de arvet
fra Object, listes ut.
*/
