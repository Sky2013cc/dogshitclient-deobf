package com.sun.tools.internal.ws.processor.util;

import com.sun.tools.internal.ws.processor.model.AbstractType;
import com.sun.tools.internal.ws.processor.model.Block;
import com.sun.tools.internal.ws.processor.model.ExtendedModelVisitor;
import com.sun.tools.internal.ws.processor.model.Fault;
import com.sun.tools.internal.ws.processor.model.Model;
import com.sun.tools.internal.ws.processor.model.ModelProperties;
import com.sun.tools.internal.ws.processor.model.Parameter;
import com.sun.tools.internal.ws.processor.model.Port;
import com.sun.tools.internal.ws.processor.model.Service;
import com.sun.tools.internal.ws.processor.model.jaxb.JAXBType;
import com.sun.tools.internal.ws.processor.model.jaxb.JAXBTypeVisitor;
import com.sun.tools.internal.ws.processor.model.jaxb.RpcLitStructure;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/util/ClassNameCollector.class */
public class ClassNameCollector extends ExtendedModelVisitor implements JAXBTypeVisitor {
    private Set<String> _seiClassNames;
    private Set<String> _jaxbGeneratedClassNames;
    private Set<String> _exceptionClassNames;
    boolean doneVisitingJAXBModel = false;
    private Set _allClassNames;
    private Set _exceptions;
    private Set _wsdlBindingNames;
    private Set _conflictingClassNames;
    private Set<QName> _portTypeNames;

    public void process(Model model) {
        try {
            this._allClassNames = new HashSet();
            this._exceptions = new HashSet();
            this._wsdlBindingNames = new HashSet();
            this._conflictingClassNames = new HashSet();
            this._seiClassNames = new HashSet();
            this._jaxbGeneratedClassNames = new HashSet();
            this._exceptionClassNames = new HashSet();
            this._portTypeNames = new HashSet();
            visit(model);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this._allClassNames = null;
            this._exceptions = null;
        }
    }

    public Set getConflictingClassNames() {
        return this._conflictingClassNames;
    }

    @Override // com.sun.tools.internal.ws.processor.model.ExtendedModelVisitor
    protected void postVisit(Model model) throws Exception {
        Iterator iter = model.getExtraTypes();
        while (iter.hasNext()) {
            visitType((AbstractType) iter.next());
        }
    }

    @Override // com.sun.tools.internal.ws.processor.model.ExtendedModelVisitor
    protected void preVisit(Service service) throws Exception {
        registerClassName(service.getJavaInterface().getName());
    }

    protected void processPort11x(Port port) {
        QName wsdlBindingName = (QName) port.getProperty(ModelProperties.PROPERTY_WSDL_BINDING_NAME);
        if (!this._wsdlBindingNames.contains(wsdlBindingName)) {
            registerClassName(port.getJavaInterface().getName());
        }
        registerClassName((String) port.getProperty(ModelProperties.PROPERTY_STUB_CLASS_NAME));
        registerClassName((String) port.getProperty(ModelProperties.PROPERTY_TIE_CLASS_NAME));
    }

    @Override // com.sun.tools.internal.ws.processor.model.ExtendedModelVisitor
    protected void preVisit(Port port) throws Exception {
        QName portTypeName = (QName) port.getProperty(ModelProperties.PROPERTY_WSDL_PORT_TYPE_NAME);
        if (this._portTypeNames.contains(portTypeName)) {
            return;
        }
        addSEIClassName(port.getJavaInterface().getName());
    }

    private void addSEIClassName(String s) {
        this._seiClassNames.add(s);
        registerClassName(s);
    }

    @Override // com.sun.tools.internal.ws.processor.model.ExtendedModelVisitor
    protected void postVisit(Port port) throws Exception {
        QName wsdlBindingName = (QName) port.getProperty(ModelProperties.PROPERTY_WSDL_BINDING_NAME);
        if (!this._wsdlBindingNames.contains(wsdlBindingName)) {
            this._wsdlBindingNames.add(wsdlBindingName);
        }
        QName portTypeName = (QName) port.getProperty(ModelProperties.PROPERTY_WSDL_PORT_TYPE_NAME);
        if (!this._portTypeNames.contains(portTypeName)) {
            this._portTypeNames.add(portTypeName);
        }
    }

    @Override // com.sun.tools.internal.ws.processor.model.ExtendedModelVisitor
    protected boolean shouldVisit(Port port) {
        QName wsdlBindingName = (QName) port.getProperty(ModelProperties.PROPERTY_WSDL_BINDING_NAME);
        return !this._wsdlBindingNames.contains(wsdlBindingName);
    }

    @Override // com.sun.tools.internal.ws.processor.model.ExtendedModelVisitor
    protected void preVisit(Fault fault) throws Exception {
        if (!this._exceptions.contains(fault.getJavaException())) {
            this._exceptions.add(fault.getJavaException());
            addExceptionClassName(fault.getJavaException().getName());
            Iterator iter = fault.getSubfaults();
            while (iter != null && iter.hasNext()) {
                Fault subfault = (Fault) iter.next();
                preVisit(subfault);
            }
        }
    }

    private void addExceptionClassName(String name) {
        if (this._allClassNames.contains(name)) {
            this._exceptionClassNames.add(name);
        }
        registerClassName(name);
    }

    @Override // com.sun.tools.internal.ws.processor.model.ExtendedModelVisitor
    protected void visitBodyBlock(Block block) throws Exception {
        visitBlock(block);
    }

    @Override // com.sun.tools.internal.ws.processor.model.ExtendedModelVisitor
    protected void visitHeaderBlock(Block block) throws Exception {
        visitBlock(block);
    }

    @Override // com.sun.tools.internal.ws.processor.model.ExtendedModelVisitor
    protected void visitFaultBlock(Block block) throws Exception {
    }

    protected void visitBlock(Block block) throws Exception {
        visitType(block.getType());
    }

    @Override // com.sun.tools.internal.ws.processor.model.ExtendedModelVisitor
    protected void visit(Parameter parameter) throws Exception {
        visitType(parameter.getType());
    }

    private void visitType(AbstractType type) throws Exception {
        if (type != null) {
            if (type instanceof JAXBType) {
                visitType((JAXBType) type);
            } else if (type instanceof RpcLitStructure) {
                visitType((RpcLitStructure) type);
            }
        }
    }

    private void visitType(JAXBType type) throws Exception {
        type.accept(this);
    }

    private void visitType(RpcLitStructure type) throws Exception {
        type.accept(this);
    }

    private void registerClassName(String name) {
        if (name == null || name.equals("")) {
            return;
        }
        if (this._allClassNames.contains(name)) {
            this._conflictingClassNames.add(name);
        } else {
            this._allClassNames.add(name);
        }
    }

    public Set<String> getSeiClassNames() {
        return this._seiClassNames;
    }

    public Set<String> getJaxbGeneratedClassNames() {
        return this._jaxbGeneratedClassNames;
    }

    public Set<String> getExceptionClassNames() {
        return this._exceptionClassNames;
    }

    @Override // com.sun.tools.internal.ws.processor.model.jaxb.JAXBTypeVisitor
    public void visit(JAXBType type) throws Exception {
        if (!this.doneVisitingJAXBModel && type.getJaxbModel() != null) {
            Set<String> classNames = type.getJaxbModel().getGeneratedClassNames();
            for (String className : classNames) {
                addJAXBGeneratedClassName(className);
            }
            this.doneVisitingJAXBModel = true;
        }
    }

    @Override // com.sun.tools.internal.ws.processor.model.jaxb.JAXBTypeVisitor
    public void visit(RpcLitStructure type) throws Exception {
        if (!this.doneVisitingJAXBModel) {
            Set<String> classNames = type.getJaxbModel().getGeneratedClassNames();
            for (String className : classNames) {
                addJAXBGeneratedClassName(className);
            }
            this.doneVisitingJAXBModel = true;
        }
    }

    private void addJAXBGeneratedClassName(String name) {
        this._jaxbGeneratedClassNames.add(name);
        registerClassName(name);
    }
}
