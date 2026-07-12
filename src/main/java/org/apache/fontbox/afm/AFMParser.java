package org.apache.fontbox.afm;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;
import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:org/apache/fontbox/afm/AFMParser.class */
public class AFMParser {
    public static final String COMMENT = "Comment";
    public static final String START_FONT_METRICS = "StartFontMetrics";
    public static final String END_FONT_METRICS = "EndFontMetrics";
    public static final String FONT_NAME = "FontName";
    public static final String FULL_NAME = "FullName";
    public static final String FAMILY_NAME = "FamilyName";
    public static final String WEIGHT = "Weight";
    public static final String FONT_BBOX = "FontBBox";
    public static final String VERSION = "Version";
    public static final String NOTICE = "Notice";
    public static final String ENCODING_SCHEME = "EncodingScheme";
    public static final String MAPPING_SCHEME = "MappingScheme";
    public static final String ESC_CHAR = "EscChar";
    public static final String CHARACTER_SET = "CharacterSet";
    public static final String CHARACTERS = "Characters";
    public static final String IS_BASE_FONT = "IsBaseFont";
    public static final String V_VECTOR = "VVector";
    public static final String IS_FIXED_V = "IsFixedV";
    public static final String CAP_HEIGHT = "CapHeight";
    public static final String X_HEIGHT = "XHeight";
    public static final String ASCENDER = "Ascender";
    public static final String DESCENDER = "Descender";
    public static final String UNDERLINE_POSITION = "UnderlinePosition";
    public static final String UNDERLINE_THICKNESS = "UnderlineThickness";
    public static final String ITALIC_ANGLE = "ItalicAngle";
    public static final String CHAR_WIDTH = "CharWidth";
    public static final String IS_FIXED_PITCH = "IsFixedPitch";
    public static final String START_CHAR_METRICS = "StartCharMetrics";
    public static final String END_CHAR_METRICS = "EndCharMetrics";
    public static final String CHARMETRICS_C = "C";
    public static final String CHARMETRICS_CH = "CH";
    public static final String CHARMETRICS_WX = "WX";
    public static final String CHARMETRICS_W0X = "W0X";
    public static final String CHARMETRICS_W1X = "W1X";
    public static final String CHARMETRICS_WY = "WY";
    public static final String CHARMETRICS_W0Y = "W0Y";
    public static final String CHARMETRICS_W1Y = "W1Y";
    public static final String CHARMETRICS_W = "W";
    public static final String CHARMETRICS_W0 = "W0";
    public static final String CHARMETRICS_W1 = "W1";
    public static final String CHARMETRICS_VV = "VV";
    public static final String CHARMETRICS_N = "N";
    public static final String CHARMETRICS_B = "B";
    public static final String CHARMETRICS_L = "L";
    public static final String STD_HW = "StdHW";
    public static final String STD_VW = "StdVW";
    public static final String START_TRACK_KERN = "StartTrackKern";
    public static final String END_TRACK_KERN = "EndTrackKern";
    public static final String START_KERN_DATA = "StartKernData";
    public static final String END_KERN_DATA = "EndKernData";
    public static final String START_KERN_PAIRS = "StartKernPairs";
    public static final String END_KERN_PAIRS = "EndKernPairs";
    public static final String START_KERN_PAIRS0 = "StartKernPairs0";
    public static final String START_KERN_PAIRS1 = "StartKernPairs1";
    public static final String START_COMPOSITES = "StartComposites";
    public static final String END_COMPOSITES = "EndComposites";
    public static final String CC = "CC";
    public static final String PCC = "PCC";
    public static final String KERN_PAIR_KP = "KP";
    public static final String KERN_PAIR_KPH = "KPH";
    public static final String KERN_PAIR_KPX = "KPX";
    public static final String KERN_PAIR_KPY = "KPY";
    private static final int BITS_IN_HEX = 16;
    private final InputStream input;

    public AFMParser(InputStream in) {
        this.input = in;
    }

    public FontMetrics parse() throws IOException {
        return parseFontMetric(false);
    }

    public FontMetrics parse(boolean reducedDataset) throws IOException {
        return parseFontMetric(reducedDataset);
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x03be A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x03c9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x03d4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x040a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0415 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0420 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:120:0x042b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0436 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0441 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x044c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0457 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0462 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0480 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:141:0x048b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0496 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x04a1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x04ac A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x04b7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x04c2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:159:0x04cd A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:162:0x04d8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x04e3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:168:0x04ee A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:171:0x04f9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0517 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0522 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:180:0x052c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0538 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0544 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x03a8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x03b3 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private FontMetrics parseFontMetric(boolean reducedDataset) throws IOException {
        String nextCommand;
        readCommand(START_FONT_METRICS);
        FontMetrics fontMetrics = new FontMetrics();
        fontMetrics.setAFMVersion(readFloat());
        boolean charMetricsRead = false;
        while (true) {
            nextCommand = readString();
            if (!END_FONT_METRICS.equals(nextCommand)) {
                boolean z = -1;
                switch (nextCommand.hashCode()) {
                    case -2037328797:
                        if (nextCommand.equals(ITALIC_ANGLE)) {
                            z = 24;
                        }
                        switch (z) {
                            case false:
                                fontMetrics.setFontName(readLine());
                                break;
                            case true:
                                fontMetrics.setFullName(readLine());
                                break;
                            case true:
                                fontMetrics.setFamilyName(readLine());
                                break;
                            case true:
                                fontMetrics.setWeight(readLine());
                                break;
                            case true:
                                BoundingBox bBox = new BoundingBox();
                                bBox.setLowerLeftX(readFloat());
                                bBox.setLowerLeftY(readFloat());
                                bBox.setUpperRightX(readFloat());
                                bBox.setUpperRightY(readFloat());
                                fontMetrics.setFontBBox(bBox);
                                break;
                            case true:
                                fontMetrics.setFontVersion(readLine());
                                break;
                            case true:
                                fontMetrics.setNotice(readLine());
                                break;
                            case true:
                                fontMetrics.setEncodingScheme(readLine());
                                break;
                            case true:
                                fontMetrics.setMappingScheme(readInt());
                                break;
                            case true:
                                fontMetrics.setEscChar(readInt());
                                break;
                            case true:
                                fontMetrics.setCharacterSet(readLine());
                                break;
                            case true:
                                fontMetrics.setCharacters(readInt());
                                break;
                            case true:
                                fontMetrics.setIsBaseFont(readBoolean());
                                break;
                            case true:
                                float[] vector = {readFloat(), readFloat()};
                                fontMetrics.setVVector(vector);
                                break;
                            case true:
                                fontMetrics.setIsFixedV(readBoolean());
                                break;
                            case true:
                                fontMetrics.setCapHeight(readFloat());
                                break;
                            case true:
                                fontMetrics.setXHeight(readFloat());
                                break;
                            case true:
                                fontMetrics.setAscender(readFloat());
                                break;
                            case true:
                                fontMetrics.setDescender(readFloat());
                                break;
                            case true:
                                fontMetrics.setStandardHorizontalWidth(readFloat());
                                break;
                            case true:
                                fontMetrics.setStandardVerticalWidth(readFloat());
                                break;
                            case true:
                                fontMetrics.addComment(readLine());
                                break;
                            case true:
                                fontMetrics.setUnderlinePosition(readFloat());
                                break;
                            case true:
                                fontMetrics.setUnderlineThickness(readFloat());
                                break;
                            case true:
                                fontMetrics.setItalicAngle(readFloat());
                                break;
                            case true:
                                float[] widths = {readFloat(), readFloat()};
                                fontMetrics.setCharWidth(widths);
                                break;
                            case true:
                                fontMetrics.setFixedPitch(readBoolean());
                                break;
                            case true:
                                charMetricsRead = parseCharMetrics(fontMetrics);
                                break;
                            case true:
                                if (!reducedDataset) {
                                    parseKernData(fontMetrics);
                                    break;
                                } else {
                                    break;
                                }
                            case true:
                                if (!reducedDataset) {
                                    parseComposites(fontMetrics);
                                    break;
                                } else {
                                    break;
                                }
                            default:
                                if (reducedDataset && charMetricsRead) {
                                    break;
                                }
                                break;
                        }
                        break;
                    case -1955822856:
                        if (nextCommand.equals(NOTICE)) {
                            z = 6;
                        }
                        switch (z) {
                        }
                        break;
                    case -1707725160:
                        if (nextCommand.equals(WEIGHT)) {
                            z = 3;
                        }
                        switch (z) {
                        }
                        break;
                    case -1679915457:
                        if (nextCommand.equals("Comment")) {
                            z = 21;
                        }
                        switch (z) {
                        }
                        break;
                    case -1587834632:
                        if (nextCommand.equals(ENCODING_SCHEME)) {
                            z = 7;
                        }
                        switch (z) {
                        }
                        break;
                    case -1502948305:
                        if (nextCommand.equals(FAMILY_NAME)) {
                            z = 2;
                        }
                        switch (z) {
                        }
                        break;
                    case -1346249825:
                        if (nextCommand.equals(X_HEIGHT)) {
                            z = 16;
                        }
                        switch (z) {
                        }
                        break;
                    case -1278893927:
                        if (nextCommand.equals(CHARACTER_SET)) {
                            z = 10;
                        }
                        switch (z) {
                        }
                        break;
                    case -1056078198:
                        if (nextCommand.equals(CHARACTERS)) {
                            z = 11;
                        }
                        switch (z) {
                        }
                        break;
                    case -812484743:
                        if (nextCommand.equals(CAP_HEIGHT)) {
                            z = 15;
                        }
                        switch (z) {
                        }
                        break;
                    case -802988361:
                        if (nextCommand.equals(DESCENDER)) {
                            z = 18;
                        }
                        switch (z) {
                        }
                        break;
                    case -766799081:
                        if (nextCommand.equals(ASCENDER)) {
                            z = 17;
                        }
                        switch (z) {
                        }
                        break;
                    case -735875088:
                        if (nextCommand.equals(CHAR_WIDTH)) {
                            z = 25;
                        }
                        switch (z) {
                        }
                        break;
                    case -535470772:
                        if (nextCommand.equals(IS_FIXED_V)) {
                            z = 14;
                        }
                        switch (z) {
                        }
                        break;
                    case -521717206:
                        if (nextCommand.equals(IS_BASE_FONT)) {
                            z = 12;
                        }
                        switch (z) {
                        }
                        break;
                    case -265632490:
                        if (nextCommand.equals(IS_FIXED_PITCH)) {
                            z = 26;
                        }
                        switch (z) {
                        }
                        break;
                    case -89974797:
                        if (nextCommand.equals(MAPPING_SCHEME)) {
                            z = 8;
                        }
                        switch (z) {
                        }
                        break;
                    case 80206418:
                        if (nextCommand.equals(STD_HW)) {
                            z = 19;
                        }
                        switch (z) {
                        }
                        break;
                    case 80206852:
                        if (nextCommand.equals(STD_VW)) {
                            z = 20;
                        }
                        switch (z) {
                        }
                        break;
                    case 199124555:
                        if (nextCommand.equals(ESC_CHAR)) {
                            z = 9;
                        }
                        switch (z) {
                        }
                        break;
                    case 425555957:
                        if (nextCommand.equals(UNDERLINE_POSITION)) {
                            z = 22;
                        }
                        switch (z) {
                        }
                        break;
                    case 429700888:
                        if (nextCommand.equals(FONT_BBOX)) {
                            z = 4;
                        }
                        switch (z) {
                        }
                        break;
                    case 430088090:
                        if (nextCommand.equals(FONT_NAME)) {
                            z = false;
                        }
                        switch (z) {
                        }
                        break;
                    case 460563630:
                        if (nextCommand.equals(START_COMPOSITES)) {
                            z = 29;
                        }
                        switch (z) {
                        }
                        break;
                    case 1239215170:
                        if (nextCommand.equals(START_KERN_DATA)) {
                            z = 28;
                        }
                        switch (z) {
                        }
                        break;
                    case 1395496410:
                        if (nextCommand.equals(FULL_NAME)) {
                            z = true;
                        }
                        switch (z) {
                        }
                        break;
                    case 1574352185:
                        if (nextCommand.equals(V_VECTOR)) {
                            z = 13;
                        }
                        switch (z) {
                        }
                        break;
                    case 1672376043:
                        if (nextCommand.equals(START_CHAR_METRICS)) {
                            z = 27;
                        }
                        switch (z) {
                        }
                        break;
                    case 1887629864:
                        if (nextCommand.equals(UNDERLINE_THICKNESS)) {
                            z = 23;
                        }
                        switch (z) {
                        }
                        break;
                    case 2016261304:
                        if (nextCommand.equals(VERSION)) {
                            z = 5;
                        }
                        switch (z) {
                        }
                        break;
                    default:
                        switch (z) {
                        }
                        break;
                }
            } else {
                return fontMetrics;
            }
        }
        throw new IOException("Unknown AFM key '" + nextCommand + OperatorName.SHOW_TEXT_LINE);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00d6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00de A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00e6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ee A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0098 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void parseKernData(FontMetrics fontMetrics) throws IOException {
        while (true) {
            String nextCommand = readString();
            if (!nextCommand.equals(END_KERN_DATA)) {
                boolean z = -1;
                switch (nextCommand.hashCode()) {
                    case -626791713:
                        if (nextCommand.equals(START_TRACK_KERN)) {
                            z = false;
                        }
                        switch (z) {
                            case false:
                                int countTrackKern = readInt();
                                for (int i = 0; i < countTrackKern; i++) {
                                    fontMetrics.addTrackKern(new TrackKern(readInt(), readFloat(), readFloat(), readFloat(), readFloat()));
                                }
                                readCommand(END_TRACK_KERN);
                                break;
                            case true:
                                parseKernPairs(fontMetrics);
                                break;
                            case true:
                                parseKernPairs0(fontMetrics);
                                break;
                            case true:
                                parseKernPairs1(fontMetrics);
                                break;
                            default:
                                throw new IOException("Unknown kerning data type '" + nextCommand + OperatorName.SHOW_TEXT_LINE);
                        }
                    case -227963071:
                        if (nextCommand.equals(START_KERN_PAIRS)) {
                            z = true;
                        }
                        switch (z) {
                        }
                        break;
                    case 1523079439:
                        if (nextCommand.equals(START_KERN_PAIRS0)) {
                            z = 2;
                        }
                        switch (z) {
                        }
                        break;
                    case 1523079440:
                        if (nextCommand.equals(START_KERN_PAIRS1)) {
                            z = 3;
                        }
                        switch (z) {
                        }
                        break;
                    default:
                        switch (z) {
                        }
                        break;
                }
            } else {
                return;
            }
        }
    }

    private void parseKernPairs(FontMetrics fontMetrics) throws IOException {
        int countKernPairs = readInt();
        for (int i = 0; i < countKernPairs; i++) {
            fontMetrics.addKernPair(parseKernPair());
        }
        readCommand(END_KERN_PAIRS);
    }

    private void parseKernPairs0(FontMetrics fontMetrics) throws IOException {
        int countKernPairs = readInt();
        for (int i = 0; i < countKernPairs; i++) {
            fontMetrics.addKernPair0(parseKernPair());
        }
        readCommand(END_KERN_PAIRS);
    }

    private void parseKernPairs1(FontMetrics fontMetrics) throws IOException {
        int countKernPairs = readInt();
        for (int i = 0; i < countKernPairs; i++) {
            fontMetrics.addKernPair1(parseKernPair());
        }
        readCommand(END_KERN_PAIRS);
    }

    private KernPair parseKernPair() throws IOException {
        String cmd = readString();
        boolean z = -1;
        switch (cmd.hashCode()) {
            case 2405:
                if (cmd.equals(KERN_PAIR_KP)) {
                    z = false;
                    break;
                }
                break;
            case 74627:
                if (cmd.equals(KERN_PAIR_KPH)) {
                    z = true;
                    break;
                }
                break;
            case 74643:
                if (cmd.equals(KERN_PAIR_KPX)) {
                    z = 2;
                    break;
                }
                break;
            case 74644:
                if (cmd.equals(KERN_PAIR_KPY)) {
                    z = 3;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                return new KernPair(readString(), readString(), readFloat(), readFloat());
            case true:
                return new KernPair(hexToString(readString()), hexToString(readString()), readFloat(), readFloat());
            case true:
                return new KernPair(readString(), readString(), readFloat(), 0.0f);
            case true:
                return new KernPair(readString(), readString(), 0.0f, readFloat());
            default:
                throw new IOException("Error expected kern pair command actual='" + cmd + OperatorName.SHOW_TEXT_LINE);
        }
    }

    private String hexToString(String hexToString) throws IOException {
        if (hexToString.length() < 2) {
            throw new IOException("Error: Expected hex string of length >= 2 not='" + hexToString);
        }
        if (hexToString.charAt(0) != '<' || hexToString.charAt(hexToString.length() - 1) != '>') {
            throw new IOException("String should be enclosed by angle brackets '" + hexToString + OperatorName.SHOW_TEXT_LINE);
        }
        String hexString = hexToString.substring(1, hexToString.length() - 1);
        byte[] data = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i += 2) {
            String hex = Character.toString(hexString.charAt(i)) + hexString.charAt(i + 1);
            data[i / 2] = (byte) parseInt(hex, 16);
        }
        return new String(data, StandardCharsets.ISO_8859_1);
    }

    private void parseComposites(FontMetrics fontMetrics) throws IOException {
        int countComposites = readInt();
        for (int i = 0; i < countComposites; i++) {
            fontMetrics.addComposite(parseComposite());
        }
        readCommand(END_COMPOSITES);
    }

    private Composite parseComposite() throws IOException {
        String partData = readLine();
        StringTokenizer tokenizer = new StringTokenizer(partData, " ;");
        String cc = tokenizer.nextToken();
        if (!cc.equals(CC)) {
            throw new IOException("Expected 'CC' actual='" + cc + OperatorName.SHOW_TEXT_LINE);
        }
        String name = tokenizer.nextToken();
        Composite composite = new Composite(name);
        int partCount = parseInt(tokenizer.nextToken());
        for (int i = 0; i < partCount; i++) {
            String pcc = tokenizer.nextToken();
            if (!pcc.equals(PCC)) {
                throw new IOException("Expected 'PCC' actual='" + pcc + OperatorName.SHOW_TEXT_LINE);
            }
            String partName = tokenizer.nextToken();
            int x = parseInt(tokenizer.nextToken());
            int y = parseInt(tokenizer.nextToken());
            composite.addPart(new CompositePart(partName, x, y));
        }
        return composite;
    }

    private boolean parseCharMetrics(FontMetrics fontMetrics) throws IOException {
        int countMetrics = readInt();
        for (int i = 0; i < countMetrics; i++) {
            fontMetrics.addCharMetric(parseCharMetric());
        }
        readCommand(END_CHAR_METRICS);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x01f4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x020c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0226 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x023a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x024e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0262 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0276 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x028a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x029e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x02c9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x02f4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x031f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x034a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x035a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x03a5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x03c4 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private CharMetric parseCharMetric() throws IOException {
        CharMetric charMetric = new CharMetric();
        String metrics = readLine();
        StringTokenizer metricsTokenizer = new StringTokenizer(metrics);
        while (metricsTokenizer.hasMoreTokens()) {
            String nextCommand = metricsTokenizer.nextToken();
            boolean z = -1;
            switch (nextCommand.hashCode()) {
                case 66:
                    if (nextCommand.equals("B")) {
                        z = 13;
                    }
                    switch (z) {
                        case false:
                            String charCodeC = metricsTokenizer.nextToken();
                            charMetric.setCharacterCode(parseInt(charCodeC));
                            verifySemicolon(metricsTokenizer);
                            break;
                        case true:
                            String charCodeCH = metricsTokenizer.nextToken();
                            charMetric.setCharacterCode(parseInt(charCodeCH, 16));
                            verifySemicolon(metricsTokenizer);
                            break;
                        case true:
                            charMetric.setWx(parseFloat(metricsTokenizer.nextToken()));
                            verifySemicolon(metricsTokenizer);
                            break;
                        case true:
                            charMetric.setW0x(parseFloat(metricsTokenizer.nextToken()));
                            verifySemicolon(metricsTokenizer);
                            break;
                        case true:
                            charMetric.setW1x(parseFloat(metricsTokenizer.nextToken()));
                            verifySemicolon(metricsTokenizer);
                            break;
                        case true:
                            charMetric.setWy(parseFloat(metricsTokenizer.nextToken()));
                            verifySemicolon(metricsTokenizer);
                            break;
                        case true:
                            charMetric.setW0y(parseFloat(metricsTokenizer.nextToken()));
                            verifySemicolon(metricsTokenizer);
                            break;
                        case true:
                            charMetric.setW1y(parseFloat(metricsTokenizer.nextToken()));
                            verifySemicolon(metricsTokenizer);
                            break;
                        case true:
                            float[] w = {parseFloat(metricsTokenizer.nextToken()), parseFloat(metricsTokenizer.nextToken())};
                            charMetric.setW(w);
                            verifySemicolon(metricsTokenizer);
                            break;
                        case true:
                            float[] w0 = {parseFloat(metricsTokenizer.nextToken()), parseFloat(metricsTokenizer.nextToken())};
                            charMetric.setW0(w0);
                            verifySemicolon(metricsTokenizer);
                            break;
                        case true:
                            float[] w1 = {parseFloat(metricsTokenizer.nextToken()), parseFloat(metricsTokenizer.nextToken())};
                            charMetric.setW1(w1);
                            verifySemicolon(metricsTokenizer);
                            break;
                        case true:
                            float[] vv = {parseFloat(metricsTokenizer.nextToken()), parseFloat(metricsTokenizer.nextToken())};
                            charMetric.setVv(vv);
                            verifySemicolon(metricsTokenizer);
                            break;
                        case true:
                            charMetric.setName(metricsTokenizer.nextToken());
                            verifySemicolon(metricsTokenizer);
                            break;
                        case true:
                            BoundingBox box = new BoundingBox();
                            box.setLowerLeftX(parseFloat(metricsTokenizer.nextToken()));
                            box.setLowerLeftY(parseFloat(metricsTokenizer.nextToken()));
                            box.setUpperRightX(parseFloat(metricsTokenizer.nextToken()));
                            box.setUpperRightY(parseFloat(metricsTokenizer.nextToken()));
                            charMetric.setBoundingBox(box);
                            verifySemicolon(metricsTokenizer);
                            break;
                        case true:
                            Ligature lig = new Ligature(metricsTokenizer.nextToken(), metricsTokenizer.nextToken());
                            charMetric.addLigature(lig);
                            verifySemicolon(metricsTokenizer);
                            break;
                        default:
                            throw new IOException("Unknown CharMetrics command '" + nextCommand + OperatorName.SHOW_TEXT_LINE);
                    }
                case 67:
                    if (nextCommand.equals("C")) {
                        z = false;
                    }
                    switch (z) {
                    }
                    break;
                case 76:
                    if (nextCommand.equals("L")) {
                        z = 14;
                    }
                    switch (z) {
                    }
                    break;
                case 78:
                    if (nextCommand.equals("N")) {
                        z = 12;
                    }
                    switch (z) {
                    }
                    break;
                case 87:
                    if (nextCommand.equals("W")) {
                        z = 8;
                    }
                    switch (z) {
                    }
                    break;
                case 2149:
                    if (nextCommand.equals(CHARMETRICS_CH)) {
                        z = true;
                    }
                    switch (z) {
                    }
                    break;
                case 2745:
                    if (nextCommand.equals(CHARMETRICS_W0)) {
                        z = 9;
                    }
                    switch (z) {
                    }
                    break;
                case 2746:
                    if (nextCommand.equals(CHARMETRICS_W1)) {
                        z = 10;
                    }
                    switch (z) {
                    }
                    break;
                case 2752:
                    if (nextCommand.equals(CHARMETRICS_VV)) {
                        z = 11;
                    }
                    switch (z) {
                    }
                    break;
                case 2785:
                    if (nextCommand.equals(CHARMETRICS_WX)) {
                        z = 2;
                    }
                    switch (z) {
                    }
                    break;
                case 2786:
                    if (nextCommand.equals(CHARMETRICS_WY)) {
                        z = 5;
                    }
                    switch (z) {
                    }
                    break;
                case 85183:
                    if (nextCommand.equals(CHARMETRICS_W0X)) {
                        z = 3;
                    }
                    switch (z) {
                    }
                    break;
                case 85184:
                    if (nextCommand.equals(CHARMETRICS_W0Y)) {
                        z = 6;
                    }
                    switch (z) {
                    }
                    break;
                case 85214:
                    if (nextCommand.equals(CHARMETRICS_W1X)) {
                        z = 4;
                    }
                    switch (z) {
                    }
                    break;
                case 85215:
                    if (nextCommand.equals(CHARMETRICS_W1Y)) {
                        z = 7;
                    }
                    switch (z) {
                    }
                    break;
                default:
                    switch (z) {
                    }
                    break;
            }
        }
        return charMetric;
    }

    private void verifySemicolon(StringTokenizer tokenizer) throws IOException {
        if (tokenizer.hasMoreTokens()) {
            String semicolon = tokenizer.nextToken();
            if (!RuntimeConstants.SIG_ENDCLASS.equals(semicolon)) {
                throw new IOException("Error: Expected semicolon in stream actual='" + semicolon + OperatorName.SHOW_TEXT_LINE);
            }
            return;
        }
        throw new IOException("CharMetrics is missing a semicolon after a command");
    }

    private boolean readBoolean() throws IOException {
        return Boolean.parseBoolean(readString());
    }

    private int readInt() throws IOException {
        return parseInt(readString(), 10);
    }

    private int parseInt(String intValue) throws IOException {
        return parseInt(intValue, 10);
    }

    private int parseInt(String intValue, int radix) throws IOException {
        try {
            return Integer.parseInt(intValue, radix);
        } catch (NumberFormatException e) {
            throw new IOException("Error parsing AFM document:" + e, e);
        }
    }

    private float readFloat() throws IOException {
        return parseFloat(readString());
    }

    private float parseFloat(String floatValue) throws IOException {
        try {
            return Float.parseFloat(floatValue);
        } catch (NumberFormatException e) {
            throw new IOException("Error parsing AFM document:" + e, e);
        }
    }

    private String readLine() throws IOException {
        int nextByte;
        StringBuilder buf = new StringBuilder(60);
        int read = this.input.read();
        while (true) {
            nextByte = read;
            if (!isWhitespace(nextByte)) {
                break;
            }
            read = this.input.read();
        }
        buf.append((char) nextByte);
        int read2 = this.input.read();
        while (true) {
            int nextByte2 = read2;
            if (nextByte2 == -1 || isEOL(nextByte2)) {
                break;
            }
            buf.append((char) nextByte2);
            read2 = this.input.read();
        }
        return buf.toString();
    }

    private String readString() throws IOException {
        int nextByte;
        StringBuilder buf = new StringBuilder(24);
        int read = this.input.read();
        while (true) {
            nextByte = read;
            if (!isWhitespace(nextByte)) {
                break;
            }
            read = this.input.read();
        }
        buf.append((char) nextByte);
        int read2 = this.input.read();
        while (true) {
            int nextByte2 = read2;
            if (nextByte2 == -1 || isWhitespace(nextByte2)) {
                break;
            }
            buf.append((char) nextByte2);
            read2 = this.input.read();
        }
        return buf.toString();
    }

    private void readCommand(String expectedCommand) throws IOException {
        String command = readString();
        if (!expectedCommand.equals(command)) {
            throw new IOException("Error: Expected '" + expectedCommand + "' actual '" + command + OperatorName.SHOW_TEXT_LINE);
        }
    }

    private boolean isEOL(int character) {
        return character == 13 || character == 10;
    }

    private boolean isWhitespace(int character) {
        return character == 32 || character == 9 || character == 13 || character == 10;
    }
}
