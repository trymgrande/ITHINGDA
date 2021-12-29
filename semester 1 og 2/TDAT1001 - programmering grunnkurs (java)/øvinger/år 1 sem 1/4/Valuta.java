//5.9.2
/*
klasse Valuta

//attributter
-valutanavn
-kurs

//konstruktør
Valuta(double kurs, String valutaNavn)

//metoder
+getValutanavn()
+getKurs()

*/

class Valuta {
	//private String valutaNavn;
	//private double kurs;

	String navn;
	double kurs;

	//konstruktør
	public Valuta(String navn, double kurs) {
		this.navn = navn;
		this.kurs = kurs;
	}

	//nok til valuta
	public double NokTilValuta(String navn, double input) {
		return input * kurs;
	}

	//valuta til nok
	public double ValutaTilNok(double input) {
		return input / kurs;
	}
	//getNavn
	public String getValutaNavn() {
		return navn;
	}
	//getgetKurs
	public double getKurs() {
		return kurs;
	}
}