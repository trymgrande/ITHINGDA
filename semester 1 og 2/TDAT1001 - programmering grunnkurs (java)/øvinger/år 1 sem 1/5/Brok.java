import javax.swing.JOptionPane;

class Brok {
	//this-brøk
	private int teller;
	private int nevner;

	//konstrukrør 1
	public Brok(int teller, int nevner) {
		this.teller = teller;
		if (nevner == 0) {
			throw new IllegalArgumentException("nevner kan ikke være = 0");
		}
		else {
			this.nevner = nevner;
		}
	}
	//konstruktør 2
	public Brok(int teller) {
		this.teller = teller;
		this.nevner = 1;
	}

	//tilgangsmetoder
	public int getTeller() {
		System.out.println(this.teller);//test
		return teller;
	}

	public int getNevner() {
		System.out.println(this.nevner);//test
		return nevner;
	}

	//mutasjonsmetoder
		//addere
	public void addere(Brok brok1) {
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

		//subtrahere
	public void subtrahere(Brok brok1) {
		if (nevner == this.nevner) {
			this.teller -= teller;
		}
		else {//finner felles nevner
			this.teller = (this.teller * nevner) + (teller * this.nevner);
			this.nevner = (this.nevner * nevner) + (nevner * this.nevner);
		}
	}

		//multiplisere
	public void multiplisere(Brok brok1) {
		this.teller *= brok1.getTeller();
		this.nevner *= brok1.getNevner();
	}

		//dividere
	public void dividere(Brok brok1) {
		int nyNevner = teller;
		int nyTeller = nevner;
		this.teller *= nyTeller;
		this.nevner *= nyNevner;
	}


	//klient
	public static void main(String[] args) {
		boolean interrupt = false;

		//lager objekter
		Brok brok1 = new Brok(1, 4);//argumentene peker til this.teller, this.nevner
		//Brok Brok2 = new Brok(1);

		while (true) {
			int tellerKlient;
			int nevnerKlient;

							//teller, nevner
			int operasjon = Integer.parseInt(JOptionPane.showInputDialog("1: +\n2: -\n3: *\n4: /\n"));

			//Brok brok1;

			switch (operasjon) {


				case 1:
				tellerKlient = Integer.parseInt(JOptionPane.showInputDialog("teller: "));
				nevnerKlient = Integer.parseInt(JOptionPane.showInputDialog("nevner: "));
				brok1 = new Brok(brok1);
				brok1.addere(brok1);
				brok1.getTeller();
				brok1.getNevner();
				System.out.println();
				break;

				case 2:
					tellerKlient = Integer.parseInt(JOptionPane.showInputDialog("teller: "));
					nevnerKlient = Integer.parseInt(JOptionPane.showInputDialog("nevner: "));
					brok1.subtrahere(brok1);
					brok1.getTeller();
					brok1.getNevner();
					System.out.println();
					break;

				case 3:
					tellerKlient = Integer.parseInt(JOptionPane.showInputDialog("teller: "));
					nevnerKlient = Integer.parseInt(JOptionPane.showInputDialog("nevner: "));
					brok1 = new Brok(tellerKlient, nevnerKlient);
					brok1.multiplisere(brok1);
					brok1.getTeller();
					brok1.getNevner();
					System.out.println();
					break;

				case 4:
					tellerKlient = Integer.parseInt(JOptionPane.showInputDialog("teller: "));
					nevnerKlient = Integer.parseInt(JOptionPane.showInputDialog("nevner: "));
					brok1.dividere(brok1);
					brok1.getTeller();
					brok1.getNevner();
					System.out.println();
					break;
			}
		}
	}
}