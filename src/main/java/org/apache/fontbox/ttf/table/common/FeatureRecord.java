package org.apache.fontbox.ttf.table.common;

/* loaded from: target.jar:org/apache/fontbox/ttf/table/common/FeatureRecord.class */
public class FeatureRecord {
    private final String featureTag;
    private final FeatureTable featureTable;

    public FeatureRecord(String featureTag, FeatureTable featureTable) {
        this.featureTag = featureTag;
        this.featureTable = featureTable;
    }

    public String getFeatureTag() {
        return this.featureTag;
    }

    public FeatureTable getFeatureTable() {
        return this.featureTable;
    }

    public String toString() {
        return String.format("FeatureRecord[featureTag=%s]", this.featureTag);
    }
}
