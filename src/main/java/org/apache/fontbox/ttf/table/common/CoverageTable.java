package org.apache.fontbox.ttf.table.common;

/* loaded from: target.jar:org/apache/fontbox/ttf/table/common/CoverageTable.class */
public abstract class CoverageTable {
    private final int coverageFormat;

    public abstract int getCoverageIndex(int i);

    public abstract int getGlyphId(int i);

    public abstract int getSize();

    public CoverageTable(int coverageFormat) {
        this.coverageFormat = coverageFormat;
    }

    public int getCoverageFormat() {
        return this.coverageFormat;
    }
}
