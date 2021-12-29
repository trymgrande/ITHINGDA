import javax.swing.JOptionPane;


class TekstanalyseKlient {
	public static void main(String[] args) {
		Tekstanalyse nyTekstanalyse = new Tekstanalyse("aa.");
		char antallForekomster;

		while (true) {

			System.out.println("antall bokstaver: " + nyTekstanalyse.getAntallBokstaver());
			System.out.println("antall forskjellige bokstaver: " + nyTekstanalyse.getAntallForskjelligeBokstaver());
			System.out.println("prosent ikke boksaver: " + nyTekstanalyse.getProsentandelIkkeBokstaver());
			//antallForekomster = (char)(JOptionPane.showInputDialog("antall forekomser for bokstav: "));
			//antallForekomster = Integer.ParseInt(antallForekomster);
			antallForekomster = 'a';
			System.out.println("antall forekomster for " + antallForekomster + ": " + nyTekstanalyse.getAntallForekomster(antallForekomster));
			System.out.println("bokstav med flest forekomster: " + nyTekstanalyse.getBokstavMedFlestForekomster());
			JOptionPane.showInputDialog("");
		}
	}
}