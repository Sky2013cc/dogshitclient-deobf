package com.sun.xml.internal.rngom.binary.visitor;

import com.sun.xml.internal.rngom.binary.Pattern;
import com.sun.xml.internal.rngom.nc.NameClass;
import java.util.HashSet;
import java.util.Set;

/* loaded from: target.jar:com/sun/xml/internal/rngom/binary/visitor/ChildElementFinder.class */
public class ChildElementFinder extends PatternWalker {
    private final Set children = new HashSet();

    /* loaded from: target.jar:com/sun/xml/internal/rngom/binary/visitor/ChildElementFinder$Element.class */
    public static class Element {
        public final NameClass nc;
        public final Pattern content;

        public Element(NameClass nc, Pattern content) {
            this.nc = nc;
            this.content = content;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Element)) {
                return false;
            }
            Element element = (Element) o;
            if (this.content != null) {
                if (!this.content.equals(element.content)) {
                    return false;
                }
            } else if (element.content != null) {
                return false;
            }
            return this.nc != null ? this.nc.equals(element.nc) : element.nc == null;
        }

        public int hashCode() {
            int result = this.nc != null ? this.nc.hashCode() : 0;
            return (29 * result) + (this.content != null ? this.content.hashCode() : 0);
        }
    }

    public Set getChildren() {
        return this.children;
    }

    @Override // com.sun.xml.internal.rngom.binary.visitor.PatternWalker, com.sun.xml.internal.rngom.binary.visitor.PatternVisitor
    public void visitElement(NameClass nc, Pattern content) {
        this.children.add(new Element(nc, content));
    }

    @Override // com.sun.xml.internal.rngom.binary.visitor.PatternWalker, com.sun.xml.internal.rngom.binary.visitor.PatternVisitor
    public void visitAttribute(NameClass ns, Pattern value) {
    }

    @Override // com.sun.xml.internal.rngom.binary.visitor.PatternWalker, com.sun.xml.internal.rngom.binary.visitor.PatternVisitor
    public void visitList(Pattern p) {
    }
}
