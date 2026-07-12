package org.relaxng.datatype.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Vector;
import org.relaxng.datatype.DatatypeLibrary;
import org.relaxng.datatype.DatatypeLibraryFactory;

/* loaded from: target.jar:org/relaxng/datatype/helpers/DatatypeLibraryLoader.class */
public class DatatypeLibraryLoader implements DatatypeLibraryFactory {
    private final Service service = new Service(DatatypeLibraryFactory.class);

    @Override // org.relaxng.datatype.DatatypeLibraryFactory
    public DatatypeLibrary createDatatypeLibrary(String uri) {
        Enumeration e = this.service.getProviders();
        while (e.hasMoreElements()) {
            DatatypeLibraryFactory factory = (DatatypeLibraryFactory) e.nextElement();
            DatatypeLibrary library = factory.createDatatypeLibrary(uri);
            if (library != null) {
                return library;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:org/relaxng/datatype/helpers/DatatypeLibraryLoader$Service.class */
    public static class Service {
        private final Class serviceClass;
        private final Enumeration configFiles;
        private Enumeration classNames = null;
        private final Vector providers = new Vector();
        private Loader loader;
        private static final int START = 0;
        private static final int IN_NAME = 1;
        private static final int IN_COMMENT = 2;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: target.jar:org/relaxng/datatype/helpers/DatatypeLibraryLoader$Service$ProviderEnumeration.class */
        public class ProviderEnumeration implements Enumeration {
            private int nextIndex;

            private ProviderEnumeration() {
                this.nextIndex = 0;
            }

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.nextIndex < Service.this.providers.size() || Service.this.moreProviders();
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                try {
                    Vector vector = Service.this.providers;
                    int i = this.nextIndex;
                    this.nextIndex = i + 1;
                    return vector.elementAt(i);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new NoSuchElementException();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: target.jar:org/relaxng/datatype/helpers/DatatypeLibraryLoader$Service$Singleton.class */
        public static class Singleton implements Enumeration {
            private Object obj;

            private Singleton(Object obj) {
                this.obj = obj;
            }

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.obj != null;
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                if (this.obj == null) {
                    throw new NoSuchElementException();
                }
                Object tem = this.obj;
                this.obj = null;
                return tem;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: target.jar:org/relaxng/datatype/helpers/DatatypeLibraryLoader$Service$Loader.class */
        public static class Loader {
            private Loader() {
            }

            Enumeration getResources(String resName) {
                URL url;
                ClassLoader cl = Loader.class.getClassLoader();
                if (cl == null) {
                    url = ClassLoader.getSystemResource(resName);
                } else {
                    url = cl.getResource(resName);
                }
                return new Singleton(url);
            }

            Class loadClass(String name) throws ClassNotFoundException {
                return Class.forName(name);
            }
        }

        /* loaded from: target.jar:org/relaxng/datatype/helpers/DatatypeLibraryLoader$Service$Loader2.class */
        private static class Loader2 extends Loader {
            private ClassLoader cl;

            Loader2() {
                super();
                this.cl = Loader2.class.getClassLoader();
                ClassLoader clt = Thread.currentThread().getContextClassLoader();
                ClassLoader classLoader = clt;
                while (true) {
                    ClassLoader tem = classLoader;
                    if (tem != null) {
                        if (tem != this.cl) {
                            classLoader = tem.getParent();
                        } else {
                            this.cl = clt;
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }

            @Override // org.relaxng.datatype.helpers.DatatypeLibraryLoader.Service.Loader
            Enumeration getResources(String resName) {
                try {
                    return this.cl.getResources(resName);
                } catch (IOException e) {
                    return new Singleton(null);
                }
            }

            @Override // org.relaxng.datatype.helpers.DatatypeLibraryLoader.Service.Loader
            Class loadClass(String name) throws ClassNotFoundException {
                return Class.forName(name, true, this.cl);
            }
        }

        public Service(Class cls) {
            try {
                this.loader = new Loader2();
            } catch (NoSuchMethodError e) {
                this.loader = new Loader();
            }
            this.serviceClass = cls;
            String resName = "META-INF/services/" + this.serviceClass.getName();
            this.configFiles = this.loader.getResources(resName);
        }

        public Enumeration getProviders() {
            return new ProviderEnumeration();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized boolean moreProviders() {
            Object obj;
            while (true) {
                if (this.classNames != null) {
                    while (this.classNames.hasMoreElements()) {
                        String className = (String) this.classNames.nextElement();
                        try {
                            Class cls = this.loader.loadClass(className);
                            obj = cls.newInstance();
                        } catch (ClassNotFoundException e) {
                        } catch (IllegalAccessException e2) {
                        } catch (InstantiationException e3) {
                        } catch (LinkageError e4) {
                        }
                        if (this.serviceClass.isInstance(obj)) {
                            this.providers.addElement(obj);
                            return true;
                        }
                        continue;
                    }
                    this.classNames = null;
                } else {
                    if (!this.configFiles.hasMoreElements()) {
                        return false;
                    }
                    this.classNames = parseConfigFile((URL) this.configFiles.nextElement());
                }
            }
        }

        private static Enumeration parseConfigFile(URL url) {
            Reader r;
            try {
                InputStream in = url.openStream();
                try {
                    r = new InputStreamReader(in, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    r = new InputStreamReader(in, "UTF8");
                }
                Reader r2 = new BufferedReader(r);
                Vector tokens = new Vector();
                StringBuffer tokenBuf = new StringBuffer();
                int state = 0;
                while (true) {
                    int n = r2.read();
                    if (n >= 0) {
                        char c = (char) n;
                        switch (c) {
                            case '\t':
                            case ' ':
                                break;
                            case '\n':
                            case '\r':
                                state = 0;
                                break;
                            case '#':
                                state = 2;
                                break;
                            default:
                                if (state != 2) {
                                    state = 1;
                                    tokenBuf.append(c);
                                    break;
                                }
                                break;
                        }
                        if (tokenBuf.length() != 0 && state != 1) {
                            tokens.addElement(tokenBuf.toString());
                            tokenBuf.setLength(0);
                        }
                    } else {
                        if (tokenBuf.length() != 0) {
                            tokens.addElement(tokenBuf.toString());
                        }
                        return tokens.elements();
                    }
                }
            } catch (IOException e2) {
                return null;
            }
        }
    }
}
