package sun.jvmstat.perfdata.monitor.protocol.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.VmIdentifier;
import sun.jvmstat.perfdata.monitor.AbstractPerfDataBuffer;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/protocol/file/PerfDataBuffer.class */
public class PerfDataBuffer extends AbstractPerfDataBuffer {
    public PerfDataBuffer(VmIdentifier vmIdentifier) throws MonitorException {
        MappedByteBuffer map;
        File file = new File(vmIdentifier.getURI());
        String mode = vmIdentifier.getMode();
        try {
            FileChannel channel = new RandomAccessFile(file, mode).getChannel();
            if (mode.compareTo(PDPageLabelRange.STYLE_ROMAN_LOWER) == 0) {
                map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, (int) channel.size());
            } else if (mode.compareTo("rw") == 0) {
                map = channel.map(FileChannel.MapMode.READ_WRITE, 0L, (int) channel.size());
            } else {
                throw new IllegalArgumentException("Invalid mode: " + mode);
            }
            channel.close();
            createPerfDataBuffer(map, 0);
        } catch (FileNotFoundException e) {
            throw new MonitorException("Could not find " + vmIdentifier.toString());
        } catch (IOException e2) {
            throw new MonitorException("Could not read " + vmIdentifier.toString());
        }
    }
}
