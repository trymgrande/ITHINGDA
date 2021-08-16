//4.14.1
import javax.swing.JOptionPane;

class oving3_1 {
	public static void main(String[] args) {
		//input

		int start_gangetabell = Integer.parseInt(JOptionPane.showInputDialog("start gangetabell: "));

		int slutt_gangetabell = Integer.parseInt(JOptionPane.showInputDialog("slutt gangetabell: "));
		for (int tabell = start_gangetabell; tabell <= slutt_gangetabell; tabell ++) { //ta i mot start- og sluttgangetabell

			//x gangetabeller
			int factor = tabell;
			System.out.println(factor + "- gangen:\n");
			for (int i = 1; i <= 10; i++) {
				//x-gangen
				System.out.println(i + " x " + factor + " = " + (i * factor));
			}
			System.out.println("\n");
		}
	}
}