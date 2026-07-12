package org.apache.fontbox.afm;

/* loaded from: target.jar:org/apache/fontbox/afm/Ligature.class */
public class Ligature {
    private final String successor;
    private final String liga;

    public Ligature(String successor, String ligature) {
        this.successor = successor;
        this.liga = ligature;
    }

    public String getLigature() {
        return this.liga;
    }

    public String getSuccessor() {
        return this.successor;
    }
}
