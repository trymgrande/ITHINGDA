/**
 * SalgMedMeny.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Filen inneholder to klasser:
 *
 * SalgMedMeny: Inneholder main(), som lar brukeren gjøre menyvalg, og som
 * deretter sørger for å gjennomføre disse ved å sende melding til et objekt
 * av klassen SalgBGS.
 *
 * SalgBGS: Klassen beskriver grensesnittet mellom et objekt av
 * klassen Salgstall og brukeren. I tillegg til en metode for innlesing av menyvalg,
 * tilbyr den en metode for utførelse av hvert enkelt menyvalg.
 *
 */

import static javax.swing.JOptionPane.*;

class SalgMedMeny {
  public static void main(String[] args) {

    Salgstall salg = new Salgstall("mat", 4, 5);
    SalgBGS bgs = new SalgBGS(salg);

    int valg = bgs.lesValg();
    while (valg >= 0) {
      bgs.utførValgtOppgave(valg);
      valg = bgs.lesValg();
    }
  }
}

/**
 *
 * Klasse for brukerkommunikasjon.
 */

class SalgBGS {

  /* Konstanter til bruk i menyen */
  private final String[] ALTERNATIVER = {"Registrer nytt salg",
  "Finn salg for dag", "Finn salg for uke", "Finn totalsalg", "Avslutt"};
  private final int REG_SALG = 0;   // tallverdiene svarer til indeksen i
  private final int FINN_SALG = 1;  // tabellen over
  private final int FINN_SALG_UKE = 2;
  private final int FINN_TOTALSALG = 3;
  private final int AVSLUTT = 4;

  /* Objektet som vi skal kommunisere med */
  private Salgstall salg;  // se programliste 9.5 side 299.
  public SalgBGS(Salgstall salg) {
    this.salg = salg;
  }

  /**
   * Viser menyen som er rekke trykknapper.
   * Metoden returnerer brukerens valg som en indeks i tabellen ALTERNATIVER.
   * Hvis brukeren vil avslutte, returneres en negativ verdi.
   */
  public int lesValg() {
    int valg = showOptionDialog(null, "Gjør et valg", "Salgstall", DEFAULT_OPTION,
                PLAIN_MESSAGE, null, ALTERNATIVER, ALTERNATIVER[0]);
    if (valg == AVSLUTT) {
      valg = -1;
    }
    return valg;
  }

  /**
   * Hovedflyt for utførelse av valgt oppgave.
   */
  public void utførValgtOppgave(int valg) {
      switch (valg) {
      case REG_SALG:
        regSalg();
        break;
      case FINN_SALG:
        finnSalg();
        break;
      case FINN_SALG_UKE:
        finnSalgUke();
        break;
      case FINN_TOTALSALG:
        finnTotalsalg();
        break;
      default:
        break;
    }
  }

  /**
   *
   * Leser inn og registrerer salget en bestemt dag.
   *
   * Innlesing fra brukeren: Ukenr, dagnr og salget.
   * Utskrift: Bekreftelse, eventuelt feilmelding.
   */
  public void regSalg() {
    int maksUke = salg.finnAntUker();
    int uke = lesHeltall( "Oppgi ukenr (min 1, maks " + maksUke + ")", 1, maksUke);
    int maksDag = salg.finnAntDgPrUke();
    int dag = lesHeltall("Oppgi dagnr (min 1, maks " + maksDag + ")", 1, maksDag);
    String melding = "Uke: " + uke + ", dag: " + dag + ": ";

    String salgLest = showInputDialog("Oppgi salg: ");
    int salget = Integer.parseInt(salgLest);

    salg.settSalg(uke - 1, dag - 1, salget);  // inndata er kontrollert på forhånd
    melding += "Salg registrert, kr " + salget;
    showMessageDialog(null, melding);
  }

  /**
   *
   * Finner salget en bestemt dag.
   *
   * Innlesing fra brukeren: Ukenr og dagnr.
   * Utskrift: Salgstall, eventuelt feilmelding.
   */
  public void finnSalg() {
    int maksUke = salg.finnAntUker();
    int uke = lesHeltall( "Oppgi ukenr (min 1, maks " + maksUke + ")", 1, maksUke);
    int maksDag = salg.finnAntDgPrUke();
    int dag = lesHeltall("Oppgi dagnr (min 1, maks " + maksDag + ")", 1, maksDag);
    String melding = "Uke: " + uke + ", dag: " + dag + ": ";

    int salget = salg.finnSalg(uke - 1, dag - 1); // inndata er kontrollert på forhånd
    melding += "Salg denne dag kr " + salget;
    showMessageDialog(null, melding);
  }

  /**
   *
   * Finner salget en bestemt uke.
   *
   * Innlesing fra brukeren: Ukenr.
   * Utskrift: Salgstall, eventuelt feilmelding.
   */
  public void finnSalgUke() {
    int maksUke = salg.finnAntUker();
    int uke = lesHeltall( "Oppgi ukenr (min 1, maks " + maksUke + ")", 1, maksUke);
    int salget = salg.finnSalgForHelUke(uke - 1);  // inndata er kontrollert på forhånd
    showMessageDialog(null, "Salget uke " + uke + " var kr " + salget);
  }

  /**
   *
   * Finner totalsalget.
   *
   * Innlesing fra brukeren: Ingenting.
   * Utskrift: Salgstall.
   */
  public void finnTotalsalg() {
    showMessageDialog(null, "Total salg kr " + salg.finnTotalsalg());
  }

  /*
   * Hjelpemetode. Leser inn et heltall som må ligge innenfor et lukket intervall.
   * Parametere:
   *   ledetekst: Ledeteksten som skrives til brukeren,
   *                 den bør inneholde informasjon om intervallet.
   *   nedre: Nedre gyldige verdi.
   *   øvre: Øvre gyldige verdi.
   */
  private int lesHeltall(String ledetekst, int nedre, int øvre) {
    String tallLest = showInputDialog(ledetekst);
    int tall = Integer.parseInt(tallLest);
    while (tall < nedre || tall > øvre) {
      tallLest = showInputDialog("Tallet du skriver må være større eller lik " + nedre
                                   + " og mindre eller lik " + øvre + ". \nPrøv på nytt: ");
      tall = Integer.parseInt(tallLest);
    }
    return tall;
  }
}
