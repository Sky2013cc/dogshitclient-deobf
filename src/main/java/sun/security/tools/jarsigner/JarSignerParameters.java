package sun.security.tools.jarsigner;

import com.sun.jarsigner.ContentSignerParameters;
import java.net.URI;
import java.security.cert.X509Certificate;
import java.util.zip.ZipFile;

/* loaded from: target.jar:sun/security/tools/jarsigner/JarSignerParameters.class */
class JarSignerParameters implements ContentSignerParameters {
    private String[] args;
    private URI tsa;
    private X509Certificate tsaCertificate;
    private byte[] signature;
    private String signatureAlgorithm;
    private X509Certificate[] signerCertificateChain;
    private byte[] content;
    private ZipFile source;
    private String tSAPolicyID;
    private String tSADigestAlg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JarSignerParameters(String[] strArr, URI uri, X509Certificate x509Certificate, String str, String str2, byte[] bArr, String str3, X509Certificate[] x509CertificateArr, byte[] bArr2, ZipFile zipFile) {
        if (bArr == null || str3 == null || x509CertificateArr == null || str2 == null) {
            throw new NullPointerException();
        }
        this.args = strArr;
        this.tsa = uri;
        this.tsaCertificate = x509Certificate;
        this.tSAPolicyID = str;
        this.tSADigestAlg = str2;
        this.signature = bArr;
        this.signatureAlgorithm = str3;
        this.signerCertificateChain = x509CertificateArr;
        this.content = bArr2;
        this.source = zipFile;
    }

    @Override // com.sun.jarsigner.ContentSignerParameters
    public String[] getCommandLine() {
        return this.args;
    }

    @Override // com.sun.jarsigner.ContentSignerParameters
    public URI getTimestampingAuthority() {
        return this.tsa;
    }

    @Override // com.sun.jarsigner.ContentSignerParameters
    public X509Certificate getTimestampingAuthorityCertificate() {
        return this.tsaCertificate;
    }

    @Override // com.sun.jarsigner.ContentSignerParameters
    public String getTSAPolicyID() {
        return this.tSAPolicyID;
    }

    public String getTSADigestAlg() {
        return this.tSADigestAlg;
    }

    @Override // com.sun.jarsigner.ContentSignerParameters
    public byte[] getSignature() {
        return this.signature;
    }

    @Override // com.sun.jarsigner.ContentSignerParameters
    public String getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    @Override // com.sun.jarsigner.ContentSignerParameters
    public X509Certificate[] getSignerCertificateChain() {
        return this.signerCertificateChain;
    }

    @Override // com.sun.jarsigner.ContentSignerParameters
    public byte[] getContent() {
        return this.content;
    }

    @Override // com.sun.jarsigner.ContentSignerParameters
    public ZipFile getSource() {
        return this.source;
    }
}
