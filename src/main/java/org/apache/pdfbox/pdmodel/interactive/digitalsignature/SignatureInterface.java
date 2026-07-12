package org.apache.pdfbox.pdmodel.interactive.digitalsignature;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: target.jar:org/apache/pdfbox/pdmodel/interactive/digitalsignature/SignatureInterface.class */
public interface SignatureInterface {
    byte[] sign(InputStream inputStream) throws IOException;
}
