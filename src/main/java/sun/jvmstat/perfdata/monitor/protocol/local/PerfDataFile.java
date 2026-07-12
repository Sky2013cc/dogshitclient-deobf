package sun.jvmstat.perfdata.monitor.protocol.local;

import java.io.File;
import java.io.FilenameFilter;
import sun.misc.VMSupport;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/protocol/local/PerfDataFile.class */
public class PerfDataFile {
    public static final String tmpDirName;
    public static final String dirNamePrefix = "hsperfdata_";
    public static final String userDirNamePattern = "hsperfdata_\\S*";
    public static final String fileNamePattern = "^[0-9]+$";
    public static final String tmpFileNamePattern = "^hsperfdata_[0-9]+(_[1-2]+)?$";

    private PerfDataFile() {
    }

    public static File getFile(int i) {
        File file;
        if (i == 0) {
            return null;
        }
        long j = 0;
        File file2 = null;
        for (String str : new File(tmpDirName).list(new FilenameFilter() { // from class: sun.jvmstat.perfdata.monitor.protocol.local.PerfDataFile.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file3, String str2) {
                if (!str2.startsWith(PerfDataFile.dirNamePrefix)) {
                    return false;
                }
                File file4 = new File(file3, str2);
                return (file4.isDirectory() || file4.isFile()) && file4.canRead();
            }
        })) {
            File file3 = new File(tmpDirName + str);
            if (file3.exists() && file3.isDirectory()) {
                file = new File(file3.getName(), Integer.toString(i));
            } else if (file3.exists() && file3.isFile()) {
                file = file3;
            } else {
                file = file3;
            }
            if (file.exists() && file.isFile() && file.canRead()) {
                long lastModified = file.lastModified();
                if (lastModified >= j) {
                    j = lastModified;
                    file2 = file;
                }
            }
        }
        return file2;
    }

    public static File getFile(String str, int i) {
        String str2;
        if (i == 0) {
            return null;
        }
        File file = new File(getTempDirectory(str) + Integer.toString(i));
        if (file.exists() && file.isFile() && file.canRead()) {
            return file;
        }
        long j = 0;
        File file2 = null;
        for (int i2 = 0; i2 < 2; i2++) {
            if (i2 == 0) {
                str2 = getTempDirectory() + Integer.toString(i);
            } else {
                str2 = getTempDirectory() + Integer.toString(i) + Integer.toString(i2);
            }
            File file3 = new File(str2);
            if (file3.exists() && file3.isFile() && file3.canRead()) {
                long lastModified = file3.lastModified();
                if (lastModified >= j) {
                    j = lastModified;
                    file2 = file3;
                }
            }
        }
        return file2;
    }

    public static int getLocalVmId(File file) {
        try {
            return Integer.parseInt(file.getName());
        } catch (NumberFormatException e) {
            String name = file.getName();
            if (name.startsWith(dirNamePrefix)) {
                int indexOf = name.indexOf(95);
                int lastIndexOf = name.lastIndexOf(95);
                try {
                    if (indexOf == lastIndexOf) {
                        return Integer.parseInt(name.substring(indexOf + 1));
                    }
                    return Integer.parseInt(name.substring(indexOf + 1, lastIndexOf));
                } catch (NumberFormatException e2) {
                    throw new IllegalArgumentException("file name does not match pattern");
                }
            }
            throw new IllegalArgumentException("file name does not match pattern");
        }
    }

    public static String getTempDirectory() {
        return tmpDirName;
    }

    public static String getTempDirectory(String str) {
        return tmpDirName + dirNamePrefix + str + File.separator;
    }

    static {
        String vMTemporaryDirectory = VMSupport.getVMTemporaryDirectory();
        if (vMTemporaryDirectory.lastIndexOf(File.separator) != vMTemporaryDirectory.length() - 1) {
            vMTemporaryDirectory = vMTemporaryDirectory + File.separator;
        }
        tmpDirName = vMTemporaryDirectory;
    }
}
