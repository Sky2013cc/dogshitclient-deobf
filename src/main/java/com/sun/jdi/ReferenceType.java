package com.sun.jdi;

import java.util.List;
import java.util.Map;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/ReferenceType.class */
public interface ReferenceType extends Type, Comparable<ReferenceType>, Accessible {
    @Override // com.sun.jdi.Type
    String name();

    String genericSignature();

    ClassLoaderReference classLoader();

    String sourceName() throws AbsentInformationException;

    List<String> sourceNames(String str) throws AbsentInformationException;

    List<String> sourcePaths(String str) throws AbsentInformationException;

    String sourceDebugExtension() throws AbsentInformationException;

    boolean isStatic();

    boolean isAbstract();

    boolean isFinal();

    boolean isPrepared();

    boolean isVerified();

    boolean isInitialized();

    boolean failedToInitialize();

    List<Field> fields();

    List<Field> visibleFields();

    List<Field> allFields();

    Field fieldByName(String str);

    List<Method> methods();

    List<Method> visibleMethods();

    List<Method> allMethods();

    List<Method> methodsByName(String str);

    List<Method> methodsByName(String str, String str2);

    List<ReferenceType> nestedTypes();

    Value getValue(Field field);

    Map<Field, Value> getValues(List<? extends Field> list);

    ClassObjectReference classObject();

    List<Location> allLineLocations() throws AbsentInformationException;

    List<Location> allLineLocations(String str, String str2) throws AbsentInformationException;

    List<Location> locationsOfLine(int i) throws AbsentInformationException;

    List<Location> locationsOfLine(String str, String str2, int i) throws AbsentInformationException;

    List<String> availableStrata();

    String defaultStratum();

    List<ObjectReference> instances(long j);

    boolean equals(Object obj);

    int hashCode();

    int majorVersion();

    int minorVersion();

    int constantPoolCount();

    byte[] constantPool();
}
