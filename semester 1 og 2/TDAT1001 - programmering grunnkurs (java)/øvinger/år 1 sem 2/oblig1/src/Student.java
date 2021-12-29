import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

class Student {
  private final String navn;
  private final String fodselsDato;


  public Student(String navn, String fodselsDato) {
    this.navn = navn;
    this.fodselsDato = fodselsDato;
  }

  public String getNavn() {
    return navn;
  }

  public int getAlder() {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMyyyy");
    LocalDate dato = LocalDate.parse(fodselsDato, format);
    Period alder = Period.between(LocalDate.now(), dato);
    return alder.getYears();
  }
}