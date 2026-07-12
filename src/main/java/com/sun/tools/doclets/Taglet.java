package com.sun.tools.doclets;

import com.sun.javadoc.Tag;

/* loaded from: target.jar:com/sun/tools/doclets/Taglet.class */
public interface Taglet {
    boolean inField();

    boolean inConstructor();

    boolean inMethod();

    boolean inOverview();

    boolean inPackage();

    boolean inType();

    boolean isInlineTag();

    String getName();

    String toString(Tag tag);

    String toString(Tag[] tagArr);
}
