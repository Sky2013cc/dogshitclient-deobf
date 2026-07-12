package org.apache.fontbox.type1;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.fontbox.type1.Token;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: target.jar:org/apache/fontbox/type1/Type1Lexer.class */
class Type1Lexer {
    private static final Log LOG = LogFactory.getLog(Type1Lexer.class);
    private final ByteBuffer buffer;
    private int openParens = 0;
    private Token aheadToken = readToken(null);

    /* JADX INFO: Access modifiers changed from: package-private */
    public Type1Lexer(byte[] bytes) throws IOException {
        this.buffer = ByteBuffer.wrap(bytes);
    }

    public Token nextToken() throws IOException {
        Token curToken = this.aheadToken;
        this.aheadToken = readToken(curToken);
        return curToken;
    }

    public Token peekToken() {
        return this.aheadToken;
    }

    public boolean peekKind(Token.Kind kind) {
        return this.aheadToken != null && this.aheadToken.getKind() == kind;
    }

    private char getChar() throws IOException {
        try {
            return (char) this.buffer.get();
        } catch (BufferUnderflowException e) {
            throw new IOException("Premature end of buffer reached");
        }
    }

    private Token readToken(Token prevToken) throws IOException {
        boolean skip;
        do {
            skip = false;
            while (this.buffer.hasRemaining()) {
                char c = getChar();
                if (c == '%') {
                    readComment();
                } else {
                    if (c == '(') {
                        return readString();
                    }
                    if (c == ')') {
                        throw new IOException("unexpected closing parenthesis");
                    }
                    if (c == '[') {
                        return new Token(c, Token.START_ARRAY);
                    }
                    if (c == '{') {
                        return new Token(c, Token.START_PROC);
                    }
                    if (c == ']') {
                        return new Token(c, Token.END_ARRAY);
                    }
                    if (c == '}') {
                        return new Token(c, Token.END_PROC);
                    }
                    if (c == '/') {
                        String regular = readRegular();
                        if (regular == null) {
                            throw new DamagedFontException("Could not read token at position " + this.buffer.position());
                        }
                        return new Token(regular, Token.LITERAL);
                    }
                    if (c == '<') {
                        char c2 = getChar();
                        if (c2 == c) {
                            return new Token("<<", Token.START_DICT);
                        }
                        this.buffer.position(this.buffer.position() - 1);
                        return new Token(c, Token.NAME);
                    }
                    if (c == '>') {
                        char c22 = getChar();
                        if (c22 == c) {
                            return new Token(">>", Token.END_DICT);
                        }
                        this.buffer.position(this.buffer.position() - 1);
                        return new Token(c, Token.NAME);
                    }
                    if (Character.isWhitespace(c)) {
                        skip = true;
                    } else if (c == 0) {
                        LOG.warn("NULL byte in font, skipped");
                        skip = true;
                    } else {
                        this.buffer.position(this.buffer.position() - 1);
                        Token number = tryReadNumber();
                        if (number != null) {
                            return number;
                        }
                        String name = readRegular();
                        if (name == null) {
                            throw new DamagedFontException("Could not read token at position " + this.buffer.position());
                        }
                        if (name.equals("RD") || name.equals("-|")) {
                            if (prevToken != null && prevToken.getKind() == Token.INTEGER) {
                                return readCharString(prevToken.intValue());
                            }
                            throw new IOException("expected INTEGER before -| or RD");
                        }
                        return new Token(name, Token.NAME);
                    }
                }
            }
        } while (skip);
        return null;
    }

    private Token tryReadNumber() throws IOException {
        this.buffer.mark();
        StringBuilder sb = new StringBuilder();
        StringBuilder radix = null;
        char c = getChar();
        boolean hasDigit = false;
        if (c == '+' || c == '-') {
            sb.append(c);
            c = getChar();
        }
        while (Character.isDigit(c)) {
            sb.append(c);
            c = getChar();
            hasDigit = true;
        }
        if (c == '.') {
            sb.append(c);
            c = getChar();
        } else if (c == '#') {
            radix = sb;
            sb = new StringBuilder();
            c = getChar();
        } else {
            if (sb.length() == 0 || !hasDigit) {
                this.buffer.reset();
                return null;
            }
            if (c != 'e' && c != 'E') {
                this.buffer.position(this.buffer.position() - 1);
                return new Token(sb.toString(), Token.INTEGER);
            }
        }
        if (Character.isDigit(c)) {
            sb.append(c);
            c = getChar();
        } else if (c != 'e' && c != 'E') {
            this.buffer.reset();
            return null;
        }
        while (Character.isDigit(c)) {
            sb.append(c);
            c = getChar();
        }
        if (c == 'E' || c == 'e') {
            sb.append(c);
            char c2 = getChar();
            if (c2 == '-') {
                sb.append(c2);
                c2 = getChar();
            }
            if (Character.isDigit(c2)) {
                sb.append(c2);
                char c3 = getChar();
                while (true) {
                    char c4 = c3;
                    if (!Character.isDigit(c4)) {
                        break;
                    }
                    sb.append(c4);
                    c3 = getChar();
                }
            } else {
                this.buffer.reset();
                return null;
            }
        }
        this.buffer.position(this.buffer.position() - 1);
        if (radix != null) {
            try {
                int val = Integer.parseInt(sb.toString(), Integer.parseInt(radix.toString()));
                return new Token(Integer.toString(val), Token.INTEGER);
            } catch (NumberFormatException ex) {
                throw new IOException("Invalid number '" + ((Object) sb) + OperatorName.SHOW_TEXT_LINE, ex);
            }
        }
        return new Token(sb.toString(), Token.REAL);
    }

    private String readRegular() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (this.buffer.hasRemaining()) {
            this.buffer.mark();
            char c = getChar();
            if (Character.isWhitespace(c) || c == '(' || c == ')' || c == '<' || c == '>' || c == '[' || c == ']' || c == '{' || c == '}' || c == '/' || c == '%') {
                this.buffer.reset();
                break;
            }
            sb.append(c);
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    private String readComment() throws IOException {
        char c;
        StringBuilder sb = new StringBuilder();
        while (this.buffer.hasRemaining() && (c = getChar()) != '\r' && c != '\n') {
            sb.append(c);
        }
        return sb.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0126, code lost:
    
        if (java.lang.Character.isDigit(r0) == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0129, code lost:
    
        r0 = java.lang.String.valueOf(new char[]{r0, getChar(), getChar()});
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0143, code lost:
    
        r0 = java.lang.Integer.parseInt(r0, 8);
        r0.append((char) r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0157, code lost:
    
        r10 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0162, code lost:
    
        throw new java.io.IOException(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0008, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Token readString() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (this.buffer.hasRemaining()) {
            char c = getChar();
            switch (c) {
                case '\n':
                case '\r':
                    sb.append("\n");
                    break;
                case '(':
                    this.openParens++;
                    sb.append('(');
                    break;
                case ')':
                    if (this.openParens == 0) {
                        return new Token(sb.toString(), Token.STRING);
                    }
                    sb.append(')');
                    this.openParens--;
                    break;
                case '\\':
                    char c1 = getChar();
                    switch (c1) {
                        case '(':
                            sb.append('(');
                            break;
                        case ')':
                            sb.append(')');
                            break;
                        case '\\':
                            sb.append('\\');
                            break;
                        case 'b':
                            sb.append('\b');
                            break;
                        case 'f':
                            sb.append('\f');
                            break;
                        case 'n':
                        case 'r':
                            sb.append("\n");
                            break;
                        case 't':
                            sb.append('\t');
                            break;
                    }
                default:
                    sb.append(c);
                    break;
            }
        }
        return null;
    }

    private Token readCharString(int length) throws IOException {
        try {
            this.buffer.get();
            byte[] data = new byte[length];
            this.buffer.get(data);
            return new Token(data, Token.CHARSTRING);
        } catch (BufferUnderflowException e) {
            throw new IOException("Premature end of buffer reached");
        }
    }
}
