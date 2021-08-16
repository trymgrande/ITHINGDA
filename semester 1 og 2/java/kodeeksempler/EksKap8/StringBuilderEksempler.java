/**
 * StringBuilderEksempler.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 */
class StringBuilderEksempler {
  public static void main(String[] args) {
    StringBuilder tekst = new StringBuilder("En tekst");
    tekst.append(" og et tall ");
    tekst.append(345);
    System.out.println(tekst);
    System.out.println("Teksten har lengde " + tekst.length());

    /* Prøver sletting og innsetting */
    tekst.delete(0, 3);
    System.out.println("Har slettet de tre første tegnene: " + tekst);
    tekst.insert(4, "xxxxxx");
    System.out.println("Har satt inn xxxxxx fra posisjon 4: " + tekst);

    /* Snur teksten */
    tekst.reverse();
    System.out.println("Har snudd teksten: " + tekst);

    /* Omformer til String */
    String tekst2 = tekst.toString(); // nyttig f.eks. i toString()-metoder
  }
}

/* Utskrift:
En tekst og et tall 345
Teksten har lengde 23
Har slettet de tre f°rste tegnene: tekst og et tall 345
Har satt inn xxxxxx fra posisjon 4: teksxxxxxxt og et tall 345
Har snudd teksten: 543 llat te go txxxxxxsket
*/