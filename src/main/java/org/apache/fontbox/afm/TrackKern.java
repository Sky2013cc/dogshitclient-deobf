package org.apache.fontbox.afm;

/* loaded from: target.jar:org/apache/fontbox/afm/TrackKern.class */
public class TrackKern {
    private final int degree;
    private final float minPointSize;
    private final float minKern;
    private final float maxPointSize;
    private final float maxKern;

    public TrackKern(int degree, float minPointSize, float minKern, float maxPointSize, float maxKern) {
        this.degree = degree;
        this.minPointSize = minPointSize;
        this.minKern = minKern;
        this.maxPointSize = maxPointSize;
        this.maxKern = maxKern;
    }

    public int getDegree() {
        return this.degree;
    }

    public float getMaxKern() {
        return this.maxKern;
    }

    public float getMaxPointSize() {
        return this.maxPointSize;
    }

    public float getMinKern() {
        return this.minKern;
    }

    public float getMinPointSize() {
        return this.minPointSize;
    }
}
