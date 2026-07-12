package com.sun.tools.internal.ws.wscompile;

import com.sun.istack.internal.tools.ParallelWorldClassLoader;
import com.sun.tools.internal.ws.resources.JavacompilerMessages;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:com/sun/tools/internal/ws/wscompile/JavaCompilerHelper.class */
public class JavaCompilerHelper {
    private static final Class[] compileMethodSignature = {String[].class, PrintWriter.class};

    JavaCompilerHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static File getJarFile(Class clazz) {
        URL url = null;
        try {
            url = ParallelWorldClassLoader.toJarUrl(clazz.getResource('/' + clazz.getName().replace('.', '/') + ".class"));
            return new File(url.toURI());
        } catch (ClassNotFoundException e) {
            throw new Error(e);
        } catch (MalformedURLException e2) {
            throw new Error(e2);
        } catch (URISyntaxException e3) {
            return new File(url.getPath());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean compile(String[] args, OutputStream out, ErrorReceiver receiver) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        try {
            Class comSunToolsJavacMainClass = cl.loadClass("com.sun.tools.javac.Main");
            try {
                Method compileMethod = comSunToolsJavacMainClass.getMethod("compile", compileMethodSignature);
                Object result = compileMethod.invoke(null, args, new PrintWriter(out));
                if (result instanceof Integer) {
                    if (((Integer) result).intValue() == 0) {
                        return true;
                    }
                }
                return false;
            } catch (IllegalAccessException e) {
                receiver.error(e);
                return false;
            } catch (NoSuchMethodException e2) {
                receiver.error(JavacompilerMessages.JAVACOMPILER_NOSUCHMETHOD_ERROR("getMethod(\"compile\", Class[])"), e2);
                return false;
            } catch (InvocationTargetException e3) {
                receiver.error(e3);
                return false;
            }
        } catch (ClassNotFoundException e4) {
            receiver.error(JavacompilerMessages.JAVACOMPILER_CLASSPATH_ERROR("com.sun.tools.javac.Main"), e4);
            return false;
        } catch (SecurityException e5) {
            receiver.error(e5);
            return false;
        }
    }
}
