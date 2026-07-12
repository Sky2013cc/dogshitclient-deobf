package sun.security.tools.jarsigner;

import com.sun.jarsigner.ContentSigner;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.zip.ZipFile;
import sun.rmi.rmic.iiop.Constants;
import sun.security.util.ManifestDigester;
import sun.security.x509.AlgorithmId;
import sun.security.x509.X500Name;
import sun.tools.java.RuntimeConstants;

/* compiled from: Main.java */
/* loaded from: target.jar:sun/security/tools/jarsigner/SignatureFile.class */
class SignatureFile {
    Manifest sf;
    String baseName;

    public SignatureFile(MessageDigest[] messageDigestArr, Manifest manifest, ManifestDigester manifestDigester, String str, boolean z) {
        this.baseName = str;
        String property = System.getProperty("java.version");
        String property2 = System.getProperty("java.vendor");
        this.sf = new Manifest();
        Attributes mainAttributes = this.sf.getMainAttributes();
        mainAttributes.putValue(Attributes.Name.SIGNATURE_VERSION.toString(), "1.0");
        mainAttributes.putValue("Created-By", property + " (" + property2 + RuntimeConstants.SIG_ENDMETHOD);
        if (z) {
            for (int i = 0; i < messageDigestArr.length; i++) {
                mainAttributes.putValue(messageDigestArr[i].getAlgorithm() + "-Digest-Manifest", Base64.getEncoder().encodeToString(manifestDigester.manifestDigest(messageDigestArr[i])));
            }
        }
        ManifestDigester.Entry entry = manifestDigester.get("Manifest-Main-Attributes", false);
        if (entry != null) {
            for (int i2 = 0; i2 < messageDigestArr.length; i2++) {
                mainAttributes.putValue(messageDigestArr[i2].getAlgorithm() + "-Digest-Manifest-Main-Attributes", Base64.getEncoder().encodeToString(entry.digest(messageDigestArr[i2])));
            }
            Map<String, Attributes> entries = this.sf.getEntries();
            Iterator<Map.Entry<String, Attributes>> it = manifest.getEntries().entrySet().iterator();
            while (it.hasNext()) {
                String key = it.next().getKey();
                ManifestDigester.Entry entry2 = manifestDigester.get(key, false);
                if (entry2 != null) {
                    Attributes attributes = new Attributes();
                    for (int i3 = 0; i3 < messageDigestArr.length; i3++) {
                        attributes.putValue(messageDigestArr[i3].getAlgorithm() + "-Digest", Base64.getEncoder().encodeToString(entry2.digest(messageDigestArr[i3])));
                    }
                    entries.put(key, attributes);
                }
            }
            return;
        }
        throw new IllegalStateException("ManifestDigester failed to create Manifest-Main-Attribute entry");
    }

    public void write(OutputStream outputStream) throws IOException {
        this.sf.write(outputStream);
    }

    public String getMetaName() {
        return "META-INF/" + this.baseName + ".SF";
    }

    public String getBaseName() {
        return this.baseName;
    }

    public Block generateBlock(PrivateKey privateKey, String str, X509Certificate[] x509CertificateArr, boolean z, String str2, X509Certificate x509Certificate, String str3, String str4, ContentSigner contentSigner, String[] strArr, ZipFile zipFile) throws NoSuchAlgorithmException, InvalidKeyException, IOException, SignatureException, CertificateException {
        return new Block(this, privateKey, str, x509CertificateArr, z, str2, x509Certificate, str3, str4, contentSigner, strArr, zipFile);
    }

    /* compiled from: Main.java */
    /* loaded from: target.jar:sun/security/tools/jarsigner/SignatureFile$Block.class */
    public static class Block {
        private byte[] block;
        private String blockFileName;

        Block(SignatureFile signatureFile, PrivateKey privateKey, String str, X509Certificate[] x509CertificateArr, boolean z, String str2, X509Certificate x509Certificate, String str3, String str4, ContentSigner contentSigner, String[] strArr, ZipFile zipFile) throws NoSuchAlgorithmException, InvalidKeyException, IOException, SignatureException, CertificateException {
            if (!(x509CertificateArr[0].getIssuerDN() instanceof X500Name)) {
            }
            x509CertificateArr[0].getSerialNumber();
            String algorithm = privateKey.getAlgorithm();
            String upperCase = str.toUpperCase(Locale.ENGLISH);
            if ((upperCase.endsWith("WITHRSA") && !algorithm.equalsIgnoreCase("RSA")) || ((upperCase.endsWith("WITHECDSA") && !algorithm.equalsIgnoreCase("EC")) || (upperCase.endsWith("WITHDSA") && !algorithm.equalsIgnoreCase("DSA")))) {
                throw new SignatureException("private key algorithm is not compatible with signature algorithm");
            }
            this.blockFileName = "META-INF/" + signatureFile.getBaseName() + Constants.NAME_SEPARATOR + algorithm;
            AlgorithmId.get(str);
            AlgorithmId.get(algorithm);
            Signature signature = Signature.getInstance(str);
            signature.initSign(privateKey);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            signatureFile.write(byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            signature.update(byteArray);
            byte[] sign = signature.sign();
            contentSigner = contentSigner == null ? new TimestampedSigner() : contentSigner;
            URI uri = null;
            if (str2 != null) {
                try {
                    uri = new URI(str2);
                } catch (URISyntaxException e) {
                    throw new IOException(e);
                }
            }
            this.block = contentSigner.generateSignedData(new JarSignerParameters(strArr, uri, x509Certificate, str3, str4, sign, str, x509CertificateArr, byteArray, zipFile), z, (str2 == null && x509Certificate == null) ? false : true);
        }

        public String getMetaName() {
            return this.blockFileName;
        }

        public void write(OutputStream outputStream) throws IOException {
            outputStream.write(this.block);
        }
    }
}
