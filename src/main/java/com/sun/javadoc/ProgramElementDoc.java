package com.sun.javadoc;

/* loaded from: target.jar:com/sun/javadoc/ProgramElementDoc.class */
public interface ProgramElementDoc extends Doc {
    ClassDoc containingClass();

    PackageDoc containingPackage();

    String qualifiedName();

    int modifierSpecifier();

    String modifiers();

    AnnotationDesc[] annotations();

    boolean isPublic();

    boolean isProtected();

    boolean isPrivate();

    boolean isPackagePrivate();

    boolean isStatic();

    boolean isFinal();
}
