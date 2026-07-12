package com.sun.tools.internal.xjc.reader.xmlschema.bindinfo;

import java.util.Collection;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;
import org.xml.sax.Locator;

@XmlRootElement(name = "dom")
/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/xmlschema/bindinfo/BIDom.class */
public class BIDom extends AbstractDeclarationImpl {

    @XmlAttribute
    String type;
    public static final QName NAME = new QName("http://java.sun.com/xml/ns/jaxb", "dom");

    @Override // com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.AbstractDeclarationImpl, com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIDeclaration
    public /* bridge */ /* synthetic */ void markAsAcknowledged() {
        super.markAsAcknowledged();
    }

    @Override // com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.AbstractDeclarationImpl, com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIDeclaration
    public /* bridge */ /* synthetic */ Collection getChildren() {
        return super.getChildren();
    }

    @Override // com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.AbstractDeclarationImpl, com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIDeclaration
    public /* bridge */ /* synthetic */ void onSetOwner() {
        super.onSetOwner();
    }

    @Override // com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.AbstractDeclarationImpl, com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIDeclaration
    public /* bridge */ /* synthetic */ void setParent(BindInfo bindInfo) {
        super.setParent(bindInfo);
    }

    @Override // com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.AbstractDeclarationImpl, com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIDeclaration
    public /* bridge */ /* synthetic */ Locator getLocation() {
        return super.getLocation();
    }

    @Override // com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIDeclaration
    public final QName getName() {
        return NAME;
    }
}
