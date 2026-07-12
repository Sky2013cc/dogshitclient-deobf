package org.apache.fontbox.ttf;

import java.io.IOException;

/* loaded from: target.jar:org/apache/fontbox/ttf/TTFTable.class */
public class TTFTable {
    private String tag;
    private long checkSum;
    private long offset;
    private long length;
    protected boolean initialized;

    public long getCheckSum() {
        return this.checkSum;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCheckSum(long checkSumValue) {
        this.checkSum = checkSumValue;
    }

    public long getLength() {
        return this.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLength(long lengthValue) {
        this.length = lengthValue;
    }

    public long getOffset() {
        return this.offset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOffset(long offsetValue) {
        this.offset = offsetValue;
    }

    public String getTag() {
        return this.tag;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTag(String tagValue) {
        this.tag = tagValue;
    }

    public boolean getInitialized() {
        return this.initialized;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void read(TrueTypeFont ttf, TTFDataStream data) throws IOException {
    }
}
