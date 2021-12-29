class Brøk{
	public double teller;
	public double nevner;
	public final double UNNTAK = 0.0;

	public Brøk(double teller, double nevner){
		if(nevner == UNNTAK){
			throw new IllegalArgumentException("Nevneren kan ikke være null.");
		}
		this.teller = teller;
		this.nevner = nevner;
	}

	public Brøk(double teller){
		this.teller = teller;
		this.nevner = 1.0;
	}

	public double getTeller(){
		return teller;
	}

    public double getNevner(){
		return nevner;
	}

	public void beregnSum(Brøk brøk2){
         if(this.nevner == brøk2.nevner){
			 this.teller += brøk2.teller;
		 }
		 else if(this.nevner != brøk2.nevner){
			 this.teller = this.teller * brøk2.nevner + this.nevner * brøk2.teller;
			 this.nevner = nevner * brøk2.nevner;
	     }
	}

	public void beregnSubtraksjon(Brøk brøk2){
           if(this.nevner == brøk2.nevner){
		   	   this.teller -= brøk2.teller;

		   }
		   else if(this.nevner != brøk2.nevner){
		   			this.teller = this.teller * brøk2.nevner - this.nevner * brøk2.teller;
		   			this.nevner = nevner * brøk2.nevner;
	       }
	}

	public void beregnMultiplikasjon(Brøk brøk2){
         this.teller *= brøk2.teller;
         this.nevner *= brøk2.nevner;
	}

	public void beregnDivisjon(Brøk brøk2){
         this.teller *= brøk2.nevner;
         this.nevner *= brøk2.teller;
	}
}
class EnBrøk{
	public static void main(String[] args){
		Brøk a = new Brøk(2, 5);
		Brøk b = new Brøk(3, 4);
        a.beregnSum(b);//Test-1: summerer to brøker med ulike nevnere.
        if(a.getTeller() == 23 && a.getNevner() == 20){
			System.out.println("EnBrøk: Test 1 vellykket.");
		}

		Brøk c = new Brøk(2, 7);
		Brøk d = new Brøk(6, 7);
		c.beregnSubtraksjon(d);//Test-2: Subtrahere to brøker med like nevnere.
		if(c.getTeller() == -4 && c.getNevner() == 7){
					System.out.println("EnBrøk: Test 2 vellykket.");
		}

        Brøk e = new Brøk(4, 5);
        Brøk f = new Brøk(7, 9);
        e.beregnMultiplikasjon(f);//Test-3: Multipliserer to brøker.
        if(e.getTeller() == 28 && e.getNevner() == 45){
					System.out.println("EnBrøk: Test 3 vellykket.");
		}

	    Brøk g = new Brøk(11, 9);
	    Brøk h = new Brøk(7, 4);
	    g.beregnDivisjon(h);//Tes-4: Dividerer to brøker.
	    if(g.getTeller() == 44 && g.getNevner() == 63){
					System.out.println("EnBrøk: Test 4 vellykket.");
		}

		Brøk i = new Brøk(2, 3);
		Brøk j = new Brøk(3,0);
		i.beregnSum(j);//Test-5: Sjekker om nevneren er lik 0.

	}
}