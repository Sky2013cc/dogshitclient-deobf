package org.apache.fontbox.cff;

import com.sun.tools.javac.jvm.ByteCodes;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType;
import okhttp3.internal.http.HttpStatusCodesKt;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;

/* loaded from: target.jar:org/apache/fontbox/cff/CFFExpertCharset.class */
public final class CFFExpertCharset extends CFFCharsetType1 {
    private static final int CHAR_CODE = 0;
    private static final int CHAR_NAME = 1;
    private static final CFFExpertCharset INSTANCE = new CFFExpertCharset();

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
    private CFFExpertCharset() {
        int gid = 0;
        for (Object[] objArr : new Object[]{new Object[]{0, ".notdef"}, new Object[]{1, "space"}, new Object[]{229, "exclamsmall"}, new Object[]{230, "Hungarumlautsmall"}, new Object[]{231, "dollaroldstyle"}, new Object[]{232, "dollarsuperior"}, new Object[]{233, "ampersandsmall"}, new Object[]{234, "Acutesmall"}, new Object[]{235, "parenleftsuperior"}, new Object[]{236, "parenrightsuperior"}, new Object[]{237, "twodotenleader"}, new Object[]{238, "onedotenleader"}, new Object[]{13, "comma"}, new Object[]{14, "hyphen"}, new Object[]{15, "period"}, new Object[]{99, "fraction"}, new Object[]{239, "zerooldstyle"}, new Object[]{240, "oneoldstyle"}, new Object[]{241, "twooldstyle"}, new Object[]{242, "threeoldstyle"}, new Object[]{243, "fouroldstyle"}, new Object[]{244, "fiveoldstyle"}, new Object[]{245, "sixoldstyle"}, new Object[]{246, "sevenoldstyle"}, new Object[]{247, "eightoldstyle"}, new Object[]{248, "nineoldstyle"}, new Object[]{27, "colon"}, new Object[]{28, "semicolon"}, new Object[]{249, "commasuperior"}, new Object[]{Integer.valueOf(LinkerCallSite.ARGLIMIT), "threequartersemdash"}, new Object[]{251, "periodsuperior"}, new Object[]{252, "questionsmall"}, new Object[]{253, "asuperior"}, new Object[]{254, "bsuperior"}, new Object[]{255, "centsuperior"}, new Object[]{256, "dsuperior"}, new Object[]{Integer.valueOf(ByteCodes.bool_not), "esuperior"}, new Object[]{258, "isuperior"}, new Object[]{Integer.valueOf(ByteCodes.bool_or), "lsuperior"}, new Object[]{Integer.valueOf(CharacterType.D), "msuperior"}, new Object[]{261, "nsuperior"}, new Object[]{262, "osuperior"}, new Object[]{263, "rsuperior"}, new Object[]{264, "ssuperior"}, new Object[]{Integer.valueOf(CharacterType.S), "tsuperior"}, new Object[]{266, "ff"}, new Object[]{109, "fi"}, new Object[]{110, "fl"}, new Object[]{267, "ffi"}, new Object[]{Integer.valueOf(CharacterType.W), "ffl"}, new Object[]{269, "parenleftinferior"}, new Object[]{Integer.valueOf(ByteCodes.ishll), "parenrightinferior"}, new Object[]{Integer.valueOf(ByteCodes.lshll), "Circumflexsmall"}, new Object[]{Integer.valueOf(ByteCodes.ishrl), "hyphensuperior"}, new Object[]{Integer.valueOf(ByteCodes.lshrl), "Gravesmall"}, new Object[]{Integer.valueOf(ByteCodes.iushrl), "Asmall"}, new Object[]{Integer.valueOf(ByteCodes.lushrl), "Bsmall"}, new Object[]{Integer.valueOf(ByteCodes.nullchk), "Csmall"}, new Object[]{Integer.valueOf(ByteCodes.error), "Dsmall"}, new Object[]{278, "Esmall"}, new Object[]{279, "Fsmall"}, new Object[]{280, "Gsmall"}, new Object[]{281, "Hsmall"}, new Object[]{282, "Ismall"}, new Object[]{283, "Jsmall"}, new Object[]{284, "Ksmall"}, new Object[]{285, "Lsmall"}, new Object[]{286, "Msmall"}, new Object[]{287, "Nsmall"}, new Object[]{288, "Osmall"}, new Object[]{289, "Psmall"}, new Object[]{290, "Qsmall"}, new Object[]{291, "Rsmall"}, new Object[]{292, "Ssmall"}, new Object[]{293, "Tsmall"}, new Object[]{294, "Usmall"}, new Object[]{295, "Vsmall"}, new Object[]{296, "Wsmall"}, new Object[]{297, "Xsmall"}, new Object[]{298, "Ysmall"}, new Object[]{299, "Zsmall"}, new Object[]{Integer.valueOf(OS2WindowsMetricsTable.WEIGHT_CLASS_LIGHT), "colonmonetary"}, new Object[]{301, "onefitted"}, new Object[]{302, "rupiah"}, new Object[]{303, "Tildesmall"}, new Object[]{304, "exclamdownsmall"}, new Object[]{305, "centoldstyle"}, new Object[]{306, "Lslashsmall"}, new Object[]{Integer.valueOf(HttpStatusCodesKt.HTTP_TEMP_REDIRECT), "Scaronsmall"}, new Object[]{Integer.valueOf(HttpStatusCodesKt.HTTP_PERM_REDIRECT), "Zcaronsmall"}, new Object[]{309, "Dieresissmall"}, new Object[]{310, "Brevesmall"}, new Object[]{311, "Caronsmall"}, new Object[]{312, "Dotaccentsmall"}, new Object[]{313, "Macronsmall"}, new Object[]{314, "figuredash"}, new Object[]{315, "hypheninferior"}, new Object[]{316, "Ogoneksmall"}, new Object[]{317, "Ringsmall"}, new Object[]{318, "Cedillasmall"}, new Object[]{158, "onequarter"}, new Object[]{155, "onehalf"}, new Object[]{163, "threequarters"}, new Object[]{319, "questiondownsmall"}, new Object[]{320, "oneeighth"}, new Object[]{321, "threeeighths"}, new Object[]{322, "fiveeighths"}, new Object[]{323, "seveneighths"}, new Object[]{324, "onethird"}, new Object[]{325, "twothirds"}, new Object[]{326, "zerosuperior"}, new Object[]{150, "onesuperior"}, new Object[]{164, "twosuperior"}, new Object[]{169, "threesuperior"}, new Object[]{327, "foursuperior"}, new Object[]{328, "fivesuperior"}, new Object[]{329, "sixsuperior"}, new Object[]{330, "sevensuperior"}, new Object[]{331, "eightsuperior"}, new Object[]{332, "ninesuperior"}, new Object[]{333, "zeroinferior"}, new Object[]{334, "oneinferior"}, new Object[]{335, "twoinferior"}, new Object[]{336, "threeinferior"}, new Object[]{337, "fourinferior"}, new Object[]{338, "fiveinferior"}, new Object[]{339, "sixinferior"}, new Object[]{340, "seveninferior"}, new Object[]{341, "eightinferior"}, new Object[]{342, "nineinferior"}, new Object[]{343, "centinferior"}, new Object[]{344, "dollarinferior"}, new Object[]{345, "periodinferior"}, new Object[]{346, "commainferior"}, new Object[]{347, "Agravesmall"}, new Object[]{348, "Aacutesmall"}, new Object[]{349, "Acircumflexsmall"}, new Object[]{350, "Atildesmall"}, new Object[]{351, "Adieresissmall"}, new Object[]{352, "Aringsmall"}, new Object[]{353, "AEsmall"}, new Object[]{354, "Ccedillasmall"}, new Object[]{355, "Egravesmall"}, new Object[]{356, "Eacutesmall"}, new Object[]{357, "Ecircumflexsmall"}, new Object[]{358, "Edieresissmall"}, new Object[]{359, "Igravesmall"}, new Object[]{360, "Iacutesmall"}, new Object[]{361, "Icircumflexsmall"}, new Object[]{362, "Idieresissmall"}, new Object[]{363, "Ethsmall"}, new Object[]{364, "Ntildesmall"}, new Object[]{365, "Ogravesmall"}, new Object[]{366, "Oacutesmall"}, new Object[]{367, "Ocircumflexsmall"}, new Object[]{368, "Otildesmall"}, new Object[]{369, "Odieresissmall"}, new Object[]{370, "OEsmall"}, new Object[]{371, "Oslashsmall"}, new Object[]{372, "Ugravesmall"}, new Object[]{373, "Uacutesmall"}, new Object[]{374, "Ucircumflexsmall"}, new Object[]{375, "Udieresissmall"}, new Object[]{376, "Yacutesmall"}, new Object[]{377, "Thornsmall"}, new Object[]{378, "Ydieresissmall"}}) {
            int i = gid;
            gid++;
            addSID(i, ((Integer) objArr[0]).intValue(), objArr[1].toString());
        }
    }

    public static CFFExpertCharset getInstance() {
        return INSTANCE;
    }
}
