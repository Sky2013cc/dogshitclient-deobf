package com.sun.tools.javah;

import java.io.PrintWriter;

/* loaded from: target.jar:com/sun/tools/javah/Main.class */
public class Main {
    public static void main(String[] strArr) {
        System.exit(new JavahTask().run(strArr));
    }

    public static int run(String[] strArr, PrintWriter printWriter) {
        JavahTask javahTask = new JavahTask();
        javahTask.setLog(printWriter);
        return javahTask.run(strArr);
    }
}
