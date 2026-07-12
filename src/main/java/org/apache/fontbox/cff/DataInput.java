package org.apache.fontbox.cff;

import java.io.IOException;

/* loaded from: target.jar:org/apache/fontbox/cff/DataInput.class */
public interface DataInput {
    boolean hasRemaining() throws IOException;

    int getPosition() throws IOException;

    void setPosition(int i) throws IOException;

    byte readByte() throws IOException;

    int readUnsignedByte() throws IOException;

    int peekUnsignedByte(int i) throws IOException;

    byte[] readBytes(int i) throws IOException;

    int length() throws IOException;

    default short readShort() throws IOException {
        return (short) readUnsignedShort();
    }

    default int readUnsignedShort() throws IOException {
        int b1 = readUnsignedByte();
        int b2 = readUnsignedByte();
        return (b1 << 8) | b2;
    }

    default int readInt() throws IOException {
        int b1 = readUnsignedByte();
        int b2 = readUnsignedByte();
        int b3 = readUnsignedByte();
        int b4 = readUnsignedByte();
        return (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
    }

    default int readOffset(int offSize) throws IOException {
        int value = 0;
        for (int i = 0; i < offSize; i++) {
            value = (value << 8) | readUnsignedByte();
        }
        return value;
    }
}
