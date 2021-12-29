//import java.io.*;
//import java.nio.charset.Charset;
//
//public class LZ77 {
//
//    private final int DEFAULT_BUFFER = 1024;
//    private int bufferSize;
//    private Reader inputFile;
//    private PrintWriter outputFile;
//    private StringBuffer searchBuffer;
//
//    public LZ77(int bufferSize){
//        this.bufferSize = bufferSize;
//        searchBuffer = new StringBuffer(this.bufferSize);
//
//    }
//
//    public LZ77(){
//        this.bufferSize = DEFAULT_BUFFER;
//        searchBuffer = new StringBuffer(this.bufferSize);
//
//    }
//
//    private void trimBuffer(){
//        if(searchBuffer.length() > bufferSize) {
//            searchBuffer = searchBuffer.delete(0, searchBuffer.length() - bufferSize);
//        }
//    }
//
//    public void compress(String filename) throws IOException {
//        inputFile = new BufferedReader(new FileReader("src/" + filename));
//        outputFile = new PrintWriter(new BufferedWriter(new FileWriter("src/compressed" + filename)));
//
//        StringBuilder match = new StringBuilder();
//        int intCurrentChar;
//        int searchResult, matchIndex = 0;
//
//
//        // Reads one character each loop
//        while((intCurrentChar = inputFile.read()) != -1){
//            char currentChar = (char) intCurrentChar;
//            searchResult = searchBuffer.indexOf(match.toString() + currentChar);
//            // Checks if match is found, else, encode.
//            if(searchResult != -1){
//                match.append(currentChar);
//                matchIndex = searchResult - match.length();
//            } else {
//                String encoded = "~" + matchIndex + "~" + match.length() + "~" + currentChar;
//                StringBuilder originalText = match.append(currentChar);
//
//                // If encoded is shorter than original, print encoded.
//                if(encoded.length() < originalText.length()){
//                    outputFile.print(encoded);
//                    searchBuffer.append(originalText);
//                    match = new StringBuilder();
//                    matchIndex = 0;
//                } else {
//                    // print original until empty or new match
//                    match = originalText;
//                    matchIndex = -1;
//                    while(match.length() > 1 && matchIndex == -1){
//                        outputFile.print(match.charAt(0));
//                        searchBuffer.append(match.charAt(0));
//                        match.deleteCharAt(0);
//                        matchIndex = searchBuffer.indexOf(match.toString());
//                    }
//                }
//                trimBuffer();
//            }
//        }
//
//        if(matchIndex != -1){
//            String encoded = "~" + matchIndex + "~" + match.length();
//            if(encoded.length() <= match.length()){
//                outputFile.print("~" + matchIndex + "~" + match.length());
//            } else {
//                outputFile.print(match);
//            }
//        }
//        inputFile.close();
//        outputFile.flush();
//        outputFile.close();
//    }
//
//    public void decompress(File file) throws IOException{
//
//        inputFile = new BufferedReader(new FileReader("src/" + filename));
//        outputFile = new PrintWriter(new BufferedWriter(new FileWriter("src/" + filename + ".decompressed")));
//        InputStream inputStream = new FileInputStream(file);
//        Reader reader = new InputStreamReader(inputStream, Charset.defaultCharset());
//
//        inputFile.close();
//        outputFile.flush();
//        outputFile.close();
//    }
//
//    public static String pakkUt(String path) {
//        return pakkUt(new File(path));
//    }
//
//    public static String pakkUt(File fil) {
//        DataInputStream inn = null;
//        DataOutputStream ut = null;
//        String utNavn = "C:\\Users\\torje\\IdeaProjects\\ØvingerAlgoritmer\\src\\Øving12\\Utpakket\\utpakket" + fil.getName();
//        try {
//            inn = new DataInputStream(new BufferedInputStream(new FileInputStream(fil)));
////            ut = new FileWriter(utNavn);
//            ut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(utNavn)));
//
//            byte[] bytes = inn.readAllBytes();
//            System.out.println("PAKKER UT " + bytes.length + " BYTES");
//            byte[] skrevetBytes = new byte[bytes.length * 5];
//            int antSkrevetBytes = 0;
//
////            while (inn.available() > 0) {
//            for (int i = 0; i < bytes.length; i++) {
////                char tegn = (char) inn.readByte();
////                byte byte1 = inn.readByte();
//                byte byte1 = bytes[i];
////                if ((byte1 & 0x000000FF) >>> 4 == 14) {//Tegnet har 3 bytes i lengde
////                    byte byte2 = inn.readByte();
////                    byte byte3 = inn.readByte();
////                    tegn = bytesTilTegn(byte1, byte2, byte3);
////                } else if ((byte1 & 0x000000FF) >>> 5 == 6) {//Tegnet har 2 bytes i lengde
////                    byte byte2 = inn.readByte();
////                    tegn = bytesTilTegn(byte1, byte2);
////                } else {
////                    tegn = (char) byte1;
////                }
//                if (byte1 == REFERANSE_BYTE_1 && bytes[i + 1] == REFERANSE_BYTE_2) {
////                    int bakover = (char) inn.readByte();
////                    int bakover = bytesTilUnsignedInt(new byte[]{inn.readByte(), inn.readByte()});
//                    int bakover = bytesTilUnsignedInt(new byte[]{bytes[i + 2], bytes[i + 3]});
////                    int antallTegn = bytesTilUnsignedInt(new byte[]{inn.readByte()});
//                    int antallBytes = bytesTilUnsignedInt(new byte[]{bytes[i + 4]});
////                    int antallTegn = bytesTilTegn(inn.readByte(), inn.readByte());
//                    int start = antSkrevetBytes - bakover;
////                    System.out.println(start + " " + antSkrevetBytes + " " + bakover + " " + antallBytes);
//                    for (int j = start; j < start + antallBytes; j++) {
//                        byte refByte = skrevetBytes[j];
//                        ut.writeByte(refByte);
//                        skrevetBytes[antSkrevetBytes] = refByte;
//                        antSkrevetBytes++;
//                    }
//
//                    i += 4;
//                } else {
////                    text.append(byte1);
//                    ut.writeByte(byte1);
//                    skrevetBytes[antSkrevetBytes] = byte1;
//                    antSkrevetBytes++;
//                }
//            }
//        } catch (
//                IOException e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            try {
//                if (inn != null) inn.close();
//                if (ut != null) ut.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//                System.out.println();
//            }
//        }
//
//        return utNavn;
//    }
//
//
//
//
//    public static void main(String[] args){
//
//        LZ77 lz = new LZ77(8192);
//        try {
//            lz.compress("diverse.txt");
//            //  lz.decompress("opg12.txt.compressed");
//
//
//        } catch (IOException ioe){
//            System.out.println(ioe);
//        }
//
//
//
//
//    }
//
//
//}
