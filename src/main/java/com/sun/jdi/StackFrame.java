package com.sun.jdi;

import java.util.List;
import java.util.Map;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/StackFrame.class */
public interface StackFrame extends Mirror, Locatable {
    @Override // com.sun.jdi.Locatable
    Location location();

    ThreadReference thread();

    ObjectReference thisObject();

    List<LocalVariable> visibleVariables() throws AbsentInformationException;

    LocalVariable visibleVariableByName(String str) throws AbsentInformationException;

    Value getValue(LocalVariable localVariable);

    Map<LocalVariable, Value> getValues(List<? extends LocalVariable> list);

    void setValue(LocalVariable localVariable, Value value) throws InvalidTypeException, ClassNotLoadedException;

    List<Value> getArgumentValues();
}
