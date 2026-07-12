package com.sun.tools.internal.jxc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: target.jar:com/sun/tools/internal/jxc/SchemaGeneratorFacade.class */
public class SchemaGeneratorFacade {
    public static void main(String[] args) throws Throwable {
        try {
            ClassLoader cl = SecureLoader.getClassClassLoader(SchemaGeneratorFacade.class);
            if (cl == null) {
                cl = SecureLoader.getSystemClassLoader();
            }
            Class driver = cl.loadClass("com.sun.tools.internal.jxc.SchemaGenerator");
            Method mainMethod = driver.getDeclaredMethod("main", String[].class);
            try {
                mainMethod.invoke(null, args);
            } catch (IllegalAccessException e) {
                throw e;
            } catch (InvocationTargetException e2) {
                if (e2.getTargetException() != null) {
                    throw e2.getTargetException();
                }
            }
        } catch (UnsupportedClassVersionError e3) {
            System.err.println("schemagen requires JDK 6.0 or later. Please download it from http://www.oracle.com/technetwork/java/javase/downloads");
        }
    }
}
