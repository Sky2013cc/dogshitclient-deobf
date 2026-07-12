package com.sun.tools.internal.xjc.runtime;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;

/* loaded from: target.jar:com/sun/tools/internal/xjc/runtime/ZeroOneBooleanAdapter.class */
public class ZeroOneBooleanAdapter extends XmlAdapter<String, Boolean> {
    public Boolean unmarshal(String v) {
        if (v == null) {
            return null;
        }
        return Boolean.valueOf(DatatypeConverter.parseBoolean(v));
    }

    public String marshal(Boolean v) {
        if (v == null) {
            return null;
        }
        if (v.booleanValue()) {
            return "1";
        }
        return PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES;
    }
}
