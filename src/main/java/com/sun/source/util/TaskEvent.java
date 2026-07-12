package com.sun.source.util;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.tools.doclint.DocLint;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/util/TaskEvent.class */
public final class TaskEvent {
    private Kind kind;
    private JavaFileObject file;
    private CompilationUnitTree unit;
    private TypeElement clazz;

    @Exported
    /* loaded from: target.jar:com/sun/source/util/TaskEvent$Kind.class */
    public enum Kind {
        PARSE,
        ENTER,
        ANALYZE,
        GENERATE,
        ANNOTATION_PROCESSING,
        ANNOTATION_PROCESSING_ROUND
    }

    public TaskEvent(Kind kind) {
        this(kind, null, null, null);
    }

    public TaskEvent(Kind kind, JavaFileObject javaFileObject) {
        this(kind, javaFileObject, null, null);
    }

    public TaskEvent(Kind kind, CompilationUnitTree compilationUnitTree) {
        this(kind, compilationUnitTree.getSourceFile(), compilationUnitTree, null);
    }

    public TaskEvent(Kind kind, CompilationUnitTree compilationUnitTree, TypeElement typeElement) {
        this(kind, compilationUnitTree.getSourceFile(), compilationUnitTree, typeElement);
    }

    private TaskEvent(Kind kind, JavaFileObject javaFileObject, CompilationUnitTree compilationUnitTree, TypeElement typeElement) {
        this.kind = kind;
        this.file = javaFileObject;
        this.unit = compilationUnitTree;
        this.clazz = typeElement;
    }

    public Kind getKind() {
        return this.kind;
    }

    public JavaFileObject getSourceFile() {
        return this.file;
    }

    public CompilationUnitTree getCompilationUnit() {
        return this.unit;
    }

    public TypeElement getTypeElement() {
        return this.clazz;
    }

    public String toString() {
        return "TaskEvent[" + this.kind + DocLint.TAGS_SEPARATOR + this.file + DocLint.TAGS_SEPARATOR + this.clazz + "]";
    }
}
