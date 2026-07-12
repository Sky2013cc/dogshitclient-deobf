package sun.rmi.rmic.iiop;

import java.util.Hashtable;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: target.jar:sun/rmi/rmic/iiop/NameContext.class */
class NameContext {
    private Hashtable table = new Hashtable();
    private boolean allowCollisions;

    public static synchronized NameContext forName(String str, boolean z, BatchEnvironment batchEnvironment) {
        NameContext nameContext = null;
        if (str == null) {
            str = "null";
        }
        if (batchEnvironment.nameContexts == null) {
            batchEnvironment.nameContexts = new Hashtable();
        } else {
            nameContext = (NameContext) batchEnvironment.nameContexts.get(str);
        }
        if (nameContext == null) {
            nameContext = new NameContext(z);
            batchEnvironment.nameContexts.put(str, nameContext);
        }
        return nameContext;
    }

    public NameContext(boolean z) {
        this.allowCollisions = z;
    }

    public void assertPut(String str) throws Exception {
        String add = add(str);
        if (add != null) {
            throw new Exception(add);
        }
    }

    public void put(String str) {
        if (!this.allowCollisions) {
            throw new Error("Must use assertPut(name)");
        }
        add(str);
    }

    private String add(String str) {
        String lowerCase = str.toLowerCase();
        Name name = (Name) this.table.get(lowerCase);
        if (name != null) {
            if (!str.equals(name.name)) {
                if (this.allowCollisions) {
                    name.collisions = true;
                    return null;
                }
                return new String(OperatorName.SHOW_TEXT_LINE_AND_SPACE + str + "\" and \"" + name.name + OperatorName.SHOW_TEXT_LINE_AND_SPACE);
            }
            return null;
        }
        this.table.put(lowerCase, new Name(str, false));
        return null;
    }

    public String get(String str) {
        String str2 = str;
        if (((Name) this.table.get(str.toLowerCase())).collisions) {
            int length = str.length();
            boolean z = true;
            for (int i = 0; i < length; i++) {
                if (Character.isUpperCase(str.charAt(i))) {
                    str2 = (str2 + "_") + i;
                    z = false;
                }
            }
            if (z) {
                str2 = str2 + "_";
            }
        }
        return str2;
    }

    public void clear() {
        this.table.clear();
    }

    /* loaded from: target.jar:sun/rmi/rmic/iiop/NameContext$Name.class */
    public class Name {
        public String name;
        public boolean collisions;

        public Name(String str, boolean z) {
            this.name = str;
            this.collisions = z;
        }
    }
}
