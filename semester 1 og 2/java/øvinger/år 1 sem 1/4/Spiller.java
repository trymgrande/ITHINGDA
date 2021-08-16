/*
klasse Spiller

//attributter
-

//konstruktør
SpillerA(0)
SpillerB(0)

//metoder
+getSumPoeng()
+kastTerningen	()
+erFerdig()

*/
import java.util.Random;

class Spiller{
	//attributter
	private int sumPoeng ;
	private String navn;
	Random terning = new Random();


	//konstruktør
	public Spiller(String navn, int sumPoeng) {
		this.sumPoeng = sumPoeng;
		this.navn = navn;

	}

	//metoder

	public int kastTerningen() {
		//random funksjon
		int terningkast = terning.nextInt(6) + 1;

		sumPoeng += terningkast;
		return terningkast;
	}


	public int getSumPoeng() {


		return sumPoeng;
	}


	public boolean erFerdig() {
		if (sumPoeng > 99) {
			return true;
		}

		return false;
	}
}