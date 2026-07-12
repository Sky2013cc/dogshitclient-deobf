package com.sun.xml.internal.rngom.parse.compact;

import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/xml/internal/rngom/parse/compact/ParseException.class */
public class ParseException extends Exception {
    private static final long serialVersionUID = 1;
    public Token currentToken;
    public int[][] expectedTokenSequences;
    public String[] tokenImage;
    protected String eol;

    public ParseException(Token currentTokenVal, int[][] expectedTokenSequencesVal, String[] tokenImageVal) {
        super(initialise(currentTokenVal, expectedTokenSequencesVal, tokenImageVal));
        this.eol = System.getProperty("line.separator", "\n");
        this.currentToken = currentTokenVal;
        this.expectedTokenSequences = expectedTokenSequencesVal;
        this.tokenImage = tokenImageVal;
    }

    public ParseException() {
        this.eol = System.getProperty("line.separator", "\n");
    }

    public ParseException(String message) {
        super(message);
        this.eol = System.getProperty("line.separator", "\n");
    }

    private static String initialise(Token currentToken, int[][] expectedTokenSequences, String[] tokenImage) {
        String retval;
        String eol = System.getProperty("line.separator", "\n");
        StringBuffer expected = new StringBuffer();
        int maxSize = 0;
        for (int i = 0; i < expectedTokenSequences.length; i++) {
            if (maxSize < expectedTokenSequences[i].length) {
                maxSize = expectedTokenSequences[i].length;
            }
            for (int j = 0; j < expectedTokenSequences[i].length; j++) {
                expected.append(tokenImage[expectedTokenSequences[i][j]]).append(' ');
            }
            if (expectedTokenSequences[i][expectedTokenSequences[i].length - 1] != 0) {
                expected.append("...");
            }
            expected.append(eol).append("    ");
        }
        String retval2 = "Encountered \"";
        Token tok = currentToken.next;
        int i2 = 0;
        while (true) {
            if (i2 >= maxSize) {
                break;
            }
            if (i2 != 0) {
                retval2 = retval2 + " ";
            }
            if (tok.kind == 0) {
                retval2 = retval2 + tokenImage[0];
                break;
            }
            retval2 = (((retval2 + " " + tokenImage[tok.kind]) + " \"") + add_escapes(tok.image)) + " \"";
            tok = tok.next;
            i2++;
        }
        String retval3 = (retval2 + "\" at line " + currentToken.next.beginLine + ", column " + currentToken.next.beginColumn) + Constants.NAME_SEPARATOR + eol;
        if (expectedTokenSequences.length == 1) {
            retval = retval3 + "Was expecting:" + eol + "    ";
        } else {
            retval = retval3 + "Was expecting one of:" + eol + "    ";
        }
        return retval + expected.toString();
    }

    static String add_escapes(String str) {
        StringBuffer retval = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case 0:
                    break;
                case '\b':
                    retval.append("\\b");
                    break;
                case '\t':
                    retval.append("\\t");
                    break;
                case '\n':
                    retval.append("\\n");
                    break;
                case '\f':
                    retval.append("\\f");
                    break;
                case '\r':
                    retval.append("\\r");
                    break;
                case '\"':
                    retval.append("\\\"");
                    break;
                case '\'':
                    retval.append("\\'");
                    break;
                case '\\':
                    retval.append("\\\\");
                    break;
                default:
                    char ch = str.charAt(i);
                    if (ch < ' ' || ch > '~') {
                        String s = "0000" + Integer.toString(ch, 16);
                        retval.append("\\u" + s.substring(s.length() - 4, s.length()));
                        break;
                    } else {
                        retval.append(ch);
                        break;
                    }
                    break;
            }
        }
        return retval.toString();
    }
}
