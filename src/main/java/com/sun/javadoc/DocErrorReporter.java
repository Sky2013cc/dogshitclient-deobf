package com.sun.javadoc;

/* loaded from: target.jar:com/sun/javadoc/DocErrorReporter.class */
public interface DocErrorReporter {
    void printError(String str);

    void printError(SourcePosition sourcePosition, String str);

    void printWarning(String str);

    void printWarning(SourcePosition sourcePosition, String str);

    void printNotice(String str);

    void printNotice(SourcePosition sourcePosition, String str);
}
