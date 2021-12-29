import java.io.*;

public class LempelZiv1 {
    DataInputStream inputStream;
    DataOutputStream outputStream;
    byte[] data;
    int pointer;


    public LempelZiv1(String fileName) throws FileNotFoundException {
        inputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
        outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("C:\\Users\\trymg\\OneDrive - NTNU\\dataingeniør\\sem 3\\algdat\\øving12\\src\\nyfil.txt")));
        while (true) {
            try {
                if (inputStream.available() == 0) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.writeByte(inputStream.readByte());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\trymg\\OneDrive - NTNU\\dataingeniør\\sem 3\\algdat\\øving12\\src\\opg12.txt");
        System.out.println(file.exists());
        System.out.println();
        LempelZiv1 compression1 = new LempelZiv1("C:\\Users\\trymg\\OneDrive - NTNU\\dataingeniør\\sem 3\\algdat\\øving12\\src\\opg12.txt");
    }
}
