import java.io.*;

public class BitWriter
{
    private OutputStream stream;

    private int bitCount = 0;
    private int currentByte = 0;

    /** Creates a OutputStreamBitWriter from an OutputStream */
    public BitWriter (OutputStream s)
    {
        stream = s;
    }

    /**
     * Create a OutputStreamBitWriter
     *
     * @param filename
     *            an absolute or relative filename
     * @exception FileNotFoundException
     *                thrown if the file is not writable
     */
    public BitWriter (String filename)
    {
        try
        {
            stream = new BufferedOutputStream (new FileOutputStream (filename));
        }
        catch(FileNotFoundException e)
        {
            System.out.println(filename + " does not exist");
        }
    }


    public void writeBit (int bit)
    {
        if (bit != 0 && bit != 1)
            throw new IllegalArgumentException ("A bit must be 0 or 1");

        currentByte = currentByte << 1 | bit;
        bitCount++;
        if (bitCount == 8) {
            try{stream.write (currentByte);}catch(IOException e){}
            currentByte = 0;
            bitCount = 0;
        }

    }

    public void writeBits (int bits, int num)
    {
        if ((num < 0) || (num > 32))
            throw new IllegalArgumentException ("Number of bits is out of range");

        while (num > 0) {
            // write out byte by byte
            int cbit = Math.min (num, (8 - bitCount));

            currentByte = (currentByte << cbit) | ((bits >>> (num - cbit)) & ((1 << cbit) - 1));

            bitCount += cbit;
            num -= cbit;

            // flush to output
            if (bitCount == 8) {
                try{stream.write (currentByte);}catch(IOException e){}
                currentByte = 0;
                bitCount = 0;
            }
        }
    }

    public void writeByte (byte nextByte)
    {
        // fast path
        if (bitCount == 0)
            try{stream.write (nextByte);}catch(IOException e){}
        else
            writeBits (nextByte, 8);
    }

    public void writeBytes (byte [] bytes)
    {
        if (bitCount == 0)
            try{stream.write (bytes);}catch(IOException e){}
        else {
            for (byte b : bytes)
                writeByte (b);
        }
    }

    public void writeInt (int value)
    {
        // see http://msdn2.microsoft.com/en-US/library/system.io.binarywriter.write7bitencodedint.aspx
        // about the format. Code taken from Mono's BinaryWriter.
        do {
            int high = (value >>> 7) & 0x01ffffff;
            byte b = (byte)(value & 0x7f);

            if (high != 0) {
                b = (byte)(b | 0x80);
            }

            writeByte (b);
            value = high;
        } while(value != 0);
    }

    public void flush ()
    {
        while (bitCount > 0)
            writeBit (0);

        try{stream.flush ();}catch(IOException e){}
    }
}