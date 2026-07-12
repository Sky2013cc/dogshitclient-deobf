package com.sun.tools.javap;

import com.sun.tools.classfile.Instruction;

/* loaded from: target.jar:com/sun/tools/javap/InstructionDetailWriter.class */
public abstract class InstructionDetailWriter extends BasicWriter {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void writeDetails(Instruction instruction);

    /* loaded from: target.jar:com/sun/tools/javap/InstructionDetailWriter$Kind.class */
    public enum Kind {
        LOCAL_VARS("localVariables"),
        LOCAL_VAR_TYPES("localVariableTypes"),
        SOURCE("source"),
        STACKMAPS("stackMaps"),
        TRY_BLOCKS("tryBlocks"),
        TYPE_ANNOS("typeAnnotations");

        final String option;

        Kind(String str) {
            this.option = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InstructionDetailWriter(Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void flush() {
    }
}
