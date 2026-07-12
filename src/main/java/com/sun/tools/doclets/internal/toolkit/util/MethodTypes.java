package com.sun.tools.doclets.internal.toolkit.util;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/util/MethodTypes.class */
public enum MethodTypes {
    ALL(65535, "doclet.All_Methods", "t0", true),
    STATIC(1, "doclet.Static_Methods", "t1", false),
    INSTANCE(2, "doclet.Instance_Methods", "t2", false),
    ABSTRACT(4, "doclet.Abstract_Methods", "t3", false),
    CONCRETE(8, "doclet.Concrete_Methods", "t4", false),
    DEFAULT(16, "doclet.Default_Methods", "t5", false),
    DEPRECATED(32, "doclet.Deprecated_Methods", "t6", false);

    private final int value;
    private final String resourceKey;
    private final String tabId;
    private final boolean isDefaultTab;

    MethodTypes(int i, String str, String str2, boolean z) {
        this.value = i;
        this.resourceKey = str;
        this.tabId = str2;
        this.isDefaultTab = z;
    }

    public int value() {
        return this.value;
    }

    public String resourceKey() {
        return this.resourceKey;
    }

    public String tabId() {
        return this.tabId;
    }

    public boolean isDefaultTab() {
        return this.isDefaultTab;
    }
}
