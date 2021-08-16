import static javax.swing.JOptionPane.*;
import javax.swing.JOptionPane;

class ValutaKlient {
	public static void main(String[] args) {
		boolean quit = false;

		//konstruktører
		Valuta usd = new Valuta("USD", 8);
		Valuta eur = new Valuta("EUR", 9.5);
		Valuta sek = new Valuta("SEK", 0.9);

		//loop
		while (quit == false) {
			//br.gsn
			System.out.print("1: nok til usd\n2: nok til eur\n3: nok til sek\n4: usd til nok\n5: eur til nok\n6: sek til nok\n7: quit\n");

			//velg valuta
			int valutaValg = Integer.parseInt(JOptionPane.showInputDialog("type valuta: "));

			//velg mengde input
			int valutaInput = Integer.parseInt(JOptionPane.showInputDialog("mengde valuta: "));

		 	String valutaValgt = showInputDialog("Mengde valuta");
			//valgstruktur
			switch (valutaValg) {
				case 1 :
				System.out.println(usd.NokTilValuta(valutaInput));
				break;

				case 2:
				System.out.println(eur.NokTilValuta(valutaInput));
				break;

				case 3:
				System.out.println(sek.NokTilValuta(valutaInput));
				break;

				case 4:
				System.out.println(usd.ValutaTilNok(valutaInput));
				break;

				case 5:
				System.out.println(eur.ValutaTilNok(valutaInput));
				break;

				case 6:
				System.out.println(sek.ValutaTilNok(valutaInput));
				break;

				case 7:
				quit = true;
				break;
			}
		}
	}
}