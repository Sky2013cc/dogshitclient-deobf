package com.sun.tools.hat.internal.model;

/* loaded from: target.jar:com/sun/tools/hat/internal/model/ReferenceChain.class */
public class ReferenceChain {
    JavaHeapObject obj;
    ReferenceChain next;

    public ReferenceChain(JavaHeapObject javaHeapObject, ReferenceChain referenceChain) {
        this.obj = javaHeapObject;
        this.next = referenceChain;
    }

    public JavaHeapObject getObj() {
        return this.obj;
    }

    public ReferenceChain getNext() {
        return this.next;
    }

    public int getDepth() {
        int i = 1;
        ReferenceChain referenceChain = this.next;
        while (true) {
            ReferenceChain referenceChain2 = referenceChain;
            if (referenceChain2 != null) {
                i++;
                referenceChain = referenceChain2.next;
            } else {
                return i;
            }
        }
    }
}
