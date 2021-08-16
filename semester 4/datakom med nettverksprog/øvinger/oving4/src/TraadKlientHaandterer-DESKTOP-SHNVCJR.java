import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class TradKlientHandterer extends Thread {
	private Socket forbindelse;

	public TradKlientHandterer(Socket socket) {
		this.forbindelse = socket;
	}

	@Override
	public void run() {
		BufferedReader leseren = null;
		PrintWriter skriveren = null;

		try {
			/* Åpner strømmer for kommunikasjon med klientprogrammet */
			InputStreamReader leseforbindelse = new InputStreamReader(forbindelse.getInputStream());
			leseren = new BufferedReader(leseforbindelse);
			skriveren = new PrintWriter(forbindelse.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		/* Sender innledning til klienten */
//		skriveren.println("Hei, du har kontakt med tjenersiden!");
//		skriveren.println("Hei, skriv hva du vil på formatet \'heltall1[+|-]heltall2\', så skal jeg regne ut, avslutt med linjeskift.");

		try {
			/* Mottar data fra klienten */
			String enLinje = leseren.readLine();  // mottar en linje med tekst
			while (enLinje != null) {  // forbindelsen på klientsiden er lukket
				System.out.println("En klient skrev: " + enLinje);
				String resultat = calculate(enLinje);
				skriveren.println("Tjeneren skrev: " + resultat);  // sender svar til klienten
				enLinje = leseren.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			/* Lukker forbindelsen */
			leseren.close();
			skriveren.close();
			forbindelse.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static String calculate(String input) {
		//checks input format
		String result;
		if (!(input.matches("(\\d+([+]|[-]))\\d+"))) {
			//invalid input
			return "invalid input";
		}
		//interprets numbers
		String[] integers = input.split("[+]|[-]");
		int integer1 = Integer.parseInt(integers[0]);
		int integer2 = Integer.parseInt(integers[1]);

		// calculates -
		if (input.indexOf('-') != -1) {
			result = Integer.toString(integer1 - integer2);
		}
		// calculates +
		else if (input.indexOf('+') != -1) {
			result = Integer.toString(integer1 + integer2);
		}
		else {
			result = "unexpected operator";
		}
		return result;
	}
}