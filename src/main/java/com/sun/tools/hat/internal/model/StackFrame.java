package com.sun.tools.hat.internal.model;

/* loaded from: target.jar:com/sun/tools/hat/internal/model/StackFrame.class */
public class StackFrame {
    public static final int LINE_NUMBER_UNKNOWN = -1;
    public static final int LINE_NUMBER_COMPILED = -2;
    public static final int LINE_NUMBER_NATIVE = -3;
    private String methodName;
    private String methodSignature;
    private String className;
    private String sourceFileName;
    private int lineNumber;

    public StackFrame(String str, String str2, String str3, String str4, int i) {
        this.methodName = str;
        this.methodSignature = str2;
        this.className = str3;
        this.sourceFileName = str4;
        this.lineNumber = i;
    }

    public void resolve(Snapshot snapshot) {
    }

    public String getMethodName() {
        return this.methodName;
    }

    public String getMethodSignature() {
        return this.methodSignature;
    }

    public String getClassName() {
        return this.className;
    }

    public String getSourceFileName() {
        return this.sourceFileName;
    }

    public String getLineNumber() {
        switch (this.lineNumber) {
            case -3:
                return "(native method)";
            case -2:
                return "(compiled method)";
            case -1:
                return "(unknown)";
            default:
                return Integer.toString(this.lineNumber, 10);
        }
    }
}
