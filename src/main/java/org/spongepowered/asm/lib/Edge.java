package org.spongepowered.asm.lib;

/* loaded from: target.jar:org/spongepowered/asm/lib/Edge.class */
class Edge {
    static final int NORMAL = 0;
    static final int EXCEPTION = Integer.MAX_VALUE;
    int info;
    Label successor;
    Edge next;
}
