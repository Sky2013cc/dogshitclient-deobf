package sun.rmi.rmic;

import sun.tools.java.Identifier;

/* loaded from: target.jar:sun/rmi/rmic/Names.class */
public class Names {
    public static final Identifier stubFor(Identifier identifier) {
        return Identifier.lookup(identifier + "_Stub");
    }

    public static final Identifier skeletonFor(Identifier identifier) {
        return Identifier.lookup(identifier + "_Skel");
    }

    public static final Identifier mangleClass(Identifier identifier) {
        if (!identifier.isInner()) {
            return identifier;
        }
        Identifier lookup = Identifier.lookup(identifier.getFlatName().toString().replace('.', '$'));
        if (lookup.isInner()) {
            throw new Error("failed to mangle inner class name");
        }
        return Identifier.lookup(identifier.getQualifier(), lookup);
    }
}
