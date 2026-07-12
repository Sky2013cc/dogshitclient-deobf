package com.sun.tools.hat.internal.parser;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/* loaded from: target.jar:com/sun/tools/hat/internal/parser/MappedReadBuffer.class */
class MappedReadBuffer implements ReadBuffer {
    private MappedByteBuffer buf;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !MappedReadBuffer.class.desiredAssertionStatus();
    }

    MappedReadBuffer(MappedByteBuffer mappedByteBuffer) {
        this.buf = mappedByteBuffer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ReadBuffer create(RandomAccessFile randomAccessFile) throws IOException {
        FileChannel channel = randomAccessFile.getChannel();
        long size = channel.size();
        if (canUseFileMap() && size <= 2147483647L) {
            try {
                MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, size);
                channel.close();
                return new MappedReadBuffer(map);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("File mapping failed, will use direct read");
            }
        }
        return new FileReadBuffer(randomAccessFile);
    }

    private static boolean canUseFileMap() {
        String property = System.getProperty("jhat.disableFileMap");
        return property == null || property.equals(Constants.FALSE);
    }

    private void seek(long j) throws IOException {
        if (!$assertionsDisabled && j > 2147483647L) {
            throw new AssertionError("position overflow");
        }
        this.buf.position((int) j);
    }

    @Override // com.sun.tools.hat.internal.parser.ReadBuffer
    public synchronized void get(long j, byte[] bArr) throws IOException {
        seek(j);
        this.buf.get(bArr);
    }

    @Override // com.sun.tools.hat.internal.parser.ReadBuffer
    public synchronized char getChar(long j) throws IOException {
        seek(j);
        return this.buf.getChar();
    }

    @Override // com.sun.tools.hat.internal.parser.ReadBuffer
    public synchronized byte getByte(long j) throws IOException {
        seek(j);
        return this.buf.get();
    }

    @Override // com.sun.tools.hat.internal.parser.ReadBuffer
    public synchronized short getShort(long j) throws IOException {
        seek(j);
        return this.buf.getShort();
    }

    @Override // com.sun.tools.hat.internal.parser.ReadBuffer
    public synchronized int getInt(long j) throws IOException {
        seek(j);
        return this.buf.getInt();
    }

    @Override // com.sun.tools.hat.internal.parser.ReadBuffer
    public synchronized long getLong(long j) throws IOException {
        seek(j);
        return this.buf.getLong();
    }
}
