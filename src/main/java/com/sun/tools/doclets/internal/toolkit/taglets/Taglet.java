package com.sun.tools.doclets.internal.toolkit.taglets;

import com.sun.javadoc.Doc;
import com.sun.javadoc.Tag;
import com.sun.tools.doclets.internal.toolkit.Content;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/taglets/Taglet.class */
public interface Taglet {
    boolean inField();

    boolean inConstructor();

    boolean inMethod();

    boolean inOverview();

    boolean inPackage();

    boolean inType();

    boolean isInlineTag();

    String getName();

    Content getTagletOutput(Tag tag, TagletWriter tagletWriter) throws IllegalArgumentException;

    Content getTagletOutput(Doc doc, TagletWriter tagletWriter) throws IllegalArgumentException;

    String toString();
}
