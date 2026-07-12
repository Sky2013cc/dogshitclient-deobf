package com.sun.jarsigner;

import java.net.URI;
import java.security.cert.X509Certificate;
import java.util.zip.ZipFile;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jarsigner/ContentSignerParameters.class */
public interface ContentSignerParameters {
    String[] getCommandLine();

    URI getTimestampingAuthority();

    X509Certificate getTimestampingAuthorityCertificate();

    byte[] getSignature();

    String getSignatureAlgorithm();

    X509Certificate[] getSignerCertificateChain();

    byte[] getContent();

    ZipFile getSource();

    default String getTSAPolicyID() {
        return null;
    }
}
