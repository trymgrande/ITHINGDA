//Skriv et program som leser inn et antall sekunder og beregner hvor mange timer, minutter og sekunder dette er. (Hint: Bruk heltallsdivisjon.) Sett opp testdatasett og pr�v ut programmet.


import static javax.swing.JOptionPane.*;

class oving1_3 {

	public static void main (String[] args){

		//input
		String totSekString = showInputDialog("sekunder: ");
		int totSek = Integer.parseInt(totSekString);

		//omregning
		int totMin = totSek / 60;
		int totTim = totMin / 60;

		int sekOvers = totSek % 60;
		int minOvers = totMin % 60;
		int timOvers = totTim % 60;

		//output
		System.out.println("timer: " + timOvers + " minutter: " + minOvers + " sekunder: " + sekOvers);


	}
}