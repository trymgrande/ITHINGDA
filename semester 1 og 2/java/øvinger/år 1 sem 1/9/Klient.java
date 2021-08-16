import java.io.Console;
import javax.swing.JOptionPane;

class Klient {
	public static void main(String[] args) {
		String n = "\n";
		Console console = System.console();
		//String s = console.readLine();
		String rawInput;
		int input;
		String buttons[]={"endre månedslønn","endre skatteprosent"};
		ArbTaker nyArbTaker = new ArbTaker("Gunnar", "Gunnarsen", 1999, 1, 2010, 3000, 40);

		//kaller alle metoder inkl toString
		System.out.println("---toString start---\n" + nyArbTaker + "\n---toString end---\n\n");
		System.out.println("klasse Person:\n" + nyArbTaker.personalia.getFornavn()+n + nyArbTaker.personalia.getEtternavn()+n + nyArbTaker.personalia.getFodselsar()+n);
		System.out.println("klasse ArbTaker:"+n + nyArbTaker.getArbtakernr()+n + nyArbTaker.getAnsettelsesar()+n + nyArbTaker.getManedslonn()+n + nyArbTaker.getSkatteprosent()+n + nyArbTaker.setManedslonn(2500)+n + nyArbTaker.setSkatteprosent(50)+n + nyArbTaker.getSkattetrekkPerManed()+n + nyArbTaker.getBruttoLonnPerAr()+n + nyArbTaker.getSkattetrekkPerAr()+n + nyArbTaker.getNavn()+n + nyArbTaker.getAlder()+n + nyArbTaker.getAntallArAnsatt()+n + nyArbTaker.getAnsattMerEnn(10));
		//pause
		JOptionPane.showInputDialog("trykk enter for å fortsette...");


		while (true) {
			//printer oppdaterte metoder
			for (int i = 0; i < 50; ++i) {System.out.println();} //cls
			System.out.println("klasse ArbTaker:"+n + nyArbTaker.getArbtakernr()+n + nyArbTaker.getAnsettelsesar()+n + nyArbTaker.getManedslonn()+n + nyArbTaker.getSkatteprosent()+n + nyArbTaker.getSkattetrekkPerManed()+n + nyArbTaker.getBruttoLonnPerAr()+n + nyArbTaker.getSkattetrekkPerAr()+n + nyArbTaker.getNavn()+n + nyArbTaker.getAlder()+n + nyArbTaker.getAntallArAnsatt()+n + nyArbTaker.getAnsattMerEnn(10));

			//valg
			int x = JOptionPane.showOptionDialog(null, "Endre:",  "Dialogboks",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,buttons,"default");

			switch (x) {
				case 0: rawInput = JOptionPane.showInputDialog("ny månedslønn");
						input = Integer.parseInt(rawInput);
						nyArbTaker.setManedslonn(input);
						break;
				case 1: rawInput = JOptionPane.showInputDialog("ny skatteprosent");
						input = Integer.parseInt(rawInput);
						nyArbTaker.setSkatteprosent(input);
						break;
				default: System.out.println("invalid input");
				break;
			}
		}
	}
}