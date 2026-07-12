package com.sun.tools.hat.internal.model;

/* loaded from: target.jar:com/sun/tools/hat/internal/model/StackTrace.class */
public class StackTrace {
    private StackFrame[] frames;

    public StackTrace(StackFrame[] stackFrameArr) {
        this.frames = stackFrameArr;
    }

    public StackTrace traceForDepth(int i) {
        if (i >= this.frames.length) {
            return this;
        }
        StackFrame[] stackFrameArr = new StackFrame[i];
        System.arraycopy(this.frames, 0, stackFrameArr, 0, i);
        return new StackTrace(stackFrameArr);
    }

    public void resolve(Snapshot snapshot) {
        for (int i = 0; i < this.frames.length; i++) {
            this.frames[i].resolve(snapshot);
        }
    }

    public StackFrame[] getFrames() {
        return this.frames;
    }
}
