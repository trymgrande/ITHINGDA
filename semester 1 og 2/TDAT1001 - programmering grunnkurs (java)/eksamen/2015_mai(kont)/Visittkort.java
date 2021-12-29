class Visittkort {
	private Navn navn;
	private int tlfMobil;
	private int tlfJobb;
	private String epost;

	public Visittkort(Navn navn, int tlfMobil, int tlfJobb, String epost) {
		this.navn = navn;
		this.tlfMobil = tlfMobil;
		this.tlfJobb = tlfJobb;
		this.epost = epost;
	}
	public Navn getNavn() {
		return navn;
	}
	public int getTlfMobil() {
		return tlfMobil;
	}
	public int getTlfJobb() {
		return tlfJobb;
	}
	public String getEpost() {
		return epost;
	}
	public String toString() {
		String temp = "";
		temp += this.getNavn().getEtternavn() + ", " + this.getNavn().getFornavn() + "\n";
		temp += "Mobiltlf: " + this.getTlfMobil() + "\n";
		temp += "Tlf jobb: " + this.getTlfJobb() + "\n";
		temp += "Epost: " + this.getEpost() + "\n";
		return temp;
	}
}