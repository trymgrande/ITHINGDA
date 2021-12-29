/**
 * EnKonto.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Eksempel p� bruk av klassen Konto.
 * For at dette programmet skal kompilere, m� du ogs� ha filen Konto.java
 * tilgjengelig i samme mappe, eventuelt ta den inn i "prosjektet" ditt.
 */
class EnKonto {
  public static void main(String[] args) {

    /* Vi oppretter et objekt av klassen Konto. Objektet heter olesKonto */
    Konto olesKonto = new Konto(123456676756L, "Ole Olsen", 2300.50);
    olesKonto.utf�rTransaksjon(1000.0); // setter inn 1000 kroner
    double saldo = olesKonto.getSaldo(); // sp�r objektet om den nye saldoen
    System.out.println("Etter innskudd er saldoen lik " + saldo);
  }
}

/* Utskrift:
Etter innskudd er saldoen lik 3300.5
*/