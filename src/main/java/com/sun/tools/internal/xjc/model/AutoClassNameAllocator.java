package com.sun.tools.internal.xjc.model;

import com.sun.tools.internal.xjc.api.ClassNameAllocator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: target.jar:com/sun/tools/internal/xjc/model/AutoClassNameAllocator.class */
public class AutoClassNameAllocator implements ClassNameAllocator {
    private final ClassNameAllocator core;
    private final Map<String, Set<String>> names = new HashMap();

    public AutoClassNameAllocator(ClassNameAllocator core) {
        this.core = core;
    }

    @Override // com.sun.tools.internal.xjc.api.ClassNameAllocator
    public String assignClassName(String packageName, String className) {
        String className2 = determineName(packageName, className);
        if (this.core != null) {
            className2 = this.core.assignClassName(packageName, className2);
        }
        return className2;
    }

    private String determineName(String packageName, String className) {
        Set<String> s = this.names.get(packageName);
        if (s == null) {
            s = new HashSet();
            this.names.put(packageName, s);
        }
        if (s.add(className)) {
            return className;
        }
        int i = 2;
        while (!s.add(className + i)) {
            i++;
        }
        return className + i;
    }
}
