import static javax.swing.JOptionPane.*;
import javax.swing.JOptionPane;

class TerningspillKlient {
	public static void main(String[] args) {
		boolean quit = false;




		//nytt objekt A
		Spiller spillerA = new Spiller("spillerA",0);

		//nytt objekt B
		Spiller spillerB = new Spiller("spillerB", 0);

		//loop
			while (quit == false) {

			//terningkast
			System.out.println("kast spiler 1: " + spillerA.kastTerningen());
			System.out.println("kast spiler 2: " + spillerB.kastTerningen());

			//sjekker poengsum
			System.out.println("sum spiller 1: " + spillerA.getSumPoeng());
			System.out.println("sum spiller 2: " + spillerB.getSumPoeng());


			//avslutter dersom poengsum > 99
			quit = spillerA.erFerdig() || spillerB.erFerdig();
		}

		if (spillerA.getSumPoeng() > 99 && spillerB.getSumPoeng() > 99) {
			System.out.println("uavgjort");
		}

		else if (spillerA.getSumPoeng() > 99) {
			System.out.println("spiller 1 vant");
		}
		else {
			System.out.println("spiller 2 vant");
		}
	}
}