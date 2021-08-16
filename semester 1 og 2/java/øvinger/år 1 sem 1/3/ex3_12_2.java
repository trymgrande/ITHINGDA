import javax.swing.JOptionPane;


class ex3_12_2 {
	public static void main(String[] args) {

		int tall1 = Integer.parseInt(JOptionPane.showInputDialog("tall 1: "));
		int tall2 = Integer.parseInt(JOptionPane.showInputDialog("tall 2: "));
		int tall3 = Integer.parseInt(JOptionPane.showInputDialog("lukket intervall start: "));
		int tall4 = Integer.parseInt(JOptionPane.showInputDialog("lukket intrevall slutt: "));

		//sjekker om tall1 er positivt
		if (tall1 >= 0) {
			System.out.println("tall 1 er positivt");
		}
		else {
			System.out.println("tall 1 er negativt");
		}

		//sjekker om tall1 er delelig med tall2
		if (tall1 % tall2 == 0) {
			System.out.println("tall 1 er delelig med tall 2");
		}
		else {
			System.out.println("tall 1 er ikke delelig med tall 2");
		}
		//sjekker om tall1 er i et lukket intervall med tall3 og tall4
		if (tall1 >= tall3 && tall1 <= tall4) {
			System.out.println("tall 1 er innenfor lukket intervall");
		}
		else {
			System.out.println("tall 1 er ikke innenfor lukket intervall");
		}
	}
}