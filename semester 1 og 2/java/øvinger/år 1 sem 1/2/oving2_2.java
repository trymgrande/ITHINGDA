//3.12.3
//
import javax.swing.JOptionPane;

class oving2_2 {
	public static void main(String[] args) {

		//input
		int ar = Integer.parseInt(JOptionPane.showInputDialog("år: "));
		if (ar % 100 == 0) {
			//delelig med 100
			if (ar % 400 == 0){

				System.out.println("skuddar");
			}

			else {

				System.out.println("ikke skuddar");
			}
		}
		else {
			if (ar % 4 == 0) {

				System.out.println("skuddar");
			}
			else {
				System.out.println("ikke skuddar");
			}
		}
	}
}

