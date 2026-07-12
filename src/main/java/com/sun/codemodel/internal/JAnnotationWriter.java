package com.sun.codemodel.internal;

import java.lang.annotation.Annotation;

/* loaded from: target.jar:com/sun/codemodel/internal/JAnnotationWriter.class */
public interface JAnnotationWriter<A extends Annotation> {
    JAnnotationUse getAnnotationUse();

    Class<A> getAnnotationType();
}
