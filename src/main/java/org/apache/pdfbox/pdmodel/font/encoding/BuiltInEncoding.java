package org.apache.pdfbox.pdmodel.font.encoding;

import java.util.Map;
import org.apache.pdfbox.cos.COSBase;

/* loaded from: target.jar:org/apache/pdfbox/pdmodel/font/encoding/BuiltInEncoding.class */
public class BuiltInEncoding extends Encoding {
    public BuiltInEncoding(Map<Integer, String> codeToName) {
        codeToName.forEach((v1, v2) -> {
            add(v1, v2);
        });
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        throw new UnsupportedOperationException("Built-in encodings cannot be serialized");
    }

    @Override // org.apache.pdfbox.pdmodel.font.encoding.Encoding
    public String getEncodingName() {
        return "built-in (TTF)";
    }
}
