package org.apache.fontbox.ttf.table.common;

/* loaded from: target.jar:org/apache/fontbox/ttf/table/common/LookupTable.class */
public class LookupTable {
    private final int lookupType;
    private final int lookupFlag;
    private final int markFilteringSet;
    private final LookupSubTable[] subTables;

    public LookupTable(int lookupType, int lookupFlag, int markFilteringSet, LookupSubTable[] subTables) {
        this.lookupType = lookupType;
        this.lookupFlag = lookupFlag;
        this.markFilteringSet = markFilteringSet;
        this.subTables = subTables;
    }

    public int getLookupType() {
        return this.lookupType;
    }

    public int getLookupFlag() {
        return this.lookupFlag;
    }

    public int getMarkFilteringSet() {
        return this.markFilteringSet;
    }

    public LookupSubTable[] getSubTables() {
        return this.subTables;
    }

    public String toString() {
        return String.format("LookupTable[lookupType=%d,lookupFlag=%d,markFilteringSet=%d]", Integer.valueOf(this.lookupType), Integer.valueOf(this.lookupFlag), Integer.valueOf(this.markFilteringSet));
    }
}
