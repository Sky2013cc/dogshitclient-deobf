package com.sun.tools.javap;

import com.sun.tools.classfile.AccessFlags;
import com.sun.tools.javap.InstructionDetailWriter;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/* loaded from: target.jar:com/sun/tools/javap/Options.class */
public class Options {
    public boolean help;
    public boolean verbose;
    public boolean version;
    public boolean fullVersion;
    public boolean showFlags;
    public boolean showLineAndLocalVariableTables;
    public int showAccess;
    public boolean showDisassembled;
    public boolean showDescriptors;
    public boolean showAllAttrs;
    public boolean showConstants;
    public boolean sysInfo;
    public boolean showInnerClasses;
    public Set<String> accessOptions = new HashSet();
    public Set<InstructionDetailWriter.Kind> details = EnumSet.noneOf(InstructionDetailWriter.Kind.class);
    public int indentWidth = 2;
    public int tabColumn = 40;

    public static Options instance(Context context) {
        Options options = (Options) context.get(Options.class);
        if (options == null) {
            options = new Options(context);
        }
        return options;
    }

    protected Options(Context context) {
        context.put(Options.class, this);
    }

    public boolean checkAccess(AccessFlags accessFlags) {
        boolean is = accessFlags.is(1);
        boolean is2 = accessFlags.is(4);
        boolean is3 = accessFlags.is(2);
        boolean z = (is || is2 || is3) ? false : true;
        if (this.showAccess == 1 && (is2 || is3 || z)) {
            return false;
        }
        if (this.showAccess == 4 && (is3 || z)) {
            return false;
        }
        if (this.showAccess == 0 && is3) {
            return false;
        }
        return true;
    }
}
