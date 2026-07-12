package com.sun.tools.internal.ws.processor.model.jaxb;

import com.sun.tools.internal.xjc.api.Mapping;
import com.sun.tools.internal.xjc.api.Property;
import com.sun.tools.internal.xjc.api.TypeAndAnnotation;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/model/jaxb/JAXBMapping.class */
public class JAXBMapping {
    private QName elementName;
    private JAXBTypeAndAnnotation type;
    private List<JAXBProperty> wrapperStyleDrilldown;

    public JAXBMapping() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JAXBMapping(Mapping rawModel) {
        this.elementName = rawModel.getElement();
        TypeAndAnnotation typeAndAnno = rawModel.getType();
        this.type = new JAXBTypeAndAnnotation(typeAndAnno);
        List<? extends Property> list = rawModel.getWrapperStyleDrilldown();
        if (list == null) {
            this.wrapperStyleDrilldown = null;
            return;
        }
        this.wrapperStyleDrilldown = new ArrayList(list.size());
        for (Property p : list) {
            this.wrapperStyleDrilldown.add(new JAXBProperty(p));
        }
    }

    public QName getElementName() {
        return this.elementName;
    }

    public void setElementName(QName elementName) {
        this.elementName = elementName;
    }

    public JAXBTypeAndAnnotation getType() {
        return this.type;
    }

    public List<JAXBProperty> getWrapperStyleDrilldown() {
        return this.wrapperStyleDrilldown;
    }
}
