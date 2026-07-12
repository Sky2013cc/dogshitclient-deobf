package sun.jvmstat.perfdata.monitor.v1_0;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.perfdata.monitor.AbstractPerfDataBufferPrologue;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/v1_0/PerfDataBufferPrologue.class */
public class PerfDataBufferPrologue extends AbstractPerfDataBufferPrologue {
    private static final int SUPPORTED_MAJOR_VERSION = 1;
    private static final int SUPPORTED_MINOR_VERSION = 0;
    static final int PERFDATA_PROLOG_USED_OFFSET = 8;
    static final int PERFDATA_PROLOG_USED_SIZE = 4;
    static final int PERFDATA_PROLOG_OVERFLOW_OFFSET = 12;
    static final int PERFDATA_PROLOG_OVERFLOW_SIZE = 4;
    static final int PERFDATA_PROLOG_MODTIMESTAMP_OFFSET = 16;
    static final int PERFDATA_PROLOG_MODTIMESTAMP_SIZE = 8;
    static final int PERFDATA_PROLOG_SIZE = 24;
    static final String PERFDATA_BUFFER_SIZE_NAME = "sun.perfdata.size";
    static final String PERFDATA_BUFFER_USED_NAME = "sun.perfdata.used";
    static final String PERFDATA_OVERFLOW_NAME = "sun.perfdata.overflow";
    static final String PERFDATA_MODTIMESTAMP_NAME = "sun.perfdata.timestamp";
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !PerfDataBufferPrologue.class.desiredAssertionStatus();
    }

    public PerfDataBufferPrologue(ByteBuffer byteBuffer) throws MonitorException {
        super(byteBuffer);
        if ($assertionsDisabled) {
            return;
        }
        if (getMajorVersion() != 1 || getMinorVersion() != 0) {
            throw new AssertionError();
        }
    }

    @Override // sun.jvmstat.perfdata.monitor.AbstractPerfDataBufferPrologue
    public boolean supportsAccessible() {
        return false;
    }

    @Override // sun.jvmstat.perfdata.monitor.AbstractPerfDataBufferPrologue
    public boolean isAccessible() {
        return true;
    }

    public int getUsed() {
        this.byteBuffer.position(8);
        return this.byteBuffer.getInt();
    }

    public int getBufferSize() {
        return this.byteBuffer.capacity();
    }

    public int getOverflow() {
        this.byteBuffer.position(12);
        return this.byteBuffer.getInt();
    }

    public long getModificationTimeStamp() {
        this.byteBuffer.position(16);
        return this.byteBuffer.getLong();
    }

    @Override // sun.jvmstat.perfdata.monitor.AbstractPerfDataBufferPrologue
    public int getSize() {
        return 24;
    }

    public IntBuffer usedBuffer() {
        this.byteBuffer.position(8);
        IntBuffer asIntBuffer = this.byteBuffer.asIntBuffer();
        asIntBuffer.limit(1);
        return asIntBuffer;
    }

    public IntBuffer sizeBuffer() {
        IntBuffer allocate = IntBuffer.allocate(1);
        allocate.put(this.byteBuffer.capacity());
        return allocate;
    }

    public IntBuffer overflowBuffer() {
        this.byteBuffer.position(12);
        IntBuffer asIntBuffer = this.byteBuffer.asIntBuffer();
        asIntBuffer.limit(1);
        return asIntBuffer;
    }

    public LongBuffer modificationTimeStampBuffer() {
        this.byteBuffer.position(16);
        LongBuffer asLongBuffer = this.byteBuffer.asLongBuffer();
        asLongBuffer.limit(1);
        return asLongBuffer;
    }
}
