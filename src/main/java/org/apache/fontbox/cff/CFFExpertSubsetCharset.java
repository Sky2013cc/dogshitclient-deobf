package org.apache.fontbox.cff;

import com.sun.tools.javac.jvm.ByteCodes;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;

/* loaded from: target.jar:org/apache/fontbox/cff/CFFExpertSubsetCharset.class */
public final class CFFExpertSubsetCharset extends CFFCharsetType1 {
    private static final int CHAR_CODE = 0;
    private static final int CHAR_NAME = 1;
    private static final CFFExpertSubsetCharset INSTANCE = new CFFExpertSubsetCharset();

    @Override // org.apache.fontbox.cff.CFFCharsetType1, org.apache.fontbox.cff.CFFCharset
    public /* bridge */ /* synthetic */ int getCIDForGID(int i) {
        return super.getCIDForGID(i);
    }

    @Override // org.apache.fontbox.cff.CFFCharsetType1, org.apache.fontbox.cff.CFFCharset
    public /* bridge */ /* synthetic */ String getNameForGID(int i) {
        return super.getNameForGID(i);
    }

    @Override // org.apache.fontbox.cff.CFFCharsetType1, org.apache.fontbox.cff.CFFCharset
    public /* bridge */ /* synthetic */ int getSID(String str) {
        return super.getSID(str);
    }

    @Override // org.apache.fontbox.cff.CFFCharsetType1, org.apache.fontbox.cff.CFFCharset
    public /* bridge */ /* synthetic */ int getGIDForCID(int i) {
        return super.getGIDForCID(i);
    }

    @Override // org.apache.fontbox.cff.CFFCharsetType1, org.apache.fontbox.cff.CFFCharset
    public /* bridge */ /* synthetic */ int getGIDForSID(int i) {
        return super.getGIDForSID(i);
    }

    @Override // org.apache.fontbox.cff.CFFCharsetType1, org.apache.fontbox.cff.CFFCharset
    public /* bridge */ /* synthetic */ int getSIDForGID(int i) {
        return super.getSIDForGID(i);
    }

    @Override // org.apache.fontbox.cff.CFFCharsetType1, org.apache.fontbox.cff.CFFCharset
    public /* bridge */ /* synthetic */ void addCID(int i, int i2) {
        super.addCID(i, i2);
    }

    @Override // org.apache.fontbox.cff.CFFCharsetType1, org.apache.fontbox.cff.CFFCharset
    public /* bridge */ /* synthetic */ void addSID(int i, int i2, String str) {
        super.addSID(i, i2, str);
    }

    @Override // org.apache.fontbox.cff.CFFCharsetType1, org.apache.fontbox.cff.CFFCharset
    public /* bridge */ /* synthetic */ boolean isCIDFont() {
        return super.isCIDFont();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private CFFExpertSubsetCharset() {
        int gid = 0;
        for (Object[] objArr : new Object[]{new Object[]{0, ".notdef"}, new Object[]{1, "space"}, new Object[]{231, "dollaroldstyle"}, new Object[]{232, "dollarsuperior"}, new Object[]{235, "parenleftsuperior"}, new Object[]{236, "parenrightsuperior"}, new Object[]{237, "twodotenleader"}, new Object[]{238, "onedotenleader"}, new Object[]{13, "comma"}, new Object[]{14, "hyphen"}, new Object[]{15, "period"}, new Object[]{99, "fraction"}, new Object[]{239, "zerooldstyle"}, new Object[]{240, "oneoldstyle"}, new Object[]{241, "twooldstyle"}, new Object[]{242, "threeoldstyle"}, new Object[]{243, "fouroldstyle"}, new Object[]{244, "fiveoldstyle"}, new Object[]{245, "sixoldstyle"}, new Object[]{246, "sevenoldstyle"}, new Object[]{247, "eightoldstyle"}, new Object[]{248, "nineoldstyle"}, new Object[]{27, "colon"}, new Object[]{28, "semicolon"}, new Object[]{249, "commasuperior"}, new Object[]{Integer.valueOf(LinkerCallSite.ARGLIMIT), "threequartersemdash"}, new Object[]{251, "periodsuperior"}, new Object[]{253, "asuperior"}, new Object[]{254, "bsuperior"}, new Object[]{255, "centsuperior"}, new Object[]{256, "dsuperior"}, new Object[]{Integer.valueOf(ByteCodes.bool_not), "esuperior"}, new Object[]{258, "isuperior"}, new Object[]{Integer.valueOf(ByteCodes.bool_or), "lsuperior"}, new Object[]{Integer.valueOf(CharacterType.D), "msuperior"}, new Object[]{261, "nsuperior"}, new Object[]{262, "osuperior"}, new Object[]{263, "rsuperior"}, new Object[]{264, "ssuperior"}, new Object[]{Integer.valueOf(CharacterType.S), "tsuperior"}, new Object[]{266, "ff"}, new Object[]{109, "fi"}, new Object[]{110, "fl"}, new Object[]{267, "ffi"}, new Object[]{Integer.valueOf(CharacterType.W), "ffl"}, new Object[]{269, "parenleftinferior"}, new Object[]{Integer.valueOf(ByteCodes.ishll), "parenrightinferior"}, new Object[]{Integer.valueOf(ByteCodes.ishrl), "hyphensuperior"}, new Object[]{Integer.valueOf(OS2WindowsMetricsTable.WEIGHT_CLASS_LIGHT), "colonmonetary"}, new Object[]{301, "onefitted"}, new Object[]{302, "rupiah"}, new Object[]{305, "centoldstyle"}, new Object[]{314, "figuredash"}, new Object[]{315, "hypheninferior"}, new Object[]{158, "onequarter"}, new Object[]{155, "onehalf"}, new Object[]{163, "threequarters"}, new Object[]{320, "oneeighth"}, new Object[]{321, "threeeighths"}, new Object[]{322, "fiveeighths"}, new Object[]{323, "seveneighths"}, new Object[]{324, "onethird"}, new Object[]{325, "twothirds"}, new Object[]{326, "zerosuperior"}, new Object[]{150, "onesuperior"}, new Object[]{164, "twosuperior"}, new Object[]{169, "threesuperior"}, new Object[]{327, "foursuperior"}, new Object[]{328, "fivesuperior"}, new Object[]{329, "sixsuperior"}, new Object[]{330, "sevensuperior"}, new Object[]{331, "eightsuperior"}, new Object[]{332, "ninesuperior"}, new Object[]{333, "zeroinferior"}, new Object[]{334, "oneinferior"}, new Object[]{335, "twoinferior"}, new Object[]{336, "threeinferior"}, new Object[]{337, "fourinferior"}, new Object[]{338, "fiveinferior"}, new Object[]{339, "sixinferior"}, new Object[]{340, "seveninferior"}, new Object[]{341, "eightinferior"}, new Object[]{342, "nineinferior"}, new Object[]{343, "centinferior"}, new Object[]{344, "dollarinferior"}, new Object[]{345, "periodinferior"}, new Object[]{346, "commainferior"}}) {
            int i = gid;
            gid++;
            addSID(i, ((Integer) objArr[0]).intValue(), objArr[1].toString());
        }
    }

    public static CFFExpertSubsetCharset getInstance() {
        return INSTANCE;
    }
}
