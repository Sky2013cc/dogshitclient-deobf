package sun.tools.jar;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import sun.net.www.MessageHeader;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:sun/tools/jar/Manifest.class */
public class Manifest {
    private Vector<MessageHeader> entries;
    private byte[] tmpbuf;
    private Hashtable<String, MessageHeader> tableEntries;
    static final String[] hashes = {"SHA"};
    static final byte[] EOL = {13, 10};
    static final boolean debug = false;
    static final String VERSION = "1.0";

    static final void debug(String str) {
    }

    public Manifest() {
        this.entries = new Vector<>();
        this.tmpbuf = new byte[512];
        this.tableEntries = new Hashtable<>();
    }

    public Manifest(byte[] bArr) throws IOException {
        this(new ByteArrayInputStream(bArr), false);
    }

    public Manifest(InputStream inputStream) throws IOException {
        this(inputStream, true);
    }

    public Manifest(InputStream inputStream, boolean z) throws IOException {
        this.entries = new Vector<>();
        this.tmpbuf = new byte[512];
        this.tableEntries = new Hashtable<>();
        inputStream = inputStream.markSupported() ? inputStream : new BufferedInputStream(inputStream);
        while (true) {
            inputStream.mark(1);
            if (inputStream.read() != -1) {
                inputStream.reset();
                MessageHeader messageHeader = new MessageHeader(inputStream);
                if (z) {
                    doHashes(messageHeader);
                }
                addEntry(messageHeader);
            } else {
                return;
            }
        }
    }

    public Manifest(String[] strArr) throws IOException {
        this.entries = new Vector<>();
        this.tmpbuf = new byte[512];
        this.tableEntries = new Hashtable<>();
        MessageHeader messageHeader = new MessageHeader();
        messageHeader.add("Manifest-Version", VERSION);
        messageHeader.add("Created-By", "Manifest JDK " + System.getProperty("java.version"));
        addEntry(messageHeader);
        addFiles(null, strArr);
    }

    public void addEntry(MessageHeader messageHeader) {
        this.entries.addElement(messageHeader);
        String findValue = messageHeader.findValue("Name");
        debug("addEntry for name: " + findValue);
        if (findValue != null) {
            this.tableEntries.put(findValue, messageHeader);
        }
    }

    public MessageHeader getEntry(String str) {
        return this.tableEntries.get(str);
    }

    public MessageHeader entryAt(int i) {
        return this.entries.elementAt(i);
    }

    public Enumeration<MessageHeader> entries() {
        return this.entries.elements();
    }

    public void addFiles(File file, String[] strArr) throws IOException {
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
            if (file2.isDirectory()) {
                addFiles(file2, file2.list());
            } else {
                addFile(file2);
            }
        }
    }

    private final String stdToLocal(String str) {
        return str.replace('/', File.separatorChar);
    }

    private final String localToStd(String str) {
        String replace = str.replace(File.separatorChar, '/');
        if (replace.startsWith("./")) {
            replace = replace.substring(2);
        } else if (replace.startsWith(RuntimeConstants.SIG_PACKAGE)) {
            replace = replace.substring(1);
        }
        return replace;
    }

    public void addFile(File file) throws IOException {
        String localToStd = localToStd(file.getPath());
        if (this.tableEntries.get(localToStd) == null) {
            MessageHeader messageHeader = new MessageHeader();
            messageHeader.add("Name", localToStd);
            addEntry(messageHeader);
        }
    }

    public void doHashes(MessageHeader messageHeader) throws IOException {
        String findValue = messageHeader.findValue("Name");
        if (findValue == null || findValue.endsWith(RuntimeConstants.SIG_PACKAGE)) {
            return;
        }
        for (int i = 0; i < hashes.length; i++) {
            FileInputStream fileInputStream = new FileInputStream(stdToLocal(findValue));
            try {
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance(hashes[i]);
                    while (true) {
                        int read = fileInputStream.read(this.tmpbuf, 0, this.tmpbuf.length);
                        if (read == -1) {
                            break;
                        } else {
                            messageDigest.update(this.tmpbuf, 0, read);
                        }
                    }
                    messageHeader.set(hashes[i] + "-Digest", Base64.getMimeEncoder().encodeToString(messageDigest.digest()));
                    fileInputStream.close();
                } catch (NoSuchAlgorithmException e) {
                    throw new JarException("Digest algorithm " + hashes[i] + " not available.");
                }
            } catch (Throwable th) {
                fileInputStream.close();
                throw th;
            }
        }
    }

    public void stream(OutputStream outputStream) throws IOException {
        PrintStream printStream;
        if (outputStream instanceof PrintStream) {
            printStream = (PrintStream) outputStream;
        } else {
            printStream = new PrintStream(outputStream);
        }
        MessageHeader elementAt = this.entries.elementAt(0);
        if (elementAt.findValue("Manifest-Version") == null) {
            String property = System.getProperty("java.version");
            if (elementAt.findValue("Name") == null) {
                elementAt.prepend("Manifest-Version", VERSION);
                elementAt.add("Created-By", "Manifest JDK " + property);
            } else {
                printStream.print("Manifest-Version: 1.0\r\nCreated-By: " + property + "\r\n\r\n");
            }
            printStream.flush();
        }
        elementAt.print(printStream);
        for (int i = 1; i < this.entries.size(); i++) {
            this.entries.elementAt(i).print(printStream);
        }
    }

    public static boolean isManifestName(String str) {
        if (str.charAt(0) == '/') {
            str = str.substring(1, str.length());
        }
        if (str.toUpperCase().equals("META-INF/MANIFEST.MF")) {
            return true;
        }
        return false;
    }
}
