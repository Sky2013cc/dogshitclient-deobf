package org.apache.fontbox.ttf.table.common;

/* loaded from: target.jar:org/apache/fontbox/ttf/table/common/LangSysTable.class */
public class LangSysTable {
    private final int lookupOrder;
    private final int requiredFeatureIndex;
    private final int featureIndexCount;
    private final int[] featureIndices;

    public LangSysTable(int lookupOrder, int requiredFeatureIndex, int featureIndexCount, int[] featureIndices) {
        this.lookupOrder = lookupOrder;
        this.requiredFeatureIndex = requiredFeatureIndex;
        this.featureIndexCount = featureIndexCount;
        this.featureIndices = featureIndices;
    }

    public int getLookupOrder() {
        return this.lookupOrder;
    }

    public int getRequiredFeatureIndex() {
        return this.requiredFeatureIndex;
    }

    public int getFeatureIndexCount() {
        return this.featureIndexCount;
    }

    public int[] getFeatureIndices() {
        return this.featureIndices;
    }

    public String toString() {
        return String.format("LangSysTable[requiredFeatureIndex=%d]", Integer.valueOf(this.requiredFeatureIndex));
    }
}
