package de.erichseifert.vectorgraphics2d.util;

import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/util/FlateEncodeStream.class */
public class FlateEncodeStream extends DeflaterOutputStream {
    public FlateEncodeStream(OutputStream outputStream) {
        super(outputStream);
    }
}
