class Ordbok implements java.io.Serializable {
	private String ordbokNavn;
	private Ord[] ordbok;
	private int antallReg;
	private final int MAKS_ANTALL_ORD = 10;
	private String filnavn = "ordliste.ser";

	public Ordbok() {
		try {
			//sjekke om data finnes på ordliste

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}