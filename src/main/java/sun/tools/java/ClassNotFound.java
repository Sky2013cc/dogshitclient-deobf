package sun.tools.java;

/* loaded from: target.jar:sun/tools/java/ClassNotFound.class */
public class ClassNotFound extends Exception {
    public Identifier name;

    public ClassNotFound(Identifier identifier) {
        super(identifier.toString());
        this.name = identifier;
    }
}
