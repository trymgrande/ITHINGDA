import javax.swing.*;
class Klient {


	public static void main(String[] args) {
		Oppgaveoversikt enOppgaveoversikt = new Oppgaveoversikt();
		String buttons[]={"registrere ny stud","get oppgaver for", "�ke antall oppgaver for"};
		String navn;

		while (true) {

			//cls
			for (int i = 0; i < 50; i++) {System.out.println();}

			//print
			enOppgaveoversikt.toStringAllClasses();

			int x = JOptionPane.showOptionDialog(null, "Valgmeny for Klientprogram",  "Dialogboks",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,buttons,"default");

			switch (x) {
				//registrere ny stud
				case 0: navn = JOptionPane.showInputDialog("navn:");
						enOppgaveoversikt.registrerEnStudent(navn);
						System.out.println("registrert student: " + navn);
						break;
				//get oppgaver for stud
				case 1: navn = JOptionPane.showInputDialog("navn:");
						int antallOppgaver = enOppgaveoversikt.getAntallOppgaverFor(navn);
						System.out.println("antall oppgaver for " + navn + ": " + antallOppgaver);
						break;
				//�ke antall oppgaver for
				case 2: navn = JOptionPane.showInputDialog("navn:");
						int �kning = Integer.parseInt(JOptionPane.showInputDialog("�kning:"));
						enOppgaveoversikt.�kAntallOppgaverFor(navn, �kning);
						System.out.println("antall oppgaver for " + navn + " �kt med " + �kning);
						break;
				default: System.out.println("invalid input");
						break;
			}
			JOptionPane.showInputDialog("press enter to refresh...");
		}
	}
}
//bugs:
//antall oppgaver returnerer -1
//kan ikke legge til mer enn en student