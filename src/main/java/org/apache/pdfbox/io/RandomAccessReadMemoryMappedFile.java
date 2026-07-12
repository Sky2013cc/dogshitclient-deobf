package org.apache.pdfbox.io;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.EnumSet;
import java.util.Optional;
import java.util.function.Consumer;

/* loaded from: target.jar:org/apache/pdfbox/io/RandomAccessReadMemoryMappedFile.class */
public class RandomAccessReadMemoryMappedFile implements RandomAccessRead {
    private ByteBuffer mappedByteBuffer;
    private final long size;
    private final FileChannel fileChannel;
    private final Consumer<? super ByteBuffer> unmapper;

    public RandomAccessReadMemoryMappedFile(String filename) throws IOException {
        this(new File(filename));
    }

    public RandomAccessReadMemoryMappedFile(File file) throws IOException {
        this(file.toPath());
    }

    public RandomAccessReadMemoryMappedFile(Path path) throws IOException {
        this.fileChannel = FileChannel.open(path, EnumSet.of(StandardOpenOption.READ), new FileAttribute[0]);
        this.size = this.fileChannel.size();
        if (this.size > 2147483647L) {
            throw new IOException(getClass().getName() + " doesn't yet support files bigger than 2147483647");
        }
        this.mappedByteBuffer = this.fileChannel.map(FileChannel.MapMode.READ_ONLY, 0L, this.size);
        this.unmapper = IOUtils::unmap;
    }

    private RandomAccessReadMemoryMappedFile(RandomAccessReadMemoryMappedFile parent) {
        this.mappedByteBuffer = parent.mappedByteBuffer.duplicate();
        this.size = parent.size;
        this.mappedByteBuffer.rewind();
        this.unmapper = null;
        this.fileChannel = null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.fileChannel != null) {
            this.fileChannel.close();
        }
        if (this.mappedByteBuffer != null) {
            Optional.ofNullable(this.unmapper).ifPresent(u -> {
                u.accept(this.mappedByteBuffer);
            });
            this.mappedByteBuffer = null;
        }
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public void seek(long position) throws IOException {
        checkClosed();
        if (position < 0) {
            throw new IOException("Invalid position " + position);
        }
        this.mappedByteBuffer.position((int) Math.min(position, this.size));
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public long getPosition() throws IOException {
        checkClosed();
        return this.mappedByteBuffer.position();
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public int read() throws IOException {
        if (isEOF()) {
            return -1;
        }
        return this.mappedByteBuffer.get() & 255;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public int read(byte[] b, int offset, int length) throws IOException {
        if (isEOF()) {
            return -1;
        }
        int remainingBytes = Math.min(((int) this.size) - this.mappedByteBuffer.position(), length);
        this.mappedByteBuffer.get(b, offset, remainingBytes);
        return remainingBytes;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public long length() throws IOException {
        checkClosed();
        return this.size;
    }

    private void checkClosed() throws IOException {
        if (isClosed()) {
            throw new IOException(getClass().getSimpleName() + " already closed");
        }
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public boolean isClosed() {
        return this.mappedByteBuffer == null;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public boolean isEOF() throws IOException {
        checkClosed();
        return ((long) this.mappedByteBuffer.position()) >= this.size;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public RandomAccessReadView createView(long startPosition, long streamLength) {
        return new RandomAccessReadView(new RandomAccessReadMemoryMappedFile(this), startPosition, streamLength, true);
    }
}
