package okio;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.internal._ZlibJvmKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: GzipSink.kt */
@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"��P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0004\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0011\u0010\t\u001a\u00060\nj\u0002`\u000bH\u0007¢\u0006\u0002\b\u0012J\b\u0010\u0013\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0018\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001d\u001a\u00020\u0011H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��R\u0012\u0010\u0006\u001a\u00060\u0007j\u0002`\bX\u0082\u0004¢\u0006\u0002\n��R\u0017\u0010\t\u001a\u00060\nj\u0002`\u000b8G¢\u0006\b\n��\u001a\u0004\b\t\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0002\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n��¨\u0006\u001e"}, d2 = {"Lokio/GzipSink;", "Lokio/Sink;", "sink", "(Lokio/Sink;)V", "closed", "", "crc", "Ljava/util/zip/CRC32;", "Lokio/internal/CRC32;", "deflater", "Ljava/util/zip/Deflater;", "Lokio/Deflater;", "()Ljava/util/zip/Deflater;", "deflaterSink", "Lokio/DeflaterSink;", "Lokio/RealBufferedSink;", "close", "", "-deprecated_deflater", "flush", "timeout", "Lokio/Timeout;", "updateCrc", "buffer", "Lokio/Buffer;", "byteCount", "", "write", "source", "writeFooter", "okio"})
@SourceDebugExtension({"SMAP\nGzipSink.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GzipSink.kt\nokio/GzipSink\n+ 2 RealBufferedSink.kt\nokio/RealBufferedSink\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,152:1\n51#2:153\n1#3:154\n86#4:155\n*S KotlinDebug\n*F\n+ 1 GzipSink.kt\nokio/GzipSink\n*L\n62#1:153\n130#1:155\n*E\n"})
/* loaded from: target.jar:okio/GzipSink.class */
public final class GzipSink implements Sink {

    @NotNull
    private final RealBufferedSink sink;

    @NotNull
    private final Deflater deflater;

    @NotNull
    private final DeflaterSink deflaterSink;
    private boolean closed;

    @NotNull
    private final CRC32 crc;

    public GzipSink(@NotNull Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        this.sink = new RealBufferedSink(sink);
        this.deflater = new Deflater(_ZlibJvmKt.getDEFAULT_COMPRESSION(), true);
        this.deflaterSink = new DeflaterSink((BufferedSink) this.sink, this.deflater);
        this.crc = new CRC32();
        RealBufferedSink this_$iv = this.sink;
        Buffer $this$_init__u24lambda_u240 = this_$iv.bufferField;
        $this$_init__u24lambda_u240.writeShort(8075);
        $this$_init__u24lambda_u240.writeByte(8);
        $this$_init__u24lambda_u240.writeByte(0);
        $this$_init__u24lambda_u240.writeInt(0);
        $this$_init__u24lambda_u240.writeByte(0);
        $this$_init__u24lambda_u240.writeByte(0);
    }

    @JvmName(name = "deflater")
    @NotNull
    public final Deflater deflater() {
        return this.deflater;
    }

    @Override // okio.Sink
    public void write(@NotNull Buffer source, long byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(byteCount >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + byteCount).toString());
        }
        if (byteCount == 0) {
            return;
        }
        updateCrc(source, byteCount);
        this.deflaterSink.write(source, byteCount);
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        this.deflaterSink.flush();
    }

    @Override // okio.Sink
    @NotNull
    public Timeout timeout() {
        return this.sink.timeout();
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        Throwable thrown = null;
        try {
            this.deflaterSink.finishDeflate$okio();
            writeFooter();
        } catch (Throwable e) {
            thrown = e;
        }
        try {
            this.deflater.end();
        } catch (Throwable e2) {
            if (thrown == null) {
                thrown = e2;
            }
        }
        try {
            this.sink.close();
        } catch (Throwable e3) {
            if (thrown == null) {
                thrown = e3;
            }
        }
        this.closed = true;
        Throwable th = thrown;
        if (th != null) {
            throw th;
        }
    }

    private final void writeFooter() {
        this.sink.writeIntLe((int) this.crc.getValue());
        this.sink.writeIntLe((int) this.deflater.getBytesRead());
    }

    private final void updateCrc(Buffer buffer, long byteCount) {
        Segment segment = buffer.head;
        Intrinsics.checkNotNull(segment);
        Segment head = segment;
        long remaining = byteCount;
        while (remaining > 0) {
            int b$iv = head.limit - head.pos;
            int segmentLength = (int) Math.min(remaining, b$iv);
            this.crc.update(head.data, head.pos, segmentLength);
            remaining -= segmentLength;
            Segment segment2 = head.next;
            Intrinsics.checkNotNull(segment2);
            head = segment2;
        }
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "deflater", imports = {}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_deflater")
    @NotNull
    /* renamed from: -deprecated_deflater, reason: not valid java name */
    public final Deflater m3835deprecated_deflater() {
        return this.deflater;
    }
}
