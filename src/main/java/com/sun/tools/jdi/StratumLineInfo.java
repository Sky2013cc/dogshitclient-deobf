package com.sun.tools.jdi;

import com.sun.jdi.AbsentInformationException;

/* loaded from: target.jar:com/sun/tools/jdi/StratumLineInfo.class */
class StratumLineInfo implements LineInfo {
    private final String stratumID;
    private final int lineNumber;
    private final String sourceName;
    private final String sourcePath;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StratumLineInfo(String str, int i, String str2, String str3) {
        this.stratumID = str;
        this.lineNumber = i;
        this.sourceName = str2;
        this.sourcePath = str3;
    }

    @Override // com.sun.tools.jdi.LineInfo
    public String liStratum() {
        return this.stratumID;
    }

    @Override // com.sun.tools.jdi.LineInfo
    public int liLineNumber() {
        return this.lineNumber;
    }

    @Override // com.sun.tools.jdi.LineInfo
    public String liSourceName() throws AbsentInformationException {
        if (this.sourceName == null) {
            throw new AbsentInformationException();
        }
        return this.sourceName;
    }

    @Override // com.sun.tools.jdi.LineInfo
    public String liSourcePath() throws AbsentInformationException {
        if (this.sourcePath == null) {
            throw new AbsentInformationException();
        }
        return this.sourcePath;
    }
}
