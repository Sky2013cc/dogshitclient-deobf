package org.apache.pdfbox.pdmodel.common;

/* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/PDImmutableRectangle.class */
public final class PDImmutableRectangle extends PDRectangle {
    public PDImmutableRectangle(float width, float height) {
        super(width, height);
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDRectangle
    public void setUpperRightY(float value) {
        throw new UnsupportedOperationException("Immutable class");
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDRectangle
    public void setUpperRightX(float value) {
        throw new UnsupportedOperationException("Immutable class");
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDRectangle
    public void setLowerLeftY(float value) {
        throw new UnsupportedOperationException("Immutable class");
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDRectangle
    public void setLowerLeftX(float value) {
        throw new UnsupportedOperationException("Immutable class");
    }
}
