import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

class Student {
    private final String navn;
    private final String fdato;
    public static final String GYLDIG_KAR = "ABCDEF";

    public Student(String navn, String fdato) {
        this.navn = navn;
        this.fdato = fdato;
    }

    public String getNavn() {
        return navn;
    }

    public int getAlder() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMyyyy");
        Period periode = Period.between(LocalDate.parse(fdato, format), LocalDate.now());
        return periode.getYears();
    }
    // *** Oppgave 2 starter her

    private char[] karakterer = new char[3];
    private int antall = 0;

    public int registrerNyKarakter(char karakter) {
        //sjekke om karakteren er gyldig
        if (sjekkeOmKarakterErGyldig(karakter) != 0) { //hvis ugyldig karakter
            return 1;
        }
        //bytter til uppercase
        karakter = Character.toUpperCase(karakter);
        //utvide dersom tabellen er full
        if (karakterer.length == antall) {
            utvidKaraktererTabell();
        }
        //legger til karakter
        karakterer[antall] = karakter;
        antall++;
        return 0;
    }
    public int sjekkeOmKarakterErGyldig(char karakter) {
        for (int i = 0; i < GYLDIG_KAR.length(); i++) {
            if (Character.toUpperCase(karakter) == GYLDIG_KAR.charAt(i)) {
                return 0;
            }
        }
        return 1;
    }
    private int utvidKaraktererTabell() {
        char[] karaktererKopi = new char[antall+1];
        for (int i = 0; i < antall; i++) {
            karaktererKopi[i] = karakterer[i];
        }
        karakterer = karaktererKopi;
        return 0;
    }

    public char finnGjennomsnittskarakteren() {
        //omform til heltall
        int[] karaktererIntegers = new int[antall];
        for (int i = 0; i < antall; i++) {
            karaktererIntegers[i] = (char) karakterer[i];
        }
        //regn ut snitt
            //summere karakterer
        int karaktererSum = 0;
        for (int i = 0; i < antall; i++) {
            karaktererSum += karakterer[i];
        }
            //dele på antall
        int gjennomsnitt = karaktererSum / antall;
        //omform tilbake til char
        char gjennomsnittKarakter = (char) gjennomsnitt;
        return gjennomsnittKarakter;
    }

    public String toString() { //navn, fødselsdato og alle registrerte karakterer med mellomrom mellom
        String str = "Navn: " + getNavn() + "\nAlder: " + getAlder() + "\nKarakterer: ";
        for (int i = 0; i < antall; i++) {
            str += karakterer[i] + " ";
        }
        return str;
    }
}