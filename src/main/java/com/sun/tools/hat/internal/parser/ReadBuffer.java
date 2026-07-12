package com.sun.tools.hat.internal.parser;

import java.io.IOException;

/* loaded from: target.jar:com/sun/tools/hat/internal/parser/ReadBuffer.class */
public interface ReadBuffer {
    void get(long j, byte[] bArr) throws IOException;

    char getChar(long j) throws IOException;

    byte getByte(long j) throws IOException;

    short getShort(long j) throws IOException;

    int getInt(long j) throws IOException;

    long getLong(long j) throws IOException;
}
