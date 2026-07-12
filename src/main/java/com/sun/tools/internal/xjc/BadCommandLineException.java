package com.sun.tools.internal.xjc;

import com.sun.istack.internal.Nullable;

/* loaded from: target.jar:com/sun/tools/internal/xjc/BadCommandLineException.class */
public class BadCommandLineException extends Exception {
    private Options options;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !BadCommandLineException.class.desiredAssertionStatus();
    }

    public BadCommandLineException(String msg) {
        super(msg);
    }

    public BadCommandLineException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadCommandLineException() {
        this(null);
    }

    public void initOptions(Options opt) {
        if (!$assertionsDisabled && this.options != null) {
            throw new AssertionError();
        }
        this.options = opt;
    }

    @Nullable
    public Options getOptions() {
        return this.options;
    }
}
