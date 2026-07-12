package org.apache.fontbox.ttf.table.common;

/* loaded from: target.jar:org/apache/fontbox/ttf/table/common/LookupSubTable.class */
public abstract class LookupSubTable {
    private final int substFormat;
    private final CoverageTable coverageTable;

    public abstract int doSubstitution(int i, int i2);

    public LookupSubTable(int substFormat, CoverageTable coverageTable) {
        this.substFormat = substFormat;
        this.coverageTable = coverageTable;
    }

    public int getSubstFormat() {
        return this.substFormat;
    }

    public CoverageTable getCoverageTable() {
        return this.coverageTable;
    }
}
