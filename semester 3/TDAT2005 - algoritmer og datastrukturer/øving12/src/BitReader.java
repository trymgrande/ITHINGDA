import java.io.*;


public class BitReader
{
    private InputStream stream;

    // number of bits left in the byte I read already
    private int bitsLeft = 0;
    private int currentByte = 0;

    public int readBitsCount = 0;

    LengthResetHelper streamSeeker;



    private interface LengthResetHelper {
        long length();
        void reset();
    }

    public BitReader (final InputStream is) throws IOException
    {
        if (! is.markSupported())
            throw new RuntimeException ("InputStream must support marking");

        stream = is;
        is.mark (0);
        // skip wil skip ahead, but also return how many
        // bytes it was able to skip.
        final long length = is.skip (Long.MAX_VALUE);
        // now just reset it back
        try{is.reset ();}catch(IOException e){}

        streamSeeker = new LengthResetHelper () {
            @Override
            public long length ()
            {
                return length;
            }

            @Override
            public void reset ()
            {
                try{is.reset ();}catch(IOException e){}
            }
        };
    }

    /**
     * Create an InputStreamBitReader
     *
     */
    public BitReader (String filename) throws FileNotFoundException
    {
        this (new File (filename));
    }

    public BitReader (File file) throws FileNotFoundException
    {
        this (new FileInputStream (file));
    }

    public BitReader (final FileInputStream file)
    {
        stream = new BufferedInputStream (file);
        streamSeeker = new LengthResetHelper () {
            public long length ()
            {
                try {
                    return file.getChannel().size();
                } catch (IOException e) {
                    throw new RuntimeException (e);
                }
            }

            public void reset ()
            {
                try {
                    file.getChannel().position(0);
                    stream = new BufferedInputStream (file);
                } catch (IOException e) {
                    throw new RuntimeException (e);
                }
            }
        };
    }

    public int readBit ()
    {
        readBitsCount++;
        if (bitsLeft == 0) {

            try{currentByte = stream.read ();}catch(IOException e){}
            if (currentByte == -1)
                return -1;
            bitsLeft = 8;
        }
        bitsLeft--;
        return (currentByte >>> bitsLeft) & 1;
    }


    public int readBits (int num)
    {
        readBitsCount += num;
        if ((num < 0) || (num > 31)) {
            throw new IllegalArgumentException ("Number of bits is out of range");
        }


        int bits = 0;
        while (num > 0) {
            if (bitsLeft == 0) {

                try{currentByte = stream.read ();}catch(IOException e){}

                if (currentByte == -1)
                    return -1;
                bitsLeft = 8;
            }

            int cbit = Math.min (num, bitsLeft);
            bits = (bits << cbit) | ((currentByte >>> (bitsLeft - cbit) & ((1 << cbit) - 1)));
            num -= cbit;
            bitsLeft -= cbit;

        }
        return bits;
    }

    public int readByte ()
    {
        readBitsCount += 8;
        try{
            if (bitsLeft == 0)
                return stream.read ();
        }
        catch(IOException e){}

        return readBits (8);
    }

    public int readBytes (byte [] buf, int start, int count)
    {
        readBitsCount += count;
        try{
            if (bitsLeft == 0)
                return stream.read(buf, start, count);
        }
        catch(IOException e){}



        for (int i = 0; i < count; i++) {
            int b = readByte ();
            if (b == -1)
                return i == 0 ? -1 : i;

            buf [start + i] = (byte) b;
        }
        return count;
    }

    public int readInt () throws EOFException
    {
        readBitsCount += Integer.SIZE;// SIZE cosntant is given in bits
        int ret = 0;
        int shift = 0;
        int b;

        do {
            b = readByte ();
            if (b == -1)
                throw new EOFException ();

            ret |= ((b & 0x7f) << shift);
            shift += 7;
        } while ((b & 0x80) == 0x80);

        return ret;
    }

    public int length ()
    {
        long l = streamSeeker.length();
        if (l > Integer.MAX_VALUE)
            throw new RuntimeException ("No support for 64 bit file IO. 640K ought to be enough for anybody.");
        return (int) l;
    }

    public void reset ()
    {
        streamSeeker.reset ();
    }
}