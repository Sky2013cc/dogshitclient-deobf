package com.sun.tools.doclets.internal.toolkit.taglets;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/taglets/BaseExecutableMemberTaglet.class */
public abstract class BaseExecutableMemberTaglet extends BaseTaglet {
    @Override // com.sun.tools.doclets.internal.toolkit.taglets.BaseTaglet, com.sun.tools.doclets.internal.toolkit.taglets.Taglet
    public boolean inField() {
        return false;
    }

    @Override // com.sun.tools.doclets.internal.toolkit.taglets.BaseTaglet, com.sun.tools.doclets.internal.toolkit.taglets.Taglet
    public boolean inOverview() {
        return false;
    }

    @Override // com.sun.tools.doclets.internal.toolkit.taglets.BaseTaglet, com.sun.tools.doclets.internal.toolkit.taglets.Taglet
    public boolean inPackage() {
        return false;
    }

    @Override // com.sun.tools.doclets.internal.toolkit.taglets.BaseTaglet, com.sun.tools.doclets.internal.toolkit.taglets.Taglet
    public boolean inType() {
        return false;
    }

    @Override // com.sun.tools.doclets.internal.toolkit.taglets.BaseTaglet, com.sun.tools.doclets.internal.toolkit.taglets.Taglet
    public boolean isInlineTag() {
        return false;
    }
}
