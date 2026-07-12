package org.apache.pdfbox.io;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/* loaded from: target.jar:org/apache/pdfbox/io/SequenceRandomAccessRead.class */
public class SequenceRandomAccessRead implements RandomAccessRead {
    private final List<RandomAccessRead> readerList;
    private final long[] startPositions;
    private final long[] endPositions;
    private final int numberOfReader;
    private int currentIndex = 0;
    private long currentPosition = 0;
    private long totalLength = 0;
    private boolean isClosed = false;
    private RandomAccessRead currentRandomAccessRead;

    public SequenceRandomAccessRead(List<RandomAccessRead> randomAccessReadList) {
        this.currentRandomAccessRead = null;
        if (randomAccessReadList == null) {
            throw new IllegalArgumentException("Missing input parameter");
        }
        if (randomAccessReadList.isEmpty()) {
            throw new IllegalArgumentException("Empty list");
        }
        this.readerList = (List) randomAccessReadList.stream().filter(r -> {
            try {
                return r.length() > 0;
            } catch (IOException e) {
                throw new IllegalArgumentException("Problematic list", e);
            }
        }).collect(Collectors.toList());
        this.currentRandomAccessRead = this.readerList.get(this.currentIndex);
        this.numberOfReader = this.readerList.size();
        this.startPositions = new long[this.numberOfReader];
        this.endPositions = new long[this.numberOfReader];
        for (int i = 0; i < this.numberOfReader; i++) {
            try {
                this.startPositions[i] = this.totalLength;
                this.totalLength += this.readerList.get(i).length();
                this.endPositions[i] = this.totalLength - 1;
            } catch (IOException e) {
                throw new IllegalArgumentException("Problematic list", e);
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        for (RandomAccessRead randomAccessRead : this.readerList) {
            randomAccessRead.close();
        }
        this.readerList.clear();
        this.currentRandomAccessRead = null;
        this.isClosed = true;
    }

    private RandomAccessRead getCurrentReader() throws IOException {
        if (this.currentRandomAccessRead.isEOF() && this.currentIndex < this.numberOfReader - 1) {
            this.currentIndex++;
            this.currentRandomAccessRead = this.readerList.get(this.currentIndex);
            this.currentRandomAccessRead.seek(0L);
        }
        return this.currentRandomAccessRead;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public int read() throws IOException {
        checkClosed();
        RandomAccessRead randomAccessRead = getCurrentReader();
        int value = randomAccessRead.read();
        if (value > -1) {
            this.currentPosition++;
        }
        return value;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public int read(byte[] b, int offset, int length) throws IOException {
        int bytesRead;
        checkClosed();
        int maxAvailBytes = Math.min(available(), length);
        if (maxAvailBytes == 0) {
            return -1;
        }
        RandomAccessRead randomAccessRead = getCurrentReader();
        int read = randomAccessRead.read(b, offset, maxAvailBytes);
        while (true) {
            bytesRead = read;
            if (bytesRead <= -1 || bytesRead >= maxAvailBytes) {
                break;
            }
            RandomAccessRead randomAccessRead2 = getCurrentReader();
            read = bytesRead + randomAccessRead2.read(b, offset + bytesRead, maxAvailBytes - bytesRead);
        }
        this.currentPosition += bytesRead;
        return bytesRead;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public long getPosition() throws IOException {
        checkClosed();
        return this.currentPosition;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public void seek(long position) throws IOException {
        checkClosed();
        if (position < 0) {
            throw new IOException("Invalid position " + position);
        }
        if (position >= this.totalLength) {
            this.currentIndex = this.numberOfReader - 1;
            this.currentPosition = this.totalLength;
        } else {
            int i = 0;
            while (true) {
                if (i < this.numberOfReader) {
                    if (position < this.startPositions[i] || position > this.endPositions[i]) {
                        i++;
                    } else {
                        this.currentIndex = i;
                        break;
                    }
                } else {
                    break;
                }
            }
            this.currentPosition = position;
        }
        this.currentRandomAccessRead = this.readerList.get(this.currentIndex);
        this.currentRandomAccessRead.seek(this.currentPosition - this.startPositions[this.currentIndex]);
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public long length() throws IOException {
        checkClosed();
        return this.totalLength;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public boolean isClosed() {
        return this.isClosed;
    }

    private void checkClosed() throws IOException {
        if (this.isClosed) {
            throw new IOException("RandomAccessBuffer already closed");
        }
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public boolean isEOF() throws IOException {
        checkClosed();
        return this.currentPosition >= this.totalLength;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public RandomAccessReadView createView(long startPosition, long streamLength) throws IOException {
        throw new UnsupportedOperationException(getClass().getName() + ".createView isn't supported.");
    }
}
