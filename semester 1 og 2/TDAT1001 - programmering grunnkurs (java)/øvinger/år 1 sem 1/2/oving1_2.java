//Skriv et program som regner om timer, minutter og sekunder til totalt antall sekunder. Sett opp testdatasett og prøv ut programmet.

import static javax.swing.JOptionPane.*;

class oving1_2{
	public static void main (String[] args){

		//input
		String timLest = showInputDialog("timer: ");
		String minLest = showInputDialog("minutter: ");
		String sekLest = showInputDialog("sekunder: ");

		int tim = Integer.parseInt(timLest);
		int min = Integer.parseInt(minLest);
		int sek = Integer.parseInt(sekLest);

		//omregning
		min = tim * 60 + min;
		sek = min * 60 + sek;

		//output
		System.out.println("totale sek: " + sek);

	}
}

