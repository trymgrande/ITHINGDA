class SkoleklasseKlient {
	public static void main(String[] args) {
		Skoleklasse enSkoleklasse = new Skoleklasse(5);

		System.out.println("testdatasett 1:");
		System.out.println(enSkoleklasse.getAntallElever());
		System.out.println(enSkoleklasse.getFravær(0));
		System.out.println(enSkoleklasse.getGjennomsnittligFravær());
		System.out.println(enSkoleklasse.getAntallEleverUtenFravær());
		System.out.println(enSkoleklasse.setFravær(0, 0));

		System.out.println("\ntestdatasett 2:");
		System.out.println(enSkoleklasse.setFravær(1, 5));
		System.out.println(enSkoleklasse.getAntallElever());
		System.out.println(enSkoleklasse.getFravær(1));
		System.out.println(enSkoleklasse.getGjennomsnittligFravær());
		System.out.println(enSkoleklasse.getAntallEleverUtenFravær());

		System.out.println("\ntestdatasett 3:");
		System.out.println(enSkoleklasse.setFravær(1, 0));
		System.out.println(enSkoleklasse.getAntallElever());
		System.out.println(enSkoleklasse.getFravær(1));
		System.out.println(enSkoleklasse.getGjennomsnittligFravær());
		System.out.println(enSkoleklasse.getAntallEleverUtenFravær());

		System.out.println("\ntestdatasett 4:");
		System.out.println(enSkoleklasse.setFravær(1, 5));
		System.out.println(enSkoleklasse.setFravær(3, 2));
		System.out.println(enSkoleklasse.setFravær(5, 1));
		System.out.println(enSkoleklasse.getAntallElever());
		System.out.println(enSkoleklasse.getFravær(1));
		System.out.println(enSkoleklasse.getFravær(3));
		System.out.println(enSkoleklasse.getFravær(5));
		System.out.println(enSkoleklasse.getGjennomsnittligFravær());
		System.out.println(enSkoleklasse.getAntallEleverUtenFravær());

		System.out.println("\ntestdatasett 5:");
		System.out.println(enSkoleklasse.setFravær(1, 2));
		System.out.println(enSkoleklasse.setFravær(2, 1));
		System.out.println(enSkoleklasse.setFravær(3, 4));
		System.out.println(enSkoleklasse.setFravær(4, 2));
		System.out.println(enSkoleklasse.setFravær(5, 1));
		System.out.println(enSkoleklasse.getAntallElever());
		System.out.println(enSkoleklasse.getFravær(1));
		System.out.println(enSkoleklasse.getFravær(2));
		System.out.println(enSkoleklasse.getFravær(3));
		System.out.println(enSkoleklasse.getFravær(4));
		System.out.println(enSkoleklasse.getFravær(5));
		System.out.println(enSkoleklasse.getGjennomsnittligFravær());
		System.out.println(enSkoleklasse.getAntallEleverUtenFravær());
	}
}