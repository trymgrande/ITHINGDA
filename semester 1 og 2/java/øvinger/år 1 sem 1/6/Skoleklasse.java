/*
//klasse
class Fravaerstabell
-int frav�r

//konstrukt�r
+Skoleklasse
	-int[] frav�r

//metoder
+getAntallElever
+setFrav�r(bestemt elev, ny frav�rsverdi)
	if (bestemt elev ugyldig)
		return -1
+getGjennomsnittligFrav�r
+getAntallEleverUtenFrav�r


*/

class Skoleklasse{
	private int antallElever;
	private int[] frav�r;
	private int gjennomsnittligFrav�r;
	private int antallEleverUtenFrav�r;

	public Skoleklasse(int antallElever) {
		if (antallElever >= 0) {
			this.antallElever = antallElever;
			frav�r = new int[antallElever];
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	public int getAntallElever() {
		return antallElever;
	}
	public int getFrav�r(int elev) {
		if (elev >= 1 && elev <= antallElever) {
			return frav�r[elev - 1];
		}
		else {
			return -1;
		}
	}


	public int setFrav�r(int elev, int verdi) {
		if (elev >= 1 && elev <= antallElever) {
			frav�r[elev - 1] = verdi;
			return 0;
		}
		else {
			return -1;
		}
	}

	public int getGjennomsnittligFrav�r() {
		gjennomsnittligFrav�r = 0;
		for (int i = 0; i < antallElever; i++) {
			gjennomsnittligFrav�r += frav�r[i];
			}
			gjennomsnittligFrav�r /= antallElever;
			return gjennomsnittligFrav�r;
	}

	public int getAntallEleverUtenFrav�r() {
		antallEleverUtenFrav�r = 0;
		for (int j = 0; j < antallElever; j++) {
			if (frav�r[j] == 0) {
				antallEleverUtenFrav�r++;
			}
		}
		return antallEleverUtenFrav�r;
	}
}