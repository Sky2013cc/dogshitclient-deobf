package com.sun.javadoc;

/* loaded from: target.jar:com/sun/javadoc/Tag.class */
public interface Tag {
    String name();

    Doc holder();

    String kind();

    String text();

    String toString();

    Tag[] inlineTags();

    Tag[] firstSentenceTags();

    SourcePosition position();
}
