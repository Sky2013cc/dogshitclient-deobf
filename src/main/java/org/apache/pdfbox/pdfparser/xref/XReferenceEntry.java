package org.apache.pdfbox.pdfparser.xref;

import org.apache.pdfbox.cos.COSObjectKey;

/* loaded from: target.jar:org/apache/pdfbox/pdfparser/xref/XReferenceEntry.class */
public interface XReferenceEntry extends Comparable<XReferenceEntry> {
    XReferenceType getType();

    COSObjectKey getReferencedKey();

    long getFirstColumnValue();

    long getSecondColumnValue();

    long getThirdColumnValue();
}
