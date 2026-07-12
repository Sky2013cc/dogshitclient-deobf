package com.sun.tools.internal.ws.processor.modeler.wsdl;

import com.sun.tools.internal.ws.processor.util.ClassNameCollector;
import com.sun.tools.internal.xjc.api.ClassNameAllocator;
import java.util.HashSet;
import java.util.Set;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/modeler/wsdl/ClassNameAllocatorImpl.class */
public class ClassNameAllocatorImpl implements ClassNameAllocator {
    private static final String TYPE_SUFFIX = "_Type";
    private ClassNameCollector classNameCollector;
    private Set<String> jaxbClasses = new HashSet();

    public ClassNameAllocatorImpl(ClassNameCollector classNameCollector) {
        this.classNameCollector = classNameCollector;
    }

    @Override // com.sun.tools.internal.xjc.api.ClassNameAllocator
    public String assignClassName(String packageName, String className) {
        if (packageName == null || className == null) {
            return className;
        }
        if (packageName.equals("") || className.equals("")) {
            return className;
        }
        String fullClassName = packageName + Constants.NAME_SEPARATOR + className;
        Set<String> seiClassNames = this.classNameCollector.getSeiClassNames();
        if (seiClassNames != null && seiClassNames.contains(fullClassName)) {
            className = className + TYPE_SUFFIX;
        }
        this.jaxbClasses.add(packageName + Constants.NAME_SEPARATOR + className);
        return className;
    }

    public Set<String> getJaxbGeneratedClasses() {
        return this.jaxbClasses;
    }
}
