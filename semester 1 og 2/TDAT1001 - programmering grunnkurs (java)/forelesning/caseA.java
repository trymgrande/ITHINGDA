//

import javax.swing.JOptionPane;
class caseA {
	public static void main(String[] args) {

		int mnd = 1;
		double saldo = 12600;
		double rentesats = 0.18;
		double totRenter = 0;
		double totRenterOgAvdrag = 0;
		double totAvdrag = 0;

		for (mnd = 1; mnd <= 36 ; mnd++) {

				System.out.println("saldo starten av mnd " + mnd + ": " + saldo);
				double renter = (saldo / 12) * rentesats;
				double avdrag = 350;
				double renterOgAvdrag = renter + avdrag;

				saldo -= avdrag;


				totRenter += renter;
				totRenterOgAvdrag += renterOgAvdrag;
				totAvdrag += avdrag;
				System.out.println("totRenterOgAvdrag: " + totRenterOgAvdrag);

				/*
				if (saldo <= renterOgAvdrag) {
					//siste avdrag: renter + resten
					saldo = 0;
					break;
				}
				else {
					saldo -= renterOgAvdrag;
				}
				*/

			System.out.println("saldo slutten av mnd " + mnd + ": " + saldo + "\n" + "renter + avdrag: " + renterOgAvdrag + "\n" + "tot avdrag: " + totAvdrag + "\n" + "renter: " + renter);

			}//for

		System.out.println("renter + avdrag: " + totRenterOgAvdrag + "\ntotale renter: " + totRenter);

	}//main
}//class