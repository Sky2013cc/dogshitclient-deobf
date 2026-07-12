package com.sun.tools.javac.parser;

import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import com.sun.tools.doclint.DocLint;
import com.sun.tools.internal.ws.wsdl.parser.Constants;
import com.sun.tools.javac.api.Formattable;
import com.sun.tools.javac.api.Messages;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Filter;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import java.util.Iterator;
import java.util.Locale;
import jdk.internal.dynalink.CallSiteDescriptor;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.slf4j.Marker;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/javac/parser/Tokens.class */
public class Tokens {
    private final Names names;
    private final TokenKind[] key;
    private int maxKey = 0;
    private Name[] tokenName = new Name[TokenKind.values().length];
    public static final Context.Key<Tokens> tokensKey = new Context.Key<>();
    public static final Token DUMMY = new Token(TokenKind.ERROR, 0, 0, null);

    /* loaded from: target.jar:com/sun/tools/javac/parser/Tokens$Comment.class */
    public interface Comment {

        /* loaded from: target.jar:com/sun/tools/javac/parser/Tokens$Comment$CommentStyle.class */
        public enum CommentStyle {
            LINE,
            BLOCK,
            JAVADOC
        }

        String getText();

        int getSourcePos(int i);

        CommentStyle getStyle();

        boolean isDeprecated();
    }

    public static Tokens instance(Context context) {
        Tokens tokens = (Tokens) context.get(tokensKey);
        if (tokens == null) {
            tokens = new Tokens(context);
        }
        return tokens;
    }

    protected Tokens(Context context) {
        context.put((Context.Key<Context.Key<Tokens>>) tokensKey, (Context.Key<Tokens>) this);
        this.names = Names.instance(context);
        for (TokenKind tokenKind : TokenKind.values()) {
            if (tokenKind.name != null) {
                enterKeyword(tokenKind.name, tokenKind);
            } else {
                this.tokenName[tokenKind.ordinal()] = null;
            }
        }
        this.key = new TokenKind[this.maxKey + 1];
        for (int i = 0; i <= this.maxKey; i++) {
            this.key[i] = TokenKind.IDENTIFIER;
        }
        for (TokenKind tokenKind2 : TokenKind.values()) {
            if (tokenKind2.name != null) {
                this.key[this.tokenName[tokenKind2.ordinal()].getIndex()] = tokenKind2;
            }
        }
    }

    private void enterKeyword(String str, TokenKind tokenKind) {
        Name fromString = this.names.fromString(str);
        this.tokenName[tokenKind.ordinal()] = fromString;
        if (fromString.getIndex() > this.maxKey) {
            this.maxKey = fromString.getIndex();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TokenKind lookupKind(Name name) {
        return name.getIndex() > this.maxKey ? TokenKind.IDENTIFIER : this.key[name.getIndex()];
    }

    TokenKind lookupKind(String str) {
        return lookupKind(this.names.fromString(str));
    }

    /* loaded from: target.jar:com/sun/tools/javac/parser/Tokens$TokenKind.class */
    public enum TokenKind implements Formattable, Filter<TokenKind> {
        EOF,
        ERROR,
        IDENTIFIER(Token.Tag.NAMED),
        ABSTRACT(Constants.ATTR_ABSTRACT),
        ASSERT("assert", Token.Tag.NAMED),
        BOOLEAN(sun.rmi.rmic.iiop.Constants.IDL_BOOLEAN, Token.Tag.NAMED),
        BREAK("break"),
        BYTE("byte", Token.Tag.NAMED),
        CASE("case"),
        CATCH("catch"),
        CHAR("char", Token.Tag.NAMED),
        CLASS("class"),
        CONST("const"),
        CONTINUE("continue"),
        DEFAULT("default"),
        DO("do"),
        DOUBLE(sun.rmi.rmic.iiop.Constants.IDL_DOUBLE, Token.Tag.NAMED),
        ELSE("else"),
        ENUM("enum", Token.Tag.NAMED),
        EXTENDS("extends"),
        FINAL(Constants.ATTR_FINAL),
        FINALLY("finally"),
        FLOAT(sun.rmi.rmic.iiop.Constants.IDL_FLOAT, Token.Tag.NAMED),
        FOR("for"),
        GOTO("goto"),
        IF("if"),
        IMPLEMENTS("implements"),
        IMPORT("import"),
        INSTANCEOF("instanceof"),
        INT("int", Token.Tag.NAMED),
        INTERFACE("interface"),
        LONG(sun.rmi.rmic.iiop.Constants.IDL_INT, Token.Tag.NAMED),
        NATIVE("native"),
        NEW("new"),
        PACKAGE("package"),
        PRIVATE("private"),
        PROTECTED("protected"),
        PUBLIC(Constants.ATTR_PUBLIC),
        RETURN("return"),
        SHORT(sun.rmi.rmic.iiop.Constants.IDL_SHORT, Token.Tag.NAMED),
        STATIC("static"),
        STRICTFP("strictfp"),
        SUPER("super", Token.Tag.NAMED),
        SWITCH("switch"),
        SYNCHRONIZED("synchronized"),
        THIS("this", Token.Tag.NAMED),
        THROW("throw"),
        THROWS("throws"),
        TRANSIENT("transient"),
        TRY("try"),
        VOID(sun.rmi.rmic.iiop.Constants.IDL_VOID, Token.Tag.NAMED),
        VOLATILE("volatile"),
        WHILE("while"),
        INTLITERAL(Token.Tag.NUMERIC),
        LONGLITERAL(Token.Tag.NUMERIC),
        FLOATLITERAL(Token.Tag.NUMERIC),
        DOUBLELITERAL(Token.Tag.NUMERIC),
        CHARLITERAL(Token.Tag.NUMERIC),
        STRINGLITERAL(Token.Tag.STRING),
        TRUE(Constants.TRUE, Token.Tag.NAMED),
        FALSE(Constants.FALSE, Token.Tag.NAMED),
        NULL("null", Token.Tag.NAMED),
        UNDERSCORE("_", Token.Tag.NAMED),
        ARROW("->"),
        COLCOL(sun.rmi.rmic.iiop.Constants.IDL_NAME_SEPARATOR),
        LPAREN(RuntimeConstants.SIG_METHOD),
        RPAREN(RuntimeConstants.SIG_ENDMETHOD),
        LBRACE("{"),
        RBRACE("}"),
        LBRACKET(RuntimeConstants.SIG_ARRAY),
        RBRACKET("]"),
        SEMI(RuntimeConstants.SIG_ENDCLASS),
        COMMA(DocLint.TAGS_SEPARATOR),
        DOT(sun.rmi.rmic.iiop.Constants.NAME_SEPARATOR),
        ELLIPSIS("..."),
        EQ("="),
        GT(">"),
        LT("<"),
        BANG("!"),
        TILDE("~"),
        QUES("?"),
        COLON(CallSiteDescriptor.TOKEN_DELIMITER),
        EQEQ("=="),
        LTEQ("<="),
        GTEQ(">="),
        BANGEQ("!="),
        AMPAMP("&&"),
        BARBAR("||"),
        PLUSPLUS("++"),
        SUBSUB("--"),
        PLUS(Marker.ANY_NON_NULL_MARKER),
        SUB(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR),
        STAR(Marker.ANY_MARKER),
        SLASH(RuntimeConstants.SIG_PACKAGE),
        AMP("&"),
        BAR(CallSiteDescriptor.OPERATOR_DELIMITER),
        CARET("^"),
        PERCENT("%"),
        LTLT("<<"),
        GTGT(">>"),
        GTGTGT(">>>"),
        PLUSEQ("+="),
        SUBEQ("-="),
        STAREQ("*="),
        SLASHEQ("/="),
        AMPEQ("&="),
        BAREQ("|="),
        CARETEQ("^="),
        PERCENTEQ("%="),
        LTLTEQ("<<="),
        GTGTEQ(">>="),
        GTGTGTEQ(">>>="),
        MONKEYS_AT("@"),
        CUSTOM;

        public final String name;
        public final Token.Tag tag;

        TokenKind() {
            this(null, Token.Tag.DEFAULT);
        }

        TokenKind(String str) {
            this(str, Token.Tag.DEFAULT);
        }

        TokenKind(Token.Tag tag) {
            this(null, tag);
        }

        TokenKind(String str, Token.Tag tag) {
            this.name = str;
            this.tag = tag;
        }

        @Override // java.lang.Enum
        public String toString() {
            switch (this) {
                case IDENTIFIER:
                    return "token.identifier";
                case CHARLITERAL:
                    return "token.character";
                case STRINGLITERAL:
                    return "token.string";
                case INTLITERAL:
                    return "token.integer";
                case LONGLITERAL:
                    return "token.long-integer";
                case FLOATLITERAL:
                    return "token.float";
                case DOUBLELITERAL:
                    return "token.double";
                case ERROR:
                    return "token.bad-symbol";
                case EOF:
                    return "token.end-of-input";
                case DOT:
                case COMMA:
                case SEMI:
                case LPAREN:
                case RPAREN:
                case LBRACKET:
                case RBRACKET:
                case LBRACE:
                case RBRACE:
                    return OperatorName.SHOW_TEXT_LINE + this.name + OperatorName.SHOW_TEXT_LINE;
                default:
                    return this.name;
            }
        }

        @Override // com.sun.tools.javac.api.Formattable
        public String getKind() {
            return "Token";
        }

        @Override // com.sun.tools.javac.api.Formattable
        public String toString(Locale locale, Messages messages) {
            return this.name != null ? toString() : messages.getLocalizedString(locale, "compiler.misc." + toString(), new Object[0]);
        }

        @Override // com.sun.tools.javac.util.Filter
        public boolean accepts(TokenKind tokenKind) {
            return this == tokenKind;
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/parser/Tokens$Token.class */
    public static class Token {
        public final TokenKind kind;
        public final int pos;
        public final int endPos;
        public final List<Comment> comments;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: target.jar:com/sun/tools/javac/parser/Tokens$Token$Tag.class */
        public enum Tag {
            DEFAULT,
            NAMED,
            STRING,
            NUMERIC
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Token(TokenKind tokenKind, int i, int i2, List<Comment> list) {
            this.kind = tokenKind;
            this.pos = i;
            this.endPos = i2;
            this.comments = list;
            checkKind();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Token[] split(Tokens tokens) {
            if (this.kind.name.length() < 2 || this.kind.tag != Tag.DEFAULT) {
                throw new AssertionError("Cant split" + this.kind);
            }
            TokenKind lookupKind = tokens.lookupKind(this.kind.name.substring(0, 1));
            TokenKind lookupKind2 = tokens.lookupKind(this.kind.name.substring(1));
            if (lookupKind == null || lookupKind2 == null) {
                throw new AssertionError("Cant split - bad subtokens");
            }
            return new Token[]{new Token(lookupKind, this.pos, this.pos + lookupKind.name.length(), this.comments), new Token(lookupKind2, this.pos + lookupKind.name.length(), this.endPos, null)};
        }

        protected void checkKind() {
            if (this.kind.tag != Tag.DEFAULT) {
                throw new AssertionError("Bad token kind - expected " + Tag.STRING);
            }
        }

        public Name name() {
            throw new UnsupportedOperationException();
        }

        public String stringVal() {
            throw new UnsupportedOperationException();
        }

        public int radix() {
            throw new UnsupportedOperationException();
        }

        public Comment comment(Comment.CommentStyle commentStyle) {
            List<Comment> comments = getComments(Comment.CommentStyle.JAVADOC);
            if (comments.isEmpty()) {
                return null;
            }
            return comments.head;
        }

        public boolean deprecatedFlag() {
            Iterator<Comment> it = getComments(Comment.CommentStyle.JAVADOC).iterator();
            while (it.hasNext()) {
                if (it.next().isDeprecated()) {
                    return true;
                }
            }
            return false;
        }

        private List<Comment> getComments(Comment.CommentStyle commentStyle) {
            if (this.comments == null) {
                return List.nil();
            }
            ListBuffer listBuffer = new ListBuffer();
            Iterator<Comment> it = this.comments.iterator();
            while (it.hasNext()) {
                Comment next = it.next();
                if (next.getStyle() == commentStyle) {
                    listBuffer.add(next);
                }
            }
            return listBuffer.toList();
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/parser/Tokens$NamedToken.class */
    static final class NamedToken extends Token {
        public final Name name;

        public NamedToken(TokenKind tokenKind, int i, int i2, Name name, List<Comment> list) {
            super(tokenKind, i, i2, list);
            this.name = name;
        }

        @Override // com.sun.tools.javac.parser.Tokens.Token
        protected void checkKind() {
            if (this.kind.tag != Token.Tag.NAMED) {
                throw new AssertionError("Bad token kind - expected " + Token.Tag.NAMED);
            }
        }

        @Override // com.sun.tools.javac.parser.Tokens.Token
        public Name name() {
            return this.name;
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/parser/Tokens$StringToken.class */
    static class StringToken extends Token {
        public final String stringVal;

        public StringToken(TokenKind tokenKind, int i, int i2, String str, List<Comment> list) {
            super(tokenKind, i, i2, list);
            this.stringVal = str;
        }

        @Override // com.sun.tools.javac.parser.Tokens.Token
        protected void checkKind() {
            if (this.kind.tag != Token.Tag.STRING) {
                throw new AssertionError("Bad token kind - expected " + Token.Tag.STRING);
            }
        }

        @Override // com.sun.tools.javac.parser.Tokens.Token
        public String stringVal() {
            return this.stringVal;
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/parser/Tokens$NumericToken.class */
    static final class NumericToken extends StringToken {
        public final int radix;

        public NumericToken(TokenKind tokenKind, int i, int i2, String str, int i3, List<Comment> list) {
            super(tokenKind, i, i2, str, list);
            this.radix = i3;
        }

        @Override // com.sun.tools.javac.parser.Tokens.StringToken, com.sun.tools.javac.parser.Tokens.Token
        protected void checkKind() {
            if (this.kind.tag != Token.Tag.NUMERIC) {
                throw new AssertionError("Bad token kind - expected " + Token.Tag.NUMERIC);
            }
        }

        @Override // com.sun.tools.javac.parser.Tokens.Token
        public int radix() {
            return this.radix;
        }
    }
}
