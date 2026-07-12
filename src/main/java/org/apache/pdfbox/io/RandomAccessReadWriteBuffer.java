package org.apache.pdfbox.io;

import java.io.IOException;

/* loaded from: target.jar:org/apache/pdfbox/io/RandomAccessReadWriteBuffer.class */
public class RandomAccessReadWriteBuffer extends RandomAccessReadBuffer implements RandomAccess {
    public RandomAccessReadWriteBuffer() {
    }

    public RandomAccessReadWriteBuffer(int definedChunkSize) {
        super(definedChunkSize);
    }

    @Override // org.apache.pdfbox.io.RandomAccessWrite
    public void clear() throws IOException {
        checkClosed();
        resetBuffers();
    }

    @Override // org.apache.pdfbox.io.RandomAccessWrite
    public void write(int b) throws IOException {
        checkClosed();
        if (this.chunkSize - this.currentBufferPointer <= 0) {
            expandBuffer();
        }
        this.currentBuffer.put((byte) b);
        this.currentBufferPointer++;
        this.pointer++;
        if (this.pointer > this.size) {
            this.size = this.pointer;
        }
    }

    @Override // org.apache.pdfbox.io.RandomAccessWrite
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override // org.apache.pdfbox.io.RandomAccessWrite
    public void write(byte[] b, int off, int len) throws IOException {
        int bytesToWrite;
        checkClosed();
        int bOff = off;
        for (int remain = len; remain > 0; remain -= bytesToWrite) {
            bytesToWrite = Math.min(remain, this.chunkSize - this.currentBufferPointer);
            if (bytesToWrite <= 0) {
                expandBuffer();
                bytesToWrite = Math.min(remain, this.chunkSize - this.currentBufferPointer);
            }
            if (bytesToWrite > 0) {
                this.currentBuffer.put(b, bOff, bytesToWrite);
                this.currentBufferPointer += bytesToWrite;
                this.pointer += bytesToWrite;
            }
            bOff += bytesToWrite;
        }
        if (this.pointer > this.size) {
            this.size = this.pointer;
        }
    }
}
