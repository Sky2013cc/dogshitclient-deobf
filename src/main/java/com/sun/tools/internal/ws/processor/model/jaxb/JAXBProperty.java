package com.sun.tools.internal.ws.processor.model.jaxb;

import com.sun.tools.internal.xjc.api.Property;
import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/model/jaxb/JAXBProperty.class */
public class JAXBProperty {
    private String name;
    private JAXBTypeAndAnnotation type;
    private QName elementName;
    private QName rawTypeName;

    public JAXBProperty() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JAXBProperty(Property prop) {
        this.name = prop.name();
        this.type = new JAXBTypeAndAnnotation(prop.type());
        this.elementName = prop.elementName();
        this.rawTypeName = prop.rawName();
    }

    public String getName() {
        return this.name;
    }

    public QName getRawTypeName() {
        return this.rawTypeName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JAXBTypeAndAnnotation getType() {
        return this.type;
    }

    public QName getElementName() {
        return this.elementName;
    }
}
