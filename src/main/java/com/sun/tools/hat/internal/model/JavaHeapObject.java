package com.sun.tools.hat.internal.model;

import com.sun.tools.hat.internal.util.Misc;
import java.util.Enumeration;
import java.util.HashMap;

/* loaded from: target.jar:com/sun/tools/hat/internal/model/JavaHeapObject.class */
public abstract class JavaHeapObject extends JavaThing {
    private JavaThing[] referers = null;
    private int referersLen = 0;

    public abstract JavaClass getClazz();

    @Override // com.sun.tools.hat.internal.model.JavaThing
    public abstract int getSize();

    public abstract long getId();

    public void resolve(Snapshot snapshot) {
        StackTrace siteTrace = snapshot.getSiteTrace(this);
        if (siteTrace != null) {
            siteTrace.resolve(snapshot);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setupReferers() {
        if (this.referersLen > 1) {
            HashMap hashMap = new HashMap();
            for (int i = 0; i < this.referersLen; i++) {
                if (hashMap.get(this.referers[i]) == null) {
                    hashMap.put(this.referers[i], this.referers[i]);
                }
            }
            this.referers = new JavaThing[hashMap.size()];
            hashMap.keySet().toArray(this.referers);
        }
        this.referersLen = -1;
    }

    public String getIdString() {
        return Misc.toHex(getId());
    }

    @Override // com.sun.tools.hat.internal.model.JavaThing
    public String toString() {
        return getClazz().getName() + "@" + getIdString();
    }

    public StackTrace getAllocatedFrom() {
        return getClazz().getSiteTrace(this);
    }

    public boolean isNew() {
        return getClazz().isNew(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setNew(boolean z) {
        getClazz().setNew(this, z);
    }

    public void visitReferencedObjects(JavaHeapObjectVisitor javaHeapObjectVisitor) {
        javaHeapObjectVisitor.visit(getClazz());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addReferenceFrom(JavaHeapObject javaHeapObject) {
        if (this.referersLen == 0) {
            this.referers = new JavaThing[1];
        } else if (this.referersLen == this.referers.length) {
            JavaThing[] javaThingArr = new JavaThing[(3 * (this.referersLen + 1)) / 2];
            System.arraycopy(this.referers, 0, javaThingArr, 0, this.referersLen);
            this.referers = javaThingArr;
        }
        JavaThing[] javaThingArr2 = this.referers;
        int i = this.referersLen;
        this.referersLen = i + 1;
        javaThingArr2[i] = javaHeapObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addReferenceFromRoot(Root root) {
        getClazz().addReferenceFromRoot(root, this);
    }

    public Root getRoot() {
        return getClazz().getRoot(this);
    }

    public Enumeration getReferers() {
        if (this.referersLen != -1) {
            throw new RuntimeException("not resolved: " + getIdString());
        }
        return new Enumeration() { // from class: com.sun.tools.hat.internal.model.JavaHeapObject.1
            private int num = 0;

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return JavaHeapObject.this.referers != null && this.num < JavaHeapObject.this.referers.length;
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                JavaThing[] javaThingArr = JavaHeapObject.this.referers;
                int i = this.num;
                this.num = i + 1;
                return javaThingArr[i];
            }
        };
    }

    public boolean refersOnlyWeaklyTo(Snapshot snapshot, JavaThing javaThing) {
        return false;
    }

    public String describeReferenceTo(JavaThing javaThing, Snapshot snapshot) {
        return "??";
    }

    @Override // com.sun.tools.hat.internal.model.JavaThing
    public boolean isHeapAllocated() {
        return true;
    }
}
