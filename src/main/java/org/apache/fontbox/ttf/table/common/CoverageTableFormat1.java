package org.apache.fontbox.ttf.table.common;

import java.util.Arrays;

/* loaded from: target.jar:org/apache/fontbox/ttf/table/common/CoverageTableFormat1.class */
public class CoverageTableFormat1 extends CoverageTable {
    private final int[] glyphArray;

    public CoverageTableFormat1(int coverageFormat, int[] glyphArray) {
        super(coverageFormat);
        this.glyphArray = glyphArray;
    }

    @Override // org.apache.fontbox.ttf.table.common.CoverageTable
    public int getCoverageIndex(int gid) {
        return Arrays.binarySearch(this.glyphArray, gid);
    }

    @Override // org.apache.fontbox.ttf.table.common.CoverageTable
    public int getGlyphId(int index) {
        return this.glyphArray[index];
    }

    @Override // org.apache.fontbox.ttf.table.common.CoverageTable
    public int getSize() {
        return this.glyphArray.length;
    }

    public int[] getGlyphArray() {
        return this.glyphArray;
    }

    public String toString() {
        return String.format("CoverageTableFormat1[coverageFormat=%d,glyphArray=%s]", Integer.valueOf(getCoverageFormat()), Arrays.toString(this.glyphArray));
    }
}
