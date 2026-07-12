package com.sun.tools.javap;

import java.io.PrintWriter;

/* loaded from: target.jar:com/sun/tools/javap/Main.class */
public class Main {
    public static void main(String[] strArr) {
        System.exit(new JavapTask().run(strArr));
    }

    public static int run(String[] strArr, PrintWriter printWriter) {
        JavapTask javapTask = new JavapTask();
        javapTask.setLog(printWriter);
        return javapTask.run(strArr);
    }
}
