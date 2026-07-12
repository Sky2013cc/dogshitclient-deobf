package com.sun.tools.internal.jxc.api.impl.j2s;

import com.sun.tools.internal.xjc.api.ErrorListener;
import com.sun.tools.internal.xjc.api.J2SJAXBModel;
import com.sun.tools.internal.xjc.api.Reference;
import com.sun.xml.internal.bind.v2.model.annotation.AnnotationReader;
import com.sun.xml.internal.bind.v2.model.core.ArrayInfo;
import com.sun.xml.internal.bind.v2.model.core.ClassInfo;
import com.sun.xml.internal.bind.v2.model.core.Element;
import com.sun.xml.internal.bind.v2.model.core.ElementInfo;
import com.sun.xml.internal.bind.v2.model.core.EnumLeafInfo;
import com.sun.xml.internal.bind.v2.model.core.NonElement;
import com.sun.xml.internal.bind.v2.model.core.Ref;
import com.sun.xml.internal.bind.v2.model.core.TypeInfoSet;
import com.sun.xml.internal.bind.v2.model.nav.Navigator;
import com.sun.xml.internal.bind.v2.schemagen.XmlSchemaGenerator;
import com.sun.xml.internal.txw2.output.ResultFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;

/* loaded from: target.jar:com/sun/tools/internal/jxc/api/impl/j2s/JAXBModelImpl.class */
final class JAXBModelImpl implements J2SJAXBModel {
    private final Map<QName, Reference> additionalElementDecls;
    private final TypeInfoSet<TypeMirror, TypeElement, VariableElement, ExecutableElement> types;
    private final AnnotationReader<TypeMirror, TypeElement, VariableElement, ExecutableElement> reader;
    private XmlSchemaGenerator<TypeMirror, TypeElement, VariableElement, ExecutableElement> xsdgen;
    static final /* synthetic */ boolean $assertionsDisabled;
    private final List<String> classList = new ArrayList();
    private final Map<Reference, NonElement<TypeMirror, TypeElement>> refMap = new HashMap();

    static {
        $assertionsDisabled = !JAXBModelImpl.class.desiredAssertionStatus();
    }

    public JAXBModelImpl(TypeInfoSet<TypeMirror, TypeElement, VariableElement, ExecutableElement> types, AnnotationReader<TypeMirror, TypeElement, VariableElement, ExecutableElement> reader, Collection<Reference> rootClasses, Map<QName, Reference> additionalElementDecls) {
        this.types = types;
        this.reader = reader;
        this.additionalElementDecls = additionalElementDecls;
        Navigator<TypeMirror, TypeElement, VariableElement, ExecutableElement> navigator = types.getNavigator();
        for (ClassInfo<TypeMirror, TypeElement> i : types.beans().values()) {
            this.classList.add(i.getName());
        }
        for (ArrayInfo<TypeMirror, TypeElement> a : types.arrays().values()) {
            String javaName = navigator.getTypeName(a.getType());
            this.classList.add(javaName);
        }
        for (EnumLeafInfo<TypeMirror, TypeElement> l : types.enums().values()) {
            QName tn = l.getTypeName();
            if (tn != null) {
                String javaName2 = navigator.getTypeName(l.getType());
                this.classList.add(javaName2);
            }
        }
        for (Reference ref : rootClasses) {
            this.refMap.put(ref, getXmlType(ref));
        }
        Iterator<Map.Entry<QName, Reference>> itr = additionalElementDecls.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<QName, Reference> entry = itr.next();
            if (entry.getValue() != null) {
                ClassInfo<TypeMirror, TypeElement> xmlType = getXmlType(entry.getValue());
                if (!$assertionsDisabled && xmlType == null) {
                    throw new AssertionError();
                }
                this.refMap.put(entry.getValue(), xmlType);
                if (xmlType instanceof ClassInfo) {
                    ClassInfo<TypeMirror, TypeElement> xct = xmlType;
                    Element<TypeMirror, TypeElement> elem = xct.asElement();
                    if (elem != null && elem.getElementName().equals(entry.getKey())) {
                        itr.remove();
                    }
                }
                ElementInfo<TypeMirror, TypeElement> ei = types.getElementInfo((Object) null, entry.getKey());
                if (ei != null && ei.getContentType() == xmlType) {
                    itr.remove();
                }
            }
        }
    }

    @Override // com.sun.tools.internal.xjc.api.JAXBModel
    public List<String> getClassList() {
        return this.classList;
    }

    @Override // com.sun.tools.internal.xjc.api.J2SJAXBModel
    public QName getXmlTypeName(Reference javaType) {
        NonElement<TypeMirror, TypeElement> ti = this.refMap.get(javaType);
        if (ti != null) {
            return ti.getTypeName();
        }
        return null;
    }

    private NonElement<TypeMirror, TypeElement> getXmlType(Reference r) {
        if (r == null) {
            throw new IllegalArgumentException();
        }
        XmlJavaTypeAdapter xjta = r.annotations.getAnnotation(XmlJavaTypeAdapter.class);
        XmlList xl = r.annotations.getAnnotation(XmlList.class);
        Ref<TypeMirror, TypeElement> ref = new Ref<>(this.reader, this.types.getNavigator(), r.type, xjta, xl);
        return this.types.getTypeInfo(ref);
    }

    @Override // com.sun.tools.internal.xjc.api.J2SJAXBModel
    public void generateSchema(SchemaOutputResolver outputResolver, ErrorListener errorListener) throws IOException {
        getSchemaGenerator().write(outputResolver, errorListener);
    }

    @Override // com.sun.tools.internal.xjc.api.J2SJAXBModel
    public void generateEpisodeFile(Result output) {
        getSchemaGenerator().writeEpisodeFile(ResultFactory.createSerializer(output));
    }

    private synchronized XmlSchemaGenerator<TypeMirror, TypeElement, VariableElement, ExecutableElement> getSchemaGenerator() {
        if (this.xsdgen == null) {
            this.xsdgen = new XmlSchemaGenerator<>(this.types.getNavigator(), this.types);
            for (Map.Entry<QName, Reference> e : this.additionalElementDecls.entrySet()) {
                Reference value = e.getValue();
                if (value != null) {
                    NonElement<TypeMirror, TypeElement> typeInfo = this.refMap.get(value);
                    if (typeInfo == null) {
                        throw new IllegalArgumentException(e.getValue() + " was not specified to JavaCompiler.bind");
                    }
                    TypeMirror type = value.type;
                    this.xsdgen.add(e.getKey(), type == null || !type.getKind().isPrimitive(), typeInfo);
                } else {
                    this.xsdgen.add(e.getKey(), false, (NonElement) null);
                }
            }
        }
        return this.xsdgen;
    }
}
