/*
NyString
-{String}

NyString(String)

+Forkorting()
+Tegnfjerning()
*/

class NyString {
	private final String string;

	NyString(String string) {
		this.string = string;
	}
	//forkorting
	public StringBuilder Forkorting() {
		StringBuilder forkortelse = new StringBuilder("");
		String[] ord = string.split(" ");


		for (int i = 0; i < ord.length; i++) {
			forkortelse.append(ord[i].charAt(0));
		}
	return forkortelse;
	}
	//fjerne tegn
	public StringBuilder Tegnfjerning(char tegn) {
		StringBuilder utenTegn = new StringBuilder(string);
		//sjekker antall occurences
		int count = 0;
		for (int i=0; i < utenTegn.toString().length(); i++) {
			if (utenTegn.toString().charAt(i) == tegn) {
				 count++;
			}
		}
		for (int i = 0; i < count; i++) {
			int tegnIndex = utenTegn.toString().indexOf(tegn);
			utenTegn.deleteCharAt(tegnIndex);
		}
	return utenTegn;
	}
}