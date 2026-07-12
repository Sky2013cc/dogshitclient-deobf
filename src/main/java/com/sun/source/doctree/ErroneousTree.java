package com.sun.source.doctree;

import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/doctree/ErroneousTree.class */
public interface ErroneousTree extends TextTree {
    Diagnostic<JavaFileObject> getDiagnostic();
}
