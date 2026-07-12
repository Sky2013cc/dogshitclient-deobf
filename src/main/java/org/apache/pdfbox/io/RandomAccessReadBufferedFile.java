package org.apache.pdfbox.io;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: target.jar:org/apache/pdfbox/io/RandomAccessReadBufferedFile.class */
public class RandomAccessReadBufferedFile implements RandomAccessRead {
    private static final int PAGE_SIZE_SHIFT = 12;
    private static final int PAGE_SIZE = 4096;
    private static final long PAGE_OFFSET_MASK = -4096;
    private static final int MAX_CACHED_PAGES = 1000;
    private final ConcurrentMap<Long, RandomAccessReadBufferedFile> rafCopies;
    private ByteBuffer lastRemovedCachePage;
    private final Map<Long, ByteBuffer> pageCache;
    private long curPageOffset;
    private ByteBuffer curPage;
    private int offsetWithinPage;
    private final FileChannel fileChannel;
    private final Path path;
    private final long fileLength;
    private long fileOffset;
    private boolean isClosed;

    public RandomAccessReadBufferedFile(String filename) throws IOException {
        this(new File(filename));
    }

    public RandomAccessReadBufferedFile(File file) throws IOException {
        this(file.toPath());
    }

    public RandomAccessReadBufferedFile(Path path) throws IOException {
        this.rafCopies = new ConcurrentHashMap();
        this.lastRemovedCachePage = null;
        this.pageCache = new LinkedHashMap<Long, ByteBuffer>(1000, 0.75f, true) { // from class: org.apache.pdfbox.io.RandomAccessReadBufferedFile.1
            private static final long serialVersionUID = -6302488539257741101L;

            @Override // java.util.LinkedHashMap
            protected boolean removeEldestEntry(Map.Entry<Long, ByteBuffer> eldest) {
                boolean doRemove = size() > 1000;
                if (doRemove) {
                    RandomAccessReadBufferedFile.this.lastRemovedCachePage = eldest.getValue();
                    RandomAccessReadBufferedFile.this.lastRemovedCachePage.clear();
                }
                return doRemove;
            }
        };
        this.curPageOffset = -1L;
        this.offsetWithinPage = 0;
        this.fileOffset = 0L;
        this.path = path;
        this.fileChannel = FileChannel.open(path, StandardOpenOption.READ);
        this.fileLength = this.fileChannel.size();
        seek(0L);
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public long getPosition() throws IOException {
        checkClosed();
        return this.fileOffset;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public void seek(long position) throws IOException {
        checkClosed();
        if (position < 0) {
            throw new IOException("Invalid position " + position);
        }
        long newPageOffset = position & PAGE_OFFSET_MASK;
        if (newPageOffset != this.curPageOffset) {
            ByteBuffer newPage = this.pageCache.get(Long.valueOf(newPageOffset));
            if (newPage == null) {
                this.fileChannel.position(newPageOffset);
                newPage = readPage();
                this.pageCache.put(Long.valueOf(newPageOffset), newPage);
            }
            this.curPageOffset = newPageOffset;
            this.curPage = newPage;
        }
        this.fileOffset = Math.min(position, this.fileLength);
        this.offsetWithinPage = (int) (this.fileOffset - this.curPageOffset);
    }

    private ByteBuffer readPage() throws IOException {
        ByteBuffer page;
        int curBytesRead;
        if (this.lastRemovedCachePage != null) {
            page = this.lastRemovedCachePage;
            this.lastRemovedCachePage = null;
        } else {
            page = ByteBuffer.allocate(4096);
        }
        int i = 0;
        while (true) {
            int readBytes = i;
            if (readBytes >= 4096 || (curBytesRead = this.fileChannel.read(page)) < 0) {
                break;
            }
            i = readBytes + curBytesRead;
        }
        return page;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public int read() throws IOException {
        checkClosed();
        if (this.fileOffset >= this.fileLength) {
            return -1;
        }
        if (this.offsetWithinPage == 4096) {
            seek(this.fileOffset);
        }
        this.fileOffset++;
        ByteBuffer byteBuffer = this.curPage;
        int i = this.offsetWithinPage;
        this.offsetWithinPage = i + 1;
        return byteBuffer.get(i) & 255;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public int read(byte[] b, int off, int len) throws IOException {
        checkClosed();
        if (this.fileOffset >= this.fileLength) {
            return -1;
        }
        if (this.offsetWithinPage == 4096) {
            seek(this.fileOffset);
        }
        int commonLen = Math.min(4096 - this.offsetWithinPage, len);
        if (this.fileLength - this.fileOffset < 4096) {
            commonLen = Math.min(commonLen, (int) (this.fileLength - this.fileOffset));
        }
        this.curPage.position(this.offsetWithinPage);
        this.curPage.get(b, off, commonLen);
        this.offsetWithinPage += commonLen;
        this.fileOffset += commonLen;
        return commonLen;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public long length() throws IOException {
        return this.fileLength;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.rafCopies.values().forEach((v0) -> {
            IOUtils.closeQuietly(v0);
        });
        this.rafCopies.clear();
        this.fileChannel.close();
        this.pageCache.clear();
        this.isClosed = true;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public boolean isClosed() {
        return this.isClosed;
    }

    private void checkClosed() throws IOException {
        if (this.isClosed) {
            throw new IOException(getClass().getName() + " already closed");
        }
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public boolean isEOF() throws IOException {
        return peek() == -1;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public RandomAccessReadView createView(long startPosition, long streamLength) throws IOException {
        checkClosed();
        Long currentThreadID = Long.valueOf(Thread.currentThread().getId());
        RandomAccessReadBufferedFile randomAccessReadBufferedFile = this.rafCopies.get(currentThreadID);
        if (randomAccessReadBufferedFile == null || randomAccessReadBufferedFile.isClosed()) {
            randomAccessReadBufferedFile = new RandomAccessReadBufferedFile(this.path);
            this.rafCopies.put(currentThreadID, randomAccessReadBufferedFile);
        }
        return new RandomAccessReadView(randomAccessReadBufferedFile, startPosition, streamLength);
    }
}
