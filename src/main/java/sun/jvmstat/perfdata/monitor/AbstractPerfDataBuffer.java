package sun.jvmstat.perfdata.monitor;

import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.List;
import sun.jvmstat.monitor.Monitor;
import sun.jvmstat.monitor.MonitorException;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/AbstractPerfDataBuffer.class */
public abstract class AbstractPerfDataBuffer {
    protected PerfDataBufferImpl impl;

    public int getLocalVmId() {
        return this.impl.getLocalVmId();
    }

    public byte[] getBytes() {
        return this.impl.getBytes();
    }

    public int getCapacity() {
        return this.impl.getCapacity();
    }

    public Monitor findByName(String str) throws MonitorException {
        return this.impl.findByName(str);
    }

    public List<Monitor> findByPattern(String str) throws MonitorException {
        return this.impl.findByPattern(str);
    }

    public MonitorStatus getMonitorStatus() throws MonitorException {
        return this.impl.getMonitorStatus();
    }

    public ByteBuffer getByteBuffer() {
        return this.impl.getByteBuffer();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void createPerfDataBuffer(ByteBuffer byteBuffer, int i) throws MonitorException {
        String str = "sun.jvmstat.perfdata.monitor.v" + AbstractPerfDataBufferPrologue.getMajorVersion(byteBuffer) + "_" + AbstractPerfDataBufferPrologue.getMinorVersion(byteBuffer) + ".PerfDataBuffer";
        try {
            this.impl = (PerfDataBufferImpl) Class.forName(str).getConstructor(Class.forName("java.nio.ByteBuffer"), Integer.TYPE).newInstance(byteBuffer, new Integer(i));
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Could not find " + str + ": " + e.getMessage(), e);
        } catch (IllegalAccessException e2) {
            throw new IllegalArgumentException("Unexpected constructor access in " + str + ": " + e2.getMessage(), e2);
        } catch (InstantiationException e3) {
            throw new IllegalArgumentException(str + "is abstract: " + e3.getMessage(), e3);
        } catch (NoSuchMethodException e4) {
            throw new IllegalArgumentException("Expected constructor missing in " + str + ": " + e4.getMessage(), e4);
        } catch (InvocationTargetException e5) {
            Throwable cause = e5.getCause();
            if (cause instanceof MonitorException) {
                throw ((MonitorException) cause);
            }
            throw new RuntimeException("Unexpected exception: " + e5.getMessage(), e5);
        }
    }
}
