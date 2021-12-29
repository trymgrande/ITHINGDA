class Ord {
	String ord;
	String[] definisjoner;

	public Ord(String ord, String[] definisjoner) {
		this.ord = ord;
		this.definisjoner = definisjoner;
	}
	String getOrd() {
		return ord;
	}
	String[] getDefinisjoner() {
		return definisjoner;
	}
	public boolean equals(Object o) {
		//sjekk instance of
		if (!(o instanceof Ord)) {
			return false;
		}
		//sjekk om objektene er like
		if (this == o) {
			return true;
		}
		//caster og sjekker om attributten ord er like
		Ord ord2 = (Ord) o;
		return this.ord.equals(ord2.getOrd());
	}
	public String toString() {
		String tempString = "";
		tempString += ord+":\n";
		for (int i = 0; i < definisjoner.length-1; i++) {
			tempString += (i+1)+". "+definisjoner[i];
		}
		return tempString;
	}
	public void utvidDefinisjoner() {
		String[] tempDefinisjoner = new String[definisjoner.length+1];
		for (int i = 0; i < definisjoner.length; i++) {
			tempDefinisjoner[i] = definisjoner[i];
		}
		definisjoner = tempDefinisjoner;
	}
	public boolean sjekkGyldigDefinisjon(String nyDefinisjon) {
		for (int i = 0; i < definisjoner.length; i++) {//sjekker hver def
			if (nyDefinisjon == definisjoner[i]) {
				System.out.println("definisjon finnes allerede");
				return false;
			}
		}
		return true;
	}
	public boolean leggTilDefinisjon(String nyDefinisjon) {
		//sjekke om def er reg fra før
		if (sjekkGyldigDefinisjon(nyDefinisjon)) {
			//legg til def
			utvidDefinisjoner();
			definisjoner[definisjoner.length-1] = nyDefinisjon;
			return true;
			}
		else {
			return false;
		}
	}

}