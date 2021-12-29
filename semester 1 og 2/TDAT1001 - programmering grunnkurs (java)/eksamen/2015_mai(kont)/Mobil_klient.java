import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

class Mobil_klient {
	public static void main(String[] args) {
		Mobil mobil = null;
		Visittkort nyttVisittkort;
		String filnavn = "mobildata.ser";
		int x = 0;
		while (x != 4) {
			//dialogboks
			String[] options = {"opprette ny mobil", "registrere nytt visittkort", "print visittkort", "avslutt"};
			x = JOptionPane.showOptionDialog(null, "velg handling: ", "Mobil_klient",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			switch (x) {
				case 1: if (mobil != null) {
						String fortsette = JOptionPane.showInputDialog("Mobil finnes allerede, all data vil bli slettet. Fortsette? (j/n)");
						if (fortsette == "n") {
							break;
						} else {
							String modell = JOptionPane.showInputDialog("modell: ");
							int egetNr = Integer.parseInt(JOptionPane.showInputDialog("telefonnr: "));
							int maksAntVisittkort = Integer.parseInt(JOptionPane.showInputDialog("maks antall visittkort"));
							mobil = new Mobil(modell, egetNr, maksAntVisittkort);
							break;
						}
					}
				case 2: mobil.registrerNyttVisittkort(new Visittkort(new Navn("Trym", "Grande"), 40475830, 40475830, "trym.grande@gmail.com"));
						break;

				case 3: mobil.printRegistrerteVisittkort();

				case 4: //skriv til fil
					FileWriter fileWriter = new FileWriter(filnavn);
					PrintWriter printWriter = new PrintWriter(fileWriter);
					

					break;
			}
		}
	}
	public static Mobil lesFraFil(String filnavn) {

		return;
	}
}