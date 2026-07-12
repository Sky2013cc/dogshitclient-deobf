package org.apache.pdfbox.pdmodel.font;

import java.util.Locale;

/* loaded from: target.jar:org/apache/pdfbox/pdmodel/font/UniUtil.class */
final class UniUtil {
    private UniUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getUniNameOfCodePoint(int codePoint) {
        String hex = Integer.toString(codePoint, 16).toUpperCase(Locale.US);
        switch (hex.length()) {
            case 1:
                return "uni000" + hex;
            case 2:
                return "uni00" + hex;
            case 3:
                return "uni0" + hex;
            default:
                return "uni" + hex;
        }
    }
}
