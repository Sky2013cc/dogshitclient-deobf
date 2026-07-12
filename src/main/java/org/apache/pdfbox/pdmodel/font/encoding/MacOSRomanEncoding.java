package org.apache.pdfbox.pdmodel.font.encoding;

import com.sun.tools.javac.jvm.ByteCodes;
import jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType;
import org.apache.pdfbox.cos.COSBase;

/* loaded from: target.jar:org/apache/pdfbox/pdmodel/font/encoding/MacOSRomanEncoding.class */
public class MacOSRomanEncoding extends MacRomanEncoding {
    private static final Object[][] MAC_OS_ROMAN_ENCODING_TABLE = {new Object[]{255, "notequal"}, new Object[]{Integer.valueOf(CharacterType.D), "infinity"}, new Object[]{262, "lessequal"}, new Object[]{263, "greaterequal"}, new Object[]{266, "partialdiff"}, new Object[]{267, "summation"}, new Object[]{Integer.valueOf(ByteCodes.ishll), "product"}, new Object[]{Integer.valueOf(ByteCodes.lshll), "pi"}, new Object[]{Integer.valueOf(ByteCodes.ishrl), "integral"}, new Object[]{Integer.valueOf(ByteCodes.lushrl), "Omega"}, new Object[]{303, "radical"}, new Object[]{305, "approxequal"}, new Object[]{306, "Delta"}, new Object[]{327, "lozenge"}, new Object[]{333, "Euro"}, new Object[]{360, "apple"}};
    public static final MacOSRomanEncoding INSTANCE = new MacOSRomanEncoding();

    public MacOSRomanEncoding() {
        for (Object[] encodingEntry : MAC_OS_ROMAN_ENCODING_TABLE) {
            add(((Integer) encodingEntry[0]).intValue(), encodingEntry[1].toString());
        }
    }

    @Override // org.apache.pdfbox.pdmodel.font.encoding.MacRomanEncoding, org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return null;
    }
}
