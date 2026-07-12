package org.apache.pdfbox.pdmodel.font;

import org.apache.fontbox.FontBoxFont;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:org/apache/pdfbox/pdmodel/font/FontInfo.class */
public abstract class FontInfo {
    public abstract String getPostScriptName();

    public abstract FontFormat getFormat();

    public abstract CIDSystemInfo getCIDSystemInfo();

    public abstract FontBoxFont getFont();

    public abstract int getFamilyClass();

    public abstract int getWeightClass();

    public abstract int getCodePageRange1();

    public abstract int getCodePageRange2();

    public abstract int getMacStyle();

    public abstract PDPanoseClassification getPanose();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getWeightClassAsPanose() {
        int usWeightClass = getWeightClass();
        switch (usWeightClass) {
            case -1:
                return 0;
            case 0:
                return 0;
            case 100:
                return 2;
            case 200:
                return 3;
            case OS2WindowsMetricsTable.WEIGHT_CLASS_LIGHT /* 300 */:
                return 4;
            case OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL /* 400 */:
                return 5;
            case 500:
                return 6;
            case OS2WindowsMetricsTable.WEIGHT_CLASS_SEMI_BOLD /* 600 */:
                return 7;
            case OS2WindowsMetricsTable.WEIGHT_CLASS_BOLD /* 700 */:
                return 8;
            case OS2WindowsMetricsTable.WEIGHT_CLASS_EXTRA_BOLD /* 800 */:
                return 9;
            case OS2WindowsMetricsTable.WEIGHT_CLASS_BLACK /* 900 */:
                return 10;
            default:
                return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long getCodePageRange() {
        long range1 = getCodePageRange1() & 4294967295L;
        long range2 = getCodePageRange2() & 4294967295L;
        return (range2 << 32) | range1;
    }

    public String toString() {
        return getPostScriptName() + " (" + getFormat() + ", mac: 0x" + Integer.toHexString(getMacStyle()) + ", os/2: 0x" + Integer.toHexString(getFamilyClass()) + ", cid: " + getCIDSystemInfo() + RuntimeConstants.SIG_ENDMETHOD;
    }
}
