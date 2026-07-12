package com.sun.tools.internal.xjc.reader.xmlschema.bindinfo;

import java.util.Collection;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;
import org.xml.sax.Locator;

@XmlRootElement(name = "typesafeEnumMember")
/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/xmlschema/bindinfo/BIEnumMember.class */
public class BIEnumMember extends AbstractDeclarationImpl {

    @XmlAttribute
    public final String name = null;

    @XmlElement
    public final String javadoc = null;
    public static final QName NAME = new QName("http://java.sun.com/xml/ns/jaxb", "typesafeEnumMember");

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
    public QName getName() {
        return NAME;
    }
}
