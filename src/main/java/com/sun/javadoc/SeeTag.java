package com.sun.javadoc;

/* loaded from: target.jar:com/sun/javadoc/SeeTag.class */
public interface SeeTag extends Tag {
    String label();

    PackageDoc referencedPackage();

    String referencedClassName();

    ClassDoc referencedClass();

    String referencedMemberName();

    MemberDoc referencedMember();
}
