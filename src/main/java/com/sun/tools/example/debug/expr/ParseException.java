package com.sun.tools.example.debug.expr;

import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/tools/example/debug/expr/ParseException.class */
public class ParseException extends Exception {
    private static final long serialVersionUID = 7978489144303647901L;
    protected boolean specialConstructor;
    public Token currentToken;
    public int[][] expectedTokenSequences;
    public String[] tokenImage;
    protected String eol;

    public ParseException(Token token, int[][] iArr, String[] strArr) {
        super("");
        this.eol = System.getProperty("line.separator", "\n");
        this.specialConstructor = true;
        this.currentToken = token;
        this.expectedTokenSequences = iArr;
        this.tokenImage = strArr;
    }

    public ParseException() {
        this.eol = System.getProperty("line.separator", "\n");
        this.specialConstructor = false;
    }

    public ParseException(String str) {
        super(str);
        this.eol = System.getProperty("line.separator", "\n");
        this.specialConstructor = false;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String str;
        if (!this.specialConstructor) {
            return super.getMessage();
        }
        String str2 = "";
        int i = 0;
        for (int[] iArr : this.expectedTokenSequences) {
            if (i < iArr.length) {
                i = iArr.length;
            }
            for (int i2 : iArr) {
                str2 = str2 + this.tokenImage[i2] + " ";
            }
            if (iArr[iArr.length - 1] != 0) {
                str2 = str2 + "...";
            }
            str2 = str2 + this.eol + "    ";
        }
        String str3 = "Encountered \"";
        Token token = this.currentToken.next;
        int i3 = 0;
        while (true) {
            if (i3 >= i) {
                break;
            }
            if (i3 != 0) {
                str3 = str3 + " ";
            }
            if (token.kind == 0) {
                str3 = str3 + this.tokenImage[0];
                break;
            }
            str3 = str3 + add_escapes(token.image);
            token = token.next;
            i3++;
        }
        String str4 = str3 + "\" at line " + this.currentToken.next.beginLine + ", column " + this.currentToken.next.beginColumn + Constants.NAME_SEPARATOR + this.eol;
        if (this.expectedTokenSequences.length == 1) {
            str = str4 + "Was expecting:" + this.eol + "    ";
        } else {
            str = str4 + "Was expecting one of:" + this.eol + "    ";
        }
        return str + str2;
    }

    protected String add_escapes(String str) {
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
}
