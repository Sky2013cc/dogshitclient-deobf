package sun.jvmstat.perfdata.monitor.protocol.local;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.VmIdentifier;
import sun.jvmstat.perfdata.monitor.AbstractPerfDataBuffer;
import sun.misc.Perf;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/protocol/local/PerfDataBuffer.class */
public class PerfDataBuffer extends AbstractPerfDataBuffer {
    private static final Perf perf = (Perf) AccessController.doPrivileged((PrivilegedAction) new Perf.GetPerfAction());

    public PerfDataBuffer(VmIdentifier vmIdentifier) throws MonitorException {
        try {
            createPerfDataBuffer(perf.attach(vmIdentifier.getLocalVmId(), vmIdentifier.getMode()), vmIdentifier.getLocalVmId());
        } catch (IOException e) {
            throw new MonitorException("Could not attach to " + vmIdentifier.getLocalVmId(), e);
        } catch (IllegalArgumentException e2) {
            try {
                FileChannel channel = new RandomAccessFile(new File(PerfDataFile.getTempDirectory() + PerfDataFile.dirNamePrefix + Integer.toString(vmIdentifier.getLocalVmId())), PDPageLabelRange.STYLE_ROMAN_LOWER).getChannel();
                MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, (int) channel.size());
                channel.close();
                createPerfDataBuffer(map, vmIdentifier.getLocalVmId());
            } catch (FileNotFoundException e3) {
                throw new MonitorException(vmIdentifier.getLocalVmId() + " not found", e2);
            } catch (IOException e4) {
                throw new MonitorException("Could not map 1.4.1 file for " + vmIdentifier.getLocalVmId(), e4);
            }
        }
    }
}
