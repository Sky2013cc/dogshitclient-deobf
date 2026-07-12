package org.apache.pdfbox.cos;

/* loaded from: target.jar:org/apache/pdfbox/cos/COSDocumentState.class */
public class COSDocumentState {
    private boolean parsing = true;

    public void setParsing(boolean parsing) {
        this.parsing = parsing;
    }

    public boolean isAcceptingUpdates() {
        return !this.parsing;
    }
}
