package com.sun.tools.internal.xjc.reader.gbind;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/gbind/SinkNode.class */
public final class SinkNode extends Element {
    public String toString() {
        return "#sink";
    }

    @Override // com.sun.tools.internal.xjc.reader.gbind.Element
    boolean isSink() {
        return true;
    }
}
