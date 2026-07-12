package jdk.nashorn.internal.runtime.regexp;

import java.util.regex.MatchResult;

/* loaded from: target.jar:jdk/nashorn/internal/runtime/regexp/RegExpMatcher.class */
public interface RegExpMatcher extends MatchResult {
    boolean search(int i);

    String getInput();
}
