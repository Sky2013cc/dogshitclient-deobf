package com.sun.tools.javadoc.api;

import com.sun.tools.javac.util.ClientCodeException;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javadoc.Start;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.tools.DocumentationTool;
import javax.tools.JavaFileObject;

/* loaded from: target.jar:com/sun/tools/javadoc/api/JavadocTaskImpl.class */
public class JavadocTaskImpl implements DocumentationTool.DocumentationTask {
    private final AtomicBoolean used = new AtomicBoolean();
    private final Context context;
    private Class<?> docletClass;
    private Iterable<String> options;
    private Iterable<? extends JavaFileObject> fileObjects;
    private Locale locale;

    public JavadocTaskImpl(Context context, Class<?> cls, Iterable<String> iterable, Iterable<? extends JavaFileObject> iterable2) {
        this.context = context;
        this.docletClass = cls;
        this.options = iterable == null ? Collections.emptySet() : nullCheck(iterable);
        this.fileObjects = iterable2 == null ? Collections.emptySet() : nullCheck(iterable2);
        setLocale(Locale.getDefault());
    }

    public void setLocale(Locale locale) {
        if (this.used.get()) {
            throw new IllegalStateException();
        }
        this.locale = locale;
    }

    /* renamed from: call, reason: merged with bridge method [inline-methods] */
    public Boolean m646call() {
        if (!this.used.getAndSet(true)) {
            initContext();
            try {
                return Boolean.valueOf(new Start(this.context).begin(this.docletClass, this.options, this.fileObjects));
            } catch (ClientCodeException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        throw new IllegalStateException("multiple calls to method 'call'");
    }

    private void initContext() {
        this.context.put((Class<Class>) Locale.class, (Class) this.locale);
    }

    private static <T> Iterable<T> nullCheck(Iterable<T> iterable) {
        Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                throw new NullPointerException();
            }
        }
        return iterable;
    }
}
