package org.apache.fontbox.ttf.table.gsub;

import java.util.Arrays;
import org.apache.fontbox.ttf.table.common.CoverageTable;
import org.apache.fontbox.ttf.table.common.LookupSubTable;

/* loaded from: target.jar:org/apache/fontbox/ttf/table/gsub/LookupTypeSingleSubstFormat2.class */
public class LookupTypeSingleSubstFormat2 extends LookupSubTable {
    private final int[] substituteGlyphIDs;

    public LookupTypeSingleSubstFormat2(int substFormat, CoverageTable coverageTable, int[] substituteGlyphIDs) {
        super(substFormat, coverageTable);
        this.substituteGlyphIDs = substituteGlyphIDs;
    }

    @Override // org.apache.fontbox.ttf.table.common.LookupSubTable
    public int doSubstitution(int gid, int coverageIndex) {
        return coverageIndex < 0 ? gid : this.substituteGlyphIDs[coverageIndex];
    }

    public int[] getSubstituteGlyphIDs() {
        return this.substituteGlyphIDs;
    }

    public String toString() {
        return String.format("LookupTypeSingleSubstFormat2[substFormat=%d,substituteGlyphIDs=%s]", Integer.valueOf(getSubstFormat()), Arrays.toString(this.substituteGlyphIDs));
    }
}
