/*
ArbTaker
----------
-Person personalia
-int arbtakernr
-int ansettelsesar
-int manedslonn
-int skatteprosent
----------
ArbTaker()
+Person getPersonalia()
+int getArbtakernr()
+int getAnsettelsesar()
+int getManedslonn()
+int getSkatteprosent()
+int setManedslonn
+int setSkatteprosent()
								|samarbeid med personalia-objektet:
+int getSkattetrekkPerManed()	|false
+int getBruttolonnPerar()		|false
+int getSkattetrekkPerar()		|false
+String getNavn()				|true
+int getAlder()					|(true)
+int antallarAnsatt()			|(true)
+boolean ansattMerEnn(ar)		|false
*/

class ArbTaker {
	java.util.GregorianCalendar kalender = new java.util.GregorianCalendar();
	int år = kalender.get(java.util.Calendar.YEAR);
	public Person personalia;
	private int arbtakernr;
	private int ansettelsesar;
	private int manedslonn;
	private int skatteprosent;

	ArbTaker(String fornavn, String etternavn, int fodselsar, int arbtakernr, int ansettelsesar, int manedslonn, int skatteprosent) {
		personalia = new Person(fornavn, etternavn, fodselsar);
		//this.personalia = personalia;
		this.arbtakernr = arbtakernr;
		this.ansettelsesar = ansettelsesar;
		this.manedslonn = manedslonn;
		this.skatteprosent = skatteprosent;
	}

	public int getArbtakernr() {
		return arbtakernr;
	}

	public int getAnsettelsesar() {
		return ansettelsesar;
	}

	public int getManedslonn() {
		return manedslonn;
	}

	public int getSkatteprosent() {
		return skatteprosent;
	}

	public int setManedslonn(int nyManedslonn) {
		this.manedslonn = nyManedslonn;
		return this.manedslonn;
	}

	public int setSkatteprosent(int nySkatteprosent) {
		this.skatteprosent = nySkatteprosent;
		return this.skatteprosent;
	}


	public int getSkattetrekkPerManed() {
		//månedslønn * skatteprosent%
		return (int) (this.manedslonn  * ((double) this.skatteprosent / 100));
	}

	public int getBruttoLonnPerAr() {
		return manedslonn * 12;
	}

	public double getSkattetrekkPerAr() {
		double antallMnd = 12 - 1.5;
		return (getSkattetrekkPerManed() * antallMnd);
	}

	public String getNavn() {
		String fornavn = personalia.getFornavn();
		String etternavn = personalia.getEtternavn();

		return etternavn + ", " + fornavn;
	}

	public int getAlder() {
		return år - personalia.getFodselsar();
	}

	public int getAntallArAnsatt() {

		return (år - ansettelsesar);
	}

	public boolean getAnsattMerEnn(int ar) {
		if (getAntallArAnsatt() > ar) {
			return true;
		}
		else {
			return false;
		}
	}

   public String toString(){
	   String res = "klasse ArbTaker\n";
	   res += "personalia: " + personalia;
	   res += "\narbtakernr: " + arbtakernr;
	   res += "\nansettelsesar: " + ansettelsesar;
	   res += "\nmanedslonn: " + manedslonn;
	   res += "\nskatteprosent: " + skatteprosent;
	   return res;
	}
}