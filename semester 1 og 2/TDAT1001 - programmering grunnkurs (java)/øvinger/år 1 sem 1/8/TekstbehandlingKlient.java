class TekstbehandlingKlient {
	public static void main(String[] args) {
	Tekstbehandling nyTekstbehandler = new Tekstbehandling("Testing testing!  123 123! זרו");
		String ord1 = "123";
		String ord2 = "321";

		System.out.println(nyTekstbehandler.getGjennomsnittligAntallOrdPerPeriode());
		System.out.println(nyTekstbehandler.getAntallOrd());
		System.out.println(nyTekstbehandler.getGjennomsnittligOrdlengde());


		System.out.println(nyTekstbehandler.getTekst());
		System.out.println(nyTekstbehandler.getTekstAllCaps());

		System.out.println(nyTekstbehandler.setErstattOrd("123", "321"));

	}
}