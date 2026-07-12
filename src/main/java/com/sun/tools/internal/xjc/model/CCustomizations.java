package com.sun.tools.internal.xjc.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: target.jar:com/sun/tools/internal/xjc/model/CCustomizations.class */
public final class CCustomizations extends ArrayList<CPluginCustomization> {
    CCustomizations next;
    private CCustomizable owner;
    public static final CCustomizations EMPTY;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !CCustomizations.class.desiredAssertionStatus();
        EMPTY = new CCustomizations();
    }

    public CCustomizations() {
    }

    public CCustomizations(Collection<? extends CPluginCustomization> cPluginCustomizations) {
        super(cPluginCustomizations);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setParent(Model model, CCustomizable owner) {
        if (this.owner != null) {
            return;
        }
        this.next = model.customizations;
        model.customizations = this;
        if (!$assertionsDisabled && owner == null) {
            throw new AssertionError();
        }
        this.owner = owner;
    }

    public CCustomizable getOwner() {
        if ($assertionsDisabled || this.owner != null) {
            return this.owner;
        }
        throw new AssertionError();
    }

    public CPluginCustomization find(String nsUri) {
        Iterator<CPluginCustomization> it = iterator();
        while (it.hasNext()) {
            CPluginCustomization p = it.next();
            if (fixNull(p.element.getNamespaceURI()).equals(nsUri)) {
                return p;
            }
        }
        return null;
    }

    public CPluginCustomization find(String nsUri, String localName) {
        Iterator<CPluginCustomization> it = iterator();
        while (it.hasNext()) {
            CPluginCustomization p = it.next();
            if (fixNull(p.element.getNamespaceURI()).equals(nsUri) && fixNull(p.element.getLocalName()).equals(localName)) {
                return p;
            }
        }
        return null;
    }

    private String fixNull(String s) {
        return s == null ? "" : s;
    }

    public static CCustomizations merge(CCustomizations lhs, CCustomizations rhs) {
        if (lhs == null || lhs.isEmpty()) {
            return rhs;
        }
        if (rhs == null || rhs.isEmpty()) {
            return lhs;
        }
        CCustomizations r = new CCustomizations(lhs);
        r.addAll(rhs);
        return r;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object o) {
        return this == o;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        return System.identityHashCode(this);
    }
}
