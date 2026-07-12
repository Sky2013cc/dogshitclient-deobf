package org.apache.fontbox.ttf.table.common;

/* loaded from: target.jar:org/apache/fontbox/ttf/table/common/RangeRecord.class */
public class RangeRecord {
    private final int startGlyphID;
    private final int endGlyphID;
    private final int startCoverageIndex;

    public RangeRecord(int startGlyphID, int endGlyphID, int startCoverageIndex) {
        this.startGlyphID = startGlyphID;
        this.endGlyphID = endGlyphID;
        this.startCoverageIndex = startCoverageIndex;
    }

    public int getStartGlyphID() {
        return this.startGlyphID;
    }

    public int getEndGlyphID() {
        return this.endGlyphID;
    }

    public int getStartCoverageIndex() {
        return this.startCoverageIndex;
    }

    public String toString() {
        return String.format("RangeRecord[startGlyphID=%d,endGlyphID=%d,startCoverageIndex=%d]", Integer.valueOf(this.startGlyphID), Integer.valueOf(this.endGlyphID), Integer.valueOf(this.startCoverageIndex));
    }
}
