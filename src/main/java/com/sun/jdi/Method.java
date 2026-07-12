package com.sun.jdi;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/Method.class */
public interface Method extends TypeComponent, Locatable, Comparable<Method> {
    String returnTypeName();

    Type returnType() throws ClassNotLoadedException;

    List<String> argumentTypeNames();

    List<Type> argumentTypes() throws ClassNotLoadedException;

    boolean isAbstract();

    boolean isSynchronized();

    boolean isNative();

    boolean isVarArgs();

    boolean isBridge();

    boolean isConstructor();

    boolean isStaticInitializer();

    boolean isObsolete();

    List<Location> allLineLocations() throws AbsentInformationException;

    List<Location> allLineLocations(String str, String str2) throws AbsentInformationException;

    List<Location> locationsOfLine(int i) throws AbsentInformationException;

    List<Location> locationsOfLine(String str, String str2, int i) throws AbsentInformationException;

    Location locationOfCodeIndex(long j);

    List<LocalVariable> variables() throws AbsentInformationException;

    List<LocalVariable> variablesByName(String str) throws AbsentInformationException;

    List<LocalVariable> arguments() throws AbsentInformationException;

    byte[] bytecodes();

    @Override // com.sun.jdi.Locatable
    Location location();

    boolean equals(Object obj);

    int hashCode();

    default boolean isDefault() {
        throw new UnsupportedOperationException();
    }
}
