package com.sun.javadoc;

/* loaded from: target.jar:com/sun/javadoc/RootDoc.class */
public interface RootDoc extends Doc, DocErrorReporter {
    String[][] options();

    PackageDoc[] specifiedPackages();

    ClassDoc[] specifiedClasses();

    ClassDoc[] classes();

    PackageDoc packageNamed(String str);

    ClassDoc classNamed(String str);
}
