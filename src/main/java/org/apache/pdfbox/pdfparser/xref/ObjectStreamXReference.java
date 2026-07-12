package org.apache.pdfbox.pdfparser.xref;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSObjectKey;

/* loaded from: target.jar:org/apache/pdfbox/pdfparser/xref/ObjectStreamXReference.class */
public class ObjectStreamXReference extends AbstractXReference {
    private final int objectStreamIndex;
    private final COSObjectKey key;
    private final COSBase object;
    private final COSObjectKey parentKey;

    public ObjectStreamXReference(int objectStreamIndex, COSObjectKey key, COSBase object, COSObjectKey parentKey) {
        super(XReferenceType.OBJECT_STREAM_ENTRY);
        this.objectStreamIndex = objectStreamIndex;
        this.key = key;
        this.object = object;
        this.parentKey = parentKey;
    }

    public int getObjectStreamIndex() {
        return this.objectStreamIndex;
    }

    @Override // org.apache.pdfbox.pdfparser.xref.XReferenceEntry
    public COSObjectKey getReferencedKey() {
        return this.key;
    }

    public COSBase getObject() {
        return this.object;
    }

    public COSObjectKey getParentKey() {
        return this.parentKey;
    }

    @Override // org.apache.pdfbox.pdfparser.xref.XReferenceEntry
    public long getSecondColumnValue() {
        return getParentKey().getNumber();
    }

    @Override // org.apache.pdfbox.pdfparser.xref.XReferenceEntry
    public long getThirdColumnValue() {
        return getObjectStreamIndex();
    }

    public String toString() {
        return "ObjectStreamEntry{ key=" + this.key + ", type=" + getType().getNumericValue() + ", objectStreamIndex=" + this.objectStreamIndex + ", parent=" + this.parentKey + " }";
    }
}
