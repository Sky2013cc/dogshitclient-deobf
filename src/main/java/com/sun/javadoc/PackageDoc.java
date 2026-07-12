package com.sun.javadoc;

/* loaded from: target.jar:com/sun/javadoc/PackageDoc.class */
public interface PackageDoc extends Doc {
    ClassDoc[] allClasses(boolean z);

    ClassDoc[] allClasses();

    ClassDoc[] ordinaryClasses();

    ClassDoc[] exceptions();

    ClassDoc[] errors();

    ClassDoc[] enums();

    ClassDoc[] interfaces();

    AnnotationTypeDoc[] annotationTypes();

    AnnotationDesc[] annotations();

    ClassDoc findClass(String str);
}
