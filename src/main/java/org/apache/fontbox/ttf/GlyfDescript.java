package org.apache.fontbox.ttf;

import java.io.IOException;

/* loaded from: target.jar:org/apache/fontbox/ttf/GlyfDescript.class */
public abstract class GlyfDescript implements GlyphDescription {
    public static final byte ON_CURVE = 1;
    public static final byte X_SHORT_VECTOR = 2;
    public static final byte Y_SHORT_VECTOR = 4;
    public static final byte REPEAT = 8;
    public static final byte X_DUAL = 16;
    public static final byte Y_DUAL = 32;
    private int[] instructions;
    private final int contourCount;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GlyfDescript(short numberOfContours) {
        this.contourCount = numberOfContours;
    }

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public void resolve() {
    }

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public int getContourCount() {
        return this.contourCount;
    }

    public int[] getInstructions() {
        return this.instructions;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void readInstructions(TTFDataStream bais, int count) throws IOException {
        this.instructions = bais.readUnsignedByteArray(count);
    }
}
