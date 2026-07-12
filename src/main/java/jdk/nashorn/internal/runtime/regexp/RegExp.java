package jdk.nashorn.internal.runtime.regexp;

import jdk.nashorn.internal.runtime.BitVector;
import jdk.nashorn.internal.runtime.ECMAErrors;
import jdk.nashorn.internal.runtime.ParserException;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: target.jar:jdk/nashorn/internal/runtime/regexp/RegExp.class */
public abstract class RegExp {
    private final String source;
    private boolean global;
    private boolean ignoreCase;
    private boolean multiline;
    protected BitVector groupsInNegativeLookahead;

    public abstract RegExpMatcher match(String str);

    /* JADX INFO: Access modifiers changed from: protected */
    public RegExp(String source, String flags) {
        this.source = source.length() == 0 ? "(?:)" : source;
        for (int i = 0; i < flags.length(); i++) {
            char ch = flags.charAt(i);
            switch (ch) {
                case 'g':
                    if (this.global) {
                        throwParserException("repeated.flag", OperatorName.NON_STROKING_GRAY);
                    }
                    this.global = true;
                    break;
                case 'i':
                    if (this.ignoreCase) {
                        throwParserException("repeated.flag", OperatorName.SET_FLATNESS);
                    }
                    this.ignoreCase = true;
                    break;
                case 'm':
                    if (this.multiline) {
                        throwParserException("repeated.flag", "m");
                    }
                    this.multiline = true;
                    break;
                default:
                    throwParserException("unsupported.flag", Character.toString(ch));
                    break;
            }
        }
    }

    public String getSource() {
        return this.source;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public boolean isGlobal() {
        return this.global;
    }

    public boolean isIgnoreCase() {
        return this.ignoreCase;
    }

    public boolean isMultiline() {
        return this.multiline;
    }

    public BitVector getGroupsInNegativeLookahead() {
        return this.groupsInNegativeLookahead;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void throwParserException(String key, String str) throws ParserException {
        throw new ParserException(ECMAErrors.getMessage("parser.error.regex." + key, str));
    }
}
