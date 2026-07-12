package com.sun.tools.hat.internal.model;

import com.sun.tools.hat.internal.parser.ReadBuffer;
import com.sun.tools.hat.internal.util.CompositeEnumeration;
import java.util.Enumeration;
import java.util.Vector;

/* loaded from: target.jar:com/sun/tools/hat/internal/model/JavaClass.class */
public class JavaClass extends JavaHeapObject {
    private long id;
    private String name;
    private JavaThing superclass;
    private JavaThing loader;
    private JavaThing signers;
    private JavaThing protectionDomain;
    private JavaField[] fields;
    private JavaStatic[] statics;
    private static final JavaClass[] EMPTY_CLASS_ARRAY = new JavaClass[0];
    private JavaClass[] subclasses;
    private Vector<JavaHeapObject> instances;
    private Snapshot mySnapshot;
    private int instanceSize;
    private int totalNumFields;

    public JavaClass(long j, String str, long j2, long j3, long j4, long j5, JavaField[] javaFieldArr, JavaStatic[] javaStaticArr, int i) {
        this.subclasses = EMPTY_CLASS_ARRAY;
        this.instances = new Vector<>();
        this.id = j;
        this.name = str;
        this.superclass = new JavaObjectRef(j2);
        this.loader = new JavaObjectRef(j3);
        this.signers = new JavaObjectRef(j4);
        this.protectionDomain = new JavaObjectRef(j5);
        this.fields = javaFieldArr;
        this.statics = javaStaticArr;
        this.instanceSize = i;
    }

    public JavaClass(String str, long j, long j2, long j3, long j4, JavaField[] javaFieldArr, JavaStatic[] javaStaticArr, int i) {
        this(-1L, str, j, j2, j3, j4, javaFieldArr, javaStaticArr, i);
    }

    @Override // com.sun.tools.hat.internal.model.JavaHeapObject
    public final JavaClass getClazz() {
        return this.mySnapshot.getJavaLangClass();
    }

    public final int getIdentifierSize() {
        return this.mySnapshot.getIdentifierSize();
    }

    public final int getMinimumObjectSize() {
        return this.mySnapshot.getMinimumObjectSize();
    }

    @Override // com.sun.tools.hat.internal.model.JavaHeapObject
    public void resolve(Snapshot snapshot) {
        if (this.mySnapshot != null) {
            return;
        }
        this.mySnapshot = snapshot;
        resolveSuperclass(snapshot);
        if (this.superclass != null) {
            ((JavaClass) this.superclass).addSubclass(this);
        }
        this.loader = this.loader.dereference(snapshot, null);
        this.signers = this.signers.dereference(snapshot, null);
        this.protectionDomain = this.protectionDomain.dereference(snapshot, null);
        for (int i = 0; i < this.statics.length; i++) {
            this.statics[i].resolve(this, snapshot);
        }
        snapshot.getJavaLangClass().addInstance(this);
        super.resolve(snapshot);
    }

    public void resolveSuperclass(Snapshot snapshot) {
        if (this.superclass != null) {
            this.totalNumFields = this.fields.length;
            this.superclass = this.superclass.dereference(snapshot, null);
            if (this.superclass == snapshot.getNullThing()) {
                this.superclass = null;
                return;
            }
            try {
                JavaClass javaClass = (JavaClass) this.superclass;
                javaClass.resolveSuperclass(snapshot);
                this.totalNumFields += javaClass.totalNumFields;
            } catch (ClassCastException e) {
                System.out.println("Warning!  Superclass of " + this.name + " is " + this.superclass);
                this.superclass = null;
            }
        }
    }

    public boolean isString() {
        return this.mySnapshot.getJavaLangString() == this;
    }

    public boolean isClassLoader() {
        return this.mySnapshot.getJavaLangClassLoader().isAssignableFrom(this);
    }

    public JavaField getField(int i) {
        if (i < 0 || i >= this.fields.length) {
            throw new Error("No field " + i + " for " + this.name);
        }
        return this.fields[i];
    }

    public int getNumFieldsForInstance() {
        return this.totalNumFields;
    }

    public JavaField getFieldForInstance(int i) {
        if (this.superclass != null) {
            JavaClass javaClass = (JavaClass) this.superclass;
            if (i < javaClass.totalNumFields) {
                return javaClass.getFieldForInstance(i);
            }
            i -= javaClass.totalNumFields;
        }
        return getField(i);
    }

    public JavaClass getClassForField(int i) {
        if (this.superclass != null) {
            JavaClass javaClass = (JavaClass) this.superclass;
            if (i < javaClass.totalNumFields) {
                return javaClass.getClassForField(i);
            }
        }
        return this;
    }

    @Override // com.sun.tools.hat.internal.model.JavaHeapObject
    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean isArray() {
        return this.name.indexOf(91) != -1;
    }

    public Enumeration getInstances(boolean z) {
        if (z) {
            Enumeration<JavaHeapObject> elements = this.instances.elements();
            for (int i = 0; i < this.subclasses.length; i++) {
                elements = new CompositeEnumeration(elements, this.subclasses[i].getInstances(true));
            }
            return elements;
        }
        return this.instances.elements();
    }

    public int getInstancesCount(boolean z) {
        int size = this.instances.size();
        if (z) {
            for (int i = 0; i < this.subclasses.length; i++) {
                size += this.subclasses[i].getInstancesCount(z);
            }
        }
        return size;
    }

    public JavaClass[] getSubclasses() {
        return this.subclasses;
    }

    public JavaClass getSuperclass() {
        return (JavaClass) this.superclass;
    }

    public JavaThing getLoader() {
        return this.loader;
    }

    public boolean isBootstrap() {
        return this.loader == this.mySnapshot.getNullThing();
    }

    public JavaThing getSigners() {
        return this.signers;
    }

    public JavaThing getProtectionDomain() {
        return this.protectionDomain;
    }

    public JavaField[] getFields() {
        return this.fields;
    }

    public JavaField[] getFieldsForInstance() {
        Vector<JavaField> vector = new Vector<>();
        addFields(vector);
        JavaField[] javaFieldArr = new JavaField[vector.size()];
        for (int i = 0; i < vector.size(); i++) {
            javaFieldArr[i] = vector.elementAt(i);
        }
        return javaFieldArr;
    }

    public JavaStatic[] getStatics() {
        return this.statics;
    }

    public JavaThing getStaticField(String str) {
        for (int i = 0; i < this.statics.length; i++) {
            JavaStatic javaStatic = this.statics[i];
            if (javaStatic.getField().getName().equals(str)) {
                return javaStatic.getValue();
            }
        }
        return null;
    }

    @Override // com.sun.tools.hat.internal.model.JavaHeapObject, com.sun.tools.hat.internal.model.JavaThing
    public String toString() {
        return "class " + this.name;
    }

    @Override // com.sun.tools.hat.internal.model.JavaThing
    public int compareTo(JavaThing javaThing) {
        if (javaThing instanceof JavaClass) {
            return this.name.compareTo(((JavaClass) javaThing).name);
        }
        return super.compareTo(javaThing);
    }

    public boolean isAssignableFrom(JavaClass javaClass) {
        if (this == javaClass) {
            return true;
        }
        if (javaClass == null) {
            return false;
        }
        return isAssignableFrom((JavaClass) javaClass.superclass);
    }

    @Override // com.sun.tools.hat.internal.model.JavaHeapObject
    public String describeReferenceTo(JavaThing javaThing, Snapshot snapshot) {
        for (int i = 0; i < this.statics.length; i++) {
            JavaField field = this.statics[i].getField();
            if (field.hasId() && this.statics[i].getValue() == javaThing) {
                return "static field " + field.getName();
            }
        }
        return super.describeReferenceTo(javaThing, snapshot);
    }

    public int getInstanceSize() {
        return this.instanceSize + this.mySnapshot.getMinimumObjectSize();
    }

    public long getTotalInstanceSize() {
        int size = this.instances.size();
        if (size == 0 || !isArray()) {
            return size * this.instanceSize;
        }
        long j = 0;
        for (int i = 0; i < size; i++) {
            j += this.instances.elementAt(i).getSize();
        }
        return j;
    }

    @Override // com.sun.tools.hat.internal.model.JavaHeapObject, com.sun.tools.hat.internal.model.JavaThing
    public int getSize() {
        JavaClass javaLangClass = this.mySnapshot.getJavaLangClass();
        if (javaLangClass == null) {
            return 0;
        }
        return javaLangClass.getInstanceSize();
    }

    @Override // com.sun.tools.hat.internal.model.JavaHeapObject
    public void visitReferencedObjects(JavaHeapObjectVisitor javaHeapObjectVisitor) {
        super.visitReferencedObjects(javaHeapObjectVisitor);
        if (getSuperclass() != null) {
            javaHeapObjectVisitor.visit(getSuperclass());
        }
        JavaThing loader = getLoader();
        if (loader instanceof JavaHeapObject) {
            javaHeapObjectVisitor.visit((JavaHeapObject) loader);
        }
        JavaThing signers = getSigners();
        if (signers instanceof JavaHeapObject) {
            javaHeapObjectVisitor.visit((JavaHeapObject) signers);
        }
        JavaThing protectionDomain = getProtectionDomain();
        if (protectionDomain instanceof JavaHeapObject) {
            javaHeapObjectVisitor.visit((JavaHeapObject) protectionDomain);
        }
        for (int i = 0; i < this.statics.length; i++) {
            JavaField field = this.statics[i].getField();
            if (!javaHeapObjectVisitor.exclude(this, field) && field.hasId()) {
                JavaThing value = this.statics[i].getValue();
                if (value instanceof JavaHeapObject) {
                    javaHeapObjectVisitor.visit((JavaHeapObject) value);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ReadBuffer getReadBuffer() {
        return this.mySnapshot.getReadBuffer();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setNew(JavaHeapObject javaHeapObject, boolean z) {
        this.mySnapshot.setNew(javaHeapObject, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isNew(JavaHeapObject javaHeapObject) {
        return this.mySnapshot.isNew(javaHeapObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final StackTrace getSiteTrace(JavaHeapObject javaHeapObject) {
        return this.mySnapshot.getSiteTrace(javaHeapObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void addReferenceFromRoot(Root root, JavaHeapObject javaHeapObject) {
        this.mySnapshot.addReferenceFromRoot(root, javaHeapObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Root getRoot(JavaHeapObject javaHeapObject) {
        return this.mySnapshot.getRoot(javaHeapObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Snapshot getSnapshot() {
        return this.mySnapshot;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addInstance(JavaHeapObject javaHeapObject) {
        this.instances.addElement(javaHeapObject);
    }

    private void addFields(Vector<JavaField> vector) {
        if (this.superclass != null) {
            ((JavaClass) this.superclass).addFields(vector);
        }
        for (int i = 0; i < this.fields.length; i++) {
            vector.addElement(this.fields[i]);
        }
    }

    private void addSubclassInstances(Vector<JavaHeapObject> vector) {
        for (int i = 0; i < this.subclasses.length; i++) {
            this.subclasses[i].addSubclassInstances(vector);
        }
        for (int i2 = 0; i2 < this.instances.size(); i2++) {
            vector.addElement(this.instances.elementAt(i2));
        }
    }

    private void addSubclass(JavaClass javaClass) {
        JavaClass[] javaClassArr = new JavaClass[this.subclasses.length + 1];
        System.arraycopy(this.subclasses, 0, javaClassArr, 0, this.subclasses.length);
        javaClassArr[this.subclasses.length] = javaClass;
        this.subclasses = javaClassArr;
    }
}
