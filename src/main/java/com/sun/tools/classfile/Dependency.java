package com.sun.tools.classfile;

/* loaded from: target.jar:com/sun/tools/classfile/Dependency.class */
public interface Dependency {

    /* loaded from: target.jar:com/sun/tools/classfile/Dependency$Filter.class */
    public interface Filter {
        boolean accepts(Dependency dependency);
    }

    /* loaded from: target.jar:com/sun/tools/classfile/Dependency$Finder.class */
    public interface Finder {
        Iterable<? extends Dependency> findDependencies(ClassFile classFile);
    }

    /* loaded from: target.jar:com/sun/tools/classfile/Dependency$Location.class */
    public interface Location {
        String getName();

        String getClassName();

        String getPackageName();
    }

    Location getOrigin();

    Location getTarget();
}
