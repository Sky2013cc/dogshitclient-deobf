package org.apache.pdfbox.cos;

import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: target.jar:org/apache/pdfbox/cos/COSFloat.class */
public class COSFloat extends COSNumber {
    private final float value;
    private String valueAsString;

    public COSFloat(float aFloat) {
        this.value = aFloat;
    }

    public COSFloat(String aFloat) throws IOException {
        String aFloat2;
        float parsedValue;
        String stringValue = null;
        try {
            float f = Float.parseFloat(aFloat);
            parsedValue = coerce(f);
            stringValue = f == parsedValue ? aFloat : null;
        } catch (NumberFormatException e) {
            if (aFloat.startsWith("--")) {
                aFloat2 = aFloat.substring(1);
            } else if (aFloat.matches("^0\\.0*-\\d+")) {
                aFloat2 = TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR + aFloat.replaceFirst(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR, "");
            } else {
                throw new IOException("Error expected floating point number actual='" + aFloat + OperatorName.SHOW_TEXT_LINE, e);
            }
            try {
                parsedValue = coerce(Float.parseFloat(aFloat2));
            } catch (NumberFormatException e2) {
                throw new IOException("Error expected floating point number actual='" + aFloat2 + OperatorName.SHOW_TEXT_LINE, e2);
            }
        }
        this.value = parsedValue;
        this.valueAsString = stringValue;
    }

    private float coerce(float floatValue) {
        if (floatValue == Float.POSITIVE_INFINITY) {
            return Float.MAX_VALUE;
        }
        if (floatValue == Float.NEGATIVE_INFINITY) {
            return -3.4028235E38f;
        }
        if (Math.abs(floatValue) < Float.MIN_NORMAL) {
            return 0.0f;
        }
        return floatValue;
    }

    @Override // org.apache.pdfbox.cos.COSNumber
    public float floatValue() {
        return this.value;
    }

    @Override // org.apache.pdfbox.cos.COSNumber
    public long longValue() {
        return this.value;
    }

    @Override // org.apache.pdfbox.cos.COSNumber
    public int intValue() {
        return (int) this.value;
    }

    public boolean equals(Object o) {
        return (o instanceof COSFloat) && Float.floatToIntBits(((COSFloat) o).value) == Float.floatToIntBits(this.value);
    }

    public int hashCode() {
        return Float.hashCode(this.value);
    }

    public String toString() {
        return "COSFloat{" + formatString() + "}";
    }

    private String formatString() {
        if (this.valueAsString == null) {
            String s = String.valueOf(this.value);
            boolean simpleFormat = s.indexOf(69) < 0;
            this.valueAsString = simpleFormat ? s : new BigDecimal(s).stripTrailingZeros().toPlainString();
        }
        return this.valueAsString;
    }

    @Override // org.apache.pdfbox.cos.COSBase
    public void accept(ICOSVisitor visitor) throws IOException {
        visitor.visitFromFloat(this);
    }

    public void writePDF(OutputStream output) throws IOException {
        output.write(formatString().getBytes(StandardCharsets.ISO_8859_1));
    }
}
