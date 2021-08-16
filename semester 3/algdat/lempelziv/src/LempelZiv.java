import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LempelZiv {

    // 12 bits to store maximum offset distance.
    public static final int MAX_WINDOW_SIZE = (1 << 12) - 1;
    // 4 bits to store length of the match.
    private static final int MAX_LENGTH = (1 << 4) - 1;
    private static final int MIN_LENGTH = 2;

    // sliding window size
    private int windowSize = MAX_WINDOW_SIZE;
    private int m,n,k;
    //private int bufferSizeBytes = 4;
    private int maxLength = MAX_LENGTH;
    private int minLength = MIN_LENGTH;


    public LempelZiv(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        //this.bufferSizeBytes = n;
        this.windowSize = (1 << m) - 1;
        this.maxLength = (1 << n) - 1;
        this.minLength = k;
    }

    /**
     * Compress given input file as follows
     * <p>
     * A 1 bit followed by eight bits means just copy the eight bits to the output directly.
     * A 0 bit is followed by a pointer followed by a length encoded. This is to be interpreted
     * as "copy the <length> bytes from <pointer> bytes start in the output to the current location" .
     *
     * @param inputFileName  name of the input File name to be compressed
     * @param outputFileName compressed input file file will be written to
     */

    public void compress(String inputFileName, String outputFileName) throws IOException {
        BitWriter out = new BitWriter(outputFileName);
        StringBuffer buffer = new StringBuffer(windowSize);

        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(inputFileName), StandardCharsets.ISO_8859_1);
        BufferedReader inputFile = new BufferedReader(inputStreamReader);

        out.writeBits(m, 8);
        out.writeBits(n, 8);
        out.writeBits(k, 8);

        try {
            String currentMatch = "";
            int matchIndex = 0, tempIndex = 0;
            int nextChar;

            while ((nextChar = inputFile.read()) != -1) {
                tempIndex = buffer.indexOf(currentMatch + (char) nextChar);
                if (tempIndex != -1 && currentMatch.length() < maxLength) {
                    currentMatch += (char) nextChar;
                    matchIndex = tempIndex;
                } else {
                    // is coded string longer than minimum?
                    if (currentMatch.length() >= minLength) {
                        out.writeBit(0);
                        out.writeBits(matchIndex, m);
                        out.writeBits(currentMatch.length(), n);
                        buffer.append(currentMatch); // append to the search buffer
                        currentMatch = "" + (char) nextChar;
                        matchIndex = 0;

                    } else {
                        // otherwise, output chars one at a time from currentMatch until we find a new match or run out of chars
                        currentMatch += (char) nextChar;
                        matchIndex = -1;
                        while (currentMatch.length() > -1 && matchIndex == -1) {
                            out.writeBit(1);
                            out.writeByte((byte) currentMatch.charAt(0));
                            buffer.append(currentMatch.charAt(0));
                            currentMatch = currentMatch.substring(1, currentMatch.length());
                            matchIndex = buffer.indexOf(currentMatch);
                        }
                    }
                    if (buffer.length() > windowSize) {
                        buffer = buffer.delete(0, buffer.length() - windowSize);
                    }
                }
            }
            //Check what left
            while (currentMatch.length() > 0) {
                if (currentMatch.length() >= minLength) {
                    out.writeBit(0);
                    out.writeBits(matchIndex, m);
                    out.writeBits(currentMatch.length(), n);
                    buffer.append(currentMatch); // append to the search buffer
                    currentMatch = "";
                    matchIndex = 0;

                } else {
                    matchIndex = -1;
                    while (currentMatch.length() > 0 && matchIndex == -1) {
                        out.writeBit(1);
                        out.writeByte((byte) currentMatch.charAt(0));
                        buffer.append(currentMatch.charAt(0));
                        currentMatch = currentMatch.substring(1, currentMatch.length());
                        matchIndex = buffer.indexOf(currentMatch);
                    }
                }
                if (buffer.length() > windowSize) {
                    buffer = buffer.delete(0, buffer.length() - windowSize);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.flush();
        }
    }


    /**
     * decompress input file and writes to output file
     *
     * @param inputFileName  compressed input file
     * @param outputFileName decompressed output file
     * @throws IOException
     */
    public void decompress(String inputFileName, String outputFileName) throws IOException {

        BitReader bitReader = new BitReader(inputFileName);
        FileOutputStream out = new FileOutputStream(outputFileName);
        int fileLen = bitReader.length() * 8;

        int m = bitReader.readByte();
        int windowSize = (1 << m) - 1;
        int n = bitReader.readByte();
        int minK = bitReader.readByte();
        fileLen -= 24;
        StringBuffer buffer = new StringBuffer(windowSize);
        while (fileLen >= 8) {
            int flag = bitReader.readBit();
            if (flag == 1) {
                int s = bitReader.readBits(8);
                buffer.append((char) s);
                out.write(s);
                fileLen -= 9;
            } else {

                int offsetValue = bitReader.readBits(m);
                int lengthValue = bitReader.readBits(n);

                if(offsetValue < 0 || lengthValue < 0) break;

                int start = offsetValue;
                int end = start + lengthValue;

                String temp = buffer.substring(start, end);
                out.write(temp.getBytes(StandardCharsets.ISO_8859_1));
                buffer.append(temp);
                fileLen -= (m+n);
            }
            if (buffer.length() > windowSize) {
                buffer = buffer.delete(0, buffer.length() - windowSize);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        int m = 12, n = 4, k = 2;

//        if (args.length > 2) {
//            try {
//                m = Integer.valueOf(args[2]);
//                n = Integer.valueOf(args[3]);
//                k = Integer.valueOf(args[4]);
//            } catch (NumberFormatException e) {
//                System.out.println("Please enter valid numbers or use default values.");
//                helpMenu();
//                return;
//            }
//        }

        LempelZiv LempelZiv1 = new LempelZiv(m, n, k);

        String fileName = "C:\\Users\\trymg\\OneDrive - NTNU\\dataingeniør\\sem 3\\algdat\\øving12\\src\\opg12.txt";

        if(!Files.exists(Paths.get(fileName))){
            System.out.println("File doesn't exists");
        }

        StringBuilder fileNameBuilder = new StringBuilder();
        int extension = fileName.lastIndexOf(".");


        //compression
        String compressedFileName = "compressed.txt";
        compression(LempelZiv1, fileName, compressedFileName);

        //decompression
//        String decompressedFileName = "C:\\Users\\trymg\\OneDrive - NTNU\\dataingeniør\\sem 3\\algdat\\øving12\\src\\opg12.txt";
//        decompression(LempelZiv1, "compressed.txt", "decompressed.txt");

//        switch (args[0]) {
//            case "c":
//                if (extension > -1) {
//                    fileNameBuilder.append(fileName.substring(0, extension));
//                    fileNameBuilder.append("-c");
//                    fileNameBuilder.append(fileName.substring(extension));
//                } else {
//                    fileNameBuilder.append(fileName);
//                    fileNameBuilder.append("-c");
//                }
//                String compressedFileName = fileNameBuilder.toString();
//                checkFile(compressedFileName);
//                compression(LempelZiv1, fileName, compressedFileName);
//                break;
//            case "d":
//                if (extension > -1) {
//                    fileNameBuilder.append(fileName.substring(0, extension));
//                    fileNameBuilder.append("-d");
//                    fileNameBuilder.append(fileName.substring(extension));
//                } else {
//                    fileNameBuilder.append(fileName);
//                    fileNameBuilder.append("-d");
//                }
//                String decompressedFileName = fileNameBuilder.toString();
//                checkFile(decompressedFileName);
//                decompression(LempelZiv1, fileName, decompressedFileName);
//                break;
//            default:
//                helpMenu();
//                break;
//        }

    }

    private static void checkFile(String filename) throws IOException {
        if(Files.exists(Paths.get(filename))){
            Files.delete(Paths.get(filename));
        }
    }

    private static void compression(LempelZiv LempelZiv1, String inputFileName, String compressedFileName) throws IOException {
        System.out.println("Compression started...");
        long startTime = System.currentTimeMillis();
        LempelZiv1.compress(inputFileName, compressedFileName);
        long endTime = System.currentTimeMillis();
        System.out.println("Compression done in: " + (endTime - startTime)/1000 + "s (" +(endTime - startTime)+"ms)");
    }

    private static void decompression(LempelZiv LempelZiv1, String inputFileName, String decompressedFileName) throws IOException {

        System.out.println("Decompression started...");
        long startTime = System.currentTimeMillis();
        LempelZiv1.decompress(inputFileName, decompressedFileName);
        long endTime = System.currentTimeMillis();
        System.out.println("Decompression done in: " + (endTime - startTime)/1000 + "s (" +(endTime - startTime)+"ms)");
    }

    private static void helpMenu(){
        System.out.println("Usage : java -jar LempelZiv1.jar c|d inputFileName [history_size_bits maximum_Match_size_bits minimumMatch]");
        System.out.println("History is optional. Default size is :" + MAX_WINDOW_SIZE+1 + ". If history size gets bigger, then compression time increases.");
        System.out.println("Compressed file will be written into input_file_name-compressed.extension");
        System.out.println("Decompressed file will be written into input_file_name-decompressed.extension");
        System.exit(1);
    }
}