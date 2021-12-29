//3.12.4
//A koster 35,90 for 450 g, B koster 39,50 for 500 g
//regne grampris

import javax.swing.JOptionPane;

class oving2_1 {
	public static void main(String[] args) {

		//input
			//dialog
		/*
		String prisProduktA = JOptionPane.showInputDialog("Pris produkt A (kr): ");
		String vektProduktA = JOptionPane.showInputDialog("Vekt produkt A (g): ");
		String prisProduktB = JOptionPane.showInputDialog("Pris produkt B (kr): ");
		String vektProduktB = JOptionPane.showInputDialog("Vekt produkt B (g): ");

			//parsing
		double doublePrisProduktA = Double.parseDouble(prisProduktA);
		double doublePrisProduktB = Double.parseDouble(prisProduktB);
		double doubleVektProduktA = Double.parseDouble(vektProduktA);
		double doubleVektProduktB = Double.parseDouble(vektProduktB);
		*/

		double doublePrisProduktA = 35.9;
		double doublePrisProduktB = 39.5;
		double doubleVektProduktA = 450;
		double doubleVektProduktB = 500;

		//omregning til kr/g
			//A
		double gramprisA = doublePrisProduktA / doubleVektProduktA;
			//B
		double gramprisB = doublePrisProduktB / doubleVektProduktB;

		//sammenligning
		if (gramprisA > gramprisB) {
			System.out.println("Produkt B er billigst");
		}
		else if (gramprisB > gramprisA) {
			System.out.println("Produkt A er billigst");
		}

		//output
		System.out.println("grampris A: " + gramprisA + "\n" + "grampris B: " + gramprisB);

	}
}