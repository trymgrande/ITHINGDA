/*
Person
----------
-String fornavn {readonly}
-String etternavn {readonly}
-int fodselsar {readonly}
----------
Person(fornavn, etternavn, fodselsar)
+String getFornavn()
+String getEtternavn()
+int getFodselsar()
*/

class Person {
	private final String fornavn;
	private final String etternavn;
	private final int fodselsar;

	Person(String fornavn, String etternavn, int fodselsar) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.fodselsar = fodselsar;
	}

	public String getFornavn() {
		return fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public int getFodselsar() {
		return fodselsar;
	}

   public String toString(){
	   String res = "klasse: person\n";
	   res += "fornavn: " + fornavn;
	   res += "\netternavn: " + etternavn;
	   res += "\nfodselsar " + fodselsar;
	   return res;
	}
}