package com.sun.xml.internal.dtdparser;

/* loaded from: target.jar:com/sun/xml/internal/dtdparser/InternalEntity.class */
final class InternalEntity extends EntityDecl {
    char[] buf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InternalEntity(String name, char[] value) {
        this.name = name;
        this.buf = value;
    }
}
