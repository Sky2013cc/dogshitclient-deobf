package com.sun.tools.internal.xjc.reader.xmlschema.bindinfo;

import java.util.Collection;
import javax.xml.namespace.QName;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/xmlschema/bindinfo/BIDeclaration.class */
public interface BIDeclaration {
    void setParent(BindInfo bindInfo);

    QName getName();

    Locator getLocation();

    void markAsAcknowledged();

    boolean isAcknowledged();

    void onSetOwner();

    Collection<BIDeclaration> getChildren();
}
