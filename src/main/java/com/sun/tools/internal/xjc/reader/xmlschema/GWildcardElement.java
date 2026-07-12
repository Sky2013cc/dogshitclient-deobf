package com.sun.tools.internal.xjc.reader.xmlschema;

import com.sun.xml.internal.xsom.XSWildcard;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/xmlschema/GWildcardElement.class */
final class GWildcardElement extends GElement {
    private boolean strict = true;

    public String toString() {
        return "#any";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sun.tools.internal.xjc.reader.xmlschema.GElement
    public String getPropertyNameSeed() {
        return Constants.IDL_ANY;
    }

    public void merge(XSWildcard wc) {
        switch (wc.getMode()) {
            case 1:
            case 3:
                this.strict = false;
                return;
            default:
                return;
        }
    }

    public boolean isStrict() {
        return this.strict;
    }
}
