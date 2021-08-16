/*
Temperaturer
------------------------
-int[30][24] temperaturer {readonly}
-----------------------
Temperaturer()
+int[] getMiddelTemperaturForHverDagIMåned()
	[dag][0], [dag][2], [dag][3], [dag][4],
	finner middelverdi dag1:
	sum =
	[dag2][0], [dag2][2], [dag2][3], [dag2][4], finner middelverdi dag2
	repeter 30x
	returnerer tabell[30] med dag/middelverdi

+double[] getMiddelTemperaturForHverTimeIMåned()
	[dag1][0], [dag2][0], [dag3][0], ... finner middelverdi
	repeter 30x
	[dag1][1], [dag2][1], [dag3][1], ... finner middelverdi
	repeter 30x
	returnerer tabell[24] med klokkeslett/middelverdi

+double getMiddelTemperatur


*/
class Temperaturer {
	private int[][] maned = {{0, 2, 3, 2}, {0, 3, 5, 3}, {4, 5, 5, 4}, {-3, -20, 20, 10}}; //to dager, 4 timer

	public double[] getMiddelTemperaturForHverDagIManed() {
		double sum;
		double[] middelverdier = new double[maned.length];
		//i = dag
		//j = time hver dag

		//looper hver dag
		for (int i = 0; i < maned.length; i++) {
			sum = 0;
			//finner sum dag 1

			//looper hver time
			for (int j = 0; j < maned[i].length; j++) {
				sum += maned[i][j];
				//System.out.println(sum);
			}
			//finner middelverdi dag (sum dag / lengde dag)
			double middelverdiDag = (double) sum / (double) maned[0].length;
			//setter middelverdien i liste
			middelverdier[i] = middelverdiDag;

		}
		return middelverdier;
	}

	public double[] getMiddelTemperaturForHverTimeIMåned() {
		double sum;
		double[] middelverdier = new double[maned[0].length];
		//i = time
		//j = dag


		//looper gjennom hver time
		for (int i = 0; i < maned[0].length; i++) {
			sum = 0;
			//looper gjennom hver dag
			for (int j = 0; j < maned.length; j++) {
				sum += maned[j][i];
			}
			//finner middelverdi for klokkeslett (sum / antall klokkeslett
			double middelverdiKl = (double) sum / (double) maned.length;
			middelverdier[i] = middelverdiKl;
		}
	return middelverdier;
	}

	public double getMiddelTemperaturMnd() {
		double[] middelTemperaturForHverDagIManed = getMiddelTemperaturForHverDagIManed();
		double sum = 0;
		for (int i = 0; i < middelTemperaturForHverDagIManed.length; i++) {
			sum += middelTemperaturForHverDagIManed[i];
			System.out.println(middelTemperaturForHverDagIManed[i]);
			System.out.println("sum: " + sum);
		}
		//middelverdi (sum / elementer)
		double middelverdi = (double) sum / (double) middelTemperaturForHverDagIManed.length;
		System.out.println(sum);
		return middelverdi;
	}

	public int[] antallDognMedTemperaturerIGrupper() {
		int[] antallDognMedTemperaturerIGrupper = new int[5];


		//looper gjennom dager
		for (int i = 0; i < getMiddelTemperaturForHverDagIManed().length; i++) {
				if (i < -5) {
					antallDognMedTemperaturerIGrupper[0]++;
				}
				else if (i >= -5 && i < 5) {
					antallDognMedTemperaturerIGrupper[1]++;
				}
				else if (i >= -5 && i < 5) {
					antallDognMedTemperaturerIGrupper[2]++;
				}
				else if (i >= -5 && i < 5) {
					antallDognMedTemperaturerIGrupper[3]++;
				}
				else if (i >= -5 && i < 5) {
					antallDognMedTemperaturerIGrupper[4]++;
				}
		}
	return antallDognMedTemperaturerIGrupper;

	}
}

/*
	public double testfunksjon() {
	for (int i = 0; i < temperaturer[0].length; i++) {
		System.out.println(temperaturer[0][i]);
	}
	for (int i = 0; i < temperaturer[1].length; i++) {
			System.out.println(temperaturer[1][i]);
	}
	return 0;
	}
}
*/