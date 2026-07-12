package com.sun.tools.javac.api;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;

/* loaded from: target.jar:com/sun/tools/javac/api/WrappingJavaFileManager.class */
public class WrappingJavaFileManager<M extends JavaFileManager> extends ForwardingJavaFileManager<M> {
    protected WrappingJavaFileManager(M m) {
        super(m);
    }

    protected FileObject wrap(FileObject fileObject) {
        return fileObject;
    }

    protected JavaFileObject wrap(JavaFileObject javaFileObject) {
        return wrap((FileObject) javaFileObject);
    }

    protected FileObject unwrap(FileObject fileObject) {
        return fileObject;
    }

    protected JavaFileObject unwrap(JavaFileObject javaFileObject) {
        return unwrap((FileObject) javaFileObject);
    }

    protected Iterable<JavaFileObject> wrap(Iterable<JavaFileObject> iterable) {
        ArrayList arrayList = new ArrayList();
        Iterator<JavaFileObject> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(wrap(it.next()));
        }
        return Collections.unmodifiableList(arrayList);
    }

    protected URI unwrap(URI uri) {
        return uri;
    }

    public Iterable<JavaFileObject> list(JavaFileManager.Location location, String str, Set<JavaFileObject.Kind> set, boolean z) throws IOException {
        return wrap(super.list(location, str, set, z));
    }

    public String inferBinaryName(JavaFileManager.Location location, JavaFileObject javaFileObject) {
        return super.inferBinaryName(location, unwrap(javaFileObject));
    }

    public JavaFileObject getJavaFileForInput(JavaFileManager.Location location, String str, JavaFileObject.Kind kind) throws IOException {
        return wrap(super.getJavaFileForInput(location, str, kind));
    }

    public JavaFileObject getJavaFileForOutput(JavaFileManager.Location location, String str, JavaFileObject.Kind kind, FileObject fileObject) throws IOException {
        return wrap(super.getJavaFileForOutput(location, str, kind, unwrap(fileObject)));
    }

    public FileObject getFileForInput(JavaFileManager.Location location, String str, String str2) throws IOException {
        return wrap(super.getFileForInput(location, str, str2));
    }

    public FileObject getFileForOutput(JavaFileManager.Location location, String str, String str2, FileObject fileObject) throws IOException {
        return wrap(super.getFileForOutput(location, str, str2, unwrap(fileObject)));
    }
}
