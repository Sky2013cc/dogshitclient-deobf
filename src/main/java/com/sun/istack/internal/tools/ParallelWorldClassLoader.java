package com.sun.istack.internal.tools;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: target.jar:com/sun/istack/internal/tools/ParallelWorldClassLoader.class */
public class ParallelWorldClassLoader extends ClassLoader implements Closeable {
    private final String prefix;
    private final Set<JarFile> jars;

    public ParallelWorldClassLoader(ClassLoader parent, String prefix) {
        super(parent);
        this.prefix = prefix;
        this.jars = Collections.synchronizedSet(new HashSet());
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x0156 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.ClassLoader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected Class findClass(String name) throws ClassNotFoundException {
        StringBuffer sb = new StringBuffer(name.length() + this.prefix.length() + 6);
        sb.append(this.prefix).append(name.replace('.', '/')).append(".class");
        URL u = getParent().getResource(sb.toString());
        if (u == null) {
            throw new ClassNotFoundException(name);
        }
        try {
            URLConnection con = u.openConnection();
            InputStream is = con.getInputStream();
            try {
                if (is == null) {
                    throw new ClassNotFoundException(name);
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
                    int packIndex = name.lastIndexOf(46);
                    if (packIndex != -1) {
                        String pkgname = name.substring(0, packIndex);
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
                if (con != null) {
                    try {
                        if (con instanceof JarURLConnection) {
                            this.jars.add(((JarURLConnection) con).getJarFile());
                        }
                    } catch (IOException e2) {
                        if (is != null) {
                            try {
                                is.close();
                            } catch (IOException e3) {
                            }
                        }
                    }
                }
                if (is != null) {
                }
            }
        } catch (IOException e4) {
            throw new ClassNotFoundException(name);
        }
    }

    @Override // java.lang.ClassLoader
    protected URL findResource(String name) {
        URL u = getParent().getResource(this.prefix + name);
        if (u != null) {
            try {
                this.jars.add(new JarFile(new File(toJarUrl(u).toURI())));
            } catch (IOException ex) {
                Logger.getLogger(ParallelWorldClassLoader.class.getName()).log(Level.WARNING, (String) null, (Throwable) ex);
            } catch (ClassNotFoundException e) {
            } catch (URISyntaxException ex2) {
                Logger.getLogger(ParallelWorldClassLoader.class.getName()).log(Level.WARNING, (String) null, (Throwable) ex2);
            }
        }
        return u;
    }

    @Override // java.lang.ClassLoader
    protected Enumeration<URL> findResources(String name) throws IOException {
        Enumeration<URL> en = getParent().getResources(this.prefix + name);
        while (en.hasMoreElements()) {
            try {
                this.jars.add(new JarFile(new File(toJarUrl(en.nextElement()).toURI())));
            } catch (IOException ex) {
                Logger.getLogger(ParallelWorldClassLoader.class.getName()).log(Level.WARNING, (String) null, (Throwable) ex);
            } catch (ClassNotFoundException e) {
            } catch (URISyntaxException ex2) {
                Logger.getLogger(ParallelWorldClassLoader.class.getName()).log(Level.WARNING, (String) null, (Throwable) ex2);
            }
        }
        return en;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        for (JarFile jar : this.jars) {
            jar.close();
        }
    }

    public static URL toJarUrl(URL res) throws ClassNotFoundException, MalformedURLException {
        String url = res.toExternalForm();
        if (!url.startsWith("jar:")) {
            throw new ClassNotFoundException("Loaded outside a jar " + url);
        }
        String url2 = url.substring(4);
        return new URL(url2.substring(0, url2.lastIndexOf(33)).replaceAll(" ", "%20"));
    }
}
