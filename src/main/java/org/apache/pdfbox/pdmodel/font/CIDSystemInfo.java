package org.apache.pdfbox.pdmodel.font;

import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;

/* loaded from: target.jar:org/apache/pdfbox/pdmodel/font/CIDSystemInfo.class */
public final class CIDSystemInfo {
    private final String registry;
    private final String ordering;
    private final int supplement;

    public CIDSystemInfo(String registry, String ordering, int supplement) {
        this.registry = registry;
        this.ordering = ordering;
        this.supplement = supplement;
    }

    public String getRegistry() {
        return this.registry;
    }

    public String getOrdering() {
        return this.ordering;
    }

    public int getSupplement() {
        return this.supplement;
    }

    public String toString() {
        return getRegistry() + TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR + getOrdering() + TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR + getSupplement();
    }
}
