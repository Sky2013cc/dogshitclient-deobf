package com.sun.javadoc;

/* loaded from: target.jar:com/sun/javadoc/Doclet.class */
public abstract class Doclet {
    public static boolean start(RootDoc rootDoc) {
        return true;
    }

    public static int optionLength(String str) {
        return 0;
    }

    public static boolean validOptions(String[][] strArr, DocErrorReporter docErrorReporter) {
        return true;
    }

    public static LanguageVersion languageVersion() {
        return LanguageVersion.JAVA_1_1;
    }
}
