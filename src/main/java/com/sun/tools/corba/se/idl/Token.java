package com.sun.tools.corba.se.idl;

import com.formdev.flatlaf.FlatClientProperties;
import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import com.sun.tools.doclint.DocLint;
import jdk.internal.dynalink.CallSiteDescriptor;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.slf4j.Marker;
import sun.rmi.rmic.iiop.Constants;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/Token.class */
class Token {
    static final int Any = 0;
    static final int Attribute = 1;
    static final int Boolean = 2;
    static final int Case = 3;
    static final int Char = 4;
    static final int Const = 5;
    static final int Context = 6;
    static final int Default = 7;
    static final int Double = 8;
    static final int Enum = 9;
    static final int Exception = 10;
    static final int FALSE = 11;
    static final int Fixed = 12;
    static final int Float = 13;
    static final int In = 14;
    static final int Inout = 15;
    static final int Interface = 16;
    static final int Long = 17;
    static final int Module = 18;
    static final int Native = 19;
    static final int Object = 20;
    static final int Octet = 21;
    static final int Oneway = 22;
    static final int Out = 23;
    static final int Raises = 24;
    static final int Readonly = 25;
    static final int Sequence = 26;
    static final int Short = 27;
    static final int String = 28;
    static final int Struct = 29;
    static final int Switch = 30;
    static final int TRUE = 31;
    static final int Typedef = 32;
    static final int Unsigned = 33;
    static final int Union = 34;
    static final int Void = 35;
    static final int Wchar = 36;
    static final int Wstring = 37;
    static final int Init = 38;
    static final int Abstract = 39;
    static final int Custom = 40;
    static final int Private = 41;
    static final int Public = 42;
    static final int Supports = 43;
    static final int Truncatable = 44;
    static final int ValueBase = 45;
    static final int Valuetype = 46;
    static final int Factory = 47;
    static final int Component = 48;
    static final int Consumes = 49;
    static final int Emits = 50;
    static final int Finder = 51;
    static final int GetRaises = 52;
    static final int Home = 53;
    static final int Import = 54;
    static final int Local = 55;
    static final int Manages = 56;
    static final int Multiple = 57;
    static final int PrimaryKey = 58;
    static final int Provides = 59;
    static final int Publishes = 60;
    static final int SetRaises = 61;
    static final int TypeId = 62;
    static final int TypePrefix = 63;
    static final int Uses = 64;
    static final int Identifier = 80;
    static final int MacroIdentifier = 81;
    static final int Semicolon = 100;
    static final int LeftBrace = 101;
    static final int RightBrace = 102;
    static final int Colon = 103;
    static final int Comma = 104;
    static final int Equal = 105;
    static final int Plus = 106;
    static final int Minus = 107;
    static final int LeftParen = 108;
    static final int RightParen = 109;
    static final int LessThan = 110;
    static final int GreaterThan = 111;
    static final int LeftBracket = 112;
    static final int RightBracket = 113;
    static final int Apostrophe = 114;
    static final int Quote = 115;
    static final int Backslash = 116;
    static final int Bar = 117;
    static final int Carat = 118;
    static final int Ampersand = 119;
    static final int Star = 120;
    static final int Slash = 121;
    static final int Percent = 122;
    static final int Tilde = 123;
    static final int DoubleColon = 124;
    static final int ShiftLeft = 125;
    static final int ShiftRight = 126;
    static final int Period = 127;
    static final int Hash = 128;
    static final int Exclamation = 129;
    static final int DoubleEqual = 130;
    static final int NotEqual = 131;
    static final int GreaterEqual = 132;
    static final int LessEqual = 133;
    static final int DoubleBar = 134;
    static final int DoubleAmpersand = 135;
    static final int BooleanLiteral = 200;
    static final int CharacterLiteral = 201;
    static final int IntegerLiteral = 202;
    static final int FloatingPointLiteral = 203;
    static final int StringLiteral = 204;
    static final int Literal = 205;
    static final int Define = 300;
    static final int Undef = 301;
    static final int If = 302;
    static final int Ifdef = 303;
    static final int Ifndef = 304;
    static final int Else = 305;
    static final int Elif = 306;
    static final int Include = 307;
    static final int Endif = 308;
    static final int Line = 309;
    static final int Error = 310;
    static final int Pragma = 311;
    static final int Null = 312;
    static final int Unknown = 313;
    static final int Defined = 400;
    static final int EOF = 999;
    private static final int FirstKeyword = 0;
    private static final int LastKeyword = 64;
    private static final int First22Keyword = 0;
    private static final int Last22Keyword = 37;
    private static final int First23Keyword = 38;
    private static final int Last23Keyword = 46;
    private static final int First24rtfKeyword = 39;
    private static final int Last24rtfKeyword = 47;
    private static final int First30Keyword = 48;
    private static final int Last30Keyword = 64;
    private static final int CORBA_LEVEL_22 = 0;
    private static final int CORBA_LEVEL_23 = 1;
    private static final int CORBA_LEVEL_24RTF = 2;
    private static final int CORBA_LEVEL_30 = 3;
    static final int FirstSymbol = 100;
    static final int LastSymbol = 199;
    static final int FirstLiteral = 200;
    static final int LastLiteral = 299;
    static final int FirstDirective = 300;
    static final int LastDirective = 399;
    static final int FirstSpecial = 400;
    static final int LastSpecial = 499;
    int type;
    String name;
    Comment comment;
    boolean isEscaped;
    boolean collidesWithKeyword;
    boolean isDeprecated;
    boolean isWide;
    static final String[] Keywords = {Constants.IDL_ANY, "attribute", Constants.IDL_BOOLEAN, "case", "char", "const", "context", "default", Constants.IDL_DOUBLE, "enum", "exception", "FALSE", com.sun.tools.internal.ws.wsdl.parser.Constants.ATTR_FIXED, Constants.IDL_FLOAT, "in", "inout", "interface", Constants.IDL_INT, "module", "native", Constants.IDL_CORBA_OBJECT, Constants.IDL_BYTE, "oneway", "out", "raises", "readonly", "sequence", Constants.IDL_SHORT, "string", "struct", "switch", "TRUE", "typedef", "unsigned", com.sun.tools.internal.ws.wsdl.parser.Constants.ATTRVALUE_UNION, Constants.IDL_VOID, Constants.IDL_CHAR, Constants.IDL_CONSTANT_STRING, "init", com.sun.tools.internal.ws.wsdl.parser.Constants.ATTR_ABSTRACT, "custom", "private", com.sun.tools.internal.ws.wsdl.parser.Constants.ATTR_PUBLIC, "supports", "truncatable", "ValueBase", "valuetype", "factory", "component", "consumes", "emits", "finder", "getRaises", "home", "import", "local", "manages", "multiple", "primaryKey", "provides", "publishes", "setRaises", "supports", "typeId", "typePrefix", "uses"};
    static final String[] Symbols = {RuntimeConstants.SIG_ENDCLASS, "{", "}", CallSiteDescriptor.TOKEN_DELIMITER, DocLint.TAGS_SEPARATOR, "=", Marker.ANY_NON_NULL_MARKER, TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR, RuntimeConstants.SIG_METHOD, RuntimeConstants.SIG_ENDMETHOD, "<", ">", RuntimeConstants.SIG_ARRAY, "]", OperatorName.SHOW_TEXT_LINE, OperatorName.SHOW_TEXT_LINE_AND_SPACE, "\\", CallSiteDescriptor.OPERATOR_DELIMITER, "^", "&", Marker.ANY_MARKER, RuntimeConstants.SIG_PACKAGE, "%", "~", Constants.IDL_NAME_SEPARATOR, "<<", ">>", Constants.NAME_SEPARATOR, "#", "!", "==", "!=", ">=", "<=", "||", "&&"};
    static final String[] Literals = {Util.getMessage("Token.boolLit"), Util.getMessage("Token.charLit"), Util.getMessage("Token.intLit"), Util.getMessage("Token.floatLit"), Util.getMessage("Token.stringLit"), Util.getMessage("Token.literal")};
    static final String[] Directives = {"define", "undef", "if", "ifdef", "ifndef", "else", "elif", "include", "endif", "line", FlatClientProperties.OUTLINE_ERROR, "pragma", ""};
    static final String[] Special = {"defined"};

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isKeyword() {
        return this.type >= 0 && this.type <= 64;
    }

    private static int getLevel(float f) {
        if (f < 2.3f) {
            return 0;
        }
        if (Util.absDelta(f, 2.3f) < 0.001f) {
            return 1;
        }
        if (f < 3.0f) {
            return 2;
        }
        return 3;
    }

    private static int getLastKeyword(int i) {
        if (i == 0) {
            return 37;
        }
        if (i == 1) {
            return 46;
        }
        if (i == 2) {
            return 47;
        }
        return 64;
    }

    public static Token makeKeywordToken(String str, float f, boolean z, boolean[] zArr) {
        int level = getLevel(f);
        int lastKeyword = getLastKeyword(level);
        boolean z2 = false;
        zArr[0] = false;
        for (int i = 0; i <= 64; i++) {
            if (str.equals(Keywords[i])) {
                if (i == 38) {
                    if (level == 1) {
                        z2 = true;
                    } else {
                        return null;
                    }
                }
                if (i > lastKeyword) {
                    zArr[0] = zArr[0] | z;
                    return null;
                }
                if (str.equals("TRUE") || str.equals("FALSE")) {
                    return new Token(200, str);
                }
                return new Token(i, z2);
            }
            if (str.equalsIgnoreCase(Keywords[i])) {
                zArr[0] = zArr[0] | true;
                return null;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isDirective() {
        return this.type >= 300 && this.type <= LastDirective;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Token(int i) {
        this.name = null;
        this.comment = null;
        this.isEscaped = false;
        this.collidesWithKeyword = false;
        this.isDeprecated = false;
        this.isWide = false;
        this.type = i;
    }

    Token(int i, boolean z) {
        this.name = null;
        this.comment = null;
        this.isEscaped = false;
        this.collidesWithKeyword = false;
        this.isDeprecated = false;
        this.isWide = false;
        this.type = i;
        this.isDeprecated = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Token(int i, String str) {
        this.name = null;
        this.comment = null;
        this.isEscaped = false;
        this.collidesWithKeyword = false;
        this.isDeprecated = false;
        this.isWide = false;
        this.type = i;
        this.name = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Token(int i, String str, boolean z) {
        this(i, str);
        this.isWide = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Token(int i, String str, boolean z, boolean z2, boolean z3) {
        this(i, str);
        this.isEscaped = z;
        this.collidesWithKeyword = z2;
        this.isDeprecated = z3;
    }

    public String toString() {
        if (this.type == 80) {
            return this.name;
        }
        if (this.type == 81) {
            return this.name + '(';
        }
        return toString(this.type);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String toString(int i) {
        if (i <= 64) {
            return Keywords[i];
        }
        if (i == 80 || i == 81) {
            return Util.getMessage("Token.identifier");
        }
        if (i <= 199) {
            return Symbols[i - 100];
        }
        if (i <= LastLiteral) {
            return Literals[i - 200];
        }
        if (i <= LastDirective) {
            return Directives[i - OS2WindowsMetricsTable.WEIGHT_CLASS_LIGHT];
        }
        if (i <= LastSpecial) {
            return Special[i - OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL];
        }
        if (i == EOF) {
            return Util.getMessage("Token.endOfFile");
        }
        return Util.getMessage("Token.unknown");
    }

    boolean equals(Token token) {
        if (this.type == token.type) {
            if (this.name == null) {
                return token.name == null;
            }
            return this.name.equals(token.name);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean equals(int i) {
        return this.type == i;
    }

    boolean equals(String str) {
        return this.type == 80 && this.name.equals(str);
    }

    public boolean isEscaped() {
        return this.type == 80 && this.isEscaped;
    }

    public boolean collidesWithKeyword() {
        return this.collidesWithKeyword;
    }

    public boolean isDeprecated() {
        return this.isDeprecated;
    }

    public boolean isWide() {
        return this.isWide;
    }
}
