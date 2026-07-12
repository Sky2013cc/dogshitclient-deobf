package sun.rmi.rmic.iiop;

import java.util.Comparator;

/* compiled from: StubGenerator.java */
/* loaded from: target.jar:sun/rmi/rmic/iiop/StringComparator.class */
class StringComparator implements Comparator {
    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return ((String) obj).compareTo((String) obj2);
    }
}
