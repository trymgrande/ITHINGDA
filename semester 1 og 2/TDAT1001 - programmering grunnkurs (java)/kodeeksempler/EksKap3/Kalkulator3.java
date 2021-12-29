/**
 * Kalkulator3.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet leser inn og regner ut et regnestykke, tall1 operator tall2.
 * Følgende operatorer er tillatt: + - * / %
 */

import static javax.swing.JOptionPane.*;
class Kalkulator3 {
  public static void main(String[] args) {
    String tall1Lest = showInputDialog("Tall 1: ");
    String tall2Lest = showInputDialog("Tall 2: ");
    String operatorLest = showInputDialog("Operator (+ - * / %): ");
    int tall1 = Integer.parseInt(tall1Lest);
    int tall2 = Integer.parseInt(tall2Lest);
    char operator = operatorLest.charAt(0);

    switch (operator) {
    case '+':
      showMessageDialog(null, "Svar: " + (tall1 + tall2));
      break;
    case '-':
      showMessageDialog(null, "Svar: " + (tall1 - tall2));
      break;
    case '*':
      showMessageDialog(null, "Svar: " + (tall1 * tall2));
      break;
    case '/':
      showMessageDialog(null, "Svar: " + (tall1 / tall2));
      break;
    case '%':
      showMessageDialog(null, "Svar: " + (tall1 % tall2));
      break;
    default:
      showMessageDialog(null, "Ugyldig operator, bruk: + - * / %");
      break;
    }
  }
}

/* Eksempler på kjøring av programmet:
Kjøring 1:
Regnestykke: 5 % 4
Svar: 1

Kjøring 2:
Regnestykke: -4 * -7
Svar: 28

Kjøring 3:
Regnestykke: 27 / 5
Svar: 5
*/