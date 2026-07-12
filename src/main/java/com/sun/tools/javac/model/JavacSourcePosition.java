package com.sun.tools.javac.model;

import com.sun.tools.javac.util.Position;
import javax.tools.JavaFileObject;
import jdk.internal.dynalink.CallSiteDescriptor;

/* loaded from: target.jar:com/sun/tools/javac/model/JavacSourcePosition.class */
class JavacSourcePosition {
    final JavaFileObject sourcefile;
    final int pos;
    final Position.LineMap lineMap;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JavacSourcePosition(JavaFileObject javaFileObject, int i, Position.LineMap lineMap) {
        this.sourcefile = javaFileObject;
        this.pos = i;
        this.lineMap = i != -1 ? lineMap : null;
    }

    public JavaFileObject getFile() {
        return this.sourcefile;
    }

    public int getOffset() {
        return this.pos;
    }

    public int getLine() {
        if (this.lineMap != null) {
            return this.lineMap.getLineNumber(this.pos);
        }
        return -1;
    }

    public int getColumn() {
        if (this.lineMap != null) {
            return this.lineMap.getColumnNumber(this.pos);
        }
        return -1;
    }

    public String toString() {
        int line = getLine();
        return line > 0 ? this.sourcefile + CallSiteDescriptor.TOKEN_DELIMITER + line : this.sourcefile.toString();
    }
}
