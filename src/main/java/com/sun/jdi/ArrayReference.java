package com.sun.jdi;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/ArrayReference.class */
public interface ArrayReference extends ObjectReference {
    int length();

    Value getValue(int i);

    List<Value> getValues();

    List<Value> getValues(int i, int i2);

    void setValue(int i, Value value) throws InvalidTypeException, ClassNotLoadedException;

    void setValues(List<? extends Value> list) throws InvalidTypeException, ClassNotLoadedException;

    void setValues(int i, List<? extends Value> list, int i2, int i3) throws InvalidTypeException, ClassNotLoadedException;
}
