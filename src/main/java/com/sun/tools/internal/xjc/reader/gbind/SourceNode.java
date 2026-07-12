package com.sun.tools.internal.xjc.reader.gbind;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/gbind/SourceNode.class */
public final class SourceNode extends Element {
    public String toString() {
        return "#source";
    }

    @Override // com.sun.tools.internal.xjc.reader.gbind.Element
    boolean isSource() {
        return true;
    }
}
