package org.apache.pdfbox.pdfparser.xref;

/* loaded from: target.jar:org/apache/pdfbox/pdfparser/xref/AbstractXReference.class */
public abstract class AbstractXReference implements XReferenceEntry {
    private final XReferenceType type;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractXReference(XReferenceType type) {
        this.type = type;
    }

    @Override // org.apache.pdfbox.pdfparser.xref.XReferenceEntry
    public XReferenceType getType() {
        return this.type;
    }

    @Override // org.apache.pdfbox.pdfparser.xref.XReferenceEntry
    public long getFirstColumnValue() {
        return getType().getNumericValue();
    }

    @Override // java.lang.Comparable
    public int compareTo(XReferenceEntry xReferenceEntry) {
        if (getReferencedKey() == null) {
            return -1;
        }
        if (xReferenceEntry == null || xReferenceEntry.getReferencedKey() == null) {
            return 1;
        }
        return getReferencedKey().compareTo(xReferenceEntry.getReferencedKey());
    }
}
