package org.apache.pdfbox.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* loaded from: target.jar:org/apache/pdfbox/io/ScratchFile.class */
public class ScratchFile implements RandomAccessStreamCache {
    private static final Log LOG = LogFactory.getLog(ScratchFile.class);
    private static final int ENLARGE_PAGE_COUNT = 16;
    private static final int INIT_UNRESTRICTED_MAINMEM_PAGECOUNT = 100000;
    private static final int PAGE_SIZE = 4096;
    private final Object ioLock;
    private final File scratchFileDirectory;
    private File file;
    private RandomAccessFile raf;
    private volatile int pageCount;
    private final BitSet freePages;
    private volatile byte[][] inMemoryPages;
    private final int inMemoryMaxPageCount;
    private final int maxPageCount;
    private final boolean useScratchFile;
    private final boolean maxMainMemoryIsRestricted;
    private final List<ScratchFileBuffer> buffers;
    private volatile boolean isClosed;

    public ScratchFile(File scratchFileDirectory) throws IOException {
        this(MemoryUsageSetting.setupTempFileOnly().setTempDir(scratchFileDirectory));
    }

    public ScratchFile(MemoryUsageSetting memUsageSetting) throws IOException {
        int i;
        this.ioLock = new Object();
        this.pageCount = 0;
        this.freePages = new BitSet();
        this.buffers = new ArrayList();
        this.isClosed = false;
        this.maxMainMemoryIsRestricted = !memUsageSetting.useMainMemory() || memUsageSetting.isMainMemoryRestricted();
        this.useScratchFile = this.maxMainMemoryIsRestricted && memUsageSetting.useTempFile();
        this.scratchFileDirectory = this.useScratchFile ? memUsageSetting.getTempDir() : null;
        if (this.scratchFileDirectory != null && !this.scratchFileDirectory.isDirectory()) {
            throw new IOException("Scratch file directory does not exist: " + this.scratchFileDirectory);
        }
        this.maxPageCount = memUsageSetting.isStorageRestricted() ? (int) Math.min(2147483647L, memUsageSetting.getMaxStorageBytes() / 4096) : Integer.MAX_VALUE;
        if (memUsageSetting.useMainMemory()) {
            i = memUsageSetting.isMainMemoryRestricted() ? (int) Math.min(2147483647L, memUsageSetting.getMaxMainMemoryBytes() / 4096) : Integer.MAX_VALUE;
        } else {
            i = 0;
        }
        this.inMemoryMaxPageCount = i;
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [byte[], byte[][]] */
    private void initPages() {
        if (this.inMemoryPages == null) {
            this.inMemoryPages = new byte[this.maxMainMemoryIsRestricted ? this.inMemoryMaxPageCount : 100000];
            this.freePages.set(0, this.inMemoryPages.length);
        }
    }

    public static ScratchFile getMainMemoryOnlyInstance() {
        try {
            return new ScratchFile(MemoryUsageSetting.setupMainMemoryOnly());
        } catch (IOException ioe) {
            LOG.error("Unexpected exception occurred creating main memory scratch file instance: " + ioe.getMessage(), ioe);
            return null;
        }
    }

    public static ScratchFile getMainMemoryOnlyInstance(long maxMainMemoryBytes) {
        try {
            return new ScratchFile(MemoryUsageSetting.setupMainMemoryOnly(maxMainMemoryBytes));
        } catch (IOException ioe) {
            LOG.error("Unexpected exception occurred creating main memory scratch file instance: " + ioe.getMessage(), ioe);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getNewPage() throws IOException {
        int i;
        synchronized (this.freePages) {
            initPages();
            int idx = this.freePages.nextSetBit(0);
            if (idx < 0) {
                enlarge();
                idx = this.freePages.nextSetBit(0);
                if (idx < 0) {
                    throw new IOException("Maximum allowed scratch file memory exceeded.");
                }
            }
            this.freePages.clear(idx);
            if (idx >= this.pageCount) {
                this.pageCount = idx + 1;
            }
            i = idx;
        }
        return i;
    }

    /* JADX WARN: Type inference failed for: r0v21, types: [byte[], byte[][], java.lang.Object] */
    private void enlarge() throws IOException {
        synchronized (this.ioLock) {
            checkClosed();
            if (this.pageCount >= this.maxPageCount) {
                return;
            }
            if (this.useScratchFile) {
                if (this.raf == null) {
                    this.file = File.createTempFile("PDFBox", ".tmp", this.scratchFileDirectory);
                    try {
                        this.raf = new RandomAccessFile(this.file, "rw");
                    } catch (IOException e) {
                        if (!this.file.delete()) {
                            LOG.warn("Error deleting scratch file: " + this.file.getAbsolutePath());
                        }
                        throw e;
                    }
                }
                long fileLen = this.raf.length();
                long expectedFileLen = (this.pageCount - this.inMemoryMaxPageCount) * 4096;
                if (expectedFileLen != fileLen) {
                    throw new IOException("Expected scratch file size of " + expectedFileLen + " but found " + fileLen);
                }
                if (this.pageCount + 16 > this.pageCount) {
                    this.raf.setLength(fileLen + 65536);
                    this.freePages.set(this.pageCount, this.pageCount + 16);
                }
            } else if (!this.maxMainMemoryIsRestricted) {
                int oldSize = this.inMemoryPages.length;
                int newSize = (int) Math.min(oldSize * 2, 2147483647L);
                if (newSize > oldSize) {
                    ?? r0 = new byte[newSize];
                    System.arraycopy(this.inMemoryPages, 0, r0, 0, oldSize);
                    this.inMemoryPages = r0;
                    this.freePages.set(oldSize, newSize);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getPageSize() {
        return 4096;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] readPage(int pageIdx) throws IOException {
        byte[] page;
        if (pageIdx < 0 || pageIdx >= this.pageCount) {
            checkClosed();
            throw new IOException("Page index out of range: " + pageIdx + ". Max value: " + (this.pageCount - 1));
        }
        if (pageIdx < this.inMemoryMaxPageCount) {
            byte[] page2 = this.inMemoryPages[pageIdx];
            if (page2 == null) {
                checkClosed();
                throw new IOException("Requested page with index " + pageIdx + " was not written before.");
            }
            return page2;
        }
        synchronized (this.ioLock) {
            if (this.raf == null) {
                checkClosed();
                throw new IOException("Missing scratch file to read page with index " + pageIdx + " from.");
            }
            page = new byte[4096];
            this.raf.seek((pageIdx - this.inMemoryMaxPageCount) * 4096);
            this.raf.readFully(page);
        }
        return page;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writePage(int pageIdx, byte[] page) throws IOException {
        if (pageIdx < 0 || pageIdx >= this.pageCount) {
            checkClosed();
            throw new IOException("Page index out of range: " + pageIdx + ". Max value: " + (this.pageCount - 1));
        }
        if (page.length != 4096) {
            throw new IOException("Wrong page size to write: " + page.length + ". Expected: 4096");
        }
        if (pageIdx < this.inMemoryMaxPageCount) {
            if (this.maxMainMemoryIsRestricted) {
                this.inMemoryPages[pageIdx] = page;
            } else {
                synchronized (this.ioLock) {
                    this.inMemoryPages[pageIdx] = page;
                }
            }
            checkClosed();
            return;
        }
        synchronized (this.ioLock) {
            checkClosed();
            this.raf.seek((pageIdx - this.inMemoryMaxPageCount) * 4096);
            this.raf.write(page);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void checkClosed() throws IOException {
        if (this.isClosed) {
            throw new IOException("Scratch file already closed");
        }
    }

    @Override // org.apache.pdfbox.io.RandomAccessStreamCache
    public RandomAccess createBuffer() throws IOException {
        ScratchFileBuffer newBuffer = new ScratchFileBuffer(this);
        synchronized (this.buffers) {
            this.buffers.add(newBuffer);
        }
        return newBuffer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeBuffer(ScratchFileBuffer buffer) {
        synchronized (this.buffers) {
            this.buffers.remove(buffer);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void markPagesAsFree(int[] pageIndexes, int off, int count) {
        synchronized (this.freePages) {
            for (int aIdx = off; aIdx < count; aIdx++) {
                int pageIdx = pageIndexes[aIdx];
                if (pageIdx >= 0 && pageIdx < this.pageCount && !this.freePages.get(pageIdx)) {
                    this.freePages.set(pageIdx);
                    if (pageIdx < this.inMemoryMaxPageCount) {
                        this.inMemoryPages[pageIdx] = null;
                    }
                }
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        IOException ioexc = null;
        synchronized (this.ioLock) {
            if (this.isClosed) {
                return;
            }
            this.isClosed = true;
            for (ScratchFileBuffer buffer : this.buffers) {
                if (buffer != null && !buffer.isClosed()) {
                    buffer.close(false);
                }
            }
            this.buffers.clear();
            if (this.raf != null) {
                try {
                    this.raf.close();
                } catch (IOException ioe) {
                    ioexc = ioe;
                }
            }
            if (this.file != null && !this.file.delete() && this.file.exists() && ioexc == null) {
                ioexc = new IOException("Error deleting scratch file: " + this.file.getAbsolutePath());
            }
            synchronized (this.freePages) {
                this.freePages.clear();
                this.pageCount = 0;
            }
            if (ioexc != null) {
                throw ioexc;
            }
        }
    }
}
