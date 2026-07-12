package sun.tools.asm;

import sun.tools.java.MemberDefinition;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:sun/tools/asm/LocalVariable.class */
public final class LocalVariable {
    MemberDefinition field;
    int slot;
    int from;
    int to;

    public LocalVariable(MemberDefinition memberDefinition, int i) {
        if (memberDefinition == null) {
            new Exception().printStackTrace();
        }
        this.field = memberDefinition;
        this.slot = i;
        this.to = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocalVariable(MemberDefinition memberDefinition, int i, int i2, int i3) {
        this.field = memberDefinition;
        this.slot = i;
        this.from = i2;
        this.to = i3;
    }

    public String toString() {
        return this.field + RuntimeConstants.SIG_PACKAGE + this.slot;
    }
}
