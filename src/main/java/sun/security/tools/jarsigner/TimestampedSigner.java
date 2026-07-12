package sun.security.tools.jarsigner;

import com.sun.jarsigner.ContentSigner;
import com.sun.jarsigner.ContentSignerParameters;
import java.io.IOException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import sun.security.pkcs.PKCS7;
import sun.security.util.DerInputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;
import sun.security.x509.AccessDescription;
import sun.security.x509.GeneralName;
import sun.security.x509.URIName;

/* loaded from: target.jar:sun/security/tools/jarsigner/TimestampedSigner.class */
public final class TimestampedSigner extends ContentSigner {
    private static final String SUBJECT_INFO_ACCESS_OID = "1.3.6.1.5.5.7.1.11";
    private static final ObjectIdentifier AD_TIMESTAMPING_Id;

    static {
        ObjectIdentifier objectIdentifier = null;
        try {
            objectIdentifier = new ObjectIdentifier("1.3.6.1.5.5.7.48.3");
        } catch (IOException e) {
        }
        AD_TIMESTAMPING_Id = objectIdentifier;
    }

    @Override // com.sun.jarsigner.ContentSigner
    public byte[] generateSignedData(ContentSignerParameters contentSignerParameters, boolean z, boolean z2) throws NoSuchAlgorithmException, CertificateException, IOException {
        if (contentSignerParameters == null) {
            throw new NullPointerException();
        }
        contentSignerParameters.getSignatureAlgorithm();
        X509Certificate[] signerCertificateChain = contentSignerParameters.getSignerCertificateChain();
        byte[] signature = contentSignerParameters.getSignature();
        byte[] content = z ? null : contentSignerParameters.getContent();
        URI uri = null;
        if (z2) {
            uri = contentSignerParameters.getTimestampingAuthority();
            if (uri == null) {
                uri = getTimestampingURI(contentSignerParameters.getTimestampingAuthorityCertificate());
                if (uri == null) {
                    throw new CertificateException("Subject Information Access extension not found");
                }
            }
        }
        String str = "SHA-256";
        if (contentSignerParameters instanceof JarSignerParameters) {
            str = ((JarSignerParameters) contentSignerParameters).getTSADigestAlg();
        }
        return PKCS7.generateSignedData(signature, signerCertificateChain, content, contentSignerParameters.getSignatureAlgorithm(), uri, contentSignerParameters.getTSAPolicyID(), str);
    }

    public static URI getTimestampingURI(X509Certificate x509Certificate) {
        if (x509Certificate == null) {
            return null;
        }
        try {
            byte[] extensionValue = x509Certificate.getExtensionValue(SUBJECT_INFO_ACCESS_OID);
            if (extensionValue == null) {
                return null;
            }
            for (DerValue derValue : new DerInputStream(new DerInputStream(extensionValue).getOctetString()).getSequence(5)) {
                AccessDescription accessDescription = new AccessDescription(derValue);
                if (accessDescription.getAccessMethod().equals(AD_TIMESTAMPING_Id)) {
                    GeneralName accessLocation = accessDescription.getAccessLocation();
                    if (accessLocation.getType() == 6) {
                        URIName name = accessLocation.getName();
                        if (name.getScheme().equalsIgnoreCase("http") || name.getScheme().equalsIgnoreCase("https")) {
                            return name.getURI();
                        }
                    } else {
                        continue;
                    }
                }
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}
