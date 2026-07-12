package org.apache.pdfbox.cos;

import java.io.IOException;
import org.apache.pdfbox.io.RandomAccessReadView;

/* loaded from: target.jar:org/apache/pdfbox/cos/ICOSParser.class */
public interface ICOSParser {
    COSBase dereferenceCOSObject(COSObject cOSObject) throws IOException;

    RandomAccessReadView createRandomAccessReadView(long j, long j2) throws IOException;
}
