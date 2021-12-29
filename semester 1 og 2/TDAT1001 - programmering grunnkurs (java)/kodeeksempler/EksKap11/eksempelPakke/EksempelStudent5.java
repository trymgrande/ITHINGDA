/**
 * EksempelStudent5.java - "Programmering i Java", 4.utgave - 2009-07-01
 * Prøver en klasse som ligger i en pakke.
 */
import poststed.Poststed;
class EksempelStudent5 {  // Pakkenivå
  public static void main(String[] args) {
    Poststed post = new Poststed("0345", "Oslo");
    Student studenten = new Student(post, 56437);
    System.out.println("Etter at objektet er opprettet: " + studenten.toString());
    System.out.println("Bare stedsnavnet: " + studenten.getSted());
    post.setPostnr("12345"); // gir kompileringsfeil
    System.out.println("Ny utskrift: " + studenten.toString());
  }
}