//4.14.5

import javax.swing.JOptionPane;

class oving3_2 {
	public static void main(String[] args){

		//input + parse
		int num = Integer.parseInt(JOptionPane.showInputDialog("number to test for prime: "));

		Boolean notPrime = false;

		//looper gjennom alle ints fra seg selv til 0
		for (int i = num - 1; i > 1 ; i--) {
			//System.out.println(i);
			//tester divisjon
			if (num % i == 0) {
				notPrime = true;
				//System.out.println(num + " delelig med " + i + ":\n" + true);
				break;
			}
			else {
				//System.out.println(num + " delelig med " + i + ":\n" + false);

			}
		}
	System.out.println(num + " primtall: " + !notPrime);

	}
}