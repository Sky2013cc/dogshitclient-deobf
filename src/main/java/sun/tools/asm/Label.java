package sun.tools.asm;

import jdk.internal.dynalink.CallSiteDescriptor;
import sun.tools.java.MemberDefinition;

/* loaded from: target.jar:sun/tools/asm/Label.class */
public final class Label extends Instruction {
    static int labelCount = 0;
    int ID;
    int depth;
    MemberDefinition[] locals;

    public Label() {
        super(0L, -1, (Object) null);
        int i = labelCount + 1;
        labelCount = i;
        this.ID = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Label getDestination() {
        Label label;
        Label label2;
        Label label3 = this;
        if (this.next != null && this.next != this && this.depth == 0) {
            this.depth = 1;
            switch (this.next.opc) {
                case -1:
                    label3 = ((Label) this.next).getDestination();
                    break;
                case 18:
                case 19:
                    if (this.next.value instanceof Integer) {
                        Instruction instruction = this.next.next;
                        if (instruction.opc == -1) {
                            instruction = ((Label) instruction).getDestination().next;
                        }
                        if (instruction.opc == 153) {
                            if (((Integer) this.next.value).intValue() == 0) {
                                label2 = (Label) instruction.value;
                            } else {
                                label2 = new Label();
                                label2.next = instruction.next;
                                instruction.next = label2;
                            }
                            label3 = label2.getDestination();
                            break;
                        } else if (instruction.opc == 154) {
                            if (((Integer) this.next.value).intValue() == 0) {
                                label = new Label();
                                label.next = instruction.next;
                                instruction.next = label;
                            } else {
                                label = (Label) instruction.value;
                            }
                            label3 = label.getDestination();
                            break;
                        }
                    }
                    break;
                case 167:
                    label3 = ((Label) this.next.value).getDestination();
                    break;
            }
            this.depth = 0;
        }
        return label3;
    }

    @Override // sun.tools.asm.Instruction
    public String toString() {
        String str = "$" + this.ID + CallSiteDescriptor.TOKEN_DELIMITER;
        if (this.value != null) {
            str = str + " stack=" + this.value;
        }
        return str;
    }
}
