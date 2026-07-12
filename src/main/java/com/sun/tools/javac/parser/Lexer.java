package com.sun.tools.javac.parser;

import com.sun.tools.javac.parser.Tokens;
import com.sun.tools.javac.util.Position;

/* loaded from: target.jar:com/sun/tools/javac/parser/Lexer.class */
public interface Lexer {
    void nextToken();

    Tokens.Token token();

    Tokens.Token token(int i);

    Tokens.Token prevToken();

    Tokens.Token split();

    int errPos();

    void errPos(int i);

    Position.LineMap getLineMap();
}
