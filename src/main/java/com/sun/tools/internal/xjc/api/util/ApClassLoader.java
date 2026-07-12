package com.sun.tools.internal.xjc.api.util;

import com.sun.istack.internal.Nullable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/* loaded from: target.jar:com/sun/tools/internal/xjc/api/util/ApClassLoader.class */
public final class ApClassLoader extends URLClassLoader {
    private final String[] packagePrefixes;

    public ApClassLoader(@Nullable ClassLoader parent, String[] packagePrefixes) throws ToolsJarNotFoundException {
        super(getToolsJar(parent), parent);
        if (getURLs().length == 0) {
            this.packagePrefixes = new String[0];
        } else {
            this.packagePrefixes = packagePrefixes;
        }
    }

    @Override // java.lang.ClassLoader
    public Class loadClass(String className) throws ClassNotFoundException {
        for (String prefix : this.packagePrefixes) {
            if (className.startsWith(prefix)) {
                return findClass(className);
            }
        }
        return super.loadClass(className);
    }

    @Override // java.net.URLClassLoader, java.lang.ClassLoader
    protected Class findClass(String name) throws ClassNotFoundException {
        StringBuilder sb = new StringBuilder(name.length() + 6);
        sb.append(name.replace('.', '/')).append(".class");
        InputStream is = getResourceAsStream(sb.toString());
        try {
            if (is == null) {
                throw new ClassNotFoundException("Class not found" + ((Object) sb));
            }
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                while (true) {
                    int len = is.read(buf);
                    if (len < 0) {
                        break;
                    }
                    baos.write(buf, 0, len);
                }
                byte[] buf2 = baos.toByteArray();
                int i = name.lastIndexOf(46);
                if (i != -1) {
                    String pkgname = name.substring(0, i);
                    Package pkg = getPackage(pkgname);
                    if (pkg == null) {
                        definePackage(pkgname, null, null, null, null, null, null, null);
                    }
                }
                return defineClass(name, buf2, 0, buf2.length);
            } catch (IOException e) {
                throw new ClassNotFoundException(name, e);
            }
        } finally {
            try {
                is.close();
            } catch (IOException e2) {
            }
        }
    }

    private static URL[] getToolsJar(@Nullable ClassLoader parent) throws ToolsJarNotFoundException {
        try {
            Class.forName("com.sun.tools.javac.Main", false, parent);
            return new URL[0];
        } catch (ClassNotFoundException e) {
            File jreHome = new File(System.getProperty("java.home"));
            File toolsJar = new File(jreHome.getParent(), "lib/tools.jar");
            if (!toolsJar.exists()) {
                throw new ToolsJarNotFoundException(toolsJar);
            }
            try {
                return new URL[]{toolsJar.toURL()};
            } catch (MalformedURLException e2) {
                throw new AssertionError(e2);
            }
        }
    }
}
