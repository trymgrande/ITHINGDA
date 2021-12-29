//import java.io.BufferedOutputStream;
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.EOFException;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.BitSet;
//import java.util.Comparator;
//import java.util.PriorityQueue;
//
//// den grunnleggende strukturen i huffmantreet
//class HuffmanNode {
//
//    int data;
//    char c;
//
//    HuffmanNode left;
//    HuffmanNode right;
//}
//
//// sammenlikner noder på grunnlag av dataen dens
//class MyComparator implements Comparator<HuffmanNode> {
//    public int compare(HuffmanNode x, HuffmanNode y) {
//
//        return x.data - y.data;
//    }
//}
//
//class Huffman {
//    public static void updateFreqArray(ArrayList<Character> textFile, int[] charFreq) {
//
//        for (int i = 0; i < textFile.size(); i++) {
//            if ((int) textFile.get(i) < 256) {
//                charFreq[(int) textFile.get(i)]++;
//            }
//        }
//    }
//
//    public static ArrayList<Character> lesFil(String filnavn) {
//        BufferedReader innfil = null;
//        ArrayList<Character> charArray = new ArrayList<Character>();
//
//        try {
//            innfil = new BufferedReader(new FileReader(new File(filnavn)));
//            int temp = innfil.read();
//            while (temp != (-1)) {
//                charArray.add((char) temp);
//                temp = innfil.read();
//            }
//            return charArray;
//
//        } catch (EOFException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                innfil.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return charArray;
//    }
//
//    // Printer huffman-koden til et tegn
//    public static void generateBitStrings(HuffmanNode root, String s, BitSet[] bitStrings) {
//
//        // Hvis høyre og ventstre er null
//        // bladnode: print koden som genereres ved å traversere treet
//        if (root.left == null && root.right == null) {
//
//            // c er nodens char
//            bitStrings[(int) root.c] = new BitSet();
//            for (int i = 0; i < s.length(); i++) {
//                if (s.charAt(i) == '1') {
//                    bitStrings[(int) root.c].set(i);
//                }
//            }
//            bitStrings[(int) root.c].set(s.length(), true);
//            System.out.println(root.c + ": " + s);
//            return;
//        }
//
//        // Hvis vi går til venstre, legg 0 til koden
//        // Hvis vi går til høyre, legg 1 til i koden
//
//        // rekursive kall for venstre og høyre sub-tre
//        generateBitStrings(root.left, s + "0", bitStrings);
//        generateBitStrings(root.right, s + "1", bitStrings);
//    }
//
//    public static byte[] int2byte(int[] src) {
//        int srcLength = src.length;
//        byte[] dst = new byte[srcLength << 2];
//
//        for (int i = 0; i < srcLength; i++) {
//            int x = src[i];
//            int j = i << 2;
//            dst[j++] = (byte) ((x >>> 0) & 0xff);
//            dst[j++] = (byte) ((x >>> 8) & 0xff);
//            dst[j++] = (byte) ((x >>> 16) & 0xff);
//            dst[j++] = (byte) ((x >>> 24) & 0xff);
//        }
//        return dst;
//    }
//
//    public static void skrivTilFil(ArrayList<Character> textFile, String filenameOutput, BitSet[] bitStrings,
//            int[] charFreq) {
//        BitSet output = new BitSet();
//        DataOutputStream utfil = null;
//        // Legger hele teksten i et langt bitset
//        int lengde = 0;
//        for (int i = 0; i < textFile.size(); i++) {
//            if ((int) textFile.get(i) < 256) {
//                BitSet tempBitSet = bitStrings[(int) textFile.get(i)];
//                for (int k = 0; k < tempBitSet.length() - 1; k++) {
//                    output.set(lengde, tempBitSet.get(k));
//                    lengde++;
//                }
//            }
//        }
//
//        // Konverterer bitset til byte array, bestående av komrimert tekst
//        byte[] textAsBytes = output.toByteArray();
//
//        // Array for både frekvenstabell og komprimert tekst
//        byte[] completeOutput = new byte[textAsBytes.length + 1024];
//
//        // Legger frekvenstabellen inn i den første kilobyten av completeOutput
//        for (int i = 0; i < charFreq.length; i++) {
//            completeOutput[4 * i + 3] = (byte) (charFreq[i] & 0xFF);
//            completeOutput[4 * i + 2] = (byte) ((charFreq[i] >> 8) & 0xFF);
//            completeOutput[4 * i + 1] = (byte) ((charFreq[i] >> 16) & 0xFF);
//            completeOutput[4 * i] = (byte) ((charFreq[i] >> 24) & 0xFF);
//        }
//
//        // Fyller siste del av completeOutput med komprimert tekst
//        for (int i = 0; i < textAsBytes.length; i++) {
//            completeOutput[1024 + i] = textAsBytes[i];
//        }
//
//        try {
//            utfil = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filenameOutput)));
//
//            // Skriver completeOutput til fil
//            utfil.write(completeOutput);
//            System.out.println("Successfully wrote to file");
//
//            // Close the file
//            utfil.close();
//        } catch (Exception e) {
//            System.out.println("Exception: " + e);
//        }
//    }
//
//    public static HuffmanNode createHuffmanTree(int[] charFreq) {
//        PriorityQueue<HuffmanNode> pqueue = new PriorityQueue<HuffmanNode>(charFreq.length, new MyComparator());
//
//        for (int i = 0; i < charFreq.length; i++) {
//            if (charFreq[i] != 0) {
//                // Lager en huffman node med et tegn og en frekvens
//                HuffmanNode hn = new HuffmanNode();
//                hn.c = (char) i;
//                hn.data = charFreq[i];
//
//                // Har ingen barn enda
//                hn.left = null;
//                hn.right = null;
//
//                // legger noden i prioritetskøen
//                pqueue.add(hn);
//            }
//        }
//
//        HuffmanNode root = null;
//
//        // Trekker ut de to minste verdiene fra køen og lager nye noder
//        // helt til det kun er en node igjen i køen
//        while (pqueue.size() > 1) {
//
//            // første minimum trekk
//            HuffmanNode x = pqueue.peek();
//            pqueue.poll();
//
//            // andre minimum trekk.
//            HuffmanNode y = pqueue.peek();
//            pqueue.poll();
//
//            // ny node som har frekvens lik summen av freq til x og y
//            HuffmanNode f = new HuffmanNode();
//            f.data = x.data + y.data;
//            f.c = '#';
//
//            // første trukkede node som venstre barn
//            f.left = x;
//
//            // andre trukkede node som høyre barn
//            f.right = y;
//
//            // markerer f noden som rot
//            // til slutt vil metoden returnere roten
//            // denne vil ha alle chars som løvnoder
//            root = f;
//
//            // Legg denne noden til i prioritetskø
//            pqueue.add(f);
//        }
//        return root;
//    }
//
//    // main metode
//    public static void main(String[] args) {
//        // Arrays for alle chars, og deres frekvenser
//        ArrayList<Character> textFile = lesFil("test.txt");
//
//        // Frekvenstabell med ascii verdier som index
//        int[] charFreq = new int[256];
//        // Fyller frekenstabellen
//
//        Huffman.updateFreqArray(textFile, charFreq);
//        // Lager et huffmantree ogreturnerer roten
//        HuffmanNode root = createHuffmanTree(charFreq);
//
//        // Bitstrengene til chars lagres i denne
//        BitSet[] bitStrings = new BitSet[charFreq.length];
//        // Printer bokstavkodene og fyllerbitstring-tabellen
//        generateBitStrings(root, "", bitStrings);
//        // Her skrivestekst til fil ved hjelp av bitstrings
//        skrivTilFil(textFile, "outputKomprimert.txt", bitStrings, charFreq);
//    }
//}
