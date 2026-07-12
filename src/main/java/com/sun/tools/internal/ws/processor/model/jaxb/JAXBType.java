package com.sun.tools.internal.ws.processor.model.jaxb;

import com.sun.tools.internal.ws.processor.model.AbstractType;
import com.sun.tools.internal.ws.processor.model.java.JavaType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/model/jaxb/JAXBType.class */
public class JAXBType extends AbstractType {
    private JAXBMapping jaxbMapping;
    private JAXBModel jaxbModel;
    private boolean unwrapped;
    private List<JAXBProperty> wrapperChildren;

    public JAXBType(JAXBType jaxbType) {
        this.unwrapped = false;
        setName(jaxbType.getName());
        this.jaxbMapping = jaxbType.getJaxbMapping();
        this.jaxbModel = jaxbType.getJaxbModel();
        init();
    }

    public JAXBType() {
        this.unwrapped = false;
    }

    public JAXBType(QName name, JavaType type) {
        super(name, type);
        this.unwrapped = false;
    }

    public JAXBType(QName name, JavaType type, JAXBMapping jaxbMapping, JAXBModel jaxbModel) {
        super(name, type);
        this.unwrapped = false;
        this.jaxbMapping = jaxbMapping;
        this.jaxbModel = jaxbModel;
        init();
    }

    public void accept(JAXBTypeVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    private void init() {
        if (this.jaxbMapping != null) {
            this.wrapperChildren = this.jaxbMapping.getWrapperStyleDrilldown();
        } else {
            this.wrapperChildren = new ArrayList();
        }
    }

    public boolean isUnwrappable() {
        return (this.jaxbMapping == null || this.jaxbMapping.getWrapperStyleDrilldown() == null) ? false : true;
    }

    public boolean hasWrapperChildren() {
        return this.wrapperChildren.size() > 0;
    }

    @Override // com.sun.tools.internal.ws.processor.model.AbstractType
    public boolean isLiteralType() {
        return true;
    }

    public List<JAXBProperty> getWrapperChildren() {
        return this.wrapperChildren;
    }

    public void setWrapperChildren(List<JAXBProperty> children) {
        this.wrapperChildren = children;
    }

    public JAXBMapping getJaxbMapping() {
        return this.jaxbMapping;
    }

    public void setJaxbMapping(JAXBMapping jaxbMapping) {
        this.jaxbMapping = jaxbMapping;
        init();
    }

    public void setUnwrapped(boolean unwrapped) {
        this.unwrapped = unwrapped;
    }

    public boolean isUnwrapped() {
        return this.unwrapped;
    }

    public JAXBModel getJaxbModel() {
        return this.jaxbModel;
    }

    public void setJaxbModel(JAXBModel jaxbModel) {
        this.jaxbModel = jaxbModel;
    }
}
