package jdk.nashorn.internal.ir;

import jdk.nashorn.internal.ir.LexicalContextNode;

/* loaded from: target.jar:jdk/nashorn/internal/ir/Flags.class */
public interface Flags<T extends LexicalContextNode> {
    int getFlags();

    boolean getFlag(int i);

    T clearFlag(LexicalContext lexicalContext, int i);

    T setFlag(LexicalContext lexicalContext, int i);

    T setFlags(LexicalContext lexicalContext, int i);
}
