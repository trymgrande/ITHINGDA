class SkoleklasseKlient {
	public static void main(String[] args) {
		Skoleklasse enSkoleklasse = new Skoleklasse(5);

		System.out.println("testdatasett 1:");
		System.out.println(enSkoleklasse.getAntallElever());
		System.out.println(enSkoleklasse.getFrav�r(0));
		System.out.println(enSkoleklasse.getGjennomsnittligFrav�r());
		System.out.println(enSkoleklasse.getAntallEleverUtenFrav�r());
		System.out.println(enSkoleklasse.setFrav�r(0, 0));

		System.out.println("\ntestdatasett 2:");
		System.out.println(enSkoleklasse.setFrav�r(1, 5));
		System.out.println(enSkoleklasse.getAntallElever());
		System.out.println(enSkoleklasse.getFrav�r(1));
		System.out.println(enSkoleklasse.getGjennomsnittligFrav�r());
		System.out.println(enSkoleklasse.getAntallEleverUtenFrav�r());

		System.out.println("\ntestdatasett 3:");
		System.out.println(enSkoleklasse.setFrav�r(1, 0));
		System.out.println(enSkoleklasse.getAntallElever());
		System.out.println(enSkoleklasse.getFrav�r(1));
		System.out.println(enSkoleklasse.getGjennomsnittligFrav�r());
		System.out.println(enSkoleklasse.getAntallEleverUtenFrav�r());

		System.out.println("\ntestdatasett 4:");
		System.out.println(enSkoleklasse.setFrav�r(1, 5));
		System.out.println(enSkoleklasse.setFrav�r(3, 2));
		System.out.println(enSkoleklasse.setFrav�r(5, 1));
		System.out.println(enSkoleklasse.getAntallElever());
		System.out.println(enSkoleklasse.getFrav�r(1));
		System.out.println(enSkoleklasse.getFrav�r(3));
		System.out.println(enSkoleklasse.getFrav�r(5));
		System.out.println(enSkoleklasse.getGjennomsnittligFrav�r());
		System.out.println(enSkoleklasse.getAntallEleverUtenFrav�r());

		System.out.println("\ntestdatasett 5:");
		System.out.println(enSkoleklasse.setFrav�r(1, 2));
		System.out.println(enSkoleklasse.setFrav�r(2, 1));
		System.out.println(enSkoleklasse.setFrav�r(3, 4));
		System.out.println(enSkoleklasse.setFrav�r(4, 2));
		System.out.println(enSkoleklasse.setFrav�r(5, 1));
		System.out.println(enSkoleklasse.getAntallElever());
		System.out.println(enSkoleklasse.getFrav�r(1));
		System.out.println(enSkoleklasse.getFrav�r(2));
		System.out.println(enSkoleklasse.getFrav�r(3));
		System.out.println(enSkoleklasse.getFrav�r(4));
		System.out.println(enSkoleklasse.getFrav�r(5));
		System.out.println(enSkoleklasse.getGjennomsnittligFrav�r());
		System.out.println(enSkoleklasse.getAntallEleverUtenFrav�r());
	}
}