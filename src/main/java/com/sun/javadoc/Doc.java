package com.sun.javadoc;

/* loaded from: target.jar:com/sun/javadoc/Doc.class */
public interface Doc extends Comparable<Object> {
    String commentText();

    Tag[] tags();

    Tag[] tags(String str);

    SeeTag[] seeTags();

    Tag[] inlineTags();

    Tag[] firstSentenceTags();

    String getRawCommentText();

    void setRawCommentText(String str);

    String name();

    @Override // java.lang.Comparable
    int compareTo(Object obj);

    boolean isField();

    boolean isEnumConstant();

    boolean isConstructor();

    boolean isMethod();

    boolean isAnnotationTypeElement();

    boolean isInterface();

    boolean isException();

    boolean isError();

    boolean isEnum();

    boolean isAnnotationType();

    boolean isOrdinaryClass();

    boolean isClass();

    boolean isIncluded();

    SourcePosition position();
}
