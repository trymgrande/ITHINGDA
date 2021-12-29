/*
jlasse Tejstanalyse
-int[] antallTegn = new int[30]

+Tejstanalyse(tejst)
-tejst.lower()

+getAntallForsjielligeBojstaver()
+getAntallBojstaver()
+getProsentandelIjjeBojstaver()
+getAntallForejomster(bestemt bojstav)
+getBojstavMedFlestForejomster()
*/

class Tekstanalyse {

	private int[] antallTegn = new int[30];
	private int[] bokstavMedFlestForekomster = new int[1];
	private String tekst;
	private char bestemtBokstav;
	private int i;
	private int j;
	private int antallForskjelligeBokstaver;
	private int antallBokstaver;
	private double totaleTegn;
	private double ikkeBokstaver;
	private double prosentandelIkkeBokstaver;
	private int unicodeVerdi;
	private char tegn;
	private int antallForekomster;
	private char charMedFlestForekomster;

	//konstruktør
	Tekstanalyse(String tekst) {
		tekst = tekst.toLowerCase();
		this.tekst = tekst;
		//fyller ut tabell

		//blar gjennom hver bokstav i tekst
		for (i = 0; i < tekst.length(); i++){
			tegn = tekst.charAt(i);
			unicodeVerdi = tegn;

			//sjekker om unicodeVerdi er utenfor alfabetet
			if (unicodeVerdi < 97 || unicodeVerdi > 122) {
				antallTegn[29]++;
			}
			//sjekker om unicodeVerdi er innenfor alfabetet
				//æ
			else if (unicodeVerdi == 230) {
				antallTegn[26]++;
			}
				//ø
			else if (unicodeVerdi == 248) {
				antallTegn[27]++;
			}
				//å
			else if (unicodeVerdi == 229) {
				antallTegn[28]++;
			}
			else {
				antallTegn[unicodeVerdi-97]++;
			}
		}
	}

	public int getAntallForskjelligeBokstaver() {
		antallForskjelligeBokstaver = 0;
for (i = 0; i < this.antallTegn.length - 1; i++) {
			int unicodeVerdi = antallTegn[i];

			//sjekker om tegnet er i alfabetet
			//System.out.println("sjekker: " + i);
			if (antallTegn[i] > 0) {
				//System.out.println("> 0: " + i);
				antallForskjelligeBokstaver++;
			}
		}
		return antallForskjelligeBokstaver;
	}

	public int getAntallBokstaver() {
		antallBokstaver = 0;
		for (i = 0; i < this.antallTegn.length - 1; i++) {

			antallBokstaver += antallTegn[i];
		}
	return antallBokstaver;
	}

	public double getProsentandelIkkeBokstaver() {
		//ikkebokstaver / totaletegn

		//ikkebokstaver = totaletegn - antallbokstaver
		totaleTegn = tekst.length();

		//System.out.println("totaletegn" + totaleTegn);
		//System.out.println("getantallbokst" + getAntallBokstaver());
		antallBokstaver = getAntallBokstaver();
		ikkeBokstaver = totaleTegn - antallBokstaver;
		//System.out.println(ikkeBokstaver);
		//System.out.println("ikkebokstaver" + ikkeBokstaver);
		prosentandelIkkeBokstaver = (ikkeBokstaver / totaleTegn) * 100;
		//System.out.println("prosentikkebokstaver" + prosentandelIkkeBokstaver);
		return prosentandelIkkeBokstaver;
	}

	public int getAntallForekomster(char bestemtBokstav) {
		unicodeVerdi = bestemtBokstav;
		antallForekomster = antallTegn[bestemtBokstav - 97];
		return antallForekomster;
	}

	public int getBokstavMedFlestForekomster() {
		for (i = 0; i < antallTegn.length - 1 ; i++); {
			if (antallTegn[i] > bokstavMedFlestForekomster[0]); {
				bokstavMedFlestForekomster[0] = antallTegn[i];
				charMedFlestForekomster = (char)(bokstavMedFlestForekomster[0] + 96);
			}
		}
	return charMedFlestForekomster;
	}
}