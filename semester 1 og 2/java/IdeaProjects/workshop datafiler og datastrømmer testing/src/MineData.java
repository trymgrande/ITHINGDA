//Programmet skal telle antall linjer lest og skrive dette antallet ut på skjermen.
import java.io.*;
/*
lage filereader
call readline til den returnerer null
tell antall linjer
print antall linjer
 */

public class MineData {
    public static void main(String[] args) {
        try {

            String minedataFilnavn = "C:\\Users\\trymg\\OneDrive - NTNU\\dataingeniør\\java\\IdeaProjects\\workshop datafiler og datastrømmer testing\\src\\minedata.txt";

            FileReader FRminedataFilnavn = new FileReader(minedataFilnavn);
            BufferedReader BRminedataFilnavn = new BufferedReader(FRminedataFilnavn);

            String linjeLest = BRminedataFilnavn.readLine();
            int i = 0;
            while (linjeLest != null) {
                i++;
                //System.out.println(linjeLest);
                linjeLest = BRminedataFilnavn.readLine();
            }
            System.out.println(i);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}