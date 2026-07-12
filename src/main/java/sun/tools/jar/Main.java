package sun.tools.jar;

import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Pack200;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import sun.misc.JarIndex;
import sun.rmi.rmic.iiop.Constants;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:sun/tools/jar/Main.class */
public class Main {
    String program;
    PrintStream out;
    PrintStream err;
    String fname;
    String mname;
    String ename;
    String[] files;
    boolean cflag;
    boolean uflag;
    boolean xflag;
    boolean tflag;
    boolean vflag;
    boolean flag0;
    boolean Mflag;
    boolean iflag;
    boolean nflag;
    boolean pflag;
    static final String MANIFEST_DIR = "META-INF/";
    static final String VERSION = "1.0";
    private static ResourceBundle rsrc;
    private static final boolean useExtractionTime;
    private boolean ok;
    static final /* synthetic */ boolean $assertionsDisabled;
    String zname = "";
    String rootjar = null;
    Map<String, File> entryMap = new HashMap();
    Set<File> entries = new LinkedHashSet();
    Set<String> paths = new HashSet();
    private byte[] copyBuf = new byte[8192];
    private HashSet<String> jarPaths = new HashSet<>();

    static {
        $assertionsDisabled = !Main.class.desiredAssertionStatus();
        useExtractionTime = Boolean.getBoolean("sun.tools.jar.useExtractionTime");
        try {
            rsrc = ResourceBundle.getBundle("sun.tools.jar.resources.jar");
        } catch (MissingResourceException e) {
            throw new Error("Fatal: Resource for jar is missing");
        }
    }

    private String getMsg(String str) {
        try {
            return rsrc.getString(str);
        } catch (MissingResourceException e) {
            throw new Error("Error in message file");
        }
    }

    private String formatMsg(String str, String str2) {
        return MessageFormat.format(getMsg(str), str2);
    }

    private String formatMsg2(String str, String str2, String str3) {
        return MessageFormat.format(getMsg(str), str2, str3);
    }

    public Main(PrintStream printStream, PrintStream printStream2, String str) {
        this.out = printStream;
        this.err = printStream2;
        this.program = str;
    }

    private static File createTempFileInSameDirectoryAs(File file) throws IOException {
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            parentFile = new File(Constants.NAME_SEPARATOR);
        }
        return File.createTempFile("jartmp", null, parentFile);
    }

    /* JADX WARN: Finally extract failed */
    public synchronized boolean run(String[] strArr) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        this.ok = true;
        if (!parseArgs(strArr)) {
            return false;
        }
        try {
            if ((this.cflag || this.uflag) && this.fname != null) {
                this.zname = this.fname.replace(File.separatorChar, '/');
                if (this.zname.startsWith("./")) {
                    this.zname = this.zname.substring(2);
                }
            }
            if (this.cflag) {
                java.util.jar.Manifest manifest = null;
                FileInputStream fileInputStream2 = null;
                if (!this.Mflag) {
                    if (this.mname != null) {
                        fileInputStream2 = new FileInputStream(this.mname);
                        manifest = new java.util.jar.Manifest(new BufferedInputStream(fileInputStream2));
                    } else {
                        manifest = new java.util.jar.Manifest();
                    }
                    addVersion(manifest);
                    addCreatedBy(manifest);
                    if (isAmbiguousMainClass(manifest)) {
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                            return false;
                        }
                        return false;
                    }
                    if (this.ename != null) {
                        addMainClass(manifest, this.ename);
                    }
                }
                expand(null, this.files, false);
                if (this.fname != null) {
                    fileOutputStream2 = new FileOutputStream(this.fname);
                } else {
                    fileOutputStream2 = new FileOutputStream(FileDescriptor.out);
                    if (this.vflag) {
                        this.vflag = false;
                    }
                }
                File file = null;
                FileOutputStream fileOutputStream3 = fileOutputStream2;
                String substring = this.fname == null ? "tmpjar" : this.fname.substring(this.fname.indexOf(File.separatorChar) + 1);
                if (this.nflag) {
                    file = createTemporaryFile(substring, ".jar");
                    fileOutputStream2 = new FileOutputStream(file);
                }
                create(new BufferedOutputStream(fileOutputStream2, 4096), manifest);
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                fileOutputStream2.close();
                if (this.nflag) {
                    JarFile jarFile = null;
                    File file2 = null;
                    JarOutputStream jarOutputStream = null;
                    try {
                        try {
                            Pack200.Packer newPacker = Pack200.newPacker();
                            newPacker.properties().put("pack.effort", "1");
                            jarFile = new JarFile(file.getCanonicalPath());
                            file2 = createTemporaryFile(substring, ".pack");
                            fileOutputStream2 = new FileOutputStream(file2);
                            newPacker.pack(jarFile, fileOutputStream2);
                            jarOutputStream = new JarOutputStream(fileOutputStream3);
                            Pack200.newUnpacker().unpack(file2, jarOutputStream);
                            if (jarFile != null) {
                                jarFile.close();
                            }
                            if (fileOutputStream2 != null) {
                                fileOutputStream2.close();
                            }
                            if (jarOutputStream != null) {
                                jarOutputStream.close();
                            }
                            if (file != null && file.exists()) {
                                file.delete();
                            }
                            if (file2 != null && file2.exists()) {
                                file2.delete();
                            }
                        } catch (Throwable th) {
                            if (jarFile != null) {
                                jarFile.close();
                            }
                            if (fileOutputStream2 != null) {
                                fileOutputStream2.close();
                            }
                            if (jarOutputStream != null) {
                                jarOutputStream.close();
                            }
                            if (file != null && file.exists()) {
                                file.delete();
                            }
                            if (file2 != null && file2.exists()) {
                                file2.delete();
                            }
                            throw th;
                        }
                    } catch (IOException e) {
                        fatalError(e);
                        if (jarFile != null) {
                            jarFile.close();
                        }
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        if (jarOutputStream != null) {
                            jarOutputStream.close();
                        }
                        if (file != null && file.exists()) {
                            file.delete();
                        }
                        if (file2 != null && file2.exists()) {
                            file2.delete();
                        }
                    }
                }
            } else if (this.uflag) {
                File file3 = null;
                File file4 = null;
                if (this.fname != null) {
                    file3 = new File(this.fname);
                    file4 = createTempFileInSameDirectoryAs(file3);
                    fileInputStream = new FileInputStream(file3);
                    fileOutputStream = new FileOutputStream(file4);
                } else {
                    fileInputStream = new FileInputStream(FileDescriptor.in);
                    fileOutputStream = new FileOutputStream(FileDescriptor.out);
                    this.vflag = false;
                }
                FileInputStream fileInputStream3 = (this.Mflag || this.mname == null) ? null : new FileInputStream(this.mname);
                expand(null, this.files, true);
                boolean update = update(fileInputStream, new BufferedOutputStream(fileOutputStream), fileInputStream3, null);
                if (this.ok) {
                    this.ok = update;
                }
                fileInputStream.close();
                fileOutputStream.close();
                if (fileInputStream3 != null) {
                    fileInputStream3.close();
                }
                if (this.ok && this.fname != null) {
                    file3.delete();
                    if (!file4.renameTo(file3)) {
                        file4.delete();
                        throw new IOException(getMsg("error.write.file"));
                    }
                    file4.delete();
                }
            } else if (this.tflag) {
                replaceFSC(this.files);
                if (this.fname != null) {
                    list(this.fname, this.files);
                } else {
                    FileInputStream fileInputStream4 = new FileInputStream(FileDescriptor.in);
                    try {
                        list(new BufferedInputStream(fileInputStream4), this.files);
                        fileInputStream4.close();
                    } catch (Throwable th2) {
                        fileInputStream4.close();
                        throw th2;
                    }
                }
            } else if (this.xflag) {
                replaceFSC(this.files);
                if (this.fname != null && this.files != null) {
                    extract(this.fname, this.files);
                } else {
                    FileInputStream fileInputStream5 = this.fname == null ? new FileInputStream(FileDescriptor.in) : new FileInputStream(this.fname);
                    try {
                        extract(new BufferedInputStream(fileInputStream5), this.files);
                        fileInputStream5.close();
                    } catch (Throwable th3) {
                        fileInputStream5.close();
                        throw th3;
                    }
                }
            } else if (this.iflag) {
                genIndex(this.rootjar, this.files);
            }
        } catch (IOException e2) {
            fatalError(e2);
            this.ok = false;
        } catch (Error e3) {
            e3.printStackTrace();
            this.ok = false;
        } catch (Throwable th4) {
            th4.printStackTrace();
            this.ok = false;
        }
        this.out.flush();
        this.err.flush();
        return this.ok;
    }

    boolean parseArgs(String[] strArr) {
        try {
            String[] parse = CommandLine.parse(strArr);
            int i = 1;
            try {
                String str = parse[0];
                if (str.startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
                    str = str.substring(1);
                }
                for (int i2 = 0; i2 < str.length(); i2++) {
                    switch (str.charAt(i2)) {
                        case '0':
                            this.flag0 = true;
                            break;
                        case 'M':
                            this.Mflag = true;
                            break;
                        case 'P':
                            this.pflag = true;
                            break;
                        case 'c':
                            if (this.xflag || this.tflag || this.uflag || this.iflag) {
                                usageError();
                                return false;
                            }
                            this.cflag = true;
                            break;
                            break;
                        case 'e':
                            int i3 = i;
                            i++;
                            this.ename = parse[i3];
                            break;
                        case 'f':
                            int i4 = i;
                            i++;
                            this.fname = parse[i4];
                            break;
                        case 'i':
                            if (this.cflag || this.uflag || this.xflag || this.tflag) {
                                usageError();
                                return false;
                            }
                            int i5 = i;
                            i++;
                            this.rootjar = parse[i5];
                            this.iflag = true;
                            break;
                        case 'm':
                            int i6 = i;
                            i++;
                            this.mname = parse[i6];
                            break;
                        case 'n':
                            this.nflag = true;
                            break;
                        case 't':
                            if (this.cflag || this.uflag || this.xflag || this.iflag) {
                                usageError();
                                return false;
                            }
                            this.tflag = true;
                            break;
                        case 'u':
                            if (this.cflag || this.xflag || this.tflag || this.iflag) {
                                usageError();
                                return false;
                            }
                            this.uflag = true;
                            break;
                        case 'v':
                            this.vflag = true;
                            break;
                        case 'x':
                            if (this.cflag || this.uflag || this.tflag || this.iflag) {
                                usageError();
                                return false;
                            }
                            this.xflag = true;
                            break;
                        default:
                            error(formatMsg("error.illegal.option", String.valueOf(str.charAt(i2))));
                            usageError();
                            return false;
                    }
                }
                if (!this.cflag && !this.tflag && !this.xflag && !this.uflag && !this.iflag) {
                    error(getMsg("error.bad.option"));
                    usageError();
                    return false;
                }
                int length = parse.length - i;
                if (length <= 0) {
                    if (this.cflag && this.mname == null) {
                        error(getMsg("error.bad.cflag"));
                        usageError();
                        return false;
                    }
                    if (!this.uflag || this.mname != null || this.ename != null) {
                        return true;
                    }
                    error(getMsg("error.bad.uflag"));
                    usageError();
                    return false;
                }
                int i7 = 0;
                String[] strArr2 = new String[length];
                int i8 = i;
                while (i8 < parse.length) {
                    try {
                        if (parse[i8].equals("-C")) {
                            int i9 = i8 + 1;
                            String str2 = parse[i9];
                            String replace = (str2.endsWith(File.separator) ? str2 : str2 + File.separator).replace(File.separatorChar, '/');
                            while (replace.indexOf("//") > -1) {
                                replace = replace.replace("//", RuntimeConstants.SIG_PACKAGE);
                            }
                            this.paths.add(replace.replace(File.separatorChar, '/'));
                            int i10 = i7;
                            i7++;
                            i8 = i9 + 1;
                            strArr2[i10] = replace + parse[i8];
                        } else {
                            int i11 = i7;
                            i7++;
                            strArr2[i11] = parse[i8];
                        }
                        i8++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        usageError();
                        return false;
                    }
                }
                this.files = new String[i7];
                System.arraycopy(strArr2, 0, this.files, 0, i7);
                return true;
            } catch (ArrayIndexOutOfBoundsException e2) {
                usageError();
                return false;
            }
        } catch (FileNotFoundException e3) {
            fatalError(formatMsg("error.cant.open", e3.getMessage()));
            return false;
        } catch (IOException e4) {
            fatalError(e4);
            return false;
        }
    }

    void expand(File file, String[] strArr, boolean z) {
        File file2;
        if (strArr == null) {
            return;
        }
        for (int i = 0; i < strArr.length; i++) {
            if (file == null) {
                file2 = new File(strArr[i]);
            } else {
                file2 = new File(file, strArr[i]);
            }
            if (file2.isFile()) {
                if (this.entries.add(file2) && z) {
                    this.entryMap.put(entryName(file2.getPath()), file2);
                }
            } else if (file2.isDirectory()) {
                if (this.entries.add(file2)) {
                    if (z) {
                        String path = file2.getPath();
                        this.entryMap.put(entryName(path.endsWith(File.separator) ? path : path + File.separator), file2);
                    }
                    expand(file2, file2.list(), z);
                }
            } else {
                error(formatMsg("error.nosuch.fileordir", String.valueOf(file2)));
                this.ok = false;
            }
        }
    }

    void create(OutputStream outputStream, java.util.jar.Manifest manifest) throws IOException {
        JarOutputStream jarOutputStream = new JarOutputStream(outputStream);
        if (this.flag0) {
            jarOutputStream.setMethod(0);
        }
        if (manifest != null) {
            if (this.vflag) {
                output(getMsg("out.added.manifest"));
            }
            ZipEntry zipEntry = new ZipEntry(MANIFEST_DIR);
            zipEntry.setTime(System.currentTimeMillis());
            zipEntry.setSize(0L);
            zipEntry.setCrc(0L);
            jarOutputStream.putNextEntry(zipEntry);
            ZipEntry zipEntry2 = new ZipEntry("META-INF/MANIFEST.MF");
            zipEntry2.setTime(System.currentTimeMillis());
            if (this.flag0) {
                crc32Manifest(zipEntry2, manifest);
            }
            jarOutputStream.putNextEntry(zipEntry2);
            manifest.write(jarOutputStream);
            jarOutputStream.closeEntry();
        }
        Iterator<File> it = this.entries.iterator();
        while (it.hasNext()) {
            addFile(jarOutputStream, it.next());
        }
        jarOutputStream.close();
    }

    private char toUpperCaseASCII(char c) {
        return (c < 'a' || c > 'z') ? c : (char) ((c + 'A') - 97);
    }

    private boolean equalsIgnoreCase(String str, String str2) {
        if (!$assertionsDisabled && !str2.toUpperCase(Locale.ENGLISH).equals(str2)) {
            throw new AssertionError();
        }
        int length = str.length();
        if (length != str2.length()) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            char charAt2 = str2.charAt(i);
            if (charAt != charAt2 && toUpperCaseASCII(charAt) != charAt2) {
                return false;
            }
        }
        return true;
    }

    boolean update(InputStream inputStream, OutputStream outputStream, InputStream inputStream2, JarIndex jarIndex) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        ZipOutputStream jarOutputStream = new JarOutputStream(outputStream);
        boolean z = false;
        boolean z2 = true;
        if (jarIndex != null) {
            addIndex(jarIndex, jarOutputStream);
        }
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry != null) {
                String name = nextEntry.getName();
                boolean equalsIgnoreCase = equalsIgnoreCase(name, "META-INF/MANIFEST.MF");
                if (jarIndex == null || !equalsIgnoreCase(name, "META-INF/INDEX.LIST")) {
                    if (!this.Mflag || !equalsIgnoreCase) {
                        if (equalsIgnoreCase && (inputStream2 != null || this.ename != null)) {
                            z = true;
                            if (inputStream2 != null) {
                                FileInputStream fileInputStream = new FileInputStream(this.mname);
                                boolean isAmbiguousMainClass = isAmbiguousMainClass(new java.util.jar.Manifest(fileInputStream));
                                fileInputStream.close();
                                if (isAmbiguousMainClass) {
                                    return false;
                                }
                            }
                            java.util.jar.Manifest manifest = new java.util.jar.Manifest(zipInputStream);
                            if (inputStream2 != null) {
                                manifest.read(inputStream2);
                            }
                            if (!updateManifest(manifest, jarOutputStream)) {
                                return false;
                            }
                        } else if (!this.entryMap.containsKey(name)) {
                            ZipEntry zipEntry = new ZipEntry(name);
                            zipEntry.setMethod(nextEntry.getMethod());
                            zipEntry.setTime(nextEntry.getTime());
                            zipEntry.setComment(nextEntry.getComment());
                            zipEntry.setExtra(nextEntry.getExtra());
                            if (nextEntry.getMethod() == 0) {
                                zipEntry.setSize(nextEntry.getSize());
                                zipEntry.setCrc(nextEntry.getCrc());
                            }
                            jarOutputStream.putNextEntry(zipEntry);
                            copy(zipInputStream, jarOutputStream);
                        } else {
                            File file = this.entryMap.get(name);
                            addFile(jarOutputStream, file);
                            this.entryMap.remove(name);
                            this.entries.remove(file);
                        }
                    }
                }
            } else {
                Iterator<File> it = this.entries.iterator();
                while (it.hasNext()) {
                    addFile(jarOutputStream, it.next());
                }
                if (!z) {
                    if (inputStream2 != null) {
                        java.util.jar.Manifest manifest2 = new java.util.jar.Manifest(inputStream2);
                        z2 = !isAmbiguousMainClass(manifest2);
                        if (z2 && !updateManifest(manifest2, jarOutputStream)) {
                            z2 = false;
                        }
                    } else if (this.ename != null && !updateManifest(new java.util.jar.Manifest(), jarOutputStream)) {
                        z2 = false;
                    }
                }
                zipInputStream.close();
                jarOutputStream.close();
                return z2;
            }
        }
    }

    private void addIndex(JarIndex jarIndex, ZipOutputStream zipOutputStream) throws IOException {
        ZipEntry zipEntry = new ZipEntry("META-INF/INDEX.LIST");
        zipEntry.setTime(System.currentTimeMillis());
        if (this.flag0) {
            CRC32OutputStream cRC32OutputStream = new CRC32OutputStream();
            jarIndex.write(cRC32OutputStream);
            cRC32OutputStream.updateEntry(zipEntry);
        }
        zipOutputStream.putNextEntry(zipEntry);
        jarIndex.write(zipOutputStream);
        zipOutputStream.closeEntry();
    }

    private boolean updateManifest(java.util.jar.Manifest manifest, ZipOutputStream zipOutputStream) throws IOException {
        addVersion(manifest);
        addCreatedBy(manifest);
        if (this.ename != null) {
            addMainClass(manifest, this.ename);
        }
        ZipEntry zipEntry = new ZipEntry("META-INF/MANIFEST.MF");
        zipEntry.setTime(System.currentTimeMillis());
        if (this.flag0) {
            crc32Manifest(zipEntry, manifest);
        }
        zipOutputStream.putNextEntry(zipEntry);
        manifest.write(zipOutputStream);
        if (this.vflag) {
            output(getMsg("out.update.manifest"));
            return true;
        }
        return true;
    }

    private static final boolean isWinDriveLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String safeName(String str) {
        int i;
        if (!this.pflag) {
            int length = str.length();
            int lastIndexOf = str.lastIndexOf("../");
            if (lastIndexOf == -1) {
                i = 0;
            } else {
                i = lastIndexOf + 3;
            }
            if (File.separatorChar != '\\') {
                while (i < length && str.charAt(i) == '/') {
                    i++;
                }
                if (i != 0) {
                    str = str.substring(i);
                }
            }
            while (i < length) {
                int i2 = i;
                if (i + 1 < length && str.charAt(i + 1) == ':' && isWinDriveLetter(str.charAt(i))) {
                    i += 2;
                }
                while (i < length && str.charAt(i) == '/') {
                    i++;
                }
                if (i == i2) {
                    break;
                }
            }
            if (i != 0) {
            }
        }
        return str;
    }

    private String entryName(String str) {
        String replace = str.replace(File.separatorChar, '/');
        String str2 = "";
        for (String str3 : this.paths) {
            if (replace.startsWith(str3) && str3.length() > str2.length()) {
                str2 = str3;
            }
        }
        String safeName = safeName(replace.substring(str2.length()));
        if (safeName.startsWith("./")) {
            safeName = safeName.substring(2);
        }
        return safeName;
    }

    private void addVersion(java.util.jar.Manifest manifest) {
        Attributes mainAttributes = manifest.getMainAttributes();
        if (mainAttributes.getValue(Attributes.Name.MANIFEST_VERSION) == null) {
            mainAttributes.put(Attributes.Name.MANIFEST_VERSION, VERSION);
        }
    }

    private void addCreatedBy(java.util.jar.Manifest manifest) {
        Attributes mainAttributes = manifest.getMainAttributes();
        if (mainAttributes.getValue(new Attributes.Name("Created-By")) == null) {
            mainAttributes.put(new Attributes.Name("Created-By"), System.getProperty("java.version") + " (" + System.getProperty("java.vendor") + RuntimeConstants.SIG_ENDMETHOD);
        }
    }

    private void addMainClass(java.util.jar.Manifest manifest, String str) {
        manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, str);
    }

    private boolean isAmbiguousMainClass(java.util.jar.Manifest manifest) {
        if (this.ename != null && manifest.getMainAttributes().get(Attributes.Name.MAIN_CLASS) != null) {
            error(getMsg("error.bad.eflag"));
            usageError();
            return true;
        }
        return false;
    }

    void addFile(ZipOutputStream zipOutputStream, File file) throws IOException {
        String path = file.getPath();
        boolean isDirectory = file.isDirectory();
        if (isDirectory) {
            path = path.endsWith(File.separator) ? path : path + File.separator;
        }
        String entryName = entryName(path);
        if (entryName.equals("") || entryName.equals(Constants.NAME_SEPARATOR) || entryName.equals(this.zname)) {
            return;
        }
        if ((entryName.equals(MANIFEST_DIR) || entryName.equals("META-INF/MANIFEST.MF")) && !this.Mflag) {
            if (this.vflag) {
                output(formatMsg("out.ignore.entry", entryName));
                return;
            }
            return;
        }
        long length = isDirectory ? 0L : file.length();
        if (this.vflag) {
            this.out.print(formatMsg("out.adding", entryName));
        }
        ZipEntry zipEntry = new ZipEntry(entryName);
        zipEntry.setTime(file.lastModified());
        if (length == 0) {
            zipEntry.setMethod(0);
            zipEntry.setSize(0L);
            zipEntry.setCrc(0L);
        } else if (this.flag0) {
            crc32File(zipEntry, file);
        }
        zipOutputStream.putNextEntry(zipEntry);
        if (!isDirectory) {
            copy(file, zipOutputStream);
        }
        zipOutputStream.closeEntry();
        if (this.vflag) {
            long size = zipEntry.getSize();
            long compressedSize = zipEntry.getCompressedSize();
            this.out.print(formatMsg2("out.size", String.valueOf(size), String.valueOf(compressedSize)));
            if (zipEntry.getMethod() == 8) {
                long j = 0;
                if (size != 0) {
                    j = ((size - compressedSize) * 100) / size;
                }
                output(formatMsg("out.deflated", String.valueOf(j)));
                return;
            }
            output(getMsg("out.stored"));
        }
    }

    private void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        while (true) {
            int read = inputStream.read(this.copyBuf);
            if (read != -1) {
                outputStream.write(this.copyBuf, 0, read);
            } else {
                return;
            }
        }
    }

    private void copy(File file, OutputStream outputStream) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            copy(fileInputStream, outputStream);
            fileInputStream.close();
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    private void copy(InputStream inputStream, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            copy(inputStream, fileOutputStream);
            fileOutputStream.close();
        } catch (Throwable th) {
            fileOutputStream.close();
            throw th;
        }
    }

    private void crc32Manifest(ZipEntry zipEntry, java.util.jar.Manifest manifest) throws IOException {
        CRC32OutputStream cRC32OutputStream = new CRC32OutputStream();
        manifest.write(cRC32OutputStream);
        cRC32OutputStream.updateEntry(zipEntry);
    }

    private void crc32File(ZipEntry zipEntry, File file) throws IOException {
        CRC32OutputStream cRC32OutputStream = new CRC32OutputStream();
        copy(file, cRC32OutputStream);
        if (cRC32OutputStream.n != file.length()) {
            throw new JarException(formatMsg("error.incorrect.length", file.getPath()));
        }
        cRC32OutputStream.updateEntry(zipEntry);
    }

    void replaceFSC(String[] strArr) {
        if (strArr != null) {
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = strArr[i].replace(File.separatorChar, '/');
            }
        }
    }

    Set<ZipEntry> newDirSet() {
        return new HashSet<ZipEntry>() { // from class: sun.tools.jar.Main.1
            @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean add(ZipEntry zipEntry) {
                if (zipEntry == null || Main.useExtractionTime) {
                    return false;
                }
                return super.add((AnonymousClass1) zipEntry);
            }
        };
    }

    void updateLastModifiedTime(Set<ZipEntry> set) throws IOException {
        for (ZipEntry zipEntry : set) {
            long time = zipEntry.getTime();
            if (time != -1) {
                String safeName = safeName(zipEntry.getName().replace(File.separatorChar, '/'));
                if (safeName.length() != 0) {
                    new File(safeName.replace('/', File.separatorChar)).setLastModified(time);
                }
            }
        }
    }

    void extract(InputStream inputStream, String[] strArr) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        Set<ZipEntry> newDirSet = newDirSet();
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry != null) {
                if (strArr == null) {
                    newDirSet.add(extractFile(zipInputStream, nextEntry));
                } else {
                    String name = nextEntry.getName();
                    int length = strArr.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        if (!name.startsWith(strArr[i])) {
                            i++;
                        } else {
                            newDirSet.add(extractFile(zipInputStream, nextEntry));
                            break;
                        }
                    }
                }
            } else {
                updateLastModifiedTime(newDirSet);
                return;
            }
        }
    }

    void extract(String str, String[] strArr) throws IOException {
        ZipFile zipFile = new ZipFile(str);
        Set<ZipEntry> newDirSet = newDirSet();
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry nextElement = entries.nextElement();
            if (strArr == null) {
                newDirSet.add(extractFile(zipFile.getInputStream(nextElement), nextElement));
            } else {
                String name = nextElement.getName();
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    if (!name.startsWith(strArr[i])) {
                        i++;
                    } else {
                        newDirSet.add(extractFile(zipFile.getInputStream(nextElement), nextElement));
                        break;
                    }
                }
            }
        }
        zipFile.close();
        updateLastModifiedTime(newDirSet);
    }

    ZipEntry extractFile(InputStream inputStream, ZipEntry zipEntry) throws IOException {
        ZipEntry zipEntry2 = null;
        String safeName = safeName(zipEntry.getName().replace(File.separatorChar, '/'));
        if (safeName.length() == 0) {
            return null;
        }
        File file = new File(safeName.replace('/', File.separatorChar));
        if (zipEntry.isDirectory()) {
            if (file.exists()) {
                if (!file.isDirectory()) {
                    throw new IOException(formatMsg("error.create.dir", file.getPath()));
                }
            } else {
                if (!file.mkdirs()) {
                    throw new IOException(formatMsg("error.create.dir", file.getPath()));
                }
                zipEntry2 = zipEntry;
            }
            if (this.vflag) {
                output(formatMsg("out.create", safeName));
            }
        } else {
            if (file.getParent() != null) {
                File file2 = new File(file.getParent());
                if ((!file2.exists() && !file2.mkdirs()) || !file2.isDirectory()) {
                    throw new IOException(formatMsg("error.create.dir", file2.getPath()));
                }
            }
            try {
                copy(inputStream, file);
                if (inputStream instanceof ZipInputStream) {
                    ((ZipInputStream) inputStream).closeEntry();
                } else {
                    inputStream.close();
                }
                if (this.vflag) {
                    if (zipEntry.getMethod() == 8) {
                        output(formatMsg("out.inflated", safeName));
                    } else {
                        output(formatMsg("out.extracted", safeName));
                    }
                }
            } catch (Throwable th) {
                if (inputStream instanceof ZipInputStream) {
                    ((ZipInputStream) inputStream).closeEntry();
                } else {
                    inputStream.close();
                }
                throw th;
            }
        }
        if (!useExtractionTime) {
            long time = zipEntry.getTime();
            if (time != -1) {
                file.setLastModified(time);
            }
        }
        return zipEntry2;
    }

    void list(InputStream inputStream, String[] strArr) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry != null) {
                zipInputStream.closeEntry();
                printEntry(nextEntry, strArr);
            } else {
                return;
            }
        }
    }

    void list(String str, String[] strArr) throws IOException {
        ZipFile zipFile = new ZipFile(str);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            printEntry(entries.nextElement(), strArr);
        }
        zipFile.close();
    }

    void dumpIndex(String str, JarIndex jarIndex) throws IOException {
        File file = new File(str);
        Path path = file.toPath();
        Path path2 = createTempFileInSameDirectoryAs(file).toPath();
        try {
            if (update(Files.newInputStream(path, new OpenOption[0]), Files.newOutputStream(path2, new OpenOption[0]), null, jarIndex)) {
                try {
                    Files.move(path2, path, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new IOException(getMsg("error.write.file"), e);
                }
            }
        } finally {
            Files.deleteIfExists(path2);
        }
    }

    List<String> getJarPath(String str) throws IOException {
        java.util.jar.Manifest manifest;
        Attributes mainAttributes;
        String value;
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        this.jarPaths.add(str);
        String substring = str.substring(0, Math.max(0, str.lastIndexOf(47) + 1));
        JarFile jarFile = new JarFile(str.replace('/', File.separatorChar));
        if (jarFile != null && (manifest = jarFile.getManifest()) != null && (mainAttributes = manifest.getMainAttributes()) != null && (value = mainAttributes.getValue(Attributes.Name.CLASS_PATH)) != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(value);
            while (stringTokenizer.hasMoreTokens()) {
                String nextToken = stringTokenizer.nextToken();
                if (!nextToken.endsWith(RuntimeConstants.SIG_PACKAGE)) {
                    String concat = substring.concat(nextToken);
                    if (!this.jarPaths.contains(concat)) {
                        arrayList.addAll(getJarPath(concat));
                    }
                }
            }
        }
        jarFile.close();
        return arrayList;
    }

    void genIndex(String str, String[] strArr) throws IOException {
        List<String> jarPath = getJarPath(str);
        int size = jarPath.size();
        if (size == 1 && strArr != null) {
            for (String str2 : strArr) {
                jarPath.addAll(getJarPath(str2));
            }
            size = jarPath.size();
        }
        dumpIndex(str, new JarIndex((String[]) jarPath.toArray(new String[size])));
    }

    void printEntry(ZipEntry zipEntry, String[] strArr) throws IOException {
        if (strArr == null) {
            printEntry(zipEntry);
            return;
        }
        String name = zipEntry.getName();
        for (String str : strArr) {
            if (name.startsWith(str)) {
                printEntry(zipEntry);
                return;
            }
        }
    }

    void printEntry(ZipEntry zipEntry) throws IOException {
        if (this.vflag) {
            StringBuilder sb = new StringBuilder();
            String l = Long.toString(zipEntry.getSize());
            for (int length = 6 - l.length(); length > 0; length--) {
                sb.append(' ');
            }
            sb.append(l).append(' ').append(new Date(zipEntry.getTime()).toString());
            sb.append(' ').append(zipEntry.getName());
            output(sb.toString());
            return;
        }
        output(zipEntry.getName());
    }

    void usageError() {
        error(getMsg("usage"));
    }

    void fatalError(Exception exc) {
        exc.printStackTrace();
    }

    void fatalError(String str) {
        error(this.program + ": " + str);
    }

    protected void output(String str) {
        this.out.println(str);
    }

    protected void error(String str) {
        this.err.println(str);
    }

    public static void main(String[] strArr) {
        System.exit(new Main(System.out, System.err, "jar").run(strArr) ? 0 : 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:sun/tools/jar/Main$CRC32OutputStream.class */
    public static class CRC32OutputStream extends OutputStream {
        final CRC32 crc = new CRC32();
        long n = 0;

        CRC32OutputStream() {
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            this.crc.update(i);
            this.n++;
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.crc.update(bArr, i, i2);
            this.n += i2;
        }

        public void updateEntry(ZipEntry zipEntry) {
            zipEntry.setMethod(0);
            zipEntry.setSize(this.n);
            zipEntry.setCrc(this.crc.getValue());
        }
    }

    private File createTemporaryFile(String str, String str2) {
        File file = null;
        try {
            file = File.createTempFile(str, str2);
        } catch (IOException | SecurityException e) {
        }
        if (file == null) {
            if (this.fname != null) {
                try {
                    file = File.createTempFile(this.fname, ".tmp" + str2, new File(this.fname).getAbsoluteFile().getParentFile());
                } catch (IOException e2) {
                    fatalError(e2);
                }
            } else {
                fatalError(new IOException(getMsg("error.create.tempfile")));
            }
        }
        return file;
    }
}
