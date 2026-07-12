package org.apache.fontbox.cff;

import org.apache.fontbox.encoding.Encoding;

/* loaded from: target.jar:org/apache/fontbox/cff/CFFEncoding.class */
public abstract class CFFEncoding extends Encoding {
    public void add(int code, int sid, String name) {
        addCharacterEncoding(code, name);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void add(int code, int sid) {
        addCharacterEncoding(code, CFFStandardString.getName(sid));
    }
}
