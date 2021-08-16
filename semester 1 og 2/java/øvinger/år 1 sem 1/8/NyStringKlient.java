class NyStringKlient {
	public static void main(String[] args) {

		NyString nyString = new NyString("abc abc abc");

		System.out.println(nyString.Forkorting());
		System.out.println(nyString.Tegnfjerning('a'));
	}
}