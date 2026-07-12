package com.sun.tools.internal.xjc;

import com.sun.tools.internal.xjc.api.ErrorListener;
import com.sun.tools.internal.xjc.outline.Outline;

/* loaded from: target.jar:com/sun/tools/internal/xjc/XJCListener.class */
public abstract class XJCListener implements ErrorListener {
    public void generatedFile(String fileName) {
    }

    public void generatedFile(String fileName, int current, int total) {
        generatedFile(fileName);
    }

    public void message(String msg) {
    }

    public void compiled(Outline outline) {
    }

    public boolean isCanceled() {
        return false;
    }
}
