package org.apache.fontbox.afm;

/* loaded from: target.jar:org/apache/fontbox/afm/KernPair.class */
public class KernPair {
    private final String firstKernCharacter;
    private final String secondKernCharacter;
    private final float x;
    private final float y;

    public KernPair(String firstKernCharacter, String secondKernCharacter, float x, float y) {
        this.firstKernCharacter = firstKernCharacter;
        this.secondKernCharacter = secondKernCharacter;
        this.x = x;
        this.y = y;
    }

    public String getFirstKernCharacter() {
        return this.firstKernCharacter;
    }

    public String getSecondKernCharacter() {
        return this.secondKernCharacter;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }
}
