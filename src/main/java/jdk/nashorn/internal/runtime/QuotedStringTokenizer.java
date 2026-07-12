package jdk.nashorn.internal.runtime;

import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

/* loaded from: target.jar:jdk/nashorn/internal/runtime/QuotedStringTokenizer.class */
public final class QuotedStringTokenizer {
    private final LinkedList<String> tokens;
    private final char[] quotes;

    public QuotedStringTokenizer(String str) {
        this(str, " ");
    }

    public QuotedStringTokenizer(String str, String delim) {
        this(str, delim, new char[]{'\"', '\''});
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x009a, code lost:
    
        r5.tokens.add(stripQuotes(r11));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private QuotedStringTokenizer(String str, String delim, char[] quotes) {
        this.quotes = quotes;
        boolean delimIsWhitespace = true;
        int i = 0;
        while (true) {
            if (i >= delim.length()) {
                break;
            }
            if (Character.isWhitespace(delim.charAt(i))) {
                i++;
            } else {
                delimIsWhitespace = false;
                break;
            }
        }
        StringTokenizer st = new StringTokenizer(str, delim);
        this.tokens = new LinkedList<>();
        while (st.hasMoreTokens()) {
            String nextToken = st.nextToken();
            while (true) {
                String token = nextToken;
                if (unmatchedQuotesIn(token)) {
                    if (!st.hasMoreTokens()) {
                        throw new IndexOutOfBoundsException(token);
                    }
                    nextToken = token + (delimIsWhitespace ? " " : delim) + st.nextToken();
                }
            }
        }
    }

    public int countTokens() {
        return this.tokens.size();
    }

    public boolean hasMoreTokens() {
        return countTokens() > 0;
    }

    public String nextToken() {
        return this.tokens.removeFirst();
    }

    private String stripQuotes(String value0) {
        String value = value0.trim();
        for (char q : this.quotes) {
            if (value.length() >= 2 && value.startsWith("" + q) && value.endsWith("" + q)) {
                value = value.substring(1, value.length() - 1).replace("\\" + q, "" + q);
            }
        }
        return value;
    }

    private boolean unmatchedQuotesIn(String str) {
        Stack<Character> quoteStack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            for (char q : this.quotes) {
                if (c == q) {
                    if (quoteStack.isEmpty()) {
                        quoteStack.push(Character.valueOf(c));
                    } else {
                        char top = quoteStack.pop().charValue();
                        if (top != c) {
                            quoteStack.push(Character.valueOf(top));
                            quoteStack.push(Character.valueOf(c));
                        }
                    }
                }
            }
        }
        return !quoteStack.isEmpty();
    }
}
