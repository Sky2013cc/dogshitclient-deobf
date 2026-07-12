package com.sun.tools.internal.xjc.reader.xmlschema.bindinfo;

import com.sun.codemodel.internal.JCodeModel;
import com.sun.tools.internal.xjc.reader.Ring;
import com.sun.tools.internal.xjc.reader.xmlschema.BGMBuilder;
import com.sun.xml.internal.bind.annotation.XmlLocation;
import com.sun.xml.internal.xsom.XSComponent;
import java.util.Collection;
import java.util.Collections;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/xmlschema/bindinfo/AbstractDeclarationImpl.class */
abstract class AbstractDeclarationImpl implements BIDeclaration {

    @XmlLocation
    Locator loc;
    protected BindInfo parent;
    private boolean isAcknowledged = false;

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public AbstractDeclarationImpl(Locator loc) {
        this.loc = loc;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractDeclarationImpl() {
    }

    @Override // com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIDeclaration
    public Locator getLocation() {
        return this.loc;
    }

    @Override // com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIDeclaration
    public void setParent(BindInfo p) {
        this.parent = p;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final XSComponent getOwner() {
        return this.parent.getOwner();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final BGMBuilder getBuilder() {
        return this.parent.getBuilder();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JCodeModel getCodeModel() {
        return (JCodeModel) Ring.get(JCodeModel.class);
    }

    @Override // com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIDeclaration
    public final boolean isAcknowledged() {
        return this.isAcknowledged;
    }

    @Override // com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIDeclaration
    public void onSetOwner() {
    }

    @Override // com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIDeclaration
    public Collection<BIDeclaration> getChildren() {
        return Collections.emptyList();
    }

    @Override // com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIDeclaration
    public void markAsAcknowledged() {
        this.isAcknowledged = true;
    }
}
