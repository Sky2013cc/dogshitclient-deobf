package sun.tools.java;

import java.util.Hashtable;

/* loaded from: target.jar:sun/tools/java/Identifier.class */
public final class Identifier implements Constants {
    static Hashtable hash = new Hashtable(3001, 0.5f);
    String name;
    Object value;
    Type typeObject = null;
    private int ipos;
    public static final char INNERCLASS_PREFIX = ' ';
    private static final String ambigPrefix = "<<ambiguous>>";

    private Identifier(String str) {
        this.name = str;
        this.ipos = str.indexOf(32);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getType() {
        if (this.value == null || !(this.value instanceof Integer)) {
            return 60;
        }
        return ((Integer) this.value).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setType(int i) {
        this.value = new Integer(i);
    }

    public static synchronized Identifier lookup(String str) {
        Identifier identifier = (Identifier) hash.get(str);
        if (identifier == null) {
            Hashtable hashtable = hash;
            Identifier identifier2 = new Identifier(str);
            identifier = identifier2;
            hashtable.put(str, identifier2);
        }
        return identifier;
    }

    public static Identifier lookup(Identifier identifier, Identifier identifier2) {
        if (identifier == idNull) {
            return identifier2;
        }
        if (identifier.name.charAt(identifier.name.length() - 1) == ' ') {
            return lookup(identifier.name + identifier2.name);
        }
        Identifier lookup = lookup(identifier + sun.rmi.rmic.iiop.Constants.NAME_SEPARATOR + identifier2);
        if (!identifier2.isQualified() && !identifier.isInner()) {
            lookup.value = identifier;
        }
        return lookup;
    }

    public static Identifier lookupInner(Identifier identifier, Identifier identifier2) {
        Identifier lookup;
        if (identifier.isInner()) {
            if (identifier.name.charAt(identifier.name.length() - 1) == ' ') {
                lookup = lookup(identifier.name + identifier2);
            } else {
                lookup = lookup(identifier, identifier2);
            }
        } else {
            lookup = lookup(identifier + sun.rmi.rmic.iiop.Constants.NAME_SEPARATOR + ' ' + identifier2);
        }
        lookup.value = identifier.value;
        return lookup;
    }

    public String toString() {
        return this.name;
    }

    public boolean isQualified() {
        int i;
        if (this.value == null) {
            int i2 = this.ipos;
            if (i2 <= 0) {
                i = this.name.length();
            } else {
                i = i2 - 1;
            }
            int lastIndexOf = this.name.lastIndexOf(46, i - 1);
            this.value = lastIndexOf < 0 ? idNull : lookup(this.name.substring(0, lastIndexOf));
        }
        return (this.value instanceof Identifier) && this.value != idNull;
    }

    public Identifier getQualifier() {
        return isQualified() ? (Identifier) this.value : idNull;
    }

    public Identifier getName() {
        return isQualified() ? lookup(this.name.substring(((Identifier) this.value).name.length() + 1)) : this;
    }

    public boolean isInner() {
        return this.ipos > 0;
    }

    public Identifier getFlatName() {
        if (isQualified()) {
            return getName().getFlatName();
        }
        if (this.ipos > 0 && this.name.charAt(this.ipos - 1) == '.') {
            if (this.ipos + 1 == this.name.length()) {
                return lookup(this.name.substring(0, this.ipos - 1));
            }
            return lookup(this.name.substring(0, this.ipos) + this.name.substring(this.ipos + 1));
        }
        return this;
    }

    public Identifier getTopName() {
        return !isInner() ? this : lookup(getQualifier(), getFlatName().getHead());
    }

    public Identifier getHead() {
        Identifier identifier = this;
        while (true) {
            Identifier identifier2 = identifier;
            if (identifier2.isQualified()) {
                identifier = identifier2.getQualifier();
            } else {
                return identifier2;
            }
        }
    }

    public Identifier getTail() {
        Identifier head = getHead();
        if (head == this) {
            return idNull;
        }
        return lookup(this.name.substring(head.name.length() + 1));
    }

    public boolean hasAmbigPrefix() {
        return this.name.startsWith(ambigPrefix);
    }

    public Identifier addAmbigPrefix() {
        return lookup(ambigPrefix + this.name);
    }

    public Identifier removeAmbigPrefix() {
        if (hasAmbigPrefix()) {
            return lookup(this.name.substring(ambigPrefix.length()));
        }
        return this;
    }
}
