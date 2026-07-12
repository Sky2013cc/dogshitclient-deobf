package org.knowm.xchart.internal.chartpart;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

/* loaded from: target.jar:org/knowm/xchart/internal/chartpart/Formatter_String.class */
class Formatter_String extends Format {
    @Override // java.text.Format
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        String string = obj.toString();
        toAppendTo.append(string);
        return toAppendTo;
    }

    @Override // java.text.Format
    public Object parseObject(String source, ParsePosition pos) {
        return null;
    }
}
