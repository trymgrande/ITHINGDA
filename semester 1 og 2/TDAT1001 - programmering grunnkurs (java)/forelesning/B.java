//i = tellende linje
//j = siste linje
// i2 = tellende stjerne
//j2 = siste stjerne

import javax.swing.JOptionPane;
class B {
	public static void main(String[] args) {

		//brukerinput int [0, evig>
		int j = Integer.parseInt(JOptionPane.showInputDialog("Antall linjer: "));

		//printe int antall stjerner

			//for linjer
			for (int i = 1; i <= j; i++) {

				//for ant stjerner
				int j2 = i;
				for (int i2 = 1; i2 <= j2; i2++) {

					//print
					System.out.print("*");
				}
				System.out.println();
			}
	}
}