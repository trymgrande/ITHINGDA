//klassen skal ha objekt av klassen java.util.random som objektvariabel. bruker dette til å generere neste tilfeldige tall, resultatet transformeres innenfor intervallet med metodene.
import javax.swing.JOptionPane;


class MinRandom {

java.util.Random random = new java.util.Random();

	public int nesteHeltall(int nedre, int ovre) {//intervallet [nedre, ovre>
		return random.nextInt((ovre - nedre) + 1) + nedre;
	}

	public double nesteDesimaltall(double nedre, double ovre) {//intervallet [nedre, ovre>

		return random.nextDouble() * (ovre - nedre) + nedre;
	}


	//klient

	public static void main(String[] args) {
		int nedre = 1;
		int ovre = 10;
		MinRandom random = new MinRandom();

		for (int i =0; i < 1000; i++) {
			System.out.println(random.nesteHeltall(nedre, ovre));
			System.out.println(random.nesteDesimaltall(nedre, ovre));
		//	String blank = JOptionPane.showInputDialog(' ');
		}
	}
}