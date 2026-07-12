package sun.jvmstat.perfdata.monitor;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import sun.jvmstat.monitor.MonitorException;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/AbstractPerfDataBufferPrologue.class */
public abstract class AbstractPerfDataBufferPrologue {
    protected ByteBuffer byteBuffer;
    static final int PERFDATA_PROLOG_OFFSET = 0;
    static final int PERFDATA_PROLOG_MAGIC_OFFSET = 0;
    static final int PERFDATA_PROLOG_BYTEORDER_OFFSET = 4;
    static final int PERFDATA_PROLOG_BYTEORDER_SIZE = 1;
    static final int PERFDATA_PROLOG_MAJOR_OFFSET = 5;
    static final int PERFDATA_PROLOG_MAJOR_SIZE = 1;
    static final int PERFDATA_PROLOG_MINOR_OFFSET = 6;
    static final int PERFDATA_PROLOG_MINOR_SIZE = 1;
    static final int PERFDATA_PROLOG_RESERVEDB1_OFFSET = 7;
    static final int PERFDATA_PROLOG_RESERVEDB1_SIZE = 1;
    static final int PERFDATA_PROLOG_SIZE = 8;
    static final byte PERFDATA_BIG_ENDIAN = 0;
    static final byte PERFDATA_LITTLE_ENDIAN = 1;
    static final int PERFDATA_MAGIC = -889274176;
    public static final String PERFDATA_MAJOR_NAME = "sun.perfdata.majorVersion";
    public static final String PERFDATA_MINOR_NAME = "sun.perfdata.minorVersion";

    public abstract boolean isAccessible();

    public abstract boolean supportsAccessible();

    public AbstractPerfDataBufferPrologue(ByteBuffer byteBuffer) throws MonitorException {
        this.byteBuffer = byteBuffer.duplicate();
        if (getMagic() != PERFDATA_MAGIC) {
            throw new MonitorVersionException("Bad Magic: " + Integer.toHexString(getMagic()));
        }
        this.byteBuffer.order(getByteOrder());
    }

    public int getMagic() {
        ByteOrder order = this.byteBuffer.order();
        this.byteBuffer.order(ByteOrder.BIG_ENDIAN);
        this.byteBuffer.position(0);
        int i = this.byteBuffer.getInt();
        this.byteBuffer.order(order);
        return i;
    }

    public ByteOrder getByteOrder() {
        this.byteBuffer.position(4);
        if (this.byteBuffer.get() == 0) {
            return ByteOrder.BIG_ENDIAN;
        }
        return ByteOrder.LITTLE_ENDIAN;
    }

    public int getMajorVersion() {
        this.byteBuffer.position(5);
        return this.byteBuffer.get();
    }

    public int getMinorVersion() {
        this.byteBuffer.position(6);
        return this.byteBuffer.get();
    }

    public int getSize() {
        return 8;
    }

    public IntBuffer majorVersionBuffer() {
        IntBuffer wrap = IntBuffer.wrap(new int[]{getMajorVersion()});
        wrap.limit(1);
        return wrap;
    }

    public IntBuffer minorVersionBuffer() {
        IntBuffer wrap = IntBuffer.wrap(new int[]{getMinorVersion()});
        wrap.limit(1);
        return wrap;
    }

    public static int getMagic(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        ByteOrder order = byteBuffer.order();
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        byteBuffer.position(0);
        int i = byteBuffer.getInt();
        byteBuffer.order(order);
        byteBuffer.position(position);
        return i;
    }

    public static int getMajorVersion(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        byteBuffer.position(5);
        byte b = byteBuffer.get();
        byteBuffer.position(position);
        return b;
    }

    public static int getMinorVersion(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        byteBuffer.position(6);
        byte b = byteBuffer.get();
        byteBuffer.position(position);
        return b;
    }

    public static ByteOrder getByteOrder(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        byteBuffer.position(4);
        ByteOrder byteOrder = byteBuffer.get() == 0 ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
        byteBuffer.position(position);
        return byteOrder;
    }
}
