package com.sun.tools.doclets.internal.toolkit.taglets;

import com.sun.javadoc.Doc;
import com.sun.javadoc.Tag;
import com.sun.tools.doclets.internal.toolkit.Content;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/taglets/BaseTaglet.class */
public abstract class BaseTaglet implements Taglet {
    protected String name = "Default";

    @Override // com.sun.tools.doclets.internal.toolkit.taglets.Taglet
    public boolean inConstructor() {
        return true;
    }

    @Override // com.sun.tools.doclets.internal.toolkit.taglets.Taglet
    public boolean inField() {
        return true;
    }

    @Override // com.sun.tools.doclets.internal.toolkit.taglets.Taglet
    public boolean inMethod() {
        return true;
    }

    @Override // com.sun.tools.doclets.internal.toolkit.taglets.Taglet
    public boolean inOverview() {
        return true;
    }

    @Override // com.sun.tools.doclets.internal.toolkit.taglets.Taglet
    public boolean inPackage() {
        return true;
    }

    @Override // com.sun.tools.doclets.internal.toolkit.taglets.Taglet
    public boolean inType() {
        return true;
    }

    @Override // com.sun.tools.doclets.internal.toolkit.taglets.Taglet
    public boolean isInlineTag() {
        return false;
    }

    @Override // com.sun.tools.doclets.internal.toolkit.taglets.Taglet
    public String getName() {
        return this.name;
    }

    @Override // com.sun.tools.doclets.internal.toolkit.taglets.Taglet
    public Content getTagletOutput(Tag tag, TagletWriter tagletWriter) {
        throw new IllegalArgumentException("Method not supported in taglet " + getName() + Constants.NAME_SEPARATOR);
    }

    @Override // com.sun.tools.doclets.internal.toolkit.taglets.Taglet
    public Content getTagletOutput(Doc doc, TagletWriter tagletWriter) {
        throw new IllegalArgumentException("Method not supported in taglet " + getName() + Constants.NAME_SEPARATOR);
    }
}
