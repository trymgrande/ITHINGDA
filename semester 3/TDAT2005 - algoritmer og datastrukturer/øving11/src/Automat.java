// oppgave 3

public class Automat {
    char[] inputalfabet;
    int[][] nesteTilstand;
    int[] aksepttilstander;

    public Automat(char[] inputalfabet, int[][] nesteTilstand, int[] aksepttilstander) {
        this.inputalfabet = inputalfabet;
        this.nesteTilstand = nesteTilstand;
        this.aksepttilstander = aksepttilstander;
    }

    public boolean sjekkInput(char[] input) {
        int tilstand = 0;
        for (int i = 0; i < input.length; i++) {
            tilstand = nesteTilstand[tilstand][inputIndex(input[i])];
        }
        for (int i = 0; i < aksepttilstander.length; i++) {
            if (tilstand == aksepttilstander[i]) {
                return true;
            }
        }
        return false;
    }

    public int inputIndex(char input) {
        for (int i = 0; i < inputalfabet.length; i++) {
            if (inputalfabet[i] == input) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        char[] inputAlfabet = new char[] {'1', '0'};
        int[][] nesteTilstand = new int[][] { {'0', '1'}, {'1', '2'}, {'2', '3'}, {'3', '0'} };
        int[] aksepttilstander = new int[] {1};
        Automat automat1 = new Automat(inputAlfabet, nesteTilstand, aksepttilstander);
//        char[] input1 = {''};
        char[] input2 = {'0', '1', '0'};
        char[] input3 = {'1', '1', '1'};
        char[] input4 = {'0', '1', '0', '1', '1', '0'};
        char[] input5 = {'0', '0', '1', '0', '0', '0'};
        System.out.println(automat1.sjekkInput(input3));
    }
}