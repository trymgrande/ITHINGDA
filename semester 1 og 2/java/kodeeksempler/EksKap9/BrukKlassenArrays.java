/**
 * BrukKlassenArrays.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet viser bruk av klassemetodene sort() og binarySearch()
 * i klassen java.util.Arrays.
 */

class BrukKlassenArrays {
  public static void main(String[] args) {
    int[] test = {13, 5, 4, 3, 30};

    /* Vi sorterer hele tabellen */
     java.util.Arrays.sort(test);
    System.out.println("Sortert tabell: ");
    for (int i = 0; i < test.length; i++) {
      System.out.print(test[i] + " ");
    }
    System.out.println();

    /* Søking */

    /* Prøver først med en verdi vi vet eksisterer */
    int indeks =  java.util.Arrays.binarySearch(test, 13);
    System.out.println("Verdien 13 har indeks " + indeks);

    /* Prøver så med en verdi vi vet ikke eksisterer */
    indeks =  java.util.Arrays.binarySearch(test, 14);
    int pos = -indeks - 1;
    System.out.println("Verdien 14 skal settes inn på indeks " + pos);

    /* Lager en ny tabell med plass til den nye verdien */
    int[] nyTabell = new int[test.length + 1];

    /* Fyller den nye tabellen med data */
    for (int i = 0; i < pos; i++) nyTabell[i] = test[i];
    nyTabell[pos] = 14;
    for (int i = pos + 1; i < nyTabell.length; i++) {
      nyTabell[i] = test[i - 1];
    }

    System.out.println("Ny tabell: ");
    for (int i = 0; i < nyTabell.length; i++) {
      System.out.print(nyTabell[i] + " ");
    }
    System.out.println();
  }
}

/* Kjøring av programmet:
Sortert tabell:
3 4 5 13 30
Verdien 13 har indeks 3
Verdien 14 skal settes inn på indeks 4
Ny tabell:
3 4 5 13 14 30
*/