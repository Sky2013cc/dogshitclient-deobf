package org.apache.fontbox.cff;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.fontbox.afm.AFMParser;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;

/* loaded from: target.jar:org/apache/fontbox/cff/CFFOperator.class */
public final class CFFOperator {
    private static final Map<Integer, String> keyMap = new LinkedHashMap(52);

    private CFFOperator() {
    }

    private static void register(int b0, String name) {
        register(b0, 0, name);
    }

    private static void register(int b0, int b1, String name) {
        keyMap.put(Integer.valueOf(calculateKey(b0, b1)), name);
    }

    public static String getOperator(int b0) {
        return getOperator(b0, 0);
    }

    public static String getOperator(int b0, int b1) {
        return keyMap.get(Integer.valueOf(calculateKey(b0, b1)));
    }

    private static int calculateKey(int b0, int b1) {
        return (b1 << 8) + b0;
    }

    static {
        register(0, "version");
        register(1, AFMParser.NOTICE);
        register(12, 0, "Copyright");
        register(2, AFMParser.FULL_NAME);
        register(3, AFMParser.FAMILY_NAME);
        register(4, AFMParser.WEIGHT);
        register(12, 1, "isFixedPitch");
        register(12, 2, AFMParser.ITALIC_ANGLE);
        register(12, 3, AFMParser.UNDERLINE_POSITION);
        register(12, 4, AFMParser.UNDERLINE_THICKNESS);
        register(12, 5, "PaintType");
        register(12, 6, "CharstringType");
        register(12, 7, "FontMatrix");
        register(13, "UniqueID");
        register(5, AFMParser.FONT_BBOX);
        register(12, 8, "StrokeWidth");
        register(14, "XUID");
        register(15, "charset");
        register(16, "Encoding");
        register(17, "CharStrings");
        register(18, StandardStructureTypes.PRIVATE);
        register(12, 20, "SyntheticBase");
        register(12, 21, "PostScript");
        register(12, 22, "BaseFontName");
        register(12, 23, "BaseFontBlend");
        register(12, 30, "ROS");
        register(12, 31, "CIDFontVersion");
        register(12, 32, "CIDFontRevision");
        register(12, 33, "CIDFontType");
        register(12, 34, "CIDCount");
        register(12, 35, "UIDBase");
        register(12, 36, "FDArray");
        register(12, 37, "FDSelect");
        register(12, 38, AFMParser.FONT_NAME);
        register(6, "BlueValues");
        register(7, "OtherBlues");
        register(8, "FamilyBlues");
        register(9, "FamilyOtherBlues");
        register(12, 9, "BlueScale");
        register(12, 10, "BlueShift");
        register(12, 11, "BlueFuzz");
        register(10, AFMParser.STD_HW);
        register(11, AFMParser.STD_VW);
        register(12, 12, "StemSnapH");
        register(12, 13, "StemSnapV");
        register(12, 14, "ForceBold");
        register(12, 15, "LanguageGroup");
        register(12, 16, "ExpansionFactor");
        register(12, 17, "initialRandomSeed");
        register(19, "Subrs");
        register(20, "defaultWidthX");
        register(21, "nominalWidthX");
    }
}
