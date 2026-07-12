package com.sun.tools.javadoc;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Deprecated
/* loaded from: target.jar:com/sun/tools/javadoc/JavaScriptScanner.class */
public class JavaScriptScanner {
    private Reporter reporter;
    protected char[] buf;
    protected int bp;
    protected int buflen;
    protected char ch;
    private boolean newline = true;
    Map<String, TagParser> tagParsers;
    Set<String> uriAttrs;

    /* loaded from: target.jar:com/sun/tools/javadoc/JavaScriptScanner$Reporter.class */
    public interface Reporter {
        void report();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/sun/tools/javadoc/JavaScriptScanner$WhitespaceRetentionPolicy.class */
    public enum WhitespaceRetentionPolicy {
        RETAIN_ALL,
        REMOVE_FIRST_SPACE,
        REMOVE_ALL
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/javadoc/JavaScriptScanner$ParseException.class */
    public static class ParseException extends Exception {
        private static final long serialVersionUID = 0;

        ParseException(String str) {
            super(str);
        }
    }

    public JavaScriptScanner() {
        initTagParsers();
        initURIAttrs();
    }

    public void parse(String str, Reporter reporter) {
        this.reporter = reporter;
        this.buf = new char[str.length() + 1];
        str.getChars(0, str.length(), this.buf, 0);
        this.buf[this.buf.length - 1] = 26;
        this.buflen = this.buf.length - 1;
        this.bp = -1;
        this.newline = true;
        nextChar();
        blockContent();
        blockTags();
    }

    private void checkHtmlTag(String str) {
        if (str.equalsIgnoreCase("script")) {
            this.reporter.report();
        }
    }

    private void checkHtmlAttr(String str, String str2) {
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        if (lowerCase.startsWith("on") || (this.uriAttrs.contains(lowerCase) && str2 != null && str2.toLowerCase(Locale.ENGLISH).trim().startsWith("javascript:"))) {
            this.reporter.report();
        }
    }

    void nextChar() {
        int i;
        char[] cArr = this.buf;
        if (this.bp < this.buflen) {
            int i2 = this.bp + 1;
            i = i2;
            this.bp = i2;
        } else {
            i = this.buflen;
        }
        this.ch = cArr[i];
        switch (this.ch) {
            case '\n':
            case '\f':
            case '\r':
                this.newline = true;
                return;
            case 11:
            default:
                return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x000f. Please report as an issue. */
    protected void blockContent() {
        while (this.bp < this.buflen) {
            switch (this.ch) {
                case '\t':
                case ' ':
                    nextChar();
                case '\n':
                case '\f':
                case '\r':
                    this.newline = true;
                    nextChar();
                case '&':
                    entity(null);
                case '<':
                    html();
                case '>':
                    this.newline = false;
                    nextChar();
                case '@':
                    if (this.newline) {
                        return;
                    }
                    this.newline = false;
                    nextChar();
                case '{':
                    inlineTag(null);
                default:
                    this.newline = false;
                    nextChar();
            }
        }
    }

    protected void blockTags() {
        while (this.ch == '@') {
            blockTag();
        }
    }

    protected void blockTag() {
        int i = this.bp;
        try {
            nextChar();
            if (isIdentifierStart(this.ch)) {
                TagParser tagParser = this.tagParsers.get(readTagName());
                if (tagParser == null) {
                    blockContent();
                } else {
                    switch (tagParser.getKind()) {
                        case BLOCK:
                            tagParser.parse(i);
                            return;
                        case INLINE:
                            return;
                    }
                }
            }
            blockContent();
        } catch (ParseException e) {
            blockContent();
        }
    }

    protected void inlineTag(Void r4) {
        this.newline = false;
        nextChar();
        if (this.ch == '@') {
            inlineTag();
        }
    }

    protected void inlineTag() {
        int i = this.bp - 1;
        try {
            nextChar();
            if (isIdentifierStart(this.ch)) {
                TagParser tagParser = this.tagParsers.get(readTagName());
                if (tagParser == null) {
                    skipWhitespace();
                    inlineText(WhitespaceRetentionPolicy.REMOVE_ALL);
                    nextChar();
                } else {
                    skipWhitespace();
                    if (tagParser.getKind() == TagParser.Kind.INLINE) {
                        tagParser.parse(i);
                    } else {
                        inlineText(WhitespaceRetentionPolicy.REMOVE_ALL);
                        nextChar();
                    }
                }
            }
        } catch (ParseException e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:11:0x0051. Please report as an issue. */
    public void inlineText(WhitespaceRetentionPolicy whitespaceRetentionPolicy) throws ParseException {
        switch (whitespaceRetentionPolicy) {
            case REMOVE_ALL:
                skipWhitespace();
                break;
            case REMOVE_FIRST_SPACE:
                if (this.ch == ' ') {
                    nextChar();
                    break;
                }
                break;
        }
        int i = this.bp;
        int i2 = 1;
        while (this.bp < this.buflen) {
            switch (this.ch) {
                case '\t':
                case ' ':
                    nextChar();
                case '\n':
                case '\f':
                case '\r':
                    this.newline = true;
                    nextChar();
                case '@':
                    if (!this.newline) {
                        this.newline = false;
                        nextChar();
                    } else {
                        throw new ParseException("dc.unterminated.inline.tag");
                    }
                case '{':
                    this.newline = false;
                    i2++;
                    nextChar();
                case '}':
                    i2--;
                    if (i2 == 0) {
                        return;
                    }
                    this.newline = false;
                    nextChar();
                default:
                    this.newline = false;
                    nextChar();
            }
        }
        throw new ParseException("dc.unterminated.inline.tag");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0016. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00ba A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00c1 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void reference(boolean z) throws ParseException {
        int i = this.bp;
        int i2 = 0;
        while (true) {
            if (this.bp < this.buflen) {
                switch (this.ch) {
                    case '\t':
                    case ' ':
                        if (i2 != 0) {
                            break;
                        } else {
                            nextChar();
                        }
                    case '\n':
                    case '\f':
                    case '\r':
                        this.newline = true;
                        if (i2 != 0) {
                        }
                        break;
                    case '(':
                    case '<':
                        this.newline = false;
                        i2++;
                        nextChar();
                    case ')':
                    case '>':
                        this.newline = false;
                        i2--;
                        nextChar();
                    case '@':
                        if (this.newline) {
                            break;
                        }
                        this.newline = false;
                        nextChar();
                    case '}':
                        if (this.bp == i) {
                            return;
                        }
                        this.newline = false;
                        break;
                    default:
                        this.newline = false;
                        nextChar();
                }
            }
        }
        if (i2 != 0) {
            throw new ParseException("dc.unterminated.signature");
        }
    }

    protected void identifier() throws ParseException {
        skipWhitespace();
        int i = this.bp;
        if (isJavaIdentifierStart(this.ch)) {
            readJavaIdentifier();
            return;
        }
        throw new ParseException("dc.identifier.expected");
    }

    protected void quotedString() {
        int i = this.bp;
        nextChar();
        while (this.bp < this.buflen) {
            switch (this.ch) {
                case '\n':
                case '\f':
                case '\r':
                    this.newline = true;
                    break;
                case '\"':
                    nextChar();
                    return;
                case '@':
                    if (!this.newline) {
                        break;
                    } else {
                        return;
                    }
            }
            nextChar();
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0016. Please report as an issue. */
    protected void inlineWord() {
        int i = this.bp;
        int i2 = 0;
        while (this.bp < this.buflen) {
            switch (this.ch) {
                case '\t':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '\n':
                    this.newline = true;
                    return;
                case '@':
                    if (this.newline) {
                        return;
                    }
                case '{':
                    i2++;
                    this.newline = false;
                    nextChar();
                case '}':
                    if (i2 == 0) {
                        return;
                    }
                    i2--;
                    if (i2 == 0) {
                        return;
                    }
                    this.newline = false;
                    nextChar();
                default:
                    this.newline = false;
                    nextChar();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x001a. Please report as an issue. */
    public void inlineContent() {
        skipWhitespace();
        int i = this.bp;
        int i2 = 1;
        while (this.bp < this.buflen) {
            switch (this.ch) {
                case '\t':
                case ' ':
                    nextChar();
                case '\n':
                case '\f':
                case '\r':
                    this.newline = true;
                    nextChar();
                case '&':
                    entity(null);
                case '<':
                    this.newline = false;
                    html();
                case '@':
                    if (this.newline) {
                        return;
                    }
                    nextChar();
                case '{':
                    this.newline = false;
                    i2++;
                    nextChar();
                case '}':
                    this.newline = false;
                    i2--;
                    if (i2 == 0) {
                        nextChar();
                        return;
                    }
                    nextChar();
                default:
                    nextChar();
            }
        }
    }

    protected void entity(Void r4) {
        this.newline = false;
        entity();
    }

    protected void entity() {
        nextChar();
        String str = null;
        if (this.ch == '#') {
            int i = this.bp;
            nextChar();
            if (isDecimalDigit(this.ch)) {
                nextChar();
                while (isDecimalDigit(this.ch)) {
                    nextChar();
                }
                str = new String(this.buf, i, this.bp - i);
            } else if (this.ch == 'x' || this.ch == 'X') {
                nextChar();
                if (isHexDigit(this.ch)) {
                    nextChar();
                    while (isHexDigit(this.ch)) {
                        nextChar();
                    }
                    str = new String(this.buf, i, this.bp - i);
                }
            }
        } else if (isIdentifierStart(this.ch)) {
            str = readIdentifier();
        }
        if (str == null || this.ch != ';') {
            return;
        }
        nextChar();
    }

    protected void html() {
        int i = this.bp;
        nextChar();
        if (isIdentifierStart(this.ch)) {
            checkHtmlTag(readIdentifier());
            htmlAttrs();
            if (this.ch == '/') {
                nextChar();
            }
            if (this.ch == '>') {
                nextChar();
                return;
            }
        } else if (this.ch == '/') {
            nextChar();
            if (isIdentifierStart(this.ch)) {
                readIdentifier();
                skipWhitespace();
                if (this.ch == '>') {
                    nextChar();
                    return;
                }
            }
        } else if (this.ch == '!') {
            nextChar();
            if (this.ch == '-') {
                nextChar();
                if (this.ch == '-') {
                    nextChar();
                    while (this.bp < this.buflen) {
                        int i2 = 0;
                        while (this.ch == '-') {
                            i2++;
                            nextChar();
                        }
                        if (i2 >= 2 && this.ch == '>') {
                            nextChar();
                            return;
                        }
                        nextChar();
                    }
                }
            }
        }
        this.bp = i + 1;
        this.ch = this.buf[this.bp];
    }

    protected void htmlAttrs() {
        skipWhitespace();
        while (isIdentifierStart(this.ch)) {
            int i = this.bp;
            String readAttributeName = readAttributeName();
            skipWhitespace();
            StringBuilder sb = new StringBuilder();
            if (this.ch == '=') {
                nextChar();
                skipWhitespace();
                if (this.ch == '\'' || this.ch == '\"') {
                    char c = this.ch;
                    nextChar();
                    while (this.bp < this.buflen && this.ch != c) {
                        if (!this.newline || this.ch != '@') {
                            sb.append(this.ch);
                            nextChar();
                        } else {
                            return;
                        }
                    }
                    nextChar();
                } else {
                    while (this.bp < this.buflen && !isUnquotedAttrValueTerminator(this.ch)) {
                        sb.append(this.ch);
                        nextChar();
                    }
                }
                skipWhitespace();
            }
            checkHtmlAttr(readAttributeName, sb.toString());
        }
    }

    protected void attrValueChar(Void r4) {
        switch (this.ch) {
            case '&':
                entity(r4);
                return;
            case '{':
                inlineTag(r4);
                return;
            default:
                nextChar();
                return;
        }
    }

    protected boolean isIdentifierStart(char c) {
        return Character.isUnicodeIdentifierStart(c);
    }

    protected String readIdentifier() {
        int i = this.bp;
        nextChar();
        while (this.bp < this.buflen && Character.isUnicodeIdentifierPart(this.ch)) {
            nextChar();
        }
        return new String(this.buf, i, this.bp - i);
    }

    protected String readAttributeName() {
        int i = this.bp;
        nextChar();
        while (this.bp < this.buflen && (Character.isUnicodeIdentifierPart(this.ch) || this.ch == '-')) {
            nextChar();
        }
        return new String(this.buf, i, this.bp - i);
    }

    protected String readTagName() {
        int i = this.bp;
        nextChar();
        while (this.bp < this.buflen && (Character.isUnicodeIdentifierPart(this.ch) || this.ch == '.' || this.ch == '-' || this.ch == ':')) {
            nextChar();
        }
        return new String(this.buf, i, this.bp - i);
    }

    protected boolean isJavaIdentifierStart(char c) {
        return Character.isJavaIdentifierStart(c);
    }

    protected String readJavaIdentifier() {
        int i = this.bp;
        nextChar();
        while (this.bp < this.buflen && Character.isJavaIdentifierPart(this.ch)) {
            nextChar();
        }
        return new String(this.buf, i, this.bp - i);
    }

    protected boolean isDecimalDigit(char c) {
        return '0' <= c && c <= '9';
    }

    protected boolean isHexDigit(char c) {
        return ('0' <= c && c <= '9') || ('a' <= c && c <= 'f') || ('A' <= c && c <= 'F');
    }

    protected boolean isUnquotedAttrValueTerminator(char c) {
        switch (c) {
            case '\t':
            case '\n':
            case '\f':
            case '\r':
            case ' ':
            case '\"':
            case '\'':
            case '<':
            case '=':
            case '>':
            case '`':
                return true;
            default:
                return false;
        }
    }

    protected boolean isWhitespace(char c) {
        return Character.isWhitespace(c);
    }

    protected void skipWhitespace() {
        while (isWhitespace(this.ch)) {
            nextChar();
        }
    }

    String newString(int i, int i2) {
        return new String(this.buf, i, i2 - i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/javadoc/JavaScriptScanner$TagParser.class */
    public static abstract class TagParser {
        final Kind kind;
        final String name;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: target.jar:com/sun/tools/javadoc/JavaScriptScanner$TagParser$Kind.class */
        public enum Kind {
            INLINE,
            BLOCK
        }

        abstract void parse(int i) throws ParseException;

        TagParser(Kind kind, String str) {
            this.kind = kind;
            this.name = str;
        }

        TagParser(Kind kind, String str, boolean z) {
            this(kind, str);
        }

        Kind getKind() {
            return this.kind;
        }

        String getName() {
            return this.name;
        }
    }

    private void initTagParsers() {
        TagParser[] tagParserArr = {new TagParser(TagParser.Kind.BLOCK, "author") { // from class: com.sun.tools.javadoc.JavaScriptScanner.1
            @Override // com.sun.tools.javadoc.JavaScriptScanner.TagParser
            public void parse(int i) {
                JavaScriptScanner.this.blockContent();
            }
        }, new TagParser(TagParser.Kind.INLINE, "code", true) { // from class: com.sun.tools.javadoc.JavaScriptScanner.2
            @Override // com.sun.tools.javadoc.JavaScriptScanner.TagParser
            public void parse(int i) throws ParseException {
                JavaScriptScanner.this.inlineText(WhitespaceRetentionPolicy.REMOVE_FIRST_SPACE);
                JavaScriptScanner.this.nextChar();
            }
        }, new TagParser(TagParser.Kind.BLOCK, "deprecated") { // from class: com.sun.tools.javadoc.JavaScriptScanner.3
            @Override // com.sun.tools.javadoc.JavaScriptScanner.TagParser
            public void parse(int i) {
                JavaScriptScanner.this.blockContent();
            }
        }, new TagParser(TagParser.Kind.INLINE, "docRoot") { // from class: com.sun.tools.javadoc.JavaScriptScanner.4
            @Override // com.sun.tools.javadoc.JavaScriptScanner.TagParser
            public void parse(int i) throws ParseException {
                if (JavaScriptScanner.this.ch != '}') {
                    JavaScriptScanner.this.inlineText(WhitespaceRetentionPolicy.REMOVE_ALL);
                    JavaScriptScanner.this.nextChar();
                    throw new ParseException("dc.unexpected.content");
                }
                JavaScriptScanner.this.nextChar();
            }
        }, new TagParser(TagParser.Kind.BLOCK, "exception") { // from class: com.sun.tools.javadoc.JavaScriptScanner.5
            @Override // com.sun.tools.javadoc.JavaScriptScanner.TagParser
            public void parse(int i) throws ParseException {
                JavaScriptScanner.this.skipWhitespace();
                JavaScriptScanner.this.reference(false);
                JavaScriptScanner.this.blockContent();
            }
        }, new TagParser(TagParser.Kind.BLOCK, "hidden") { // from class: com.sun.tools.javadoc.JavaScriptScanner.6
            @Override // com.sun.tools.javadoc.JavaScriptScanner.TagParser
            public void parse(int i) {
                JavaScriptScanner.this.blockContent();
            }
        }, new TagParser(TagParser.Kind.INLINE, "index") { // from class: com.sun.tools.javadoc.JavaScriptScanner.7
            @Override // com.sun.tools.javadoc.JavaScriptScanner.TagParser
            public void parse(int i) throws ParseException {
                JavaScriptScanner.this.skipWhitespace();
                if (JavaScriptScanner.this.ch == '}') {
                    throw new ParseException("dc.no.content");
                }
                if (JavaScriptScanner.this.ch == '\"') {
                    JavaScriptScanner.this.quotedString();
                } else {
                    JavaScriptScanner.this.inlineWord();
                }
                JavaScriptScanner.this.skipWhitespace();
                if (JavaScriptScanner.this.ch != '}') {
                    JavaScriptScanner.this.inlineContent();
                } else {
                    JavaScriptScanner.this.nextChar();
                }
            }
        }, new TagParser(TagParser.Kind.INLINE, "inheritDoc") { // from class: com.sun.tools.javadoc.JavaScriptScanner.8
            @Override // com.sun.tools.javadoc.JavaScriptScanner.TagParser
            public void parse(int i) throws ParseException {
                if (JavaScriptScanner.this.ch != '}') {
                    JavaScriptScanner.this.inlineText(WhitespaceRetentionPolicy.REMOVE_ALL);
                    JavaScriptScanner.this.nextChar();
                    throw new ParseException("dc.unexpected.content");
                }
                JavaScriptScanner.this.nextChar();
            }
        }, new TagParser(TagParser.Kind.INLINE, "link") { // from class: com.sun.tools.javadoc.JavaScriptScanner.9
            @Override // com.sun.tools.javadoc.JavaScriptScanner.TagParser
            public void parse(int i) throws ParseException {
                JavaScriptScanner.this.reference(true);
                JavaScriptScanner.this.inlineContent();
            }
        }, new TagParser(TagParser.Kind.INLINE, "linkplain") { // from class: com.sun.tools.javadoc.JavaScriptScanner.10
            @Override // com.sun.tools.javadoc.JavaScriptScanner.TagParser
            public void parse(int i) throws ParseException {
                JavaScriptScanner.this.reference(true);
                JavaScriptScanner.this.inlineContent();
            }
        }, new TagParser(TagParser.Kind.INLINE, Constants.ATTRVALUE_LITERAL, true) { // from class: com.sun.tools.javadoc.JavaScriptScanner.11
            @Override // com.sun.tools.javadoc.JavaScriptScanner.TagParser
            public void parse(int i) throws ParseException {
                JavaScriptScanner.this.inlineText(WhitespaceRetentionPolicy.REMOVE_FIRST_SPACE);
                JavaScriptScanner.this.nextChar();
            }
        }, new TagParser(TagParser.Kind.BLOCK, "param") { // from class: com.sun.tools.javadoc.JavaScriptScanner.12
            @Override // com.sun.tools.javadoc.JavaScriptScanner.TagParser
            public void parse(int i) throws ParseException {
                JavaScriptScanner.this.skipWhitespace();
                boolean z = false;
                if (JavaScriptScanner.this.ch == '<') {
                    z = true;
                    JavaScriptScanner.this.nextChar();
                }
                JavaScriptScanner.this.identifier();
                if (z) {
                    if (JavaScriptScanner.this.ch != '>') {
                        throw new ParseException("dc.gt.expected");
                    }
                    JavaScriptScanner.this.nextChar();
                }
                JavaScriptScanner.this.skipWhitespace();
                JavaScriptScanner.this.blockContent();
            }
        }, new TagParser(TagParser.Kind.BLOCK, "return") { // from class: com.sun.tools.javadoc.JavaScriptScanner.13
            @Override // com.sun.tools.javadoc.JavaScriptScanner.TagParser
            public void parse(int i) {
                JavaScriptScanner.this.blockContent();
            }
        }, new TagParser(TagParser.Kind.BLOCK, "see") { // from class: com.sun.tools.javadoc.JavaScriptScanner.14
            /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000e. Please report as an issue. */
            @Override // com.sun.tools.javadoc.JavaScriptScanner.TagParser
            public void parse(int i) throws ParseException {
                JavaScriptScanner.this.skipWhitespace();
                switch (JavaScriptScanner.this.ch) {
                    case 26:
                        if (JavaScriptScanner.this.bp == JavaScriptScanner.this.buf.length - 1) {
                            throw new ParseException("dc.no.content");
                        }
                        throw new ParseException("dc.unexpected.content");
                    case '\"':
                        JavaScriptScanner.this.quotedString();
                        JavaScriptScanner.this.skipWhitespace();
                        if (JavaScriptScanner.this.ch == '@') {
                            return;
                        }
                        if (JavaScriptScanner.this.ch == 26 && JavaScriptScanner.this.bp == JavaScriptScanner.this.buf.length - 1) {
                            return;
                        }
                        throw new ParseException("dc.unexpected.content");
                    case '<':
                        JavaScriptScanner.this.blockContent();
                        return;
                    case '@':
                        if (JavaScriptScanner.this.newline) {
                            throw new ParseException("dc.no.content");
                        }
                        throw new ParseException("dc.unexpected.content");
                    default:
                        if (JavaScriptScanner.this.isJavaIdentifierStart(JavaScriptScanner.this.ch) || JavaScriptScanner.this.ch == '#') {
                            JavaScriptScanner.this.reference(true);
                            JavaScriptScanner.this.blockContent();
                        }
                        throw new ParseException("dc.unexpected.content");
                }
            }
        }, new TagParser(TagParser.Kind.BLOCK, "@serialData") { // from class: com.sun.tools.javadoc.JavaScriptScanner.15
            @Override // com.sun.tools.javadoc.JavaScriptScanner.TagParser
            public void parse(int i) {
                JavaScriptScanner.this.blockContent();
            }
        }, new TagParser(TagParser.Kind.BLOCK, "serialField") { // from class: com.sun.tools.javadoc.JavaScriptScanner.16
            @Override // com.sun.tools.javadoc.JavaScriptScanner.TagParser
            public void parse(int i) throws ParseException {
                JavaScriptScanner.this.skipWhitespace();
                JavaScriptScanner.this.identifier();
                JavaScriptScanner.this.skipWhitespace();
                JavaScriptScanner.this.reference(false);
                if (JavaScriptScanner.this.isWhitespace(JavaScriptScanner.this.ch)) {
                    JavaScriptScanner.this.skipWhitespace();
                    JavaScriptScanner.this.blockContent();
                }
            }
        }, new TagParser(TagParser.Kind.BLOCK, "serial") { // from class: com.sun.tools.javadoc.JavaScriptScanner.17
            @Override // com.sun.tools.javadoc.JavaScriptScanner.TagParser
            public void parse(int i) {
                JavaScriptScanner.this.blockContent();
            }
        }, new TagParser(TagParser.Kind.BLOCK, "since") { // from class: com.sun.tools.javadoc.JavaScriptScanner.18
            @Override // com.sun.tools.javadoc.JavaScriptScanner.TagParser
            public void parse(int i) {
                JavaScriptScanner.this.blockContent();
            }
        }, new TagParser(TagParser.Kind.BLOCK, "throws") { // from class: com.sun.tools.javadoc.JavaScriptScanner.19
            @Override // com.sun.tools.javadoc.JavaScriptScanner.TagParser
            public void parse(int i) throws ParseException {
                JavaScriptScanner.this.skipWhitespace();
                JavaScriptScanner.this.reference(false);
                JavaScriptScanner.this.blockContent();
            }
        }, new TagParser(TagParser.Kind.INLINE, "value") { // from class: com.sun.tools.javadoc.JavaScriptScanner.20
            @Override // com.sun.tools.javadoc.JavaScriptScanner.TagParser
            public void parse(int i) throws ParseException {
                JavaScriptScanner.this.reference(true);
                JavaScriptScanner.this.skipWhitespace();
                if (JavaScriptScanner.this.ch == '}') {
                    JavaScriptScanner.this.nextChar();
                } else {
                    JavaScriptScanner.this.nextChar();
                    throw new ParseException("dc.unexpected.content");
                }
            }
        }, new TagParser(TagParser.Kind.BLOCK, "version") { // from class: com.sun.tools.javadoc.JavaScriptScanner.21
            @Override // com.sun.tools.javadoc.JavaScriptScanner.TagParser
            public void parse(int i) {
                JavaScriptScanner.this.blockContent();
            }
        }};
        this.tagParsers = new HashMap();
        for (TagParser tagParser : tagParserArr) {
            this.tagParsers.put(tagParser.getName(), tagParser);
        }
    }

    private void initURIAttrs() {
        this.uriAttrs = new HashSet(Arrays.asList("action", "cite", "classid", "codebase", "data", "datasrc", "for", "href", "longdesc", "profile", "src", "usemap"));
    }
}
