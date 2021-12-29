class Mobil {
	private String modell;
	private int egetNr;
	private Visittkort[] visittkortliste;
	private int antallVisittkort;
	private final int MAKS_ANTALL_VISITTKORT;

	Mobil(String modell, int egetNr, int MAKS_ANTALL_VISITTKORT) {
		this.modell = modell;
		this.egetNr = egetNr;
		visittkortliste = new Visittkort[MAKS_ANTALL_VISITTKORT];
		this.MAKS_ANTALL_VISITTKORT = MAKS_ANTALL_VISITTKORT;
	}
	public boolean registrerNyttVisittkort(Visittkort nyttVisittkort) {
		if (antallVisittkort == MAKS_ANTALL_VISITTKORT) {
			return false;
		}
		for (int i = 0; i < antallVisittkort; i++) {
			if (nyttVisittkort.getNavn().compareTo(visittkortliste[i].getNavn()) == 0) {
				return false;
			}
		}
		visittkortliste[antallVisittkort] = nyttVisittkort;
		return true;
	}
	public int printRegistrerteVisittkort() {
		try {
			for (int i = 0; i < antallVisittkort-1; i++) {
				visittkortliste[i].toString();
			}
			return 0;
		}
		catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}
}