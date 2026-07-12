package org.apache.fontbox.encoding;

import java.util.Map;

/* loaded from: target.jar:org/apache/fontbox/encoding/BuiltInEncoding.class */
public class BuiltInEncoding extends Encoding {
    public BuiltInEncoding(Map<Integer, String> codeToName) {
        codeToName.forEach((v1, v2) -> {
            addCharacterEncoding(v1, v2);
        });
    }
}
