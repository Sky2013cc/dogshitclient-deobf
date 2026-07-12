package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/Location.class */
public interface Location extends Mirror, Comparable<Location> {
    ReferenceType declaringType();

    Method method();

    long codeIndex();

    String sourceName() throws AbsentInformationException;

    String sourceName(String str) throws AbsentInformationException;

    String sourcePath() throws AbsentInformationException;

    String sourcePath(String str) throws AbsentInformationException;

    int lineNumber();

    int lineNumber(String str);

    boolean equals(Object obj);

    int hashCode();
}
