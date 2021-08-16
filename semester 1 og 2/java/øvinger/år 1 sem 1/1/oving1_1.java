import static javax.swing.JOptionPane.*;

class oving1_1{

	public static void main (String[] args) {

		//input
		String inchLest = showInputDialog("inch: ");
		double inch = Double.parseDouble(inchLest);

		//omregning
		double cm = inch * 2.54;

		//output
		System.out.println(inch + " inches = " + cm + " cm.");

	}
}