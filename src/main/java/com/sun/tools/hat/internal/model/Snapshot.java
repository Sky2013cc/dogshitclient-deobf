package com.sun.tools.hat.internal.model;

import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import com.sun.tools.hat.internal.parser.ReadBuffer;
import com.sun.tools.hat.internal.util.Misc;
import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import org.apache.fontbox.ttf.HeaderTable;
import sun.rmi.rmic.iiop.Constants;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/hat/internal/model/Snapshot.class */
public class Snapshot {
    public static long SMALL_ID_MASK = 4294967295L;
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final JavaField[] EMPTY_FIELD_ARRAY = new JavaField[0];
    private static final JavaStatic[] EMPTY_STATIC_ARRAY = new JavaStatic[0];
    private volatile Map<JavaHeapObject, Boolean> newObjects;
    private volatile Map<JavaHeapObject, StackTrace> siteTraces;
    private SoftReference<Vector> finalizablesCache;
    private JavaClass weakReferenceClass;
    private int referentFieldIndex;
    private JavaClass javaLangClass;
    private JavaClass javaLangString;
    private JavaClass javaLangClassLoader;
    private volatile JavaClass otherArrayType;
    private ReachableExcludes reachableExcludes;
    private ReadBuffer readBuf;
    private boolean hasNewSet;
    private boolean unresolvedObjectsOK;
    private boolean newStyleArrayClass;
    private int minimumObjectSize;
    private static final int DOT_LIMIT = 5000;
    private Hashtable<Number, JavaHeapObject> heapObjects = new Hashtable<>();
    private Hashtable<Number, JavaClass> fakeClasses = new Hashtable<>();
    private Vector<Root> roots = new Vector<>();
    private Map<String, JavaClass> classes = new TreeMap();
    private Map<JavaHeapObject, Root> rootsMap = new HashMap();
    private int identifierSize = 4;
    private JavaThing nullThing = new HackJavaValue("<null>", 0);

    public Snapshot(ReadBuffer readBuffer) {
        this.readBuf = readBuffer;
    }

    public void setSiteTrace(JavaHeapObject javaHeapObject, StackTrace stackTrace) {
        if (stackTrace != null && stackTrace.getFrames().length != 0) {
            initSiteTraces();
            this.siteTraces.put(javaHeapObject, stackTrace);
        }
    }

    public StackTrace getSiteTrace(JavaHeapObject javaHeapObject) {
        if (this.siteTraces != null) {
            return this.siteTraces.get(javaHeapObject);
        }
        return null;
    }

    public void setNewStyleArrayClass(boolean z) {
        this.newStyleArrayClass = z;
    }

    public boolean isNewStyleArrayClass() {
        return this.newStyleArrayClass;
    }

    public void setIdentifierSize(int i) {
        this.identifierSize = i;
        this.minimumObjectSize = 2 * i;
    }

    public int getIdentifierSize() {
        return this.identifierSize;
    }

    public int getMinimumObjectSize() {
        return this.minimumObjectSize;
    }

    public void addHeapObject(long j, JavaHeapObject javaHeapObject) {
        this.heapObjects.put(makeId(j), javaHeapObject);
    }

    public void addRoot(Root root) {
        root.setIndex(this.roots.size());
        this.roots.addElement(root);
    }

    public void addClass(long j, JavaClass javaClass) {
        addHeapObject(j, javaClass);
        putInClassesMap(javaClass);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JavaClass addFakeInstanceClass(long j, int i) {
        String str = "unknown-class<@" + Misc.toHex(j) + ">";
        int i2 = i / 4;
        int i3 = i % 4;
        JavaField[] javaFieldArr = new JavaField[i2 + i3];
        for (int i4 = 0; i4 < i2; i4++) {
            javaFieldArr[i4] = new JavaField("unknown-field-" + i4, "I");
        }
        for (int i5 = 0; i5 < i3; i5++) {
            javaFieldArr[i5 + i2] = new JavaField("unknown-field-" + i5 + i2, "B");
        }
        JavaClass javaClass = new JavaClass(str, 0L, 0L, 0L, 0L, javaFieldArr, EMPTY_STATIC_ARRAY, i);
        addFakeClass(makeId(j), javaClass);
        return javaClass;
    }

    public boolean getHasNewSet() {
        return this.hasNewSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/sun/tools/hat/internal/model/Snapshot$MyVisitor.class */
    public static class MyVisitor extends AbstractJavaHeapObjectVisitor {
        JavaHeapObject t;

        private MyVisitor() {
        }

        @Override // com.sun.tools.hat.internal.model.AbstractJavaHeapObjectVisitor, com.sun.tools.hat.internal.model.JavaHeapObjectVisitor
        public void visit(JavaHeapObject javaHeapObject) {
            javaHeapObject.addReferenceFrom(this.t);
        }
    }

    public void resolve(boolean z) {
        System.out.println("Resolving " + this.heapObjects.size() + " objects...");
        this.javaLangClass = findClass("java.lang.Class");
        if (this.javaLangClass == null) {
            System.out.println("WARNING:  hprof file does not include java.lang.Class!");
            this.javaLangClass = new JavaClass("java.lang.Class", 0L, 0L, 0L, 0L, EMPTY_FIELD_ARRAY, EMPTY_STATIC_ARRAY, 0);
            addFakeClass(this.javaLangClass);
        }
        this.javaLangString = findClass("java.lang.String");
        if (this.javaLangString == null) {
            System.out.println("WARNING:  hprof file does not include java.lang.String!");
            this.javaLangString = new JavaClass("java.lang.String", 0L, 0L, 0L, 0L, EMPTY_FIELD_ARRAY, EMPTY_STATIC_ARRAY, 0);
            addFakeClass(this.javaLangString);
        }
        this.javaLangClassLoader = findClass("java.lang.ClassLoader");
        if (this.javaLangClassLoader == null) {
            System.out.println("WARNING:  hprof file does not include java.lang.ClassLoader!");
            this.javaLangClassLoader = new JavaClass("java.lang.ClassLoader", 0L, 0L, 0L, 0L, EMPTY_FIELD_ARRAY, EMPTY_STATIC_ARRAY, 0);
            addFakeClass(this.javaLangClassLoader);
        }
        for (JavaHeapObject javaHeapObject : this.heapObjects.values()) {
            if (javaHeapObject instanceof JavaClass) {
                javaHeapObject.resolve(this);
            }
        }
        for (JavaHeapObject javaHeapObject2 : this.heapObjects.values()) {
            if (!(javaHeapObject2 instanceof JavaClass)) {
                javaHeapObject2.resolve(this);
            }
        }
        this.heapObjects.putAll(this.fakeClasses);
        this.fakeClasses.clear();
        this.weakReferenceClass = findClass("java.lang.ref.Reference");
        if (this.weakReferenceClass == null) {
            this.weakReferenceClass = findClass("sun.misc.Ref");
            this.referentFieldIndex = 0;
        } else {
            JavaField[] fieldsForInstance = this.weakReferenceClass.getFieldsForInstance();
            int i = 0;
            while (true) {
                if (i >= fieldsForInstance.length) {
                    break;
                }
                if (!"referent".equals(fieldsForInstance[i].getName())) {
                    i++;
                } else {
                    this.referentFieldIndex = i;
                    break;
                }
            }
        }
        if (z) {
            calculateReferencesToObjects();
            System.out.print("Eliminating duplicate references");
            System.out.flush();
        }
        int i2 = 0;
        Iterator<JavaHeapObject> it = this.heapObjects.values().iterator();
        while (it.hasNext()) {
            it.next().setupReferers();
            i2++;
            if (z && i2 % DOT_LIMIT == 0) {
                System.out.print(Constants.NAME_SEPARATOR);
                System.out.flush();
            }
        }
        if (z) {
            System.out.println("");
        }
        this.classes = Collections.unmodifiableMap(this.classes);
    }

    private void calculateReferencesToObjects() {
        System.out.print("Chasing references, expect " + (this.heapObjects.size() / DOT_LIMIT) + " dots");
        System.out.flush();
        int i = 0;
        MyVisitor myVisitor = new MyVisitor();
        for (JavaHeapObject javaHeapObject : this.heapObjects.values()) {
            myVisitor.t = javaHeapObject;
            javaHeapObject.visitReferencedObjects(myVisitor);
            i++;
            if (i % DOT_LIMIT == 0) {
                System.out.print(Constants.NAME_SEPARATOR);
                System.out.flush();
            }
        }
        System.out.println();
        Iterator<Root> it = this.roots.iterator();
        while (it.hasNext()) {
            Root next = it.next();
            next.resolve(this);
            JavaHeapObject findThing = findThing(next.getId());
            if (findThing != null) {
                findThing.addReferenceFromRoot(next);
            }
        }
    }

    public void markNewRelativeTo(Snapshot snapshot) {
        boolean z;
        this.hasNewSet = true;
        for (JavaHeapObject javaHeapObject : this.heapObjects.values()) {
            long id = javaHeapObject.getId();
            if (id == 0 || id == -1) {
                z = false;
            } else {
                JavaHeapObject findThing = snapshot.findThing(javaHeapObject.getId());
                if (findThing == null) {
                    z = true;
                } else {
                    z = !javaHeapObject.isSameTypeAs(findThing);
                }
            }
            javaHeapObject.setNew(z);
        }
    }

    public Enumeration<JavaHeapObject> getThings() {
        return this.heapObjects.elements();
    }

    public JavaHeapObject findThing(long j) {
        Number makeId = makeId(j);
        JavaHeapObject javaHeapObject = this.heapObjects.get(makeId);
        return javaHeapObject != null ? javaHeapObject : this.fakeClasses.get(makeId);
    }

    public JavaHeapObject findThing(String str) {
        return findThing(Misc.parseHex(str));
    }

    public JavaClass findClass(String str) {
        if (str.startsWith("0x")) {
            return (JavaClass) findThing(str);
        }
        return this.classes.get(str);
    }

    public Iterator getClasses() {
        return this.classes.values().iterator();
    }

    public JavaClass[] getClassesArray() {
        JavaClass[] javaClassArr = new JavaClass[this.classes.size()];
        this.classes.values().toArray(javaClassArr);
        return javaClassArr;
    }

    public synchronized Enumeration getFinalizerObjects() {
        Vector vector;
        if (this.finalizablesCache != null && (vector = this.finalizablesCache.get()) != null) {
            return vector.elements();
        }
        JavaThing field = ((JavaObject) findClass("java.lang.ref.Finalizer").getStaticField("queue")).getField(HeaderTable.TAG);
        Vector vector2 = new Vector();
        if (field != getNullThing()) {
            JavaObject javaObject = (JavaObject) field;
            while (true) {
                JavaHeapObject javaHeapObject = (JavaHeapObject) javaObject.getField("referent");
                JavaThing field2 = javaObject.getField("next");
                if (field2 == getNullThing() || field2.equals(javaObject)) {
                    break;
                }
                javaObject = (JavaObject) field2;
                vector2.add(javaHeapObject);
            }
        }
        this.finalizablesCache = new SoftReference<>(vector2);
        return vector2.elements();
    }

    public Enumeration<Root> getRoots() {
        return this.roots.elements();
    }

    public Root[] getRootsArray() {
        Root[] rootArr = new Root[this.roots.size()];
        this.roots.toArray(rootArr);
        return rootArr;
    }

    public Root getRootAt(int i) {
        return this.roots.elementAt(i);
    }

    public ReferenceChain[] rootsetReferencesTo(JavaHeapObject javaHeapObject, boolean z) {
        Vector vector = new Vector();
        Hashtable hashtable = new Hashtable();
        Vector vector2 = new Vector();
        hashtable.put(javaHeapObject, javaHeapObject);
        vector.addElement(new ReferenceChain(javaHeapObject, null));
        while (vector.size() > 0) {
            ReferenceChain referenceChain = (ReferenceChain) vector.elementAt(0);
            vector.removeElementAt(0);
            JavaHeapObject obj = referenceChain.getObj();
            if (obj.getRoot() != null) {
                vector2.addElement(referenceChain);
            }
            Enumeration referers = obj.getReferers();
            while (referers.hasMoreElements()) {
                JavaHeapObject javaHeapObject2 = (JavaHeapObject) referers.nextElement();
                if (javaHeapObject2 != null && !hashtable.containsKey(javaHeapObject2) && (z || !javaHeapObject2.refersOnlyWeaklyTo(this, obj))) {
                    hashtable.put(javaHeapObject2, javaHeapObject2);
                    vector.addElement(new ReferenceChain(javaHeapObject2, referenceChain));
                }
            }
        }
        ReferenceChain[] referenceChainArr = new ReferenceChain[vector2.size()];
        for (int i = 0; i < vector2.size(); i++) {
            referenceChainArr[i] = (ReferenceChain) vector2.elementAt(i);
        }
        return referenceChainArr;
    }

    public boolean getUnresolvedObjectsOK() {
        return this.unresolvedObjectsOK;
    }

    public void setUnresolvedObjectsOK(boolean z) {
        this.unresolvedObjectsOK = z;
    }

    public JavaClass getWeakReferenceClass() {
        return this.weakReferenceClass;
    }

    public int getReferentFieldIndex() {
        return this.referentFieldIndex;
    }

    public JavaThing getNullThing() {
        return this.nullThing;
    }

    public void setReachableExcludes(ReachableExcludes reachableExcludes) {
        this.reachableExcludes = reachableExcludes;
    }

    public ReachableExcludes getReachableExcludes() {
        return this.reachableExcludes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addReferenceFromRoot(Root root, JavaHeapObject javaHeapObject) {
        Root root2 = this.rootsMap.get(javaHeapObject);
        if (root2 == null) {
            this.rootsMap.put(javaHeapObject, root);
        } else {
            this.rootsMap.put(javaHeapObject, root2.mostInteresting(root));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Root getRoot(JavaHeapObject javaHeapObject) {
        return this.rootsMap.get(javaHeapObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JavaClass getJavaLangClass() {
        return this.javaLangClass;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JavaClass getJavaLangString() {
        return this.javaLangString;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JavaClass getJavaLangClassLoader() {
        return this.javaLangClassLoader;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JavaClass getOtherArrayType() {
        if (this.otherArrayType == null) {
            synchronized (this) {
                if (this.otherArrayType == null) {
                    addFakeClass(new JavaClass("[<other>", 0L, 0L, 0L, 0L, EMPTY_FIELD_ARRAY, EMPTY_STATIC_ARRAY, 0));
                    this.otherArrayType = findClass("[<other>");
                }
            }
        }
        return this.otherArrayType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JavaClass getArrayClass(String str) {
        JavaClass findClass;
        synchronized (this.classes) {
            findClass = findClass(RuntimeConstants.SIG_ARRAY + str);
            if (findClass == null) {
                findClass = new JavaClass(RuntimeConstants.SIG_ARRAY + str, 0L, 0L, 0L, 0L, EMPTY_FIELD_ARRAY, EMPTY_STATIC_ARRAY, 0);
                addFakeClass(findClass);
            }
        }
        return findClass;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReadBuffer getReadBuffer() {
        return this.readBuf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setNew(JavaHeapObject javaHeapObject, boolean z) {
        initNewObjects();
        if (z) {
            this.newObjects.put(javaHeapObject, Boolean.TRUE);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isNew(JavaHeapObject javaHeapObject) {
        return (this.newObjects == null || this.newObjects.get(javaHeapObject) == null) ? false : true;
    }

    private Number makeId(long j) {
        if (this.identifierSize == 4) {
            return new Integer((int) j);
        }
        return new Long(j);
    }

    private void putInClassesMap(JavaClass javaClass) {
        String name = javaClass.getName();
        if (this.classes.containsKey(name)) {
            String str = name + TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR + javaClass.getIdString();
        }
        this.classes.put(javaClass.getName(), javaClass);
    }

    private void addFakeClass(JavaClass javaClass) {
        putInClassesMap(javaClass);
        javaClass.resolve(this);
    }

    private void addFakeClass(Number number, JavaClass javaClass) {
        this.fakeClasses.put(number, javaClass);
        addFakeClass(javaClass);
    }

    private synchronized void initNewObjects() {
        if (this.newObjects == null) {
            synchronized (this) {
                if (this.newObjects == null) {
                    this.newObjects = new HashMap();
                }
            }
        }
    }

    private synchronized void initSiteTraces() {
        if (this.siteTraces == null) {
            synchronized (this) {
                if (this.siteTraces == null) {
                    this.siteTraces = new HashMap();
                }
            }
        }
    }
}
