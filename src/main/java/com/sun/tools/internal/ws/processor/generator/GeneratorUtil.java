package com.sun.tools.internal.ws.processor.generator;

import com.sun.tools.internal.ws.wscompile.Options;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/generator/GeneratorUtil.class */
public class GeneratorUtil {
    public static boolean classExists(Options options, String className) {
        try {
            getLoadableClassName(className, options.getClassLoader());
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private static String getLoadableClassName(String className, ClassLoader classLoader) throws ClassNotFoundException {
        try {
            Class.forName(className, true, classLoader);
            return className;
        } catch (ClassNotFoundException e) {
            int idx = className.lastIndexOf(GeneratorConstants.DOTC.getValue());
            if (idx > -1) {
                String tmp = className.substring(0, idx) + GeneratorConstants.SIG_INNERCLASS.getValue();
                return getLoadableClassName(tmp + className.substring(idx + 1), classLoader);
            }
            throw e;
        }
    }
}
