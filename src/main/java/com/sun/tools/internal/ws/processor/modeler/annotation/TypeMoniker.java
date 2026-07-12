package com.sun.tools.internal.ws.processor.modeler.annotation;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.type.TypeMirror;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/modeler/annotation/TypeMoniker.class */
public interface TypeMoniker {
    TypeMirror create(ProcessingEnvironment processingEnvironment);
}
