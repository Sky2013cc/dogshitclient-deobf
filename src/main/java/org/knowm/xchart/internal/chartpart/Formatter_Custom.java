package org.knowm.xchart.internal.chartpart;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.function.Function;

/* loaded from: target.jar:org/knowm/xchart/internal/chartpart/Formatter_Custom.class */
public class Formatter_Custom extends Format {
    private final Function<Double, String> customFormattingFunction;

    public Formatter_Custom(Function<Double, String> customFormattingFunction) {
        this.customFormattingFunction = customFormattingFunction;
    }

    @Override // java.text.Format
    public StringBuffer format(Object o, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        Number number = (Number) o;
        stringBuffer.append(this.customFormattingFunction.apply(Double.valueOf(number.doubleValue())));
        return stringBuffer;
    }

    @Override // java.text.Format
    public Object parseObject(String s, ParsePosition parsePosition) {
        return null;
    }
}
