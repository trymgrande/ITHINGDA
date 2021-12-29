class Oppgaveoversikt {
	private Student[] studenter;
	int antallStud = 0;

	Oppgaveoversikt() {
		studenter = new Student[antallStud + 10];
	}

	public int getAntallStudenterRegistrert() {
		return antallStud;
	}

	public int getAntallOppgaverFor(String navn) {
		int antallOppgaver;
		//finner studentobjekt gitt navn
		for (int i = 0; i < antallStud; i++) {
			//System.out.println("debugging: " + studenter[i]); //debugging
			if (navn.equals(studenter[i].getNavn())) {
				return studenter[i].getAntOppg();
			}
		}
		return -1;
	}

	public void registrerEnStudent(String navn) {
		if (studenter.length == antallStud) {
			//kopier til ny liste
			Student[] nyStudenter = new Student[antallStud + 10];
			for (int i = 0; i < studenter.length; i++) {
				nyStudenter[i] = studenter[i];
			}
			studenter = nyStudenter;
		}
		Student enStudent = new Student(navn);
		studenter[antallStud] = enStudent;
		antallStud++;

	}

	public void �kAntallOppgaverFor(String navn, int �kning) {
		//finner studentobjekt gitt navn
		for (int i = 0; i < antallStud; i++) {
			if (navn.equals(studenter[i].getNavn())) {
				studenter[i].�kAntOppg(�kning);
			}
		}
	}

	public String toString() {
	   return "antall studenter er: " + antallStud;
	}

	public void toStringAllClasses() {
		System.out.println("antall studenter registrert: " + getAntallStudenterRegistrert());
		for (int i = 0; i < antallStud; i++) {
			System.out.println("navn: " + studenter[i].getNavn() + "\tantall oppgaver: " + getAntallOppgaverFor(studenter[i].getNavn()) + "\n");
		}
	}
}