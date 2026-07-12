package com.sun.tools.internal.xjc.reader.dtd;

import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/dtd/Block.class */
public final class Block {
    final boolean isOptional;
    final boolean isRepeated;
    final Set<Element> elements = new LinkedHashSet();

    /* JADX INFO: Access modifiers changed from: package-private */
    public Block(boolean optional, boolean repeated) {
        this.isOptional = optional;
        this.isRepeated = repeated;
    }
}
