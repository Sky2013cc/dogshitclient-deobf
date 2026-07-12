package com.sun.tools.hat.internal.model;

/* loaded from: target.jar:com/sun/tools/hat/internal/model/JavaHeapObjectVisitor.class */
public interface JavaHeapObjectVisitor {
    void visit(JavaHeapObject javaHeapObject);

    boolean exclude(JavaClass javaClass, JavaField javaField);

    boolean mightExclude();
}
