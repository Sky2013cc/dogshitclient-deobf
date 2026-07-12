package com.sun.source.tree;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/Scope.class */
public interface Scope {
    Scope getEnclosingScope();

    TypeElement getEnclosingClass();

    ExecutableElement getEnclosingMethod();

    Iterable<? extends Element> getLocalElements();
}
