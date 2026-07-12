package com.sun.tools.internal.xjc.model;

import com.sun.codemodel.internal.JPackage;
import com.sun.tools.internal.xjc.api.ClassNameAllocator;
import com.sun.tools.internal.xjc.model.CClassInfoParent;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:com/sun/tools/internal/xjc/model/ClassNameAllocatorWrapper.class */
public final class ClassNameAllocatorWrapper implements ClassNameAllocator {
    private final ClassNameAllocator core;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassNameAllocatorWrapper(ClassNameAllocator core) {
        this.core = core == null ? new ClassNameAllocator() { // from class: com.sun.tools.internal.xjc.model.ClassNameAllocatorWrapper.1
            @Override // com.sun.tools.internal.xjc.api.ClassNameAllocator
            public String assignClassName(String packageName, String className) {
                return className;
            }
        } : core;
    }

    @Override // com.sun.tools.internal.xjc.api.ClassNameAllocator
    public String assignClassName(String packageName, String className) {
        return this.core.assignClassName(packageName, className);
    }

    public String assignClassName(JPackage pkg, String className) {
        return this.core.assignClassName(pkg.name(), className);
    }

    public String assignClassName(CClassInfoParent parent, String className) {
        if (parent instanceof CClassInfoParent.Package) {
            CClassInfoParent.Package p = (CClassInfoParent.Package) parent;
            return assignClassName(p.pkg, className);
        }
        return className;
    }
}
