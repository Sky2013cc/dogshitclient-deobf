package org.apache.pdfbox.pdfparser.xref;

import org.apache.pdfbox.cos.COSObjectKey;

/* loaded from: target.jar:org/apache/pdfbox/pdfparser/xref/FreeXReference.class */
public class FreeXReference extends AbstractXReference {
    public static final FreeXReference NULL_ENTRY = new FreeXReference(new COSObjectKey(0, 65535), 0);
    private final COSObjectKey key;
    private final long nextFreeObject;

    public FreeXReference(COSObjectKey key, long nextFreeObject) {
        super(XReferenceType.FREE);
        this.key = key;
        this.nextFreeObject = nextFreeObject;
    }

    @Override // org.apache.pdfbox.pdfparser.xref.XReferenceEntry
    public COSObjectKey getReferencedKey() {
        return this.key;
    }

    @Override // org.apache.pdfbox.pdfparser.xref.XReferenceEntry
    public long getSecondColumnValue() {
        return this.nextFreeObject;
    }

    @Override // org.apache.pdfbox.pdfparser.xref.XReferenceEntry
    public long getThirdColumnValue() {
        return getReferencedKey().getGeneration();
    }

    public String toString() {
        return "FreeReference{key=" + this.key + ", nextFreeObject=" + this.nextFreeObject + ", type=" + getType().getNumericValue() + " }";
    }
}
