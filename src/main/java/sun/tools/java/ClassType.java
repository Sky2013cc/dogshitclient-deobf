package sun.tools.java;

/* loaded from: target.jar:sun/tools/java/ClassType.class */
public final class ClassType extends Type {
    Identifier className;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassType(String str, Identifier identifier) {
        super(10, str);
        this.className = identifier;
    }

    @Override // sun.tools.java.Type
    public Identifier getClassName() {
        return this.className;
    }

    @Override // sun.tools.java.Type
    public String typeString(String str, boolean z, boolean z2) {
        String identifier = (z ? getClassName().getFlatName() : Identifier.lookup(getClassName().getQualifier(), getClassName().getFlatName())).toString();
        return str.length() > 0 ? identifier + " " + str : identifier;
    }
}
