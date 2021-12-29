import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

class Huffman{
    static void komprimer(String source, String output) throws Exception{

        //Lese data fra fil inn i byte-array:
        //byte []data  : arrayet vi leser inn i
        //int posisjon : index i byte-array for det vi leser inn
        //int mengde   : antall byte vi vil lese inninnfil.readFully(data, posisjon, mengde);

        DataInputStream innfil = new DataInputStream(new BufferedInputStream(new FileInputStream(source)));
        DataOutputStream utfil = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(output)));

        //lager en array for alle bytes som leses inn fra filen

        byte[] data = new byte[innfil.available()]; 

        //leser inn alle bytes i fra filen og legger i arrayen data 

        innfil.readFully(data);

        //vil nå finne antall forekomster av hver byte, lager da en int-array
        //hvor index svarer til byte-verdien 

        int[] forekomster = new int[256];

        //finner forekomsten av bytene

        for(int i = 0; i < data.length; i++){
            //System.out.println(data[i]);
            forekomster[data[i]+128]++;
        }

        int unike = 0;

        //finner hvor mange unike bytes det er i teksten

        for(int i = 0; i < forekomster.length; i++){
            if(forekomster[i] != 0) unike++;
        }

        /*

        //benytter byteklassen som holder informasjon om byte-verdien og hvor mange ganger
        //byten kommer frem i teksten

        Byteklasse[] rawData = new Byteklasse[unike];

        int hjelp = 0;

        //har nå en array som inneholder informasjon om alle bytes i teksten og hvor mange ganger de 
        //inntreffer 

        for(int i = 0; i < forekomster.length; i++){
            if(forekomster[i] == 0) continue;
            rawData[hjelp] = new Byteklasse((byte) i, forekomster[i]);
        }

        //benytter boblesortering for å ha bytene i stigende rekkefølge i forhold til hvor mange ganger de inntreffer

        Byteklasse temp;
		for (int i = 0; i < rawData.length - 1; i++) {
			for (int j = 1; j < rawData.length - i; j++) {
				if (rawData[j-1].ant > rawData[j].ant) {
					temp = rawData[j - 1];
					rawData[j - 1] = rawData[j];
					rawData[j] = temp;
				}
			}
        }*/
        
        //nå skal vi lage en ny tabell som sorterer tegn etter hvor ofte de forekommer
        //char-index vil da svare til bit-verdi, mens tabellverdien er hvilket tegn det er 

        char[] nyBit = new char[256];

        for(int i = 0; i < forekomster.length; i++){
            int strst = 0;
            for(int j = i; j < forekomster.length; j++){
                if(forekomster[j] > strst) strst = j;
            }
            nyBit[i] = (char) strst;
        }

        byte[] oversatt = new byte[data.length];

        for(int i = 0; i < oversatt.length; i++){
            byte placeholder = data[i];

            for(int j = 0; j < nyBit.length; j++){
                if(nyBit[j] == (char) placeholder){
                    oversatt[i] = (byte) j;
                    break;
                }
            }
        }

        utfil.write(oversatt);
    }

    public static void main(String[] args) throws Exception{
        komprimer("../test.txt", "komprimert.txt");
    }
}

class Byteklasse{
    byte value;
    int ant;

    public Byteklasse(byte value, int ant){
        this.value = value;
        this.ant = ant;
    }
}