package com.sun.jdi;

import java.util.List;
import java.util.Map;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/ObjectReference.class */
public interface ObjectReference extends Value {
    public static final int INVOKE_SINGLE_THREADED = 1;
    public static final int INVOKE_NONVIRTUAL = 2;

    ReferenceType referenceType();

    Value getValue(Field field);

    Map<Field, Value> getValues(List<? extends Field> list);

    void setValue(Field field, Value value) throws InvalidTypeException, ClassNotLoadedException;

    Value invokeMethod(ThreadReference threadReference, Method method, List<? extends Value> list, int i) throws InvalidTypeException, ClassNotLoadedException, IncompatibleThreadStateException, InvocationException;

    void disableCollection();

    void enableCollection();

    boolean isCollected();

    long uniqueID();

    List<ThreadReference> waitingThreads() throws IncompatibleThreadStateException;

    ThreadReference owningThread() throws IncompatibleThreadStateException;

    int entryCount() throws IncompatibleThreadStateException;

    List<ObjectReference> referringObjects(long j);

    boolean equals(Object obj);

    int hashCode();
}
