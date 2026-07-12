package sun.applet;

import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FilePermission;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.NoSuchElementException;
import sun.awt.AppContext;
import sun.misc.IOUtils;
import sun.net.www.ParseUtil;
import sun.rmi.rmic.iiop.Constants;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:sun/applet/AppletClassLoader.class */
public class AppletClassLoader extends URLClassLoader {
    private URL base;
    private CodeSource codesource;
    private AccessControlContext acc;
    private boolean exceptionStatus;
    private final Object threadGroupSynchronizer;
    private final Object grabReleaseSynchronizer;
    private boolean codebaseLookup;
    private volatile boolean allowRecursiveDirectoryRead;
    private Object syncResourceAsStream;
    private Object syncResourceAsStreamFromJar;
    private boolean resourceAsStreamInCall;
    private boolean resourceAsStreamFromJarInCall;
    private AppletThreadGroup threadGroup;
    private AppContext appContext;
    int usageCount;
    private HashMap jdk11AppletInfo;
    private HashMap jdk12AppletInfo;
    private static AppletMessageHandler mh = new AppletMessageHandler("appletclassloader");

    /* JADX INFO: Access modifiers changed from: protected */
    public AppletClassLoader(URL url) {
        super(new URL[0]);
        this.exceptionStatus = false;
        this.threadGroupSynchronizer = new Object();
        this.grabReleaseSynchronizer = new Object();
        this.codebaseLookup = true;
        this.allowRecursiveDirectoryRead = true;
        this.syncResourceAsStream = new Object();
        this.syncResourceAsStreamFromJar = new Object();
        this.resourceAsStreamInCall = false;
        this.resourceAsStreamFromJarInCall = false;
        this.usageCount = 0;
        this.jdk11AppletInfo = new HashMap();
        this.jdk12AppletInfo = new HashMap();
        this.base = url;
        this.codesource = new CodeSource(url, (Certificate[]) null);
        this.acc = AccessController.getContext();
    }

    public void disableRecursiveDirectoryRead() {
        this.allowRecursiveDirectoryRead = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCodebaseLookup(boolean z) {
        this.codebaseLookup = z;
    }

    URL getBaseURL() {
        return this.base;
    }

    @Override // java.net.URLClassLoader
    public URL[] getURLs() {
        URL[] uRLs = super.getURLs();
        URL[] urlArr = new URL[uRLs.length + 1];
        System.arraycopy(uRLs, 0, urlArr, 0, uRLs.length);
        urlArr[urlArr.length - 1] = this.base;
        return urlArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addJar(String str) throws IOException {
        try {
            addURL(new URL(this.base, str));
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("name");
        }
    }

    @Override // java.lang.ClassLoader
    public synchronized Class loadClass(String str, boolean z) throws ClassNotFoundException {
        SecurityManager securityManager;
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf != -1 && (securityManager = System.getSecurityManager()) != null) {
            securityManager.checkPackageAccess(str.substring(0, lastIndexOf));
        }
        try {
            return super.loadClass(str, z);
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (Error e2) {
            throw e2;
        } catch (RuntimeException e3) {
            throw e3;
        }
    }

    @Override // java.net.URLClassLoader, java.lang.ClassLoader
    protected Class findClass(String str) throws ClassNotFoundException {
        int indexOf = str.indexOf(RuntimeConstants.SIG_ENDCLASS);
        String str2 = "";
        if (indexOf != -1) {
            str2 = str.substring(indexOf, str.length());
            str = str.substring(0, indexOf);
        }
        try {
            return super.findClass(str);
        } catch (ClassNotFoundException e) {
            if (!this.codebaseLookup) {
                throw new ClassNotFoundException(str);
            }
            final String stringBuffer = new StringBuffer(ParseUtil.encodePath(str.replace('.', '/'), false)).append(".class").append(str2).toString();
            try {
                byte[] bArr = (byte[]) AccessController.doPrivileged(new PrivilegedExceptionAction() { // from class: sun.applet.AppletClassLoader.1
                    @Override // java.security.PrivilegedExceptionAction
                    public Object run() throws IOException {
                        try {
                            URL url = new URL(AppletClassLoader.this.base, stringBuffer);
                            if (AppletClassLoader.this.base.getProtocol().equals(url.getProtocol()) && AppletClassLoader.this.base.getHost().equals(url.getHost()) && AppletClassLoader.this.base.getPort() == url.getPort()) {
                                return AppletClassLoader.getBytes(url);
                            }
                            return null;
                        } catch (Exception e2) {
                            return null;
                        }
                    }
                }, this.acc);
                if (bArr != null) {
                    return defineClass(str, bArr, 0, bArr.length, this.codesource);
                }
                throw new ClassNotFoundException(str);
            } catch (PrivilegedActionException e2) {
                throw new ClassNotFoundException(str, e2.getException());
            }
        }
    }

    @Override // java.net.URLClassLoader, java.security.SecureClassLoader
    protected PermissionCollection getPermissions(CodeSource codeSource) {
        Permission permission;
        Permission permission2;
        int lastIndexOf;
        PermissionCollection permissions = super.getPermissions(codeSource);
        URL location = codeSource.getLocation();
        String str = null;
        try {
            permission = location.openConnection().getPermission();
        } catch (IOException e) {
            permission = null;
        }
        if (permission instanceof FilePermission) {
            str = permission.getName();
        } else if (permission == null && location.getProtocol().equals("file")) {
            str = ParseUtil.decode(location.getFile().replace('/', File.separatorChar));
        }
        if (str != null) {
            String str2 = str;
            if (!str.endsWith(File.separator) && (lastIndexOf = str.lastIndexOf(File.separatorChar)) != -1) {
                permissions.add(new FilePermission(str.substring(0, lastIndexOf + 1) + TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR, "read"));
            }
            boolean isDirectory = new File(str2).isDirectory();
            if (this.allowRecursiveDirectoryRead && (isDirectory || str2.toLowerCase().endsWith(".jar") || str2.toLowerCase().endsWith(".zip"))) {
                try {
                    permission2 = this.base.openConnection().getPermission();
                } catch (IOException e2) {
                    permission2 = null;
                }
                if (permission2 instanceof FilePermission) {
                    String name = permission2.getName();
                    if (name.endsWith(File.separator)) {
                        name = name + TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR;
                    }
                    permissions.add(new FilePermission(name, "read"));
                } else if (permission2 == null && this.base.getProtocol().equals("file")) {
                    String decode = ParseUtil.decode(this.base.getFile().replace('/', File.separatorChar));
                    if (decode.endsWith(File.separator)) {
                        decode = decode + TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR;
                    }
                    permissions.add(new FilePermission(decode, "read"));
                }
            }
        }
        return permissions;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] getBytes(URL url) throws IOException {
        URLConnection openConnection = url.openConnection();
        if ((openConnection instanceof HttpURLConnection) && ((HttpURLConnection) openConnection).getResponseCode() >= 400) {
            throw new IOException("open HTTP connection failed.");
        }
        int contentLength = openConnection.getContentLength();
        if (contentLength == -1) {
            contentLength = Integer.MAX_VALUE;
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(openConnection.getInputStream());
        try {
            byte[] readNBytesOrEOF = IOUtils.readNBytesOrEOF(bufferedInputStream, contentLength);
            bufferedInputStream.close();
            return readNBytesOrEOF;
        } catch (Throwable th) {
            bufferedInputStream.close();
            throw th;
        }
    }

    @Override // java.net.URLClassLoader, java.lang.ClassLoader
    public InputStream getResourceAsStream(String str) {
        InputStream resourceAsStream;
        if (str == null) {
            throw new NullPointerException("name");
        }
        try {
            synchronized (this.syncResourceAsStream) {
                this.resourceAsStreamInCall = true;
                resourceAsStream = super.getResourceAsStream(str);
                this.resourceAsStreamInCall = false;
            }
            if (this.codebaseLookup && resourceAsStream == null) {
                resourceAsStream = new URL(this.base, ParseUtil.encodePath(str, false)).openStream();
            }
            return resourceAsStream;
        } catch (Exception e) {
            return null;
        }
    }

    public InputStream getResourceAsStreamFromJar(String str) {
        InputStream resourceAsStream;
        if (str == null) {
            throw new NullPointerException("name");
        }
        try {
            synchronized (this.syncResourceAsStreamFromJar) {
                this.resourceAsStreamFromJarInCall = true;
                resourceAsStream = super.getResourceAsStream(str);
                this.resourceAsStreamFromJarInCall = false;
            }
            return resourceAsStream;
        } catch (Exception e) {
            return null;
        }
    }

    @Override // java.net.URLClassLoader, java.lang.ClassLoader
    public URL findResource(String str) {
        boolean z;
        boolean z2;
        URL findResource = super.findResource(str);
        if (str.startsWith("META-INF/")) {
            return findResource;
        }
        if (!this.codebaseLookup) {
            return findResource;
        }
        if (findResource == null) {
            synchronized (this.syncResourceAsStreamFromJar) {
                z = this.resourceAsStreamFromJarInCall;
            }
            if (z) {
                return null;
            }
            synchronized (this.syncResourceAsStream) {
                z2 = this.resourceAsStreamInCall;
            }
            if (!z2) {
                try {
                    findResource = new URL(this.base, ParseUtil.encodePath(str, false));
                    if (!resourceExists(findResource)) {
                        findResource = null;
                    }
                } catch (Exception e) {
                    findResource = null;
                }
            }
        }
        return findResource;
    }

    private boolean resourceExists(URL url) {
        boolean z = true;
        try {
            URLConnection openConnection = url.openConnection();
            if (openConnection instanceof HttpURLConnection) {
                HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
                httpURLConnection.setRequestMethod("HEAD");
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    return true;
                }
                if (responseCode >= 400) {
                    return false;
                }
            } else {
                openConnection.getInputStream().close();
            }
        } catch (Exception e) {
            z = false;
        }
        return z;
    }

    @Override // java.net.URLClassLoader, java.lang.ClassLoader
    public Enumeration findResources(String str) throws IOException {
        final Enumeration<URL> findResources = super.findResources(str);
        if (str.startsWith("META-INF/")) {
            return findResources;
        }
        if (!this.codebaseLookup) {
            return findResources;
        }
        URL url = new URL(this.base, ParseUtil.encodePath(str, false));
        if (!resourceExists(url)) {
            url = null;
        }
        final URL url2 = url;
        return new Enumeration() { // from class: sun.applet.AppletClassLoader.2
            private boolean done;

            @Override // java.util.Enumeration
            public Object nextElement() {
                if (!this.done) {
                    if (findResources.hasMoreElements()) {
                        return findResources.nextElement();
                    }
                    this.done = true;
                    if (url2 != null) {
                        return url2;
                    }
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return !this.done && (findResources.hasMoreElements() || url2 != null);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Class loadCode(String str) throws ClassNotFoundException {
        String replace = str.replace('/', '.').replace(File.separatorChar, '.');
        String str2 = null;
        int indexOf = replace.indexOf(RuntimeConstants.SIG_ENDCLASS);
        if (indexOf != -1) {
            str2 = replace.substring(indexOf, replace.length());
            replace = replace.substring(0, indexOf);
        }
        String str3 = replace;
        if (replace.endsWith(".class") || replace.endsWith(Constants.SOURCE_FILE_EXTENSION)) {
            replace = replace.substring(0, replace.lastIndexOf(46));
        }
        if (str2 != null) {
            try {
                replace = new StringBuffer(replace).append(str2).toString();
            } catch (ClassNotFoundException e) {
                if (str2 != null) {
                    str3 = new StringBuffer(str3).append(str2).toString();
                }
                return loadClass(str3);
            }
        }
        return loadClass(replace);
    }

    public ThreadGroup getThreadGroup() {
        AppletThreadGroup appletThreadGroup;
        synchronized (this.threadGroupSynchronizer) {
            if (this.threadGroup == null || this.threadGroup.isDestroyed()) {
                AccessController.doPrivileged(new PrivilegedAction() { // from class: sun.applet.AppletClassLoader.3
                    @Override // java.security.PrivilegedAction
                    public Object run() {
                        AppletClassLoader.this.threadGroup = new AppletThreadGroup(AppletClassLoader.this.base + "-threadGroup");
                        AppContextCreator appContextCreator = new AppContextCreator(AppletClassLoader.this.threadGroup);
                        appContextCreator.setContextClassLoader(AppletClassLoader.this);
                        appContextCreator.start();
                        try {
                            synchronized (appContextCreator.syncObject) {
                                while (!appContextCreator.created) {
                                    appContextCreator.syncObject.wait();
                                }
                            }
                        } catch (InterruptedException e) {
                        }
                        AppletClassLoader.this.appContext = appContextCreator.appContext;
                        return null;
                    }
                });
            }
            appletThreadGroup = this.threadGroup;
        }
        return appletThreadGroup;
    }

    public AppContext getAppContext() {
        return this.appContext;
    }

    public void grab() {
        synchronized (this.grabReleaseSynchronizer) {
            this.usageCount++;
        }
        getThreadGroup();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setExceptionStatus() {
        this.exceptionStatus = true;
    }

    public boolean getExceptionStatus() {
        return this.exceptionStatus;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void release() {
        AppContext appContext = null;
        synchronized (this.grabReleaseSynchronizer) {
            if (this.usageCount > 1) {
                this.usageCount--;
            } else {
                synchronized (this.threadGroupSynchronizer) {
                    appContext = resetAppContext();
                }
            }
        }
        if (appContext != null) {
            try {
                appContext.dispose();
            } catch (IllegalThreadStateException e) {
            }
        }
    }

    protected AppContext resetAppContext() {
        AppContext appContext;
        synchronized (this.threadGroupSynchronizer) {
            appContext = this.appContext;
            this.usageCount = 0;
            this.appContext = null;
            this.threadGroup = null;
        }
        return appContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setJDK11Target(Class cls, boolean z) {
        this.jdk11AppletInfo.put(cls.toString(), Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setJDK12Target(Class cls, boolean z) {
        this.jdk12AppletInfo.put(cls.toString(), Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Boolean isJDK11Target(Class cls) {
        return (Boolean) this.jdk11AppletInfo.get(cls.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Boolean isJDK12Target(Class cls) {
        return (Boolean) this.jdk12AppletInfo.get(cls.toString());
    }

    private static void printError(String str, Throwable th) {
        String str2 = null;
        if (th == null) {
            str2 = mh.getMessage("filenotfound", str);
        } else if (th instanceof IOException) {
            str2 = mh.getMessage("fileioexception", str);
        } else if (th instanceof ClassFormatError) {
            str2 = mh.getMessage("fileformat", str);
        } else if (th instanceof ThreadDeath) {
            str2 = mh.getMessage("filedeath", str);
        } else if (th instanceof Error) {
            str2 = mh.getMessage("fileerror", th.toString(), str);
        }
        if (str2 != null) {
            System.err.println(str2);
        }
    }
}
