/*
//klasse
class Fravaerstabell
-int fravær

//konstruktør
+Skoleklasse
	-int[] fravær

//metoder
+getAntallElever
+setFravær(bestemt elev, ny fraværsverdi)
	if (bestemt elev ugyldig)
		return -1
+getGjennomsnittligFravær
+getAntallEleverUtenFravær


*/

class Skoleklasse{
	private int antallElever;
	private int[] fravær;
	private int gjennomsnittligFravær;
	private int antallEleverUtenFravær;

	public Skoleklasse(int antallElever) {
		if (antallElever >= 0) {
			this.antallElever = antallElever;
			fravær = new int[antallElever];
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	public int getAntallElever() {
		return antallElever;
	}
	public int getFravær(int elev) {
		if (elev >= 1 && elev <= antallElever) {
			return fravær[elev - 1];
		}
		else {
			return -1;
		}
	}


	public int setFravær(int elev, int verdi) {
		if (elev >= 1 && elev <= antallElever) {
			fravær[elev - 1] = verdi;
			return 0;
		}
		else {
			return -1;
		}
	}

	public int getGjennomsnittligFravær() {
		gjennomsnittligFravær = 0;
		for (int i = 0; i < antallElever; i++) {
			gjennomsnittligFravær += fravær[i];
			}
			gjennomsnittligFravær /= antallElever;
			return gjennomsnittligFravær;
	}

	public int getAntallEleverUtenFravær() {
		antallEleverUtenFravær = 0;
		for (int j = 0; j < antallElever; j++) {
			if (fravær[j] == 0) {
				antallEleverUtenFravær++;
			}
		}
		return antallEleverUtenFravær;
	}
}