package com.sun.tools.javadoc;

import com.sun.javadoc.SourcePosition;
import com.sun.tools.javac.util.Position;
import java.io.File;
import javax.tools.FileObject;
import jdk.internal.dynalink.CallSiteDescriptor;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/javadoc/SourcePositionImpl.class */
public class SourcePositionImpl implements SourcePosition {
    FileObject filename;
    int position;
    Position.LineMap lineMap;

    @Override // com.sun.javadoc.SourcePosition
    public File file() {
        if (this.filename == null) {
            return null;
        }
        return new File(this.filename.getName());
    }

    public FileObject fileObject() {
        return this.filename;
    }

    @Override // com.sun.javadoc.SourcePosition
    public int line() {
        if (this.lineMap == null) {
            return 0;
        }
        return this.lineMap.getLineNumber(this.position);
    }

    @Override // com.sun.javadoc.SourcePosition
    public int column() {
        if (this.lineMap == null) {
            return 0;
        }
        return this.lineMap.getColumnNumber(this.position);
    }

    private SourcePositionImpl(FileObject fileObject, int i, Position.LineMap lineMap) {
        this.filename = fileObject;
        this.position = i;
        this.lineMap = lineMap;
    }

    public static SourcePosition make(FileObject fileObject, int i, Position.LineMap lineMap) {
        if (fileObject == null) {
            return null;
        }
        return new SourcePositionImpl(fileObject, i, lineMap);
    }

    @Override // com.sun.javadoc.SourcePosition
    public String toString() {
        int lastIndexOf;
        String name = this.filename.getName();
        if (name.endsWith(RuntimeConstants.SIG_ENDMETHOD) && (lastIndexOf = name.lastIndexOf(RuntimeConstants.SIG_METHOD)) != -1) {
            name = name.substring(0, lastIndexOf) + File.separatorChar + name.substring(lastIndexOf + 1, name.length() - 1);
        }
        if (this.position == -1) {
            return name;
        }
        return name + CallSiteDescriptor.TOKEN_DELIMITER + line();
    }
}
