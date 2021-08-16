import java.util.ArrayList;

public class OppgaveOversikt {
    /*
    holder orden på hvor mange oppgaver hver enkelt student har fått godkjent.
    Forholdet mellom et objekt av denne klassen og studentobjektene er en aggregering.
     */
    private ArrayList<Student> studenter = new ArrayList<Student>();

    public OppgaveOversikt() {

    }
    public boolean regNyStudent(String navn) {
       studenter.add(new Student(navn));
        return true;
    }
    public int finnAntStud() { // Dette skal være lik antStud.
        return studenter.size();
    }
    public int finnAntOppgaver(String navn) { // Metoden skal returnere -1 dersom student med dette navnet ikke eksisterer.
        //finn studentobjektet med navnet og hent antOppg fra matchende objekt dersom det eksisterer
        for (int i = 0; i < studenter.size(); i++) {
            if (navn.equals(studenter.get(i).getNavn())) {
                return studenter.get(i).getAntOppg();
            }
        }
        return -1;

    }
    public boolean økAntOppg(String navn, int økning) { //Øk antall oppgaver for en bestemt student.
        for (int i = 0; i < studenter.size(); i++) {
            if (navn.equals(studenter.get(i).getNavn())) {
                studenter.get(i).setAntOppg(studenter.get(i).getAntOppg() + økning);
                return true;
            }
        }
        return false;
    }
    public String[] finnAlleNavn() { // navnene til alle studentene
        String[] studenterNavn = new String[studenter.size()];
        for (int i = 0; i < studenter.size(); i++) {
            studenterNavn[i] = studenter.get(i).getNavn();
        }
        return studenterNavn;
    }
    public String toString() {
        String str = "";
        str += "Her kommer informasjon om alle studentene:\n";
        for (int i = 0; i < studenter.size(); i++) {
            System.out.println(studenter.get(i).toString());
        }
        return str;
    }
}