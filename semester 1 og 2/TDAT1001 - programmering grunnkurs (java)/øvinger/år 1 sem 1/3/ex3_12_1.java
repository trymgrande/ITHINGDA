import javax.swing.JOptionPane;


class ex3_1_2 {
	public static void main(String[] args) {


		int f�dselsdag = Integer.parseInt(JOptionPane.showInputDialog("f�dselsdag: "));

		if (f�dselsdag >= 1 && f�dselsdag <= 8) {
			System.out.println(113);
		}
		else if (f�dselsdag >= 9 && f�dselsdag <= 14) {
			System.out.println(120);
		}
		else if (f�dselsdag >= 15 && f�dselsdag <= 25) {
			System.out.println(125);
		}
		else if (f�dselsdag >= 26 && f�dselsdag <= 31) {
			System.out.println(134);
		}
		else {
			System.out.println("invalid input");
		}
	}
}