package sun.tools.java;

/* loaded from: target.jar:sun/tools/java/IdentifierToken.class */
public class IdentifierToken {
    long where;
    int modifiers;
    Identifier id;

    public IdentifierToken(long j, Identifier identifier) {
        this.where = j;
        this.id = identifier;
    }

    public IdentifierToken(Identifier identifier) {
        this.where = 0L;
        this.id = identifier;
    }

    public IdentifierToken(long j, Identifier identifier, int i) {
        this.where = j;
        this.id = identifier;
        this.modifiers = i;
    }

    public long getWhere() {
        return this.where;
    }

    public Identifier getName() {
        return this.id;
    }

    public int getModifiers() {
        return this.modifiers;
    }

    public String toString() {
        return this.id.toString();
    }

    public static long getWhere(IdentifierToken identifierToken, long j) {
        return (identifierToken == null || identifierToken.where == 0) ? j : identifierToken.where;
    }
}
