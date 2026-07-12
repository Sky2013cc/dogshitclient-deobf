package org.apache.fontbox.pfb;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* loaded from: target.jar:org/apache/fontbox/pfb/PfbParser.class */
public class PfbParser {
    private static final Log LOG = LogFactory.getLog(PfbParser.class);
    private static final int PFB_HEADER_LENGTH = 18;
    private static final int START_MARKER = 128;
    private static final int ASCII_MARKER = 1;
    private static final int BINARY_MARKER = 2;
    private static final int EOF_MARKER = 3;
    private static final int BUFFER_SIZE = 65535;
    private byte[] pfbdata;
    private final int[] lengths;

    public PfbParser(String filename) throws IOException {
        this(Files.readAllBytes(Paths.get(filename, new String[0])));
    }

    public PfbParser(InputStream in) throws IOException {
        this.lengths = new int[3];
        byte[] pfb = readFully(in);
        parsePfb(pfb);
    }

    public PfbParser(byte[] bytes) throws IOException {
        this.lengths = new int[3];
        parsePfb(bytes);
    }

    private void parsePfb(byte[] pfb) throws IOException {
        if (pfb.length < 18) {
            throw new IOException("PFB header missing");
        }
        List<Integer> typeList = new ArrayList<>(3);
        List<byte[]> barrList = new ArrayList<>(3);
        ByteArrayInputStream in = new ByteArrayInputStream(pfb);
        int total = 0;
        while (true) {
            int r = in.read();
            if (r == -1 && total > 0) {
                break;
            }
            if (r != 128) {
                throw new IOException("Start marker missing");
            }
            int recordType = in.read();
            if (recordType == 3) {
                break;
            }
            if (recordType != 1 && recordType != 2) {
                throw new IOException("Incorrect record type: " + recordType);
            }
            int size = in.read() + (in.read() << 8) + (in.read() << 16) + (in.read() << 24);
            if (LOG.isDebugEnabled()) {
                LOG.debug("record type: " + recordType + ", segment size: " + size);
            }
            byte[] ar = new byte[size];
            int got = in.read(ar);
            if (got != size) {
                throw new EOFException("EOF while reading PFB font");
            }
            total += size;
            typeList.add(Integer.valueOf(recordType));
            barrList.add(ar);
        }
        this.pfbdata = new byte[total];
        byte[] cleartomarkSegment = null;
        int dstPos = 0;
        for (int i = 0; i < typeList.size(); i++) {
            if (typeList.get(i).intValue() == 1) {
                byte[] ar2 = barrList.get(i);
                if (i == typeList.size() - 1 && ar2.length < 600 && new String(ar2).contains("cleartomark")) {
                    cleartomarkSegment = ar2;
                } else {
                    System.arraycopy(ar2, 0, this.pfbdata, dstPos, ar2.length);
                    dstPos += ar2.length;
                }
            }
        }
        this.lengths[0] = dstPos;
        for (int i2 = 0; i2 < typeList.size(); i2++) {
            if (typeList.get(i2).intValue() == 2) {
                byte[] ar3 = barrList.get(i2);
                System.arraycopy(ar3, 0, this.pfbdata, dstPos, ar3.length);
                dstPos += ar3.length;
            }
        }
        this.lengths[1] = dstPos - this.lengths[0];
        if (cleartomarkSegment != null) {
            System.arraycopy(cleartomarkSegment, 0, this.pfbdata, dstPos, cleartomarkSegment.length);
            this.lengths[2] = cleartomarkSegment.length;
        }
    }

    private byte[] readFully(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] tmpbuf = new byte[65535];
        while (true) {
            int amountRead = in.read(tmpbuf);
            if (amountRead != -1) {
                out.write(tmpbuf, 0, amountRead);
            } else {
                return out.toByteArray();
            }
        }
    }

    public int[] getLengths() {
        return this.lengths;
    }

    public byte[] getPfbdata() {
        return this.pfbdata;
    }

    public InputStream getInputStream() {
        return new ByteArrayInputStream(this.pfbdata);
    }

    public int size() {
        return this.pfbdata.length;
    }

    public byte[] getSegment1() {
        return Arrays.copyOfRange(this.pfbdata, 0, this.lengths[0]);
    }

    public byte[] getSegment2() {
        return Arrays.copyOfRange(this.pfbdata, this.lengths[0], this.lengths[0] + this.lengths[1]);
    }
}
