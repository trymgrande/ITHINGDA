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

	public void �kAntOppg(int �kning) {
		antOppg += �kning;
	}

	public String toString() {
		return "studenten: " + navn + "har godkjent " + antOppg + " antall oppgaver";
	}
}