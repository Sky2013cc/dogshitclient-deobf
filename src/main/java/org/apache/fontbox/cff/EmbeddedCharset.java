package org.apache.fontbox.cff;

/* loaded from: target.jar:org/apache/fontbox/cff/EmbeddedCharset.class */
class EmbeddedCharset implements CFFCharset {
    private final CFFCharset charset;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EmbeddedCharset(boolean isCIDFont) {
        this.charset = isCIDFont ? new CFFCharsetCID() : new CFFCharsetType1();
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public int getCIDForGID(int gid) {
        return this.charset.getCIDForGID(gid);
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public boolean isCIDFont() {
        return this.charset.isCIDFont();
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public void addSID(int gid, int sid, String name) {
        this.charset.addSID(gid, sid, name);
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public void addCID(int gid, int cid) {
        this.charset.addCID(gid, cid);
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public int getSIDForGID(int sid) {
        return this.charset.getSIDForGID(sid);
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public int getGIDForSID(int sid) {
        return this.charset.getGIDForSID(sid);
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public int getGIDForCID(int cid) {
        return this.charset.getGIDForCID(cid);
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public int getSID(String name) {
        return this.charset.getSID(name);
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public String getNameForGID(int gid) {
        return this.charset.getNameForGID(gid);
    }
}
