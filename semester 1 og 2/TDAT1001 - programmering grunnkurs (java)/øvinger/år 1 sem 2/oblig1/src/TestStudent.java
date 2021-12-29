class TestStudent {
  public static void main(String[] args) {
    Student studenten = new Student("Ole Andreas Thomassen", "300499");
    System.out.println("Studenten heter " + studenten.getNavn() + " og er "
      + studenten.getAlder() + " ar gammel.");
  }
}