package org.apache.pdfbox.pdfparser.xref;

/* loaded from: target.jar:org/apache/pdfbox/pdfparser/xref/XReferenceType.class */
public enum XReferenceType {
    FREE(0),
    NORMAL(1),
    OBJECT_STREAM_ENTRY(2);

    private final int numericValue;

    XReferenceType(int numericValue) {
        this.numericValue = numericValue;
    }

    public int getNumericValue() {
        return this.numericValue;
    }
}
