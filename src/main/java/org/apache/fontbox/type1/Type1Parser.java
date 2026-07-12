package org.apache.fontbox.type1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jdk.internal.dynalink.CallSiteDescriptor;
import jdk.nashorn.internal.runtime.regexp.joni.constants.AsmConstants;
import org.apache.fontbox.afm.AFMParser;
import org.apache.fontbox.encoding.BuiltInEncoding;
import org.apache.fontbox.encoding.StandardEncoding;
import org.apache.fontbox.type1.Token;
import org.apache.pdfbox.pdfparser.BaseParser;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:org/apache/fontbox/type1/Type1Parser.class */
public final class Type1Parser {
    private static final int EEXEC_KEY = 55665;
    private static final int CHARSTRING_KEY = 4330;
    private Type1Lexer lexer;
    private Type1Font font;

    public Type1Font parse(byte[] segment1, byte[] segment2) throws IOException {
        this.font = new Type1Font(segment1, segment2);
        try {
            parseASCII(segment1);
            if (segment2.length > 0) {
                parseBinary(segment2);
            }
            return this.font;
        } catch (NumberFormatException ex) {
            throw new IOException(ex);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:31:0x0107. Please report as an issue. */
    private void parseASCII(byte[] bytes) throws IOException {
        Token token;
        if (bytes.length == 0) {
            throw new IOException("ASCII segment of type 1 font is empty");
        }
        if (bytes.length < 2 || (bytes[0] != 37 && bytes[1] != 33)) {
            throw new IOException("Invalid start of ASCII segment of type 1 font");
        }
        this.lexer = new Type1Lexer(bytes);
        if ("FontDirectory".equals(this.lexer.peekToken().getText())) {
            read(Token.NAME, "FontDirectory");
            read(Token.LITERAL);
            read(Token.NAME, "known");
            read(Token.START_PROC);
            readProcVoid();
            read(Token.START_PROC);
            readProcVoid();
            read(Token.NAME, "ifelse");
        }
        int length = read(Token.INTEGER).intValue();
        read(Token.NAME, "dict");
        readMaybe(Token.NAME, "dup");
        read(Token.NAME, "begin");
        for (int i = 0; i < length && (token = this.lexer.peekToken()) != null && (token.getKind() != Token.NAME || (!"currentdict".equals(token.getText()) && !AsmConstants.END.equals(token.getText()))); i++) {
            String key = read(Token.LITERAL).getText();
            boolean z = -1;
            switch (key.hashCode()) {
                case -1674487645:
                    if (key.equals("Metrics")) {
                        z = 2;
                        break;
                    }
                    break;
                case 429951421:
                    if (key.equals("FontInfo")) {
                        z = false;
                        break;
                    }
                    break;
                case 430904733:
                    if (key.equals("Fontinfo")) {
                        z = true;
                        break;
                    }
                    break;
                case 1775866227:
                    if (key.equals("Encoding")) {
                        z = 3;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                case true:
                    readFontInfo(readSimpleDict());
                    break;
                case true:
                    readSimpleDict();
                    break;
                case true:
                    readEncoding();
                    break;
                default:
                    readSimpleValue(key);
                    break;
            }
        }
        readMaybe(Token.NAME, "currentdict");
        read(Token.NAME, AsmConstants.END);
        read(Token.NAME, "currentfile");
        read(Token.NAME, "eexec");
    }

    private void readSimpleValue(String key) throws IOException {
        List<Token> value = readDictValue();
        boolean z = -1;
        switch (key.hashCode()) {
            case -703032754:
                if (key.equals("StrokeWidth")) {
                    z = 6;
                    break;
                }
                break;
            case -229816116:
                if (key.equals("UniqueID")) {
                    z = 5;
                    break;
                }
                break;
            case 69601:
                if (key.equals("FID")) {
                    z = 7;
                    break;
                }
                break;
            case 429700888:
                if (key.equals(AFMParser.FONT_BBOX)) {
                    z = 4;
                    break;
                }
                break;
            case 430088090:
                if (key.equals(AFMParser.FONT_NAME)) {
                    z = false;
                    break;
                }
                break;
            case 430289993:
                if (key.equals("FontType")) {
                    z = 2;
                    break;
                }
                break;
            case 644005016:
                if (key.equals("PaintType")) {
                    z = true;
                    break;
                }
                break;
            case 969389328:
                if (key.equals("FontMatrix")) {
                    z = 3;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                this.font.fontName = value.get(0).getText();
                return;
            case true:
                this.font.paintType = value.get(0).intValue();
                return;
            case true:
                this.font.fontType = value.get(0).intValue();
                return;
            case true:
                this.font.fontMatrix = arrayToNumbers(value);
                return;
            case true:
                this.font.fontBBox = arrayToNumbers(value);
                return;
            case true:
                this.font.uniqueID = value.get(0).intValue();
                return;
            case true:
                this.font.strokeWidth = value.get(0).floatValue();
                return;
            case true:
                this.font.fontID = value.get(0).getText();
                return;
            default:
                return;
        }
    }

    private void readEncoding() throws IOException {
        if (this.lexer.peekKind(Token.NAME)) {
            String name = this.lexer.nextToken().getText();
            if (name.equals("StandardEncoding")) {
                this.font.encoding = StandardEncoding.INSTANCE;
                readMaybe(Token.NAME, "readonly");
                read(Token.NAME, BaseParser.DEF);
                return;
            }
            throw new IOException("Unknown encoding: " + name);
        }
        read(Token.INTEGER).intValue();
        readMaybe(Token.NAME, "array");
        do {
            if (this.lexer.peekKind(Token.NAME) && (this.lexer.peekToken().getText().equals("dup") || this.lexer.peekToken().getText().equals("readonly") || this.lexer.peekToken().getText().equals(BaseParser.DEF))) {
                Map<Integer, String> codeToName = new HashMap<>();
                while (this.lexer.peekKind(Token.NAME) && this.lexer.peekToken().getText().equals("dup")) {
                    read(Token.NAME, "dup");
                    int code = read(Token.INTEGER).intValue();
                    String name2 = read(Token.LITERAL).getText();
                    read(Token.NAME, "put");
                    codeToName.put(Integer.valueOf(code), name2);
                }
                this.font.encoding = new BuiltInEncoding(codeToName);
                readMaybe(Token.NAME, "readonly");
                read(Token.NAME, BaseParser.DEF);
                return;
            }
        } while (this.lexer.nextToken() != null);
        throw new IOException("Incomplete data while reading encoding of type 1 font");
    }

    private List<Number> arrayToNumbers(List<Token> value) throws IOException {
        List<Number> numbers = new ArrayList<>();
        int size = value.size() - 1;
        for (int i = 1; i < size; i++) {
            Token token = value.get(i);
            if (token.getKind() == Token.REAL) {
                numbers.add(Float.valueOf(token.floatValue()));
            } else if (token.getKind() == Token.INTEGER) {
                numbers.add(Integer.valueOf(token.intValue()));
            } else {
                throw new IOException("Expected INTEGER or REAL but got " + token + " at array position " + i);
            }
        }
        return numbers;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0041. Please report as an issue. */
    private void readFontInfo(Map<String, List<Token>> fontInfo) {
        for (Map.Entry<String, List<Token>> entry : fontInfo.entrySet()) {
            String key = entry.getKey();
            List<Token> value = entry.getValue();
            boolean z = -1;
            switch (key.hashCode()) {
                case -2037328797:
                    if (key.equals(AFMParser.ITALIC_ANGLE)) {
                        z = 5;
                        break;
                    }
                    break;
                case -1955822856:
                    if (key.equals(AFMParser.NOTICE)) {
                        z = true;
                        break;
                    }
                    break;
                case -1707725160:
                    if (key.equals(AFMParser.WEIGHT)) {
                        z = 4;
                        break;
                    }
                    break;
                case -1502948305:
                    if (key.equals(AFMParser.FAMILY_NAME)) {
                        z = 3;
                        break;
                    }
                    break;
                case -429952778:
                    if (key.equals("isFixedPitch")) {
                        z = 6;
                        break;
                    }
                    break;
                case 351608024:
                    if (key.equals("version")) {
                        z = false;
                        break;
                    }
                    break;
                case 425555957:
                    if (key.equals(AFMParser.UNDERLINE_POSITION)) {
                        z = 7;
                        break;
                    }
                    break;
                case 1395496410:
                    if (key.equals(AFMParser.FULL_NAME)) {
                        z = 2;
                        break;
                    }
                    break;
                case 1887629864:
                    if (key.equals(AFMParser.UNDERLINE_THICKNESS)) {
                        z = 8;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    this.font.version = value.get(0).getText();
                    break;
                case true:
                    this.font.notice = value.get(0).getText();
                    break;
                case true:
                    this.font.fullName = value.get(0).getText();
                    break;
                case true:
                    this.font.familyName = value.get(0).getText();
                    break;
                case true:
                    this.font.weight = value.get(0).getText();
                    break;
                case true:
                    this.font.italicAngle = value.get(0).floatValue();
                    break;
                case true:
                    this.font.isFixedPitch = value.get(0).booleanValue();
                    break;
                case true:
                    this.font.underlinePosition = value.get(0).floatValue();
                    break;
                case true:
                    this.font.underlineThickness = value.get(0).floatValue();
                    break;
            }
        }
    }

    private Map<String, List<Token>> readSimpleDict() throws IOException {
        Map<String, List<Token>> dict = new HashMap<>();
        int length = read(Token.INTEGER).intValue();
        read(Token.NAME, "dict");
        readMaybe(Token.NAME, "dup");
        read(Token.NAME, "begin");
        for (int i = 0; i < length && this.lexer.peekToken() != null; i++) {
            if (this.lexer.peekKind(Token.NAME) && !this.lexer.peekToken().getText().equals(AsmConstants.END)) {
                read(Token.NAME);
            }
            if (this.lexer.peekToken() == null || (this.lexer.peekKind(Token.NAME) && this.lexer.peekToken().getText().equals(AsmConstants.END))) {
                break;
            }
            String key = read(Token.LITERAL).getText();
            List<Token> value = readDictValue();
            dict.put(key, value);
        }
        read(Token.NAME, AsmConstants.END);
        readMaybe(Token.NAME, "readonly");
        read(Token.NAME, BaseParser.DEF);
        return dict;
    }

    private List<Token> readDictValue() throws IOException {
        List<Token> value = readValue();
        readDef();
        return value;
    }

    private List<Token> readValue() throws IOException {
        List<Token> value = new ArrayList<>();
        Token token = this.lexer.nextToken();
        if (this.lexer.peekToken() == null) {
            return value;
        }
        value.add(token);
        if (token.getKind() == Token.START_ARRAY) {
            int openArray = 1;
            while (this.lexer.peekToken() != null) {
                if (this.lexer.peekKind(Token.START_ARRAY)) {
                    openArray++;
                }
                Token token2 = this.lexer.nextToken();
                value.add(token2);
                if (token2.getKind() == Token.END_ARRAY) {
                    openArray--;
                    if (openArray == 0) {
                    }
                }
            }
            return value;
        }
        if (token.getKind() == Token.START_PROC) {
            value.addAll(readProc());
        } else if (token.getKind() == Token.START_DICT) {
            read(Token.END_DICT);
            return value;
        }
        readPostScriptWrapper(value);
        return value;
    }

    private void readPostScriptWrapper(List<Token> value) throws IOException {
        if (this.lexer.peekToken() == null) {
            throw new IOException("Missing start token for the system dictionary");
        }
        if ("systemdict".equals(this.lexer.peekToken().getText())) {
            read(Token.NAME, "systemdict");
            read(Token.LITERAL, "internaldict");
            read(Token.NAME, "known");
            read(Token.START_PROC);
            readProcVoid();
            read(Token.START_PROC);
            readProcVoid();
            read(Token.NAME, "ifelse");
            read(Token.START_PROC);
            read(Token.NAME, "pop");
            value.clear();
            value.addAll(readValue());
            read(Token.END_PROC);
            read(Token.NAME, "if");
        }
    }

    private List<Token> readProc() throws IOException {
        List<Token> value = new ArrayList<>();
        int openProc = 1;
        while (this.lexer.peekToken() != null) {
            if (this.lexer.peekKind(Token.START_PROC)) {
                openProc++;
            }
            Token token = this.lexer.nextToken();
            value.add(token);
            if (token.getKind() == Token.END_PROC) {
                openProc--;
                if (openProc == 0) {
                    Token executeonly = readMaybe(Token.NAME, "executeonly");
                    if (executeonly != null) {
                        value.add(executeonly);
                    }
                    return value;
                }
            }
        }
        throw new IOException("Malformed procedure: missing token");
    }

    private void readProcVoid() throws IOException {
        int openProc = 1;
        while (this.lexer.peekToken() != null) {
            if (this.lexer.peekKind(Token.START_PROC)) {
                openProc++;
            }
            Token token = this.lexer.nextToken();
            if (token.getKind() == Token.END_PROC) {
                openProc--;
                if (openProc == 0) {
                    readMaybe(Token.NAME, "executeonly");
                    return;
                }
            }
        }
        throw new IOException("Malformed procedure: missing token");
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0281  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void parseBinary(byte[] bytes) throws IOException {
        byte[] decrypted;
        Token peekToken;
        if (isBinary(bytes)) {
            decrypted = decrypt(bytes, EEXEC_KEY, 4);
        } else {
            decrypted = decrypt(hexToBinary(bytes), EEXEC_KEY, 4);
        }
        this.lexer = new Type1Lexer(decrypted);
        Token peekToken2 = this.lexer.peekToken();
        while (true) {
            peekToken = peekToken2;
            if (peekToken == null || StandardStructureTypes.PRIVATE.equals(peekToken.getText())) {
                break;
            }
            this.lexer.nextToken();
            peekToken2 = this.lexer.peekToken();
        }
        if (peekToken == null) {
            throw new IOException("/Private token not found");
        }
        read(Token.LITERAL, StandardStructureTypes.PRIVATE);
        int length = read(Token.INTEGER).intValue();
        read(Token.NAME, "dict");
        readMaybe(Token.NAME, "dup");
        read(Token.NAME, "begin");
        int lenIV = 4;
        for (int i = 0; i < length && this.lexer.peekKind(Token.LITERAL); i++) {
            String key = read(Token.LITERAL).getText();
            boolean z = -1;
            switch (key.hashCode()) {
                case 2486:
                    if (key.equals("ND")) {
                        z = 3;
                    }
                    switch (z) {
                        case false:
                            readSubrs(lenIV);
                            break;
                        case true:
                            readOtherSubrs();
                            break;
                        case true:
                            lenIV = readDictValue().get(0).intValue();
                            break;
                        case true:
                            read(Token.START_PROC);
                            readMaybe(Token.NAME, "noaccess");
                            read(Token.NAME, BaseParser.DEF);
                            read(Token.END_PROC);
                            readMaybe(Token.NAME, "executeonly");
                            readMaybe(Token.NAME, "readonly");
                            read(Token.NAME, BaseParser.DEF);
                            break;
                        case true:
                            read(Token.START_PROC);
                            readMaybe(Token.NAME, "noaccess");
                            read(Token.NAME);
                            read(Token.END_PROC);
                            readMaybe(Token.NAME, "executeonly");
                            readMaybe(Token.NAME, "readonly");
                            read(Token.NAME, BaseParser.DEF);
                            break;
                        case true:
                            read(Token.START_PROC);
                            readProcVoid();
                            readMaybe(Token.NAME, "bind");
                            readMaybe(Token.NAME, "executeonly");
                            readMaybe(Token.NAME, "readonly");
                            read(Token.NAME, BaseParser.DEF);
                            break;
                        default:
                            readPrivate(key, readDictValue());
                            break;
                    }
                case 2498:
                    if (key.equals("NP")) {
                        z = 4;
                    }
                    switch (z) {
                    }
                    break;
                case 2610:
                    if (key.equals("RD")) {
                        z = 5;
                    }
                    switch (z) {
                    }
                    break;
                case 80235617:
                    if (key.equals("Subrs")) {
                        z = false;
                    }
                    switch (z) {
                    }
                    break;
                case 102857218:
                    if (key.equals("lenIV")) {
                        z = 2;
                    }
                    switch (z) {
                    }
                    break;
                case 1800180593:
                    if (key.equals("OtherSubrs")) {
                        z = true;
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
        do {
            if (this.lexer.peekKind(Token.LITERAL) && this.lexer.peekToken().getText().equals("CharStrings")) {
                read(Token.LITERAL, "CharStrings");
                readCharStrings(lenIV);
                return;
            }
        } while (this.lexer.nextToken() != null);
        throw new IOException("Missing 'CharStrings' dictionary in type 1 font");
    }

    private void readPrivate(String key, List<Token> value) throws IOException {
        boolean z = -1;
        switch (key.hashCode()) {
            case -1043134379:
                if (key.equals("StemSnapH")) {
                    z = 9;
                    break;
                }
                break;
            case -1043134365:
                if (key.equals("StemSnapV")) {
                    z = 10;
                    break;
                }
                break;
            case -1030363955:
                if (key.equals("FamilyOtherBlues")) {
                    z = 3;
                    break;
                }
                break;
            case -763499536:
                if (key.equals("ForceBold")) {
                    z = 11;
                    break;
                }
                break;
            case -427397143:
                if (key.equals("BlueFuzz")) {
                    z = 6;
                    break;
                }
                break;
            case -352964368:
                if (key.equals("BlueScale")) {
                    z = 4;
                    break;
                }
                break;
            case -352807896:
                if (key.equals("BlueShift")) {
                    z = 5;
                    break;
                }
                break;
            case 80206418:
                if (key.equals(AFMParser.STD_HW)) {
                    z = 7;
                    break;
                }
                break;
            case 80206852:
                if (key.equals(AFMParser.STD_VW)) {
                    z = 8;
                    break;
                }
                break;
            case 364444135:
                if (key.equals("LanguageGroup")) {
                    z = 12;
                    break;
                }
                break;
            case 642496053:
                if (key.equals("FamilyBlues")) {
                    z = 2;
                    break;
                }
                break;
            case 1784230473:
                if (key.equals("OtherBlues")) {
                    z = true;
                    break;
                }
                break;
            case 2027383356:
                if (key.equals("BlueValues")) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                this.font.blueValues = arrayToNumbers(value);
                return;
            case true:
                this.font.otherBlues = arrayToNumbers(value);
                return;
            case true:
                this.font.familyBlues = arrayToNumbers(value);
                return;
            case true:
                this.font.familyOtherBlues = arrayToNumbers(value);
                return;
            case true:
                this.font.blueScale = value.get(0).floatValue();
                return;
            case true:
                this.font.blueShift = value.get(0).intValue();
                return;
            case true:
                this.font.blueFuzz = value.get(0).intValue();
                return;
            case true:
                this.font.stdHW = arrayToNumbers(value);
                return;
            case true:
                this.font.stdVW = arrayToNumbers(value);
                return;
            case true:
                this.font.stemSnapH = arrayToNumbers(value);
                return;
            case true:
                this.font.stemSnapV = arrayToNumbers(value);
                return;
            case true:
                this.font.forceBold = value.get(0).booleanValue();
                return;
            case true:
                this.font.languageGroup = value.get(0).intValue();
                return;
            default:
                return;
        }
    }

    private void readSubrs(int lenIV) throws IOException {
        int length = read(Token.INTEGER).intValue();
        for (int i = 0; i < length; i++) {
            this.font.subrs.add(null);
        }
        read(Token.NAME, "array");
        for (int i2 = 0; i2 < length && this.lexer.peekToken() != null && this.lexer.peekKind(Token.NAME) && this.lexer.peekToken().getText().equals("dup"); i2++) {
            read(Token.NAME, "dup");
            Token index = read(Token.INTEGER);
            read(Token.INTEGER);
            Token charstring = read(Token.CHARSTRING);
            int j = index.intValue();
            if (j < this.font.subrs.size()) {
                this.font.subrs.set(j, decrypt(charstring.getData(), CHARSTRING_KEY, lenIV));
            }
            readPut();
        }
        readDef();
    }

    private void readOtherSubrs() throws IOException {
        if (this.lexer.peekToken() == null) {
            throw new IOException("Missing start token of OtherSubrs procedure");
        }
        if (this.lexer.peekKind(Token.START_ARRAY)) {
            readValue();
            readDef();
            return;
        }
        int length = read(Token.INTEGER).intValue();
        read(Token.NAME, "array");
        for (int i = 0; i < length; i++) {
            read(Token.NAME, "dup");
            read(Token.INTEGER);
            readValue();
            readPut();
        }
        readDef();
    }

    private void readCharStrings(int lenIV) throws IOException {
        int length = read(Token.INTEGER).intValue();
        read(Token.NAME, "dict");
        read(Token.NAME, "dup");
        read(Token.NAME, "begin");
        for (int i = 0; i < length && this.lexer.peekToken() != null && (!this.lexer.peekKind(Token.NAME) || !this.lexer.peekToken().getText().equals(AsmConstants.END)); i++) {
            String name = read(Token.LITERAL).getText();
            read(Token.INTEGER);
            Token charstring = read(Token.CHARSTRING);
            this.font.charstrings.put(name, decrypt(charstring.getData(), CHARSTRING_KEY, lenIV));
            readDef();
        }
        read(Token.NAME, AsmConstants.END);
    }

    private void readDef() throws IOException {
        readMaybe(Token.NAME, "readonly");
        readMaybe(Token.NAME, "noaccess");
        Token token = read(Token.NAME);
        String text = token.getText();
        boolean z = -1;
        switch (text.hashCode()) {
            case 2486:
                if (text.equals("ND")) {
                    z = false;
                    break;
                }
                break;
            case 3889:
                if (text.equals("|-")) {
                    z = true;
                    break;
                }
                break;
            case 1035775589:
                if (text.equals("noaccess")) {
                    z = 2;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
            case true:
                return;
            case true:
                token = read(Token.NAME);
                break;
        }
        if (token.getText().equals(BaseParser.DEF)) {
        } else {
            throw new IOException("Found " + token + " but expected ND");
        }
    }

    private void readPut() throws IOException {
        readMaybe(Token.NAME, "readonly");
        Token token = read(Token.NAME);
        String text = token.getText();
        boolean z = -1;
        switch (text.hashCode()) {
            case 124:
                if (text.equals(CallSiteDescriptor.OPERATOR_DELIMITER)) {
                    z = true;
                    break;
                }
                break;
            case 2498:
                if (text.equals("NP")) {
                    z = false;
                    break;
                }
                break;
            case 1035775589:
                if (text.equals("noaccess")) {
                    z = 2;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
            case true:
                return;
            case true:
                token = read(Token.NAME);
                break;
        }
        if (token.getText().equals("put")) {
        } else {
            throw new IOException("Found " + token + " but expected NP");
        }
    }

    private Token read(Token.Kind kind) throws IOException {
        Token token = this.lexer.nextToken();
        if (token == null || token.getKind() != kind) {
            throw new IOException("Found " + token + " but expected " + kind);
        }
        return token;
    }

    private void read(Token.Kind kind, String name) throws IOException {
        Token token = read(kind);
        if (token.getText() == null || !token.getText().equals(name)) {
            throw new IOException("Found " + token + " but expected " + name);
        }
    }

    private Token readMaybe(Token.Kind kind, String name) throws IOException {
        if (this.lexer.peekKind(kind) && this.lexer.peekToken().getText().equals(name)) {
            return this.lexer.nextToken();
        }
        return null;
    }

    private byte[] decrypt(byte[] cipherBytes, int r, int n) {
        if (n == -1) {
            return cipherBytes;
        }
        if (cipherBytes.length == 0 || n > cipherBytes.length) {
            return new byte[0];
        }
        byte[] plainBytes = new byte[cipherBytes.length - n];
        for (int i = 0; i < cipherBytes.length; i++) {
            int cipher = cipherBytes[i] & 255;
            int plain = cipher ^ (r >> 8);
            if (i >= n) {
                plainBytes[i - n] = (byte) plain;
            }
            r = (((cipher + r) * 52845) + 22719) & 65535;
        }
        return plainBytes;
    }

    private boolean isBinary(byte[] bytes) {
        if (bytes.length < 4) {
            return true;
        }
        for (int i = 0; i < 4; i++) {
            byte by = bytes[i];
            if (by != 10 && by != 13 && by != 32 && by != 9 && Character.digit((char) by, 16) == -1) {
                return true;
            }
        }
        return false;
    }

    private byte[] hexToBinary(byte[] bytes) {
        int len = 0;
        for (byte by : bytes) {
            if (Character.digit((char) by, 16) != -1) {
                len++;
            }
        }
        byte[] res = new byte[len / 2];
        int r = 0;
        int prev = -1;
        for (byte by2 : bytes) {
            int digit = Character.digit((char) by2, 16);
            if (digit != -1) {
                if (prev == -1) {
                    prev = digit;
                } else {
                    int i = r;
                    r++;
                    res[i] = (byte) ((prev * 16) + digit);
                    prev = -1;
                }
            }
        }
        return res;
    }
}
