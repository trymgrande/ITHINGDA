/**
 * EksempelStudent2.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Mutabel klasse Student med attributter studentnr. og poststed.
 * Klassen er laget så enkel for å illustrere aggregering.
 * Poststedobjektet er "en-del-av" studentobjektet, men
 * det kan også være "en-del-av" andre objekter.
 *
 * Nederst er et enkelt klientprogram.
 */

import static javax.swing.JOptionPane.*;
class Student {
  private Poststed poststed;
  private int studnr;

  public Student(Poststed poststed, int studnr) {
    this.poststed = poststed;
    this.studnr = studnr;
  }

  public Poststed getPoststed() {
    return poststed;
  }

  public int getStudnr() {
    return studnr;
  }

  public void setPoststed(Poststed poststed) {
    this.poststed = poststed;
  }

  public void setStudnr(int studnr) {
    this.studnr = studnr;
  }

  public String getPostnr() {
    return poststed.getPostnr();
  }

  public String getSted() {
    return poststed.getSted();
  }

  public String toString() {
    return "Studentnr.: " + studnr + ", postadresse: " + poststed;
  }
}

/**
 * Enkel klient som oppretter et studentobjekt og prøver set-metodene.
 */
class EksempelStudent2 {
  public static void main(String[] args) {
    /* Oppretter et poststedobjekt som blir argument til Student-konstruktøren. */
    Poststed post = new Poststed("0345", "Oslo");
    Student studenten = new Student(post, 56437);
    System.out.println("Etter at objektet er opprettet: " + studenten.toString());

    /* Leser inn ny postadresse og registrerer den i studentobjektet. */
    String nyPostadrLest = showInputDialog
                                      ("Oppgi ny postadresse, eks 7020 Trondheim: ");
    String[] ord = nyPostadrLest.split(" ");
    Poststed nyttPoststed = new Poststed(ord[0], ord[1]);
    studenten.setPoststed(nyttPoststed);

    /* Leser inn nytt studentnr. og registerer det i studentobjektet. */
    String nyttStudnrLest = showInputDialog("Oppgi nytt studentnr: ");
    int nyttStudnr = Integer.parseInt(nyttStudnrLest);
    studenten.setStudnr(nyttStudnr);

    System.out.println("Etter endring av data: " + studenten.toString());
  }
}

/* Kjøring av programmet:
Etter at objektet er opprettet: Studentnr.: 56437, postadresse: 0345 Oslo
<leser inn nye data: "7025 Trondheim" og "12345">
Etter endring av data: Studentnr.: 12345, postadresse: 7025 Trondheim
*/