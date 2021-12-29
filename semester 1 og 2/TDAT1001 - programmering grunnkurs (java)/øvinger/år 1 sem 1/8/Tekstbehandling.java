/*
Tekstbehandling
------------------------
-String tekst
------------------------
Tekstbehandling(String tekst)
+int getAntallOrd()
+int getGjennomsnittligOrdlengde()
+double getGjennomsnittligAntallOrdPerPeriode()
+void setErstattOrd(String ord1, String ord2)
+String getTekst()
+String getTekstAllCaps()
*/

class Tekstbehandling {
	private final String tekst;
	String[] ord;


	Tekstbehandling(String tekst) {
		this.tekst = tekst;
	}

	public int getAntallOrd() {
		int antallOrd = 0;
		//StringBuilder tekst = new StringBuilder(tekst);
		String[] ord = tekst.split(" ");
		this.ord = ord;
		for (int i = 0; i < ord.length; i++) {
			antallOrd++;
		}
		return antallOrd;
	}

	public double getGjennomsnittligOrdlengde() {
		int totaltAntallBokstaver = 0;
		//blar gjennom hvert ord
		for (int i = 0; i < ord.length; i++) {
			//summerer alle bokstaver
			totaltAntallBokstaver += ord[i].length();
		}
		double gjennomsnittligOrdlengde = totaltAntallBokstaver / ord.length;
		return gjennomsnittligOrdlengde;
	}

	public double getGjennomsnittligAntallOrdPerPeriode() {
		//totaltantallord / antallperioder
		int antallPerioder = 0;
		String[] perioder = tekst.split(".:?!");
		//System.out.println(perioder[0]);
		antallPerioder = perioder.length;

		//System.out.println("antallperioder: " + antallPerioder);

		return (double) getAntallOrd() / (double) antallPerioder;
	}

	public String setErstattOrd(String ord1, String ord2) {
		String nyTekst = tekst.replace(ord1, ord2);
		return nyTekst;
		/*
		for (int i = 0; i < ord.length; i++) {
			//string.replace()

			if (ord1 == ord[i]) {
				ord[i] = ord2;
			}
		}
		*/
	}

	public String getTekst() {
		return tekst;
	}

	public String getTekstAllCaps() {
		return tekst.toUpperCase();
	}

/*
	public StringBuilder toString() {
		StringBuilder res = new StringBuilder("");
		res += "\nantall ord: " + getAntallOrd();
		res += "\ngjennomsnittlig ordlengde: " + getGjennomsnittligOrdlengde();
		res += "\ngjennomsnittlig antall ord per periode: " + getGjennomsnittligAntallOrdPerPeriode();
		res += "\ntekst: " + getTekst();
		res += "\ntekst all caps: " + getTekstAllCaps();
   		return res;
	}
*/
}