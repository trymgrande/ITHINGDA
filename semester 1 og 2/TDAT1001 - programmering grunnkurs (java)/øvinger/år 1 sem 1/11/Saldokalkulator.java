import java.io.*;
import javax.swing.JOptionPane.*;

class Saldokalkulator {
	public static void main(String[] agrs) throws IOException {
		String saldoFilnavn = "saldo.txt";
		String transaksjonerFilnavn = "transaksjoner.txt";
		int antTransaksjoner = 0;

		FileReader fileReaderSaldo = new FileReader(saldoFilnavn);
		BufferedReader leserSaldo = new BufferedReader(fileReaderSaldo);

		FileReader fileReaderTransaksjoner = new FileReader(transaksjonerFilnavn);
		BufferedReader leserTransaksjoner = new BufferedReader(fileReaderTransaksjoner);

		//lese saldo
		String saldoLest = leserSaldo.readLine();
		int saldo = Integer.parseInt(saldoLest);
		System.out.println("saldo: " + saldo + "\n");
		leserSaldo.close();

		//lese transaksjoner og legger dem i liste
		String enTransaksjonLest = "";
		String[] transaksjonerLest = new String[antTransaksjoner];

		//teller transaksjoner lest
		boolean breakLoop = false;
		while (breakLoop == false) {
			if (transaksjonerLest.length == antTransaksjoner) {
				String[] nyTransaksjonerLest = new String[antTransaksjoner+1];
				//kopierer til dynamisk liste
				for (int j = 0; j < transaksjonerLest.length; j++) {
					nyTransaksjonerLest[j] = transaksjonerLest[j];
				}
				transaksjonerLest = nyTransaksjonerLest;
			}

			//lagrer transaksjoner i liste og øker antTransaksjoner
			for (int k = 0; k < transaksjonerLest.length; k++) {
				enTransaksjonLest = leserTransaksjoner.readLine();
				if (enTransaksjonLest == null) {
					breakLoop = true;
				}
				else {
					transaksjonerLest[k] = enTransaksjonLest;
					antTransaksjoner++;
				}
			}
		}
		//printer transaksjoner
		for (int i = 0; i < antTransaksjoner; i++) {
			System.out.println("transaksjon " + i + ": " + transaksjonerLest[i]);
		}
		leserTransaksjoner.close();

		//velge fortegn for transaksjoner
		int[] transaksjoner = new int[antTransaksjoner];
		for (int i = 0; i < antTransaksjoner; i++) {
			if (transaksjonerLest[i].toLowerCase().charAt(0) == 'u') {
				transaksjoner[i] = Integer.parseInt(transaksjonerLest[i].substring(1, transaksjonerLest[i].length()));
				transaksjoner[i] *= -1;
			}
			else if (transaksjonerLest[i].toLowerCase().charAt(0) == 'i') {
				transaksjoner[i] = Integer.parseInt(transaksjonerLest[i].substring(1, transaksjonerLest[i].length()));
			}
			else {
				System.out.println("invalid prefix for transaction\n");
			}
		}
		System.out.println();
		for (int i = 0; i < transaksjoner.length; i++) {
			System.out.println("transaksjon " + i + ": " + transaksjoner[i]);
		}

			//summere transaksjoner og saldo
		for (int i = 0; i < transaksjoner.length; i++) {
			saldo += transaksjoner[i];
		}
		System.out.println("\nny saldo: " + saldo);

		//slette gammel saldo og transaksjoner + skrive ny saldo
		FileWriter fileWriterSaldo = new FileWriter(saldoFilnavn, false);
		BufferedWriter BufferedWriterSaldo = new BufferedWriter(fileWriterSaldo);
		PrintWriter writerSaldo = new PrintWriter(BufferedWriterSaldo);
		writerSaldo.print(saldo);
		writerSaldo.close();

		FileWriter fileWriterTransaksjoner = new FileWriter(transaksjonerFilnavn, false);
		BufferedWriter BufferedWriterTransaksjoner = new BufferedWriter(fileWriterTransaksjoner);
		PrintWriter writerTransaksjoner = new PrintWriter(BufferedWriterTransaksjoner);
		writerTransaksjoner.print("");
		writerTransaksjoner.close();
	}
}