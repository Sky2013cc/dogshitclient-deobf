package org.apache.pdfbox.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: target.jar:org/apache/pdfbox/io/RandomAccessReadBuffer.class */
public class RandomAccessReadBuffer implements RandomAccessRead {
    public static final int DEFAULT_CHUNK_SIZE_4KB = 4096;
    protected int chunkSize;
    private final List<ByteBuffer> bufferList;
    protected ByteBuffer currentBuffer;
    protected long pointer;
    protected int currentBufferPointer;
    protected long size;
    private int bufferListIndex;
    private int bufferListMaxIndex;
    private final ConcurrentMap<Long, RandomAccessReadBuffer> rarbCopies;

    /* JADX INFO: Access modifiers changed from: protected */
    public RandomAccessReadBuffer() {
        this(4096);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RandomAccessReadBuffer(int definedChunkSize) {
        this.chunkSize = 4096;
        this.pointer = 0L;
        this.currentBufferPointer = 0;
        this.size = 0L;
        this.bufferListIndex = 0;
        this.bufferListMaxIndex = 0;
        this.rarbCopies = new ConcurrentHashMap();
        this.chunkSize = definedChunkSize;
        this.currentBuffer = ByteBuffer.allocate(this.chunkSize);
        this.bufferList = new ArrayList(1);
        this.bufferList.add(this.currentBuffer);
    }

    public RandomAccessReadBuffer(byte[] input) {
        this(ByteBuffer.wrap(input));
    }

    public RandomAccessReadBuffer(ByteBuffer input) {
        this.chunkSize = 4096;
        this.pointer = 0L;
        this.currentBufferPointer = 0;
        this.size = 0L;
        this.bufferListIndex = 0;
        this.bufferListMaxIndex = 0;
        this.rarbCopies = new ConcurrentHashMap();
        this.chunkSize = input.capacity();
        this.size = this.chunkSize;
        this.currentBuffer = input;
        this.bufferList = new ArrayList(1);
        this.bufferList.add(this.currentBuffer);
    }

    public RandomAccessReadBuffer(InputStream input) throws IOException {
        this();
        int bytesRead;
        int remainingBytes = this.chunkSize;
        int offset = 0;
        byte[] eofCheck = new byte[1];
        while (remainingBytes > 0 && (bytesRead = input.read(this.currentBuffer.array(), offset, remainingBytes)) > -1) {
            remainingBytes -= bytesRead;
            offset += bytesRead;
            this.size += bytesRead;
            if (remainingBytes == 0 && input.read(eofCheck) > 0) {
                expandBuffer();
                this.currentBuffer.put(eofCheck);
                offset = 1;
                remainingBytes = this.chunkSize - 1;
                this.size++;
            }
        }
        this.currentBuffer.limit(offset);
        seek(0L);
    }

    private RandomAccessReadBuffer(RandomAccessReadBuffer parent) {
        this.chunkSize = 4096;
        this.pointer = 0L;
        this.currentBufferPointer = 0;
        this.size = 0L;
        this.bufferListIndex = 0;
        this.bufferListMaxIndex = 0;
        this.rarbCopies = new ConcurrentHashMap();
        this.chunkSize = parent.chunkSize;
        this.size = parent.size;
        this.bufferListMaxIndex = parent.bufferListMaxIndex;
        this.bufferList = new ArrayList(parent.bufferList.size());
        for (ByteBuffer buffer : parent.bufferList) {
            ByteBuffer newBuffer = buffer.duplicate();
            newBuffer.rewind();
            this.bufferList.add(newBuffer);
        }
        this.currentBuffer = this.bufferList.get(0);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.rarbCopies.values().forEach((v0) -> {
            IOUtils.closeQuietly(v0);
        });
        this.rarbCopies.clear();
        this.currentBuffer = null;
        this.bufferList.clear();
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public void seek(long position) throws IOException {
        checkClosed();
        if (position < 0) {
            throw new IOException("Invalid position " + position);
        }
        if (position < this.size) {
            this.pointer = position;
            this.bufferListIndex = this.chunkSize > 0 ? (int) (this.pointer / this.chunkSize) : 0;
            this.currentBufferPointer = this.chunkSize > 0 ? (int) (this.pointer % this.chunkSize) : 0;
            this.currentBuffer = this.bufferList.get(this.bufferListIndex);
        } else {
            this.pointer = this.size;
            this.bufferListIndex = this.bufferListMaxIndex;
            this.currentBuffer = this.bufferList.get(this.bufferListIndex);
            this.currentBufferPointer = this.chunkSize > 0 ? (int) (this.size % this.chunkSize) : 0;
        }
        this.currentBuffer.position(this.currentBufferPointer);
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public long getPosition() throws IOException {
        checkClosed();
        return this.pointer;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public int read() throws IOException {
        checkClosed();
        if (this.pointer >= this.size) {
            return -1;
        }
        if (this.currentBufferPointer >= this.chunkSize) {
            if (this.bufferListIndex >= this.bufferListMaxIndex) {
                return -1;
            }
            List<ByteBuffer> list = this.bufferList;
            int i = this.bufferListIndex + 1;
            this.bufferListIndex = i;
            this.currentBuffer = list.get(i);
            this.currentBufferPointer = 0;
        }
        this.pointer++;
        ByteBuffer byteBuffer = this.currentBuffer;
        int i2 = this.currentBufferPointer;
        this.currentBufferPointer = i2 + 1;
        return byteBuffer.get(i2) & 255;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public int read(byte[] b, int offset, int length) throws IOException {
        checkClosed();
        int bytesRead = readRemainingBytes(b, offset, length);
        if (bytesRead == -1) {
            if (available() > 0) {
                bytesRead = 0;
            } else {
                return -1;
            }
        }
        while (bytesRead < length && available() > 0) {
            if (this.currentBufferPointer == this.chunkSize) {
                nextBuffer();
            }
            bytesRead += readRemainingBytes(b, offset + bytesRead, length - bytesRead);
        }
        return bytesRead;
    }

    private int readRemainingBytes(byte[] b, int offset, int length) {
        if (this.pointer >= this.size) {
            return -1;
        }
        int maxLength = (int) Math.min(length, this.size - this.pointer);
        int remainingBytes = this.chunkSize - this.currentBufferPointer;
        if (remainingBytes == 0) {
            return -1;
        }
        if (maxLength >= remainingBytes) {
            this.currentBuffer.position(this.currentBufferPointer);
            this.currentBuffer.get(b, offset, remainingBytes);
            this.currentBufferPointer += remainingBytes;
            this.pointer += remainingBytes;
            return remainingBytes;
        }
        this.currentBuffer.position(this.currentBufferPointer);
        this.currentBuffer.get(b, offset, maxLength);
        this.currentBufferPointer += maxLength;
        this.pointer += maxLength;
        return maxLength;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public long length() throws IOException {
        checkClosed();
        return this.size;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void expandBuffer() throws IOException {
        if (this.bufferListMaxIndex > this.bufferListIndex) {
            nextBuffer();
            return;
        }
        this.currentBuffer = ByteBuffer.allocate(this.chunkSize);
        this.bufferList.add(this.currentBuffer);
        this.currentBufferPointer = 0;
        this.bufferListMaxIndex++;
        this.bufferListIndex++;
    }

    private void nextBuffer() throws IOException {
        if (this.bufferListIndex == this.bufferListMaxIndex) {
            throw new IOException("No more chunks available, end of buffer reached");
        }
        this.currentBufferPointer = 0;
        List<ByteBuffer> list = this.bufferList;
        int i = this.bufferListIndex + 1;
        this.bufferListIndex = i;
        this.currentBuffer = list.get(i);
        this.currentBuffer.rewind();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkClosed() throws IOException {
        if (this.currentBuffer == null) {
            throw new IOException("RandomAccessBuffer already closed");
        }
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public boolean isClosed() {
        return this.currentBuffer == null;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public boolean isEOF() throws IOException {
        checkClosed();
        return this.pointer >= this.size;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public RandomAccessReadView createView(long startPosition, long streamLength) throws IOException {
        Long currentThreadID = Long.valueOf(Thread.currentThread().getId());
        RandomAccessReadBuffer randomAccessReadBuffer = this.rarbCopies.get(currentThreadID);
        if (randomAccessReadBuffer == null || randomAccessReadBuffer.isClosed()) {
            randomAccessReadBuffer = new RandomAccessReadBuffer(this);
            this.rarbCopies.put(currentThreadID, randomAccessReadBuffer);
        }
        return new RandomAccessReadView(randomAccessReadBuffer, startPosition, streamLength);
    }

    public static RandomAccessReadBuffer createBufferFromStream(InputStream inputStream) throws IOException {
        try {
            RandomAccessReadBuffer randomAccessRead = new RandomAccessReadBuffer(inputStream);
            return randomAccessRead;
        } finally {
            inputStream.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void resetBuffers() {
        this.size = 0L;
        this.pointer = 0L;
        this.currentBuffer = this.bufferList.get(0);
        this.currentBuffer.position(0);
        this.currentBufferPointer = 0;
        this.bufferListIndex = 0;
        this.bufferListMaxIndex = 0;
        this.bufferList.clear();
        this.bufferList.add(this.currentBuffer);
    }
}
