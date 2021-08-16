/**
 * EksempelStudent1.java  - "Programmering i Java", 4.utgave - 2009-07-01
 * Første eksempel i en serie på 4. Her vises velkjent samarbeid mellom objekter.
 */

/**
 * Klassen Student med attributter studentnummer og poststed.
 * Poststedet lagres som en tekst med postnummer etterfulgt av
 * mellomrom og stedsnavn.
 */
class Student {
  private final String poststed;  // postnr. + mellomrom + stedsnavn
  private final int studnr;

  public Student(String poststed, int studnr) {
    this.poststed = poststed;
    this.studnr = studnr;
  }

  public String getPoststed() {
    return poststed;
  }

  public int getStudnr() {
    return studnr;
  }

  /**
   * Henter ut stedsnavnet som siste ord i poststedet.
   */
  public String getStedsnavn() {
    int sisteBlanke = poststed.lastIndexOf(' ');
    String sted = poststed.substring(sisteBlanke + 1);
    return sted;
  }

  public String toString() {
    return "Poststed: " + poststed + ", studentnr.: " + studnr;
  }
}

/**
 * Enkel klient som oppretter et studentobjekt og deretter henter ut stedsnavnet.
 */
class EksempelStudent1 {
  public static void main(String[] args) {
    Student studenten = new Student("0345 Oslo", 5566778);
    String sted = studenten.getStedsnavn();  // prøver metoden for å hente stedsnavn
    System.out.println("All info.: " + studenten.toString() + ", kun stedet: " + sted);
   }
}

/* Kjøring av programmet:
All info.: Poststed: 0345 Oslo, studentnr.: 5566778, kun stedet: Oslo
*/