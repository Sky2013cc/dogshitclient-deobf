package com.sun.tools.extcheck;

import java.io.File;

/* loaded from: target.jar:com/sun/tools/extcheck/Main.class */
public final class Main {
    public static final String INSUFFICIENT = "Insufficient number of arguments";
    public static final String MISSING = "Missing <jar file> argument";
    public static final String DOES_NOT_EXIST = "Jarfile does not exist: ";
    public static final String EXTRA = "Extra command line argument: ";

    public static void main(String[] strArr) {
        try {
            realMain(strArr);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

    public static void realMain(String[] strArr) throws Exception {
        if (strArr.length < 1) {
            usage(INSUFFICIENT);
        }
        int i = 0;
        boolean z = false;
        if (strArr[0].equals("-verbose")) {
            z = true;
            i = 0 + 1;
            if (i >= strArr.length) {
                usage(MISSING);
            }
        }
        String str = strArr[i];
        int i2 = i + 1;
        File file = new File(str);
        if (!file.exists()) {
            usage(DOES_NOT_EXIST + str);
        }
        if (i2 < strArr.length) {
            usage(EXTRA + strArr[i2]);
        }
        if (ExtCheck.create(file, z).checkInstalledAgainstTarget()) {
            System.exit(0);
        } else {
            System.exit(1);
        }
    }

    private static void usage(String str) throws Exception {
        throw new Exception(str + "\nUsage: extcheck [-verbose] <jar file>");
    }
}
