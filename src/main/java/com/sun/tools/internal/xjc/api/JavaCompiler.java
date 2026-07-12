package com.sun.tools.internal.xjc.api;

import java.util.Collection;
import java.util.Map;
import javax.annotation.processing.ProcessingEnvironment;
import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/tools/internal/xjc/api/JavaCompiler.class */
public interface JavaCompiler {
    J2SJAXBModel bind(Collection<Reference> collection, Map<QName, Reference> map, String str, ProcessingEnvironment processingEnvironment);
}
