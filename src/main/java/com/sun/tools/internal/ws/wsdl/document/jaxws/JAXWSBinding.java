package com.sun.tools.internal.ws.wsdl.document.jaxws;

import com.sun.tools.internal.ws.wsdl.framework.ExtensionImpl;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/document/jaxws/JAXWSBinding.class */
public class JAXWSBinding extends ExtensionImpl {
    private String wsdlNamespace;
    private String wsdlLocation;
    private String node;
    private String version;
    private CustomName jaxwsPackage;
    private List<Parameter> parameters;
    private Boolean enableWrapperStyle;
    private Boolean enableAsyncMapping;
    private Boolean enableMimeContentMapping;
    private Boolean isProvider;
    private Set<Element> jaxbBindings;
    private CustomName className;
    private CustomName methodName;

    public JAXWSBinding(Locator locator) {
        super(locator);
        this.jaxbBindings = new HashSet();
    }

    @Override // com.sun.tools.internal.ws.wsdl.framework.Entity
    public void validateThis() {
    }

    @Override // com.sun.tools.internal.ws.wsdl.framework.Elemental
    public QName getElementName() {
        return JAXWSBindingsConstants.JAXWS_BINDINGS;
    }

    public QName getWSDLElementName() {
        return getElementName();
    }

    public void addExtension(ExtensionImpl e) {
    }

    public Iterable<ExtensionImpl> extensions() {
        return null;
    }

    public Boolean isEnableAsyncMapping() {
        return this.enableAsyncMapping;
    }

    public void setEnableAsyncMapping(Boolean enableAsyncMapping) {
        this.enableAsyncMapping = enableAsyncMapping;
    }

    public Boolean isEnableMimeContentMapping() {
        return this.enableMimeContentMapping;
    }

    public void setEnableMimeContentMapping(Boolean enableMimeContentMapping) {
        this.enableMimeContentMapping = enableMimeContentMapping;
    }

    public Boolean isEnableWrapperStyle() {
        return this.enableWrapperStyle;
    }

    public void setEnableWrapperStyle(Boolean enableWrapperStyle) {
        this.enableWrapperStyle = enableWrapperStyle;
    }

    public CustomName getJaxwsPackage() {
        return this.jaxwsPackage;
    }

    public void setJaxwsPackage(CustomName jaxwsPackage) {
        this.jaxwsPackage = jaxwsPackage;
    }

    public String getNode() {
        return this.node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getWsdlLocation() {
        return this.wsdlLocation;
    }

    public void setWsdlLocation(String wsdlLocation) {
        this.wsdlLocation = wsdlLocation;
    }

    public String getWsdlNamespace() {
        return this.wsdlNamespace;
    }

    public void setWsdlNamespace(String wsdlNamespace) {
        this.wsdlNamespace = wsdlNamespace;
    }

    public Set<Element> getJaxbBindings() {
        return this.jaxbBindings;
    }

    public void addJaxbBindings(Element jaxbBinding) {
        if (this.jaxbBindings == null) {
            return;
        }
        this.jaxbBindings.add(jaxbBinding);
    }

    public Boolean isProvider() {
        return this.isProvider;
    }

    public void setProvider(Boolean isProvider) {
        this.isProvider = isProvider;
    }

    public CustomName getMethodName() {
        return this.methodName;
    }

    public void setMethodName(CustomName methodName) {
        this.methodName = methodName;
    }

    public Iterator<Parameter> parameters() {
        return this.parameters.iterator();
    }

    public void addParameter(Parameter parameter) {
        if (this.parameters == null) {
            this.parameters = new ArrayList();
        }
        this.parameters.add(parameter);
    }

    public String getParameterName(String msgName, String wsdlPartName, QName element, boolean wrapperStyle) {
        if (msgName == null || wsdlPartName == null || element == null || this.parameters == null) {
            return null;
        }
        for (Parameter param : this.parameters) {
            if (param.getMessageName().equals(msgName) && param.getPart().equals(wsdlPartName)) {
                if (wrapperStyle && param.getElement() != null) {
                    if (param.getElement().equals(element)) {
                        return param.getName();
                    }
                } else if (!wrapperStyle) {
                    return param.getName();
                }
            }
        }
        return null;
    }

    public CustomName getClassName() {
        return this.className;
    }

    public void setClassName(CustomName className) {
        this.className = className;
    }
}
