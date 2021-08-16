//import java.io.BufferedInputStream;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.DataInputStream;
//import java.io.EOFException;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.ByteBuffer;
//import java.util.BitSet;
//import java.util.PriorityQueue;
//
//class HuffmanNode {
//
//    int data;
//    char c;
//
//    HuffmanNode left;
//    HuffmanNode right;
//}
//
//class Dekomprimering {
//    public static void skrivOutputTilFil(String filnavn, String innhold) {
//        BufferedWriter utfil = null;
//
//        try {
//            utfil = new BufferedWriter(new FileWriter(new File(filnavn)));
//            utfil.write(innhold, 0, innhold.length());
//
//            utfil.close();
//        } catch (EOFException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                utfil.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static byte[] lesFilInnhold(String filnavn) {
//        DataInputStream innfil = null;
//        byte[] filInnhold = null;
//
//        // Leser inn frekvensTabell
//        try {
//            innfil = new DataInputStream(new BufferedInputStream(new FileInputStream(filnavn)));
//            filInnhold = new byte[innfil.available()];
//            innfil.readFully(filInnhold);
//            innfil.close();
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
//
//        return filInnhold;
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
//    public static String dekomprimer(byte[] bytes) {
//        // Oppretter frekvenstabell
//        int[] frekvensTabell = new int[256];
//        // Leser inn 4 bytes for å lage en int i frekvenstabellen
//        for (int i = 0; i < 256; i++) {
//            byte[] buffer = new byte[] { bytes[i * 4], bytes[i * 4 + 1], bytes[i * 4 + 2], bytes[i * 4 + 3] };
//            frekvensTabell[i] = ByteBuffer.wrap(buffer).getInt();
//        }
//
//        // lager huffmantre ved hjelp av frekvenstabell
//        HuffmanNode root = createHuffmanTree(frekvensTabell);
//
//        // Fyller en byte tabell med komprimert tekst
//        byte[] data = new byte[bytes.length - 1024];
//        for (int i = 0; i < data.length; i++) {
//            data[i] = bytes[i + 1024];
//        }
//
//        // lager et bitset av all komprimert tekst
//        BitSet bs = BitSet.valueOf(data);
//        String output = "";
//
//        HuffmanNode currentNode = root;
//        for (int i = 0; i < bs.length(); i++) {
//            if (bs.get(i)) {
//                // Hvis bit er satt på plass i, gå til høyre barnenode
//                currentNode = currentNode.right;
//            } else {
//                // Hvis bit ikke er satt på plass i, gå til venstre barnenode
//                currentNode = currentNode.left;
//            }
//            // Hvis currentNode ikke har barn/er løvnode, legg dens char til i output string
//            if (currentNode.c != '#') {
//                output += currentNode.c;
//                currentNode = root;
//            }
//        }
//        // returnerer output
//        return output;
//    }
//
//    public static void main(String[] args) {
//        // Leser komprimert tekst som bytes fra fil
//        byte[] filInnhold = lesFilInnhold("outputKomprimert.txt");
//        // Dekomprimerer og returnerer innhold
//        String output = dekomprimer(filInnhold);
//        // Skriver dekomprimert tekst til fil
//        skrivOutputTilFil("DekomprimertTekst.txt", output);
//    }
//}
