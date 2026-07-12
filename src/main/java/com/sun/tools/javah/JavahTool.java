package com.sun.tools.javah;

import com.sun.tools.javah.JavahTask;
import com.sun.tools.javah.NativeHeaderTool;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Set;
import javax.lang.model.SourceVersion;
import javax.tools.DiagnosticListener;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;

/* loaded from: target.jar:com/sun/tools/javah/JavahTool.class */
public class JavahTool implements NativeHeaderTool {
    @Override // com.sun.tools.javah.NativeHeaderTool
    public NativeHeaderTool.NativeHeaderTask getTask(Writer writer, JavaFileManager javaFileManager, DiagnosticListener<? super JavaFileObject> diagnosticListener, Iterable<String> iterable, Iterable<String> iterable2) {
        return new JavahTask(writer, javaFileManager, diagnosticListener, iterable, iterable2);
    }

    @Override // com.sun.tools.javah.NativeHeaderTool
    public StandardJavaFileManager getStandardFileManager(DiagnosticListener<? super JavaFileObject> diagnosticListener, Locale locale, Charset charset) {
        return JavahTask.getDefaultFileManager(diagnosticListener, null);
    }

    public int run(InputStream inputStream, OutputStream outputStream, OutputStream outputStream2, String... strArr) {
        return new JavahTask(JavahTask.getPrintWriterForStream(outputStream), null, null, Arrays.asList(strArr), null).run() ? 0 : 1;
    }

    public Set<SourceVersion> getSourceVersions() {
        return EnumSet.allOf(SourceVersion.class);
    }

    public int isSupportedOption(String str) {
        JavahTask.Option[] optionArr = JavahTask.recognizedOptions;
        for (int i = 0; i < optionArr.length; i++) {
            if (optionArr[i].matches(str)) {
                return optionArr[i].hasArg ? 1 : 0;
            }
        }
        return -1;
    }
}
