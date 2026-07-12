package org.apache.fontbox.ttf.table.gsub;

import org.apache.fontbox.ttf.table.common.CoverageTable;
import org.apache.fontbox.ttf.table.common.LookupSubTable;

/* loaded from: target.jar:org/apache/fontbox/ttf/table/gsub/LookupTypeMultipleSubstitutionFormat1.class */
public class LookupTypeMultipleSubstitutionFormat1 extends LookupSubTable {
    private final SequenceTable[] sequenceTables;

    public LookupTypeMultipleSubstitutionFormat1(int substFormat, CoverageTable coverageTable, SequenceTable[] sequenceTables) {
        super(substFormat, coverageTable);
        this.sequenceTables = sequenceTables;
    }

    public SequenceTable[] getSequenceTables() {
        return this.sequenceTables;
    }

    @Override // org.apache.fontbox.ttf.table.common.LookupSubTable
    public int doSubstitution(int gid, int coverageIndex) {
        throw new UnsupportedOperationException("not applicable");
    }
}
