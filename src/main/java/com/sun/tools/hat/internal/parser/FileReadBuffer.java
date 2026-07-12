package com.sun.tools.hat.internal.parser;

import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: target.jar:com/sun/tools/hat/internal/parser/FileReadBuffer.class */
class FileReadBuffer implements ReadBuffer {
    private RandomAccessFile file;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileReadBuffer(RandomAccessFile randomAccessFile) {
        this.file = randomAccessFile;
    }

    private void seek(long j) throws IOException {
        this.file.getChannel().position(j);
    }

    @Override // com.sun.tools.hat.internal.parser.ReadBuffer
    public synchronized void get(long j, byte[] bArr) throws IOException {
        seek(j);
        this.file.read(bArr);
    }

    @Override // com.sun.tools.hat.internal.parser.ReadBuffer
    public synchronized char getChar(long j) throws IOException {
        seek(j);
        return this.file.readChar();
    }

    @Override // com.sun.tools.hat.internal.parser.ReadBuffer
    public synchronized byte getByte(long j) throws IOException {
        seek(j);
        return (byte) this.file.read();
    }

    @Override // com.sun.tools.hat.internal.parser.ReadBuffer
    public synchronized short getShort(long j) throws IOException {
        seek(j);
        return this.file.readShort();
    }

    @Override // com.sun.tools.hat.internal.parser.ReadBuffer
    public synchronized int getInt(long j) throws IOException {
        seek(j);
        return this.file.readInt();
    }

    @Override // com.sun.tools.hat.internal.parser.ReadBuffer
    public synchronized long getLong(long j) throws IOException {
        seek(j);
        return this.file.readLong();
    }
}
