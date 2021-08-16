import javax.swing.*;

public class StudentKlient {
    public static void main(String[] args) {
        //oppretter et studentobjekt
        Student enStudent = new Student("Trym", "30041999");
        //registrerer nye karakterer
        String karakterLest = "";
        while (!(karakterLest.equals(null))) { //while ikke null
            karakterLest = JOptionPane.showInputDialog("Oppgi en karakter (bokstav A-F), avslutt med Esc:");
            try {
                char karakter = karakterLest.charAt(0);
                if (enStudent.sjekkeOmKarakterErGyldig(karakter) == 0) {
                    System.out.println(enStudent.registrerNyKarakter(karakter));
                }
                else {
                    JOptionPane.showMessageDialog(null, "Ugyldig karakter. Du skrev " + karakter);
                }
            } catch (NullPointerException e) {
                break;
            }
        }
        JOptionPane.showMessageDialog(null, enStudent.toString() +
                "\nGjennomsnittkarakter: " + enStudent.finnGjennomsnittskarakteren());
    }
}