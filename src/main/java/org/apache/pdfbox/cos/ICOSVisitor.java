package org.apache.pdfbox.cos;

import java.io.IOException;

/* loaded from: target.jar:org/apache/pdfbox/cos/ICOSVisitor.class */
public interface ICOSVisitor {
    void visitFromArray(COSArray cOSArray) throws IOException;

    void visitFromBoolean(COSBoolean cOSBoolean) throws IOException;

    void visitFromDictionary(COSDictionary cOSDictionary) throws IOException;

    void visitFromDocument(COSDocument cOSDocument) throws IOException;

    void visitFromFloat(COSFloat cOSFloat) throws IOException;

    void visitFromInt(COSInteger cOSInteger) throws IOException;

    void visitFromName(COSName cOSName) throws IOException;

    void visitFromNull(COSNull cOSNull) throws IOException;

    void visitFromStream(COSStream cOSStream) throws IOException;

    void visitFromString(COSString cOSString) throws IOException;
}
