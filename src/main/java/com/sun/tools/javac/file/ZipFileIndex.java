package com.sun.tools.javac.file;

import com.sun.tools.javac.file.RelativePath;
import com.sun.tools.javac.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.ZipException;
import jdk.internal.dynalink.CallSiteDescriptor;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;
import sun.tools.java.RuntimeConstants;

/*  JADX ERROR: NullPointerException in pass: ClassModifier
    java.lang.NullPointerException: Cannot invoke "java.util.List.forEach(java.util.function.Consumer)" because "blocks" is null
    	at jadx.core.utils.BlockUtils.collectAllInsns(BlockUtils.java:1017)
    	at jadx.core.dex.visitors.ClassModifier.removeBridgeMethod(ClassModifier.java:239)
    	at jadx.core.dex.visitors.ClassModifier.removeSyntheticMethods(ClassModifier.java:154)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:64)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:57)
    */
/* loaded from: target.jar:com/sun/tools/javac/file/ZipFileIndex.class */
public class ZipFileIndex {
    private static final String MIN_CHAR = String.valueOf((char) 0);
    private static final String MAX_CHAR = String.valueOf((char) 65535);
    public static final long NOT_MODIFIED = Long.MIN_VALUE;
    private static final boolean NON_BATCH_MODE;
    final File zipFile;
    private Reference<File> absFileRef;
    long zipFileLastModified;
    private RandomAccessFile zipRandomFile;
    private Entry[] entries;
    final RelativePath.RelativeDirectory symbolFilePrefix;
    private final int symbolFilePrefixLength;
    private final boolean usePreindexedCache;
    private final String preindexedCacheLocation;
    private boolean writeIndex;
    private SoftReference<Inflater> inflaterRef;
    private Map<RelativePath.RelativeDirectory, DirectoryEntry> directories = Collections.emptyMap();
    private Set<RelativePath.RelativeDirectory> allDirs = Collections.emptySet();
    private boolean readFromIndex = false;
    private File zipIndexFile = null;
    private boolean triedToReadIndex = false;
    private boolean hasPopulatedData = false;
    long lastReferenceTimeStamp = Long.MIN_VALUE;
    private Map<String, SoftReference<RelativePath.RelativeDirectory>> relativeDirectoryCache = new HashMap();

    static {
        NON_BATCH_MODE = System.getProperty("nonBatchMode") != null;
    }

    public synchronized boolean isOpen() {
        return this.zipRandomFile != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZipFileIndex(File file, RelativePath.RelativeDirectory relativeDirectory, boolean z, boolean z2, String str) throws IOException {
        this.zipFileLastModified = Long.MIN_VALUE;
        this.writeIndex = false;
        this.zipFile = file;
        this.symbolFilePrefix = relativeDirectory;
        this.symbolFilePrefixLength = relativeDirectory == null ? 0 : relativeDirectory.getPath().getBytes("UTF-8").length;
        this.writeIndex = z;
        this.usePreindexedCache = z2;
        this.preindexedCacheLocation = str;
        if (file != null) {
            this.zipFileLastModified = file.lastModified();
        }
        checkIndex();
    }

    public String toString() {
        return "ZipFileIndex[" + this.zipFile + "]";
    }

    protected void finalize() throws Throwable {
        closeFile();
        super.finalize();
    }

    private boolean isUpToDate() {
        if (this.zipFile == null) {
            return false;
        }
        if ((!NON_BATCH_MODE || this.zipFileLastModified == this.zipFile.lastModified()) && this.hasPopulatedData) {
            return true;
        }
        return false;
    }

    private void checkIndex() throws IOException {
        boolean z = true;
        if (!isUpToDate()) {
            closeFile();
            z = false;
        }
        if (this.zipRandomFile != null || z) {
            this.lastReferenceTimeStamp = System.currentTimeMillis();
            return;
        }
        this.hasPopulatedData = true;
        if (readIndex()) {
            this.lastReferenceTimeStamp = System.currentTimeMillis();
            return;
        }
        this.directories = Collections.emptyMap();
        this.allDirs = Collections.emptySet();
        try {
            openFile();
            new ZipDirectory(this, this.zipRandomFile, 0L, this.zipRandomFile.length(), this).buildIndex();
            if (this.zipRandomFile != null) {
                closeFile();
            }
            this.lastReferenceTimeStamp = System.currentTimeMillis();
        } catch (Throwable th) {
            if (this.zipRandomFile != null) {
                closeFile();
            }
            throw th;
        }
    }

    private void openFile() throws FileNotFoundException {
        if (this.zipRandomFile == null && this.zipFile != null) {
            this.zipRandomFile = new RandomAccessFile(this.zipFile, PDPageLabelRange.STYLE_ROMAN_LOWER);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanupState() {
        this.entries = Entry.EMPTY_ARRAY;
        this.directories = Collections.emptyMap();
        this.zipFileLastModified = Long.MIN_VALUE;
        this.allDirs = Collections.emptySet();
    }

    public synchronized void close() {
        writeIndex();
        closeFile();
    }

    private void closeFile() {
        if (this.zipRandomFile != null) {
            try {
                this.zipRandomFile.close();
            } catch (IOException e) {
            }
            this.zipRandomFile = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Entry getZipIndexEntry(RelativePath relativePath) {
        try {
            checkIndex();
            DirectoryEntry directoryEntry = this.directories.get(relativePath.dirname());
            String basename = relativePath.basename();
            if (directoryEntry == null) {
                return null;
            }
            return directoryEntry.getEntry(basename);
        } catch (IOException e) {
            return null;
        }
    }

    public synchronized List<String> getFiles(RelativePath.RelativeDirectory relativeDirectory) {
        try {
            checkIndex();
            DirectoryEntry directoryEntry = this.directories.get(relativeDirectory);
            List<String> files = directoryEntry == null ? null : directoryEntry.getFiles();
            if (files == null) {
                return List.nil();
            }
            return files;
        } catch (IOException e) {
            return List.nil();
        }
    }

    public synchronized java.util.List<String> getDirectories(RelativePath.RelativeDirectory relativeDirectory) {
        try {
            checkIndex();
            DirectoryEntry directoryEntry = this.directories.get(relativeDirectory);
            List directories = directoryEntry == null ? null : directoryEntry.getDirectories();
            if (directories == null) {
                return List.nil();
            }
            return directories;
        } catch (IOException e) {
            return List.nil();
        }
    }

    public synchronized Set<RelativePath.RelativeDirectory> getAllDirectories() {
        try {
            checkIndex();
            if (this.allDirs == Collections.EMPTY_SET) {
                this.allDirs = new LinkedHashSet(this.directories.keySet());
            }
            return this.allDirs;
        } catch (IOException e) {
            return Collections.emptySet();
        }
    }

    public synchronized boolean contains(RelativePath relativePath) {
        try {
            checkIndex();
            return getZipIndexEntry(relativePath) != null;
        } catch (IOException e) {
            return false;
        }
    }

    public synchronized boolean isDirectory(RelativePath relativePath) throws IOException {
        if (relativePath.getPath().length() == 0) {
            this.lastReferenceTimeStamp = System.currentTimeMillis();
            return true;
        }
        checkIndex();
        return this.directories.get(relativePath) != null;
    }

    public synchronized long getLastModified(RelativePath.RelativeFile relativeFile) throws IOException {
        Entry zipIndexEntry = getZipIndexEntry(relativeFile);
        if (zipIndexEntry == null) {
            throw new FileNotFoundException();
        }
        return zipIndexEntry.getLastModified();
    }

    public synchronized int length(RelativePath.RelativeFile relativeFile) throws IOException {
        Entry zipIndexEntry = getZipIndexEntry(relativeFile);
        if (zipIndexEntry == null) {
            throw new FileNotFoundException();
        }
        if (zipIndexEntry.isDir) {
            return 0;
        }
        if (get2ByteLittleEndian(getHeader(zipIndexEntry), 8) == 0) {
            return zipIndexEntry.compressedSize;
        }
        return zipIndexEntry.size;
    }

    public synchronized byte[] read(RelativePath.RelativeFile relativeFile) throws IOException {
        Entry zipIndexEntry = getZipIndexEntry(relativeFile);
        if (zipIndexEntry == null) {
            throw new FileNotFoundException("Path not found in ZIP: " + relativeFile.path);
        }
        return read(zipIndexEntry);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized byte[] read(Entry entry) throws IOException {
        openFile();
        byte[] readBytes = readBytes(entry);
        closeFile();
        return readBytes;
    }

    public synchronized int read(RelativePath.RelativeFile relativeFile, byte[] bArr) throws IOException {
        Entry zipIndexEntry = getZipIndexEntry(relativeFile);
        if (zipIndexEntry == null) {
            throw new FileNotFoundException();
        }
        return read(zipIndexEntry, bArr);
    }

    synchronized int read(Entry entry, byte[] bArr) throws IOException {
        return readBytes(entry, bArr);
    }

    private byte[] readBytes(Entry entry) throws IOException {
        byte[] header = getHeader(entry);
        int i = entry.compressedSize;
        byte[] bArr = new byte[i];
        this.zipRandomFile.skipBytes(get2ByteLittleEndian(header, 26) + get2ByteLittleEndian(header, 28));
        this.zipRandomFile.readFully(bArr, 0, i);
        if (get2ByteLittleEndian(header, 8) == 0) {
            return bArr;
        }
        int i2 = entry.size;
        byte[] bArr2 = new byte[i2];
        if (inflate(bArr, bArr2) != i2) {
            throw new ZipException("corrupted zip file");
        }
        return bArr2;
    }

    private int readBytes(Entry entry, byte[] bArr) throws IOException {
        int read;
        byte[] header = getHeader(entry);
        if (get2ByteLittleEndian(header, 8) == 0) {
            this.zipRandomFile.skipBytes(get2ByteLittleEndian(header, 26) + get2ByteLittleEndian(header, 28));
            int i = 0;
            int length = bArr.length;
            while (i < length && (read = this.zipRandomFile.read(bArr, i, length - i)) != -1) {
                i += read;
            }
            return entry.size;
        }
        int i2 = entry.compressedSize;
        byte[] bArr2 = new byte[i2];
        this.zipRandomFile.skipBytes(get2ByteLittleEndian(header, 26) + get2ByteLittleEndian(header, 28));
        this.zipRandomFile.readFully(bArr2, 0, i2);
        if (inflate(bArr2, bArr) == -1) {
            throw new ZipException("corrupted zip file");
        }
        return entry.size;
    }

    private byte[] getHeader(Entry entry) throws IOException {
        this.zipRandomFile.seek(entry.offset);
        byte[] bArr = new byte[30];
        this.zipRandomFile.readFully(bArr);
        if (get4ByteLittleEndian(bArr, 0) != 67324752) {
            throw new ZipException("corrupted zip file");
        }
        if ((get2ByteLittleEndian(bArr, 6) & 1) != 0) {
            throw new ZipException("encrypted zip file");
        }
        return bArr;
    }

    private int inflate(byte[] bArr, byte[] bArr2) {
        Inflater inflater = this.inflaterRef == null ? null : this.inflaterRef.get();
        if (inflater == null) {
            Inflater inflater2 = new Inflater(true);
            inflater = inflater2;
            this.inflaterRef = new SoftReference<>(inflater2);
        }
        inflater.reset();
        inflater.setInput(bArr);
        try {
            return inflater.inflate(bArr2);
        } catch (DataFormatException e) {
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int get2ByteLittleEndian(byte[] bArr, int i) {
        return (bArr[i] & 255) + ((bArr[i + 1] & 255) << 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int get4ByteLittleEndian(byte[] bArr, int i) {
        return (bArr[i] & 255) + ((bArr[i + 1] & 255) << 8) + ((bArr[i + 2] & 255) << 16) + ((bArr[i + 3] & 255) << 24);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/sun/tools/javac/file/ZipFileIndex$ZipDirectory.class */
    public class ZipDirectory {
        private RelativePath.RelativeDirectory lastDir;
        private int lastStart;
        private int lastLen;
        byte[] zipDir;
        RandomAccessFile zipRandomFile;
        ZipFileIndex zipFileIndex;
        final /* synthetic */ ZipFileIndex this$0;

        public ZipDirectory(ZipFileIndex zipFileIndex, RandomAccessFile randomAccessFile, long j, long j2, ZipFileIndex zipFileIndex2) throws IOException {
            this.this$0 = zipFileIndex;
            this.zipRandomFile = null;
            this.zipFileIndex = null;
            this.zipRandomFile = randomAccessFile;
            this.zipFileIndex = zipFileIndex2;
            hasValidHeader();
            findCENRecord(j, j2);
        }

        private boolean hasValidHeader() throws IOException {
            long filePointer = this.zipRandomFile.getFilePointer();
            try {
                if (this.zipRandomFile.read() == 80 && this.zipRandomFile.read() == 75 && this.zipRandomFile.read() == 3) {
                    if (this.zipRandomFile.read() == 4) {
                        return true;
                    }
                }
                this.zipRandomFile.seek(filePointer);
                throw new ZipFormatException("invalid zip magic");
            } finally {
                this.zipRandomFile.seek(filePointer);
            }
        }

        private void findCENRecord(long j, long j2) throws IOException {
            long j3 = j2 - j;
            int i = 1024;
            byte[] bArr = new byte[1024];
            long j4 = j2 - j;
            while (true) {
                long j5 = j4;
                if (j5 >= 22) {
                    if (j5 < i) {
                        i = (int) j5;
                    }
                    long j6 = j5 - i;
                    this.zipRandomFile.seek(j + j6);
                    this.zipRandomFile.readFully(bArr, 0, i);
                    int i2 = i - 22;
                    while (i2 >= 0 && (bArr[i2] != 80 || bArr[i2 + 1] != 75 || bArr[i2 + 2] != 5 || bArr[i2 + 3] != 6 || j6 + i2 + 22 + ZipFileIndex.get2ByteLittleEndian(bArr, i2 + 20) != j3)) {
                        i2--;
                    }
                    if (i2 >= 0) {
                        this.zipDir = new byte[ZipFileIndex.get4ByteLittleEndian(bArr, i2 + 12)];
                        int i3 = ZipFileIndex.get4ByteLittleEndian(bArr, i2 + 16);
                        if (i3 < 0 || ZipFileIndex.get2ByteLittleEndian(bArr, i2 + 10) == 65535) {
                            throw new ZipFormatException("detected a zip64 archive");
                        }
                        this.zipRandomFile.seek(j + i3);
                        this.zipRandomFile.readFully(this.zipDir, 0, this.zipDir.length);
                        return;
                    }
                    j4 = j6 + 21;
                } else {
                    throw new ZipException("cannot read zip file");
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void buildIndex() throws IOException {
            int length = this.zipDir.length;
            if (length <= 0) {
                this.this$0.cleanupState();
                return;
            }
            this.this$0.directories = new LinkedHashMap();
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    break;
                } else {
                    i = readEntry(i2, arrayList, this.this$0.directories);
                }
            }
            for (RelativePath.RelativeDirectory relativeDirectory : this.this$0.directories.keySet()) {
                Entry entry = new Entry(this.this$0.getRelativeDirectory(relativeDirectory.dirname().getPath()), relativeDirectory.basename());
                entry.isDir = true;
                arrayList.add(entry);
            }
            this.this$0.entries = (Entry[]) arrayList.toArray(new Entry[arrayList.size()]);
            Arrays.sort(this.this$0.entries);
        }

        private int readEntry(int i, java.util.List<Entry> list, Map<RelativePath.RelativeDirectory, DirectoryEntry> map) throws IOException {
            if (ZipFileIndex.get4ByteLittleEndian(this.zipDir, i) != 33639248) {
                throw new ZipException("cannot read zip file entry");
            }
            int i2 = i + 46;
            int i3 = i2;
            int i4 = i3 + ZipFileIndex.get2ByteLittleEndian(this.zipDir, i + 28);
            if (this.zipFileIndex.symbolFilePrefixLength != 0 && i4 - i3 >= this.this$0.symbolFilePrefixLength) {
                i2 += this.zipFileIndex.symbolFilePrefixLength;
                i3 += this.zipFileIndex.symbolFilePrefixLength;
            }
            for (int i5 = i3; i5 < i4; i5++) {
                byte b = this.zipDir[i5];
                if (b == 92) {
                    this.zipDir[i5] = 47;
                    i3 = i5 + 1;
                } else if (b == 47) {
                    i3 = i5 + 1;
                }
            }
            RelativePath.RelativeDirectory relativeDirectory = null;
            if (i3 == i2) {
                relativeDirectory = this.this$0.getRelativeDirectory("");
            } else if (this.lastDir != null && this.lastLen == (i3 - i2) - 1) {
                int i6 = this.lastLen - 1;
                while (true) {
                    if (this.zipDir[this.lastStart + i6] != this.zipDir[i2 + i6]) {
                        break;
                    }
                    if (i6 == 0) {
                        relativeDirectory = this.lastDir;
                        break;
                    }
                    i6--;
                }
            }
            if (relativeDirectory == null) {
                this.lastStart = i2;
                this.lastLen = (i3 - i2) - 1;
                relativeDirectory = this.this$0.getRelativeDirectory(new String(this.zipDir, i2, this.lastLen, "UTF-8"));
                this.lastDir = relativeDirectory;
                RelativePath.RelativeDirectory relativeDirectory2 = relativeDirectory;
                while (true) {
                    RelativePath.RelativeDirectory relativeDirectory3 = relativeDirectory2;
                    if (map.get(relativeDirectory3) != null) {
                        break;
                    }
                    map.put(relativeDirectory3, new DirectoryEntry(relativeDirectory3, this.zipFileIndex));
                    if (relativeDirectory3.path.indexOf(RuntimeConstants.SIG_PACKAGE) == relativeDirectory3.path.length() - 1) {
                        break;
                    }
                    relativeDirectory2 = this.this$0.getRelativeDirectory(relativeDirectory3.dirname().getPath());
                }
            } else if (map.get(relativeDirectory) == null) {
                map.put(relativeDirectory, new DirectoryEntry(relativeDirectory, this.zipFileIndex));
            }
            if (i3 != i4) {
                Entry entry = new Entry(relativeDirectory, new String(this.zipDir, i3, i4 - i3, "UTF-8"));
                entry.setNativeTime(ZipFileIndex.get4ByteLittleEndian(this.zipDir, i + 12));
                entry.compressedSize = ZipFileIndex.get4ByteLittleEndian(this.zipDir, i + 20);
                entry.size = ZipFileIndex.get4ByteLittleEndian(this.zipDir, i + 24);
                entry.offset = ZipFileIndex.get4ByteLittleEndian(this.zipDir, i + 42);
                list.add(entry);
            }
            return i + 46 + ZipFileIndex.get2ByteLittleEndian(this.zipDir, i + 28) + ZipFileIndex.get2ByteLittleEndian(this.zipDir, i + 30) + ZipFileIndex.get2ByteLittleEndian(this.zipDir, i + 32);
        }
    }

    public long getZipFileLastModified() throws IOException {
        long j;
        synchronized (this) {
            checkIndex();
            j = this.zipFileLastModified;
        }
        return j;
    }

    /*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
        java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.nodes.MethodNode.getBasicBlocks()" is null
        	at jadx.core.dex.visitors.kotlin.ProcessKotlinInternals.processMth(ProcessKotlinInternals.java:93)
        	at jadx.core.dex.visitors.kotlin.ProcessKotlinInternals.visit(ProcessKotlinInternals.java:84)
        */
    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/javac/file/ZipFileIndex$DirectoryEntry.class */
    public static class DirectoryEntry {
        private boolean zipFileEntriesInited;
        private RelativePath.RelativeDirectory dirName;
        private ZipFileIndex zipFileIndex;
        private int numEntries;
        private long writtenOffsetOffset = 0;
        private List<String> zipFileEntriesFiles = List.nil();
        private List<String> zipFileEntriesDirectories = List.nil();
        private List<Entry> zipFileEntries = List.nil();
        private java.util.List<Entry> entries = new ArrayList();
        private boolean filesInited = false;
        private boolean directoriesInited = false;
        private boolean entriesInited = false;

        /*  JADX ERROR: Failed to decode insn: 0x0002: MOVE_MULTI, method: com.sun.tools.javac.file.ZipFileIndex.DirectoryEntry.access$1602(com.sun.tools.javac.file.ZipFileIndex$DirectoryEntry, long):long
            java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
            	at java.base/java.lang.System.arraycopy(Native Method)
            	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:49)
            	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:118)
            	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
            	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:54)
            	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:81)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:50)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:156)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:443)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:449)
            	at jadx.core.ProcessClass.process(ProcessClass.java:70)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:118)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:400)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:388)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:338)
            */
        static /* synthetic */ long access$1602(com.sun.tools.javac.file.ZipFileIndex.DirectoryEntry r6, long r7) {
            /*
                r0 = r6
                r1 = r7
                // decode failed: arraycopy: source index -1 out of bounds for object array[6]
                r0.writtenOffsetOffset = r1
                return r-1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sun.tools.javac.file.ZipFileIndex.DirectoryEntry.access$1602(com.sun.tools.javac.file.ZipFileIndex$DirectoryEntry, long):long");
        }

        static /* synthetic */ RelativePath.RelativeDirectory access$1700(DirectoryEntry directoryEntry) {
            return directoryEntry.dirName;
        }

        DirectoryEntry(RelativePath.RelativeDirectory relativeDirectory, ZipFileIndex zipFileIndex) {
            this.dirName = relativeDirectory;
            this.zipFileIndex = zipFileIndex;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public List<String> getFiles() {
            if (!this.filesInited) {
                initEntries();
                for (Entry entry : this.entries) {
                    if (!entry.isDir) {
                        this.zipFileEntriesFiles = this.zipFileEntriesFiles.append(entry.name);
                    }
                }
                this.filesInited = true;
            }
            return this.zipFileEntriesFiles;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public List<String> getDirectories() {
            if (!this.directoriesInited) {
                initEntries();
                for (Entry entry : this.entries) {
                    if (entry.isDir) {
                        this.zipFileEntriesDirectories = this.zipFileEntriesDirectories.append(entry.name);
                    }
                }
                this.directoriesInited = true;
            }
            return this.zipFileEntriesDirectories;
        }

        private List<Entry> getEntries() {
            if (!this.zipFileEntriesInited) {
                initEntries();
                this.zipFileEntries = List.nil();
                Iterator<Entry> it = this.entries.iterator();
                while (it.hasNext()) {
                    this.zipFileEntries = this.zipFileEntries.append(it.next());
                }
                this.zipFileEntriesInited = true;
            }
            return this.zipFileEntries;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Entry getEntry(String str) {
            initEntries();
            int binarySearch = Collections.binarySearch(this.entries, new Entry(this.dirName, str));
            if (binarySearch < 0) {
                return null;
            }
            return this.entries.get(binarySearch);
        }

        private void initEntries() {
            if (!this.entriesInited) {
                if (this.zipFileIndex.readFromIndex) {
                    File indexFile = this.zipFileIndex.getIndexFile();
                    if (indexFile != null) {
                        RandomAccessFile randomAccessFile = null;
                        try {
                            randomAccessFile = new RandomAccessFile(indexFile, PDPageLabelRange.STYLE_ROMAN_LOWER);
                            randomAccessFile.seek(this.writtenOffsetOffset);
                            for (int i = 0; i < this.numEntries; i++) {
                                byte[] bArr = new byte[randomAccessFile.readInt()];
                                randomAccessFile.read(bArr);
                                String str = new String(bArr, "UTF-8");
                                boolean z = randomAccessFile.readByte() != 0;
                                int readInt = randomAccessFile.readInt();
                                int readInt2 = randomAccessFile.readInt();
                                int readInt3 = randomAccessFile.readInt();
                                long readLong = randomAccessFile.readLong();
                                Entry entry = new Entry(this.dirName, str);
                                entry.isDir = z;
                                entry.offset = readInt;
                                entry.size = readInt2;
                                entry.compressedSize = readInt3;
                                entry.javatime = readLong;
                                this.entries.add(entry);
                            }
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (Throwable th) {
                                }
                            }
                        } catch (Throwable th2) {
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (Throwable th3) {
                                }
                            }
                        }
                    }
                } else {
                    int i2 = (-Arrays.binarySearch(this.zipFileIndex.entries, new Entry(this.dirName, ZipFileIndex.MIN_CHAR))) - 1;
                    int i3 = (-Arrays.binarySearch(this.zipFileIndex.entries, new Entry(this.dirName, ZipFileIndex.MAX_CHAR))) - 1;
                    for (int i4 = i2; i4 < i3; i4++) {
                        this.entries.add(this.zipFileIndex.entries[i4]);
                    }
                }
                this.entriesInited = true;
            }
        }

        java.util.List<Entry> getEntriesAsCollection() {
            initEntries();
            return this.entries;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.sun.tools.javac.file.ZipFileIndex.DirectoryEntry.access$1602(com.sun.tools.javac.file.ZipFileIndex$DirectoryEntry, long):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:74)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Class not yet loaded at codegen stage: com.sun.tools.javac.file.ZipFileIndex
        	at jadx.core.dex.nodes.ClassNode.reloadAtCodegenStage(ClassNode.java:883)
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:66)
        	... 1 more
        */
    private boolean readIndex() {
        /*
            Method dump skipped, instructions count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.tools.javac.file.ZipFileIndex.readIndex():boolean");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.sun.tools.javac.file.ZipFileIndex.DirectoryEntry.access$1602(com.sun.tools.javac.file.ZipFileIndex$DirectoryEntry, long):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:74)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Class not yet loaded at codegen stage: com.sun.tools.javac.file.ZipFileIndex
        	at jadx.core.dex.nodes.ClassNode.reloadAtCodegenStage(ClassNode.java:883)
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:66)
        	... 1 more
        */
    private boolean writeIndex() {
        /*
            Method dump skipped, instructions count: 601
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.tools.javac.file.ZipFileIndex.writeIndex():boolean");
    }

    public boolean writeZipIndex() {
        boolean writeIndex;
        synchronized (this) {
            writeIndex = writeIndex();
        }
        return writeIndex;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File getIndexFile() {
        if (this.zipIndexFile == null) {
            if (this.zipFile == null) {
                return null;
            }
            this.zipIndexFile = new File((this.preindexedCacheLocation == null ? "" : this.preindexedCacheLocation) + this.zipFile.getName() + ".index");
        }
        return this.zipIndexFile;
    }

    public File getZipFile() {
        return this.zipFile;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public File getAbsoluteFile() {
        File file = this.absFileRef == null ? null : this.absFileRef.get();
        if (file == null) {
            file = this.zipFile.getAbsoluteFile();
            this.absFileRef = new SoftReference(file);
        }
        return file;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RelativePath.RelativeDirectory getRelativeDirectory(String str) {
        RelativePath.RelativeDirectory relativeDirectory;
        SoftReference<RelativePath.RelativeDirectory> softReference = this.relativeDirectoryCache.get(str);
        if (softReference != null && (relativeDirectory = softReference.get()) != null) {
            return relativeDirectory;
        }
        RelativePath.RelativeDirectory relativeDirectory2 = new RelativePath.RelativeDirectory(str);
        this.relativeDirectoryCache.put(str, new SoftReference<>(relativeDirectory2));
        return relativeDirectory2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/javac/file/ZipFileIndex$Entry.class */
    public static class Entry implements Comparable<Entry> {
        public static final Entry[] EMPTY_ARRAY = new Entry[0];
        RelativePath.RelativeDirectory dir;
        boolean isDir;
        String name;
        int offset;
        int size;
        int compressedSize;
        long javatime;
        private int nativetime;

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Entry entry) {
            return compareTo2(entry);
        }

        static {
        }

        public Entry(RelativePath relativePath) {
            this(relativePath.dirname(), relativePath.basename());
        }

        public Entry(RelativePath.RelativeDirectory relativeDirectory, String str) {
            this.dir = relativeDirectory;
            this.name = str;
        }

        public String getName() {
            return new RelativePath.RelativeFile(this.dir, this.name).getPath();
        }

        public String getFileName() {
            return this.name;
        }

        public long getLastModified() {
            if (this.javatime == 0) {
                this.javatime = dosToJavaTime(this.nativetime);
            }
            return this.javatime;
        }

        private static long dosToJavaTime(int i) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(1, ((i >> 25) & 127) + 1980);
            calendar.set(2, ((i >> 21) & 15) - 1);
            calendar.set(5, (i >> 16) & 31);
            calendar.set(11, (i >> 11) & 31);
            calendar.set(12, (i >> 5) & 63);
            calendar.set(13, (i << 1) & 62);
            calendar.set(14, 0);
            return calendar.getTimeInMillis();
        }

        void setNativeTime(int i) {
            this.nativetime = i;
        }

        public boolean isDirectory() {
            return this.isDir;
        }

        /* renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(Entry entry) {
            int compareTo;
            RelativePath.RelativeDirectory relativeDirectory = entry.dir;
            if (this.dir != relativeDirectory && (compareTo = this.dir.compareTo((RelativePath) relativeDirectory)) != 0) {
                return compareTo;
            }
            return this.name.compareTo(entry.name);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            return this.dir.equals(entry.dir) && this.name.equals(entry.name);
        }

        public int hashCode() {
            return (97 * ((97 * 7) + (this.dir != null ? this.dir.hashCode() : 0))) + (this.name != null ? this.name.hashCode() : 0);
        }

        public String toString() {
            return this.isDir ? "Dir:" + this.dir + " : " + this.name : this.dir + CallSiteDescriptor.TOKEN_DELIMITER + this.name;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/javac/file/ZipFileIndex$ZipFormatException.class */
    public static final class ZipFormatException extends IOException {
        private static final long serialVersionUID = 8000196834066748623L;

        protected ZipFormatException(String str) {
            super(str);
        }

        protected ZipFormatException(String str, Throwable th) {
            super(str, th);
        }
    }
}
