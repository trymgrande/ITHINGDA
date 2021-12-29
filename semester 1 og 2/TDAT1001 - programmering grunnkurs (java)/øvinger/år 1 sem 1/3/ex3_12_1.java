import javax.swing.JOptionPane;


class ex3_1_2 {
	public static void main(String[] args) {


		int fødselsdag = Integer.parseInt(JOptionPane.showInputDialog("fødselsdag: "));

		if (fødselsdag >= 1 && fødselsdag <= 8) {
			System.out.println(113);
		}
		else if (fødselsdag >= 9 && fødselsdag <= 14) {
			System.out.println(120);
		}
		else if (fødselsdag >= 15 && fødselsdag <= 25) {
			System.out.println(125);
		}
		else if (fødselsdag >= 26 && fødselsdag <= 31) {
			System.out.println(134);
		}
		else {
			System.out.println("invalid input");
		}
	}
}