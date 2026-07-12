package com.sun.tools.javac.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.ServiceConfigurationError;
import jdk.internal.dynalink.CallSiteDescriptor;

/* loaded from: target.jar:com/sun/tools/javac/util/ServiceLoader.class */
public final class ServiceLoader<S> implements Iterable<S> {
    private static final String PREFIX = "META-INF/services/";
    private Class<S> service;
    private ClassLoader loader;
    private LinkedHashMap<String, S> providers = new LinkedHashMap<>();
    private ServiceLoader<S>.LazyIterator lookupIterator;

    public void reload() {
        this.providers.clear();
        this.lookupIterator = new LazyIterator(this.service, this.loader);
    }

    private ServiceLoader(Class<S> cls, ClassLoader classLoader) {
        this.service = (Class) Objects.requireNonNull(cls, "Service interface cannot be null");
        this.loader = classLoader == null ? ClassLoader.getSystemClassLoader() : classLoader;
        reload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void fail(Class<?> cls, String str, Throwable th) throws ServiceConfigurationError {
        throw new ServiceConfigurationError(cls.getName() + ": " + str, th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void fail(Class<?> cls, String str) throws ServiceConfigurationError {
        throw new ServiceConfigurationError(cls.getName() + ": " + str);
    }

    private static void fail(Class<?> cls, URL url, int i, String str) throws ServiceConfigurationError {
        fail(cls, url + CallSiteDescriptor.TOKEN_DELIMITER + i + ": " + str);
    }

    private int parseLine(Class<?> cls, URL url, BufferedReader bufferedReader, int i, java.util.List<String> list) throws IOException, ServiceConfigurationError {
        String readLine = bufferedReader.readLine();
        if (readLine == null) {
            return -1;
        }
        int indexOf = readLine.indexOf(35);
        if (indexOf >= 0) {
            readLine = readLine.substring(0, indexOf);
        }
        String trim = readLine.trim();
        int length = trim.length();
        if (length != 0) {
            if (trim.indexOf(32) >= 0 || trim.indexOf(9) >= 0) {
                fail(cls, url, i, "Illegal configuration-file syntax");
            }
            int codePointAt = trim.codePointAt(0);
            if (!Character.isJavaIdentifierStart(codePointAt)) {
                fail(cls, url, i, "Illegal provider-class name: " + trim);
            }
            int charCount = Character.charCount(codePointAt);
            while (true) {
                int i2 = charCount;
                if (i2 >= length) {
                    break;
                }
                int codePointAt2 = trim.codePointAt(i2);
                if (!Character.isJavaIdentifierPart(codePointAt2) && codePointAt2 != 46) {
                    fail(cls, url, i, "Illegal provider-class name: " + trim);
                }
                charCount = i2 + Character.charCount(codePointAt2);
            }
            if (!this.providers.containsKey(trim) && !list.contains(trim)) {
                list.add(trim);
            }
        }
        return i + 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Iterator<String> parse(Class<?> cls, URL url) throws ServiceConfigurationError {
        int parseLine;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        ArrayList arrayList = new ArrayList();
        try {
            try {
                URLConnection openConnection = url.openConnection();
                openConnection.setUseCaches(false);
                inputStream = openConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                int i = 1;
                do {
                    parseLine = parseLine(cls, url, bufferedReader, i, arrayList);
                    i = parseLine;
                } while (parseLine >= 0);
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        fail(cls, "Error closing configuration file", e);
                    }
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Throwable th) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        fail(cls, "Error closing configuration file", e2);
                        throw th;
                    }
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (IOException e3) {
            fail(cls, "Error reading configuration file", e3);
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e4) {
                    fail(cls, "Error closing configuration file", e4);
                }
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return arrayList.iterator();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/sun/tools/javac/util/ServiceLoader$LazyIterator.class */
    public class LazyIterator implements Iterator<S> {
        Class<S> service;
        ClassLoader loader;
        Enumeration<URL> configs;
        Iterator<String> pending;
        String nextName;

        private LazyIterator(Class<S> cls, ClassLoader classLoader) {
            this.configs = null;
            this.pending = null;
            this.nextName = null;
            this.service = cls;
            this.loader = classLoader;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.nextName != null) {
                return true;
            }
            if (this.configs == null) {
                try {
                    String str = ServiceLoader.PREFIX + this.service.getName();
                    if (this.loader == null) {
                        this.configs = ClassLoader.getSystemResources(str);
                    } else {
                        this.configs = this.loader.getResources(str);
                    }
                } catch (IOException e) {
                    ServiceLoader.fail(this.service, "Error locating configuration files", e);
                }
            }
            while (true) {
                if (this.pending == null || !this.pending.hasNext()) {
                    if (!this.configs.hasMoreElements()) {
                        return false;
                    }
                    this.pending = ServiceLoader.this.parse(this.service, this.configs.nextElement());
                } else {
                    this.nextName = this.pending.next();
                    return true;
                }
            }
        }

        @Override // java.util.Iterator
        public S next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            String str = this.nextName;
            this.nextName = null;
            Class<?> cls = null;
            try {
                cls = Class.forName(str, false, this.loader);
            } catch (ClassNotFoundException e) {
                ServiceLoader.fail(this.service, "Provider " + str + " not found");
            }
            if (!this.service.isAssignableFrom(cls)) {
                ServiceLoader.fail(this.service, "Provider " + str + " not a subtype");
            }
            try {
                S cast = this.service.cast(cls.newInstance());
                ServiceLoader.this.providers.put(str, cast);
                return cast;
            } catch (Throwable th) {
                ServiceLoader.fail(this.service, "Provider " + str + " could not be instantiated: " + th, th);
                throw new Error();
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.lang.Iterable
    public Iterator<S> iterator() {
        return new Iterator<S>() { // from class: com.sun.tools.javac.util.ServiceLoader.1
            Iterator<Map.Entry<String, S>> knownProviders;

            {
                this.knownProviders = ServiceLoader.this.providers.entrySet().iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (!this.knownProviders.hasNext()) {
                    return ServiceLoader.this.lookupIterator.hasNext();
                }
                return true;
            }

            @Override // java.util.Iterator
            public S next() {
                if (!this.knownProviders.hasNext()) {
                    return (S) ServiceLoader.this.lookupIterator.next();
                }
                return this.knownProviders.next().getValue();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static <S> ServiceLoader<S> load(Class<S> cls, ClassLoader classLoader) {
        return new ServiceLoader<>(cls, classLoader);
    }

    public static <S> ServiceLoader<S> load(Class<S> cls) {
        return load(cls, Thread.currentThread().getContextClassLoader());
    }

    public static <S> ServiceLoader<S> loadInstalled(Class<S> cls) {
        ClassLoader classLoader = null;
        for (ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader(); systemClassLoader != null; systemClassLoader = systemClassLoader.getParent()) {
            classLoader = systemClassLoader;
        }
        return load(cls, classLoader);
    }

    public String toString() {
        return "java.util.ServiceLoader[" + this.service.getName() + "]";
    }
}
