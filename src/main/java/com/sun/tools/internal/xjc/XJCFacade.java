package com.sun.tools.internal.xjc;

import com.sun.tools.internal.ws.wsdl.document.jaxws.JAXWSBindingsConstants;
import java.io.Closeable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLClassLoader;

/* loaded from: target.jar:com/sun/tools/internal/xjc/XJCFacade.class */
public class XJCFacade {
    private static final String JDK6_REQUIRED = "XJC requires JDK 6.0 or later. Please download it from http://www.oracle.com/technetwork/java/javase/downloads";

    public static void main(String[] args) throws Throwable {
        String v = JAXWSBindingsConstants.JAXB_BINDING_VERSION;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-source") && i + 1 < args.length) {
                v = parseVersion(args[i + 1]);
            }
        }
        ClassLoader oldContextCl = SecureLoader.getContextClassLoader();
        try {
            try {
                ClassLoader cl = ClassLoaderBuilder.createProtectiveClassLoader(SecureLoader.getClassClassLoader(XJCFacade.class), v);
                SecureLoader.setContextClassLoader(cl);
                Class<?> driver = cl.loadClass("com.sun.tools.internal.xjc.Driver");
                Method mainMethod = driver.getDeclaredMethod("main", String[].class);
                try {
                    mainMethod.invoke(null, args);
                } catch (InvocationTargetException e) {
                    if (e.getTargetException() != null) {
                        throw e.getTargetException();
                    }
                }
                SecureLoader.setContextClassLoader(oldContextCl);
                for (ClassLoader cl2 = SecureLoader.getContextClassLoader(); cl2 != null && !oldContextCl.equals(cl2); cl2 = SecureLoader.getParentClassLoader(cl2)) {
                    if (cl2 instanceof Closeable) {
                        ((Closeable) cl2).close();
                    } else if (cl2 instanceof URLClassLoader) {
                        try {
                            Class<?> clUtil = oldContextCl.loadClass("sun.misc.ClassLoaderUtil");
                            Method release = clUtil.getDeclaredMethod("releaseLoader", URLClassLoader.class);
                            release.invoke(null, cl2);
                        } catch (ClassNotFoundException e2) {
                            System.err.println(JDK6_REQUIRED);
                        }
                    }
                }
            } catch (Throwable th) {
                SecureLoader.setContextClassLoader(oldContextCl);
                for (ClassLoader cl3 = SecureLoader.getContextClassLoader(); cl3 != null && !oldContextCl.equals(cl3); cl3 = SecureLoader.getParentClassLoader(cl3)) {
                    if (cl3 instanceof Closeable) {
                        ((Closeable) cl3).close();
                    } else if (cl3 instanceof URLClassLoader) {
                        try {
                            Class<?> clUtil2 = oldContextCl.loadClass("sun.misc.ClassLoaderUtil");
                            Method release2 = clUtil2.getDeclaredMethod("releaseLoader", URLClassLoader.class);
                            release2.invoke(null, cl3);
                        } catch (ClassNotFoundException e3) {
                            System.err.println(JDK6_REQUIRED);
                        }
                    }
                }
                throw th;
            }
        } catch (UnsupportedClassVersionError e4) {
            System.err.println(JDK6_REQUIRED);
            SecureLoader.setContextClassLoader(oldContextCl);
            for (ClassLoader cl4 = SecureLoader.getContextClassLoader(); cl4 != null && !oldContextCl.equals(cl4); cl4 = SecureLoader.getParentClassLoader(cl4)) {
                if (cl4 instanceof Closeable) {
                    ((Closeable) cl4).close();
                } else if (cl4 instanceof URLClassLoader) {
                    try {
                        Class<?> clUtil3 = oldContextCl.loadClass("sun.misc.ClassLoaderUtil");
                        Method release3 = clUtil3.getDeclaredMethod("releaseLoader", URLClassLoader.class);
                        release3.invoke(null, cl4);
                    } catch (ClassNotFoundException e5) {
                        System.err.println(JDK6_REQUIRED);
                    }
                }
            }
        }
    }

    public static String parseVersion(String version) {
        return JAXWSBindingsConstants.JAXB_BINDING_VERSION;
    }
}
