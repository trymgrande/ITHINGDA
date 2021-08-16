public class Main {
	public static void main(String[] args) {
		String input = "grasswas added by Markentoth and appears on 65 lists\n" +
				"greasewas added by Markentoth and appears on 30 lists\n" +
				"racewas added by Markentoth and appears on 44 lists\n" +
				"Swedeswas added by Markentoth and appears on just this list\n" +
				"Qatarwas added by Markentoth and appears on 11 lists\n" +
				"Greecewas added by Markentoth and appears on 11 lists\n" +
				"verdewas added by Markentoth and appears on 3 lists\n" +
				"tersewas added by Markentoth and appears on 174 lists\n" +
				"tweedwas added by Markentoth and appears on 39 lists\n" +
				"westwas added by Markentoth and appears on 29 lists\n" +
				"eastwas added by Markentoth and appears on 23 lists\n" +
				"gaswas added by Markentoth and appears on 46 lists\n" +
				"farwas added by Markentoth and appears on 41 lists\n" +
				"aswas added by Markentoth and appears on 44 lists\n" +
				"geezwas added by Markentoth and appears on 13 lists\n" +
				"wewas added by Markentoth and appears on 44 lists\n" +
				"satwas added by Markentoth and appears on 15 lists\n" +
				"cartwas added by Markentoth and appears on 29 lists\n" +
				"treewas added by Markentoth and appears on 79 lists\n" +
				"fasterwas added by Markentoth and appears on 13 lists\n" +
				"fastwas added by Markentoth and appears on 63 lists\n" +
				"reverberatewas added by unjessing and appears on 46 lists\n" +
				"cascadewas added by 504304481 and appears on 99 lists\n" +
				"greatestwas added by Silenturion and appears on 13 lists\n" +
				"accretewas added by Silenturion and appears on 40 lists\n" +
				"abracadabrawas added by Silenturion and appears on 46 lists\n" +
				"defacedwas added by Silenturion and appears on 9 lists\n" +
				"weedeaterwas added by Silenturion and appears on 3 lists\n" +
				"redfacedwas added by Silenturion and appears on 2 lists\n" +
				"bargewas added by Silenturion and appears on 42 lists\n" +
				"tweetwas added by Silenturion and appears on 48 lists\n" +
				"retardwas added by rbt and appears on 47 lists\n" +
				"retractwas added by rbt and appears on 45 lists\n" +
				"dragwas added by ontrag and appears on 48 lists\n" +
				"gaffwas added by ontrag and appears on 30 lists\n" +
				"vestwas added by ontrag and appears on 28 lists\n" +
				"vastwas added by ontrag and appears on 48 lists\n" +
				"wartwas added by ontrag and appears on 21 lists\n" +
				"starvewas added by ontrag and appears on 11 lists\n" +
				"dreadwas added by ontrag and appears on 62 lists\n" +
				"wagewas added by ontrag and appears on 30 lists\n" +
				"werewas added by ontrag and appears on 22 lists\n" +
				"waterwas added by ontrag and appears on 144 lists\n" +
				"wastewas added by ontrag and appears on 35 lists\n" +
				"affectwas added by ontrag and appears on 62 lists\n" +
				"effectwas added by ontrag and appears on 54 lists\n" +
				"strafewas added by Silenturion and appears on 34 lists\n" +
				"gravewas added by Silenturion and appears on 84 lists\n" +
				"cravewas added by Silenturion and appears on 39 lists\n" +
				"farcewas added by Silenturion and appears on 63 lists\n" +
				"Stewartwas added by Silenturion and appears on just this list\n" +
				"avewas added by Silenturion and appears on 18 lists\n" +
				"caveswas added by Silenturion and appears on 9 lists\n" +
				"veerwas added by Silenturion and appears on 53 lists\n" +
				"Avestawas added by Silenturion and appears on 3 lists\n" +
				"staveswas added by Silenturion and appears on 7 lists\n" +
				"sexwas added by Silenturion and appears on 84 lists\n" +
				"starterwas added by Silenturion and appears on 11 lists\n" +
				"execwas added by Silenturion and appears on 11 lists\n" +
				"wastrelwas added by Silenturion and appears on 119 lists\n" +
				"crestedwas added by Silenturion and appears on 11 lists\n" +
				"greedwas added by Silenturion and appears on 40 lists\n" +
				"greatwas added by Silenturion and appears on 59 lists\n" +
				"badgewas added by Silenturion and appears on 23 lists\n" +
				"bastardwas added by Silenturion and appears on 61 lists\n" +
				"gatedwas added by Silenturion and appears on 4 lists\n" +
				"agewas added by Silenturion and appears on 39 lists\n" +
				"drearwas added by Silenturion and appears on 13 lists\n" +
				"treatwas added by Silenturion and appears on 43 lists\n" +
				"eaveswas added by Silenturion and appears on 20 lists\n" +
				"zedwas added by Silenturion and appears on 25 lists\n" +
				"vexeswas added by Silenturion and appears on 2 lists\n" +
				"craterwas added by Silenturion and appears on 25 lists\n" +
				"stresswas added by Silenturion and appears on 32 lists\n" +
				"dressedwas added by Silenturion and appears on 12 lists\n" +
				"createdwas added by Silenturion and appears on 12 lists\n" +
				"waverwas added by Silenturion and appears on 37 lists\n" +
				"qatwas added by Silenturion and appears on 45 lists\n" +
				"wrestwas added by Silenturion and appears on 65 lists\n" +
				"axeswas added by Silenturion and appears on 5 lists\n" +
				"waveswas added by Silenturion and appears on 32 lists\n" +
				"red teawas added by Silenturion and appears on 3 lists\n" +
				"stewardesseswas added by Silenturion and appears on 5 lists";

		String[] splitList = input.split("was added by |\n");
		String[] outputList = new String[splitList.length];
		for (int i = 0; i < splitList.length; i++) {
//			System.out.println(splitList[i]);
			if (i % 2 == 0) {
				outputList[i] = splitList[i];
//				System.out.println(outputList[i]);
			}
		}
		for (int i = 0; i < outputList.length; i++) {
			if (i % 2 == 0) {
				System.out.print(outputList[i] + " ");
			}
		}
	}
}
