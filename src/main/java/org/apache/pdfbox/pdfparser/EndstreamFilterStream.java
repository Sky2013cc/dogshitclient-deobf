package org.apache.pdfbox.pdfparser;

/* loaded from: target.jar:org/apache/pdfbox/pdfparser/EndstreamFilterStream.class */
class EndstreamFilterStream {
    private boolean hasCR = false;
    private boolean hasLF = false;
    private int pos = 0;
    private boolean mustFilter = true;
    private long length = 0;

    public void filter(byte[] b, int off, int len) {
        if (this.pos == 0 && len > 10) {
            this.mustFilter = false;
            for (int i = 0; i < 10; i++) {
                if (b[i] < 9 || (b[i] > 10 && b[i] < 32 && b[i] != 13)) {
                    this.mustFilter = true;
                    break;
                }
            }
        }
        if (this.mustFilter) {
            if (this.hasCR) {
                this.hasCR = false;
                if (!this.hasLF && len == 1 && b[off] == 10) {
                    return;
                } else {
                    this.length++;
                }
            }
            if (this.hasLF) {
                this.length++;
                this.hasLF = false;
            }
            if (len > 0) {
                if (b[(off + len) - 1] == 13) {
                    this.hasCR = true;
                    len--;
                } else if (b[(off + len) - 1] == 10) {
                    this.hasLF = true;
                    len--;
                    if (len > 0 && b[(off + len) - 1] == 13) {
                        this.hasCR = true;
                        len--;
                    }
                }
            }
        }
        this.length += len;
        this.pos += len;
    }

    public long calculateLength() {
        if (this.hasCR && !this.hasLF) {
            this.length++;
            this.pos++;
        }
        this.hasCR = false;
        this.hasLF = false;
        return this.length;
    }
}
