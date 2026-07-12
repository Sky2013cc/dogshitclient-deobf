package com.sun.xml.internal.rngom.nc;

import com.sun.xml.internal.rngom.ast.om.ParsedNameClass;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/xml/internal/rngom/nc/NameClass.class */
public abstract class NameClass implements ParsedNameClass, Serializable {
    static final int SPECIFICITY_NONE = -1;
    static final int SPECIFICITY_ANY_NAME = 0;
    static final int SPECIFICITY_NS_NAME = 1;
    static final int SPECIFICITY_NAME = 2;
    public static final NameClass ANY = new AnyNameClass();
    public static final NameClass NULL = new NullNameClass();

    public abstract boolean contains(QName qName);

    public abstract int containsSpecificity(QName qName);

    public abstract <V> V accept(NameClassVisitor<V> nameClassVisitor);

    public abstract boolean isOpen();

    public Set<QName> listNames() {
        final Set<QName> names = new HashSet<>();
        accept(new NameClassWalker() { // from class: com.sun.xml.internal.rngom.nc.NameClass.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.sun.xml.internal.rngom.nc.NameClassWalker, com.sun.xml.internal.rngom.nc.NameClassVisitor
            public Void visitName(QName name) {
                names.add(name);
                return null;
            }
        });
        return names;
    }

    public final boolean hasOverlapWith(NameClass nc2) {
        return OverlapDetector.overlap(this, nc2);
    }
}
