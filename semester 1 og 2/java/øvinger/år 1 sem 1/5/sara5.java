class Br�k{
	public double teller;
	public double nevner;
	public final double UNNTAK = 0.0;

	public Br�k(double teller, double nevner){
		if(nevner == UNNTAK){
			throw new IllegalArgumentException("Nevneren kan ikke v�re null.");
		}
		this.teller = teller;
		this.nevner = nevner;
	}

	public Br�k(double teller){
		this.teller = teller;
		this.nevner = 1.0;
	}

	public double getTeller(){
		return teller;
	}

    public double getNevner(){
		return nevner;
	}

	public void beregnSum(Br�k br�k2){
         if(this.nevner == br�k2.nevner){
			 this.teller += br�k2.teller;
		 }
		 else if(this.nevner != br�k2.nevner){
			 this.teller = this.teller * br�k2.nevner + this.nevner * br�k2.teller;
			 this.nevner = nevner * br�k2.nevner;
	     }
	}

	public void beregnSubtraksjon(Br�k br�k2){
           if(this.nevner == br�k2.nevner){
		   	   this.teller -= br�k2.teller;

		   }
		   else if(this.nevner != br�k2.nevner){
		   			this.teller = this.teller * br�k2.nevner - this.nevner * br�k2.teller;
		   			this.nevner = nevner * br�k2.nevner;
	       }
	}

	public void beregnMultiplikasjon(Br�k br�k2){
         this.teller *= br�k2.teller;
         this.nevner *= br�k2.nevner;
	}

	public void beregnDivisjon(Br�k br�k2){
         this.teller *= br�k2.nevner;
         this.nevner *= br�k2.teller;
	}
}
class EnBr�k{
	public static void main(String[] args){
		Br�k a = new Br�k(2, 5);
		Br�k b = new Br�k(3, 4);
        a.beregnSum(b);//Test-1: summerer to br�ker med ulike nevnere.
        if(a.getTeller() == 23 && a.getNevner() == 20){
			System.out.println("EnBr�k: Test 1 vellykket.");
		}

		Br�k c = new Br�k(2, 7);
		Br�k d = new Br�k(6, 7);
		c.beregnSubtraksjon(d);//Test-2: Subtrahere to br�ker med like nevnere.
		if(c.getTeller() == -4 && c.getNevner() == 7){
					System.out.println("EnBr�k: Test 2 vellykket.");
		}

        Br�k e = new Br�k(4, 5);
        Br�k f = new Br�k(7, 9);
        e.beregnMultiplikasjon(f);//Test-3: Multipliserer to br�ker.
        if(e.getTeller() == 28 && e.getNevner() == 45){
					System.out.println("EnBr�k: Test 3 vellykket.");
		}

	    Br�k g = new Br�k(11, 9);
	    Br�k h = new Br�k(7, 4);
	    g.beregnDivisjon(h);//Tes-4: Dividerer to br�ker.
	    if(g.getTeller() == 44 && g.getNevner() == 63){
					System.out.println("EnBr�k: Test 4 vellykket.");
		}

		Br�k i = new Br�k(2, 3);
		Br�k j = new Br�k(3,0);
		i.beregnSum(j);//Test-5: Sjekker om nevneren er lik 0.

	}
}