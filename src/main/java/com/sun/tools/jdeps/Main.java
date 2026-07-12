package com.sun.tools.jdeps;

import java.io.PrintWriter;

/* loaded from: target.jar:com/sun/tools/jdeps/Main.class */
public class Main {
    public static void main(String... strArr) throws Exception {
        System.exit(new JdepsTask().run(strArr));
    }

    public static int run(String[] strArr, PrintWriter printWriter) {
        JdepsTask jdepsTask = new JdepsTask();
        jdepsTask.setLog(printWriter);
        return jdepsTask.run(strArr);
    }
}
