public class OppgaveOversikt {
    /*
    holder orden på hvor mange oppgaver hver enkelt student har fått godkjent.
    Forholdet mellom et objekt av denne klassen og studentobjektene er en aggregering.
     */
    private Student[] studenter = new Student[5];
    private int antStud = 0;
    public OppgaveOversikt() {}
    public boolean regNyStudent(String navn) {
        if (navn == null) {
            return false;
        }
        if (studenter.length == antStud) {
            utvidStudenter();
        }
        studenter[antStud] = new Student(navn);
        antStud++;
        return true;
    }
    public void utvidStudenter() {
        Student[] nyTab = new Student[studenter.length + 5];
        for (int i = 0; i < antStud; i++) {
            nyTab[i] = studenter[i];
        }
        studenter = nyTab;
    }
    public int finnAntStud() { // Dette skal være lik antStud.
        return antStud;
    }
    public int finnAntOppgaver(String navn) { // Metoden skal returnere -1 dersom student med dette navnet ikke eksisterer.
        //finn studentobjektet med navnet og hent antOppg fra matchende objekt dersom det eksisterer
        for (int i = 0; i < antStud; i++) {
            if (navn.equals(studenter[i].getNavn())) {
                return studenter[i].getAntOppg();
            }
        }
        return -1;

    }
    public boolean økAntOppg(String navn, int økning) { //Øk antall oppgaver for en bestemt student.
        for (int i = 0; i < antStud; i++) {
            if (navn.equals(studenter[i].getNavn())) {
                studenter[i].setAntOppg(studenter[i].getAntOppg() + økning);
                return true;
            }
        }
        return false;
    }
    public String[] finnAlleNavn() { // navnene til alle studentene
        String[] studenterNavn = new String[antStud];
        for (int i = 0; i < antStud; i++) {
            studenterNavn[i] = studenter[i].getNavn();
        }
        return studenterNavn;
    }
    public String toString() {
        String str = "";
        str += "Her kommer informasjon om alle studentene:\n";
        for (int i = 0; i < antStud; i++) {
            System.out.println(studenter[i].toString());
        }
        return str;
    }
}