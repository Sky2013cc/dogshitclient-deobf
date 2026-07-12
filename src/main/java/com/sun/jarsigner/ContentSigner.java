package com.sun.jarsigner;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jarsigner/ContentSigner.class */
public abstract class ContentSigner {
    public abstract byte[] generateSignedData(ContentSignerParameters contentSignerParameters, boolean z, boolean z2) throws NoSuchAlgorithmException, CertificateException, IOException;
}
