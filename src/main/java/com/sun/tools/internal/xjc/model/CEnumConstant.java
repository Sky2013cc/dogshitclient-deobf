package com.sun.tools.internal.xjc.model;

import com.sun.tools.internal.xjc.model.nav.NClass;
import com.sun.tools.internal.xjc.model.nav.NType;
import com.sun.xml.internal.bind.v2.model.core.EnumConstant;
import com.sun.xml.internal.xsom.XSComponent;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/xjc/model/CEnumConstant.class */
public final class CEnumConstant implements EnumConstant<NType, NClass>, CCustomizable {
    public final String name;
    public final String javadoc;
    private final String lexical;
    private CEnumLeafInfo parent;
    private final XSComponent source;
    private final CCustomizations customizations;
    private final Locator locator;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !CEnumConstant.class.desiredAssertionStatus();
    }

    public CEnumConstant(String name, String javadoc, String lexical, XSComponent source, CCustomizations customizations, Locator loc) {
        if (!$assertionsDisabled && name == null) {
            throw new AssertionError();
        }
        this.name = name;
        this.javadoc = javadoc;
        this.lexical = lexical;
        this.source = source;
        this.customizations = customizations;
        this.locator = loc;
    }

    /* renamed from: getEnclosingClass, reason: merged with bridge method [inline-methods] */
    public CEnumLeafInfo m308getEnclosingClass() {
        return this.parent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setParent(CEnumLeafInfo parent) {
        this.parent = parent;
    }

    public String getLexicalValue() {
        return this.lexical;
    }

    public String getName() {
        return this.name;
    }

    @Override // com.sun.tools.internal.xjc.model.CCustomizable
    public XSComponent getSchemaComponent() {
        return this.source;
    }

    @Override // com.sun.tools.internal.xjc.model.CCustomizable
    public CCustomizations getCustomizations() {
        return this.customizations;
    }

    @Override // com.sun.tools.internal.xjc.model.CCustomizable
    public Locator getLocator() {
        return this.locator;
    }
}
