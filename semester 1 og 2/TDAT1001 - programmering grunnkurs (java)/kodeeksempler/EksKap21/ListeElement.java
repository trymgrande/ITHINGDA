/**
 * ListeElement.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Tilrettelegger for at objekt av generisk type vil kunne være elementer
 * i en enkeltlenket liste.
 */

class ListeElement<Type> {
  private ListeElement<Type> neste;
  private Type element;

  public ListeElement(Type element) {
    this.element = element;
    neste = null;
  }

  public ListeElement(Type element, ListeElement<Type> neste) {
    this.element = element;
    this.neste = neste;
  }

  public Type getElement() {
    return element;
  }

  public ListeElement<Type> getNeste() {
    return neste;
  }

  public void setElement(Type element) {
    this.element = element;
  }

  public void setNeste(ListeElement<Type> neste) {
    this.neste = neste;
  }
}