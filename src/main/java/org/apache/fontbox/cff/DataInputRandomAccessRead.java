package org.apache.fontbox.cff;

import java.io.IOException;
import org.apache.pdfbox.io.RandomAccessRead;

/* loaded from: target.jar:org/apache/fontbox/cff/DataInputRandomAccessRead.class */
public class DataInputRandomAccessRead implements DataInput {
    private final RandomAccessRead randomAccessRead;

    public DataInputRandomAccessRead(RandomAccessRead randomAccessRead) {
        this.randomAccessRead = randomAccessRead;
    }

    @Override // org.apache.fontbox.cff.DataInput
    public boolean hasRemaining() throws IOException {
        return this.randomAccessRead.available() > 0;
    }

    @Override // org.apache.fontbox.cff.DataInput
    public int getPosition() throws IOException {
        return (int) this.randomAccessRead.getPosition();
    }

    @Override // org.apache.fontbox.cff.DataInput
    public void setPosition(int position) throws IOException {
        if (position < 0) {
            throw new IOException("position is negative");
        }
        if (position >= this.randomAccessRead.length()) {
            throw new IOException("New position is out of range " + position + " >= " + this.randomAccessRead.length());
        }
        this.randomAccessRead.seek(position);
    }

    @Override // org.apache.fontbox.cff.DataInput
    public byte readByte() throws IOException {
        if (!hasRemaining()) {
            throw new IOException("End off buffer reached");
        }
        return (byte) this.randomAccessRead.read();
    }

    @Override // org.apache.fontbox.cff.DataInput
    public int readUnsignedByte() throws IOException {
        if (!hasRemaining()) {
            throw new IOException("End off buffer reached");
        }
        return this.randomAccessRead.read();
    }

    @Override // org.apache.fontbox.cff.DataInput
    public int peekUnsignedByte(int offset) throws IOException {
        if (offset < 0) {
            throw new IOException("offset is negative");
        }
        if (offset == 0) {
            return this.randomAccessRead.peek();
        }
        long currentPosition = this.randomAccessRead.getPosition();
        if (currentPosition + offset >= this.randomAccessRead.length()) {
            throw new IOException("Offset position is out of range " + (currentPosition + offset) + " >= " + this.randomAccessRead.length());
        }
        this.randomAccessRead.seek(currentPosition + offset);
        int peekValue = this.randomAccessRead.read();
        this.randomAccessRead.seek(currentPosition);
        return peekValue;
    }

    @Override // org.apache.fontbox.cff.DataInput
    public byte[] readBytes(int length) throws IOException {
        if (length < 0) {
            throw new IOException("length is negative");
        }
        if (this.randomAccessRead.length() - this.randomAccessRead.getPosition() < length) {
            throw new IOException("Premature end of buffer reached");
        }
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = readByte();
        }
        return bytes;
    }

    @Override // org.apache.fontbox.cff.DataInput
    public int length() throws IOException {
        return (int) this.randomAccessRead.length();
    }
}
