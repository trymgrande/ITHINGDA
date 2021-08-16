class Navn {
	private String fornavn;
	private String etternavn;

	public Navn(String fornavn, String etternavn) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
	}
	public String getFornavn() {
		return fornavn;
	}
	public String getEtternavn() {
		return etternavn;
	}
	public Boolean equals(Navn detAndre) {
		if (this.fornavn.equals(detAndre.getFornavn()) && this.etternavn.equals(detAndre.getEtternavn())) {
			return true;
		}
		else {
			return false;
		}
	}
	public int compareTo(Navn detAndre) {
		//hvis etternavnet er likt
		if (this.getEtternavn().compareTo(detAndre.getEtternavn()) == 0) {
			//return compareto fornavn
			return this.getFornavn().compareTo(detAndre.getFornavn());
		}
		else {
			//hvis ikke, return compareto etternavn
			return this.getEtternavn().compareTo(detAndre.getEtternavn());
		}
	}
	//navn1.compareto(navn2)
	//this.compareto(detandre)
	//returns -1
}