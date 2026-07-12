package org.apache.fontbox.ttf.table.gsub;

import org.apache.fontbox.ttf.table.common.CoverageTable;
import org.apache.fontbox.ttf.table.common.LookupSubTable;

/* loaded from: target.jar:org/apache/fontbox/ttf/table/gsub/LookupTypeSingleSubstFormat1.class */
public class LookupTypeSingleSubstFormat1 extends LookupSubTable {
    private final short deltaGlyphID;

    public LookupTypeSingleSubstFormat1(int substFormat, CoverageTable coverageTable, short deltaGlyphID) {
        super(substFormat, coverageTable);
        this.deltaGlyphID = deltaGlyphID;
    }

    @Override // org.apache.fontbox.ttf.table.common.LookupSubTable
    public int doSubstitution(int gid, int coverageIndex) {
        return coverageIndex < 0 ? gid : gid + this.deltaGlyphID;
    }

    public short getDeltaGlyphID() {
        return this.deltaGlyphID;
    }

    public String toString() {
        return String.format("LookupTypeSingleSubstFormat1[substFormat=%d,deltaGlyphID=%d]", Integer.valueOf(getSubstFormat()), Short.valueOf(this.deltaGlyphID));
    }
}
