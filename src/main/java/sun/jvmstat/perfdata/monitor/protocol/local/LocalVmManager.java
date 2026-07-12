package sun.jvmstat.perfdata.monitor.protocol.local;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/protocol/local/LocalVmManager.class */
public class LocalVmManager {
    private String userName;
    private File tmpdir;
    private Pattern userPattern;
    private Matcher userMatcher;
    private FilenameFilter userFilter;
    private Pattern filePattern;
    private Matcher fileMatcher;
    private FilenameFilter fileFilter;
    private Pattern tmpFilePattern;
    private Matcher tmpFileMatcher;
    private FilenameFilter tmpFileFilter;

    public LocalVmManager() {
        this(null);
    }

    public LocalVmManager(String str) {
        this.userName = str;
        if (this.userName == null) {
            this.tmpdir = new File(PerfDataFile.getTempDirectory());
            this.userPattern = Pattern.compile(PerfDataFile.userDirNamePattern);
            this.userMatcher = this.userPattern.matcher("");
            this.userFilter = new FilenameFilter() { // from class: sun.jvmstat.perfdata.monitor.protocol.local.LocalVmManager.1
                @Override // java.io.FilenameFilter
                public boolean accept(File file, String str2) {
                    LocalVmManager.this.userMatcher.reset(str2);
                    return LocalVmManager.this.userMatcher.lookingAt();
                }
            };
        } else {
            this.tmpdir = new File(PerfDataFile.getTempDirectory(this.userName));
        }
        this.filePattern = Pattern.compile(PerfDataFile.fileNamePattern);
        this.fileMatcher = this.filePattern.matcher("");
        this.fileFilter = new FilenameFilter() { // from class: sun.jvmstat.perfdata.monitor.protocol.local.LocalVmManager.2
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str2) {
                LocalVmManager.this.fileMatcher.reset(str2);
                return LocalVmManager.this.fileMatcher.matches();
            }
        };
        this.tmpFilePattern = Pattern.compile(PerfDataFile.tmpFileNamePattern);
        this.tmpFileMatcher = this.tmpFilePattern.matcher("");
        this.tmpFileFilter = new FilenameFilter() { // from class: sun.jvmstat.perfdata.monitor.protocol.local.LocalVmManager.3
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str2) {
                LocalVmManager.this.tmpFileMatcher.reset(str2);
                return LocalVmManager.this.tmpFileMatcher.matches();
            }
        };
    }

    public synchronized Set<Integer> activeVms() {
        File[] listFiles;
        HashSet hashSet = new HashSet();
        if (!this.tmpdir.isDirectory()) {
            return hashSet;
        }
        if (this.userName == null) {
            File[] listFiles2 = this.tmpdir.listFiles(this.userFilter);
            for (int i = 0; i < listFiles2.length; i++) {
                if (listFiles2[i].isDirectory() && (listFiles = listFiles2[i].listFiles(this.fileFilter)) != null) {
                    for (int i2 = 0; i2 < listFiles.length; i2++) {
                        if (listFiles[i2].isFile() && listFiles[i2].canRead()) {
                            hashSet.add(new Integer(PerfDataFile.getLocalVmId(listFiles[i2])));
                        }
                    }
                }
            }
        } else {
            File[] listFiles3 = this.tmpdir.listFiles(this.fileFilter);
            if (listFiles3 != null) {
                for (int i3 = 0; i3 < listFiles3.length; i3++) {
                    if (listFiles3[i3].isFile() && listFiles3[i3].canRead()) {
                        hashSet.add(new Integer(PerfDataFile.getLocalVmId(listFiles3[i3])));
                    }
                }
            }
        }
        File[] listFiles4 = this.tmpdir.listFiles(this.tmpFileFilter);
        if (listFiles4 != null) {
            for (int i4 = 0; i4 < listFiles4.length; i4++) {
                if (listFiles4[i4].isFile() && listFiles4[i4].canRead()) {
                    hashSet.add(new Integer(PerfDataFile.getLocalVmId(listFiles4[i4])));
                }
            }
        }
        return hashSet;
    }
}
