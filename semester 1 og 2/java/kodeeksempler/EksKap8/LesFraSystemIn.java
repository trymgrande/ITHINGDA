/**
 * LesFraSystemIn.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Leser inn mange tall over flere linjer, og summerer dem.
 */

class LesFraSystemIn {
  public static void main(String[] args) {
    java.util.Scanner scan = new java.util.Scanner(System.in);

    System.out.println("Skriv navnet ditt: ");
    String navn = scan.nextLine();
    System.out.println("Du skrev: " + navn);

    System.out.println("Skriv tre ord: ");
    String[] ord = new String[3];
    ord[0] = scan.next();
    ord[1] = scan.next();
    ord[2] = scan.next();
    System.out.println("Du skrev: " + ord[0] + " " + ord[1] + " " + ord[2]);

    System.out.println("Skriv tre tall som skal summeres: ");
    int sum = scan.nextInt() + scan.nextInt() + scan.nextInt();
    System.out.println("Summen av tallene er " + sum + ".");
  }
}

/* Eksempel på kjøring:
Skriv navnet ditt:
Anne Marie Hansen
Du skrev: Anne Marie Hansen
Skriv tre ord:
en to tre
Du skrev: en to tre
Skriv tre tall som skal summeres:
1 2 3
Summen av tallene er 6.
*/

