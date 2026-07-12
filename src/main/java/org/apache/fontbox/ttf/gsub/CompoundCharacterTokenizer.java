package org.apache.fontbox.ttf.gsub;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:org/apache/fontbox/ttf/gsub/CompoundCharacterTokenizer.class */
public class CompoundCharacterTokenizer {
    private final Pattern regexExpression;

    public CompoundCharacterTokenizer(Set<String> compoundWords) {
        this.regexExpression = Pattern.compile(getRegexFromTokens(compoundWords));
    }

    public CompoundCharacterTokenizer(String singleRegex) {
        this.regexExpression = Pattern.compile(singleRegex);
    }

    public List<String> tokenize(String text) {
        int lastIndexOfPrevMatch;
        List<String> tokens = new ArrayList<>();
        Matcher regexMatcher = this.regexExpression.matcher(text);
        int i = 0;
        while (true) {
            lastIndexOfPrevMatch = i;
            if (!regexMatcher.find()) {
                break;
            }
            int beginIndexOfNextMatch = regexMatcher.start();
            String prevToken = text.substring(lastIndexOfPrevMatch, beginIndexOfNextMatch);
            if (prevToken.length() > 0) {
                tokens.add(prevToken);
            }
            String currentMatch = regexMatcher.group();
            tokens.add(currentMatch);
            i = regexMatcher.end();
        }
        String tail = text.substring(lastIndexOfPrevMatch);
        if (tail.length() > 0) {
            tokens.add(tail);
        }
        return tokens;
    }

    private String getRegexFromTokens(Set<String> compoundWords) {
        StringJoiner sj = new StringJoiner(")|(", RuntimeConstants.SIG_METHOD, RuntimeConstants.SIG_ENDMETHOD);
        sj.getClass();
        compoundWords.forEach((v1) -> {
            r1.add(v1);
        });
        return sj.toString();
    }
}
