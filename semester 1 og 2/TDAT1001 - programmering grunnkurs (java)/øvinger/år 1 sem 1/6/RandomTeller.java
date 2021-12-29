class RandomTeller {
	public static void main(String[] args) {
		int tall;
		int i;
		int j;
		int[] antall = new int[10];
		java.util.Random random = new java.util.Random();

		for (i = 1; i < 1000; i++) {
			tall = random.nextInt(10);
			antall[tall] += 1;
		}

		System.out.println("antall looper: " + i);
		System.out.println("antall forekomster pr indeks:");

		for (j = 0; j < 10; j++) {
			System.out.println(j + ": " + antall[j]);
		}
	}
}