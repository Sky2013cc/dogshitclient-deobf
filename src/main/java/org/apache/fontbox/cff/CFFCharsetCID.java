package org.apache.fontbox.cff;

import java.util.HashMap;
import java.util.Map;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;

/* loaded from: target.jar:org/apache/fontbox/cff/CFFCharsetCID.class */
class CFFCharsetCID implements CFFCharset {
    private static final String EXCEPTION_MESSAGE = "Not a Type 1-equivalent font";
    private final Map<Integer, Integer> sidOrCidToGid = new HashMap(LinkerCallSite.ARGLIMIT);
    private final Map<Integer, Integer> gidToCid = new HashMap();

    @Override // org.apache.fontbox.cff.CFFCharset
    public boolean isCIDFont() {
        return true;
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public void addSID(int gid, int sid, String name) {
        throw new IllegalStateException(EXCEPTION_MESSAGE);
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public void addCID(int gid, int cid) {
        this.sidOrCidToGid.put(Integer.valueOf(cid), Integer.valueOf(gid));
        this.gidToCid.put(Integer.valueOf(gid), Integer.valueOf(cid));
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public int getSIDForGID(int sid) {
        throw new IllegalStateException(EXCEPTION_MESSAGE);
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public int getGIDForSID(int sid) {
        throw new IllegalStateException(EXCEPTION_MESSAGE);
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public int getGIDForCID(int cid) {
        Integer gid = this.sidOrCidToGid.get(Integer.valueOf(cid));
        if (gid == null) {
            return 0;
        }
        return gid.intValue();
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public int getSID(String name) {
        throw new IllegalStateException(EXCEPTION_MESSAGE);
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public String getNameForGID(int gid) {
        throw new IllegalStateException(EXCEPTION_MESSAGE);
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public int getCIDForGID(int gid) {
        Integer cid = this.gidToCid.get(Integer.valueOf(gid));
        if (cid != null) {
            return cid.intValue();
        }
        return 0;
    }
}
