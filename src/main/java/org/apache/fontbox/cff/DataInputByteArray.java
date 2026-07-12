package org.apache.fontbox.cff;

import java.io.IOException;

/* loaded from: target.jar:org/apache/fontbox/cff/DataInputByteArray.class */
public class DataInputByteArray implements DataInput {
    private final byte[] inputBuffer;
    private int bufferPosition = 0;

    public DataInputByteArray(byte[] buffer) {
        this.inputBuffer = buffer;
    }

    @Override // org.apache.fontbox.cff.DataInput
    public boolean hasRemaining() throws IOException {
        return this.bufferPosition < this.inputBuffer.length;
    }

    @Override // org.apache.fontbox.cff.DataInput
    public int getPosition() {
        return this.bufferPosition;
    }

    @Override // org.apache.fontbox.cff.DataInput
    public void setPosition(int position) throws IOException {
        if (position < 0) {
            throw new IOException("position is negative");
        }
        if (position >= this.inputBuffer.length) {
            throw new IOException("New position is out of range " + position + " >= " + this.inputBuffer.length);
        }
        this.bufferPosition = position;
    }

    @Override // org.apache.fontbox.cff.DataInput
    public byte readByte() throws IOException {
        if (!hasRemaining()) {
            throw new IOException("End off buffer reached");
        }
        byte[] bArr = this.inputBuffer;
        int i = this.bufferPosition;
        this.bufferPosition = i + 1;
        return bArr[i];
    }

    @Override // org.apache.fontbox.cff.DataInput
    public int readUnsignedByte() throws IOException {
        if (!hasRemaining()) {
            throw new IOException("End off buffer reached");
        }
        byte[] bArr = this.inputBuffer;
        int i = this.bufferPosition;
        this.bufferPosition = i + 1;
        return bArr[i] & 255;
    }

    @Override // org.apache.fontbox.cff.DataInput
    public int peekUnsignedByte(int offset) throws IOException {
        if (offset < 0) {
            throw new IOException("offset is negative");
        }
        if (this.bufferPosition + offset >= this.inputBuffer.length) {
            throw new IOException("Offset position is out of range " + (this.bufferPosition + offset) + " >= " + this.inputBuffer.length);
        }
        return this.inputBuffer[this.bufferPosition + offset] & 255;
    }

    @Override // org.apache.fontbox.cff.DataInput
    public byte[] readBytes(int length) throws IOException {
        if (length < 0) {
            throw new IOException("length is negative");
        }
        if (this.inputBuffer.length - this.bufferPosition < length) {
            throw new IOException("Premature end of buffer reached");
        }
        byte[] bytes = new byte[length];
        System.arraycopy(this.inputBuffer, this.bufferPosition, bytes, 0, length);
        this.bufferPosition += length;
        return bytes;
    }

    @Override // org.apache.fontbox.cff.DataInput
    public int length() throws IOException {
        return this.inputBuffer.length;
    }
}
