package org.apache.pdfbox.pdmodel.encryption;

import java.io.IOException;
import java.security.Provider;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* loaded from: target.jar:org/apache/pdfbox/pdmodel/encryption/SecurityProvider.class */
public class SecurityProvider {
    private static Provider provider = null;

    private SecurityProvider() {
    }

    public static Provider getProvider() throws IOException {
        if (provider == null) {
            provider = new BouncyCastleProvider();
        }
        return provider;
    }

    public static void setProvider(Provider provider2) {
        provider = provider2;
    }
}
