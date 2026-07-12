package com.sun.tools.internal.ws.processor.model.jaxb;

import com.sun.tools.internal.ws.processor.model.AbstractType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/model/jaxb/RpcLitStructure.class */
public class RpcLitStructure extends AbstractType {
    private List<RpcLitMember> members;
    private JAXBModel jaxbModel;

    public RpcLitStructure() {
    }

    public RpcLitStructure(QName name, JAXBModel jaxbModel) {
        setName(name);
        this.jaxbModel = jaxbModel;
        this.members = new ArrayList();
    }

    public RpcLitStructure(QName name, JAXBModel jaxbModel, List<RpcLitMember> members) {
        setName(name);
        this.members = members;
    }

    public void accept(JAXBTypeVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    public List<RpcLitMember> getRpcLitMembers() {
        return this.members;
    }

    public List<RpcLitMember> setRpcLitMembers(List<RpcLitMember> members) {
        this.members = members;
        return members;
    }

    public void addRpcLitMember(RpcLitMember member) {
        this.members.add(member);
    }

    public JAXBModel getJaxbModel() {
        return this.jaxbModel;
    }

    public void setJaxbModel(JAXBModel jaxbModel) {
        this.jaxbModel = jaxbModel;
    }

    @Override // com.sun.tools.internal.ws.processor.model.AbstractType
    public boolean isLiteralType() {
        return true;
    }
}
