/*
Lag et program som leser inn 10 heltall fra brukeren og skriver dem ut igjen i motsatt rekkefølge med ett tall per linje i en dialogboks.
(Lagre de innleste tallene i en array.)
 */
import javax.swing.JOptionPane;

public class motsattTeller {
    public static void main(String[] args) {
        //input 10 heltall
        int[] integers = new int[10];

        for (int i = 0; i < 10; i++) {
            integers[i] = Integer.parseInt(JOptionPane.showInputDialog("skriv inn int: " + (i+1)));
        }


        //skriv ut motsatt rekkefølge
        for (int i = 9; i >= 0; i--) {
            System.out.println(integers[i]);
        }
    }
}