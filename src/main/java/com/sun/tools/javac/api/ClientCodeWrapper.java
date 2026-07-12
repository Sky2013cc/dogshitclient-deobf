package com.sun.tools.javac.api;

import com.sun.source.util.TaskEvent;
import com.sun.source.util.TaskListener;
import com.sun.tools.javac.util.ClientCodeException;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/javac/api/ClientCodeWrapper.class */
public class ClientCodeWrapper {
    Map<Class<?>, Boolean> trustedClasses = new HashMap();

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: target.jar:com/sun/tools/javac/api/ClientCodeWrapper$Trusted.class */
    public @interface Trusted {
    }

    public static ClientCodeWrapper instance(Context context) {
        ClientCodeWrapper clientCodeWrapper = (ClientCodeWrapper) context.get(ClientCodeWrapper.class);
        if (clientCodeWrapper == null) {
            clientCodeWrapper = new ClientCodeWrapper(context);
        }
        return clientCodeWrapper;
    }

    protected ClientCodeWrapper(Context context) {
    }

    public JavaFileManager wrap(JavaFileManager javaFileManager) {
        if (isTrusted(javaFileManager)) {
            return javaFileManager;
        }
        return new WrappedJavaFileManager(javaFileManager);
    }

    public FileObject wrap(FileObject fileObject) {
        if (isTrusted(fileObject)) {
            return fileObject;
        }
        return new WrappedFileObject(fileObject);
    }

    FileObject unwrap(FileObject fileObject) {
        if (fileObject instanceof WrappedFileObject) {
            return ((WrappedFileObject) fileObject).clientFileObject;
        }
        return fileObject;
    }

    public JavaFileObject wrap(JavaFileObject javaFileObject) {
        if (isTrusted(javaFileObject)) {
            return javaFileObject;
        }
        return new WrappedJavaFileObject(javaFileObject);
    }

    public Iterable<JavaFileObject> wrapJavaFileObjects(Iterable<? extends JavaFileObject> iterable) {
        ArrayList arrayList = new ArrayList();
        Iterator<? extends JavaFileObject> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(wrap(it.next()));
        }
        return Collections.unmodifiableList(arrayList);
    }

    JavaFileObject unwrap(JavaFileObject javaFileObject) {
        if (javaFileObject instanceof WrappedJavaFileObject) {
            return ((WrappedJavaFileObject) javaFileObject).clientFileObject;
        }
        return javaFileObject;
    }

    public <T> DiagnosticListener<T> wrap(DiagnosticListener<T> diagnosticListener) {
        if (isTrusted(diagnosticListener)) {
            return diagnosticListener;
        }
        return new WrappedDiagnosticListener(diagnosticListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TaskListener wrap(TaskListener taskListener) {
        if (isTrusted(taskListener)) {
            return taskListener;
        }
        return new WrappedTaskListener(taskListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TaskListener unwrap(TaskListener taskListener) {
        if (taskListener instanceof WrappedTaskListener) {
            return ((WrappedTaskListener) taskListener).clientTaskListener;
        }
        return taskListener;
    }

    Collection<TaskListener> unwrap(Collection<? extends TaskListener> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator<? extends TaskListener> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(unwrap(it.next()));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> Diagnostic<T> unwrap(Diagnostic<T> diagnostic) {
        if (diagnostic instanceof JCDiagnostic) {
            return new DiagnosticSourceUnwrapper((JCDiagnostic) diagnostic);
        }
        return diagnostic;
    }

    protected boolean isTrusted(Object obj) {
        Class<?> cls = obj.getClass();
        Boolean bool = this.trustedClasses.get(cls);
        if (bool == null) {
            bool = Boolean.valueOf(cls.getName().startsWith("com.sun.tools.javac.") || cls.isAnnotationPresent(Trusted.class));
            this.trustedClasses.put(cls, bool);
        }
        return bool.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String wrappedToString(Class<?> cls, Object obj) {
        return cls.getSimpleName() + RuntimeConstants.SIG_ARRAY + obj + "]";
    }

    /* loaded from: target.jar:com/sun/tools/javac/api/ClientCodeWrapper$WrappedJavaFileManager.class */
    protected class WrappedJavaFileManager implements JavaFileManager {
        protected JavaFileManager clientJavaFileManager;

        WrappedJavaFileManager(JavaFileManager javaFileManager) {
            javaFileManager.getClass();
            this.clientJavaFileManager = javaFileManager;
        }

        public ClassLoader getClassLoader(JavaFileManager.Location location) {
            try {
                return this.clientJavaFileManager.getClassLoader(location);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public Iterable<JavaFileObject> list(JavaFileManager.Location location, String str, Set<JavaFileObject.Kind> set, boolean z) throws IOException {
            try {
                return ClientCodeWrapper.this.wrapJavaFileObjects(this.clientJavaFileManager.list(location, str, set, z));
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public String inferBinaryName(JavaFileManager.Location location, JavaFileObject javaFileObject) {
            try {
                return this.clientJavaFileManager.inferBinaryName(location, ClientCodeWrapper.this.unwrap(javaFileObject));
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public boolean isSameFile(FileObject fileObject, FileObject fileObject2) {
            try {
                return this.clientJavaFileManager.isSameFile(ClientCodeWrapper.this.unwrap(fileObject), ClientCodeWrapper.this.unwrap(fileObject2));
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public boolean handleOption(String str, Iterator<String> it) {
            try {
                return this.clientJavaFileManager.handleOption(str, it);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public boolean hasLocation(JavaFileManager.Location location) {
            try {
                return this.clientJavaFileManager.hasLocation(location);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public JavaFileObject getJavaFileForInput(JavaFileManager.Location location, String str, JavaFileObject.Kind kind) throws IOException {
            try {
                return ClientCodeWrapper.this.wrap(this.clientJavaFileManager.getJavaFileForInput(location, str, kind));
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public JavaFileObject getJavaFileForOutput(JavaFileManager.Location location, String str, JavaFileObject.Kind kind, FileObject fileObject) throws IOException {
            try {
                return ClientCodeWrapper.this.wrap(this.clientJavaFileManager.getJavaFileForOutput(location, str, kind, ClientCodeWrapper.this.unwrap(fileObject)));
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public FileObject getFileForInput(JavaFileManager.Location location, String str, String str2) throws IOException {
            try {
                return ClientCodeWrapper.this.wrap(this.clientJavaFileManager.getFileForInput(location, str, str2));
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public FileObject getFileForOutput(JavaFileManager.Location location, String str, String str2, FileObject fileObject) throws IOException {
            try {
                return ClientCodeWrapper.this.wrap(this.clientJavaFileManager.getFileForOutput(location, str, str2, ClientCodeWrapper.this.unwrap(fileObject)));
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public void flush() throws IOException {
            try {
                this.clientJavaFileManager.flush();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public void close() throws IOException {
            try {
                this.clientJavaFileManager.close();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public int isSupportedOption(String str) {
            try {
                return this.clientJavaFileManager.isSupportedOption(str);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public String toString() {
            return ClientCodeWrapper.this.wrappedToString(getClass(), this.clientJavaFileManager);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: target.jar:com/sun/tools/javac/api/ClientCodeWrapper$WrappedFileObject.class */
    public class WrappedFileObject implements FileObject {
        protected FileObject clientFileObject;

        WrappedFileObject(FileObject fileObject) {
            fileObject.getClass();
            this.clientFileObject = fileObject;
        }

        public URI toUri() {
            try {
                return this.clientFileObject.toUri();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public String getName() {
            try {
                return this.clientFileObject.getName();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public InputStream openInputStream() throws IOException {
            try {
                return this.clientFileObject.openInputStream();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public OutputStream openOutputStream() throws IOException {
            try {
                return this.clientFileObject.openOutputStream();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public Reader openReader(boolean z) throws IOException {
            try {
                return this.clientFileObject.openReader(z);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public CharSequence getCharContent(boolean z) throws IOException {
            try {
                return this.clientFileObject.getCharContent(z);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public Writer openWriter() throws IOException {
            try {
                return this.clientFileObject.openWriter();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public long getLastModified() {
            try {
                return this.clientFileObject.getLastModified();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public boolean delete() {
            try {
                return this.clientFileObject.delete();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public String toString() {
            return ClientCodeWrapper.this.wrappedToString(getClass(), this.clientFileObject);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: target.jar:com/sun/tools/javac/api/ClientCodeWrapper$WrappedJavaFileObject.class */
    public class WrappedJavaFileObject extends WrappedFileObject implements JavaFileObject {
        WrappedJavaFileObject(JavaFileObject javaFileObject) {
            super(javaFileObject);
        }

        public JavaFileObject.Kind getKind() {
            try {
                return this.clientFileObject.getKind();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public boolean isNameCompatible(String str, JavaFileObject.Kind kind) {
            try {
                return this.clientFileObject.isNameCompatible(str, kind);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public NestingKind getNestingKind() {
            try {
                return this.clientFileObject.getNestingKind();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public Modifier getAccessLevel() {
            try {
                return this.clientFileObject.getAccessLevel();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        @Override // com.sun.tools.javac.api.ClientCodeWrapper.WrappedFileObject
        public String toString() {
            return ClientCodeWrapper.this.wrappedToString(getClass(), this.clientFileObject);
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/api/ClientCodeWrapper$WrappedDiagnosticListener.class */
    protected class WrappedDiagnosticListener<T> implements DiagnosticListener<T> {
        protected DiagnosticListener<T> clientDiagnosticListener;

        WrappedDiagnosticListener(DiagnosticListener<T> diagnosticListener) {
            diagnosticListener.getClass();
            this.clientDiagnosticListener = diagnosticListener;
        }

        public void report(Diagnostic<? extends T> diagnostic) {
            try {
                this.clientDiagnosticListener.report(ClientCodeWrapper.this.unwrap(diagnostic));
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public String toString() {
            return ClientCodeWrapper.this.wrappedToString(getClass(), this.clientDiagnosticListener);
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/api/ClientCodeWrapper$DiagnosticSourceUnwrapper.class */
    public class DiagnosticSourceUnwrapper implements Diagnostic<JavaFileObject> {
        public final JCDiagnostic d;

        DiagnosticSourceUnwrapper(JCDiagnostic jCDiagnostic) {
            this.d = jCDiagnostic;
        }

        public Diagnostic.Kind getKind() {
            return this.d.getKind();
        }

        /* renamed from: getSource, reason: merged with bridge method [inline-methods] */
        public JavaFileObject m399getSource() {
            return ClientCodeWrapper.this.unwrap(this.d.mo615getSource());
        }

        public long getPosition() {
            return this.d.getPosition();
        }

        public long getStartPosition() {
            return this.d.getStartPosition();
        }

        public long getEndPosition() {
            return this.d.getEndPosition();
        }

        public long getLineNumber() {
            return this.d.getLineNumber();
        }

        public long getColumnNumber() {
            return this.d.getColumnNumber();
        }

        public String getCode() {
            return this.d.getCode();
        }

        public String getMessage(Locale locale) {
            return this.d.getMessage(locale);
        }

        public String toString() {
            return this.d.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: target.jar:com/sun/tools/javac/api/ClientCodeWrapper$WrappedTaskListener.class */
    public class WrappedTaskListener implements TaskListener {
        protected TaskListener clientTaskListener;

        WrappedTaskListener(TaskListener taskListener) {
            taskListener.getClass();
            this.clientTaskListener = taskListener;
        }

        @Override // com.sun.source.util.TaskListener
        public void started(TaskEvent taskEvent) {
            try {
                this.clientTaskListener.started(taskEvent);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        @Override // com.sun.source.util.TaskListener
        public void finished(TaskEvent taskEvent) {
            try {
                this.clientTaskListener.finished(taskEvent);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error e2) {
                throw new ClientCodeException(e2);
            } catch (RuntimeException e3) {
                throw new ClientCodeException(e3);
            }
        }

        public String toString() {
            return ClientCodeWrapper.this.wrappedToString(getClass(), this.clientTaskListener);
        }
    }
}
