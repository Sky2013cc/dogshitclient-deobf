package org.apache.fontbox.ttf.table.common;

import java.util.ArrayList;
import java.util.List;

/* loaded from: target.jar:org/apache/fontbox/ttf/table/common/CoverageTableFormat2.class */
public class CoverageTableFormat2 extends CoverageTableFormat1 {
    private final RangeRecord[] rangeRecords;

    public CoverageTableFormat2(int coverageFormat, RangeRecord[] rangeRecords) {
        super(coverageFormat, getRangeRecordsAsArray(rangeRecords));
        this.rangeRecords = rangeRecords;
    }

    public RangeRecord[] getRangeRecords() {
        return this.rangeRecords;
    }

    private static int[] getRangeRecordsAsArray(RangeRecord[] rangeRecords) {
        List<Integer> glyphIds = new ArrayList<>();
        for (RangeRecord rangeRecord : rangeRecords) {
            for (int glyphId = rangeRecord.getStartGlyphID(); glyphId <= rangeRecord.getEndGlyphID(); glyphId++) {
                glyphIds.add(Integer.valueOf(glyphId));
            }
        }
        int[] glyphArray = new int[glyphIds.size()];
        for (int i = 0; i < glyphArray.length; i++) {
            glyphArray[i] = glyphIds.get(i).intValue();
        }
        return glyphArray;
    }

    @Override // org.apache.fontbox.ttf.table.common.CoverageTableFormat1
    public String toString() {
        return String.format("CoverageTableFormat2[coverageFormat=%d]", Integer.valueOf(getCoverageFormat()));
    }
}
