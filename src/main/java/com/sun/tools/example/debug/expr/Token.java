package com.sun.tools.example.debug.expr;

/* loaded from: target.jar:com/sun/tools/example/debug/expr/Token.class */
public class Token {
    public int kind;
    public int beginLine;
    public int beginColumn;
    public int endLine;
    public int endColumn;
    public String image;
    public Token next;
    public Token specialToken;

    public final String toString() {
        return this.image;
    }

    public static final Token newToken(int i) {
        switch (i) {
            default:
                return new Token();
        }
    }
}
