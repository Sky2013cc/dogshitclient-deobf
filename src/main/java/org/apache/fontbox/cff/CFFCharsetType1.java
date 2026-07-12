package org.apache.fontbox.cff;

import java.util.HashMap;
import java.util.Map;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;

/* loaded from: target.jar:org/apache/fontbox/cff/CFFCharsetType1.class */
class CFFCharsetType1 implements CFFCharset {
    private static final String EXCEPTION_MESSAGE = "Not a CIDFont";
    private final Map<Integer, Integer> sidOrCidToGid = new HashMap(LinkerCallSite.ARGLIMIT);
    private final Map<Integer, Integer> gidToSid = new HashMap(LinkerCallSite.ARGLIMIT);
    private final Map<String, Integer> nameToSid = new HashMap(LinkerCallSite.ARGLIMIT);
    private final Map<Integer, String> gidToName = new HashMap(LinkerCallSite.ARGLIMIT);

    @Override // org.apache.fontbox.cff.CFFCharset
    public boolean isCIDFont() {
        return false;
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public void addSID(int gid, int sid, String name) {
        this.sidOrCidToGid.put(Integer.valueOf(sid), Integer.valueOf(gid));
        this.gidToSid.put(Integer.valueOf(gid), Integer.valueOf(sid));
        this.nameToSid.put(name, Integer.valueOf(sid));
        this.gidToName.put(Integer.valueOf(gid), name);
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public void addCID(int gid, int cid) {
        throw new IllegalStateException(EXCEPTION_MESSAGE);
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public int getSIDForGID(int gid) {
        Integer sid = this.gidToSid.get(Integer.valueOf(gid));
        if (sid == null) {
            return 0;
        }
        return sid.intValue();
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public int getGIDForSID(int sid) {
        Integer gid = this.sidOrCidToGid.get(Integer.valueOf(sid));
        if (gid == null) {
            return 0;
        }
        return gid.intValue();
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public int getGIDForCID(int cid) {
        throw new IllegalStateException(EXCEPTION_MESSAGE);
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public int getSID(String name) {
        Integer sid = this.nameToSid.get(name);
        if (sid == null) {
            return 0;
        }
        return sid.intValue();
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public String getNameForGID(int gid) {
        return this.gidToName.get(Integer.valueOf(gid));
    }

    @Override // org.apache.fontbox.cff.CFFCharset
    public int getCIDForGID(int gid) {
        throw new IllegalStateException(EXCEPTION_MESSAGE);
    }
}
