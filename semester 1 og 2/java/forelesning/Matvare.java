/*
//enkel detaljering

//klasse varenavn
Matvare

//attributter
-varevarenavn
-energi
-fett
-karbo

//konstruktør
+getVarenavnavn()
+getEnergi()
+getFett()
+getKarbo()

--------------------

//videre detaljering

//klasse varenavn
Matvare

//attributter
-String varenavn {readonly}
-int energi {readonly}
-double fett {readonly}
-double karbo {readonly}

//konstruktør
+Matvare(String varenavn, int energi, double fett, double karbo)
+String getVarenavn()
+int getEnergi()
+getFett()
+getKarbo()
*/

class Matvare {
	private final String varenavn;
	private final int energi;
	private final double fett;
	private final double karbo;

	public Matvare(String varenavn, int energi, double fett, double karbo) {
		this.varenavn = varenavn;
		this.energi = energi;
		this.fett = fett;
		this.karbo = karbo;
	}
	public String getVarenavn() {
		return varenavn;
	}
	public int getEnergi() {
		return energi;
	}
	public double fett() {
		return fett;
	}
	public double karbo() {
		return karbo;
	}
	public int fettGram(int gram) {
		return gram;
	}
	public String toString() {
		return varenavn + '\n' + energi + '\n' + fett + '\n' + karbo + '\n' + gram;
	}
}