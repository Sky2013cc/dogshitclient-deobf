package org.apache.pdfbox.cos;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: target.jar:org/apache/pdfbox/cos/COSBoolean.class */
public final class COSBoolean extends COSBase {
    private static final byte[] TRUE_BYTES = {116, 114, 117, 101};
    private static final byte[] FALSE_BYTES = {102, 97, 108, 115, 101};
    public static final COSBoolean TRUE = new COSBoolean(true);
    public static final COSBoolean FALSE = new COSBoolean(false);
    private final boolean value;

    private COSBoolean(boolean aValue) {
        this.value = aValue;
    }

    public boolean getValue() {
        return this.value;
    }

    public Boolean getValueAsObject() {
        return this.value ? Boolean.TRUE : Boolean.FALSE;
    }

    public static COSBoolean getBoolean(boolean value) {
        return value ? TRUE : FALSE;
    }

    public static COSBoolean getBoolean(Boolean value) {
        return getBoolean(value.booleanValue());
    }

    @Override // org.apache.pdfbox.cos.COSBase
    public void accept(ICOSVisitor visitor) throws IOException {
        visitor.visitFromBoolean(this);
    }

    public String toString() {
        return String.valueOf(this.value);
    }

    public int hashCode() {
        return this.value ? 1231 : 1237;
    }

    public boolean equals(Object obj) {
        return this == obj;
    }

    public void writePDF(OutputStream output) throws IOException {
        if (this.value) {
            output.write(TRUE_BYTES);
        } else {
            output.write(FALSE_BYTES);
        }
    }
}
