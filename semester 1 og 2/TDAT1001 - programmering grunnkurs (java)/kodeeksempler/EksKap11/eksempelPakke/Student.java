/**
 * Student.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Mutabel klasse Student med attributter studentnr og poststed.
 */
import poststed.Poststed;
public class Student {
  private Poststed poststed;
  private int studnr;

  Student(Poststed poststed, int studnr) {
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
    return "Student nr.: " + studnr + ", postadresse: " + poststed;
  }
}