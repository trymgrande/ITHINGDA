class Student {
	private String navn;
	private int antOppg;

	Student(String navn) {

		this.navn = navn;
	}

	public String getNavn() {
		return navn;
	}

	public int getAntOppg() {
		return antOppg;
	}

	public void økAntOppg(int økning) {
		antOppg += økning;
	}

	public String toString() {
		return "studenten: " + navn + "har godkjent " + antOppg + " antall oppgaver";
	}
}