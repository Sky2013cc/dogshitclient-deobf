package sun.tools.java;

/* loaded from: target.jar:sun/tools/java/AmbiguousClass.class */
public class AmbiguousClass extends ClassNotFound {
    public Identifier name1;
    public Identifier name2;

    public AmbiguousClass(Identifier identifier, Identifier identifier2) {
        super(identifier.getName());
        this.name1 = identifier;
        this.name2 = identifier2;
    }
}
