package de.erichseifert.vectorgraphics2d;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/Document.class */
public interface Document {
    void writeTo(OutputStream outputStream) throws IOException;

    boolean isCompressed();
}
