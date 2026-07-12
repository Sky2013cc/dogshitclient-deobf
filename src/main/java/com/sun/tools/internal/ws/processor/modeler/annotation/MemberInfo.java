package com.sun.tools.internal.ws.processor.modeler.annotation;

import java.lang.annotation.Annotation;
import java.util.List;
import javax.lang.model.type.TypeMirror;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/modeler/annotation/MemberInfo.class */
final class MemberInfo implements Comparable<MemberInfo> {
    private final TypeMirror paramType;
    private final String paramName;
    private final List<Annotation> jaxbAnnotations;

    public MemberInfo(TypeMirror paramType, String paramName, List<Annotation> jaxbAnnotations) {
        this.paramType = paramType;
        this.paramName = paramName;
        this.jaxbAnnotations = jaxbAnnotations;
    }

    public List<Annotation> getJaxbAnnotations() {
        return this.jaxbAnnotations;
    }

    public TypeMirror getParamType() {
        return this.paramType;
    }

    public String getParamName() {
        return this.paramName;
    }

    @Override // java.lang.Comparable
    public int compareTo(MemberInfo member) {
        return this.paramName.compareTo(member.paramName);
    }

    public boolean equals(Object o) {
        return super.equals(o);
    }

    public int hashCode() {
        int hash = (47 * 5) + (this.paramType != null ? this.paramType.hashCode() : 0);
        return (47 * ((47 * hash) + (this.paramName != null ? this.paramName.hashCode() : 0))) + (this.jaxbAnnotations != null ? this.jaxbAnnotations.hashCode() : 0);
    }
}
