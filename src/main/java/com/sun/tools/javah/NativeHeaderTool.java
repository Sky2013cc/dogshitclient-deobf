package com.sun.tools.javah;

import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.concurrent.Callable;
import javax.tools.DiagnosticListener;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.OptionChecker;
import javax.tools.StandardJavaFileManager;
import javax.tools.Tool;

/* loaded from: target.jar:com/sun/tools/javah/NativeHeaderTool.class */
public interface NativeHeaderTool extends Tool, OptionChecker {

    /* loaded from: target.jar:com/sun/tools/javah/NativeHeaderTool$NativeHeaderTask.class */
    public interface NativeHeaderTask extends Callable<Boolean> {
        void setLocale(Locale locale);

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        Boolean call();
    }

    NativeHeaderTask getTask(Writer writer, JavaFileManager javaFileManager, DiagnosticListener<? super JavaFileObject> diagnosticListener, Iterable<String> iterable, Iterable<String> iterable2);

    StandardJavaFileManager getStandardFileManager(DiagnosticListener<? super JavaFileObject> diagnosticListener, Locale locale, Charset charset);
}
