package com.sun.tools.doclets.internal.toolkit.taglets;

import com.sun.tools.doclets.internal.toolkit.util.DocFinder;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/taglets/InheritableTaglet.class */
public interface InheritableTaglet extends Taglet {
    void inherit(DocFinder.Input input, DocFinder.Output output);
}
