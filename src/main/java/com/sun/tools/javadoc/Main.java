package com.sun.tools.javadoc;

import java.io.PrintWriter;

/* loaded from: target.jar:com/sun/tools/javadoc/Main.class */
public class Main {
    private Main() {
    }

    public static void main(String... strArr) {
        System.exit(execute(strArr));
    }

    public static int execute(String... strArr) {
        return new Start().begin(strArr);
    }

    public static int execute(ClassLoader classLoader, String... strArr) {
        return new Start(classLoader).begin(strArr);
    }

    public static int execute(String str, String... strArr) {
        return new Start(str).begin(strArr);
    }

    public static int execute(String str, ClassLoader classLoader, String... strArr) {
        return new Start(str, classLoader).begin(strArr);
    }

    public static int execute(String str, String str2, String... strArr) {
        return new Start(str, str2).begin(strArr);
    }

    public static int execute(String str, String str2, ClassLoader classLoader, String... strArr) {
        return new Start(str, str2, classLoader).begin(strArr);
    }

    public static int execute(String str, PrintWriter printWriter, PrintWriter printWriter2, PrintWriter printWriter3, String str2, String... strArr) {
        return new Start(str, printWriter, printWriter2, printWriter3, str2).begin(strArr);
    }

    public static int execute(String str, PrintWriter printWriter, PrintWriter printWriter2, PrintWriter printWriter3, String str2, ClassLoader classLoader, String... strArr) {
        return new Start(str, printWriter, printWriter2, printWriter3, str2, classLoader).begin(strArr);
    }
}
