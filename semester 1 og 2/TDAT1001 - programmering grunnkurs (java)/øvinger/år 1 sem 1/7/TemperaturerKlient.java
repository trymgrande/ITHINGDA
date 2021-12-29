class TemperaturerKlient {
 	public static void main(String[] args) {
		Temperaturer nyTemperaturer = new Temperaturer();
		//System.out.println(nyTemperaturer.getMiddelTemperaturForHverDagIManed());

		//printer metode a
		double[] resultatA = nyTemperaturer.getMiddelTemperaturForHverDagIManed();
		System.out.println("middeltemperatur for hver dag i mnd");
		for (int i=0; i<resultatA.length; i++) {
			System.out.print("dag: " + i + "\n" + resultatA[i] + "\n");
		}
		System.out.println();


		double[] resultatB = nyTemperaturer.getMiddelTemperaturForHverTimeIMåned();
		System.out.println("middeltemperaturen for hver time i dognet for mnd");
		for (int i=0; i<resultatB.length; i++) {
			System.out.print("kl: " + i + "\n" + resultatB[i] + "\n");
		}
		System.out.println();

		System.out.println("middeltemperaturen for mnd: " + nyTemperaturer.getMiddelTemperaturMnd());
		System.out.println();

		int[] resultatD = nyTemperaturer.antallDognMedTemperaturerIGrupper();
				System.out.println("antall Dogn Med Temperaturer I Grupper");
				for (int i=0; i<resultatB.length; i++) {
					System.out.print("gruppe: " + i + "\n" + resultatD[i] + "\n");
				}
		System.out.println();

	}
}