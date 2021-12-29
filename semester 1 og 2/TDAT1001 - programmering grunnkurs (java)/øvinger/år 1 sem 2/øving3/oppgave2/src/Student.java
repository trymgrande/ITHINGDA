public class Student {
    private final String navn;
    private int antOppg;

    public Student(String navn) {
        this.navn = navn;
        antOppg = 0;
    }
    public String getNavn() {
        return navn;
    }
    public int getAntOppg() {
        return antOppg;
    }
    public void setAntOppg(int antOppg) { //skal kaste unntaksobjekt av typen IllegalArgumentException hvis argumentet er negativt
        if (antOppg < 0) {
            throw new IllegalArgumentException("antall oppgaver kan ikke være negativt");
        }
        else {
            this.antOppg = antOppg;
        }
    }
    public String toString() {
        String str = "";
        str += "Navn: " + navn + ", antall oppgaver løst: " + antOppg + "\n";
        return str;
    }
}