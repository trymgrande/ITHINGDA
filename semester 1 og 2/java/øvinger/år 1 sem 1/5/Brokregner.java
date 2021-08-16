/*

klasse BrokRegner
-int teller
-int nevner

+konstruktør1(teller, nevner)
+konstruktør2(teller)

+getTeller()
+getNevner()

+addere()
+subrahere()
+multiplisere()
+dividere()

+toString()

*/
import javax.swing.JOptionPane;

class Brokregner {
	//this-brøk
	private int teller;
	private int nevner;

	//konstrukrør 1
	public Brokregner(int teller, int nevner) {
		this.teller = teller;
		if (nevner == 0) {
			throw new IllegalArgumentException("nevner kan ikke være = 0");
		}
		else {
			this.nevner = nevner;
		}
	}
	//konstruktør 2
	public Brokregner(int teller) {
		this.teller = teller;
		this.nevner = 1;
	}

	public int getTeller() {
		System.out.println(this.teller);//test
		return teller;
	}

	public int getNevner() {
		System.out.println(this.nevner);//test
		return nevner;
	}

	public void addere(int teller, int nevner) {
		//test
		//System.out.println( teller + this.teller);
		if (nevner == this.nevner) {
			this.teller += teller;
		}
		else {//finner felles nevner
			this.teller = (this.teller * nevner) + (teller * this.nevner);
			this.nevner = (this.nevner * nevner) + (nevner * this.nevner);
		}
	}

	public void subtrahere(int teller, int nevner) {
		if (nevner == this.nevner) {
			this.teller -= teller;
		}
		else {//finner felles nevner
			this.teller = (this.teller * nevner) + (teller * this.nevner);
			this.nevner = (this.nevner * nevner) + (nevner * this.nevner);
		}
	}

	public void multiplisere(int teller, int nevner) {
		this.teller *= teller;
		this.nevner *= nevner;
	}

	public void dividere(int teller, int nevner) {
		int nyNevner = teller;
		int nyTeller = nevner;
		this.teller *= nyTeller;
		this.nevner *= nyNevner;
	}


	//klient
	public static void main(String[] args) {
		boolean interrupt = false;

		//lager objekter
		Brokregner Brok1 = new Brokregner(1, 1);//argumentene peker til this.teller, this.nevner
		Brokregner Brok2 = new Brokregner(1);

		while (true) {
			int tellerKlient;
			int nevnerKlient;

							//teller, nevner
			int operasjon = Integer.parseInt(JOptionPane.showInputDialog("1: +\n2: -\n3: *\n4: /\n"));

			switch (operasjon) {


				case 1:
				tellerKlient = Integer.parseInt(JOptionPane.showInputDialog("teller: "));
				nevnerKlient = Integer.parseInt(JOptionPane.showInputDialog("nevner: "));
				Brok1.addere(tellerKlient, nevnerKlient);
				Brok1.getTeller();
				Brok1.getNevner();
				System.out.println();
				break;

				case 2:
					tellerKlient = Integer.parseInt(JOptionPane.showInputDialog("teller: "));
					nevnerKlient = Integer.parseInt(JOptionPane.showInputDialog("nevner: "));
					Brok1.subtrahere(tellerKlient, nevnerKlient);
					Brok1.getTeller();
					Brok1.getNevner();
					System.out.println();
					break;

				case 3:
					tellerKlient = Integer.parseInt(JOptionPane.showInputDialog("teller: "));
					nevnerKlient = Integer.parseInt(JOptionPane.showInputDialog("nevner: "));
					Brok1.multiplisere(tellerKlient, nevnerKlient);
					Brok1.getTeller();
					Brok1.getNevner();
					System.out.println();
					break;

				case 4:
					tellerKlient = Integer.parseInt(JOptionPane.showInputDialog("teller: "));
					nevnerKlient = Integer.parseInt(JOptionPane.showInputDialog("nevner: "));
					Brok1.dividere(tellerKlient, nevnerKlient);
					Brok1.getTeller();
					Brok1.getNevner();
					System.out.println();
					break;
			}
		}
	}
}