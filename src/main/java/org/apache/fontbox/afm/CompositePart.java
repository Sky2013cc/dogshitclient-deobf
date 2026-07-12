package org.apache.fontbox.afm;

/* loaded from: target.jar:org/apache/fontbox/afm/CompositePart.class */
public class CompositePart {
    private final String name;
    private final int xDisplacement;
    private final int yDisplacement;

    public CompositePart(String name, int xDisplacement, int yDisplacement) {
        this.name = name;
        this.xDisplacement = xDisplacement;
        this.yDisplacement = yDisplacement;
    }

    public String getName() {
        return this.name;
    }

    public int getXDisplacement() {
        return this.xDisplacement;
    }

    public int getYDisplacement() {
        return this.yDisplacement;
    }
}
