package org.apache.pdfbox.cos;

import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import java.io.IOException;
import org.slf4j.Marker;

/* loaded from: target.jar:org/apache/pdfbox/cos/COSNumber.class */
public abstract class COSNumber extends COSBase {
    public abstract float floatValue();

    public abstract int intValue();

    public abstract long longValue();

    public static COSNumber get(String number) throws IOException {
        if (number.length() == 1) {
            char digit = number.charAt(0);
            if ('0' <= digit && digit <= '9') {
                return COSInteger.get(digit - 48);
            }
            if (digit == '-' || digit == '.') {
                return COSInteger.ZERO;
            }
            throw new IOException("Not a number: " + number);
        }
        if (isFloat(number)) {
            return new COSFloat(number);
        }
        try {
            return COSInteger.get(Long.parseLong(number));
        } catch (NumberFormatException e) {
            String numberString = (number.startsWith(Marker.ANY_NON_NULL_MARKER) || number.startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) ? number.substring(1) : number;
            if (numberString.matches("[0-9]*")) {
                return number.startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR) ? COSInteger.OUT_OF_RANGE_MIN : COSInteger.OUT_OF_RANGE_MAX;
            }
            throw new IOException("Not a number: " + number);
        }
    }

    private static boolean isFloat(String number) {
        int length = number.length();
        for (int i = 0; i < length; i++) {
            char digit = number.charAt(i);
            if (digit == '.' || digit == 'e') {
                return true;
            }
        }
        return false;
    }
}
