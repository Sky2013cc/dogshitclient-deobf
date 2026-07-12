package com.sun.tools.example.debug.expr;

import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: target.jar:com/sun/tools/example/debug/expr/TokenMgrError.class */
public class TokenMgrError extends Error {
    private static final long serialVersionUID = -6236440836177601522L;
    static final int LEXICAL_ERROR = 0;
    static final int STATIC_LEXER_ERROR = 1;
    static final int INVALID_LEXICAL_STATE = 2;
    static final int LOOP_DETECTED = 3;
    int errorCode;

    protected static final String addEscapes(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case 0:
                    break;
                case '\b':
                    stringBuffer.append("\\b");
                    break;
                case '\t':
                    stringBuffer.append("\\t");
                    break;
                case '\n':
                    stringBuffer.append("\\n");
                    break;
                case '\f':
                    stringBuffer.append("\\f");
                    break;
                case '\r':
                    stringBuffer.append("\\r");
                    break;
                case '\"':
                    stringBuffer.append("\\\"");
                    break;
                case '\'':
                    stringBuffer.append("\\'");
                    break;
                case '\\':
                    stringBuffer.append("\\\\");
                    break;
                default:
                    char charAt = str.charAt(i);
                    if (charAt < ' ' || charAt > '~') {
                        String str2 = "0000" + Integer.toString(charAt, 16);
                        stringBuffer.append("\\u" + str2.substring(str2.length() - 4, str2.length()));
                        break;
                    } else {
                        stringBuffer.append(charAt);
                        break;
                    }
                    break;
            }
        }
        return stringBuffer.toString();
    }

    private static final String LexicalError(boolean z, int i, int i2, int i3, String str, char c) {
        return "Lexical error at line " + i2 + ", column " + i3 + ".  Encountered: " + (z ? "<EOF> " : OperatorName.SHOW_TEXT_LINE_AND_SPACE + addEscapes(String.valueOf(c)) + OperatorName.SHOW_TEXT_LINE_AND_SPACE + " (" + ((int) c) + "), ") + "after : \"" + addEscapes(str) + OperatorName.SHOW_TEXT_LINE_AND_SPACE;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }

    public TokenMgrError() {
    }

    public TokenMgrError(String str, int i) {
        super(str);
        this.errorCode = i;
    }

    public TokenMgrError(boolean z, int i, int i2, int i3, String str, char c, int i4) {
        this(LexicalError(z, i, i2, i3, str, c), i4);
    }
}
