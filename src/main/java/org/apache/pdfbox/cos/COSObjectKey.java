package org.apache.pdfbox.cos;

/* loaded from: target.jar:org/apache/pdfbox/cos/COSObjectKey.class */
public final class COSObjectKey implements Comparable<COSObjectKey> {
    private static final int NUMBER_OFFSET = 16;
    private static final long GENERATION_MASK = ((long) Math.pow(2.0d, 16.0d)) - 1;
    private final long numberAndGeneration;
    private final int streamIndex;

    public COSObjectKey(long num, int gen) {
        if (num < 0) {
            throw new IllegalArgumentException("Object number must not be a negative value");
        }
        if (gen < 0) {
            throw new IllegalArgumentException("Generation number must not be a negative value");
        }
        this.numberAndGeneration = (num << 16) | (gen & GENERATION_MASK);
        this.streamIndex = -1;
    }

    public COSObjectKey(long num, int gen, int index) {
        if (num < 0) {
            throw new IllegalArgumentException("Object number must not be a negative value");
        }
        if (gen < 0) {
            throw new IllegalArgumentException("Generation number must not be a negative value");
        }
        this.numberAndGeneration = computeInternalHash(num, gen);
        this.streamIndex = index;
    }

    public static final long computeInternalHash(long num, int gen) {
        return (num << 16) | (gen & GENERATION_MASK);
    }

    public long getInternalHash() {
        return this.numberAndGeneration;
    }

    public boolean equals(Object obj) {
        COSObjectKey objToBeCompared = obj instanceof COSObjectKey ? (COSObjectKey) obj : null;
        return objToBeCompared != null && objToBeCompared.numberAndGeneration == this.numberAndGeneration;
    }

    public int getGeneration() {
        return (int) (this.numberAndGeneration & GENERATION_MASK);
    }

    public long getNumber() {
        return this.numberAndGeneration >>> 16;
    }

    public int getStreamIndex() {
        return this.streamIndex;
    }

    public int hashCode() {
        return Long.hashCode(this.numberAndGeneration);
    }

    public String toString() {
        return getNumber() + " " + getGeneration() + " R";
    }

    @Override // java.lang.Comparable
    public int compareTo(COSObjectKey other) {
        return Long.compare(this.numberAndGeneration, other.numberAndGeneration);
    }
}
