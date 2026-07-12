package sun.tools.jar;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Vector;
import sun.net.www.MessageHeader;
import sun.rmi.rmic.iiop.Constants;
import sun.security.pkcs.PKCS7;
import sun.security.x509.AlgorithmId;

/* loaded from: target.jar:sun/tools/jar/SignatureFile.class */
public class SignatureFile {
    static final boolean debug = false;
    private Vector<MessageHeader> entries;
    static final String[] hashes = {"SHA"};
    private Manifest manifest;
    private String rawName;
    private PKCS7 signatureBlock;
    private Hashtable<String, MessageDigest> digests;

    static final void debug(String str) {
    }

    private SignatureFile(String str) throws JarException {
        this.entries = new Vector<>();
        this.digests = new Hashtable<>();
        this.entries = new Vector<>();
        if (str != null) {
            if (str.length() > 8 || str.indexOf(46) != -1) {
                throw new JarException("invalid file name");
            }
            this.rawName = str.toUpperCase(Locale.ENGLISH);
        }
    }

    private SignatureFile(String str, boolean z) throws JarException {
        this(str);
        if (z) {
            MessageHeader messageHeader = new MessageHeader();
            messageHeader.set("Signature-Version", "1.0");
            this.entries.addElement(messageHeader);
        }
    }

    public SignatureFile(Manifest manifest, String str) throws JarException {
        this(str, true);
        this.manifest = manifest;
        Enumeration<MessageHeader> entries = manifest.entries();
        while (entries.hasMoreElements()) {
            String findValue = entries.nextElement().findValue("Name");
            if (findValue != null) {
                add(findValue);
            }
        }
    }

    public SignatureFile(Manifest manifest, String[] strArr, String str) throws JarException {
        this(str, true);
        this.manifest = manifest;
        add(strArr);
    }

    public SignatureFile(InputStream inputStream, String str) throws IOException {
        this(str);
        while (inputStream.available() > 0) {
            this.entries.addElement(new MessageHeader(inputStream));
        }
    }

    public SignatureFile(InputStream inputStream) throws IOException {
        this(inputStream, (String) null);
    }

    public SignatureFile(byte[] bArr) throws IOException {
        this(new ByteArrayInputStream(bArr));
    }

    public String getName() {
        return "META-INF/" + this.rawName + ".SF";
    }

    public String getBlockName() {
        String str = "DSA";
        if (this.signatureBlock != null) {
            str = this.signatureBlock.getSignerInfos()[0].getDigestEncryptionAlgorithmId().getName();
            String encAlgFromSigAlg = AlgorithmId.getEncAlgFromSigAlg(str);
            if (encAlgFromSigAlg != null) {
                str = encAlgFromSigAlg;
            }
        }
        return "META-INF/" + this.rawName + Constants.NAME_SEPARATOR + str;
    }

    public PKCS7 getBlock() {
        return this.signatureBlock;
    }

    public void setBlock(PKCS7 pkcs7) {
        this.signatureBlock = pkcs7;
    }

    public void add(String[] strArr) throws JarException {
        for (String str : strArr) {
            add(str);
        }
    }

    public void add(String str) throws JarException {
        MessageHeader entry = this.manifest.getEntry(str);
        if (entry == null) {
            throw new JarException("entry " + str + " not in manifest");
        }
        try {
            this.entries.addElement(computeEntry(entry));
        } catch (IOException e) {
            throw new JarException(e.getMessage());
        }
    }

    public MessageHeader getEntry(String str) {
        Enumeration<MessageHeader> entries = entries();
        while (entries.hasMoreElements()) {
            MessageHeader nextElement = entries.nextElement();
            if (str.equals(nextElement.findValue("Name"))) {
                return nextElement;
            }
        }
        return null;
    }

    public MessageHeader entryAt(int i) {
        return this.entries.elementAt(i);
    }

    public Enumeration<MessageHeader> entries() {
        return this.entries.elements();
    }

    private MessageHeader computeEntry(MessageHeader messageHeader) throws IOException {
        MessageHeader messageHeader2 = new MessageHeader();
        String findValue = messageHeader.findValue("Name");
        if (findValue == null) {
            return null;
        }
        messageHeader2.set("Name", findValue);
        for (int i = 0; i < hashes.length; i++) {
            try {
                MessageDigest digest = getDigest(hashes[i]);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                messageHeader.print(new PrintStream(byteArrayOutputStream));
                messageHeader2.set(hashes[i] + "-Digest", Base64.getMimeEncoder().encodeToString(digest.digest(byteArrayOutputStream.toByteArray())));
            } catch (NoSuchAlgorithmException e) {
                throw new JarException(e.getMessage());
            }
        }
        return messageHeader2;
    }

    private MessageDigest getDigest(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = this.digests.get(str);
        if (messageDigest == null) {
            messageDigest = MessageDigest.getInstance(str);
            this.digests.put(str, messageDigest);
        }
        messageDigest.reset();
        return messageDigest;
    }

    public void stream(OutputStream outputStream) throws IOException {
        MessageHeader elementAt = this.entries.elementAt(0);
        if (elementAt.findValue("Signature-Version") == null) {
            throw new JarException("Signature file requires Signature-Version: 1.0 in 1st header");
        }
        PrintStream printStream = new PrintStream(outputStream);
        elementAt.print(printStream);
        for (int i = 1; i < this.entries.size(); i++) {
            this.entries.elementAt(i).print(printStream);
        }
    }
}
