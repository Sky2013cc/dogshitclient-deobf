package com.sun.tools.internal.xjc.api.util;

import com.sun.codemodel.internal.CodeWriter;
import com.sun.codemodel.internal.JPackage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.tools.StandardLocation;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/tools/internal/xjc/api/util/FilerCodeWriter.class */
public final class FilerCodeWriter extends CodeWriter {
    private final Filer filer;

    public FilerCodeWriter(Filer filer) {
        this.filer = filer;
    }

    @Override // com.sun.codemodel.internal.CodeWriter
    public OutputStream openBinary(JPackage pkg, String fileName) throws IOException {
        StandardLocation loc;
        if (fileName.endsWith(Constants.SOURCE_FILE_EXTENSION)) {
            loc = StandardLocation.SOURCE_PATH;
        } else {
            loc = StandardLocation.CLASS_PATH;
        }
        return this.filer.createResource(loc, pkg.name(), fileName, new Element[0]).openOutputStream();
    }

    @Override // com.sun.codemodel.internal.CodeWriter
    public Writer openSource(JPackage pkg, String fileName) throws IOException {
        String name;
        if (pkg.isUnnamed()) {
            name = fileName;
        } else {
            name = pkg.name() + '.' + fileName;
        }
        return this.filer.createSourceFile(name.substring(0, name.length() - 5), new Element[0]).openWriter();
    }

    @Override // com.sun.codemodel.internal.CodeWriter
    public void close() {
    }
}
