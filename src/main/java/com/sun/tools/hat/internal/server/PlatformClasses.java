package com.sun.tools.hat.internal.server;

import com.sun.tools.hat.internal.model.JavaClass;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/hat/internal/server/PlatformClasses.class */
public class PlatformClasses {
    static String[] names = null;

    public static synchronized String[] getNames() {
        if (names == null) {
            LinkedList linkedList = new LinkedList();
            InputStream resourceAsStream = PlatformClasses.class.getResourceAsStream("/com/sun/tools/hat/resources/platform_names.txt");
            if (resourceAsStream != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        if (readLine.length() > 0) {
                            linkedList.add(readLine);
                        }
                    }
                    bufferedReader.close();
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            names = (String[]) linkedList.toArray(new String[linkedList.size()]);
        }
        return names;
    }

    public static boolean isPlatformClass(JavaClass javaClass) {
        int lastIndexOf;
        if (javaClass.isBootstrap()) {
            return true;
        }
        String name = javaClass.getName();
        if (name.startsWith(RuntimeConstants.SIG_ARRAY) && (lastIndexOf = name.lastIndexOf(91)) != -1) {
            if (name.charAt(lastIndexOf + 1) != 'L') {
                return true;
            }
            name = name.substring(lastIndexOf + 2);
        }
        for (String str : getNames()) {
            if (name.startsWith(str)) {
                return true;
            }
        }
        return false;
    }
}
