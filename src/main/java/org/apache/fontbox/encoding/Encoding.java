package org.apache.fontbox.encoding;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;

/* loaded from: target.jar:org/apache/fontbox/encoding/Encoding.class */
public abstract class Encoding {
    private final Map<Integer, String> codeToName = new HashMap(LinkerCallSite.ARGLIMIT);
    private final Map<String, Integer> nameToCode = new HashMap(LinkerCallSite.ARGLIMIT);

    /* JADX INFO: Access modifiers changed from: protected */
    public void addCharacterEncoding(int code, String name) {
        this.codeToName.put(Integer.valueOf(code), name);
        this.nameToCode.put(name, Integer.valueOf(code));
    }

    public Integer getCode(String name) {
        return this.nameToCode.get(name);
    }

    public String getName(int code) {
        String name = this.codeToName.get(Integer.valueOf(code));
        if (name != null) {
            return name;
        }
        return ".notdef";
    }

    public Map<Integer, String> getCodeToNameMap() {
        return Collections.unmodifiableMap(this.codeToName);
    }
}
