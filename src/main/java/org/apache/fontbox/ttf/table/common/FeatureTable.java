package org.apache.fontbox.ttf.table.common;

/* loaded from: target.jar:org/apache/fontbox/ttf/table/common/FeatureTable.class */
public class FeatureTable {
    private final int featureParams;
    private final int lookupIndexCount;
    private final int[] lookupListIndices;

    public FeatureTable(int featureParams, int lookupIndexCount, int[] lookupListIndices) {
        this.featureParams = featureParams;
        this.lookupIndexCount = lookupIndexCount;
        this.lookupListIndices = lookupListIndices;
    }

    public int getFeatureParams() {
        return this.featureParams;
    }

    public int getLookupIndexCount() {
        return this.lookupIndexCount;
    }

    public int[] getLookupListIndices() {
        return this.lookupListIndices;
    }

    public String toString() {
        return String.format("FeatureTable[lookupListIndicesCount=%d]", Integer.valueOf(this.lookupListIndices.length));
    }
}
